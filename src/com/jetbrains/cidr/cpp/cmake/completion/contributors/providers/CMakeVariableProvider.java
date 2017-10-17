// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import java.util.Iterator;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeRecognizedGnuLanguage;
import com.jetbrains.cidr.cpp.cmake.CMakePolicy;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import java.util.Collection;
import com.intellij.util.ArrayUtil;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeRecognizedLanguage;
import java.util.List;

public class CMakeVariableProvider extends AbstractCMakeCompletionProvider
{
    private static final String CMAKE_VARIABLE_PREFIX = "${";
    private static final String[] INFORMATION_VARIABLES;
    private static final String[] CHANGING_INFORMATION_VARIABLES;
    private static final String CMAKE_POLICY_DEFAULT_VARIABLE_PREFIX = "CMAKE_POLICY_DEFAULT_";
    private static final String CMAKE_POLICY_WARNING_VARIABLE_PREFIX = "CMAKE_POLICY_WARNING_";
    private static final String[] BEHAVIOR_VARIABLES;
    private static final String[] CHANGING_BEHAVIOR_VARIABLES;
    public static final String[] CMAKE_POLICY_DEFAULT_HARDCODED;
    public static final String[] CMAKE_POLICY_WARNING_HARDCODED;
    private static final String[] SYSTEM_DESCRIPTION_VARIABLES;
    private static final String[] BUILD_CONTROL_VARIABLES;
    private static final String[] CMAKE_INCLUDE_WHAT_YOU_USE_HARDCODED;
    private static final String[] CMAKE_VISIBILITY_PRESET_HARDCODED;
    private static final String[] LANGUAGES_VARIABLES;
    private static final String CMAKE_COMPILER_IS_GNU_PREFIX = "CMAKE_COMPILER_IS_GNU";
    private static final String[] CMAKE_COMPILER_IS_GNU_VARIABLES;
    private static final String[] CHANGING_RECOGNIZED_LANGUAGES_VARIABLES;
    private static final String[] CHANGING_RECOGNIZED_LANGUAGES_VARIABLES_HARDCODED;
    private static final String[] CTEST_VARIABLES;
    private static final String[] CPACK_VARIABLES;
    private static final String[][] ALL_COMPLETION_VARIABLE_ARRAYS;
    private static final List<String> ALL_VARIABLES;
    static final String[] ALL_COMPLETION_VARIABLES;
    
    private static String[] a(final String s, final String s2) {
        final String[] array = new String[CMakeRecognizedLanguage.values().length];
        int i = 0;
        try {
            while (i < array.length) {
                array[i] = s + CMakeRecognizedLanguage.values()[i] + s2;
                ++i;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return array;
    }
    
    protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        addCompletions(completionParameters, set, "${", CMakeVariableProvider.ALL_COMPLETION_VARIABLES);
    }
    
    protected static void addCompletions(@NotNull final CompletionParameters p0, @NotNull final CompletionResultSet p1, @NotNull final String p2, @NotNull final String[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       52
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc_w           "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc_w           3
        //    14: anewarray       Ljava/lang/Object;
        //    17: dup            
        //    18: ldc_w           0
        //    21: ldc_w           "parameters"
        //    24: aastore        
        //    25: dup            
        //    26: ldc_w           1
        //    29: ldc_w           "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider"
        //    32: aastore        
        //    33: dup            
        //    34: ldc_w           2
        //    37: ldc_w           "addCompletions"
        //    40: aastore        
        //    41: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    44: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    47: athrow         
        //    48: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: ifnonnull       104
        //    56: new             Ljava/lang/IllegalArgumentException;
        //    59: dup            
        //    60: ldc_w           "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    63: ldc_w           3
        //    66: anewarray       Ljava/lang/Object;
        //    69: dup            
        //    70: ldc_w           0
        //    73: ldc_w           "result"
        //    76: aastore        
        //    77: dup            
        //    78: ldc_w           1
        //    81: ldc_w           "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider"
        //    84: aastore        
        //    85: dup            
        //    86: ldc_w           2
        //    89: ldc_w           "addCompletions"
        //    92: aastore        
        //    93: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    96: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    99: athrow         
        //   100: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_2        
        //   105: ifnonnull       156
        //   108: new             Ljava/lang/IllegalArgumentException;
        //   111: dup            
        //   112: ldc_w           "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   115: ldc_w           3
        //   118: anewarray       Ljava/lang/Object;
        //   121: dup            
        //   122: ldc_w           0
        //   125: ldc_w           "variablePrefix"
        //   128: aastore        
        //   129: dup            
        //   130: ldc_w           1
        //   133: ldc_w           "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider"
        //   136: aastore        
        //   137: dup            
        //   138: ldc_w           2
        //   141: ldc_w           "addCompletions"
        //   144: aastore        
        //   145: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   148: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   151: athrow         
        //   152: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_3        
        //   157: ifnonnull       208
        //   160: new             Ljava/lang/IllegalArgumentException;
        //   163: dup            
        //   164: ldc_w           "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   167: ldc_w           3
        //   170: anewarray       Ljava/lang/Object;
        //   173: dup            
        //   174: ldc_w           0
        //   177: ldc_w           "completionVariables"
        //   180: aastore        
        //   181: dup            
        //   182: ldc_w           1
        //   185: ldc_w           "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider"
        //   188: aastore        
        //   189: dup            
        //   190: ldc_w           2
        //   193: ldc_w           "addCompletions"
        //   196: aastore        
        //   197: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   200: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   203: athrow         
        //   204: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_0        
        //   209: invokevirtual   com/intellij/codeInsight/completion/CompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //   212: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   217: astore          4
        //   219: aload_2        
        //   220: invokevirtual   java/lang/String.length:()I
        //   223: istore          5
        //   225: aload_0        
        //   226: invokevirtual   com/intellij/codeInsight/completion/CompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //   229: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   234: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   239: astore          6
        //   241: aload           4
        //   243: ldc             "IntellijIdeaRulezzz"
        //   245: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   248: istore          7
        //   250: iload           7
        //   252: iconst_m1      
        //   253: if_icmpeq       553
        //   256: aload           4
        //   258: iconst_0       
        //   259: iload           7
        //   261: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   264: aload_2        
        //   265: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   268: istore          8
        //   270: iload           8
        //   272: iload           5
        //   274: iadd           
        //   275: iload           7
        //   277: if_icmpgt       553
        //   280: aload           4
        //   282: aload_2        
        //   283: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   286: ifeq            334
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload           6
        //   298: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //   301: if_acmpeq       334
        //   304: goto            311
        //   307: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   310: athrow         
        //   311: aload           4
        //   313: iconst_0       
        //   314: iload           7
        //   316: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   319: astore          9
        //   321: aload           9
        //   323: iload           5
        //   325: aload_0        
        //   326: aload_1        
        //   327: aload_3        
        //   328: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/String;ILcom/intellij/codeInsight/completion/CompletionParameters;Lcom/intellij/codeInsight/completion/CompletionResultSet;[Ljava/lang/String;)V
        //   331: goto            553
        //   334: aload           6
        //   336: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //   339: if_acmpne       553
        //   342: iload           8
        //   344: iflt            395
        //   347: goto            354
        //   350: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: iload           8
        //   356: ifeq            387
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload           4
        //   368: iload           8
        //   370: iconst_1       
        //   371: isub           
        //   372: invokevirtual   java/lang/String.charAt:(I)C
        //   375: bipush          92
        //   377: if_icmpeq       395
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: iconst_1       
        //   388: goto            396
        //   391: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: iconst_0       
        //   396: istore          9
        //   398: iload           9
        //   400: ifeq            553
        //   403: iconst_0       
        //   404: istore          10
        //   406: iload           8
        //   408: iload           5
        //   410: iadd           
        //   411: istore          11
        //   413: aload           4
        //   415: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.b:(Ljava/lang/String;)Z
        //   418: ifeq            434
        //   421: iinc            10, 1
        //   424: iinc            11, -1
        //   427: goto            434
        //   430: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   433: athrow         
        //   434: aload           4
        //   436: iload           10
        //   438: iload           7
        //   440: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   443: astore          12
        //   445: aload           4
        //   447: invokevirtual   java/lang/String.length:()I
        //   450: istore          13
        //   452: aload           4
        //   454: ldc             "\""
        //   456: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   459: ifeq            472
        //   462: iinc            13, -1
        //   465: goto            472
        //   468: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   471: athrow         
        //   472: iload           7
        //   474: ldc             "IntellijIdeaRulezzz "
        //   476: invokevirtual   java/lang/String.length:()I
        //   479: iadd           
        //   480: istore          15
        //   482: iload           15
        //   484: aload           4
        //   486: invokevirtual   java/lang/String.length:()I
        //   489: if_icmpge       520
        //   492: iload           15
        //   494: iload           13
        //   496: if_icmpge       520
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload           4
        //   508: iload           15
        //   510: iload           13
        //   512: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   515: astore          14
        //   517: goto            541
        //   520: iload           7
        //   522: ldc             "IntellijIdeaRulezzz"
        //   524: invokevirtual   java/lang/String.length:()I
        //   527: iadd           
        //   528: istore          16
        //   530: aload           4
        //   532: iload           16
        //   534: iload           13
        //   536: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   539: astore          14
        //   541: aload           12
        //   543: iload           11
        //   545: aload_0        
        //   546: aload_1        
        //   547: aload           14
        //   549: aload_3        
        //   550: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider.a:(Ljava/lang/String;ILcom/intellij/codeInsight/completion/CompletionParameters;Lcom/intellij/codeInsight/completion/CompletionResultSet;Ljava/lang/String;[Ljava/lang/String;)V
        //   553: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      48     48     52     Ljava/lang/IllegalArgumentException;
        //  52     100    100    104    Ljava/lang/IllegalArgumentException;
        //  104    152    152    156    Ljava/lang/IllegalArgumentException;
        //  156    204    204    208    Ljava/lang/IllegalArgumentException;
        //  270    289    292    296    Ljava/lang/IllegalArgumentException;
        //  280    304    307    311    Ljava/lang/IllegalArgumentException;
        //  334    347    350    354    Ljava/lang/IllegalArgumentException;
        //  342    359    362    366    Ljava/lang/IllegalArgumentException;
        //  354    380    383    387    Ljava/lang/IllegalArgumentException;
        //  366    391    391    395    Ljava/lang/IllegalArgumentException;
        //  413    427    430    434    Ljava/lang/IllegalArgumentException;
        //  452    465    468    472    Ljava/lang/IllegalArgumentException;
        //  482    499    502    506    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0354:
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
    
    private static void a(@NotNull final String s, final int n, @NotNull final CompletionParameters completionParameters, @NotNull final CompletionResultSet set, @NotNull final String[] array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "matcherText", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "completionVariables", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        a(s, n, completionParameters, set, "", array);
    }
    
    private static void a(@NotNull final String s, final int n, @NotNull final CompletionParameters completionParameters, @NotNull final CompletionResultSet set, @NotNull final String s2, @NotNull final String[] array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "matcherText", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literalSuffix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "completionVariables", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        Label_0293: {
            try {
                a(s, n, set, array, s2);
                if (!CMakeVariableProviderUtils.shouldCreatePolicyCompletions(completionParameters)) {
                    return;
                }
                final CompletionParameters completionParameters2 = completionParameters;
                final boolean b = a(completionParameters2);
                if (b) {
                    break Label_0293;
                }
                return;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final CompletionParameters completionParameters2 = completionParameters;
                final boolean b = a(completionParameters2);
                if (b) {
                    a(s, n, set, CMakeVariableProvider.CMAKE_POLICY_DEFAULT_HARDCODED, s2);
                    a(s, n, set, CMakeVariableProvider.CMAKE_POLICY_WARNING_HARDCODED, s2);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
    }
    
    private static boolean a(@NotNull final CompletionParameters completionParameters) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "isInsideVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return completionParameters.getPosition().getText().contains("${");
    }
    
    private static boolean b(final String s) {
        try {
            if (s.startsWith("\"")) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static void a(@NotNull final String s, final int n, @NotNull CompletionResultSet set, @NotNull final String[] array, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "matcherText", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "completionVariables", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literalSuffix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "completeResults"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final List map = ContainerUtil.map((Object[])array, s2 -> {
            try {
                if (s2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literalSuffix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "lambda$completeResults$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return LookupElementBuilder.create(s2).withInsertHandler((InsertHandler)a(s2));
        });
        Label_0279: {
            Label_0265: {
                try {
                    if (CodeInsightSettings.getInstance().COMPLETION_CASE_SENSITIVE != 3 || n == -1) {
                        break Label_0265;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                set = set.withPrefixMatcher((PrefixMatcher)new FirstLetterCaseMatcher(s.substring(n), n));
                break Label_0279;
            }
            set = set.withPrefixMatcher(s.substring(Math.max(0, n)));
        }
        set.addAllElements((Iterable)map);
    }
    
    @NotNull
    private static InsertHandler<LookupElement> a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literalSuffix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "createParenthesisClosingHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        InsertHandler<LookupElement> insertHandler;
        try {
            insertHandler = new InsertHandler<LookupElement>() {
                public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
                    final int n = insertionContext.getStartOffset() + lookupElement.getLookupString().length();
                    final boolean b = n >= insertionContext.getDocument().getText().length() || insertionContext.getDocument().getText().charAt(n) != '}';
                    final boolean b2 = insertionContext.getCompletionChar() == '\t';
                    final int n2 = 0;
                    if (b) {
                        if (b2) {
                            final int index = s.indexOf("}");
                            if (index == -1) {
                                insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)("}" + s));
                                insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset() - s.length() + n2);
                            }
                            else {
                                final String substring = s.substring(index);
                                insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)substring);
                                insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset() - substring.length() + n2 + 1);
                            }
                        }
                        else {
                            insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)"}");
                            insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset() + n2);
                        }
                    }
                    else {
                        insertionContext.getEditor().getCaretModel().moveToOffset(Math.min(insertionContext.getDocument().getTextLength(), insertionContext.getTailOffset() + n2 + 1));
                    }
                }
            };
            if (insertHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider", "createParenthesisClosingHandler"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (InsertHandler<LookupElement>)insertHandler;
    }
    
    public static String[] getAllCompletionVariables() {
        return ArrayUtil.toStringArray((Collection)CMakeVariableProvider.ALL_VARIABLES);
    }
    
    static {
        INFORMATION_VARIABLES = new String[] { "CMAKE_ARGC", "CMAKE_ARGV0", "CMAKE_AR", "CMAKE_BINARY_DIR", "CMAKE_BUILD_TOOL", "CMAKE_BUILD_RPATH", "CMAKE_CACHEFILE_DIR", "CMAKE_CACHE_MAJOR_VERSION", "CMAKE_CACHE_MINOR_VERSION", "CMAKE_CACHE_PATCH_VERSION", "CMAKE_CFG_INTDIR", "CMAKE_COMMAND", "CMAKE_CROSSCOMPILING", "CMAKE_CROSSCOMPILING_EMULATOR", "CMAKE_CTEST_COMMAND", "CMAKE_CUDA_EXTENSIONS", "CMAKE_CUDA_STANDARD", "CMAKE_CUDA_STANDARD_REQUIRED", "CMAKE_CUDA_TOOLKIT_INCLUDE_DIRECTORIES", "CMAKE_CURRENT_BINARY_DIR", "CMAKE_CURRENT_LIST_DIR", "CMAKE_CURRENT_LIST_FILE", "CMAKE_CURRENT_LIST_LINE", "CMAKE_CURRENT_SOURCE_DIR", "CMAKE_DL_LIBS", "CMAKE_ECLIPSE_GENERATE_LINKED_RESOURCES", "CMAKE_ECLIPSE_GENERATE_SOURCE_PROJECT", "CMAKE_ECLIPSE_MAKE_ARGUMENTS", "CMAKE_ECLIPSE_VERSION", "CMAKE_EDIT_COMMAND", "CMAKE_EXECUTABLE_SUFFIX", "CMAKE_EXTRA_GENERATOR", "CMAKE_EXTRA_SHARED_LIBRARY_SUFFIXES", "CMAKE_FIND_PACKAGE_NAME", "CMAKE_FIND_PACKAGE_SORT_ORDER", "CMAKE_FIND_PACKAGE_SORT_DIRECTION", "CMAKE_GENERATOR", "CMAKE_GENERATOR_PLATFORM", "CMAKE_GENERATOR_TOOLSET", "CMAKE_HOME_DIRECTORY", "CMAKE_IMPORT_LIBRARY_PREFIX", "CMAKE_IMPORT_LIBRARY_SUFFIX", "CMAKE_JOB_POOL_COMPILE", "CMAKE_JOB_POOL_LINK", "CMAKE_LINK_LIBRARY_SUFFIX", "CMAKE_MAJOR_VERSION", "CMAKE_MAKE_PROGRAM", "CMAKE_MATCH_COUNT", "CMAKE_MINIMUM_REQUIRED_VERSION", "CMAKE_MINOR_VERSION", "CMAKE_PARENT_LIST_FILE", "CMAKE_PATCH_VERSION", "CMAKE_PROJECT_NAME", "CMAKE_RANLIB", "CMAKE_ROOT", "CMAKE_SCRIPT_MODE_FILE", "CMAKE_SHARED_LIBRARY_PREFIX", "CMAKE_SHARED_LIBRARY_SUFFIX", "CMAKE_SHARED_MODULE_PREFIX", "CMAKE_SHARED_MODULE_SUFFIX", "CMAKE_SIZEOF_VOID_P", "CMAKE_SKIP_INSTALL_RULES", "CMAKE_SKIP_RPATH", "CMAKE_SOURCE_DIR", "CMAKE_STATIC_LIBRARY_PREFIX", "CMAKE_STATIC_LIBRARY_SUFFIX", "CMAKE_TOOLCHAIN_FILE", "CMAKE_TWEAK_VERSION", "CMAKE_VERBOSE_MAKEFILE", "CMAKE_VERSION", "CMAKE_VS_DEVENV_COMMAND", "CMAKE_VS_INTEL_Fortran_PROJECT_VERSION", "CMAKE_VS_MSBUILD_COMMAND", "CMAKE_VS_NsightTegra_VERSION", "CMAKE_VS_PLATFORM_NAME", "CMAKE_VS_PLATFORM_TOOLSET", "CMAKE_VS_PLATFORM_TOOLSET_HOST_ARCHITECTURE", "CMAKE_XCODE_PLATFORM_TOOLSET", "PROJECT_BINARY_DIR", "PROJECT_NAME", "PROJECT_SOURCE_DIR", "PROJECT_VERSION", "PROJECT_VERSION_MAJOR", "PROJECT_VERSION_MINOR", "PROJECT_VERSION_PATCH", "PROJECT_VERSION_TWEAK" };
        CHANGING_INFORMATION_VARIABLES = new String[] { "<PROJECT-NAME>_BINARY_DIR", "<PROJECT-NAME>_SOURCE_DIR", "<PROJECT-NAME>_VERSION", "<PROJECT-NAME>_VERSION_MAJOR", "<PROJECT-NAME>_VERSION_MINOR", "<PROJECT-NAME>_VERSION_PATCH", "<PROJECT-NAME>_VERSION_TWEAK" };
        BEHAVIOR_VARIABLES = new String[] { "BUILD_SHARED_LIBS", "CMAKE_ABSOLUTE_DESTINATION_FILES", "CMAKE_APPBUNDLE_PATH", "CMAKE_AUTOMOC_RELAXED_MODE", "CMAKE_BACKWARDS_COMPATIBILITY", "CMAKE_BUILD_TYPE", "CMAKE_COLOR_MAKEFILE", "CMAKE_CONFIGURATION_TYPES", "CMAKE_DEBUG_TARGET_PROPERTIES", "CMAKE_DEPENDS_IN_PROJECT_ONLY", "CMAKE_ENABLE_EXPORTS", "CMAKE_ERROR_DEPRECATED", "CMAKE_ERROR_ON_ABSOLUTE_INSTALL_DESTINATION", "CMAKE_EXPORT_COMPILE_COMMANDS", "CMAKE_EXPORT_NO_PACKAGE_REGISTRY", "CMAKE_SUBLIME_TEXT_2_ENV_SETTINGS", "CMAKE_SUBLIME_TEXT_2_EXCLUDE_BUILD_TREE", "CMAKE_SYSROOT", "CMAKE_FIND_APPBUNDLE", "CMAKE_FIND_FRAMEWORK", "CMAKE_FIND_LIBRARY_PREFIXES", "CMAKE_FIND_LIBRARY_SUFFIXES", "CMAKE_FIND_NO_INSTALL_PREFIX", "CMAKE_FIND_PACKAGE_NO_PACKAGE_REGISTRY", "CMAKE_FIND_PACKAGE_NO_SYSTEM_PACKAGE_REGISTRY", "CMAKE_FIND_PACKAGE_WARN_NO_MODULE", "CMAKE_FIND_ROOT_PATH", "CMAKE_FIND_ROOT_PATH_MODE_INCLUDE", "CMAKE_FIND_ROOT_PATH_MODE_LIBRARY", "CMAKE_FIND_ROOT_PATH_MODE_PACKAGE", "CMAKE_FIND_ROOT_PATH_MODE_PROGRAM", "CMAKE_FRAMEWORK_PATH", "CMAKE_IGNORE_PATH", "CMAKE_INCLUDE_PATH", "CMAKE_INCLUDE_DIRECTORIES_BEFORE", "CMAKE_INCLUDE_DIRECTORIES_PROJECT_BEFORE", "CMAKE_INSTALL_DEFAULT_COMPONENT_NAME", "CMAKE_INSTALL_MESSAGE", "CMAKE_INSTALL_PREFIX", "CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT", "CMAKE_LIBRARY_PATH", "CMAKE_MFC_FLAG", "CMAKE_MODULE_PATH", "CMAKE_NINJA_OUTPUT_PATH_PREFIX", "CMAKE_NOT_USING_CONFIG_FLAGS", "CMAKE_PREFIX_PATH", "CMAKE_PROGRAM_PATH", "CMAKE_SKIP_INSTALL_ALL_DEPENDENCY", "CMAKE_STAGING_PREFIX", "CMAKE_SYSTEM_APPBUNDLE_PATH", "CMAKE_SYSTEM_FRAMEWORK_PATH", "CMAKE_SYSTEM_IGNORE_PATH", "CMAKE_SYSTEM_INCLUDE_PATH", "CMAKE_SYSTEM_LIBRARY_PATH", "CMAKE_SYSTEM_PREFIX_PATH", "CMAKE_SYSTEM_PROGRAM_PATH", "CMAKE_USER_MAKE_RULES_OVERRIDE", "CMAKE_WARN_DEPRECATED", "CMAKE_WARN_ON_ABSOLUTE_INSTALL_DESTINATION" };
        CHANGING_BEHAVIOR_VARIABLES = new String[] { "CMAKE_DISABLE_FIND_PACKAGE_<PackageName>", "CMAKE_PROJECT_<PROJECT-NAME>_INCLUDE", "CMAKE_POLICY_DEFAULT_CMP<NNNN>", "CMAKE_POLICY_WARNING_CMP<NNNN>" };
        CMAKE_POLICY_DEFAULT_HARDCODED = new String[CMakePolicy.knownPoliciesNumber()];
        CMAKE_POLICY_WARNING_HARDCODED = new String[CMakePolicy.knownPoliciesNumber()];
        int i = 0;
        try {
            while (i < CMakeVariableProvider.CMAKE_POLICY_DEFAULT_HARDCODED.length) {
                CMakeVariableProvider.CMAKE_POLICY_DEFAULT_HARDCODED[i] = "CMAKE_POLICY_DEFAULT_" + CMakePolicy.getPolicyName(i);
                ++i;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int j = 0;
        try {
            while (j < CMakeVariableProvider.CMAKE_POLICY_WARNING_HARDCODED.length) {
                CMakeVariableProvider.CMAKE_POLICY_WARNING_HARDCODED[j] = "CMAKE_POLICY_WARNING_" + CMakePolicy.getPolicyName(j);
                ++j;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        SYSTEM_DESCRIPTION_VARIABLES = new String[] { "ANDROID", "APPLE", "BORLAND", "CMAKE_CL_64", "CMAKE_CODELITE_USE_TARGETS", "CMAKE_COMPILER_2005", "CMAKE_HOST_APPLE", "CMAKE_HOST_SOLARIS", "CMAKE_HOST_SYSTEM_NAME", "CMAKE_HOST_SYSTEM_PROCESSOR", "CMAKE_HOST_SYSTEM", "CMAKE_HOST_SYSTEM_VERSION", "CMAKE_HOST_UNIX", "CMAKE_HOST_WIN32", "CMAKE_LIBRARY_ARCHITECTURE_REGEX", "CMAKE_LIBRARY_ARCHITECTURE", "CMAKE_OBJECT_PATH_MAX", "CMAKE_SYSTEM_NAME", "CMAKE_SYSTEM_PROCESSOR", "CMAKE_SYSTEM", "CMAKE_SYSTEM_VERSION", "CMAKE_Swift_LANGUAGE_VERSION", "CYGWIN", "GHS-MULTI", "MINGW", "MSVC10", "MSVC11", "MSVC12", "MSVC14", "MSVC60", "MSVC70", "MSVC71", "MSVC80", "MSVC90", "MSVC_IDE", "MSVC", "MSVC_VERSION", "UNIX", "WIN32", "WINCE", "WINDOWS_PHONE", "WINDOWS_STORE", "XCODE", "XCODE_VERSION" };
        BUILD_CONTROL_VARIABLES = new String[] { "CMAKE_ANDROID_ANT_ADDITIONAL_OPTIONS", "CMAKE_ANDROID_API", "CMAKE_ANDROID_API_MIN", "CMAKE_ANDROID_ARCH", "CMAKE_ANDROID_ARCH_ABI", "CMAKE_ANDROID_ARM_MODE", "CMAKE_ANDROID_ARM_NEON", "CMAKE_ANDROID_ASSETS_DIRECTORIES", "CMAKE_ANDROID_GUI", "CMAKE_ANDROID_JAR_DEPENDENCIES", "CMAKE_ANDROID_JAR_DIRECTORIES", "CMAKE_ANDROID_JAVA_SOURCE_DIR", "CMAKE_ANDROID_NATIVE_LIB_DEPENDENCIES", "CMAKE_ANDROID_NATIVE_LIB_DIRECTORIES", "CMAKE_ANDROID_NDK", "CMAKE_ANDROID_NDK_TOOLCHAIN_HOST_TAG", "CMAKE_ANDROID_NDK_TOOLCHAIN_VERSION", "CMAKE_ANDROID_PROCESS_MAX", "CMAKE_ANDROID_PROGUARD", "CMAKE_ANDROID_PROGUARD_CONFIG_PATH", "CMAKE_ANDROID_SECURE_PROPS_PATH", "CMAKE_ANDROID_SKIP_ANT_STEP", "CMAKE_ANDROID_STANDALONE_TOOLCHAIN", "CMAKE_ANDROID_STL_TYPE", "CMAKE_ARCHIVE_OUTPUT_DIRECTORY", "CMAKE_ARCHIVE_OUTPUT_DIRECTORY_<CONFIG>", "CMAKE_AUTOMOC_MOC_OPTIONS", "CMAKE_AUTOMOC", "CMAKE_AUTORCC", "CMAKE_AUTORCC_OPTIONS", "CMAKE_AUTOUIC", "CMAKE_AUTOUIC_OPTIONS", "CMAKE_BUILD_WITH_INSTALL_RPATH", "CMAKE_COMPILE_PDB_OUTPUT_DIRECTORY", "CMAKE_COMPILE_PDB_OUTPUT_DIRECTORY_<CONFIG>", "CMAKE_DEBUG_POSTFIX", "CMAKE_<CONFIG>_POSTFIX", "CMAKE_EXE_LINKER_FLAGS", "CMAKE_EXE_LINKER_FLAGS_INIT", "CMAKE_EXE_LINKER_FLAGS_<CONFIG>", "CMAKE_EXE_LINKER_FLAGS_<CONFIG>_INIT", "CMAKE_Fortran_FORMAT", "CMAKE_Fortran_MODULE_DIRECTORY", "CMAKE_GNUtoMS", "CMAKE_INCLUDE_CURRENT_DIR_IN_INTERFACE", "CMAKE_INCLUDE_CURRENT_DIR", "CMAKE_INSTALL_NAME_DIR", "CMAKE_INSTALL_RPATH", "CMAKE_INSTALL_RPATH_USE_LINK_PATH", "CMAKE_IOS_INSTALL_COMBINED", "CMAKE_LIBRARY_OUTPUT_DIRECTORY", "CMAKE_LIBRARY_OUTPUT_DIRECTORY_<CONFIG>", "CMAKE_LIBRARY_PATH_FLAG", "CMAKE_LINK_DEF_FILE_FLAG", "CMAKE_LINK_DEPENDS_NO_SHARED", "CMAKE_LINK_INTERFACE_LIBRARIES", "CMAKE_LINK_LIBRARY_FILE_FLAG", "CMAKE_LINK_LIBRARY_FLAG", "CMAKE_LINK_SEARCH_END_STATIC", "CMAKE_LINK_SEARCH_START_STATIC", "CMAKE_LINK_WHAT_YOU_USE", "CMAKE_MACOSX_BUNDLE", "CMAKE_MACOSX_RPATH", "CMAKE_MAP_IMPORTED_CONFIG_<CONFIG>", "CMAKE_MODULE_LINKER_FLAGS", "CMAKE_MODULE_LINKER_FLAGS_INIT", "CMAKE_MODULE_LINKER_FLAGS_<CONFIG>", "CMAKE_MODULE_LINKER_FLAGS_<CONFIG>_INIT", "CMAKE_NO_BUILTIN_CHRPATH", "CMAKE_NO_SYSTEM_FROM_IMPORTED", "CMAKE_OSX_ARCHITECTURES", "CMAKE_OSX_DEPLOYMENT_TARGET", "CMAKE_OSX_SYSROOT", "CMAKE_PDB_OUTPUT_DIRECTORY", "CMAKE_PDB_OUTPUT_DIRECTORY_<CONFIG>", "CMAKE_POSITION_INDEPENDENT_CODE", "CMAKE_RUNTIME_OUTPUT_DIRECTORY", "CMAKE_RUNTIME_OUTPUT_DIRECTORY_<CONFIG>", "CMAKE_SHARED_LINKER_FLAGS", "CMAKE_SHARED_LINKER_FLAGS_<CONFIG>_INIT", "CMAKE_SHARED_LINKER_FLAGS_INIT", "CMAKE_SHARED_LINKER_FLAGS_<CONFIG>", "CMAKE_SKIP_BUILD_RPATH", "CMAKE_SKIP_INSTALL_RPATH", "CMAKE_STATIC_LINKER_FLAGS", "CMAKE_STATIC_LINKER_FLAGS_<CONFIG>", "CMAKE_STATIC_LINKER_FLAGS_<CONFIG>_INIT", "CMAKE_STATIC_LINKER_FLAGS_INIT", "CMAKE_TRY_COMPILE_CONFIGURATION", "CMAKE_TRY_COMPILE_PLATFORM_VARIABLES", "CMAKE_TRY_COMPILE_TARGET_TYPE", "CMAKE_USE_RELATIVE_PATHS", "CMAKE_VISIBILITY_INLINES_HIDDEN", "CMAKE_VS_INCLUDE_INSTALL_TO_DEFAULT_BUILD", "CMAKE_VS_INCLUDE_PACKAGE_TO_DEFAULT_BUILD", "CMAKE_VS_WINDOWS_TARGET_PLATFORM_VERSION", "CMAKE_WIN32_EXECUTABLE", "CMAKE_WINDOWS_EXPORT_ALL_SYMBOLS", "CMAKE_XCODE_ATTRIBUTE_<an-attribute>", "EXECUTABLE_OUTPUT_PATH", "LIBRARY_OUTPUT_PATH" };
        CMAKE_INCLUDE_WHAT_YOU_USE_HARDCODED = a("CMAKE_", "_INCLUDE_WHAT_YOU_USE");
        CMAKE_VISIBILITY_PRESET_HARDCODED = a("CMAKE_", "_VISIBILITY_PRESET");
        LANGUAGES_VARIABLES = new String[] { "CMAKE_C_COMPILE_FEATURES", "CMAKE_C_EXTENSIONS", "CMAKE_C_STANDARD", "CMAKE_C_STANDARD_REQUIRED", "CMAKE_CXX_COMPILE_FEATURES", "CMAKE_CXX_EXTENSIONS", "CMAKE_CXX_STANDARD", "CMAKE_CXX_STANDARD_REQUIRED", "CMAKE_Fortran_MODDIR_DEFAULT", "CMAKE_Fortran_MODDIR_FLAG", "CMAKE_Fortran_MODOUT_FLAG", "CMAKE_INTERNAL_PLATFORM_ABI" };
        CMAKE_COMPILER_IS_GNU_VARIABLES = new String[CMakeRecognizedGnuLanguage.values().length];
        final CMakeRecognizedGnuLanguage[] values = CMakeRecognizedGnuLanguage.values();
        int k = 0;
        try {
            while (k < values.length) {
                CMakeVariableProvider.CMAKE_COMPILER_IS_GNU_VARIABLES[k] = "CMAKE_COMPILER_IS_GNU" + values[k].getLanguage();
                ++k;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        CHANGING_RECOGNIZED_LANGUAGES_VARIABLES = new String[] { "CMAKE_<LANG>_ANDROID_TOOLCHAIN_MACHINE", "CMAKE_<LANG>_ANDROID_TOOLCHAIN_PREFIX", "CMAKE_<LANG>_ANDROID_TOOLCHAIN_SUFFIX", "CMAKE_<LANG>_ARCHIVE_APPEND", "CMAKE_<LANG>_ARCHIVE_CREATE", "CMAKE_<LANG>_ARCHIVE_FINISH", "CMAKE_<LANG>_COMPILE_OBJECT", "CMAKE_<LANG>_COMPILER_ABI", "CMAKE_<LANG>_COMPILER_ID", "CMAKE_<LANG>_COMPILER_LAUNCHER", "CMAKE_<LANG>_COMPILER_LOADED", "CMAKE_<LANG>_CLANG_TIDY", "CMAKE_<LANG>_CPPLINT", "CMAKE_<LANG>_COMPILER", "CMAKE_<LANG>_COMPILER_EXTERNAL_TOOLCHAIN", "CMAKE_<LANG>_COMPILER_TARGET", "CMAKE_<LANG>_COMPILER_VERSION", "CMAKE_<LANG>_CREATE_SHARED_LIBRARY", "CMAKE_<LANG>_CREATE_SHARED_MODULE", "CMAKE_<LANG>_CREATE_STATIC_LIBRARY", "CMAKE_<LANG>_FLAGS_DEBUG", "CMAKE_<LANG>_FLAGS_DEBUG_INIT", "CMAKE_<LANG>_FLAGS_INIT", "CMAKE_<LANG>_FLAGS_MINSIZEREL", "CMAKE_<LANG>_FLAGS_MINSIZEREL_INIT", "CMAKE_<LANG>_FLAGS_RELEASE", "CMAKE_<LANG>_FLAGS_RELEASE_INIT", "CMAKE_<LANG>_FLAGS_RELWITHDEBINFO", "CMAKE_<LANG>_FLAGS_RELWITHDEBINFO_INIT", "CMAKE_<LANG>_FLAGS", "CMAKE_<LANG>_GHS_KERNEL_FLAGS_DEBUG", "CMAKE_<LANG>_GHS_KERNEL_FLAGS_MINSIZEREL", "CMAKE_<LANG>_GHS_KERNEL_FLAGS_RELEASE", "CMAKE_<LANG>_GHS_KERNEL_FLAGS_RELWITHDEBINFO", "CMAKE_<LANG>_IGNORE_EXTENSIONS", "CMAKE_<LANG>_IMPLICIT_INCLUDE_DIRECTORIES", "CMAKE_<LANG>_IMPLICIT_LINK_DIRECTORIES", "CMAKE_<LANG>_IMPLICIT_LINK_FRAMEWORK_DIRECTORIES", "CMAKE_<LANG>_IMPLICIT_LINK_LIBRARIES", "CMAKE_<LANG>_LIBRARY_ARCHITECTURE", "CMAKE_<LANG>_LINKER_PREFERENCE_PROPAGATES", "CMAKE_<LANG>_LINKER_PREFERENCE", "CMAKE_<LANG>_LINK_EXECUTABLE", "CMAKE_<LANG>_OUTPUT_EXTENSION", "CMAKE_<LANG>_PLATFORM_ID", "CMAKE_<LANG>_SIMULATE_ID", "CMAKE_<LANG>_SIMULATE_VERSION", "CMAKE_<LANG>_SIZEOF_DATA_PTR", "CMAKE_<LANG>_SOURCE_FILE_EXTENSIONS", "CMAKE_<LANG>_STANDARD_INCLUDE_DIRECTORIES", "CMAKE_<LANG>_STANDARD_LIBRARIES", "CMAKE_USER_MAKE_RULES_OVERRIDE_<LANG>", "CMAKE_<LANG>_INCLUDE_WHAT_YOU_USE", "CMAKE_<LANG>_VISIBILITY_PRESET" };
        CHANGING_RECOGNIZED_LANGUAGES_VARIABLES_HARDCODED = new String[CMakeVariableProvider.CHANGING_RECOGNIZED_LANGUAGES_VARIABLES.length * CMakeRecognizedLanguage.values().length];
        int n = 0;
        for (final String s : CMakeVariableProvider.CHANGING_RECOGNIZED_LANGUAGES_VARIABLES) {
            final CMakeRecognizedLanguage[] values2 = CMakeRecognizedLanguage.values();
            for (int length2 = values2.length, n2 = 0; n2 < length2; ++n2) {
                CMakeVariableProvider.CHANGING_RECOGNIZED_LANGUAGES_VARIABLES_HARDCODED[n] = s.replace("<LANG>", values2[n2].getLanguage());
                ++n;
            }
        }
        CTEST_VARIABLES = new String[] { "CTEST_BINARY_DIRECTORY", "CTEST_BUILD_COMMAND", "CTEST_BUILD_NAME", "CTEST_BZR_COMMAND", "CTEST_BZR_UPDATE_OPTIONS", "CTEST_CHANGE_ID", "CTEST_CHECKOUT_COMMAND", "CTEST_CONFIGURATION_TYPE", "CTEST_CONFIGURE_COMMAND", "CTEST_COVERAGE_COMMAND", "CTEST_COVERAGE_EXTRA_FLAGS", "CTEST_CURL_OPTIONS", "CTEST_CUSTOM_COVERAGE_EXCLUDE", "CTEST_CUSTOM_ERROR_EXCEPTION", "CTEST_CUSTOM_ERROR_MATCH", "CTEST_CUSTOM_ERROR_POST_CONTEXT", "CTEST_CUSTOM_ERROR_PRE_CONTEXT", "CTEST_CUSTOM_MAXIMUM_FAILED_TEST_OUTPUT_SIZE", "CTEST_CUSTOM_MAXIMUM_NUMBER_OF_ERRORS", "CTEST_CUSTOM_MAXIMUM_NUMBER_OF_WARNINGS", "CTEST_CUSTOM_MAXIMUM_PASSED_TEST_OUTPUT_SIZE", "CTEST_CUSTOM_MEMCHECK_IGNORE", "CTEST_CUSTOM_POST_MEMCHECK", "CTEST_CUSTOM_POST_TEST", "CTEST_CUSTOM_PRE_MEMCHECK", "CTEST_CUSTOM_PRE_TEST", "CTEST_CUSTOM_TEST_IGNORE", "CTEST_CUSTOM_WARNING_EXCEPTION", "CTEST_CUSTOM_WARNING_MATCH", "CTEST_CVS_CHECKOUT", "CTEST_CVS_COMMAND", "CTEST_CVS_UPDATE_OPTIONS", "CTEST_DROP_LOCATION", "CTEST_DROP_METHOD", "CTEST_DROP_SITE", "CTEST_DROP_SITE_CDASH", "CTEST_DROP_SITE_PASSWORD", "CTEST_DROP_SITE_USER", "CTEST_EXTRA_COVERAGE_GLOB", "CTEST_GIT_COMMAND", "CTEST_GIT_INIT_SUBMODULES", "CTEST_GIT_UPDATE_CUSTOM", "CTEST_GIT_UPDATE_OPTIONS", "CTEST_HG_COMMAND", "CTEST_HG_UPDATE_OPTIONS", "CTEST_MEMORYCHECK_COMMAND", "CTEST_MEMORYCHECK_COMMAND_OPTIONS", "CTEST_MEMORYCHECK_SANITIZER_OPTIONS", "CTEST_MEMORYCHECK_SUPPRESSIONS_FILE", "CTEST_MEMORYCHECK_TYPE", "CTEST_NIGHTLY_START_TIME", "CTEST_P4_CLIENT", "CTEST_P4_COMMAND", "CTEST_P4_OPTIONS", "CTEST_P4_UPDATE_OPTIONS", "CTEST_SCP_COMMAND", "CTEST_SITE", "CTEST_SOURCE_DIRECTORY", "CTEST_SVN_COMMAND", "CTEST_SVN_OPTIONS", "CTEST_SVN_UPDATE_OPTIONS", "CTEST_TEST_LOAD", "CTEST_TEST_TIMEOUT", "CTEST_TRIGGER_SITE", "CTEST_UPDATE_COMMAND", "CTEST_UPDATE_OPTIONS", "CTEST_UPDATE_VERSION_ONLY", "CTEST_USE_LAUNCHERS" };
        CPACK_VARIABLES = new String[] { "CPACK_ABSOLUTE_DESTINATION_FILES", "CPACK_COMPONENT_INCLUDE_TOPLEVEL_DIRECTORY", "CPACK_ERROR_ON_ABSOLUTE_INSTALL_DESTINATION", "CPACK_INCLUDE_TOPLEVEL_DIRECTORY", "CPACK_INSTALL_SCRIPT", "CPACK_PACKAGING_INSTALL_PREFIX", "CPACK_SET_DESTDIR", "CPACK_WARN_ON_ABSOLUTE_INSTALL_DESTINATION" };
        ALL_COMPLETION_VARIABLE_ARRAYS = new String[][] { CMakeVariableProvider.INFORMATION_VARIABLES, CMakeVariableProvider.BEHAVIOR_VARIABLES, CMakeVariableProvider.SYSTEM_DESCRIPTION_VARIABLES, CMakeVariableProvider.BUILD_CONTROL_VARIABLES, CMakeVariableProvider.LANGUAGES_VARIABLES, CMakeVariableProvider.CTEST_VARIABLES, CMakeVariableProvider.CPACK_VARIABLES, CMakeVariableProvider.CMAKE_INCLUDE_WHAT_YOU_USE_HARDCODED, CMakeVariableProvider.CMAKE_VISIBILITY_PRESET_HARDCODED, CMakeVariableProvider.CMAKE_COMPILER_IS_GNU_VARIABLES, CMakeVariableProvider.CHANGING_RECOGNIZED_LANGUAGES_VARIABLES_HARDCODED };
        ALL_VARIABLES = ContainerUtil.collect((Iterator)ContainerUtil.concat((Object[][])new String[][] { CMakeVariableProvider.INFORMATION_VARIABLES, CMakeVariableProvider.BEHAVIOR_VARIABLES, CMakeVariableProvider.SYSTEM_DESCRIPTION_VARIABLES, CMakeVariableProvider.BUILD_CONTROL_VARIABLES, CMakeVariableProvider.LANGUAGES_VARIABLES, CMakeVariableProvider.CTEST_VARIABLES, CMakeVariableProvider.CPACK_VARIABLES, CMakeVariableProvider.CHANGING_RECOGNIZED_LANGUAGES_VARIABLES, CMakeVariableProvider.CMAKE_COMPILER_IS_GNU_VARIABLES, CMakeVariableProvider.CHANGING_BEHAVIOR_VARIABLES, CMakeVariableProvider.CHANGING_INFORMATION_VARIABLES }).iterator());
        int n3 = 0;
        final String[][] all_COMPLETION_VARIABLE_ARRAYS = CMakeVariableProvider.ALL_COMPLETION_VARIABLE_ARRAYS;
        for (int length3 = all_COMPLETION_VARIABLE_ARRAYS.length, n4 = 0; n4 < length3; ++n4) {
            n3 += all_COMPLETION_VARIABLE_ARRAYS[n4].length;
        }
        ALL_COMPLETION_VARIABLES = new String[n3];
        int n5 = 0;
        for (final String[] array : CMakeVariableProvider.ALL_COMPLETION_VARIABLE_ARRAYS) {
            for (int length5 = array.length, n7 = 0; n7 < length5; ++n7) {
                CMakeVariableProvider.ALL_COMPLETION_VARIABLES[n5] = array[n7];
                ++n5;
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class FirstLetterCaseMatcher extends CamelHumpMatcher
    {
        private final int myIndexOfFirstVariableChar;
        
        public FirstLetterCaseMatcher(@NotNull final String s, final int myIndexOfFirstVariableChar) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher", "<init>"));
            }
            super(s);
            this.myIndexOfFirstVariableChar = myIndexOfFirstVariableChar;
        }
        
        @Override
        public boolean prefixMatches(@NotNull final String p0) {
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
            //    18: ldc             "name"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "prefixMatches"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.myPrefix:Ljava/lang/String;
            //    48: invokevirtual   java/lang/String.length:()I
            //    51: ifle            75
            //    54: aload_0        
            //    55: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.myPrefix:Ljava/lang/String;
            //    58: iconst_0       
            //    59: invokevirtual   java/lang/String.charAt:(I)C
            //    62: invokestatic    java/lang/Character.isUpperCase:(C)Z
            //    65: ifne            92
            //    68: goto            75
            //    71: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    74: athrow         
            //    75: aload_0        
            //    76: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.myPrefix:Ljava/lang/String;
            //    79: invokevirtual   java/lang/String.length:()I
            //    82: ifne            115
            //    85: goto            92
            //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    91: athrow         
            //    92: aload_0        
            //    93: aload_1        
            //    94: invokespecial   com/intellij/codeInsight/completion/impl/CamelHumpMatcher.prefixMatches:(Ljava/lang/String;)Z
            //    97: ifeq            115
            //   100: goto            107
            //   103: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   106: athrow         
            //   107: iconst_1       
            //   108: goto            116
            //   111: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   114: athrow         
            //   115: iconst_0       
            //   116: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     68     71     75     Ljava/lang/IllegalArgumentException;
            //  54     85     88     92     Ljava/lang/IllegalArgumentException;
            //  75     100    103    107    Ljava/lang/IllegalArgumentException;
            //  92     111    111    115    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public PrefixMatcher cloneWithPrefix(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher", "cloneWithPrefix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            FirstLetterCaseMatcher firstLetterCaseMatcher;
            try {
                firstLetterCaseMatcher = new FirstLetterCaseMatcher(s, this.myIndexOfFirstVariableChar);
                if (firstLetterCaseMatcher == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher", "cloneWithPrefix"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return firstLetterCaseMatcher;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
