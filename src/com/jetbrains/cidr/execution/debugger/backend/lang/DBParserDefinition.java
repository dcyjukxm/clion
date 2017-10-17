// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;

public abstract class DBParserDefinition implements ParserDefinition
{
    @NotNull
    protected abstract Language getLanguage();
    
    protected abstract LanguageFileType getFileType();
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet create;
        try {
            create = TokenSet.create(new IElementType[] { GDBTokenType.WHITE_SPACE });
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition", "getWhitespaceTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return create;
    }
    
    @NotNull
    public TokenSet getCommentTokens() {
        TokenSet empty;
        try {
            empty = TokenSet.EMPTY;
            if (empty == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition", "getCommentTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return empty;
    }
    
    @NotNull
    public TokenSet getStringLiteralElements() {
        TokenSet create;
        try {
            create = TokenSet.create(new IElementType[] { GDBTokenType.QUOTED_STRING });
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition", "getStringLiteralElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return create;
    }
    
    @NotNull
    public PsiElement createElement(final ASTNode node) {
        ASTWrapperPsiElement astWrapperPsiElement = null;
        Label_0066: {
            GDBExpressionPlaceholder gdbExpressionPlaceholder = null;
            Label_0031: {
                try {
                    if (node.getElementType() != GDBTokenType.EXPRESSION_PLACEHOLDER) {
                        break Label_0066;
                    }
                    final ASTNode astNode = node;
                    gdbExpressionPlaceholder = new GDBExpressionPlaceholder(astNode);
                    if (gdbExpressionPlaceholder == null) {
                        break Label_0031;
                    }
                    return (PsiElement)gdbExpressionPlaceholder;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final ASTNode astNode = node;
                    gdbExpressionPlaceholder = new GDBExpressionPlaceholder(astNode);
                    if (gdbExpressionPlaceholder == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition", "createElement"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return (PsiElement)gdbExpressionPlaceholder;
            try {
                astWrapperPsiElement = new ASTWrapperPsiElement(node);
                if (astWrapperPsiElement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition", "createElement"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return (PsiElement)astWrapperPsiElement;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        final LanguageFileType fileType = this.getFileType();
        return (PsiFile)new PsiFileBase(fileViewProvider, fileType.getLanguage()) {
            @NotNull
            public FileType getFileType() {
                LanguageFileType val$type;
                try {
                    val$type = fileType;
                    if (val$type == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition$1", "getFileType"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return (FileType)val$type;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return ParserDefinition.SpaceRequirements.MAY;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
