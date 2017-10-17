// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class MissingClassNamespaceBodyFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor p0, @NotNull final OCSmartEnterProcessor p1, @NotNull final PsiElement p2) throws IncorrectOperationException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "apply"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "processor"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "apply"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "psiElement"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "apply"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isMissingSemicolonError:(Lcom/intellij/psi/PsiElement;)Z
        //   136: ifeq            204
        //   139: aload_3        
        //   140: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   145: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   148: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   151: if_acmpne       204
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   160: athrow         
        //   161: aload_3        
        //   162: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   167: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   172: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   177: ifnull          204
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   186: athrow         
        //   187: aload_3        
        //   188: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   193: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   198: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   203: astore_3       
        //   204: aload_3        
        //   205: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   208: istore          4
        //   210: iload           4
        //   212: ifne            229
        //   215: aload_3        
        //   216: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   219: ifeq            687
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   228: athrow         
        //   229: aload_3        
        //   230: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //   233: astore          5
        //   235: aload_3        
        //   236: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   239: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   244: astore          6
        //   246: aload           6
        //   248: ifnull          687
        //   251: aload           5
        //   253: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //   258: astore          7
        //   260: aload           5
        //   262: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //   267: astore          8
        //   269: aload           7
        //   271: ifnonnull       386
        //   274: aload           8
        //   276: ifnonnull       386
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   285: athrow         
        //   286: iload           4
        //   288: ifeq            386
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   297: athrow         
        //   298: aload_1        
        //   299: aload           5
        //   301: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.getSemicolonInsertionOffset:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   304: iconst_m1      
        //   305: if_icmpeq       381
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   314: athrow         
        //   315: aload           5
        //   317: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.isLikeStructVarDeclaration:(Lcom/intellij/psi/PsiElement;)Z
        //   320: ifne            381
        //   323: goto            330
        //   326: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   329: athrow         
        //   330: aload           5
        //   332: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getNode:()Lcom/intellij/lang/ASTNode;
        //   337: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.getPrevEssentialLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   340: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   343: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEDEF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   346: if_acmpeq       381
        //   349: goto            356
        //   352: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   355: athrow         
        //   356: aload           5
        //   358: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   363: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   368: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   371: ifeq            386
        //   374: goto            381
        //   377: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   380: athrow         
        //   381: return         
        //   382: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   385: athrow         
        //   386: iconst_1       
        //   387: newarray        I
        //   389: dup            
        //   390: iconst_0       
        //   391: aload           5
        //   393: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   396: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   399: iastore        
        //   400: astore          9
        //   402: iload           4
        //   404: ifeq            565
        //   407: aload_3        
        //   408: ldc             Lcom/intellij/psi/PsiErrorElement;.class
        //   410: invokestatic    com/intellij/psi/util/PsiTreeUtil.findChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   413: checkcast       Lcom/intellij/psi/PsiErrorElement;
        //   416: astore          10
        //   418: aload           10
        //   420: ifnull          565
        //   423: aload           10
        //   425: ldc             "Base clause element expected"
        //   427: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   430: istore          11
        //   432: iload           11
        //   434: ifeq            484
        //   437: aload           10
        //   439: invokeinterface com/intellij/psi/PsiErrorElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   444: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.getPrevEssentialLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   447: astore          12
        //   449: aload           12
        //   451: ifnull          481
        //   454: aload           9
        //   456: iconst_0       
        //   457: aload           12
        //   459: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   462: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   465: iastore        
        //   466: aload_2        
        //   467: aload           9
        //   469: iconst_0       
        //   470: iaload         
        //   471: invokevirtual   com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.registerUnresolvedError:(I)V
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   480: athrow         
        //   481: goto            565
        //   484: aload           10
        //   486: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isMissingSemicolonError:(Lcom/intellij/psi/PsiElement;)Z
        //   489: ifne            565
        //   492: aload           10
        //   494: ldc             "Unexpected end of file"
        //   496: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   499: ifne            565
        //   502: goto            509
        //   505: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   508: athrow         
        //   509: aload           10
        //   511: ldc             "Syntax error"
        //   513: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   516: ifne            565
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   525: athrow         
        //   526: aload           10
        //   528: ldc             "Expecting '{'"
        //   530: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   533: ifne            565
        //   536: goto            543
        //   539: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   542: athrow         
        //   543: aload           10
        //   545: ldc             "} expected"
        //   547: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   550: ifne            565
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   559: athrow         
        //   560: return         
        //   561: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   564: athrow         
        //   565: aload_1        
        //   566: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   571: astore          10
        //   573: aload           7
        //   575: ifnonnull       679
        //   578: iload           4
        //   580: ifeq            634
        //   583: goto            590
        //   586: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   589: athrow         
        //   590: aload           5
        //   592: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getNode:()Lcom/intellij/lang/ASTNode;
        //   597: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.endsTheLine:(Lcom/intellij/lang/ASTNode;)Z
        //   600: ifne            634
        //   603: goto            610
        //   606: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   609: athrow         
        //   610: aload           5
        //   612: new             Lcom/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer$1;
        //   615: dup            
        //   616: aload_0        
        //   617: aload           9
        //   619: invokespecial   com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer$1.<init>:(Lcom/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer;[I)V
        //   622: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   627: goto            634
        //   630: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   633: athrow         
        //   634: aload_1        
        //   635: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   640: aload           9
        //   642: iconst_0       
        //   643: iaload         
        //   644: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   649: aload           10
        //   651: aload           9
        //   653: iconst_0       
        //   654: iaload         
        //   655: iload           4
        //   657: ifeq            669
        //   660: ldc             "{};"
        //   662: goto            671
        //   665: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   668: athrow         
        //   669: ldc             "{}"
        //   671: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   676: goto            687
        //   679: aload_1        
        //   680: aload           5
        //   682: iconst_0       
        //   683: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingClassNamespaceBodyFixer.fixBlockIfNeed:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;Z)Z
        //   686: pop            
        //   687: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    154    157    161    Lcom/intellij/util/IncorrectOperationException;
        //  139    180    183    187    Lcom/intellij/util/IncorrectOperationException;
        //  210    222    225    229    Lcom/intellij/util/IncorrectOperationException;
        //  269    279    282    286    Lcom/intellij/util/IncorrectOperationException;
        //  274    291    294    298    Lcom/intellij/util/IncorrectOperationException;
        //  286    308    311    315    Lcom/intellij/util/IncorrectOperationException;
        //  298    323    326    330    Lcom/intellij/util/IncorrectOperationException;
        //  315    349    352    356    Lcom/intellij/util/IncorrectOperationException;
        //  330    374    377    381    Lcom/intellij/util/IncorrectOperationException;
        //  356    382    382    386    Lcom/intellij/util/IncorrectOperationException;
        //  449    474    477    481    Lcom/intellij/util/IncorrectOperationException;
        //  484    502    505    509    Lcom/intellij/util/IncorrectOperationException;
        //  492    519    522    526    Lcom/intellij/util/IncorrectOperationException;
        //  509    536    539    543    Lcom/intellij/util/IncorrectOperationException;
        //  526    553    556    560    Lcom/intellij/util/IncorrectOperationException;
        //  543    561    561    565    Lcom/intellij/util/IncorrectOperationException;
        //  573    583    586    590    Lcom/intellij/util/IncorrectOperationException;
        //  578    603    606    610    Lcom/intellij/util/IncorrectOperationException;
        //  590    627    630    634    Lcom/intellij/util/IncorrectOperationException;
        //  634    665    665    669    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0286:
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
