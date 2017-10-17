// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;

public abstract class OCExpressionWithReferenceBase<T extends PsiReference> extends OCExpressionBase
{
    protected T myReference;
    
    public OCExpressionWithReferenceBase(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase", "<init>"));
        }
        super(astNode);
    }
    
    public final T getReference() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.myReference:Lcom/intellij/psi/PsiReference;
        //     4: ifnull          46
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.myReference:Lcom/intellij/psi/PsiReference;
        //    11: instanceof      Lcom/jetbrains/cidr/lang/resolve/references/OCStatefulReference;
        //    14: ifeq            61
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.myReference:Lcom/intellij/psi/PsiReference;
        //    28: checkcast       Lcom/jetbrains/cidr/lang/resolve/references/OCStatefulReference;
        //    31: invokeinterface com/jetbrains/cidr/lang/resolve/references/OCStatefulReference.isValid:()Z
        //    36: ifne            61
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_0        
        //    47: aload_0        
        //    48: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.createReference:()Lcom/intellij/psi/PsiReference;
        //    51: putfield        com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.myReference:Lcom/intellij/psi/PsiReference;
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: aload_0        
        //    62: getfield        com/jetbrains/cidr/lang/psi/impl/OCExpressionWithReferenceBase.myReference:Lcom/intellij/psi/PsiReference;
        //    65: areturn        
        //    Signature:
        //  ()TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      39     42     46     Ljava/lang/IllegalArgumentException;
        //  24     54     57     61     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    @Nullable
    protected abstract T createReference();
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
