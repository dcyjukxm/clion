// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.execution.debugger.evaluation.CidrDebuggerTypesHelper;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.CidrRunProfile;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.execution.configurations.RunProfile;

public class OCDebuggerLanguageSupportFactory extends CidrDebuggerLanguageSupportFactory
{
    @Nullable
    @Override
    public XDebuggerEditorsProvider createEditor(final RunProfile runProfile) {
        Label_0018: {
            try {
                if (runProfile instanceof CidrRunProfile) {
                    break Label_0018;
                }
                final RunProfile runProfile2 = runProfile;
                if (runProfile2 == null) {
                    break Label_0018;
                }
                return null;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final RunProfile runProfile2 = runProfile;
                if (runProfile2 == null) {
                    return this.createEditorProvider();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public XDebuggerEditorsProvider createEditor(final XBreakpoint<? extends XBreakpointProperties> xBreakpoint) {
        return this.createEditorProvider();
    }
    
    @NotNull
    protected OCDebuggerEditorsProvider createEditorProvider() {
        OCDebuggerEditorsProvider ocDebuggerEditorsProvider;
        try {
            ocDebuggerEditorsProvider = new OCDebuggerEditorsProvider();
            if (ocDebuggerEditorsProvider == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/OCDebuggerLanguageSupportFactory", "createEditorProvider"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDebuggerEditorsProvider;
    }
    
    @Override
    public CidrDebuggerTypesHelper createTypesHelper(final CidrDebugProcess cidrDebugProcess) {
        return new OCDebuggerTypesHelper(cidrDebugProcess);
    }
    
    @Nullable
    @Override
    public CidrEvaluator createEvaluator(@NotNull final CidrStackFrame cidrStackFrame) {
        try {
            if (cidrStackFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/OCDebuggerLanguageSupportFactory", "createEvaluator"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new OCEvaluator(cidrStackFrame);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
