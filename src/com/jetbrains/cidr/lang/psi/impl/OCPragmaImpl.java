// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCPragma;

public class OCPragmaImpl extends OCDirectiveImpl implements OCPragma
{
    public OCPragmaImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public Pair<Mode, String> parsePragma() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.getHeaderToken:()Lcom/intellij/psi/PsiElement;
        //     6: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    11: astore_2       
        //    12: aload_2        
        //    13: ifnull          292
        //    16: aload_2        
        //    17: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //    20: ifeq            37
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: goto            282
        //    33: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_2        
        //    38: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    41: astore_3       
        //    42: iload_1        
        //    43: tableswitch {
        //                0: 68
        //                1: 105
        //                2: 121
        //          default: 277
        //        }
        //    68: aload_3        
        //    69: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_CLANG_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    72: if_acmpeq       279
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_3        
        //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_IDE_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    86: if_acmpne       103
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: goto            279
        //    99: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aconst_null    
        //   104: areturn        
        //   105: aload_3        
        //   106: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_DIAGNOSTIC_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   109: if_acmpne       119
        //   112: goto            279
        //   115: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aconst_null    
        //   120: areturn        
        //   121: aload_3        
        //   122: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_PUSH_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   125: if_acmpne       140
        //   128: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.PUSH:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   131: aconst_null    
        //   132: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   135: areturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_3        
        //   141: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_POP_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   144: if_acmpne       159
        //   147: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.POP:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   150: aconst_null    
        //   151: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   154: areturn        
        //   155: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_2        
        //   160: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getNextNonWhitespaceSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   163: astore          4
        //   165: aload           4
        //   167: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   170: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   173: if_acmpne       275
        //   176: aload           4
        //   178: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   183: ldc             "\\\n"
        //   185: ldc             ""
        //   187: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   190: invokestatic    com/intellij/openapi/util/text/StringUtil.unquoteString:(Ljava/lang/String;)Ljava/lang/String;
        //   193: astore          5
        //   195: aload_3        
        //   196: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_IGNORED_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   199: if_acmpne       215
        //   202: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.IGNORE:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   205: aload           5
        //   207: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   210: areturn        
        //   211: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_3        
        //   216: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_WARNING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   219: if_acmpne       235
        //   222: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.WARNING:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   225: aload           5
        //   227: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   230: areturn        
        //   231: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_3        
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_ERROR_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   239: if_acmpne       255
        //   242: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.ERROR:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   245: aload           5
        //   247: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   250: areturn        
        //   251: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: aload_3        
        //   256: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_FATAL_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   259: if_acmpne       275
        //   262: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.FATAL:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   265: aload           5
        //   267: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   270: areturn        
        //   271: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   274: athrow         
        //   275: aconst_null    
        //   276: areturn        
        //   277: aconst_null    
        //   278: areturn        
        //   279: iinc            1, 1
        //   282: aload_2        
        //   283: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   288: astore_2       
        //   289: goto            12
        //   292: aconst_null    
        //   293: areturn        
        //    Signature:
        //  ()Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     23     26     30     Ljava/lang/IllegalArgumentException;
        //  16     33     33     37     Ljava/lang/IllegalArgumentException;
        //  42     75     78     82     Ljava/lang/IllegalArgumentException;
        //  68     89     92     96     Ljava/lang/IllegalArgumentException;
        //  82     99     99     103    Ljava/lang/IllegalArgumentException;
        //  105    115    115    119    Ljava/lang/IllegalArgumentException;
        //  121    136    136    140    Ljava/lang/IllegalArgumentException;
        //  140    155    155    159    Ljava/lang/IllegalArgumentException;
        //  195    211    211    215    Ljava/lang/IllegalArgumentException;
        //  215    231    231    235    Ljava/lang/IllegalArgumentException;
        //  235    251    251    255    Ljava/lang/IllegalArgumentException;
        //  255    271    271    275    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCPragmaImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocVisitor.visitPragma(this);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
