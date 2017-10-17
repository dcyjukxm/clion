// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.openapi.util.Condition;
import com.intellij.formatting.Block;
import java.util.List;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.tree.TokenSet;

public class OCFormatterUtil
{
    public static final TokenSet FORMAT_DIRECTIVES;
    public static final TokenSet FORMAT_DIRECTIVES_AND_NON_COMPILED;
    public static final TokenSet FORMAT_PROBLEM_LEAFS_IN_NONCOMPILED;
    public static final TokenSet FORMAT_DIRECTIVES_INCREASE_INDENT;
    private static final TokenSet CLASS_CHILDREN;
    private static TokenSet CLASS_DECL_HEADER_PARTS;
    private static TokenSet CLASS_DECL_KEYWORDS;
    public static final TokenSet SPACES_COMMENTS;
    public static final TokenSet MACRO_SPACES_COMMENTS;
    
    public static String _sr(final Document document, final int n, final int n2) {
        return showRangeInDoc(document, n, n2);
    }
    
    public static String _sr(final Document document, final int n) {
        return showRangeInDoc(document, n, n);
    }
    
    public static String showRangeInDoc(final Document document, final int n, final int n2) {
        final String text = document.getText();
        final StringBuilder sb = new StringBuilder(text.length() + 2);
        sb.append(text.substring(0, n));
        sb.append('|');
        sb.append(text.substring(n, n2));
        sb.append('|');
        sb.append(text.substring(n2));
        return sb.toString();
    }
    
    public static String showRangeInDoc(final Document document, final TextRange textRange) {
        return showRangeInDoc(document, textRange.getStartOffset(), textRange.getEndOffset());
    }
    
    @Contract("null, _->false")
    public static boolean isOrFollows(ASTNode treePrev, final IElementType elementType) {
        while (true) {
            Label_0027: {
                try {
                    if (treePrev == null) {
                        break;
                    }
                    final ASTNode astNode = treePrev;
                    final IElementType elementType2 = astNode.getElementType();
                    final IElementType elementType3 = elementType;
                    if (elementType2 == elementType3) {
                        return true;
                    }
                    break Label_0027;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ASTNode astNode = treePrev;
                    final IElementType elementType2 = astNode.getElementType();
                    final IElementType elementType3 = elementType;
                    if (elementType2 == elementType3) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            treePrev = treePrev.getTreePrev();
        }
        return false;
    }
    
    public static boolean isNotEmptyAndOneOf(final ASTNode astNode, final PsiElement... array) {
        Label_0025: {
            try {
                if (OCElementUtil.getElementType(astNode) == OCElementTypes.EMPTY_STATEMENT) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final PsiElement[] array2 = array;
                final boolean b = OCElementUtil.isOneOf(astNode2, array2);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ASTNode astNode2 = astNode;
                final PsiElement[] array2 = array;
                final boolean b = OCElementUtil.isOneOf(astNode2, array2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Contract("null->false")
    public static boolean isAnyLBrace(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //     4: if_acmpeq       35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    @Contract("null->false")
    public static boolean isAnyRBrace(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //     4: if_acmpeq       35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    @Contract("null->false")
    public static boolean isBlock(final IElementType elementType) {
        return OCElementTypes.BLOCK_STATEMENTS.contains(elementType);
    }
    
    @Contract("null->false")
    public static boolean isInsideAnyBraces(@Nullable ASTNode treePrev) {
        try {
            if (treePrev == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n = 0;
        while (true) {
            final ASTNode astNode = treePrev = treePrev.getTreePrev();
            Label_0042: {
                try {
                    if (astNode == null) {
                        break;
                    }
                    final ASTNode astNode2 = treePrev;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b = isAnyLBrace(elementType);
                    if (b) {
                        break Label_0042;
                    }
                    break Label_0042;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final ASTNode astNode2 = treePrev;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b = isAnyLBrace(elementType);
                    if (b) {
                        ++n;
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            if (isAnyRBrace(treePrev.getElementType())) {
                --n;
            }
        }
        try {
            if (n > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return false;
    }
    
    @Contract("null->false")
    public static boolean isKeyword(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCTokenTypes.KEYWORDS.contains(OCElementUtil.getElementType(astNode));
    }
    
    @Contract("null->false")
    public static boolean isClassHeader(@Nullable ASTNode treeNext) {
        while (true) {
            try {
                if (treeNext == null || !OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(treeNext.getElementType())) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            treeNext = treeNext.getTreeNext();
        }
        try {
            if (treeNext == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final IElementType elementType = treeNext.getElementType();
        if (elementType == OCElementTypes.OBJC_KEYWORD) {
            final ASTNode lastChildNode = treeNext.getLastChildNode();
            Label_0105: {
                try {
                    if (!OCFormatterUtil.CLASS_DECL_KEYWORDS.contains(OCElementUtil.getElementType(lastChildNode))) {
                        return false;
                    }
                    final TokenSet set = OCElementTypes.CLASSES;
                    final ASTNode astNode = treeNext;
                    final ASTNode astNode2 = astNode.getTreeParent();
                    final IElementType elementType2 = OCElementUtil.getElementType(astNode2);
                    final boolean b = set.contains(elementType2);
                    if (b) {
                        break Label_0105;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final TokenSet set = OCElementTypes.CLASSES;
                    final ASTNode astNode = treeNext;
                    final ASTNode astNode2 = astNode.getTreeParent();
                    final IElementType elementType2 = OCElementUtil.getElementType(astNode2);
                    final boolean b = set.contains(elementType2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return false;
        }
        try {
            if (elementType == OCTokenTypes.IDENTIFIER) {
                return OCElementTypes.CLASSES.contains(OCElementUtil.getElementType(treeNext.getTreeParent()));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return OCFormatterUtil.CLASS_DECL_HEADER_PARTS.contains(elementType);
    }
    
    public static boolean isClassMember(final ASTNode astNode) {
        Label_0024: {
            try {
                if (isClassHeader(astNode)) {
                    return false;
                }
                final OCElementType ocElementType = OCTokenTypes.END_KEYWORD;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = OCElementUtil.getObjCKeywordElementType(astNode2);
                if (ocElementType != elementType) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCElementType ocElementType = OCTokenTypes.END_KEYWORD;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = OCElementUtil.getObjCKeywordElementType(astNode2);
                if (ocElementType != elementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isMessageArgumentValue(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCFormatterUtil", "isMessageArgumentValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0078: {
            try {
                if (OCElementUtil.getElementType(astNode.getTreeParent()) != OCElementTypes.MESSAGE_ARGUMENT) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final OCElementType ocElementType = OCElementTypes.ARGUMENT_SELECTOR;
                if (elementType != ocElementType) {
                    break Label_0078;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final OCElementType ocElementType = OCElementTypes.ARGUMENT_SELECTOR;
                if (elementType != ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isVarArgArgument(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode.getElementType() != OCElementTypes.MESSAGE_ARGUMENT) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ASTNode childByType = astNode.findChildByType((IElementType)OCElementTypes.ARGUMENT_SELECTOR);
        Label_0061: {
            try {
                if (childByType == null) {
                    break Label_0061;
                }
                final ASTNode astNode2 = childByType;
                final String s = astNode2.getText();
                final boolean b = StringUtil.isEmptyOrSpaces(s);
                if (b) {
                    break Label_0061;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final ASTNode astNode2 = childByType;
                final String s = astNode2.getText();
                final boolean b = StringUtil.isEmptyOrSpaces(s);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static boolean isFollowedByVarArg(@Nullable ASTNode nextNonWhitespaceSibling) {
        try {
            if (nextNonWhitespaceSibling == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (nextNonWhitespaceSibling.getElementType() != OCElementTypes.MESSAGE_ARGUMENT) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        while ((nextNonWhitespaceSibling = FormatterUtil.getNextNonWhitespaceSibling(nextNonWhitespaceSibling)) != null) {
            if (isVarArgArgument(nextNonWhitespaceSibling)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isProtocolListOrReference(final IElementType elementType) {
        Label_0021: {
            try {
                if (elementType == OCElementTypes.PROTOCOL_LIST) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCElementTypes.REFERENCE_ELEMENT;
                if (elementType2 == ocElementType) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCElementTypes.REFERENCE_ELEMENT;
                if (elementType2 == ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isDeclarationOrDefinition(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     4: if_acmpeq       108
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    11: if_acmpeq       108
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpeq       108
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    39: if_acmpeq       108
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    53: if_acmpeq       108
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_0        
        //    64: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    67: if_acmpeq       108
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASS_PREDEF_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    81: if_acmpeq       108
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //    94: aload_0        
        //    95: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    98: ifeq            116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     84     87     91     Ljava/lang/IllegalArgumentException;
        //  77     101    104    108    Ljava/lang/IllegalArgumentException;
        //  91     112    112    116    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    public static boolean isVariablesListOrStructure(final IElementType elementType) {
        Label_0021: {
            try {
                if (elementType == OCElementTypes.INSTANCE_VARIABLES_LIST) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final boolean b = isStructure(elementType2);
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final boolean b = isStructure(elementType2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isCollectionOrStructureInitializer(@Nullable final IElementType elementType) {
        Label_0024: {
            try {
                if (elementType == OCElementTypes.COMPOUND_INITIALIZER) {
                    break Label_0024;
                }
                final TokenSet set = OCElementTypes.NS_COLLECTION_LITERALS;
                final IElementType elementType2 = elementType;
                final boolean b = set.contains(elementType2);
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final TokenSet set = OCElementTypes.NS_COLLECTION_LITERALS;
                final IElementType elementType2 = elementType;
                final boolean b = set.contains(elementType2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isStructure(final IElementType elementType) {
        return OCElementTypes.STRUCTURE_TYPES.contains(elementType);
    }
    
    public static boolean isGlobalDeclarationScope(final IElementType elementType) {
        Label_0021: {
            try {
                if (isNamespace(elementType)) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final IFileElementType fileElementType = OCTokenTypes.OC_FILE;
                if (elementType2 == fileElementType) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final IFileElementType fileElementType = OCTokenTypes.OC_FILE;
                if (elementType2 == fileElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isNamespace(final IElementType elementType) {
        try {
            if (elementType == OCElementTypes.CPP_NAMESPACE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public static boolean isStructureOrNamespace(final IElementType elementType) {
        Label_0021: {
            try {
                if (isStructure(elementType)) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final boolean b = isNamespace(elementType2);
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final boolean b = isNamespace(elementType2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Contract("null->null")
    public static ASTNode getNextNonWhitespaceOrCommentSibling(@Nullable final ASTNode astNode) {
        ASTNode treeNext = null;
        Label_0018: {
            try {
                if (astNode == null) {
                    treeNext = null;
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            treeNext = astNode.getTreeNext();
        }
        ASTNode treeNext2 = treeNext;
        while (true) {
            try {
                if (treeNext2 == null || !isInessential(treeNext2)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            treeNext2 = treeNext2.getTreeNext();
        }
        return treeNext2;
    }
    
    @Contract("null->null")
    public static ASTNode getPreviousNonWhitespaceOrCommentSibling(@Nullable final ASTNode astNode) {
        ASTNode treePrev = null;
        Label_0018: {
            try {
                if (astNode == null) {
                    treePrev = null;
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            treePrev = astNode.getTreePrev();
        }
        ASTNode treePrev2 = treePrev;
        while (true) {
            try {
                if (treePrev2 == null || !isInessential(treePrev2)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            treePrev2 = treePrev2.getTreePrev();
        }
        return treePrev2;
    }
    
    @Contract("null->true")
    public static boolean isInessential(@Nullable final ASTNode p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          40
        //     4: aload_0        
        //     5: invokestatic    com/intellij/psi/formatter/FormatterUtil.isWhitespaceOrEmpty:(Lcom/intellij/lang/ASTNode;)Z
        //     8: ifne            40
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //    21: aload_0        
        //    22: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    27: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    30: ifeq            48
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: iconst_1       
        //    41: goto            49
        //    44: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iconst_0       
        //    49: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      33     36     40     Ljava/lang/IllegalArgumentException;
        //  18     44     44     48     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    public static boolean isOCVisibilityKeywordOrCPPVisibilityColon(@Nullable final ASTNode p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isOCVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //     4: ifne            41
        //     7: aload_0        
        //     8: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    11: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    14: if_acmpne       49
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.getPreviousNonWhitespaceOrCommentSibling:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isCPPVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      34     37     41     Ljava/lang/IllegalArgumentException;
        //  24     45     45     49     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public static boolean isForOrForEachStatement(final IElementType elementType) {
        Label_0021: {
            try {
                if (elementType == OCElementTypes.FOR_STATEMENT) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCElementTypes.FOREACH_STATEMENT;
                if (elementType2 == ocElementType) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCElementTypes.FOREACH_STATEMENT;
                if (elementType2 == ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isLoopStatement(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isForOrForEachStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //     4: ifne            35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DO_WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    public static boolean isControlStatement(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.RETURN_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     4: if_acmpeq       105
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    11: if_acmpeq       105
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isLoopStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //    25: ifne            105
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SWITCH_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    39: if_acmpeq       105
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    53: if_acmpeq       105
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_0        
        //    64: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TRY_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    67: if_acmpeq       105
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNCHRONIZED_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    81: if_acmpeq       105
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_0        
        //    92: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.AUTO_RELEASE_POOL_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    95: if_acmpne       113
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: iconst_1       
        //   106: goto            114
        //   109: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     84     87     91     Ljava/lang/IllegalArgumentException;
        //  77     98     101    105    Ljava/lang/IllegalArgumentException;
        //  91     109    109    113    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    public static boolean isControlStatementOrSection(final IElementType elementType) {
        Label_0021: {
            try {
                if (isControlStatement(elementType)) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final boolean b = isControlSection(elementType2);
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final boolean b = isControlSection(elementType2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isControlSection(final IElementType elementType) {
        Label_0021: {
            try {
                if (elementType == OCElementTypes.CATCH_SECTION) {
                    break Label_0021;
                }
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCElementTypes.FINALLY_SECTION;
                if (elementType2 == ocElementType) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCElementTypes.FINALLY_SECTION;
                if (elementType2 == ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isInsideDirective(@Nullable final ASTNode astNode) {
        ASTNode treeParent = astNode;
        while (true) {
            Label_0026: {
                try {
                    if (treeParent == null) {
                        break;
                    }
                    final ASTNode astNode2 = treeParent;
                    final boolean b = isDirective(astNode2);
                    if (b) {
                        return true;
                    }
                    break Label_0026;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ASTNode astNode2 = treeParent;
                    final boolean b = isDirective(astNode2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            treeParent = treeParent.getTreeParent();
        }
        return false;
    }
    
    public static boolean isDirective(@Nullable final ASTNode astNode) {
        Label_0026: {
            try {
                if (astNode == null) {
                    return false;
                }
                final TokenSet set = OCFormatterUtil.FORMAT_DIRECTIVES;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set.contains(elementType);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final TokenSet set = OCFormatterUtil.FORMAT_DIRECTIVES;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set.contains(elementType);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static <T> T processBraced(@Nullable final ASTNode p0, @Nullable final IElementType p1, final boolean p2, @NotNull final LeftBracesProcessor<T> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "processor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processBraced"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    48: astore          4
        //    50: aload_1        
        //    51: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    54: if_acmpne       66
        //    57: aload_3        
        //    58: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processInterfaceOrStructure:()Ljava/lang/Object;
        //    61: areturn        
        //    62: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: iload_2        
        //    67: ifeq            99
        //    70: aload           4
        //    72: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isVariablesListOrStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //    75: ifeq            99
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_1        
        //    86: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    89: if_acmpeq       124
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iload_2        
        //   100: ifne            133
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_1        
        //   111: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isVariablesListOrStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //   114: ifeq            133
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: aload_3        
        //   125: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processInterfaceOrStructure:()Ljava/lang/Object;
        //   128: areturn        
        //   129: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: iload_2        
        //   134: ifeq            166
        //   137: aload           4
        //   139: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNamespace:(Lcom/intellij/psi/tree/IElementType;)Z
        //   142: ifeq            166
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_1        
        //   153: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   156: if_acmpeq       191
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: iload_2        
        //   167: ifne            200
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload_1        
        //   178: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNamespace:(Lcom/intellij/psi/tree/IElementType;)Z
        //   181: ifeq            200
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload_3        
        //   192: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processNamespace:()Ljava/lang/Object;
        //   195: areturn        
        //   196: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_1        
        //   201: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   204: ifeq            467
        //   207: aload           4
        //   209: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   212: if_acmpne       231
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_3        
        //   223: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processMethod:()Ljava/lang/Object;
        //   226: areturn        
        //   227: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload           4
        //   233: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   236: if_acmpne       248
        //   239: aload_3        
        //   240: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processFunction:()Ljava/lang/Object;
        //   243: areturn        
        //   244: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload           4
        //   250: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   253: if_acmpeq       271
        //   256: aload           4
        //   258: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   261: if_acmpne       280
        //   264: goto            271
        //   267: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: aload_3        
        //   272: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processBlock:()Ljava/lang/Object;
        //   275: areturn        
        //   276: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload           4
        //   282: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   285: if_acmpne       297
        //   288: aload_3        
        //   289: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processIfStatement:()Ljava/lang/Object;
        //   292: areturn        
        //   293: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: aload           4
        //   299: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isForOrForEachStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //   302: ifeq            314
        //   305: aload_3        
        //   306: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processForOrForEachStatement:()Ljava/lang/Object;
        //   309: areturn        
        //   310: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: aload           4
        //   316: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   319: if_acmpne       331
        //   322: aload_3        
        //   323: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processWhileStatement:()Ljava/lang/Object;
        //   326: areturn        
        //   327: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload           4
        //   333: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DO_WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   336: if_acmpne       348
        //   339: aload_3        
        //   340: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processDoWhileStatement:()Ljava/lang/Object;
        //   343: areturn        
        //   344: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aload           4
        //   350: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SWITCH_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   353: if_acmpne       365
        //   356: aload_3        
        //   357: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processSwitchStatement:()Ljava/lang/Object;
        //   360: areturn        
        //   361: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   364: athrow         
        //   365: aload           4
        //   367: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   370: if_acmpne       382
        //   373: aload_3        
        //   374: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processSwitchStatement:()Ljava/lang/Object;
        //   377: areturn        
        //   378: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: aload           4
        //   384: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TRY_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   387: if_acmpne       399
        //   390: aload_3        
        //   391: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processTryStatement:()Ljava/lang/Object;
        //   394: areturn        
        //   395: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: aload           4
        //   401: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATCH_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   404: if_acmpne       416
        //   407: aload_3        
        //   408: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processCatchStatement:()Ljava/lang/Object;
        //   411: areturn        
        //   412: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: aload           4
        //   418: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FINALLY_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   421: if_acmpne       433
        //   424: aload_3        
        //   425: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processFinallyStatement:()Ljava/lang/Object;
        //   428: areturn        
        //   429: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   432: athrow         
        //   433: aload           4
        //   435: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNCHRONIZED_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   438: if_acmpne       450
        //   441: aload_3        
        //   442: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processSynchronizedStatement:()Ljava/lang/Object;
        //   445: areturn        
        //   446: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   449: athrow         
        //   450: aload           4
        //   452: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.AUTO_RELEASE_POOL_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   455: if_acmpne       467
        //   458: aload_3        
        //   459: invokevirtual   com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor.processAutoreleasePoolStatement:()Ljava/lang/Object;
        //   462: areturn        
        //   463: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   466: athrow         
        //   467: aconst_null    
        //   468: areturn        
        //    Signature:
        //  <T:Ljava/lang/Object;>(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;ZLcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor<TT;>;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  50     62     62     66     Ljava/lang/IllegalArgumentException;
        //  66     78     81     85     Ljava/lang/IllegalArgumentException;
        //  70     92     95     99     Ljava/lang/IllegalArgumentException;
        //  85     103    106    110    Ljava/lang/IllegalArgumentException;
        //  99     117    120    124    Ljava/lang/IllegalArgumentException;
        //  110    129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    145    148    152    Ljava/lang/IllegalArgumentException;
        //  137    159    162    166    Ljava/lang/IllegalArgumentException;
        //  152    170    173    177    Ljava/lang/IllegalArgumentException;
        //  166    184    187    191    Ljava/lang/IllegalArgumentException;
        //  177    196    196    200    Ljava/lang/IllegalArgumentException;
        //  200    215    218    222    Ljava/lang/IllegalArgumentException;
        //  207    227    227    231    Ljava/lang/IllegalArgumentException;
        //  231    244    244    248    Ljava/lang/IllegalArgumentException;
        //  248    264    267    271    Ljava/lang/IllegalArgumentException;
        //  256    276    276    280    Ljava/lang/IllegalArgumentException;
        //  280    293    293    297    Ljava/lang/IllegalArgumentException;
        //  297    310    310    314    Ljava/lang/IllegalArgumentException;
        //  314    327    327    331    Ljava/lang/IllegalArgumentException;
        //  331    344    344    348    Ljava/lang/IllegalArgumentException;
        //  348    361    361    365    Ljava/lang/IllegalArgumentException;
        //  365    378    378    382    Ljava/lang/IllegalArgumentException;
        //  382    395    395    399    Ljava/lang/IllegalArgumentException;
        //  399    412    412    416    Ljava/lang/IllegalArgumentException;
        //  416    429    429    433    Ljava/lang/IllegalArgumentException;
        //  433    446    446    450    Ljava/lang/IllegalArgumentException;
        //  450    463    463    467    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
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
    
    static boolean isObjCKeywordWithDog(final IElementType elementType, final ASTNode astNode, final OCElementType ocElementType) {
        Label_0022: {
            try {
                if (elementType != OCElementTypes.OBJC_KEYWORD) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final IElementType elementType2 = OCElementUtil.getObjCKeywordElementType(astNode2);
                final OCElementType ocElementType2 = ocElementType;
                if (elementType2 == ocElementType2) {
                    break Label_0022;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ASTNode astNode2 = astNode;
                final IElementType elementType2 = OCElementUtil.getObjCKeywordElementType(astNode2);
                final OCElementType ocElementType2 = ocElementType;
                if (elementType2 == ocElementType2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isCPPClassDeclaration(@Nullable final ASTNode astNode) {
        try {
            if (OCElementUtil.getElementType(astNode) != OCElementTypes.DECLARATION) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode childByType = astNode.findChildByType((IElementType)OCElementTypes.TYPE_ELEMENT);
        Label_0049: {
            try {
                if (childByType == null) {
                    return false;
                }
                final ASTNode astNode2 = childByType;
                final ASTNode astNode3 = astNode2.getFirstChildNode();
                final boolean b = isCPPClass(astNode3);
                if (b) {
                    break Label_0049;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode2 = childByType;
                final ASTNode astNode3 = astNode2.getFirstChildNode();
                final boolean b = isCPPClass(astNode3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isCPPClass(@Nullable final ASTNode p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isClassStructUnion:(Lcom/intellij/lang/ASTNode;)Z
        //     4: ifeq            58
        //     7: aload_0        
        //     8: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    13: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    18: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CLASS_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    21: if_acmpeq       50
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_0        
        //    32: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.CLASS_CHILDREN:Lcom/intellij/psi/tree/TokenSet;
        //    35: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/TokenSet;)Lcom/intellij/lang/ASTNode;
        //    40: ifnull          58
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_1       
        //    51: goto            59
        //    54: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_0       
        //    59: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      24     27     31     Ljava/lang/IllegalArgumentException;
        //  7      43     46     50     Ljava/lang/IllegalArgumentException;
        //  31     54     54     58     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    
    public static boolean isClassStructUnion(@Nullable final ASTNode astNode) {
        Label_0027: {
            try {
                if (OCElementUtil.getElementType(astNode) == OCElementTypes.STRUCT) {
                    break Label_0027;
                }
                final ASTNode astNode2 = astNode;
                final IElementType elementType = OCElementUtil.getElementType(astNode2);
                final OCElementType ocElementType = OCElementTypes.UNION;
                if (elementType == ocElementType) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ASTNode astNode2 = astNode;
                final IElementType elementType = OCElementUtil.getElementType(astNode2);
                final OCElementType ocElementType = OCElementTypes.UNION;
                if (elementType == ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static ASTNode nextLeafInOwner(@NotNull final ASTNode p0, @NotNull final ASTNode p1) {
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
        //    18: ldc             "current"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "nextLeafInOwner"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "owner"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "nextLeafInOwner"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //    94: astore_2       
        //    95: aload_2        
        //    96: ifnull          124
        //    99: aload_2        
        //   100: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.firstLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   103: astore_3       
        //   104: aload_3        
        //   105: ifnull          114
        //   108: aload_3        
        //   109: areturn        
        //   110: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload_2        
        //   115: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   120: astore_2       
        //   121: goto            95
        //   124: aload_0        
        //   125: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   130: astore_3       
        //   131: aload_3        
        //   132: aload_1        
        //   133: if_acmpeq       161
        //   136: aload_3        
        //   137: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   140: ifne            161
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload_3        
        //   151: ifnonnull       167
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aconst_null    
        //   162: areturn        
        //   163: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_3        
        //   168: aload_1        
        //   169: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.nextLeafInOwner:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   172: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  104    110    110    114    Ljava/lang/IllegalArgumentException;
        //  131    143    146    150    Ljava/lang/IllegalArgumentException;
        //  136    154    157    161    Ljava/lang/IllegalArgumentException;
        //  150    163    163    167    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
    
    @Nullable
    public static ASTNode prevLeafInOwner(@NotNull final ASTNode p0, @NotNull final ASTNode p1) {
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
        //    18: ldc             "current"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "prevLeafInOwner"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "owner"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "prevLeafInOwner"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //    94: astore_2       
        //    95: aload_2        
        //    96: ifnull          124
        //    99: aload_2        
        //   100: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   103: astore_3       
        //   104: aload_3        
        //   105: ifnull          114
        //   108: aload_3        
        //   109: areturn        
        //   110: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload_2        
        //   115: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   120: astore_2       
        //   121: goto            95
        //   124: aload_0        
        //   125: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   130: astore_3       
        //   131: aload_3        
        //   132: aload_1        
        //   133: if_acmpeq       161
        //   136: aload_3        
        //   137: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   140: ifne            161
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload_3        
        //   151: ifnonnull       167
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aconst_null    
        //   162: areturn        
        //   163: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_3        
        //   168: aload_1        
        //   169: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.prevLeafInOwner:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   172: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  104    110    110    114    Ljava/lang/IllegalArgumentException;
        //  131    143    146    150    Ljava/lang/IllegalArgumentException;
        //  136    154    157    161    Ljava/lang/IllegalArgumentException;
        //  150    163    163    167    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
    
    @Nullable
    public static ASTNode lastLeaf(@NotNull final ASTNode element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/formatting/OCFormatterUtil", "lastLeaf"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return TreeUtil.findLastLeaf(element);
    }
    
    @Nullable
    public static ASTNode firstLeaf(@NotNull final ASTNode element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/formatting/OCFormatterUtil", "firstLeaf"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (ASTNode)TreeUtil.findFirstLeaf(element);
    }
    
    public static boolean isAncestorOrSelf(@Nullable final ASTNode astNode, @NotNull final ASTNode astNode2) {
        try {
            if (astNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/formatting/OCFormatterUtil", "isAncestorOrSelf"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (astNode == astNode2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        ASTNode astNode3 = astNode2.getTreeParent();
        while (true) {
            try {
                if (astNode3 == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (astNode3 == astNode) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            astNode3 = astNode3.getTreeParent();
        }
    }
    
    @NotNull
    static ASTNode getBestCommonOwner(@NotNull final ASTNode p0, @NotNull final ASTNode p1, @NotNull final ASTNode p2) {
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
        //    18: ldc             "defaultOwner"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getBestCommonOwner"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "node1"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getBestCommonOwner"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "node2"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getBestCommonOwner"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   138: astore_3       
        //   139: aload_2        
        //   140: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   145: astore          4
        //   147: aload_3        
        //   148: ifnull          263
        //   151: aload           4
        //   153: ifnull          263
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_3        
        //   164: aload           4
        //   166: invokestatic    com/intellij/psi/util/PsiTreeUtil.findCommonParent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   169: astore          5
        //   171: aload           5
        //   173: instanceof      Lcom/intellij/psi/impl/source/tree/LeafElement;
        //   176: ifeq            188
        //   179: aload           5
        //   181: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   186: astore          5
        //   188: aload           5
        //   190: ifnull          263
        //   193: aload           5
        //   195: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   200: ifnull          263
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aload           5
        //   212: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   217: dup            
        //   218: ifnonnull       262
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: new             Ljava/lang/IllegalStateException;
        //   231: dup            
        //   232: ldc             "@NotNull method %s.%s must not return null"
        //   234: ldc             2
        //   236: anewarray       Ljava/lang/Object;
        //   239: dup            
        //   240: ldc             0
        //   242: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //   244: aastore        
        //   245: dup            
        //   246: ldc             1
        //   248: ldc             "getBestCommonOwner"
        //   250: aastore        
        //   251: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   254: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   257: athrow         
        //   258: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: areturn        
        //   263: aload_0        
        //   264: dup            
        //   265: ifnonnull       302
        //   268: new             Ljava/lang/IllegalStateException;
        //   271: dup            
        //   272: ldc             "@NotNull method %s.%s must not return null"
        //   274: ldc             2
        //   276: anewarray       Ljava/lang/Object;
        //   279: dup            
        //   280: ldc             0
        //   282: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //   284: aastore        
        //   285: dup            
        //   286: ldc             1
        //   288: ldc             "getBestCommonOwner"
        //   290: aastore        
        //   291: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   294: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   297: athrow         
        //   298: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  147    156    159    163    Ljava/lang/IllegalArgumentException;
        //  188    203    206    210    Ljava/lang/IllegalArgumentException;
        //  193    221    224    228    Ljava/lang/IllegalArgumentException;
        //  210    258    258    262    Ljava/lang/IllegalArgumentException;
        //  263    298    298    302    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0210:
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
    
    public static boolean isInBlockEnclosed(@Nullable final ASTNode p0, @NotNull final ASTNode p1) {
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
        //    18: ldc             "parent"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isInBlockEnclosed"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_0       
        //    45: istore_2       
        //    46: iconst_0       
        //    47: istore_3       
        //    48: aload_1        
        //    49: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    54: astore          4
        //    56: aload           4
        //    58: ifnull          249
        //    61: aload           4
        //    63: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    68: astore          5
        //    70: aload           4
        //    72: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    77: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //    80: istore          6
        //    82: aload_0        
        //    83: ifnull          99
        //    86: aload_0        
        //    87: aload           4
        //    89: if_acmpeq       152
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_0        
        //   100: ifnonnull       157
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: iload           6
        //   112: ifne            157
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload           5
        //   124: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   127: if_acmpeq       157
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload           5
        //   139: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   142: if_acmpeq       157
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: iconst_1       
        //   153: istore_2       
        //   154: goto            237
        //   157: aload           5
        //   159: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   162: if_acmpne       170
        //   165: iconst_1       
        //   166: istore_3       
        //   167: goto            237
        //   170: iload_3        
        //   171: ifeq            237
        //   174: iload_2        
        //   175: ifne            204
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload           4
        //   187: bipush          10
        //   189: invokeinterface com/intellij/lang/ASTNode.textContains:(C)Z
        //   194: ifne            231
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: aload           5
        //   206: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   209: if_acmpeq       237
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: iload           6
        //   221: ifne            237
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: iconst_0       
        //   232: ireturn        
        //   233: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload           4
        //   239: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   244: astore          4
        //   246: goto            56
        //   249: iload_2        
        //   250: ifne            264
        //   253: aload_0        
        //   254: ifnonnull       272
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: iconst_1       
        //   265: goto            273
        //   268: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   271: athrow         
        //   272: iconst_0       
        //   273: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  82     92     95     99     Ljava/lang/IllegalArgumentException;
        //  86     103    106    110    Ljava/lang/IllegalArgumentException;
        //  99     115    118    122    Ljava/lang/IllegalArgumentException;
        //  110    130    133    137    Ljava/lang/IllegalArgumentException;
        //  122    145    148    152    Ljava/lang/IllegalArgumentException;
        //  170    178    181    185    Ljava/lang/IllegalArgumentException;
        //  174    197    200    204    Ljava/lang/IllegalArgumentException;
        //  185    212    215    219    Ljava/lang/IllegalArgumentException;
        //  204    224    227    231    Ljava/lang/IllegalArgumentException;
        //  219    233    233    237    Ljava/lang/IllegalArgumentException;
        //  249    257    260    264    Ljava/lang/IllegalArgumentException;
        //  253    268    268    272    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0099:
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
    public static boolean isDirectlyInsideSwitch(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode treeParent = astNode.getTreeParent();
        IElementType elementType = OCElementUtil.getElementType(treeParent);
        if (OCElementTypes.BLOCK_STATEMENTS.contains(elementType)) {
            elementType = OCElementUtil.getElementType(treeParent.getTreeParent());
        }
        try {
            if (elementType == OCElementTypes.SWITCH_STATEMENT) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public static LABEL_TYPE getLabelType(@Nullable ASTNode treeParent) {
        LABEL_TYPE label_TYPE = LABEL_TYPE.OTHER;
        while (treeParent != null) {
            final IElementType elementType = treeParent.getElementType();
            if (elementType == OCElementTypes.LABELED_STATEMENT) {
                label_TYPE = LABEL_TYPE.LABEL_LIKE;
            }
            else {
                if (elementType != OCElementTypes.CASE_STATEMENT) {
                    break;
                }
                label_TYPE = LABEL_TYPE.CASE_LIKE;
            }
            try {
                if (isDirectlyInsideSwitch(treeParent)) {
                    return LABEL_TYPE.CASE_LIKE;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            treeParent = treeParent.getTreeParent();
        }
        return label_TYPE;
    }
    
    private static ASTNode b(@Nullable final ASTNode astNode) {
        ASTNode treePrev = null;
        Label_0018: {
            try {
                if (astNode == null) {
                    treePrev = null;
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            treePrev = astNode.getTreePrev();
        }
        ASTNode treePrev2;
        for (treePrev2 = treePrev; treePrev2 != null && OCFormatterUtil.MACRO_SPACES_COMMENTS.contains(treePrev2.getElementType()); treePrev2 = treePrev2.getTreePrev()) {}
        return treePrev2;
    }
    
    @Contract("null,_->false")
    public static boolean isPrecededBy(@Nullable final ASTNode astNode, final IElementType elementType) {
        try {
            if (OCElementUtil.getElementType(b(astNode)) == elementType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Contract("null,_->false")
    public static boolean isPrecededBy(@Nullable final ASTNode astNode, final TokenSet set) {
        return set.contains(OCElementUtil.getElementType(b(astNode)));
    }
    
    public static boolean isFirstEssentialChild(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/formatting/OCFormatterUtil", "isFirstEssentialChild"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode treeParent = astNode.getTreeParent();
        try {
            if (treeParent == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ASTNode astNode2;
        for (astNode2 = treeParent.getFirstChildNode(); astNode2 != null; astNode2 = astNode2.getTreeNext()) {
            final IElementType elementType = astNode2.getElementType();
            try {
                if (!(elementType instanceof OCPunctuatorElementType)) {
                    if (!OCFormatterUtil.MACRO_SPACES_COMMENTS.contains(elementType)) {
                        break;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (astNode2 == astNode) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return false;
    }
    
    public static void applyIndentCorrection(final boolean p0, final List<Block> p1, final int p2, final boolean p3, final Condition<ASTNode> p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifne            20
        //     4: iload_3        
        //     5: ifne            20
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: return         
        //    16: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: iload_0        
        //    21: istore          5
        //    23: iload           5
        //    25: ifne            221
        //    28: iconst_0       
        //    29: istore          6
        //    31: aload_1        
        //    32: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    37: astore          7
        //    39: aload           7
        //    41: invokeinterface java/util/Iterator.hasNext:()Z
        //    46: ifeq            221
        //    49: aload           7
        //    51: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    56: checkcast       Lcom/intellij/formatting/Block;
        //    59: astore          8
        //    61: aload           8
        //    63: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.extractFirstNode:(Lcom/intellij/formatting/Block;)Lcom/intellij/lang/ASTNode;
        //    66: astore          9
        //    68: aload           4
        //    70: aload           9
        //    72: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
        //    77: ifeq            218
        //    80: iinc            6, 1
        //    83: iload           6
        //    85: iconst_1       
        //    86: if_icmple       218
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: iload_2        
        //    97: iconst_2       
        //    98: if_icmpne       116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: istore          5
        //   119: iload           5
        //   121: ifeq            131
        //   124: goto            221
        //   127: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload           9
        //   133: iconst_0       
        //   134: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   137: invokestatic    com/intellij/psi/formatter/FormatterUtil.getPreviousLeaf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   140: astore          10
        //   142: aload           10
        //   144: ifnull          218
        //   147: aload           10
        //   149: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   154: astore          11
        //   156: aload           11
        //   158: ifnull          203
        //   161: aload           11
        //   163: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   166: ifeq            203
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload           11
        //   178: bipush          10
        //   180: invokeinterface com/intellij/psi/PsiElement.textContains:(C)Z
        //   185: ifeq            203
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: iconst_1       
        //   196: goto            204
        //   199: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: iconst_0       
        //   204: istore          5
        //   206: iload           5
        //   208: ifeq            218
        //   211: goto            221
        //   214: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: goto            39
        //   221: iload           5
        //   223: ifeq            342
        //   226: iload_0        
        //   227: ifeq            250
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: getstatic       com/intellij/formatting/Indent$Type.CONTINUATION:Lcom/intellij/formatting/Indent$Type;
        //   240: invokestatic    com/intellij/formatting/Indent.getSmartIndent:(Lcom/intellij/formatting/Indent$Type;)Lcom/intellij/formatting/Indent;
        //   243: goto            258
        //   246: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: getstatic       com/intellij/formatting/Indent$Type.CONTINUATION:Lcom/intellij/formatting/Indent$Type;
        //   253: iconst_0       
        //   254: iconst_1       
        //   255: invokestatic    com/intellij/formatting/Indent.getIndent:(Lcom/intellij/formatting/Indent$Type;ZZ)Lcom/intellij/formatting/Indent;
        //   258: astore          6
        //   260: aload_1        
        //   261: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   266: astore          7
        //   268: aload           7
        //   270: invokeinterface java/util/Iterator.hasNext:()Z
        //   275: ifeq            342
        //   278: aload           7
        //   280: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   285: checkcast       Lcom/intellij/formatting/Block;
        //   288: astore          8
        //   290: aload           8
        //   292: instanceof      Lcom/jetbrains/cidr/lang/formatting/OCIndentChanger;
        //   295: ifeq            339
        //   298: aload           4
        //   300: aload           8
        //   302: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.extractFirstNode:(Lcom/intellij/formatting/Block;)Lcom/intellij/lang/ASTNode;
        //   305: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
        //   310: ifeq            339
        //   313: goto            320
        //   316: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: aload           8
        //   322: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCIndentChanger;
        //   325: aload           6
        //   327: invokeinterface com/jetbrains/cidr/lang/formatting/OCIndentChanger.putIndent:(Lcom/intellij/formatting/Indent;)V
        //   332: goto            339
        //   335: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   338: athrow         
        //   339: goto            268
        //   342: return         
        //    Signature:
        //  (ZLjava/util/List<Lcom/intellij/formatting/Block;>;IZLcom/intellij/openapi/util/Condition<Lcom/intellij/lang/ASTNode;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      16     16     20     Ljava/lang/IllegalArgumentException;
        //  68     89     92     96     Ljava/lang/IllegalArgumentException;
        //  80     101    104    108    Ljava/lang/IllegalArgumentException;
        //  96     112    112    116    Ljava/lang/IllegalArgumentException;
        //  119    127    127    131    Ljava/lang/IllegalArgumentException;
        //  156    169    172    176    Ljava/lang/IllegalArgumentException;
        //  161    188    191    195    Ljava/lang/IllegalArgumentException;
        //  176    199    199    203    Ljava/lang/IllegalArgumentException;
        //  206    214    214    218    Ljava/lang/IllegalArgumentException;
        //  221    230    233    237    Ljava/lang/IllegalArgumentException;
        //  226    246    246    250    Ljava/lang/IllegalArgumentException;
        //  290    313    316    320    Ljava/lang/IllegalArgumentException;
        //  298    332    335    339    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0096:
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
    
    private static boolean a(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null || astNode.getElementType() != OCElementTypes.CPP_NAMESPACE) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode firstChildNode = astNode.getFirstChildNode();
        Label_0053: {
            try {
                if (firstChildNode == null) {
                    return false;
                }
                final ASTNode astNode2 = firstChildNode;
                final IElementType elementType = astNode2.getElementType();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON2X;
                if (elementType == ocPunctuatorElementType) {
                    break Label_0053;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode2 = firstChildNode;
                final IElementType elementType = astNode2.getElementType();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON2X;
                if (elementType == ocPunctuatorElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isNestedInlineBlock(@NotNull final CommonCodeStyleSettings p0, @NotNull final OCCodeStyleSettings p1, @Nullable final ASTNode p2) {
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
        //    18: ldc             "commonSettings"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isNestedInlineBlock"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "ocSettings"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isNestedInlineBlock"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_NESTED_NAMESPACES_IN_ONE_LINE:Z
        //    92: ifeq            154
        //    95: aload_2        
        //    96: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    99: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   102: if_acmpne       154
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_2        
        //   113: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   118: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   121: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   124: if_acmpne       154
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_2        
        //   135: aload_2        
        //   136: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   141: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isInBlockEnclosed:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Z
        //   144: ifne            168
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_2        
        //   155: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Lcom/intellij/lang/ASTNode;)Z
        //   158: ifeq            176
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: iconst_1       
        //   169: goto            177
        //   172: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: iconst_0       
        //   177: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     105    108    112    Ljava/lang/IllegalArgumentException;
        //  95     127    130    134    Ljava/lang/IllegalArgumentException;
        //  112    147    150    154    Ljava/lang/IllegalArgumentException;
        //  134    161    164    168    Ljava/lang/IllegalArgumentException;
        //  154    172    172    176    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    public static boolean isNestedBlockOwner(@NotNull final CommonCodeStyleSettings p0, @NotNull final OCCodeStyleSettings p1, @NotNull final ASTNode p2) {
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
        //    18: ldc             "commonSettings"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isNestedBlockOwner"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "ocSettings"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isNestedBlockOwner"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "owner"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCFormatterUtil"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "isNestedBlockOwner"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   136: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   141: astore_3       
        //   142: aload_1        
        //   143: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_NESTED_NAMESPACES_IN_ONE_LINE:Z
        //   146: ifeq            182
        //   149: aload_2        
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   153: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   156: if_acmpne       182
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_0        
        //   167: aload_1        
        //   168: aload_3        
        //   169: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNestedInlineBlock:(Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/lang/ASTNode;)Z
        //   172: ifne            196
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_3        
        //   183: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Lcom/intellij/lang/ASTNode;)Z
        //   186: ifeq            204
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: iconst_1       
        //   197: goto            205
        //   200: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: iconst_0       
        //   205: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  142    159    162    166    Ljava/lang/IllegalArgumentException;
        //  149    175    178    182    Ljava/lang/IllegalArgumentException;
        //  166    189    192    196    Ljava/lang/IllegalArgumentException;
        //  182    200    200    204    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166:
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
    
    public static boolean isNamespaceWithKeyword(@Nullable final ASTNode p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          45
        //     4: aload_0        
        //     5: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    10: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    13: if_acmpne       45
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    22: athrow         
        //    23: aload_0        
        //    24: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Lcom/intellij/lang/ASTNode;)Z
        //    27: ifne            45
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: iconst_1       
        //    38: goto            46
        //    41: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iconst_0       
        //    46: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     19     23     Ljava/lang/IllegalArgumentException;
        //  4      30     33     37     Ljava/lang/IllegalArgumentException;
        //  23     41     41     45     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    
    static {
        FORMAT_DIRECTIVES = TokenSet.orSet(new TokenSet[] { OCTokenTypes.DIRECTIVES, OCElementTypes.DIRECTIVES, TokenSet.create(new IElementType[] { OCTokenTypes.HASH }) });
        FORMAT_DIRECTIVES_AND_NON_COMPILED = TokenSet.orSet(new TokenSet[] { OCTokenTypes.DIRECTIVES, OCElementTypes.DIRECTIVES, TokenSet.create(new IElementType[] { OCTokenTypes.HASH, OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT }) });
        FORMAT_PROBLEM_LEAFS_IN_NONCOMPILED = TokenSet.orSet(new TokenSet[] { OCTokenTypes.RAW_STRING_LITERALS, TokenSet.create(new IElementType[] { OCTokenTypes.BLOCK_COMMENT }) });
        FORMAT_DIRECTIVES_INCREASE_INDENT = TokenSet.orSet(new TokenSet[] { OCTokenTypes.IF_DIRECTIVES, TokenSet.create(new IElementType[] { OCTokenTypes.ELSE_DIRECTIVE, OCTokenTypes.ELIF_DIRECTIVE }) });
        CLASS_CHILDREN = TokenSet.create(new IElementType[] { OCElementTypes.FUNCTION_DEFINITION, OCElementTypes.FUNCTION_DECLARATION });
        OCFormatterUtil.CLASS_DECL_HEADER_PARTS = TokenSet.create(new IElementType[] { OCElementTypes.INSTANCE_VARIABLES_LIST, OCElementTypes.SUPER_CLASS_REF, OCElementTypes.PROTOCOL_LIST, OCElementTypes.GENERIC_PARAMETERS_LIST, OCElementTypes.CATEGORY_NAME });
        OCFormatterUtil.CLASS_DECL_KEYWORDS = TokenSet.create(new IElementType[] { OCTokenTypes.PROTOCOL_KEYWORD, OCTokenTypes.INTERFACE_KEYWORD, OCTokenTypes.IMPLEMENTATION_KEYWORD });
        SPACES_COMMENTS = TokenSet.orSet(new TokenSet[] { OCTokenTypes.WHITESPACES, OCTokenTypes.COMMENTS });
        MACRO_SPACES_COMMENTS = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCElementTypes.MACRO_CALL }), OCFormatterUtil.SPACES_COMMENTS });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    enum LABEL_TYPE
    {
        CASE_LIKE, 
        LABEL_LIKE, 
        OTHER;
    }
    
    public abstract static class LeftBracesProcessor<T>
    {
        public T processNamespace() {
            return this.processGeneral();
        }
        
        public T processInterfaceOrStructure() {
            return this.processGeneral();
        }
        
        public T processMethod() {
            return this.processGeneral();
        }
        
        public T processFunction() {
            return this.processGeneral();
        }
        
        public T processBlock() {
            return this.processFunction();
        }
        
        public T processIfStatement() {
            return this.processGeneral();
        }
        
        public T processForOrForEachStatement() {
            return this.processGeneral();
        }
        
        public T processWhileStatement() {
            return this.processGeneral();
        }
        
        public T processDoWhileStatement() {
            return this.processGeneral();
        }
        
        public T processSwitchStatement() {
            return this.processGeneral();
        }
        
        public T processTryStatement() {
            return this.processGeneral();
        }
        
        public T processCatchStatement() {
            return this.processGeneral();
        }
        
        public T processFinallyStatement() {
            return this.processGeneral();
        }
        
        public T processSynchronizedStatement() {
            return this.processGeneral();
        }
        
        public T processAutoreleasePoolStatement() {
            return this.processGeneral();
        }
        
        public T processGeneral() {
            return null;
        }
    }
}
