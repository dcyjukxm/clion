// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.intellij.util.Processor;

class 1OffsetProcessor implements Processor
{
    public boolean hasInlineDefinition;
    public boolean hasOutsideDefinition;
    public int maxOffset;
    public PsiFile file;
    final /* synthetic */ OCFile val$topLevelFile;
    
    1OffsetProcessor(final OCFile val$topLevelFile) {
        this.val$topLevelFile = val$topLevelFile;
        this.hasInlineDefinition = false;
        this.hasOutsideDefinition = false;
        this.maxOffset = 0;
        this.file = null;
    }
    
    @Nullable
    Pair<PsiFile, Integer> location() {
        try {
            if (this.file == null) {
                final Pair create = null;
                return (Pair<PsiFile, Integer>)create;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Pair create = Pair.create((Object)this.file, (Object)this.maxOffset);
        return (Pair<PsiFile, Integer>)create;
    }
    
    boolean shouldBeInlined(@NotNull final OCMembersContainer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "parent"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldBeInlined"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.hasOutsideDefinition:Z
        //    48: ifne            87
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.hasInlineDefinition:Z
        //    55: ifne            79
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_1        
        //    66: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.access$100:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;)Z
        //    69: ifne            87
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: iconst_1       
        //    80: goto            88
        //    83: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: iconst_0       
        //    88: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     83     83     87     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    public boolean process(final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: ifne            13
        //     7: iconst_1       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    17: astore_2       
        //    18: aload_2        
        //    19: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //    24: ifeq            209
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.val$topLevelFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    31: ifnull          67
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_2        
        //    42: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    47: aload_0        
        //    48: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.val$topLevelFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    51: if_acmpeq       67
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: iconst_1       
        //    62: ireturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_2        
        //    68: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    73: astore_3       
        //    74: aload_3        
        //    75: ifnull          206
        //    78: aload_3        
        //    79: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    84: astore          4
        //    86: aload           4
        //    88: ifnull          206
        //    91: aload           4
        //    93: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    98: astore          5
        //   100: aload           5
        //   102: ifnull          206
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.file:Lcom/intellij/psi/PsiFile;
        //   109: ifnonnull       152
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload           5
        //   121: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //   124: ifeq            152
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_0        
        //   135: aload           5
        //   137: putfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.file:Lcom/intellij/psi/PsiFile;
        //   140: aload_0        
        //   141: iconst_1       
        //   142: putfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.hasOutsideDefinition:Z
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_0        
        //   153: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.file:Lcom/intellij/psi/PsiFile;
        //   156: ifnull          206
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.file:Lcom/intellij/psi/PsiFile;
        //   163: aload           5
        //   165: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   168: ifeq            206
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_0        
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.maxOffset:I
        //   183: aload           4
        //   185: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   190: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   193: invokestatic    java/lang/Math.max:(II)I
        //   196: putfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.maxOffset:I
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: goto            228
        //   209: aload_2        
        //   210: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   213: ifeq            228
        //   216: aload_0        
        //   217: iconst_1       
        //   218: putfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.hasInlineDefinition:Z
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$1OffsetProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: iconst_1       
        //   229: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  18     34     37     41     Ljava/lang/IllegalArgumentException;
        //  27     54     57     61     Ljava/lang/IllegalArgumentException;
        //  41     63     63     67     Ljava/lang/IllegalArgumentException;
        //  100    112    115    119    Ljava/lang/IllegalArgumentException;
        //  105    127    130    134    Ljava/lang/IllegalArgumentException;
        //  119    145    148    152    Ljava/lang/IllegalArgumentException;
        //  152    171    174    178    Ljava/lang/IllegalArgumentException;
        //  159    199    202    206    Ljava/lang/IllegalArgumentException;
        //  209    221    224    228    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
