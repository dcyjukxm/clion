// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;

public interface OCDeclarationStatement extends OCStatement
{
    @NotNull
    OCDeclaration getDeclaration();
}
