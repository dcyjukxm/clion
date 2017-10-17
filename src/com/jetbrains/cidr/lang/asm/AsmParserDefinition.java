// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm;

import com.intellij.lang.Language;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.asm.psi.AsmTypes;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.asm.psi.AsmFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.lang.asm.parser.AsmParser;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ParserDefinition;

public class AsmParserDefinition implements ParserDefinition
{
    public static final TokenSet WHITE_SPACES;
    public static final TokenSet COMMENTS;
    public static final IFileElementType FILE;
    
    @NotNull
    public Lexer createLexer(final Project project) {
        AsmLexer asmLexer;
        try {
            asmLexer = new AsmLexer();
            if (asmLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)asmLexer;
    }
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet white_SPACES;
        try {
            white_SPACES = AsmParserDefinition.WHITE_SPACES;
            if (white_SPACES == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmParserDefinition", "getWhitespaceTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return white_SPACES;
    }
    
    @NotNull
    public TokenSet getCommentTokens() {
        TokenSet comments;
        try {
            comments = AsmParserDefinition.COMMENTS;
            if (comments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmParserDefinition", "getCommentTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return comments;
    }
    
    @NotNull
    public TokenSet getStringLiteralElements() {
        TokenSet empty;
        try {
            empty = TokenSet.EMPTY;
            if (empty == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmParserDefinition", "getStringLiteralElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return empty;
    }
    
    @NotNull
    public PsiParser createParser(final Project project) {
        AsmParser asmParser;
        try {
            asmParser = new AsmParser();
            if (asmParser == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmParserDefinition", "createParser"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (PsiParser)asmParser;
    }
    
    public IFileElementType getFileNodeType() {
        return AsmParserDefinition.FILE;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        return (PsiFile)new AsmFile(fileViewProvider);
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return ParserDefinition.SpaceRequirements.MAY;
    }
    
    @NotNull
    public PsiElement createElement(final ASTNode astNode) {
        PsiElement element;
        try {
            element = AsmTypes.Factory.createElement(astNode);
            if (element == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmParserDefinition", "createElement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return element;
    }
    
    static {
        WHITE_SPACES = TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE });
        COMMENTS = TokenSet.create(new IElementType[] { AsmTypes.LINE_COMMENT, AsmTypes.BLOCK_COMMENT });
        FILE = new IFileElementType(Language.findInstance((Class)AsmLanguage.class));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
