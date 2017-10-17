// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCForStatement;

public class OCForStatementImpl extends OCElementBase implements OCForStatement
{
    public OCForStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCStatement getInitializer() {
        final OCElement a = this.a(0);
        try {
            if (a instanceof OCStatement) {
                return (OCStatement)a;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    public PsiElement getExpression() {
        return null;
    }
    
    @Nullable
    @Override
    public OCDeclarationOrExpression getCondition() {
        final OCElement a = this.a(1);
        try {
            if (a instanceof OCDeclarationOrExpression) {
                return (OCDeclarationOrExpression)a;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCStatement getIncrement() {
        return (OCStatement)this.a(2);
    }
    
    @Nullable
    public ASTNode getLParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.LPAR);
    }
    
    @Nullable
    public ASTNode getRParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.RPAR);
    }
    
    @Nullable
    private OCElement a(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_2       
        //    10: aload_2        
        //    11: ifnull          139
        //    14: aload_2        
        //    15: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    20: astore_3       
        //    21: aload_3        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    25: if_acmpne       35
        //    28: goto            139
        //    31: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_3        
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    39: if_acmpne       63
        //    42: iinc            1, -1
        //    45: iload_1        
        //    46: ifge            63
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: goto            139
        //    59: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iload_1        
        //    64: ifne            129
        //    67: aload_3        
        //    68: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION_OR_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    71: if_acmpeq       115
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //    84: aload_3        
        //    85: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    88: ifne            115
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   101: aload_3        
        //   102: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   105: ifeq            129
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_2        
        //   116: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   121: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   124: areturn        
        //   125: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aload_2        
        //   130: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   135: astore_2       
        //   136: goto            10
        //   139: aconst_null    
        //   140: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  21     31     31     35     Ljava/lang/IllegalArgumentException;
        //  35     49     52     56     Ljava/lang/IllegalArgumentException;
        //  42     59     59     63     Ljava/lang/IllegalArgumentException;
        //  63     74     77     81     Ljava/lang/IllegalArgumentException;
        //  67     91     94     98     Ljava/lang/IllegalArgumentException;
        //  81     108    111    115    Ljava/lang/IllegalArgumentException;
        //  98     125    125    129    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0081:
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
    public OCElementType getKeywordType() {
        OCElementType for_KEYWORD;
        try {
            for_KEYWORD = OCTokenTypes.FOR_KEYWORD;
            if (for_KEYWORD == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl", "getKeywordType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return for_KEYWORD;
    }
    
    public OCStatement getBody() {
        ASTNode astNode = this.getNode().getFirstChildNode();
        boolean b = false;
        while (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            Label_0070: {
                if (elementType == OCTokenTypes.RPAR) {
                    b = true;
                }
                else {
                    Label_0056: {
                        try {
                            if (!b) {
                                break Label_0070;
                            }
                            final TokenSet set = OCElementTypes.STATEMENTS;
                            final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                            final boolean b2 = set.contains((IElementType)ocPunctuatorElementType);
                            if (b2) {
                                break Label_0056;
                            }
                            break Label_0070;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final TokenSet set = OCElementTypes.STATEMENTS;
                            final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                            final boolean b2 = set.contains((IElementType)ocPunctuatorElementType);
                            if (b2) {
                                return (OCStatement)astNode.getPsi();
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                }
            }
            astNode = astNode.getTreeNext();
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCForStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitForStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
