// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCDelegatingSymbol;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.HashMap;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import java.util.Collection;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

public abstract class OCFileSymbolsContainerBase implements Processor<OCSymbol>, OCNamespaceLikeSymbol.FileSymbolsProvider
{
    private OCFile myFile;
    protected MostlySingularMultiMap<String, OCSymbol> myNameToSymbol;
    protected Collection<OCUsingSymbol> myUsingSymbols;
    protected Collection<OCNamespaceSymbol> myInlineNamespaces;
    protected HashMap<OCUsingSymbol, Integer> myAllUsingSymbolsToIndex;
    protected int myCurrentUsingIndex;
    
    protected OCFileSymbolsContainerBase(final OCFile myFile) {
        this.myNameToSymbol = (MostlySingularMultiMap<String, OCSymbol>)new MostlySingularMultiMap();
        this.myUsingSymbols = new ArrayList<OCUsingSymbol>();
        this.myInlineNamespaces = new ArrayList<OCNamespaceSymbol>();
        this.myAllUsingSymbolsToIndex = new HashMap<OCUsingSymbol, Integer>();
        this.myCurrentUsingIndex = 0;
        this.myFile = myFile;
    }
    
    public OCFile getFile() {
        return this.myFile;
    }
    
    public String getPresentableName() {
        return "File '" + this.myFile.getName() + "'";
    }
    
    public OCFile getContainingOCFile() {
        return this.myFile;
    }
    
    @Nullable
    public PsiElement getTargetElement() {
        return (PsiElement)this.myFile;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Top Level Functions";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileSymbolsContainerBase", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nullable
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            @Nullable
            public String getPresentableText() {
                return OCFileSymbolsContainerBase.this.getName();
            }
            
            @Nullable
            public String getLocationString() {
                return OCFileSymbolsContainerBase.this.myFile.getName();
            }
            
            @Nullable
            public Icon getIcon(final boolean b) {
                return OCFileSymbolsContainerBase.this.myFile.getIcon(0);
            }
        };
    }
    
    public void navigate(final boolean b) {
        this.myFile.navigate(b);
    }
    
    public boolean canNavigate() {
        return this.myFile.canNavigate();
    }
    
    public boolean canNavigateToSource() {
        return this.myFile.canNavigateToSource();
    }
    
    protected void fillUsingsIndex(final OCNamespaceSymbol ocNamespaceSymbol) {
        final List<OCUsingSymbol> namespaceUsings = ocNamespaceSymbol.getNamespaceUsings();
        if (namespaceUsings != null) {
            final Iterator<Object> iterator = namespaceUsings.iterator();
            while (iterator.hasNext()) {
                this.myAllUsingSymbolsToIndex.put(iterator.next(), this.myCurrentUsingIndex++);
            }
        }
        ocNamespaceSymbol.processMembers(null, (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (ocSymbol.getKind() == OCSymbolKind.NAMESPACE) {
                    this.fillUsingsIndex((OCNamespaceSymbol)ocSymbol);
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return true;
        }));
    }
    
    public boolean processMembers(@Nullable final String s, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileSymbolsContainerBase", "processMembers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s != null) {
                return this.myNameToSymbol.processForKey((Object)s, (Processor)OCDelegatingSymbol.getDelegateProcessor(processor));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return this.myNameToSymbol.processAllValues((Processor)OCDelegatingSymbol.getDelegateProcessor(processor));
    }
    
    public Collection<OCUsingSymbol> getNamespaceUsings() {
        return this.myUsingSymbols;
    }
    
    @Nullable
    public Collection<OCNamespaceSymbol> getInlineNamespaces() {
        return this.myInlineNamespaces;
    }
    
    public int getUsingNamespaceIndex(final OCUsingSymbol ocUsingSymbol) {
        final Integer n = this.myAllUsingSymbolsToIndex.get(ocUsingSymbol);
        try {
            if (n != null) {
                return n;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return -1;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
