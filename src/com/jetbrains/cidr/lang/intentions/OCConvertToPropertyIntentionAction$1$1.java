// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.refactoring.introduce.OCPropertyInplaceIntroducer;

class OCConvertToPropertyIntentionAction$1$1 extends OCPropertyInplaceIntroducer {
    final /* synthetic */ Editor val$editor;
    
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
        OCConvertToPropertyIntentionAction.access$000(OCBaseLocalConvertibleHandler.this.this$0, OCBaseLocalConvertibleHandler.this.val$file, OCBaseLocalConvertibleHandler.this.val$context, OCBaseLocalConvertibleHandler.this.val$ivars, this.getInputName(), (OCPropertySymbol.PropertySemantics)this.mySemantics.compute(), this.myReadonly, this.isConvertUsages(), this.myPutToPrivateCategoryCB != null && this.myPutToPrivateCategoryCB.isSelected());
    }
    
    @Override
    protected boolean askToGenerateSynthesize() {
        return false;
    }
    
    @Override
    protected boolean askToConvertUsages() {
        return OCCompilerFeatures.supportsAutosynthesis(OCBaseLocalConvertibleHandler.this.val$file) && !(OCElementUtil.getAdjacentParentOfType(OCBaseLocalConvertibleHandler.this.val$file.findElementAt(this.val$editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class) instanceof OCQualifiedExpression);
    }
    
    @Override
    protected String[] suggestNames(final boolean b, @Nullable final OCDeclarator ocDeclarator) {
        if (!OCBaseLocalConvertibleHandler.this.val$propName.equals(OCBaseLocalConvertibleHandler.this.val$ivar.getName())) {
            return new String[] { OCBaseLocalConvertibleHandler.this.val$propName, OCBaseLocalConvertibleHandler.this.val$ivar.getName() };
        }
        return new String[] { OCBaseLocalConvertibleHandler.this.val$propName };
    }
}