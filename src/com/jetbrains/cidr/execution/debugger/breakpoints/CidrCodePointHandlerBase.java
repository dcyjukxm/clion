// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.icons.AllIcons;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;
import com.jetbrains.cidr.execution.debugger.CidrEvaluatorHelper;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.execution.ExecutionException;
import gnu.trove.THashMap;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import com.intellij.util.containers.FactoryMap;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.breakpoints.XBreakpointType;
import com.jetbrains.cidr.execution.debugger.backend.LLCodepoint;
import java.util.List;
import java.util.Map;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.breakpoints.XBreakpointHandler;
import com.intellij.xdebugger.breakpoints.XBreakpoint;

public abstract class CidrCodePointHandlerBase<T extends XBreakpoint<?>> extends XBreakpointHandler<T>
{
    protected final CidrDebugProcess myProcess;
    private final Map<T, List<LLCodepoint>> myBreakpoints;
    private final Map<Integer, T> myIdToBreakpoint;
    
    public CidrCodePointHandlerBase(final CidrDebugProcess myProcess, @NotNull final Class<? extends XBreakpointType<T, ?>> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase", "<init>"));
        }
        super((Class)clazz);
        this.myBreakpoints = (Map<T, List<LLCodepoint>>)new FactoryMap<T, List<LLCodepoint>>() {
            @Nullable
            protected List<LLCodepoint> create(final T t) {
                return new ArrayList<LLCodepoint>();
            }
        };
        this.myIdToBreakpoint = (Map<Integer, T>)new THashMap();
        this.myProcess = myProcess;
    }
    
    public void registerBreakpoint(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase", "registerBreakpoint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        final long n;
        final int n2;
        this.myProcess.postCommand(debuggerDriver -> {
            this.myProcess.getCurrentThreadId();
            this.myProcess.getCurrentFrameIndex();
            try {
                if (t == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase", "lambda$registerBreakpoint$0"));
                    throw ex2;
                }
            }
            catch (ExecutionException ex3) {
                throw b((Exception)ex3);
            }
            this.doRegisterBreakpoint(debuggerDriver, t, n, n2);
        });
    }
    
    protected void doRegisterBreakpoint(final DebuggerDriver debuggerDriver, final T t, final long n, final int n2) throws ExecutionException {
        final Collection<LLCodepoint> doAddCodepoints = this.doAddCodepoints(debuggerDriver, t, n, n2);
        try {
            if (doAddCodepoints.isEmpty()) {
                return;
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        synchronized (this.myBreakpoints) {
            this.myBreakpoints.get(t).addAll(doAddCodepoints);
            final Iterator<LLCodepoint> iterator = doAddCodepoints.iterator();
            while (iterator.hasNext()) {
                this.myIdToBreakpoint.put(iterator.next().getId(), t);
            }
        }
    }
    
    protected abstract Collection<LLCodepoint> doAddCodepoints(final DebuggerDriver p0, final T p1, final long p2, final int p3) throws ExecutionException;
    
    public void unregisterBreakpoint(@NotNull final T t, final boolean b) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase", "unregisterBreakpoint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        this.myProcess.postCommand(debuggerDriver -> {
            try {
                if (t == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase", "lambda$unregisterBreakpoint$1"));
                    throw ex2;
                }
            }
            catch (DebuggerCommandException ex3) {
                throw b(ex3);
            }
            try {
                this.doUnregisterBreakpoint(debuggerDriver, t);
            }
            catch (DebuggerCommandException ex4) {
                throw new ExecutionException((Throwable)ex4);
            }
        });
    }
    
    protected List<Integer> doUnregisterBreakpoint(final T t) {
        final List map;
        synchronized (this.myBreakpoints) {
            final List<LLCodepoint> list = this.myBreakpoints.remove(t);
            if (list == null) {
                return Collections.emptyList();
            }
            map = ContainerUtil.map((Collection)list, LLCodepoint::getId);
            final Iterator<Integer> iterator = map.iterator();
            while (iterator.hasNext()) {
                this.myIdToBreakpoint.remove(iterator.next());
            }
        }
        return Collections.unmodifiableList((List<? extends Integer>)map);
    }
    
    protected void doUnregisterBreakpoint(@NotNull final DebuggerDriver debuggerDriver, final T t) throws ExecutionException, DebuggerCommandException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase", "doUnregisterBreakpoint"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final List<Integer> doUnregisterBreakpoint = this.doUnregisterBreakpoint(t);
        try {
            if (!doUnregisterBreakpoint.isEmpty()) {
                this.removeBreakpointInBackend(debuggerDriver, doUnregisterBreakpoint, t);
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
    }
    
    protected void removeBreakpointInBackend(final DebuggerDriver debuggerDriver, final List<Integer> list, final T t) throws ExecutionException, DebuggerCommandException {
        debuggerDriver.removeCodepoints(list);
    }
    
    @Nullable
    public T getCodepoint(final int n) {
        synchronized (this.myBreakpoints) {
            return this.myIdToBreakpoint.get(n);
        }
    }
    
    @Nullable
    protected String convertCondition(final XBreakpoint<? extends XBreakpointProperties> xBreakpoint) {
        if (xBreakpoint.getCondition() != null) {
            final String s = (String)new WriteAction<String>() {
                protected void run(@NotNull final Result<String> result) throws Throwable {
                    try {
                        if (result == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stringResult", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase$2", "run"));
                        }
                    }
                    catch (CidrEvaluatorHelper.ConversionException ex) {
                        throw b(ex);
                    }
                    try {
                        result.setResult((Object)CidrEvaluatorHelper.getInstance().convertExpression(CidrCodePointHandlerBase.this.myProcess, xBreakpoint.getCondition(), xBreakpoint.getSourcePosition()));
                    }
                    catch (CidrEvaluatorHelper.ConversionException ex2) {
                        try {
                            if (xBreakpoint instanceof XLineBreakpoint) {
                                CidrCodePointHandlerBase.this.myProcess.getSession().updateBreakpointPresentation((XLineBreakpoint)xBreakpoint, AllIcons.Debugger.Db_invalid_breakpoint, ex2.getMessage());
                            }
                        }
                        catch (CidrEvaluatorHelper.ConversionException ex3) {
                            throw b(ex3);
                        }
                    }
                }
                
                private static CidrEvaluatorHelper.ConversionException b(final CidrEvaluatorHelper.ConversionException ex) {
                    return ex;
                }
            }.execute().getResultObject();
            try {
                if (s != null) {
                    return s;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
        return xBreakpoint.getCondition();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
