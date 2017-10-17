// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import com.intellij.find.findUsages.AbstractFindUsagesDialog;
import org.jetbrains.annotations.NotNull;
import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.ui.StateRestoringCheckBox;
import com.intellij.find.findUsages.CommonFindUsagesDialog;

public class OCFindUsagesDialog extends CommonFindUsagesDialog
{
    private StateRestoringCheckBox myIvarUsagesCB;
    private StateRestoringCheckBox myPropertyUsagesCB;
    private StateRestoringCheckBox myDerivedClassUsagesCB;
    
    public OCFindUsagesDialog(final PsiElement psiElement, final Project project, final FindUsagesOptions findUsagesOptions, final boolean b, final boolean b2, final boolean b3, @NotNull final FindUsagesHandler findUsagesHandler) {
        if (findUsagesHandler == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesDialog", "<init>"));
        }
        super(psiElement, project, findUsagesOptions, b, b2, b3, findUsagesHandler);
        this.init();
    }
    
    @Override
    public void calcFindUsagesOptions(final FindUsagesOptions findUsagesOptions) {
        try {
            super.calcFindUsagesOptions(findUsagesOptions);
            if (AbstractFindUsagesDialog.isToChange(this.myIvarUsagesCB)) {
                ((OCFindUsagesOptions)findUsagesOptions).isSearchForIvars = AbstractFindUsagesDialog.isSelected(this.myIvarUsagesCB);
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (AbstractFindUsagesDialog.isToChange(this.myPropertyUsagesCB)) {
                ((OCFindUsagesOptions)findUsagesOptions).isSearchForProperties = AbstractFindUsagesDialog.isSelected(this.myPropertyUsagesCB);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (AbstractFindUsagesDialog.isToChange(this.myDerivedClassUsagesCB)) {
                ((OCFindUsagesOptions)findUsagesOptions).isSearchForDerivedClasses = AbstractFindUsagesDialog.isSelected(this.myDerivedClassUsagesCB);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
    }
    
    @Override
    protected void addUsagesOptions(final JPanel panel) {
        new OCFindUsagesHandler(this.myPsiElement).processSecondaryElements(new OCFindUsagesHandler.AssociatedElementProcessor() {
            final /* synthetic */ OCFindUsagesOptions val$options = OCFindUsagesOptions.getInstance(OCFindUsagesDialog.this.myPsiElement.getProject());
            
            @Override
            public boolean proceedProperty(final OCPropertySymbol ocPropertySymbol) {
                OCFindUsagesDialog.this.myPropertyUsagesCB = OCFindUsagesDialog.this.addCheckboxToPanel("Include " + ocPropertySymbol.getNameWithKindLowercase(), this.val$options.isSearchForProperties, panel, false);
                OCFindUsagesDialog.this.myPropertyUsagesCB.setMnemonic('p');
                return false;
            }
            
            @Override
            public boolean proceedIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
                OCFindUsagesDialog.this.myIvarUsagesCB = OCFindUsagesDialog.this.addCheckboxToPanel("Include " + ocInstanceVariableSymbol.getNameWithKindLowercase(), this.val$options.isSearchForIvars, panel, false);
                OCFindUsagesDialog.this.myIvarUsagesCB.setMnemonic('i');
                return false;
            }
            
            @Override
            public boolean proceedDerivedClasses() {
                OCFindUsagesDialog.this.myDerivedClassUsagesCB = OCFindUsagesDialog.this.addCheckboxToPanel("Include derived classes", this.val$options.isSearchForDerivedClasses, panel, false);
                OCFindUsagesDialog.this.myDerivedClassUsagesCB.setMnemonic('d');
                return false;
            }
            
            public boolean process(final PsiElement psiElement) {
                return false;
            }
        });
        super.addUsagesOptions(panel);
    }
    
    @Override
    protected void doOKAction() {
        final OCFindUsagesOptions instance = OCFindUsagesOptions.getInstance(this.myPsiElement.getProject());
        try {
            if (AbstractFindUsagesDialog.isToChange(this.myIvarUsagesCB)) {
                instance.isSearchForIvars = this.myIvarUsagesCB.isSelected();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (AbstractFindUsagesDialog.isToChange(this.myPropertyUsagesCB)) {
                instance.isSearchForProperties = this.myPropertyUsagesCB.isSelected();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (AbstractFindUsagesDialog.isToChange(this.myDerivedClassUsagesCB)) {
                instance.isSearchForDerivedClasses = this.myDerivedClassUsagesCB.isSelected();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        super.doOKAction();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
