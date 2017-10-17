// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.PrefixMatcher;

private static class TagPrefixMatcher extends PrefixMatcher
{
    public TagPrefixMatcher(final String s) {
        super(s);
    }
    
    public boolean prefixMatches(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$TagPrefixMatcher", "prefixMatches"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return StringUtil.startsWith((CharSequence)s, (CharSequence)this.getPrefix());
    }
    
    @NotNull
    public PrefixMatcher cloneWithPrefix(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$TagPrefixMatcher", "cloneWithPrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        TagPrefixMatcher tagPrefixMatcher;
        try {
            tagPrefixMatcher = new TagPrefixMatcher(s);
            if (tagPrefixMatcher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$TagPrefixMatcher", "cloneWithPrefix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return tagPrefixMatcher;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
