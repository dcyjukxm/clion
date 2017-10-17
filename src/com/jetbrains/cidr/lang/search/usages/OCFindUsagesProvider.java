// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.lexer.Lexer;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.lexer.OCLexerWithDirectives;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.findUsages.FindUsagesProvider;

public class OCFindUsagesProvider implements FindUsagesProvider
{
    private static final TokenSet CODE_ID_HOLDERS;
    private static final TokenSet TOKEN_AS_WORD;
    
    public WordsScanner getWordsScanner() {
        return (WordsScanner)new DefaultWordsScanner((Lexer)OCLexerWithDirectives.createDefault(), OCFindUsagesProvider.CODE_ID_HOLDERS, OCTokenTypes.COMMENTS, OCTokenTypes.ALL_STRINGS, TokenSet.ANY, OCFindUsagesProvider.TOKEN_AS_WORD);
    }
    
    public boolean canFindUsagesFor(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "canFindUsagesFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiElement instanceof OCSymbolDeclarator;
    }
    
    public String getHelpId(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getHelpId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    public String getType(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCSymbol symbol = null;
        Label_0068: {
            try {
                if (psiElement instanceof OCSymbolDeclarator) {
                    symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
                    break Label_0068;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            symbol = null;
        }
        final OCSymbol ocSymbol = symbol;
        String s = null;
        Label_0088: {
            try {
                if (ocSymbol != null) {
                    final String kindLowercase;
                    s = (kindLowercase = ocSymbol.getKindLowercase());
                    break Label_0088;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            String kindLowercase;
            s = (kindLowercase = "unknown");
            try {
                if (kindLowercase == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getType"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return s;
    }
    
    @NotNull
    public String getDescriptiveName(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getDescriptiveName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String a;
        try {
            a = a(psiElement);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getDescriptiveName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    private static String a(final PsiElement psiElement) {
        String s2 = null;
        Label_0068: {
            if (psiElement instanceof PsiNamedElement) {
                final String name = ((PsiNamedElement)psiElement).getName();
                String s = null;
                Label_0033: {
                    try {
                        if (name == null) {
                            break Label_0068;
                        }
                        s = name;
                        if (s == null) {
                            break Label_0033;
                        }
                        return s;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        s = name;
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getElementName"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return s;
            }
            try {
                s2 = "";
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getElementName"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s2;
    }
    
    @NotNull
    public String getNodeText(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getNodeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String descriptiveName;
        try {
            descriptiveName = this.getDescriptiveName(psiElement);
            if (descriptiveName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesProvider", "getNodeText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return descriptiveName;
    }
    
    static {
        CODE_ID_HOLDERS = TokenSet.orSet(new TokenSet[] { OCTokenTypes.POSSIBLE_ID_NAMES, TokenSet.create(new IElementType[] { OCTokenTypes.HEADER_PATH_LITERAL }) });
        TOKEN_AS_WORD = TokenSet.orSet(new TokenSet[] { OCTokenTypes.OVERLOADED_CPP_OPERATORS, OCTokenTypes.KEYWORDS, OCTokenTypes.DIRECTIVES });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
