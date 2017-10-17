// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiElement;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Processor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCClassSymbol extends OCSymbol<OCClassDeclarationBase>, OCMembersContainer<OCMemberSymbol>
{
    String getCategoryName();
    
    void setSuperType(@NotNull final OCReferenceType p0);
    
    @NotNull
    OCReferenceType getSuperType();
    
    @NotNull
    String getSuperClassName();
    
    @NotNull
    List<String> getProtocolNames();
    
    int getMembersCount();
    
    boolean processMembers(final String p0, @NotNull final Processor<OCMemberSymbol> p1);
    
     <T extends OCMemberSymbol> boolean processMembers(final Class<T> p0, final Processor<? super T> p1);
    
     <T extends OCMemberSymbol> boolean processMembers(@Nullable final String p0, @NotNull final Class<? extends T> p1, final Processor<? super T> p2);
    
     <T extends OCMemberSymbol> boolean processMembersInAllCategories(@Nullable final String p0, final Class<? extends T> p1, final Processor<? super T> p2);
    
     <T extends OCMemberSymbol> boolean processMembersInAllCategories(@Nullable final String p0, final Class<? extends T> p1, final Processor<? super T> p2, final boolean p3);
    
     <T extends OCMemberSymbol> boolean processMembers(@Nullable final String p0, @Nullable final String p1, @NotNull final Class<? extends T> p2, @NotNull final Processor<? super T> p3, final boolean p4);
    
    boolean processAllMethods(@Nullable final String p0, @NotNull final Processor<OCMethodSymbol> p1, @Nullable final Set<String> p2, @Nullable final PsiElement p3);
    
    boolean processCategories(final Processor<? super OCClassSymbol> p0, final boolean p1, @Nullable final PsiElement p2);
    
    @Nullable
    OCClassSymbol getDefinitionSymbol();
    
    @Nullable
    OCInterfaceSymbol getInterface();
    
    @Nullable
    OCClassSymbol getInterfaceOrProtocol();
    
    @Nullable
    OCImplementationSymbol getImplementation();
    
    @Nullable
    OCInterfaceSymbol getMainInterface();
    
    @Nullable
    OCImplementationSymbol getMainImplementation();
    
    @Nullable
    OCObjectType getResolvedType(final boolean p0);
    
    boolean isSameClass(final OCClassSymbol p0);
    
    boolean isSameCategory(final OCSymbol p0);
    
    boolean isSubclass(final OCClassSymbol p0);
}
