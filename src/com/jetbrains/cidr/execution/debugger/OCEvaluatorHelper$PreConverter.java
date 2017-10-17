// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;

private static class PreConverter extends MyVisitor
{
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
        final OCSymbolKind ocSymbolKind = (resolveToSymbol == null) ? null : resolveToSymbol.getKind();
        if (ocSymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
            final String name = resolveToSymbol.getName();
            if (!"self".equals(name) && this.replaceAndVisit(ocReferenceExpression, "self->" + name)) {
                return;
            }
        }
        else if (ocSymbolKind == OCSymbolKind.ENUM_CONST) {
            final Integer evaluateEnumConst = OCExpressionEvaluator.evaluateEnumConst(resolveToSymbol, ocReferenceExpression.getContainingFile());
            if (MyVisitor.replace(ocReferenceExpression, "((int)" + ((evaluateEnumConst == null) ? ocReferenceExpression.getText() : String.valueOf(evaluateEnumConst)) + ")") != null) {
                return;
            }
        }
        super.visitReferenceExpression(ocReferenceExpression);
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        final Prop a = a(ocQualifiedExpression);
        if (a != null && a.getter != null && this.replaceAndVisit(ocQualifiedExpression, "[" + a.receiver + " " + a.getter + "]")) {
            return;
        }
        super.visitQualifiedExpression(ocQualifiedExpression);
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        final Prop a = a(ocAssignmentExpression.getReceiverExpression());
        final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
        if (a != null && a.isReadWrite() && sourceExpression != null) {
            String s = sourceExpression.getText();
            final String name = ocAssignmentExpression.getOperationSign().getName();
            if (name.endsWith("=") && name.length() == 2) {
                s = "([" + a.receiver + " " + a.getter + "]" + name.substring(0, 1) + s + ")";
            }
            if (this.replaceAndVisit(ocAssignmentExpression, "[" + a.receiver + " " + a.setter + " " + s + "]")) {
                return;
            }
        }
        super.visitAssignmentExpression(ocAssignmentExpression);
    }
    
    @Override
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        if (this.a(ocPrefixExpression, true)) {
            return;
        }
        super.visitPrefixExpression(ocPrefixExpression);
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        if (this.a(ocPostfixExpression, false)) {
            return;
        }
        super.visitPostfixExpression(ocPostfixExpression);
    }
    
    private boolean a(final OCExpression ocExpression, final boolean b) {
        final Prop a = a(b ? ((OCPrefixExpression)ocExpression).getOperand() : ((OCPostfixExpression)ocExpression).getOperand());
        return a != null && a.isReadWrite() && this.replaceAndVisit(ocExpression, "[" + a.receiver + " " + a.setter + " " + ("[" + a.receiver + " " + a.getter + "]" + ((b ? ((OCPrefixExpression)ocExpression).getOperationSign() : ((OCPostfixExpression)ocExpression).getOperationSign()).getName().equals("--") ? "-1" : "+1")) + "]");
    }
    
    @Nullable
    private static Prop a(@Nullable final OCExpression ocExpression) {
        if (ocExpression instanceof OCQualifiedExpression) {
            final OCSymbol resolveToSymbol = ((OCQualifiedExpression)ocExpression).resolveToSymbol();
            final String text = ((OCQualifiedExpression)ocExpression).getQualifier().getText();
            if (resolveToSymbol instanceof OCPropertySymbol) {
                return new Prop(text, ((OCPropertySymbol)resolveToSymbol).getGetterName(), ((OCPropertySymbol)resolveToSymbol).getSetterName());
            }
            if (resolveToSymbol instanceof OCMethodSymbol) {
                final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)resolveToSymbol;
                final String name = resolveToSymbol.getName();
                return new Prop(text, ocMethodSymbol.isGetter() ? name : OCNameSuggester.getObjCGetterFromSetter(name), ocMethodSymbol.isSetter() ? name : OCNameSuggester.getObjCSetterFromGetter(name));
            }
        }
        return null;
    }
    
    private static class Prop
    {
        @NotNull
        public final String receiver;
        @Nullable
        public final String getter;
        @Nullable
        public final String setter;
        
        private Prop(@NotNull final String receiver, @Nullable final String getter, @Nullable final String setter) {
            if (receiver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "receiver", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$PreConverter$Prop", "<init>"));
            }
            this.receiver = receiver;
            this.getter = getter;
            this.setter = setter;
        }
        
        public boolean isReadWrite() {
            Label_0021: {
                try {
                    if (this.getter == null) {
                        return false;
                    }
                    final Prop prop = this;
                    final String s = prop.setter;
                    if (s != null) {
                        break Label_0021;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final Prop prop = this;
                    final String s = prop.setter;
                    if (s != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
