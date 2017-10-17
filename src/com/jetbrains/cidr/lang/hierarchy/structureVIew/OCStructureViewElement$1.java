// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

class OCStructureViewElement$1 extends OCVisitor {
    final /* synthetic */ List val$children;
    
    @Override
    public void visitOCElement(final OCElement ocElement) {
        if (this.a(ocElement)) {
            this.val$children.add(ocElement);
        }
        else if (!this.b(ocElement)) {
            ocElement.acceptChildren((PsiElementVisitor)this);
        }
    }
    
    public void visitComment(final PsiComment psiComment) {
        this.val$children.add(psiComment);
    }
    
    @Override
    public void visitPragma(final OCPragma ocPragma) {
        this.val$children.add(ocPragma);
    }
    
    private boolean a(final OCElement ocElement) {
        return ocElement instanceof OCDefineDirective || ocElement instanceof OCDeclarator || ocElement instanceof OCFunctionDefinition || ocElement instanceof OCCppNamespace || ocElement instanceof OCStructLike || ocElement instanceof OCMethod || ocElement instanceof OCInterface || ocElement instanceof OCImplementation || ocElement instanceof OCProtocol;
    }
    
    private boolean b(final OCElement ocElement) {
        return ocElement instanceof OCTemplateParameterList;
    }
}