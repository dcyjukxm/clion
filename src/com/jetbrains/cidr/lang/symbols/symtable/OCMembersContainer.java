// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.navigation.PsiElementNavigationItem;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCMembersContainer<M extends OCSymbol> extends PsiElementNavigationItem
{
    String getPresentableName();
    
    @Nullable
    OCFile getContainingOCFile();
    
    boolean processMembers(@Nullable final String p0, @NotNull final Processor<M> p1);
}
