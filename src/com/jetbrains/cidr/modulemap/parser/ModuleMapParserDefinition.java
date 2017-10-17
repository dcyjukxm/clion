// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.parser;

import com.intellij.lang.LanguageUtil;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapFileImpl;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.modulemap.psi.ModuleMapTokenTypes;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.lexer.ModuleMapLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.lang.ParserDefinition;

public class ModuleMapParserDefinition implements ParserDefinition
{
    @NotNull
    public Lexer createLexer(final Project project) {
        ModuleMapLexer moduleMapLexer;
        try {
            moduleMapLexer = new ModuleMapLexer();
            if (moduleMapLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/parser/ModuleMapParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)moduleMapLexer;
    }
    
    public PsiParser createParser(final Project project) {
        return (PsiParser)new ModuleMapParser();
    }
    
    public IFileElementType getFileNodeType() {
        return ModuleMapTokenTypes.MODULE_MAP_FILE;
    }
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet whitespaces;
        try {
            whitespaces = ModuleMapTokenTypes.WHITESPACES;
            if (whitespaces == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/parser/ModuleMapParserDefinition", "getWhitespaceTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return whitespaces;
    }
    
    @NotNull
    public TokenSet getCommentTokens() {
        TokenSet comments;
        try {
            comments = ModuleMapTokenTypes.COMMENTS;
            if (comments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/parser/ModuleMapParserDefinition", "getCommentTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return comments;
    }
    
    @NotNull
    public TokenSet getStringLiteralElements() {
        TokenSet strings;
        try {
            strings = ModuleMapTokenTypes.STRINGS;
            if (strings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/parser/ModuleMapParserDefinition", "getStringLiteralElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return strings;
    }
    
    @NotNull
    public PsiElement createElement(final ASTNode astNode) {
        PsiElement element;
        try {
            element = ModuleMapParserTypes.Factory.createElement(astNode);
            if (element == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/parser/ModuleMapParserDefinition", "createElement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return element;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        return (PsiFile)new ModuleMapFileImpl(fileViewProvider);
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return LanguageUtil.canStickTokensTogetherByLexer(astNode, astNode2, (Lexer)new ModuleMapLexer());
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
