// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.daemon.OCConstantExpressionVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import javax.swing.JCheckBox;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCConstantInplaceIntroducer extends OCBaseExpressionInplaceIntroducer<OCDeclarator, OCExpression>
{
    private JCheckBox myStaticCB;
    
    public OCConstantInplaceIntroducer(final Project project, final Editor editor, final OCExpression ocExpression, final List<OCExpression> list, final String s) {
        super(project, editor, (PsiElement)ocExpression, (List<PsiElement>)list, OCDeclarator.class, (Class<PsiElement>)OCExpression.class, s);
    }
    
    @Override
    public void configurePanel() {
        super.configurePanel();
        final OCCodeStyleSettings ocCodeStyleSettings;
        this.myStaticCB = (JCheckBox)this.createCheckBox("Declare &static", ((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class)).INTRODUCE_STATIC_CONSTS, () -> {
            ocCodeStyleSettings.INTRODUCE_STATIC_CONSTS = this.myStaticCB.isSelected();
            this.g();
        });
    }
    
    private void g() {
        final OCDeclarator ocDeclarator = ((OCBaseInplaceIntroducer<OCDeclarator, E>)this).getVariable();
        if (ocDeclarator != null) {
            OCChangeUtil.replaceHandlingMacros(ocDeclarator.getParent(), (PsiElement)this.a(ocDeclarator.getName(), ocDeclarator.getInitializer()));
        }
    }
    
    @Override
    protected OCSymbolKind getDeclaratorKind() {
        return OCSymbolKind.GLOBAL_VARIABLE;
    }
    
    @Nullable
    @Override
    protected String checkExpression(final OCExpression ocExpression) {
        final String checkType = this.checkType();
        try {
            if (checkType != null) {
                return checkType;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (!new OCConstantExpressionVisitor() {
                @Override
                protected boolean isConstDeclarator(final OCReferenceExpression ocReferenceExpression, final OCSymbol ocSymbol) {
                    return super.isConstDeclarator(ocReferenceExpression, ocSymbol) && ocSymbol.isGlobal();
                }
            }.isConstant(ocExpression)) {
                return "Selected expression cannot be a constant initializer";
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Override
    public PsiElement evaluateAnchor() {
        final OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType(this.getCommonContext(), false, new Class[] { OCCppNamespace.class, OCFile.class });
        try {
            if (ocElement != null) {
                return this.findAnchor((PsiElement)ocElement);
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return null;
    }
    
    private OCDeclaration a(final String s, final OCExpression ocExpression) {
        final ArrayList<String> list = new ArrayList<String>();
        try {
            if (this.myStaticCB.isSelected()) {
                list.add("static");
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        String s2 = OCElementFactory.declarationFromText(OCElementFactory.declarationText(list, s, this.myExprType.cloneWithConstModifier(this.myProject), "0", (PsiElement)ocExpression, null, false), (PsiElement)ocExpression, true).getTextWithMacros();
        if (s2.endsWith("0;")) {
            s2 = s2.substring(0, s2.length() - 2) + ocExpression.getTextWithMacros() + ";";
        }
        else {
            OCConstantInplaceIntroducer.LOG.error(s2);
        }
        return OCElementFactory.declarationFromText(s2, (PsiElement)ocExpression);
    }
    
    private OCDeclaration a(final String s, final boolean b) {
        final OCExpression ocExpression = ((OCBaseInplaceIntroducer<V, OCExpression>)this).getMainExpression();
        final PsiElement anchor = this.getAnchor();
        final OCDeclaration ocDeclaration = OCChangeUtil.addBefore(this.getAnchorParent(anchor, b), this.a(s, ocExpression), anchor);
        try {
            if (b) {
                ((OCBaseInplaceIntroducer<OCDeclarator, E>)this).setVariable(ocDeclaration.getDeclarators().get(0));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return ocDeclaration;
    }
    
    @Override
    protected void introduceForPreview(final String s) {
        this.a(s, true);
    }
    
    @Override
    protected void introduceForReal(final String s) {
        OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)this.a(s, false));
        for (final OCExpression ocExpression : (OCExpression[])this.myOccurrences) {
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocExpression, (PsiElement)OCElementFactory.expressionFromText(s, (PsiElement)ocExpression));
        }
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceConstant";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCConstantInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    public PsiElement getPreviewElement(final OCDeclarator ocDeclarator) {
        return ocDeclarator.getParent();
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
