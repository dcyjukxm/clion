// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import java.util.Set;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Processor;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Version;
import com.jetbrains.cidr.cpp.CPPToolchains;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.util.ExecUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import com.jetbrains.cidr.ToolVersion;
import com.intellij.util.containers.SLRUMap;
import com.jetbrains.cidr.toolchains.CidrToolSet;

public class Cygwin extends CidrToolSet
{
    private static final SLRUMap<CacheKey, String> ourLocalToCygwinCache;
    private static final SLRUMap<CacheKey, String> ourCygwinToLocalCache;
    public static final ToolVersion MIN_VERSION;
    public static final ToolVersion MAX_VERSION;
    private final String myNormalizedHomePath;
    private final boolean mySuppressWin32WarningsInTests;
    
    public Cygwin(@NotNull final File file) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "<init>"));
        }
        this(file, false);
    }
    
    public Cygwin(@NotNull final File file, final boolean mySuppressWin32WarningsInTests) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "<init>"));
        }
        super(file);
        this.myNormalizedHomePath = FileUtil.normalize(file.getPath());
        this.mySuppressWin32WarningsInTests = mySuppressWin32WarningsInTests;
    }
    
    @NotNull
    @Override
    public String getName() {
        String message;
        try {
            message = CPPBundle.message("cygwin", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return message;
    }
    
    @Override
    public boolean isCygwin() {
        return true;
    }
    
    public boolean isCygwin64() {
        return a(this.myHome);
    }
    
    private static boolean a(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "isCygwin64"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final File file2 = new File(file, "/bin/uname.exe");
        try {
            if (!file2.isFile() || !file2.canExecute()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String execAndReadLine = ExecUtil.execAndReadLine(new GeneralCommandLine(new String[] { file2.getAbsolutePath(), "-m" }));
        try {
            if (execAndReadLine != null) {
                return execAndReadLine.trim().equalsIgnoreCase("x86_64");
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @Override
    public boolean forceToolSetGDB() {
        return true;
    }
    
    @NotNull
    @Override
    public File getGDBPath() {
        File subFile;
        try {
            subFile = this.getSubFile("bin/gdb.exe");
            if (subFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "getGDBPath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return subFile;
    }
    
    @Contract("null -> null")
    public static File getCMakePath(@Nullable final File file) {
        try {
            if (file == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new File(file, "bin/cmake.exe");
    }
    
    @Contract("null -> null")
    public static File getGDBPath(@Nullable final File file) {
        try {
            if (file == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new File(file, "bin/gdb.exe");
    }
    
    @Nullable
    @Override
    public String readVersion() {
        return readVersion(this.myHome);
    }
    
    @Override
    public String checkVersion(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "checkVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return checkVersion(s, this.isCygwin64());
    }
    
    @Nullable
    public static String checkVersion(@NotNull final String s, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "checkVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                return CPPToolchains.checkVersion(Cygwin.MIN_VERSION, Cygwin.MAX_VERSION, ToolVersion.parse(s, CPPEnvironment.VERSION_PATTERN));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return "32-bit Cygwin is not supported, please install 64-bit version";
    }
    
    @Nullable
    public static String readVersion(@Nullable final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       35
        //     4: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //     7: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //    10: ifeq            33
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: ldc             "Cygwin"
        //    22: aconst_null    
        //    23: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;)V
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aconst_null    
        //    34: areturn        
        //    35: new             Ljava/io/File;
        //    38: dup            
        //    39: aload_0        
        //    40: ldc             "/bin/cygcheck.exe"
        //    42: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    45: astore_1       
        //    46: aload_1        
        //    47: invokevirtual   java/io/File.isFile:()Z
        //    50: ifeq            67
        //    53: aload_1        
        //    54: invokevirtual   java/io/File.canExecute:()Z
        //    57: ifne            98
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    70: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //    73: ifeq            96
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: ldc             "Cygwin"
        //    85: aload_1        
        //    86: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;)V
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aconst_null    
        //    97: areturn        
        //    98: new             Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   101: dup            
        //   102: iconst_2       
        //   103: anewarray       Ljava/lang/String;
        //   106: dup            
        //   107: iconst_0       
        //   108: aload_1        
        //   109: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   112: aastore        
        //   113: dup            
        //   114: iconst_1       
        //   115: ldc             "-V"
        //   117: aastore        
        //   118: invokespecial   com/intellij/execution/configurations/GeneralCommandLine.<init>:([Ljava/lang/String;)V
        //   121: invokestatic    com/intellij/execution/util/ExecUtil.execAndReadLine:(Lcom/intellij/execution/configurations/GeneralCommandLine;)Ljava/lang/String;
        //   124: astore_2       
        //   125: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   128: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   131: ifeq            148
        //   134: ldc             "Cygwin"
        //   136: aload_1        
        //   137: aload_2        
        //   138: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;)V
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload_2        
        //   149: ifnonnull       160
        //   152: aconst_null    
        //   153: goto            166
        //   156: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload_2        
        //   161: ldc             "cygcheck (cygwin) "
        //   163: invokestatic    com/intellij/openapi/util/text/StringUtil.trimStart:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   166: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  4      26     29     33     Ljava/lang/IllegalArgumentException;
        //  46     60     63     67     Ljava/lang/IllegalArgumentException;
        //  53     76     79     83     Ljava/lang/IllegalArgumentException;
        //  67     89     92     96     Ljava/lang/IllegalArgumentException;
        //  125    141    144    148    Ljava/lang/IllegalArgumentException;
        //  148    156    156    160    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Contract("null, _ -> null")
    public static String toLocalPath(@Nullable final String s, @Nullable final Cygwin cygwin) {
        return toLocalPath(null, s, cygwin);
    }
    
    @Contract("_, null, _ -> null")
    public static String toLocalPath(@Nullable final File file, @Nullable String canonicalPath, @Nullable final Cygwin cygwin) {
        try {
            if (canonicalPath == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (FileUtil.isWindowsAbsolutePath(canonicalPath)) {
                return canonicalPath;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (FileUtil.isUnixAbsolutePath(canonicalPath) || file == null) {
                return a(true, canonicalPath, cygwin, Cygwin.ourCygwinToLocalCache);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        canonicalPath = FileUtil.toCanonicalPath(StringUtil.trimEnd(toCygwinPath(file.getPath(), cygwin), "/") + "/" + canonicalPath);
        return a(true, canonicalPath, cygwin, Cygwin.ourCygwinToLocalCache);
    }
    
    @Contract("null, _ -> null")
    public static String toCygwinPath(@Nullable final String s, @Nullable final Cygwin cygwin) {
        return a(false, s, cygwin, Cygwin.ourLocalToCygwinCache);
    }
    
    @NotNull
    @Override
    public char[] getSupportedFileSeparators() {
        char[] windows_UNIX_PATH_SEPARATORS;
        try {
            windows_UNIX_PATH_SEPARATORS = DefaultCidrToolEnvironment.WINDOWS_UNIX_PATH_SEPARATORS;
            if (windows_UNIX_PATH_SEPARATORS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "getSupportedFileSeparators"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return windows_UNIX_PATH_SEPARATORS;
    }
    
    public static void convertPathVariableToCygwin(@NotNull final GeneralCommandLine generalCommandLine, @Nullable final Cygwin cygwin) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "convertPathVariableToCygwin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (cygwin != null) {
            final String s = generalCommandLine.getEnvironment().get("PATH");
            if (s != null) {
                generalCommandLine.getEnvironment().put("PATH", StringUtil.join((Collection)ContainerUtil.map((Object[])s.split(";"), s -> toCygwinPath(s, cygwin)), ":"));
            }
        }
    }
    
    @Override
    public void prepareEnvironment(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final CidrToolEnvironment.PrepareFor prepareFor, @NotNull final List<Option> list) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "prepareEnvironment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "prepareEnvironment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "prepareEnvironment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        CidrToolSet.prependPathVariable(generalCommandLine, this.getSubFilePath("bin"));
        final Map environment = generalCommandLine.getEnvironment();
        if (generalCommandLine.isPassParentEnvironment()) {
            final Map parentEnvironment = generalCommandLine.getParentEnvironment();
            try {
                if (parentEnvironment.containsKey("TEMP")) {
                    environment.put("TEMP", "/tmp");
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (parentEnvironment.containsKey("TMP")) {
                    environment.put("TMP", "/tmp");
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (parentEnvironment.containsKey("USERNAME")) {
                    environment.put("USER", parentEnvironment.get("USERNAME"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                if (parentEnvironment.containsKey("COMPUTERNAME")) {
                    environment.put("HOSTNAME", parentEnvironment.get("COMPUTERNAME"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        try {
            if (this.mySuppressWin32WarningsInTests) {
                environment.put("CMAKE_LEGACY_CYGWIN_WIN32", "0");
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
    }
    
    public static boolean processCygwinInstallations(final Processor<Pair<File, Boolean>> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lgnu/trove/THashSet;
        //     3: dup            
        //     4: getstatic       com/intellij/openapi/util/io/FileUtil.FILE_HASHING_STRATEGY:Lgnu/trove/TObjectHashingStrategy;
        //     7: invokespecial   gnu/trove/THashSet.<init>:(Lgnu/trove/TObjectHashingStrategy;)V
        //    10: astore_1       
        //    11: aload_1        
        //    12: aload_0        
        //    13: invokedynamic   process:(Ljava/util/Set;Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //    18: astore_2       
        //    19: ldc             "HKEY_CURRENT_USER\\SOFTWARE\\Cygwin\\Installations"
        //    21: astore_3       
        //    22: aload_3        
        //    23: invokestatic    com/intellij/openapi/util/io/WindowsRegistryUtil.readRegistryBranchValues:(Ljava/lang/String;)Ljava/util/List;
        //    26: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    31: astore          4
        //    33: aload           4
        //    35: invokeinterface java/util/Iterator.hasNext:()Z
        //    40: ifeq            115
        //    43: aload           4
        //    45: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    50: checkcast       Ljava/lang/String;
        //    53: astore          5
        //    55: aload_3        
        //    56: aload           5
        //    58: invokestatic    com/intellij/openapi/util/io/WindowsRegistryUtil.readRegistryValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    61: astore          6
        //    63: aload           6
        //    65: ifnonnull       75
        //    68: goto            33
        //    71: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload           6
        //    77: ldc             "\\??\\"
        //    79: invokestatic    com/intellij/openapi/util/text/StringUtil.trimStart:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    82: astore          6
        //    84: new             Ljava/io/File;
        //    87: dup            
        //    88: aload           6
        //    90: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    93: astore          7
        //    95: aload_2        
        //    96: aload           7
        //    98: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   103: ifne            112
        //   106: iconst_0       
        //   107: ireturn        
        //   108: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: goto            33
        //   115: new             Ljava/io/File;
        //   118: dup            
        //   119: ldc             "C:\\"
        //   121: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   124: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //   127: astore          4
        //   129: aload           4
        //   131: ifnull          223
        //   134: aload           4
        //   136: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;)V
        //   139: aload           4
        //   141: astore          5
        //   143: aload           5
        //   145: arraylength    
        //   146: istore          6
        //   148: iconst_0       
        //   149: istore          7
        //   151: iload           7
        //   153: iload           6
        //   155: if_icmpge       223
        //   158: aload           5
        //   160: iload           7
        //   162: aaload         
        //   163: astore          8
        //   165: aload           8
        //   167: invokevirtual   java/io/File.isDirectory:()Z
        //   170: ifeq            217
        //   173: aload           8
        //   175: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   178: ldc             "cygwin"
        //   180: invokestatic    com/intellij/openapi/util/text/StringUtil.containsIgnoreCase:(Ljava/lang/String;Ljava/lang/String;)Z
        //   183: ifeq            217
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: aload_2        
        //   194: aload           8
        //   196: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   201: ifne            217
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: iconst_0       
        //   212: ireturn        
        //   213: invokestatic    com/jetbrains/cidr/cpp/toolchains/Cygwin.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iinc            7, 1
        //   220: goto            151
        //   223: iconst_1       
        //   224: ireturn        
        //    Signature:
        //  (Lcom/intellij/util/Processor<Lcom/intellij/openapi/util/Pair<Ljava/io/File;Ljava/lang/Boolean;>;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  63     71     71     75     Ljava/lang/IllegalArgumentException;
        //  95     108    108    112    Ljava/lang/IllegalArgumentException;
        //  165    186    189    193    Ljava/lang/IllegalArgumentException;
        //  173    204    207    211    Ljava/lang/IllegalArgumentException;
        //  193    213    213    217    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0193:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Contract("_, null, _, _ -> null")
    private static String a(final boolean b, @Nullable final String s, @Nullable final Cygwin cygwin, final SLRUMap<CacheKey, String> slruMap) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cygwin == null) {
                return s;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!SystemInfo.isWindows) {
                return s;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final CacheKey cacheKey = new CacheKey(s, cygwin.myNormalizedHomePath);
        synchronized (slruMap) {
            final String s2 = (String)slruMap.get((Object)cacheKey);
            if (s2 != null) {
                return s2;
            }
        }
        final String a = a(b, s, cygwin);
        try {
            if (a == null) {
                return s;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        synchronized (slruMap) {
            slruMap.put((Object)cacheKey, (Object)a);
        }
        return a;
    }
    
    @Nullable
    private static String a(final boolean b, @NotNull final String s, @NotNull final Cygwin cygwin) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "runCygpath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cygwin == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cygwin", "com/jetbrains/cidr/cpp/toolchains/Cygwin", "runCygpath"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final File file = new File(cygwin.myHome, "\\bin\\cygpath.exe");
        try {
            if (!file.canExecute()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        String[] array = null;
        int n = 0;
        String s2 = null;
        Label_0147: {
            try {
                array = new String[] { file.getPath(), null, null };
                n = 1;
                if (b) {
                    s2 = "-w";
                    break Label_0147;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            s2 = "-u";
        }
        array[n] = s2;
        array[2] = s;
        String s3 = ExecUtil.execAndReadLine(new GeneralCommandLine(array).withCharset(CharsetToolkit.UTF8_CHARSET));
        try {
            if (s3 == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!b || !s3.startsWith("\\\\?\\")) {
                return s3;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        s3 = s3.replace("\\\\?\\", "");
        return s3;
    }
    
    static {
        ourLocalToCygwinCache = new SLRUMap(10000, 10000);
        ourCygwinToLocalCache = new SLRUMap(10000, 10000);
        MIN_VERSION = new ToolVersion(1, 7, 32);
        MAX_VERSION = new ToolVersion(2, -1, -1);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class CacheKey
    {
        @NotNull
        private final String path;
        @NotNull
        private final String cygwinHome;
        private final int hashCode;
        
        public CacheKey(@NotNull final String path, @NotNull final String cygwinHome) {
            if (path == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/cpp/toolchains/Cygwin$CacheKey", "<init>"));
            }
            if (cygwinHome == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cygwinHome", "com/jetbrains/cidr/cpp/toolchains/Cygwin$CacheKey", "<init>"));
            }
            this.path = path;
            this.cygwinHome = cygwinHome;
            this.hashCode = this.calculateHashCode();
        }
        
        public int calculateHashCode() {
            return 31 * this.path.hashCode() + this.cygwinHome.hashCode();
        }
        
        @Override
        public String toString() {
            return this.path + '@' + this.cygwinHome;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final CacheKey cacheKey = this;
                    final Class<? extends CacheKey> clazz = cacheKey.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final CacheKey cacheKey = this;
                    final Class<? extends CacheKey> clazz = cacheKey.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final CacheKey cacheKey2 = (CacheKey)o;
            try {
                if (this.hashCode != cacheKey2.hashCode) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (!this.path.equals(cacheKey2.path)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (!this.cygwinHome.equals(cacheKey2.cygwinHome)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return this.hashCode;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
