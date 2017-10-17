// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseInplaceIntroducer;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIvarInplaceIntroducer;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.List;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateIvarsActionContext;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseLocalConvertibleHandler;

class OCConvertToIvarIntentionAction$1 extends OCBaseLocalConvertibleHandler {
    final /* synthetic */ OCElement val$element;
    final /* synthetic */ SmartPsiElementPointer val$pointer;
    final /* synthetic */ PsiFile val$file;
    final /* synthetic */ OCGenerateIvarsActionContext val$context;
    final /* synthetic */ List val$properties;
    final /* synthetic */ String val$ivarName;
    final /* synthetic */ OCPropertySymbol val$property;
    
    @Override
    protected OCIvarInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        return new OCIvarInplaceIntroducer(project, editor, psiElement, list, s) {
            @Override
            public PsiElement evaluateAnchor() {
                return (PsiElement)OCBaseLocalConvertibleHandler.this.val$element;
            }
            
            @Override
            public PsiElement[] getOccurrences() {
                final PsiElement element = OCBaseLocalConvertibleHandler.this.val$pointer.getElement();
                final PsiElement[] array;
                if (element != null) {
                    array = new PsiElement[] { element };
                }
                else {
                    final PsiElement[] empty_ARRAY = PsiElement.EMPTY_ARRAY;
                }
                return array;
            }
            
            @Override
            protected void performIntroduce() {
                OCConvertToIvarIntentionAction.this.performAction(OCBaseLocalConvertibleHandler.this.val$file, OCBaseLocalConvertibleHandler.this.val$context, OCBaseLocalConvertibleHandler.this.val$properties, this.getInputName(), this.myDeclareInInterfaceCb != null && !this.myDeclareInInterfaceCb.isSelected());
            }
            
            @Override
            protected boolean askToGenerateProperty() {
                return false;
            }
            
            @Override
            protected String[] suggestNames(final boolean b, @Nullable final OCDeclarator ocDeclarator) {
                if (!OCBaseLocalConvertibleHandler.this.val$ivarName.equals(OCBaseLocalConvertibleHandler.this.val$property.getName())) {
                    return new String[] { OCBaseLocalConvertibleHandler.this.val$ivarName, OCBaseLocalConvertibleHandler.this.val$property.getName() };
                }
                return new String[] { OCBaseLocalConvertibleHandler.this.val$ivarName };
            }
        };
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.convertToIvar";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction$1", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}