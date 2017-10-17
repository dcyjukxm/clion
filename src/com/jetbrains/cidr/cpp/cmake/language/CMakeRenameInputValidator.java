// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.language;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.intellij.util.ProcessingContext;
import com.intellij.patterns.StandardPatterns;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.ElementPattern;
import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.intellij.lang.LanguageNamesValidation;
import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.refactoring.rename.RenameInputValidator;

public class CMakeRenameInputValidator implements RenameInputValidator
{
    private final NamesValidator myNamesValidator;
    
    public CMakeRenameInputValidator() {
        this.myNamesValidator = (NamesValidator)LanguageNamesValidation.INSTANCE.forLanguage((Language)CMakeLanguage.INSTANCE);
    }
    
    public ElementPattern<? extends PsiElement> getPattern() {
        return (ElementPattern<? extends PsiElement>)StandardPatterns.or(new ElementPattern[] { PlatformPatterns.psiElement(CMakeTokenTypes.C_MAKE_ARGUMENT), PlatformPatterns.psiElement(CMakeTokenTypes.C_MAKE_COMMAND_NAME) });
    }
    
    public boolean isInputValid(final String s, final PsiElement psiElement, final ProcessingContext processingContext) {
        return ((!(psiElement instanceof CMakeArgument) || !((CMakeArgument)psiElement).isCommandDefinitionName()) && !(psiElement instanceof CMakeCommandName)) || this.myNamesValidator.isIdentifier(s, psiElement.getProject());
    }
}
