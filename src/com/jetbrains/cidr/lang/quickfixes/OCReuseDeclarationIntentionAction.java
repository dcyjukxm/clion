// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCReuseDeclarationIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private String myIntentionName;
    
    public OCReuseDeclarationIntentionAction(final OCSymbol ocSymbol, final String myIntentionName) {
        super(ocSymbol);
        this.myIntentionName = myIntentionName;
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return this.myIntentionName;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Reuse declaration";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCReuseDeclarationIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        Label_0026: {
            try {
                if (!OCSearchScope.isInProjectSources(ocSymbol)) {
                    return false;
                }
                final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                final PsiElement psiElement = ocSymbol2.locateDefinition();
                final boolean b = OCCodeInsightUtil.isValid(psiElement);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                final PsiElement psiElement = ocSymbol2.locateDefinition();
                final boolean b = OCCodeInsightUtil.isValid(psiElement);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected void invoke(final OCSymbol ocSymbol) {
        final OCDeclarator ocDeclarator = ocSymbol.locateDefinition();
        try {
            if (ocDeclarator == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0209: {
            OCExpressionStatement ocExpressionStatement = null;
            Label_0132: {
                Label_0087: {
                    try {
                        if (ocDeclarator.getInitializer() == null) {
                            break Label_0209;
                        }
                        if (!OCElementUtil.getTextWithMacros(ocDeclarator.getParent()).endsWith(";")) {
                            break Label_0087;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    ocExpressionStatement = (OCExpressionStatement)OCElementFactory.statementFromText(ocDeclarator.getName() + "=b;", (PsiElement)ocDeclarator);
                    break Label_0132;
                }
                ocExpressionStatement = (OCExpressionStatement)((OCForStatement)OCElementFactory.statementFromText("for(" + ocDeclarator.getName() + "=b;;);", (PsiElement)ocDeclarator)).getInitializer();
            }
            OCChangeUtil.replaceHandlingMacros((PsiElement)((OCAssignmentExpression)ocExpressionStatement.getExpression()).getSourceExpression(), (PsiElement)ocDeclarator.getInitializer());
            final PsiElement parent = ((OCDeclaration)ocDeclarator.getParent()).getParent();
            try {
                if (!(parent instanceof OCDeclarationStatement)) {
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            parent.getParent().addAfter((PsiElement)ocExpressionStatement, parent);
        }
        if (ocDeclarator.getParent().getParent() instanceof OCForeachStatement) {
            OCChangeUtil.replaceHandlingMacros(ocDeclarator.getParent(), (PsiElement)OCElementFactory.expressionFromText(ocDeclarator.getName(), (PsiElement)ocDeclarator));
        }
        else {
            OCChangeUtil.delete((PsiElement)ocDeclarator);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
