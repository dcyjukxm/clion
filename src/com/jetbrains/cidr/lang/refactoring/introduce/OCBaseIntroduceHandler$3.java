// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.List;
import java.util.LinkedHashMap;
import com.intellij.refactoring.introduce.inplace.OccurrencesChooser;
import com.intellij.openapi.util.Pass;

class OCBaseIntroduceHandler$3 extends Pass<OccurrencesChooser.ReplaceChoice> {
    final /* synthetic */ OCBaseInplaceIntroducer val$introducer;
    final /* synthetic */ LinkedHashMap val$occurrencesMap;
    final /* synthetic */ List val$occurrences;
    
    public void pass(final OccurrencesChooser.ReplaceChoice replaceChoice) {
        this.val$introducer.setOccurrences((replaceChoice != null) ? this.val$occurrencesMap.get(replaceChoice) : this.val$occurrences);
        final PsiElement evaluateAnchor = this.val$introducer.evaluateAnchor();
        if (evaluateAnchor != null) {
            this.val$introducer.setAnchor(evaluateAnchor);
            this.val$introducer.configurePanel();
            this.val$introducer.startInplaceIntroduceTemplate();
        }
    }
}