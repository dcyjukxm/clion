// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import java.util.Map;
import java.util.Iterator;
import com.intellij.util.PathUtil;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.openapi.util.Pair;
import java.util.Arrays;
import com.intellij.util.containers.SortedList;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.EnvironmentUtil;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import com.intellij.util.containers.hash.HashMap;
import java.util.regex.Matcher;
import java.util.List;
import org.jetbrains.annotations.Contract;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.ToolVersion;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.cpp.CPPLog;
import java.util.Scanner;
import java.util.Collections;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.configurations.GeneralCommandLine;
import java.util.NoSuchElementException;
import com.jetbrains.cidr.cpp.CPPBundle;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import com.jetbrains.cidr.ArchitectureType;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Pattern;
import com.jetbrains.cidr.toolchains.CidrToolSet;

public class MinGW extends CidrToolSet
{
    private static Pattern W64_MAJOR_MINOR_VERSION_PATTERN;
    private static Pattern MING32_MAJOR_MINOR_VERSION_PATTERN;
    private static Pattern OLD_MING32_VERSION_PATTERN;
    private static Pattern MINGW_W64_GCC_HOME_PATTERN;
    @Nullable
    private Boolean myIsW64;
    @Nullable
    private ArchitectureType myArchitectureType;
    private final File myGCCHome;
    
    public MinGW(@NotNull final File file) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/MinGW", "<init>"));
        }
        super(file);
        this.myGCCHome = a(file);
    }
    
    @NotNull
    @Override
    public String getName() {
        String s = null;
        Label_0032: {
            try {
                if (this.isMinGW64()) {
                    final String s2;
                    s = (s2 = CPPBundle.message("mingw64", new Object[0]));
                    break Label_0032;
                }
            }
            catch (NoSuchElementException ex) {
                throw c(ex);
            }
            String s2;
            s = (s2 = CPPBundle.message("mingw", new Object[0]));
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "getName"));
                }
            }
            catch (NoSuchElementException ex2) {
                throw c(ex2);
            }
        }
        return s;
    }
    
    @Override
    public boolean isMinGW() {
        return true;
    }
    
    @Override
    public boolean isMinGW64() {
        if (this.myIsW64 == null) {
            this.myIsW64 = "_mingw_mac.h".equals(b(this.myHome).getName());
        }
        return this.myIsW64;
    }
    
    @NotNull
    public ArchitectureType getArchitectureType() {
        ArchitectureType myArchitectureType = null;
        Label_0241: {
            Label_0057: {
                ArchitectureType architectureType = null;
                Label_0022: {
                    try {
                        if (this.myArchitectureType == null) {
                            break Label_0057;
                        }
                        final MinGW minGW = this;
                        architectureType = minGW.myArchitectureType;
                        if (architectureType == null) {
                            break Label_0022;
                        }
                        return architectureType;
                    }
                    catch (NoSuchElementException ex) {
                        throw c(ex);
                    }
                    try {
                        final MinGW minGW = this;
                        architectureType = minGW.myArchitectureType;
                        if (architectureType == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "getArchitectureType"));
                        }
                    }
                    catch (NoSuchElementException ex2) {
                        throw c(ex2);
                    }
                }
                return architectureType;
                try {
                    if (!this.isMinGW64()) {
                        this.myArchitectureType = ArchitectureType.I386;
                        break Label_0241;
                    }
                }
                catch (NoSuchElementException ex3) {
                    throw c(ex3);
                }
            }
            final GeneralCommandLine generalCommandLine = new GeneralCommandLine(new String[] { this.getSubFilePath("bin\\gcc.exe") });
            generalCommandLine.addParameter("-v");
            this.prepareEnvironment(generalCommandLine, CidrToolEnvironment.PrepareFor.BUILD, Collections.emptyList());
            try {
                final Scanner scanner = new Scanner(generalCommandLine.createProcess().getErrorStream());
                while (true) {
                    Label_0154: {
                        try {
                            if (!scanner.hasNext()) {
                                break;
                            }
                            final Scanner scanner2 = scanner;
                            final String s = "Target:";
                            final String s2 = scanner2.findInLine(s);
                            if (s2 != null) {
                                break Label_0154;
                            }
                            break Label_0154;
                        }
                        catch (NoSuchElementException ex4) {
                            throw c(ex4);
                        }
                        try {
                            final Scanner scanner2 = scanner;
                            final String s = "Target:";
                            final String s2 = scanner2.findInLine(s);
                            if (s2 != null) {
                                this.myArchitectureType = a(scanner.next());
                                break;
                            }
                        }
                        catch (NoSuchElementException ex5) {
                            throw c(ex5);
                        }
                    }
                    scanner.nextLine();
                }
                try {
                    if (this.myArchitectureType == null) {
                        this.myArchitectureType = ArchitectureType.UNKNOWN;
                    }
                }
                catch (NoSuchElementException ex6) {
                    throw c(ex6);
                }
            }
            catch (NoSuchElementException ex9) {
                CPPLog.LOG.error("Cannot parse gcc -v output");
                this.myArchitectureType = ArchitectureType.UNKNOWN;
            }
            catch (ExecutionException ex7) {
                CPPLog.LOG.error(ex7.getMessage());
                this.myArchitectureType = ArchitectureType.UNKNOWN;
            }
            try {
                myArchitectureType = this.myArchitectureType;
                if (myArchitectureType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "getArchitectureType"));
                }
            }
            catch (NoSuchElementException ex8) {
                throw c(ex8);
            }
        }
        return myArchitectureType;
    }
    
    private static ArchitectureType a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/cpp/toolchains/MinGW", "parseTarget"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        try {
            if ("x86_64-w64-mingw32".equals(s)) {
                return ArchitectureType.X86_64;
            }
        }
        catch (NoSuchElementException ex2) {
            throw c(ex2);
        }
        try {
            if ("i686-w64-mingw32".equals(s)) {
                return ArchitectureType.I386;
            }
        }
        catch (NoSuchElementException ex3) {
            throw c(ex3);
        }
        return ArchitectureType.UNKNOWN;
    }
    
    @Override
    public boolean forceToolSetGDB() {
        return this.isMinGW64();
    }
    
    @NotNull
    public String getGCCHomePath() {
        String path;
        try {
            path = this.myGCCHome.getPath();
            if (path == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "getGCCHomePath"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        return path;
    }
    
    @Nullable
    @Override
    public String readVersion() {
        return readVersion(this.myHome);
    }
    
    @Nullable
    public static String readVersion(@Nullable final File file) {
        Label_0035: {
            Label_0020: {
                try {
                    if (file != null) {
                        break Label_0035;
                    }
                    final Logger logger = CPPLog.LOG;
                    final boolean b = logger.isDebugEnabled();
                    if (b) {
                        break Label_0020;
                    }
                    return null;
                }
                catch (IOException ex) {
                    throw c(ex);
                }
                try {
                    final Logger logger = CPPLog.LOG;
                    final boolean b = logger.isDebugEnabled();
                    if (b) {
                        CPPLog.logReadVersion("MinGW", null);
                    }
                }
                catch (IOException ex2) {
                    throw c(ex2);
                }
            }
            return null;
            try {
                return parseVersion(FileUtil.loadFile(b(file)));
            }
            catch (IOException ex3) {
                try {
                    if (CPPLog.LOG.isDebugEnabled()) {
                        CPPLog.LOG.debug("MinGW.readVersion() file: '" + file + "' error", (Throwable)ex3);
                    }
                }
                catch (IOException ex4) {
                    throw c(ex4);
                }
                return null;
            }
        }
    }
    
    @Override
    public String checkVersion(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/MinGW", "checkVersion"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        return checkVersion(s, this.isMinGW64());
    }
    
    @Nullable
    public static String checkVersion(@NotNull final String s, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/MinGW", "checkVersion"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        final ToolVersion parse = ToolVersion.parse(s, CPPEnvironment.VERSION_PATTERN);
        try {
            if (parse.isUnknown()) {
                return CPPBundle.message("settings.select.UnsupportedVersion.unknown", parse);
            }
        }
        catch (NoSuchElementException ex2) {
            throw c(ex2);
        }
        return null;
    }
    
    @NotNull
    private static File b(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/MinGW", "findMingwHeaderFile"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        final File file2 = new File(a(file), "include");
        final File file3 = new File(file2, "_mingw_mac.h");
        File file5 = null;
        Label_0123: {
            File file4 = null;
            Label_0088: {
                try {
                    if (!file3.exists()) {
                        break Label_0123;
                    }
                    file4 = file3;
                    if (file4 == null) {
                        break Label_0088;
                    }
                    return file4;
                }
                catch (NoSuchElementException ex2) {
                    throw c(ex2);
                }
                try {
                    file4 = file3;
                    if (file4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "findMingwHeaderFile"));
                    }
                }
                catch (NoSuchElementException ex3) {
                    throw c(ex3);
                }
            }
            return file4;
            try {
                file5 = new File(file2, "_mingw.h");
                if (file5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "findMingwHeaderFile"));
                }
            }
            catch (NoSuchElementException ex4) {
                throw c(ex4);
            }
        }
        return file5;
    }
    
    @NotNull
    private static File a(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/MinGW", "findGCCHome"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        File file2 = file;
        final CommonProcessors.FindFirstProcessor<File> findFirstProcessor = new CommonProcessors.FindFirstProcessor<File>() {
            protected boolean accept(final File file) {
                return MinGW.MINGW_W64_GCC_HOME_PATTERN.matcher(file.getName()).matches();
            }
        };
        final File[] listFiles = file.listFiles();
        File file3 = null;
        Label_0091: {
            try {
                if (listFiles == null) {
                    break Label_0091;
                }
                ContainerUtil.process((Object[])listFiles, (Processor)findFirstProcessor);
                if (findFirstProcessor.getFoundValue() == null) {
                    break Label_0091;
                }
            }
            catch (NoSuchElementException ex2) {
                throw c(ex2);
            }
            file2 = (File)findFirstProcessor.getFoundValue();
            try {
                file3 = file2;
                if (file3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "findGCCHome"));
                }
            }
            catch (NoSuchElementException ex3) {
                throw c(ex3);
            }
        }
        return file3;
    }
    
    @Contract("null -> null")
    public File getGCCSubFile(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        return new File(this.myGCCHome, s);
    }
    
    @NotNull
    @Override
    public File getGDBPath() {
        File subFile;
        try {
            subFile = this.getSubFile("bin/gdb.exe");
            if (subFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "getGDBPath"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        return subFile;
    }
    
    @Override
    public void prepareEnvironment(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final CidrToolEnvironment.PrepareFor prepareFor, @NotNull final List<Option> list) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/MinGW", "prepareEnvironment"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/cpp/toolchains/MinGW", "prepareEnvironment"));
            }
        }
        catch (NoSuchElementException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/cpp/toolchains/MinGW", "prepareEnvironment"));
            }
        }
        catch (NoSuchElementException ex3) {
            throw c(ex3);
        }
        CidrToolSet.prependPathVariable(generalCommandLine, this.getSubFilePath("bin"));
    }
    
    static String parseVersion(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/toolchains/MinGW", "parseVersion"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        final String a = a(MinGW.W64_MAJOR_MINOR_VERSION_PATTERN, s);
        try {
            if (a != null) {
                return "w64 " + a;
            }
        }
        catch (NoSuchElementException ex2) {
            throw c(ex2);
        }
        final String a2 = a(MinGW.MING32_MAJOR_MINOR_VERSION_PATTERN, s);
        try {
            if (a2 != null) {
                return a2;
            }
        }
        catch (NoSuchElementException ex3) {
            throw c(ex3);
        }
        final Matcher matcher = MinGW.OLD_MING32_VERSION_PATTERN.matcher(s);
        if (matcher.find()) {
            String s2 = matcher.group(2);
            try {
                if (matcher.group(1) == null || !matcher.find()) {
                    return s2;
                }
            }
            catch (NoSuchElementException ex4) {
                throw c(ex4);
            }
            s2 = matcher.group(2);
            return s2;
        }
        return null;
    }
    
    @Nullable
    private static String a(final Pattern pattern, final String s) {
        final Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            final HashMap hashMap = new HashMap();
            try {
                ((Map<String, String>)hashMap).put(matcher.group(1), matcher.group(4));
                if (matcher.find()) {
                    ((Map<String, String>)hashMap).put(matcher.group(1), matcher.group(4));
                    return ((Map<String, String>)hashMap).get((Object)"MAJOR") + "." + ((Map<String, String>)hashMap).get((Object)"MINOR");
                }
            }
            catch (NoSuchElementException ex) {
                throw c(ex);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public char[] getSupportedFileSeparators() {
        char[] windows_UNIX_PATH_SEPARATORS;
        try {
            windows_UNIX_PATH_SEPARATORS = DefaultCidrToolEnvironment.WINDOWS_UNIX_PATH_SEPARATORS;
            if (windows_UNIX_PATH_SEPARATORS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MinGW", "getSupportedFileSeparators"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        return windows_UNIX_PATH_SEPARATORS;
    }
    
    public static boolean processMinGWInstallations(final Processor<File> processor) {
        return processMinGWInstallations(new File("C:\\"), EnvironmentUtil.getValue("PATH"), processor);
    }
    
    static boolean processMinGWInstallations(@NotNull final File file, @Nullable final String s, final Processor<File> processor) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/cpp/toolchains/MinGW", "processMinGWInstallations"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        final int n;
        final SortedList list = new SortedList((pair, pair2) -> {
            StringUtil.compareVersionNumbers((String)pair2.first, (String)pair.first);
            try {
                if (n != 0) {
                    return n;
                }
            }
            catch (NoSuchElementException ex2) {
                throw c(ex2);
            }
            return FileUtil.compareFiles((File)pair.second, (File)pair2.second);
        });
        final Iterator<File> iterator = Arrays.asList(file, new File(file, "Program Files"), new File(file, "Program Files (x86)")).iterator();
        while (iterator.hasNext()) {
            final File[] listFiles = iterator.next().listFiles();
            if (listFiles != null) {
                for (final File file2 : listFiles) {
                    Label_0203: {
                        try {
                            if (!file2.isDirectory()) {
                                break Label_0203;
                            }
                        }
                        catch (NoSuchElementException ex3) {
                            throw c(ex3);
                        }
                        try {
                            if (StringUtil.containsIgnoreCase(file2.getName(), "mingw")) {
                                a(file2, 4, (List<Pair<String, File>>)list);
                            }
                        }
                        catch (NoSuchElementException ex4) {
                            throw c(ex4);
                        }
                    }
                }
            }
        }
        if (s != null) {
            for (final String s2 : PathEnvironmentVariableUtil.getPathDirs(s)) {
                try {
                    if (!PathUtil.getFileName(s2).equalsIgnoreCase("bin") || !StringUtil.containsIgnoreCase(s2, "mingw")) {
                        continue;
                    }
                }
                catch (NoSuchElementException ex5) {
                    throw c(ex5);
                }
                final File parentFile = new File(s2).getParentFile();
                final String version = readVersion(parentFile);
                try {
                    if (version == null) {
                        continue;
                    }
                    ((List<Pair<String, File>>)list).add((Pair<String, File>)Pair.create((Object)version, (Object)parentFile));
                }
                catch (NoSuchElementException ex6) {
                    throw c(ex6);
                }
            }
        }
        for (final Pair<String, File> pair3 : list) {
            try {
                if (!processor.process(pair3.second)) {
                    return false;
                }
                continue;
            }
            catch (NoSuchElementException ex7) {
                throw c(ex7);
            }
        }
        return true;
    }
    
    private static void a(@NotNull final File file, final int n, final List<Pair<String, File>> list) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dir", "com/jetbrains/cidr/cpp/toolchains/MinGW", "findRecursively"));
            }
        }
        catch (NoSuchElementException ex) {
            throw c(ex);
        }
        try {
            if (n == 0) {
                return;
            }
        }
        catch (NoSuchElementException ex2) {
            throw c(ex2);
        }
        final String version = readVersion(file);
        try {
            if (version != null) {
                list.add((Pair<String, File>)Pair.create((Object)version, (Object)file));
                return;
            }
        }
        catch (NoSuchElementException ex3) {
            throw c(ex3);
        }
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (final File file2 : listFiles) {
                Label_0140: {
                    try {
                        if (!file2.isDirectory()) {
                            break Label_0140;
                        }
                    }
                    catch (NoSuchElementException ex4) {
                        throw c(ex4);
                    }
                    a(file2, n - 1, list);
                }
            }
        }
    }
    
    static {
        MinGW.W64_MAJOR_MINOR_VERSION_PATTERN = Pattern.compile("^[\\s]*#[\\s]*define[\\s]+__MINGW64_VERSION_((MAJOR)|(MINOR))[\\s]+([\\S]+)$", 8);
        MinGW.MING32_MAJOR_MINOR_VERSION_PATTERN = Pattern.compile("^[\\s]*#[\\s]*define[\\s]+__MINGW32_((MAJOR)|(MINOR))_VERSION[\\s]+([\\S]+)$", 8);
        MinGW.OLD_MING32_VERSION_PATTERN = Pattern.compile("^[\\s]*#[\\s]*define[\\s]+__MINGW(32)?_VERSION[\\s]+([\\S]+)$", 8);
        MinGW.MINGW_W64_GCC_HOME_PATTERN = Pattern.compile("((i686)|(x86_64))-w64-mingw32");
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
