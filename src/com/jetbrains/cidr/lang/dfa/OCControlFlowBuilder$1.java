// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.lang.ASTNode;

class OCControlFlowBuilder$1 implements KidIterator {
    private ASTNode child;
    private boolean initialized;
    final /* synthetic */ ASTNode val$firstChild;
    
    @Override
    public void skipLeaves() {
        if (!this.initialized) {
            this.initialized = true;
            this.child = this.val$firstChild;
        }
        while (this.child != null && (this.child.getFirstChildNode() == null || this.child.getElementType() == OCElementTypes.OBJC_KEYWORD || !OCElementUtil.isElementSignificant(this.child.getPsi()))) {
            final PsiElement psi = this.child.getPsi();
            if (psi != null && OCControlFlowBuilder.this.doEnlargeNodes()) {
                OCNode lastAddedNode = OCControlFlowBuilder.this.myGraph.getLastAddedNode();
                if (lastAddedNode.isEmpty() && OCControlFlowBuilder.access$000().contains(this.child.getElementType())) {
                    for (OCNode ocNode = OCControlFlowBuilder.this.myGraph.getPreviousNonEmptyNode(lastAddedNode); ocNode != null && lastAddedNode.getEndOffset() < ocNode.getEndOffset() && ocNode.getEndOffset() <= psi.getTextOffset(); lastAddedNode = ocNode, ocNode = OCControlFlowBuilder.this.myGraph.getPreviousNonEmptyNode(lastAddedNode)) {}
                }
                lastAddedNode.enlarge(psi, psi.getParent());
            }
            this.child = this.child.getTreeNext();
        }
    }
    
    @Override
    public void accept(@Nullable final PsiElement psiElement) {
        if (psiElement == null) {
            return;
        }
        this.skipLeaves();
        if (this.child != null && psiElement == this.child.getPsi()) {
            psiElement.accept((PsiElementVisitor)OCControlFlowBuilder.this);
            this.child = this.child.getTreeNext();
        }
    }
    
    @Override
    public void skip(@Nullable final PsiElement psiElement) {
        if (psiElement == null) {
            return;
        }
        this.skipLeaves();
        if (this.child != null && psiElement == this.child.getPsi()) {
            this.child = this.child.getTreeNext();
        }
    }
    
    @Override
    public void acceptAll() {
        this.skipLeaves();
        while (this.child != null) {
            ProgressManager.checkCanceled();
            final PsiElement psi = this.child.getPsi();
            if (psi != null) {
                psi.accept((PsiElementVisitor)OCControlFlowBuilder.this);
            }
            this.child = this.child.getTreeNext();
            this.skipLeaves();
        }
    }
    
    @Override
    public void finish() {
        this.skipLeaves();
    }
}