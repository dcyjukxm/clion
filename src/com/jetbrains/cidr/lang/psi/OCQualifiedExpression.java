// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;
import com.intellij.util.Processor;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Ref;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCQualifiedExpression extends OCExpression, OCTemplateArgumentsOwner, OCSymbolNameOwner, PsiNameIdentifierOwner, OCResolvesToSymbol<OCSymbol>
{
    public static final Key<OCPunctuatorElementType> COMPLETION_QUALIFYING_TOKEN_KEY = new Key("COMPLETION QUALIFYING TOKEN");
    
    @NotNull
    OCExpression getQualifier();
    
    @NotNull
    OCPunctuatorElementType getQualifyingTokenKind();
    
    @NotNull
    ASTNode getQualifyingToken();
    
    @Nullable
    OCType getQualifierContainerType(final Ref<Boolean> p0);
    
    @NotNull
    OCElement getQualifyingElement();
    
    @NotNull
    CVQualifiers getCVQualifiers();
    
    @Nullable
    OCSymbolGroupContext getSymbolContext();
    
    @Nullable
    OCSymbol resolveToSymbol();
    
    @Nullable
    OCSymbol resolveToSymbol(@NotNull final OCResolveContext p0, final boolean p1, final boolean p2, final boolean p3);
    
    @NotNull
    Collection<OCSymbol> resolveToOverloadsSymbols();
    
    boolean processTargets(@Nullable final String p0, final Processor<OCSymbol> p1, final boolean p2, final OCPunctuatorElementType p3, final boolean p4, final boolean p5, @Nullable final Ref<OCType> p6);
    
    boolean canChangeQualifyingToken();
    
    @NotNull
    List<OCPunctuatorElementType> qualifyingTokensForCompletion();
}
