// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.jetbrains.cidr.doxygen.psi.DxDocComment;
import com.jetbrains.cidr.doxygen.DoxygenUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.TokenType;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCmd;
import com.jetbrains.cidr.doxygen.psi.DxDocTag;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.psi.PsiComment;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import java.util.Set;

public class DxCommentProcessor
{
    public static final Set<String> PLAIN_TAGS;
    final PsiElement element;
    private static final Result NONE;
    
    public DxCommentProcessor(@Nullable final PsiElement element) {
        this.element = element;
    }
    
    @NotNull
    public DoxygenCommentGroupImpl getCommentGroup() {
        final DoxygenCommentGroupImpl.Builder a = a(DxCommentProcessorUtil.findOCCommentFor(this.element));
        PsiElement psiElement = this.element;
        if (psiElement instanceof OCDeclarator) {
            psiElement = psiElement.getParent();
        }
        if (psiElement instanceof OCCallable) {
            final List parameters = ((OCCallable)psiElement).getParameters();
            if (parameters != null) {
                for (final PsiNamedElement psiNamedElement : parameters) {
                    final String name = psiNamedElement.getName();
                    if (!"<unnamed>".equals(name)) {
                        final DoxygenCommentGroupImpl build = a(DxCommentProcessorUtil.findOCCommentFor((PsiElement)psiNamedElement)).build();
                        try {
                            if (build.isEmpty()) {
                                continue;
                            }
                            a.addTag(new DoxygenCommentTagImpl("param", build.getOptions(), name, build.getDescription()));
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                    }
                }
            }
        }
        DoxygenCommentGroupImpl build2;
        try {
            build2 = a.build();
            if (build2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "getCommentGroup"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return build2;
    }
    
    @NotNull
    private static DoxygenCommentGroupImpl.Builder a(@NotNull final List<PsiComment> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "process"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        int n = 0;
        final DoxygenCommentGroupImpl.Builder builder = new DoxygenCommentGroupImpl.Builder();
        final Comment comment = new Comment(list);
        PsiElement psiElement = comment.getFistChild();
        while (psiElement != null) {
            final IElementType elementType = psiElement.getNode().getElementType();
            if (elementType == DxTypes.DOC_COMMENT_DATA) {
                final StringBuilder sb = new StringBuilder();
                psiElement = a(comment, psiElement, sb).element;
                builder.addText(sb.toString());
            }
            else {
                Label_0234: {
                    Label_0207: {
                        Label_0169: {
                            try {
                                if (elementType != DxTypes.TAG_OPTION) {
                                    break Label_0207;
                                }
                                if (n != 0) {
                                    break Label_0169;
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                            n = 1;
                            builder.addOptions(psiElement.getNode());
                            break Label_0234;
                        }
                        final StringBuilder sb2 = new StringBuilder();
                        psiElement = a(comment, psiElement, sb2).element;
                        builder.addText(sb2.toString());
                        continue;
                    }
                    if (elementType == DxTypes.DOC_TAG) {
                        psiElement = a(comment, psiElement, builder).element;
                        continue;
                    }
                }
                psiElement = comment.nextSibling(psiElement);
            }
        }
        DoxygenCommentGroupImpl.Builder builder2;
        try {
            builder2 = builder;
            if (builder2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "process"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return builder2;
    }
    
    @NotNull
    private static Result a(@NotNull final Comment p0, @Nullable final PsiElement p1, @NotNull final StringBuilder p2) {
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
        //    18: ldc             "comment"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processDocData"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "description"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processDocData"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload_1        
        //    89: ifnonnull       141
        //    92: getstatic       com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.NONE:Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result;
        //    95: dup            
        //    96: ifnonnull       140
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   105: athrow         
        //   106: new             Ljava/lang/IllegalStateException;
        //   109: dup            
        //   110: ldc             "@NotNull method %s.%s must not return null"
        //   112: ldc             2
        //   114: anewarray       Ljava/lang/Object;
        //   117: dup            
        //   118: ldc             0
        //   120: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   122: aastore        
        //   123: dup            
        //   124: ldc             1
        //   126: ldc             "processDocData"
        //   128: aastore        
        //   129: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   132: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   135: athrow         
        //   136: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   139: athrow         
        //   140: areturn        
        //   141: aload_1        
        //   142: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   147: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   152: astore_3       
        //   153: aload_3        
        //   154: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.DOC_COMMENT_DATA:Lcom/intellij/psi/tree/IElementType;
        //   157: if_acmpeq       174
        //   160: aload_3        
        //   161: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.TAG_OPTION:Lcom/intellij/psi/tree/IElementType;
        //   164: if_acmpne       241
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   173: athrow         
        //   174: aload_1        
        //   175: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   180: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   183: astore          4
        //   185: aload_2        
        //   186: aload           4
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: pop            
        //   192: aload_0        
        //   193: aload_0        
        //   194: aload_1        
        //   195: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment.nextSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   198: aload_2        
        //   199: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment;Lcom/intellij/psi/PsiElement;Ljava/lang/StringBuilder;)Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result;
        //   202: dup            
        //   203: ifnonnull       240
        //   206: new             Ljava/lang/IllegalStateException;
        //   209: dup            
        //   210: ldc             "@NotNull method %s.%s must not return null"
        //   212: ldc             2
        //   214: anewarray       Ljava/lang/Object;
        //   217: dup            
        //   218: ldc             0
        //   220: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   222: aastore        
        //   223: dup            
        //   224: ldc             1
        //   226: ldc             "processDocData"
        //   228: aastore        
        //   229: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   232: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   235: athrow         
        //   236: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   239: athrow         
        //   240: areturn        
        //   241: aload_2        
        //   242: ldc             " "
        //   244: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   247: pop            
        //   248: aload_1        
        //   249: ifnull          507
        //   252: aload_1        
        //   253: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   258: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   263: astore          4
        //   265: aload           4
        //   267: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.DOC_TAG:Lcom/intellij/psi/tree/IElementType;
        //   270: if_acmpne       328
        //   273: new             Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result;
        //   276: dup            
        //   277: aload_1        
        //   278: iconst_1       
        //   279: invokespecial   com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result.<init>:(Lcom/intellij/psi/PsiElement;Z)V
        //   282: dup            
        //   283: ifnonnull       327
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   292: athrow         
        //   293: new             Ljava/lang/IllegalStateException;
        //   296: dup            
        //   297: ldc             "@NotNull method %s.%s must not return null"
        //   299: ldc             2
        //   301: anewarray       Ljava/lang/Object;
        //   304: dup            
        //   305: ldc             0
        //   307: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   309: aastore        
        //   310: dup            
        //   311: ldc             1
        //   313: ldc             "processDocData"
        //   315: aastore        
        //   316: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   319: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   322: athrow         
        //   323: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   326: athrow         
        //   327: areturn        
        //   328: aload           4
        //   330: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.DOC_COMMENT_DATA:Lcom/intellij/psi/tree/IElementType;
        //   333: if_acmpeq       351
        //   336: aload           4
        //   338: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.TAG_OPTION:Lcom/intellij/psi/tree/IElementType;
        //   341: if_acmpne       403
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   350: athrow         
        //   351: aload_0        
        //   352: aload_1        
        //   353: aload_2        
        //   354: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment;Lcom/intellij/psi/PsiElement;Ljava/lang/StringBuilder;)Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result;
        //   357: dup            
        //   358: ifnonnull       402
        //   361: goto            368
        //   364: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   367: athrow         
        //   368: new             Ljava/lang/IllegalStateException;
        //   371: dup            
        //   372: ldc             "@NotNull method %s.%s must not return null"
        //   374: ldc             2
        //   376: anewarray       Ljava/lang/Object;
        //   379: dup            
        //   380: ldc             0
        //   382: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   384: aastore        
        //   385: dup            
        //   386: ldc             1
        //   388: ldc             "processDocData"
        //   390: aastore        
        //   391: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   394: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   397: athrow         
        //   398: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   401: athrow         
        //   402: areturn        
        //   403: aload           4
        //   405: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.EOF_DOC_COMMENT_START:Lcom/intellij/psi/tree/IElementType;
        //   408: if_acmpeq       426
        //   411: aload           4
        //   413: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   416: if_acmpne       498
        //   419: goto            426
        //   422: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   425: athrow         
        //   426: aload_0        
        //   427: aload_1        
        //   428: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result;
        //   431: astore          5
        //   433: aload           5
        //   435: getfield        com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result.proceed:Z
        //   438: ifne            489
        //   441: aload           5
        //   443: dup            
        //   444: ifnonnull       488
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   453: athrow         
        //   454: new             Ljava/lang/IllegalStateException;
        //   457: dup            
        //   458: ldc             "@NotNull method %s.%s must not return null"
        //   460: ldc             2
        //   462: anewarray       Ljava/lang/Object;
        //   465: dup            
        //   466: ldc             0
        //   468: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   470: aastore        
        //   471: dup            
        //   472: ldc             1
        //   474: ldc             "processDocData"
        //   476: aastore        
        //   477: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   480: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   483: athrow         
        //   484: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   487: athrow         
        //   488: areturn        
        //   489: aload           5
        //   491: getfield        com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result.element:Lcom/intellij/psi/PsiElement;
        //   494: astore_1       
        //   495: goto            248
        //   498: aload_0        
        //   499: aload_1        
        //   500: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment.nextSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   503: astore_1       
        //   504: goto            248
        //   507: getstatic       com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.NONE:Lcom/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Result;
        //   510: dup            
        //   511: ifnonnull       548
        //   514: new             Ljava/lang/IllegalStateException;
        //   517: dup            
        //   518: ldc             "@NotNull method %s.%s must not return null"
        //   520: ldc             2
        //   522: anewarray       Ljava/lang/Object;
        //   525: dup            
        //   526: ldc             0
        //   528: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   530: aastore        
        //   531: dup            
        //   532: ldc             1
        //   534: ldc             "processDocData"
        //   536: aastore        
        //   537: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   540: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   543: athrow         
        //   544: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   547: athrow         
        //   548: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     99     102    106    Ljava/lang/IllegalStateException;
        //  92     136    136    140    Ljava/lang/IllegalStateException;
        //  153    167    170    174    Ljava/lang/IllegalStateException;
        //  185    236    236    240    Ljava/lang/IllegalStateException;
        //  265    286    289    293    Ljava/lang/IllegalStateException;
        //  273    323    323    327    Ljava/lang/IllegalStateException;
        //  328    344    347    351    Ljava/lang/IllegalStateException;
        //  336    361    364    368    Ljava/lang/IllegalStateException;
        //  351    398    398    402    Ljava/lang/IllegalStateException;
        //  403    419    422    426    Ljava/lang/IllegalStateException;
        //  433    447    450    454    Ljava/lang/IllegalStateException;
        //  441    484    484    488    Ljava/lang/IllegalStateException;
        //  507    544    544    548    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0351:
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
    
    @NotNull
    private static Result a(@NotNull final Comment comment, @NotNull PsiElement psiElement, @NotNull final DoxygenCommentGroupImpl.Builder builder) {
        try {
            if (comment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (builder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final DxDocTag dxDocTag = (DxDocTag)psiElement;
        final String name = dxDocTag.getName();
        if (DoxygenCmd.SURROUND_TAGS.containsKey(name)) {
            psiElement = a(comment, psiElement, (ComplexItemBuilder)builder);
            Result result;
            try {
                result = new Result(psiElement, true);
                if (result == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            return result;
        }
        if (DoxygenCmd.SURROUND_TAGS.containsValue(name)) {
            psiElement = comment.nextSibling(psiElement);
            Result result2;
            try {
                result2 = new Result(psiElement, true);
                if (result2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            return result2;
        }
        final DoxygenCommentTagImpl doxygenCommentTagImpl = new DoxygenCommentTagImpl(dxDocTag);
        builder.addTag(doxygenCommentTagImpl);
        psiElement = comment.nextSibling(psiElement);
        while (psiElement != null) {
            final IElementType elementType = psiElement.getNode().getElementType();
            Result result5 = null;
            Label_0501: {
                Label_0641: {
                    Label_0549: {
                        Label_0463: {
                            Label_0435: {
                                try {
                                    if (elementType != DxTypes.DOC_COMMENT_DATA) {
                                        if (elementType != DxTypes.TAG_OPTION) {
                                            break Label_0435;
                                        }
                                    }
                                }
                                catch (IllegalStateException ex6) {
                                    throw a(ex6);
                                }
                                final StringBuilder sb = new StringBuilder();
                                final Result a = a(comment, psiElement, sb);
                                doxygenCommentTagImpl.addText(sb.toString().trim());
                                psiElement = a.element;
                                try {
                                    if (a.proceed) {
                                        continue;
                                    }
                                }
                                catch (IllegalStateException ex7) {
                                    throw a(ex7);
                                }
                                Result result3;
                                try {
                                    result3 = a;
                                    if (result3 == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
                                    }
                                }
                                catch (IllegalStateException ex8) {
                                    throw a(ex8);
                                }
                                return result3;
                                try {
                                    if (elementType != DxTypes.DOC_TAG) {
                                        break Label_0549;
                                    }
                                    final Set<String> set = DxCommentProcessor.PLAIN_TAGS;
                                    final String s = name;
                                    final boolean b = set.contains(s);
                                    if (!b) {
                                        break Label_0463;
                                    }
                                    break Label_0501;
                                }
                                catch (IllegalStateException ex9) {
                                    throw a(ex9);
                                }
                            }
                            try {
                                final Set<String> set = DxCommentProcessor.PLAIN_TAGS;
                                final String s = name;
                                final boolean b = set.contains(s);
                                if (b) {
                                    break Label_0501;
                                }
                                if (!DoxygenCmd.SURROUND_TAGS.containsKey(((DxDocTag)psiElement).getName())) {
                                    break Label_0501;
                                }
                            }
                            catch (IllegalStateException ex10) {
                                throw a(ex10);
                            }
                        }
                        psiElement = a(comment, psiElement, doxygenCommentTagImpl);
                        continue;
                        try {
                            if (elementType != DxTypes.EOF_DOC_COMMENT_START) {
                                if (elementType != TokenType.WHITE_SPACE) {
                                    break Label_0641;
                                }
                            }
                        }
                        catch (IllegalStateException ex11) {
                            throw a(ex11);
                        }
                    }
                    final Result a2 = a(comment, psiElement);
                    psiElement = a2.element;
                    try {
                        if (a2.proceed) {
                            continue;
                        }
                    }
                    catch (IllegalStateException ex12) {
                        throw a(ex12);
                    }
                    Result result4;
                    try {
                        result4 = a2;
                        if (result4 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
                        }
                    }
                    catch (IllegalStateException ex13) {
                        throw a(ex13);
                    }
                    return result4;
                }
                psiElement = comment.nextSibling(psiElement);
                continue;
                try {
                    result5 = new Result(psiElement, true);
                    if (result5 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
                    }
                }
                catch (IllegalStateException ex14) {
                    throw a(ex14);
                }
            }
            return result5;
        }
        Result none;
        try {
            none = DxCommentProcessor.NONE;
            if (none == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processTag"));
            }
        }
        catch (IllegalStateException ex15) {
            throw a(ex15);
        }
        return none;
    }
    
    @Nullable
    private static PsiElement a(@NotNull final Comment p0, @NotNull final PsiElement p1, @NotNull final ComplexItemBuilder p2) {
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
        //    18: ldc             "comment"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processSurroundTag"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processSurroundTag"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "builder"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processSurroundTag"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: new             Lcom/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl;
        //   135: dup            
        //   136: aload_1        
        //   137: checkcast       Lcom/jetbrains/cidr/doxygen/psi/DxDocTag;
        //   140: invokespecial   com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl.<init>:(Lcom/jetbrains/cidr/doxygen/psi/DxDocTag;)V
        //   143: astore_3       
        //   144: getstatic       com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.SURROUND_TAGS:Ljava/util/Map;
        //   147: aload_3        
        //   148: invokevirtual   com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl.getName:()Ljava/lang/String;
        //   151: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   156: checkcast       Ljava/lang/String;
        //   159: astore          4
        //   161: aload_2        
        //   162: aload_3        
        //   163: invokeinterface com/jetbrains/cidr/doxygen/comment/ComplexItemBuilder.addTag:(Lcom/jetbrains/cidr/lang/documentation/doxygen/api/TagItem;)V
        //   168: new             Ljava/lang/StringBuilder;
        //   171: dup            
        //   172: invokespecial   java/lang/StringBuilder.<init>:()V
        //   175: astore          5
        //   177: aload_0        
        //   178: aload_1        
        //   179: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment.nextSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   182: astore_1       
        //   183: aload_1        
        //   184: ifnull          414
        //   187: aload_1        
        //   188: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   193: astore          6
        //   195: aload_1        
        //   196: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   201: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   206: astore          7
        //   208: aload           7
        //   210: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   213: if_acmpne       305
        //   216: aload           6
        //   218: invokestatic    com/intellij/openapi/util/text/StringUtil.getLineBreakCount:(Ljava/lang/CharSequence;)I
        //   221: istore          8
        //   223: iload           8
        //   225: ifle            294
        //   228: aload           6
        //   230: ldc             "*"
        //   232: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   235: ifeq            294
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   244: athrow         
        //   245: iconst_0       
        //   246: istore          9
        //   248: iload           9
        //   250: iload           8
        //   252: if_icmpge       291
        //   255: aload           5
        //   257: invokevirtual   java/lang/StringBuilder.length:()I
        //   260: ifle            285
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   269: athrow         
        //   270: aload           5
        //   272: ldc             "\n"
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: pop            
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   284: athrow         
        //   285: iinc            9, 1
        //   288: goto            248
        //   291: goto            302
        //   294: aload           5
        //   296: aload           6
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   301: pop            
        //   302: goto            405
        //   305: aload           7
        //   307: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.EOF_DOC_COMMENT_START:Lcom/intellij/psi/tree/IElementType;
        //   310: if_acmpne       343
        //   313: aload           5
        //   315: invokevirtual   java/lang/StringBuilder.length:()I
        //   318: ifle            405
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   327: athrow         
        //   328: aload           5
        //   330: ldc             "\n"
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: pop            
        //   336: goto            405
        //   339: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   342: athrow         
        //   343: aload           7
        //   345: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.DOC_TAG:Lcom/intellij/psi/tree/IElementType;
        //   348: if_acmpne       375
        //   351: aload           4
        //   353: aload_1        
        //   354: checkcast       Lcom/jetbrains/cidr/doxygen/psi/DxDocTag;
        //   357: invokeinterface com/jetbrains/cidr/doxygen/psi/DxDocTag.getName:()Ljava/lang/String;
        //   362: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   365: ifne            414
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   374: athrow         
        //   375: aload           7
        //   377: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.DOC_COMMENT_END:Lcom/intellij/psi/tree/IElementType;
        //   380: if_acmpne       397
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   389: athrow         
        //   390: goto            414
        //   393: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   396: athrow         
        //   397: aload           5
        //   399: aload           6
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: pop            
        //   405: aload_0        
        //   406: aload_1        
        //   407: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment.nextSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   410: astore_1       
        //   411: goto            183
        //   414: aload           5
        //   416: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   419: invokestatic    com/intellij/openapi/util/text/StringUtil.trimTrailing:(Ljava/lang/String;)Ljava/lang/String;
        //   422: astore          6
        //   424: aload_3        
        //   425: aload           6
        //   427: ldc             "\n"
        //   429: invokestatic    com/intellij/openapi/util/text/StringUtil.trimStart:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   432: invokevirtual   com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl.addText:(Ljava/lang/String;)V
        //   435: aload_1        
        //   436: ifnull          449
        //   439: aload_0        
        //   440: aload_1        
        //   441: invokevirtual   com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment.nextSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   444: areturn        
        //   445: invokestatic    com/jetbrains/cidr/doxygen/comment/DxCommentProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   448: athrow         
        //   449: aconst_null    
        //   450: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     128    128    132    Ljava/lang/IllegalStateException;
        //  223    238    241    245    Ljava/lang/IllegalStateException;
        //  248    263    266    270    Ljava/lang/IllegalStateException;
        //  255    278    281    285    Ljava/lang/IllegalStateException;
        //  305    321    324    328    Ljava/lang/IllegalStateException;
        //  313    339    339    343    Ljava/lang/IllegalStateException;
        //  343    368    371    375    Ljava/lang/IllegalStateException;
        //  351    383    386    390    Ljava/lang/IllegalStateException;
        //  375    393    393    397    Ljava/lang/IllegalStateException;
        //  424    445    445    449    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0375:
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
    
    @NotNull
    private static Result a(@NotNull final Comment comment, @NotNull PsiElement nextSibling) {
        try {
            if (comment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processWS"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (nextSibling == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processWS"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        int n = 0;
        while (nextSibling != null) {
            final IElementType elementType = nextSibling.getNode().getElementType();
            if (elementType == TokenType.WHITE_SPACE) {
                n += StringUtil.getLineBreakCount((CharSequence)nextSibling.getText());
            }
            else {
                try {
                    if (elementType != DxTypes.EOF_DOC_COMMENT_START) {
                        break;
                    }
                    ++n;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            nextSibling = comment.nextSibling(nextSibling);
        }
        boolean b = false;
        Label_0168: {
            try {
                if (n <= 1) {
                    b = true;
                    break Label_0168;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            b = false;
        }
        final boolean b2 = b;
        Result result;
        try {
            result = new Result(nextSibling, b2);
            if (result == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor", "processWS"));
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return result;
    }
    
    static {
        PLAIN_TAGS = ContainerUtil.set((Object[])new String[] { DoxygenCmd.BRIEF.toString() });
        NONE = new Result(null, true);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private static class Result
    {
        public final PsiElement element;
        public final boolean proceed;
        
        public Result(final PsiElement element, final boolean proceed) {
            this.element = element;
            this.proceed = proceed;
        }
    }
    
    private static class Comment
    {
        private int idx;
        private final List<PsiComment> comments;
        
        public Comment(@NotNull final List<PsiComment> comments) {
            if (comments == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment", "<init>"));
            }
            this.idx = 0;
            this.comments = comments;
        }
        
        @Nullable
        public PsiElement getFistChild() {
            return this.a(0);
        }
        
        @Nullable
        private PsiElement a(final int n) {
            if (n < this.comments.size()) {
                final DxDocComment convertToDoxygen = DoxygenUtil.convertToDoxygen(this.comments.get(n));
                try {
                    if (convertToDoxygen != null) {
                        return convertToDoxygen.getFirstChild();
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            return null;
        }
        
        public PsiElement nextSibling(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment", "nextSibling"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiElement nextSibling = psiElement.getNextSibling();
            try {
                if (nextSibling == null) {
                    return this.a(++this.idx);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return nextSibling;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
