// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public interface OCNamespaceLikeSymbol extends OCMembersContainer<OCSymbol>
{
    @Nullable
    Collection<OCUsingSymbol> getNamespaceUsings();
    
    @Nullable
    Collection<OCNamespaceSymbol> getInlineNamespaces();
    
    public interface FileSymbolsProvider extends UsingNamespaceProvider
    {
        OCFile getFile();
    }
    
    public interface UsingNamespaceProvider extends OCNamespaceLikeSymbol
    {
        int getUsingNamespaceIndex(final OCUsingSymbol p0);
    }
}
