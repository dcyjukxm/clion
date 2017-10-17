// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.intellij.openapi.util.Computable;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCFunctionType;

class OCTypeCompatibilityVisitor_OCFunctionType extends OCTypeCompatibilityVisitor<OCFunctionType>
{
    protected OCTypeCompatibilityVisitor_OCFunctionType(@NotNull final OCFunctionType ocFunctionType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocFunctionType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType", "<init>"));
        }
        super(ocFunctionType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        final OCFunctionType ocFunctionType2 = (OCFunctionType)this.mySourceType;
        if (!this.isSuperTypeForFunctionChecks(ocFunctionType.getReturnType(), ocFunctionType2.getReturnType())) {
            Object returnTypeElement = null;
            OCSymbol symbol = null;
            if (this.mySource instanceof OCBlockExpression) {
                returnTypeElement = ((OCBlockExpression)this.mySource).getReturnTypeElement();
                symbol = ((OCBlockExpression)this.mySource).getSymbol();
            }
            return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "ext_typecheck_convert_incompatible_pointer", returnTypeElement, new IntentionAction[] { new OCChangeTypeIntentionAction(symbol, ocFunctionType.getReturnType(), true) }) {
                @Override
                public String getMessage() {
                    return "Incompatible function return types '" + ocFunctionType.getReturnType().getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "' and '" + ocFunctionType2.getReturnType().getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "'";
                }
            };
        }
        Label_0146: {
            Label_0138: {
                try {
                    if (!OCCodeInsightUtil.isInPlainOldC((PsiElement)this.myResolveContext.getFile())) {
                        break Label_0146;
                    }
                    final OCFunctionType ocFunctionType3 = ocFunctionType;
                    final boolean b = true;
                    final List<OCType> list = ocFunctionType3.getParameterTypes(b);
                    final boolean b2 = list.isEmpty();
                    if (b2) {
                        break Label_0138;
                    }
                    break Label_0146;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCFunctionType ocFunctionType3 = ocFunctionType;
                    final boolean b = true;
                    final List<OCType> list = ocFunctionType3.getParameterTypes(b);
                    final boolean b2 = list.isEmpty();
                    if (b2) {
                        return OCTypeCompatibilityVisitor_OCFunctionType.OK_RESULT;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            try {
                if (ocFunctionType.getParameterTypes().size() != ocFunctionType2.getParameterTypes().size()) {
                    return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, "Incompatible number of arguments in function types", OCInspections.IncompatiblePointers.class, "CIDRincompatible-function-pointer-arguments", new IntentionAction[0]);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        for (int i = 0; i < ocFunctionType.getParameterTypes().size(); ++i) {
            final OCType ocType = ocFunctionType.getParameterTypes().get(i);
            final OCType ocType2 = ocFunctionType2.getParameterTypes().get(i);
            if (!this.isSuperTypeForFunctionChecks(ocType2, ocType)) {
                PsiElement parent = null;
                OCSymbol symbol2 = null;
                if (this.mySource instanceof OCBlockExpression) {
                    final List<OCDeclarator> parameters = ((OCBlockExpression)this.mySource).getParameters();
                    try {
                        if (parameters == null || parameters.size() != ocFunctionType.getParameterTypes().size()) {
                            return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "CIDRincompatible-function-pointer-arguments", parent, new IntentionAction[] { new OCChangeTypeIntentionAction(symbol2, ocType) }) {
                                final /* synthetic */ OCType val$selfArgumentType;
                                final /* synthetic */ OCType val$hisArgumentType;
                                
                                @Override
                                public String getMessage() {
                                    return "Incompatible function parameter types '" + this.val$selfArgumentType.getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "' and '" + this.val$hisArgumentType.getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "'";
                                }
                            };
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    symbol2 = parameters.get(i).getSymbol();
                    parent = parameters.get(i).getParent();
                }
                return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "CIDRincompatible-function-pointer-arguments", parent, new IntentionAction[] { new OCChangeTypeIntentionAction(symbol2, ocType) }, ocType2) {
                    final /* synthetic */ OCType val$selfArgumentType;
                    final /* synthetic */ OCType val$hisArgumentType;
                    
                    @Override
                    public String getMessage() {
                        return "Incompatible function parameter types '" + ocType.getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "' and '" + ocType2.getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "'";
                    }
                };
            }
        }
        return OCTypeCompatibilityVisitor_OCFunctionType.OK_RESULT;
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
        //     5: aload_2        
        //     6: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //     9: ifne            79
        //    12: aload_2        
        //    13: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    16: ifne            79
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_1        
        //    27: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //    30: ifeq            79
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.myContext:Lcom/intellij/psi/PsiElement;
        //    44: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    49: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    52: ifeq            79
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: aload_1        
        //    64: iconst_0       
        //    65: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.checkArcBridgeCast:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Z)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    68: astore_3       
        //    69: aload_3        
        //    70: ifnull          79
        //    73: aload_3        
        //    74: areturn        
        //    75: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.myContext:Lcom/intellij/psi/PsiElement;
        //    83: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    88: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    91: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    96: istore_3       
        //    97: aload_1        
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   101: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   104: ifeq            145
        //   107: aload_1        
        //   108: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   111: ifne            145
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_1        
        //   122: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   125: aload_0        
        //   126: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   129: aload_0        
        //   130: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.myContext:Lcom/intellij/psi/PsiElement;
        //   137: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   140: areturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_1        
        //   146: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVoid:()Z
        //   149: ifeq            171
        //   152: iload_3        
        //   153: ifne            171
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   166: areturn        
        //   167: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType$3;
        //   174: dup            
        //   175: aload_0        
        //   176: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   179: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //   181: ldc             "ext_typecheck_convert_incompatible_pointer"
        //   183: iconst_0       
        //   184: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   187: aload_1        
        //   188: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType$3.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCFunctionType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //   191: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      19     22     26     Ljava/lang/IllegalArgumentException;
        //  12     33     36     40     Ljava/lang/IllegalArgumentException;
        //  26     55     58     62     Ljava/lang/IllegalArgumentException;
        //  69     75     75     79     Ljava/lang/IllegalArgumentException;
        //  97     114    117    121    Ljava/lang/IllegalArgumentException;
        //  107    141    141    145    Ljava/lang/IllegalArgumentException;
        //  145    156    159    163    Ljava/lang/IllegalArgumentException;
        //  152    167    167    171    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    public OCType.TypeCheckResult visitStructType(final OCStructType ocStructType) {
        try {
            if (ocStructType.getKind() != OCSymbolKind.ENUM || ocStructType.isEnumClass()) {
                return this.checkStructCompatibleCtor(ocStructType);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.checkAssignToEnum(ocStructType, (Computable<String>)(() -> "Taking enum type '" + ocStructType.getBestNameInContext(this.myContext) + "' from pointer"));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
