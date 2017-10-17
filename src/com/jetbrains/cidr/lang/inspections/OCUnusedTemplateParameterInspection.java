// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;

public class OCUnusedTemplateParameterInspection extends OCUnusedCodeInspection
{
    @NotNull
    @Override
    public UnusedVisitor buildVisitor() {
        UnusedVisitor unusedVisitor;
        try {
            unusedVisitor = new UnusedVisitor() {
                @Override
                public void visitTypeParameterDeclaration(final OCTypeParameterDeclaration ocTypeParameterDeclaration) {
                    if (!OCUnusedTemplateParameterInspection.isTraitTemplateParameter((OCTemplateParameterList)ocTypeParameterDeclaration.getParent())) {
                        this.checkSymbolUsed((PsiElement)ocTypeParameterDeclaration, this.getSymbol(ocTypeParameterDeclaration));
                    }
                }
            };
            if (unusedVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedTemplateParameterInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return unusedVisitor;
    }
    
    public static boolean isTraitTemplateParameter(final OCTemplateParameterList list) {
        final PsiElement parent = list.getParent();
        OCSymbolWithQualifiedName ocSymbolWithQualifiedName = null;
        if (parent instanceof OCFunctionDeclaration) {
            ocSymbolWithQualifiedName = ((OCFunctionDeclaration)parent).getSymbol();
        }
        else if (parent instanceof OCDeclaration) {
            final OCTypeElement typeElement = ((OCFunctionDeclaration)parent).getTypeElement();
            OCType resolve = null;
            Label_0076: {
                try {
                    if (typeElement != null) {
                        resolve = typeElement.getType().resolve(list.getContainingFile());
                        break Label_0076;
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                resolve = null;
            }
            final OCStructType ocStructType = (OCStructType)resolve;
            OCSymbolWithQualifiedName symbol = null;
            Label_0102: {
                try {
                    if (ocStructType instanceof OCStructType) {
                        symbol = ocStructType.getSymbol();
                        break Label_0102;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw c(ex2);
                }
                symbol = null;
            }
            ocSymbolWithQualifiedName = symbol;
        }
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
        Label_0131: {
            try {
                if (ocSymbolWithQualifiedName == null) {
                    return false;
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName3 = ocSymbolWithQualifiedName;
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName4 = ocSymbolWithQualifiedName2;
                final CommonProcessors.FindProcessor<OCSymbol> findProcessor = new CommonProcessors.FindProcessor<OCSymbol>() {
                    final /* synthetic */ OCSymbolWithQualifiedName val$finalParentSymbol;
                    
                    protected boolean accept(final OCSymbol ocSymbol) {
                        return ocSymbol != this.val$finalParentSymbol;
                    }
                };
                final boolean b = ocSymbolWithQualifiedName3.processSameSymbols((Processor)findProcessor);
                if (!b) {
                    break Label_0131;
                }
                return false;
            }
            catch (IllegalStateException ex3) {
                throw c(ex3);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName3 = ocSymbolWithQualifiedName;
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName4 = ocSymbolWithQualifiedName2;
                final CommonProcessors.FindProcessor<OCSymbol> findProcessor = new CommonProcessors.FindProcessor<OCSymbol>() {
                    final /* synthetic */ OCSymbolWithQualifiedName val$finalParentSymbol;
                    
                    protected boolean accept(final OCSymbol ocSymbol) {
                        return ocSymbol != ocSymbolWithQualifiedName4;
                    }
                };
                final boolean b = ocSymbolWithQualifiedName3.processSameSymbols((Processor)findProcessor);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalStateException ex4) {
                throw c(ex4);
            }
        }
        return false;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
