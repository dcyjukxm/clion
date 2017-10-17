// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCProtocolExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;

private static class PostConverter extends MyVisitor
{
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
        final OCSymbolKind ocSymbolKind = (resolveToSymbol == null) ? null : resolveToSymbol.getKind();
        if (ocSymbolKind == OCSymbolKind.INTERFACE || ocSymbolKind == OCSymbolKind.IMPLEMENTATION) {
            if (this.a(ocReferenceExpression, resolveToSymbol.getName())) {
                return;
            }
        }
        else if (ocSymbolKind == OCSymbolKind.PROTOCOL && this.a((OCExpression)ocReferenceExpression, resolveToSymbol.getName())) {
            return;
        }
        super.visitReferenceExpression(ocReferenceExpression);
    }
    
    @Override
    public void visitProtocolExpression(final OCProtocolExpression ocProtocolExpression) {
        final OCTypeElement typeElement = ocProtocolExpression.getTypeElement();
        if (typeElement != null && this.a(ocProtocolExpression, typeElement.getText())) {
            return;
        }
        super.visitProtocolExpression(ocProtocolExpression);
    }
    
    private boolean a(final OCReferenceExpression ocReferenceExpression, final String s) {
        return this.replaceAndVisit(ocReferenceExpression, "(id)NSClassFromString(@\"" + s + "\")");
    }
    
    private boolean a(final OCExpression ocExpression, final String s) {
        return this.replaceAndVisit(ocExpression, "(id)NSProtocolFromString(@\"" + s + "\")");
    }
}
