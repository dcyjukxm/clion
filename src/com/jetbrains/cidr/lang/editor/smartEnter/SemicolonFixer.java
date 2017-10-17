// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class SemicolonFixer extends OCFixer
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
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "apply"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "apply"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "apply"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   138: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   141: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findParent:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   144: ifnull          152
        //   147: return         
        //   148: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   151: athrow         
        //   152: aload_3        
        //   153: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   156: ifeq            273
        //   159: aload_3        
        //   160: iconst_2       
        //   161: anewarray       Ljava/lang/Class;
        //   164: dup            
        //   165: iconst_0       
        //   166: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   168: aastore        
        //   169: dup            
        //   170: iconst_1       
        //   171: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   173: aastore        
        //   174: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   177: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   180: astore          4
        //   182: aload           4
        //   184: ifnull          273
        //   187: aload           4
        //   189: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   194: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   197: ifeq            273
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   206: athrow         
        //   207: aload_3        
        //   208: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   211: astore          5
        //   213: aload           5
        //   215: invokeinterface com/jetbrains/cidr/lang/psi/OCReturnStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   220: ifnull          273
        //   223: aload           5
        //   225: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RETURN_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   228: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.hasMacroBasedStatement:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   231: ifne            273
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   240: athrow         
        //   241: aload_1        
        //   242: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   247: astore          6
        //   249: aload           6
        //   251: aload           5
        //   253: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   256: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   259: ldc             "return"
        //   261: invokevirtual   java/lang/String.length:()I
        //   264: iadd           
        //   265: ldc             ";"
        //   267: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   272: return         
        //   273: aload_3        
        //   274: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/psi/PsiElement;)Z
        //   277: ifeq            474
        //   280: aload_3        
        //   281: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   284: ifeq            320
        //   287: goto            294
        //   290: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   293: athrow         
        //   294: aload_3        
        //   295: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   300: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   303: ifeq            320
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   312: athrow         
        //   313: aload_3        
        //   314: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   319: astore_3       
        //   320: aload_3        
        //   321: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   326: astore          4
        //   328: aload           4
        //   330: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   333: ifne            429
        //   336: aload           4
        //   338: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   341: ifne            429
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   350: athrow         
        //   351: aload           4
        //   353: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   356: ifeq            394
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   365: athrow         
        //   366: aload           4
        //   368: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   373: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   376: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   381: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/lang/ASTNode;)Z
        //   384: ifne            429
        //   387: goto            394
        //   390: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   393: athrow         
        //   394: aload           4
        //   396: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   399: ifeq            434
        //   402: goto            409
        //   405: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   408: athrow         
        //   409: aload           4
        //   411: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   416: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/lang/ASTNode;)Z
        //   419: ifeq            434
        //   422: goto            429
        //   425: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   428: athrow         
        //   429: return         
        //   430: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   433: athrow         
        //   434: aload_3        
        //   435: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   438: ifeq            468
        //   441: aload_3        
        //   442: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   447: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   450: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findParent:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   453: astore          5
        //   455: aload           5
        //   457: ifnull          468
        //   460: aload           5
        //   462: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   467: astore_3       
        //   468: aload_1        
        //   469: aload_3        
        //   470: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.fixSemicolonAtTheEnd:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)Z
        //   473: pop            
        //   474: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    148    148    152    Lcom/intellij/util/IncorrectOperationException;
        //  182    200    203    207    Lcom/intellij/util/IncorrectOperationException;
        //  213    234    237    241    Lcom/intellij/util/IncorrectOperationException;
        //  273    287    290    294    Lcom/intellij/util/IncorrectOperationException;
        //  280    306    309    313    Lcom/intellij/util/IncorrectOperationException;
        //  328    344    347    351    Lcom/intellij/util/IncorrectOperationException;
        //  336    359    362    366    Lcom/intellij/util/IncorrectOperationException;
        //  351    387    390    394    Lcom/intellij/util/IncorrectOperationException;
        //  366    402    405    409    Lcom/intellij/util/IncorrectOperationException;
        //  394    422    425    429    Lcom/intellij/util/IncorrectOperationException;
        //  409    430    430    434    Lcom/intellij/util/IncorrectOperationException;
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
    
    private static boolean a(@NotNull final PsiElement p0) {
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
        //    18: ldc             "psiElement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "canNeedSemicolon"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDoWhileStatement;
        //    48: ifeq            60
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DoWhileConditionFixer.applicable:(Lcom/intellij/psi/PsiElement;)Z
        //    55: ireturn        
        //    56: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    59: athrow         
        //    60: aload_0        
        //    61: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    64: ifeq            121
        //    67: aload_0        
        //    68: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    71: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //    76: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.isRealLeafElement:(Lcom/intellij/psi/PsiElement;)Z
        //    79: ifeq            119
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    88: athrow         
        //    89: aload_0        
        //    90: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //    98: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.isRealLeafElement:(Lcom/intellij/psi/PsiElement;)Z
        //   101: ifeq            119
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   110: athrow         
        //   111: iconst_1       
        //   112: goto            120
        //   115: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   118: athrow         
        //   119: iconst_0       
        //   120: ireturn        
        //   121: aload_0        
        //   122: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   125: ifeq            145
        //   128: aload_0        
        //   129: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   132: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getContainingClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   137: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   140: ireturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   144: athrow         
        //   145: aload_0        
        //   146: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isMissingSemicolonError:(Lcom/intellij/psi/PsiElement;)Z
        //   149: ifne            264
        //   152: aload_0        
        //   153: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   156: ifne            264
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   165: athrow         
        //   166: aload_0        
        //   167: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   170: ifne            264
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   179: athrow         
        //   180: aload_0        
        //   181: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   184: ifne            264
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   193: athrow         
        //   194: aload_0        
        //   195: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBreakStatement;
        //   198: ifne            264
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   207: athrow         
        //   208: aload_0        
        //   209: instanceof      Lcom/jetbrains/cidr/lang/psi/OCContinueStatement;
        //   212: ifne            264
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   221: athrow         
        //   222: aload_0        
        //   223: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceAlias;
        //   226: ifne            264
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   235: athrow         
        //   236: aload_0        
        //   237: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //   240: ifne            264
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   249: athrow         
        //   250: aload_0        
        //   251: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppStaticAssert;
        //   254: ifeq            272
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   263: athrow         
        //   264: iconst_1       
        //   265: goto            273
        //   268: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   271: athrow         
        //   272: iconst_0       
        //   273: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     56     56     60     Lcom/intellij/util/IncorrectOperationException;
        //  60     82     85     89     Lcom/intellij/util/IncorrectOperationException;
        //  67     104    107    111    Lcom/intellij/util/IncorrectOperationException;
        //  89     115    115    119    Lcom/intellij/util/IncorrectOperationException;
        //  121    141    141    145    Lcom/intellij/util/IncorrectOperationException;
        //  145    159    162    166    Lcom/intellij/util/IncorrectOperationException;
        //  152    173    176    180    Lcom/intellij/util/IncorrectOperationException;
        //  166    187    190    194    Lcom/intellij/util/IncorrectOperationException;
        //  180    201    204    208    Lcom/intellij/util/IncorrectOperationException;
        //  194    215    218    222    Lcom/intellij/util/IncorrectOperationException;
        //  208    229    232    236    Lcom/intellij/util/IncorrectOperationException;
        //  222    243    246    250    Lcom/intellij/util/IncorrectOperationException;
        //  236    257    260    264    Lcom/intellij/util/IncorrectOperationException;
        //  250    268    268    272    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
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
    
    @Contract("null -> false")
    private static boolean a(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode childByType = astNode.findChildByType(OCElementTypes.STRUCTURE_TYPES);
        Label_0043: {
            try {
                if (childByType == null) {
                    return false;
                }
                final ASTNode astNode2 = childByType;
                final PsiElement psiElement = astNode2.getPsi();
                final boolean b = b(psiElement);
                if (b) {
                    break Label_0043;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode2 = childByType;
                final PsiElement psiElement = astNode2.getPsi();
                final boolean b = b(psiElement);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
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
        //    18: ldc             "structLikePsi"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isIncompleteStruct"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    48: ifeq            111
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.isLikeStructVarDeclaration:(Lcom/intellij/psi/PsiElement;)Z
        //    55: ifne            111
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    64: athrow         
        //    65: aload_0        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    69: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //    74: ifnonnull       111
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    83: athrow         
        //    84: aload_0        
        //    85: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    88: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //    93: ifnonnull       111
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   102: athrow         
        //   103: iconst_1       
        //   104: goto            112
        //   107: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/SemicolonFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   110: athrow         
        //   111: iconst_0       
        //   112: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     58     61     65     Lcom/intellij/util/IncorrectOperationException;
        //  51     77     80     84     Lcom/intellij/util/IncorrectOperationException;
        //  65     96     99     103    Lcom/intellij/util/IncorrectOperationException;
        //  84     107    107    111    Lcom/intellij/util/IncorrectOperationException;
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
