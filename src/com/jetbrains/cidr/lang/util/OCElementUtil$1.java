// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCPragma;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCElementUtil$1 extends OCRecursiveVisitor {
    int nestLevel = 0;
    int directivesAtZeroNestLevel = 0;
    final /* synthetic */ OCDirective[] val$candidate;
    final /* synthetic */ PsiFile val$file;
    final /* synthetic */ PsiElement[] val$lastEssential;
    final /* synthetic */ PsiElement[] val$lastEndif;
    
    @Override
    public void visitElement(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil$1", "visitElement"));
            }
        }
        catch (1CancelException ex) {
            throw b(ex);
        }
        Label_0084: {
            Label_0072: {
                try {
                    super.visitElement(psiElement);
                    if (!OCElementUtil.isEssentialNode(psiElement)) {
                        return;
                    }
                    final OCRecursiveVisitor ocRecursiveVisitor = this;
                    final OCDirective[] array = ocRecursiveVisitor.val$candidate;
                    final int n = 0;
                    final OCDirective ocDirective = array[n];
                    if (ocDirective == null) {
                        break Label_0072;
                    }
                    break Label_0084;
                }
                catch (1CancelException ex2) {
                    throw b(ex2);
                }
                try {
                    final OCRecursiveVisitor ocRecursiveVisitor = this;
                    final OCDirective[] array = ocRecursiveVisitor.val$candidate;
                    final int n = 0;
                    final OCDirective ocDirective = array[n];
                    if (ocDirective == null) {
                        throw new 1CancelException();
                    }
                }
                catch (1CancelException ex3) {
                    throw b(ex3);
                }
            }
            try {
                if (psiElement != this.val$file) {
                    this.val$lastEssential[0] = psiElement;
                }
            }
            catch (1CancelException ex4) {
                throw b(ex4);
            }
        }
    }
    
    @Override
    public void visitPragma(@NotNull final OCPragma ocPragma) {
        try {
            if (ocPragma == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pragma", "com/jetbrains/cidr/lang/util/OCElementUtil$1", "visitPragma"));
            }
        }
        catch (1CancelException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitDirective(@NotNull final OCDirective ocDirective) {
        try {
            if (ocDirective == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directive", "com/jetbrains/cidr/lang/util/OCElementUtil$1", "visitDirective"));
            }
        }
        catch (1CancelException ex) {
            throw b(ex);
        }
        final IElementType elementType = OCElementUtil.getElementType(ocDirective.getHeaderToken());
        Label_0199: {
            Label_0180: {
                Label_0148: {
                    Label_0122: {
                        Label_0091: {
                            Label_0077: {
                                try {
                                    if (this.val$candidate[0] != null) {
                                        break Label_0091;
                                    }
                                    final OCDirective ocDirective2 = ocDirective;
                                    final boolean b = OCElementUtil.access$000(ocDirective2);
                                    if (b) {
                                        break Label_0077;
                                    }
                                    break Label_0091;
                                }
                                catch (1CancelException ex2) {
                                    throw b(ex2);
                                }
                                try {
                                    final OCDirective ocDirective2 = ocDirective;
                                    final boolean b = OCElementUtil.access$000(ocDirective2);
                                    if (b) {
                                        this.val$candidate[0] = ocDirective;
                                    }
                                }
                                catch (1CancelException ex3) {
                                    throw b(ex3);
                                }
                            }
                            try {
                                if (OCTokenTypes.ENDIF_DIRECTIVE != elementType) {
                                    break Label_0148;
                                }
                                final OCRecursiveVisitor ocRecursiveVisitor = this;
                                final int n = ocRecursiveVisitor.nestLevel;
                                final int n2 = 1;
                                final int n3 = n - n2;
                                ocRecursiveVisitor.nestLevel = n3;
                                final OCRecursiveVisitor ocRecursiveVisitor2 = this;
                                final int n4 = ocRecursiveVisitor2.nestLevel;
                                if (n4 < 0) {
                                    break Label_0122;
                                }
                                break Label_0122;
                            }
                            catch (1CancelException ex4) {
                                throw b(ex4);
                            }
                        }
                        try {
                            final OCRecursiveVisitor ocRecursiveVisitor = this;
                            final int n = ocRecursiveVisitor.nestLevel;
                            final int n2 = 1;
                            final int n3 = n - n2;
                            ocRecursiveVisitor.nestLevel = n3;
                            final OCRecursiveVisitor ocRecursiveVisitor2 = this;
                            final int n4 = ocRecursiveVisitor2.nestLevel;
                            if (n4 < 0) {
                                this.val$candidate[0] = null;
                                throw new 1CancelException();
                            }
                        }
                        catch (1CancelException ex5) {
                            throw b(ex5);
                        }
                    }
                    this.val$lastEndif[0] = (PsiElement)ocDirective;
                    try {
                        if (this.nestLevel != 0) {
                            break Label_0199;
                        }
                        final OCRecursiveVisitor ocRecursiveVisitor3 = this;
                        ++ocRecursiveVisitor3.directivesAtZeroNestLevel;
                        final OCRecursiveVisitor ocRecursiveVisitor4 = this;
                        final int n5 = ocRecursiveVisitor4.directivesAtZeroNestLevel;
                        final int n6 = 2;
                        if (n5 > n6) {
                            break Label_0180;
                        }
                        break Label_0199;
                    }
                    catch (1CancelException ex6) {
                        throw b(ex6);
                    }
                }
                try {
                    final OCRecursiveVisitor ocRecursiveVisitor3 = this;
                    ++ocRecursiveVisitor3.directivesAtZeroNestLevel;
                    final OCRecursiveVisitor ocRecursiveVisitor4 = this;
                    final int n5 = ocRecursiveVisitor4.directivesAtZeroNestLevel;
                    final int n6 = 2;
                    if (n5 > n6) {
                        this.val$candidate[0] = null;
                        throw new 1CancelException();
                    }
                }
                catch (1CancelException ex7) {
                    throw b(ex7);
                }
            }
            try {
                if (OCTokenTypes.IF_DIRECTIVES.contains(elementType)) {
                    ++this.nestLevel;
                }
            }
            catch (1CancelException ex8) {
                throw b(ex8);
            }
        }
        super.visitDirective(ocDirective);
    }
    
    private static 1CancelException b(final 1CancelException ex) {
        return ex;
    }
}