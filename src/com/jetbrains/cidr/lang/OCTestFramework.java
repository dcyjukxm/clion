// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCTestFramework
{
    public static final ExtensionPointName<OCTestFramework> EP_NAME = ExtensionPointName.create("cidr.lang.testFramework");
    
    @Contract("null -> true")
    boolean isAvailable(@Nullable final PsiFile p0);
    
    @Contract("null -> false")
    boolean isTestClassOrStruct(@Nullable final OCSymbol p0);
    
    @Contract("null -> false")
    boolean isTestClass(@Nullable final PsiElement p0);
    
    @Contract("null -> false")
    boolean isTestMethodOrFunction(@Nullable final OCSymbol p0);
}
