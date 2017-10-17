// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.execution.debugger.breakpoints.CidrCodePointHandlerBase;
import com.intellij.openapi.util.Comparing;
import javax.swing.Icon;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.execution.debugger.evaluation.ExpiredException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicReference;
import com.intellij.execution.process.AnsiEscapeDecoder;
import com.intellij.util.Alarm;
import com.intellij.psi.util.PsiModificationTracker;
import java.util.ArrayList;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import com.intellij.execution.ui.ExecutionConsole;
import com.jetbrains.cidr.execution.debugger.backend.LLFrame;
import com.jetbrains.cidr.execution.debugger.backend.LLThread;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrEvaluatedValue;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.impl.breakpoints.XExpressionImpl;
import com.intellij.xdebugger.impl.XDebugSessionImpl;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.util.ExceptionUtil;
import javax.swing.event.HyperlinkListener;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.util.CachedValueImpl;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import com.intellij.xdebugger.frame.XStackFrame;
import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.frame.XSuspendContext;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointType;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrSymbolicBreakpointType;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrExceptionBreakpointType;
import com.intellij.openapi.ui.MessageType;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.ui.content.Content;
import com.intellij.util.ui.UIUtil;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.openapi.util.Condition;
import com.intellij.execution.console.LanguageConsoleBuilder;
import com.intellij.execution.ui.layout.PlaceInGrid;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import javax.swing.JComponent;
import com.intellij.icons.AllIcons;
import com.intellij.execution.ui.RunnerLayoutUi;
import com.intellij.xdebugger.ui.XDebugTabLayouter;
import java.util.Iterator;
import com.intellij.execution.console.LanguageConsoleImpl;
import java.util.concurrent.TimeUnit;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.intellij.openapi.util.Expirable;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.util.Consumer;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.containers.HashMap;
import com.intellij.xdebugger.XDebuggerBundle;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import java.io.File;
import com.intellij.openapi.util.Couple;
import java.util.List;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrDebuggerTypesHelper;
import com.jetbrains.cidr.execution.RunParameters;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.disasm.CidrDisasmView;
import com.intellij.execution.console.LanguageConsoleView;
import com.intellij.execution.ui.ConsoleView;
import java.util.concurrent.Semaphore;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrSymbolicBreakpointHandler;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrExceptionBreakpointHandler;
import com.intellij.xdebugger.breakpoints.XBreakpointHandler;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrWatchpointHandler;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrBreakpointHandler;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.intellij.util.concurrency.QueueProcessor;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolderEx;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.xdebugger.XDebugProcess;

public abstract class CidrDebugProcess extends XDebugProcess implements DebuggerDriver.Handler, UserDataHolderEx
{
    public static final long ABORT_COMMAND_TIMEOUT = 3000L;
    public static final String BACKEND_CONTENT_ID = "DEBUGGER_BACKEND_CONSOLE";
    public static final Key<CidrDebugProcess> DEBUG_PROCESS_KEY;
    public static final Key THROW_ON_THREAD_COLLECTION;
    public static final Key THROW_ON_FRAME_COLLECTION;
    private final UserDataHolderBase myUserDataHolder;
    private final QueueProcessor<DebuggerCommand> myCommandQueue;
    private final MyProcessHandler myProcessHandler;
    private final DebuggerDriver myDriverDoNotUse;
    private final String myDriverName;
    private final DebuggerDriverConfiguration myConfiguration;
    private final XDebuggerEditorsProvider myEditorsProvider;
    private final CidrBreakpointHandler myBreakpointHandler;
    private final CidrWatchpointHandler myWatchpointHandler;
    private final XBreakpointHandler<?>[] myBreakpointHandlers;
    private final CidrExceptionBreakpointHandler myExceptionBreakpointHandler;
    @Nullable
    private final CidrSymbolicBreakpointHandler mySymbolicBreakpointHandler;
    private final Semaphore myAttachedSemaphore;
    protected final ConsoleView myConsole;
    private volatile LanguageConsoleView myBackendConsole;
    @NotNull
    private final CidrDisasmView myDisassemblyView;
    protected final RunParameters myRunParameters;
    private final CidrDebuggerTypesHelper myTypesHelper;
    private final List<Couple<File>> mySymbolFiles;
    private volatile State myState;
    private volatile String myCurrentStateMessage;
    private final Map<Pair<String, Integer>, CachedValue<List<String>>> myCompletionCache;
    
    public CidrDebugProcess(@NotNull final RunParameters myRunParameters, @NotNull final XDebugSession xDebugSession, @NotNull final TextConsoleBuilder textConsoleBuilder) throws ExecutionException {
        if (myRunParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "<init>"));
        }
        if (xDebugSession == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "<init>"));
        }
        if (textConsoleBuilder == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consoleBuilder", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "<init>"));
        }
        super(xDebugSession);
        this.myUserDataHolder = new UserDataHolderBase();
        this.myAttachedSemaphore = new Semaphore(0);
        this.myTypesHelper = this.a();
        this.mySymbolFiles = (List<Couple<File>>)ContainerUtil.newArrayList();
        this.myState = State.INITIALIZED;
        this.myCurrentStateMessage = XDebuggerBundle.message("debugger.state.message.connecting", new Object[0]);
        this.myCompletionCache = (Map<Pair<String, Integer>, CachedValue<List<String>>>)new HashMap();
        xDebugSession.setPauseActionSupported(true);
        this.myCommandQueue = (QueueProcessor<DebuggerCommand>)new QueueProcessor((Consumer)new MyCommandProcessor(), Conditions.alwaysFalse());
        this.myRunParameters = myRunParameters;
        this.myConfiguration = myRunParameters.getDebuggerDriverConfiguration();
        this.myDriverName = this.myConfiguration.getDriverName();
        (this.myDriverDoNotUse = this.myConfiguration.createDriver(this)).start(this.myRunParameters.getInstaller(), this.myRunParameters.getArchitectureId());
        (this.myProcessHandler = new MyProcessHandler()).addProcessListener((ProcessListener)new ProcessAdapter() {
            final /* synthetic */ ProcessHandler val$driverProcessHandler = CidrDebugProcess.this.myDriverDoNotUse.getProcessHandler();
            
            public void startNotified(final ProcessEvent processEvent) {
                this.val$driverProcessHandler.startNotify();
            }
            
            public void processWillTerminate(final ProcessEvent processEvent, final boolean b) {
                CidrDebugProcess.this.a(!b);
            }
        });
        this.myEditorsProvider = a(xDebugSession.getRunProfile());
        this.myBreakpointHandler = this.createBreakpointHandler();
        this.myWatchpointHandler = new CidrWatchpointHandler(this);
        this.myExceptionBreakpointHandler = this.createExceptionHandler();
        this.mySymbolicBreakpointHandler = this.b();
        final List packNullables = ContainerUtil.packNullables((Object[])new XBreakpointHandler[] { this.myBreakpointHandler, this.myWatchpointHandler, this.myExceptionBreakpointHandler, this.mySymbolicBreakpointHandler });
        this.myBreakpointHandlers = (XBreakpointHandler<?>[])ContainerUtil.toArray(packNullables, (Object[])new XBreakpointHandler[packNullables.size()]);
        this.myConsole = textConsoleBuilder.getConsole();
        this.myDisassemblyView = new CidrDisasmView(this);
    }
    
    @NotNull
    public String getCurrentStateMessage() {
        String myCurrentStateMessage;
        try {
            myCurrentStateMessage = this.myCurrentStateMessage;
            if (myCurrentStateMessage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getCurrentStateMessage"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myCurrentStateMessage;
    }
    
    @NotNull
    public ConsoleView getConsole() {
        ConsoleView myConsole;
        try {
            myConsole = this.myConsole;
            if (myConsole == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getConsole"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myConsole;
    }
    
    @NotNull
    private CidrDebuggerTypesHelper a() throws ExecutionException {
        final CidrDebuggerLanguageSupportFactory[] array = (CidrDebuggerLanguageSupportFactory[])CidrDebuggerLanguageSupportFactory.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final CidrDebuggerTypesHelper typesHelper = array[i].createTypesHelper(this);
            CidrDebuggerTypesHelper cidrDebuggerTypesHelper = null;
            Label_0051: {
                try {
                    if (typesHelper == null) {
                        continue;
                    }
                    cidrDebuggerTypesHelper = typesHelper;
                    if (cidrDebuggerTypesHelper == null) {
                        break Label_0051;
                    }
                    return cidrDebuggerTypesHelper;
                }
                catch (ExecutionException ex) {
                    throw b((Exception)ex);
                }
                try {
                    cidrDebuggerTypesHelper = typesHelper;
                    if (cidrDebuggerTypesHelper == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createTypesHelper"));
                    }
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return cidrDebuggerTypesHelper;
        }
        throw new ExecutionException("Cannot create types helper for: " + this);
    }
    
    @NotNull
    private static XDebuggerEditorsProvider a(final RunProfile runProfile) throws ExecutionException {
        final CidrDebuggerLanguageSupportFactory[] array = (CidrDebuggerLanguageSupportFactory[])CidrDebuggerLanguageSupportFactory.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final XDebuggerEditorsProvider editor = array[i].createEditor(runProfile);
            XDebuggerEditorsProvider xDebuggerEditorsProvider = null;
            Label_0051: {
                try {
                    if (editor == null) {
                        continue;
                    }
                    xDebuggerEditorsProvider = editor;
                    if (xDebuggerEditorsProvider == null) {
                        break Label_0051;
                    }
                    return xDebuggerEditorsProvider;
                }
                catch (ExecutionException ex) {
                    throw b((Exception)ex);
                }
                try {
                    xDebuggerEditorsProvider = editor;
                    if (xDebuggerEditorsProvider == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createEditorsProvider"));
                    }
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return xDebuggerEditorsProvider;
        }
        throw new ExecutionException("Cannot create editor for: " + runProfile.getClass());
    }
    
    public boolean isDetachDefault() {
        return false;
    }
    
    private void a(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/CidrDebugProcess.myAttachedSemaphore:Ljava/util/concurrent/Semaphore;
        //     4: invokevirtual   java/util/concurrent/Semaphore.release:()V
        //     7: iload_1        
        //     8: ifeq            40
        //    11: aload_0        
        //    12: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.isDetachDefault:()Z
        //    15: ifeq            30
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    24: athrow         
        //    25: iconst_1       
        //    26: istore_1       
        //    27: goto            40
        //    30: iconst_0       
        //    31: istore_1       
        //    32: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    35: ldc             "Detaching the debug process is not supported"
        //    37: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //    40: iload_1        
        //    41: istore_2       
        //    42: new             Lcom/intellij/util/Alarm;
        //    45: dup            
        //    46: getstatic       com/intellij/util/Alarm$ThreadToUse.SWING_THREAD:Lcom/intellij/util/Alarm$ThreadToUse;
        //    49: invokespecial   com/intellij/util/Alarm.<init>:(Lcom/intellij/util/Alarm$ThreadToUse;)V
        //    52: astore_3       
        //    53: aload_0        
        //    54: aload_3        
        //    55: iload_2        
        //    56: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;Lcom/intellij/util/Alarm;Z)Ljava/lang/Runnable;
        //    61: astore          4
        //    63: aload_3        
        //    64: aload           4
        //    66: invokedynamic   run:(Ljava/lang/Runnable;)Ljava/lang/Runnable;
        //    71: ldc2_w          3000
        //    74: invokestatic    com/intellij/openapi/application/ModalityState.any:()Lcom/intellij/openapi/application/ModalityState;
        //    77: invokevirtual   com/intellij/util/Alarm.addRequest:(Ljava/lang/Runnable;JLcom/intellij/openapi/application/ModalityState;)V
        //    80: aload_0        
        //    81: new             Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$2;
        //    84: dup            
        //    85: aload_0        
        //    86: iload_2        
        //    87: aload           4
        //    89: invokespecial   com/jetbrains/cidr/execution/debugger/CidrDebugProcess$2.<init>:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;ZLjava/lang/Runnable;)V
        //    92: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.postCommand:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerCommand;)V
        //    95: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      18     21     25     Lcom/intellij/openapi/progress/ProcessCanceledException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    public boolean supportsWatchpoints() {
        return this.myDriverDoNotUse.supportsWatchpoints();
    }
    
    public boolean supportsWatchpointLifetime() {
        return this.myDriverDoNotUse.supportsWatchpointLifetime();
    }
    
    public Project getProject() {
        return this.getSession().getProject();
    }
    
    @NotNull
    public EvaluationContext createEvaluationContext(@NotNull final DebuggerDriver debuggerDriver, @Nullable final Expirable expirable, @NotNull final CidrStackFrame cidrStackFrame) {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createEvaluationContext"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (cidrStackFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createEvaluationContext"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        EvaluationContext evaluationContext;
        try {
            evaluationContext = this.myConfiguration.createEvaluationContext(debuggerDriver, expirable, cidrStackFrame);
            if (evaluationContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createEvaluationContext"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        return evaluationContext;
    }
    
    public boolean driverSupportsArrayEvaluation() {
        return this.myConfiguration.supportsArrayEvaluation();
    }
    
    public boolean driverSupportsCodeFragmentEvaluation() {
        return this.myConfiguration.isCodeFragmentEvaluationSupported();
    }
    
    public boolean driverSupportsDisasm() {
        return this.myDriverDoNotUse.supportsDisasm();
    }
    
    public DebuggerDriver getDriverInTests() {
        return this.myDriverDoNotUse;
    }
    
    protected ProcessHandler doGetProcessHandler() {
        return this.myProcessHandler;
    }
    
    protected boolean waitForTermination() {
        return this.myDriverDoNotUse.getProcessHandler().waitFor();
    }
    
    boolean waitForAttach(final int n) throws InterruptedException {
        return this.myAttachedSemaphore.tryAcquire(n, TimeUnit.MILLISECONDS);
    }
    
    public final void start() {
        this.myState = State.STARTING;
        this.myBackendConsole = new LanguageConsoleImpl(this.getProject(), this.myDriverName, this.myDriverDoNotUse.getConsoleLanguage());
        this.myBackendConsole.getConsoleEditor().getDocument().putUserData((Key)CidrDebugProcess.DEBUG_PROCESS_KEY, (Object)this);
        this.myBackendConsole.setEditable(false);
        final Iterator<Couple<File>> iterator;
        Couple<File> couple;
        this.postCommand(debuggerDriver -> {
            this.doStart(debuggerDriver);
            synchronized (this.mySymbolFiles) {
                this.mySymbolFiles.iterator();
                while (iterator.hasNext()) {
                    couple = iterator.next();
                    debuggerDriver.addSymbolsFile((File)couple.first, (File)couple.second);
                }
                this.mySymbolFiles.clear();
            }
            this.myState = State.STARTED;
        });
    }
    
    protected abstract void doStart(@NotNull final DebuggerDriver p0) throws ExecutionException;
    
    @NotNull
    public XDebugTabLayouter createTabLayouter() {
        XDebugTabLayouter xDebugTabLayouter;
        try {
            xDebugTabLayouter = new XDebugTabLayouter() {
                public void registerAdditionalContent(@NotNull final RunnerLayoutUi runnerLayoutUi) {
                    try {
                        if (runnerLayoutUi == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ui", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$3", "registerAdditionalContent"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final Content content = runnerLayoutUi.createContent("DEBUGGER_BACKEND_CONSOLE", CidrDebugProcess.this.myBackendConsole.getComponent(), CidrDebugProcess.this.myDriverName, AllIcons.Debugger.ToolConsole, (JComponent)null);
                    Disposer.register((Disposable)runnerLayoutUi.getContentManager(), (Disposable)CidrDebugProcess.this.myBackendConsole);
                    content.setCloseable(false);
                    runnerLayoutUi.addContent(content, 0, PlaceInGrid.center, false);
                    final BackendConsoleInjectionHelper[] array = (BackendConsoleInjectionHelper[])BackendConsoleInjectionHelper.EP_NAME.getExtensions();
                    for (int length = array.length, i = 0; i < length; ++i) {
                        array[i].subscribeToInjection(CidrDebugProcess.this.getSession());
                    }
                    LanguageConsoleBuilder.registerExecuteAction(CidrDebugProcess.this.myBackendConsole, (Consumer<String>)(s -> CidrDebugProcess.this.executeConsoleCommand(s)), "AppCode.Debug.Console", null, null);
                    CidrDebugProcess.this.getSession().addSessionListener((XDebugSessionListener)new XDebugSessionListener() {
                        public void sessionPaused() {
                            this.b(true);
                        }
                        
                        public void sessionResumed() {
                            this.b(false);
                        }
                        
                        public void sessionStopped() {
                            this.b(false);
                        }
                        
                        private void b(final boolean editable) {
                            UIUtil.invokeAndWaitIfNeeded(() -> {
                                if (!CidrDebugProcess.this.getProject().isDisposed() && !Disposer.isDisposed((Disposable)CidrDebugProcess.this.myBackendConsole)) {
                                    CidrDebugProcess.this.myBackendConsole.setEditable(editable);
                                }
                            });
                        }
                    });
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            if (xDebugTabLayouter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createTabLayouter"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return xDebugTabLayouter;
    }
    
    public boolean checkCanPerformCommands() {
        if (this.myDriverDoNotUse.isInPromptMode()) {
            this.getSession().reportMessage(CidrDebuggerBundle.message("debug.command.error.inPrompt", new Object[0]), MessageType.WARNING);
            final RunnerLayoutUi ui = this.getSession().getUI();
            ui.selectAndFocus(ui.findContent("DEBUGGER_BACKEND_CONSOLE"), true, true);
            return false;
        }
        return true;
    }
    
    @NotNull
    public XDebuggerEditorsProvider getEditorsProvider() {
        XDebuggerEditorsProvider myEditorsProvider;
        try {
            myEditorsProvider = this.myEditorsProvider;
            if (myEditorsProvider == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getEditorsProvider"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myEditorsProvider;
    }
    
    @NotNull
    protected CidrBreakpointHandler createBreakpointHandler() {
        CidrBreakpointHandler cidrBreakpointHandler;
        try {
            cidrBreakpointHandler = new CidrBreakpointHandler(this);
            if (cidrBreakpointHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createBreakpointHandler"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return cidrBreakpointHandler;
    }
    
    @NotNull
    protected CidrExceptionBreakpointHandler createExceptionHandler() {
        CidrExceptionBreakpointHandler cidrExceptionBreakpointHandler;
        try {
            cidrExceptionBreakpointHandler = new CidrExceptionBreakpointHandler(this, CidrExceptionBreakpointType.class);
            if (cidrExceptionBreakpointHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createExceptionHandler"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return cidrExceptionBreakpointHandler;
    }
    
    @Nullable
    private CidrSymbolicBreakpointHandler b() {
        final CidrSymbolicBreakpointType cidrSymbolicBreakpointType = (CidrSymbolicBreakpointType)XBreakpointType.EXTENSION_POINT_NAME.findExtension((Class)CidrSymbolicBreakpointType.class);
        try {
            if (cidrSymbolicBreakpointType != null) {
                return new CidrSymbolicBreakpointHandler(this, cidrSymbolicBreakpointType.getClass());
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return null;
    }
    
    @NotNull
    public XBreakpointHandler<?>[] getBreakpointHandlers() {
        XBreakpointHandler<?>[] myBreakpointHandlers;
        try {
            myBreakpointHandlers = this.myBreakpointHandlers;
            if (myBreakpointHandlers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getBreakpointHandlers"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myBreakpointHandlers;
    }
    
    @NotNull
    public ConsoleView createConsole() {
        ConsoleView myConsole;
        try {
            this.myConsole.attachToProcess((ProcessHandler)this.myProcessHandler);
            myConsole = this.myConsole;
            if (myConsole == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createConsole"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myConsole;
    }
    
    public LanguageConsoleView getDebuggerConsole() {
        return this.myBackendConsole;
    }
    
    public void sessionInitialized() {
        this.postCommand(debuggerDriver -> {
            try {
                if (this.myState != State.STARTED) {
                    return;
                }
            }
            catch (ExecutionException ex) {
                throw b((Exception)ex);
            }
            this.doLaunchTarget(debuggerDriver);
            this.myCurrentStateMessage = XDebuggerBundle.message("debugger.state.message.connected", new Object[0]);
            this.getSession().rebuildViews();
        });
    }
    
    protected abstract void doLaunchTarget(@NotNull final DebuggerDriver p0) throws ExecutionException;
    
    public void stop() {
        UIUtil.invokeLaterIfNeeded(() -> {
            try {
                if (this.getProject().isDisposed()) {
                    return;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            this.myWatchpointHandler.cleanup();
            return;
        });
        this.getProcessHandler().destroyProcess();
    }
    
    public void startPausing() {
        this.postCommand(DebuggerDriver::interrupt);
    }
    
    public void resume(@Nullable final XSuspendContext xSuspendContext) {
        this.postCommand(DebuggerDriver::resume);
    }
    
    public void runToPosition(@NotNull final XSourcePosition xSourcePosition, @Nullable final XSuspendContext xSuspendContext) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "runToPosition"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        final IllegalArgumentException ex2;
        this.postCommand(debuggerDriver -> {
            try {
                if (xSourcePosition == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "lambda$runToPosition$5"));
                    throw ex2;
                }
            }
            catch (ExecutionException ex3) {
                throw b((Exception)ex3);
            }
            debuggerDriver.runTo(xSourcePosition.getFile().getPath(), xSourcePosition.getLine());
        });
    }
    
    private static boolean a(@Nullable final XSuspendContext xSuspendContext) {
        try {
            if (xSuspendContext == null) {
                return false;
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        final XExecutionStack activeExecutionStack = xSuspendContext.getActiveExecutionStack();
        try {
            if (activeExecutionStack == null) {
                return false;
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        final XStackFrame topFrame = activeExecutionStack.getTopFrame();
        try {
            if (!(topFrame instanceof CidrStackFrame)) {
                return false;
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (!((CidrStackFrame)topFrame).hasSourceFile()) {
                return true;
            }
        }
        catch (ProcessCanceledException ex4) {
            throw b((Exception)ex4);
        }
        return false;
    }
    
    public void startStepOver(@Nullable final XSuspendContext xSuspendContext) {
        this.postCommand(debuggerDriver -> debuggerDriver.stepOver(a(xSuspendContext)));
    }
    
    public void startStepInto(@Nullable final XSuspendContext xSuspendContext) {
        this.postCommand(debuggerDriver -> debuggerDriver.stepInto(false, a(xSuspendContext)));
    }
    
    public void startForceStepInto(@Nullable final XSuspendContext xSuspendContext) {
        this.postCommand(debuggerDriver -> debuggerDriver.stepInto(true, a(xSuspendContext)));
    }
    
    public void startStepOut(@Nullable final XSuspendContext xSuspendContext) {
        this.postCommand(DebuggerDriver::stepOut);
    }
    
    @Nullable
    public XSourcePosition createDisasmPosition(@NotNull final Address address) {
        try {
            if (address == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "createDisasmPosition"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (!this.driverSupportsDisasm()) {
                return null;
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        return this.myDisassemblyView.disassemble(address);
    }
    
    protected void executeConsoleCommand(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "executeConsoleCommand"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        final IllegalArgumentException ex2;
        final long n;
        final int n2;
        this.postCommand(debuggerDriver -> {
            this.getCurrentThreadId();
            this.getCurrentFrameIndex();
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "lambda$executeConsoleCommand$9"));
                    throw ex2;
                }
            }
            catch (DebuggerCommandException ex3) {
                throw b(ex3);
            }
            try {
                debuggerDriver.executeConsoleCommand(n, n2, s);
            }
            catch (DebuggerCommandException ex4) {
                this.printlnToConsole(ex4.getMessage());
            }
        });
    }
    
    public void postCommand(@NotNull final DebuggerCommand debuggerCommand) {
        try {
            if (debuggerCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "postCommand"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.myCommandQueue.add((Object)debuggerCommand);
    }
    
    public void handleConsoleCompletion(final CompletionParameters completionParameters, final CompletionResultSet set) {
        final String text = completionParameters.getOriginalFile().getText();
        int n = 0;
        Label_0031: {
            try {
                if (completionParameters.getOffset() > 0) {
                    n = completionParameters.getOffset() - 1;
                    break Label_0031;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            n = 0;
        }
        final int n2 = n;
        final Pair pair = new Pair((Object)text, (Object)n2);
        Object o = this.myCompletionCache.get(pair);
        if (o == null) {
            o = new CachedValueImpl<List<String>>(() -> {
                final ArrayList<String> list = new ArrayList<String>();
                final Semaphore semaphore = new Semaphore(0);
                this.postCommand(debuggerDriver -> debuggerDriver.handleCompletion(text, n2, list));
                try {
                    semaphore.tryAcquire(2000L, TimeUnit.MILLISECONDS);
                }
                catch (InterruptedException ex) {}
                return new CachedValueProvider.Result((Object)list, new Object[] { PsiModificationTracker.MODIFICATION_COUNT });
            }) {
                @Override
                public boolean isFromMyProject(final Project project) {
                    return true;
                }
            };
        }
        this.myCompletionCache.put((Pair<String, Integer>)pair, (CachedValue<List<String>>)o);
        final Iterator iterator = ((List)((CachedValue)o).getValue()).iterator();
        while (iterator.hasNext()) {
            set.addElement((LookupElement)LookupElementBuilder.create((String)iterator.next()));
        }
    }
    
    @Nullable
    public PsiElement getDebuggerContext() {
        return this.getDebuggerContext(this.getCurrentPosition());
    }
    
    @Nullable
    public PsiElement getDebuggerContext(@Nullable final XSourcePosition xSourcePosition) {
        final AccessToken acquireReadActionLock = ApplicationManager.getApplication().acquireReadActionLock();
        try {
            return this.getTypesHelper().getContextElement(xSourcePosition);
        }
        finally {
            acquireReadActionLock.finish();
        }
    }
    
    @Nullable
    public <T> T getUserData(@NotNull final Key<T> key) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getUserData"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return (T)this.myUserDataHolder.getUserData((Key)key);
    }
    
    public <T> void putUserData(@NotNull final Key<T> key, @Nullable final T t) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "putUserData"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.myUserDataHolder.putUserData((Key)key, (Object)t);
    }
    
    @NotNull
    public <T> T putUserDataIfAbsent(@NotNull final Key<T> key, @NotNull final T t) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "putUserDataIfAbsent"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "putUserDataIfAbsent"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        Object putUserDataIfAbsent;
        try {
            putUserDataIfAbsent = this.myUserDataHolder.putUserDataIfAbsent((Key)key, (Object)t);
            if (putUserDataIfAbsent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "putUserDataIfAbsent"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        return (T)putUserDataIfAbsent;
    }
    
    public <T> boolean replace(@NotNull final Key<T> key, @Nullable final T t, @Nullable final T t2) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "replace"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return this.myUserDataHolder.replace((Key)key, (Object)t, (Object)t2);
    }
    
    @NotNull
    public CidrDebuggerTypesHelper getTypesHelper() {
        CidrDebuggerTypesHelper myTypesHelper;
        try {
            myTypesHelper = this.myTypesHelper;
            if (myTypesHelper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getTypesHelper"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myTypesHelper;
    }
    
    public void addSymbolsFile(final File file, final File file2) {
        Logger log = null;
        boolean b = false;
        Label_0022: {
            try {
                log = CidrDebuggerLog.LOG;
                if (this.myState == State.INITIALIZED) {
                    b = true;
                    break Label_0022;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            b = false;
        }
        log.assertTrue(b, (Object)("Session is already started: " + this.myState));
        synchronized (this.mySymbolFiles) {
            this.mySymbolFiles.add((Couple<File>)Couple.of((Object)file, (Object)file2));
        }
    }
    
    @NotNull
    public RunParameters getRunParameters() {
        RunParameters myRunParameters;
        try {
            myRunParameters = this.myRunParameters;
            if (myRunParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "getRunParameters"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myRunParameters;
    }
    
    protected void handleCommandException(@NotNull final DebuggerDriver p0, @NotNull final DebuggerCommand p1, @NotNull final ExecutionException p2) {
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
        //    18: ldc             "driver"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebugProcess"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "handleCommandException"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "command"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebugProcess"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "handleCommandException"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "exception"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebugProcess"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "handleCommandException"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload_3        
        //   133: ldc             Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerIllegalStateException;.class
        //   135: invokestatic    com/intellij/util/ExceptionUtil.findCause:(Ljava/lang/Throwable;Ljava/lang/Class;)Ljava/lang/Object;
        //   138: checkcast       Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerIllegalStateException;
        //   141: astore          4
        //   143: aload           4
        //   145: ifnull          199
        //   148: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   151: aload_3        
        //   152: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/Throwable;)V
        //   155: aload           4
        //   157: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerUtil.getExceptionMessage:(Ljava/lang/Exception;)Ljava/lang/String;
        //   160: astore          5
        //   162: aload_2        
        //   163: aload           5
        //   165: invokeinterface com/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerCommand.rejected:(Ljava/lang/String;)V
        //   170: aload_2        
        //   171: instanceof      Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerUIUpdateCommand;
        //   174: ifne            198
        //   177: aload_0        
        //   178: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.getSession:()Lcom/intellij/xdebugger/XDebugSession;
        //   181: aload           5
        //   183: getstatic       com/intellij/openapi/ui/MessageType.WARNING:Lcom/intellij/openapi/ui/MessageType;
        //   186: invokeinterface com/intellij/xdebugger/XDebugSession.reportMessage:(Ljava/lang/String;Lcom/intellij/openapi/ui/MessageType;)V
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   197: athrow         
        //   198: return         
        //   199: aload_3        
        //   200: ldc             Lcom/intellij/execution/ExecutionFinishedException;.class
        //   202: invokestatic    com/intellij/util/ExceptionUtil.causedBy:(Ljava/lang/Throwable;Ljava/lang/Class;)Z
        //   205: ifeq            248
        //   208: aload_3        
        //   209: ldc             Lcom/intellij/openapi/progress/ProcessCanceledException;.class
        //   211: invokestatic    com/intellij/util/ExceptionUtil.causedBy:(Ljava/lang/Throwable;Ljava/lang/Class;)Z
        //   214: ifne            238
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   223: athrow         
        //   224: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   227: aload_3        
        //   228: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/Throwable;)V
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   237: athrow         
        //   238: aload_0        
        //   239: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.getSession:()Lcom/intellij/xdebugger/XDebugSession;
        //   242: invokeinterface com/intellij/xdebugger/XDebugSession.stop:()V
        //   247: return         
        //   248: aload_3        
        //   249: ldc             Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerFatalException;.class
        //   251: invokestatic    com/intellij/util/ExceptionUtil.causedBy:(Ljava/lang/Throwable;Ljava/lang/Class;)Z
        //   254: istore          5
        //   256: aload_2        
        //   257: instanceof      Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerStartupCommand;
        //   260: ifne            275
        //   263: iload           5
        //   265: ifeq            334
        //   268: goto            275
        //   271: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   274: athrow         
        //   275: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   278: aload_3        
        //   279: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/Throwable;)V
        //   282: aload_0        
        //   283: aload_3        
        //   284: invokevirtual   com/intellij/execution/ExecutionException.getMessage:()Ljava/lang/String;
        //   287: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.printlnToConsole:(Ljava/lang/String;)V
        //   290: aload_0        
        //   291: aload_3        
        //   292: invokespecial   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.a:(Lcom/intellij/execution/ExecutionException;)V
        //   295: iload           5
        //   297: ifeq            324
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   306: athrow         
        //   307: aload_0        
        //   308: getfield        com/jetbrains/cidr/execution/debugger/CidrDebugProcess.myDriverDoNotUse:Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver;
        //   311: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver.getProcessHandler:()Lcom/intellij/execution/process/ProcessHandler;
        //   314: invokevirtual   com/intellij/execution/process/ProcessHandler.destroyProcess:()V
        //   317: goto            333
        //   320: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   323: athrow         
        //   324: aload_0        
        //   325: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.getSession:()Lcom/intellij/xdebugger/XDebugSession;
        //   328: invokeinterface com/intellij/xdebugger/XDebugSession.stop:()V
        //   333: return         
        //   334: aload_3        
        //   335: instanceof      Lcom/jetbrains/cidr/execution/debugger/ThrowInTest$TestExecutionException;
        //   338: ifne            380
        //   341: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   344: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   349: ifeq            373
        //   352: goto            359
        //   355: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   358: athrow         
        //   359: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   362: aload_3        
        //   363: invokevirtual   com/intellij/openapi/diagnostic/Logger.warn:(Ljava/lang/Throwable;)V
        //   366: goto            380
        //   369: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   372: athrow         
        //   373: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   376: aload_3        
        //   377: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/Throwable;)V
        //   380: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  44     84     84     88     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  88     128    128    132    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  162    191    194    198    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  199    217    220    224    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  208    231    234    238    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  256    268    271    275    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  263    300    303    307    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  275    320    320    324    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  334    352    355    359    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  341    369    369    373    Lcom/intellij/openapi/progress/ProcessCanceledException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0275:
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
    
    private void a(final ExecutionException ex) {
        String s = ex.getMessage();
        if (StringUtil.isEmptyOrSpaces(s)) {
            CidrDebuggerLog.LOG.error("Execution errors must have error description", (Throwable)ex);
            s = CidrDebuggerUtil.getExceptionMessage((Exception)ex);
        }
        this.getSession().reportMessage(s, MessageType.ERROR, (HyperlinkListener)ExceptionUtil.findCause((Throwable)ex, (Class)HyperlinkListener.class));
    }
    
    public void printlnToConsole(@Nullable String s) {
        try {
            if (s == null || this.getProject().isDisposed()) {
                return;
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        s = StringUtil.stripHtml(s, true);
        if (!StringUtil.endsWithLineBreak((CharSequence)s)) {
            s += "\n";
        }
        this.getProcessHandler().notifyTextAvailable(s, ProcessOutputTypes.SYSTEM);
    }
    
    @Nullable
    XSourcePosition getCurrentPosition() {
        final XStackFrame currentStackFrame = this.getSession().getCurrentStackFrame();
        try {
            if (currentStackFrame != null) {
                return currentStackFrame.getSourcePosition();
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return null;
    }
    
    public long getCurrentThreadId() {
        final CidrStackFrame cidrStackFrame = (CidrStackFrame)this.getSession().getCurrentStackFrame();
        try {
            if (cidrStackFrame != null) {
                return cidrStackFrame.getThreadId();
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return -1L;
    }
    
    public int getCurrentFrameIndex() {
        final CidrStackFrame cidrStackFrame = (CidrStackFrame)this.getSession().getCurrentStackFrame();
        try {
            if (cidrStackFrame != null) {
                return cidrStackFrame.getFrameIndex();
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return -1;
    }
    
    public void handleRunning() {
        this.getSession().sessionResumed();
    }
    
    public void handleInterrupted(@NotNull final DebuggerDriver.StopPlace stopPlace) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleInterrupted"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.getSession().positionReached((XSuspendContext)new CidrSuspendContext(stopPlace, null));
    }
    
    public void handleSignal(@NotNull final DebuggerDriver.StopPlace stopPlace, @NotNull final String s, @NotNull final String s2) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleSignal"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signal", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleSignal"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "meaning", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleSignal"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        this.a(stopPlace, new CidrSuspensionCause("Signal", s + " (" + s2 + ")"));
    }
    
    public void handleException(@NotNull final DebuggerDriver.StopPlace stopPlace, @NotNull final String s) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleException"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "description", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleException"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        this.a(stopPlace, new CidrSuspensionCause("Exception", s));
    }
    
    private void a(@NotNull final DebuggerDriver.StopPlace stopPlace, @NotNull final CidrSuspensionCause cidrSuspensionCause) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "doHandleSignalled"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (cidrSuspensionCause == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cause", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "doHandleSignalled"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        this.printlnToConsole(cidrSuspensionCause.getDisplayString());
        ((XDebugSessionImpl)this.getSession()).positionReached(new CidrSuspendContext(stopPlace, cidrSuspensionCause), true);
    }
    
    public void handleBreakpoint(@NotNull final DebuggerDriver.StopPlace stopPlace, final int n) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleBreakpoint"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        XBreakpoint<?> xBreakpoint = ((CidrCodePointHandlerBase<XBreakpoint<?>>)this.myBreakpointHandler).getCodepoint(n);
        if (xBreakpoint == null) {
            xBreakpoint = ((CidrCodePointHandlerBase<XBreakpoint<?>>)this.myExceptionBreakpointHandler).getCodepoint(n);
        }
        Label_0093: {
            try {
                if (xBreakpoint != null || this.mySymbolicBreakpointHandler == null) {
                    break Label_0093;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
            xBreakpoint = ((CidrCodePointHandlerBase<XBreakpoint<?>>)this.mySymbolicBreakpointHandler).getCodepoint(n);
        }
        this.handleCodepoint(stopPlace, xBreakpoint);
    }
    
    public void handleWatchpoint(@NotNull final DebuggerDriver.StopPlace stopPlace, final int n) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleWatchpoint"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.handleCodepoint(stopPlace, ((CidrCodePointHandlerBase<XBreakpoint<?>>)this.myWatchpointHandler).getCodepoint(n));
    }
    
    public void handleAttached(final int n) {
        this.printlnToConsole(CidrDebuggerBundle.message("debugger.attachedTo", n));
        this.myAttachedSemaphore.release();
    }
    
    public void handleConnected(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "connection", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleConnected"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.printlnToConsole(CidrDebuggerBundle.message("debugger.connectedTo", s));
    }
    
    public void handleDetached() {
        this.printlnToConsole(CidrDebuggerBundle.message("debugger.detached", new Object[0]));
        this.getSession().stop();
    }
    
    public void handleDisconnected() {
        this.printlnToConsole(CidrDebuggerBundle.message("debugger.disconnected", new Object[0]));
        this.getSession().stop();
    }
    
    protected void handleCodepoint(@NotNull final DebuggerDriver.StopPlace stopPlace, @Nullable final XBreakpoint<?> xBreakpoint) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleCodepoint"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        CidrSuspensionCause cidrSuspensionCause = null;
        XBreakpointProperties properties = null;
        Label_0064: {
            try {
                if (xBreakpoint != null) {
                    properties = xBreakpoint.getProperties();
                    break Label_0064;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
            properties = null;
        }
        if (properties instanceof CidrExceptionBreakpointType.Properties) {
            cidrSuspensionCause = new CidrSuspensionCause("Exception", "Exception breakpoint");
        }
        final CidrSuspendContext cidrSuspendContext = new CidrSuspendContext(stopPlace, cidrSuspensionCause);
        try {
            if (xBreakpoint == null) {
                ((XDebugSessionImpl)this.getSession()).positionReached(cidrSuspendContext, true);
                return;
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        if (xBreakpoint.getLogExpression() == null) {
            final boolean breakpointReached = this.getSession().breakpointReached((XBreakpoint)xBreakpoint, (String)null, (XSuspendContext)cidrSuspendContext);
            try {
                if (!breakpointReached) {
                    this.postCommand(DebuggerDriver::resume);
                }
            }
            catch (ProcessCanceledException ex4) {
                throw b((Exception)ex4);
            }
            return;
        }
        final CidrSuspendContext cidrSuspendContext2;
        final XExecutionStack xExecutionStack;
        final XStackFrame xStackFrame;
        final CidrEvaluator cidrEvaluator2;
        final CidrEvaluator cidrEvaluator;
        final XSourcePosition xSourcePosition;
        final String s;
        final CidrEvaluatedValue cidrEvaluatedValue;
        String s2;
        final boolean b;
        this.postCommand(debuggerDriver -> {
            cidrSuspendContext2.getActiveExecutionStack();
            Label_0027_1: {
                try {
                    if (xExecutionStack != null) {
                        xExecutionStack.getTopFrame();
                        break Label_0027_1;
                    }
                }
                catch (DebuggerCommandException ex5) {
                    throw b(ex5);
                }
            }
            Label_0047_1: {
                try {
                    if (xStackFrame != null) {
                        xStackFrame.getSourcePosition();
                        break Label_0047_1;
                    }
                }
                catch (DebuggerCommandException ex6) {
                    throw b(ex6);
                }
            }
            Label_0067_1: {
                try {
                    if (xStackFrame != null) {
                        xStackFrame.getEvaluator();
                        break Label_0067_1;
                    }
                }
                catch (DebuggerCommandException ex7) {
                    throw b(ex7);
                }
            }
            cidrEvaluator = cidrEvaluator2;
            try {
                if (cidrEvaluator != null) {
                    try {
                        cidrEvaluator.doEvaluate(debuggerDriver, xSourcePosition, (XExpression)XExpressionImpl.fromText(s));
                        s2 = s + " = " + cidrEvaluatedValue.getConsoleDescription(cidrEvaluatedValue.createEvaluationContext(debuggerDriver, null));
                    }
                    catch (DebuggerCommandException ex8) {
                        s2 = "error evaluating " + s + ": " + ex8.getMessage();
                    }
                }
                else {
                    s2 = "error evaluating " + s;
                }
            }
            finally {
                this.getSession().breakpointReached((XBreakpoint)xBreakpoint, s2, (XSuspendContext)cidrSuspendContext2);
                try {
                    if (!b) {
                        debuggerDriver.resume();
                    }
                }
                catch (DebuggerCommandException ex9) {
                    throw b(ex9);
                }
            }
        });
    }
    
    public void handleTargetOutput(@NotNull final String s, @NotNull final Key key) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleTargetOutput"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleTargetOutput"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        this.myProcessHandler.notifyTextAvailable(s, key);
    }
    
    public void handleGDBOutput(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleGDBOutput"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        final IllegalArgumentException ex2;
        UIUtil.invokeLaterIfNeeded(() -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "lambda$handleGDBOutput$13"));
                    throw ex2;
                }
            }
            catch (ProcessCanceledException ex3) {
                throw b((Exception)ex3);
            }
            this.myBackendConsole.print(s, ConsoleViewContentType.NORMAL_OUTPUT);
        });
    }
    
    public void handlePrompt(@NotNull final String p0) {
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
        //    18: ldc             "prompt"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebugProcess"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "handlePrompt"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;Ljava/lang/String;)Ljava/lang/Runnable;
        //    51: invokestatic    com/intellij/util/ui/UIUtil.invokeLaterIfNeeded:(Ljava/lang/Runnable;)V
        //    54: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072_1:
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
    
    public void handleTargetFinished(final int exitCode, @Nullable final String s) {
        try {
            this.myProcessHandler.setExitCode(exitCode);
            if (s != null) {
                this.myProcessHandler.notifyTextAvailable(s + "\n", ProcessOutputTypes.SYSTEM);
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.getSession().stop();
    }
    
    public void handleTargetTerminated() {
        this.getSession().stop();
    }
    
    public void handleExited(final int n) {
        this.getSession().stop();
    }
    
    public void handleWatchpointScope(final int n) {
        UIUtil.invokeLaterIfNeeded(() -> this.myWatchpointHandler.handleWatchpointScope(n));
        this.postCommand(DebuggerDriver::resume);
    }
    
    public void handleModulesLoaded(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modules", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "handleModulesLoaded"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
    }
    
    @NotNull
    protected XExecutionStack newExecutionStack(@NotNull final LLThread llThread, @Nullable final LLFrame llFrame, final boolean b, @Nullable final CidrSuspensionCause cidrSuspensionCause) {
        try {
            if (llThread == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thread", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "newExecutionStack"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        CidrExecutionStack cidrExecutionStack;
        try {
            cidrExecutionStack = new CidrExecutionStack(llThread, llFrame, b, cidrSuspensionCause);
            if (cidrExecutionStack == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "newExecutionStack"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        return cidrExecutionStack;
    }
    
    public static boolean viewsUpdatesDisabledInTests(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess", "viewsUpdatesDisabledInTests"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        Label_0077: {
            try {
                if (!ApplicationManager.getApplication().isUnitTestMode()) {
                    return false;
                }
                final Object o2 = o;
                final Class<?> clazz = o2.getClass();
                final String s = clazz.getSimpleName();
                final String s2 = "XTest";
                final boolean b = s.contains(s2);
                if (!b) {
                    break Label_0077;
                }
                return false;
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
            try {
                final Object o2 = o;
                final Class<?> clazz = o2.getClass();
                final String s = clazz.getSimpleName();
                final String s2 = "XTest";
                final boolean b = s.contains(s2);
                if (!b) {
                    return true;
                }
            }
            catch (ProcessCanceledException ex3) {
                throw b((Exception)ex3);
            }
        }
        return false;
    }
    
    static {
        DEBUG_PROCESS_KEY = Key.create(CidrDebugProcess.class.getSimpleName());
        THROW_ON_THREAD_COLLECTION = Key.create("THROW_ON_THREAD_COLLECTION");
        THROW_ON_FRAME_COLLECTION = Key.create("THROW_ON_FRAME_COLLECTION");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    enum State
    {
        INITIALIZED, 
        STARTING, 
        STARTED, 
        FINISHED, 
        DETACHED;
    }
    
    public interface DebuggerCommand
    {
        void run(@NotNull final DebuggerDriver p0) throws ExecutionException;
        
        default void rejected(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerCommand", "rejected"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private class MyProcessHandler extends ProcessHandler implements AnsiEscapeDecoder.ColoredTextAcceptor
    {
        private final AtomicReference<Integer> myExitCode;
        private final AnsiEscapeDecoder myAnsiEscapeDecoder;
        
        private MyProcessHandler() {
            this.myExitCode = new AtomicReference<Integer>();
            this.myAnsiEscapeDecoder = new AnsiEscapeDecoder();
        }
        
        public void setExitCode(final int n) {
            this.myExitCode.set(n);
        }
        
        @NotNull
        public Integer getExitCode() {
            final Integer n = this.myExitCode.get();
            Integer value = null;
            Label_0027: {
                try {
                    if (n == null) {
                        final int intValue = 0;
                        break Label_0027;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final int intValue = n;
                try {
                    value = intValue;
                    if (value == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler", "getExitCode"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return value;
        }
        
        protected void destroyProcessImpl() {
            this.a(false);
        }
        
        protected void detachProcessImpl() {
            this.a(true);
        }
        
        private void a(final boolean p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
            //     3: aload_0        
            //     4: iload_1        
            //     5: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler;Z)Ljava/lang/Runnable;
            //    10: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
            //    15: pop            
            //    16: return         
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
            //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
        
        public boolean detachIsDefault() {
            return CidrDebugProcess.this.isDetachDefault();
        }
        
        public OutputStream getProcessInput() {
            return CidrDebugProcess.this.myDriverDoNotUse.getProcessInput();
        }
        
        public final void notifyTextAvailable(final String s, final Key key) {
            this.myAnsiEscapeDecoder.escapeText(s, key, (AnsiEscapeDecoder.ColoredTextAcceptor)this);
        }
        
        public void coloredTextAvailable(@NotNull final String s, @NotNull final Key key) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler", "coloredTextAvailable"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (key == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler", "coloredTextAvailable"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            super.notifyTextAvailable(s, key);
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    private class MyCommandProcessor implements Consumer<DebuggerCommand>
    {
        public void consume(final DebuggerCommand debuggerCommand) {
            final ProcessHandler processHandler = CidrDebugProcess.this.myDriverDoNotUse.getProcessHandler();
            Label_0045: {
                Label_0032: {
                    try {
                        if (processHandler.isProcessTerminating()) {
                            break Label_0032;
                        }
                        final ProcessHandler processHandler2 = processHandler;
                        final boolean b = processHandler2.isProcessTerminated();
                        if (b) {
                            break Label_0032;
                        }
                        break Label_0045;
                    }
                    catch (ExpiredException ex) {
                        throw b(ex);
                    }
                    try {
                        final ProcessHandler processHandler2 = processHandler;
                        final boolean b = processHandler2.isProcessTerminated();
                        if (b) {
                            debuggerCommand.rejected("Process finished");
                            return;
                        }
                    }
                    catch (ExpiredException ex2) {
                        throw b(ex2);
                    }
                }
                try {
                    debuggerCommand.run(CidrDebugProcess.this.myDriverDoNotUse);
                }
                catch (ExpiredException ex4) {}
                catch (ExecutionException ex3) {
                    CidrDebugProcess.this.handleCommandException(CidrDebugProcess.this.myDriverDoNotUse, debuggerCommand, ex3);
                }
            }
        }
        
        private static ExpiredException b(final ExpiredException ex) {
            return ex;
        }
    }
    
    private class CidrSuspendContext extends XSuspendContext
    {
        @NotNull
        private final XExecutionStack myActiveStack;
        private final long myActiveStackFrameId;
        
        public CidrSuspendContext(@Nullable final DebuggerDriver.StopPlace stopPlace, final CidrSuspensionCause cidrSuspensionCause) {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrSuspendContext", "<init>"));
            }
            this.myActiveStack = CidrDebugProcess.this.newExecutionStack(stopPlace.thread, stopPlace.frame, true, cidrSuspensionCause);
            this.myActiveStackFrameId = stopPlace.thread.getId();
        }
        
        @Nullable
        public XExecutionStack getActiveExecutionStack() {
            return this.myActiveStack;
        }
        
        public void computeExecutionStacks(final XSuspendContext.XExecutionStackContainer xExecutionStackContainer) {
            try {
                if (CidrDebugProcess.viewsUpdatesDisabledInTests(xExecutionStackContainer)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final List<LLThread> list2;
            ArrayList list;
            final Iterator<LLThread> iterator;
            LLThread llThread;
            XExecutionStack myActiveStack;
            final List<XExecutionStack> list3;
            CidrDebugProcess.this.postCommand(debuggerDriver -> {
                try {
                    ThrowInTest.doThrow((UserDataHolder)CidrDebugProcess.this, CidrDebugProcess.THROW_ON_THREAD_COLLECTION);
                    debuggerDriver.getThreads();
                    list = new ArrayList(list2.size());
                    list2.iterator();
                    while (iterator.hasNext()) {
                        llThread = iterator.next();
                        Label_0098_1: {
                            try {
                                if (llThread.getId() == this.myActiveStackFrameId) {
                                    myActiveStack = this.myActiveStack;
                                    break Label_0098_1;
                                }
                            }
                            catch (DebuggerCommandException ex2) {
                                throw b(ex2);
                            }
                            CidrDebugProcess.this.newExecutionStack(llThread, null, false, null);
                        }
                        list3.add(myActiveStack);
                    }
                    xExecutionStackContainer.addExecutionStack((List)list, true);
                }
                catch (DebuggerCommandException ex3) {
                    xExecutionStackContainer.errorOccurred(ex3.getMessage());
                }
                catch (ExecutionException ex4) {
                    xExecutionStackContainer.errorOccurred(CidrDebuggerUtil.getExceptionMessage((Exception)ex4));
                    throw ex4;
                }
            });
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
    
    private class CidrExecutionStack extends XExecutionStack
    {
        @NotNull
        private final LLThread myThread;
        @Nullable
        private volatile CidrStackFrame myTopFrame;
        @Nullable
        private final CidrSuspensionCause mySuspensionCause;
        
        public CidrExecutionStack(@Nullable final LLThread myThread, final LLFrame llFrame, @Nullable final boolean b, final CidrSuspensionCause mySuspensionCause) {
            if (myThread == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thread", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack", "<init>"));
            }
            super(myThread.getDisplayName(), b ? AllIcons.Debugger.ThreadCurrent : AllIcons.Debugger.ThreadSuspended);
            this.myThread = myThread;
            this.mySuspensionCause = mySuspensionCause;
            this.myTopFrame = ((llFrame == null) ? null : this.a(llFrame));
        }
        
        public XStackFrame getTopFrame() {
            return this.myTopFrame;
        }
        
        public void computeStackFrames(final int p0, final XExecutionStack.XStackFrameContainer p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.viewsUpdatesDisabledInTests:(Ljava/lang/Object;)Z
            //     4: ifeq            12
            //     7: return         
            //     8: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    11: athrow         
            //    12: aload_0        
            //    13: getfield        com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack.this$0:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
            //    16: aload_0        
            //    17: aload_2        
            //    18: iload_1        
            //    19: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack;Lcom/intellij/xdebugger/frame/XExecutionStack$XStackFrameContainer;I)Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerUIUpdateCommand;
            //    24: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.postCommand:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerCommand;)V
            //    27: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      8      8      12     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0275_1:
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
        
        private boolean a() {
            try {
                if (!CidrDebugProcess.this.driverSupportsDisasm()) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (this.mySuspensionCause == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return false;
        }
        
        @NotNull
        private CidrStackFrame a(@NotNull final LLFrame llFrame) {
            try {
                if (llFrame == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack", "newFrame"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            CidrStackFrame cidrStackFrame;
            try {
                cidrStackFrame = new CidrStackFrame(CidrDebugProcess.this, this.myThread, llFrame, this.mySuspensionCause);
                if (cidrStackFrame == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack", "newFrame"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return cidrStackFrame;
        }
        
        @Nullable
        public GutterIconRenderer getExecutionLineIconRenderer() {
            try {
                if (this.mySuspensionCause == null) {
                    return super.getExecutionLineIconRenderer();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return new GutterIconRenderer() {
                @NotNull
                public Icon getIcon() {
                    Icon icon;
                    try {
                        icon = CidrExecutionStack.this.mySuspensionCause.icon;
                        if (icon == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack$1", "getIcon"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return icon;
                }
                
                @Nullable
                public String getTooltipText() {
                    return CidrExecutionStack.this.mySuspensionCause.getDisplayString();
                }
                
                public boolean equals(final Object o) {
                    try {
                        if (this == o) {
                            return true;
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        if (o == null) {
                            return false;
                        }
                        final GutterIconRenderer gutterIconRenderer = this;
                        final Class<? extends CidrDebugProcess$CidrExecutionStack$1> clazz = gutterIconRenderer.getClass();
                        final Object o2 = o;
                        final Class<?> clazz2 = o2.getClass();
                        if (clazz != clazz2) {
                            return false;
                        }
                        return Comparing.equal((Object)CidrExecutionStack.this.mySuspensionCause, (Object)((CidrExecutionStack)o).mySuspensionCause);
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final GutterIconRenderer gutterIconRenderer = this;
                        final Class<? extends CidrDebugProcess$CidrExecutionStack$1> clazz = gutterIconRenderer.getClass();
                        final Object o2 = o;
                        final Class<?> clazz2 = o2.getClass();
                        if (clazz != clazz2) {
                            return false;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    return Comparing.equal((Object)CidrExecutionStack.this.mySuspensionCause, (Object)((CidrExecutionStack)o).mySuspensionCause);
                }
                
                public int hashCode() {
                    return CidrExecutionStack.this.mySuspensionCause.hashCode();
                }
                
                public boolean isDumbAware() {
                    return true;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
        }
        
        public String toString() {
            return this.myThread.toString();
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
    
    public interface DebuggerUIUpdateCommand extends DebuggerCommand
    {
    }
    
    public interface DebuggerStartupCommand extends DebuggerCommand
    {
    }
}
