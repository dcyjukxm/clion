// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.intellij.psi.PsiElement;
import java.util.Comparator;

class OCKindSorter$1 implements Comparator<OCStructureViewElement> {
    @Override
    public int compare(final OCStructureViewElement ocStructureViewElement, final OCStructureViewElement ocStructureViewElement2) {
        return Integer.compare(this.a(ocStructureViewElement.getValue()), this.a(ocStructureViewElement2.getValue()));
    }
    
    private int a(final PsiElement psiElement) {
        if (psiElement instanceof OCCppNamespace) {
            return 0;
        }
        if (psiElement instanceof OCStructLike) {
            return 1;
        }
        if (psiElement instanceof OCClassDeclaration) {
            return 2;
        }
        if (psiElement instanceof OCFunctionDefinition) {
            final OCFunctionSymbol symbol = ((OCFunctionDefinition)psiElement).getSymbol();
            if (symbol != null) {
                if (symbol.isCppConstructor()) {
                    return 3;
                }
                if (symbol.isCppDestructor()) {
                    return 4;
                }
            }
            if (psiElement.getParent() instanceof OCClassDeclaration) {
                return 9;
            }
            return 5;
        }
        else if (psiElement instanceof OCMethod) {
            final OCMethodSymbol ocMethodSymbol = ((OCMethod)psiElement).getSymbol();
            if (ocMethodSymbol != null && ocMethodSymbol.isConstructorMethod()) {
                return 3;
            }
            return 5;
        }
        else {
            if (psiElement instanceof OCDeclarator) {
                final OCSymbol symbol2 = ((OCDeclarator)psiElement).getSymbol();
                if (symbol2 != null) {
                    switch (symbol2.getKind()) {
                        case CPP_CONSTRUCTOR_DECLARATION:
                        case CPP_CONSTRUCTOR_PREDECLARATION: {
                            return 3;
                        }
                        case FUNCTION_DECLARATION:
                        case FUNCTION_PREDECLARATION: {
                            return 5;
                        }
                        case PROPERTY: {
                            return 6;
                        }
                        case INSTANCE_VARIABLE:
                        case STRUCT_FIELD: {
                            return 7;
                        }
                        case TYPEDEF: {
                            return 8;
                        }
                        case GLOBAL_VARIABLE:
                        case GLOBAL_VARIABLE_PREDECLARATION: {
                            return 10;
                        }
                    }
                }
                return 11;
            }
            if (psiElement instanceof OCDirective) {
                return 12;
            }
            return Integer.MAX_VALUE;
        }
    }
}