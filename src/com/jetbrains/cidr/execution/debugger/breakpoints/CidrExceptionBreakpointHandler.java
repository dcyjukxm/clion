// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import java.util.Collections;
import com.jetbrains.cidr.execution.debugger.backend.LLCodepoint;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.breakpoints.XBreakpoint;

public class CidrExceptionBreakpointHandler extends CidrCodePointHandlerBase<XBreakpoint<CidrExceptionBreakpointType.Properties>>
{
    public CidrExceptionBreakpointHandler(final CidrDebugProcess cidrDebugProcess, final Class<? extends CidrExceptionBreakpointType> clazz) {
        super(cidrDebugProcess, clazz);
    }
    
    @Override
    protected Collection<LLCodepoint> doAddCodepoints(final DebuggerDriver debuggerDriver, final XBreakpoint<CidrExceptionBreakpointType.Properties> xBreakpoint, final long n, final int n2) throws ExecutionException {
        final CidrExceptionBreakpointType.Properties properties = (CidrExceptionBreakpointType.Properties)xBreakpoint.getProperties();
        try {
            if (properties == null) {
                return (Collection<LLCodepoint>)Collections.emptyList();
            }
        }
        catch (DebuggerCommandException ex) {
            throw c(ex);
        }
        final ArrayList<LLCodepoint> list = new ArrayList<LLCodepoint>();
        for (final String s : a(properties)) {
            try {
                ContainerUtil.addIfNotNull((Collection)list, (Object)debuggerDriver.addSymbolicBreakpoint(s));
            }
            catch (DebuggerCommandException ex2) {
                return (Collection<LLCodepoint>)Collections.emptyList();
            }
        }
        return list;
    }
    
    private static List<String> a(@NotNull final CidrExceptionBreakpointType.Properties properties) {
        try {
            if (properties == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "properties", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointHandler", "getExceptionSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final CidrExceptionBreakpointType.Properties.Type whenThrown = properties.getWhenThrown();
        final ArrayList<String> list = new ArrayList<String>(2);
        Label_0097: {
            Label_0076: {
                try {
                    if (whenThrown == null) {
                        break Label_0097;
                    }
                    final CidrExceptionBreakpointType.Properties.Type type = whenThrown;
                    final CidrExceptionBreakpointType.Properties.Type type2 = CidrExceptionBreakpointType.Properties.Type.OBJC_EXCEPTION;
                    if (type == type2) {
                        break Label_0076;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final CidrExceptionBreakpointType.Properties.Type type = whenThrown;
                    final CidrExceptionBreakpointType.Properties.Type type2 = CidrExceptionBreakpointType.Properties.Type.OBJC_EXCEPTION;
                    if (type == type2) {
                        list.add("objc_exception_throw");
                        break Label_0097;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            list.add("__cxa_throw");
            try {
                if (properties.getWhenCaught() != null) {
                    list.add("__cxa_begin_catch");
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return list;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
