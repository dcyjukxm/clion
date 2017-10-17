// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
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
import com.jetbrains.cidr.lang.types.OCStructType;

class OCTypeCompatibilityVisitor_OCStructType extends OCTypeCompatibilityVisitor<OCStructType>
{
    protected OCTypeCompatibilityVisitor_OCStructType(@NotNull final OCStructType ocStructType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocStructType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType", "<init>"));
        }
        super(ocStructType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        if (((OCStructType)this.mySourceType).isIntegerCompatible(this.myContext, false)) {
            return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, "Taking pointer from integer without a cast", OCInspections.ImplicitPointerAndIntegerConversion.class, "ext_typecheck_convert_int_pointer", new IntentionAction[0]);
        }
        Label_0102: {
            Label_0078: {
                try {
                    if (((OCStructType)this.mySourceType).getKind() != OCSymbolKind.ENUM) {
                        break Label_0102;
                    }
                    final OCTypeCompatibilityVisitor_OCStructType ocTypeCompatibilityVisitor_OCStructType = this;
                    final OCType ocType = ocTypeCompatibilityVisitor_OCStructType.mySourceType;
                    final OCStructType ocStructType = (OCStructType)ocType;
                    final boolean b = ocStructType.isEnumClass();
                    if (!b) {
                        break Label_0078;
                    }
                    break Label_0102;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCTypeCompatibilityVisitor_OCStructType ocTypeCompatibilityVisitor_OCStructType = this;
                    final OCType ocType = ocTypeCompatibilityVisitor_OCStructType.mySourceType;
                    final OCStructType ocStructType = (OCStructType)ocType;
                    final boolean b = ocStructType.isEnumClass();
                    if (!b) {
                        return new OCType.TypeCheckResult(OCType.TypeCheckState.WARNING, OCInspections.ImplicitIntegerAndEnumConversion.class, "CIDR", new IntentionAction[0]) {
                            @Override
                            public String getMessage() {
                                return "Taking integer from enum type '" + OCTypeCompatibilityVisitor_OCStructType.this.getSourceTypeName() + "'";
                            }
                        };
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            try {
                if (((OCStructType)this.mySourceType).isPointerCompatible(this.myContext, false)) {
                    return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "ext_typecheck_convert_incompatible_pointer", new IntentionAction[0]) {
                        @Override
                        public String getMessage() {
                            return "Incompatible pointer types '" + ocFunctionType.getName(OCTypeCompatibilityVisitor_OCStructType.this.myContext) + "' and '" + OCTypeCompatibilityVisitor_OCStructType.this.getSourceTypeName() + "'";
                        }
                    };
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return this.visitType(ocFunctionType);
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
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //    16: iconst_0       
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //    20: ifeq            101
        //    23: aload_2        
        //    24: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    27: ifne            101
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_1        
        //    38: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //    41: aload_0        
        //    42: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    45: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    48: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPointerToPointerToObjectCompatible:()Z
        //    51: ixor           
        //    52: ifeq            101
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //    66: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    71: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    74: ifeq            101
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: aload_1        
        //    86: iconst_0       
        //    87: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.checkArcBridgeCast:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Z)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    90: astore_3       
        //    91: aload_3        
        //    92: ifnull          101
        //    95: aload_3        
        //    96: areturn        
        //    97: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload_0        
        //   102: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   105: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   110: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   113: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   118: istore_3       
        //   119: aload_0        
        //   120: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   130: iconst_0       
        //   131: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //   134: ifeq            162
        //   137: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   140: dup            
        //   141: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   144: ldc             "Taking pointer from integer without a cast"
        //   146: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitPointerAndIntegerConversion;.class
        //   148: ldc             "ext_typecheck_convert_int_pointer"
        //   150: iconst_0       
        //   151: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   154: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   157: areturn        
        //   158: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: aload_1        
        //   163: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVoid:()Z
        //   166: ifeq            216
        //   169: aload_0        
        //   170: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   173: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   176: aload_0        
        //   177: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   180: aload_0        
        //   181: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myAllowImplicitConversions:Z
        //   184: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //   187: ifeq            216
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: iload_3        
        //   198: ifne            216
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   211: areturn        
        //   212: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   220: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   223: aload_0        
        //   224: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   227: iconst_0       
        //   228: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //   231: ifeq            259
        //   234: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$3;
        //   237: dup            
        //   238: aload_0        
        //   239: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   242: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //   244: ldc             "ext_typecheck_convert_incompatible_pointer"
        //   246: iconst_0       
        //   247: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   250: aload_1        
        //   251: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$3.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //   254: areturn        
        //   255: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   258: athrow         
        //   259: aload_0        
        //   260: aload_1        
        //   261: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.visitType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   264: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      30     33     37     Ljava/lang/IllegalArgumentException;
        //  23     55     58     62     Ljava/lang/IllegalArgumentException;
        //  37     77     80     84     Ljava/lang/IllegalArgumentException;
        //  91     97     97     101    Ljava/lang/IllegalArgumentException;
        //  119    158    158    162    Ljava/lang/IllegalArgumentException;
        //  162    190    193    197    Ljava/lang/IllegalArgumentException;
        //  169    201    204    208    Ljava/lang/IllegalArgumentException;
        //  197    212    212    216    Ljava/lang/IllegalArgumentException;
        //  216    255    255    259    Ljava/lang/IllegalArgumentException;
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
    protected OCType.TypeCheckResult checkRefType(final OCCppReferenceType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkRefType:(Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //     5: astore_2       
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //    10: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //    13: ifeq            119
        //    16: aload_2        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //    24: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //    27: ifeq            119
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_0        
        //    38: aload_1        
        //    39: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    45: ifeq            119
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //    59: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    64: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    72: ifeq            119
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    86: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    89: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    92: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    95: if_acmpne       119
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_2        
        //   106: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   109: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setState:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;)V
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_2        
        //   120: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  6      30     33     37     Ljava/lang/IllegalArgumentException;
        //  16     48     51     55     Ljava/lang/IllegalArgumentException;
        //  37     75     78     82     Ljava/lang/IllegalArgumentException;
        //  55     98     101    105    Ljava/lang/IllegalArgumentException;
        //  82     112    115    119    Ljava/lang/IllegalArgumentException;
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
        //     0: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     8: iconst_0       
        //     9: iconst_0       
        //    10: iconst_1       
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myAssumeNullSubstitutionsEquals:Z
        //    15: iconst_1       
        //    16: iconst_1       
        //    17: iconst_1       
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    22: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //    25: aload_1        
        //    26: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    29: ifeq            74
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    36: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    39: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isVolatile:()Z
        //    42: ifeq            66
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isVolatile:()Z
        //    56: ifeq            74
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    69: areturn        
        //    70: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    78: aload_1        
        //    79: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    82: ifeq            93
        //    85: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    88: areturn        
        //    89: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_1        
        //    94: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    97: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   100: if_acmpne       237
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   107: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   110: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   113: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   116: if_acmpne       151
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$4;
        //   129: dup            
        //   130: aload_0        
        //   131: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   134: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleEnums;.class
        //   136: ldc             "warn_impcast_different_enum_types"
        //   138: iconst_0       
        //   139: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   142: aload_1        
        //   143: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$4.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCStructType;)V
        //   146: areturn        
        //   147: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aconst_null    
        //   152: astore_2       
        //   153: aload_0        
        //   154: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   157: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   160: aload_0        
        //   161: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   164: iconst_0       
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //   168: ifeq            182
        //   171: aload_0        
        //   172: aload_1        
        //   173: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/intellij/openapi/util/Computable;
        //   178: astore_2       
        //   179: goto            222
        //   182: aload_1        
        //   183: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //   186: ifne            222
        //   189: aload_0        
        //   190: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   193: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   196: aload_0        
        //   197: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   200: iconst_0       
        //   201: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //   204: ifeq            222
        //   207: goto            214
        //   210: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: aload_0        
        //   215: aload_1        
        //   216: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/intellij/openapi/util/Computable;
        //   221: astore_2       
        //   222: aload_2        
        //   223: ifnull          237
        //   226: aload_0        
        //   227: aload_1        
        //   228: aload_2        
        //   229: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.checkAssignToEnum:(Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   232: areturn        
        //   233: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload_0        
        //   238: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   241: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   243: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   246: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   249: astore_2       
        //   250: aload_2        
        //   251: ifnull          267
        //   254: aload_2        
        //   255: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   260: goto            268
        //   263: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: aconst_null    
        //   268: astore_3       
        //   269: aload_0        
        //   270: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   273: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   278: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   281: ifeq            323
        //   284: aload_0        
        //   285: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   288: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   293: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   296: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   301: aload_0        
        //   302: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   305: if_acmpne       323
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: iconst_1       
        //   316: goto            324
        //   319: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: iconst_0       
        //   324: istore          4
        //   326: aload_1        
        //   327: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   330: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   335: astore          5
        //   337: aload           5
        //   339: invokeinterface java/util/Iterator.hasNext:()Z
        //   344: ifeq            805
        //   347: aload           5
        //   349: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   354: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   357: astore          6
        //   359: aload_0        
        //   360: aload           6
        //   362: aload_1        
        //   363: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.processTransparentUnion:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   366: astore          7
        //   368: aload           7
        //   370: ifnull          380
        //   373: aload           7
        //   375: areturn        
        //   376: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: aload_0        
        //   381: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myAllowImplicitConversions:Z
        //   384: ifeq            395
        //   387: aload_0        
        //   388: aload           6
        //   390: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.processConstructors:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   393: astore          7
        //   395: aload_0        
        //   396: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   399: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   404: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   407: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   412: ifeq            790
        //   415: aload_0        
        //   416: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   419: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   422: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   425: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   428: if_acmpne       790
        //   431: goto            438
        //   434: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   437: athrow         
        //   438: aload_0        
        //   439: aload_1        
        //   440: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   443: ifeq            790
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: aload_3        
        //   454: ifnull          511
        //   457: goto            464
        //   460: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: aload_3        
        //   465: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   468: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   471: ifeq            511
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   480: athrow         
        //   481: aload           6
        //   483: aload_3        
        //   484: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   487: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   490: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isAncestor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
        //   493: ifeq            511
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: iconst_1       
        //   504: goto            512
        //   507: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   510: athrow         
        //   511: iconst_0       
        //   512: istore          8
        //   514: aload_0        
        //   515: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   518: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   521: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   524: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   529: astore          9
        //   531: aload           9
        //   533: invokeinterface java/util/Iterator.hasNext:()Z
        //   538: ifeq            790
        //   541: aload           9
        //   543: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   548: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   551: astore          10
        //   553: new             Lcom/intellij/openapi/util/Ref;
        //   556: dup            
        //   557: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   560: astore          11
        //   562: aload           10
        //   564: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   567: dup            
        //   568: aload_0        
        //   569: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.myContext:Lcom/intellij/psi/PsiElement;
        //   572: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   577: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   580: aload           6
        //   582: aload           11
        //   584: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   589: iload           8
        //   591: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processAllBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;Z)Z
        //   594: ifne            730
        //   597: iload           4
        //   599: ifne            726
        //   602: goto            609
        //   605: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   608: athrow         
        //   609: iload           8
        //   611: ifne            639
        //   614: goto            621
        //   617: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   620: athrow         
        //   621: aload           11
        //   623: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   626: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   629: if_acmpne       657
        //   632: goto            639
        //   635: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   638: athrow         
        //   639: aload           11
        //   641: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   644: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   647: if_acmpne       726
        //   650: goto            657
        //   653: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   656: athrow         
        //   657: aload           7
        //   659: ifnonnull       730
        //   662: goto            669
        //   665: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$5;
        //   672: dup            
        //   673: aload_0        
        //   674: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   677: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MemberVisibility;.class
        //   679: ldc             "err_typecheck_convert_incompatible"
        //   681: iconst_2       
        //   682: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   685: dup            
        //   686: iconst_0       
        //   687: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction;
        //   690: dup            
        //   691: aload           10
        //   693: aload           6
        //   695: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   698: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   701: aastore        
        //   702: dup            
        //   703: iconst_1       
        //   704: aload_0        
        //   705: aload           6
        //   707: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.getNewConstructorFix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   710: aastore        
        //   711: aload           6
        //   713: aload_1        
        //   714: aload           11
        //   716: aload           10
        //   718: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$5.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)V
        //   721: astore          7
        //   723: goto            730
        //   726: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   729: areturn        
        //   730: aload           7
        //   732: ifnonnull       787
        //   735: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$6;
        //   738: dup            
        //   739: aload_0        
        //   740: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   743: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotSuperclass;.class
        //   745: ldc             "err_ovl_no_viable_oper"
        //   747: iconst_2       
        //   748: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   751: dup            
        //   752: iconst_0       
        //   753: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction;
        //   756: dup            
        //   757: aload           10
        //   759: aload           6
        //   761: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   764: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   767: aastore        
        //   768: dup            
        //   769: iconst_1       
        //   770: aload_0        
        //   771: aload           6
        //   773: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.getNewConstructorFix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   776: aastore        
        //   777: aload           10
        //   779: aload           6
        //   781: aload_1        
        //   782: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType$6.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/OCStructType;)V
        //   785: astore          7
        //   787: goto            531
        //   790: aload           7
        //   792: ifnull          802
        //   795: aload           7
        //   797: areturn        
        //   798: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   801: athrow         
        //   802: goto            337
        //   805: aload_0        
        //   806: aload_1        
        //   807: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.visitType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   810: astore          5
        //   812: aload           5
        //   814: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   817: if_acmpeq       862
        //   820: aload_0        
        //   821: aload_1        
        //   822: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   825: ifeq            862
        //   828: goto            835
        //   831: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   834: athrow         
        //   835: aload           5
        //   837: iconst_1       
        //   838: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   841: dup            
        //   842: iconst_0       
        //   843: aload_0        
        //   844: aload_1        
        //   845: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   848: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.getNewConstructorFix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   851: aastore        
        //   852: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setQuickFixes:([Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   855: goto            862
        //   858: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCStructType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   861: athrow         
        //   862: aload           5
        //   864: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      45     48     52     Ljava/lang/IllegalArgumentException;
        //  32     59     62     66     Ljava/lang/IllegalArgumentException;
        //  52     70     70     74     Ljava/lang/IllegalArgumentException;
        //  74     89     89     93     Ljava/lang/IllegalArgumentException;
        //  93     119    122    126    Ljava/lang/IllegalArgumentException;
        //  103    147    147    151    Ljava/lang/IllegalArgumentException;
        //  182    207    210    214    Ljava/lang/IllegalArgumentException;
        //  222    233    233    237    Ljava/lang/IllegalArgumentException;
        //  250    263    263    267    Ljava/lang/IllegalArgumentException;
        //  269    308    311    315    Ljava/lang/IllegalArgumentException;
        //  284    319    319    323    Ljava/lang/IllegalArgumentException;
        //  368    376    376    380    Ljava/lang/IllegalArgumentException;
        //  395    431    434    438    Ljava/lang/IllegalArgumentException;
        //  415    446    449    453    Ljava/lang/IllegalArgumentException;
        //  438    457    460    464    Ljava/lang/IllegalArgumentException;
        //  453    474    477    481    Ljava/lang/IllegalArgumentException;
        //  464    496    499    503    Ljava/lang/IllegalArgumentException;
        //  481    507    507    511    Ljava/lang/IllegalArgumentException;
        //  562    602    605    609    Ljava/lang/IllegalArgumentException;
        //  597    614    617    621    Ljava/lang/IllegalArgumentException;
        //  609    632    635    639    Ljava/lang/IllegalArgumentException;
        //  621    650    653    657    Ljava/lang/IllegalArgumentException;
        //  639    662    665    669    Ljava/lang/IllegalArgumentException;
        //  790    798    798    802    Ljava/lang/IllegalArgumentException;
        //  812    828    831    835    Ljava/lang/IllegalArgumentException;
        //  820    855    858    862    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
