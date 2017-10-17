// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import com.intellij.psi.PsiComment;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class CidrDocumentationProvider$1 extends OCRecursiveVisitor {
    final /* synthetic */ List val$comments;
    
    public void visitComment(final PsiComment psiComment) {
        this.val$comments.add(psiComment);
    }
}