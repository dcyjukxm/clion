// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCIntType;

class OCTypeCompatibilityVisitor_OCIntType extends OCTypeCompatibilityVisitor<OCIntType>
{
    protected OCTypeCompatibilityVisitor_OCIntType(@NotNull final OCIntType ocIntType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocIntType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType", "<init>"));
        }
        super(ocIntType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, "Taking pointer from integer without a cast", OCInspections.ImplicitPointerAndIntegerConversion.class, "ext_typecheck_convert_int_pointer", new IntentionAction[0]);
    }
    
    @Override
    public OCType.TypeCheckResult visitObjectType(final OCObjectType ocObjectType) {
        return this.visitType(ocObjectType);
    }
    
    @Override
    public OCType.TypeCheckResult visitPointerType(final OCPointerType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: astore_2       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.myContext:Lcom/intellij/psi/PsiElement;
        //    16: iconst_0       
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //    20: ifeq            101
        //    23: aload_2        
        //    24: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    27: ifne            101
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_1        
        //    38: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //    41: aload_0        
        //    42: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    45: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    48: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isPointerToPointerToObjectCompatible:()Z
        //    51: ixor           
        //    52: ifeq            101
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.myContext:Lcom/intellij/psi/PsiElement;
        //    66: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    71: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    74: ifeq            101
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: aload_1        
        //    86: iconst_0       
        //    87: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.checkArcBridgeCast:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Z)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    90: astore_3       
        //    91: aload_3        
        //    92: ifnull          101
        //    95: aload_3        
        //    96: areturn        
        //    97: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   104: dup            
        //   105: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   108: ldc             "Taking pointer from integer without a cast"
        //   110: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitPointerAndIntegerConversion;.class
        //   112: ldc             "ext_typecheck_convert_int_pointer"
        //   114: iconst_0       
        //   115: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   118: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   121: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      30     33     37     Ljava/lang/IllegalArgumentException;
        //  23     55     58     62     Ljava/lang/IllegalArgumentException;
        //  37     77     80     84     Ljava/lang/IllegalArgumentException;
        //  91     97     97     101    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    public OCType.TypeCheckResult visitStructType(final OCStructType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     7: if_acmpne       86
        //    10: aload_1        
        //    11: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isUnnamed:()Z
        //    14: ifeq            71
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_1        
        //    25: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //    28: ifnonnull       71
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_1        
        //    39: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //    42: ifne            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    55: areturn        
        //    56: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aload_0        
        //    61: aload_1        
        //    62: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/intellij/openapi/util/Computable;
        //    67: astore_2       
        //    68: goto            79
        //    71: aload_0        
        //    72: aload_1        
        //    73: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/intellij/openapi/util/Computable;
        //    78: astore_2       
        //    79: aload_0        
        //    80: aload_1        
        //    81: aload_2        
        //    82: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.checkAssignToEnum:(Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    85: areturn        
        //    86: aload_0        
        //    87: aload_1        
        //    88: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIntType.checkStructCompatibleCtor:(Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    91: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     56     56     60     Ljava/lang/IllegalArgumentException;
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
