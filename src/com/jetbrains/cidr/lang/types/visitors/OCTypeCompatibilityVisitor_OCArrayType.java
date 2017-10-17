// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.intellij.openapi.util.Computable;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCTollFreeBridges;
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
import com.jetbrains.cidr.lang.types.OCArrayType;

class OCTypeCompatibilityVisitor_OCArrayType extends OCTypeCompatibilityVisitor<OCArrayType>
{
    protected OCTypeCompatibilityVisitor_OCArrayType(@NotNull final OCArrayType ocArrayType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocArrayType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType", "<init>"));
        }
        super(ocArrayType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "ext_typecheck_convert_incompatible_pointer", new IntentionAction[0]) {
            @Override
            public String getMessage() {
                return "Incompatible pointer types '" + ocFunctionType.getName(OCTypeCompatibilityVisitor_OCArrayType.this.myContext) + "' and '" + OCTypeCompatibilityVisitor_OCArrayType.this.getSourceTypeName() + "'";
            }
        };
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
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    12: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: astore_3       
        //    16: aload_1        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.pointersDepth:()I
        //    20: istore          4
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    26: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    29: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.pointersDepth:()I
        //    32: istore          5
        //    34: aload_2        
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    38: ifne            170
        //    41: aload_3        
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    45: ifne            170
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_2        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    59: ifeq            83
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_3        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    73: ifne            170
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_1        
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //    87: aload_0        
        //    88: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    91: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    94: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.isPointerToPointerToObjectCompatible:()Z
        //    97: ixor           
        //    98: ifeq            170
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_1        
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   113: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   116: istore          6
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   122: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   127: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   130: ifeq            157
        //   133: aload_0        
        //   134: aload_1        
        //   135: iload           6
        //   137: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.checkArcBridgeCast:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Z)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   140: astore          7
        //   142: aload           7
        //   144: ifnull          154
        //   147: aload           7
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: goto            170
        //   157: iload           6
        //   159: ifeq            170
        //   162: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   165: areturn        
        //   166: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_1        
        //   171: aload_0        
        //   172: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   179: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.validateConstPointers:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   182: astore          6
        //   184: aload_0        
        //   185: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   188: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   193: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   196: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   201: istore          7
        //   203: aload           6
        //   205: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   208: aload_0        
        //   209: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   212: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   215: ifne            225
        //   218: aload           6
        //   220: areturn        
        //   221: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: aload_2        
        //   226: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   229: ifeq            285
        //   232: aload_3        
        //   233: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   236: ifeq            285
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: aload_1        
        //   247: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   250: aload_0        
        //   251: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   254: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   257: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   260: aload_0        
        //   261: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   264: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   267: ifeq            285
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   280: areturn        
        //   281: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_1        
        //   286: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVoid:()Z
        //   289: ifeq            312
        //   292: iload           7
        //   294: ifne            312
        //   297: goto            304
        //   300: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   307: areturn        
        //   308: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload_0        
        //   313: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   316: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   319: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.isPointerToVoid:()Z
        //   322: ifeq            345
        //   325: iload           7
        //   327: ifne            345
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   340: areturn        
        //   341: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload_2        
        //   346: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   349: ifeq            410
        //   352: aload_3        
        //   353: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   356: ifeq            410
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: iload           4
        //   368: iload           5
        //   370: if_icmpne       410
        //   373: goto            380
        //   376: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: aload_1        
        //   381: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   384: aload_0        
        //   385: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   388: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   391: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   394: aload_0        
        //   395: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   398: aload_0        
        //   399: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   402: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   405: areturn        
        //   406: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   409: athrow         
        //   410: aload_2        
        //   411: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   414: ifeq            425
        //   417: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   420: areturn        
        //   421: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_0        
        //   426: aload_2        
        //   427: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   430: ifeq            551
        //   433: aload_0        
        //   434: aload_3        
        //   435: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   438: ifeq            551
        //   441: goto            448
        //   444: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   447: athrow         
        //   448: iload           4
        //   450: iload           5
        //   452: if_icmpne       551
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: aload_1        
        //   463: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   466: aload_0        
        //   467: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   470: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   473: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   476: aload_0        
        //   477: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   480: aload_0        
        //   481: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   484: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   487: astore          8
        //   489: iload           7
        //   491: ifeq            511
        //   494: aload_0        
        //   495: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   498: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   501: ifeq            548
        //   504: goto            511
        //   507: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   510: athrow         
        //   511: aload           8
        //   513: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   516: aload_0        
        //   517: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   520: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   523: ifeq            548
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload           8
        //   535: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   538: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setState:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;)V
        //   541: goto            548
        //   544: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   547: athrow         
        //   548: aload           8
        //   550: areturn        
        //   551: aload_2        
        //   552: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   555: ifeq            616
        //   558: aload_3        
        //   559: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   562: ifeq            616
        //   565: goto            572
        //   568: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: iload           4
        //   574: iload           5
        //   576: if_icmpne       616
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: aload_1        
        //   587: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   590: aload_0        
        //   591: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   594: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   597: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   600: aload_0        
        //   601: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   604: aload_0        
        //   605: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   608: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   611: areturn        
        //   612: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   615: athrow         
        //   616: aload_1        
        //   617: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   620: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   623: ifeq            671
        //   626: aload_1        
        //   627: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   630: checkcast       Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   633: invokevirtual   com/jetbrains/cidr/lang/types/OCIdType.getAllProtocols:()Ljava/util/List;
        //   636: invokeinterface java/util/List.isEmpty:()Z
        //   641: ifeq            671
        //   644: goto            651
        //   647: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   650: athrow         
        //   651: aload_0        
        //   652: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   655: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   658: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.isPointerToObjectCompatible:()Z
        //   661: ifne            759
        //   664: goto            671
        //   667: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   670: athrow         
        //   671: aload_1        
        //   672: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObjectCompatible:()Z
        //   675: ifeq            767
        //   678: goto            685
        //   681: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   684: athrow         
        //   685: aload_0        
        //   686: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   689: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   692: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.isPointerToID:()Z
        //   695: ifeq            767
        //   698: goto            705
        //   701: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   704: athrow         
        //   705: aload_0        
        //   706: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   709: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   712: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   715: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   718: ifeq            767
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: aload_0        
        //   729: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   732: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   735: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   738: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   741: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //   744: invokeinterface java/util/List.isEmpty:()Z
        //   749: ifeq            767
        //   752: goto            759
        //   755: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   758: athrow         
        //   759: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   762: areturn        
        //   763: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   766: athrow         
        //   767: aload_2        
        //   768: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   771: ifne            788
        //   774: aload_3        
        //   775: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   778: ifeq            831
        //   781: goto            788
        //   784: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   787: athrow         
        //   788: aload_1        
        //   789: aload_0        
        //   790: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   793: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   796: aload_0        
        //   797: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   800: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   803: aload_0        
        //   804: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.myContext:Lcom/intellij/psi/PsiElement;
        //   807: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   810: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   813: ifeq            831
        //   816: goto            823
        //   819: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   822: athrow         
        //   823: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   826: areturn        
        //   827: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   830: athrow         
        //   831: aload_0        
        //   832: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   835: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;)Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   838: astore          8
        //   840: iload           7
        //   842: ifeq            1052
        //   845: iload           4
        //   847: iconst_1       
        //   848: if_icmpne       1052
        //   851: goto            858
        //   854: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   857: athrow         
        //   858: iload           5
        //   860: iconst_1       
        //   861: if_icmpne       1052
        //   864: goto            871
        //   867: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   870: athrow         
        //   871: aload           8
        //   873: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   876: ifeq            1052
        //   879: goto            886
        //   882: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   885: athrow         
        //   886: aload_2        
        //   887: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   890: ifeq            1052
        //   893: goto            900
        //   896: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   899: athrow         
        //   900: aload_3        
        //   901: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   904: ifeq            1052
        //   907: goto            914
        //   910: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   913: athrow         
        //   914: aload_0        
        //   915: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   918: aload_2        
        //   919: aload_3        
        //   920: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   923: istore          9
        //   925: aload_0        
        //   926: getstatic       com/jetbrains/cidr/lang/types/OCIntType.WCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   929: aload_2        
        //   930: aload_3        
        //   931: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   934: istore          10
        //   936: aload_0        
        //   937: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR16:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   940: aload_2        
        //   941: aload_3        
        //   942: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   945: istore          11
        //   947: aload_0        
        //   948: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR32:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   951: aload_2        
        //   952: aload_3        
        //   953: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   956: istore          12
        //   958: iload           9
        //   960: ifne            999
        //   963: iload           10
        //   965: ifne            999
        //   968: goto            975
        //   971: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   974: athrow         
        //   975: iload           11
        //   977: ifne            999
        //   980: goto            987
        //   983: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   986: athrow         
        //   987: iload           12
        //   989: ifeq            1052
        //   992: goto            999
        //   995: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   998: athrow         
        //   999: aload_2        
        //  1000: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //  1003: ifne            1052
        //  1006: goto            1013
        //  1009: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1012: athrow         
        //  1013: aload_3        
        //  1014: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //  1017: ifeq            1052
        //  1020: goto            1027
        //  1023: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1026: athrow         
        //  1027: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType$2;
        //  1030: dup            
        //  1031: aload_0        
        //  1032: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1035: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //  1037: ldc             "ext_deprecated_string_literal_conversion"
        //  1039: iconst_0       
        //  1040: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1043: aload_1        
        //  1044: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType$2.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //  1047: areturn        
        //  1048: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1051: athrow         
        //  1052: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType$3;
        //  1055: dup            
        //  1056: aload_0        
        //  1057: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1060: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //  1062: ldc             "ext_typecheck_convert_incompatible_pointer"
        //  1064: iconst_0       
        //  1065: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1068: aload_1        
        //  1069: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType$3.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCArrayType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //  1072: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  34     48     51     55     Ljava/lang/IllegalArgumentException;
        //  41     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     76     79     83     Ljava/lang/IllegalArgumentException;
        //  69     101    104    108    Ljava/lang/IllegalArgumentException;
        //  142    150    150    154    Ljava/lang/IllegalArgumentException;
        //  157    166    166    170    Ljava/lang/IllegalArgumentException;
        //  203    221    221    225    Ljava/lang/IllegalArgumentException;
        //  225    239    242    246    Ljava/lang/IllegalArgumentException;
        //  232    270    273    277    Ljava/lang/IllegalArgumentException;
        //  246    281    281    285    Ljava/lang/IllegalArgumentException;
        //  285    297    300    304    Ljava/lang/IllegalArgumentException;
        //  292    308    308    312    Ljava/lang/IllegalArgumentException;
        //  312    330    333    337    Ljava/lang/IllegalArgumentException;
        //  325    341    341    345    Ljava/lang/IllegalArgumentException;
        //  345    359    362    366    Ljava/lang/IllegalArgumentException;
        //  352    373    376    380    Ljava/lang/IllegalArgumentException;
        //  366    406    406    410    Ljava/lang/IllegalArgumentException;
        //  410    421    421    425    Ljava/lang/IllegalArgumentException;
        //  425    441    444    448    Ljava/lang/IllegalArgumentException;
        //  433    455    458    462    Ljava/lang/IllegalArgumentException;
        //  489    504    507    511    Ljava/lang/IllegalArgumentException;
        //  494    526    529    533    Ljava/lang/IllegalArgumentException;
        //  511    541    544    548    Ljava/lang/IllegalArgumentException;
        //  551    565    568    572    Ljava/lang/IllegalArgumentException;
        //  558    579    582    586    Ljava/lang/IllegalArgumentException;
        //  572    612    612    616    Ljava/lang/IllegalArgumentException;
        //  616    644    647    651    Ljava/lang/IllegalArgumentException;
        //  626    664    667    671    Ljava/lang/IllegalArgumentException;
        //  651    678    681    685    Ljava/lang/IllegalArgumentException;
        //  671    698    701    705    Ljava/lang/IllegalArgumentException;
        //  685    721    724    728    Ljava/lang/IllegalArgumentException;
        //  705    752    755    759    Ljava/lang/IllegalArgumentException;
        //  728    763    763    767    Ljava/lang/IllegalArgumentException;
        //  767    781    784    788    Ljava/lang/IllegalArgumentException;
        //  774    816    819    823    Ljava/lang/IllegalArgumentException;
        //  788    827    827    831    Ljava/lang/IllegalArgumentException;
        //  840    851    854    858    Ljava/lang/IllegalArgumentException;
        //  845    864    867    871    Ljava/lang/IllegalArgumentException;
        //  858    879    882    886    Ljava/lang/IllegalArgumentException;
        //  871    893    896    900    Ljava/lang/IllegalArgumentException;
        //  886    907    910    914    Ljava/lang/IllegalArgumentException;
        //  958    968    971    975    Ljava/lang/IllegalArgumentException;
        //  963    980    983    987    Ljava/lang/IllegalArgumentException;
        //  975    992    995    999    Ljava/lang/IllegalArgumentException;
        //  987    1006   1009   1013   Ljava/lang/IllegalArgumentException;
        //  999    1020   1023   1027   Ljava/lang/IllegalArgumentException;
        //  1013   1048   1048   1052   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    public OCType.TypeCheckResult visitArrayType(final OCArrayType ocArrayType) {
        final OCType.TypeCheckResult checkAssignPointerToArray = this.checkAssignPointerToArray(ocArrayType);
        try {
            if (checkAssignPointerToArray != null) {
                return checkAssignPointerToArray;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.visitArrayType(ocArrayType);
    }
    
    @Override
    public OCType.TypeCheckResult visitStructType(final OCStructType ocStructType) {
        try {
            if (OCTollFreeBridges.isCompatible(this.mySourceType, ocStructType)) {
                return OCTypeCompatibilityVisitor_OCArrayType.OK_RESULT;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocStructType.getKind() != OCSymbolKind.ENUM || ocStructType.isEnumClass()) {
                return this.checkStructCompatibleCtor(ocStructType);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return this.checkAssignToEnum(ocStructType, (Computable<String>)(() -> "Taking enum type '" + ocStructType.getBestNameInContext(this.myContext) + "' from pointer"));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
