// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import java.util.function.Consumer;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

private static class GlobalVisitor extends OCVisitor
{
    static final String AUTO_REG = "AutoReg";
    static final String NAME_AND_DESC = "NameAndDesc";
    protected final Consumer<CidrCatchTestCache> myDeclaratorConsumer;
    
    public GlobalVisitor(final Consumer<CidrCatchTestCache> myDeclaratorConsumer) {
        this.myDeclaratorConsumer = myDeclaratorConsumer;
    }
    
    public void visitElement(final PsiElement psiElement) {
        super.visitElement(psiElement);
        if (psiElement instanceof OCFile || psiElement instanceof OCCppNamespace) {
            for (PsiElement psiElement2 = psiElement.getFirstChild(); psiElement2 != null; psiElement2 = psiElement2.getNextSibling()) {
                psiElement2.accept((PsiElementVisitor)this);
            }
        }
    }
    
    @Override
    public void visitDeclaration(final OCDeclaration ocDeclaration) {
        if ("AutoReg".equals(ocDeclaration.getType().getName())) {
            final OCArgumentList list = (OCArgumentList)PsiTreeUtil.findChildOfType((PsiElement)ocDeclaration, (Class)OCArgumentList.class);
            if (list != null) {
                list.accept((PsiElementVisitor)this.createArgumentVisiter(ocDeclaration));
            }
        }
    }
    
    protected OCRecursiveVisitor createArgumentVisiter(final OCDeclaration ocDeclaration) {
        return new BaseArgVisitor(ocDeclaration, this.myDeclaratorConsumer);
    }
    
    static class BaseArgVisitor extends OCRecursiveVisitor
    {
        private OCDeclaration myDeclaration;
        private Consumer<CidrCatchTestCache> myDeclaratorConsumer;
        
        public BaseArgVisitor(final OCDeclaration myDeclaration, final Consumer<CidrCatchTestCache> myDeclaratorConsumer) {
            this.myDeclaration = myDeclaration;
            this.myDeclaratorConsumer = myDeclaratorConsumer;
        }
        
        @Override
        public void visitCallExpression(final OCCallExpression ocCallExpression) {
            final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocCallExpression.getFunctionReferenceExpression());
            if (symbol != null && "NameAndDesc".equals(symbol.getName())) {
                final List<OCExpression> arguments = ocCallExpression.getArgumentList().getArguments();
                if (arguments.size() >= 1) {
                    final OCExpression ocExpression = arguments.get(0);
                    final OCExpression ocExpression2 = (arguments.size() > 1) ? arguments.get(1) : null;
                    final List<OCDeclarator> declarators = this.myDeclaration.getDeclarators();
                    if (ocExpression instanceof OCLiteralExpression && (ocExpression2 == null || ocExpression2 instanceof OCLiteralExpression) && declarators.size() == 1) {
                        this.myDeclaratorConsumer.accept(new CidrCatchTestCache(((OCLiteralExpression)ocExpression).getUnescapedLiteralText(), (ocExpression2 == null) ? "" : ((OCLiteralExpression)ocExpression2).getUnescapedLiteralText(), declarators.get(0)));
                    }
                }
            }
        }
    }
}
