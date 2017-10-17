// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache;

import com.jetbrains.cidr.cpp.cmake.cmakecache.psi.CMakeCacheTxtFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.cpp.cmake.cmakecache.psi.CMakeCacheTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.cpp.cmake.cmakecache.psi.CMakeCacheElementTypes;
import com.intellij.psi.tree.IFileElementType;
import com.jetbrains.cidr.cpp.cmake.cmakecache.psi.CMakeCacheParser;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.cmakecache.psi.CMakeCacheLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.lang.ParserDefinition;

public class CMakeCacheParserDefinition implements ParserDefinition
{
    @NotNull
    public Lexer createLexer(final Project project) {
        CMakeCacheLexer cMakeCacheLexer;
        try {
            cMakeCacheLexer = new CMakeCacheLexer();
            if (cMakeCacheLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/CMakeCacheParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)cMakeCacheLexer;
    }
    
    public PsiParser createParser(final Project project) {
        return (PsiParser)new CMakeCacheParser();
    }
    
    public IFileElementType getFileNodeType() {
        return CMakeCacheElementTypes.CMAKE_CACHE_FILE;
    }
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet whitespaces_SET;
        try {
            whitespaces_SET = CMakeCacheElementTypes.WHITESPACES_SET;
            if (whitespaces_SET == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/CMakeCacheParserDefinition", "getWhitespaceTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return whitespaces_SET;
    }
    
    @NotNull
    public TokenSet getCommentTokens() {
        TokenSet comments;
        try {
            comments = CMakeCacheElementTypes.COMMENTS;
            if (comments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/CMakeCacheParserDefinition", "getCommentTokens"));
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
            literals = CMakeCacheElementTypes.LITERALS;
            if (literals == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/CMakeCacheParserDefinition", "getStringLiteralElements"));
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
            element = CMakeCacheTokenTypes.Factory.createElement(astNode);
            if (element == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/CMakeCacheParserDefinition", "createElement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return element;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        return (PsiFile)new CMakeCacheTxtFile(fileViewProvider);
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return ParserDefinition.SpaceRequirements.MAY;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
