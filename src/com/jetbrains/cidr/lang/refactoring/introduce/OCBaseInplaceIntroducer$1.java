// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import java.awt.event.ActionEvent;
import com.intellij.ui.NonFocusableCheckBox;
import com.intellij.util.Processor;
import com.intellij.openapi.project.Project;
import java.awt.event.ActionListener;

static final class OCBaseInplaceIntroducer$1 implements ActionListener {
    final /* synthetic */ Project val$project;
    final /* synthetic */ String val$actionTitle;
    final /* synthetic */ Processor val$listener;
    final /* synthetic */ NonFocusableCheckBox val$checkBox;
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        new WriteCommandAction(this.val$project, this.val$actionTitle, this.val$actionTitle, new PsiFile[0]) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer$1$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                ActionListener.this.val$listener.process((Object)ActionListener.this.val$checkBox.isSelected());
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
}