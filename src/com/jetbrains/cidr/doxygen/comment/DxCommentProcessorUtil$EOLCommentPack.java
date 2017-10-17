// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.doxygen.DoxygenUtil;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiComment;
import java.util.List;

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
