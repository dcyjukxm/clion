// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.lang.ForeignLeafType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.impl.source.tree.ForeignLeafPsiElement;

public class OCMacroForeignLeafElement extends ForeignLeafPsiElement
{
    private String myMacroName;
    private int myMacroArgumentIndex;
    private TextRange myRangeInMacroArgument;
    private int myOffsetInTopSubstitution;
    
    public OCMacroForeignLeafElement(final OCMacroForeignLeafType type, final CharSequence text, final String myMacroName, final int myMacroArgumentIndex, final TextRange myRangeInMacroArgument, final int myOffsetInTopSubstitution) {
        super(type, text);
        this.myMacroName = myMacroName;
        this.myMacroArgumentIndex = myMacroArgumentIndex;
        this.myRangeInMacroArgument = myRangeInMacroArgument;
        this.myOffsetInTopSubstitution = myOffsetInTopSubstitution;
    }
    
    public int getMacroArgumentIndex() {
        return this.myMacroArgumentIndex;
    }
    
    public TextRange getRangeInMacroArgument() {
        return this.myRangeInMacroArgument;
    }
    
    public String getMacroName() {
        return this.myMacroName;
    }
    
    public int getOffsetInTopSubstitution() {
        return this.myOffsetInTopSubstitution;
    }
    
    public int getRealStartOffset() {
        int n = 0;
        for (TreeElement treeParent = this; treeParent.getTreeParent() != null; treeParent = treeParent.getTreeParent()) {
            n += treeParent.getStartOffsetInParent();
        }
        return n;
    }
    
    public TextRange getRealTextRange() {
        final int realStartOffset = this.getRealStartOffset();
        return new TextRange(realStartOffset, realStartOffset);
    }
    
    public void copyFromElement(final OCMacroForeignLeafElement ocMacroForeignLeafElement) {
        this.myMacroName = ocMacroForeignLeafElement.myMacroName;
        this.myMacroArgumentIndex = ocMacroForeignLeafElement.myMacroArgumentIndex;
        this.myRangeInMacroArgument = ocMacroForeignLeafElement.myRangeInMacroArgument;
        this.myOffsetInTopSubstitution = ocMacroForeignLeafElement.myOffsetInTopSubstitution;
    }
}
