// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.symbols.OCSymbolHolderBase;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.ide.util.TreeChooser;
import java.awt.event.ActionEvent;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import java.awt.event.ActionListener;

static final class OCTypeReferenceEditor$1 implements ActionListener {
    final /* synthetic */ Ref val$result;
    final /* synthetic */ Project val$project;
    final /* synthetic */ Condition val$symbolCondition;
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final OCSymbol classDeclaration = ((OCTypeReferenceEditor)this.val$result.get()).getClassDeclaration(this.val$project);
        final OCClassChooserDialog ocClassChooserDialog = new OCClassChooserDialog("Choose Containing Class", this.val$project, (TreeChooser.Filter<OCSymbolHolderVirtualPsiElement>)new TreeChooser.Filter<OCSymbolHolderVirtualPsiElement>() {
            public boolean isAccepted(final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement) {
                return ActionListener.this.val$symbolCondition.value(((OCSymbolHolderBase<Object>)ocSymbolHolderVirtualPsiElement).getSymbol());
            }
        }, (classDeclaration != null) ? new OCSymbolHolderVirtualPsiElement(classDeclaration) : null, (Condition<OCSymbol>)this.val$symbolCondition);
        ocClassChooserDialog.showDialog();
        if (ocClassChooserDialog.isOK()) {
            ((OCTypeReferenceEditor)this.val$result.get()).setText(ocClassChooserDialog.getSelected().getSymbol().getPresentableName());
        }
    }
}