// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.regex.Matcher;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.io.FileUtil;
import gnu.trove.TObjectHashingStrategy;
import java.util.regex.Pattern;

private static class Parser extends CMakeStringSettingsFileParser
{
    private static final String[] STRIP_PREFIXES;
    private static final Pattern COMPILER_PATTERN;
    private static final Pattern CMAKE_2_8_11_TARGET_FLAGS_PATTERN;
    
    @NotNull
    @Override
    protected TObjectHashingStrategy<String> getHashingStrategy() {
        TObjectHashingStrategy path_HASHING_STRATEGY;
        try {
            path_HASHING_STRATEGY = FileUtil.PATH_HASHING_STRATEGY;
            if (path_HASHING_STRATEGY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "getHashingStrategy"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (TObjectHashingStrategy<String>)path_HASHING_STRATEGY;
    }
    
    @NotNull
    @Override
    protected Pair<String, String> parseVariable(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "beforeEqual", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "parseVariable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "afterEqual", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "parseVariable"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        Pair create;
        try {
            create = Pair.create((Object)s.trim(), (Object)s2.trim());
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "parseVariable"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (Pair<String, String>)create;
    }
    
    @NotNull
    @Override
    protected String convertLineAndKeepEOLs(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lineWithEOLs", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "convertLineAndKeepEOLs"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        for (final String s2 : Parser.STRIP_PREFIXES) {
            Label_0130: {
                String s5 = null;
                Label_0095: {
                    try {
                        if (!s.startsWith(s2)) {
                            break Label_0130;
                        }
                        final String s3 = s;
                        final String s4 = s2;
                        final int n = s4.length();
                        s5 = s3.substring(n);
                        if (s5 == null) {
                            break Label_0095;
                        }
                        return s5;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final String s3 = s;
                        final String s4 = s2;
                        final int n = s4.length();
                        s5 = s3.substring(n);
                        if (s5 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "convertLineAndKeepEOLs"));
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                return s5;
            }
        }
        final Matcher matcher = Parser.COMPILER_PATTERN.matcher(s);
        Label_0224: {
            try {
                if (!matcher.matches()) {
                    break Label_0224;
                }
                final String string = matcher.group(1) + "_COMPILER=" + matcher.group(2);
                if (string != null) {
                    return string;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "convertLineAndKeepEOLs"));
        }
        final Matcher matcher2 = Parser.CMAKE_2_8_11_TARGET_FLAGS_PATTERN.matcher(s);
        Label_0308: {
            String s8 = null;
            Label_0273: {
                try {
                    if (!matcher2.matches()) {
                        break Label_0308;
                    }
                    final StringBuilder sb = new StringBuilder();
                    final String s6 = "_AD_HOC_TARGET_FLAGS_=";
                    final StringBuilder sb2 = sb.append(s6);
                    final Matcher matcher3 = matcher2;
                    final int n2 = 1;
                    final String s7 = matcher3.group(n2);
                    final StringBuilder sb3 = sb2.append(s7);
                    s8 = sb3.toString();
                    if (s8 == null) {
                        break Label_0273;
                    }
                    return s8;
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
                try {
                    final StringBuilder sb = new StringBuilder();
                    final String s6 = "_AD_HOC_TARGET_FLAGS_=";
                    final StringBuilder sb2 = sb.append(s6);
                    final Matcher matcher3 = matcher2;
                    final int n2 = 1;
                    final String s7 = matcher3.group(n2);
                    final StringBuilder sb3 = sb2.append(s7);
                    s8 = sb3.toString();
                    if (s8 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "convertLineAndKeepEOLs"));
                    }
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
            return s8;
            try {
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile$Parser", "convertLineAndKeepEOLs"));
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
        }
        return s;
    }
    
    static {
        STRIP_PREFIXES = new String[] { "# Custom flags: ", "# Custom defines: " };
        COMPILER_PATTERN = Pattern.compile("# compile (\\w+) with (.*)", 32);
        CMAKE_2_8_11_TARGET_FLAGS_PATTERN = Pattern.compile("# TARGET_FLAGS = (.*)", 32);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
