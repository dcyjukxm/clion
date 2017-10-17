// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import org.jetbrains.annotations.NotNull;

public enum Diagnostic implements Type<DiagnosticLevel>
{
    MISSING_RETURN_FROM_NON_VOID(DiagnosticLevel.WARNING), 
    FOLDING_CONSTANT(DiagnosticLevel.ERROR);
    
    @NotNull
    private final DiagnosticLevel myDefault;
    
    private Diagnostic(final DiagnosticLevel myDefault) {
        if (myDefault == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "def", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic", "<init>"));
        }
        super(s, n);
        this.myDefault = myDefault;
    }
    
    @NotNull
    @Override
    public DiagnosticLevel getDefault() {
        DiagnosticLevel myDefault;
        try {
            myDefault = this.myDefault;
            if (myDefault == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic", "getDefault"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myDefault;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
