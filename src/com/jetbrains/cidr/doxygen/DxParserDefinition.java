// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.lang.Language;
import com.jetbrains.cidr.doxygen.psi.DxTokenType;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.doxygen.parser.DoxygenParser;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ParserDefinition;

public class DxParserDefinition implements ParserDefinition
{
    public static final TokenSet WHITE_SPACES;
    private static final TokenSet COMMENTS;
    public static final IFileElementType FILE;
    
    @NotNull
    public Lexer createLexer(final Project project) {
        DoxygenLexerAdapter doxygenLexerAdapter;
        try {
            doxygenLexerAdapter = new DoxygenLexerAdapter();
            if (doxygenLexerAdapter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)doxygenLexerAdapter;
    }
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet white_SPACES;
        try {
            white_SPACES = DxParserDefinition.WHITE_SPACES;
            if (white_SPACES == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxParserDefinition", "getWhitespaceTokens"));
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
            comments = DxParserDefinition.COMMENTS;
            if (comments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxParserDefinition", "getCommentTokens"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxParserDefinition", "getStringLiteralElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return empty;
    }
    
    @NotNull
    public PsiParser createParser(final Project project) {
        DoxygenParser doxygenParser;
        try {
            doxygenParser = new DoxygenParser();
            if (doxygenParser == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxParserDefinition", "createParser"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (PsiParser)doxygenParser;
    }
    
    public IFileElementType getFileNodeType() {
        return DxParserDefinition.FILE;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        return (PsiFile)new DxFile(fileViewProvider);
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return ParserDefinition.SpaceRequirements.MAY;
    }
    
    @NotNull
    public PsiElement createElement(final ASTNode astNode) {
        PsiElement element;
        try {
            element = DxTypes.Factory.createElement(astNode);
            if (element == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxParserDefinition", "createElement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return element;
    }
    
    static {
        WHITE_SPACES = TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE });
        COMMENTS = TokenSet.create(new IElementType[] { new DxTokenType("FAKE_COMMENT") });
        FILE = new IFileElementType(Language.findInstance((Class)DxLanguage.class));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
