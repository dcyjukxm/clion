// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.text.StringUtil;
import java.util.Iterator;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;
import java.io.Writer;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class ClangTidyUtil
{
    @NotNull
    public static String disableCheck(@NotNull final String p0, @NotNull final String p1) {
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
        //    18: ldc             "configuration"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "disableCheck"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "checkName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "disableCheck"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.a:(Ljava/lang/String;)Ljava/util/List;
        //    92: astore_2       
        //    93: aload_2        
        //    94: aload_1        
        //    95: invokedynamic   test:(Ljava/lang/String;)Ljava/util/function/Predicate;
        //   100: invokeinterface java/util/List.removeIf:(Ljava/util/function/Predicate;)Z
        //   105: pop            
        //   106: aload_2        
        //   107: aload_1        
        //   108: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.a:(Ljava/util/List;Ljava/lang/String;)Z
        //   111: ifne            147
        //   114: aload_2        
        //   115: new             Ljava/lang/StringBuilder;
        //   118: dup            
        //   119: invokespecial   java/lang/StringBuilder.<init>:()V
        //   122: ldc             "-"
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: aload_1        
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   134: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   139: pop            
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   146: athrow         
        //   147: aload_2        
        //   148: ldc             ","
        //   150: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   153: dup            
        //   154: ifnonnull       191
        //   157: new             Ljava/lang/IllegalStateException;
        //   160: dup            
        //   161: ldc             "@NotNull method %s.%s must not return null"
        //   163: ldc             2
        //   165: anewarray       Ljava/lang/Object;
        //   168: dup            
        //   169: ldc             0
        //   171: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //   173: aastore        
        //   174: dup            
        //   175: ldc             1
        //   177: ldc             "disableCheck"
        //   179: aastore        
        //   180: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   183: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   186: athrow         
        //   187: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   190: athrow         
        //   191: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  93     140    143    147    Ljava/lang/IllegalArgumentException;
        //  147    187    187    191    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    public static String disableGroup(@NotNull final String p0, @NotNull final String p1) {
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
        //    18: ldc             "configuration"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "disableGroup"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "groupName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "disableGroup"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.a:(Ljava/lang/String;)Ljava/util/List;
        //    92: astore_2       
        //    93: aload_2        
        //    94: aload_1        
        //    95: invokedynamic   test:(Ljava/lang/String;)Ljava/util/function/Predicate;
        //   100: invokeinterface java/util/List.removeIf:(Ljava/util/function/Predicate;)Z
        //   105: pop            
        //   106: aload_2        
        //   107: aload_1        
        //   108: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.a:(Ljava/util/List;Ljava/lang/String;)Z
        //   111: ifne            152
        //   114: aload_2        
        //   115: new             Ljava/lang/StringBuilder;
        //   118: dup            
        //   119: invokespecial   java/lang/StringBuilder.<init>:()V
        //   122: ldc             "-"
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: aload_1        
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: ldc             "-*"
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   139: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   144: pop            
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   151: athrow         
        //   152: aload_2        
        //   153: ldc             ","
        //   155: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   158: dup            
        //   159: ifnonnull       196
        //   162: new             Ljava/lang/IllegalStateException;
        //   165: dup            
        //   166: ldc             "@NotNull method %s.%s must not return null"
        //   168: ldc             2
        //   170: anewarray       Ljava/lang/Object;
        //   173: dup            
        //   174: ldc             0
        //   176: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //   178: aastore        
        //   179: dup            
        //   180: ldc             1
        //   182: ldc             "disableGroup"
        //   184: aastore        
        //   185: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   188: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   191: athrow         
        //   192: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   195: athrow         
        //   196: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  93     145    148    152    Ljava/lang/IllegalArgumentException;
        //  152    192    192    196    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068_1:
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
    public static String getGroupNameForCheck(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checkName", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "getGroupNameForCheck"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final int index = s.indexOf(45);
        try {
            if (index != -1) {
                return s.substring(0, index);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    public static void writeCompilationDatabase(@NotNull final Writer writer, @NotNull final String s, @NotNull final String s2, @NotNull final List<String> list, @NotNull final String s3) throws IOException {
        try {
            if (writer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputWriter", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "writeCompilationDatabase"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerExecutable", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "writeCompilationDatabase"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerWorkingDirectory", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "writeCompilationDatabase"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerOptions", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "writeCompilationDatabase"));
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inputFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "writeCompilationDatabase"));
            }
        }
        catch (IOException ex5) {
            throw b(ex5);
        }
        final JsonWriter jsonWriter = new JsonWriter(writer);
        try {
            jsonWriter.beginArray();
            jsonWriter.beginObject();
            jsonWriter.name("directory");
            jsonWriter.value(s2);
            jsonWriter.name("arguments");
            jsonWriter.beginArray();
            jsonWriter.value(s);
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                jsonWriter.value((String)iterator.next());
            }
            jsonWriter.value(s3);
            jsonWriter.endArray();
            jsonWriter.name("file");
            jsonWriter.value(s3);
            jsonWriter.endObject();
            jsonWriter.endArray();
        }
        finally {
            jsonWriter.close();
        }
    }
    
    private static List<String> a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "getTrimmedOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final List split = StringUtil.split(s, ",");
        split.replaceAll(String::trim);
        return (List<String>)split;
    }
    
    private static boolean a(@NotNull final List<String> list, @NotNull final String s) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "isDisabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checkName", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil", "isDisabled"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        boolean b = false;
        for (final String s2 : list) {
            if (s2.equals("*")) {
                b = false;
            }
            else if (s2.equals("-*")) {
                b = true;
            }
            else if (b(s2, s)) {
                b = false;
            }
            else {
                if (!a(s2, s)) {
                    continue;
                }
                b = true;
            }
        }
        return b;
    }
    
    private static boolean b(@NotNull final String p0, @NotNull final String p1) {
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
        //    18: ldc             "option"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "groupOrCheckEnabled"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "checkName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "groupOrCheckEnabled"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    93: ifne            152
        //    96: aload_0        
        //    97: invokevirtual   java/lang/String.length:()I
        //   100: iconst_1       
        //   101: if_icmple       160
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   110: athrow         
        //   111: aload_0        
        //   112: ldc             "*"
        //   114: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   117: ifeq            160
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   126: athrow         
        //   127: aload_1        
        //   128: aload_0        
        //   129: iconst_0       
        //   130: aload_0        
        //   131: invokevirtual   java/lang/String.length:()I
        //   134: iconst_1       
        //   135: isub           
        //   136: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   139: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   142: ifeq            160
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   151: athrow         
        //   152: iconst_1       
        //   153: goto            161
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   159: athrow         
        //   160: iconst_0       
        //   161: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     104    107    111    Ljava/lang/IllegalArgumentException;
        //  96     120    123    127    Ljava/lang/IllegalArgumentException;
        //  111    145    148    152    Ljava/lang/IllegalArgumentException;
        //  127    156    156    160    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
    
    private static boolean a(@NotNull final String p0, @NotNull final String p1) {
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
        //    18: ldc             "option"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "groupOrCheckDisabled"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "checkName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "groupOrCheckDisabled"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: ldc             "-"
        //    91: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    94: ifne            103
        //    97: iconst_0       
        //    98: ireturn        
        //    99: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   102: athrow         
        //   103: aload_0        
        //   104: ldc             "*"
        //   106: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   109: ifeq            162
        //   112: aload_0        
        //   113: invokevirtual   java/lang/String.length:()I
        //   116: iconst_2       
        //   117: if_icmple       160
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   126: athrow         
        //   127: aload_1        
        //   128: aload_0        
        //   129: iconst_1       
        //   130: aload_0        
        //   131: invokevirtual   java/lang/String.length:()I
        //   134: iconst_1       
        //   135: isub           
        //   136: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   139: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   142: ifeq            160
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   151: athrow         
        //   152: iconst_1       
        //   153: goto            161
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   159: athrow         
        //   160: iconst_0       
        //   161: ireturn        
        //   162: aload_0        
        //   163: invokevirtual   java/lang/String.length:()I
        //   166: iconst_1       
        //   167: if_icmple       197
        //   170: aload_1        
        //   171: aload_0        
        //   172: iconst_1       
        //   173: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   176: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   179: ifeq            197
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   188: athrow         
        //   189: iconst_1       
        //   190: goto            198
        //   193: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   196: athrow         
        //   197: iconst_0       
        //   198: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     99     99     103    Ljava/lang/IllegalArgumentException;
        //  103    120    123    127    Ljava/lang/IllegalArgumentException;
        //  112    145    148    152    Ljava/lang/IllegalArgumentException;
        //  127    156    156    160    Ljava/lang/IllegalArgumentException;
        //  162    182    185    189    Ljava/lang/IllegalArgumentException;
        //  170    193    193    197    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0127:
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
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
