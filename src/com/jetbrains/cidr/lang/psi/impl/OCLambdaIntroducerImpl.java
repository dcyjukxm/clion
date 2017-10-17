// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCLambdaIntroducer;

public class OCLambdaIntroducerImpl extends OCElementBase implements OCLambdaIntroducer
{
    public OCLambdaIntroducerImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCLambdaIntroducerImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLambdaIntroducerImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitLambdaIntroducer(this);
    }
    
    @Nullable
    @Override
    public ASTNode getCaptureDefault() {
        for (final PsiElement psiElement : this.getChildren()) {
            final ASTNode node = psiElement.getNode();
            try {
                if (node.getElementType() == OCTokenTypes.EQ) {
                    return node;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0101: {
                try {
                    if (node.getElementType() != OCTokenTypes.AND) {
                        break Label_0101;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final int n = 2;
                    final Class[] array = new Class[n];
                    final int n2 = 0;
                    final Class<PsiWhiteSpace> clazz = PsiWhiteSpace.class;
                    array[n2] = clazz;
                    final int n3 = 1;
                    final Class<PsiComment> clazz2 = PsiComment.class;
                    array[n3] = clazz2;
                    final PsiElement psiElement3 = PsiTreeUtil.skipSiblingsForward(psiElement2, array);
                    final boolean b = psiElement3 instanceof OCReferenceElement;
                    if (!b) {
                        return node;
                    }
                    break Label_0101;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final int n = 2;
                    final Class[] array = new Class[n];
                    final int n2 = 0;
                    final Class<PsiWhiteSpace> clazz = PsiWhiteSpace.class;
                    array[n2] = clazz;
                    final int n3 = 1;
                    final Class<PsiComment> clazz2 = PsiComment.class;
                    array[n3] = clazz2;
                    final PsiElement psiElement3 = PsiTreeUtil.skipSiblingsForward(psiElement2, array);
                    final boolean b = psiElement3 instanceof OCReferenceElement;
                    if (!b) {
                        return node;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        return null;
    }
    
    @Override
    public List<OCReferenceElement> getReferencedElements() {
        final ArrayList<OCReferenceElement> list = new ArrayList<OCReferenceElement>();
        for (final PsiElement psiElement : this.getChildren()) {
            try {
                if (psiElement instanceof OCReferenceElement) {
                    list.add((OCReferenceElement)psiElement);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return list;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
