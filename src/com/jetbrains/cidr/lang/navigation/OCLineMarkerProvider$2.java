// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import java.util.List;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.ArrayList;
import javax.swing.Icon;
import com.intellij.psi.PsiElement;

static final class OCLineMarkerProvider$2 extends OCGotoActionSync {
    final /* synthetic */ OCLineMarkerInfo[] val$markers;
    
    @Override
    protected ArrayList<OCSymbol> evaluateTargets() {
        final ArrayList<OCSymbol> list = new ArrayList<OCSymbol>();
        final OCLineMarkerInfo[] val$markers = this.val$markers;
        for (int length = val$markers.length, i = 0; i < length; ++i) {
            final List<? extends OCSymbol> targets = val$markers[i].getAction().getTargets();
            if (targets != null) {
                list.addAll(targets);
            }
        }
        return list;
    }
}