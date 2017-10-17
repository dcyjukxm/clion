// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.lang.ASTNode;
import java.util.NoSuchElementException;
import java.util.Iterator;
import com.intellij.psi.PsiElement;

class OCElementsRange$1 implements Iterable<PsiElement> {
    @Override
    public Iterator<PsiElement> iterator() {
        return new Iterator<PsiElement>() {
            PsiElement nextElem = OCElementsRange.this.myFirstElement;
            
            @Override
            public boolean hasNext() {
                try {
                    if (this.nextElem != null) {
                        return true;
                    }
                }
                catch (NoSuchElementException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            @Override
            public PsiElement next() {
                final PsiElement nextElem = this.nextElem;
                try {
                    if (this.nextElem == null) {
                        throw new NoSuchElementException();
                    }
                }
                catch (NoSuchElementException ex) {
                    throw b(ex);
                }
                if (this.nextElem != OCElementsRange.this.myLastElement) {
                    final ASTNode treeNext = this.nextElem.getNode().getTreeNext();
                    PsiElement psi = null;
                    Label_0075: {
                        try {
                            if (treeNext != null) {
                                psi = treeNext.getPsi();
                                break Label_0075;
                            }
                        }
                        catch (NoSuchElementException ex2) {
                            throw b(ex2);
                        }
                        psi = null;
                    }
                    this.nextElem = psi;
                }
                else {
                    this.nextElem = null;
                }
                return nextElem;
            }
            
            @Override
            public void remove() {
            }
            
            private static NoSuchElementException b(final NoSuchElementException ex) {
                return ex;
            }
        };
    }
}