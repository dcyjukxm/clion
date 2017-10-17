// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import javax.swing.Icon;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.intellij.util.Processor;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Condition;
import java.io.Serializable;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.navigation.PsiElementNavigationItem;
import com.intellij.psi.PsiElement;

public interface OCSymbol<T extends PsiElement> extends PsiElementNavigationItem, Comparable<OCSymbol>, DataProvider, Serializable, ProjectAndVirtualFileOwner, DeepEqual.Equality
{
    public static final String UNNAMED = "<unnamed>";
    public static final Condition<OCSymbol> PREDIFINITION_CONDITION = ocSymbol -> ocSymbol.isPredeclaration();
    public static final Condition<OCSymbol> NON_PREDIFINITION_CONDITION = ocSymbol -> !ocSymbol.isPredeclaration();
    public static final Condition<OCSymbol> NON_FANTOM_SYMBOL_CONDITION = ocSymbol -> ocSymbol.getContainingFile() != null || ocSymbol.getOffset() != 0;
    public static final Condition<OCSymbol> HEADER_DECLARATION_CONDITION = ocSymbol -> {
        final OCFile containingOCFile = ocSymbol.getContainingOCFile();
        return containingOCFile != null && containingOCFile.isHeader();
    };
    
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getResolvedType();
    
    OCType getResolvedType(final boolean p0);
    
    boolean isGlobal();
    
    boolean isTopLevel();
    
    boolean isCallable();
    
    boolean isDefinition();
    
    boolean isPredeclaration();
    
    @NotNull
    List<String> getAttributes();
    
    void addAttributes(final List<String> p0);
    
    boolean isUnnamed();
    
    Object getData(@NonNls final String p0);
    
    @Nullable
    T locateDefinition();
    
    void updateOffset(final int p0, final int p1, final int p2);
    
    void compact();
    
    @NotNull
    String getName();
    
    @NotNull
    String getSignature();
    
    @Nullable
    TextRange getScope();
    
    @NotNull
    String getPresentableName();
    
    @Nullable
    String getLocationString();
    
    boolean isSynthetic();
    
    @NotNull
    OCSymbolKind getKind();
    
    @NotNull
    String getNameWithKindLowercase();
    
    @NotNull
    String getNameWithKindUppercase();
    
    String getKindLowercase();
    
    String getKindUppercase();
    
    @Nullable
    OCSymbol getDefinitionSymbol();
    
    boolean processAssociatedSymbols(final Processor<OCSymbol> p0);
    
    @Nullable
    OCSymbol getAssociatedSymbol();
    
    OCSymbol getFirstPredeclaration();
    
    boolean processPredeclarations(final Processor<OCSymbol> p0);
    
    boolean processSameSymbols(final Processor<OCSymbol> p0);
    
    @Nullable
    PsiFile getContainingPsiFile();
    
    @Nullable
    OCFile getContainingOCFile();
    
    int getOffset();
    
    long getComplexOffset();
    
    @Nullable
    Icon getIcon();
    
    @Nullable
    Icon getBaseIcon();
    
    @Nullable
    Icon computeFullIconNow(@Nullable final T p0);
    
    boolean isSameSymbol(@Nullable final OCSymbol p0);
    
    int hashCodeExcludingOffset();
    
    OCType getEffectiveType();
    
    @NotNull
    OCType getEffectiveResolvedType();
    
    boolean isDeprecated();
    
    String getDeprecatedMessage();
    
    boolean isUnavailable();
    
    String getUnavailableMessage();
    
    boolean isForbiddenByARC(@NotNull final PsiElement p0);
    
    boolean isTransparentUnion();
    
    boolean hasAttribute(final String p0);
    
    @Nullable
    String getAttributeParameters(final String p0);
    
    @NotNull
    OCSymbol<?> getDelegate();
    
    @NotNull
    String getPresentableText();
}
