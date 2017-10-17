// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.Version;
import com.jetbrains.cidr.cpp.CPPToolchains;
import java.util.regex.Pattern;
import com.jetbrains.cidr.cpp.CPPPathManager;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.ToolVersion;
import com.jetbrains.cidr.toolchains.CidrExecutableTool;

public class CMake extends CidrExecutableTool
{
    public static final String EXECUTABLE_NAME;
    public static final ToolVersion MIN_VERSION;
    public static final ToolVersion MAX_VERSION;
    @Nullable
    private final CidrToolSet myToolSet;
    
    public CMake(@NotNull final File file, @Nullable final CidrToolSet myToolSet) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/cpp/toolchains/CMake", "<init>"));
        }
        super(file);
        this.myToolSet = myToolSet;
    }
    
    @NotNull
    public static CMake getBundledCMake(@Nullable final CidrToolSet set) {
        boolean b = false;
        Label_0027: {
            Label_0026: {
                try {
                    if (set == null || !set.isCygwin()) {
                        break Label_0026;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                b = true;
                break Label_0027;
            }
            b = false;
        }
        final CMake cMake = new CMake(CPPPathManager.getBundledCMakeBinary(b), set);
        if (cMake == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/CMake", "getBundledCMake"));
        }
        return cMake;
    }
    
    @NotNull
    @Override
    public File getExecutable() {
        File myExecutable;
        try {
            myExecutable = this.myExecutable;
            if (myExecutable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/CMake", "getExecutable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myExecutable;
    }
    
    @Nullable
    @Override
    public String readVersion() {
        return readVersion(this.myExecutable, this.myToolSet);
    }
    
    @Nullable
    public static String readBundledVersion(@Nullable final CidrToolSet set) {
        return getBundledCMake(set).readVersion();
    }
    
    @Nullable
    public static String readVersion(@Nullable final File p0, @Nullable final CidrToolSet p1) {
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
        //    14: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    17: athrow         
        //    18: aload_0        
        //    19: invokevirtual   java/io/File.canExecute:()Z
        //    22: ifne            63
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    31: athrow         
        //    32: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    35: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //    38: ifeq            61
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    47: athrow         
        //    48: ldc             "CMake"
        //    50: aload_0        
        //    51: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;)V
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    60: athrow         
        //    61: aconst_null    
        //    62: areturn        
        //    63: new             Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    66: dup            
        //    67: invokespecial   com/intellij/execution/configurations/GeneralCommandLine.<init>:()V
        //    70: astore_2       
        //    71: aload_1        
        //    72: ifnull          96
        //    75: aload_1        
        //    76: aload_2        
        //    77: getstatic       com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor.BUILD:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;
        //    80: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    83: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.prepareEnvironment:(Lcom/intellij/execution/configurations/GeneralCommandLine;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;Ljava/util/List;)V
        //    86: goto            96
        //    89: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    92: athrow         
        //    93: astore_3       
        //    94: aconst_null    
        //    95: areturn        
        //    96: aload_2        
        //    97: aload_0        
        //    98: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   101: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.setExePath:(Ljava/lang/String;)V
        //   104: aload_2        
        //   105: ldc             "-version"
        //   107: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameter:(Ljava/lang/String;)V
        //   110: aload_2        
        //   111: invokestatic    com/intellij/execution/util/ExecUtil.execAndReadLine:(Lcom/intellij/execution/configurations/GeneralCommandLine;)Ljava/lang/String;
        //   114: astore_3       
        //   115: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   118: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   121: ifeq            138
        //   124: ldc             "CMake"
        //   126: aload_0        
        //   127: aload_3        
        //   128: invokestatic    com/jetbrains/cidr/cpp/CPPLog.logReadVersion:(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;)V
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   137: athrow         
        //   138: aload_3        
        //   139: ifnonnull       150
        //   142: aconst_null    
        //   143: goto            156
        //   146: invokestatic    com/jetbrains/cidr/cpp/toolchains/CMake.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   149: athrow         
        //   150: aload_3        
        //   151: ldc             "cmake version "
        //   153: invokestatic    com/intellij/openapi/util/text/StringUtil.trimStart:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   156: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  32     54     57     61     Lcom/intellij/execution/ExecutionException;
        //  18     41     44     48     Lcom/intellij/execution/ExecutionException;
        //  4      25     28     32     Lcom/intellij/execution/ExecutionException;
        //  0      11     14     18     Lcom/intellij/execution/ExecutionException;
        //  75     86     93     96     Lcom/intellij/execution/ExecutionException;
        //  71     89     89     93     Ljava/lang/IllegalArgumentException;
        //  115    131    134    138    Lcom/intellij/execution/ExecutionException;
        //  138    146    146    150    Lcom/intellij/execution/ExecutionException;
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
    public static String checkVersion(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/CMake", "checkVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return CPPToolchains.checkVersion(CMake.MIN_VERSION, CMake.MAX_VERSION, ToolVersion.parse(s, CPPEnvironment.VERSION_PATTERN));
    }
    
    static {
        String executable_NAME = null;
        Label_0017: {
            try {
                if (SystemInfo.isWindows) {
                    executable_NAME = "cmake.exe";
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            executable_NAME = "cmake";
        }
        EXECUTABLE_NAME = executable_NAME;
        MIN_VERSION = new ToolVersion(2, 8, 11);
        MAX_VERSION = new ToolVersion(3, 9, -1);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
