// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.parameterInfo;

import org.jetbrains.annotations.NotNull;

public class OCFunctionCallOption
{
    @NotNull
    private OCFunctionParameterInfo myParameterInfo;
    private final int myOffset;
    
    public OCFunctionCallOption(@NotNull final OCFunctionParameterInfo myParameterInfo, final int myOffset) {
        if (myParameterInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallOption", "<init>"));
        }
        this.myParameterInfo = myParameterInfo;
        this.myOffset = myOffset;
    }
    
    public int getOffset() {
        return this.myOffset;
    }
    
    @NotNull
    public OCFunctionParameterInfo getParameterInfo() {
        OCFunctionParameterInfo myParameterInfo;
        try {
            myParameterInfo = this.myParameterInfo;
            if (myParameterInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallOption", "getParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myParameterInfo;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
