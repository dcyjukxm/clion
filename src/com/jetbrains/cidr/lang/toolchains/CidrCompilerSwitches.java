// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.toolchains;

import java.util.Set;
import gnu.trove.THashSet;
import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.function.BiFunction;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CidrCompilerSwitches
{
    @NotNull
    private final List<String> myRawArgs;
    
    public CidrCompilerSwitches(@NotNull final List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rawArgs", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "<init>"));
        }
        this.myRawArgs = Collections.unmodifiableList((List<? extends String>)list);
    }
    
    @NotNull
    public List<String> getList(@NotNull final Format format) {
        try {
            if (format == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "format", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "getList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        List map = null;
        Label_0101: {
            List<String> list = null;
            Label_0066: {
                try {
                    if (format != Format.RAW) {
                        break Label_0101;
                    }
                    final CidrCompilerSwitches cidrCompilerSwitches = this;
                    list = cidrCompilerSwitches.myRawArgs;
                    if (list == null) {
                        break Label_0066;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final CidrCompilerSwitches cidrCompilerSwitches = this;
                    list = cidrCompilerSwitches.myRawArgs;
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "getList"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            return list;
            try {
                map = ContainerUtil.map((Collection)this.myRawArgs, s -> {
                    try {
                        if (format == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "format", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "lambda$getList$0"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return CidrSwitchBuilder.convert(s, format);
                });
                if (map == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "getList"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return (List<String>)map;
    }
    
    @NotNull
    public String getCommandLineString(@NotNull final Format format) {
        try {
            if (format == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "format", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "getCommandLineString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (format == Format.RAW) {
                throw new IllegalArgumentException("RAW format cannot be correctly used in command line strings.");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        String join;
        try {
            join = StringUtil.join((Collection)this.getList(format), " ");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "getCommandLineString"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return join;
    }
    
    @NotNull
    public CidrCompilerSwitches filterOptions(@NotNull final BiFunction<String, String, Boolean> biFunction) {
        try {
            if (biFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentsFilter", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "filterOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ArrayList<String> list = new ArrayList<String>();
        for (int size = this.myRawArgs.size(), i = 0; i < size; ++i) {
            final String trim = this.myRawArgs.get(i).trim();
            String trim2 = null;
            Label_0125: {
                try {
                    if (i + 1 < size) {
                        trim2 = this.myRawArgs.get(i + 1).trim();
                        break Label_0125;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                trim2 = null;
            }
            final String s = trim2;
            try {
                if (biFunction.apply(trim, s)) {
                    list.add(trim);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        CidrCompilerSwitches cidrCompilerSwitches;
        try {
            cidrCompilerSwitches = new CidrCompilerSwitches(list);
            if (cidrCompilerSwitches == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "filterOptions"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return cidrCompilerSwitches;
    }
    
    @NotNull
    public CidrCompilerSwitches expandResponseFiles(@NotNull final File file, @NotNull final CidrToolEnvironment cidrToolEnvironment, @NotNull final Format format) throws IOException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseDirectory", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (format == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseFileFormat", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        Label_0153: {
            try {
                if (format == Format.GCC_RESPONSE_FILE) {
                    break Label_0153;
                }
                final Format format2 = format;
                final Format format3 = Format.MSVC_RESPONSE_FILE;
                if (format2 != format3) {
                    break Label_0153;
                }
                break Label_0153;
            }
            catch (IOException ex4) {
                throw b(ex4);
            }
            try {
                final Format format2 = format;
                final Format format3 = Format.MSVC_RESPONSE_FILE;
                if (format2 != format3) {
                    throw new IllegalArgumentException("Passed format cannot be used as response file format.");
                }
            }
            catch (IOException ex5) {
                throw b(ex5);
            }
        }
        final ArrayList<String> list = new ArrayList<String>(this.myRawArgs);
        final THashSet set = new THashSet(FileUtil.FILE_HASHING_STRATEGY);
        CidrCompilerSwitches cidrCompilerSwitches;
        try {
            a(file, list, (Set<File>)set, cidrToolEnvironment, format);
            cidrCompilerSwitches = new CidrCompilerSwitches(list);
            if (cidrCompilerSwitches == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex6) {
            throw b(ex6);
        }
        return cidrCompilerSwitches;
    }
    
    private static void a(@NotNull final File file, @NotNull final List<String> list, @NotNull final Set<File> set, @NotNull final CidrToolEnvironment cidrToolEnvironment, @NotNull final Format format) throws IOException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseDirectory", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expansionStack", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        try {
            if (format == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseFileFormat", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFiles"));
            }
        }
        catch (IOException ex5) {
            throw b(ex5);
        }
        try {
            if (set.size() > 20) {
                throw new IOException("Maximum recursion depth has been reached during response files expansion.");
            }
        }
        catch (IOException ex6) {
            throw b(ex6);
        }
        int i = 0;
        while (i < list.size()) {
            final String s = list.get(i);
            try {
                if (!s.startsWith("@")) {
                    ++i;
                    continue;
                }
            }
            catch (IOException ex7) {
                throw b(ex7);
            }
            final List<String> a = a(file, s.substring(1), set, cidrToolEnvironment, format);
            list.remove(i);
            list.addAll(i, a);
            i += a.size();
        }
    }
    
    private static List<String> a(@NotNull final File file, @NotNull final String s, @NotNull final Set<File> set, @NotNull final CidrToolEnvironment cidrToolEnvironment, @NotNull final Format format) throws IOException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseDirectory", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFile"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseFilePath", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFile"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expansionStack", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFile"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFile"));
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        try {
            if (format == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseFileFormat", "com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches", "expandResponseFile"));
            }
        }
        catch (IOException ex5) {
            throw b(ex5);
        }
        final File file2 = new File(cidrToolEnvironment.toLocalPath(file, s));
        try {
            if (set.contains(file2)) {
                throw new IOException("Cyclic dependency has been detected during response files expansion.");
            }
        }
        catch (IOException ex6) {
            throw b(ex6);
        }
        Label_0288: {
            try {
                if (!file2.exists()) {
                    break Label_0288;
                }
                final File file3 = file2;
                final boolean b = file3.isFile();
                if (!b) {
                    break Label_0288;
                }
                break Label_0288;
            }
            catch (IOException ex7) {
                throw b(ex7);
            }
            try {
                final File file3 = file2;
                final boolean b = file3.isFile();
                if (!b) {
                    return Collections.emptyList();
                }
            }
            catch (IOException ex8) {
                throw b(ex8);
            }
        }
        set.add(file2);
        final List<String> args = CidrSwitchBuilder.parseArgs(FileUtil.loadFile(file2), format);
        a(file, args, set, cidrToolEnvironment, format);
        set.remove(file2);
        return args;
    }
    
    @Override
    public String toString() {
        return this.getCommandLineString(Format.BASH_SHELL);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public enum Format
    {
        RAW, 
        BASH_SHELL, 
        WINDOWS_SHELL, 
        GNU_MAKEFILES, 
        MINGW_MAKEFILES, 
        NMAKE_MAKEFILES, 
        GCC_RESPONSE_FILE, 
        MSVC_RESPONSE_FILE;
    }
}
