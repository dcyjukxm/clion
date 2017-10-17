// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.Iterator;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import java.util.ArrayList;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;

public abstract class OCBaseLocalConvertibleHandler extends OCBaseIntroduceHandler<PsiElement>
{
    protected OCBaseLocalConvertibleHandler(final String s) {
        super("an expression or a local declarator", s, (Class[])new Class[] { OCExpression.class, OCDeclarator.class });
    }
    
    @Override
    protected PsiElement substituteElement(final PsiElement psiElement) {
        if (psiElement instanceof OCReferenceExpression) {
            final OCSymbol resolveToSymbol = ((OCReferenceExpression)psiElement).resolveToSymbol();
            if (resolveToSymbol != null && resolveToSymbol.getKind() == OCSymbolKind.LOCAL_VARIABLE) {
                final PsiElement locateDefinition = resolveToSymbol.locateDefinition();
                return (locateDefinition instanceof OCDeclarator) ? ((OCDeclarator)locateDefinition).getNameIdentifier() : null;
            }
            return super.substituteElement(psiElement);
        }
        else {
            if (!(psiElement instanceof OCDeclarator)) {
                return super.substituteElement(psiElement);
            }
            final OCSymbol symbol = ((OCDeclarator)psiElement).getSymbol();
            if (symbol != null && symbol.getKind() == OCSymbolKind.LOCAL_VARIABLE) {
                return ((OCDeclarator)psiElement).getNameIdentifier();
            }
            return null;
        }
    }
    
    @Override
    protected List<PsiElement> findElementOccurrences(final PsiElement psiElement) {
        if (isDeclaratorIdentifier(psiElement)) {
            final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
            list.add(((OCDeclarator)psiElement.getParent()).getNameIdentifier());
            final Iterator<PsiReference> iterator = ReferencesSearch.search(psiElement.getParent(), psiElement.getUseScope()).findAll().iterator();
            while (iterator.hasNext()) {
                final PsiElement parentOfType = PsiTreeUtil.getParentOfType(iterator.next().getElement(), (Class)OCExpression.class, false);
                if (parentOfType != null) {
                    list.add(parentOfType);
                }
            }
            return list;
        }
        return super.findElementOccurrences(psiElement);
    }
    
    @Override
    protected boolean filterUsages(final PsiElement psiElement) {
        return super.filterUsages(psiElement) && !isDeclaratorIdentifier(psiElement);
    }
    
    static boolean isDeclaratorIdentifier(final PsiElement psiElement) {
        return OCElementUtil.getElementType(psiElement) == OCTokenTypes.IDENTIFIER && psiElement.getParent() instanceof OCDeclarator;
    }
}
