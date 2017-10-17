// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import java.util.Iterator;
import com.jetbrains.cidr.doxygen.DoxygenUtil;
import java.util.Collection;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiWhiteSpace;
import java.util.LinkedList;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCProperty;
import java.util.Collections;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiComment;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class DxCommentProcessorUtil
{
    @NotNull
    public static List<PsiComment> getCommentScope(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elt", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "getCommentScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<PsiComment> a = a(psiElement);
        List<PsiComment> list;
        try {
            list = a;
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "getCommentScope"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list;
    }
    
    @NotNull
    public static List<PsiComment> findOCCommentFor(@Nullable final PsiElement psiElement) {
        final List<PsiComment> a = a(psiElement);
        List<PsiComment> list = null;
        Label_0103: {
            try {
                if (a.size() != 0 || !(psiElement instanceof OCSymbolDeclarator)) {
                    break Label_0103;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCSymbol<PsiElement> ocSymbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (ocSymbol != null) {
                ocSymbol = ocSymbol.getAssociatedSymbol();
            }
            if (ocSymbol != null) {
                final PsiElement locateDefinition = ocSymbol.locateDefinition();
                List<PsiComment> a2;
                try {
                    a2 = a(locateDefinition);
                    if (a2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findOCCommentFor"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return a2;
            }
            try {
                list = a;
                if (list == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findOCCommentFor"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return list;
    }
    
    @NotNull
    private static List<PsiComment> a(@Nullable PsiElement psiElement) {
        if (psiElement instanceof OCDeclarator) {
            psiElement = psiElement.getParent();
        }
        if (psiElement instanceof OCStructLike) {
            psiElement = PsiTreeUtil.findFirstParent(psiElement, psiElement -> psiElement instanceof OCDeclaration);
        }
        Label_0084: {
            List<PsiComment> list = null;
            Label_0049: {
                try {
                    if (psiElement != null) {
                        break Label_0084;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0049;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findOCCommentForElementInternal"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return list;
        }
        if (psiElement.getParent() instanceof OCProperty) {
            psiElement = psiElement.getParent();
        }
        if (psiElement.getParent() instanceof OCDeclarationStatement) {
            psiElement = psiElement.getParent();
        }
        Label_0180: {
            List<PsiComment> list2 = null;
            Label_0145: {
                try {
                    if (psiElement.getContainingFile() != null) {
                        break Label_0180;
                    }
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        break Label_0145;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findOCCommentForElementInternal"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return list2;
        }
        final LinkedList<PsiComment> list3 = new LinkedList<PsiComment>();
        LinkedList<PsiComment> list4;
        try {
            c(psiElement, list3);
            d(psiElement, list3);
            b(psiElement, list3);
            list4 = list3;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findOCCommentForElementInternal"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return list4;
    }
    
    private static void c(@NotNull final PsiElement psiElement, @NotNull final List<PsiComment> list) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elt", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findPrevComments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findPrevComments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        PsiElement firstChild = psiElement.getFirstChild();
        PsiElement psiElement2 = psiElement.getPrevSibling();
        while (true) {
            Label_0150: {
                Label_0127: {
                    Label_0120: {
                        try {
                            if (psiElement2 == null) {
                                break;
                            }
                            final PsiElement psiElement3 = psiElement2;
                            final boolean b = psiElement3 instanceof PsiWhiteSpace;
                            if (b) {
                                break Label_0120;
                            }
                            break Label_0127;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final PsiElement psiElement3 = psiElement2;
                            final boolean b = psiElement3 instanceof PsiWhiteSpace;
                            if (b) {
                                break Label_0150;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        if (psiElement2 instanceof OCMacroCall) {
                            break Label_0150;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                if (!(psiElement2 instanceof PsiComment)) {
                    break;
                }
                firstChild = psiElement2;
            }
            psiElement2 = psiElement2.getPrevSibling();
        }
        if (firstChild != null) {
            final ArrayList<PsiComment> list2 = new ArrayList<PsiComment>();
            final ArrayList<PsiComment> list3 = new ArrayList<PsiComment>();
            a(firstChild, list2, false);
            a(firstChild, list3, true);
            list2.removeAll(list3);
            for (final PsiComment psiComment : list2) {
                try {
                    if (!DoxygenUtil.isDoxygenComment(psiComment)) {
                        continue;
                    }
                    list.add(psiComment);
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
    }
    
    private static void d(@NotNull final PsiElement psiElement, @NotNull final List<PsiComment> list) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elt", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findInnerComments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findInnerComments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0114: {
            try {
                if (psiElement instanceof OCClassDeclaration) {
                    return;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCMethod;
                if (b) {
                    return;
                }
                break Label_0114;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCMethod;
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final ArrayList<PsiComment> list2 = new ArrayList<PsiComment>();
        a(psiElement.getFirstChild(), list2, false);
        final List childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList(psiElement, (Class)PsiComment.class);
        childrenOfTypeAsList.removeAll(list2);
        for (final PsiComment psiComment : childrenOfTypeAsList) {
            try {
                if (!DoxygenUtil.isDoxygenComment(psiComment)) {
                    continue;
                }
                list.add(psiComment);
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
    }
    
    private static void b(@NotNull final PsiElement psiElement, @NotNull final List<PsiComment> list) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elt", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findNextComments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil", "findNextComments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0114: {
            try {
                if (psiElement instanceof OCParameterDeclaration) {
                    break Label_0114;
                }
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final boolean b = psiElement3 instanceof OCEnum;
                if (b) {
                    break Label_0114;
                }
                break Label_0114;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final boolean b = psiElement3 instanceof OCEnum;
                if (b) {
                    a(psiElement, list);
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        a(psiElement.getNextSibling(), list, true);
    }
    
    private static void a(@Nullable final PsiElement psiElement, final List<PsiComment> list, final boolean b) {
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Object o = psiElement;
        while (true) {
            Label_0193: {
                Label_0029: {
                    try {
                        if (o == null) {
                            break;
                        }
                        final Object o2 = o;
                        final boolean b2 = o2 instanceof PsiWhiteSpace;
                        if (b2) {
                            break Label_0029;
                        }
                        break Label_0029;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final Object o2 = o;
                        final boolean b2 = o2 instanceof PsiWhiteSpace;
                        if (b2) {
                            break Label_0193;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                if (o instanceof PsiComment) {
                    final PsiComment psiComment = (PsiComment)o;
                    try {
                        if (!b) {
                            list.add(psiComment);
                            break Label_0193;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    Label_0139: {
                        Label_0092: {
                            try {
                                if (!DoxygenUtil.isDoxygenComment(psiComment)) {
                                    break;
                                }
                                final PsiComment psiComment2 = psiComment;
                                final boolean b3 = DoxygenUtil.hasArrow(psiComment2);
                                if (b3) {
                                    break Label_0092;
                                }
                                break;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                            try {
                                final PsiComment psiComment2 = psiComment;
                                final boolean b3 = DoxygenUtil.hasArrow(psiComment2);
                                if (!b3) {
                                    break;
                                }
                                if (!DoxygenUtil.isDoxygenEOLComment(psiComment)) {
                                    break Label_0139;
                                }
                            }
                            catch (IllegalArgumentException ex6) {
                                throw a(ex6);
                            }
                        }
                        final EOLCommentPack eolCommentPack = new EOLCommentPack(psiComment);
                        list.addAll(eolCommentPack.getComments());
                        o = eolCommentPack.getLastComment();
                        break Label_0193;
                    }
                    list.add(psiComment);
                    break Label_0193;
                    break;
                }
                Label_0175: {
                    try {
                        if (o instanceof OCDeclaration) {
                            break Label_0175;
                        }
                        final PsiComment psiComment3 = (PsiComment)o;
                        final boolean b4 = b((PsiElement)psiComment3);
                        if (b4) {
                            break Label_0175;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                    try {
                        final PsiComment psiComment3 = (PsiComment)o;
                        final boolean b4 = b((PsiElement)psiComment3);
                        if (b4) {
                            a(((PsiElement)o).getFirstChild(), list, b);
                            break;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                }
            }
            o = ((PsiElement)o).getNextSibling();
        }
    }
    
    private static void a(@NotNull final PsiElement p0, @NotNull final List<PsiComment> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "elt"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findNextCommentsForCommaSeparatedSequence"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "comments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "findNextCommentsForCommaSeparatedSequence"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_0       
        //    89: istore_2       
        //    90: aload_0        
        //    91: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    96: astore_3       
        //    97: aload_3        
        //    98: ifnull          273
        //   101: aload_3        
        //   102: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   105: ifeq            122
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: goto            263
        //   118: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_3        
        //   123: instanceof      Lcom/intellij/psi/impl/source/tree/LeafElement;
        //   126: ifeq            154
        //   129: aload_3        
        //   130: checkcast       Lcom/intellij/psi/impl/source/tree/LeafElement;
        //   133: invokevirtual   com/intellij/psi/impl/source/tree/LeafElement.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   136: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   139: if_acmpne       154
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: iconst_1       
        //   150: istore_2       
        //   151: goto            263
        //   154: aload_3        
        //   155: instanceof      Lcom/intellij/psi/PsiComment;
        //   158: ifeq            273
        //   161: aload_3        
        //   162: checkcast       Lcom/intellij/psi/PsiComment;
        //   165: astore          4
        //   167: aload           4
        //   169: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.isDoxygenComment:(Lcom/intellij/psi/PsiComment;)Z
        //   172: ifeq            260
        //   175: iload_2        
        //   176: ifeq            208
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload           4
        //   188: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.hasArrow:(Lcom/intellij/psi/PsiComment;)Z
        //   191: ifne            208
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: goto            273
        //   204: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload           4
        //   210: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.isDoxygenEOLComment:(Lcom/intellij/psi/PsiComment;)Z
        //   213: ifeq            248
        //   216: new             Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack;
        //   219: dup            
        //   220: aload           4
        //   222: invokespecial   com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.<init>:(Lcom/intellij/psi/PsiComment;)V
        //   225: astore          5
        //   227: aload_1        
        //   228: aload           5
        //   230: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.getComments:()Ljava/util/List;
        //   233: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   238: pop            
        //   239: aload           5
        //   241: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.getLastComment:()Lcom/intellij/psi/PsiComment;
        //   244: astore_3       
        //   245: goto            263
        //   248: aload_1        
        //   249: aload           4
        //   251: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   256: pop            
        //   257: goto            263
        //   260: goto            273
        //   263: aload_3        
        //   264: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   269: astore_3       
        //   270: goto            97
        //   273: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/intellij/psi/PsiComment;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  97     108    111    115    Ljava/lang/IllegalArgumentException;
        //  101    118    118    122    Ljava/lang/IllegalArgumentException;
        //  122    142    145    149    Ljava/lang/IllegalArgumentException;
        //  167    179    182    186    Ljava/lang/IllegalArgumentException;
        //  175    194    197    201    Ljava/lang/IllegalArgumentException;
        //  186    204    204    208    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static boolean b(@NotNull final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "elt"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isOCDeclaration"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    48: ifne            93
        //    51: aload_0        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    55: ifne            93
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    69: ifne            93
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    83: ifeq            101
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     86     89     93     Ljava/lang/IllegalArgumentException;
        //  79     97     97     101    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class EOLCommentPack
    {
        private final List<PsiComment> comments;
        static final /* synthetic */ boolean $assertionsDisabled;
        
        public EOLCommentPack(@NotNull final PsiComment psiComment) {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "EOLComment", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack", "<init>"));
            }
            this.comments = new ArrayList<PsiComment>();
            if (!EOLCommentPack.$assertionsDisabled) {
                try {
                    if (!DoxygenUtil.isDoxygenEOLComment(psiComment)) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            this.comments.add(psiComment);
            a(psiComment, this.comments);
        }
        
        public List<PsiComment> getComments() {
            return this.comments;
        }
        
        @NotNull
        public PsiComment getLastComment() {
            PsiComment psiComment = null;
            Label_0037: {
                Label_0025: {
                    try {
                        if (EOLCommentPack.$assertionsDisabled) {
                            break Label_0037;
                        }
                        final EOLCommentPack eolCommentPack = this;
                        final List<PsiComment> list = eolCommentPack.comments;
                        final int n = list.size();
                        if (n <= 0) {
                            break Label_0025;
                        }
                        break Label_0037;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final EOLCommentPack eolCommentPack = this;
                        final List<PsiComment> list = eolCommentPack.comments;
                        final int n = list.size();
                        if (n <= 0) {
                            throw new AssertionError();
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                try {
                    psiComment = this.comments.get(this.comments.size() - 1);
                    if (psiComment == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack", "getLastComment"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return psiComment;
        }
        
        private static void a(@NotNull final PsiComment p0, @NotNull final List<PsiComment> p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "EOLComment"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "processEOLCommentPack"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_1        
            //    45: ifnonnull       88
            //    48: new             Ljava/lang/IllegalArgumentException;
            //    51: dup            
            //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    54: ldc             3
            //    56: anewarray       Ljava/lang/Object;
            //    59: dup            
            //    60: ldc             0
            //    62: ldc             "pack"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "processEOLCommentPack"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_0        
            //    89: invokeinterface com/intellij/psi/PsiComment.getProject:()Lcom/intellij/openapi/project/Project;
            //    94: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
            //    97: astore_2       
            //    98: aload_2        
            //    99: aload_0        
            //   100: invokeinterface com/intellij/psi/PsiComment.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //   105: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
            //   108: astore_3       
            //   109: aload_3        
            //   110: ifnonnull       118
            //   113: return         
            //   114: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   117: athrow         
            //   118: aload_0        
            //   119: invokeinterface com/intellij/psi/PsiComment.getTextOffset:()I
            //   124: istore          4
            //   126: iload           4
            //   128: aload_3        
            //   129: aload_3        
            //   130: iload           4
            //   132: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
            //   137: invokeinterface com/intellij/openapi/editor/Document.getLineStartOffset:(I)I
            //   142: isub           
            //   143: istore          5
            //   145: aload_0        
            //   146: invokeinterface com/intellij/psi/PsiComment.getNextSibling:()Lcom/intellij/psi/PsiElement;
            //   151: astore          6
            //   153: aload           6
            //   155: ifnull          375
            //   158: aload           6
            //   160: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
            //   163: ifeq            201
            //   166: goto            173
            //   169: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   172: athrow         
            //   173: aload           6
            //   175: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
            //   180: invokestatic    com/intellij/openapi/util/text/StringUtil.countNewLines:(Ljava/lang/CharSequence;)I
            //   183: iconst_1       
            //   184: if_icmpne       201
            //   187: goto            194
            //   190: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   193: athrow         
            //   194: goto            363
            //   197: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   200: athrow         
            //   201: aload           6
            //   203: instanceof      Lcom/intellij/psi/PsiComment;
            //   206: ifeq            238
            //   209: aload           6
            //   211: checkcast       Lcom/intellij/psi/PsiComment;
            //   214: aload_3        
            //   215: iload           5
            //   217: aload_1        
            //   218: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Lcom/intellij/psi/PsiComment;Lcom/intellij/openapi/editor/Document;ILjava/util/List;)Z
            //   221: ifeq            375
            //   224: goto            231
            //   227: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   230: athrow         
            //   231: goto            363
            //   234: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   237: athrow         
            //   238: aload           6
            //   240: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
            //   243: ifne            261
            //   246: aload           6
            //   248: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil.access$000:(Lcom/intellij/psi/PsiElement;)Z
            //   251: ifeq            375
            //   254: goto            261
            //   257: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   260: athrow         
            //   261: aload           6
            //   263: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
            //   268: astore          7
            //   270: aload           7
            //   272: ifnull          360
            //   275: aload           7
            //   277: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
            //   280: ifeq            318
            //   283: goto            290
            //   286: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   289: athrow         
            //   290: aload           7
            //   292: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
            //   297: invokestatic    com/intellij/openapi/util/text/StringUtil.countNewLines:(Ljava/lang/CharSequence;)I
            //   300: iconst_1       
            //   301: if_icmpne       318
            //   304: goto            311
            //   307: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   310: athrow         
            //   311: goto            348
            //   314: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   317: athrow         
            //   318: aload           7
            //   320: instanceof      Lcom/intellij/psi/PsiComment;
            //   323: ifeq            360
            //   326: aload           7
            //   328: checkcast       Lcom/intellij/psi/PsiComment;
            //   331: aload_3        
            //   332: iload           5
            //   334: aload_1        
            //   335: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Lcom/intellij/psi/PsiComment;Lcom/intellij/openapi/editor/Document;ILjava/util/List;)Z
            //   338: ifeq            360
            //   341: goto            348
            //   344: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   347: athrow         
            //   348: aload           7
            //   350: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
            //   355: astore          7
            //   357: goto            270
            //   360: goto            375
            //   363: aload           6
            //   365: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
            //   370: astore          6
            //   372: goto            153
            //   375: return         
            //    Signature:
            //  (Lcom/intellij/psi/PsiComment;Ljava/util/List<Lcom/intellij/psi/PsiComment;>;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  109    114    114    118    Ljava/lang/IllegalArgumentException;
            //  153    166    169    173    Ljava/lang/IllegalArgumentException;
            //  158    187    190    194    Ljava/lang/IllegalArgumentException;
            //  173    197    197    201    Ljava/lang/IllegalArgumentException;
            //  201    224    227    231    Ljava/lang/IllegalArgumentException;
            //  209    234    234    238    Ljava/lang/IllegalArgumentException;
            //  238    254    257    261    Ljava/lang/IllegalArgumentException;
            //  270    283    286    290    Ljava/lang/IllegalArgumentException;
            //  275    304    307    311    Ljava/lang/IllegalArgumentException;
            //  290    314    314    318    Ljava/lang/IllegalArgumentException;
            //  318    341    344    348    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private static boolean a(@NotNull final PsiComment psiComment, @NotNull final Document document, final int n, @NotNull final List<PsiComment> list) {
            try {
                if (psiComment == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack", "processEOLComment"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (document == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack", "processEOLComment"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pack", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessorUtil$EOLCommentPack", "processEOLComment"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            if (DoxygenUtil.isDoxygenEOLComment(psiComment)) {
                final int textOffset = psiComment.getTextOffset();
                final int n2 = textOffset - document.getLineStartOffset(document.getLineNumber(textOffset));
                try {
                    if (n == n2) {
                        list.add(psiComment);
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return false;
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!DxCommentProcessorUtil.class.desiredAssertionStatus()) {
                        $assertionsDisabled2 = true;
                        break Label_0017;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                $assertionsDisabled2 = false;
            }
            $assertionsDisabled = $assertionsDisabled2;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
