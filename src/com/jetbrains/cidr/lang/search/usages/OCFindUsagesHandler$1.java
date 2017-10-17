// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Set;

class OCFindUsagesHandler$1 implements AssociatedElementProcessor {
    final /* synthetic */ Set val$result;
    
    @Override
    public boolean proceedProperty(final OCPropertySymbol ocPropertySymbol) {
        final String string = "Do you want to " + OCFindUsagesHandler.access$000(OCFindUsagesHandler.this) + " the " + ocPropertySymbol.getNameWithKindLowercase() + " as well?";
        return ApplicationManager.getApplication().isUnitTestMode() || Messages.showYesNoDialog(string, "Properties search", Messages.getQuestionIcon()) == 0;
    }
    
    @Override
    public boolean proceedIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        if (ocInstanceVariableSymbol.getGeneratedFromProperty() != null) {
            return true;
        }
        final String string = "Do you want to " + OCFindUsagesHandler.access$000(OCFindUsagesHandler.this) + " the " + ocInstanceVariableSymbol.getNameWithKindLowercase() + " as well?";
        return ApplicationManager.getApplication().isUnitTestMode() || Messages.showYesNoDialog(string, "Instance variables search", Messages.getQuestionIcon()) == 0;
    }
    
    @Override
    public boolean proceedDerivedClasses() {
        return false;
    }
    
    public boolean process(final PsiElement psiElement) {
        this.val$result.add(psiElement);
        return true;
    }
}