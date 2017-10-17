// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.intellij.psi.PsiFileFactory;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.lang.editor.OCInjectionListener;
import com.jetbrains.cidr.lang.psi.impl.OCCodeFragmentImpl;
import com.intellij.psi.impl.source.tree.injected.InjectedFileViewProvider;
import com.intellij.psi.impl.source.PsiPlainTextFileImpl;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.tree.IElementType;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.NotNullLazyKey;
import com.intellij.lang.ParserDefinition;

public class OCParserDefinition implements ParserDefinition
{
    private static final NotNullLazyKey<PsiFile, Project> DUMMY_FILE;
    
    @NotNull
    public Lexer createLexer(final Project project) {
        final PsiFile psiFile = (PsiFile)OCParserDefinition.DUMMY_FILE.getValue((UserDataHolder)project);
        final OCInclusionContext empty = OCInclusionContext.empty(OCLanguageKindCalculator.calculateLanguageKindFast(psiFile), psiFile);
        OCPreprocessingLexer ocPreprocessingLexer;
        try {
            ocPreprocessingLexer = new OCPreprocessingLexer(empty, (OCFile)psiFile);
            if (ocPreprocessingLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)ocPreprocessingLexer;
    }
    
    public PsiParser createParser(final Project project) {
        return (PsiParser)OCParser.getInstance();
    }
    
    public IFileElementType getFileNodeType() {
        return OCTokenTypes.OC_FILE;
    }
    
    @NotNull
    public TokenSet getWhitespaceTokens() {
        TokenSet whitespaces;
        try {
            whitespaces = OCTokenTypes.WHITESPACES;
            if (whitespaces == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "getWhitespaceTokens"));
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
            comments = OCTokenTypes.COMMENTS;
            if (comments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "getCommentTokens"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return comments;
    }
    
    @NotNull
    public TokenSet getStringLiteralElements() {
        TokenSet all_STRINGS;
        try {
            all_STRINGS = OCTokenTypes.ALL_STRINGS;
            if (all_STRINGS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "getStringLiteralElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return all_STRINGS;
    }
    
    @NotNull
    public PsiElement createElement(final ASTNode node) {
        final IElementType elementType = node.getElementType();
        ASTWrapperPsiElement astWrapperPsiElement = null;
        Label_0129: {
            PsiElement psiElement2 = null;
            Label_0094: {
                Label_0068: {
                    PsiElement psiElement = null;
                    Label_0033: {
                        try {
                            if (!(elementType instanceof OCPsiElementType)) {
                                break Label_0068;
                            }
                            final OCPsiElementType ocPsiElementType = (OCPsiElementType)elementType;
                            final OCPsiElementType ocPsiElementType2 = ocPsiElementType;
                            final ASTNode astNode = node;
                            psiElement = ocPsiElementType2.createPsi(astNode);
                            if (psiElement == null) {
                                break Label_0033;
                            }
                            return psiElement;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCPsiElementType ocPsiElementType = (OCPsiElementType)elementType;
                            final OCPsiElementType ocPsiElementType2 = ocPsiElementType;
                            final ASTNode astNode = node;
                            psiElement = ocPsiElementType2.createPsi(astNode);
                            if (psiElement == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "createElement"));
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    return psiElement;
                    try {
                        if (!(elementType instanceof OCStubElementType)) {
                            break Label_0129;
                        }
                        final OCPsiElementType ocPsiElementType3 = (OCPsiElementType)elementType;
                        final OCStubElementType ocStubElementType = (OCStubElementType)ocPsiElementType3;
                        final ASTNode astNode2 = node;
                        psiElement2 = ocStubElementType.createPsi(astNode2);
                        if (psiElement2 == null) {
                            break Label_0094;
                        }
                        return psiElement2;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final OCPsiElementType ocPsiElementType3 = (OCPsiElementType)elementType;
                    final OCStubElementType ocStubElementType = (OCStubElementType)ocPsiElementType3;
                    final ASTNode astNode2 = node;
                    psiElement2 = ocStubElementType.createPsi(astNode2);
                    if (psiElement2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "createElement"));
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            return psiElement2;
            try {
                astWrapperPsiElement = new ASTWrapperPsiElement(node);
                if (astWrapperPsiElement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "createElement"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return (PsiElement)astWrapperPsiElement;
    }
    
    public PsiFile createFile(final FileViewProvider fileViewProvider) {
        final Project project = fileViewProvider.getManager().getProject();
        try {
            if (project.getUserData((Key)OCLanguage.LANGUAGE_SUPPORT_DISABLED) == Boolean.TRUE) {
                return (PsiFile)new PsiPlainTextFileImpl(fileViewProvider);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (fileViewProvider instanceof InjectedFileViewProvider) {
            final OCCodeFragmentImpl ocCodeFragmentImpl = new OCCodeFragmentImpl(fileViewProvider, true, (IElementType)OCElementTypes.EXPRESSION_CODE_FRAGMENT);
            ((OCInjectionListener)project.getMessageBus().syncPublisher((Topic)OCInjectionListener.INJECTION_TOPIC)).didInject(ocCodeFragmentImpl, (PsiLanguageInjectionHost)InjectedLanguageUtil.getShreds(fileViewProvider).getHostPointer().getElement());
            return (PsiFile)ocCodeFragmentImpl;
        }
        return (PsiFile)new OCFileImpl(fileViewProvider);
    }
    
    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode2) {
        return ParserDefinition.SpaceRequirements.MAY;
    }
    
    private static PsiFile a(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/parser/OCParserDefinition", "createDummyFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return PsiFileFactory.getInstance(project).createFileFromText("dummy." + CLanguageKind.maxLanguage(project).getDefaultSourceExtension(), (Language)OCLanguage.getInstance(), (CharSequence)"");
    }
    
    static {
        DUMMY_FILE = NotNullLazyKey.create("PARSER_DEFINITION_DUMMY", project -> a(project));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
