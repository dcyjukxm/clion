// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.intellij.openapi.util.Computable;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCTollFreeBridges;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCArrayType;
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
import com.jetbrains.cidr.lang.types.OCPointerType;

class OCTypeCompatibilityVisitor_OCPointerType extends OCTypeCompatibilityVisitor<OCPointerType>
{
    protected OCTypeCompatibilityVisitor_OCPointerType(@NotNull final OCPointerType ocPointerType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocPointerType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType", "<init>"));
        }
        super(ocPointerType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        try {
            if (((OCPointerType)this.mySourceType).getRefType() instanceof OCVoidType) {
                return OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR_IF_CPP, OCInspections.IncompatiblePointers.class, "ext_typecheck_convert_incompatible_pointer", new IntentionAction[0]) {
            @Override
            public String getMessage() {
                return "Incompatible pointer types '" + ocFunctionType.getName(OCTypeCompatibilityVisitor_OCPointerType.this.myContext) + "' and '" + OCTypeCompatibilityVisitor_OCPointerType.this.getSourceTypeName() + "'";
            }
        };
    }
    
    @Override
    public OCType.TypeCheckResult visitObjectType(final OCObjectType ocObjectType) {
        return this.visitType(ocObjectType);
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
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    12: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: astore_3       
        //    16: aload_1        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.pointersDepth:()I
        //    20: istore          4
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    26: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    29: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.pointersDepth:()I
        //    32: istore          5
        //    34: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType$2;
        //    37: dup            
        //    38: aload_0        
        //    39: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    42: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //    44: ldc             "ext_typecheck_convert_incompatible_pointer"
        //    46: iconst_0       
        //    47: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //    50: aload_1        
        //    51: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType$2.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //    54: astore          6
        //    56: aload_1        
        //    57: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    60: ifnull          121
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    67: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    70: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    73: ifnull          121
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    87: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    90: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    93: aload_1        
        //    94: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   101: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   104: ifne            195
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload           6
        //   116: areturn        
        //   117: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_1        
        //   122: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   125: ifnull          145
        //   128: aload_1        
        //   129: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   132: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   135: ifeq            188
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_0        
        //   146: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   149: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   152: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   155: ifnull          195
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: aload_0        
        //   166: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   169: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   172: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   175: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   178: ifne            195
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload           6
        //   190: areturn        
        //   191: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_2        
        //   196: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   199: ifne            331
        //   202: aload_3        
        //   203: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   206: ifne            331
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_2        
        //   217: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   220: ifeq            244
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload_3        
        //   231: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   234: ifne            331
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   243: athrow         
        //   244: aload_1        
        //   245: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //   248: aload_0        
        //   249: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   252: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   255: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //   258: ixor           
        //   259: ifeq            331
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload_1        
        //   270: aload_0        
        //   271: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   274: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   277: istore          7
        //   279: aload_0        
        //   280: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   283: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   288: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   291: ifeq            318
        //   294: aload_0        
        //   295: aload_1        
        //   296: iload           7
        //   298: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.checkArcBridgeCast:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Z)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   301: astore          8
        //   303: aload           8
        //   305: ifnull          315
        //   308: aload           8
        //   310: areturn        
        //   311: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: goto            331
        //   318: iload           7
        //   320: ifeq            331
        //   323: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   326: areturn        
        //   327: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload_0        
        //   332: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   335: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   340: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   343: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   348: istore          7
        //   350: aload_1        
        //   351: aload_0        
        //   352: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   355: aload_0        
        //   356: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   359: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.validateConstPointers:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   362: astore          8
        //   364: aload           8
        //   366: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   369: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   372: if_acmpeq       382
        //   375: aload           8
        //   377: areturn        
        //   378: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: aload_2        
        //   383: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   386: ifeq            442
        //   389: aload_3        
        //   390: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   393: ifeq            442
        //   396: goto            403
        //   399: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   402: athrow         
        //   403: aload_1        
        //   404: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   407: aload_0        
        //   408: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   411: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   414: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   417: aload_0        
        //   418: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   421: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   424: ifeq            442
        //   427: goto            434
        //   430: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   433: athrow         
        //   434: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   437: areturn        
        //   438: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   441: athrow         
        //   442: aload_1        
        //   443: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVoid:()Z
        //   446: ifeq            469
        //   449: iload           7
        //   451: ifne            469
        //   454: goto            461
        //   457: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   464: areturn        
        //   465: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   468: athrow         
        //   469: aload_0        
        //   470: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   473: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   476: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVoid:()Z
        //   479: ifeq            502
        //   482: iload           7
        //   484: ifne            502
        //   487: goto            494
        //   490: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   497: areturn        
        //   498: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   501: athrow         
        //   502: aload_2        
        //   503: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   506: ifeq            567
        //   509: aload_3        
        //   510: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   513: ifeq            567
        //   516: goto            523
        //   519: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   522: athrow         
        //   523: iload           4
        //   525: iload           5
        //   527: if_icmpne       567
        //   530: goto            537
        //   533: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   536: athrow         
        //   537: aload_1        
        //   538: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   541: aload_0        
        //   542: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   545: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   548: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   551: aload_0        
        //   552: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   555: aload_0        
        //   556: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   559: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   562: areturn        
        //   563: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: aload_2        
        //   568: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   571: ifeq            582
        //   574: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   577: areturn        
        //   578: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: aload_0        
        //   583: aload_2        
        //   584: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   587: ifeq            708
        //   590: aload_0        
        //   591: aload_3        
        //   592: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.isCppClassType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   595: ifeq            708
        //   598: goto            605
        //   601: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   604: athrow         
        //   605: iload           4
        //   607: iload           5
        //   609: if_icmpne       708
        //   612: goto            619
        //   615: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   618: athrow         
        //   619: aload_1        
        //   620: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   623: aload_0        
        //   624: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   627: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   630: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   633: aload_0        
        //   634: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   637: aload_0        
        //   638: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   641: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   644: astore          9
        //   646: iload           7
        //   648: ifeq            668
        //   651: aload_0        
        //   652: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   655: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   658: ifeq            705
        //   661: goto            668
        //   664: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   667: athrow         
        //   668: aload           9
        //   670: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   673: aload_0        
        //   674: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   677: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   680: ifeq            705
        //   683: goto            690
        //   686: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   689: athrow         
        //   690: aload           9
        //   692: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   695: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setState:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;)V
        //   698: goto            705
        //   701: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   704: athrow         
        //   705: aload           9
        //   707: areturn        
        //   708: aload_2        
        //   709: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   712: ifeq            804
        //   715: aload_3        
        //   716: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   719: ifeq            804
        //   722: goto            729
        //   725: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   728: athrow         
        //   729: iload           4
        //   731: iload           5
        //   733: if_icmpne       804
        //   736: goto            743
        //   739: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   742: athrow         
        //   743: aload_1        
        //   744: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   747: ifne            774
        //   750: goto            757
        //   753: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   756: athrow         
        //   757: aload_0        
        //   758: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   761: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   764: ifne            804
        //   767: goto            774
        //   770: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   773: athrow         
        //   774: aload_1        
        //   775: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   778: aload_0        
        //   779: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   782: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   785: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   788: aload_0        
        //   789: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   792: aload_0        
        //   793: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   796: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   799: areturn        
        //   800: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   803: athrow         
        //   804: aload_1        
        //   805: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   808: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   811: ifeq            859
        //   814: aload_1        
        //   815: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   818: checkcast       Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   821: invokevirtual   com/jetbrains/cidr/lang/types/OCIdType.getAllProtocols:()Ljava/util/List;
        //   824: invokeinterface java/util/List.isEmpty:()Z
        //   829: ifeq            859
        //   832: goto            839
        //   835: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   838: athrow         
        //   839: aload_0        
        //   840: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   843: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   846: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObjectCompatible:()Z
        //   849: ifne            947
        //   852: goto            859
        //   855: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   858: athrow         
        //   859: aload_1        
        //   860: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObjectCompatible:()Z
        //   863: ifeq            955
        //   866: goto            873
        //   869: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   872: athrow         
        //   873: aload_0        
        //   874: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   877: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   880: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToID:()Z
        //   883: ifeq            955
        //   886: goto            893
        //   889: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   892: athrow         
        //   893: aload_0        
        //   894: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   897: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   900: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   903: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   906: ifeq            955
        //   909: goto            916
        //   912: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   915: athrow         
        //   916: aload_0        
        //   917: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   920: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   923: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   926: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   929: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //   932: invokeinterface java/util/List.isEmpty:()Z
        //   937: ifeq            955
        //   940: goto            947
        //   943: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   946: athrow         
        //   947: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   950: areturn        
        //   951: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   954: athrow         
        //   955: aload_2        
        //   956: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   959: ifne            976
        //   962: aload_3        
        //   963: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   966: ifeq            1019
        //   969: goto            976
        //   972: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   975: athrow         
        //   976: aload_1        
        //   977: aload_0        
        //   978: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   981: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   984: aload_0        
        //   985: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   988: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   991: aload_0        
        //   992: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.myContext:Lcom/intellij/psi/PsiElement;
        //   995: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   998: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1001: ifeq            1019
        //  1004: goto            1011
        //  1007: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1010: athrow         
        //  1011: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //  1014: areturn        
        //  1015: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1018: athrow         
        //  1019: aload           8
        //  1021: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1024: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1027: if_acmpne       1037
        //  1030: aload           8
        //  1032: areturn        
        //  1033: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCPointerType.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1036: athrow         
        //  1037: aload           6
        //  1039: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  56     76     79     83     Ljava/lang/IllegalArgumentException;
        //  63     107    110    114    Ljava/lang/IllegalArgumentException;
        //  83     117    117    121    Ljava/lang/IllegalArgumentException;
        //  121    138    141    145    Ljava/lang/IllegalArgumentException;
        //  128    158    161    165    Ljava/lang/IllegalArgumentException;
        //  145    181    184    188    Ljava/lang/IllegalArgumentException;
        //  165    191    191    195    Ljava/lang/IllegalArgumentException;
        //  195    209    212    216    Ljava/lang/IllegalArgumentException;
        //  202    223    226    230    Ljava/lang/IllegalArgumentException;
        //  216    237    240    244    Ljava/lang/IllegalArgumentException;
        //  230    262    265    269    Ljava/lang/IllegalArgumentException;
        //  303    311    311    315    Ljava/lang/IllegalArgumentException;
        //  318    327    327    331    Ljava/lang/IllegalArgumentException;
        //  364    378    378    382    Ljava/lang/IllegalArgumentException;
        //  382    396    399    403    Ljava/lang/IllegalArgumentException;
        //  389    427    430    434    Ljava/lang/IllegalArgumentException;
        //  403    438    438    442    Ljava/lang/IllegalArgumentException;
        //  442    454    457    461    Ljava/lang/IllegalArgumentException;
        //  449    465    465    469    Ljava/lang/IllegalArgumentException;
        //  469    487    490    494    Ljava/lang/IllegalArgumentException;
        //  482    498    498    502    Ljava/lang/IllegalArgumentException;
        //  502    516    519    523    Ljava/lang/IllegalArgumentException;
        //  509    530    533    537    Ljava/lang/IllegalArgumentException;
        //  523    563    563    567    Ljava/lang/IllegalArgumentException;
        //  567    578    578    582    Ljava/lang/IllegalArgumentException;
        //  582    598    601    605    Ljava/lang/IllegalArgumentException;
        //  590    612    615    619    Ljava/lang/IllegalArgumentException;
        //  646    661    664    668    Ljava/lang/IllegalArgumentException;
        //  651    683    686    690    Ljava/lang/IllegalArgumentException;
        //  668    698    701    705    Ljava/lang/IllegalArgumentException;
        //  708    722    725    729    Ljava/lang/IllegalArgumentException;
        //  715    736    739    743    Ljava/lang/IllegalArgumentException;
        //  729    750    753    757    Ljava/lang/IllegalArgumentException;
        //  743    767    770    774    Ljava/lang/IllegalArgumentException;
        //  757    800    800    804    Ljava/lang/IllegalArgumentException;
        //  804    832    835    839    Ljava/lang/IllegalArgumentException;
        //  814    852    855    859    Ljava/lang/IllegalArgumentException;
        //  839    866    869    873    Ljava/lang/IllegalArgumentException;
        //  859    886    889    893    Ljava/lang/IllegalArgumentException;
        //  873    909    912    916    Ljava/lang/IllegalArgumentException;
        //  893    940    943    947    Ljava/lang/IllegalArgumentException;
        //  916    951    951    955    Ljava/lang/IllegalArgumentException;
        //  955    969    972    976    Ljava/lang/IllegalArgumentException;
        //  962    1004   1007   1011   Ljava/lang/IllegalArgumentException;
        //  976    1015   1015   1019   Ljava/lang/IllegalArgumentException;
        //  1019   1033   1033   1037   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0083:
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
                return OCTypeCompatibilityVisitor_OCPointerType.OK_RESULT;
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
