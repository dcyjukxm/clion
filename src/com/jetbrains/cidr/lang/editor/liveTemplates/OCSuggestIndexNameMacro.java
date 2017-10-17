// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Macro;

public class OCSuggestIndexNameMacro extends Macro
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public String getName() {
        return "suggestIndexName";
    }
    
    public String getPresentableName() {
        return "suggestIndexName()";
    }
    
    @NotNull
    public String getDefaultValue() {
        String s;
        try {
            s = "i";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestIndexNameMacro", "getDefaultValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestIndexNameMacro", "calculateResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement insertionPlace = OCTemplatesUtil.getInsertionPlace(expressionContext);
        final PsiFile containingFile = insertionPlace.getContainingFile();
        Label_0075: {
            try {
                if (OCSuggestIndexNameMacro.$assertionsDisabled) {
                    break Label_0075;
                }
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    break Label_0075;
                }
                break Label_0075;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final String[] array2 = { "i", "j", "k", "l", "m", "n" };
        int n = 0;
        while (true) {
            for (String string : array2) {
                if (n > 0) {
                    string += n;
                }
                try {
                    if (a(string, containingFile, insertionPlace)) {
                        return (Result)new TextResult(string);
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            ++n;
        }
    }
    
    private static boolean a(@NotNull final String s, @NotNull final PsiFile psiFile, @Nullable final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidate", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestIndexNameMacro", "isNameAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestIndexNameMacro", "isNameAvailable"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCSymbol resolveNameInScope = OCCodeInsightUtil.resolveNameInScope(OCSymbolKind.LOCAL_VARIABLE, s, null, psiElement, psiFile.getProject());
        try {
            if (resolveNameInScope == null) {
                return true;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (psiElement == null) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final PsiElement parent = psiElement.getParent();
        Label_0161: {
            try {
                if (!(parent instanceof OCDeclarator)) {
                    return false;
                }
                final PsiElement psiElement2 = parent;
                final OCSymbol ocSymbol = resolveNameInScope;
                final PsiElement psiElement3 = ocSymbol.locateDefinition();
                final boolean b = psiElement2.equals(psiElement3);
                if (b) {
                    break Label_0161;
                }
                return false;
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            try {
                final PsiElement psiElement2 = parent;
                final OCSymbol ocSymbol = resolveNameInScope;
                final PsiElement psiElement3 = ocSymbol.locateDefinition();
                final boolean b = psiElement2.equals(psiElement3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return false;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSuggestIndexNameMacro.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
