// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OCArgumentList extends OCElement
{
    @NotNull
    List<OCExpression> getArguments();
    
    @NotNull
    List<OCType> getArgumentTypes(@NotNull final OCResolveContext p0);
    
    @Nullable
    PsiElement getLeftPar();
    
    @Nullable
    PsiElement getRightPar();
}
