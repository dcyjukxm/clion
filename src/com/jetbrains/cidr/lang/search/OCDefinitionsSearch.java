// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.util.QueryExecutor;

public class OCDefinitionsSearch implements QueryExecutor<PsiElement, PsiElement>
{
    public boolean execute(@NotNull final PsiElement psiElement, @NotNull final Processor<PsiElement> processor) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "subj", "com/jetbrains/cidr/lang/search/OCDefinitionsSearch", "execute"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCDefinitionsSearch", "execute"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return (boolean)ReadAction.compute(() -> {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "subj", "com/jetbrains/cidr/lang/search/OCDefinitionsSearch", "lambda$execute$1"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCDefinitionsSearch", "lambda$execute$1"));
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                if (!(psiElement instanceof OCSymbolDeclarator)) {
                    return true;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            final OCSymbolWithParent<PsiElement, ?> symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            try {
                if (symbol == null) {
                    return true;
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            Label_0148: {
                try {
                    if (psiElement instanceof OCMethod) {
                        break Label_0148;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCDeclarator;
                    if (b) {
                        break Label_0148;
                    }
                    return a(symbol, processor);
                }
                catch (RuntimeException ex5) {
                    throw b(ex5);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCDeclarator;
                    if (!b) {
                        return a(symbol, processor);
                    }
                    if (!(psiElement.getParent() instanceof OCFunctionDeclaration)) {
                        return a(symbol, processor);
                    }
                }
                catch (RuntimeException ex6) {
                    throw b(ex6);
                }
            }
            final OCSymbol<PsiElement> definitionSymbol = symbol.getDefinitionSymbol();
            c((com.intellij.util.Processor<Object>)processor, definitionSymbol);
            OCSearchUtil.processMembersHierarchy(symbol, (com.intellij.util.Processor<? super OCSymbolWithParent<PsiElement, ?>>)(p2 -> {
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
                //    18: ldc             "consumer"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/search/OCDefinitionsSearch"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "lambda$null$0"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    43: athrow         
                //    44: aload_2        
                //    45: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.isDefinition:()Z
                //    50: ifne            69
                //    53: aload_2        
                //    54: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    59: ifnonnull       96
                //    62: goto            69
                //    65: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    68: athrow         
                //    69: aload_2        
                //    70: aload_0        
                //    71: if_acmpeq       96
                //    74: goto            81
                //    77: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    80: athrow         
                //    81: aload_1        
                //    82: aload_2        
                //    83: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.c:(Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
                //    86: ifeq            104
                //    89: goto            96
                //    92: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    95: athrow         
                //    96: iconst_1       
                //    97: goto            105
                //   100: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //   103: athrow         
                //   104: iconst_0       
                //   105: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                        
                //  -----  -----  -----  -----  ----------------------------
                //  0      40     40     44     Ljava/lang/RuntimeException;
                //  44     62     65     69     Ljava/lang/RuntimeException;
                //  53     74     77     81     Ljava/lang/RuntimeException;
                //  69     89     92     96     Ljava/lang/RuntimeException;
                //  81     100    100    104    Ljava/lang/RuntimeException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
            }), false, true, false);
            return true;
        });
    }
    
    @Contract("null, _ -> true")
    private static boolean a(final OCSymbol<?> p0, final Processor<PsiElement> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //     4: ifeq            68
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    11: aload_1        
        //    12: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //    17: iconst_1       
        //    18: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.processAccessorMethods:(Lcom/intellij/util/Processor;Z)Z
        //    23: ifeq            66
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    32: athrow         
        //    33: aload_0        
        //    34: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    37: aload_1        
        //    38: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //    43: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.processSynthesizes:(Lcom/intellij/util/Processor;)Z
        //    48: ifeq            66
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    57: athrow         
        //    58: iconst_1       
        //    59: goto            67
        //    62: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    65: athrow         
        //    66: iconst_0       
        //    67: ireturn        
        //    68: aload_0        
        //    69: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    72: ifeq            136
        //    75: aload_0        
        //    76: aload_0        
        //    77: aload_1        
        //    78: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //    83: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //    88: ifeq            134
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    97: athrow         
        //    98: aload_0        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   102: invokestatic    com/jetbrains/cidr/lang/search/OCClassInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Lcom/intellij/util/Query;
        //   105: aload_1        
        //   106: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //   111: invokeinterface com/intellij/util/Query.forEach:(Lcom/intellij/util/Processor;)Z
        //   116: ifeq            134
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   125: athrow         
        //   126: iconst_1       
        //   127: goto            135
        //   130: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   133: athrow         
        //   134: iconst_0       
        //   135: ireturn        
        //   136: aload_0        
        //   137: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   140: ifeq            190
        //   143: aload_0        
        //   144: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   149: astore_2       
        //   150: aload_0        
        //   151: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   154: aload_2        
        //   155: invokestatic    com/jetbrains/cidr/lang/search/OCStructInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/intellij/psi/PsiElement;)Lcom/intellij/util/Query;
        //   158: astore_3       
        //   159: aload_3        
        //   160: aload_1        
        //   161: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //   166: invokeinterface com/intellij/util/Query.forEach:(Lcom/intellij/util/Processor;)Z
        //   171: pop            
        //   172: aload_0        
        //   173: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   176: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.getRelatedSymbols:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Ljava/util/List;
        //   179: aload_1        
        //   180: invokedynamic   accept:(Lcom/intellij/util/Processor;)Ljava/util/function/Consumer;
        //   185: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   190: aload_0        
        //   191: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCLocalizedStringSymbol;
        //   194: ifeq            217
        //   197: aload_0        
        //   198: aload_1        
        //   199: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //   204: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   209: pop            
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/search/OCDefinitionsSearch.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   216: athrow         
        //   217: iconst_1       
        //   218: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCSymbol<*>;Lcom/intellij/util/Processor<Lcom/intellij/psi/PsiElement;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      26     29     33     Ljava/lang/RuntimeException;
        //  7      51     54     58     Ljava/lang/RuntimeException;
        //  33     62     62     66     Ljava/lang/RuntimeException;
        //  68     91     94     98     Ljava/lang/RuntimeException;
        //  75     119    122    126    Ljava/lang/RuntimeException;
        //  98     130    130    134    Ljava/lang/RuntimeException;
        //  190    210    213    217    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
    
    @Contract("_, null -> true")
    private static <T> boolean c(final Processor<T> processor, final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        try {
            if (locateDefinition != null) {
                return processor.process((Object)locateDefinition);
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
