// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.Stack;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCPragmaSuppressionUtils$1 extends OCRecursiveVisitor {
    final /* synthetic */ ScopesStack val$stack;
    final /* synthetic */ Document val$document;
    
    @Override
    public void visitPragma(final OCPragma ocPragma) {
        this.val$stack.a(ocPragma, this.val$document);
    }
}