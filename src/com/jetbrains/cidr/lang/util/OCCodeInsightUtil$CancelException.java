// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCDeclarator;

static class CancelException extends RuntimeException
{
    private final OCDeclarator myDeclarator;
    
    public CancelException(final OCDeclarator myDeclarator) {
        this.myDeclarator = myDeclarator;
    }
    
    public OCDeclarator getDeclarator() {
        return this.myDeclarator;
    }
    
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
