// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCInlineConstHandler extends OCInlineActionHandlerBase<OCDeclarator>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Override
    protected String getElementKind(final OCDeclarator ocDeclarator) {
        return "const";
    }
    
    public boolean canInlineElement(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof OCDeclarator)) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = ((OCDeclarator)psiElement).getSymbol();
        Label_0049: {
            try {
                if (!(symbol instanceof OCDeclaratorSymbol)) {
                    return false;
                }
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final boolean b = ocSymbolKind.isGlobalVariable();
                if (b) {
                    break Label_0049;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final boolean b = ocSymbolKind.isGlobalVariable();
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Override
    protected String checkValidness(final OCDeclarator ocDeclarator, final List<PsiElement> list, final PsiElement psiElement, final String s, final Editor editor, final Ref<PsiElement> ref, final List<String> list2, final boolean b) {
        try {
            if (!ocDeclarator.getSymbol().isConst()) {
                return "Variable \"" + ocDeclarator.getName() + "\" must be const";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocDeclarator.getInitializer() == null) {
                return StringUtil.capitalize(s) + " must have an initializer";
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return super.checkValidness(ocDeclarator, list, psiElement, s, editor, ref, list2, b);
    }
    
    @Override
    protected void inlineUsage(final PsiElement psiElement, final OCDeclarator ocDeclarator, final PsiElement psiElement2, final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        try {
            if (!(psiElement instanceof OCReferenceElement)) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCExpression initializer = ocDeclarator.getInitializer();
        final PsiElement parent = psiElement.getParent();
        Label_0058: {
            Label_0046: {
                try {
                    if (OCInlineConstHandler.$assertionsDisabled) {
                        break Label_0058;
                    }
                    final OCExpression ocExpression = initializer;
                    if (ocExpression == null) {
                        break Label_0046;
                    }
                    break Label_0058;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCExpression ocExpression = initializer;
                    if (ocExpression == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (!(parent instanceof OCReferenceExpression)) {
                    return;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        final OCExpression ocExpression2 = OCCodeInsightUtil.findElementAtRange((PsiFile)initializer.getContainingFile().copy(), initializer.getTextRange(), initializer.getClass(), true);
        if (ocExpression2 != null) {
            OCBindUtil.encodeContextInfo((PsiElement)ocExpression2, false);
            OCBindUtil.decodeContextInfo(OCParenthesesUtils.replaceExpressionAndAppendParentheses((OCExpression)parent, ocExpression2), null, map);
        }
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.inlineConst";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineConstHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInlineConstHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
