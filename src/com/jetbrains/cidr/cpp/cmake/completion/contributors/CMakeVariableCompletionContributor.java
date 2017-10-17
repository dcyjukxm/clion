// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors;

import com.intellij.patterns.StandardPatterns;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.providers.CMakeSetAndUnsetPlainVariableProvider;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.providers.CMakeEnvironmentVariableProvider;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.providers.CMakeVariableProvider;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionContributor;
import com.intellij.psi.tree.TokenSet;
import com.intellij.patterns.StringPattern;

public class CMakeVariableCompletionContributor extends AbstractCMakeCompletionContributor
{
    public static final String SET_AND_UNSET_STRING_PATTERN = "(?i)(un)?set";
    public static final StringPattern SET_AND_UNSET_PATTERN;
    private static final TokenSet VARIABLE_COMPLETION_PSI_ELEMENTS;
    
    @Override
    public void extendContributor(final CMakeCompletionContributor cMakeCompletionContributor) {
        final CMakeVariableProvider cMakeVariableProvider = new CMakeVariableProvider();
        final CMakeEnvironmentVariableProvider cMakeEnvironmentVariableProvider = new CMakeEnvironmentVariableProvider();
        for (final IElementType elementType : CMakeVariableCompletionContributor.VARIABLE_COMPLETION_PSI_ELEMENTS.getTypes()) {
            cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement(elementType), (CompletionProvider)cMakeVariableProvider);
            cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement(elementType), (CompletionProvider)cMakeVariableProvider);
            cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement(elementType), (CompletionProvider)cMakeEnvironmentVariableProvider);
        }
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement(CMakeTokenTypes.LITERAL).afterLeaf((ElementPattern)PlatformPatterns.psiElement(CMakeTokenTypes.LPAR).afterLeaf((ElementPattern)PlatformPatterns.psiElement().withText((ElementPattern)CMakeVariableCompletionContributor.SET_AND_UNSET_PATTERN))), (CompletionProvider)new CMakeSetAndUnsetPlainVariableProvider());
    }
    
    public static boolean shouldAutoPopup(final PsiElement psiElement, final char c) {
        final IElementType elementType = psiElement.getNode().getElementType();
        return '{' == c && CMakeVariableCompletionContributor.VARIABLE_COMPLETION_PSI_ELEMENTS.contains(elementType) && StringUtil.containsChar(psiElement.getText(), '$');
    }
    
    static {
        SET_AND_UNSET_PATTERN = StandardPatterns.string().matches("(?i)(un)?set");
        VARIABLE_COMPLETION_PSI_ELEMENTS = TokenSet.create(new IElementType[] { CMakeTokenTypes.LITERAL, CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS });
    }
}
