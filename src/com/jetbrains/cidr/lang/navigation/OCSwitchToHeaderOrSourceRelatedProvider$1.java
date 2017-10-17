// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.codeInsight.navigation.NavigationUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.navigation.GotoRelatedItem;

class OCSwitchToHeaderOrSourceRelatedProvider$1 extends GotoRelatedItem {
    final /* synthetic */ NavigatablePsiElement val$counter;
    
    public void navigate() {
        NavigationUtil.activateFileWithPsiElement((PsiElement)this.val$counter);
    }
}