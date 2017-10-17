// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import java.io.Serializable;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;

public interface OCMethodSymbol extends OCMemberSymbol, OCSymbolWithSubstitution
{
    public static final String INSTANCETYPE = "instancetype";
    
    @NotNull
    OCType getReturnType(@Nullable final OCObjectType p0);
    
    @NotNull
    OCType getReturnType();
    
    boolean isOptional();
    
    boolean isVararg();
    
    boolean isFactoryMethod();
    
    boolean isConstructorMethod();
    
    boolean isClassConstructorMethod();
    
    @Nullable
    OCMethodSymbol getDefinitionSymbol();
    
    boolean isGetter();
    
    boolean isSetter();
    
    @Nullable
    OCPropertySymbol getGeneratedFromProperty();
    
    boolean isAccessorWithAliasedName();
    
    @NotNull
    List<SelectorPartSymbol> getSelectors();
    
    @NotNull
    List<OCDeclaratorSymbol> getParameterSymbols();
    
    @Nullable
    OCMethodSymbol getAssociatedSymbol();
    
    @Nullable
    OCMethodSymbol getAssociatedSymbol(@Nullable final PsiElement p0);
    
    public interface SelectorPartSymbol extends DeepEqual.Equality<SelectorPartSymbol>, Serializable, OCSymbolWithSubstitution
    {
        @Nullable
        OCDeclaratorSymbol getParameter();
        
        @Nullable
        OCDeclaratorSymbol getParameterWithoutSubstitution();
        
        @Nullable
        String getSelectorName();
    }
}
