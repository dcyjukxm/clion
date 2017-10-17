// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import gnu.trove.THashSet;
import com.intellij.util.Processor;

private static class SymbolMembersProcessor implements Processor<OCSymbol>
{
    private final String myName;
    private final Processor<OCSymbol> myProcessor;
    private final boolean myOnlySimpleNamespaces;
    private final boolean myOnlyTypes;
    private OCResolveContext myMemoization;
    private final THashSet<OCSymbol> myProcessed;
    
    public SymbolMembersProcessor(@Nullable final String myName, final Processor<OCSymbol> myProcessor, final boolean myOnlySimpleNamespaces, final boolean myOnlyTypes, @NotNull final OCResolveContext myMemoization) {
        if (myMemoization == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolMembersProcessor", "<init>"));
        }
        this.myName = myName;
        this.myProcessor = myProcessor;
        this.myOnlySimpleNamespaces = myOnlySimpleNamespaces;
        this.myOnlyTypes = myOnlyTypes;
        this.myMemoization = myMemoization;
        this.myProcessed = (THashSet<OCSymbol>)new THashSet((TObjectHashingStrategy)new TObjectHashingStrategy<OCSymbol>() {
            public int computeHashCode(final OCSymbol ocSymbol) {
                return ocSymbol.hashCode();
            }
            
            public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
                return ocSymbol == ocSymbol2;
            }
        });
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        try {
            if (!this.myProcessed.add((Object)ocSymbol)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCResolveContext myMemoization = this.myMemoization;
        try {
            OCTypeParameterTypeSymbol ocTypeParameterTypeSymbol = null;
            Label_0128: {
                Label_0116: {
                    Label_0095: {
                        OCNamespaceSymbol ocNamespaceSymbol2 = null;
                        String s = null;
                        Label_0063: {
                            try {
                                this.myMemoization = this.myMemoization.useFor(ocSymbol);
                                if (!(ocSymbol instanceof OCNamespaceSymbol)) {
                                    break Label_0095;
                                }
                                final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                                ocNamespaceSymbol2 = ocNamespaceSymbol;
                                final SymbolMembersProcessor symbolMembersProcessor = this;
                                s = symbolMembersProcessor.myName;
                                final SymbolMembersProcessor symbolMembersProcessor2 = this;
                                final boolean b = symbolMembersProcessor2.myOnlySimpleNamespaces;
                                if (!b) {
                                    break Label_0063;
                                }
                                break Label_0063;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            try {
                                final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                                ocNamespaceSymbol2 = ocNamespaceSymbol;
                                final SymbolMembersProcessor symbolMembersProcessor = this;
                                s = symbolMembersProcessor.myName;
                                final SymbolMembersProcessor symbolMembersProcessor2 = this;
                                final boolean b = symbolMembersProcessor2.myOnlySimpleNamespaces;
                                if (!b) {
                                    final boolean b2 = true;
                                    return OCStructType.processMembersOfNamespace(ocNamespaceSymbol2, s, b2, this.myOnlyTypes, this.myProcessor, this.myMemoization);
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        final boolean b2 = false;
                        return OCStructType.processMembersOfNamespace(ocNamespaceSymbol2, s, b2, this.myOnlyTypes, this.myProcessor, this.myMemoization);
                        try {
                            if (!(ocSymbol instanceof OCTypeParameterSymbol)) {
                                return true;
                            }
                            final OCNamespaceSymbol ocNamespaceSymbol3 = (OCNamespaceSymbol)ocSymbol;
                            final boolean b3 = ocNamespaceSymbol3 instanceof OCTypeParameterTypeSymbol;
                            if (b3) {
                                break Label_0116;
                            }
                            break Label_0116;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        final OCNamespaceSymbol ocNamespaceSymbol3 = (OCNamespaceSymbol)ocSymbol;
                        final boolean b3 = ocNamespaceSymbol3 instanceof OCTypeParameterTypeSymbol;
                        if (b3) {
                            ocTypeParameterTypeSymbol = (OCTypeParameterTypeSymbol)ocSymbol;
                            break Label_0128;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                ocTypeParameterTypeSymbol = null;
            }
            if (!this.myProcessor.process((Object)new OCTypeParameterTypeSymbol(null, null, 0L, this.myName, ocTypeParameterTypeSymbol, null, Collections.emptyList(), null, false))) {
                return false;
            }
            if (!this.myProcessor.process((Object)new OCDeclaratorSymbol(null, null, 0, null, this.myName, Collections.emptyList(), new OCMagicType(this.myName), OCSymbolKind.TEMPLATE_VALUE_PARAMETER))) {
                return false;
            }
            return true;
        }
        finally {
            this.myMemoization = myMemoization;
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
