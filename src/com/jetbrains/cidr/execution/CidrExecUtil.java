// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import java.util.StringTokenizer;
import com.intellij.execution.ExecutionFinishedException;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.text.StringUtil;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class CidrExecUtil
{
    private static final int ERROR_MESSAGE_WRAP_LENGTH = 80;
    
    @NotNull
    public static Map<String, String> appendBuildPathVars(@NotNull final Map<String, String> map, @Nullable final String s) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/CidrExecUtil", "appendBuildPathVars"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            appendSearchPath(map, "DYLD_LIBRARY_PATH", s);
            appendSearchPath(map, "DYLD_FRAMEWORK_PATH", s);
            appendSearchPath(map, "__XPC_DYLD_LIBRARY_PATH", s);
            appendSearchPath(map, "__XPC_DYLD_FRAMEWORK_PATH", s);
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrExecUtil", "appendBuildPathVars"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return map;
    }
    
    public static void setIfAbsent(@NotNull final Map<String, String> map, @NotNull final String s, @Nullable final String s2) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/CidrExecUtil", "setIfAbsent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "paramName", "com/jetbrains/cidr/execution/CidrExecUtil", "setIfAbsent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0109: {
            try {
                if (s2 == null) {
                    return;
                }
                final Map<String, String> map2 = map;
                final String s3 = s;
                final boolean b = map2.containsKey(s3);
                if (!b) {
                    break Label_0109;
                }
                return;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Map<String, String> map2 = map;
                final String s3 = s;
                final boolean b = map2.containsKey(s3);
                if (!b) {
                    map.put(s, s2);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
    }
    
    public static void appendSearchPath(@NotNull final Map<String, String> map, @NotNull final String s, @Nullable final String s2) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/CidrExecUtil", "appendSearchPath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "paramName", "com/jetbrains/cidr/execution/CidrExecUtil", "appendSearchPath"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s2 == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String s3 = map.get(s);
        String string;
        if (s3 != null) {
            string = s3 + File.pathSeparator + s2;
        }
        else {
            string = s2;
        }
        map.put(s, string);
    }
    
    public static void checkErrorOnTermination(@NotNull final StringBuilder sb, @NotNull final ExecutionResult<?> executionResult) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errOut", "com/jetbrains/cidr/execution/CidrExecUtil", "checkErrorOnTermination"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (executionResult == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/CidrExecUtil", "checkErrorOnTermination"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (executionResult.isDone()) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String string = sb.toString();
        Object o = null;
        if (!StringUtil.isEmptyOrSpaces(string)) {
            o = new ExecutionException(string);
        }
        executionResult.setException((Throwable)new ExecutionFinishedException((Throwable)o));
    }
    
    public static String wrapErrorOutput(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/execution/CidrExecUtil", "wrapErrorOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return wrapErrorOutput(s, 80);
    }
    
    public static String wrapErrorOutput(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/execution/CidrExecUtil", "wrapErrorOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String[] splitByLinesKeepSeparators = StringUtil.splitByLinesKeepSeparators(s);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splitByLinesKeepSeparators.length; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(splitByLinesKeepSeparators[i], " \n\r");
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                sb.append(nextToken);
                int length = nextToken.length();
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken2 = stringTokenizer.nextToken();
                    final int length2 = nextToken2.length();
                    if (length + length2 > n) {
                        sb.append('\n');
                        length = length2;
                    }
                    else {
                        sb.append(' ');
                        length += length2 + 1;
                    }
                    sb.append(nextToken2);
                }
            }
            try {
                if (i < splitByLinesKeepSeparators.length - 1) {
                    sb.append('\n');
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return sb.toString();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
