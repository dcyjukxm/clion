// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseInplaceIntroducer;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.refactoring.introduce.OCPropertyInplaceIntroducer;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateFromIvarsActionContext;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseLocalConvertibleHandler;

class OCConvertToPropertyIntentionAction$1 extends OCBaseLocalConvertibleHandler {
    final /* synthetic */ OCElement val$element;
    final /* synthetic */ SmartPsiElementPointer val$pointer;
    final /* synthetic */ PsiFile val$file;
    final /* synthetic */ OCGenerateFromIvarsActionContext val$context;
    final /* synthetic */ List val$ivars;
    final /* synthetic */ String val$propName;
    final /* synthetic */ OCInstanceVariableSymbol val$ivar;
    
    @Override
    protected OCPropertyInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        return new OCPropertyInplaceIntroducer(project, editor, psiElement, list, s) {
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
                OCConvertToPropertyIntentionAction.access$000(OCConvertToPropertyIntentionAction.this, OCBaseLocalConvertibleHandler.this.val$file, OCBaseLocalConvertibleHandler.this.val$context, OCBaseLocalConvertibleHandler.this.val$ivars, this.getInputName(), (OCPropertySymbol.PropertySemantics)this.mySemantics.compute(), this.myReadonly, this.isConvertUsages(), this.myPutToPrivateCategoryCB != null && this.myPutToPrivateCategoryCB.isSelected());
            }
            
            @Override
            protected boolean askToGenerateSynthesize() {
                return false;
            }
            
            @Override
            protected boolean askToConvertUsages() {
                return OCCompilerFeatures.supportsAutosynthesis(OCBaseLocalConvertibleHandler.this.val$file) && !(OCElementUtil.getAdjacentParentOfType(OCBaseLocalConvertibleHandler.this.val$file.findElementAt(editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class) instanceof OCQualifiedExpression);
            }
            
            @Override
            protected String[] suggestNames(final boolean b, @Nullable final OCDeclarator ocDeclarator) {
                if (!OCBaseLocalConvertibleHandler.this.val$propName.equals(OCBaseLocalConvertibleHandler.this.val$ivar.getName())) {
                    return new String[] { OCBaseLocalConvertibleHandler.this.val$propName, OCBaseLocalConvertibleHandler.this.val$ivar.getName() };
                }
                return new String[] { OCBaseLocalConvertibleHandler.this.val$propName };
            }
        };
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.convertToProperty";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction$1", "getFeatureID"));
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