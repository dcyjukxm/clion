// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.openapi.diagnostic.Logger;
import java.util.Collections;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import java.io.File;
import com.intellij.openapi.vfs.CharsetToolkit;
import java.nio.charset.Charset;
import com.jetbrains.cidr.toolchains.CidrExecutableTool;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.util.regex.Pattern;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;

public class CPPEnvironment extends CidrToolEnvironment
{
    static final Pattern VERSION_PATTERN;
    @Nullable
    protected final CidrToolSet myToolSet;
    @NotNull
    protected final List<CidrToolSet.Option> myToolSetOptions;
    @Nullable
    private static Consumer<GeneralCommandLine> myAdditionalPreparationInTest;
    
    @Nullable
    public static CPPEnvironment create(@NotNull final EnvironmentProblems environmentProblems, @NotNull final List<CidrToolSet.Option> list) {
        try {
            if (environmentProblems == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problems", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toolSetOptions", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "create"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final ValidationResult validate = validate(CPPToolchains.getInstance(), environmentProblems, true);
        try {
            if (!environmentProblems.hasProblems()) {
                return new CPPEnvironment(validate.toolSet, list);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @NotNull
    protected static ValidationResult validate(@NotNull final CPPToolchains p0, @NotNull final EnvironmentProblems p1, final boolean p2) {
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
        //    18: ldc             "toolchains"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "validate"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "problems"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "validate"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aconst_null    
        //    89: astore_3       
        //    90: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //    93: ifeq            211
        //    96: aload_0        
        //    97: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.getToolSet:()Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   100: astore_3       
        //   101: aload_3        
        //   102: ifnonnull       142
        //   105: aload_1        
        //   106: new             Ljava/lang/StringBuilder;
        //   109: dup            
        //   110: invokespecial   java/lang/StringBuilder.<init>:()V
        //   113: ldc             "Environment ("
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.c:()Ljava/lang/String;
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: ldc             ") is not selected"
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   132: invokevirtual   com/jetbrains/cidr/toolchains/EnvironmentProblems.addProblem:(Ljava/lang/String;)V
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   141: athrow         
        //   142: aload_3        
        //   143: ifnull          211
        //   146: iload_2        
        //   147: ifeq            211
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   156: athrow         
        //   157: aload_3        
        //   158: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.readVersion:()Ljava/lang/String;
        //   161: ifnonnull       211
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   170: athrow         
        //   171: aload_1        
        //   172: new             Ljava/lang/StringBuilder;
        //   175: dup            
        //   176: invokespecial   java/lang/StringBuilder.<init>:()V
        //   179: aload_3        
        //   180: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.getName:()Ljava/lang/String;
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: ldc             " not found at: "
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: aload_3        
        //   192: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.getHome:()Ljava/io/File;
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   198: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   201: invokevirtual   com/jetbrains/cidr/toolchains/EnvironmentProblems.addProblem:(Ljava/lang/String;)V
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   210: athrow         
        //   211: new             Lcom/jetbrains/cidr/cpp/toolchains/CPPEnvironment$ValidationResult;
        //   214: dup            
        //   215: aload_3        
        //   216: invokespecial   com/jetbrains/cidr/cpp/toolchains/CPPEnvironment$ValidationResult.<init>:(Lcom/jetbrains/cidr/toolchains/CidrToolSet;)V
        //   219: dup            
        //   220: ifnonnull       257
        //   223: new             Ljava/lang/IllegalStateException;
        //   226: dup            
        //   227: ldc             "@NotNull method %s.%s must not return null"
        //   229: ldc             2
        //   231: anewarray       Ljava/lang/Object;
        //   234: dup            
        //   235: ldc             0
        //   237: ldc             "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment"
        //   239: aastore        
        //   240: dup            
        //   241: ldc             1
        //   243: ldc             "validate"
        //   245: aastore        
        //   246: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   249: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   252: athrow         
        //   253: invokestatic    com/jetbrains/cidr/cpp/toolchains/CPPEnvironment.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   256: athrow         
        //   257: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  101    135    138    142    Ljava/lang/IllegalArgumentException;
        //  142    150    153    157    Ljava/lang/IllegalArgumentException;
        //  146    164    167    171    Ljava/lang/IllegalArgumentException;
        //  157    204    207    211    Ljava/lang/IllegalArgumentException;
        //  211    253    253    257    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0157:
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
    private static String c() {
        String join;
        try {
            join = StringUtil.join((Collection)ContainerUtil.mapNotNull((Object[])CPPToolchains.WinEnvironment.values(), winEnvironment -> {
                Label_0020: {
                    try {
                        if (winEnvironment != CPPToolchains.WinEnvironment.MSVC) {
                            return winEnvironment.getDisplayName();
                        }
                        final boolean b = MSVC.isEnabled();
                        if (!b) {
                            break Label_0020;
                        }
                        return winEnvironment.getDisplayName();
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final boolean b = MSVC.isEnabled();
                        if (!b) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                return winEnvironment.getDisplayName();
            }), "/");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "allToolsetsString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return join;
    }
    
    protected static void checkTool(@Nullable final CidrExecutableTool cidrExecutableTool, @NotNull final String s, @NotNull final EnvironmentProblems environmentProblems) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toolName", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "checkTool"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (environmentProblems == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problems", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "checkTool"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (cidrExecutableTool == null) {
                environmentProblems.addProblem(s + " executable not specified");
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!cidrExecutableTool.getExecutable().exists()) {
                environmentProblems.addProblem(s + " executable not found");
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (cidrExecutableTool.readVersion() == null) {
                environmentProblems.addProblem(s + " executable is incorrect");
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
    }
    
    @NotNull
    public static Charset getCharset() {
        Charset charset = null;
        Label_0022: {
            try {
                if (CPPToolchains.getInstance().getCygwin() == null) {
                    final Charset charset2;
                    charset = (charset2 = Charset.defaultCharset());
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            Charset charset2;
            charset = (charset2 = CharsetToolkit.UTF8_CHARSET);
            try {
                if (charset2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "getCharset"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return charset;
    }
    
    @Override
    public String toLocalPath(@Nullable final File file, @Nullable final String s) {
        return Cygwin.toLocalPath(file, s, this.b());
    }
    
    @Override
    public String toEnvPath(@Nullable final String s) {
        return Cygwin.toCygwinPath(s, this.b());
    }
    
    @NotNull
    @Override
    public char[] getSupportedFileSeparators() {
        char[] array = null;
        Label_0024: {
            try {
                if (this.myToolSet != null) {
                    final char[] array2;
                    array = (array2 = this.myToolSet.getSupportedFileSeparators());
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            char[] array2;
            array = (array2 = DefaultCidrToolEnvironment.UNIX_PATH_SEPARATORS);
            try {
                if (array2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "getSupportedFileSeparators"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return array;
    }
    
    @Override
    public void prepare(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final PrepareFor prepareFor) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "prepare"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "prepare"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            super.prepare(generalCommandLine, prepareFor);
            if (this.myToolSet != null) {
                this.myToolSet.prepareEnvironment(generalCommandLine, prepareFor, this.myToolSetOptions);
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (CPPEnvironment.myAdditionalPreparationInTest != null) {
                CPPEnvironment.myAdditionalPreparationInTest.consume((Object)generalCommandLine);
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
    }
    
    public void convertPathVariableToEnv(@NotNull final GeneralCommandLine generalCommandLine) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/CPPEnvironment", "convertPathVariableToEnv"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Cygwin.convertPathVariableToCygwin(generalCommandLine, this.b());
    }
    
    @Nullable
    private Cygwin b() {
        try {
            if (this.myToolSet instanceof Cygwin) {
                return (Cygwin)this.myToolSet;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    private MinGW a() {
        try {
            if (this.myToolSet instanceof MinGW) {
                return (MinGW)this.myToolSet;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    public CPPEnvironment(@Nullable final CidrToolSet set, final List<CidrToolSet.Option> list) {
        this(set, list, false);
    }
    
    private CPPEnvironment(@Nullable final CidrToolSet myToolSet, final List<CidrToolSet.Option> list, final boolean b) {
        Label_0106: {
            if (!b) {
                Logger log = null;
                boolean b3 = false;
                Label_0081: {
                    Label_0065: {
                        Logger logger = null;
                        boolean b2 = false;
                        Label_0037: {
                            Label_0028: {
                                try {
                                    if (!SystemInfo.isWindows) {
                                        break Label_0065;
                                    }
                                    logger = CPPLog.LOG;
                                    final CidrToolSet set = myToolSet;
                                    if (set != null) {
                                        break Label_0028;
                                    }
                                    break Label_0028;
                                }
                                catch (IllegalArgumentException ex) {
                                    throw b(ex);
                                }
                                try {
                                    logger = CPPLog.LOG;
                                    final CidrToolSet set = myToolSet;
                                    if (set != null) {
                                        b2 = true;
                                        break Label_0037;
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw b(ex2);
                                }
                            }
                            b2 = false;
                        }
                        logger.assertTrue(b2, (Object)(c() + " should be specified"));
                        break Label_0106;
                        try {
                            log = CPPLog.LOG;
                            if (myToolSet == null) {
                                b3 = true;
                                break Label_0081;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    b3 = false;
                }
                log.assertTrue(b3, (Object)(c() + " should not be specified"));
            }
        }
        this.myToolSet = myToolSet;
        this.myToolSetOptions = Collections.unmodifiableList((List<? extends CidrToolSet.Option>)list);
    }
    
    public static CPPEnvironment createInTestMode(@Nullable final CidrToolSet set, final List<CidrToolSet.Option> list) {
        return new CPPEnvironment(set, list, true);
    }
    
    public boolean isMSVC() {
        Label_0024: {
            try {
                if (this.myToolSet == null) {
                    return false;
                }
                final CPPEnvironment cppEnvironment = this;
                final CidrToolSet set = cppEnvironment.myToolSet;
                final boolean b = set.isMSVC();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final CPPEnvironment cppEnvironment = this;
                final CidrToolSet set = cppEnvironment.myToolSet;
                final boolean b = set.isMSVC();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public boolean isCygwin() {
        Label_0024: {
            try {
                if (this.myToolSet == null) {
                    return false;
                }
                final CPPEnvironment cppEnvironment = this;
                final CidrToolSet set = cppEnvironment.myToolSet;
                final boolean b = set.isCygwin();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final CPPEnvironment cppEnvironment = this;
                final CidrToolSet set = cppEnvironment.myToolSet;
                final boolean b = set.isCygwin();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public boolean isMinGW() {
        Label_0024: {
            try {
                if (this.myToolSet == null) {
                    return false;
                }
                final CPPEnvironment cppEnvironment = this;
                final CidrToolSet set = cppEnvironment.myToolSet;
                final boolean b = set.isMinGW();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final CPPEnvironment cppEnvironment = this;
                final CidrToolSet set = cppEnvironment.myToolSet;
                final boolean b = set.isMinGW();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public static void setAdditionalPreparationInTest(@Nullable final Consumer<GeneralCommandLine> myAdditionalPreparationInTest) {
        CPPEnvironment.myAdditionalPreparationInTest = myAdditionalPreparationInTest;
    }
    
    @Nullable
    public CidrToolSet getToolSet() {
        return this.myToolSet;
    }
    
    static {
        VERSION_PATTERN = Pattern.compile("^[a-zA-Z() \\d]*([\\d]+\\.[\\d]+\\.?[\\d]*).*", 8);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    protected static class ValidationResult
    {
        @Nullable
        public final CidrToolSet toolSet;
        
        public ValidationResult(@Nullable final CidrToolSet toolSet) {
            this.toolSet = toolSet;
        }
    }
}
