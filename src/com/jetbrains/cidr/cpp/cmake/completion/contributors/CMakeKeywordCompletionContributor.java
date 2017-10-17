// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors;

import com.jetbrains.cidr.cpp.cmake.completion.contributors.providers.CMakeKeywordProvider;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeMacroCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFunctionCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeForeachCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeWhileCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeIfCommand;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.patterns.ElementPattern;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionContributor;

public class CMakeKeywordCompletionContributor extends AbstractCMakeCompletionContributor
{
    public static final String BREAK = "break";
    public static final String CONTINUE = "continue";
    
    @Override
    public void extendContributor(final CMakeCompletionContributor cMakeCompletionContributor) {
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE, (CompletionProvider)a(CMakeTokenTypes.IF, CMakeTokenTypes.WHILE, CMakeTokenTypes.FOREACH, CMakeTokenTypes.FUNCTION, CMakeTokenTypes.MACRO));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.withSuperParent(4, (Class)CMakeIfCommand.class), (CompletionProvider)a(CMakeTokenTypes.ELSEIF, CMakeTokenTypes.ELSE, CMakeTokenTypes.ENDIF));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.withSuperParent(4, (Class)CMakeWhileCommand.class), (CompletionProvider)a(CMakeTokenTypes.ENDWHILE));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.withSuperParent(4, (Class)CMakeForeachCommand.class), (CompletionProvider)a(CMakeTokenTypes.ENDFOREACH));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.withSuperParent(4, (Class)CMakeFunctionCommand.class), (CompletionProvider)a(CMakeTokenTypes.ENDFUNCTION));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.withSuperParent(4, (Class)CMakeMacroCommand.class), (CompletionProvider)a(CMakeTokenTypes.ENDMACRO));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.inside((Class)CMakeForeachCommand.class), (CompletionProvider)a(false, "break", "continue"));
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeKeywordCompletionContributor.ID_CAPTURE.inside((Class)CMakeWhileCommand.class), (CompletionProvider)a(false, "break", "continue"));
    }
    
    private static CompletionProvider<CompletionParameters> a(final IElementType... array) {
        return new CMakeKeywordProvider(array, true, true);
    }
    
    private static CompletionProvider<CompletionParameters> a(final boolean b, final String... array) {
        return new CMakeKeywordProvider(array, true, b);
    }
}
