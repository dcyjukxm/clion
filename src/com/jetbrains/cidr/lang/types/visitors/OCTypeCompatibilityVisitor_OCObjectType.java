// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCObjectType;

class OCTypeCompatibilityVisitor_OCObjectType extends OCTypeCompatibilityVisitor<OCObjectType>
{
    protected OCTypeCompatibilityVisitor_OCObjectType(@NotNull final OCObjectType ocObjectType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocObjectType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType", "<init>"));
        }
        super(ocObjectType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        return this.visitType(ocFunctionType);
    }
    
    @Override
    public OCType.TypeCheckResult visitObjectType(final OCObjectType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     5: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isAncestorOf:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Z
        //    11: ifne            55
        //    14: aload_0        
        //    15: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    18: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    21: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isKindof:()Z
        //    24: ifeq            63
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    38: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    41: aload_1        
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isAncestorOf:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Z
        //    45: ifeq            63
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: iconst_1       
        //    56: goto            64
        //    59: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_0       
        //    64: istore_2       
        //    65: iload_2        
        //    66: ifne            119
        //    69: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType$1;
        //    72: dup            
        //    73: aload_0        
        //    74: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    77: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotSuperclass;.class
        //    79: ldc             "CIDR"
        //    81: iconst_1       
        //    82: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //    85: dup            
        //    86: iconst_0       
        //    87: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction;
        //    90: dup            
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    95: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   101: aload_1        
        //   102: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   105: aconst_null    
        //   106: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   109: aastore        
        //   110: aload_1        
        //   111: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType$1.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCObjectType;)V
        //   114: areturn        
        //   115: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_0        
        //   120: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isKindof:()Z
        //   129: ifne            149
        //   132: aload_0        
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   137: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   140: aload_1        
        //   141: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.getProtocolCompatibilityCheckResult:(Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/types/OCObjectType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   144: areturn        
        //   145: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCObjectType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   152: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      27     30     34     Ljava/lang/IllegalArgumentException;
        //  14     48     51     55     Ljava/lang/IllegalArgumentException;
        //  34     59     59     63     Ljava/lang/IllegalArgumentException;
        //  65     115    115    119    Ljava/lang/IllegalArgumentException;
        //  119    145    145    149    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    public OCType.TypeCheckResult visitPointerType(final OCPointerType ocPointerType) {
        return this.visitType(ocPointerType);
    }
    
    @Override
    public OCType.TypeCheckResult visitStructType(final OCStructType ocStructType) {
        return this.checkStructCompatibleCtor(ocStructType);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
