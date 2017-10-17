// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.intellij.util.io.BaseOutputReader;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import java.io.OutputStream;
import java.util.LinkedList;
import com.jetbrains.cidr.execution.debugger.backend.LLInstruction;
import com.jetbrains.cidr.execution.debugger.memory.AddressRange;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerEvaluationTimedOutException;
import com.jetbrains.cidr.execution.ipcUtils.ProtobufTimedOutException;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.debugger.backend.LLFrame;
import com.jetbrains.cidr.execution.debugger.backend.LLThread;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.backend.LLSymbolicBreakpoint;
import java.util.Collections;
import com.jetbrains.cidr.execution.debugger.backend.LLWatchpoint;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLBreakpoint;
import com.intellij.openapi.util.Ref;
import java.util.Iterator;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Map;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.util.PathUtil;
import com.intellij.execution.ExecutionFinishedException;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Broadcasts;
import java.io.IOException;
import com.jetbrains.cidr.execution.Installer;
import com.intellij.execution.process.ProcessHandler;
import com.jetbrains.cidr.execution.debugger.backend.lldb.lang.LLDBLanguage;
import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.HashSet;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Model;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerFatalException;
import java.util.concurrent.atomic.AtomicReference;
import com.jetbrains.cidr.execution.GLogOutputReaders;
import com.jetbrains.cidr.execution.debugger.backend.LLDBDriverConfiguration;
import java.util.Set;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;
import com.jetbrains.cidr.execution.ipcUtils.ProtobufServer;
import com.jetbrains.cidr.execution.ExecutionResult;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.configurations.GeneralCommandLine;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.diagnostic.Logger;
import com.google.protobuf.GeneratedMessage;
import com.intellij.util.Consumer;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;

public class LLDBDriver extends DebuggerDriver implements Consumer<GeneratedMessage>
{
    public static final Logger LOG;
    public static final Key<Boolean> ENABLE_STL_RENDERERS;
    private static final Key<Integer> LLVALUE_ID;
    private static final Key<LLValueDataLoader> LLVALUE_DATA_LOADER;
    private static final Key<LLValueData> LLVALUE_DATA;
    private static final Key<Integer> CHILDREN_COUNT_CACHE;
    public static final String NO_RESULT = "<no result>";
    private volatile boolean myRemote;
    @Nullable
    private volatile String myArchitectureId;
    private GeneralCommandLine myLLDBCommandLine;
    private OSProcessHandler myLLDBFrontendHandler;
    private final ExecutionResult<ProtobufServer<Protocol.CompositeResponse>> myConnectedClient;
    private final Set<Integer> myTemporaryBreakpoints;
    private final ProcessInputWriter myProcessInputHandler;
    private volatile Integer myAsyncAttachingTo;
    private final LLDBDriverConfiguration myStarter;
    private boolean myValuesFilteringEnabled;
    private ProtobufServer<Protocol.CompositeResponse> myProtobufServer;
    private final GLogOutputReaders myGLogOutputReaders;
    private final AtomicReference<DebuggerFatalException> myAsyncFatalException;
    private volatile long myStoppedThreadID;
    
    private void a(final String s, final Model.DispatchTarget dispatchTarget) throws ExecutionException {
        this.getProtobufClient().sendMessage(ProtobufMessageFactory.dispatchInput(s, dispatchTarget), Protocol.DispatchInput_Res.class, (com.intellij.util.Consumer<Protocol.DispatchInput_Res>)(dispatchInput_Res -> {}), null);
    }
    
    protected ProtobufServer<Protocol.CompositeResponse> getProtobufClient() throws ExecutionException {
        this.checkErrors();
        return this.myConnectedClient.get();
    }
    
    private void a(final DebuggerFatalException ex) {
        this.myAsyncFatalException.compareAndSet(null, ex);
    }
    
    @Override
    public void checkErrors() throws ExecutionException {
        final DebuggerFatalException ex = this.myAsyncFatalException.get();
        try {
            if (ex != null) {
                this.myAsyncFatalException.compareAndSet(ex, null);
                throw new DebuggerFatalException((Throwable)ex);
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
    }
    
    public LLDBDriver(final Handler handler, final LLDBDriverConfiguration myStarter) {
        super(handler);
        this.myConnectedClient = new ExecutionResult<ProtobufServer<Protocol.CompositeResponse>>();
        this.myTemporaryBreakpoints = new HashSet<Integer>();
        this.myProcessInputHandler = new ProcessInputWriter();
        this.myAsyncFatalException = new AtomicReference<DebuggerFatalException>();
        this.myStarter = myStarter;
        this.myGLogOutputReaders = new GLogOutputReaders(b(), "LLDBFrontend") {
            @Override
            protected void onTextAvailable(@NotNull final String s, @NotNull final LogType logType) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$1", "onTextAvailable"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (logType == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$1", "onTextAvailable"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (LLDBDriver.LOG.isTraceEnabled()) {
                        LLDBDriver.LOG.trace(s.trim());
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    @NotNull
    private static File b() {
        String s = null;
        Label_0046: {
            try {
                if (ApplicationManager.getApplication().isUnitTestMode()) {
                    s = PathManager.getSystemPath() + "/testlog";
                    break Label_0046;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            s = PathManager.getLogPath();
        }
        final File file = new File(s);
        if (file == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getLogDir"));
        }
        return file;
    }
    
    @Override
    public boolean supportsWatchpointLifetime() {
        return false;
    }
    
    @Override
    public boolean supportsDisasm() {
        return false;
    }
    
    @NotNull
    @Override
    public Language getConsoleLanguage() {
        LLDBLanguage instance;
        try {
            instance = LLDBLanguage.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getConsoleLanguage"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return instance;
    }
    
    @NotNull
    @Override
    public ProcessHandler getProcessHandler() {
        OSProcessHandler myLLDBFrontendHandler;
        try {
            myLLDBFrontendHandler = this.myLLDBFrontendHandler;
            if (myLLDBFrontendHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getProcessHandler"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return (ProcessHandler)myLLDBFrontendHandler;
    }
    
    @Override
    public boolean isInPromptMode() {
        return false;
    }
    
    public int getPort() {
        return this.myProtobufServer.getPort();
    }
    
    @Override
    public void start(@NotNull final Installer myInstaller, @Nullable final String myArchitectureId) throws ExecutionException {
        try {
            if (myInstaller == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "installer", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "start"));
            }
        }
        catch (IOException ex) {
            throw c(ex);
        }
        this.myInstaller = myInstaller;
        this.myArchitectureId = myArchitectureId;
        try {
            this.myProtobufServer = new ProtobufServer<Protocol.CompositeResponse>(this, new ProtobufServer.ProtobufParser<Protocol.CompositeResponse>() {
                @Override
                public Protocol.CompositeResponse parse(final byte[] array) throws IOException {
                    return Protocol.CompositeResponse.parseFrom(array);
                }
                
                @Override
                public boolean decompose(final GeneratedMessage generatedMessage) {
                    return generatedMessage instanceof Protocol.CompositeResponse || generatedMessage instanceof Broadcasts.CompositeBroadcast;
                }
            }) {
                @Override
                protected void handleIOException(final IOException exception) {
                    CidrDebuggerLog.LOG.warn((Throwable)exception);
                    if (!LLDBDriver.this.myConnectedClient.isDone()) {
                        LLDBDriver.this.myConnectedClient.setException(exception);
                    }
                    else {
                        LLDBDriver.this.a(new DebuggerFatalException(exception));
                    }
                }
            };
        }
        catch (IOException ex2) {
            throw new ExecutionException((Throwable)ex2);
        }
        this.myLLDBCommandLine = this.myStarter.createDriverCommandLine(this, myInstaller);
        final Map environment = this.myLLDBCommandLine.getEnvironment();
        Label_0195: {
            try {
                environment.put("GLOG_log_dir", this.myGLogOutputReaders.getLogDir().getPath());
                if (LLDBDriver.LOG.isTraceEnabled()) {
                    environment.put("GLOG_minloglevel", "0");
                    environment.put("GLOG_logbufsecs", "0");
                    environment.put("GLOG_v", "1");
                    this.myGLogOutputReaders.init();
                    break Label_0195;
                }
            }
            catch (IOException ex3) {
                throw c(ex3);
            }
            environment.put("GLOG_minloglevel", "2");
        }
        (this.myLLDBFrontendHandler = new LLDBProcessHandler(this.myLLDBCommandLine)).setShouldDestroyProcessRecursively(false);
        this.myLLDBFrontendHandler.addProcessListener((ProcessListener)new ProcessAdapter() {
            public void processTerminated(final ProcessEvent processEvent) {
                if (!LLDBDriver.this.myConnectedClient.isDone()) {
                    LLDBDriver.this.myConnectedClient.setException((Throwable)new ExecutionFinishedException());
                }
                LLDBDriver.this.myProcessInputHandler.close();
                LLDBDriver.this.handleExited(processEvent.getExitCode());
            }
            
            public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
                final String text = processEvent.getText();
                if (text != null && CidrDebuggerLog.LOG.isDebugEnabled()) {
                    CidrDebuggerLog.LOG.debug(PathUtil.getFileName(LLDBDriver.this.myLLDBCommandLine.getExePath()) + ": " + text);
                }
                if (key == ProcessOutputTypes.STDERR) {
                    LLDBDriver.this.handleTargetOutput(processEvent.getText(), key);
                }
            }
        });
    }
    
    @Override
    protected void closeOutputReaders() {
        super.closeOutputReaders();
        this.myGLogOutputReaders.close();
    }
    
    @Override
    public void setValuesFilteringEnabled(final boolean myValuesFilteringEnabled) throws ExecutionException {
        try {
            if (this.myValuesFilteringEnabled == myValuesFilteringEnabled) {
                return;
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        a(this.myValuesFilteringEnabled = myValuesFilteringEnabled, this.getProtobufClient());
    }
    
    private static void a(final boolean valuesFilteringEnabled, final ProtobufServer<Protocol.CompositeResponse> protobufServer) throws ExecutionException {
        protobufServer.sendMessageAndWaitForReply(ProtobufMessageFactory.setValuesFilteringEnabled(valuesFilteringEnabled), Protocol.ValuesFilteringPolicy_Res.class, (com.intellij.util.Consumer<Protocol.ValuesFilteringPolicy_Res>)new ThrowIfNotValid("couldn't set values filtering policy"));
    }
    
    @Override
    public void loadForLaunch() throws ExecutionException {
        this.myTargetCommandLine = this.myInstaller.install();
        this.sendCreateTargetRequest(ProtobufMessageFactory.createTarget(this.myInstaller.getExecutableFile().getPath(), StringUtil.notNullize(this.myArchitectureId)));
    }
    
    @Override
    public void loadForAttach() throws ExecutionException {
        this.sendCreateTargetRequest(ProtobufMessageFactory.createTarget("", ""));
    }
    
    public void loadForRemoteLaunch(@NotNull final File file, @NotNull final List<PathMapping> list) throws ExecutionException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sysroot", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "loadForRemoteLaunch"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pathMappings", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "loadForRemoteLaunch"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        this.myRemote = true;
        this.myTargetCommandLine = this.myInstaller.install();
        final String path = this.myInstaller.getExecutableFile().getPath();
        final String exePath = this.myTargetCommandLine.getExePath();
        CidrDebuggerLog.LOG.debug("creating remote target: exepath: " + path + " remotePath: " + exePath + " arch: " + StringUtil.notNullize(this.myArchitectureId));
        this.sendCreateTargetRequest(ProtobufMessageFactory.createRemoteTarget(path, StringUtil.notNullize(this.myArchitectureId), "remote-ios", file.getAbsolutePath(), exePath));
        this.b(list);
    }
    
    private void b(@NotNull final List<PathMapping> list) throws ExecutionException {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mappings", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "addPathMapping"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final StringBuilder sb = new StringBuilder();
        for (final PathMapping pathMapping : list) {
            sb.append("\"").append(pathMapping.from).append("\" ");
            sb.append("\"").append(pathMapping.to).append("\" ");
        }
        this.myProtobufServer.sendMessageAndWaitForReply(ProtobufMessageFactory.handleConsoleCommand(-1L, -1, "target modules search-paths add " + sb.toString()), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)new ThrowIfNotValid(CidrDebuggerBundle.message("debug.command.error.cannotAddModulesSearchPaths", new Object[0])), 2147483647L);
    }
    
    protected void sendCreateTargetRequest(@NotNull final Protocol.CompositeRequest compositeRequest) throws ExecutionException {
        try {
            if (compositeRequest == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "createTargetRequest", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "sendCreateTargetRequest"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.CreateTarget_Res>("Couldn't create target");
        this.getProtobufClient().sendMessageAndWaitForReply(compositeRequest, Protocol.CreateTarget_Res.class, (com.intellij.util.Consumer<Protocol.CreateTarget_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
    }
    
    @Override
    public long launch() throws ExecutionException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/intellij/openapi/util/Ref;
        //     3: dup            
        //     4: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //     7: astore_1       
        //     8: new             Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$5;
        //    11: dup            
        //    12: aload_0        
        //    13: ldc             "Couldn't launch process"
        //    15: aload_1        
        //    16: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$5.<init>:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;Ljava/lang/String;Lcom/intellij/openapi/util/Ref;)V
        //    19: astore_2       
        //    20: getstatic       com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.$assertionsDisabled:Z
        //    23: ifne            54
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myTargetCommandLine:Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    30: ifnonnull       54
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    39: athrow         
        //    40: new             Ljava/lang/AssertionError;
        //    43: dup            
        //    44: ldc             "Not Installed"
        //    46: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //    49: athrow         
        //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    53: athrow         
        //    54: aload_0        
        //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.getDebugServerSocket:()Ljava/lang/String;
        //    58: astore          4
        //    60: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    63: aload           4
        //    65: ifnonnull       82
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myRemote:Z
        //    72: ifne            90
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    81: athrow         
        //    82: iconst_1       
        //    83: goto            91
        //    86: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    89: athrow         
        //    90: iconst_0       
        //    91: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(Z)Z
        //    94: pop            
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myRemote:Z
        //    99: ifne            260
        //   102: aload_0        
        //   103: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myToRedirect:Z
        //   106: ifeq            142
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   115: athrow         
        //   116: aload_0        
        //   117: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //   120: ifne            138
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   129: athrow         
        //   130: iconst_1       
        //   131: goto            139
        //   134: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   137: athrow         
        //   138: iconst_0       
        //   139: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.initReaders:(Z)V
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myReaders:Lcom/jetbrains/cidr/execution/ProcessOutputReaders;
        //   146: ifnull          163
        //   149: aload_0        
        //   150: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myReaders:Lcom/jetbrains/cidr/execution/ProcessOutputReaders;
        //   153: invokevirtual   com/jetbrains/cidr/execution/ProcessOutputReaders.getOutFileAbsolutePath:()Ljava/lang/String;
        //   156: goto            164
        //   159: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   162: athrow         
        //   163: aconst_null    
        //   164: astore          5
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myReaders:Lcom/jetbrains/cidr/execution/ProcessOutputReaders;
        //   170: ifnull          187
        //   173: aload_0        
        //   174: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myReaders:Lcom/jetbrains/cidr/execution/ProcessOutputReaders;
        //   177: invokevirtual   com/jetbrains/cidr/execution/ProcessOutputReaders.getErrFileAbsolutePath:()Ljava/lang/String;
        //   180: goto            188
        //   183: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   186: athrow         
        //   187: aconst_null    
        //   188: astore          6
        //   190: ldc             "process_pipe_dir"
        //   192: aconst_null    
        //   193: invokestatic    com/intellij/openapi/util/io/FileUtil.createTempDirectory:(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
        //   196: astore          7
        //   198: goto            220
        //   201: astore          8
        //   203: new             Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriverException;
        //   206: dup            
        //   207: ldc             "debug.lldb.cannotCreatePipe"
        //   209: iconst_0       
        //   210: anewarray       Ljava/lang/Object;
        //   213: invokestatic    com/jetbrains/cidr/execution/CidrDebuggerBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   216: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriverException.<init>:(Ljava/lang/String;)V
        //   219: athrow         
        //   220: aload_0        
        //   221: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myProcessInputHandler:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter;
        //   224: aload           7
        //   226: ldc             "process_pipe"
        //   228: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.initPipeInput:(Ljava/io/File;Ljava/lang/String;)V
        //   231: aload_0        
        //   232: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myTargetCommandLine:Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   235: new             Ljava/io/File;
        //   238: dup            
        //   239: aload           7
        //   241: ldc             "process_pipe"
        //   243: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   246: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   249: aload           5
        //   251: aload           6
        //   253: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory.launch:(Lcom/intellij/execution/configurations/GeneralCommandLine;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest;
        //   256: astore_3       
        //   257: goto            289
        //   260: aload_0        
        //   261: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myProcessInputHandler:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter;
        //   264: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.initDispatchInput:()V
        //   267: aload_0        
        //   268: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myInstaller:Lcom/jetbrains/cidr/execution/Installer;
        //   271: invokeinterface com/jetbrains/cidr/execution/Installer.getExecutableFile:()Ljava/io/File;
        //   276: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   279: aload_0        
        //   280: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myTargetCommandLine:Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   283: aload           4
        //   285: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory.remoteLaunch:(Ljava/lang/String;Lcom/intellij/execution/configurations/GeneralCommandLine;Ljava/lang/String;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest;
        //   288: astore_3       
        //   289: aload_0        
        //   290: aload_0        
        //   291: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myTargetCommandLine:Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   294: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.printTargetCommandLine:(Lcom/intellij/execution/configurations/GeneralCommandLine;)V
        //   297: aload_0        
        //   298: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.getProtobufClient:()Lcom/jetbrains/cidr/execution/ipcUtils/ProtobufServer;
        //   301: aload_3        
        //   302: ldc             Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;.class
        //   304: aload_2        
        //   305: invokevirtual   com/jetbrains/cidr/execution/ipcUtils/ProtobufServer.sendMessageAndWaitForReply:(Lcom/google/protobuf/GeneratedMessage;Ljava/lang/Class;Lcom/intellij/util/Consumer;)V
        //   308: aload_0        
        //   309: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.myRemote:Z
        //   312: ifeq            385
        //   315: aload_2        
        //   316: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ThrowIfNotValid.isValid:()Z
        //   319: ifne            385
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   328: athrow         
        //   329: ldc             "debug.lldb.lockedDeviceResponse"
        //   331: iconst_0       
        //   332: anewarray       Ljava/lang/Object;
        //   335: invokestatic    com/jetbrains/cidr/execution/CidrDebuggerBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   338: aload_2        
        //   339: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ThrowIfNotValid.getMessage:()Ljava/lang/String;
        //   342: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   345: ifeq            385
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   354: athrow         
        //   355: new             Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriverException;
        //   358: dup            
        //   359: ldc             "debug.lldb.lockedDeviceUserMessage"
        //   361: iconst_1       
        //   362: anewarray       Ljava/lang/Object;
        //   365: dup            
        //   366: iconst_0       
        //   367: invokestatic    com/intellij/openapi/application/ApplicationNamesInfo.getInstance:()Lcom/intellij/openapi/application/ApplicationNamesInfo;
        //   370: invokevirtual   com/intellij/openapi/application/ApplicationNamesInfo.getProductName:()Ljava/lang/String;
        //   373: aastore        
        //   374: invokestatic    com/jetbrains/cidr/execution/CidrDebuggerBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   377: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriverException.<init>:(Ljava/lang/String;)V
        //   380: athrow         
        //   381: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   384: athrow         
        //   385: aload_2        
        //   386: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ThrowIfNotValid.throwIfNeeded:()V
        //   389: aload_0        
        //   390: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.handlePrompt:()V
        //   393: aload_1        
        //   394: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   397: checkcast       Ljava/lang/Long;
        //   400: invokevirtual   java/lang/Long.longValue:()J
        //   403: lreturn        
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  166    183    183    187    Ljava/io/IOException;
        //  142    159    159    163    Ljava/io/IOException;
        //  116    134    134    138    Ljava/io/IOException;
        //  102    123    126    130    Ljava/io/IOException;
        //  91     109    112    116    Ljava/io/IOException;
        //  68     86     86     90     Ljava/io/IOException;
        //  60     75     78     82     Ljava/io/IOException;
        //  26     50     50     54     Ljava/io/IOException;
        //  20     33     36     40     Ljava/io/IOException;
        //  190    198    201    220    Ljava/io/IOException;
        //  289    322    325    329    Ljava/io/IOException;
        //  315    348    351    355    Ljava/io/IOException;
        //  329    381    381    385    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void attachTo(final int n) throws ExecutionException {
        this.myAsyncAttachingTo = n;
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.Attach_Res>("Couldn't attach to process");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.attach(n), Protocol.Attach_Res.class, (com.intellij.util.Consumer<Protocol.Attach_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
        this.handlePrompt();
    }
    
    @Override
    public void attachByName(final String s, final boolean b) throws ExecutionException {
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.AttachByName_Res>("Couldn't attach to process");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.attachByName(s, b), Protocol.AttachByName_Res.class, (com.intellij.util.Consumer<Protocol.AttachByName_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
        this.handlePrompt();
    }
    
    @Override
    public void detach() throws ExecutionException {
        final ThrowIfNotValid<Protocol.Detach_Res> throwIfNotValid = new ThrowIfNotValid<Protocol.Detach_Res>("Couldn't detach process");
        Label_0049: {
            try {
                this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.detach(), Protocol.Detach_Res.class, (com.intellij.util.Consumer<Protocol.Detach_Res>)throwIfNotValid);
                if (throwIfNotValid.isValid()) {
                    break Label_0049;
                }
                final String s = "Sending disconnect packet failed.";
                final ThrowIfNotValid<Protocol.Detach_Res> throwIfNotValid2 = throwIfNotValid;
                final String s2 = throwIfNotValid2.getMessage();
                final boolean b = s.equals(s2);
                if (!b) {
                    break Label_0049;
                }
                break Label_0049;
            }
            catch (ExecutionException ex) {
                throw c((Exception)ex);
            }
            try {
                final String s = "Sending disconnect packet failed.";
                final ThrowIfNotValid<Protocol.Detach_Res> throwIfNotValid2 = throwIfNotValid;
                final String s2 = throwIfNotValid2.getMessage();
                final boolean b = s.equals(s2);
                if (!b) {
                    throwIfNotValid.throwIfNeeded();
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
        }
        this.handleDetached();
    }
    
    @Override
    public boolean interrupt() throws ExecutionException {
        final Ref ref = new Ref();
        final Protocol.CompositeRequest suspend = ProtobufMessageFactory.suspend();
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(suspend, Protocol.Suspend_Res.class, (com.intellij.util.Consumer<Protocol.Suspend_Res>)(suspend_Res -> ref.set((Object)suspend_Res.getCommonResponse().getIsValid())));
            if (ref.isNull()) {
                return false;
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return (boolean)ref.get();
    }
    
    @Override
    public boolean resume() throws ExecutionException {
        final boolean[] array = { false };
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.resume(), Protocol.Continue_Res.class, (com.intellij.util.Consumer<Protocol.Continue_Res>)(continue_Res -> array[0] = continue_Res.getCommonResponse().getIsValid()));
        return array[0];
    }
    
    @Override
    public void stepOver(@Nullable final Boolean b) throws ExecutionException {
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.StepOver_Res>("Error stepping over");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.stepOver(this.myStoppedThreadID), Protocol.StepOver_Res.class, (com.intellij.util.Consumer<Protocol.StepOver_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
    }
    
    @Override
    public void stepInto(final boolean b, @Nullable final Boolean b2) throws ExecutionException {
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.StepInto_Res>("Error stepping into");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.stepInto(this.myStoppedThreadID), Protocol.StepInto_Res.class, (com.intellij.util.Consumer<Protocol.StepInto_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
    }
    
    @Override
    public void stepOut() throws ExecutionException {
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.StepOut_Res>("Error stepping out");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.stepOut(this.myStoppedThreadID), Protocol.StepOut_Res.class, (com.intellij.util.Consumer<Protocol.StepOut_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
    }
    
    @Override
    public void runTo(final String s, final int n) throws ExecutionException {
        synchronized (this.myTemporaryBreakpoints) {
            try {
                final Iterator<LLBreakpoint> iterator = this.addBreakpoint(s, n).iterator();
                while (iterator.hasNext()) {
                    this.myTemporaryBreakpoints.add(iterator.next().getId());
                }
            }
            catch (DebuggerCommandException ex) {
                throw new ExecutionException("Cannot set a breakpoint", (Throwable)ex);
            }
            try {
                if (!this.resume()) {
                    throw new ExecutionException("Couldn't resume program");
                }
            }
            catch (DebuggerCommandException ex2) {
                throw c(ex2);
            }
        }
    }
    
    private void a() throws ExecutionException {
        synchronized (this.myTemporaryBreakpoints) {
            final Iterator<Integer> iterator = this.myTemporaryBreakpoints.iterator();
            while (iterator.hasNext()) {
                this.getProtobufClient().sendMessage(a(iterator.next()), Protocol.RemoveBreakpoint_Res.class, (com.intellij.util.Consumer<Protocol.RemoveBreakpoint_Res>)(removeBreakpoint_Res -> {
                    try {
                        if (!removeBreakpoint_Res.getCommonResponse().getIsValid()) {
                            CidrDebuggerLog.LOG.error("Couldn't remove breakpoint. error: " + removeBreakpoint_Res.getCommonResponse().getErrorMessage());
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw c(ex);
                    }
                }), null);
            }
            this.myTemporaryBreakpoints.clear();
        }
    }
    
    @Override
    public boolean abort() throws ExecutionException {
        final Ref create = Ref.create();
        final Ref create2 = Ref.create((Object)false);
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.kill(), Protocol.Kill_Res.class, (com.intellij.util.Consumer<Protocol.Kill_Res>)(kill_Res -> {
            final Protocol.CommonResponse commonResponse = kill_Res.getCommonResponse();
            try {
                if (commonResponse.getIsValid()) {
                    create2.set((Object)true);
                    return;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            String errorMessage = commonResponse.getErrorMessage();
            try {
                if ("process not exist".equals(errorMessage)) {
                    create2.set((Object)false);
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
            if (StringUtil.isEmptyOrSpaces(errorMessage)) {
                errorMessage = "Failed to abort process";
            }
            create.set((Object)new LLDBDriverException(errorMessage));
        }));
        return (boolean)create2.get();
    }
    
    @NotNull
    @Override
    public LLWatchpoint addWatchpoint(final long n, final int n2, final LLValue llValue, final String s, final LLWatchpoint.Lifetime lifetime, final LLWatchpoint.AccessType accessType) throws ExecutionException, DebuggerCommandException {
        final String referenceExpression = llValue.getReferenceExpression();
        int b = 0;
        String s2 = null;
        boolean b2 = false;
        boolean b3 = false;
        Label_0077: {
            Label_0068: {
                Label_0045: {
                    Label_0036: {
                        try {
                            b = b(llValue);
                            s2 = null;
                            if (accessType == LLWatchpoint.AccessType.ANY) {
                                break Label_0036;
                            }
                            final LLWatchpoint.AccessType accessType2 = accessType;
                            final LLWatchpoint.AccessType accessType3 = LLWatchpoint.AccessType.READ;
                            if (accessType2 == accessType3) {
                                break Label_0036;
                            }
                            break Label_0036;
                        }
                        catch (ExecutionException ex) {
                            throw c((Exception)ex);
                        }
                        try {
                            final LLWatchpoint.AccessType accessType2 = accessType;
                            final LLWatchpoint.AccessType accessType3 = LLWatchpoint.AccessType.READ;
                            if (accessType2 == accessType3) {
                                b2 = true;
                                break Label_0045;
                            }
                        }
                        catch (ExecutionException ex2) {
                            throw c((Exception)ex2);
                        }
                    }
                    b2 = false;
                    try {
                        if (accessType == LLWatchpoint.AccessType.ANY) {
                            break Label_0068;
                        }
                        final LLWatchpoint.AccessType accessType4 = accessType;
                        final LLWatchpoint.AccessType accessType5 = LLWatchpoint.AccessType.WRITE;
                        if (accessType4 == accessType5) {
                            break Label_0068;
                        }
                        break Label_0068;
                    }
                    catch (ExecutionException ex3) {
                        throw c((Exception)ex3);
                    }
                }
                try {
                    final LLWatchpoint.AccessType accessType4 = accessType;
                    final LLWatchpoint.AccessType accessType5 = LLWatchpoint.AccessType.WRITE;
                    if (accessType4 == accessType5) {
                        b3 = true;
                        break Label_0077;
                    }
                }
                catch (ExecutionException ex4) {
                    throw c((Exception)ex4);
                }
            }
            b3 = false;
        }
        final Protocol.CompositeRequest addWatchpoint = ProtobufMessageFactory.addWatchpoint(b, s2, b2, b3, true);
        final Ref create = Ref.create((Object)null);
        final Ref create2 = Ref.create((Object)null);
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(addWatchpoint, Protocol.AddWatchpoint_Res.class, (com.intellij.util.Consumer<Protocol.AddWatchpoint_Res>)(addWatchpoint_Res -> {
                try {
                    if (!addWatchpoint_Res.getCommonResponse().getIsValid()) {
                        create2.set((Object)new DebuggerCommandException(addWatchpoint_Res.getCommonResponse().getErrorMessage()));
                        return;
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                create.set((Object)new LLWatchpoint(addWatchpoint_Res.getWatchpointId(), referenceExpression));
            }));
            if (create.isNull()) {
                throw (DebuggerCommandException)create2.get();
            }
        }
        catch (ExecutionException ex5) {
            throw c((Exception)ex5);
        }
        LLWatchpoint llWatchpoint;
        try {
            llWatchpoint = (LLWatchpoint)create.get();
            if (llWatchpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "addWatchpoint"));
            }
        }
        catch (ExecutionException ex6) {
            throw c((Exception)ex6);
        }
        return llWatchpoint;
    }
    
    @NotNull
    @Override
    public List<LLBreakpoint> addBreakpoint(final String s, final int n, @Nullable final String s2) throws ExecutionException {
        final String convertToProjectModelPath = this.myStarter.convertToProjectModelPath(s);
        final Protocol.CompositeRequest addBreakpoint = ProtobufMessageFactory.addBreakpoint(convertToProjectModelPath, n + 1, s2);
        final Ref ref = new Ref();
        Object o = null;
        Label_0078: {
            try {
                this.getProtobufClient().sendMessageAndWaitForReply(addBreakpoint, Protocol.AddBreakpoint_Res.class, (com.intellij.util.Consumer<Protocol.AddBreakpoint_Res>)(addBreakpoint_Res -> ref.set((Object)new LLBreakpoint(addBreakpoint_Res.getId(), convertToProjectModelPath, n, false, s2, null))));
                if (ref.isNull()) {
                    final Object o2;
                    o = (o2 = Collections.emptyList());
                    break Label_0078;
                }
            }
            catch (ExecutionException ex) {
                throw c((Exception)ex);
            }
            Object o2;
            o = (o2 = Collections.singletonList(ref.get()));
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "addBreakpoint"));
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
        }
        return (List<LLBreakpoint>)o;
    }
    
    @Nullable
    @Override
    public LLSymbolicBreakpoint addSymbolicBreakpoint(@NotNull final SymbolicBreakpoint symbolicBreakpoint) throws ExecutionException, DebuggerCommandException {
        try {
            if (symbolicBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symBreakpoint", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "addSymbolicBreakpoint"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final Protocol.CompositeRequest addBreakpoint = ProtobufMessageFactory.addBreakpoint(symbolicBreakpoint.getPattern(), symbolicBreakpoint.isRegexpPattern(), symbolicBreakpoint.getModule(), symbolicBreakpoint.getCondition(), symbolicBreakpoint.getThreadId());
        final Ref ref = new Ref();
        this.getProtobufClient().sendMessageAndWaitForReply(addBreakpoint, Protocol.AddBreakpoint_Res.class, (com.intellij.util.Consumer<Protocol.AddBreakpoint_Res>)(addBreakpoint_Res -> ref.set((Object)new LLSymbolicBreakpoint(addBreakpoint_Res.getId()))));
        return (LLSymbolicBreakpoint)ref.get();
    }
    
    @Override
    public void removeCodepoints(@NotNull final Collection<Integer> collection) throws ExecutionException {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ids", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "removeCodepoints"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        for (final Integer n : collection) {
            final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.RemoveBreakpoint_Res>("Couldn't remove breakpoint");
            this.getProtobufClient().sendMessageAndWaitForReply(a(n), Protocol.RemoveBreakpoint_Res.class, (com.intellij.util.Consumer<Protocol.RemoveBreakpoint_Res>)throwIfNotValid);
            throwIfNotValid.throwIfNeeded();
        }
    }
    
    private static Protocol.CompositeRequest a(final int n) {
        return ProtobufMessageFactory.removeBreakpoint(n);
    }
    
    @NotNull
    private LLThread a(@NotNull final Model.LLDBThread lldbThread) {
        try {
            if (lldbThread == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lldbThread", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "newLLThread"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        final Model.ThreadStopReasonInfo stopReasonInfo = lldbThread.getStopReasonInfo();
        boolean b = false;
        Label_0079: {
            Label_0070: {
                try {
                    if (stopReasonInfo == null) {
                        break Label_0070;
                    }
                    final Model.ThreadStopReasonInfo threadStopReasonInfo = stopReasonInfo;
                    final Model.ThreadStopReason threadStopReason = threadStopReasonInfo.getStopReason();
                    final Model.ThreadStopReason threadStopReason2 = Model.ThreadStopReason.ThreadStopReasonInvalid;
                    if (threadStopReason != threadStopReason2) {
                        break Label_0070;
                    }
                    break Label_0070;
                }
                catch (IllegalStateException ex2) {
                    throw c(ex2);
                }
                try {
                    final Model.ThreadStopReasonInfo threadStopReasonInfo = stopReasonInfo;
                    final Model.ThreadStopReason threadStopReason = threadStopReasonInfo.getStopReason();
                    final Model.ThreadStopReason threadStopReason2 = Model.ThreadStopReason.ThreadStopReasonInvalid;
                    if (threadStopReason != threadStopReason2) {
                        b = true;
                        break Label_0079;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw c(ex3);
                }
            }
            b = false;
        }
        final boolean b2 = b;
        long n = 0L;
        String s = null;
        Label_0103: {
            try {
                n = lldbThread.getId();
                if (b2) {
                    s = "STOPPED";
                    break Label_0103;
                }
            }
            catch (IllegalStateException ex4) {
                throw c(ex4);
            }
            s = null;
        }
        final LLThread llThread = new LLThread(n, s, lldbThread.getQueue(), lldbThread.getName());
        if (llThread == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "newLLThread"));
        }
        return llThread;
    }
    
    @NotNull
    private LLFrame a(@NotNull final Model.LLDBFrame lldbFrame) {
        try {
            if (lldbFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "newLLFrame"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        int number = 0;
        String function = null;
        Label_0071: {
            try {
                number = lldbFrame.getNumber();
                if (lldbFrame.hasFunction()) {
                    function = lldbFrame.getFunction();
                    break Label_0071;
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
            function = null;
        }
        final LLFrame llFrame = new LLFrame(number, function, lldbFrame.hasFile() ? this.myStarter.convertToLocalPath(lldbFrame.getFile()) : null, lldbFrame.hasLine() ? (lldbFrame.getLine() - 1) : -1, lldbFrame.hasPc() ? lldbFrame.getPc() : 0L, lldbFrame.hasLanguage() ? a(lldbFrame.getLanguage()) : null, lldbFrame.hasOptimized() && lldbFrame.getOptimized());
        if (llFrame == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "newLLFrame"));
        }
        return llFrame;
    }
    
    @Nullable
    private static DebuggerLanguage a(@Nullable final Model.Language language) {
        Label_0104: {
            Label_0080: {
                try {
                    if (language == null) {
                        return null;
                    }
                    final int[] array = LLDBDriver.LLDBDriver$6.$SwitchMap$com$jetbrains$cidr$execution$debugger$backend$lldb$auto_generated$Model$Language;
                    final Model.Language language2 = language;
                    final int n = language2.ordinal();
                    final int n2 = array[n];
                    switch (n2) {
                        case 1:
                        case 2:
                        case 3:
                        case 4: {
                            break Label_0080;
                            break Label_0080;
                        }
                        case 5:
                        case 6:
                        case 7:
                        case 8: {
                            return StandardDebuggerLanguage.C_PLUS_PLUS;
                        }
                        case 9: {
                            return StandardDebuggerLanguage.OBJC;
                        }
                        case 10: {
                            return StandardDebuggerLanguage.OBJC_PLUS_PLUS;
                        }
                        case 11: {
                            return StandardDebuggerLanguage.SWIFT;
                        }
                        case 12: {
                            break Label_0104;
                        }
                        default: {
                            return null;
                        }
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                try {
                    final int[] array = LLDBDriver.LLDBDriver$6.$SwitchMap$com$jetbrains$cidr$execution$debugger$backend$lldb$auto_generated$Model$Language;
                    final Model.Language language2 = language;
                    final int n = language2.ordinal();
                    final int n2 = array[n];
                    switch (n2) {
                        case 1:
                        case 2:
                        case 3:
                        case 4: {
                            return StandardDebuggerLanguage.C;
                        }
                        case 5:
                        case 6:
                        case 7:
                        case 8: {
                            break;
                        }
                        case 9: {
                            return StandardDebuggerLanguage.OBJC;
                        }
                        case 10: {
                            return StandardDebuggerLanguage.OBJC_PLUS_PLUS;
                        }
                        case 11: {
                            return StandardDebuggerLanguage.SWIFT;
                        }
                        case 12: {
                            break Label_0104;
                        }
                        default: {
                            return null;
                        }
                    }
                }
                catch (IllegalStateException ex2) {
                    throw c(ex2);
                }
            }
            return StandardDebuggerLanguage.C_PLUS_PLUS;
        }
        CidrDebuggerLog.LOG.warn("Unknown language reported by LLDB. Protocol needs to be updated");
        return null;
    }
    
    @Nullable
    private static Model.Language a(@Nullable final DebuggerLanguage debuggerLanguage) throws DebuggerCommandException {
        try {
            if (debuggerLanguage == null) {
                return null;
            }
        }
        catch (DebuggerCommandException ex) {
            throw c(ex);
        }
        Label_0068: {
            try {
                if (!(debuggerLanguage instanceof StandardDebuggerLanguage)) {
                    throw new DebuggerCommandException(debuggerLanguage.toString() + " is not supported by LLDB");
                }
                final int[] array = LLDBDriver.LLDBDriver$6.$SwitchMap$com$jetbrains$cidr$execution$debugger$backend$DebuggerDriver$StandardDebuggerLanguage;
                final DebuggerLanguage debuggerLanguage2 = debuggerLanguage;
                final StandardDebuggerLanguage standardDebuggerLanguage = (StandardDebuggerLanguage)debuggerLanguage2;
                final int n = standardDebuggerLanguage.ordinal();
                final int n2 = array[n];
                switch (n2) {
                    case 1: {
                        break Label_0068;
                        break Label_0068;
                    }
                    case 2: {
                        return Model.Language.LanguageC_plus_plus;
                    }
                    case 3: {
                        return Model.Language.LanguageObjC;
                    }
                    case 4: {
                        return Model.Language.LanguageObjC_plus_plus;
                    }
                    case 5: {
                        return Model.Language.LanguageSwift;
                    }
                    default: {
                        throw new DebuggerCommandException(debuggerLanguage.toString() + " is not supported by LLDB");
                    }
                }
            }
            catch (DebuggerCommandException ex2) {
                throw c(ex2);
            }
            try {
                final int[] array = LLDBDriver.LLDBDriver$6.$SwitchMap$com$jetbrains$cidr$execution$debugger$backend$DebuggerDriver$StandardDebuggerLanguage;
                final DebuggerLanguage debuggerLanguage2 = debuggerLanguage;
                final StandardDebuggerLanguage standardDebuggerLanguage = (StandardDebuggerLanguage)debuggerLanguage2;
                final int n = standardDebuggerLanguage.ordinal();
                final int n2 = array[n];
                switch (n2) {
                    case 1: {
                        return Model.Language.LanguageC;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        return Model.Language.LanguageObjC;
                    }
                    case 4: {
                        return Model.Language.LanguageObjC_plus_plus;
                    }
                    case 5: {
                        return Model.Language.LanguageSwift;
                    }
                    default: {
                        throw new DebuggerCommandException(debuggerLanguage.toString() + " is not supported by LLDB");
                    }
                }
            }
            catch (DebuggerCommandException ex3) {
                throw c(ex3);
            }
        }
        return Model.Language.LanguageC_plus_plus;
    }
    
    @NotNull
    @Override
    public List<LLThread> getThreads() throws ExecutionException, DebuggerCommandException {
        final ArrayList list = new ArrayList<LLThread>();
        final Protocol.CompositeRequest threads = ProtobufMessageFactory.getThreads();
        ArrayList list2;
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(threads, Protocol.GetThreads_Res.class, (com.intellij.util.Consumer<Protocol.GetThreads_Res>)(getThreads_Res -> {
                final Iterator<Model.LLDBThread> iterator = getThreads_Res.getThreadList().iterator();
                while (iterator.hasNext()) {
                    list.add(this.a(iterator.next()));
                }
            }), 2147483647L);
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getThreads"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return (List<LLThread>)list2;
    }
    
    @NotNull
    @Override
    public ResultList<LLFrame> getFrames(final long n, final int n2, final int n3, final boolean b) throws ExecutionException, DebuggerCommandException {
        final Ref ref = new Ref();
        final Protocol.CompositeRequest frames = ProtobufMessageFactory.getFrames(n, n2, n3, b);
        ResultList list;
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(frames, Protocol.GetFrames_Res.class, (com.intellij.util.Consumer<Protocol.GetFrames_Res>)(getFrames_Res -> {
                final List<Model.LLDBFrame> frameList = getFrames_Res.getFrameList();
                final ArrayList list = new ArrayList<Object>(frameList.size());
                final Iterator<Model.LLDBFrame> iterator = frameList.iterator();
                while (iterator.hasNext()) {
                    list.add(this.a(iterator.next()));
                }
                ref.set((Object)ResultList.create((List<Object>)list, getFrames_Res.getHasMore()));
            }), 2147483647L);
            list = (ResultList)ref.get();
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getFrames"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return (ResultList<LLFrame>)list;
    }
    
    @NotNull
    @Override
    public List<LLValue> getVariables(final long n, final int n2) throws ExecutionException {
        final boolean staticVarsLoadingEnabled = this.myStarter.isStaticVarsLoadingEnabled();
        List<LLValue> variables;
        try {
            variables = this.getVariables(n, n2, staticVarsLoadingEnabled, staticVarsLoadingEnabled);
            if (variables == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getVariables"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return variables;
    }
    
    @NotNull
    public List<LLValue> getVariables(final long n, final int n2, final boolean b, final boolean b2) throws ExecutionException {
        final Protocol.CompositeRequest vars = ProtobufMessageFactory.getVars(n, n2, b, b2);
        final ArrayList<LLValue> list = new ArrayList<LLValue>();
        final Ref ref = new Ref();
        ArrayList<LLValue> list2 = null;
        Label_0100: {
            Label_0080: {
                try {
                    this.getProtobufClient().sendMessageAndWaitForReply(vars, Protocol.GetVars_Res.class, (com.intellij.util.Consumer<Protocol.GetVars_Res>)(getVars_Res -> {
                        final Protocol.CommonResponse commonResponse = getVars_Res.getCommonResponse();
                        if (!commonResponse.getIsValid()) {
                            final String errorMessage = commonResponse.getErrorMessage();
                            try {
                                if (errorMessage != null) {
                                    ref.set((Object)errorMessage);
                                }
                            }
                            catch (IllegalStateException ex) {
                                throw c(ex);
                            }
                        }
                        else {
                            final Iterator<Model.LLDBValue> iterator = getVars_Res.getValueList().iterator();
                            while (iterator.hasNext()) {
                                list.add(this.a(iterator.next(), null));
                            }
                        }
                    }), 0L);
                    if (ref.isNull()) {
                        break Label_0100;
                    }
                    final Ref ref2 = ref;
                    final Object o = ref2.get();
                    final String s = (String)o;
                    final boolean b3 = StringUtil.isEmpty(s);
                    if (!b3) {
                        break Label_0080;
                    }
                    break Label_0100;
                }
                catch (ExecutionException ex) {
                    throw c((Exception)ex);
                }
                try {
                    final Ref ref2 = ref;
                    final Object o = ref2.get();
                    final String s = (String)o;
                    final boolean b3 = StringUtil.isEmpty(s);
                    if (!b3) {
                        throw new ExecutionException((String)ref.get());
                    }
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
            }
            try {
                list2 = list;
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getVariables"));
                }
            }
            catch (ExecutionException ex3) {
                throw c((Exception)ex3);
            }
        }
        return list2;
    }
    
    @NotNull
    @Override
    public LLValueData getData(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getData"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        LLValueData a;
        try {
            a = a(llValue);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getData"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return a;
    }
    
    @Nullable
    @Override
    public String getDescription(@NotNull final LLValue llValue, final int n) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getDescription"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final Protocol.CompositeRequest valueDescription = ProtobufMessageFactory.getValueDescription(b(llValue), n);
        final Ref create = Ref.create();
        final Ref create2 = Ref.create();
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(valueDescription, Protocol.GetValueDescription_Res.class, (com.intellij.util.Consumer<Protocol.GetValueDescription_Res>)(getValueDescription_Res -> {
                final Protocol.CommonResponse commonResponse = getValueDescription_Res.getCommonResponse();
                try {
                    if (!commonResponse.getIsValid()) {
                        create2.set((Object)new DebuggerCommandException(commonResponse.getErrorMessage()));
                        return;
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                try {
                    if (getValueDescription_Res.hasDescription()) {
                        create.set((Object)getValueDescription_Res.getDescription());
                    }
                }
                catch (IllegalStateException ex2) {
                    throw c(ex2);
                }
            }));
            if (!create2.isNull()) {
                throw (DebuggerCommandException)create2.get();
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return (String)create.get();
    }
    
    @NotNull
    @Override
    public ResultList<LLValue> getVariableChildren(final LLValue llValue, final int n, final int n2) throws ExecutionException, DebuggerCommandException {
        final Integer childrenCount = this.getChildrenCount(llValue);
        Label_0060: {
            ResultList<LLValue> list = null;
            Label_0025: {
                try {
                    if (n2 != 0) {
                        break Label_0060;
                    }
                    list = ResultList.empty();
                    if (list == null) {
                        break Label_0025;
                    }
                    return list;
                }
                catch (ExecutionException ex) {
                    throw c((Exception)ex);
                }
                try {
                    list = ResultList.empty();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getVariableChildren"));
                    }
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
            }
            return list;
        }
        final Protocol.CompositeRequest valueChildren = ProtobufMessageFactory.getValueChildren(b(llValue), n, n2);
        final Ref ref = new Ref();
        final ArrayList<LLValue> list2 = new ArrayList<LLValue>();
        this.getProtobufClient().sendMessageAndWaitForReply(valueChildren, Protocol.GetValueChildren_Res.class, (com.intellij.util.Consumer<Protocol.GetValueChildren_Res>)(getValueChildren_Res -> {
            final boolean isValid = getValueChildren_Res.getCommonResponse().getIsValid();
            try {
                if (!isValid) {
                    ref.set((Object)getValueChildren_Res.getCommonResponse().getErrorMessage());
                    return;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            this.a(getValueChildren_Res.getValueList(), list2);
        }), 0L);
        final String s = (String)ref.get();
        try {
            if (s != null) {
                throw new DebuggerCommandException(s);
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        boolean b = false;
        Label_0160: {
            try {
                if (n + n2 < childrenCount) {
                    b = true;
                    break Label_0160;
                }
            }
            catch (ExecutionException ex4) {
                throw c((Exception)ex4);
            }
            b = false;
        }
        final boolean b2 = b;
        ResultList<LLValue> create;
        try {
            create = ResultList.create(list2, b2);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getVariableChildren"));
            }
        }
        catch (ExecutionException ex5) {
            throw c((Exception)ex5);
        }
        return create;
    }
    
    @Override
    public void addSymbolsFile(final File file, final File file2) throws ExecutionException {
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.handleConsoleCommand(-1L, -1, "target module add \"" + file2.getAbsolutePath() + "\""), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)new ThrowIfNotValid("couldn't add module: " + file2.getAbsolutePath()));
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.handleConsoleCommand(-1L, -1, "target symbols add \"" + file.getAbsolutePath() + "\""), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)new ThrowIfNotValid("couldn't add dSYM: " + file.getAbsolutePath()));
    }
    
    private void a(final List<Model.LLDBValue> list, final List<LLValue> list2) {
        final Iterator<Model.LLDBValue> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(this.a(iterator.next(), null));
        }
    }
    
    private static int b(@NotNull final LLValue llValue) throws ExecutionException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "valId"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return (int)llValue.getUserData((Key)LLDBDriver.LLVALUE_ID);
    }
    
    public List<LLValue> arraySlice(final LLValue llValue, final int n, final int n2) throws ExecutionException, DebuggerCommandException {
        final Ref create = Ref.create((Object)"unknown error");
        final ArrayList<LLValue> list = new ArrayList<LLValue>();
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.arraySlice(b(llValue), n, n2), Protocol.GetArraySlice_Res.class, (com.intellij.util.Consumer<Protocol.GetArraySlice_Res>)(getArraySlice_Res -> {
                if (!getArraySlice_Res.getCommonResponse().getIsValid()) {
                    final String errorMessage = getArraySlice_Res.getCommonResponse().getErrorMessage();
                    try {
                        if (errorMessage != null) {
                            create.set((Object)errorMessage);
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw c(ex);
                    }
                    return;
                }
                create.set((Object)null);
                this.a(getArraySlice_Res.getValueList(), list);
            }));
            if (!create.isNull()) {
                throw new DebuggerCommandException((String)create.get());
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return list;
    }
    
    @NotNull
    private LLValue a(@NotNull final Model.LLDBValue lldbValue, @Nullable final String s) {
        try {
            if (lldbValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lldbValue", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "createLLValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        LLValue.TypeClass typeClass = null;
        switch (lldbValue.getTypeClass()) {
            case TypeClassFunction: {
                typeClass = LLValue.TypeClass.FUNCTION;
                break;
            }
            case TypeClassBuiltin: {
                typeClass = LLValue.TypeClass.BUILTIN;
                break;
            }
            case TypeClassClass:
            case TypeClassStruct: {
                typeClass = LLValue.TypeClass.CLASS_STRUCT;
                break;
            }
            case TypeClassObjCObjectPointer: {
                typeClass = LLValue.TypeClass.OBJC_POINTER;
                break;
            }
            default: {
                typeClass = null;
                break;
            }
        }
        final String name = lldbValue.getName();
        String name2 = null;
        Label_0144: {
            try {
                if (s == null) {
                    name2 = lldbValue.getName();
                    break Label_0144;
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
            name2 = s;
        }
        final LLValue llValue = new LLValue(name2, lldbValue.getType(), typeClass, name);
        LLValue llValue2;
        try {
            llValue.putUserData((Key)LLDBDriver.LLVALUE_ID, (Object)lldbValue.getId());
            llValue.putUserData((Key)LLDBDriver.LLVALUE_DATA_LOADER, (Object)new LLValueDataLoader());
            llValue2 = llValue;
            if (llValue2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "createLLValue"));
            }
        }
        catch (IllegalStateException ex3) {
            throw c(ex3);
        }
        return llValue2;
    }
    
    @NotNull
    private static LLValueData a(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getLLValueData"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        synchronized (llValue) {
            final LLValueDataLoader llValueDataLoader = (LLValueDataLoader)llValue.getUserData((Key)LLDBDriver.LLVALUE_DATA_LOADER);
            if (llValueDataLoader != null) {
                final LLValueData loadData = llValueDataLoader.loadData(llValue);
                llValue.putUserData((Key)LLDBDriver.LLVALUE_DATA_LOADER, (Object)null);
                llValue.putUserData((Key)LLDBDriver.LLVALUE_DATA, (Object)loadData);
                final LLValueData llValueData = loadData;
                // monitorexit(llValue)
                if (llValueData == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getLLValueData"));
                }
                return llValueData;
            }
            else {
                final LLValueData llValueData2 = (LLValueData)llValue.getUserData((Key)LLDBDriver.LLVALUE_DATA);
                try {
                    if (llValueData2 == null) {
                        throw new ExecutionException("Internal error, variable _p not initialized: " + llValue);
                    }
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
                final LLValueData llValueData3 = llValueData2;
                // monitorexit(llValue)
                if (llValueData3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getLLValueData"));
                }
                return llValueData3;
            }
        }
    }
    
    @NotNull
    @Override
    public LLValue evaluate(final long n, final int n2, @NotNull final String s, @Nullable final DebuggerLanguage debuggerLanguage) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "evaluate"));
            }
        }
        catch (ProtobufTimedOutException ex) {
            throw c((Exception)ex);
        }
        final Protocol.CompositeRequest evaluateExpression = ProtobufMessageFactory.evaluateExpression(n, n2, s, a(debuggerLanguage));
        final Ref ref = new Ref();
        final Ref ref2 = new Ref();
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(evaluateExpression, Protocol.EvaluateExpression_Res.class, (com.intellij.util.Consumer<Protocol.EvaluateExpression_Res>)(evaluateExpression_Res -> {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "lambda$evaluate$14"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                Label_0104: {
                    Label_0095: {
                        try {
                            if (!evaluateExpression_Res.getCommonResponse().getIsValid()) {
                                break Label_0104;
                            }
                            if (!evaluateExpression_Res.hasResult()) {
                                break Label_0095;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw c(ex2);
                        }
                        ref.set((Object)this.a(evaluateExpression_Res.getResult(), s));
                        return;
                    }
                    ref2.set((Object)"<no result>");
                    return;
                }
                final String errorMessage = evaluateExpression_Res.getCommonResponse().getErrorMessage();
                String s2 = null;
                Label_0131: {
                    try {
                        if (errorMessage != null) {
                            s2 = errorMessage;
                            break Label_0131;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw c(ex3);
                    }
                    s2 = "Unknown evaluation error";
                }
                ref2.set((Object)s2);
            }), 0L);
        }
        catch (ProtobufTimedOutException ex5) {
            throw new DebuggerEvaluationTimedOutException(s);
        }
        if (ref2.isNull()) {
            try {
                if (ref.isNull()) {
                    throw new ExecutionException("Unknown evaluation error");
                }
            }
            catch (ProtobufTimedOutException ex2) {
                throw c((Exception)ex2);
            }
            LLValue llValue;
            try {
                llValue = (LLValue)ref.get();
                if (llValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "evaluate"));
                }
            }
            catch (ProtobufTimedOutException ex3) {
                throw c((Exception)ex3);
            }
            return llValue;
        }
        String group = (String)ref2.get();
        if ("<no result>".equals(group)) {
            final LLValue llValue2 = new LLValue("result", "void", null, "");
            LLValue llValue3;
            try {
                llValue2.putUserData((Key)LLDBDriver.LLVALUE_ID, (Object)0);
                llValue2.putUserData((Key)LLDBDriver.LLVALUE_DATA, (Object)new LLValueData("", null, false, false, false));
                llValue2.putUserData((Key)LLDBDriver.CHILDREN_COUNT_CACHE, (Object)0);
                llValue3 = llValue2;
                if (llValue3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "evaluate"));
                }
            }
            catch (ProtobufTimedOutException ex4) {
                throw c((Exception)ex4);
            }
            return llValue3;
        }
        final Matcher matcher = Pattern.compile("error: (.*)\nerror: \\d+ error[s]? parsing expression\n").matcher(group);
        if (matcher.find()) {
            group = matcher.group(1);
        }
        throw new DebuggerCommandException(group);
    }
    
    @NotNull
    @Override
    public List<LLInstruction> disassemble(@NotNull final AddressRange addressRange) throws ExecutionException, DebuggerCommandException {
        try {
            if (addressRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "disassemble"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        throw new ExecutionException("Disassembling not implemented in " + this.getClass().getSimpleName());
    }
    
    @NotNull
    @Override
    public ShellCommandResult executeShellCommand(@NotNull final String s, @Nullable final List<String> list, @Nullable final String s2, final int n) throws ExecutionException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "executeShellCommand"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final Ref ref = new Ref();
        final Ref ref2 = new Ref();
        final LinkedList<String> list2 = new LinkedList<String>();
        try {
            list2.add(s);
            if (list != null) {
                list2.addAll((Collection<?>)list);
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        final String join = StringUtil.join((Collection)list2, s -> StringUtil.escapeCharCharacters(s), " ");
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.executeShellCommand(join, s2, n), Protocol.ExecuteShellCommand_Res.class, (com.intellij.util.Consumer<Protocol.ExecuteShellCommand_Res>)(executeShellCommand_Res -> {
                final Protocol.CommonResponse commonResponse = executeShellCommand_Res.getCommonResponse();
                if (!commonResponse.getIsValid()) {
                    final String errorMessage = commonResponse.getErrorMessage();
                    String s2 = null;
                    Label_0050: {
                        Label_0039: {
                            try {
                                if (errorMessage == null) {
                                    break Label_0039;
                                }
                                final String s = errorMessage;
                                final boolean b = StringUtil.isEmpty(s);
                                if (!b) {
                                    break Label_0039;
                                }
                                break Label_0039;
                            }
                            catch (IllegalStateException ex) {
                                throw c(ex);
                            }
                            try {
                                final String s = errorMessage;
                                final boolean b = StringUtil.isEmpty(s);
                                if (!b) {
                                    s2 = errorMessage;
                                    break Label_0050;
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw c(ex2);
                            }
                        }
                        s2 = "Invalid shell command response";
                    }
                    ref2.set((Object)s2);
                }
                else {
                    ref.set((Object)new ShellCommandResult(executeShellCommand_Res.getOutput(), executeShellCommand_Res.getStatus(), executeShellCommand_Res.getSignal()));
                }
            }), (n + 10) * 1000);
            if (!ref2.isNull()) {
                throw new ExecutionException((String)ref2.get());
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        ShellCommandResult shellCommandResult;
        try {
            shellCommandResult = (ShellCommandResult)ref.get();
            if (shellCommandResult == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "executeShellCommand"));
            }
        }
        catch (ExecutionException ex4) {
            throw c((Exception)ex4);
        }
        return shellCommandResult;
    }
    
    @Override
    public void executeConsoleCommand(final String s) throws ExecutionException {
        this.executeConsoleCommand(-1L, -1, s);
    }
    
    @Override
    public void executeConsoleCommand(final long n, final int n2, final String s) throws ExecutionException {
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.handleConsoleCommand(n, n2, s), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)(handleConsoleCommand_Res -> {
            try {
                if (handleConsoleCommand_Res.hasOut()) {
                    this.handleGDBOutput(handleConsoleCommand_Res.getOut());
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            try {
                if (handleConsoleCommand_Res.hasErr()) {
                    this.handleGDBOutput(handleConsoleCommand_Res.getErr());
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
        }));
    }
    
    @Override
    public void handleCompletion(final String s, final int n, final List<String> list) throws ExecutionException {
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.handleCompletion(s, n), Protocol.HandleCompletion_Res.class, (com.intellij.util.Consumer<Protocol.HandleCompletion_Res>)(handleCompletion_Res -> list.addAll(handleCompletion_Res.getCompletionList())));
    }
    
    @Override
    public void handleSignal(final String s, final boolean b, final boolean b2, final boolean b3) throws ExecutionException {
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.HandleSignal_Res>("Couldn't handle signal");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.handleSignal(s, b, b2, b3), Protocol.HandleSignal_Res.class, (com.intellij.util.Consumer<Protocol.HandleSignal_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
    }
    
    @Override
    protected String getPromptText() {
        return "lldb";
    }
    
    @Override
    public OutputStream getProcessInput() {
        return this.myProcessInputHandler;
    }
    
    @Override
    public void removeWatchpoint(final List<Integer> list) throws ExecutionException {
        final int intValue = list.get(0);
        final ThrowIfNotValid throwIfNotValid = new ThrowIfNotValid<Protocol.RemoveWatchpoint_Res>("Couldn't remove watchpoint");
        this.getProtobufClient().sendMessageAndWaitForReply(ProtobufMessageFactory.removeWatchpoint(intValue), Protocol.RemoveWatchpoint_Res.class, (com.intellij.util.Consumer<Protocol.RemoveWatchpoint_Res>)throwIfNotValid);
        throwIfNotValid.throwIfNeeded();
    }
    
    @NotNull
    @Override
    public Integer getChildrenCount(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getChildrenCount"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final Integer n = (Integer)llValue.getUserData((Key)LLDBDriver.CHILDREN_COUNT_CACHE);
        Label_0106: {
            Integer n2 = null;
            Label_0071: {
                try {
                    if (n == null) {
                        break Label_0106;
                    }
                    n2 = n;
                    if (n2 == null) {
                        break Label_0071;
                    }
                    return n2;
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
                try {
                    n2 = n;
                    if (n2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getChildrenCount"));
                    }
                }
                catch (ExecutionException ex3) {
                    throw c((Exception)ex3);
                }
            }
            return n2;
        }
        final Protocol.CompositeRequest childrenCount = ProtobufMessageFactory.getChildrenCount(b(llValue));
        final Ref ref = new Ref();
        final Ref create = Ref.create((Object)0);
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(childrenCount, Protocol.GetChildrenCount_Res.class, (com.intellij.util.Consumer<Protocol.GetChildrenCount_Res>)(getChildrenCount_Res -> {
                final boolean isValid = getChildrenCount_Res.getCommonResponse().getIsValid();
                try {
                    if (!isValid) {
                        ref.set((Object)getChildrenCount_Res.getCommonResponse().getErrorMessage());
                        return;
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                create.set((Object)getChildrenCount_Res.getCount());
            }), 0L);
            if (!ref.isNull()) {
                throw new DebuggerCommandException((String)ref.get());
            }
        }
        catch (ExecutionException ex4) {
            throw c((Exception)ex4);
        }
        Integer n3;
        try {
            llValue.putUserData((Key)LLDBDriver.CHILDREN_COUNT_CACHE, create.get());
            n3 = (Integer)create.get();
            if (n3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getChildrenCount"));
            }
        }
        catch (ExecutionException ex5) {
            throw c((Exception)ex5);
        }
        return n3;
    }
    
    public long getValueAddress(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "getValueAddress"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final Protocol.CompositeRequest valueAddress = ProtobufMessageFactory.getValueAddress(b(llValue));
        final Ref ref = new Ref();
        final Ref create = Ref.create();
        try {
            this.getProtobufClient().sendMessageAndWaitForReply(valueAddress, Protocol.GetValueAddress_Res.class, (com.intellij.util.Consumer<Protocol.GetValueAddress_Res>)(getValueAddress_Res -> {
                final boolean isValid = getValueAddress_Res.getCommonResponse().getIsValid();
                try {
                    if (!isValid) {
                        ref.set((Object)getValueAddress_Res.getCommonResponse().getErrorMessage());
                        return;
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                create.set((Object)getValueAddress_Res.getAddress());
            }), 0L);
            if (!ref.isNull()) {
                throw new DebuggerCommandException((String)ref.get());
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return (long)create.get();
    }
    
    protected void lldbSet(@NotNull final String s, final boolean b) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "setting", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "lldbSet"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String s2 = null;
        Label_0061: {
            try {
                if (b) {
                    s2 = "true";
                    break Label_0061;
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
            s2 = "false";
        }
        this.lldbSet(s, s2);
    }
    
    protected void lldbSet(@NotNull final String s, @Nullable final String s2) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "setting", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver", "lldbSet"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        ProtobufServer<Protocol.CompositeResponse> myProtobufServer = null;
        long n = 0L;
        int n2 = 0;
        StringBuilder append = null;
        String s3 = null;
        Label_0122: {
            try {
                myProtobufServer = this.myProtobufServer;
                n = -1L;
                n2 = -1;
                append = new StringBuilder().append("set ");
                if (s2 != null) {
                    s3 = "set " + s + " " + s2;
                    break Label_0122;
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
            s3 = "remove " + s;
        }
        myProtobufServer.sendMessageAndWaitUntilSent(ProtobufMessageFactory.handleConsoleCommand(n, n2, append.append(s3).toString()), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)new ThrowIfNotValid("couldn't set " + s + " = " + s2));
    }
    
    private void c() {
        try {
            try {
                this.lldbSet("target.process.thread.step-in-avoid-nodebug", true);
                this.lldbSet("target.process.thread.step-out-avoid-nodebug", true);
                this.myProtobufServer.sendMessageAndWaitUntilSent(ProtobufMessageFactory.setValuesFilteringEnabled(CidrDebuggerSettings.getInstance().VALUES_FILTER_ENABLED), Protocol.ValuesFilteringPolicy_Res.class, (com.intellij.util.Consumer<Protocol.ValuesFilteringPolicy_Res>)new ThrowIfNotValid("couldn't set values filtering policy"));
                if (this.myLLDBCommandLine.getUserData((Key)LLDBDriver.ENABLE_STL_RENDERERS) == Boolean.TRUE) {
                    this.myProtobufServer.sendMessageAndWaitUntilSent(ProtobufMessageFactory.handleConsoleCommand(-1L, -1, "script import lldb_formatters.jetbrains_stl_formatters"), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)(handleConsoleCommand_Res -> {
                        try {
                            if (!handleConsoleCommand_Res.hasOut()) {
                                if (!handleConsoleCommand_Res.hasErr()) {
                                    return;
                                }
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw c(ex);
                        }
                        String s = "Error during data formatters setup:";
                        if (handleConsoleCommand_Res.hasOut()) {
                            s = s + "\n" + handleConsoleCommand_Res.getOut();
                        }
                        if (handleConsoleCommand_Res.hasErr()) {
                            s = s + "\n" + handleConsoleCommand_Res.getErr();
                        }
                        this.handleTargetOutput(s, ProcessOutputTypes.SYSTEM);
                    }));
                }
            }
            catch (Exception ex) {
                throw c(ex);
            }
            if (CidrDebuggerLog.LOG.isTraceEnabled()) {
                this.myProtobufServer.sendMessageAndWaitUntilSent(ProtobufMessageFactory.handleConsoleCommand(-1L, -1, "log enable -f " + (b() + "/lldb.log") + " lldb default"), Protocol.HandleConsoleCommand_Res.class, (com.intellij.util.Consumer<Protocol.HandleConsoleCommand_Res>)(handleConsoleCommand_Res -> {}));
            }
            this.myConnectedClient.set(this.myProtobufServer);
        }
        catch (Exception exception) {
            this.myConnectedClient.setException(exception);
        }
    }
    
    public void consume(final GeneratedMessage generatedMessage) {
        try {
            if (generatedMessage instanceof Model.Initialized_Message) {
                this.c();
                return;
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        if (generatedMessage instanceof Broadcasts.ProcessExited_Broadcast) {
            final Broadcasts.ProcessExited_Broadcast processExited_Broadcast = (Broadcasts.ProcessExited_Broadcast)generatedMessage;
            final int exitCode = processExited_Broadcast.getExitCode();
            int n = 0;
            String exitDescription = null;
            Label_0056: {
                try {
                    n = exitCode;
                    if (processExited_Broadcast.hasExitDescription()) {
                        exitDescription = processExited_Broadcast.getExitDescription();
                        break Label_0056;
                    }
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
                exitDescription = null;
            }
            this.handleTargetFinished(n, exitDescription);
        }
        else if (generatedMessage instanceof Broadcasts.ProcessRunning_Broadcast) {
            final Integer myAsyncAttachingTo = this.myAsyncAttachingTo;
            try {
                this.myAsyncAttachingTo = null;
                if (myAsyncAttachingTo != null) {
                    this.handleAttached(myAsyncAttachingTo);
                }
            }
            catch (ExecutionException ex3) {
                throw c((Exception)ex3);
            }
            this.handleRunning();
        }
        else {
            Label_0396: {
                Label_0124: {
                    try {
                        if (generatedMessage instanceof Broadcasts.ProcessInterrupted_Broadcast) {
                            final LLDBDriver lldbDriver = this;
                            lldbDriver.a();
                            break Label_0124;
                        }
                        break Label_0396;
                    }
                    catch (IllegalStateException ex4) {
                        throw c(ex4);
                    }
                    try {
                        final LLDBDriver lldbDriver = this;
                        lldbDriver.a();
                    }
                    catch (ExecutionException ex18) {}
                }
                final Broadcasts.ProcessInterrupted_Broadcast processInterrupted_Broadcast = (Broadcasts.ProcessInterrupted_Broadcast)generatedMessage;
                final Model.LLDBThread thread = processInterrupted_Broadcast.getThread();
                final StopPlace stopPlace = new StopPlace(this.a(thread), this.a(processInterrupted_Broadcast.getFrame()));
                this.myStoppedThreadID = stopPlace.thread.getId();
                final Model.ThreadStopReasonInfo stopReasonInfo = thread.getStopReasonInfo();
                Model.ThreadStopReason stopReason = null;
                Label_0200: {
                    try {
                        if (stopReasonInfo == null) {
                            stopReason = null;
                            break Label_0200;
                        }
                    }
                    catch (ExecutionException ex5) {
                        throw c((Exception)ex5);
                    }
                    stopReason = stopReasonInfo.getStopReason();
                }
                final Model.ThreadStopReason threadStopReason = stopReason;
                Label_0266: {
                    Label_0243: {
                        Label_0225: {
                            try {
                                if (threadStopReason != Model.ThreadStopReason.ThreadStopReasonBreakpoint) {
                                    break Label_0243;
                                }
                                final Model.ThreadStopReasonInfo threadStopReasonInfo = stopReasonInfo;
                                final boolean b = threadStopReasonInfo.hasCodepointId();
                                if (b) {
                                    break Label_0225;
                                }
                                break Label_0243;
                            }
                            catch (ExecutionException ex6) {
                                throw c((Exception)ex6);
                            }
                            try {
                                final Model.ThreadStopReasonInfo threadStopReasonInfo = stopReasonInfo;
                                final boolean b = threadStopReasonInfo.hasCodepointId();
                                if (b) {
                                    this.handleBreakpoint(stopPlace, stopReasonInfo.getCodepointId());
                                    return;
                                }
                            }
                            catch (ExecutionException ex7) {
                                throw c((Exception)ex7);
                            }
                        }
                        try {
                            if (threadStopReason != Model.ThreadStopReason.ThreadStopReasonWatchpoint) {
                                break Label_0266;
                            }
                            final Model.ThreadStopReasonInfo threadStopReasonInfo2 = stopReasonInfo;
                            final boolean b2 = threadStopReasonInfo2.hasCodepointId();
                            if (b2) {
                                break Label_0266;
                            }
                            break Label_0266;
                        }
                        catch (ExecutionException ex8) {
                            throw c((Exception)ex8);
                        }
                    }
                    try {
                        final Model.ThreadStopReasonInfo threadStopReasonInfo2 = stopReasonInfo;
                        final boolean b2 = threadStopReasonInfo2.hasCodepointId();
                        if (b2) {
                            this.handleWatchpoint(stopPlace, stopReasonInfo.getCodepointId());
                            return;
                        }
                    }
                    catch (ExecutionException ex9) {
                        throw c((Exception)ex9);
                    }
                }
                if (threadStopReason == Model.ThreadStopReason.ThreadStopReasonSignal) {
                    final int signal = stopReasonInfo.getSignal();
                    final String signalName = stopReasonInfo.getSignalName();
                    try {
                        if (DebuggerDriver.isTargetTerminationSignal(signal)) {
                            this.handleTargetTerminated();
                            return;
                        }
                    }
                    catch (ExecutionException ex10) {
                        throw c((Exception)ex10);
                    }
                    StopPlace stopPlace2 = null;
                    String value = null;
                    Label_0350: {
                        try {
                            stopPlace2 = stopPlace;
                            if (!signalName.isEmpty()) {
                                value = signalName;
                                break Label_0350;
                            }
                        }
                        catch (ExecutionException ex11) {
                            throw c((Exception)ex11);
                        }
                        value = String.valueOf(signal);
                    }
                    this.handleSignal(stopPlace2, value, stopReasonInfo.getStopDescription());
                }
                else {
                    try {
                        if (threadStopReason == Model.ThreadStopReason.ThreadStopReasonException) {
                            this.handleException(stopPlace, stopReasonInfo.getStopDescription());
                            return;
                        }
                    }
                    catch (ExecutionException ex12) {
                        throw c((Exception)ex12);
                    }
                    this.handleInterrupted(stopPlace);
                }
                return;
                try {
                    if (generatedMessage instanceof Broadcasts.ChangePrompt_Broadcast) {
                        this.handlePrompt(((Broadcasts.ChangePrompt_Broadcast)generatedMessage).getPrompt());
                        return;
                    }
                }
                catch (ExecutionException ex13) {
                    throw c((Exception)ex13);
                }
            }
            Label_0461: {
                LLDBDriver lldbDriver2 = null;
                boolean b3 = false;
                Label_0455: {
                    Label_0446: {
                        try {
                            if (!(generatedMessage instanceof Broadcasts.ReadyForCommands_Broadcast)) {
                                break Label_0461;
                            }
                            lldbDriver2 = this;
                            final GeneratedMessage generatedMessage2 = generatedMessage;
                            final Broadcasts.ReadyForCommands_Broadcast readyForCommands_Broadcast = (Broadcasts.ReadyForCommands_Broadcast)generatedMessage2;
                            final int n2 = readyForCommands_Broadcast.getReady();
                            if (n2 == 0) {
                                break Label_0446;
                            }
                            break Label_0446;
                        }
                        catch (ExecutionException ex14) {
                            throw c((Exception)ex14);
                        }
                        try {
                            lldbDriver2 = this;
                            final GeneratedMessage generatedMessage2 = generatedMessage;
                            final Broadcasts.ReadyForCommands_Broadcast readyForCommands_Broadcast = (Broadcasts.ReadyForCommands_Broadcast)generatedMessage2;
                            final int n2 = readyForCommands_Broadcast.getReady();
                            if (n2 == 0) {
                                b3 = true;
                                break Label_0455;
                            }
                        }
                        catch (ExecutionException ex15) {
                            throw c((Exception)ex15);
                        }
                    }
                    b3 = false;
                }
                lldbDriver2.handlePrompt(b3);
                return;
                try {
                    if (generatedMessage instanceof Broadcasts.CommandsInterpreter_Broadcast) {
                        this.handleGDBOutput(((Broadcasts.CommandsInterpreter_Broadcast)generatedMessage).getMessage());
                        return;
                    }
                }
                catch (ExecutionException ex16) {
                    throw c((Exception)ex16);
                }
            }
            if (generatedMessage instanceof Broadcasts.TargetProcessOutput_Broadcast) {
                final Broadcasts.TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = (Broadcasts.TargetProcessOutput_Broadcast)generatedMessage;
                this.handleTargetOutput(targetProcessOutput_Broadcast.getText(), a(targetProcessOutput_Broadcast.getOutputType()));
            }
            else if (generatedMessage instanceof Broadcasts.LogMessage_Broadcast) {
                CidrDebuggerLog.LOG.info(((Broadcasts.LogMessage_Broadcast)generatedMessage).getMessage());
            }
            else {
                try {
                    if (generatedMessage instanceof Broadcasts.ModulesLoaded_Broadcast) {
                        this.handleModulesLoaded(((Broadcasts.ModulesLoaded_Broadcast)generatedMessage).getModulesList());
                    }
                }
                catch (ExecutionException ex17) {
                    throw c((Exception)ex17);
                }
            }
        }
    }
    
    private static Key a(final Model.OutputType outputType) {
        try {
            if (outputType == Model.OutputType.OutputTypeStdout) {
                return ProcessOutputTypes.STDOUT;
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return ProcessOutputTypes.STDERR;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!LLDBDriver.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        LOG = CidrDebuggerLog.LOG;
        ENABLE_STL_RENDERERS = Key.create("LLDBDriver.synthethicsEnabled");
        LLVALUE_ID = Key.create("LLDBDriver.LLVALUE_ID");
        LLVALUE_DATA_LOADER = Key.create("LLDBDriver.LLVALUE_DATA_LOADER");
        LLVALUE_DATA = Key.create("LLDBDriver.LLVALUE_DATA");
        CHILDREN_COUNT_CACHE = Key.create("LLDBDriver.CHILDREN_COUNT_CACHE");
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    protected static class ThrowIfNotValid<T extends GeneratedMessage> implements Consumer<T>
    {
        private String myMessage;
        private boolean myIsValid;
        
        public ThrowIfNotValid(final String myMessage) {
            this.myIsValid = false;
            this.myMessage = myMessage;
        }
        
        public void consume(final T t) {
            for (final Protocol.CommonResponse next : t.getAllFields().values()) {
                if (next instanceof Protocol.CommonResponse) {
                    final Protocol.CommonResponse commonResponse = next;
                    this.myIsValid = commonResponse.getIsValid();
                    if (this.myIsValid || !commonResponse.hasErrorMessage()) {
                        continue;
                    }
                    final String errorMessage = commonResponse.getErrorMessage();
                    if (StringUtil.isEmptyOrSpaces(errorMessage)) {
                        continue;
                    }
                    this.myMessage = errorMessage;
                }
            }
        }
        
        public void throwIfNeeded() throws LLDBDriverException {
            try {
                if (!this.myIsValid) {
                    throw new LLDBDriverException(this.myMessage);
                }
            }
            catch (LLDBDriverException ex) {
                throw b(ex);
            }
        }
        
        public String getMessage() {
            return this.myMessage;
        }
        
        public boolean isValid() {
            return this.myIsValid;
        }
        
        private static LLDBDriverException b(final LLDBDriverException ex) {
            return ex;
        }
    }
    
    private class LLValueDataLoader
    {
        @NotNull
        public LLValueData loadData(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
            try {
                if (llValue == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLValueDataLoader", "loadData"));
                }
            }
            catch (ExecutionException ex) {
                throw b((Exception)ex);
            }
            final Protocol.CompositeRequest valueData = ProtobufMessageFactory.getValueData(b(llValue), 1000);
            final Ref create = Ref.create();
            final Ref create2 = Ref.create();
            try {
                LLDBDriver.this.getProtobufClient().sendMessageAndWaitForReply(valueData, Protocol.GetValueData_Res.class, (com.intellij.util.Consumer<Protocol.GetValueData_Res>)(getValueData_Res -> {
                    final Protocol.CommonResponse commonResponse = getValueData_Res.getCommonResponse();
                    try {
                        if (!commonResponse.getIsValid()) {
                            create2.set((Object)new DebuggerCommandException(commonResponse.getErrorMessage()));
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    create.set((Object)getValueData_Res.getValue());
                }));
                if (!create2.isNull()) {
                    throw (DebuggerCommandException)create2.get();
                }
            }
            catch (ExecutionException ex2) {
                throw b((Exception)ex2);
            }
            final Model.LLDBValueData lldbValueData = (Model.LLDBValueData)create.get();
            String value = null;
            String description = null;
            Label_0145: {
                try {
                    value = lldbValueData.getValue();
                    if (lldbValueData.hasDescription()) {
                        description = lldbValueData.getDescription();
                        break Label_0145;
                    }
                }
                catch (ExecutionException ex3) {
                    throw b((Exception)ex3);
                }
                description = null;
            }
            final LLValueData llValueData = new LLValueData(value, description, lldbValueData.getHasLongerDescription(), lldbValueData.getMayHaveChildren(), lldbValueData.getIsSynthetic());
            if (llValueData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLValueDataLoader", "loadData"));
            }
            return llValueData;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
    
    public class ProcessInputWriter extends OutputStream
    {
        private final Object myCloseOpLock;
        private File myInputFileDir;
        private volatile boolean closed;
        private volatile boolean pipeInput;
        private volatile OutputStream myOutputStream;
        
        public ProcessInputWriter() {
            this.myCloseOpLock = new Object();
        }
        
        public void initPipeInput(@NotNull final File p0, @NotNull final String p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "inputFileDir"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "initPipeInput"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    43: athrow         
            //    44: aload_2        
            //    45: ifnonnull       88
            //    48: new             Ljava/lang/IllegalArgumentException;
            //    51: dup            
            //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    54: ldc             3
            //    56: anewarray       Ljava/lang/Object;
            //    59: dup            
            //    60: ldc             0
            //    62: ldc             "inputFileName"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "initPipeInput"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    87: athrow         
            //    88: aload_0        
            //    89: iconst_1       
            //    90: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.pipeInput:Z
            //    93: aload_0        
            //    94: iconst_0       
            //    95: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.closed:Z
            //    98: aload_0        
            //    99: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myCloseOpLock:Ljava/lang/Object;
            //   102: dup            
            //   103: astore_3       
            //   104: monitorenter   
            //   105: aload_0        
            //   106: aload_1        
            //   107: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myInputFileDir:Ljava/io/File;
            //   110: aload_3        
            //   111: monitorexit    
            //   112: goto            122
            //   115: astore          4
            //   117: aload_3        
            //   118: monitorexit    
            //   119: aload           4
            //   121: athrow         
            //   122: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
            //   125: aload_0        
            //   126: aload_1        
            //   127: aload_2        
            //   128: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter;Ljava/io/File;Ljava/lang/String;)Ljava/lang/Runnable;
            //   133: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
            //   138: pop            
            //   139: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  105    112    115    122    Any
            //  115    119    115    122    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119_1:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public void initDispatchInput() {
            this.pipeInput = false;
        }
        
        @Override
        public void write(final int n) throws IOException {
            LLDBDriver.LOG.error("Shouldn't be here");
        }
        
        @Override
        public void write(@NotNull final byte[] p0, final int p1, final int p2) throws IOException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "bytes"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "write"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    43: athrow         
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.pipeInput:Z
            //    48: ifeq            96
            //    51: aload_0        
            //    52: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.closed:Z
            //    55: ifne            145
            //    58: goto            65
            //    61: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    64: athrow         
            //    65: aload_0        
            //    66: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myOutputStream:Ljava/io/OutputStream;
            //    69: ifnull          145
            //    72: goto            79
            //    75: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    78: athrow         
            //    79: aload_0        
            //    80: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myOutputStream:Ljava/io/OutputStream;
            //    83: aload_1        
            //    84: iload_2        
            //    85: iload_3        
            //    86: invokevirtual   java/io/OutputStream.write:([BII)V
            //    89: goto            145
            //    92: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    95: athrow         
            //    96: new             Ljava/lang/String;
            //    99: dup            
            //   100: aload_1        
            //   101: iload_2        
            //   102: iload_3        
            //   103: aload_0        
            //   104: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.this$0:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;
            //   107: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.access$400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;)Lcom/intellij/execution/configurations/GeneralCommandLine;
            //   110: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getCharset:()Ljava/nio/charset/Charset;
            //   113: invokespecial   java/lang/String.<init>:([BIILjava/nio/charset/Charset;)V
            //   116: astore          4
            //   118: aload_0        
            //   119: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.this$0:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;
            //   122: aload           4
            //   124: getstatic       com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$DispatchTarget.DispatchTargetProcess:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$DispatchTarget;
            //   127: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.access$800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$DispatchTarget;)V
            //   130: goto            145
            //   133: astore          4
            //   135: new             Ljava/io/IOException;
            //   138: dup            
            //   139: aload           4
            //   141: invokespecial   java/io/IOException.<init>:(Ljava/lang/Throwable;)V
            //   144: athrow         
            //   145: return         
            //    Exceptions:
            //  throws java.io.IOException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                       
            //  -----  -----  -----  -----  -------------------------------------------
            //  65     92     92     96     Lcom/intellij/execution/ExecutionException;
            //  51     72     75     79     Lcom/intellij/execution/ExecutionException;
            //  44     58     61     65     Lcom/intellij/execution/ExecutionException;
            //  0      40     40     44     Lcom/intellij/execution/ExecutionException;
            //  96     130    133    145    Lcom/intellij/execution/ExecutionException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        File getInputFileDir() {
            synchronized (this.myCloseOpLock) {
                return this.myInputFileDir;
            }
        }
        
        @Override
        public void close() {
            try {
                if (!this.pipeInput) {
                    return;
                }
            }
            catch (IOException ex) {
                throw b(ex);
            }
            this.closed = true;
            synchronized (this.myCloseOpLock) {
                if (this.myOutputStream != null) {
                    try {
                        this.myOutputStream.close();
                    }
                    catch (IOException ex2) {
                        LLDBDriver.LOG.warn((Throwable)ex2);
                    }
                    try {
                        if (this.myInputFileDir.exists()) {
                            FileUtil.delete(this.myInputFileDir);
                        }
                    }
                    catch (IOException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
    
    private class LLDBProcessHandler extends OSProcessHandler
    {
        final /* synthetic */ LLDBDriver this$0;
        
        public LLDBProcessHandler(final GeneralCommandLine generalCommandLine) throws ExecutionException {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandLine", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLDBProcessHandler", "<init>"));
            }
            super(generalCommandLine);
        }
        
        @NotNull
        protected BaseOutputReader.Options readerOptions() {
            BaseOutputReader.Options blocking;
            try {
                blocking = BaseOutputReader.Options.BLOCKING;
                if (blocking == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLDBProcessHandler", "readerOptions"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return blocking;
        }
        
        protected void doDestroyProcess() {
            Label_0042: {
                try {
                    if (LLDBDriver.this.myConnectedClient.isDone()) {
                        final LLDBProcessHandler lldbProcessHandler = this;
                        final LLDBDriver lldbDriver = lldbProcessHandler.this$0;
                        final ExecutionResult executionResult = lldbDriver.myConnectedClient;
                        final ProtobufServer protobufServer = executionResult.get();
                        final ProtobufServer protobufServer2 = protobufServer;
                        final Protocol.CompositeRequest compositeRequest = ProtobufMessageFactory.exit();
                        final Class clazz = null;
                        final Consumer consumer = null;
                        protobufServer2.sendMessage(compositeRequest, clazz, consumer);
                    }
                    break Label_0042;
                }
                catch (TimeoutException ex) {
                    throw b(ex);
                }
                try {
                    final LLDBProcessHandler lldbProcessHandler = this;
                    final LLDBDriver lldbDriver = lldbProcessHandler.this$0;
                    final ExecutionResult executionResult = lldbDriver.myConnectedClient;
                    final ProtobufServer protobufServer = executionResult.get();
                    final ProtobufServer protobufServer2 = protobufServer;
                    final Protocol.CompositeRequest compositeRequest = ProtobufMessageFactory.exit();
                    final Class clazz = null;
                    final Consumer consumer = null;
                    protobufServer2.sendMessage(compositeRequest, clazz, consumer);
                }
                catch (ExecutionException ex2) {}
            }
            LLDBDriver.this.myProtobufServer.tearDown();
            try {
                this.executeOnPooledThread(() -> {
                    try {
                        this.getProcess().waitFor();
                    }
                    catch (InterruptedException ex3) {}
                    return;
                }).get(4L, TimeUnit.SECONDS);
            }
            catch (TimeoutException ex4) {}
            catch (InterruptedException ex5) {}
            catch (java.util.concurrent.ExecutionException ex6) {}
            super.doDestroyProcess();
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
