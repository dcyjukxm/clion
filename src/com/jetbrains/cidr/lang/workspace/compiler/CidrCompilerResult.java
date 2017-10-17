// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import org.jetbrains.annotations.Nullable;

public class CidrCompilerResult<T>
{
    @Nullable
    private final T myResult;
    @Nullable
    private final Throwable myError;
    
    private CidrCompilerResult(@Nullable final T myResult, @Nullable final Throwable myError) {
        this.myResult = myResult;
        this.myError = myError;
    }
    
    @Nullable
    public T getResult() {
        return this.myResult;
    }
    
    @Nullable
    public Throwable getError() {
        return this.myError;
    }
    
    public static <T> CidrCompilerResult<T> create(final T t) {
        return new CidrCompilerResult<T>(t, null);
    }
    
    public static <T> CidrCompilerResult<T> error(final Throwable t) {
        return new CidrCompilerResult<T>(null, t);
    }
}
