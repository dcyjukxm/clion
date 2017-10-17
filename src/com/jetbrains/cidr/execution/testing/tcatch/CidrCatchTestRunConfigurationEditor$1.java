// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import java.util.Set;
import com.intellij.openapi.util.Pair;
import com.intellij.codeInsight.completion.PrefixMatcher;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import javax.swing.Icon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.ui.OCFieldAdapter;

class CidrCatchTestRunConfigurationEditor$1 implements OCFieldAdapter<String> {
    @NotNull
    @Contract(pure = true)
    @Override
    public String getName(final String s) {
        String s2 = null;
        Label_0014: {
            try {
                if (s == null) {
                    final String s3;
                    s2 = (s3 = "non-tagged");
                    break Label_0014;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            s2 = s;
            String s3 = s;
            try {
                if (s3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getName"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return s2;
    }
    
    @Contract(value = "_ -> null", pure = true)
    @Override
    public Icon getIcon(final String s) {
        return null;
    }
    
    @Contract(value = "_ -> null", pure = true)
    @Override
    public String getTypeText(final String s) {
        return null;
    }
    
    @NotNull
    @Contract(pure = true)
    @Override
    public String getReadableName(final String s) {
        String name;
        try {
            name = this.getName(s);
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getReadableName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    @Override
    public Collection<String> collectValuesFromProject(@NotNull final Project p0, @Nullable final Condition<String> p1) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "collectValuesFromProject"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.this$0:Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.this$0:Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;
        //    52: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor.access$000:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;)Lcom/intellij/openapi/project/Project;
        //    55: aload_2        
        //    56: invokedynamic   accept:(Lcom/intellij/openapi/util/Condition;)Ljava/util/function/Consumer;
        //    61: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor.access$100:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;Lcom/intellij/openapi/project/Project;Ljava/util/function/Consumer;)Ljava/util/Set;
        //    64: dup            
        //    65: ifnonnull       102
        //    68: new             Ljava/lang/IllegalStateException;
        //    71: dup            
        //    72: ldc             "@NotNull method %s.%s must not return null"
        //    74: ldc             2
        //    76: anewarray       Ljava/lang/Object;
        //    79: dup            
        //    80: ldc             0
        //    82: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1"
        //    84: aastore        
        //    85: dup            
        //    86: ldc             1
        //    88: ldc             "collectValuesFromProject"
        //    90: aastore        
        //    91: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    94: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    97: athrow         
        //    98: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   101: athrow         
        //   102: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/util/Condition<Ljava/lang/String;>;)Ljava/util/Collection<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     98     98     102    Ljava/lang/IllegalStateException;
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
    
    @Contract(pure = true)
    @NotNull
    @Override
    public PrefixMatcher createPrefixMatcher(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "createPrefixMatcher"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        TagPrefixMatcher tagPrefixMatcher;
        try {
            tagPrefixMatcher = new TagPrefixMatcher(s);
            if (tagPrefixMatcher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "createPrefixMatcher"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return tagPrefixMatcher;
    }
    
    @NotNull
    @Override
    public String getPrefix(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getPrefix"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final int n2 = Math.max(s.lastIndexOf(93, n - 1), s.lastIndexOf(32, n - 1)) + 1;
        final int n3 = s.lastIndexOf(10, n - 1) + 1;
        String substring;
        try {
            substring = s.substring(Math.max(n2, n3), n);
            if (substring == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getPrefix"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return substring;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}