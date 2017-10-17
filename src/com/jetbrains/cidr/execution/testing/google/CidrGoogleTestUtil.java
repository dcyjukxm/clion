// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.Collections;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import java.util.List;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.util.CommonProcessors;
import java.util.ArrayList;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.Set;
import java.util.regex.Pattern;

public class CidrGoogleTestUtil
{
    private static final Pattern namePattern;
    private static final Set<String> GOOGLE_TEST_MACRO_NAMES;
    
    @Nullable
    public static OCStructSymbol findGoogleTestSymbol(final Project project, @NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Ref ref = new Ref();
        boolean b = false;
        Label_0148: {
            try {
                if (!OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)(ocSymbol -> {
                    try {
                        if (s == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "lambda$findGoogleTestSymbol$0"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (s2 == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "lambda$findGoogleTestSymbol$0"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    Label_0114: {
                        try {
                            if (!(ocSymbol instanceof OCStructSymbol)) {
                                return true;
                            }
                            final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                            final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                            final String s3 = s;
                            final String s4 = s2;
                            final boolean b = googleTestNameMatches(ocStructSymbol2, s3, s4);
                            if (b) {
                                break Label_0114;
                            }
                            return true;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                            final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                            final String s3 = s;
                            final String s4 = s2;
                            final boolean b = googleTestNameMatches(ocStructSymbol2, s3, s4);
                            if (b) {
                                ref.set((Object)ocSymbol);
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    return true;
                }), s + "_" + s2 + "_Test")) {
                    b = true;
                    break Label_0148;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            b = false;
        }
        final boolean b2 = b;
        try {
            if (!b2) {
                OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)(ocSymbol -> {
                    try {
                        if (s2 == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "lambda$findGoogleTestSymbol$1"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    OCSymbol member = null;
                    Label_0067: {
                        try {
                            if (ocSymbol instanceof OCNamespaceSymbol) {
                                member = ((OCNamespaceSymbol)ocSymbol).findMember(s2);
                                break Label_0067;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        member = null;
                    }
                    final OCSymbol ocSymbol2 = member;
                    try {
                        if (ocSymbol2 instanceof OCStructSymbol) {
                            ref.set((Object)ocSymbol2);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    return true;
                }), "gtest_case_" + s + "_");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (OCStructSymbol)ref.get();
    }
    
    @NotNull
    public static Collection<OCStructSymbol> findGoogleTestSymbols(final Project project) {
        Collection<OCStructSymbol> a;
        try {
            a = a(project, null, false, true);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    public static Collection<OCStructSymbol> findGoogleTestSymbolsForSuiteRandomly(final Project project, @Nullable final String s, final boolean b) {
        Collection<OCStructSymbol> a;
        try {
            a = a(project, s, b, false);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestSymbolsForSuiteRandomly"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    public static Collection<OCStructSymbol> findGoogleTestSymbolsForSuiteSorted(final Project project, @Nullable final String s) {
        Collection<OCStructSymbol> a;
        try {
            a = a(project, s, false, true);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestSymbolsForSuiteSorted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    private static Collection<OCStructSymbol> a(final Project project, @Nullable final String s, final boolean b, final boolean b2) {
        Pattern pattern = null;
        Label_0018: {
            try {
                if (s == null) {
                    pattern = CidrGoogleTestUtil.namePattern;
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            pattern = getSimpleTestPattern(s);
        }
        final Pattern pattern2 = pattern;
        final ArrayList list = new ArrayList<OCStructSymbol>();
        final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol instanceof OCNamespaceSymbol && (s == null || ocSymbol.getName().contains(s));
            }
        };
        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)collectProcessor, null);
        final Processor processor = p4 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aconst_null    
            //     1: astore          5
            //     3: aload           4
            //     5: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //     8: ifeq            113
            //    11: aload           4
            //    13: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    16: astore          6
            //    18: aload_0        
            //    19: aload           6
            //    21: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
            //    24: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
            //    27: invokevirtual   java/util/regex/Matcher.matches:()Z
            //    30: ifeq            98
            //    33: aload_1        
            //    34: ifnull          60
            //    37: goto            44
            //    40: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload           6
            //    46: aload_1        
            //    47: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.googleTestNameMatches:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;)Z
            //    50: ifeq            98
            //    53: goto            60
            //    56: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    59: athrow         
            //    60: aload           6
            //    62: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.isGoogleTestClass:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
            //    65: ifeq            98
            //    68: goto            75
            //    71: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    74: athrow         
            //    75: aload           6
            //    77: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.b:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
            //    80: ifne            98
            //    83: goto            90
            //    86: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    89: athrow         
            //    90: iconst_1       
            //    91: goto            99
            //    94: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    97: athrow         
            //    98: iconst_0       
            //    99: istore          7
            //   101: iload           7
            //   103: ifeq            110
            //   106: aload           6
            //   108: astore          5
            //   110: goto            253
            //   113: aload_1        
            //   114: ifnonnull       173
            //   117: aload           4
            //   119: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   124: ldc             "gtest_case_"
            //   126: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   129: ifeq            169
            //   132: goto            139
            //   135: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   138: athrow         
            //   139: aload           4
            //   141: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   146: ldc             "_"
            //   148: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
            //   151: ifeq            169
            //   154: goto            161
            //   157: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   160: athrow         
            //   161: iconst_1       
            //   162: goto            207
            //   165: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   168: athrow         
            //   169: iconst_0       
            //   170: goto            207
            //   173: aload           4
            //   175: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   180: new             Ljava/lang/StringBuilder;
            //   183: dup            
            //   184: invokespecial   java/lang/StringBuilder.<init>:()V
            //   187: ldc             "gtest_case_"
            //   189: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   192: aload_1        
            //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   196: ldc             "_"
            //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   201: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   204: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   207: istore          6
            //   209: iload           6
            //   211: ifeq            253
            //   214: new             Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil$2;
            //   217: dup            
            //   218: invokespecial   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil$2.<init>:()V
            //   221: astore          7
            //   223: aload           4
            //   225: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //   228: aconst_null    
            //   229: aload           7
            //   231: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
            //   234: pop            
            //   235: aload           7
            //   237: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
            //   240: ifeq            253
            //   243: aload           7
            //   245: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
            //   248: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   251: astore          5
            //   253: aload           5
            //   255: ifnull          288
            //   258: aload_2        
            //   259: aload           5
            //   261: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   266: pop            
            //   267: iload_3        
            //   268: ifne            286
            //   271: goto            278
            //   274: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   277: athrow         
            //   278: iconst_1       
            //   279: goto            287
            //   282: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   285: athrow         
            //   286: iconst_0       
            //   287: ireturn        
            //   288: iconst_1       
            //   289: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  18     37     40     44     Ljava/lang/IllegalArgumentException;
            //  33     53     56     60     Ljava/lang/IllegalArgumentException;
            //  44     68     71     75     Ljava/lang/IllegalArgumentException;
            //  60     83     86     90     Ljava/lang/IllegalArgumentException;
            //  75     94     94     98     Ljava/lang/IllegalArgumentException;
            //  113    132    135    139    Ljava/lang/IllegalArgumentException;
            //  117    154    157    161    Ljava/lang/IllegalArgumentException;
            //  139    165    165    169    Ljava/lang/IllegalArgumentException;
            //  253    271    274    278    Ljava/lang/IllegalArgumentException;
            //  258    282    282    286    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
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
        };
        ArrayList list2 = null;
        Label_0103: {
            Label_0086: {
                try {
                    ContainerUtil.process((Iterable)collectProcessor.getResults(), processor);
                    if (b) {
                        break Label_0103;
                    }
                    final boolean b3 = b2;
                    if (b3) {
                        break Label_0086;
                    }
                    break Label_0103;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final boolean b3 = b2;
                    if (b3) {
                        final int n;
                        final int n2;
                        final OCSymbolBase ocSymbolBase;
                        final Object o;
                        final int n3;
                        ContainerUtil.sort((List)list, (ocStructSymbol, ocStructSymbol2) -> {
                            ocStructSymbol.getName().compareTo(ocStructSymbol2.getName());
                            try {
                                if (n != 0) {
                                    return n;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            n2 = ocStructSymbol.getOffset() - ocStructSymbol2.getOffset();
                            try {
                                if (n2 != 0) {
                                    return n2;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            Label_0063_1: {
                                try {
                                    if (ocStructSymbol.getContainingFile() != null) {
                                        ocSymbolBase.getContainingFile();
                                        if (o == null) {
                                            break Label_0063_1;
                                        }
                                        else {
                                            return FileUtil.comparePaths(ocStructSymbol.getContainingFile().getPath(), ocStructSymbol2.getContainingFile().getPath());
                                        }
                                    }
                                    else {
                                        break Label_0063_1;
                                    }
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw a(ex5);
                                }
                                try {
                                    ocSymbolBase.getContainingFile();
                                    if (o == null) {
                                        return n3;
                                    }
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw a(ex6);
                                }
                            }
                            FileUtil.comparePaths(ocStructSymbol.getContainingFile().getPath(), ocStructSymbol2.getContainingFile().getPath());
                            return n3;
                        });
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            try {
                list2 = list;
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestSymbolsForSuite"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return (Collection<OCStructSymbol>)list2;
    }
    
    public static Pattern getSimpleTestPattern(final String s) {
        return Pattern.compile(Pattern.quote(s) + "_\\w+_Test");
    }
    
    private static boolean b(final OCStructSymbol ocStructSymbol) {
        try {
            if (!a(ocStructSymbol).isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Contract("null, _ -> false")
    public static boolean isGoogleTestFixture(@Nullable final OCStructSymbol ocStructSymbol, final boolean b) {
        Label_0019: {
            try {
                if (ocStructSymbol == null) {
                    return false;
                }
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b2 = b;
                final OCStructSymbol ocStructSymbol3 = findGoogleTestFixture(ocStructSymbol2, b2);
                if (ocStructSymbol3 != null) {
                    break Label_0019;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b2 = b;
                final OCStructSymbol ocStructSymbol3 = findGoogleTestFixture(ocStructSymbol2, b2);
                if (ocStructSymbol3 != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static OCStructSymbol findGoogleTestFixture(@NotNull final OCStructSymbol p0, final boolean p1) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findGoogleTestFixture"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.isGoogleTestClass:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
        //    48: ifeq            71
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractGoogleTestName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/intellij/openapi/util/Couple;
        //    55: ifnonnull       71
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: areturn        
        //    67: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iload_1        
        //    72: ifne            81
        //    75: aconst_null    
        //    76: areturn        
        //    77: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: invokestatic    com/intellij/openapi/util/Ref.create:()Lcom/intellij/openapi/util/Ref;
        //    84: astore_2       
        //    85: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    88: dup            
        //    89: aload_0        
        //    90: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    93: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    96: astore_3       
        //    97: aload_0        
        //    98: aload_3        
        //    99: aload_2        
        //   100: invokedynamic   process:(Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   105: iconst_0       
        //   106: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processAllBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;Z)Z
        //   109: pop            
        //   110: aload_2        
        //   111: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   114: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   117: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     67     67     71     Ljava/lang/IllegalArgumentException;
        //  71     77     77     81     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    public static boolean isGoogleTestClass(@NotNull final OCStructSymbol ocStructSymbol) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "isGoogleTestClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0077: {
            try {
                if (ocStructSymbol.getKind() != OCSymbolKind.STRUCT) {
                    return false;
                }
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final int n = 1;
                final String[] array = new String[n];
                final int n2 = 0;
                final String s = "Test";
                array[n2] = s;
                final boolean b = isGoogleTestClassWithAnyAncestor(ocStructSymbol2, array);
                if (b) {
                    break Label_0077;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final int n = 1;
                final String[] array = new String[n];
                final int n2 = 0;
                final String s = "Test";
                array[n2] = s;
                final boolean b = isGoogleTestClassWithAnyAncestor(ocStructSymbol2, array);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isGoogleTestClassWithAnyAncestor(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final String... array) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "isGoogleTestClassWithAnyAncestor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ancestorNames", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "isGoogleTestClassWithAnyAncestor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ContainerUtil.map2Set((Object[])array, s -> "::testing::" + s);
        try {
            final OCQualifiedName ocQualifiedName;
            final OCQualifiedName ocQualifiedName2;
            final boolean b;
            final Set set;
            final Object o;
            final Object o2;
            final boolean b2;
            if (!ocStructSymbol.processAllBaseClasses((ocSymbolWithQualifiedName, p2) -> {
                try {
                    if (!(ocSymbolWithQualifiedName instanceof OCSymbolWithQualifiedName)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                ocSymbolWithQualifiedName.getResolvedQualifiedName(false);
                Label_0047_1: {
                    try {
                        if (ocQualifiedName != null) {
                            ocQualifiedName2.getCanonicalName(b);
                            set.contains(o);
                            if (!o2) {
                                break Label_0047_1;
                            }
                            else {
                                return false;
                            }
                        }
                        else {
                            break Label_0047_1;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        ocQualifiedName2.getCanonicalName(b);
                        set.contains(o);
                        if (!o2) {
                            return b2;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                return b2;
            })) {
                return true;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return false;
    }
    
    @Nullable
    public static Couple<String> extractGoogleTestName(@Nullable final OCStructSymbol ocStructSymbol) {
        Object o = Collections.emptyList();
        if (ocStructSymbol != null) {
            final String name = ocStructSymbol.getName();
            Label_0179: {
                if (name.endsWith("_Test")) {
                    final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getParentOfType(ocStructSymbol.locateDefinition(), (Class)OCDeclaration.class);
                    if (ocDeclaration != null) {
                        final OCDeclarator ocDeclarator = (OCDeclarator)PsiTreeUtil.getChildOfType((PsiElement)PsiTreeUtil.getNextSiblingOfType((PsiElement)ocDeclaration, (Class)OCDeclaration.class), (Class)OCDeclarator.class);
                        Label_0096: {
                            try {
                                if (ocDeclarator == null) {
                                    break Label_0179;
                                }
                                if (!"test_info_".equals(ocDeclarator.getName())) {
                                    break Label_0096;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            o = a((PsiElement)ocDeclarator);
                            break Label_0179;
                        }
                        if (("gtest_" + name.substring(0, name.length() - "_Test".length()) + "_registered_").equals(ocDeclarator.getName())) {
                            final List<OCExpression> a = a((PsiElement)ocDeclarator);
                            if (a.size() >= 3) {
                                o = a.subList(1, 3);
                            }
                        }
                    }
                }
            }
            if (((List)o).isEmpty()) {
                o = a(ocStructSymbol, "AddToRegistry");
            }
            if (((List)o).isEmpty()) {
                o = a(ocStructSymbol);
            }
        }
        if (((List)o).size() >= 2) {
            return (Couple<String>)Couple.of((Object)StringUtil.stripQuotesAroundValue(extractArgumentValue(((List<PsiElement>)o).get(0))), (Object)StringUtil.stripQuotesAroundValue(extractArgumentValue(((List<PsiElement>)o).get(1))));
        }
        return null;
    }
    
    private static List<OCExpression> a(final OCStructSymbol ocStructSymbol) {
        List<OCExpression> list = (List<OCExpression>)Collections.emptyList();
        if (ocStructSymbol.getParent() instanceof OCNamespaceSymbol) {
            list = a((OCNamespaceSymbol)ocStructSymbol.getParent(), "gtest_" + ocStructSymbol.getName() + "_defined_");
            if (list.size() >= 4) {
                list = list.subList(2, 4);
            }
        }
        return list;
    }
    
    public static boolean googleTestNameMatches(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final String s) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "googleTestNameMatches"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "googleTestNameMatches"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return googleTestNameMatches(ocStructSymbol, s, null);
    }
    
    public static boolean googleTestNameMatches(@NotNull final OCStructSymbol p0, @NotNull final String p1, @Nullable final String p2) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "googleTestNameMatches"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "suiteName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "googleTestNameMatches"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractGoogleTestName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/intellij/openapi/util/Couple;
        //    92: astore_3       
        //    93: aload_3        
        //    94: ifnull          152
        //    97: aload_1        
        //    98: aload_3        
        //    99: getfield        com/intellij/openapi/util/Couple.first:Ljava/lang/Object;
        //   102: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   105: ifeq            152
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_2        
        //   116: ifnull          144
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_2        
        //   127: aload_3        
        //   128: getfield        com/intellij/openapi/util/Couple.second:Ljava/lang/Object;
        //   131: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   134: ifeq            152
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: iconst_1       
        //   145: goto            153
        //   148: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: iconst_0       
        //   153: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  93     108    111    115    Ljava/lang/IllegalArgumentException;
        //  97     119    122    126    Ljava/lang/IllegalArgumentException;
        //  115    137    140    144    Ljava/lang/IllegalArgumentException;
        //  126    148    148    152    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0115:
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
    public static OCMacroCall findGoogleTestMacros(@Nullable final PsiElement psiElement) {
        return a(psiElement, CidrGoogleTestUtil.GOOGLE_TEST_MACRO_NAMES);
    }
    
    @Nullable
    private static OCMacroCall a(@Nullable PsiElement psiElement, @NotNull final Set<String> set) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "macroNames", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCReferenceElement macroReferenceElement;
        String name = null;
        String s;
        for (psiElement = PsiTreeUtil.getNonStrictParentOfType(psiElement, new Class[] { OCMacroCall.class }); psiElement instanceof OCMacroCall; psiElement = psiElement.getNextSibling()) {
            macroReferenceElement = ((OCMacroCall)psiElement).getMacroReferenceElement();
            Label_0093: {
                try {
                    if (macroReferenceElement == null) {
                        name = null;
                        break Label_0093;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                name = macroReferenceElement.getName();
            }
            s = name;
            try {
                if (set.contains(s)) {
                    return (OCMacroCall)psiElement;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public static Couple<String> extractFullSuiteNameFromMacro(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractFullSuiteNameFromMacro"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<String> a = a(psiElement, ContainerUtil.newHashSet((Object[])new String[] { "INSTANTIATE_TEST_CASE_P", "INSTANTIATE_TYPED_TEST_CASE_P" }), 0, 1);
        if (a.isEmpty()) {
            final List<String> a2 = a(psiElement, ContainerUtil.newHashSet((Object[])new String[] { "TYPED_TEST_CASE", "TYPED_TEST_CASE_P", "REGISTER_TYPED_TEST_CASE_P" }), 0, 0);
            try {
                if (a2.isEmpty()) {
                    final Couple of = null;
                    return (Couple<String>)of;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final Couple of = Couple.of((Object)a2.get(0), (Object)null);
            return (Couple<String>)of;
        }
        return (Couple<String>)Couple.of((Object)a.get(1), (Object)a.get(0));
    }
    
    @NotNull
    private static List<String> a(@NotNull final PsiElement p0, final Set<String> p1, final int p2, final int p3) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "extractMacroArgumentValues"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: new             Ljava/util/ArrayList;
        //    47: dup            
        //    48: invokespecial   java/util/ArrayList.<init>:()V
        //    51: astore          4
        //    53: aload_0        
        //    54: aload_1        
        //    55: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Lcom/intellij/psi/PsiElement;Ljava/util/Set;)Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    58: astore          5
        //    60: aload           5
        //    62: ifnull          129
        //    65: aload           5
        //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getArguments:()Ljava/util/List;
        //    72: astore          6
        //    74: aload           6
        //    76: invokeinterface java/util/List.size:()I
        //    81: iload_3        
        //    82: if_icmple       129
        //    85: iload_2        
        //    86: iload_3        
        //    87: if_icmpgt       129
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload           4
        //    99: aload           6
        //   101: iload_2        
        //   102: iinc            2, 1
        //   105: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   110: checkcast       Lcom/intellij/psi/PsiElement;
        //   113: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractArgumentValue:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   116: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   121: pop            
        //   122: goto            85
        //   125: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aload           4
        //   131: dup            
        //   132: ifnonnull       169
        //   135: new             Ljava/lang/IllegalStateException;
        //   138: dup            
        //   139: ldc             "@NotNull method %s.%s must not return null"
        //   141: ldc             2
        //   143: anewarray       Ljava/lang/Object;
        //   146: dup            
        //   147: ldc             0
        //   149: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil"
        //   151: aastore        
        //   152: dup            
        //   153: ldc             1
        //   155: ldc             "extractMacroArgumentValues"
        //   157: aastore        
        //   158: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   161: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   164: athrow         
        //   165: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/util/Set<Ljava/lang/String;>;II)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  74     90     93     97     Ljava/lang/IllegalArgumentException;
        //  85     125    125    129    Ljava/lang/IllegalArgumentException;
        //  129    165    165    169    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
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
    public static String extractArgumentValue(@Nullable final PsiElement psiElement) {
        final LeafPsiElement leafPsiElement = (LeafPsiElement)PsiTreeUtil.findChildOfType(psiElement, (Class)LeafPsiElement.class);
        String s = null;
        Label_0030: {
            try {
                if (leafPsiElement == null) {
                    final String notNullize;
                    s = (notNullize = "");
                    break Label_0030;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String notNullize;
            s = (notNullize = StringUtil.notNullize(leafPsiElement.getText()));
            try {
                if (notNullize == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractArgumentValue"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    private static List<OCExpression> a(@NotNull final OCNamespaceSymbol ocNamespaceSymbol, @NotNull final String s) {
        try {
            if (ocNamespaceSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "methodName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCSymbol member = ocNamespaceSymbol.findMember(s);
        List<OCExpression> list = null;
        Label_0112: {
            try {
                if (member != null) {
                    final List<OCExpression> list2;
                    list = (list2 = a(member));
                    break Label_0112;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            List<OCExpression> list2;
            list = (list2 = Collections.emptyList());
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return list;
    }
    
    @NotNull
    private static List<OCExpression> a(@Nullable final PsiElement psiElement) {
        List<OCExpression> emptyList = null;
        Label_0077: {
            if (psiElement != null) {
                final OCCallExpression ocCallExpression = (OCCallExpression)PsiTreeUtil.findChildOfType(psiElement.getParent(), (Class)OCCallExpression.class);
                List<OCExpression> list = null;
                Label_0042: {
                    try {
                        if (ocCallExpression == null) {
                            break Label_0077;
                        }
                        final OCCallExpression ocCallExpression2 = ocCallExpression;
                        list = ocCallExpression2.getArguments();
                        if (list == null) {
                            break Label_0042;
                        }
                        return list;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCCallExpression ocCallExpression2 = ocCallExpression;
                        list = ocCallExpression2.getArguments();
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return list;
            }
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return emptyList;
    }
    
    @NotNull
    private static List<OCExpression> a(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<OCExpression> a;
        try {
            a = a(ocSymbol.locateDefinition());
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "extractMethodArguments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @Nullable
    public static OCSymbol findGoogleTestInstantiationSymbol(@NotNull final Project project, @NotNull final String s, @NotNull final String s2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestInstantiationSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestInstantiationSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instantiationName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil", "findGoogleTestInstantiationSymbol"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        try {
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)findFirstProcessor, "gtest_" + s2 + s + "_dummy_");
            if (findFirstProcessor.isFound()) {
                return (OCSymbol)findFirstProcessor.getFoundValue();
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)findFirstProcessor, "gtest_" + s2 + "_" + s);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    public static boolean fileIncludesGoogleTest(final PsiFile psiFile) {
        final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol instanceof OCStructSymbol && "::testing::TestCase".equals(ocSymbol.getType().getCanonicalName((PsiElement)psiFile));
            }
        };
        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(psiFile.getProject(), (Processor<OCSymbol>)collectProcessor, "TestCase");
        for (final OCSymbol ocSymbol : collectProcessor.getResults()) {
            if (ocSymbol.getContainingFile() != null) {
                final Collection<VirtualFile> allHeaderRoots = OCImportGraph.getAllHeaderRoots(psiFile.getProject(), ocSymbol.getContainingFile());
                try {
                    if (allHeaderRoots.contains(psiFile.getVirtualFile())) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
        return false;
    }
    
    static {
        namePattern = Pattern.compile("\\w+_\\w+_Test");
        GOOGLE_TEST_MACRO_NAMES = ContainerUtil.newHashSet((Object[])new String[] { "GTEST_TEST_", "TEST_P", "TYPED_TEST", "TYPED_TEST_P" });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
