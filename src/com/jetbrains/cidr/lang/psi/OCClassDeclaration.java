// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCObjectType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public interface OCClassDeclaration<T extends OCClassSymbol> extends OCElement, OCClassDeclarationBase
{
    public static final OCClassDeclaration[] EMPTY_ARRAY = new OCClassDeclaration[0];
    
    @Nullable
    String getName();
    
    @Nullable
    OCClassSymbol getSymbol();
    
    @Nullable
    String getCanonicalName();
    
    @Nullable
    OCCategoryName getCategoryElement();
    
    @Nullable
    String getCategory();
    
    @Nullable
    OCObjectType getType(final boolean p0);
    
    @Nullable
    OCObjectType getType();
    
    List<OCClassDeclaration> getSuperTypes();
    
    @NotNull
    OCSuperClassRef getSuperClassRef();
    
    @NotNull
    OCProtocolList getProtocolList();
    
    @NotNull
    List<OCMethod> getMethods();
    
    @NotNull
    List<OCProperty> getProperties();
    
    @NotNull
    List<OCSynthesizePropertiesList> getSynthesizes();
    
    @Nullable
    OCInterface getSuperClass();
    
    List<OCSymbol> getSuperSymbols();
    
    @NotNull
    OCInstanceVariablesList getInstanceVariablesList();
    
    int getMethodsStartOffset(final boolean p0);
    
    int getMethodsEndOffset(final boolean p0);
    
    int getMethodsInsertPosition(final boolean p0, final PsiElement p1, final int p2);
    
    @Nullable
    PsiElement getBestMemberPlace(final OCMemberSymbol p0);
}
