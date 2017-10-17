// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrDebuggerTypesHelper;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class CidrDebuggerLanguageSupportFactory
{
    public static final ExtensionPointName<CidrDebuggerLanguageSupportFactory> EP_NAME;
    
    @Nullable
    public abstract XDebuggerEditorsProvider createEditor(final RunProfile p0);
    
    @Nullable
    public abstract XDebuggerEditorsProvider createEditor(final XBreakpoint<? extends XBreakpointProperties> p0);
    
    public abstract CidrDebuggerTypesHelper createTypesHelper(final CidrDebugProcess p0);
    
    @Nullable
    public abstract CidrEvaluator createEvaluator(@NotNull final CidrStackFrame p0);
    
    @Nullable
    public static XDebuggerEditorsProvider getEditorsProvider(@NotNull final XBreakpoint<? extends XBreakpointProperties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/CidrDebuggerLanguageSupportFactory", "getEditorsProvider"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrDebuggerLanguageSupportFactory[] array = (CidrDebuggerLanguageSupportFactory[])CidrDebuggerLanguageSupportFactory.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final XDebuggerEditorsProvider editor = array[i].createEditor(xBreakpoint);
            try {
                if (editor != null) {
                    return editor;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    static {
        EP_NAME = ExtensionPointName.create("cidr.debugger.languageSupportFactory");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
