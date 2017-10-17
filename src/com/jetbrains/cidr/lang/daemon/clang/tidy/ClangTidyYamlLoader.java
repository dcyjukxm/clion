// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import java.util.SortedSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Comparator;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import java.util.List;
import com.intellij.openapi.editor.Document;
import org.jetbrains.annotations.NotNull;

public class ClangTidyYamlLoader
{
    public List<ClangTidyDiagnostic> load(@NotNull final String p0, @NotNull final Document p1) {
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
        //    18: ldc             "yamlContent"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "load"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "document"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "load"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: new             Lorg/yaml/snakeyaml/constructor/Constructor;
        //    91: dup            
        //    92: ldc             Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlData;.class
        //    94: invokespecial   org/yaml/snakeyaml/constructor/Constructor.<init>:(Ljava/lang/Class;)V
        //    97: astore_3       
        //    98: new             Lorg/yaml/snakeyaml/Yaml;
        //   101: dup            
        //   102: aload_3        
        //   103: invokespecial   org/yaml/snakeyaml/Yaml.<init>:(Lorg/yaml/snakeyaml/constructor/BaseConstructor;)V
        //   106: astore          4
        //   108: aload           4
        //   110: aload_1        
        //   111: ldc             Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlData;.class
        //   113: invokevirtual   org/yaml/snakeyaml/Yaml.loadAs:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
        //   116: checkcast       Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlData;
        //   119: astore          5
        //   121: aload           5
        //   123: ifnull          141
        //   126: aload           5
        //   128: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlData.Diagnostics:Ljava/util/List;
        //   131: ifnonnull       149
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   144: areturn        
        //   145: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload           5
        //   151: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlData.Diagnostics:Ljava/util/List;
        //   154: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   159: invokedynamic   test:()Ljava/util/function/Predicate;
        //   164: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //   169: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   172: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   177: checkcast       Ljava/util/List;
        //   180: astore          6
        //   182: aload           6
        //   184: aload_2        
        //   185: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/util/List;Lcom/intellij/openapi/editor/Document;)Ljava/util/Map;
        //   188: astore          7
        //   190: aload           6
        //   192: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   197: aload           7
        //   199: invokedynamic   apply:(Ljava/util/Map;)Ljava/util/function/Function;
        //   204: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   209: invokedynamic   test:()Ljava/util/function/Predicate;
        //   214: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //   219: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   222: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   227: checkcast       Ljava/util/List;
        //   230: areturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/intellij/openapi/editor/Document;)Ljava/util/List<Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  121    134    137    141    Ljava/lang/IllegalArgumentException;
        //  126    145    145    149    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: (p0:YamlDiagnostic) -> return:boolean(invokestatic:boolean(ClangTidyYamlLoader::c, p0:YamlDiagnostic))
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.GotoRemoval.traverseGraph(GotoRemoval.java:88)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:52)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:294)
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
    
    private static ClangTidyDiagnostic a(@NotNull final YamlDiagnostic p0, @NotNull final Map<Integer, Integer> p1) {
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
        //    18: ldc             "diagnostic"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createClangTidyDiagnostic"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "byteOffsetMap"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createClangTidyDiagnostic"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    91: astore_2       
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.Replacements:Ljava/util/List;
        //    96: ifnull          131
        //    99: aload_0        
        //   100: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.Replacements:Ljava/util/List;
        //   103: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   108: aload_1        
        //   109: invokedynamic   apply:(Ljava/util/Map;)Ljava/util/function/Function;
        //   114: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   119: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   122: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   127: checkcast       Ljava/util/List;
        //   130: astore_2       
        //   131: aload_1        
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.FileOffset:I
        //   136: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   139: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   144: checkcast       Ljava/lang/Integer;
        //   147: astore_3       
        //   148: aload_3        
        //   149: ifnull          209
        //   152: aload_2        
        //   153: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   158: invokedynamic   test:()Ljava/util/function/Predicate;
        //   163: invokeinterface java/util/stream/Stream.allMatch:(Ljava/util/function/Predicate;)Z
        //   168: ifeq            209
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: new             Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic;
        //   181: dup            
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.Message:Ljava/lang/String;
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.DiagnosticName:Ljava/lang/String;
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.FilePath:Ljava/lang/String;
        //   194: aload_3        
        //   195: invokevirtual   java/lang/Integer.intValue:()I
        //   198: aload_2        
        //   199: invokespecial   com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V
        //   202: goto            210
        //   205: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aconst_null    
        //   210: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic;Ljava/util/Map<Ljava/lang/Integer;Ljava/lang/Integer;>;)Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  148    171    174    178    Ljava/lang/IllegalArgumentException;
        //  152    205    205    209    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    private static ClangTidyReplacement a(@NotNull final YamlReplacement yamlReplacement, @NotNull final Map<Integer, Integer> map) {
        try {
            if (yamlReplacement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacement", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader", "createClangTidyReplacement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "byteOffsetMap", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader", "createClangTidyReplacement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Integer n = map.get(yamlReplacement.Offset);
        final Integer n2 = map.get(yamlReplacement.Offset + yamlReplacement.Length);
        Label_0142: {
            try {
                if (n == null) {
                    return null;
                }
                final Integer n3 = n2;
                if (n3 != null) {
                    break Label_0142;
                }
                return null;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Integer n3 = n2;
                if (n3 != null) {
                    return new ClangTidyReplacement(yamlReplacement.FilePath, yamlReplacement.ReplacementText, n, n2);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    private static boolean c(@Nullable final YamlDiagnostic p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          134
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.DiagnosticName:Ljava/lang/String;
        //     8: invokestatic    com/intellij/openapi/util/text/StringUtil.isNotEmpty:(Ljava/lang/String;)Z
        //    11: ifeq            134
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.FilePath:Ljava/lang/String;
        //    25: invokestatic    com/intellij/openapi/util/text/StringUtil.isNotEmpty:(Ljava/lang/String;)Z
        //    28: ifeq            134
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.Message:Ljava/lang/String;
        //    42: invokestatic    com/intellij/openapi/util/text/StringUtil.isNotEmpty:(Ljava/lang/String;)Z
        //    45: ifeq            134
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.FileOffset:I
        //    59: iflt            134
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.Replacements:Ljava/util/List;
        //    73: ifnull          112
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic.Replacements:Ljava/util/List;
        //    87: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    92: invokedynamic   test:()Ljava/util/function/Predicate;
        //    97: invokeinterface java/util/stream/Stream.allMatch:(Ljava/util/function/Predicate;)Z
        //   102: ifeq            134
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_0        
        //   113: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlDiagnostic;)Z
        //   116: ifeq            134
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_1       
        //   127: goto            135
        //   130: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: iconst_0       
        //   135: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  4      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     48     51     55     Ljava/lang/IllegalArgumentException;
        //  38     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     76     79     83     Ljava/lang/IllegalArgumentException;
        //  69     105    108    112    Ljava/lang/IllegalArgumentException;
        //  83     119    122    126    Ljava/lang/IllegalArgumentException;
        //  112    130    130    134    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    private static boolean a(@NotNull final YamlDiagnostic yamlDiagnostic) {
        try {
            if (yamlDiagnostic == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostic", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader", "appliedToSingleFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0148: {
            Label_0076: {
                try {
                    if (yamlDiagnostic.Replacements == null) {
                        return true;
                    }
                    final YamlDiagnostic yamlDiagnostic2 = yamlDiagnostic;
                    final List<YamlReplacement> list = yamlDiagnostic2.Replacements;
                    final boolean b = list.isEmpty();
                    if (b) {
                        return true;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final YamlDiagnostic yamlDiagnostic2 = yamlDiagnostic;
                    final List<YamlReplacement> list = yamlDiagnostic2.Replacements;
                    final boolean b = list.isEmpty();
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (!yamlDiagnostic.FilePath.equals(yamlDiagnostic.Replacements.get(0).FilePath)) {
                        return false;
                    }
                    final YamlDiagnostic yamlDiagnostic3 = yamlDiagnostic;
                    final List<YamlReplacement> list2 = yamlDiagnostic3.Replacements;
                    final Stream<Object> stream = list2.stream();
                    final Function<Object, String> function = yamlReplacement -> yamlReplacement.FilePath;
                    final Stream<Object> stream2 = stream.map((Function<? super Object, ?>)function);
                    final Collector<? super Object, ?, Set<? super Object>> collector = Collectors.toSet();
                    final Set set = stream2.collect((Collector<? super Object, Object, Set>)collector);
                    final Set set2 = set;
                    final int n = set2.size();
                    final boolean b2 = true;
                    if (n == (b2 ? 1 : 0)) {
                        break Label_0148;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final YamlDiagnostic yamlDiagnostic3 = yamlDiagnostic;
                final List<YamlReplacement> list2 = yamlDiagnostic3.Replacements;
                final Stream<Object> stream = list2.stream();
                final Function<Object, String> function = yamlReplacement2 -> yamlReplacement2.FilePath;
                final Stream<Object> stream2 = stream.map((Function<? super Object, ?>)function);
                final Collector<? super Object, ?, Set<? super Object>> collector = Collectors.toSet();
                final Set set = stream2.collect((Collector<? super Object, Object, Set>)collector);
                final Set set2 = set;
                final int n = set2.size();
                final boolean b2 = true;
                if (n == (b2 ? 1 : 0)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    private static boolean a(@Nullable final YamlReplacement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          71
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlReplacement.FilePath:Ljava/lang/String;
        //     8: invokestatic    com/intellij/openapi/util/text/StringUtil.isNotEmpty:(Ljava/lang/String;)Z
        //    11: ifeq            71
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlReplacement.ReplacementText:Ljava/lang/String;
        //    25: ifnull          71
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlReplacement.Length:I
        //    39: iflt            71
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader$YamlReplacement.Offset:I
        //    53: iflt            71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  4      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    private static Map<Integer, Integer> a(@NotNull final List<YamlDiagnostic> list, @NotNull final Document document) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostics", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader", "createByteOffsetMap"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader", "createByteOffsetMap"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final byte[] bytes = document.getText().getBytes(StandardCharsets.UTF_8);
        final TreeSet<Integer> set = new TreeSet<Integer>(Comparator.naturalOrder());
        final Set<Integer> set2;
        final Iterator<YamlReplacement> iterator;
        YamlReplacement yamlReplacement;
        list.forEach(yamlDiagnostic -> {
            set2.add(yamlDiagnostic.FileOffset);
            if (yamlDiagnostic.Replacements != null) {
                yamlDiagnostic.Replacements.iterator();
                while (iterator.hasNext()) {
                    yamlReplacement = iterator.next();
                    set2.add(yamlReplacement.Offset);
                    set2.add(yamlReplacement.Offset + yamlReplacement.Length);
                }
            }
            return;
        });
        int intValue = 0;
        int n = 0;
        final HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (final Integer n2 : set) {
            try {
                if (n2 > bytes.length) {
                    break;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final int n3 = n + new String(bytes, intValue, n2 - intValue).length();
            hashMap.put(n2, n3);
            intValue = n2;
            n = n3;
        }
        HashMap<Integer, Integer> hashMap2;
        try {
            hashMap2 = hashMap;
            if (hashMap2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyYamlLoader", "createByteOffsetMap"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return hashMap2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    static class YamlData
    {
        public String MainSourceFile;
        public List<YamlDiagnostic> Diagnostics;
    }
    
    static class YamlDiagnostic
    {
        public String Message;
        public String DiagnosticName;
        public String FilePath;
        public int FileOffset;
        public List<YamlReplacement> Replacements;
    }
    
    static class YamlReplacement
    {
        public String FilePath;
        public String ReplacementText;
        public int Offset;
        public int Length;
    }
}
