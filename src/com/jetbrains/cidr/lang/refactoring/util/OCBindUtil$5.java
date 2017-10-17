// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.intellij.psi.PsiElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.quickfixes.OCChangeVisibilityIntentionAction;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.command.CommandProcessor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCEscalateVisibilityDialog;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.command.WriteCommandAction;

static final class OCBindUtil$5 extends WriteCommandAction {
    final /* synthetic */ Project val$project;
    final /* synthetic */ VirtualFile[] val$affectedFiles;
    final /* synthetic */ OCEscalateVisibilityDialog val$dialog;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/util/OCBindUtil$5", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        CommandProcessor.getInstance().markCurrentCommandAsGlobal(this.val$project);
        CommandProcessor.getInstance().setCurrentCommandName("Escalate visibility");
        CommandProcessor.getInstance().addAffectedFiles(this.val$project, this.val$affectedFiles);
        for (final Pair<SmartPsiElementPointer, OCVisibility> pair : this.val$dialog.getCheckedMembers()) {
            final PsiElement element = ((SmartPsiElementPointer)pair.getFirst()).getElement();
            OCSymbolWithParent symbol = null;
            Label_0148: {
                try {
                    if (element instanceof OCSymbolDeclarator) {
                        symbol = ((OCSymbolDeclarator<OCSymbolWithParent>)element).getSymbol();
                        break Label_0148;
                    }
                }
                catch (Throwable t2) {
                    throw a(t2);
                }
                symbol = null;
            }
            final OCSymbolWithParent ocSymbolWithParent = symbol;
            try {
                if (!(ocSymbolWithParent instanceof OCSymbolWithParent)) {
                    continue;
                }
                new OCChangeVisibilityIntentionAction(ocSymbolWithParent, (OCVisibility)pair.getSecond()).invoke();
            }
            catch (Throwable t3) {
                throw a(t3);
            }
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}