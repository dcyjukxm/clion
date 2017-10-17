// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIvarInplaceIntroducer;

class OCConvertToIvarIntentionAction$1$1 extends OCIvarInplaceIntroducer {
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
        OCBaseLocalConvertibleHandler.this.this$0.performAction(OCBaseLocalConvertibleHandler.this.val$file, OCBaseLocalConvertibleHandler.this.val$context, OCBaseLocalConvertibleHandler.this.val$properties, this.getInputName(), this.myDeclareInInterfaceCb != null && !this.myDeclareInInterfaceCb.isSelected());
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
}