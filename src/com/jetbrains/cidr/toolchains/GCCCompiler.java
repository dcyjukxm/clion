// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.util.function.BiFunction;
import java.util.Set;
import com.intellij.util.containers.ContainerUtil;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.regex.Matcher;
import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.CidrLog;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchPath;
import com.intellij.openapi.util.SystemInfo;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.lang.toolchains.CidrSwitchBuilder;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Function;
import java.util.Collections;
import java.io.File;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessOutput;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;
import java.util.regex.Pattern;

public class GCCCompiler extends CidrCompilerBase
{
    private static final String QUERY_PREFIX = "____CIDR_test_query_";
    private static final Pattern QUERY_RESULT_PATTERN;
    public static final String QUOTES = "[\u2018\u2019'\"]";
    private static final Pattern[] BAD_SWITCH_FILTER_RULES;
    private static final Pattern INCLUDE_FILE_PATTERN;
    private static final Pattern DEPENDENCY_PATTERN;
    static final String CIDR_DEFINITIONS_END = "___CIDR_DEFINITIONS_END";
    static final String CIDR_FEATURES_START = "___CIDR_FEATURES_START";
    private static final String UNSUPPORTED_COMPILER = "This compiler might be unsupported.\nIf you are using GCC/Clang, please report the bug in https://youtrack.jetbrains.com/issues/CPP.";
    private static final String CHECK_TYPE_SIZE = "typeSize";
    private static final String CHECK_FEATURE = "feature";
    private static final String CHECK_CLANG_FEATURE = "clangFeature";
    private static final String CHECK_CLANG_EXTENSION = "clangExtension";
    private static final String CHECK_CLANG_ATTRIBUTE = "clangAttribute";
    
    public static void setGCCErrorInTests(final boolean b) {
        CompilerRunner compilerRunnerInTests = null;
        Label_0019: {
            try {
                if (!b) {
                    compilerRunnerInTests = null;
                    break Label_0019;
                }
            }
            catch (NumberFormatException ex) {
                throw c(ex);
            }
            compilerRunnerInTests = new CompilerRunner() {
                @NotNull
                @Override
                public ProcessOutput run(@NotNull final GeneralCommandLine generalCommandLine) throws ExecutionException {
                    try {
                        if (generalCommandLine == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/toolchains/GCCCompiler$1", "run"));
                        }
                    }
                    catch (ExecutionException ex) {
                        throw b(ex);
                    }
                    final ProcessOutput processOutput = new ProcessOutput(1);
                    ProcessOutput processOutput2;
                    try {
                        processOutput.appendStderr("Emulated GCC error");
                        processOutput2 = processOutput;
                        if (processOutput2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler$1", "run"));
                        }
                    }
                    catch (ExecutionException ex2) {
                        throw b(ex2);
                    }
                    return processOutput2;
                }
                
                private static ExecutionException b(final ExecutionException ex) {
                    return ex;
                }
            };
        }
        CidrCompilerBase.setCompilerRunnerInTests(compilerRunnerInTests);
    }
    
    public GCCCompiler(@NotNull final File file, @NotNull final File file2) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/toolchains/GCCCompiler", "<init>"));
        }
        if (file2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workingDirectory", "com/jetbrains/cidr/toolchains/GCCCompiler", "<init>"));
        }
        super(file, file2);
    }
    
    @Nullable
    @Override
    public String readVersion() {
        return this.doReadVersion(Collections.singletonList("--version"), (Function<ProcessOutput, String>)(processOutput -> {
            for (final String s : processOutput.getStdoutLines()) {
                try {
                    if (StringUtil.isEmptyOrSpaces(s)) {
                        continue;
                    }
                }
                catch (NumberFormatException ex) {
                    throw c(ex);
                }
                return s;
            }
            return null;
        }));
    }
    
    @NotNull
    public static String getLanguageOption(@NotNull final OCLanguageKind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "kind"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLanguageOption"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/CLanguageKind;
        //    48: ifeq            267
        //    51: getstatic       com/jetbrains/cidr/toolchains/GCCCompiler$3.$SwitchMap$com$jetbrains$cidr$lang$CLanguageKind:[I
        //    54: aload_0        
        //    55: checkcast       Lcom/jetbrains/cidr/lang/CLanguageKind;
        //    58: invokevirtual   com/jetbrains/cidr/lang/CLanguageKind.ordinal:()I
        //    61: iaload         
        //    62: tableswitch {
        //                2: 96
        //                3: 144
        //                4: 185
        //                5: 226
        //          default: 267
        //        }
        //    92: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    95: athrow         
        //    96: ldc             "-xc"
        //    98: dup            
        //    99: ifnonnull       143
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   108: athrow         
        //   109: new             Ljava/lang/IllegalStateException;
        //   112: dup            
        //   113: ldc             "@NotNull method %s.%s must not return null"
        //   115: ldc             2
        //   117: anewarray       Ljava/lang/Object;
        //   120: dup            
        //   121: ldc             0
        //   123: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   125: aastore        
        //   126: dup            
        //   127: ldc             1
        //   129: ldc             "getLanguageOption"
        //   131: aastore        
        //   132: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   135: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   138: athrow         
        //   139: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   142: athrow         
        //   143: areturn        
        //   144: ldc             "-xobjective-c"
        //   146: dup            
        //   147: ifnonnull       184
        //   150: new             Ljava/lang/IllegalStateException;
        //   153: dup            
        //   154: ldc             "@NotNull method %s.%s must not return null"
        //   156: ldc             2
        //   158: anewarray       Ljava/lang/Object;
        //   161: dup            
        //   162: ldc             0
        //   164: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   166: aastore        
        //   167: dup            
        //   168: ldc             1
        //   170: ldc             "getLanguageOption"
        //   172: aastore        
        //   173: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   176: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   179: athrow         
        //   180: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   183: athrow         
        //   184: areturn        
        //   185: ldc             "-xc++"
        //   187: dup            
        //   188: ifnonnull       225
        //   191: new             Ljava/lang/IllegalStateException;
        //   194: dup            
        //   195: ldc             "@NotNull method %s.%s must not return null"
        //   197: ldc             2
        //   199: anewarray       Ljava/lang/Object;
        //   202: dup            
        //   203: ldc             0
        //   205: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   207: aastore        
        //   208: dup            
        //   209: ldc             1
        //   211: ldc             "getLanguageOption"
        //   213: aastore        
        //   214: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   217: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   220: athrow         
        //   221: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   224: athrow         
        //   225: areturn        
        //   226: ldc             "-xobjective-c++"
        //   228: dup            
        //   229: ifnonnull       266
        //   232: new             Ljava/lang/IllegalStateException;
        //   235: dup            
        //   236: ldc             "@NotNull method %s.%s must not return null"
        //   238: ldc             2
        //   240: anewarray       Ljava/lang/Object;
        //   243: dup            
        //   244: ldc             0
        //   246: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   248: aastore        
        //   249: dup            
        //   250: ldc             1
        //   252: ldc             "getLanguageOption"
        //   254: aastore        
        //   255: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   258: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   261: athrow         
        //   262: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   265: athrow         
        //   266: areturn        
        //   267: ldc             ""
        //   269: dup            
        //   270: ifnonnull       307
        //   273: new             Ljava/lang/IllegalStateException;
        //   276: dup            
        //   277: ldc             "@NotNull method %s.%s must not return null"
        //   279: ldc             2
        //   281: anewarray       Ljava/lang/Object;
        //   284: dup            
        //   285: ldc             0
        //   287: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   289: aastore        
        //   290: dup            
        //   291: ldc             1
        //   293: ldc             "getLanguageOption"
        //   295: aastore        
        //   296: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   299: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   302: athrow         
        //   303: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   306: athrow         
        //   307: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     92     92     96     Ljava/lang/NumberFormatException;
        //  51     102    105    109    Ljava/lang/NumberFormatException;
        //  96     139    139    143    Ljava/lang/NumberFormatException;
        //  144    180    180    184    Ljava/lang/NumberFormatException;
        //  185    221    221    225    Ljava/lang/NumberFormatException;
        //  226    262    262    266    Ljava/lang/NumberFormatException;
        //  267    303    303    307    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0096:
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
    
    @NotNull
    @Override
    public CompilerInfo collectInfo(@NotNull final OCLanguageKind ocLanguageKind, @NotNull CidrCompilerSwitches build, @NotNull final CidrToolEnvironment cidrToolEnvironment) throws ExecutionException {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/toolchains/GCCCompiler", "collectInfo"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (build == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "switches", "com/jetbrains/cidr/toolchains/GCCCompiler", "collectInfo"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/toolchains/GCCCompiler", "collectInfo"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        final ArrayList<String> list = new ArrayList<String>();
        build = new CidrSwitchBuilder().addSingleRaw(getLanguageOption(ocLanguageKind)).addAll(build).addSingleRaw("-fpch-preprocess").addSingleRaw("-v").addSingleRaw("-dD").addSingleRaw("-E").addSingleRaw("-D___CIDR_DEFINITIONS_END").build();
        final ProcessOutput a = this.a(cidrToolEnvironment, build, buildFeaturesCheckText(), list);
        final OutputSections splitOutput = splitOutput(a.getStdout(), list);
        final Pair<String, Map<OCCompilerFeatures.Type<?>, Object>> collectDefinitionsAndFeatures = collectDefinitionsAndFeatures(splitOutput, list);
        final String s = (String)collectDefinitionsAndFeatures.first;
        final Map map = (Map)collectDefinitionsAndFeatures.second;
        final boolean a2 = a((CharSequence)s);
        boolean b2 = false;
        Label_0284: {
            Label_0275: {
                try {
                    a(build, a2, map);
                    if (!SystemInfo.isMac) {
                        break Label_0275;
                    }
                    final boolean b = a2;
                    if (!b) {
                        break Label_0275;
                    }
                    break Label_0275;
                }
                catch (ExecutionException ex4) {
                    throw c((Exception)ex4);
                }
                try {
                    final boolean b = a2;
                    if (!b) {
                        b2 = true;
                        break Label_0284;
                    }
                }
                catch (ExecutionException ex5) {
                    throw c((Exception)ex5);
                }
            }
            b2 = false;
        }
        final List<HeadersSearchPath> collectHeaderSearchPaths = collectHeaderSearchPaths(a, b2, cidrToolEnvironment, this.myWorkingDirectory);
        final List<File> a3 = this.a(build, cidrToolEnvironment, splitOutput.preprocessed, list);
        CompilerInfo compilerInfo;
        try {
            compilerInfo = new CompilerInfo(s, map, collectHeaderSearchPaths, a3, list);
            if (compilerInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "collectInfo"));
            }
        }
        catch (ExecutionException ex6) {
            throw c((Exception)ex6);
        }
        return compilerInfo;
    }
    
    @NotNull
    static OutputSections splitOutput(final String s, final List<String> list) {
        String substring = "";
        String substring2 = "";
        final int index = s.indexOf("___CIDR_DEFINITIONS_END");
        final int index2 = s.indexOf("___CIDR_FEATURES_START");
        final Function function = value -> {
            while (true) {
                try {
                    if (value <= 0 || StringUtil.isLineBreak(s.charAt(value - 1))) {
                        break;
                    }
                }
                catch (NumberFormatException ex) {
                    throw c(ex);
                }
                --value;
            }
            return value;
        };
        OutputSections outputSections = null;
        Label_0171: {
            Label_0124: {
                try {
                    if (index < 0 || index2 < 0) {
                        break Label_0124;
                    }
                }
                catch (NumberFormatException ex) {
                    throw c(ex);
                }
                final String substring3 = s.substring(0, (int)function.fun((Object)index));
                substring = s.substring(index + "___CIDR_DEFINITIONS_END".length() + 1, (int)function.fun((Object)index2));
                substring2 = s.substring(index2 + "___CIDR_FEATURES_START".length() + 1);
                break Label_0171;
            }
            list.add("Unexpected compiler output. This compiler might be unsupported.\nIf you are using GCC/Clang, please report the bug in https://youtrack.jetbrains.com/issues/CPP.");
            CidrLog.LOG.warn("Unexpected compiler output: CIDR_DEFINITIONS_END=" + index + " CIDR_FEATURES_START=" + index2);
            final String substring3 = s;
            try {
                outputSections = new OutputSections(substring3, substring, substring2);
                if (outputSections == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "splitOutput"));
                }
            }
            catch (NumberFormatException ex2) {
                throw c(ex2);
            }
        }
        return outputSections;
    }
    
    private static boolean a(final CharSequence charSequence) {
        return StringUtil.contains(charSequence, (CharSequence)"__clang_version__");
    }
    
    @NotNull
    private List<File> a(@NotNull final CidrCompilerSwitches p0, @NotNull final CidrToolEnvironment p1, @NotNull final String p2, @NotNull final List<String> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "switches"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getPrecompiledHeaders"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "environment"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getPrecompiledHeaders"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "preprocessed"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getPrecompiledHeaders"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "warnLog"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "getPrecompiledHeaders"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   176: athrow         
        //   177: new             Ljava/util/LinkedHashSet;
        //   180: dup            
        //   181: iconst_1       
        //   182: invokespecial   java/util/LinkedHashSet.<init>:(I)V
        //   185: astore          5
        //   187: aload_1        
        //   188: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.RAW:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   191: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches.getList:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   194: astore          6
        //   196: iconst_1       
        //   197: istore          7
        //   199: iconst_0       
        //   200: istore          8
        //   202: iload           8
        //   204: aload           6
        //   206: invokeinterface java/util/List.size:()I
        //   211: if_icmpge       381
        //   214: aload           6
        //   216: iload           8
        //   218: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   223: checkcast       Ljava/lang/String;
        //   226: ldc             "-include"
        //   228: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   231: ifeq            375
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   240: athrow         
        //   241: iinc            8, 1
        //   244: iload           8
        //   246: aload           6
        //   248: invokeinterface java/util/List.size:()I
        //   253: if_icmpge       375
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   262: athrow         
        //   263: aload           6
        //   265: iload           8
        //   267: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   272: checkcast       Ljava/lang/String;
        //   275: astore          9
        //   277: aload_0        
        //   278: aload_2        
        //   279: aload           9
        //   281: invokevirtual   com/jetbrains/cidr/toolchains/GCCCompiler.preparePath:(Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Ljava/lang/String;)Ljava/lang/String;
        //   284: astore          10
        //   286: new             Ljava/io/File;
        //   289: dup            
        //   290: aload           10
        //   292: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   295: astore          11
        //   297: aload           11
        //   299: invokevirtual   java/io/File.exists:()Z
        //   302: ifeq            320
        //   305: aload           11
        //   307: invokevirtual   java/io/File.isDirectory:()Z
        //   310: ifeq            352
        //   313: goto            320
        //   316: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   319: athrow         
        //   320: iload           7
        //   322: ifeq            349
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   331: athrow         
        //   332: aload_0        
        //   333: aload_3        
        //   334: aload_2        
        //   335: aload           11
        //   337: aload           4
        //   339: invokespecial   com/jetbrains/cidr/toolchains/GCCCompiler.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Ljava/io/File;Ljava/util/List;)Ljava/io/File;
        //   342: goto            350
        //   345: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   348: athrow         
        //   349: aconst_null    
        //   350: astore          11
        //   352: aload           11
        //   354: ifnull          372
        //   357: aload           5
        //   359: aload           11
        //   361: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
        //   364: pop            
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   371: athrow         
        //   372: iconst_0       
        //   373: istore          7
        //   375: iinc            8, 1
        //   378: goto            202
        //   381: new             Lcom/intellij/util/SmartList;
        //   384: dup            
        //   385: aload           5
        //   387: invokespecial   com/intellij/util/SmartList.<init>:(Ljava/util/Collection;)V
        //   390: dup            
        //   391: ifnonnull       428
        //   394: new             Ljava/lang/IllegalStateException;
        //   397: dup            
        //   398: ldc             "@NotNull method %s.%s must not return null"
        //   400: ldc             2
        //   402: anewarray       Ljava/lang/Object;
        //   405: dup            
        //   406: ldc             0
        //   408: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   410: aastore        
        //   411: dup            
        //   412: ldc             1
        //   414: ldc             "getPrecompiledHeaders"
        //   416: aastore        
        //   417: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   420: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   423: athrow         
        //   424: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   427: athrow         
        //   428: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;)Ljava/util/List<Ljava/io/File;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     84     84     88     Ljava/lang/NumberFormatException;
        //  88     128    128    132    Ljava/lang/NumberFormatException;
        //  132    173    173    177    Ljava/lang/NumberFormatException;
        //  202    234    237    241    Ljava/lang/NumberFormatException;
        //  214    256    259    263    Ljava/lang/NumberFormatException;
        //  297    313    316    320    Ljava/lang/NumberFormatException;
        //  305    325    328    332    Ljava/lang/NumberFormatException;
        //  320    345    345    349    Ljava/lang/NumberFormatException;
        //  352    365    368    372    Ljava/lang/NumberFormatException;
        //  381    424    424    428    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0320:
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
    private File a(@NotNull final String s, @NotNull final CidrToolEnvironment cidrToolEnvironment, @NotNull final File file, @NotNull final List<String> list) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "preprocessed", "com/jetbrains/cidr/toolchains/GCCCompiler", "extractOriginalPrecompiledHeaderFile"));
            }
        }
        catch (IOException ex) {
            throw c(ex);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/toolchains/GCCCompiler", "extractOriginalPrecompiledHeaderFile"));
            }
        }
        catch (IOException ex2) {
            throw c(ex2);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pch", "com/jetbrains/cidr/toolchains/GCCCompiler", "extractOriginalPrecompiledHeaderFile"));
            }
        }
        catch (IOException ex3) {
            throw c(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "warnLog", "com/jetbrains/cidr/toolchains/GCCCompiler", "extractOriginalPrecompiledHeaderFile"));
            }
        }
        catch (IOException ex4) {
            throw c(ex4);
        }
        final boolean contains = s.contains("#pragma GCC pch_preprocess");
        if (!contains) {
            final Matcher matcher = GCCCompiler.INCLUDE_FILE_PATTERN.matcher(s);
            if (matcher.find()) {
                return new File(this.preparePath(cidrToolEnvironment, matcher.group(1)));
            }
        }
        File file2 = null;
        if (!file.isDirectory()) {
            file2 = new File(file.getAbsolutePath() + ".d");
        }
        else {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (final File file3 : listFiles) {
                    if (file3.getName().endsWith(".d")) {
                        file2 = file3;
                        break;
                    }
                }
            }
        }
        Label_0476: {
            try {
                if (file2 == null || !file2.exists()) {
                    break Label_0476;
                }
            }
            catch (IOException ex5) {
                throw c(ex5);
            }
            try {
                final Matcher matcher2 = GCCCompiler.DEPENDENCY_PATTERN.matcher(FileUtil.loadFile(file2));
                if (matcher2.find()) {
                    final File file4 = new File(this.preparePath(cidrToolEnvironment, matcher2.group(1).replaceAll("\\\\[ ]", " ")));
                    try {
                        if (file4.exists()) {
                            return file4;
                        }
                    }
                    catch (IOException ex6) {
                        throw c(ex6);
                    }
                    return null;
                }
            }
            catch (IOException ex7) {
                list.add("Dependency file (which is used to get the original file name of a precompiled header)\nis corrupted or has invalid permissions: " + file2 + "\nplease run PCH compilation with -MD (or -MMD) flag.");
                return null;
            }
        }
        if (contains) {
            list.add("Cannot get the original file name of the precompiled header: " + file + "\nplease run PCH compilation with -MD (or -MMD) flag.");
        }
        return null;
    }
    
    static List<HeadersSearchPath> collectHeaderSearchPaths(final ProcessOutput processOutput, boolean b, final CidrToolEnvironment cidrToolEnvironment, final File file) {
        final ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<? extends HeadersSearchPath> list2 = null;
        Label_0028: {
            try {
                if (b) {
                    list2 = new ArrayList<HeadersSearchPath>();
                    break Label_0028;
                }
            }
            catch (NumberFormatException ex) {
                throw c(ex);
            }
            list2 = null;
        }
        final ArrayList<? extends HeadersSearchPath> list3 = list2;
        Boolean b2 = null;
        final Iterator<String> iterator = (Iterator<String>)processOutput.getStderrLines().iterator();
        while (iterator.hasNext()) {
            String trim = iterator.next().trim();
            if ("#include \"...\" search starts here:".equals(trim)) {
                b2 = true;
            }
            else if ("#include <...> search starts here:".equals(trim)) {
                b2 = false;
            }
            else {
                try {
                    if ("End of search list.".equals(trim)) {
                        break;
                    }
                }
                catch (NumberFormatException ex2) {
                    throw c(ex2);
                }
                if (b2 == null) {
                    continue;
                }
                final String trimEnd = StringUtil.trimEnd(trim, " (framework directory)");
                boolean b3 = false;
                if (!trim.equals(trimEnd)) {
                    trim = trimEnd;
                    b3 = true;
                    b = false;
                }
                final String localPath = cidrToolEnvironment.toLocalPath(file, StringUtil.nullize(trim.trim(), true));
                if (localPath == null) {
                    continue;
                }
                final File file2 = new File(FileUtil.toCanonicalPath(localPath, true));
                try {
                    list.add(new HeadersSearchPath(file2, false, b2, b3));
                    if (!b) {
                        continue;
                    }
                    list3.add((HeadersSearchPath)new HeadersSearchPath(file2, false, b2, true));
                }
                catch (NumberFormatException ex3) {
                    throw c(ex3);
                }
            }
        }
        Label_0283: {
            try {
                if (!b) {
                    return (List<HeadersSearchPath>)list;
                }
                final ArrayList<? extends HeadersSearchPath> list4 = list3;
                if (list4 != null) {
                    break Label_0283;
                }
                return (List<HeadersSearchPath>)list;
            }
            catch (NumberFormatException ex4) {
                throw c(ex4);
            }
            try {
                final ArrayList<? extends HeadersSearchPath> list4 = list3;
                if (list4 != null) {
                    list.addAll(list3);
                }
            }
            catch (NumberFormatException ex5) {
                throw c(ex5);
            }
        }
        return (List<HeadersSearchPath>)list;
    }
    
    @NotNull
    static Pair<String, Map<OCCompilerFeatures.Type<?>, Object>> collectDefinitionsAndFeatures(@NotNull final OutputSections outputSections, final List<String> list) {
        try {
            if (outputSections == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/toolchains/GCCCompiler", "collectDefinitionsAndFeatures"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        final StringBuilder sb = new StringBuilder();
        final LinkedHashMap<OCCompilerFeatures.TypeSize, Short> linkedHashMap = new LinkedHashMap<OCCompilerFeatures.TypeSize, Short>();
        final String[] splitByLines = StringUtil.splitByLines(outputSections.defines);
        for (int length = splitByLines.length, i = 0; i < length; ++i) {
            final String trim = splitByLines[i].trim();
            try {
                if (trim.isEmpty()) {
                    continue;
                }
            }
            catch (NumberFormatException ex2) {
                throw c(ex2);
            }
            try {
                if (trim.startsWith("#define ")) {
                    sb.append(trim).append("\n");
                }
            }
            catch (NumberFormatException ex3) {
                throw c(ex3);
            }
        }
        final boolean a = a(sb);
        final StringBuilder sb2 = new StringBuilder();
        boolean b = false;
        try {
            if (a) {
                sb2.append(getClangTestMacros());
            }
        }
        catch (NumberFormatException ex4) {
            throw c(ex4);
        }
        final String[] splitByLines2 = StringUtil.splitByLines(outputSections.featureChecks);
        for (int length2 = splitByLines2.length, j = 0; j < length2; ++j) {
            final Matcher matcher = GCCCompiler.QUERY_RESULT_PATTERN.matcher(splitByLines2[j]);
            try {
                if (!matcher.find()) {
                    continue;
                }
            }
            catch (NumberFormatException ex5) {
                throw c(ex5);
            }
            final String group = matcher.group(1);
            final String group2 = matcher.group(2);
            String group3 = matcher.group(3);
            Label_0354: {
                try {
                    if ("typeSize".equals(group)) {
                        final LinkedHashMap<OCCompilerFeatures.TypeSize, Short> linkedHashMap2 = linkedHashMap;
                        final String s = group2;
                        final OCCompilerFeatures.TypeSize typeSize = OCCompilerFeatures.TypeSize.valueOf(s);
                        final String s2 = group3;
                        final short n = Short.parseShort(s2);
                        final Short n2 = n;
                        linkedHashMap2.put(typeSize, n2);
                        continue;
                    }
                    break Label_0354;
                }
                catch (IllegalStateException ex6) {
                    throw c(ex6);
                }
                try {
                    final LinkedHashMap<OCCompilerFeatures.TypeSize, Short> linkedHashMap2 = linkedHashMap;
                    final String s = group2;
                    final OCCompilerFeatures.TypeSize typeSize = OCCompilerFeatures.TypeSize.valueOf(s);
                    final String s2 = group3;
                    final short n = Short.parseShort(s2);
                    final Short n2 = n;
                    linkedHashMap2.put(typeSize, n2);
                    continue;
                }
                catch (NumberFormatException ex13) {
                    list.add("Cannot determine type size for " + group2 + ":" + group3 + "\n" + "This compiler might be unsupported.\nIf you are using GCC/Clang, please report the bug in https://youtrack.jetbrains.com/issues/CPP.");
                    continue;
                }
                try {
                    if ("feature".equals(group)) {
                        linkedHashMap.put((OCCompilerFeatures.TypeSize)OCCompilerFeatures.Feature.valueOf(group2), (Short)(Object)group3.equals("1"));
                        continue;
                    }
                }
                catch (NumberFormatException ex7) {
                    throw c(ex7);
                }
            }
            String s3;
            if (group.equals("clangFeature")) {
                s3 = clangFeatureMacro(group2);
            }
            else if (group.equals("clangExtension")) {
                s3 = clangExtensionMacro(group2);
            }
            else {
                if (!group.equals("clangAttribute")) {
                    CidrLog.LOG.error("Unexpected query type: " + group);
                    continue;
                }
                s3 = clangAttributeMacro(group2);
            }
            Label_0509: {
                try {
                    if (!a) {
                        continue;
                    }
                    if (!ClangFeatures.getUnsupportedClangFeatures().contains(group2)) {
                        break Label_0509;
                    }
                }
                catch (NumberFormatException ex8) {
                    throw c(ex8);
                }
                group3 = "0";
            }
            sb2.append("#define ").append(s3).append(" ").append(group3).append("\n");
            final OCCompilerFeatures.Feature featureForClangId = ClangFeatures.getFeatureForClangId(group2);
            try {
                if (featureForClangId != null) {
                    linkedHashMap.put((OCCompilerFeatures.TypeSize)featureForClangId, (Short)(Object)group3.equals("1"));
                }
            }
            catch (NumberFormatException ex9) {
                throw c(ex9);
            }
            try {
                if (!"objc_bool".equals(group2) || !"1".equals(group3)) {
                    continue;
                }
            }
            catch (NumberFormatException ex10) {
                throw c(ex10);
            }
            b = true;
        }
        try {
            sb.append((CharSequence)sb2);
            if (b) {
                sb.append("#define __objc_no ((BOOL)0)\n#define __objc_yes ((BOOL)1)\n");
            }
        }
        catch (NumberFormatException ex11) {
            throw c(ex11);
        }
        Pair pair;
        try {
            sb.append("#define __extension__\n");
            sb.append(getGCCBuiltInFunctionMacros());
            pair = new Pair((Object)sb.toString(), (Object)linkedHashMap);
            if (pair == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "collectDefinitionsAndFeatures"));
            }
        }
        catch (NumberFormatException ex12) {
            throw c(ex12);
        }
        return (Pair<String, Map<OCCompilerFeatures.Type<?>, Object>>)pair;
    }
    
    protected static String buildFeaturesCheckText() {
        final StringBuilder sb = new StringBuilder("#define ___CIDR_FEATURES_START\n");
        final GCCCompiler.1Checker 1Checker = new GCCCompiler.1Checker(sb);
        sb.append("#if __clang__\n");
        sb.append("#if !(defined (__has_extension)) && defined(__has_feature)\n  #define __has_extension __has_feature\n#endif\n#if !defined(__has_attribute)\n  #define __has_attribute(x) 0\n#endif\n#if !defined(__is_identifier)\n  #define __is_identifier(x) 1\n#endif\n");
        final GCCCompiler.1Checker 1Checker2;
        ClangFeatures.getAllFeatures().forEach(s -> {
            1Checker2.check("__has_feature(" + s + ")", "clangFeature", s);
            1Checker2.check("__has_extension(" + s + ")", "clangExtension", s);
            return;
        });
        ClangFeatures.getAllAttributes().forEach(s2 -> 1Checker.check("__has_attribute(" + s2 + ")", "clangAttribute", s2));
        1Checker.check("!__is_identifier(__auto_type)", OCCompilerFeatures.Feature.GCC_AUTO_TYPE);
        1Checker.check("__has_builtin(__builtin_available)", OCCompilerFeatures.Feature.BUILTIN_AVAILABLE);
        sb.append("#else\n");
        sb.append("#define __CIDR_GCC_VERSION (__GNUC__ * 10000 + __GNUC_MINOR__ * 100 + __GNUC_PATCHLEVEL__)\n");
        final LinkedHashMap<OCCompilerFeatures.Feature, String> linkedHashMap = new LinkedHashMap<OCCompilerFeatures.Feature, String>();
        linkedHashMap.put(OCCompilerFeatures.Feature.GCC_AUTO_TYPE, "!defined(__cplusplus) && __CIDR_GCC_VERSION >= 40900");
        linkedHashMap.put(OCCompilerFeatures.Feature.C_STATIC_ASSERT, "!defined(__cplusplus) && __STDC_VERSION__ >= 201112L && __CIDR_GCC_VERSION >= 40600");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_AUTO_TYPE, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40400");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_OVERRIDE_CONTROL, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40700");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_GENERALIZED_INITIALIZERS, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40400");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_NULLPTR, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40600");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_NONSTATIC_MEMBER_INIT, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40700");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_EXCEPTIONS, "defined(__cplusplus) && __EXCEPTIONS");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_CONSTEXPR, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40600");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_RAW_STRING_LITERALS, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40500");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_USER_LITERALS, "__cplusplus >= 201103 && __CIDR_GCC_VERSION >= 40700");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_BINARY_LITERALS, "__cplusplus >= 201402 && __CIDR_GCC_VERSION >= 40900");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_RETURN_TYPE_DEDUCTION, "__cplusplus >= 201402 && __CIDR_GCC_VERSION >= 40900");
        linkedHashMap.put(OCCompilerFeatures.Feature.CXX_GENERIC_LAMBDAS, "__cplusplus >= 201402 && __CIDR_GCC_VERSION >= 40900");
        linkedHashMap.forEach((feature, s3) -> 1Checker.check(s3, feature));
        sb.append("#endif\n");
        getTypeSizesMacros().forEach((typeSize, s4) -> sb.append("#if defined(").append(s4).append(")\n").append("____CIDR_test_query_").append("typeSize").append("->").append(typeSize).append("=").append(s4).append("\n").append("#endif\n"));
        return sb.toString();
    }
    
    @NotNull
    public static LinkedHashMap<OCCompilerFeatures.TypeSize, String> getTypeSizesMacros() {
        final LinkedHashMap<OCCompilerFeatures.TypeSize, String> linkedHashMap = new LinkedHashMap<OCCompilerFeatures.TypeSize, String>();
        LinkedHashMap<OCCompilerFeatures.TypeSize, String> linkedHashMap2;
        try {
            linkedHashMap.put(OCCompilerFeatures.TypeSize.WCHAR_T, "__SIZEOF_WCHAR_T__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.SHORT, "__SIZEOF_SHORT__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.INT, "__SIZEOF_INT__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.LONG, "__SIZEOF_LONG__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.POINTER, "__SIZEOF_POINTER__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.LONG_LONG, "__SIZEOF_LONG_LONG__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.INT128_T, "__SIZEOF_INT128__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.FLOAT, "__SIZEOF_FLOAT__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.DOUBLE, "__SIZEOF_DOUBLE__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.LONG_DOUBLE, "__SIZEOF_LONG_DOUBLE__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.SIZE_T, "__SIZEOF_SIZE_T__");
            linkedHashMap.put(OCCompilerFeatures.TypeSize.PTRDIFF_T, "__SIZEOF_PTRDIFF_T__");
            linkedHashMap2 = linkedHashMap;
            if (linkedHashMap2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "getTypeSizesMacros"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        return linkedHashMap2;
    }
    
    private static void a(@NotNull final CidrCompilerSwitches p0, final boolean p1, final Map<OCCompilerFeatures.Type<?>, Object> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "switches"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "collectDiagnostics"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: new             Lgnu/trove/THashSet;
        //    47: dup            
        //    48: aload_0        
        //    49: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.RAW:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    52: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches.getList:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //    55: invokespecial   gnu/trove/THashSet.<init>:(Ljava/util/Collection;)V
        //    58: astore_3       
        //    59: aload_3        
        //    60: ldc             "-w"
        //    62: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    67: ifeq            75
        //    70: return         
        //    71: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    74: athrow         
        //    75: aload_3        
        //    76: ldc             "-Weverything"
        //    78: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    83: istore          4
        //    85: iload           4
        //    87: ifne            108
        //    90: aload_3        
        //    91: ldc             "-Wall"
        //    93: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    98: ifeq            116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   115: athrow         
        //   116: iconst_0       
        //   117: istore          5
        //   119: aload_3        
        //   120: ldc             "-pedantic-errors"
        //   122: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   127: istore          6
        //   129: iload           4
        //   131: ifne            182
        //   134: iload           6
        //   136: ifne            182
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   145: athrow         
        //   146: aload_3        
        //   147: ldc             "-pedantic"
        //   149: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   154: ifne            182
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   163: athrow         
        //   164: aload_3        
        //   165: ldc             "-Wpedantic"
        //   167: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   172: ifeq            190
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   181: athrow         
        //   182: iconst_1       
        //   183: goto            191
        //   186: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   189: athrow         
        //   190: iconst_0       
        //   191: istore          7
        //   193: aload_3        
        //   194: ldc             "-Werror"
        //   196: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   201: istore          8
        //   203: new             Lcom/jetbrains/cidr/toolchains/GCCCompiler$2Checker;
        //   206: dup            
        //   207: aload_2        
        //   208: aload_3        
        //   209: invokespecial   com/jetbrains/cidr/toolchains/GCCCompiler$2Checker.<init>:(Ljava/util/Map;Ljava/util/Set;)V
        //   212: astore          9
        //   214: aload           9
        //   216: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic.MISSING_RETURN_FROM_NON_VOID:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic;
        //   219: iload_1        
        //   220: ifne            235
        //   223: iload           5
        //   225: ifeq            243
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   234: athrow         
        //   235: iconst_1       
        //   236: goto            244
        //   239: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   242: athrow         
        //   243: iconst_0       
        //   244: iload           8
        //   246: ldc             "return-type"
        //   248: invokevirtual   com/jetbrains/cidr/toolchains/GCCCompiler$2Checker.add:(Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic;ZZLjava/lang/String;)V
        //   251: iload_1        
        //   252: ifeq            276
        //   255: aload           9
        //   257: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic.FOLDING_CONSTANT:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic;
        //   260: iload           7
        //   262: iload           6
        //   264: ldc             "gnu-folding-constant"
        //   266: invokevirtual   com/jetbrains/cidr/toolchains/GCCCompiler$2Checker.add:(Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic;ZZLjava/lang/String;)V
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   275: athrow         
        //   276: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;ZLjava/util/Map<Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type<*>;Ljava/lang/Object;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  59     71     71     75     Ljava/lang/NumberFormatException;
        //  85     101    104    108    Ljava/lang/NumberFormatException;
        //  90     112    112    116    Ljava/lang/NumberFormatException;
        //  129    139    142    146    Ljava/lang/NumberFormatException;
        //  134    157    160    164    Ljava/lang/NumberFormatException;
        //  146    175    178    182    Ljava/lang/NumberFormatException;
        //  164    186    186    190    Ljava/lang/NumberFormatException;
        //  214    228    231    235    Ljava/lang/NumberFormatException;
        //  223    239    239    243    Ljava/lang/NumberFormatException;
        //  244    269    272    276    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0146:
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
    
    private ProcessOutput a(@NotNull final CidrToolEnvironment cidrToolEnvironment, @NotNull final CidrCompilerSwitches cidrCompilerSwitches, @NotNull final String s, @NotNull final List<String> list) throws ExecutionException {
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/toolchains/GCCCompiler", "runGCC"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (cidrCompilerSwitches == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/toolchains/GCCCompiler", "runGCC"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileText", "com/jetbrains/cidr/toolchains/GCCCompiler", "runGCC"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "warnLog", "com/jetbrains/cidr/toolchains/GCCCompiler", "runGCC"));
            }
        }
        catch (ExecutionException ex4) {
            throw c((Exception)ex4);
        }
        return this.a(cidrToolEnvironment, cidrCompilerSwitches, ContainerUtil.set((Object[])new String[] { "-Werror", "-fdiagnostics-format", "-imacros", "-" }), 0, 0, false, s, list);
    }
    
    private ProcessOutput a(@NotNull final CidrToolEnvironment p0, @NotNull final CidrCompilerSwitches p1, @NotNull final Set<String> p2, final int p3, final int p4, final boolean p5, @NotNull final String p6, @NotNull final List<String> p7) throws ExecutionException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "environment"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "tryRunGCC"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "options"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "tryRunGCC"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "skipOptions"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "tryRunGCC"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload           7
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "fileText"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "tryRunGCC"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   176: athrow         
        //   177: aload           8
        //   179: ifnonnull       222
        //   182: new             Ljava/lang/IllegalArgumentException;
        //   185: dup            
        //   186: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   188: ldc             3
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: ldc             0
        //   196: ldc             "warnLog"
        //   198: aastore        
        //   199: dup            
        //   200: ldc             1
        //   202: ldc             "com/jetbrains/cidr/toolchains/GCCCompiler"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             2
        //   208: ldc             "tryRunGCC"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   221: athrow         
        //   222: aload_2        
        //   223: aload_3        
        //   224: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.filterOptions:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Ljava/util/Set;)Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;
        //   227: astore          9
        //   229: aconst_null    
        //   230: astore          11
        //   232: aconst_null    
        //   233: astore          14
        //   235: aload_0        
        //   236: invokevirtual   com/jetbrains/cidr/toolchains/GCCCompiler.getExecutablePath:()Ljava/lang/String;
        //   239: astore          15
        //   241: ldc             "response-file"
        //   243: ldc             ".txt"
        //   245: iconst_1       
        //   246: invokestatic    com/intellij/openapi/util/io/FileUtil.createTempFile:(Ljava/lang/String;Ljava/lang/String;Z)Ljava/io/File;
        //   249: astore          11
        //   251: new             Ljava/lang/StringBuilder;
        //   254: dup            
        //   255: invokespecial   java/lang/StringBuilder.<init>:()V
        //   258: ldc             "@"
        //   260: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: aload_1        
        //   264: aload           11
        //   266: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   269: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.toEnvPath:(Ljava/lang/String;)Ljava/lang/String;
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   278: astore          12
        //   280: aload           9
        //   282: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   285: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches.getCommandLineString:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/lang/String;
        //   288: astore          13
        //   290: iload           6
        //   292: ifne            309
        //   295: aload           11
        //   297: aload           13
        //   299: invokestatic    com/intellij/openapi/util/io/FileUtil.writeToFile:(Ljava/io/File;Ljava/lang/String;)V
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   308: athrow         
        //   309: ldc             "compiler-file"
        //   311: ldc             ""
        //   313: iconst_1       
        //   314: invokestatic    com/intellij/openapi/util/io/FileUtil.createTempFile:(Ljava/lang/String;Ljava/lang/String;Z)Ljava/io/File;
        //   317: astore          14
        //   319: aload           14
        //   321: aload           7
        //   323: invokestatic    com/intellij/openapi/util/io/FileUtil.writeToFile:(Ljava/io/File;Ljava/lang/String;)V
        //   326: goto            343
        //   329: astore          16
        //   331: new             Lcom/intellij/execution/ExecutionException;
        //   334: dup            
        //   335: ldc             "Unable to create temporary file"
        //   337: aload           16
        //   339: invokespecial   com/intellij/execution/ExecutionException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   342: athrow         
        //   343: new             Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   346: dup            
        //   347: invokespecial   com/intellij/execution/configurations/GeneralCommandLine.<init>:()V
        //   350: astore          16
        //   352: aload_1        
        //   353: aload           16
        //   355: getstatic       com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor.BUILD:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;
        //   358: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.prepare:(Lcom/intellij/execution/configurations/GeneralCommandLine;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;)V
        //   361: aload           16
        //   363: aload           15
        //   365: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.setExePath:(Ljava/lang/String;)V
        //   368: aload           16
        //   370: aload_0        
        //   371: getfield        com/jetbrains/cidr/toolchains/GCCCompiler.myWorkingDirectory:Ljava/io/File;
        //   374: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.withWorkDirectory:(Ljava/io/File;)Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   377: pop            
        //   378: aload           16
        //   380: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getEnvironment:()Ljava/util/Map;
        //   383: ldc             "LC_ALL"
        //   385: ldc             "C"
        //   387: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   392: pop            
        //   393: iload           6
        //   395: ifne            419
        //   398: aload           16
        //   400: iconst_1       
        //   401: anewarray       Ljava/lang/String;
        //   404: dup            
        //   405: iconst_0       
        //   406: aload           12
        //   408: aastore        
        //   409: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameters:([Ljava/lang/String;)V
        //   412: goto            432
        //   415: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   418: athrow         
        //   419: aload           16
        //   421: aload           9
        //   423: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.RAW:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   426: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches.getList:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   429: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameters:(Ljava/util/List;)V
        //   432: aload           16
        //   434: iconst_1       
        //   435: anewarray       Ljava/lang/String;
        //   438: dup            
        //   439: iconst_0       
        //   440: aload_1        
        //   441: aload           14
        //   443: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   446: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.toEnvPath:(Ljava/lang/String;)Ljava/lang/String;
        //   449: aastore        
        //   450: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameters:([Ljava/lang/String;)V
        //   453: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   456: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   459: ifeq            508
        //   462: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   465: new             Ljava/lang/StringBuilder;
        //   468: dup            
        //   469: invokespecial   java/lang/StringBuilder.<init>:()V
        //   472: ldc             "Running compiler: "
        //   474: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: aload           16
        //   479: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getCommandLineString:()Ljava/lang/String;
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: ldc             "\nArguments file contents: "
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: aload           9
        //   492: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   495: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   498: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   501: goto            508
        //   504: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   507: athrow         
        //   508: getstatic       com/jetbrains/cidr/toolchains/GCCCompiler.outCompilerRunner:Lcom/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner;
        //   511: aload           16
        //   513: invokevirtual   com/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner.run:(Lcom/intellij/execution/configurations/GeneralCommandLine;)Lcom/intellij/execution/process/ProcessOutput;
        //   516: astore          10
        //   518: aload           14
        //   520: ifnull          536
        //   523: aload           14
        //   525: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   528: pop            
        //   529: goto            536
        //   532: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   535: athrow         
        //   536: aload           11
        //   538: ifnull          595
        //   541: aload           11
        //   543: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   546: pop            
        //   547: goto            595
        //   550: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   553: athrow         
        //   554: astore          17
        //   556: aload           14
        //   558: ifnull          574
        //   561: aload           14
        //   563: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   566: pop            
        //   567: goto            574
        //   570: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   573: athrow         
        //   574: aload           11
        //   576: ifnull          592
        //   579: aload           11
        //   581: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   584: pop            
        //   585: goto            592
        //   588: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   591: athrow         
        //   592: aload           17
        //   594: athrow         
        //   595: new             Ljava/lang/StringBuilder;
        //   598: dup            
        //   599: invokespecial   java/lang/StringBuilder.<init>:()V
        //   602: aload           15
        //   604: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   607: ldc             " "
        //   609: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   612: aload           13
        //   614: ldc             " -D___CIDR_DEFINITIONS_END"
        //   616: invokestatic    java/util/regex/Matcher.quoteReplacement:(Ljava/lang/String;)Ljava/lang/String;
        //   619: ldc             ""
        //   621: invokevirtual   java/lang/String.replaceFirst:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   624: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   627: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   630: astore          16
        //   632: aload           10
        //   634: invokevirtual   com/intellij/execution/process/ProcessOutput.isTimeout:()Z
        //   637: ifeq            691
        //   640: iload           5
        //   642: iconst_1       
        //   643: if_icmpge       685
        //   646: goto            653
        //   649: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   652: athrow         
        //   653: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   656: ldc             "Trying to run compiler after timeout"
        //   658: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   661: aload_0        
        //   662: aload_1        
        //   663: aload_2        
        //   664: aload_3        
        //   665: iload           4
        //   667: iload           5
        //   669: iconst_1       
        //   670: iadd           
        //   671: iload           6
        //   673: aload           7
        //   675: aload           8
        //   677: invokespecial   com/jetbrains/cidr/toolchains/GCCCompiler.a:(Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Ljava/util/Set;IIZLjava/lang/String;Ljava/util/List;)Lcom/intellij/execution/process/ProcessOutput;
        //   680: areturn        
        //   681: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   684: athrow         
        //   685: aload           16
        //   687: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.throwCompilerTimeout:(Ljava/lang/String;)Lcom/intellij/execution/ExecutionException;
        //   690: athrow         
        //   691: aload           10
        //   693: invokevirtual   com/intellij/execution/process/ProcessOutput.getExitCode:()I
        //   696: ifeq            868
        //   699: iload           6
        //   701: ifne            760
        //   704: goto            711
        //   707: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   710: athrow         
        //   711: aload           10
        //   713: invokevirtual   com/intellij/execution/process/ProcessOutput.getStderr:()Ljava/lang/String;
        //   716: aload           12
        //   718: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   721: ifeq            760
        //   724: goto            731
        //   727: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   730: athrow         
        //   731: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   734: ldc             "Trying to run compiler without a @response file"
        //   736: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   739: aload_0        
        //   740: aload_1        
        //   741: aload_2        
        //   742: aload_3        
        //   743: iload           4
        //   745: iload           5
        //   747: iconst_1       
        //   748: aload           7
        //   750: aload           8
        //   752: invokespecial   com/jetbrains/cidr/toolchains/GCCCompiler.a:(Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Ljava/util/Set;IIZLjava/lang/String;Ljava/util/List;)Lcom/intellij/execution/process/ProcessOutput;
        //   755: areturn        
        //   756: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   759: athrow         
        //   760: iload           4
        //   762: iconst_2       
        //   763: if_icmpge       860
        //   766: aload           10
        //   768: invokevirtual   com/intellij/execution/process/ProcessOutput.getStderrLines:()Ljava/util/List;
        //   771: aload_3        
        //   772: aload           8
        //   774: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.collectSkipOptionsGcc:(Ljava/util/List;Ljava/util/Set;Ljava/util/List;)Z
        //   777: ifeq            860
        //   780: goto            787
        //   783: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   786: athrow         
        //   787: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   790: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   793: ifeq            840
        //   796: goto            803
        //   799: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   802: athrow         
        //   803: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   806: new             Ljava/lang/StringBuilder;
        //   809: dup            
        //   810: invokespecial   java/lang/StringBuilder.<init>:()V
        //   813: ldc             "Trying to run compiler with skipped options: "
        //   815: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   818: aload_3        
        //   819: ldc             " "
        //   821: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   824: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   827: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   830: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   833: goto            840
        //   836: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   839: athrow         
        //   840: aload_0        
        //   841: aload_1        
        //   842: aload_2        
        //   843: aload_3        
        //   844: iload           4
        //   846: iconst_1       
        //   847: iadd           
        //   848: iload           5
        //   850: iload           6
        //   852: aload           7
        //   854: aload           8
        //   856: invokespecial   com/jetbrains/cidr/toolchains/GCCCompiler.a:(Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Ljava/util/Set;IIZLjava/lang/String;Ljava/util/List;)Lcom/intellij/execution/process/ProcessOutput;
        //   859: areturn        
        //   860: aload           10
        //   862: aload           16
        //   864: invokestatic    com/jetbrains/cidr/toolchains/GCCCompiler.throwCompilerError:(Lcom/intellij/execution/process/ProcessOutput;Ljava/lang/String;)Lcom/intellij/execution/ExecutionException;
        //   867: athrow         
        //   868: aload           10
        //   870: areturn        
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Ljava/util/Set<Ljava/lang/String;>;IIZLjava/lang/String;Ljava/util/List<Ljava/lang/String;>;)Lcom/intellij/execution/process/ProcessOutput;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  290    302    305    309    Ljava/io/IOException;
        //  177    218    218    222    Ljava/io/IOException;
        //  132    173    173    177    Ljava/io/IOException;
        //  88     128    128    132    Ljava/io/IOException;
        //  44     84     84     88     Ljava/io/IOException;
        //  0      40     40     44     Ljava/io/IOException;
        //  241    326    329    343    Ljava/io/IOException;
        //  432    501    504    508    Ljava/io/IOException;
        //  352    415    415    419    Ljava/io/IOException;
        //  241    518    554    595    Any
        //  536    550    550    554    Ljava/io/IOException;
        //  518    529    532    536    Ljava/io/IOException;
        //  554    556    554    595    Any
        //  556    567    570    574    Ljava/io/IOException;
        //  574    585    588    592    Ljava/io/IOException;
        //  632    646    649    653    Ljava/io/IOException;
        //  640    681    681    685    Ljava/io/IOException;
        //  691    704    707    711    Ljava/io/IOException;
        //  699    724    727    731    Ljava/io/IOException;
        //  711    756    756    760    Ljava/io/IOException;
        //  760    780    783    787    Ljava/io/IOException;
        //  766    796    799    803    Ljava/io/IOException;
        //  787    833    836    840    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0711:
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
    
    @NotNull
    static CidrCompilerSwitches filterOptions(@NotNull final CidrCompilerSwitches cidrCompilerSwitches, @NotNull final Set<String> set) {
        try {
            if (cidrCompilerSwitches == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "switches", "com/jetbrains/cidr/toolchains/GCCCompiler", "filterOptions"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "skipOptions", "com/jetbrains/cidr/toolchains/GCCCompiler", "filterOptions"));
            }
        }
        catch (NumberFormatException ex2) {
            throw c(ex2);
        }
        final BiFunction<String, String, Boolean> biFunction = new BiFunction<String, String, Boolean>() {
            boolean archAdded = false;
            boolean skipOptionValue = false;
            
            @Override
            public Boolean apply(String trim, final String s) {
                trim = trim.trim();
                final boolean access$000 = a(trim);
                if (this.skipOptionValue) {
                    this.skipOptionValue = false;
                    if (!access$000) {
                        return false;
                    }
                }
                if (set.contains(trim)) {
                    this.skipOptionValue = access$000;
                    return false;
                }
                if (trim.startsWith("-o")) {
                    if (trim.equals("-o")) {
                        this.skipOptionValue = true;
                    }
                    return false;
                }
                if ("-arch".equals(trim)) {
                    if (this.archAdded) {
                        this.skipOptionValue = true;
                        return false;
                    }
                    this.archAdded = true;
                }
                if ("-include".equals(trim) && set.contains(s)) {
                    return false;
                }
                return true;
            }
        };
        CidrCompilerSwitches filterOptions;
        try {
            filterOptions = cidrCompilerSwitches.filterOptions(biFunction);
            if (filterOptions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "filterOptions"));
            }
        }
        catch (NumberFormatException ex3) {
            throw c(ex3);
        }
        return filterOptions;
    }
    
    private static boolean a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "option", "com/jetbrains/cidr/toolchains/GCCCompiler", "isOptionSwitch"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        return s.startsWith("-");
    }
    
    static boolean collectSkipOptionsGcc(final List<String> list, final Set<String> set, final List<String> list2) {
        return CidrCompilerBase.collectOptionsToSkip(list, set, list2, GCCCompiler.BAD_SWITCH_FILTER_RULES);
    }
    
    @NotNull
    protected String preparePath(@NotNull final CidrToolEnvironment cidrToolEnvironment, @NotNull final String s) {
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/toolchains/GCCCompiler", "preparePath"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/toolchains/GCCCompiler", "preparePath"));
            }
        }
        catch (NumberFormatException ex2) {
            throw c(ex2);
        }
        String canonicalPath;
        try {
            canonicalPath = FileUtil.toCanonicalPath(cidrToolEnvironment.toLocalPath(this.myWorkingDirectory, s));
            if (canonicalPath == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "preparePath"));
            }
        }
        catch (NumberFormatException ex3) {
            throw c(ex3);
        }
        return canonicalPath;
    }
    
    public static String getClangTestMacros() {
        return "#define __has_feature(X) " + clangFeatureMacro("##X") + "\n#define __has_extension(X) " + clangExtensionMacro("##X") + "\n#define __has_attribute(X) " + clangAttributeMacro("##X") + "\n";
    }
    
    @NotNull
    public static String getGCCBuiltInFunctionMacros() {
        String s;
        try {
            s = "#define __builtin_va_start(list, paramN) ((void)(list = sizeof(paramN)))\n#define __builtin_va_arg(list, type) ((type)list)\n#define __builtin_va_end(list) ((void)list)\n#define __builtin_va_copy(dest, src) ((void)(dest = src))\n#define __builtin_offsetof(type, member) ((size_t)(&(((type *)0)->member)))\n#define __builtin_types_compatible_p(X,Y) 1\n#define __builtin_choose_expr(C,T,E) T\n";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "getGCCBuiltInFunctionMacros"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @NotNull
    public static String clangFeatureMacro(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "feature", "com/jetbrains/cidr/toolchains/GCCCompiler", "clangFeatureMacro"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        String string;
        try {
            string = "__CIDR_clang__has_feature_" + s;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "clangFeatureMacro"));
            }
        }
        catch (NumberFormatException ex2) {
            throw c(ex2);
        }
        return string;
    }
    
    @NotNull
    static String clangExtensionMacro(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "extension", "com/jetbrains/cidr/toolchains/GCCCompiler", "clangExtensionMacro"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        String string;
        try {
            string = "__CIDR_clang__has_extension_" + s;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "clangExtensionMacro"));
            }
        }
        catch (NumberFormatException ex2) {
            throw c(ex2);
        }
        return string;
    }
    
    @NotNull
    static String clangAttributeMacro(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "extension", "com/jetbrains/cidr/toolchains/GCCCompiler", "clangAttributeMacro"));
            }
        }
        catch (NumberFormatException ex) {
            throw c(ex);
        }
        String string;
        try {
            string = "__CIDR_clang__has_attribute_" + s;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler", "clangAttributeMacro"));
            }
        }
        catch (NumberFormatException ex2) {
            throw c(ex2);
        }
        return string;
    }
    
    static {
        QUERY_RESULT_PATTERN = Pattern.compile("^____CIDR_test_query_(\\w+)->([^=]+)=(\\d+)$");
        BAD_SWITCH_FILTER_RULES = new Pattern[] { Pattern.compile(".*error:.*?(-+\\S+) is not supported.*"), Pattern.compile(".*error:.*?[\u2018\u2019'\"](-.+?)[\u2018\u2019'\"].*"), Pattern.compile(".*error:\\s(.+):\\sNo such file or directory"), Pattern.compile(".*error: [\u2018\u2019'\"](.+?)[\u2018\u2019'\"].* not found") };
        INCLUDE_FILE_PATTERN = Pattern.compile("# 1 \"(.*)\" 1\\s*[\\r\\n]");
        DEPENDENCY_PATTERN = Pattern.compile(".*:(?:\\s|(?:\\\\[\\r\\n]))*((?:[\\S&&[^\\\\]]+(?:\\\\[ ]?)*)*)");
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    static class OutputSections
    {
        final String defines;
        final String preprocessed;
        final String featureChecks;
        
        public OutputSections(@NotNull final String defines, @NotNull final String preprocessed, @NotNull final String featureChecks) {
            if (defines == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defines", "com/jetbrains/cidr/toolchains/GCCCompiler$OutputSections", "<init>"));
            }
            if (preprocessed == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "preprocessed", "com/jetbrains/cidr/toolchains/GCCCompiler$OutputSections", "<init>"));
            }
            if (featureChecks == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "featureTests", "com/jetbrains/cidr/toolchains/GCCCompiler$OutputSections", "<init>"));
            }
            this.defines = defines;
            this.preprocessed = preprocessed;
            this.featureChecks = featureChecks;
        }
    }
}
