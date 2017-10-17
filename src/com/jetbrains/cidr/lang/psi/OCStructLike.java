// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.NavigatablePsiElement;

public interface OCStructLike extends OCElement, OCBlockOwner, NavigatablePsiElement, OCNamespaceQualifierOwner, PsiNameIdentifierOwner, OCLocalSymbolDeclarator<OCStructSymbol>, OCTemplateArgumentsOwner, OCSymbolNameOwner
{
    OCSymbolKind getKind();
    
    List<OCDeclaration> getFields();
    
    @Nullable
    OCCppBaseClauseList getBaseClausesList();
    
    boolean isDeclaration();
    
    @NotNull
    TextRange getHeaderRange();
    
    int getFunctionsStartOffset();
    
    OCVisibility getDefaultVisibility();
}
