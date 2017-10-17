// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.editor.Editor;
import com.intellij.featureStatistics.FeatureUsageTracker;
import java.awt.event.MouseEvent;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.daemon.LineMarkerInfo;

static class OCLineMarkerInfo extends LineMarkerInfo<PsiElement>
{
    private OCGotoAction myAction;
    
    private OCLineMarkerInfo(@NotNull final OCGotoAction myAction, @NotNull final PsiElement psiElement, final int n) {
        if (myAction == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo", "<init>"));
        }
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo", "<init>"));
        }
        super(psiElement, OCElementUtil.getTextRangeWithoutComments(psiElement), myAction.getIcon(), n, psiElement -> {
            try {
                if (myAction == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo", "lambda$new$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myAction.getName();
        }, (GutterIconNavigationHandler)new GutterIconNavigationHandler<PsiElement>() {
            public void navigate(final MouseEvent mouseEvent, final PsiElement psiElement) {
                FeatureUsageTracker.getInstance().triggerFeatureUsed("navigation.class.hierarchy");
                myAction.navigate(mouseEvent, null);
            }
        }, GutterIconRenderer.Alignment.RIGHT);
        this.myAction = myAction;
    }
    
    public OCGotoAction getAction() {
        return this.myAction;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
