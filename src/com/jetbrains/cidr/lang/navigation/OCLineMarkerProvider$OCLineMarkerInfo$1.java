// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.openapi.editor.Editor;
import com.intellij.featureStatistics.FeatureUsageTracker;
import java.awt.event.MouseEvent;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;

class OCLineMarkerProvider$OCLineMarkerInfo$1 implements GutterIconNavigationHandler<PsiElement> {
    final /* synthetic */ OCGotoAction val$action;
    
    public void navigate(final MouseEvent mouseEvent, final PsiElement psiElement) {
        FeatureUsageTracker.getInstance().triggerFeatureUsed("navigation.class.hierarchy");
        this.val$action.navigate(mouseEvent, null);
    }
}