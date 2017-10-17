// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.HashSet;
import com.jetbrains.cidr.lang.psi.OCDesignatedInitializer;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import java.util.List;
import com.intellij.psi.PsiElement;

private static class ChildTypeInference
{
    private final PsiElement myTarget;
    private final List<PsiElement> myChildren;
    private final Map<PsiElement, Pair<OCType, OCSymbol>> myAcc;
    private final PsiFile myFile;
    private final int myChildrenSize;
    int myChildCounter;
    
    private ChildTypeInference(final List<PsiElement> myChildren, final PsiElement myTarget) {
        this.myChildren = myChildren;
        this.myChildrenSize = this.myChildren.size();
        this.myChildCounter = 0;
        this.myTarget = myTarget;
        this.myAcc = null;
        this.myFile = this.myTarget.getContainingFile();
    }
    
    public ChildTypeInference(final List<PsiElement> myChildren, final Map<PsiElement, Pair<OCType, OCSymbol>> myAcc) {
        this.myChildren = myChildren;
        this.myChildrenSize = this.myChildren.size();
        this.myChildCounter = 0;
        this.myTarget = null;
        this.myAcc = myAcc;
        this.myFile = (this.myChildren.isEmpty() ? null : this.myChildren.get(0).getContainingFile());
    }
    
    @Nullable
    private OCType a(final OCStructType ocStructType, final Set<OCSymbol> set) {
        final List<OCDeclaratorSymbol> fields = ocStructType.getFields();
        final int size = fields.size();
        int n = 0;
        while (n < size && this.myChildCounter < this.myChildrenSize) {
            final OCDeclaratorSymbol ocDeclaratorSymbol = fields.get(n);
            final PsiElement psiElement = this.myChildren.get(this.myChildCounter);
            if (ocDeclaratorSymbol.getKind() != OCSymbolKind.STRUCT_FIELD || set.contains(ocDeclaratorSymbol) || (ocDeclaratorSymbol.isUnnamed() && !(psiElement instanceof OCCompoundInitializer))) {
                ++n;
            }
            else if (psiElement instanceof OCDesignatedInitializer) {
                if (this.myAcc != null) {
                    this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocDeclaratorSymbol.getType(), (Object)ocDeclaratorSymbol));
                }
                if (psiElement == this.myTarget) {
                    return ocDeclaratorSymbol.getType();
                }
                set.add(((OCDesignatedInitializer)psiElement).getDesignation().resolveToSymbol());
                ++this.myChildCounter;
            }
            else if (psiElement instanceof OCCompoundInitializer) {
                if (this.myAcc != null) {
                    this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocDeclaratorSymbol.getType(), (Object)ocDeclaratorSymbol));
                    ((OCCompoundInitializer)psiElement).inferChildTypes(this.myAcc, ocDeclaratorSymbol.isUnnamed() ? set : new HashSet<OCSymbol>());
                }
                if (psiElement == this.myTarget) {
                    return ocDeclaratorSymbol.getType();
                }
                set.add(ocDeclaratorSymbol);
                ++n;
                ++this.myChildCounter;
            }
            else {
                boolean b = false;
                final OCType resolve = ocDeclaratorSymbol.getType().resolve(psiElement.getContainingFile());
                if (psiElement instanceof OCExpression) {
                    final OCCompoundInitializer ocCompoundInitializer = (OCCompoundInitializer)psiElement;
                    if (resolve.isCompatible(ocCompoundInitializer.getResolvedType(), (PsiElement)ocCompoundInitializer.getContainingOCFile())) {
                        b = true;
                    }
                }
                if (!b && resolve instanceof OCStructType && ((OCStructType)resolve).getKind() == OCSymbolKind.STRUCT) {
                    final OCType a = this.a((OCStructType)resolve, new HashSet<OCSymbol>());
                    if (a != null) {
                        return a;
                    }
                    set.add(ocDeclaratorSymbol);
                    ++n;
                }
                else if (!b && resolve instanceof OCArrayType) {
                    final OCType a2 = this.a((OCArrayType)resolve, ocDeclaratorSymbol);
                    if (a2 != null) {
                        return a2;
                    }
                    set.add(ocDeclaratorSymbol);
                    ++n;
                }
                else {
                    if (this.myAcc != null) {
                        this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocDeclaratorSymbol.getType(), (Object)ocDeclaratorSymbol));
                    }
                    if (psiElement == this.myTarget) {
                        return ocDeclaratorSymbol.getType();
                    }
                    set.add(ocDeclaratorSymbol);
                    ++n;
                    ++this.myChildCounter;
                }
            }
        }
        return null;
    }
    
    @Nullable
    private OCType a(final OCArrayType ocArrayType, @Nullable final OCSymbol ocSymbol) {
        OCType ocType = ocArrayType.getRefType();
        if (!this.myChildren.isEmpty()) {
            ocType = ocType.resolve(this.myChildren.get(0).getContainingFile());
        }
        int n = 0;
        final boolean b = (ocType instanceof OCStructType && ((OCStructType)ocType).isEmpty(new HashSet<OCStructSymbol>())) || (ocType instanceof OCArrayType && ((OCArrayType)ocType).isEmpty(this.myFile, new HashSet<OCStructSymbol>()));
        final int length = ocArrayType.getLength();
        while ((!ocArrayType.hasLength() || n < length) && this.myChildCounter < this.myChildrenSize) {
            final PsiElement psiElement = this.myChildren.get(this.myChildCounter);
            boolean b2 = false;
            if (psiElement instanceof OCDesignatedInitializer || psiElement instanceof OCCompoundInitializer) {
                b2 = true;
            }
            else if (psiElement instanceof OCExpression && ocType.isCompatible(((OCExpression)psiElement).getResolvedType(), psiElement)) {
                b2 = true;
            }
            if (b2) {
                if (this.myAcc != null) {
                    this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocArrayType.getRefType(), (Object)null));
                }
                if (psiElement == this.myTarget) {
                    if (psiElement instanceof OCExpression && b) {
                        break;
                    }
                    return ocArrayType.getRefType();
                }
                else {
                    ++this.myChildCounter;
                    ++n;
                }
            }
            else if (ocType instanceof OCStructType && ((OCStructType)ocType).getKind() == OCSymbolKind.STRUCT) {
                if (b) {
                    break;
                }
                final OCType a = this.a((OCStructType)ocType, new HashSet<OCSymbol>());
                if (a != null) {
                    return a;
                }
                ++n;
            }
            else if (ocType instanceof OCArrayType) {
                if (b) {
                    break;
                }
                final OCType a2 = this.a((OCArrayType)ocType, null);
                if (a2 != null) {
                    return a2;
                }
                ++n;
            }
            else {
                boolean b3 = false;
                OCExpression diveIntoParentheses = null;
                if (psiElement instanceof OCExpression) {
                    diveIntoParentheses = OCParenthesesUtils.diveIntoParentheses((OCExpression)psiElement);
                }
                if (diveIntoParentheses instanceof OCLiteralExpression && ocArrayType.isCString() && ((OCExpression)psiElement).getResolvedType().isCString()) {
                    b3 = true;
                }
                final OCType ocType2 = b3 ? ocArrayType : ocArrayType.getRefType();
                final OCSymbol ocSymbol2 = b3 ? ocSymbol : null;
                if (this.myAcc != null) {
                    this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)Pair.create((Object)ocType2, (Object)ocSymbol2));
                }
                if (psiElement == this.myTarget) {
                    return ocType2;
                }
                ++this.myChildCounter;
                if (b3) {
                    break;
                }
                ++n;
            }
        }
        return null;
    }
}
