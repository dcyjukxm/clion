// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;

public interface OCDeclarator extends OCElement, OCLocalSymbolDeclarator, PsiNamedElement, OCNamespaceQualifiedNameOwner, PsiNameIdentifierOwner, OCSymbolNameOwner
{
    @NotNull
    String getName();
    
    @Nullable
    OCCppNamespaceQualifier getNamespaceQualifier();
    
    @NotNull
    String getModifiersText();
    
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getResolvedType();
    
    @NotNull
    OCType getResolvedType(@NotNull final OCResolveContext p0);
    
    @NotNull
    OCType getRawType();
    
    @NotNull
    List<OCExpression> getArrayLengths();
    
    @Nullable
    OCExpression getInitializer();
    
    @Nullable
    OCCompoundInitializer getInitializerList();
    
    @NotNull
    List<OCExpression> getInitializers();
    
    @Nullable
    OCArgumentList getArgumentList();
    
    boolean isExplicitConstructorCall();
    
    @Nullable
    OCParameterList getParameterList();
    
    boolean isPointerToFunction();
    
    boolean isPossibleStructMember();
    
    @Nullable
    OCTemplateArgumentList getTemplateArgumentList();
    
    @NotNull
    PsiElement getExtendedContext();
}
