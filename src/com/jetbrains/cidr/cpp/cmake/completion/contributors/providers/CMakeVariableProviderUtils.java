// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.codeInsight.completion.CompletionParameters;

public class CMakeVariableProviderUtils
{
    public static boolean shouldCreatePolicyCompletions(final CompletionParameters completionParameters) {
        return completionParameters.getInvocationCount() >= 2;
    }
}
