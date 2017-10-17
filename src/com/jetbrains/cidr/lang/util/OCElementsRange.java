// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.lang.ASTNode;
import java.util.NoSuchElementException;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;

public class OCElementsRange implements Comparable<OCElementsRange>
{
    public static final TokenSet NON_IMPORTANT_TOKENS;
    protected PsiElement myFirstElement;
    protected PsiElement myLastElement;
    protected boolean myContainsCompositeElement;
    
    public OCElementsRange(final PsiElement myFirstElement, final PsiElement myLastElement) {
        this.myFirstElement = myFirstElement;
        this.myLastElement = myLastElement;
    }
    
    public OCElementsRange(final OCElementsRange ocElementsRange) {
        this.myFirstElement = ocElementsRange.myFirstElement;
        this.myLastElement = ocElementsRange.myLastElement;
        this.myContainsCompositeElement = ocElementsRange.myContainsCompositeElement;
    }
    
    public PsiElement getFirstElement() {
        return this.myFirstElement;
    }
    
    public void setFirstElement(final PsiElement myFirstElement) {
        this.myFirstElement = myFirstElement;
    }
    
    public PsiElement getLastElement() {
        return this.myLastElement;
    }
    
    public void setLastElement(final PsiElement myLastElement) {
        this.myLastElement = myLastElement;
    }
    
    public TextRange getTextRange() {
        final int startOffset = OCElementUtil.getRangeWithMacros(this.myFirstElement).getStartOffset();
        int endOffset = OCElementUtil.getRangeWithMacros(this.myLastElement).getEndOffset();
        if (endOffset < startOffset) {
            endOffset = startOffset;
        }
        return new TextRange(startOffset, endOffset);
    }
    
    public int getStartOffset() {
        return ((this.myFirstElement instanceof OCMacroForeignLeafElement) ? ((OCMacroForeignLeafElement)this.myFirstElement).getRealTextRange() : this.myFirstElement.getTextRange()).getStartOffset();
    }
    
    public int getEndOffset() {
        return ((this.myLastElement instanceof OCMacroForeignLeafElement) ? ((OCMacroForeignLeafElement)this.myLastElement).getRealTextRange() : this.myLastElement.getTextRange()).getEndOffset();
    }
    
    public String getText() {
        final TextRange textRange = this.getTextRange();
        return this.myFirstElement.getContainingFile().getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }
    
    public PsiFile getFile() {
        return this.myFirstElement.getContainingFile();
    }
    
    public boolean isValid() {
        return !(this.myFirstElement instanceof OCMacroForeignLeafElement) && !(this.myLastElement instanceof OCMacroForeignLeafElement);
    }
    
    public boolean containsCompositeElement() {
        return this.myContainsCompositeElement;
    }
    
    public void setContainsCompositeElement(final boolean myContainsCompositeElement) {
        this.myContainsCompositeElement = myContainsCompositeElement;
    }
    
    public void merge(final OCElementsRange ocElementsRange) {
        if (this.getEndOffset() < ocElementsRange.getEndOffset()) {
            this.myLastElement = ocElementsRange.myLastElement;
        }
        this.myContainsCompositeElement |= ocElementsRange.myContainsCompositeElement;
    }
    
    @Nullable
    public OCElementsRange trim(final TokenSet set) {
        PsiElement psiElement = PsiTreeUtil.firstChild(this.myFirstElement);
        PsiElement psiElement2 = PsiTreeUtil.lastChild(this.myLastElement);
        boolean b = false;
        PsiElement commonParent;
        for (commonParent = PsiTreeUtil.findCommonParent(psiElement, psiElement2); psiElement != psiElement2 && psiElement != null && a(set, psiElement, commonParent); psiElement = PsiTreeUtil.nextLeaf(psiElement), b = true) {}
        while (psiElement != psiElement2 && psiElement2 != null && a(set, psiElement2, commonParent)) {
            psiElement2 = PsiTreeUtil.prevLeaf(psiElement2);
            b = true;
        }
        if (psiElement == psiElement2 && a(set, psiElement, commonParent)) {
            return null;
        }
        if (b) {
            return new OCElementsRange(psiElement, psiElement2);
        }
        return this;
    }
    
    public static List<OCElementsRange> mergeRanges(final List<OCElementsRange> list) {
        Collections.sort((List<Comparable>)list);
        final ArrayList<OCElementsRange> list2 = new ArrayList<OCElementsRange>();
        OCElementsRange ocElementsRange = null;
        final TokenSet non_IMPORTANT_TOKENS = OCElementsRange.NON_IMPORTANT_TOKENS;
        for (final OCElementsRange ocElementsRange2 : list) {
            if (ocElementsRange2 == null) {
                continue;
            }
            if (ocElementsRange != null && ocElementsRange.getEndOffset() + 1 >= ocElementsRange2.getStartOffset()) {
                ocElementsRange.merge(ocElementsRange2);
            }
            else {
                if (ocElementsRange != null && ocElementsRange.isValid() && ocElementsRange.containsCompositeElement()) {
                    final OCElementsRange trim = ocElementsRange.trim(non_IMPORTANT_TOKENS);
                    if (trim != null) {
                        list2.add(trim);
                    }
                }
                ocElementsRange = new OCElementsRange(ocElementsRange2);
            }
        }
        if (ocElementsRange != null && ocElementsRange.isValid() && ocElementsRange.containsCompositeElement()) {
            final OCElementsRange trim2 = ocElementsRange.trim(non_IMPORTANT_TOKENS);
            if (trim2 != null) {
                list2.add(trim2);
            }
        }
        return list2;
    }
    
    private static boolean a(final TokenSet set, PsiElement parent, final PsiElement psiElement) {
        while (parent != psiElement) {
            if (set.contains(OCElementUtil.getElementType(parent))) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }
    
    public boolean isEmpty() {
        for (PsiElement psiElement = this.myFirstElement; psiElement != null && psiElement.getTextRange().getStartOffset() < this.myLastElement.getTextRange().getEndOffset(); psiElement = PsiTreeUtil.nextLeaf(psiElement, true)) {
            final IElementType elementType = OCElementUtil.getElementType(psiElement);
            if (elementType != OCTokenTypes.LBRACE && elementType != OCTokenTypes.RBRACE && !OCElementUtil.isElementEmpty(psiElement)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int compareTo(final OCElementsRange ocElementsRange) {
        return this.getStartOffset() - ocElementsRange.getStartOffset();
    }
    
    public Iterable<PsiElement> getElements() {
        return new Iterable<PsiElement>() {
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
        };
    }
    
    static {
        NON_IMPORTANT_TOKENS = TokenSet.orSet(new TokenSet[] { OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET, OCElementTypes.DIRECTIVES, OCTokenTypes.DIRECTIVES, TokenSet.create(new IElementType[] { OCTokenTypes.LBRACE, OCTokenTypes.RBRACE, OCTokenTypes.ELSE_KEYWORD }) });
    }
}
