// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementCppActionContext;
import java.util.List;
import com.intellij.openapi.command.WriteCommandAction;

class OCOverrideImplementCppFunctionsHandler$2 extends WriteCommandAction {
    protected void run(@NotNull final Result p0) throws Throwable {
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
        //    18: ldc             "result"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "run"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$chosenCandidates:Ljava/util/List;
        //    48: invokedynamic   compare:()Ljava/util/Comparator;
        //    53: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$chosenCandidates:Ljava/util/List;
        //    60: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    65: astore_2       
        //    66: aload_2        
        //    67: invokeinterface java/util/Iterator.hasNext:()Z
        //    72: ifeq            195
        //    75: aload_2        
        //    76: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    81: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    84: astore_3       
        //    85: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix;
        //    88: dup            
        //    89: aload_3        
        //    90: iconst_0       
        //    91: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)V
        //    94: astore          4
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;
        //   100: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //   103: astore          5
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;
        //   109: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getEditorFile:()Lcom/intellij/psi/PsiFile;
        //   112: astore          6
        //   114: aload_0        
        //   115: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;
        //   118: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   121: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   124: aload_3        
        //   125: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   128: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.equals:(Ljava/lang/Object;)Z
        //   131: ifne            192
        //   134: aload_3        
        //   135: iconst_0       
        //   136: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.findFirstVirtual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   139: ifnonnull       192
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   148: athrow         
        //   149: aload           4
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$project:Lcom/intellij/openapi/project/Project;
        //   155: aload           5
        //   157: aload           6
        //   159: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //   162: ifeq            192
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   171: athrow         
        //   172: aload           4
        //   174: aload_0        
        //   175: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$project:Lcom/intellij/openapi/project/Project;
        //   178: aload           5
        //   180: aload           6
        //   182: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   191: athrow         
        //   192: goto            66
        //   195: return         
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      40     40     44     Ljava/lang/Throwable;
        //  114    142    145    149    Ljava/lang/Throwable;
        //  134    165    168    172    Ljava/lang/Throwable;
        //  149    185    188    192    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0149:
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
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}