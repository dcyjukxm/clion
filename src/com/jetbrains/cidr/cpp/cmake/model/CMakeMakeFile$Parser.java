// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.Trinity;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.io.FileUtil;
import gnu.trove.TObjectHashingStrategy;

static class Parser extends CMakeSettingsFileParser<Rule>
{
    private Parser() {
        super(": ");
    }
    
    @NotNull
    @Override
    protected TObjectHashingStrategy<String> getHashingStrategy() {
        TObjectHashingStrategy path_HASHING_STRATEGY;
        try {
            path_HASHING_STRATEGY = FileUtil.PATH_HASHING_STRATEGY;
            if (path_HASHING_STRATEGY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "getHashingStrategy"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return (TObjectHashingStrategy<String>)path_HASHING_STRATEGY;
    }
    
    @NotNull
    @Override
    protected Trinity<String, Rule, Integer> parseVariable(@NotNull final String s, @NotNull final String s2, final List<String> list) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < list.size()) {
            final String s3 = list.get(i);
            Label_0153: {
                try {
                    if (j != 0) {
                        break Label_0153;
                    }
                    final String s4 = s3;
                    final String s5 = "\t";
                    final boolean b = s4.startsWith(s5);
                    if (!b) {
                        break Label_0153;
                    }
                    break Label_0153;
                }
                catch (UnsupportedOperationException ex3) {
                    throw b(ex3);
                }
                try {
                    final String s4 = s3;
                    final String s5 = "\t";
                    final boolean b = s4.startsWith(s5);
                    if (!b) {
                        break;
                    }
                }
                catch (UnsupportedOperationException ex4) {
                    throw b(ex4);
                }
            }
            int n = 0;
            do {
                if (j == 0) {
                    n = s3.indexOf("@<<", n);
                    if (n != -1) {
                        j = 1;
                        n += 3;
                    }
                }
                if (j != 0) {
                    n = s3.indexOf("<<", n);
                    if (n == -1) {
                        break;
                    }
                    j = 0;
                    n += 2;
                }
            } while (j != 0);
            sb.append(s3);
            ++i;
        }
        Trinity create;
        try {
            create = Trinity.create((Object)s, (Object)new Rule(s, s2, sb.toString()), (Object)i);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
            }
        }
        catch (UnsupportedOperationException ex5) {
            throw b(ex5);
        }
        return (Trinity<String, Rule, Integer>)create;
    }
    
    @NotNull
    @Override
    protected Pair<String, Rule> parseVariable(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        throw new UnsupportedOperationException();
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
