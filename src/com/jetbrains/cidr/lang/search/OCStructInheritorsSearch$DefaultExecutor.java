// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import java.util.Set;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.HashSet;
import com.intellij.util.containers.Stack;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.openapi.application.QueryExecutorBase;

private static class DefaultExecutor extends QueryExecutorBase<OCStructSymbol, SearchParameters>
{
    public void processQuery(@NotNull final SearchParameters searchParameters, @NotNull final Processor<OCStructSymbol> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final GlobalSearchScope scope = searchParameters.getScope();
        final Project project = searchParameters.getProject();
        final Stack stack = new Stack();
        final HashSet<OCQualifiedName> set = new HashSet<OCQualifiedName>();
        final HashSet set2 = new HashSet();
        stack.push((Object)searchParameters.getStructName());
        while (!stack.isEmpty()) {
            ProgressManager.checkCanceled();
            final OCQualifiedName ocQualifiedName = (OCQualifiedName)stack.pop();
            try {
                if (!set.add(ocQualifiedName)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            OCDirectStructInheritorsSearch.search(ocQualifiedName, GlobalSearchScope.allScope(project), searchParameters.getFile(), project).forEach(p4 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_3        
                //     1: ifnonnull       44
                //     4: new             Ljava/lang/IllegalArgumentException;
                //     7: dup            
                //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
                //    10: ldc             3
                //    12: anewarray       Ljava/lang/Object;
                //    15: dup            
                //    16: ldc             0
                //    18: ldc             "consumer"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "lambda$processQuery$0"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload           4
                //    46: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //    49: astore          5
                //    51: aload_0        
                //    52: aload           5
                //    54: invokevirtual   com/intellij/util/containers/Stack.push:(Ljava/lang/Object;)V
                //    57: aload_1        
                //    58: aload           4
                //    60: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //    63: invokevirtual   com/intellij/psi/search/GlobalSearchScope.contains:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
                //    66: ifeq            111
                //    69: aload_2        
                //    70: aload           5
                //    72: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
                //    77: ifeq            111
                //    80: goto            87
                //    83: invokestatic    com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    86: athrow         
                //    87: aload_3        
                //    88: aload           4
                //    90: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
                //    95: ifne            111
                //    98: goto            105
                //   101: invokestatic    com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   104: athrow         
                //   105: iconst_0       
                //   106: ireturn        
                //   107: invokestatic    com/jetbrains/cidr/lang/search/OCStructInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   110: athrow         
                //   111: iconst_1       
                //   112: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  51     80     83     87     Ljava/lang/IllegalArgumentException;
                //  69     98     101    105    Ljava/lang/IllegalArgumentException;
                //  87     107    107    111    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
            });
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
