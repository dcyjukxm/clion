// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.containers.MultiMap;
import com.intellij.util.Processor;

private static class InnerFriendFinder implements Processor<OCSymbol>
{
    private final MultiMap<String, OCSymbol> ourContext;
    
    public InnerFriendFinder(final MultiMap<String, OCSymbol> ourContext) {
        this.ourContext = ourContext;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCFunctionSymbol && ((OCFunctionSymbol)ocSymbol).isFriend()) {
            for (final OCSymbol ocSymbol2 : this.ourContext.get((Object)ocSymbol.getName())) {
                boolean b = false;
                if (((OCFunctionSymbol)ocSymbol).getQualifier() != null) {
                    for (final OCSymbol ocSymbol3 : OCSymbolReference.getGlobalReference(((OCFunctionSymbol)ocSymbol).getQualifiedName(), ((OCFunctionSymbol)ocSymbol).getParent()).resolveToSymbols(true, false, (PsiFile)ocSymbol.getContainingOCFile())) {
                        final OCQualifiedName resolvedQualifiedName = ((OCFunctionSymbol)ocSymbol2).getResolvedQualifiedName();
                        if (ocSymbol3 instanceof OCFunctionSymbol && Comparing.equal((Object)((OCFunctionSymbol)ocSymbol3).getResolvedQualifiedName(), (Object)resolvedQualifiedName)) {
                            b = true;
                            break;
                        }
                    }
                }
                else {
                    b = true;
                }
                if (b && new OCTypeEqualityVisitor(ocSymbol.getResolvedType(), true, true, true, true, new OCResolveContext()).equal(ocSymbol2.getResolvedType())) {
                    return false;
                }
            }
        }
        else if (ocSymbol instanceof OCStructSymbol) {
            if (((OCStructSymbol)ocSymbol).isFriend()) {
                final Iterator<OCSymbol> iterator3 = this.ourContext.get((Object)ocSymbol.getName()).iterator();
                if (iterator3.hasNext()) {
                    final OCSymbol ocSymbol4 = iterator3.next();
                    return false;
                }
            }
            if (!((OCStructSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)this)) {
                return false;
            }
        }
        return true;
    }
}
