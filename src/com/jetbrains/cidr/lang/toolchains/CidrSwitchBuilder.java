// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.toolchains;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.CommandLineUtil;
import java.util.Collections;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CidrSwitchBuilder
{
    private final List<String> myArgs;
    
    public CidrSwitchBuilder() {
        this.myArgs = new ArrayList<String>();
    }
    
    @NotNull
    public CidrSwitchBuilder addSingleRaw(@Nullable final String s) {
        try {
            if (s != null) {
                this.myArgs.add(s);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "addSingleRaw"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    @NotNull
    public CidrSwitchBuilder addAllRaw(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rawArgs", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "addAllRaw"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            this.myArgs.addAll(list);
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "addAllRaw"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    public CidrSwitchBuilder parseAndAdd(@Nullable final String s, @NotNull final CidrCompilerSwitches.Format format) {
        try {
            if (format == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "format", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "parseAndAdd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s != null) {
                this.myArgs.addAll(parseArgs(s, format));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    @NotNull
    public CidrSwitchBuilder addAll(@Nullable final CidrCompilerSwitches cidrCompilerSwitches) {
        try {
            if (cidrCompilerSwitches != null) {
                this.myArgs.addAll(cidrCompilerSwitches.getList(CidrCompilerSwitches.Format.RAW));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "addAll"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    @NotNull
    public List<String> getArgs() {
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends String>)this.myArgs);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "getArgs"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<String>)unmodifiableList;
    }
    
    @NotNull
    public CidrCompilerSwitches build() {
        CidrCompilerSwitches cidrCompilerSwitches;
        try {
            cidrCompilerSwitches = new CidrCompilerSwitches(new ArrayList<String>(this.myArgs));
            if (cidrCompilerSwitches == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "build"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cidrCompilerSwitches;
    }
    
    @NotNull
    public static List<String> parseArgs(@NotNull final String p0, @NotNull final CidrCompilerSwitches.Format p1) {
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
        //    18: ldc             "options"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "parseArgs"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "format"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "parseArgs"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.RAW:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    92: if_acmpne       109
        //    95: new             Ljava/lang/IllegalArgumentException;
        //    98: dup            
        //    99: ldc             "RAW format cannot be correctly parsed into multiple arguments, you probably meant something else"
        //   101: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   104: athrow         
        //   105: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_1        
        //   110: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GNU_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   113: if_acmpeq       144
        //   116: aload_1        
        //   117: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MINGW_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   120: if_acmpeq       144
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_1        
        //   131: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.NMAKE_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   134: if_acmpne       150
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: aload_0        
        //   145: aload_1        
        //   146: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.b:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/lang/String;
        //   149: astore_0       
        //   150: aload_1        
        //   151: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.BASH_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   154: if_acmpeq       199
        //   157: aload_1        
        //   158: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   161: if_acmpeq       199
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aload_1        
        //   172: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GNU_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   175: if_acmpeq       199
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload_1        
        //   186: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MINGW_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   189: if_acmpne       250
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload_0        
        //   200: aload_1        
        //   201: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.parseGNU:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   204: dup            
        //   205: ifnonnull       249
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: new             Ljava/lang/IllegalStateException;
        //   218: dup            
        //   219: ldc             "@NotNull method %s.%s must not return null"
        //   221: ldc             2
        //   223: anewarray       Ljava/lang/Object;
        //   226: dup            
        //   227: ldc             0
        //   229: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   231: aastore        
        //   232: dup            
        //   233: ldc             1
        //   235: ldc             "parseArgs"
        //   237: aastore        
        //   238: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   241: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   244: athrow         
        //   245: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: areturn        
        //   250: aload_1        
        //   251: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.WINDOWS_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   254: if_acmpeq       285
        //   257: aload_1        
        //   258: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MSVC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   261: if_acmpeq       285
        //   264: goto            271
        //   267: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: aload_1        
        //   272: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.NMAKE_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   275: if_acmpne       336
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_0        
        //   286: aload_1        
        //   287: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.d:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   290: dup            
        //   291: ifnonnull       335
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: new             Ljava/lang/IllegalStateException;
        //   304: dup            
        //   305: ldc             "@NotNull method %s.%s must not return null"
        //   307: ldc             2
        //   309: anewarray       Ljava/lang/Object;
        //   312: dup            
        //   313: ldc             0
        //   315: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   317: aastore        
        //   318: dup            
        //   319: ldc             1
        //   321: ldc             "parseArgs"
        //   323: aastore        
        //   324: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   327: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   330: athrow         
        //   331: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: areturn        
        //   336: new             Ljava/lang/IllegalArgumentException;
        //   339: dup            
        //   340: new             Ljava/lang/StringBuilder;
        //   343: dup            
        //   344: invokespecial   java/lang/StringBuilder.<init>:()V
        //   347: aload_1        
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   351: ldc             " is not supported yet"
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   359: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   362: athrow         
        //    Signature:
        //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     105    105    109    Ljava/lang/IllegalArgumentException;
        //  109    123    126    130    Ljava/lang/IllegalArgumentException;
        //  116    137    140    144    Ljava/lang/IllegalArgumentException;
        //  150    164    167    171    Ljava/lang/IllegalArgumentException;
        //  157    178    181    185    Ljava/lang/IllegalArgumentException;
        //  171    192    195    199    Ljava/lang/IllegalArgumentException;
        //  185    208    211    215    Ljava/lang/IllegalArgumentException;
        //  199    245    245    249    Ljava/lang/IllegalArgumentException;
        //  250    264    267    271    Ljava/lang/IllegalArgumentException;
        //  257    278    281    285    Ljava/lang/IllegalArgumentException;
        //  271    294    297    301    Ljava/lang/IllegalArgumentException;
        //  285    331    331    335    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0171:
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
    public static String convert(@NotNull final String p0, @NotNull final CidrCompilerSwitches.Format p1) {
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
        //    18: ldc             "arg"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "convert"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "format"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "convert"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.RAW:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    92: if_acmpne       142
        //    95: aload_0        
        //    96: dup            
        //    97: ifnonnull       141
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: new             Ljava/lang/IllegalStateException;
        //   110: dup            
        //   111: ldc             "@NotNull method %s.%s must not return null"
        //   113: ldc             2
        //   115: anewarray       Ljava/lang/Object;
        //   118: dup            
        //   119: ldc             0
        //   121: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   123: aastore        
        //   124: dup            
        //   125: ldc             1
        //   127: ldc             "convert"
        //   129: aastore        
        //   130: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   133: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   136: athrow         
        //   137: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: areturn        
        //   142: aload_1        
        //   143: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.BASH_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   146: if_acmpeq       163
        //   149: aload_1        
        //   150: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   153: if_acmpne       214
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: aload_1        
        //   165: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/lang/String;
        //   168: dup            
        //   169: ifnonnull       213
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: new             Ljava/lang/IllegalStateException;
        //   182: dup            
        //   183: ldc             "@NotNull method %s.%s must not return null"
        //   185: ldc             2
        //   187: anewarray       Ljava/lang/Object;
        //   190: dup            
        //   191: ldc             0
        //   193: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   195: aastore        
        //   196: dup            
        //   197: ldc             1
        //   199: ldc             "convert"
        //   201: aastore        
        //   202: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   205: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   208: athrow         
        //   209: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: areturn        
        //   214: aload_1        
        //   215: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.WINDOWS_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   218: if_acmpeq       235
        //   221: aload_1        
        //   222: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MSVC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   225: if_acmpne       286
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_0        
        //   236: aload_1        
        //   237: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.c:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/lang/String;
        //   240: dup            
        //   241: ifnonnull       285
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: new             Ljava/lang/IllegalStateException;
        //   254: dup            
        //   255: ldc             "@NotNull method %s.%s must not return null"
        //   257: ldc             2
        //   259: anewarray       Ljava/lang/Object;
        //   262: dup            
        //   263: ldc             0
        //   265: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   267: aastore        
        //   268: dup            
        //   269: ldc             1
        //   271: ldc             "convert"
        //   273: aastore        
        //   274: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   277: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   280: athrow         
        //   281: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: areturn        
        //   286: new             Ljava/lang/IllegalArgumentException;
        //   289: dup            
        //   290: new             Ljava/lang/StringBuilder;
        //   293: dup            
        //   294: invokespecial   java/lang/StringBuilder.<init>:()V
        //   297: aload_1        
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   301: ldc             " is not supported yet"
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   309: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   312: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     100    103    107    Ljava/lang/IllegalArgumentException;
        //  95     137    137    141    Ljava/lang/IllegalArgumentException;
        //  142    156    159    163    Ljava/lang/IllegalArgumentException;
        //  149    172    175    179    Ljava/lang/IllegalArgumentException;
        //  163    209    209    213    Ljava/lang/IllegalArgumentException;
        //  214    228    231    235    Ljava/lang/IllegalArgumentException;
        //  221    244    247    251    Ljava/lang/IllegalArgumentException;
        //  235    281    281    285    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
    private static String b(@NotNull final String p0, @NotNull final CidrCompilerSwitches.Format p1) {
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
        //    18: ldc             "options"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "preprocessForMakefiles"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "format"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "preprocessForMakefiles"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: ldc             "$#"
        //    91: invokestatic    com/intellij/openapi/util/text/StringUtil.indexOfAny:(Ljava/lang/String;Ljava/lang/String;)I
        //    94: iconst_m1      
        //    95: if_icmpne       145
        //    98: aload_0        
        //    99: dup            
        //   100: ifnonnull       144
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: new             Ljava/lang/IllegalStateException;
        //   113: dup            
        //   114: ldc             "@NotNull method %s.%s must not return null"
        //   116: ldc             2
        //   118: anewarray       Ljava/lang/Object;
        //   121: dup            
        //   122: ldc             0
        //   124: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   126: aastore        
        //   127: dup            
        //   128: ldc             1
        //   130: ldc             "preprocessForMakefiles"
        //   132: aastore        
        //   133: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   136: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   139: athrow         
        //   140: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: areturn        
        //   145: aload_0        
        //   146: invokevirtual   java/lang/String.length:()I
        //   149: istore_2       
        //   150: new             Ljava/lang/StringBuilder;
        //   153: dup            
        //   154: iload_2        
        //   155: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   158: astore_3       
        //   159: aload_1        
        //   160: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.NMAKE_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   163: if_acmpne       175
        //   166: bipush          94
        //   168: goto            177
        //   171: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: bipush          92
        //   177: istore          4
        //   179: iconst_0       
        //   180: istore          5
        //   182: iload           5
        //   184: iload_2        
        //   185: if_icmpge       275
        //   188: aload_0        
        //   189: iload           5
        //   191: invokevirtual   java/lang/String.charAt:(I)C
        //   194: istore          6
        //   196: iload           6
        //   198: iload           4
        //   200: if_icmpne       248
        //   203: iload           5
        //   205: iload_2        
        //   206: iconst_1       
        //   207: isub           
        //   208: if_icmpge       248
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: aload_0        
        //   219: iload           5
        //   221: iconst_1       
        //   222: iadd           
        //   223: invokevirtual   java/lang/String.charAt:(I)C
        //   226: bipush          35
        //   228: if_icmpne       248
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: iinc            5, 1
        //   241: bipush          35
        //   243: istore          6
        //   245: goto            262
        //   248: iload           6
        //   250: bipush          35
        //   252: if_icmpne       262
        //   255: goto            275
        //   258: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: aload_3        
        //   263: iload           6
        //   265: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   268: pop            
        //   269: iinc            5, 1
        //   272: goto            182
        //   275: aload_3        
        //   276: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   279: astore_0       
        //   280: aload_0        
        //   281: invokevirtual   java/lang/String.length:()I
        //   284: istore_2       
        //   285: aload_3        
        //   286: iconst_0       
        //   287: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   290: iconst_0       
        //   291: istore          5
        //   293: iload           5
        //   295: iload_2        
        //   296: if_icmpge       458
        //   299: aload_0        
        //   300: iload           5
        //   302: invokevirtual   java/lang/String.charAt:(I)C
        //   305: istore          6
        //   307: iload           6
        //   309: bipush          36
        //   311: if_icmpne       445
        //   314: iload           5
        //   316: iload_2        
        //   317: iconst_1       
        //   318: isub           
        //   319: if_icmpne       336
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: goto            452
        //   332: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   335: athrow         
        //   336: aload_0        
        //   337: iinc            5, 1
        //   340: iload           5
        //   342: invokevirtual   java/lang/String.charAt:(I)C
        //   345: istore          6
        //   347: iload           6
        //   349: bipush          40
        //   351: if_icmpeq       368
        //   354: iload           6
        //   356: bipush          123
        //   358: if_icmpne       431
        //   361: goto            368
        //   364: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: iload           6
        //   370: bipush          40
        //   372: if_icmpne       391
        //   375: goto            382
        //   378: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: bipush          41
        //   384: goto            393
        //   387: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: bipush          125
        //   393: istore          7
        //   395: iload           5
        //   397: iload_2        
        //   398: iconst_1       
        //   399: isub           
        //   400: if_icmpge       452
        //   403: iload           6
        //   405: iload           7
        //   407: if_icmpeq       452
        //   410: goto            417
        //   413: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: aload_0        
        //   418: iinc            5, 1
        //   421: iload           5
        //   423: invokevirtual   java/lang/String.charAt:(I)C
        //   426: istore          6
        //   428: goto            395
        //   431: iload           6
        //   433: bipush          36
        //   435: if_icmpeq       445
        //   438: goto            452
        //   441: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   444: athrow         
        //   445: aload_3        
        //   446: iload           6
        //   448: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   451: pop            
        //   452: iinc            5, 1
        //   455: goto            293
        //   458: aload_3        
        //   459: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   462: dup            
        //   463: ifnonnull       500
        //   466: new             Ljava/lang/IllegalStateException;
        //   469: dup            
        //   470: ldc             "@NotNull method %s.%s must not return null"
        //   472: ldc             2
        //   474: anewarray       Ljava/lang/Object;
        //   477: dup            
        //   478: ldc             0
        //   480: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   482: aastore        
        //   483: dup            
        //   484: ldc             1
        //   486: ldc             "preprocessForMakefiles"
        //   488: aastore        
        //   489: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   492: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   495: athrow         
        //   496: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   499: athrow         
        //   500: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     103    106    110    Ljava/lang/IllegalArgumentException;
        //  98     140    140    144    Ljava/lang/IllegalArgumentException;
        //  159    171    171    175    Ljava/lang/IllegalArgumentException;
        //  196    211    214    218    Ljava/lang/IllegalArgumentException;
        //  203    231    234    238    Ljava/lang/IllegalArgumentException;
        //  248    258    258    262    Ljava/lang/IllegalArgumentException;
        //  307    322    325    329    Ljava/lang/IllegalArgumentException;
        //  314    332    332    336    Ljava/lang/IllegalArgumentException;
        //  347    361    364    368    Ljava/lang/IllegalArgumentException;
        //  354    375    378    382    Ljava/lang/IllegalArgumentException;
        //  368    387    387    391    Ljava/lang/IllegalArgumentException;
        //  395    410    413    417    Ljava/lang/IllegalArgumentException;
        //  431    441    441    445    Ljava/lang/IllegalArgumentException;
        //  458    496    496    500    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0368:
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
    
    public static List<String> parseGNU(@NotNull final String p0, @NotNull final CidrCompilerSwitches.Format p1) {
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
        //    18: ldc             "options"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "parseGNU"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "format"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "parseGNU"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.BASH_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    92: if_acmpeq       123
        //    95: aload_1        
        //    96: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GNU_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    99: if_acmpeq       123
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_1        
        //   110: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MINGW_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   113: if_acmpne       131
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: iconst_1       
        //   124: goto            132
        //   127: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iconst_0       
        //   132: istore_2       
        //   133: new             Ljava/util/ArrayList;
        //   136: dup            
        //   137: invokespecial   java/util/ArrayList.<init>:()V
        //   140: astore_3       
        //   141: new             Ljava/lang/StringBuilder;
        //   144: dup            
        //   145: invokespecial   java/lang/StringBuilder.<init>:()V
        //   148: astore          4
        //   150: iconst_1       
        //   151: newarray        Z
        //   153: dup            
        //   154: iconst_0       
        //   155: iconst_0       
        //   156: bastore        
        //   157: astore          5
        //   159: aload           4
        //   161: aload           5
        //   163: aload_3        
        //   164: invokedynamic   run:(Ljava/lang/StringBuilder;[ZLjava/util/List;)Ljava/lang/Runnable;
        //   169: astore          6
        //   171: iconst_0       
        //   172: istore          7
        //   174: aload_0        
        //   175: invokevirtual   java/lang/String.length:()I
        //   178: istore          8
        //   180: iconst_0       
        //   181: istore          9
        //   183: iload           9
        //   185: iload           8
        //   187: if_icmpge       813
        //   190: aload_0        
        //   191: iload           9
        //   193: invokevirtual   java/lang/String.charAt:(I)C
        //   196: istore          10
        //   198: iload_2        
        //   199: ifeq            359
        //   202: iload           10
        //   204: bipush          36
        //   206: if_icmpne       359
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: iload           9
        //   218: iload           8
        //   220: iconst_1       
        //   221: isub           
        //   222: if_icmpge       359
        //   225: goto            232
        //   228: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload_0        
        //   233: iload           9
        //   235: iconst_1       
        //   236: iadd           
        //   237: invokevirtual   java/lang/String.charAt:(I)C
        //   240: bipush          39
        //   242: if_icmpne       359
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: new             Ljava/lang/StringBuilder;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuilder.<init>:()V
        //   259: astore          11
        //   261: iload           9
        //   263: iconst_1       
        //   264: iadd           
        //   265: istore          12
        //   267: iinc            12, 1
        //   270: iload           12
        //   272: iload           8
        //   274: if_icmpge       333
        //   277: aload_0        
        //   278: iload           12
        //   280: invokevirtual   java/lang/String.charAt:(I)C
        //   283: istore          13
        //   285: iload           13
        //   287: bipush          39
        //   289: if_icmpne       319
        //   292: aload_0        
        //   293: iload           12
        //   295: iconst_1       
        //   296: isub           
        //   297: invokevirtual   java/lang/String.charAt:(I)C
        //   300: bipush          92
        //   302: if_icmpeq       319
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: goto            333
        //   315: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aload           11
        //   321: iload           13
        //   323: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   326: pop            
        //   327: iinc            12, 1
        //   330: goto            270
        //   333: aload           4
        //   335: aload           11
        //   337: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   340: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.unescapeAnsiStringCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: pop            
        //   347: aload           5
        //   349: iconst_0       
        //   350: iconst_1       
        //   351: bastore        
        //   352: iload           12
        //   354: istore          9
        //   356: goto            807
        //   359: iload           10
        //   361: bipush          92
        //   363: if_icmpne       692
        //   366: iload_2        
        //   367: ifeq            391
        //   370: goto            377
        //   373: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   376: athrow         
        //   377: iload           7
        //   379: bipush          39
        //   381: if_icmpeq       692
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: iload           9
        //   393: iload           8
        //   395: iconst_1       
        //   396: isub           
        //   397: if_icmpne       414
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: goto            807
        //   410: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: aload_0        
        //   415: iinc            9, 1
        //   418: iload           9
        //   420: invokevirtual   java/lang/String.charAt:(I)C
        //   423: istore          10
        //   425: iload           10
        //   427: bipush          10
        //   429: if_icmpne       476
        //   432: iload_2        
        //   433: ifne            807
        //   436: goto            443
        //   439: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   442: athrow         
        //   443: aload_1        
        //   444: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   447: if_acmpne       476
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: iload           7
        //   459: ifne            476
        //   462: goto            469
        //   465: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   468: athrow         
        //   469: goto            807
        //   472: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   475: athrow         
        //   476: aload_1        
        //   477: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   480: if_acmpne       489
        //   483: iconst_1       
        //   484: istore          11
        //   486: goto            669
        //   489: aload_1        
        //   490: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MINGW_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   493: if_acmpne       624
        //   496: ldc             "\"$`\n"
        //   498: iload           10
        //   500: invokevirtual   java/lang/String.indexOf:(I)I
        //   503: iconst_m1      
        //   504: if_icmpne       610
        //   507: goto            514
        //   510: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   513: athrow         
        //   514: iload           7
        //   516: ifne            544
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: ldc             " \\'{}[]()<>#|^&*;?`"
        //   528: iload           10
        //   530: invokevirtual   java/lang/String.indexOf:(I)I
        //   533: iconst_m1      
        //   534: if_icmpne       610
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   543: athrow         
        //   544: iload           7
        //   546: ifeq            618
        //   549: goto            556
        //   552: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   555: athrow         
        //   556: iload           10
        //   558: bipush          92
        //   560: if_icmpne       618
        //   563: goto            570
        //   566: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   569: athrow         
        //   570: iload           9
        //   572: iload           8
        //   574: iconst_1       
        //   575: isub           
        //   576: if_icmpge       618
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: ldc             "\"\n"
        //   588: aload_0        
        //   589: iload           9
        //   591: iconst_1       
        //   592: iadd           
        //   593: invokevirtual   java/lang/String.charAt:(I)C
        //   596: invokevirtual   java/lang/String.indexOf:(I)I
        //   599: iconst_m1      
        //   600: if_icmpeq       618
        //   603: goto            610
        //   606: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   609: athrow         
        //   610: iconst_1       
        //   611: goto            619
        //   614: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   617: athrow         
        //   618: iconst_0       
        //   619: istore          11
        //   621: goto            669
        //   624: iload           7
        //   626: ifeq            658
        //   629: iload_2        
        //   630: ifeq            666
        //   633: goto            640
        //   636: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   639: athrow         
        //   640: ldc             "\\\"$`\n"
        //   642: iload           10
        //   644: invokevirtual   java/lang/String.indexOf:(I)I
        //   647: iconst_m1      
        //   648: if_icmpeq       666
        //   651: goto            658
        //   654: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: iconst_1       
        //   659: goto            667
        //   662: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   665: athrow         
        //   666: iconst_0       
        //   667: istore          11
        //   669: iload           11
        //   671: ifne            689
        //   674: aload           4
        //   676: bipush          92
        //   678: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   681: pop            
        //   682: goto            689
        //   685: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   688: athrow         
        //   689: goto            799
        //   692: iload           7
        //   694: ifeq            717
        //   697: iload           10
        //   699: iload           7
        //   701: if_icmpne       717
        //   704: goto            711
        //   707: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   710: athrow         
        //   711: iconst_0       
        //   712: istore          7
        //   714: goto            807
        //   717: iload           7
        //   719: ifne            762
        //   722: iload           10
        //   724: bipush          34
        //   726: if_icmpeq       750
        //   729: goto            736
        //   732: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   735: athrow         
        //   736: iload           10
        //   738: bipush          39
        //   740: if_icmpne       762
        //   743: goto            750
        //   746: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   749: athrow         
        //   750: iload           10
        //   752: istore          7
        //   754: aload           5
        //   756: iconst_0       
        //   757: iconst_1       
        //   758: bastore        
        //   759: goto            807
        //   762: iload           7
        //   764: ifne            799
        //   767: ldc             " \t\n\r\f"
        //   769: iload           10
        //   771: invokevirtual   java/lang/String.indexOf:(I)I
        //   774: iconst_m1      
        //   775: if_icmpeq       799
        //   778: goto            785
        //   781: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   784: athrow         
        //   785: aload           6
        //   787: invokeinterface java/lang/Runnable.run:()V
        //   792: goto            807
        //   795: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   798: athrow         
        //   799: aload           4
        //   801: iload           10
        //   803: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   806: pop            
        //   807: iinc            9, 1
        //   810: goto            183
        //   813: aload           6
        //   815: invokeinterface java/lang/Runnable.run:()V
        //   820: aload_3        
        //   821: areturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    105    109    Ljava/lang/IllegalArgumentException;
        //  95     116    119    123    Ljava/lang/IllegalArgumentException;
        //  109    127    127    131    Ljava/lang/IllegalArgumentException;
        //  198    209    212    216    Ljava/lang/IllegalArgumentException;
        //  202    225    228    232    Ljava/lang/IllegalArgumentException;
        //  216    245    248    252    Ljava/lang/IllegalArgumentException;
        //  285    305    308    312    Ljava/lang/IllegalArgumentException;
        //  292    315    315    319    Ljava/lang/IllegalArgumentException;
        //  359    370    373    377    Ljava/lang/IllegalArgumentException;
        //  366    384    387    391    Ljava/lang/IllegalArgumentException;
        //  377    400    403    407    Ljava/lang/IllegalArgumentException;
        //  391    410    410    414    Ljava/lang/IllegalArgumentException;
        //  425    436    439    443    Ljava/lang/IllegalArgumentException;
        //  432    450    453    457    Ljava/lang/IllegalArgumentException;
        //  443    462    465    469    Ljava/lang/IllegalArgumentException;
        //  457    472    472    476    Ljava/lang/IllegalArgumentException;
        //  489    507    510    514    Ljava/lang/IllegalArgumentException;
        //  496    519    522    526    Ljava/lang/IllegalArgumentException;
        //  514    537    540    544    Ljava/lang/IllegalArgumentException;
        //  526    549    552    556    Ljava/lang/IllegalArgumentException;
        //  544    563    566    570    Ljava/lang/IllegalArgumentException;
        //  556    579    582    586    Ljava/lang/IllegalArgumentException;
        //  570    603    606    610    Ljava/lang/IllegalArgumentException;
        //  586    614    614    618    Ljava/lang/IllegalArgumentException;
        //  624    633    636    640    Ljava/lang/IllegalArgumentException;
        //  629    651    654    658    Ljava/lang/IllegalArgumentException;
        //  640    662    662    666    Ljava/lang/IllegalArgumentException;
        //  669    682    685    689    Ljava/lang/IllegalArgumentException;
        //  692    704    707    711    Ljava/lang/IllegalArgumentException;
        //  717    729    732    736    Ljava/lang/IllegalArgumentException;
        //  722    743    746    750    Ljava/lang/IllegalArgumentException;
        //  762    778    781    785    Ljava/lang/IllegalArgumentException;
        //  767    795    795    799    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    private static String a(final String p0, final CidrCompilerSwitches.Format p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   java/lang/String.length:()I
        //     4: ifne            55
        //     7: ldc             "\"\""
        //     9: dup            
        //    10: ifnonnull       54
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: new             Ljava/lang/IllegalStateException;
        //    23: dup            
        //    24: ldc             "@NotNull method %s.%s must not return null"
        //    26: ldc             2
        //    28: anewarray       Ljava/lang/Object;
        //    31: dup            
        //    32: ldc             0
        //    34: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    36: aastore        
        //    37: dup            
        //    38: ldc             1
        //    40: ldc             "convertGNU"
        //    42: aastore        
        //    43: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    46: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    49: athrow         
        //    50: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: areturn        
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: astore_2       
        //    63: aload_0        
        //    64: invokevirtual   java/lang/String.length:()I
        //    67: istore_3       
        //    68: iconst_0       
        //    69: istore          4
        //    71: iload           4
        //    73: iload_3        
        //    74: if_icmpge       183
        //    77: aload_0        
        //    78: iload           4
        //    80: invokevirtual   java/lang/String.charAt:(I)C
        //    83: istore          5
        //    85: aload_1        
        //    86: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.BASH_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    89: if_acmpeq       106
        //    92: aload_1        
        //    93: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    96: if_acmpne       124
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: ldc             "\"' \\\t\n\r\f"
        //   108: iload           5
        //   110: invokevirtual   java/lang/String.indexOf:(I)I
        //   113: iconst_m1      
        //   114: if_icmpne       156
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: aload_1        
        //   125: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.BASH_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   128: if_acmpne       170
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: ldc             "|&;<>()$`"
        //   140: iload           5
        //   142: invokevirtual   java/lang/String.indexOf:(I)I
        //   145: iconst_m1      
        //   146: if_icmpeq       170
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_2        
        //   157: bipush          92
        //   159: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   162: pop            
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_2        
        //   171: iload           5
        //   173: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   176: pop            
        //   177: iinc            4, 1
        //   180: goto            71
        //   183: aload_2        
        //   184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   187: dup            
        //   188: ifnonnull       225
        //   191: new             Ljava/lang/IllegalStateException;
        //   194: dup            
        //   195: ldc             "@NotNull method %s.%s must not return null"
        //   197: ldc             2
        //   199: anewarray       Ljava/lang/Object;
        //   202: dup            
        //   203: ldc             0
        //   205: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //   207: aastore        
        //   208: dup            
        //   209: ldc             1
        //   211: ldc             "convertGNU"
        //   213: aastore        
        //   214: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   217: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   220: athrow         
        //   221: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  7      50     50     54     Ljava/lang/IllegalArgumentException;
        //  85     99     102    106    Ljava/lang/IllegalArgumentException;
        //  92     117    120    124    Ljava/lang/IllegalArgumentException;
        //  106    131    134    138    Ljava/lang/IllegalArgumentException;
        //  124    149    152    156    Ljava/lang/IllegalArgumentException;
        //  138    163    166    170    Ljava/lang/IllegalArgumentException;
        //  183    221    221    225    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0106:
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
    
    private static List<String> d(@NotNull final String p0, @NotNull final CidrCompilerSwitches.Format p1) {
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
        //    18: ldc             "options"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "parseWindows"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "format"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "parseWindows"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.WINDOWS_SHELL:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    92: if_acmpeq       109
        //    95: aload_1        
        //    96: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.NMAKE_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    99: if_acmpne       117
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: iconst_1       
        //   110: goto            118
        //   113: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: iconst_0       
        //   118: istore_2       
        //   119: new             Ljava/util/ArrayList;
        //   122: dup            
        //   123: invokespecial   java/util/ArrayList.<init>:()V
        //   126: astore_3       
        //   127: new             Ljava/lang/StringBuilder;
        //   130: dup            
        //   131: invokespecial   java/lang/StringBuilder.<init>:()V
        //   134: astore          4
        //   136: iconst_1       
        //   137: newarray        Z
        //   139: dup            
        //   140: iconst_0       
        //   141: iconst_0       
        //   142: bastore        
        //   143: astore          5
        //   145: aload           4
        //   147: aload           5
        //   149: aload_3        
        //   150: invokedynamic   run:(Ljava/lang/StringBuilder;[ZLjava/util/List;)Ljava/lang/Runnable;
        //   155: astore          6
        //   157: iconst_0       
        //   158: istore          7
        //   160: iconst_0       
        //   161: istore          8
        //   163: aload_0        
        //   164: invokevirtual   java/lang/String.length:()I
        //   167: istore          9
        //   169: iconst_0       
        //   170: istore          10
        //   172: iload           10
        //   174: iload           9
        //   176: if_icmpge       776
        //   179: aload_0        
        //   180: iload           10
        //   182: invokevirtual   java/lang/String.charAt:(I)C
        //   185: istore          11
        //   187: iload           8
        //   189: istore          12
        //   191: iconst_0       
        //   192: istore          8
        //   194: iload           11
        //   196: bipush          92
        //   198: if_icmpne       334
        //   201: iconst_0       
        //   202: istore          13
        //   204: iload           10
        //   206: iload           9
        //   208: iconst_1       
        //   209: isub           
        //   210: if_icmpge       244
        //   213: iload           11
        //   215: bipush          92
        //   217: if_icmpne       244
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: iinc            13, 1
        //   230: aload_0        
        //   231: iinc            10, 1
        //   234: iload           10
        //   236: invokevirtual   java/lang/String.charAt:(I)C
        //   239: istore          11
        //   241: goto            204
        //   244: iload           11
        //   246: bipush          34
        //   248: if_icmpne       318
        //   251: iload           13
        //   253: iconst_2       
        //   254: irem           
        //   255: iconst_1       
        //   256: if_icmpne       300
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload           4
        //   268: bipush          92
        //   270: iload           13
        //   272: iconst_1       
        //   273: iadd           
        //   274: iconst_2       
        //   275: idiv           
        //   276: iconst_1       
        //   277: isub           
        //   278: invokestatic    com/intellij/openapi/util/text/StringUtil.repeatSymbol:(CI)Ljava/lang/String;
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: pop            
        //   285: aload           4
        //   287: bipush          34
        //   289: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   292: pop            
        //   293: goto            770
        //   296: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   299: athrow         
        //   300: aload           4
        //   302: bipush          92
        //   304: iload           13
        //   306: iconst_2       
        //   307: idiv           
        //   308: invokestatic    com/intellij/openapi/util/text/StringUtil.repeatSymbol:(CI)Ljava/lang/String;
        //   311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: pop            
        //   315: goto            331
        //   318: aload           4
        //   320: bipush          92
        //   322: iload           13
        //   324: invokestatic    com/intellij/openapi/util/text/StringUtil.repeatSymbol:(CI)Ljava/lang/String;
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: pop            
        //   331: iconst_0       
        //   332: istore          12
        //   334: iload           11
        //   336: bipush          34
        //   338: if_icmpne       489
        //   341: iload           7
        //   343: ifeq            427
        //   346: goto            353
        //   349: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   352: athrow         
        //   353: aload_1        
        //   354: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MSVC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   357: if_acmpne       421
        //   360: goto            367
        //   363: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   366: athrow         
        //   367: iload           10
        //   369: iload           9
        //   371: iconst_1       
        //   372: isub           
        //   373: if_icmpge       421
        //   376: goto            383
        //   379: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   382: athrow         
        //   383: aload_0        
        //   384: iload           10
        //   386: iconst_1       
        //   387: iadd           
        //   388: invokevirtual   java/lang/String.charAt:(I)C
        //   391: bipush          34
        //   393: if_icmpne       421
        //   396: goto            403
        //   399: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   402: athrow         
        //   403: iinc            10, 1
        //   406: aload           4
        //   408: iload           11
        //   410: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   413: pop            
        //   414: goto            421
        //   417: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   420: athrow         
        //   421: iconst_0       
        //   422: istore          7
        //   424: goto            770
        //   427: iload_2        
        //   428: ifeq            478
        //   431: iload           10
        //   433: ifle            478
        //   436: goto            443
        //   439: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   442: athrow         
        //   443: aload_0        
        //   444: iload           10
        //   446: iconst_1       
        //   447: isub           
        //   448: invokevirtual   java/lang/String.charAt:(I)C
        //   451: bipush          34
        //   453: if_icmpne       478
        //   456: goto            463
        //   459: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   462: athrow         
        //   463: aload           4
        //   465: iload           11
        //   467: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   470: pop            
        //   471: goto            478
        //   474: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: iconst_1       
        //   479: istore          7
        //   481: aload           5
        //   483: iconst_0       
        //   484: iconst_1       
        //   485: bastore        
        //   486: goto            770
        //   489: iload_2        
        //   490: ifeq            537
        //   493: iload           7
        //   495: ifne            537
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   504: athrow         
        //   505: iload           11
        //   507: bipush          94
        //   509: if_icmpne       537
        //   512: goto            519
        //   515: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   518: athrow         
        //   519: iload           12
        //   521: ifne            537
        //   524: goto            531
        //   527: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   530: athrow         
        //   531: iconst_1       
        //   532: istore          8
        //   534: goto            770
        //   537: iload           11
        //   539: bipush          10
        //   541: if_icmpeq       558
        //   544: iload           11
        //   546: bipush          13
        //   548: if_icmpne       725
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: aload_1        
        //   559: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.NMAKE_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   562: if_acmpne       592
        //   565: goto            572
        //   568: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: iload           12
        //   574: ifeq            592
        //   577: goto            584
        //   580: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   583: athrow         
        //   584: iconst_1       
        //   585: goto            593
        //   588: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   591: athrow         
        //   592: iconst_0       
        //   593: istore          13
        //   595: iload           13
        //   597: ifeq            615
        //   600: aload           4
        //   602: iload           11
        //   604: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   607: pop            
        //   608: goto            615
        //   611: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   614: athrow         
        //   615: iload           11
        //   617: bipush          13
        //   619: if_icmpne       692
        //   622: iload           10
        //   624: iload           9
        //   626: iconst_1       
        //   627: isub           
        //   628: if_icmpge       692
        //   631: goto            638
        //   634: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   637: athrow         
        //   638: aload_0        
        //   639: iload           10
        //   641: iconst_1       
        //   642: iadd           
        //   643: invokevirtual   java/lang/String.charAt:(I)C
        //   646: bipush          10
        //   648: if_icmpne       692
        //   651: goto            658
        //   654: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: iinc            10, 1
        //   661: iload           13
        //   663: ifeq            692
        //   666: goto            673
        //   669: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   672: athrow         
        //   673: aload           4
        //   675: aload_0        
        //   676: iload           10
        //   678: invokevirtual   java/lang/String.charAt:(I)C
        //   681: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   684: pop            
        //   685: goto            692
        //   688: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   691: athrow         
        //   692: iload           13
        //   694: ifeq            704
        //   697: goto            770
        //   700: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   703: athrow         
        //   704: iload           12
        //   706: ifeq            715
        //   709: iconst_1       
        //   710: istore          8
        //   712: goto            770
        //   715: aload           6
        //   717: invokeinterface java/lang/Runnable.run:()V
        //   722: goto            770
        //   725: iload           7
        //   727: ifne            762
        //   730: ldc             " \t\f"
        //   732: iload           11
        //   734: invokevirtual   java/lang/String.indexOf:(I)I
        //   737: iconst_m1      
        //   738: if_icmpeq       762
        //   741: goto            748
        //   744: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   747: athrow         
        //   748: aload           6
        //   750: invokeinterface java/lang/Runnable.run:()V
        //   755: goto            770
        //   758: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   761: athrow         
        //   762: aload           4
        //   764: iload           11
        //   766: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   769: pop            
        //   770: iinc            10, 1
        //   773: goto            172
        //   776: aload           6
        //   778: invokeinterface java/lang/Runnable.run:()V
        //   783: aload_3        
        //   784: areturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    105    109    Ljava/lang/IllegalArgumentException;
        //  95     113    113    117    Ljava/lang/IllegalArgumentException;
        //  204    220    223    227    Ljava/lang/IllegalArgumentException;
        //  244    259    262    266    Ljava/lang/IllegalArgumentException;
        //  251    296    296    300    Ljava/lang/IllegalArgumentException;
        //  334    346    349    353    Ljava/lang/IllegalArgumentException;
        //  341    360    363    367    Ljava/lang/IllegalArgumentException;
        //  353    376    379    383    Ljava/lang/IllegalArgumentException;
        //  367    396    399    403    Ljava/lang/IllegalArgumentException;
        //  383    414    417    421    Ljava/lang/IllegalArgumentException;
        //  427    436    439    443    Ljava/lang/IllegalArgumentException;
        //  431    456    459    463    Ljava/lang/IllegalArgumentException;
        //  443    471    474    478    Ljava/lang/IllegalArgumentException;
        //  489    498    501    505    Ljava/lang/IllegalArgumentException;
        //  493    512    515    519    Ljava/lang/IllegalArgumentException;
        //  505    524    527    531    Ljava/lang/IllegalArgumentException;
        //  537    551    554    558    Ljava/lang/IllegalArgumentException;
        //  544    565    568    572    Ljava/lang/IllegalArgumentException;
        //  558    577    580    584    Ljava/lang/IllegalArgumentException;
        //  572    588    588    592    Ljava/lang/IllegalArgumentException;
        //  595    608    611    615    Ljava/lang/IllegalArgumentException;
        //  615    631    634    638    Ljava/lang/IllegalArgumentException;
        //  622    651    654    658    Ljava/lang/IllegalArgumentException;
        //  638    666    669    673    Ljava/lang/IllegalArgumentException;
        //  658    685    688    692    Ljava/lang/IllegalArgumentException;
        //  692    700    700    704    Ljava/lang/IllegalArgumentException;
        //  725    741    744    748    Ljava/lang/IllegalArgumentException;
        //  730    758    758    762    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0353:
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
    private static String c(final String s, final CidrCompilerSwitches.Format format) {
        String escapeParameterOnWindows = null;
        Label_0017: {
            try {
                if (format == CidrCompilerSwitches.Format.WINDOWS_SHELL) {
                    final boolean b = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final boolean b = false;
            try {
                escapeParameterOnWindows = CommandLineUtil.escapeParameterOnWindows(s, b);
                if (escapeParameterOnWindows == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder", "convertWindows"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return escapeParameterOnWindows;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
