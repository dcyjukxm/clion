// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;

public class OCForeachStatementImpl extends OCElementBase implements OCForeachStatement
{
    public OCForeachStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCElement getVariableExpression() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_1       
        //    10: aload_1        
        //    11: ifnull          117
        //    14: aload_1        
        //    15: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    20: astore_2       
        //    21: aload_2        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpne       49
        //    28: ldc             "in"
        //    30: aload_1        
        //    31: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    36: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    39: ifne            63
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_2        
        //    50: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    53: if_acmpne       69
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aconst_null    
        //    64: areturn        
        //    65: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //    72: aload_2        
        //    73: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    76: ifne            93
        //    79: aload_2        
        //    80: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSION_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    83: if_acmpne       107
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_1        
        //    94: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    99: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   102: areturn        
        //   103: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_1        
        //   108: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   113: astore_1       
        //   114: goto            10
        //   117: aconst_null    
        //   118: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  28     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     65     65     69     Ljava/lang/IllegalArgumentException;
        //  69     86     89     93     Ljava/lang/IllegalArgumentException;
        //  79     103    103    107    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
    public boolean isCpp11Foreach() {
        try {
            if (this.findChildByType(OCTokenTypes.COLON) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public OCElement getVariableDeclaration() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_1       
        //    10: aload_1        
        //    11: ifnull          114
        //    14: aload_1        
        //    15: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    20: astore_2       
        //    21: aload_2        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpne       49
        //    28: ldc             "in"
        //    30: aload_1        
        //    31: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    36: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    39: ifne            63
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_2        
        //    50: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    53: if_acmpne       69
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aconst_null    
        //    64: areturn        
        //    65: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_2        
        //    70: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    73: if_acmpeq       90
        //    76: aload_2        
        //    77: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    80: if_acmpne       104
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_1        
        //    91: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    96: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    99: areturn        
        //   100: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_1        
        //   105: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   110: astore_1       
        //   111: goto            10
        //   114: aconst_null    
        //   115: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  28     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     65     65     69     Ljava/lang/IllegalArgumentException;
        //  69     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     100    100    104    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
    public OCExpression getCollectionExpression() {
        ASTNode astNode = this.getNode().getFirstChildNode();
        boolean b = false;
        while (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            Label_0091: {
                Label_0070: {
                    Label_0051: {
                        try {
                            if (elementType != OCTokenTypes.IDENTIFIER) {
                                break Label_0051;
                            }
                            final String s = "in";
                            final ASTNode astNode2 = astNode;
                            final String s2 = astNode2.getText();
                            final boolean b2 = s.equals(s2);
                            if (!b2) {
                                break Label_0051;
                            }
                            break Label_0051;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final String s = "in";
                            final ASTNode astNode2 = astNode;
                            final String s2 = astNode2.getText();
                            final boolean b2 = s.equals(s2);
                            if (!b2) {
                                if (elementType != OCTokenTypes.COLON) {
                                    break Label_0070;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    b = true;
                    break Label_0091;
                    try {
                        if (!b) {
                            break Label_0091;
                        }
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                        final boolean b3 = set.contains((IElementType)ocPunctuatorElementType);
                        if (b3) {
                            break Label_0091;
                        }
                        break Label_0091;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final TokenSet set = OCElementTypes.EXPRESSIONS;
                    final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                    final boolean b3 = set.contains((IElementType)ocPunctuatorElementType);
                    if (b3) {
                        return (OCExpression)astNode.getPsi();
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            astNode = astNode.getTreeNext();
        }
        return null;
    }
    
    @Nullable
    @Override
    public PsiElement getCondition() {
        return null;
    }
    
    @Nullable
    public PsiElement getExpression() {
        return null;
    }
    
    @NotNull
    public OCElementType getKeywordType() {
        OCElementType for_KEYWORD;
        try {
            for_KEYWORD = OCTokenTypes.FOR_KEYWORD;
            if (for_KEYWORD == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl", "getKeywordType"));
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
    
    @Nullable
    public ASTNode getLParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.LPAR);
    }
    
    @Nullable
    public ASTNode getRParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.RPAR);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCForeachStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitForeachStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
