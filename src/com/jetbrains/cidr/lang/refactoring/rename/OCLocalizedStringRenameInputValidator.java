// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ProcessingContext;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.ElementPattern;
import com.intellij.refactoring.rename.RenameInputValidator;

public class OCLocalizedStringRenameInputValidator implements RenameInputValidator
{
    public ElementPattern<? extends PsiElement> getPattern() {
        return (ElementPattern<? extends PsiElement>)PlatformPatterns.psiElement((Class)OCLocalizedString.class);
    }
    
    public boolean isInputValid(final String s, final PsiElement psiElement, final ProcessingContext processingContext) {
        return StringUtil.isNotEmpty(s);
    }
}
