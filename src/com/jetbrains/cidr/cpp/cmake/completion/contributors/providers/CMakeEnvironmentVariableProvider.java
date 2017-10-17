// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import java.util.Iterator;
import java.util.Collection;
import com.intellij.util.EnvironmentUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import com.jetbrains.cidr.PredefinedVariables;
import java.util.ArrayList;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;

public class CMakeEnvironmentVariableProvider extends CMakeVariableProvider
{
    @Override
    protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeEnvironmentVariableProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeEnvironmentVariableProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        CMakeVariableProvider.addCompletions(completionParameters, set, "$ENV{", getCompletionVariables(completionParameters));
    }
    
    @NotNull
    public static String[] getCompletionVariables(@NotNull final CompletionParameters completionParameters) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeEnvironmentVariableProvider", "getCompletionVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ArrayList<Object> list = new ArrayList<Object>();
        final Iterator<String> iterator = PredefinedVariables.getIDEVariables().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        for (final CMakeSettings.Configuration configuration : CMakeSettings.Companion.getInstance(completionParameters.getPosition().getProject()).getConfigurations()) {
            try {
                if (configuration.getGenerationPassSystemEnvironment()) {
                    list.addAll(EnvironmentUtil.getEnvironmentMap().keySet());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            list.addAll(configuration.getAdditionalGenerationEnvironment().keySet());
        }
        String[] array;
        try {
            array = list.toArray(new String[0]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeEnvironmentVariableProvider", "getCompletionVariables"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return array;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
