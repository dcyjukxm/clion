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
import java.util.function.Consumer;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

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
