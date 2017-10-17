// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionUtils;
import java.util.Collections;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jetbrains.cidr.cpp.cmake.settings.CMakeCodeStyleSettings;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.tree.IElementType;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractCMakeCaseSensitiveCompletionProvider extends AbstractCMakeCompletionProvider
{
    private final List<String> myVariants;
    private final boolean myBoldCompletion;
    private final boolean myPutCaretInsideParentheses;
    
    public AbstractCMakeCaseSensitiveCompletionProvider(final String[] array) {
        this(array, false);
    }
    
    public AbstractCMakeCaseSensitiveCompletionProvider(final String[] array, final boolean b) {
        this(array, b, true);
    }
    
    public AbstractCMakeCaseSensitiveCompletionProvider(final String[] array, final boolean b, final boolean b2) {
        this(Arrays.asList(array), b, b2);
    }
    
    public AbstractCMakeCaseSensitiveCompletionProvider(final IElementType[] array, final boolean b, final boolean b2) {
        this(ContainerUtil.map((Object[])array, elementType -> elementType.toString()), b, b2);
    }
    
    public AbstractCMakeCaseSensitiveCompletionProvider(final List<String> myVariants, final boolean myBoldCompletion, final boolean myPutCaretInsideParentheses) {
        this.myVariants = myVariants;
        this.myBoldCompletion = myBoldCompletion;
        this.myPutCaretInsideParentheses = myPutCaretInsideParentheses;
    }
    
    protected void addCompletions(@NotNull final CompletionParameters p0, final ProcessingContext p1, @NotNull final CompletionResultSet p2) {
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
        //    18: ldc             "parameters"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addCompletions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "result"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "addCompletions"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/intellij/codeInsight/completion/CompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //    92: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //    97: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   100: ldc             Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettings;.class
        //   102: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   105: checkcast       Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettings;
        //   108: astore          4
        //   110: aload           4
        //   112: ifnonnull       125
        //   115: getstatic       com/jetbrains/cidr/cpp/cmake/settings/Case.DO_NOT_CHANGE:Lcom/jetbrains/cidr/cpp/cmake/settings/Case;
        //   118: goto            133
        //   121: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload           4
        //   127: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettings;)I
        //   130: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/Case.resolveByValue:(I)Lcom/jetbrains/cidr/cpp/cmake/settings/Case;
        //   133: astore          5
        //   135: aload_1        
        //   136: invokevirtual   com/intellij/codeInsight/completion/CompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //   139: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   144: ldc             "IntellijIdeaRulezzz"
        //   146: ldc             ""
        //   148: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   151: astore          6
        //   153: aload           5
        //   155: getstatic       com/jetbrains/cidr/cpp/cmake/settings/Case.TO_UPPER:Lcom/jetbrains/cidr/cpp/cmake/settings/Case;
        //   158: if_acmpeq       210
        //   161: aload           5
        //   163: getstatic       com/jetbrains/cidr/cpp/cmake/settings/Case.DO_NOT_CHANGE:Lcom/jetbrains/cidr/cpp/cmake/settings/Case;
        //   166: if_acmpne       218
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload           6
        //   178: invokevirtual   java/lang/String.length:()I
        //   181: ifle            218
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload           6
        //   193: iconst_0       
        //   194: invokevirtual   java/lang/String.charAt:(I)C
        //   197: invokestatic    java/lang/Character.isUpperCase:(C)Z
        //   200: ifeq            218
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: iconst_1       
        //   211: goto            219
        //   214: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: iconst_0       
        //   219: istore          7
        //   221: aload_3        
        //   222: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.caseInsensitive:()Lcom/intellij/codeInsight/completion/CompletionResultSet;
        //   225: aload_0        
        //   226: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider.myVariants:Ljava/util/List;
        //   229: aload_0        
        //   230: iload           7
        //   232: invokedynamic   fun:(Lcom/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider;Z)Lcom/intellij/util/Function;
        //   237: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   240: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addAllElements:(Ljava/lang/Iterable;)V
        //   243: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  110    121    121    125    Ljava/lang/IllegalArgumentException;
        //  153    169    172    176    Ljava/lang/IllegalArgumentException;
        //  161    184    187    191    Ljava/lang/IllegalArgumentException;
        //  176    203    206    210    Ljava/lang/IllegalArgumentException;
        //  191    214    214    218    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0176:
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
    
    private static int a(@NotNull final CMakeCodeStyleSettings cMakeCodeStyleSettings) {
        try {
            if (cMakeCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/AbstractCMakeCaseSensitiveCompletionProvider", "getCaseVariableValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cMakeCodeStyleSettings.FORCE_COMMANDS_CASE;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
