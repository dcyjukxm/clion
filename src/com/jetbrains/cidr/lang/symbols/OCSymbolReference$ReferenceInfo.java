// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import org.jetbrains.annotations.NotNull;

private static class ReferenceInfo
{
    @NotNull
    final OCSymbolReference symbolReference;
    @NotNull
    final OCTypeSubstitution substitution;
    @Nullable
    final OCResolveContext context;
    private final boolean resolveSpecialization;
    
    private ReferenceInfo(@NotNull final OCSymbolReference symbolReference, @NotNull final OCTypeSubstitution substitution, @Nullable final OCResolveContext context, final boolean resolveSpecialization) {
        if (symbolReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolReference", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo", "<init>"));
        }
        if (substitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo", "<init>"));
        }
        this.symbolReference = symbolReference;
        this.substitution = substitution;
        this.context = context;
        this.resolveSpecialization = resolveSpecialization;
    }
    
    @Override
    public boolean equals(final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo;
        //     4: ifne            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo;
        //    17: astore_2       
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.resolveSpecialization:Z
        //    22: aload_2        
        //    23: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.resolveSpecialization:Z
        //    26: if_icmpne       93
        //    29: aload_0        
        //    30: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.context:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    33: ifnull          93
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_0        
        //    44: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.symbolReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //    47: aload_2        
        //    48: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.symbolReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //    51: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.equals:(Ljava/lang/Object;)Z
        //    54: ifeq            93
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_0        
        //    65: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.substitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    68: aload_2        
        //    69: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.substitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    72: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.equals:(Ljava/lang/Object;)Z
        //    75: ifeq            93
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_1       
        //    86: goto            94
        //    89: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_0       
        //    94: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  18     36     39     43     Ljava/lang/IllegalArgumentException;
        //  29     57     60     64     Ljava/lang/IllegalArgumentException;
        //  43     78     81     85     Ljava/lang/IllegalArgumentException;
        //  64     89     89     93     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    @Override
    public int hashCode() {
        final int n = 31 * this.symbolReference.hashCode() + this.substitution.hashCode();
        int n2;
        try {
            n2 = 31 * n;
            if (this.resolveSpecialization) {
                final int n3 = 1;
                return n2 + n3;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int n3 = 0;
        return n2 + n3;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
