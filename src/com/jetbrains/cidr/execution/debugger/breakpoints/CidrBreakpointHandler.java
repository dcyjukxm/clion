// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.execution.ExecutionException;
import java.util.Iterator;
import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.debugger.backend.LLBreakpoint;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import java.util.Collections;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.jetbrains.cidr.execution.debugger.backend.LLCodepoint;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.breakpoints.XLineBreakpointType;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;

public class CidrBreakpointHandler extends CidrCodePointHandlerBase<XLineBreakpoint<XBreakpointProperties>>
{
    private CidrDebugProcess myProcess;
    
    public CidrBreakpointHandler(final CidrDebugProcess cidrDebugProcess) {
        this(cidrDebugProcess, CidrLineBreakpointType.class);
    }
    
    public CidrBreakpointHandler(final CidrDebugProcess myProcess, @NotNull final Class<? extends XLineBreakpointType<XBreakpointProperties>> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrBreakpointHandler", "<init>"));
        }
        super(myProcess, clazz);
        this.myProcess = myProcess;
    }
    
    @Override
    protected Collection<LLCodepoint> doAddCodepoints(final DebuggerDriver debuggerDriver, final XLineBreakpoint<XBreakpointProperties> xLineBreakpoint, final long n, final int n2) throws ExecutionException {
        final String convertCondition = this.convertCondition((XBreakpoint<? extends XBreakpointProperties>)xLineBreakpoint);
        List<LLBreakpoint> addBreakpoint;
        try {
            addBreakpoint = debuggerDriver.addBreakpoint(VfsUtil.urlToPath(xLineBreakpoint.getFileUrl()), xLineBreakpoint.getLine(), convertCondition);
        }
        catch (DebuggerCommandException ex) {
            this.myProcess.getSession().updateBreakpointPresentation((XLineBreakpoint)xLineBreakpoint, AllIcons.Debugger.Db_invalid_breakpoint, ex.getMessage());
            return (Collection<LLCodepoint>)Collections.emptyList();
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<LLBreakpoint> iterator = addBreakpoint.iterator();
        while (iterator.hasNext()) {
            final String conditionError = iterator.next().getConditionError();
            try {
                if (conditionError == null) {
                    continue;
                }
                list.add(conditionError);
            }
            catch (DebuggerCommandException ex2) {
                throw b(ex2);
            }
        }
        try {
            if (list.isEmpty()) {
                this.myProcess.getSession().updateBreakpointPresentation((XLineBreakpoint)xLineBreakpoint, AllIcons.Debugger.Db_verified_breakpoint, (String)null);
                return (Collection<LLCodepoint>)addBreakpoint;
            }
        }
        catch (DebuggerCommandException ex3) {
            throw b(ex3);
        }
        this.myProcess.getSession().updateBreakpointPresentation((XLineBreakpoint)xLineBreakpoint, AllIcons.Debugger.Db_invalid_breakpoint, StringUtil.join((Collection)list, "\n"));
        return (Collection<LLCodepoint>)addBreakpoint;
    }
    
    private static DebuggerCommandException b(final DebuggerCommandException ex) {
        return ex;
    }
}
