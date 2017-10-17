// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import java.util.Collections;
import java.util.EventListener;
import com.intellij.openapi.util.registry.Registry;
import org.jetbrains.annotations.Contract;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import java.util.concurrent.TimeUnit;
import com.intellij.execution.process.ProcessOutputTypes;
import java.io.OutputStream;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Iterator;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import com.jetbrains.cidr.execution.debugger.memory.AddressRange;
import java.util.Collection;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.lang.Language;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.Installer;
import com.intellij.execution.configurations.GeneralCommandLine;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.ProcessOutputReaders;
import com.intellij.util.concurrency.QueueProcessor;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Consumer;
import java.util.List;
import com.intellij.openapi.util.Key;

public abstract class DebuggerDriver
{
    public static final Key<String> DEBUGSERVER_SOCKET;
    public static final Key<Integer> DEBUGSERVER_ID;
    public static final int LOAD_TIMEOUT_MS = 90000;
    public static final int TIMEOUT_MS = 30000;
    public static final int EVALUATION_TIMEOUT_MS = 30000;
    public static final int CLOSE_TIMEOUT_MS = 300;
    public static final int MAX_DESCRIPTION = 1000;
    protected final Handler myHandler;
    private volatile TargetState myState;
    protected final List<Consumer<Pair<String, Key>>> myOutputListeners;
    protected final QueueProcessor<Runnable> myHandlerProcessor;
    @Nullable
    protected ProcessOutputReaders myReaders;
    protected volatile GeneralCommandLine myTargetCommandLine;
    protected volatile Installer myInstaller;
    protected boolean myToRedirect;
    
    public DebuggerDriver(@NotNull final Handler myHandler) {
        if (myHandler == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "<init>"));
        }
        this.myState = TargetState.NOT_READY;
        this.myOutputListeners = (List<Consumer<Pair<String, Key>>>)ContainerUtil.createLockFreeCopyOnWriteList();
        this.myHandler = myHandler;
        this.myHandlerProcessor = (QueueProcessor<Runnable>)QueueProcessor.createRunnableQueueProcessor();
    }
    
    public boolean supportsWatchpoints() {
        return true;
    }
    
    public abstract boolean supportsWatchpointLifetime();
    
    public boolean supportsDisasm() {
        return true;
    }
    
    @NotNull
    public abstract Language getConsoleLanguage();
    
    @NotNull
    public abstract ProcessHandler getProcessHandler();
    
    public abstract boolean isInPromptMode();
    
    public TargetState getState() {
        return this.myState;
    }
    
    public abstract void start(@NotNull final Installer p0, @Nullable final String p1) throws ExecutionException;
    
    public abstract void setValuesFilteringEnabled(final boolean p0) throws ExecutionException;
    
    public abstract void loadForLaunch() throws ExecutionException;
    
    public abstract void loadForAttach() throws ExecutionException;
    
    public abstract long launch() throws ExecutionException;
    
    public abstract void attachTo(final int p0) throws ExecutionException;
    
    public abstract void detach() throws ExecutionException;
    
    public abstract void attachByName(final String p0, final boolean p1) throws ExecutionException;
    
    public abstract boolean interrupt() throws ExecutionException;
    
    public abstract boolean resume() throws ExecutionException;
    
    public void stepOver() throws ExecutionException {
        this.stepOver(null);
    }
    
    public void stepInto(final boolean b) throws ExecutionException {
        this.stepInto(b, null);
    }
    
    public abstract void stepOver(@Nullable final Boolean p0) throws ExecutionException;
    
    public abstract void stepInto(final boolean p0, @Nullable final Boolean p1) throws ExecutionException;
    
    public abstract void stepOut() throws ExecutionException;
    
    public abstract void runTo(final String p0, final int p1) throws ExecutionException;
    
    public abstract boolean abort() throws ExecutionException;
    
    @NotNull
    public abstract LLWatchpoint addWatchpoint(final long p0, final int p1, final LLValue p2, final String p3, final LLWatchpoint.Lifetime p4, final LLWatchpoint.AccessType p5) throws ExecutionException, DebuggerCommandException;
    
    public abstract void removeWatchpoint(final List<Integer> p0) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public List<LLBreakpoint> addBreakpoint(final String s, final int n) throws ExecutionException, DebuggerCommandException {
        List<LLBreakpoint> addBreakpoint;
        try {
            addBreakpoint = this.addBreakpoint(s, n, null);
            if (addBreakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "addBreakpoint"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return addBreakpoint;
    }
    
    @NotNull
    public abstract List<LLBreakpoint> addBreakpoint(final String p0, final int p1, @Nullable final String p2) throws ExecutionException, DebuggerCommandException;
    
    @Nullable
    public LLSymbolicBreakpoint addSymbolicBreakpoint(@NotNull final String s) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolPattern", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "addSymbolicBreakpoint"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return this.addSymbolicBreakpoint(s, null, null);
    }
    
    @Nullable
    public LLSymbolicBreakpoint addSymbolicBreakpoint(@NotNull final String pattern, @Nullable final String module, @Nullable final String condition) throws ExecutionException, DebuggerCommandException {
        try {
            if (pattern == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolPattern", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "addSymbolicBreakpoint"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final SymbolicBreakpoint symbolicBreakpoint = new SymbolicBreakpoint();
        symbolicBreakpoint.setPattern(pattern);
        symbolicBreakpoint.setModule(module);
        symbolicBreakpoint.setCondition(condition);
        return this.addSymbolicBreakpoint(symbolicBreakpoint);
    }
    
    @Nullable
    public abstract LLSymbolicBreakpoint addSymbolicBreakpoint(@NotNull final SymbolicBreakpoint p0) throws ExecutionException, DebuggerCommandException;
    
    public abstract void removeCodepoints(@NotNull final Collection<Integer> p0) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public abstract List<LLThread> getThreads() throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public ResultList<LLFrame> getFrames(final long n, final int n2, final int n3) throws ExecutionException, DebuggerCommandException {
        ResultList<LLFrame> frames;
        try {
            frames = this.getFrames(n, n2, n3, false);
            if (frames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "getFrames"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return frames;
    }
    
    @NotNull
    public abstract ResultList<LLFrame> getFrames(final long p0, final int p1, final int p2, final boolean p3) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public abstract List<LLValue> getVariables(final long p0, final int p1) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public abstract LLValueData getData(@NotNull final LLValue p0) throws ExecutionException, DebuggerCommandException;
    
    @Nullable
    public abstract String getDescription(@NotNull final LLValue p0, final int p1) throws ExecutionException, DebuggerCommandException;
    
    @Nullable
    public abstract Integer getChildrenCount(@NotNull final LLValue p0) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public abstract ResultList<LLValue> getVariableChildren(final LLValue p0, final int p1, final int p2) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public final LLValue evaluate(final long n, final int n2, @NotNull final String s) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "evaluate"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        LLValue evaluate;
        try {
            evaluate = this.evaluate(n, n2, s, null);
            if (evaluate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "evaluate"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return evaluate;
    }
    
    @NotNull
    public abstract LLValue evaluate(final long p0, final int p1, @NotNull final String p2, @Nullable final DebuggerLanguage p3) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    public abstract List<LLInstruction> disassemble(@NotNull final AddressRange p0) throws ExecutionException, DebuggerCommandException;
    
    @Nullable
    public LLInstruction disassemble(@NotNull final Address address) throws ExecutionException, DebuggerCommandException {
        try {
            if (address == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "disassemble"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final List<LLInstruction> disassemble = this.disassemble(address.rangeTo(address));
        try {
            if (disassemble.isEmpty()) {
                return null;
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return disassemble.get(0);
    }
    
    @NotNull
    public abstract ShellCommandResult executeShellCommand(@NotNull final String p0, @Nullable final List<String> p1, @Nullable final String p2, final int p3) throws ExecutionException;
    
    public abstract void executeConsoleCommand(final String p0) throws ExecutionException, DebuggerCommandException;
    
    public abstract void executeConsoleCommand(final long p0, final int p1, final String p2) throws ExecutionException, DebuggerCommandException;
    
    public abstract void handleCompletion(final String p0, final int p1, final List<String> p2) throws ExecutionException;
    
    public abstract void handleSignal(final String p0, final boolean p1, final boolean p2, final boolean p3) throws ExecutionException, DebuggerCommandException;
    
    public void addOutputListener(final Consumer<Pair<String, Key>> consumer) {
        this.myOutputListeners.add(consumer);
    }
    
    protected void setState(final TargetState myState) {
        try {
            this.myState = myState;
            if (myState == TargetState.FINISHED) {
                this.closeOutputReaders();
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
    }
    
    protected void handleRunning() {
        this.setState(TargetState.RUNNING);
        this.myHandlerProcessor.add((Object)this.myHandler::handleRunning);
    }
    
    protected void handleModulesLoaded(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modules", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleModulesLoaded"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (list == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modules", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleModulesLoaded$0"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handleModulesLoaded(list);
        });
    }
    
    protected void handleInterrupted(@NotNull final StopPlace stopPlace) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleInterrupted"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        this.setState(TargetState.SUSPENDED);
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (stopPlace == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleInterrupted$1"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handleInterrupted(stopPlace);
        });
    }
    
    protected void handleSignal(@NotNull final StopPlace stopPlace, @NotNull final String s, @NotNull final String s2) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleSignal"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signal", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleSignal"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "meaning", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleSignal"));
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        this.setState(TargetState.SUSPENDED);
        final IllegalArgumentException ex4;
        final IllegalArgumentException ex6;
        final IllegalArgumentException ex8;
        this.myHandlerProcessor.add(() -> {
            try {
                if (stopPlace == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleSignal$2"));
                    throw ex4;
                }
            }
            catch (NumberFormatException ex5) {
                throw b(ex5);
            }
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signal", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleSignal$2"));
                    throw ex6;
                }
            }
            catch (NumberFormatException ex7) {
                throw b(ex7);
            }
            try {
                if (s2 == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "meaning", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleSignal$2"));
                    throw ex8;
                }
            }
            catch (NumberFormatException ex9) {
                throw b(ex9);
            }
            this.myHandler.handleSignal(stopPlace, s, s2);
        });
    }
    
    protected void handleException(@NotNull final StopPlace stopPlace, @NotNull final String s) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleException"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "description", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleException"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        this.setState(TargetState.SUSPENDED);
        final IllegalArgumentException ex3;
        final IllegalArgumentException ex5;
        this.myHandlerProcessor.add(() -> {
            try {
                if (stopPlace == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleException$3"));
                    throw ex3;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "description", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleException$3"));
                    throw ex5;
                }
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
            this.myHandler.handleException(stopPlace, s);
        });
    }
    
    protected void handleBreakpoint(@NotNull final StopPlace stopPlace, final int n) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleBreakpoint"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        this.setState(TargetState.SUSPENDED);
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (stopPlace == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleBreakpoint$4"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handleBreakpoint(stopPlace, n);
        });
    }
    
    protected void handleWatchpoint(@NotNull final StopPlace stopPlace, final int n) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleWatchpoint"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        this.setState(TargetState.SUSPENDED);
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (stopPlace == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleWatchpoint$5"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handleWatchpoint(stopPlace, n);
        });
    }
    
    protected void handleWatchpointScope(final int n) {
        this.setState(TargetState.SUSPENDED);
        this.myHandlerProcessor.add(() -> this.myHandler.handleWatchpointScope(n));
    }
    
    protected void handleTargetOutput(@NotNull final String s, @NotNull final Key key) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleTargetOutput"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleTargetOutput"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final IllegalArgumentException ex3;
        final IllegalArgumentException ex5;
        final Iterator<Consumer<Pair<String, Key>>> iterator;
        this.myHandlerProcessor.add(() -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleTargetOutput$7"));
                    throw ex3;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
            try {
                if (key == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleTargetOutput$7"));
                    throw ex5;
                }
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
            this.myHandler.handleTargetOutput(s, key);
            this.myOutputListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().consume((Object)Pair.create((Object)s, (Object)key));
            }
        });
    }
    
    protected void handleAttached(final int n) {
        this.myHandlerProcessor.add(() -> this.myHandler.handleAttached(n));
    }
    
    protected void handleConnected(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "connection", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleConnected"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "connection", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleConnected$9"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handleConnected(s);
        });
    }
    
    protected void handleDisconnected() {
        this.myHandlerProcessor.add((Object)this.myHandler::handleDisconnected);
    }
    
    protected void handleGDBOutput(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handleGDBOutput"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handleGDBOutput$10"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handleGDBOutput(s);
        });
    }
    
    protected void handlePrompt() {
        this.handlePrompt(false);
    }
    
    protected void handlePrompt(final boolean b) {
        int n = 0;
        Label_0014: {
            try {
                if (b) {
                    n = 1;
                    break Label_0014;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            n = 0;
        }
        this.handlePrompt(n);
    }
    
    protected void handlePrompt(final int n) {
        String s = null;
        Label_0045: {
            try {
                if (n == 0) {
                    s = "(" + this.getPromptText() + ") ";
                    break Label_0045;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            s = StringUtil.repeat("> ", n);
        }
        this.handlePrompt(s);
    }
    
    protected abstract String getPromptText();
    
    protected void handlePrompt(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prompt", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "handlePrompt"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        this.myHandlerProcessor.add(() -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prompt", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "lambda$handlePrompt$11"));
                    throw ex2;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            this.myHandler.handlePrompt(s);
        });
    }
    
    protected void handleTargetFinished(final int n, @Nullable final String s) {
        this.setState(TargetState.FINISHED);
        this.myHandlerProcessor.add(() -> this.myHandler.handleTargetFinished(n, s));
    }
    
    protected void handleTargetTerminated() {
        this.setState(TargetState.FINISHED);
        this.myHandlerProcessor.add((Object)this.myHandler::handleTargetTerminated);
    }
    
    protected void handleDetached() {
        this.setState(TargetState.FINISHED);
        this.myHandlerProcessor.add((Object)this.myHandler::handleDetached);
    }
    
    protected void handleExited(final int n) {
        this.setState(TargetState.FINISHED);
        this.myHandlerProcessor.add(() -> this.myHandler.handleExited(n));
    }
    
    public void waitHandlerProcessed() {
        this.myHandlerProcessor.waitFor();
    }
    
    @Nullable
    public OutputStream getProcessInput() {
        return null;
    }
    
    public abstract void checkErrors() throws ExecutionException;
    
    protected void printTargetCommandLine(@Nullable final GeneralCommandLine generalCommandLine) {
        try {
            if (generalCommandLine != null) {
                this.handleTargetOutput(generalCommandLine.getCommandLineString() + "\n", ProcessOutputTypes.SYSTEM);
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
    }
    
    public void setRedirectOutputToFiles(final boolean myToRedirect) {
        this.myToRedirect = myToRedirect;
    }
    
    protected void initReaders(final boolean b) throws ExecutionException {
        (this.myReaders = new ProcessOutputReaders() {
            @Override
            protected void onTextAvailable(@NotNull final String s, @NotNull final Key key) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$1", "onTextAvailable"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (key == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$1", "onTextAvailable"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                DebuggerDriver.this.handleTargetOutput(s, key);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }).init(this.myTargetCommandLine, b);
    }
    
    protected void closeOutputReaders() {
        final ProcessOutputReaders myReaders = this.myReaders;
        if (myReaders != null) {
            try {
                if (!myReaders.waitFor(300L, TimeUnit.MILLISECONDS)) {
                    CidrDebuggerLog.LOG.warn("Closing inferior output readers took too long");
                }
            }
            finally {
                myReaders.close();
                this.myReaders = null;
            }
        }
    }
    
    protected static boolean isTargetTerminationSignal(final int n) {
        Label_0019: {
            try {
                if (15 == n) {
                    break Label_0019;
                }
                final int n2 = 9;
                final int n3 = n;
                if (n2 == n3) {
                    break Label_0019;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final int n2 = 9;
                final int n3 = n;
                if (n2 == n3) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    protected static boolean isTargetTerminationSignal(@Nullable final String s) {
        Label_0025: {
            try {
                if ("SIGTERM".equals(s)) {
                    break Label_0025;
                }
                final String s2 = "SIGKILL";
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final String s2 = "SIGKILL";
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    protected String getDebugServerSocket() {
        return (String)this.myTargetCommandLine.getUserData((Key)DebuggerDriver.DEBUGSERVER_SOCKET);
    }
    
    public abstract void addSymbolsFile(final File p0, final File p1) throws ExecutionException;
    
    private static int a(final String s, final int n, final ByteArrayOutputStream byteArrayOutputStream) {
        Label_0033: {
            try {
                if (n + 4 > s.length()) {
                    return -1;
                }
                final String s2 = s;
                final int n2 = n;
                final char c = s2.charAt(n2);
                final char c2 = '\\';
                if (c != c2) {
                    return -1;
                }
                break Label_0033;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final String s2 = s;
                final int n2 = n;
                final char c = s2.charAt(n2);
                final char c2 = '\\';
                if (c != c2) {
                    return -1;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                byteArrayOutputStream.write(Integer.parseInt(s.substring(n + 1, n + 4), 8));
                return n + 4;
            }
            catch (NumberFormatException ex3) {
                return -1;
            }
        }
    }
    
    @NotNull
    public static String unescapeString(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "unescapeString"));
            }
        }
        catch (UnsupportedEncodingException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder(s.length());
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            Label_0409: {
                Label_0316: {
                    Label_0306: {
                        Label_0296: {
                            Label_0286: {
                                Label_0276: {
                                    Label_0266: {
                                        Label_0256: {
                                            Label_0246: {
                                                Label_0236: {
                                                    Label_0119: {
                                                        Label_0109: {
                                                            try {
                                                                if (n != 0) {
                                                                    break Label_0119;
                                                                }
                                                                if (char1 != '\\') {
                                                                    break Label_0109;
                                                                }
                                                            }
                                                            catch (UnsupportedEncodingException ex2) {
                                                                throw b(ex2);
                                                            }
                                                            n = 1;
                                                            continue;
                                                        }
                                                        sb.append(char1);
                                                        continue;
                                                        try {
                                                            switch (char1) {
                                                                case 'n': {
                                                                    sb.append('\n');
                                                                    break Label_0409;
                                                                }
                                                                case 'r': {
                                                                    break;
                                                                }
                                                                case 'b': {
                                                                    break Label_0236;
                                                                }
                                                                case 't': {
                                                                    break Label_0246;
                                                                }
                                                                case 'f': {
                                                                    break Label_0256;
                                                                }
                                                                case 'e': {
                                                                    break Label_0266;
                                                                }
                                                                case 'a': {
                                                                    break Label_0276;
                                                                }
                                                                case '\'': {
                                                                    break Label_0286;
                                                                }
                                                                case '\"': {
                                                                    break Label_0296;
                                                                }
                                                                case '\\': {
                                                                    break Label_0306;
                                                                }
                                                                default: {
                                                                    break Label_0316;
                                                                }
                                                            }
                                                        }
                                                        catch (UnsupportedEncodingException ex3) {
                                                            throw b(ex3);
                                                        }
                                                    }
                                                    sb.append('\r');
                                                    break Label_0409;
                                                }
                                                sb.append('\b');
                                                break Label_0409;
                                            }
                                            sb.append('\t');
                                            break Label_0409;
                                        }
                                        sb.append('\f');
                                        break Label_0409;
                                    }
                                    sb.append('\u001b');
                                    break Label_0409;
                                }
                                sb.append('\u0007');
                                break Label_0409;
                            }
                            sb.append('\'');
                            break Label_0409;
                        }
                        sb.append('\"');
                        break Label_0409;
                    }
                    sb.append('\\');
                    break Label_0409;
                }
                int a = i - 1;
                byteArrayOutputStream.reset();
                while ((a = a(s, a, byteArrayOutputStream)) != -1) {
                    i = a;
                }
                Label_0395: {
                    Label_0389: {
                        try {
                            if (byteArrayOutputStream.size() > 0) {
                                final StringBuilder sb2 = sb;
                                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                                final byte[] array = byteArrayOutputStream2.toByteArray();
                                final String s2 = "UTF-8";
                                final String s3 = new String(array, s2);
                                sb2.append(s3);
                                break Label_0389;
                            }
                            break Label_0395;
                        }
                        catch (NumberFormatException ex4) {
                            throw b(ex4);
                        }
                        try {
                            final StringBuilder sb2 = sb;
                            final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                            final byte[] array = byteArrayOutputStream2.toByteArray();
                            final String s2 = "UTF-8";
                            final String s3 = new String(array, s2);
                            sb2.append(s3);
                        }
                        catch (UnsupportedEncodingException ex5) {
                            CidrDebuggerLog.LOG.error((Throwable)ex5);
                        }
                    }
                    --i;
                    break Label_0409;
                }
                sb.append('\\');
                sb.append(char1);
            }
            n = 0;
        }
        try {
            if (n != 0) {
                sb.append('\\');
            }
        }
        catch (UnsupportedEncodingException ex6) {
            throw b(ex6);
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "unescapeString"));
            }
        }
        catch (UnsupportedEncodingException ex7) {
            throw b(ex7);
        }
        return string;
    }
    
    @Contract("_, !null -> !null")
    public static Address parseAddress(@NotNull final String s, final Address address) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "parseAddress"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            return Address.parseHexString(s);
        }
        catch (NumberFormatException ex2) {
            return address;
        }
    }
    
    @NotNull
    public static Address parseAddress(@NotNull final String s) throws ExecutionException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "parseAddress"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            Address hexString;
            try {
                hexString = Address.parseHexString(s);
                if (hexString == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "parseAddress"));
                }
            }
            catch (ExecutionException ex2) {
                throw b((Exception)ex2);
            }
            return hexString;
        }
        catch (NumberFormatException ex3) {
            throw new ExecutionException((Throwable)ex3);
        }
    }
    
    @NotNull
    public static Address parseAddressSafe(@NotNull final String s) throws DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "parseAddressSafe"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            Address hexString;
            try {
                hexString = Address.parseHexString(s);
                if (hexString == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver", "parseAddressSafe"));
                }
            }
            catch (DebuggerCommandException ex2) {
                throw b(ex2);
            }
            return hexString;
        }
        catch (NumberFormatException ex3) {
            throw new DebuggerCommandException(ex3);
        }
    }
    
    protected static int getTimeoutMs() {
        return Registry.intValue("cidr.debugger.timeout", 30000);
    }
    
    protected static int getLoadTimeoutMs() {
        return Registry.intValue("cidr.debugger.timeout.load", 90000);
    }
    
    protected static int getEvaluationTimeoutMs() {
        return Registry.intValue("cidr.debugger.timeout.evaluate", 30000);
    }
    
    static {
        DEBUGSERVER_SOCKET = Key.create("DEBUGSERVER_SOCKET");
        DEBUGSERVER_ID = Key.create("DEBUGSERVER_ID");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public static class ShellCommandResult
    {
        private final String myOutput;
        private final int myStatus;
        private final int mySignal;
        
        public ShellCommandResult(@NotNull final String myOutput, final int myStatus, final int mySignal) {
            if (myOutput == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ShellCommandResult", "<init>"));
            }
            this.myOutput = myOutput;
            this.myStatus = myStatus;
            this.mySignal = mySignal;
        }
        
        @NotNull
        public String getOutput() {
            String myOutput;
            try {
                myOutput = this.myOutput;
                if (myOutput == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ShellCommandResult", "getOutput"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myOutput;
        }
        
        public int getStatus() {
            return this.myStatus;
        }
        
        public int getSignal() {
            return this.mySignal;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum TargetState
    {
        NOT_READY, 
        RUNNING, 
        SUSPENDED, 
        FINISHED;
    }
    
    public interface Handler extends EventListener
    {
        default void handleRunning() {
        }
        
        default void handleModulesLoaded(@NotNull final List<String> list) {
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modules", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleModulesLoaded"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handleInterrupted(@NotNull final StopPlace stopPlace) {
            try {
                if (stopPlace == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleInterrupted"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handleSignal(@NotNull final StopPlace stopPlace, @NotNull final String s, @NotNull final String s2) {
            try {
                if (stopPlace == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleSignal"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signal", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleSignal"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (s2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "meaning", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleSignal"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        
        default void handleException(@NotNull final StopPlace stopPlace, @NotNull final String s) {
            try {
                if (stopPlace == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleException"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "description", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleException"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        
        default void handleBreakpoint(@NotNull final StopPlace stopPlace, final int n) {
            try {
                if (stopPlace == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleBreakpoint"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handleWatchpoint(@NotNull final StopPlace stopPlace, final int n) {
            try {
                if (stopPlace == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleWatchpoint"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handleWatchpointScope(final int n) {
        }
        
        default void handleTargetOutput(@NotNull final String s, @NotNull final Key key) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleTargetOutput"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (key == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleTargetOutput"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        
        default void handleGDBOutput(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleGDBOutput"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handlePrompt(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prompt", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handlePrompt"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handleTargetFinished(final int n, @Nullable final String s) {
        }
        
        default void handleTargetTerminated() {
        }
        
        default void handleExited(final int n) {
        }
        
        default void handleAttached(final int n) {
        }
        
        default void handleDetached() {
        }
        
        default void handleConnected(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "connection", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleConnected"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default void handleDisconnected() {
        }
        
        default IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class PathMapping
    {
        @NotNull
        public final String from;
        @NotNull
        public final String to;
        
        public PathMapping(@NotNull final String from, @NotNull final String to) {
            if (from == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "from", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$PathMapping", "<init>"));
            }
            if (to == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "to", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$PathMapping", "<init>"));
            }
            this.from = from;
            this.to = to;
        }
    }
    
    public static class StopPlace
    {
        @NotNull
        public final LLThread thread;
        @NotNull
        public final LLFrame frame;
        
        public StopPlace(@NotNull final LLThread thread, @NotNull final LLFrame frame) {
            if (thread == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thread", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StopPlace", "<init>"));
            }
            if (frame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StopPlace", "<init>"));
            }
            this.thread = thread;
            this.frame = frame;
        }
        
        @Override
        public String toString() {
            return this.thread + ": " + this.frame;
        }
    }
    
    public static class ResultList<T>
    {
        @NotNull
        public final List<T> list;
        public final boolean hasMore;
        
        public ResultList(@NotNull final List<T> list, final boolean hasMore) {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "<init>"));
            }
            this.list = list;
            this.hasMore = hasMore;
        }
        
        @NotNull
        public static <T> ResultList<T> create(@NotNull final List<T> list, final boolean b) {
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "create"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ResultList list2;
            try {
                list2 = new ResultList<T>(list, b);
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "create"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return (ResultList<T>)list2;
        }
        
        @NotNull
        public static <T> ResultList<T> empty() {
            ResultList<T> create;
            try {
                create = create(Collections.emptyList(), false);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "empty"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return create;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum StandardDebuggerLanguage implements DebuggerLanguage
    {
        C("C"), 
        C_PLUS_PLUS("C++"), 
        OBJC("Objective-C"), 
        OBJC_PLUS_PLUS("Objective-C++"), 
        SWIFT("Swift");
        
        private final String myName;
        
        private StandardDebuggerLanguage(final String myName) {
            if (myName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage", "<init>"));
            }
            super(s, n);
            this.myName = myName;
        }
        
        @NotNull
        @Override
        public String toString() {
            String myName;
            try {
                myName = this.myName;
                if (myName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage", "toString"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myName;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class SymbolicBreakpoint
    {
        public static final int INVALID_THREAD_ID = 0;
        private String myPattern;
        private boolean myRegexpPattern;
        @Nullable
        private String myModule;
        @Nullable
        private String myCondition;
        private long myThreadId;
        
        public SymbolicBreakpoint() {
            this.myRegexpPattern = false;
            this.myThreadId = 0L;
        }
        
        public String getPattern() {
            return this.myPattern;
        }
        
        public void setPattern(final String myPattern) {
            this.myPattern = myPattern;
        }
        
        public boolean isRegexpPattern() {
            return this.myRegexpPattern;
        }
        
        public void setRegexpPattern(final boolean myRegexpPattern) {
            this.myRegexpPattern = myRegexpPattern;
        }
        
        @Nullable
        public String getModule() {
            return this.myModule;
        }
        
        public void setModule(@Nullable final String myModule) {
            this.myModule = myModule;
        }
        
        @Nullable
        public String getCondition() {
            return this.myCondition;
        }
        
        public void setCondition(@Nullable final String myCondition) {
            this.myCondition = myCondition;
        }
        
        public long getThreadId() {
            return this.myThreadId;
        }
        
        public void setThreadId(final long myThreadId) {
            this.myThreadId = myThreadId;
        }
    }
    
    public interface DebuggerLanguage
    {
        String toString();
    }
}
