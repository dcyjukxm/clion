// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import com.intellij.psi.PsiElement;

public interface OCTemplateSymbol<T extends PsiElement> extends OCSymbolWithSubstitution, OCSymbol<T>
{
    boolean isTemplateSymbol();
    
    boolean isVariadicTemplate();
    
    @NotNull
    List<OCTypeParameterSymbol> getTemplateParameters();
    
    boolean isSpecialization();
    
    boolean isExplicitInstantiation();
    
    int getRequiredTemplateArgumentsCnt();
    
    @Nullable
    List<OCTypeArgument> getTemplateSpecialization();
}
