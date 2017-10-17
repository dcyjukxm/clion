// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.lang.ASTNode;
import java.util.NoSuchElementException;
import com.intellij.psi.PsiElement;
import java.util.Iterator;

class OCElementsRange$1$1 implements Iterator<PsiElement> {
    PsiElement nextElem = Iterable.this.this$0.myFirstElement;
    
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
        if (this.nextElem != Iterable.this.this$0.myLastElement) {
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
}