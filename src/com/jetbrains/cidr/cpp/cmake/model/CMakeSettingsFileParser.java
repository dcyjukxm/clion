// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import gnu.trove.TObjectHashingStrategy;
import com.intellij.openapi.util.Trinity;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import java.util.Map;
import com.intellij.util.containers.hash.EqualityPolicy;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Function;
import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import com.intellij.util.containers.hash.LinkedHashMap;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import com.intellij.util.containers.LinkedMultiMap;
import java.io.File;
import org.jetbrains.annotations.NotNull;

abstract class CMakeSettingsFileParser<T>
{
    @NotNull
    private final String mySeparator;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    protected CMakeSettingsFileParser() {
        this("=");
    }
    
    protected CMakeSettingsFileParser(@NotNull final String mySeparator) {
        if (mySeparator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "separator", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "<init>"));
        }
        this.mySeparator = mySeparator;
    }
    
    @NotNull
    public LinkedMultiMap<String, T> parse(@NotNull final File file, @NotNull final String s) throws CMakeException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
            }
        }
        catch (CMakeException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
            }
        }
        catch (CMakeException ex2) {
            throw b(ex2);
        }
        com.intellij.util.containers.LinkedMultiMap<String, T> parse;
        try {
            parse = this.parse(loadText(file, s));
            if (parse == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
            }
        }
        catch (CMakeException ex3) {
            throw b(ex3);
        }
        return parse;
    }
    
    @NotNull
    public LinkedHashMap<String, T> parseUnique(@NotNull final File file, @NotNull final String s) throws CMakeException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseUnique"));
            }
        }
        catch (CMakeException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseUnique"));
            }
        }
        catch (CMakeException ex2) {
            throw b(ex2);
        }
        com.intellij.util.containers.hash.LinkedHashMap<String, T> uniqueMap;
        try {
            uniqueMap = this.toUniqueMap(this.parse(file, s));
            if (uniqueMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseUnique"));
            }
        }
        catch (CMakeException ex3) {
            throw b(ex3);
        }
        return uniqueMap;
    }
    
    @NotNull
    protected static String loadText(final File file, @NotNull final String s) throws CMakeException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "loadText"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            String loadFile;
            try {
                loadFile = FileUtil.loadFile(file, s);
                if (loadFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "loadText"));
                }
            }
            catch (CMakeException ex2) {
                throw b(ex2);
            }
            return loadFile;
        }
        catch (IOException ex3) {
            throw CMakeException.cannotReadFile(file, ex3);
        }
    }
    
    @NotNull
    public LinkedMultiMap<String, T> parse(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        LinkedMultiMap linkedMultiMap;
        try {
            linkedMultiMap = (LinkedMultiMap)this.parse(s, null).second;
            if (linkedMultiMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (LinkedMultiMap<String, T>)linkedMultiMap;
    }
    
    @NotNull
    public LinkedHashMap<String, T> parseUnique(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseUnique"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        com.intellij.util.containers.hash.LinkedHashMap<String, T> uniqueMap;
        try {
            uniqueMap = this.toUniqueMap(this.parse(s));
            if (uniqueMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseUnique"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return uniqueMap;
    }
    
    protected LinkedHashMap<String, T> toUniqueMap(final LinkedMultiMap<String, T> linkedMultiMap) {
        final LinkedHashMap linkedHashMap = new LinkedHashMap((EqualityPolicy)this.a());
        for (final Map.Entry<K, Collection> entry : linkedMultiMap.entrySet()) {
            final Object firstItem = ContainerUtil.getFirstItem((Collection)entry.getValue());
            Label_0075: {
                try {
                    if (CMakeSettingsFileParser.$assertionsDisabled) {
                        break Label_0075;
                    }
                    final Object o = firstItem;
                    if (o == null) {
                        break Label_0075;
                    }
                    break Label_0075;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final Object o = firstItem;
                    if (o == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            linkedHashMap.put((Object)entry.getKey(), firstItem);
        }
        return (LinkedHashMap<String, T>)linkedHashMap;
    }
    
    @NotNull
    protected Pair<String, LinkedMultiMap<String, T>> parse(@NotNull final String s, @Nullable final Function<Pair<String, T>, Pair<String, T>> function) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final LinkedMultiMap<String, T> linkedMultiMap = new LinkedMultiMap<String, T>() {
            protected EqualityPolicy<String> getEqualityPolicy() {
                return (EqualityPolicy<String>)CMakeSettingsFileParser.this.a();
            }
        };
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(StringUtil.splitByLinesKeepSeparators(s)));
        for (int i = 0, size = list.size(); i < size; ++i) {
            final String trimLeading = StringUtil.trimLeading(this.convertLineAndKeepEOLs(StringUtil.trimLeading((String)list.get(i))));
            final int indexOfAny = StringUtil.indexOfAny(trimLeading, "\r\n");
            String substring = null;
            Label_0148: {
                try {
                    if (indexOfAny == -1) {
                        substring = trimLeading;
                        break Label_0148;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                substring = trimLeading.substring(0, indexOfAny);
            }
            final String s2 = substring;
            String substring2 = null;
            Label_0172: {
                try {
                    if (indexOfAny == -1) {
                        substring2 = "";
                        break Label_0172;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                substring2 = trimLeading.substring(indexOfAny);
            }
            final String s3 = substring2;
            final String s4 = s2;
            try {
                if (s4.startsWith("#")) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            this.preprocess(s4);
            final int index = s4.indexOf(this.mySeparator);
            try {
                if (index < 0) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            String trim = null;
            String substring3 = null;
            List<Object> list2 = null;
            Label_0277: {
                try {
                    trim = s4.substring(0, index).trim();
                    substring3 = s4.substring(index + 1);
                    if (i == size - 1) {
                        list2 = Collections.emptyList();
                        break Label_0277;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                list2 = list.subList(i + 1, size);
            }
            final com.intellij.openapi.util.Trinity<String, T, Integer> variable = this.parseVariable(trim, substring3, (List<String>)list2);
            final String s5 = (String)variable.first;
            Object o = variable.second;
            if (function != null) {
                final Pair pair = (Pair)function.fun((Object)Pair.create((Object)s4, o));
                list.set(i, (String)pair.first + s3);
                o = pair.second;
            }
            linkedMultiMap.putValue((Object)s5, o);
            i += (int)variable.third;
        }
        Pair create = null;
        Label_0417: {
            try {
                this.cleanup();
                if (function == null) {
                    final String join = s;
                    break Label_0417;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            final String join = StringUtil.join((Collection)list, "");
            try {
                create = Pair.create((Object)join, (Object)linkedMultiMap);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parse"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
        }
        return (Pair<String, LinkedMultiMap<String, T>>)create;
    }
    
    @NotNull
    private EqualityPolicy<String> a() {
        final TObjectHashingStrategy<String> hashingStrategy = this.getHashingStrategy();
        EqualityPolicy<String> equalityPolicy;
        try {
            equalityPolicy = new EqualityPolicy<String>() {
                public int getHashCode(final String s) {
                    return hashingStrategy.computeHashCode((Object)s);
                }
                
                public boolean isEqual(final String s, final String s2) {
                    return hashingStrategy.equals((Object)s, (Object)s2);
                }
            };
            if (equalityPolicy == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "getEqualityPolicy"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (EqualityPolicy<String>)equalityPolicy;
    }
    
    @NotNull
    protected TObjectHashingStrategy<String> getHashingStrategy() {
        TObjectHashingStrategy canonical;
        try {
            canonical = TObjectHashingStrategy.CANONICAL;
            if (canonical == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "getHashingStrategy"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (TObjectHashingStrategy<String>)canonical;
    }
    
    @NotNull
    protected String convertLineAndKeepEOLs(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lineWithEOLs", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "convertLineAndKeepEOLs"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "convertLineAndKeepEOLs"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return s;
    }
    
    protected void preprocess(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "line", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "preprocess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @NotNull
    protected abstract Pair<String, T> parseVariable(@NotNull final String p0, @NotNull final String p1);
    
    @NotNull
    protected Trinity<String, T, Integer> parseVariable(@NotNull final String s, @NotNull final String s2, final List<String> list) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "beforeEqual", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "afterEqual", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final com.intellij.openapi.util.Pair<String, T> variable = this.parseVariable(s, s2);
        Trinity create;
        try {
            create = Trinity.create(variable.first, variable.second, (Object)0);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeSettingsFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return (Trinity<String, T, Integer>)create;
    }
    
    protected void cleanup() {
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CMakeSettingsFileParser.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
