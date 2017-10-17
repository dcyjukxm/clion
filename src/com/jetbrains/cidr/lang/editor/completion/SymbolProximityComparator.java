// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.util.ProcessingContext;
import com.intellij.psi.WeighingService;
import com.intellij.openapi.module.ModuleUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.psi.WeighingComparable;
import com.intellij.util.containers.FactoryMap;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.ProximityLocation;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.Weigher;
import com.intellij.openapi.util.Key;
import java.util.Comparator;

public class SymbolProximityComparator implements Comparator<Object>
{
    public static final Key<Weigher<OCSymbol, ProximityLocation>> WEIGHER_KEY;
    private final PsiElement myContext;
    private final FactoryMap<OCSymbol, WeighingComparable<OCSymbol, ProximityLocation>> myProximities;
    private static final Key<Module> MODULE_BY_LOCATION;
    
    public SymbolProximityComparator(@Nullable final PsiElement myContext) {
        this.myProximities = new FactoryMap<OCSymbol, WeighingComparable<OCSymbol, ProximityLocation>>() {
            protected WeighingComparable<OCSymbol, ProximityLocation> create(final OCSymbol ocSymbol) {
                return SymbolProximityComparator.getProximity(ocSymbol, SymbolProximityComparator.this.myContext);
            }
        };
        this.myContext = myContext;
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        final OCSymbol ocSymbol = (o instanceof OCSymbol) ? ((OCSymbol)o) : null;
        final OCSymbol ocSymbol2 = (o2 instanceof OCSymbol) ? ((OCSymbol)o2) : null;
        if (ocSymbol == null) {
            return (ocSymbol2 != null) ? 1 : 0;
        }
        if (ocSymbol2 == null) {
            return -1;
        }
        final WeighingComparable weighingComparable = (WeighingComparable)this.myProximities.get((Object)ocSymbol);
        final WeighingComparable weighingComparable2 = (WeighingComparable)this.myProximities.get((Object)ocSymbol2);
        if (weighingComparable == null || weighingComparable2 == null) {
            return 0;
        }
        if (!weighingComparable.equals(weighingComparable2)) {
            return -weighingComparable.compareTo(weighingComparable2);
        }
        return 0;
    }
    
    @Nullable
    public static WeighingComparable<OCSymbol, ProximityLocation> getProximity(final OCSymbol ocSymbol, final PsiElement psiElement) {
        if (ocSymbol == null) {
            return null;
        }
        return (WeighingComparable<OCSymbol, ProximityLocation>)WeighingService.weigh((Key)SymbolProximityComparator.WEIGHER_KEY, (Object)ocSymbol, (Object)new ProximityLocation(psiElement, (psiElement != null) ? ModuleUtil.findModuleForPsiElement(psiElement) : null));
    }
    
    @Nullable
    public static WeighingComparable<OCSymbol, ProximityLocation> getProximity(final OCSymbol ocSymbol, final PsiElement psiElement, final ProcessingContext processingContext) {
        if (ocSymbol == null) {
            return null;
        }
        if (psiElement == null) {
            return null;
        }
        Module moduleForPsiElement = (Module)processingContext.get((Key)SymbolProximityComparator.MODULE_BY_LOCATION);
        if (moduleForPsiElement == null) {
            moduleForPsiElement = ModuleUtil.findModuleForPsiElement(psiElement);
            processingContext.put((Key)SymbolProximityComparator.MODULE_BY_LOCATION, (Object)moduleForPsiElement);
        }
        if (moduleForPsiElement == null) {
            return null;
        }
        return (WeighingComparable<OCSymbol, ProximityLocation>)WeighingService.weigh((Key)SymbolProximityComparator.WEIGHER_KEY, (Object)ocSymbol, (Object)new ProximityLocation(psiElement, moduleForPsiElement, processingContext));
    }
    
    static {
        WEIGHER_KEY = Key.create("symbolProximity");
        MODULE_BY_LOCATION = Key.create("ModuleByLocation");
    }
}
