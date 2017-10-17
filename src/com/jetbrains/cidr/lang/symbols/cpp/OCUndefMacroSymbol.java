// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import javax.swing.Icon;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.Lexer;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.symbols.symtable.OCNamesInternary;
import com.intellij.lexer.LexerUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.TokenType;
import com.jetbrains.cidr.lang.lexer.OCLexer;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import java.util.Collections;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.jetbrains.cidr.lang.psi.OCUndefDirective;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCUndefMacroSymbol extends OCSymbolImpl<OCUndefDirective> implements Iconable
{
    public OCUndefMacroSymbol() {
    }
    
    public OCUndefMacroSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, @Nullable final String s) {
        super(project, virtualFile, n, s, Collections.emptyList());
    }
    
    @Nullable
    public static OCUndefMacroSymbol parseFromDirectiveContent(final CharSequence charSequence, final OCFile ocFile, final int n) {
        final Lexer rawLexerForPreprocessor = OCLexer.createRawLexerForPreprocessor();
        try {
            rawLexerForPreprocessor.start(charSequence);
            if (rawLexerForPreprocessor.getTokenType() == TokenType.WHITE_SPACE) {
                rawLexerForPreprocessor.advance();
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final IElementType tokenType = rawLexerForPreprocessor.getTokenType();
        try {
            if (tokenType != OCTokenTypes.IDENTIFIER) {
                if (!OCTokenTypes.KEYWORDS.contains(tokenType)) {
                    return null;
                }
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        final String intern = OCNamesInternary.intern(LexerUtil.getTokenText(rawLexerForPreprocessor).toString());
        try {
            rawLexerForPreprocessor.advance();
            if (ocFile != null) {
                final Project project = ocFile.getProject();
                return new OCUndefMacroSymbol(project, OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile), n, intern);
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        final Project project = null;
        return new OCUndefMacroSymbol(project, OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile), n, intern);
    }
    
    public Icon getIcon(final int n) {
        return this.getIcon();
    }
    
    @NotNull
    public OCSymbolKind getKind() {
        OCSymbolKind undef_MACRO;
        try {
            undef_MACRO = OCSymbolKind.UNDEF_MACRO;
            if (undef_MACRO == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCUndefMacroSymbol", "getKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return undef_MACRO;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
