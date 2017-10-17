// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import javax.swing.JPanel;

class OCFindUsagesDialog$1 implements OCFindUsagesHandler.AssociatedElementProcessor {
    final /* synthetic */ OCFindUsagesOptions val$options;
    final /* synthetic */ JPanel val$optionsPanel;
    
    @Override
    public boolean proceedProperty(final OCPropertySymbol ocPropertySymbol) {
        OCFindUsagesDialog.access$002(OCFindUsagesDialog.this, OCFindUsagesDialog.access$100(OCFindUsagesDialog.this, "Include " + ocPropertySymbol.getNameWithKindLowercase(), this.val$options.isSearchForProperties, this.val$optionsPanel, false));
        OCFindUsagesDialog.access$000(OCFindUsagesDialog.this).setMnemonic('p');
        return false;
    }
    
    @Override
    public boolean proceedIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        OCFindUsagesDialog.access$202(OCFindUsagesDialog.this, OCFindUsagesDialog.access$300(OCFindUsagesDialog.this, "Include " + ocInstanceVariableSymbol.getNameWithKindLowercase(), this.val$options.isSearchForIvars, this.val$optionsPanel, false));
        OCFindUsagesDialog.access$200(OCFindUsagesDialog.this).setMnemonic('i');
        return false;
    }
    
    @Override
    public boolean proceedDerivedClasses() {
        OCFindUsagesDialog.access$402(OCFindUsagesDialog.this, OCFindUsagesDialog.access$500(OCFindUsagesDialog.this, "Include derived classes", this.val$options.isSearchForDerivedClasses, this.val$optionsPanel, false));
        OCFindUsagesDialog.access$400(OCFindUsagesDialog.this).setMnemonic('d');
        return false;
    }
    
    public boolean process(final PsiElement psiElement) {
        return false;
    }
}