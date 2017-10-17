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
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;

class OCTypeCompatibilityVisitor_OCBlockPointerType extends OCTypeCompatibilityVisitor<OCBlockPointerType>
{
    protected OCTypeCompatibilityVisitor_OCBlockPointerType(@NotNull final OCBlockPointerType ocBlockPointerType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocBlockPointerType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType", "<init>"));
        }
        super(ocBlockPointerType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        try {
            if (((OCBlockPointerType)this.mySourceType).getRefType() instanceof OCVoidType) {
                return OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "ext_typecheck_convert_incompatible_pointer", new IntentionAction[0]) {
            @Override
            public String getMessage() {
                return "Incompatible pointer types '" + ocFunctionType.getName(OCTypeCompatibilityVisitor_OCBlockPointerType.this.myContext) + "' and '" + OCTypeCompatibilityVisitor_OCBlockPointerType.this.getSourceTypeName() + "'";
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
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    12: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: astore_3       
        //    16: aload_1        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.pointersDepth:()I
        //    20: istore          4
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    26: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    29: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.pointersDepth:()I
        //    32: istore          5
        //    34: aload_2        
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    38: ifne            170
        //    41: aload_3        
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    45: ifne            170
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_2        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    59: ifeq            83
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_3        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    73: ifne            170
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_1        
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //    87: aload_0        
        //    88: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    91: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    94: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.isPointerToPointerToObjectCompatible:()Z
        //    97: ixor           
        //    98: ifeq            170
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_1        
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   113: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   116: istore          6
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   122: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   127: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   130: ifeq            157
        //   133: aload_0        
        //   134: aload_1        
        //   135: iload           6
        //   137: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.checkArcBridgeCast:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Z)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   140: astore          7
        //   142: aload           7
        //   144: ifnull          154
        //   147: aload           7
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: goto            170
        //   157: iload           6
        //   159: ifeq            170
        //   162: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   165: areturn        
        //   166: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_1        
        //   171: aload_0        
        //   172: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   179: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.validateConstPointers:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   182: astore          6
        //   184: aload_0        
        //   185: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   188: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   193: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   196: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   201: istore          7
        //   203: aload           6
        //   205: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   208: aload_0        
        //   209: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   212: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   215: ifne            225
        //   218: aload           6
        //   220: areturn        
        //   221: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: aload_2        
        //   226: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   229: ifeq            285
        //   232: aload_3        
        //   233: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   236: ifeq            285
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: aload_1        
        //   247: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   250: aload_0        
        //   251: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   254: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   257: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   260: aload_0        
        //   261: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   264: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   267: ifeq            285
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   280: areturn        
        //   281: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_1        
        //   286: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVoid:()Z
        //   289: ifeq            312
        //   292: iload           7
        //   294: ifne            312
        //   297: goto            304
        //   300: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   307: areturn        
        //   308: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload_0        
        //   313: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   316: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   319: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.isPointerToVoid:()Z
        //   322: ifeq            345
        //   325: iload           7
        //   327: ifne            345
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   340: areturn        
        //   341: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload_2        
        //   346: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   349: ifeq            410
        //   352: aload_3        
        //   353: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   356: ifeq            410
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: iload           4
        //   368: iload           5
        //   370: if_icmpne       410
        //   373: goto            380
        //   376: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: aload_1        
        //   381: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   384: aload_0        
        //   385: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   388: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   391: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   394: aload_0        
        //   395: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   398: aload_0        
        //   399: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   402: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   405: areturn        
        //   406: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   409: athrow         
        //   410: aload_2        
        //   411: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   414: ifeq            425
        //   417: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   420: areturn        
        //   421: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_0        
        //   426: aload_2        
        //   427: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   430: ifeq            551
        //   433: aload_0        
        //   434: aload_3        
        //   435: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   438: ifeq            551
        //   441: goto            448
        //   444: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   447: athrow         
        //   448: iload           4
        //   450: iload           5
        //   452: if_icmpne       551
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: aload_1        
        //   463: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   466: aload_0        
        //   467: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   470: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   473: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   476: aload_0        
        //   477: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   480: aload_0        
        //   481: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   484: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   487: astore          8
        //   489: iload           7
        //   491: ifeq            511
        //   494: aload_0        
        //   495: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   498: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   501: ifeq            548
        //   504: goto            511
        //   507: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   510: athrow         
        //   511: aload           8
        //   513: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   516: aload_0        
        //   517: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   520: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   523: ifeq            548
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload           8
        //   535: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   538: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setState:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;)V
        //   541: goto            548
        //   544: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   547: athrow         
        //   548: aload           8
        //   550: areturn        
        //   551: aload_2        
        //   552: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   555: ifeq            630
        //   558: aload_3        
        //   559: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   562: ifeq            630
        //   565: goto            572
        //   568: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: iload           4
        //   574: iload           5
        //   576: if_icmpne       630
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: aload_1        
        //   587: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   590: ifeq            630
        //   593: goto            600
        //   596: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   599: athrow         
        //   600: aload_1        
        //   601: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   604: aload_0        
        //   605: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   608: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   611: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   614: aload_0        
        //   615: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   618: aload_0        
        //   619: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   622: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   625: areturn        
        //   626: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: aload_1        
        //   631: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   634: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   637: ifeq            665
        //   640: aload_1        
        //   641: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   644: checkcast       Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   647: invokevirtual   com/jetbrains/cidr/lang/types/OCIdType.getAllProtocols:()Ljava/util/List;
        //   650: invokeinterface java/util/List.isEmpty:()Z
        //   655: ifne            753
        //   658: goto            665
        //   661: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   664: athrow         
        //   665: aload_1        
        //   666: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObjectCompatible:()Z
        //   669: ifeq            761
        //   672: goto            679
        //   675: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   678: athrow         
        //   679: aload_0        
        //   680: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   683: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   686: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.isPointerToID:()Z
        //   689: ifeq            761
        //   692: goto            699
        //   695: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   698: athrow         
        //   699: aload_0        
        //   700: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   703: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   706: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   709: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   712: ifeq            761
        //   715: goto            722
        //   718: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   721: athrow         
        //   722: aload_0        
        //   723: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   726: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   729: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   732: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   735: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //   738: invokeinterface java/util/List.isEmpty:()Z
        //   743: ifeq            761
        //   746: goto            753
        //   749: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   752: athrow         
        //   753: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   756: areturn        
        //   757: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   760: athrow         
        //   761: aload_2        
        //   762: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   765: ifne            782
        //   768: aload_3        
        //   769: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   772: ifeq            825
        //   775: goto            782
        //   778: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   781: athrow         
        //   782: aload_1        
        //   783: aload_0        
        //   784: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   787: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   790: aload_0        
        //   791: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   794: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   797: aload_0        
        //   798: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   801: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   804: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   807: ifeq            825
        //   810: goto            817
        //   813: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   816: athrow         
        //   817: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   820: areturn        
        //   821: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   824: athrow         
        //   825: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType$2;
        //   828: dup            
        //   829: aload_0        
        //   830: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   833: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //   835: ldc             "ext_typecheck_convert_incompatible_pointer"
        //   837: iconst_0       
        //   838: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   841: aload_1        
        //   842: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType$2.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCBlockPointerType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //   845: areturn        
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
        //  572    593    596    600    Ljava/lang/IllegalArgumentException;
        //  586    626    626    630    Ljava/lang/IllegalArgumentException;
        //  630    658    661    665    Ljava/lang/IllegalArgumentException;
        //  640    672    675    679    Ljava/lang/IllegalArgumentException;
        //  665    692    695    699    Ljava/lang/IllegalArgumentException;
        //  679    715    718    722    Ljava/lang/IllegalArgumentException;
        //  699    746    749    753    Ljava/lang/IllegalArgumentException;
        //  722    757    757    761    Ljava/lang/IllegalArgumentException;
        //  761    775    778    782    Ljava/lang/IllegalArgumentException;
        //  768    810    813    817    Ljava/lang/IllegalArgumentException;
        //  782    821    821    825    Ljava/lang/IllegalArgumentException;
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
    public OCType.TypeCheckResult visitStructType(final OCStructType ocStructType) {
        try {
            if (OCTollFreeBridges.isCompatible(this.mySourceType, ocStructType)) {
                return OCTypeCompatibilityVisitor_OCBlockPointerType.OK_RESULT;
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
