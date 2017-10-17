// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.doxygen.psi.DxParamId;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;

public class DxFindUsagesProvider implements FindUsagesProvider
{
    private static final DefaultWordsScanner WORDS_SCANNER;
    
    @Nullable
    public WordsScanner getWordsScanner() {
        return (WordsScanner)DxFindUsagesProvider.WORDS_SCANNER;
    }
    
    public boolean canFindUsagesFor(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "canFindUsagesFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiElement instanceof PsiNamedElement;
    }
    
    @Nullable
    public String getHelpId(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getHelpId"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String s2 = null;
        Label_0099: {
            String s = null;
            Label_0064: {
                try {
                    if (!(psiElement instanceof DxParamId)) {
                        break Label_0099;
                    }
                    s = "parameter";
                    if (s == null) {
                        break Label_0064;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    s = "parameter";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getType"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return s;
            try {
                s2 = "";
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getType"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return s2;
    }
    
    @NotNull
    public String getDescriptiveName(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getDescriptiveName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String s2 = null;
        Label_0106: {
            String s = null;
            Label_0071: {
                try {
                    if (!(psiElement instanceof DxParamId)) {
                        break Label_0106;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final DxParamId dxParamId = (DxParamId)psiElement2;
                    s = dxParamId.getName();
                    if (s == null) {
                        break Label_0071;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final DxParamId dxParamId = (DxParamId)psiElement2;
                    s = dxParamId.getName();
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getDescriptiveName"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return s;
            try {
                s2 = "";
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getDescriptiveName"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return s2;
    }
    
    @NotNull
    public String getNodeText(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getNodeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String s2 = null;
        Label_0106: {
            String s = null;
            Label_0071: {
                try {
                    if (!(psiElement instanceof DxParamId)) {
                        break Label_0106;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final DxParamId dxParamId = (DxParamId)psiElement2;
                    s = dxParamId.getName();
                    if (s == null) {
                        break Label_0071;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final DxParamId dxParamId = (DxParamId)psiElement2;
                    s = dxParamId.getName();
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getNodeText"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return s;
            try {
                s2 = "";
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFindUsagesProvider", "getNodeText"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return s2;
    }
    
    static {
        WORDS_SCANNER = new DefaultWordsScanner((Lexer)new DoxygenLexerAdapter(), TokenSet.create(new IElementType[] { DxTypes.PARAM_ID }), TokenSet.EMPTY, TokenSet.EMPTY);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
