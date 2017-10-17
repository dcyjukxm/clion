// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.lang.ParserDefinition;

public class CMakeParserDefinition implements ParserDefinition
{
    @NotNull
    public Lexer createLexer(final Project project) {
        CMakeLexer cMakeLexer;
        try {
            cMakeLexer = new CMakeLexer();
            if (cMakeLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)cMakeLexer;
    }
    
    public PsiParser createParser(final Project project) {
        return (PsiParser)new CMakeParser();
    }
    
    public IFileElementType getFileNodeType() {
        return CMakeElementTypes.CMAKE_FILE;
    }
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet white_SPACES;
        try {
            white_SPACES = CMakeElementTypes.WHITE_SPACES;
            if (white_SPACES == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeParserDefinition", "getWhitespaceTokens"));
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
            comments = CMakeElementTypes.COMMENTS;
            if (comments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeParserDefinition", "getCommentTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return comments;
    }
    
    @NotNull
    public TokenSet getStringLiteralElements() {
        TokenSet literals;
        try {
            literals = CMakeElementTypes.LITERALS;
            if (literals == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeParserDefinition", "getStringLiteralElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return literals;
    }
    
    @NotNull
    public PsiElement createElement(final ASTNode astNode) {
        PsiElement element;
        try {
            element = CMakeTokenTypes.Factory.createElement(astNode);
            if (element == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeParserDefinition", "createElement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return element;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        return (PsiFile)new CMakeFile(fileViewProvider);
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return ParserDefinition.SpaceRequirements.MAY;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
