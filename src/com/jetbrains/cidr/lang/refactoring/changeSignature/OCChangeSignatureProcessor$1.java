// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import javax.swing.Action;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.usages.UsageView;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.MultiMap;
import com.intellij.usageView.UsageInfo;
import com.intellij.refactoring.ui.ConflictsDialog;

class OCChangeSignatureProcessor$1 extends ConflictsDialog {
    final /* synthetic */ UsageInfo[] val$usages;
    final /* synthetic */ MultiMap val$conflicts;
    
    @Override
    protected Runnable getDoRefactoringRunnable(@Nullable final UsageView usageView) {
        final Collection collection;
        return () -> {
            OCChangeSignatureProcessor.access$000(this.val$usages, usageView);
            OCChangeSignatureProcessor.access$100(OCChangeSignatureProcessor.this, collection.toArray(new UsageInfo[collection.size()]));
        };
    }
    
    @NotNull
    protected Action getOKAction() {
        final Action okAction = super.getOKAction();
        for (final String s : this.val$conflicts.values()) {
            try {
                if (OCChangeSignatureUsageProcessor.canProceedWithConflict(s)) {
                    continue;
                }
                okAction.setEnabled(false);
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        Action action;
        try {
            action = okAction;
            if (action == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor$1", "getOKAction"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return action;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}