// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.ui.OCFieldAdapterForSymbolName;

class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1 extends OCFieldAdapterForSymbolName {
    @NotNull
    @Override
    public String getReadableName(@NotNull final OCSymbol p0) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReadableName"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    48: ifeq            123
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    58: ifnull          123
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_1        
        //    69: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    72: iconst_1       
        //    73: iconst_0       
        //    74: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getSignatureWithoutParamNames:(ZZ)Ljava/lang/String;
        //    77: dup            
        //    78: ifnonnull       122
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: new             Ljava/lang/IllegalStateException;
        //    91: dup            
        //    92: ldc             "@NotNull method %s.%s must not return null"
        //    94: ldc             2
        //    96: anewarray       Ljava/lang/Object;
        //    99: dup            
        //   100: ldc             0
        //   102: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1"
        //   104: aastore        
        //   105: dup            
        //   106: ldc             1
        //   108: ldc             "getReadableName"
        //   110: aastore        
        //   111: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   114: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   117: athrow         
        //   118: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: areturn        
        //   123: aload_1        
        //   124: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getPresentableName:()Ljava/lang/String;
        //   129: dup            
        //   130: ifnonnull       167
        //   133: new             Ljava/lang/IllegalStateException;
        //   136: dup            
        //   137: ldc             "@NotNull method %s.%s must not return null"
        //   139: ldc             2
        //   141: anewarray       Ljava/lang/Object;
        //   144: dup            
        //   145: ldc             0
        //   147: ldc             "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1"
        //   149: aastore        
        //   150: dup            
        //   151: ldc             1
        //   153: ldc             "getReadableName"
        //   155: aastore        
        //   156: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   159: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   162: athrow         
        //   163: invokestatic    com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$1.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     61     64     68     Ljava/lang/IllegalArgumentException;
        //  51     81     84     88     Ljava/lang/IllegalArgumentException;
        //  68     118    118    122    Ljava/lang/IllegalArgumentException;
        //  123    163    163    167    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}