// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors;

import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.patterns.ElementPattern;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.providers.CMakeCommandProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionContributor;

public class CMakeCommandCompletionContributor extends AbstractCMakeCompletionContributor
{
    @Override
    public void extendContributor(final CMakeCompletionContributor cMakeCompletionContributor) {
        cMakeCompletionContributor.extend(CompletionType.BASIC, (ElementPattern)CMakeCommandCompletionContributor.ID_CAPTURE, (CompletionProvider)new CMakeCommandProvider());
    }
}
