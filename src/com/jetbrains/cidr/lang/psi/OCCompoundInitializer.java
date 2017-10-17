// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OCCompoundInitializer extends OCExpression
{
    @NotNull
    List<OCCompoundInitializerMember> getInitializers();
    
    @NotNull
    List<OCExpression> getInitializerExpressions();
    
    @Nullable
    OCType inferType();
    
    @Nullable
    OCType inferChildType(final OCElement p0);
    
    void inferChildTypes(final Map<PsiElement, Pair<OCType, OCSymbol>> p0, final Set<OCSymbol> p1);
}
