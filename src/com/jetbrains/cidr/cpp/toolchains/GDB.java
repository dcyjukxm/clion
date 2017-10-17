// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.util.Version;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerPathManager;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.ToolVersion;
import java.util.regex.Pattern;
import com.jetbrains.cidr.toolchains.CidrExecutableTool;

public class GDB extends CidrExecutableTool
{
    private static final Pattern[] VERSION_PATTERNS;
    public static final ToolVersion MIN_VERSION;
    public static final ToolVersion MAX_VERSION;
    @Nullable
    private final CidrToolSet myToolSet;
    @Nullable
    private final File myPythonHome;
    
    public GDB(@NotNull final File file, @Nullable final CidrToolSet set) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/cpp/toolchains/GDB", "<init>"));
        }
        this(file, null, set);
    }
    
    private GDB(@NotNull final File file, @Nullable final File myPythonHome, @Nullable final CidrToolSet myToolSet) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/cpp/toolchains/GDB", "<init>"));
        }
        super(file);
        this.myToolSet = myToolSet;
        this.myPythonHome = myPythonHome;
    }
    
    public static GDB getBundledGDB(@Nullable final CidrToolSet set) {
        final File bundledGDBBinary = CidrDebuggerPathManager.getBundledGDBBinary();
        final File parentFile = bundledGDBBinary.getParentFile().getParentFile();
        File file;
        try {
            file = bundledGDBBinary;
            if (a(parentFile).exists()) {
                final File file2 = parentFile;
                return new GDB(file, file2, set);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final File file2 = null;
        return new GDB(file, file2, set);
    }
    
    @Nullable
    public File getPythonHome() {
        return this.myPythonHome;
    }
    
    @Nullable
    @Override
    public String readVersion() {
        final ToolVersion a = a(this.myExecutable, this.myPythonHome, this.myToolSet);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return a.toCompactString();
    }
    
    @Nullable
    public static ToolVersion readVersion(@Nullable final File file, @Nullable final CidrToolSet set) {
        return a(file, null, set);
    }
    
    @Nullable
    private static ToolVersion a(@Nullable final File p0, @Nullable final File p1, @Nullable final CidrToolSet p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          32
        //     4: aload_0        
        //     5: invokevirtual   java/io/File.isFile:()Z
        //     8: ifeq            32
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    17: athrow         
        //    18: aload_0        
        //    19: invokevirtual   java/io/File.canExecute:()Z
        //    22: ifne            63
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    31: athrow         
        //    32: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    35: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //    38: ifeq            61
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    47: athrow         
        //    48: ldc             "GDB"
        //    50: aload_0        
        //    51: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;)V
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    60: athrow         
        //    61: aconst_null    
        //    62: areturn        
        //    63: aconst_null    
        //    64: astore_3       
        //    65: new             Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    68: dup            
        //    69: invokespecial   com/intellij/execution/configurations/GeneralCommandLine.<init>:()V
        //    72: astore          4
        //    74: aload_2        
        //    75: ifnull          97
        //    78: aload_2        
        //    79: aload           4
        //    81: getstatic       com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor.RUN:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;
        //    84: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    87: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.prepareEnvironment:(Lcom/intellij/execution/configurations/GeneralCommandLine;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;Ljava/util/List;)V
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    96: athrow         
        //    97: aload_0        
        //    98: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   101: aload_1        
        //   102: aload           4
        //   104: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.a:(Ljava/lang/String;Ljava/io/File;Lcom/intellij/execution/configurations/GeneralCommandLine;)V
        //   107: aload           4
        //   109: ldc             "--version"
        //   111: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameter:(Ljava/lang/String;)V
        //   114: aload           4
        //   116: invokestatic    com/intellij/execution/util/ExecUtil.execAndGetOutput:(Lcom/intellij/execution/configurations/GeneralCommandLine;)Lcom/intellij/execution/process/ProcessOutput;
        //   119: astore          5
        //   121: aload           5
        //   123: invokevirtual   com/intellij/execution/process/ProcessOutput.getExitCode:()I
        //   126: ifeq            165
        //   129: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   132: new             Ljava/lang/StringBuilder;
        //   135: dup            
        //   136: invokespecial   java/lang/StringBuilder.<init>:()V
        //   139: ldc             "Cannot read GDB version:\n"
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: aload           5
        //   146: invokevirtual   com/intellij/execution/process/ProcessOutput.getStderr:()Ljava/lang/String;
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   155: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;)V
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   164: athrow         
        //   165: aload           5
        //   167: invokevirtual   com/intellij/execution/process/ProcessOutput.getStdout:()Ljava/lang/String;
        //   170: invokestatic    com/intellij/openapi/util/text/StringUtil.splitByLines:(Ljava/lang/String;)[Ljava/lang/String;
        //   173: iconst_0       
        //   174: aaload         
        //   175: astore_3       
        //   176: goto            191
        //   179: astore          4
        //   181: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   184: ldc             "Cannot read GDB version: "
        //   186: aload           4
        //   188: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   191: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   194: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   197: ifeq            214
        //   200: ldc             "GDB"
        //   202: aload_0        
        //   203: aload_3        
        //   204: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;)V
        //   207: goto            214
        //   210: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   213: athrow         
        //   214: aload_3        
        //   215: ifnonnull       224
        //   218: aconst_null    
        //   219: areturn        
        //   220: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   223: athrow         
        //   224: aload_3        
        //   225: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.parseVersion:(Ljava/lang/String;)Lcom/jetbrains/cidr/ToolVersion;
        //   228: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  121    158    161    165    Ljava/lang/Exception;
        //  74     90     93     97     Ljava/lang/Exception;
        //  32     54     57     61     Ljava/lang/Exception;
        //  18     41     44     48     Ljava/lang/Exception;
        //  4      25     28     32     Ljava/lang/Exception;
        //  0      11     14     18     Ljava/lang/Exception;
        //  65     176    179    191    Ljava/lang/Exception;
        //  191    207    210    214    Ljava/lang/Exception;
        //  214    220    220    224    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    @Nullable
    public static String readBundledVersion(final CidrToolSet set) {
        return getBundledGDB(set).readVersion();
    }
    
    @NotNull
    public static ToolVersion parseVersion(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayVersion", "com/jetbrains/cidr/cpp/toolchains/GDB", "parseVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ToolVersion parse;
        try {
            parse = ToolVersion.parse(s, GDB.VERSION_PATTERNS);
            if (parse == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/GDB", "parseVersion"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return parse;
    }
    
    @Nullable
    public static String checkVersion(@NotNull final ToolVersion toolVersion) {
        try {
            if (toolVersion == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/GDB", "checkVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return CPPToolchains.checkVersion(GDB.MIN_VERSION, GDB.MAX_VERSION, toolVersion);
    }
    
    public void prepare(@NotNull final GeneralCommandLine generalCommandLine) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/GDB", "prepare"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        a(this.getExecutablePath(), this.myPythonHome, generalCommandLine);
    }
    
    private static void a(@NotNull final String exePath, @Nullable final File file, @NotNull final GeneralCommandLine generalCommandLine) {
        try {
            if (exePath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "execPath", "com/jetbrains/cidr/cpp/toolchains/GDB", "prepare"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/GDB", "prepare"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (file != null) {
                generalCommandLine.getEnvironment().put("PYTHONHOME", file.getPath());
                generalCommandLine.getEnvironment().put("PYTHONPATH", a(file).getPath());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        generalCommandLine.getEnvironment().put("PYTHONDONTWRITEBYTECODE", "1");
        generalCommandLine.setExePath(exePath);
    }
    
    @NotNull
    private static File a(@Nullable final File file) {
        File file2;
        try {
            file2 = new File(file, "lib/python2.7");
            if (file2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/GDB", "getLibPython27"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return file2;
    }
    
    static {
        VERSION_PATTERNS = new Pattern[] { Pattern.compile("^GNU gdb ([\\d]+\\.[\\d]+\\.?[\\d]*)", 8), Pattern.compile("^GNU gdb \\(GDB(?:;.*)?\\) ([\\d]+\\.[\\d]+(?:\\.[\\d]+)*).*", 8), CPPEnvironment.VERSION_PATTERN };
        MIN_VERSION = new ToolVersion(7, 8, -1);
        MAX_VERSION = new ToolVersion(7, 11, -1);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
