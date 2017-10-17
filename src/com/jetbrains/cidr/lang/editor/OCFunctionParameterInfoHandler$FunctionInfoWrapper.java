// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCFunctionParameterInfo;

public static class FunctionInfoWrapper
{
    @NotNull
    private final OCFunctionParameterInfo myInfo;
    @NotNull
    private final OCResolveContext myContext;
    
    public FunctionInfoWrapper(@NotNull final OCFunctionParameterInfo myInfo, @NotNull final OCResolveContext myContext) {
        if (myInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler$FunctionInfoWrapper", "<init>"));
        }
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler$FunctionInfoWrapper", "<init>"));
        }
        this.myInfo = myInfo;
        this.myContext = myContext;
    }
    
    @NotNull
    public OCFunctionParameterInfo getInfo() {
        OCFunctionParameterInfo myInfo;
        try {
            myInfo = this.myInfo;
            if (myInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler$FunctionInfoWrapper", "getInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myInfo;
    }
    
    @Override
    public int hashCode() {
        return this.myInfo.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        Label_0041: {
            try {
                if (!(o instanceof FunctionInfoWrapper)) {
                    return false;
                }
                final FunctionInfoWrapper functionInfoWrapper = this;
                final OCFunctionParameterInfo ocFunctionParameterInfo = functionInfoWrapper.myInfo;
                final OCFunctionType ocFunctionType = ocFunctionParameterInfo.getType();
                final Object o2 = o;
                final FunctionInfoWrapper functionInfoWrapper2 = (FunctionInfoWrapper)o2;
                final OCFunctionParameterInfo ocFunctionParameterInfo2 = functionInfoWrapper2.getInfo();
                final OCFunctionType ocFunctionType2 = ocFunctionParameterInfo2.getType();
                final FunctionInfoWrapper functionInfoWrapper3 = this;
                final OCResolveContext ocResolveContext = functionInfoWrapper3.myContext;
                final boolean b = ocFunctionType.equals(ocFunctionType2, ocResolveContext);
                if (b) {
                    break Label_0041;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final FunctionInfoWrapper functionInfoWrapper = this;
                final OCFunctionParameterInfo ocFunctionParameterInfo = functionInfoWrapper.myInfo;
                final OCFunctionType ocFunctionType = ocFunctionParameterInfo.getType();
                final Object o2 = o;
                final FunctionInfoWrapper functionInfoWrapper2 = (FunctionInfoWrapper)o2;
                final OCFunctionParameterInfo ocFunctionParameterInfo2 = functionInfoWrapper2.getInfo();
                final OCFunctionType ocFunctionType2 = ocFunctionParameterInfo2.getType();
                final FunctionInfoWrapper functionInfoWrapper3 = this;
                final OCResolveContext ocResolveContext = functionInfoWrapper3.myContext;
                final boolean b = ocFunctionType.equals(ocFunctionType2, ocResolveContext);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
