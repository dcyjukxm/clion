// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

private static class FuncDescriptor
{
    @NotNull
    public final OCFunctionSymbol symbol;
    @NotNull
    private final OCFunctionType myResolvedType;
    @NotNull
    private final OCQualifiedName myName;
    @NotNull
    private final OCTypeEqualityAfterResolvingVisitor myVisitor;
    
    public FuncDescriptor(@NotNull final OCFunctionSymbol symbol, @NotNull final OCFunctionType myResolvedType, @NotNull final OCQualifiedName myName, @NotNull final OCResolveContext ocResolveContext) {
        if (symbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
        }
        if (myResolvedType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
        }
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
        }
        this.symbol = symbol;
        this.myResolvedType = myResolvedType;
        this.myName = myName;
        this.myVisitor = new OCTypeEqualityAfterResolvingVisitor(this.myResolvedType, false, ocResolveContext);
    }
    
    public boolean isUndistinguishable(@NotNull final FuncDescriptor p0) {
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
        //    18: ldc             "other"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isUndistinguishable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.symbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    48: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
        //    51: ifne            92
        //    54: aload_1        
        //    55: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.symbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    58: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
        //    61: ifne            92
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    75: aload_1        
        //    76: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    79: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.equals:(Ljava/lang/Object;)Z
        //    82: ifeq            121
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myVisitor:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
        //    96: aload_1        
        //    97: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myResolvedType:Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   100: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.isFunctionSignatureEqual:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   103: ifeq            121
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_1       
        //   114: goto            122
        //   117: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: iconst_0       
        //   122: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     64     67     71     Ljava/lang/IllegalArgumentException;
        //  54     85     88     92     Ljava/lang/IllegalArgumentException;
        //  71     106    109    113    Ljava/lang/IllegalArgumentException;
        //  92     117    117    121    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
