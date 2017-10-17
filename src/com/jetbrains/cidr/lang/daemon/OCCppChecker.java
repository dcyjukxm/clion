// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.types.visitors.OCTypeNameVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCMoveDefinitionIntentionAction;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.quickfixes.OCChangeVisibilityIntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCSafeDeleteIntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTextIntentionAction;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.quickfixes.OCCreateNewDefinitionIntentionAction;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.quickfixes.OCChangeFunctionSignatureIntentionAction;
import com.jetbrains.cidr.OCAnnotatingArgumentsChecker;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.quickfixes.OCAddInitializerIntentionAction;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.quickfixes.OCAddFieldInitializerFix;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import java.util.Collections;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.intentions.OCAddParametersToConstructorIntentionAction;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.quickfixes.OCAddSuperConstructorCallsFix;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import java.util.HashSet;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCCppBaseClauseList;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.quickfixes.OCGenerateConstructorFix;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveTypeModifierIntentionAction;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;
import com.jetbrains.cidr.lang.editor.colors.OCHighlightingKeys;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.ArrayList;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.quickfixes.OCChangeElementIntentionAction;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCMakeFunctionVirtualFix;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;

public class OCCppChecker extends OCAnnotatorSinkWrapper
{
    public OCCppChecker(@NotNull final OCAnnotatorSink ocAnnotatorSink) {
        if (ocAnnotatorSink == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "impl", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "<init>"));
        }
        super(ocAnnotatorSink);
    }
    
    @Nullable
    public Annotation checkAssignment(@Nullable final OCExpression p0, @Nullable final OCSymbol p1, @Nullable final String p2, @NotNull final PsiElement p3, @NotNull final OCType p4, @NotNull final OCType p5, @Nullable final OCSymbol p6, @Nullable final String p7, @Nullable final OCType p8, final boolean p9, @Nullable final Class<? extends OCInspection> p10, @Nullable final String p11, @NotNull final String p12) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "annotationElement"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "checkAssignment"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload           5
        //    47: ifnonnull       90
        //    50: new             Ljava/lang/IllegalArgumentException;
        //    53: dup            
        //    54: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    56: ldc             3
        //    58: anewarray       Ljava/lang/Object;
        //    61: dup            
        //    62: ldc             0
        //    64: ldc             "lType"
        //    66: aastore        
        //    67: dup            
        //    68: ldc             1
        //    70: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //    72: aastore        
        //    73: dup            
        //    74: ldc             2
        //    76: ldc             "checkAssignment"
        //    78: aastore        
        //    79: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    82: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    85: athrow         
        //    86: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload           6
        //    92: ifnonnull       135
        //    95: new             Ljava/lang/IllegalArgumentException;
        //    98: dup            
        //    99: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   101: ldc             3
        //   103: anewarray       Ljava/lang/Object;
        //   106: dup            
        //   107: ldc             0
        //   109: ldc             "rType"
        //   111: aastore        
        //   112: dup            
        //   113: ldc             1
        //   115: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //   117: aastore        
        //   118: dup            
        //   119: ldc             2
        //   121: ldc             "checkAssignment"
        //   123: aastore        
        //   124: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   127: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   130: athrow         
        //   131: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload           13
        //   137: ifnonnull       180
        //   140: new             Ljava/lang/IllegalArgumentException;
        //   143: dup            
        //   144: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   146: ldc             3
        //   148: anewarray       Ljava/lang/Object;
        //   151: dup            
        //   152: ldc             0
        //   154: ldc             "messagePrefix"
        //   156: aastore        
        //   157: dup            
        //   158: ldc             1
        //   160: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //   162: aastore        
        //   163: dup            
        //   164: ldc             2
        //   166: ldc             "checkAssignment"
        //   168: aastore        
        //   169: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   172: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   175: athrow         
        //   176: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload           5
        //   182: aload           6
        //   184: aload_1        
        //   185: aload           4
        //   187: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   190: astore          14
        //   192: aconst_null    
        //   193: astore          15
        //   195: aload           14
        //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getAnnotationElement:()Lcom/intellij/psi/PsiElement;
        //   200: ifnull          210
        //   203: aload           14
        //   205: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getAnnotationElement:()Lcom/intellij/psi/PsiElement;
        //   208: astore          4
        //   210: aload           11
        //   212: ifnonnull       229
        //   215: aload           14
        //   217: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getInspectionClass:()Ljava/lang/Class;
        //   220: astore          11
        //   222: aload           14
        //   224: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getClangID:()Ljava/lang/String;
        //   227: astore          12
        //   229: aload           14
        //   231: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   234: aload           4
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   239: ifeq            315
        //   242: iload           10
        //   244: ifeq            293
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_0        
        //   255: aload           4
        //   257: aload           11
        //   259: aload           12
        //   261: aload           13
        //   263: aload           14
        //   265: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;)Ljava/lang/String;
        //   268: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   271: astore          15
        //   273: aload           15
        //   275: ifnull          401
        //   278: aload           15
        //   280: getstatic       com/intellij/codeInspection/ProblemHighlightType.GENERIC_ERROR_OR_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   283: invokevirtual   com/intellij/lang/annotation/Annotation.setHighlightType:(Lcom/intellij/codeInspection/ProblemHighlightType;)V
        //   286: goto            401
        //   289: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload_0        
        //   294: aload           4
        //   296: aload           11
        //   298: aload           12
        //   300: aload           13
        //   302: aload           14
        //   304: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;)Ljava/lang/String;
        //   307: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   310: astore          15
        //   312: goto            401
        //   315: aload           14
        //   317: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   320: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isOK:()Z
        //   323: ifne            401
        //   326: aload           14
        //   328: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   331: aload           4
        //   333: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isWarning:(Lcom/intellij/psi/PsiElement;)Z
        //   336: ifeq            368
        //   339: goto            346
        //   342: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: aload_0        
        //   347: aload           4
        //   349: aload           11
        //   351: aload           12
        //   353: aload           13
        //   355: aload           14
        //   357: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;)Ljava/lang/String;
        //   360: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   363: astore          15
        //   365: goto            401
        //   368: aload           14
        //   370: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   373: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.INFO:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   376: if_acmpne       401
        //   379: aload_0        
        //   380: aload           4
        //   382: aload           11
        //   384: aload           12
        //   386: aload           13
        //   388: aload           14
        //   390: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;)Ljava/lang/String;
        //   393: getstatic       com/intellij/codeInspection/ProblemHighlightType.WEAK_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   396: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   399: astore          15
        //   401: aload           14
        //   403: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getInspectionClass:()Ljava/lang/Class;
        //   406: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   408: if_acmpne       419
        //   411: iconst_1       
        //   412: goto            420
        //   415: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: iconst_0       
        //   420: istore          16
        //   422: aload_1        
        //   423: ifnull          577
        //   426: aload           14
        //   428: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   431: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isOK:()Z
        //   434: ifne            577
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: aload           14
        //   446: aload           5
        //   448: aload           6
        //   450: aload_1        
        //   451: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.canBeCasted:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   454: ifne            476
        //   457: goto            464
        //   460: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: iload           16
        //   466: ifeq            577
        //   469: goto            476
        //   472: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   475: athrow         
        //   476: aload           5
        //   478: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   481: ifeq            555
        //   484: goto            491
        //   487: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   490: athrow         
        //   491: aload           6
        //   493: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   496: ifeq            555
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload           6
        //   508: aload           5
        //   510: aload_1        
        //   511: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   514: ifne            555
        //   517: goto            524
        //   520: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   523: athrow         
        //   524: aload           5
        //   526: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   529: ifne            555
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: aload_1        
        //   540: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getProject:()Lcom/intellij/openapi/project/Project;
        //   545: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   548: goto            557
        //   551: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   554: athrow         
        //   555: aload           5
        //   557: astore          17
        //   559: aload_0        
        //   560: aload           15
        //   562: new             Lcom/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction;
        //   565: dup            
        //   566: aload_1        
        //   567: aload           17
        //   569: iload           16
        //   571: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //   574: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   577: aload           15
        //   579: ifnull          784
        //   582: aload_1        
        //   583: ifnull          634
        //   586: goto            593
        //   589: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   592: athrow         
        //   593: aload_0        
        //   594: aload           15
        //   596: new             Lcom/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction;
        //   599: dup            
        //   600: aload_1        
        //   601: aload           5
        //   603: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   606: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   609: aload_0        
        //   610: aload           15
        //   612: new             Lcom/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction;
        //   615: dup            
        //   616: aload_1        
        //   617: aload           5
        //   619: aload           6
        //   621: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   624: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   627: goto            634
        //   630: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   633: athrow         
        //   634: aload           14
        //   636: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getQuickFixes:()[Lcom/intellij/codeInsight/intention/IntentionAction;
        //   639: ifnull          685
        //   642: aload           14
        //   644: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getQuickFixes:()[Lcom/intellij/codeInsight/intention/IntentionAction;
        //   647: astore          17
        //   649: aload           17
        //   651: arraylength    
        //   652: istore          18
        //   654: iconst_0       
        //   655: istore          19
        //   657: iload           19
        //   659: iload           18
        //   661: if_icmpge       685
        //   664: aload           17
        //   666: iload           19
        //   668: aaload         
        //   669: astore          20
        //   671: aload_0        
        //   672: aload           15
        //   674: aload           20
        //   676: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   679: iinc            19, 1
        //   682: goto            657
        //   685: aload           7
        //   687: ifnull          733
        //   690: aload           9
        //   692: ifnull          733
        //   695: goto            702
        //   698: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   701: athrow         
        //   702: aload_0        
        //   703: aload           15
        //   705: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   708: dup            
        //   709: aload           7
        //   711: aload           9
        //   713: aload           4
        //   715: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   718: aload           8
        //   720: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)V
        //   723: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   726: goto            733
        //   729: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   732: athrow         
        //   733: aload_2        
        //   734: ifnull          761
        //   737: aload_0        
        //   738: aload           15
        //   740: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   743: dup            
        //   744: aload_2        
        //   745: aload           5
        //   747: aload_3        
        //   748: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)V
        //   751: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   754: goto            784
        //   757: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   760: athrow         
        //   761: aload_1        
        //   762: ifnull          784
        //   765: aload_0        
        //   766: aload           15
        //   768: aload_1        
        //   769: aload           5
        //   771: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   774: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   777: goto            784
        //   780: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   783: athrow         
        //   784: aload           14
        //   786: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getImplicitConstructor:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   789: astore          17
        //   791: aload           17
        //   793: ifnull          813
        //   796: aload_0        
        //   797: aload           17
        //   799: aload           4
        //   801: aconst_null    
        //   802: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFieldVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   805: pop            
        //   806: goto            813
        //   809: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   812: athrow         
        //   813: aload           15
        //   815: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/Class<+Lcom/jetbrains/cidr/lang/inspections/OCInspection;>;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     86     86     90     Ljava/lang/IllegalArgumentException;
        //  90     131    131    135    Ljava/lang/IllegalArgumentException;
        //  135    176    176    180    Ljava/lang/IllegalArgumentException;
        //  229    247    250    254    Ljava/lang/IllegalArgumentException;
        //  273    289    289    293    Ljava/lang/IllegalArgumentException;
        //  315    339    342    346    Ljava/lang/IllegalArgumentException;
        //  401    415    415    419    Ljava/lang/IllegalArgumentException;
        //  422    437    440    444    Ljava/lang/IllegalArgumentException;
        //  426    457    460    464    Ljava/lang/IllegalArgumentException;
        //  444    469    472    476    Ljava/lang/IllegalArgumentException;
        //  464    484    487    491    Ljava/lang/IllegalArgumentException;
        //  476    499    502    506    Ljava/lang/IllegalArgumentException;
        //  491    517    520    524    Ljava/lang/IllegalArgumentException;
        //  506    532    535    539    Ljava/lang/IllegalArgumentException;
        //  524    551    551    555    Ljava/lang/IllegalArgumentException;
        //  577    586    589    593    Ljava/lang/IllegalArgumentException;
        //  582    627    630    634    Ljava/lang/IllegalArgumentException;
        //  685    695    698    702    Ljava/lang/IllegalArgumentException;
        //  690    726    729    733    Ljava/lang/IllegalArgumentException;
        //  733    757    757    761    Ljava/lang/IllegalArgumentException;
        //  761    777    780    784    Ljava/lang/IllegalArgumentException;
        //  791    806    809    813    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0444:
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
    public Annotation checkAssignment(@Nullable final OCExpression ocExpression, @NotNull final PsiElement psiElement, @NotNull final OCType ocType, @NotNull final OCType ocType2, @Nullable final OCSymbol ocSymbol, @Nullable final OCType ocType3, final boolean b, @NotNull final String s) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "navigationElement", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messagePrefix", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return this.checkAssignment(ocExpression, null, null, psiElement, ocType, ocType2, ocSymbol, null, ocType3, b, null, null, s);
    }
    
    @Nullable
    public Annotation checkAssignment(@Nullable final OCExpression ocExpression, @NotNull final PsiElement psiElement, @NotNull final OCType ocType, @NotNull final OCType ocType2, @Nullable final OCSymbol ocSymbol, @NotNull final String s) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "navigationElement", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messagePrefix", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return this.checkAssignment(ocExpression, null, null, psiElement, ocType, ocType2, ocSymbol, null, ocType2, false, null, null, s);
    }
    
    @Nullable
    public Annotation checkAssignment(@Nullable final OCExpression ocExpression, @NotNull final PsiElement psiElement, @NotNull final OCType ocType, @NotNull final OCType ocType2, @Nullable final OCSymbol ocSymbol, @Nullable final OCType ocType3) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "navigationElement", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return this.checkAssignment(ocExpression, psiElement, ocType, ocType2, ocSymbol, ocType3, false, "");
    }
    
    @Nullable
    public Annotation checkAssignment(@Nullable final OCExpression ocExpression, @NotNull final PsiElement psiElement, @NotNull final OCType ocType, @NotNull final OCType ocType2, @NotNull final String s) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "navigationElement", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rType", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messagePrefix", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "checkAssignment"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return this.checkAssignment(ocExpression, psiElement, ocType, ocType2, null, null, false, s);
    }
    
    public void checkCppFunction(final OCFunctionDeclaration p0, final OCFunctionSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    11: astore_3       
        //    12: aload_3        
        //    13: ifnull          29
        //    16: aload_0        
        //    17: aload_2        
        //    18: aload_3        
        //    19: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/psi/OCExpression;)V
        //    22: goto            174
        //    25: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_2        
        //    30: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPredeclaration:()Z
        //    33: ifeq            174
        //    36: aload_2        
        //    37: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    40: ifeq            174
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_2        
        //    51: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDefault:()Z
        //    54: ifne            174
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_2        
        //    65: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDelete:()Z
        //    68: ifne            174
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_2        
        //    79: iconst_1       
        //    80: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.shouldGenerateDefinitionsFor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //    83: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.REQUIRED:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //    86: if_acmpne       174
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: aload_1        
        //    98: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotImplementedFunctions;.class
        //   100: ldc             "CIDR"
        //   102: new             Ljava/lang/StringBuilder;
        //   105: dup            
        //   106: invokespecial   java/lang/StringBuilder.<init>:()V
        //   109: aload_2        
        //   110: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: ldc             " is not implemented"
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   124: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   127: astore          4
        //   129: aload_0        
        //   130: aload           4
        //   132: new             Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateDefinitionQuickFix;
        //   135: dup            
        //   136: aload_2        
        //   137: iconst_1       
        //   138: invokespecial   com/jetbrains/cidr/lang/generate/actions/OCGenerateDefinitionQuickFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)V
        //   141: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   144: aload_0        
        //   145: aload           4
        //   147: new             Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateDefinitionQuickFix;
        //   150: dup            
        //   151: aload_2        
        //   152: iconst_0       
        //   153: invokespecial   com/jetbrains/cidr/lang/generate/actions/OCGenerateDefinitionQuickFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)V
        //   156: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   159: aload_0        
        //   160: aload           4
        //   162: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix;
        //   165: dup            
        //   166: aload_2        
        //   167: iconst_1       
        //   168: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)V
        //   171: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   174: new             Lcom/intellij/openapi/util/Ref;
        //   177: dup            
        //   178: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   181: astore          4
        //   183: new             Lcom/jetbrains/cidr/lang/daemon/OCCppChecker$1;
        //   186: dup            
        //   187: aload_0        
        //   188: aload           4
        //   190: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker$1.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;Lcom/intellij/openapi/util/Ref;)V
        //   193: astore          5
        //   195: new             Lcom/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery;
        //   198: dup            
        //   199: aload_2        
        //   200: iconst_1       
        //   201: iconst_0       
        //   202: invokespecial   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZZ)V
        //   205: aload           5
        //   207: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.forEach:(Lcom/intellij/util/Processor;)Z
        //   210: pop            
        //   211: aload           5
        //   213: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   216: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   219: astore          6
        //   221: aload           4
        //   223: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   226: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   229: astore          7
        //   231: aload           6
        //   233: ifnull          280
        //   236: aload_0        
        //   237: aload_1        
        //   238: aload_2        
        //   239: aload           6
        //   241: iconst_1       
        //   242: ldc             "derived function"
        //   244: new             Ljava/lang/StringBuilder;
        //   247: dup            
        //   248: invokespecial   java/lang/StringBuilder.<init>:()V
        //   251: ldc             "virtual "
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: aload_2        
        //   257: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   260: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   266: ldc             "base function"
        //   268: ldc             "err_different_return_type_for_overriding_virtual_function"
        //   270: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   273: goto            381
        //   276: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_2        
        //   281: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   284: ifnull          381
        //   287: aload           7
        //   289: ifnull          381
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload           7
        //   301: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   304: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   307: if_acmpeq       381
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: new             Ljava/lang/StringBuilder;
        //   320: dup            
        //   321: invokespecial   java/lang/StringBuilder.<init>:()V
        //   324: aload_2        
        //   325: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: ldc             " hides a non-virtual function from "
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: aload           7
        //   338: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   341: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getNameWithKindLowercase:()Ljava/lang/String;
        //   344: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   350: astore          8
        //   352: aload_0        
        //   353: aload_1        
        //   354: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$HidingNonVirtualFunction;.class
        //   356: ldc             "CIDR"
        //   358: aload           8
        //   360: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   363: astore          9
        //   365: aload_0        
        //   366: aload           9
        //   368: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix;
        //   371: dup            
        //   372: aload           7
        //   374: iconst_0       
        //   375: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)V
        //   378: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   381: aload_2        
        //   382: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   385: ifeq            429
        //   388: aload_2        
        //   389: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDefinition:()Z
        //   392: ifeq            429
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: aload_1        
        //   403: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   406: ifeq            429
        //   409: goto            416
        //   412: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: aload_0        
        //   417: aload_1        
        //   418: aload_2        
        //   419: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.c:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //   422: goto            429
        //   425: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: aload_0        
        //   430: aload_1        
        //   431: aload_2        
        //   432: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //   435: aload_0        
        //   436: aload_1        
        //   437: aload_2        
        //   438: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //   441: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     25     25     29     Ljava/lang/IllegalArgumentException;
        //  29     43     46     50     Ljava/lang/IllegalArgumentException;
        //  36     57     60     64     Ljava/lang/IllegalArgumentException;
        //  50     71     74     78     Ljava/lang/IllegalArgumentException;
        //  64     89     92     96     Ljava/lang/IllegalArgumentException;
        //  231    276    276    280    Ljava/lang/IllegalArgumentException;
        //  280    292    295    299    Ljava/lang/IllegalArgumentException;
        //  287    310    313    317    Ljava/lang/IllegalArgumentException;
        //  381    395    398    402    Ljava/lang/IllegalArgumentException;
        //  388    409    412    416    Ljava/lang/IllegalArgumentException;
        //  402    422    425    429    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
    
    private void a(final OCFunctionDeclaration p0, final OCFunctionSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //    11: astore_3       
        //    12: aload_3        
        //    13: ifnull          43
        //    16: aload_2        
        //    17: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDefinition:()Z
        //    20: ifeq            43
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_3        
        //    31: aload_2        
        //    32: iconst_1       
        //    33: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.getPredeclarationInParent:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Z)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    38: astore          4
        //    40: goto            150
        //    43: aload_2        
        //    44: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    47: astore          4
        //    49: aload           4
        //    51: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    54: ifeq            150
        //    57: aload           4
        //    59: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDefinition:()Z
        //    64: ifeq            150
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload           4
        //    76: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    81: astore          5
        //    83: aload           5
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    88: ifeq            108
        //    91: aload           5
        //    93: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    96: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   101: goto            109
        //   104: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aconst_null    
        //   109: astore_3       
        //   110: aload_3        
        //   111: ifnull          150
        //   114: aload_3        
        //   115: aload           4
        //   117: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   120: iconst_1       
        //   121: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.getPredeclarationInParent:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Z)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   126: astore          6
        //   128: aload           6
        //   130: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   133: ifeq            148
        //   136: aload           6
        //   138: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   141: goto            149
        //   144: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aconst_null    
        //   149: astore_2       
        //   150: aload_2        
        //   151: ifnull          318
        //   154: aload           4
        //   156: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   159: ifeq            318
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_2        
        //   170: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDefinition:()Z
        //   173: ifeq            247
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload           4
        //   185: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDefinition:()Z
        //   190: ifne            247
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_0        
        //   201: aload_1        
        //   202: aload_2        
        //   203: aload           4
        //   205: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   208: iconst_0       
        //   209: ldc             "function definition"
        //   211: new             Ljava/lang/StringBuilder;
        //   214: dup            
        //   215: invokespecial   java/lang/StringBuilder.<init>:()V
        //   218: aload_2        
        //   219: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: ldc             " definition"
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   233: ldc             "function declaration"
        //   235: ldc             "err_member_def_does_not_match_ret_type"
        //   237: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   240: goto            318
        //   243: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aload_2        
        //   248: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDefinition:()Z
        //   251: ifne            318
        //   254: aload           4
        //   256: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDefinition:()Z
        //   261: ifeq            318
        //   264: goto            271
        //   267: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: aload_0        
        //   272: aload_1        
        //   273: aload_2        
        //   274: aload           4
        //   276: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   279: iconst_0       
        //   280: ldc             "function declaration"
        //   282: new             Ljava/lang/StringBuilder;
        //   285: dup            
        //   286: invokespecial   java/lang/StringBuilder.<init>:()V
        //   289: aload_2        
        //   290: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: ldc             " declaration"
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   301: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   304: ldc             "function definition"
        //   306: ldc             "err_member_def_does_not_match_ret_type"
        //   308: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   311: goto            318
        //   314: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   317: athrow         
        //   318: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     23     26     30     Ljava/lang/IllegalArgumentException;
        //  49     67     70     74     Ljava/lang/IllegalArgumentException;
        //  83     104    104    108    Ljava/lang/IllegalArgumentException;
        //  128    144    144    148    Ljava/lang/IllegalArgumentException;
        //  150    162    165    169    Ljava/lang/IllegalArgumentException;
        //  154    176    179    183    Ljava/lang/IllegalArgumentException;
        //  169    193    196    200    Ljava/lang/IllegalArgumentException;
        //  183    243    243    247    Ljava/lang/IllegalArgumentException;
        //  247    264    267    271    Ljava/lang/IllegalArgumentException;
        //  254    311    314    318    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
    
    private void b(final OCFunctionDeclaration p0, final OCFunctionSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //     4: astore_3       
        //     5: aload_3        
        //     6: ifnull          26
        //     9: aload_3        
        //    10: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    13: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    16: ifne            343
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: iconst_0       
        //    27: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    30: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //    33: astore          4
        //    35: new             Lcom/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery;
        //    38: dup            
        //    39: aload_2        
        //    40: iconst_1       
        //    41: iconst_0       
        //    42: invokespecial   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZZ)V
        //    45: aload_0        
        //    46: aload           4
        //    48: aload_2        
        //    49: aload_1        
        //    50: invokedynamic   process:(Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;)Lcom/intellij/util/Processor;
        //    55: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.forEach:(Lcom/intellij/util/Processor;)Z
        //    58: pop            
        //    59: iconst_0       
        //    60: istore          5
        //    62: aload_1        
        //    63: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getVirtualSpecifiers:()Ljava/util/List;
        //    68: astore          6
        //    70: aload           6
        //    72: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    77: astore          7
        //    79: aload           7
        //    81: invokeinterface java/util/Iterator.hasNext:()Z
        //    86: ifeq            343
        //    89: aload           7
        //    91: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    96: checkcast       Lcom/intellij/psi/PsiElement;
        //    99: astore          8
        //   101: aload           8
        //   103: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   106: astore          9
        //   108: aload_0        
        //   109: aload           8
        //   111: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_KEYWORD:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   114: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.highlight:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/lang/annotation/Annotation;
        //   117: astore          10
        //   119: iload           5
        //   121: ifne            340
        //   124: aload           4
        //   126: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   129: checkcast       Ljava/lang/Boolean;
        //   132: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   135: ifne            340
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CPP_VIRTUAL_SPECIFIERS:Lcom/intellij/psi/tree/TokenSet;
        //   148: aload           9
        //   150: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   153: ifeq            340
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload           9
        //   165: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FINAL_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   168: if_acmpne       199
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_2        
        //   179: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
        //   182: ifeq            199
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: goto            79
        //   195: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload           9
        //   201: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OVERRIDE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   204: if_acmpne       216
        //   207: ldc             "override_keyword_only_allowed_on_virtual_member_functions"
        //   209: goto            218
        //   212: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: ldc             "err_function_marked_override_not_overriding"
        //   218: astore          11
        //   220: aload           9
        //   222: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OVERRIDE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   225: if_acmpne       237
        //   228: ldc             "Function doesn't override any base member functions"
        //   230: goto            239
        //   233: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: ldc             "Only virtual member functions can be marked 'final'"
        //   239: astore          12
        //   241: aload_0        
        //   242: aload           8
        //   244: aload           11
        //   246: aload           12
        //   248: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   251: astore          13
        //   253: aload_0        
        //   254: aload           13
        //   256: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction;
        //   259: dup            
        //   260: aload_2        
        //   261: aload           9
        //   263: checkcast       Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   266: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;)V
        //   269: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   272: iconst_1       
        //   273: istore          5
        //   275: aload           10
        //   277: ifnull          340
        //   280: aload           13
        //   282: ifnull          340
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: invokestatic    com/intellij/openapi/editor/colors/EditorColorsManager.getInstance:()Lcom/intellij/openapi/editor/colors/EditorColorsManager;
        //   295: invokevirtual   com/intellij/openapi/editor/colors/EditorColorsManager.getGlobalScheme:()Lcom/intellij/openapi/editor/colors/EditorColorsScheme;
        //   298: astore          14
        //   300: aload           14
        //   302: aload           10
        //   304: invokevirtual   com/intellij/lang/annotation/Annotation.getTextAttributes:()Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   307: invokeinterface com/intellij/openapi/editor/colors/EditorColorsScheme.getAttributes:(Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/openapi/editor/markup/TextAttributes;
        //   312: astore          15
        //   314: aload           14
        //   316: aload           13
        //   318: invokevirtual   com/intellij/lang/annotation/Annotation.getTextAttributes:()Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   321: invokeinterface com/intellij/openapi/editor/colors/EditorColorsScheme.getAttributes:(Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/openapi/editor/markup/TextAttributes;
        //   326: astore          16
        //   328: aload           13
        //   330: aload           15
        //   332: aload           16
        //   334: invokestatic    com/intellij/openapi/editor/markup/TextAttributes.merge:(Lcom/intellij/openapi/editor/markup/TextAttributes;Lcom/intellij/openapi/editor/markup/TextAttributes;)Lcom/intellij/openapi/editor/markup/TextAttributes;
        //   337: invokevirtual   com/intellij/lang/annotation/Annotation.setEnforcedTextAttributes:(Lcom/intellij/openapi/editor/markup/TextAttributes;)V
        //   340: goto            79
        //   343: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      19     22     26     Ljava/lang/IllegalArgumentException;
        //  119    138    141    145    Ljava/lang/IllegalArgumentException;
        //  124    156    159    163    Ljava/lang/IllegalArgumentException;
        //  145    171    174    178    Ljava/lang/IllegalArgumentException;
        //  163    185    188    192    Ljava/lang/IllegalArgumentException;
        //  178    195    195    199    Ljava/lang/IllegalArgumentException;
        //  199    212    212    216    Ljava/lang/IllegalArgumentException;
        //  220    233    233    237    Ljava/lang/IllegalArgumentException;
        //  275    285    288    292    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0145:
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
    
    private void a(final OCFunctionSymbol ocFunctionSymbol, final OCExpression ocExpression) {
        if (!OCCallableUtil.resolveIsVirtual(ocFunctionSymbol)) {
            final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)ocExpression, OCInspections.ConstructionIsNotAllowed.class, "err_non_virtual_pure", "Only virtual function can have pure specifier");
            this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCMakeFunctionVirtualFix(ocFunctionSymbol, false));
            this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocExpression, "Remove pure specifier"));
        }
        try {
            if (ocExpression instanceof OCLiteralExpression) {
                if ("0".equals(((OCLiteralExpression)ocExpression).getUnescapedLiteralText())) {
                    return;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Annotation addErrorAnnotation2 = this.addErrorAnnotation((PsiElement)ocExpression, OCInspections.ConstructionIsNotAllowed.class, "err_member_function_initialization", "Invalid pure specifier");
        this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCChangeElementIntentionAction((PsiElement)ocExpression, (PsiElement)OCElementFactory.expressionFromText("0", (PsiElement)ocExpression, false), "Change pure specifier to '= 0'"));
        this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocExpression, "Remove pure specifier"));
    }
    
    private void a(final OCFunctionDeclaration p0, final OCFunctionSymbol p1, final OCFunctionSymbol p2, final boolean p3, final String p4, final String p5, final String p6, final String p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     6: astore          9
        //     8: aload_3        
        //     9: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    12: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: aload_3        
        //    16: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    19: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    22: astore          10
        //    24: aload_2        
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    28: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    31: aload_2        
        //    32: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    38: astore          11
        //    40: aload           10
        //    42: aload_1        
        //    43: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    46: astore          12
        //    48: aload           11
        //    50: aload_1        
        //    51: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    54: astore          13
        //    56: aload_1        
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    62: astore          14
        //    64: aload           14
        //    66: ifnull          78
        //    69: aload           14
        //    71: goto            79
        //    74: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_1        
        //    79: astore          14
        //    81: aconst_null    
        //    82: astore          15
        //    84: iload           4
        //    86: ifeq            241
        //    89: aload           10
        //    91: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //    94: ifne            119
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload           10
        //   106: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   109: ifeq            241
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload           11
        //   121: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //   124: ifne            149
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload           11
        //   136: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   139: ifeq            241
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: new             Ljava/lang/StringBuilder;
        //   152: dup            
        //   153: invokespecial   java/lang/StringBuilder.<init>:()V
        //   156: ldc             "Return type of "
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: aload           6
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: ldc             " ("
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: aload           13
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: ldc             ") isn't derived from the return type of "
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: aload           7
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: ldc             " ("
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: aload           12
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: ldc             ")"
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   204: astore          16
        //   206: aload           10
        //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   211: aload           11
        //   213: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   216: aload           14
        //   218: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSuperType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   221: ifne            238
        //   224: aload_0        
        //   225: aload           14
        //   227: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DerivedFunctionsReturnTypeMismatch;.class
        //   229: ldc             "err_covariant_return_not_derived"
        //   231: aload           16
        //   233: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   236: astore          15
        //   238: goto            343
        //   241: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
        //   244: dup            
        //   245: aload           11
        //   247: iconst_1       
        //   248: iconst_1       
        //   249: iconst_0       
        //   250: iconst_0       
        //   251: iconst_1       
        //   252: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   255: dup            
        //   256: aload           9
        //   258: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   261: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   264: aload           10
        //   266: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   269: ifne            343
        //   272: new             Ljava/lang/StringBuilder;
        //   275: dup            
        //   276: invokespecial   java/lang/StringBuilder.<init>:()V
        //   279: ldc             "Return type of "
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: aload           6
        //   286: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   289: ldc             " ("
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: aload           13
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: ldc             ") differs from the return type of "
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: aload           7
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: ldc             " ("
        //   311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: aload           12
        //   316: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   319: ldc             ")"
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   327: astore          16
        //   329: aload_0        
        //   330: aload           14
        //   332: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DerivedFunctionsReturnTypeMismatch;.class
        //   334: aload           8
        //   336: aload           16
        //   338: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   341: astore          15
        //   343: aload           15
        //   345: ifnull          393
        //   348: aload_0        
        //   349: aload           15
        //   351: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   354: dup            
        //   355: aload_2        
        //   356: aload           10
        //   358: iconst_1       
        //   359: aload           5
        //   361: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)V
        //   364: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   367: aload_0        
        //   368: aload           15
        //   370: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   373: dup            
        //   374: aload_3        
        //   375: aload           11
        //   377: iconst_1       
        //   378: aload           7
        //   380: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)V
        //   383: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   386: goto            393
        //   389: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   392: athrow         
        //   393: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  64     74     74     78     Ljava/lang/IllegalArgumentException;
        //  84     97     100    104    Ljava/lang/IllegalArgumentException;
        //  89     112    115    119    Ljava/lang/IllegalArgumentException;
        //  104    127    130    134    Ljava/lang/IllegalArgumentException;
        //  119    142    145    149    Ljava/lang/IllegalArgumentException;
        //  343    386    389    393    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0104:
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
    
    private static String a(final Collection<OCStructSymbol> collection) {
        boolean b = false;
        Label_0019: {
            try {
                if (collection.size() > 1) {
                    b = true;
                    break Label_0019;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        StringBuilder append2 = null;
        Label_0050: {
            StringBuilder append;
            try {
                append = new StringBuilder().append("Base ");
                if (b2) {
                    final String pluralize = StringUtil.pluralize("class");
                    break Label_0050;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final String pluralize = "class";
            try {
                append2 = append.append(pluralize).append(" ").append(StringUtil.join((Collection)collection, ocStructSymbol -> "'" + ocStructSymbol.getName() + "'", ", "));
                if (b2) {
                    final String s = " don't";
                    return append2.append(s).append(" have a default constructor").toString();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        final String s = " doesn't";
        return append2.append(s).append(" have a default constructor").toString();
    }
    
    private static List<OCStructSymbol> a(final OCStructSymbol ocStructSymbol, final PsiFile psiFile) {
        final ArrayList<OCStructSymbol> list = new ArrayList<OCStructSymbol>();
        final List<OCStructSymbol> list2;
        ocStructSymbol.processBaseClasses(new OCResolveContext((PsiElement)psiFile), (ocStructSymbol2, p2) -> {
            try {
                if (ocStructSymbol2 instanceof OCStructSymbol) {
                    list2.add(ocStructSymbol2);
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return true;
        });
        return list;
    }
    
    public void checkClass(final OCStruct ocStruct) {
        final ASTNode[] children = ocStruct.getNode().getChildren(OCTokenTypes.CPP_CLASS_VIRTUAL_SPECIFIERS);
        for (int length = children.length, i = 0; i < length; ++i) {
            this.highlight(children[i].getPsi(), OCHighlightingKeys.OC_KEYWORD);
        }
        final OCStructSymbol ocStructSymbol = ocStruct.getSymbol();
        Label_0091: {
            try {
                if (ocStructSymbol == null) {
                    return;
                }
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b = ocStructSymbol2.isPredeclaration();
                if (b) {
                    return;
                }
                break Label_0091;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b = ocStructSymbol2.isPredeclaration();
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCCppBaseClauseList baseClausesList = ocStruct.getBaseClausesList();
        if (baseClausesList != null) {
            for (final OCCppBaseClause ocCppBaseClause : baseClausesList.getBaseClauses()) {
                final OCReferenceElement referenceElement = ocCppBaseClause.getReferenceElement();
                OCSymbol resolveToSymbol = null;
                Label_0169: {
                    try {
                        if (referenceElement != null) {
                            resolveToSymbol = referenceElement.resolveToSymbol();
                            break Label_0169;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    resolveToSymbol = null;
                }
                final OCSymbol ocSymbol = resolveToSymbol;
                try {
                    if (!(ocSymbol instanceof OCStructSymbol) || !((OCStructSymbol)ocSymbol).isFinal()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)ocCppBaseClause, "err_class_marked_final_used_as_base", ocSymbol.getNameWithKindUppercase() + " is marked as final");
                this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCRemoveTypeModifierIntentionAction((OCSymbolWithQualifiedName)ocSymbol, OCTokenTypes.FINAL_CPP_KEYWORD));
                this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocCppBaseClause, "Remove '" + ocSymbol.getName() + "' from the base classes list"));
            }
        }
        try {
            if (!ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)new CommonProcessors.FindFirstProcessor(), true, new OCResolveContext((PsiElement)ocStruct))) {
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final List filter = ContainerUtil.filter((Collection)a(ocStructSymbol, ocStruct.getContainingFile()), ocStructSymbol -> {
            try {
                if (!ocStructSymbol.hasDefaultConstructor()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return false;
        });
        if (!filter.isEmpty()) {
            final Annotation addErrorAnnotation2 = this.addErrorAnnotation(ocStruct.getHeaderRange(), OCInspections.NoDefaultBaseConstructor.class, "err_missing_default_ctor", a(filter), ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
            this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCGenerateConstructorFix(ocStructSymbol, true));
            final Iterator<OCStructSymbol> iterator2 = filter.iterator();
            while (iterator2.hasNext()) {
                this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCGenerateConstructorFix(iterator2.next(), false));
            }
        }
    }
    
    private void c(final OCFunctionDeclaration ocFunctionDeclaration, final OCFunctionSymbol ocFunctionSymbol) {
        final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ocFunctionSymbol.getResolvedOwner();
        if (resolvedOwner instanceof OCStructSymbol) {
            final LinkedHashSet set = new LinkedHashSet<Object>(a((OCStructSymbol)resolvedOwner, ocFunctionDeclaration.getContainingFile()));
            final CommonProcessors.CollectProcessor<OCDeclaratorSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCDeclaratorSymbol>() {
                protected boolean accept(final OCDeclaratorSymbol ocDeclaratorSymbol) {
                    if (ocDeclaratorSymbol.getKind() == OCSymbolKind.STRUCT_FIELD && !ocDeclaratorSymbol.isFriendOrStatic() && !(ocDeclaratorSymbol.getResolvedType() instanceof OCArrayType)) {
                        final OCType resolvedType = ocDeclaratorSymbol.getResolvedType();
                        return resolvedType instanceof OCStructType || resolvedType instanceof OCCppReferenceType || ocDeclaratorSymbol.isConst();
                    }
                    return false;
                }
            };
            ((OCStructSymbol)resolvedOwner).processFields((Processor<OCDeclaratorSymbol>)collectProcessor);
            final HashSet set2 = new HashSet<Object>(collectProcessor.getResults());
            final OCConstructorInitializationList constructorInitializationList = ((OCFunctionDefinition)ocFunctionDeclaration).getConstructorInitializationList();
            if (constructorInitializationList != null) {
                final Iterator<OCConstructorFieldInitializer> iterator = constructorInitializationList.getInitializers().iterator();
                while (iterator.hasNext()) {
                    final OCReferenceElement referenceElement = iterator.next().getReferenceElement();
                    try {
                        if (referenceElement == null) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    for (final OCSymbol ocSymbol : OCSymbolReference.getLocalReference(OCSymbolReferenceResolver.getQualifiedName(referenceElement), (PsiElement)referenceElement).resolveToSymbols(true, false, referenceElement.getContainingFile())) {
                        try {
                            if (ocSymbol instanceof OCStructSymbol) {
                                set.remove(ocSymbol);
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        if (ocSymbol instanceof OCFunctionSymbol) {
                            final OCSymbolWithQualifiedName<OCElement> resolvedOwner2 = ((OCFunctionSymbol)ocSymbol).getResolvedOwner();
                            try {
                                if (resolvedOwner.equals(resolvedOwner2)) {
                                    return;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw b(ex3);
                            }
                            try {
                                if (resolvedOwner2 == null) {
                                    continue;
                                }
                                set.remove(resolvedOwner2);
                            }
                            catch (IllegalArgumentException ex4) {
                                throw b(ex4);
                            }
                        }
                        else {
                            Label_0309: {
                                try {
                                    if (!(ocSymbol instanceof OCDeclaratorSymbol)) {
                                        continue;
                                    }
                                    final HashSet set3 = set2;
                                    final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)ocSymbol;
                                    final boolean b = set3.contains(ocFunctionSymbol2);
                                    if (b) {
                                        break Label_0309;
                                    }
                                    continue;
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw b(ex5);
                                }
                                try {
                                    final HashSet set3 = set2;
                                    final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)ocSymbol;
                                    final boolean b = set3.contains(ocFunctionSymbol2);
                                    if (!b) {
                                        continue;
                                    }
                                    set2.remove(ocSymbol);
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw b(ex6);
                                }
                            }
                        }
                    }
                }
            }
            final List filter = ContainerUtil.filter((Collection)set, ocStructSymbol -> {
                try {
                    if (!ocStructSymbol.hasDefaultConstructor()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return false;
            });
            if (!filter.isEmpty()) {
                final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)ocFunctionDeclaration, OCInspections.NoDefaultBaseConstructor.class, "err_missing_default_ctor", a(filter));
                this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCAddSuperConstructorCallsFix(ocFunctionSymbol, filter));
                final Iterator<OCStructSymbol> iterator3 = filter.iterator();
                while (iterator3.hasNext()) {
                    this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCGenerateConstructorFix(iterator3.next(), false));
                }
            }
            else {
                final Iterator<OCStructSymbol> iterator4 = set.iterator();
                while (iterator4.hasNext()) {
                    this.checkFieldVisibility(iterator4.next().getDefaultConstructor(), (PsiElement)ocFunctionDeclaration, null);
                }
            }
            for (final OCDeclaratorSymbol ocDeclaratorSymbol : set2) {
                final OCType resolvedType = ocDeclaratorSymbol.getResolvedType();
                final OCDeclarator ocDeclarator = ((OCSymbolBase<OCDeclarator>)ocDeclaratorSymbol).locateDefinition();
                Label_0559: {
                    try {
                        if (ocDeclarator == null) {
                            break Label_0559;
                        }
                        final OCDeclarator ocDeclarator2 = ocDeclarator;
                        final OCExpression ocExpression = ocDeclarator2.getInitializer();
                        if (ocExpression != null) {
                            break Label_0559;
                        }
                        break Label_0559;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                    try {
                        final OCDeclarator ocDeclarator2 = ocDeclarator;
                        final OCExpression ocExpression = ocDeclarator2.getInitializer();
                        if (ocExpression != null) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw b(ex8);
                    }
                }
                if (resolvedType instanceof OCStructType) {
                    final OCStructSymbol symbol = ((OCStructType)resolvedType).getSymbol();
                    final OCFunctionSymbol defaultConstructor = symbol.getDefaultConstructor();
                    try {
                        if (defaultConstructor != null) {
                            this.checkFieldVisibility(defaultConstructor, (PsiElement)ocFunctionDeclaration, null);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw b(ex9);
                    }
                    try {
                        if (symbol.hasDefaultConstructor()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex10) {
                        throw b(ex10);
                    }
                }
                final Annotation addErrorAnnotation2 = this.addErrorAnnotation((PsiElement)ocFunctionDeclaration, OCInspections.FieldMustBeInitialized.class, "err_uninitialized_member_in_ctor", ocDeclaratorSymbol.getNameWithKindUppercase() + " must be initialized");
                Label_0788: {
                    try {
                        this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCAddParametersToConstructorIntentionAction() {
                            @Nullable
                            @Override
                            protected OCDeclaratorSymbol getField(final Editor editor, final PsiFile psiFile) {
                                return ocDeclaratorSymbol;
                            }
                            
                            @NotNull
                            @Override
                            public String getText() {
                                String string;
                                try {
                                    string = "Add " + ocDeclaratorSymbol.getNameWithKindLowercase() + " as a parameter to constructor";
                                    if (string == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCCppChecker$4", "getText"));
                                    }
                                }
                                catch (IllegalStateException ex) {
                                    throw b(ex);
                                }
                                return string;
                            }
                            
                            @Override
                            protected boolean enableChooseDialog(final Collection<OCFunctionSymbol> collection) {
                                return false;
                            }
                            
                            @Nullable
                            @Override
                            protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
                                return new OCCppActionContext<OCStructSymbol, OCFunctionSymbol>((OCStructSymbol)resolvedOwner, ocFunctionDeclaration) {
                                    @NotNull
                                    @Override
                                    public Collection<OCFunctionSymbol> getMemberCandidates() {
                                        List<OCFunctionSymbol> singletonList;
                                        try {
                                            singletonList = Collections.singletonList(ocFunctionSymbol);
                                            if (singletonList == null) {
                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCCppChecker$4$1", "getMemberCandidates"));
                                            }
                                        }
                                        catch (IllegalStateException ex) {
                                            throw a(ex);
                                        }
                                        return singletonList;
                                    }
                                    
                                    private static IllegalStateException a(final IllegalStateException ex) {
                                        return ex;
                                    }
                                };
                            }
                            
                            private static IllegalStateException b(final IllegalStateException ex) {
                                return ex;
                            }
                        });
                        if (resolvedType instanceof OCCppReferenceType) {
                            this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCChangeTypeIntentionAction(ocDeclaratorSymbol, ((OCCppReferenceType)resolvedType).getRefType()));
                            break Label_0788;
                        }
                    }
                    catch (IllegalArgumentException ex11) {
                        throw b(ex11);
                    }
                    try {
                        if (!(resolvedType instanceof OCStructType)) {
                            this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCAddFieldInitializerFix((OCFunctionDefinition)ocFunctionDeclaration, ocDeclaratorSymbol));
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw b(ex12);
                    }
                    try {
                        if (OCCompilerFeatures.supportsInClassInitialization(ocFunctionDeclaration.getContainingFile())) {
                            this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCAddInitializerIntentionAction(ocDeclarator, ocDeclaratorSymbol));
                        }
                    }
                    catch (IllegalArgumentException ex13) {
                        throw b(ex13);
                    }
                    try {
                        if (ocDeclaratorSymbol.isConst()) {
                            this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCRemoveTypeModifierIntentionAction(ocDeclaratorSymbol, OCTokenTypes.CONST_KEYWORD));
                        }
                    }
                    catch (IllegalArgumentException ex14) {
                        throw b(ex14);
                    }
                }
                try {
                    if (!(resolvedType instanceof OCStructType)) {
                        continue;
                    }
                    this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCGenerateConstructorFix(((OCStructType)resolvedType).getSymbol(), false));
                }
                catch (IllegalArgumentException ex15) {
                    throw b(ex15);
                }
            }
        }
    }
    
    public void checkTypeInitialization(@NotNull final OCElement p0, @Nullable final PsiElement p1, @NotNull final OCArgumentsList<OCExpression> p2, @Nullable final OCSymbol p3, final OCType p4, final boolean p5, final PsiElement p6) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkTypeInitialization"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "arguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkTypeInitialization"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getExprs:()Ljava/util/List;
        //    92: astore          8
        //    94: aload           8
        //    96: ifnonnull       104
        //    99: return         
        //   100: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload           5
        //   106: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   109: ifeq            147
        //   112: aload           5
        //   114: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   117: aload_3        
        //   118: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   121: dup            
        //   122: aload_1        
        //   123: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   126: iconst_1       
        //   127: iconst_0       
        //   128: aconst_null    
        //   129: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.findConstructor:(Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZLcom/intellij/util/Producer;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   132: astore          9
        //   134: aload_0        
        //   135: aload_1        
        //   136: aload           8
        //   138: aload           9
        //   140: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkConstructorCall:(Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   143: pop            
        //   144: goto            310
        //   147: aload           8
        //   149: invokeinterface java/util/List.size:()I
        //   154: iconst_1       
        //   155: if_icmple       236
        //   158: aload           5
        //   160: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   163: ifne            236
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload           5
        //   175: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   178: ifeq            209
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload           5
        //   190: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   193: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   196: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   199: ifne            236
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_0        
        //   210: aload           8
        //   212: iconst_1       
        //   213: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   218: checkcast       Lcom/intellij/psi/PsiElement;
        //   221: ldc             "err_excess_initializers"
        //   223: ldc             "Only one initializer is permitted"
        //   225: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   228: pop            
        //   229: goto            310
        //   232: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload           8
        //   238: invokeinterface java/util/List.size:()I
        //   243: iconst_1       
        //   244: if_icmpne       310
        //   247: aload           8
        //   249: iconst_0       
        //   250: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   255: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   258: astore          9
        //   260: aload           9
        //   262: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   267: astore          10
        //   269: iload           6
        //   271: ifeq            294
        //   274: aload_0        
        //   275: aload           5
        //   277: aload           10
        //   279: aload_2        
        //   280: aload           9
        //   282: aload           7
        //   284: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkTypeCast:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;)V
        //   287: goto            310
        //   290: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: aload_0        
        //   295: aload           9
        //   297: aload_1        
        //   298: aload           5
        //   300: aload           10
        //   302: aload           4
        //   304: aload           10
        //   306: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/intellij/lang/annotation/Annotation;
        //   309: pop            
        //   310: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/psi/PsiElement;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  94     100    100    104    Ljava/lang/IllegalArgumentException;
        //  147    166    169    173    Ljava/lang/IllegalArgumentException;
        //  158    181    184    188    Ljava/lang/IllegalArgumentException;
        //  173    202    205    209    Ljava/lang/IllegalArgumentException;
        //  188    232    232    236    Ljava/lang/IllegalArgumentException;
        //  269    290    290    294    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
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
    
    public boolean checkConstructorCall(@NotNull final OCElement p0, @NotNull final List<OCExpression> p1, @Nullable final OCSymbol p2) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkConstructorCall"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "arguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/OCCppChecker"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkConstructorCall"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_1       
        //    89: istore          4
        //    91: aload_3        
        //    92: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //    95: ifeq            165
        //    98: aload_3        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   102: astore          5
        //   104: aload_1        
        //   105: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   108: ifeq            148
        //   111: aload_1        
        //   112: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   115: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.isExplicitConstructorCall:()Z
        //   120: ifne            148
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload           5
        //   132: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getCause:()Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;
        //   135: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause.NoViable:Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;
        //   138: if_acmpeq       162
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload_0        
        //   149: aload_1        
        //   150: aload           5
        //   152: ldc             "No matching constructor"
        //   154: invokestatic    com/jetbrains/cidr/lang/daemon/OCFunctionGroupHelperKt.annotateAmbig:(Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;Ljava/lang/String;)Z
        //   157: ireturn        
        //   158: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: goto            811
        //   165: aload_3        
        //   166: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   169: ifeq            463
        //   172: iconst_0       
        //   173: istore          5
        //   175: aload_1        
        //   176: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   179: ifeq            204
        //   182: aload_1        
        //   183: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   186: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   191: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   194: ifne            212
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: iconst_1       
        //   205: goto            213
        //   208: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: iconst_0       
        //   213: istore          6
        //   215: iload           6
        //   217: ifeq            389
        //   220: aload_3        
        //   221: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   224: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   227: ifeq            389
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload_2        
        //   238: invokeinterface java/util/List.size:()I
        //   243: iconst_1       
        //   244: if_icmpne       389
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_3        
        //   255: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   258: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   261: astore          7
        //   263: aload_2        
        //   264: iconst_0       
        //   265: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   270: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   273: astore          8
        //   275: aload           8
        //   277: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   282: astore          9
        //   284: aload           7
        //   286: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   289: ifeq            389
        //   292: aload           8
        //   294: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   297: ifne            389
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: aload           7
        //   309: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   312: aload           9
        //   314: aload_1        
        //   315: iconst_0       
        //   316: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Z
        //   319: ifeq            389
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: iconst_1       
        //   330: istore          5
        //   332: aload_3        
        //   333: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   336: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   339: astore          10
        //   341: aload           10
        //   343: invokeinterface java/util/List.size:()I
        //   348: iconst_1       
        //   349: if_icmpne       383
        //   352: aload           10
        //   354: iconst_0       
        //   355: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   360: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   363: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   366: aload           9
        //   368: aload_1        
        //   369: iconst_0       
        //   370: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Z
        //   373: ifne            389
        //   376: goto            383
        //   379: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   382: athrow         
        //   383: iconst_1       
        //   384: ireturn        
        //   385: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: iload           5
        //   391: ifne            417
        //   394: aload_0        
        //   395: aload_1        
        //   396: aload_2        
        //   397: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List;)Ljava/lang/Boolean;
        //   400: astore          7
        //   402: aload           7
        //   404: ifnull          417
        //   407: aload           7
        //   409: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   412: ireturn        
        //   413: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: iload           6
        //   419: ifne            429
        //   422: iload           4
        //   424: ireturn        
        //   425: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: aload_3        
        //   430: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   435: aload_1        
        //   436: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   441: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   444: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   447: astore          7
        //   449: aload_0        
        //   450: aload_1        
        //   451: aload           7
        //   453: aload_2        
        //   454: aload_3        
        //   455: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFunctionArguments:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   458: istore          4
        //   460: goto            811
        //   463: aload_3        
        //   464: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   467: ifeq            811
        //   470: aload_3        
        //   471: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   476: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   479: if_acmpne       811
        //   482: goto            489
        //   485: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload_2        
        //   490: invokeinterface java/util/List.size:()I
        //   495: iconst_1       
        //   496: if_icmpne       636
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload_2        
        //   507: iconst_0       
        //   508: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   513: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   516: ifeq            636
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: aload_3        
        //   527: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   532: aload_1        
        //   533: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   538: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInitializerListType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiFile;)Z
        //   541: ifeq            589
        //   544: goto            551
        //   547: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   550: athrow         
        //   551: aload_0        
        //   552: aload_1        
        //   553: aload_2        
        //   554: invokespecial   com/jetbrains/cidr/lang/daemon/OCCppChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List;)Ljava/lang/Boolean;
        //   557: astore          5
        //   559: aload           5
        //   561: ifnull          579
        //   564: aload           5
        //   566: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   569: ifeq            587
        //   572: goto            579
        //   575: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   578: athrow         
        //   579: iconst_1       
        //   580: goto            588
        //   583: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   586: athrow         
        //   587: iconst_0       
        //   588: ireturn        
        //   589: aload_1        
        //   590: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   593: ifeq            620
        //   596: aload_3        
        //   597: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   600: iconst_0       
        //   601: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPOD:(Z)Z
        //   604: ifeq            620
        //   607: goto            614
        //   610: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   613: athrow         
        //   614: iconst_1       
        //   615: ireturn        
        //   616: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   619: athrow         
        //   620: aload_2        
        //   621: iconst_0       
        //   622: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   627: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   630: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializerExpressions:()Ljava/util/List;
        //   635: astore_2       
        //   636: aload_2        
        //   637: invokeinterface java/util/List.size:()I
        //   642: iconst_1       
        //   643: if_icmpne       708
        //   646: aload_3        
        //   647: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   652: aload_2        
        //   653: iconst_0       
        //   654: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   659: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   662: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   667: aconst_null    
        //   668: aload_1        
        //   669: iconst_1       
        //   670: aload_1        
        //   671: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   674: iconst_1       
        //   675: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   678: dup            
        //   679: aload_1        
        //   680: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   683: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkConvertible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;ZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   686: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   689: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   692: if_acmpne       708
        //   695: goto            702
        //   698: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   701: athrow         
        //   702: iconst_1       
        //   703: ireturn        
        //   704: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   707: athrow         
        //   708: aload_2        
        //   709: invokeinterface java/util/List.isEmpty:()Z
        //   714: ifeq            740
        //   717: aload_3        
        //   718: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   721: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.hasDefaultConstructor:()Z
        //   724: ifeq            740
        //   727: goto            734
        //   730: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   733: athrow         
        //   734: iconst_1       
        //   735: ireturn        
        //   736: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   739: athrow         
        //   740: aload_2        
        //   741: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   746: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   749: astore          5
        //   751: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   754: dup            
        //   755: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   758: aload           5
        //   760: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   763: astore          6
        //   765: aload_0        
        //   766: aload_1        
        //   767: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$CannotResolve;.class
        //   769: ldc             "CIDR"
        //   771: aload_3        
        //   772: aload           6
        //   774: aload_1        
        //   775: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.getCantResolveCtorMessage:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   778: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   781: astore          7
        //   783: aload_0        
        //   784: aload           7
        //   786: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   789: dup            
        //   790: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   793: aload_1        
        //   794: aload_3        
        //   795: aload_3        
        //   796: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   801: aload           6
        //   803: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   806: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   809: iconst_0       
        //   810: ireturn        
        //   811: iload           4
        //   813: aload_0        
        //   814: aload_3        
        //   815: aload_1        
        //   816: aconst_null    
        //   817: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFieldVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   820: iand           
        //   821: istore          4
        //   823: iload           4
        //   825: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  104    123    126    130    Ljava/lang/IllegalArgumentException;
        //  111    141    144    148    Ljava/lang/IllegalArgumentException;
        //  130    158    158    162    Ljava/lang/IllegalArgumentException;
        //  175    197    200    204    Ljava/lang/IllegalArgumentException;
        //  182    208    208    212    Ljava/lang/IllegalArgumentException;
        //  215    230    233    237    Ljava/lang/IllegalArgumentException;
        //  220    247    250    254    Ljava/lang/IllegalArgumentException;
        //  284    300    303    307    Ljava/lang/IllegalArgumentException;
        //  292    322    325    329    Ljava/lang/IllegalArgumentException;
        //  341    376    379    383    Ljava/lang/IllegalArgumentException;
        //  352    385    385    389    Ljava/lang/IllegalArgumentException;
        //  402    413    413    417    Ljava/lang/IllegalArgumentException;
        //  417    425    425    429    Ljava/lang/IllegalArgumentException;
        //  463    482    485    489    Ljava/lang/IllegalArgumentException;
        //  470    499    502    506    Ljava/lang/IllegalArgumentException;
        //  489    519    522    526    Ljava/lang/IllegalArgumentException;
        //  506    544    547    551    Ljava/lang/IllegalArgumentException;
        //  559    572    575    579    Ljava/lang/IllegalArgumentException;
        //  564    583    583    587    Ljava/lang/IllegalArgumentException;
        //  589    607    610    614    Ljava/lang/IllegalArgumentException;
        //  596    616    616    620    Ljava/lang/IllegalArgumentException;
        //  636    695    698    702    Ljava/lang/IllegalArgumentException;
        //  646    704    704    708    Ljava/lang/IllegalArgumentException;
        //  708    727    730    734    Ljava/lang/IllegalArgumentException;
        //  717    736    736    740    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0130:
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
    private Boolean a(final OCElement p0, final List<OCExpression> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     4: ifeq            276
        //     7: aload_1        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    16: astore_3       
        //    17: aload_3        
        //    18: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    21: ifeq            276
        //    24: aload_3        
        //    25: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    28: astore          4
        //    30: aload           4
        //    32: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //    35: ifne            276
        //    38: aload           4
        //    40: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //    43: ifne            276
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_2        
        //    54: invokeinterface java/util/List.size:()I
        //    59: iconst_1       
        //    60: if_icmpne       231
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_2        
        //    71: iconst_0       
        //    72: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    77: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    80: astore          5
        //    82: aload           5
        //    84: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //    87: ifeq            116
        //    90: aload           5
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //    95: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializers:()Ljava/util/List;
        //   100: invokeinterface java/util/List.size:()I
        //   105: iconst_1       
        //   106: if_icmpne       231
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload           5
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   121: ifeq            152
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload           5
        //   133: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializerExpressions:()Ljava/util/List;
        //   141: iconst_0       
        //   142: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   147: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   150: astore          5
        //   152: aload           5
        //   154: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   159: astore          6
        //   161: aload           6
        //   163: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   166: ifeq            211
        //   169: aload           6
        //   171: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   174: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //   177: ifne            211
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: aload           6
        //   189: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   192: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   195: ifne            211
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aconst_null    
        //   206: areturn        
        //   207: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload           4
        //   213: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   216: aload           6
        //   218: aload_1        
        //   219: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   222: ifeq            231
        //   225: aconst_null    
        //   226: areturn        
        //   227: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload_0        
        //   232: aload_1        
        //   233: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //   235: ldc             "err_lvalue_reference_bind_to_unrelated"
        //   237: ldc             "Can't use constructors for initialization of references"
        //   239: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   242: astore          5
        //   244: aload_0        
        //   245: aload           5
        //   247: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   250: dup            
        //   251: aload_1        
        //   252: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   255: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   260: aload           4
        //   262: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   265: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   268: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   271: iconst_0       
        //   272: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   275: areturn        
        //   276: aconst_null    
        //   277: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;)Ljava/lang/Boolean;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  30     46     49     53     Ljava/lang/IllegalArgumentException;
        //  38     63     66     70     Ljava/lang/IllegalArgumentException;
        //  82     109    112    116    Ljava/lang/IllegalArgumentException;
        //  90     124    127    131    Ljava/lang/IllegalArgumentException;
        //  161    180    183    187    Ljava/lang/IllegalArgumentException;
        //  169    198    201    205    Ljava/lang/IllegalArgumentException;
        //  187    207    207    211    Ljava/lang/IllegalArgumentException;
        //  211    227    227    231    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    
    boolean checkFunctionArguments(final OCElement ocElement, final OCFunctionType ocFunctionType, final List<OCExpression> list, final OCSymbol ocSymbol) {
        final List<Annotation> checkFunctionArguments = new OCAnnotatingArgumentsChecker(this).checkFunctionArguments(ocElement, ocFunctionType, list, ocSymbol);
        final List<OCType> types = OCArgumentsList.getArgumentList(list).getTypes();
        if (ocSymbol != null) {
            this.checkFormatSpecifiers(ocSymbol, list, types);
            final OCFunctionType ocFunctionType2 = new OCFunctionType(ocFunctionType.getReturnType(), ContainerUtil.map((Collection)list, ocExpression -> OCExpectedTypeUtil.getExpressionType(ocExpression, true)));
            try {
                this.registerQuickFixes(checkFunctionArguments, (IntentionAction)new OCChangeFunctionSignatureIntentionAction(ocSymbol, ocFunctionType2));
                if (!ocElement.getContainingOCFile().isCpp() || !(ocSymbol instanceof OCFunctionSymbol)) {
                    return checkFunctionArguments.isEmpty();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ((OCFunctionSymbol)ocSymbol).getResolvedOwner();
            final boolean resolveIsStatic = ((OCFunctionSymbol)ocSymbol).resolveIsStatic();
            OCElement functionReferenceExpression = null;
            Label_0157: {
                try {
                    if (ocElement instanceof OCCallExpression) {
                        functionReferenceExpression = ((OCCallExpression)ocElement).getFunctionReferenceExpression();
                        break Label_0157;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                functionReferenceExpression = ocElement;
            }
            this.registerQuickFixes(checkFunctionArguments, (IntentionAction)new OCCreateNewDefinitionIntentionAction(ocSymbol.getKind().toDeclarationKind(), (PsiElement)functionReferenceExpression, null, resolvedOwner, ocSymbol.getName(), ocFunctionType2, resolveIsStatic));
        }
        return checkFunctionArguments.isEmpty();
    }
    
    public void checkFormatSpecifiers(final OCSymbol p0, final List<OCExpression> p1, final List<OCType> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_2        
        //     2: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.getFormatSpecifiersInfo:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/List;)Lcom/intellij/openapi/util/Pair;
        //     5: astore          4
        //     7: aload           4
        //     9: ifnonnull       17
        //    12: return         
        //    13: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload           4
        //    19: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    22: checkcast       Ljava/util/List;
        //    25: astore          5
        //    27: aload           5
        //    29: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    34: astore          6
        //    36: aload           6
        //    38: invokeinterface java/util/Iterator.hasNext:()Z
        //    43: ifeq            91
        //    46: aload           6
        //    48: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    53: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;
        //    56: astore          7
        //    58: aload           7
        //    60: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getRange:()Lcom/intellij/openapi/util/TextRange;
        //    63: astore          8
        //    65: aload           8
        //    67: ifnull          88
        //    70: aload_0        
        //    71: aload           8
        //    73: aload           7
        //    75: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getType:()Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //    78: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.highlight:(Lcom/intellij/openapi/util/TextRange;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: goto            36
        //    91: aload           4
        //    93: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    96: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //    99: astore          6
        //   101: aload           6
        //   103: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   106: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.needArgumentsCheck:()Z
        //   109: ifeq            142
        //   112: aload           6
        //   114: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   117: iflt            142
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: aload           6
        //   129: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatStringIndex:I
        //   132: ifge            147
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: return         
        //   143: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_2        
        //   148: aload           6
        //   150: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatStringIndex:I
        //   153: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   158: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   161: astore          7
        //   163: aload           6
        //   165: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   168: istore          8
        //   170: iconst_0       
        //   171: istore          9
        //   173: aload           5
        //   175: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   180: astore          10
        //   182: aload           10
        //   184: invokeinterface java/util/Iterator.hasNext:()Z
        //   189: ifeq            903
        //   192: aload           10
        //   194: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   199: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;
        //   202: astore          11
        //   204: aload           11
        //   206: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getRange:()Lcom/intellij/openapi/util/TextRange;
        //   209: astore          12
        //   211: aload           11
        //   213: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getType:()Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   216: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_VALID_STRING_ESCAPE:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   219: if_acmpne       229
        //   222: goto            182
        //   225: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload           11
        //   231: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getType:()Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   234: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_INVALID_STRING_ESCAPE:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   237: if_acmpne       266
        //   240: aload_0        
        //   241: aload           7
        //   243: aload           12
        //   245: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FormatSpecifiers;.class
        //   247: ldc             "warn_printf_incomplete_specifier"
        //   249: ldc             "Unknown, incomplete or optional format specifier"
        //   251: aconst_null    
        //   252: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   255: pop            
        //   256: iinc            8, 1
        //   259: goto            182
        //   262: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload           11
        //   268: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getName:()Ljava/lang/String;
        //   271: astore          13
        //   273: aload           6
        //   275: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   278: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.SCANF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   281: if_acmpne       307
        //   284: aload           13
        //   286: ldc             "*"
        //   288: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   291: ifeq            307
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: iconst_1       
        //   302: istore          9
        //   304: goto            182
        //   307: iload           9
        //   309: ifeq            318
        //   312: iconst_0       
        //   313: istore          9
        //   315: goto            182
        //   318: aload           6
        //   320: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   323: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.PRINTF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   326: if_acmpeq       347
        //   329: aload           6
        //   331: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   334: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.NSSTRING:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   337: if_acmpne       410
        //   340: goto            347
        //   343: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   346: athrow         
        //   347: aload           13
        //   349: ldc             "%m"
        //   351: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   354: ifeq            410
        //   357: goto            364
        //   360: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: aload_0        
        //   365: aload           7
        //   367: aload           12
        //   369: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FormatSpecifiers;.class
        //   371: ldc             "CIDR"
        //   373: new             Ljava/lang/StringBuilder;
        //   376: dup            
        //   377: invokespecial   java/lang/StringBuilder.<init>:()V
        //   380: ldc             "Format specifier '"
        //   382: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   385: aload           13
        //   387: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   390: ldc             "' is a Glibc extension"
        //   392: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   395: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   398: aconst_null    
        //   399: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   402: pop            
        //   403: goto            182
        //   406: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   409: athrow         
        //   410: iload           8
        //   412: aload_2        
        //   413: invokeinterface java/util/List.size:()I
        //   418: if_icmplt       518
        //   421: aload_1        
        //   422: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingPsiFile:()Lcom/intellij/psi/PsiFile;
        //   427: astore          14
        //   429: aload           6
        //   431: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   434: aload           13
        //   436: aload           14
        //   438: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.resolveType:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   441: astore          15
        //   443: aload           15
        //   445: ifnonnull       457
        //   448: ldc             ""
        //   450: goto            464
        //   453: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: aload           15
        //   459: aload           14
        //   461: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   464: astore          16
        //   466: aload_0        
        //   467: aload           7
        //   469: aload           12
        //   471: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FormatSpecifiers;.class
        //   473: ldc             "warn_printf_data_arg_not_used"
        //   475: new             Ljava/lang/StringBuilder;
        //   478: dup            
        //   479: invokespecial   java/lang/StringBuilder.<init>:()V
        //   482: ldc             "Argument missing: format specifier '"
        //   484: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   487: aload           13
        //   489: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: ldc             "' requires '"
        //   494: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   497: aload           16
        //   499: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: ldc             "' argument"
        //   504: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   507: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   510: aconst_null    
        //   511: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   514: pop            
        //   515: goto            182
        //   518: aload_2        
        //   519: iload           8
        //   521: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   526: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   529: astore          14
        //   531: aload_3        
        //   532: iload           8
        //   534: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   539: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   542: astore          15
        //   544: aload           6
        //   546: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   549: astore          16
        //   551: aload           16
        //   553: aload           15
        //   555: aload           14
        //   557: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.getSpecifierForType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   560: astore          17
        //   562: aload           16
        //   564: aload           13
        //   566: aload           14
        //   568: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.resolveType:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   571: astore          18
        //   573: aload           17
        //   575: ifnull          897
        //   578: aload           18
        //   580: ifnull          897
        //   583: goto            590
        //   586: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   589: athrow         
        //   590: aload           16
        //   592: aload           18
        //   594: aload           15
        //   596: aload           13
        //   598: aload           17
        //   600: aload           14
        //   602: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Z
        //   605: ifne            897
        //   608: goto            615
        //   611: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   614: athrow         
        //   615: new             Ljava/lang/StringBuilder;
        //   618: dup            
        //   619: invokespecial   java/lang/StringBuilder.<init>:()V
        //   622: ldc             "Format specifier '"
        //   624: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   627: aload           13
        //   629: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   632: ldc             "' requires '"
        //   634: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   637: aload           18
        //   639: aload           14
        //   641: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   644: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   647: ldc             "' argument instead of '"
        //   649: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   652: aload           15
        //   654: aload           14
        //   656: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   659: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   662: ldc             "'"
        //   664: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   667: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   670: astore          19
        //   672: aload           17
        //   674: ldc             "<pointer type required>"
        //   676: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   679: ifne            770
        //   682: aload           12
        //   684: ifnull          770
        //   687: goto            694
        //   690: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   693: athrow         
        //   694: aload_0        
        //   695: aload           7
        //   697: aload           12
        //   699: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FormatSpecifiers;.class
        //   701: ldc             "warn_format_conversion_argument_type_mismatch"
        //   703: aload           19
        //   705: aconst_null    
        //   706: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   709: astore          20
        //   711: aload_0        
        //   712: aload           20
        //   714: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   717: dup            
        //   718: aload           14
        //   720: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   725: aload           12
        //   727: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   730: aload           12
        //   732: invokevirtual   com/intellij/openapi/util/TextRange.getLength:()I
        //   735: aload           17
        //   737: new             Ljava/lang/StringBuilder;
        //   740: dup            
        //   741: invokespecial   java/lang/StringBuilder.<init>:()V
        //   744: ldc             "Change format specifier to '"
        //   746: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   749: aload           17
        //   751: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   754: ldc             "'"
        //   756: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   759: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   762: ldc             "Change format specifier"
        //   764: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   767: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   770: aload_0        
        //   771: aload           14
        //   773: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FormatSpecifiers;.class
        //   775: ldc             "warn_format_conversion_argument_type_mismatch"
        //   777: aload           19
        //   779: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   782: astore          20
        //   784: aload           13
        //   786: ldc             "%@"
        //   788: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   791: ifne            861
        //   794: aload           16
        //   796: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.SCANF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   799: if_acmpeq       833
        //   802: goto            809
        //   805: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   808: athrow         
        //   809: aload_0        
        //   810: aload           20
        //   812: new             Lcom/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction;
        //   815: dup            
        //   816: aload           14
        //   818: aload           18
        //   820: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   823: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   826: goto            833
        //   829: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   832: athrow         
        //   833: aload           18
        //   835: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //   838: ifne            861
        //   841: aload_0        
        //   842: aload           20
        //   844: aload           14
        //   846: aload           18
        //   848: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   851: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   854: goto            861
        //   857: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   860: athrow         
        //   861: aload_0        
        //   862: aload           20
        //   864: new             Lcom/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction;
        //   867: dup            
        //   868: aload           14
        //   870: aload           18
        //   872: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   875: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   878: aload_0        
        //   879: aload           20
        //   881: new             Lcom/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction;
        //   884: dup            
        //   885: aload           14
        //   887: aload           18
        //   889: aload           15
        //   891: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   894: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   897: iinc            8, 1
        //   900: goto            182
        //   903: iload           8
        //   905: aload_2        
        //   906: invokeinterface java/util/List.size:()I
        //   911: if_icmpge       1103
        //   914: aload_2        
        //   915: iload           8
        //   917: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   922: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   925: astore          10
        //   927: aload_2        
        //   928: aload_2        
        //   929: invokeinterface java/util/List.size:()I
        //   934: iconst_1       
        //   935: isub           
        //   936: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   941: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   944: astore          11
        //   946: iload           8
        //   948: aload           6
        //   950: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   953: isub           
        //   954: istore          12
        //   956: aload_2        
        //   957: invokeinterface java/util/List.size:()I
        //   962: aload           6
        //   964: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   967: isub           
        //   968: istore          13
        //   970: aload_0        
        //   971: aload           10
        //   973: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   978: new             Lcom/jetbrains/cidr/lang/util/OCElementsRange;
        //   981: dup            
        //   982: aload           10
        //   984: aload           11
        //   986: invokespecial   com/jetbrains/cidr/lang/util/OCElementsRange.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   989: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   992: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FormatSpecifiers;.class
        //   994: ldc             "warn_printf_insufficient_data_args"
        //   996: new             Ljava/lang/StringBuilder;
        //   999: dup            
        //  1000: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1003: ldc             "Too many arguments: format string requires "
        //  1005: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1008: iload           12
        //  1010: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1013: ldc             ", but has "
        //  1015: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1018: iload           13
        //  1020: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1023: ldc             " argument"
        //  1025: iload           13
        //  1027: invokestatic    com/intellij/openapi/util/text/StringUtil.pluralize:(Ljava/lang/String;I)Ljava/lang/String;
        //  1030: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1033: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1036: aconst_null    
        //  1037: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //  1040: astore          14
        //  1042: new             Ljava/lang/StringBuilder;
        //  1045: dup            
        //  1046: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1049: ldc             "Remove"
        //  1051: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1054: ldc             " argument"
        //  1056: iload           13
        //  1058: iload           12
        //  1060: isub           
        //  1061: invokestatic    com/intellij/openapi/util/text/StringUtil.pluralize:(Ljava/lang/String;I)Ljava/lang/String;
        //  1064: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1067: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1070: astore          15
        //  1072: aload_0        
        //  1073: aload           14
        //  1075: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //  1078: dup            
        //  1079: aload_2        
        //  1080: iload           8
        //  1082: aload_2        
        //  1083: invokeinterface java/util/List.size:()I
        //  1088: invokeinterface java/util/List.subList:(II)Ljava/util/List;
        //  1093: aload           15
        //  1095: aload           15
        //  1097: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Ljava/util/Collection;Ljava/lang/String;Ljava/lang/String;)V
        //  1100: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1103: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCType;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      13     13     17     Ljava/lang/IllegalArgumentException;
        //  65     81     84     88     Ljava/lang/IllegalArgumentException;
        //  101    120    123    127    Ljava/lang/IllegalArgumentException;
        //  112    135    138    142    Ljava/lang/IllegalArgumentException;
        //  127    143    143    147    Ljava/lang/IllegalArgumentException;
        //  211    225    225    229    Ljava/lang/IllegalArgumentException;
        //  229    262    262    266    Ljava/lang/IllegalArgumentException;
        //  273    294    297    301    Ljava/lang/IllegalArgumentException;
        //  318    340    343    347    Ljava/lang/IllegalArgumentException;
        //  329    357    360    364    Ljava/lang/IllegalArgumentException;
        //  347    406    406    410    Ljava/lang/IllegalArgumentException;
        //  443    453    453    457    Ljava/lang/IllegalArgumentException;
        //  573    583    586    590    Ljava/lang/IllegalArgumentException;
        //  578    608    611    615    Ljava/lang/IllegalArgumentException;
        //  672    687    690    694    Ljava/lang/IllegalArgumentException;
        //  784    802    805    809    Ljava/lang/IllegalArgumentException;
        //  794    826    829    833    Ljava/lang/IllegalArgumentException;
        //  833    854    857    861    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0127:
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
    
    public boolean checkFieldVisibility(final OCSymbol ocSymbol, final PsiElement psiElement, @Nullable final OCType ocType) {
        Label_0027: {
            try {
                if (ocSymbol instanceof OCSymbolWithQualifiedName) {
                    break Label_0027;
                }
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                final boolean b = ocFunctionSymbol instanceof OCInstanceVariableSymbol;
                if (!b) {
                    return true;
                }
                break Label_0027;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                final boolean b = ocFunctionSymbol instanceof OCInstanceVariableSymbol;
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)ocSymbol;
        final OCVisibility declaredVisibility = OCVisibility.getDeclaredVisibility(ocFunctionSymbol2);
        String s = null;
        Label_0072: {
            try {
                if (ocFunctionSymbol2 instanceof OCFunctionSymbol) {
                    s = ocFunctionSymbol2.getSignatureWithoutParamNames(true, false);
                    break Label_0072;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            s = ocFunctionSymbol2.getNameWithParent();
        }
        final String s2 = s;
        Label_0300: {
            try {
                if (!(ocSymbol instanceof OCFunctionSymbol) || !((OCFunctionSymbol)ocSymbol).isDelete()) {
                    break Label_0300;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            final Annotation addErrorAnnotation = this.addErrorAnnotation(psiElement, OCInspections.MemberVisibility.class, "err_ovl_deleted_member_call", "'" + s2 + "' is deleted");
            final PsiElement locateDefinition = ((OCSymbol<PsiElement>)ocFunctionSymbol2).locateDefinition();
            if (locateDefinition instanceof OCDeclarator) {
                final ASTNode astNode = (ASTNode)ContainerUtil.find((Object[])locateDefinition.getNode().getChildren((TokenSet)null), astNode -> {
                    try {
                        if (astNode.getElementType() == OCTokenTypes.DELETE_CPP_KEYWORD) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return false;
                });
                try {
                    if (astNode != null) {
                        this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCChangeTextIntentionAction(astNode.getPsi().getContainingFile(), astNode.getStartOffset(), astNode.getTextLength(), "default", "Make " + ocSymbol.getNameWithKindLowercase() + " default"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCSafeDeleteIntentionAction(locateDefinition, ocSymbol.getNameWithKindLowercase() + " declaration"));
            }
            return false;
            try {
                if (declaredVisibility == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        final OCVisibility visibility = OCVisibility.getVisibility(ocFunctionSymbol2, psiElement, ocType);
        try {
            if (visibility.ordinal() >= declaredVisibility.ordinal()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        final Annotation addErrorAnnotation2 = this.addErrorAnnotation(psiElement, OCInspections.MemberVisibility.class, "err_access", declaredVisibility + " '" + s2 + "' is inaccessible");
        try {
            if (visibility.ordinal() >= OCVisibility.PUBLIC.ordinal()) {
                this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCChangeVisibilityIntentionAction(ocFunctionSymbol2, OCVisibility.PUBLIC));
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        try {
            if (ocFunctionSymbol2 instanceof OCInstanceVariableSymbol) {
                this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCChangeVisibilityIntentionAction(ocFunctionSymbol2, OCVisibility.PACKAGE));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        try {
            if (visibility.ordinal() < OCVisibility.PROTECTED.ordinal()) {
                return false;
            }
            this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCChangeVisibilityIntentionAction(ocFunctionSymbol2, OCVisibility.PROTECTED));
            if (!(ocFunctionSymbol2 instanceof OCInstanceVariableSymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw b(ex10);
        }
        final OCImplementationSymbol symbol = ((OCImplementation)PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCImplementation.class })).getSymbol();
        OCInterfaceSymbol interface1 = null;
        Label_0544: {
            try {
                if (symbol != null) {
                    interface1 = symbol.getInterface();
                    break Label_0544;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw b(ex11);
            }
            interface1 = null;
        }
        final OCInterfaceSymbol ocInterfaceSymbol = interface1;
        try {
            if (symbol != null) {
                this.registerQuickFix(addErrorAnnotation2, (IntentionAction)new OCMoveDefinitionIntentionAction(ocFunctionSymbol2.getKind(), psiElement, ocInterfaceSymbol, ocFunctionSymbol2));
            }
        }
        catch (IllegalArgumentException ex12) {
            throw b(ex12);
        }
        return false;
    }
    
    public void checkTypeCast(final OCType p0, final OCType p1, final PsiElement p2, final OCExpression p3, final PsiElement p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: aload           5
        //     3: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //     6: astore          6
        //     8: aload_1        
        //     9: aload           5
        //    11: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    14: astore          7
        //    16: aload_0        
        //    17: aload_1        
        //    18: aload_3        
        //    19: aconst_null    
        //    20: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkARCPointerTypes:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    23: ifne            31
        //    26: return         
        //    27: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           4
        //    35: aload           5
        //    37: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    40: astore          8
        //    42: aload           8
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload           5
        //    48: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.canBeCasted:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    51: ifeq            71
        //    54: aload           8
        //    56: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getInspectionClass:()Ljava/lang/Class;
        //    59: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //    61: if_acmpne       197
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: new             Ljava/lang/StringBuilder;
        //    74: dup            
        //    75: invokespecial   java/lang/StringBuilder.<init>:()V
        //    78: ldc             "Expression of type '"
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload           6
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: ldc             "' can't be cast to type '"
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    93: aload           7
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: ldc             "': "
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload           8
        //   105: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getMessage:()Ljava/lang/String;
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   111: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   114: astore          9
        //   116: aload_0        
        //   117: aload_3        
        //   118: aload           8
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getInspectionClass:()Ljava/lang/Class;
        //   123: ldc             "err_typecheck_expect_scalar_operand"
        //   125: aload           9
        //   127: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   130: astore          10
        //   132: aload_0        
        //   133: aload           10
        //   135: aload           4
        //   137: aload_1        
        //   138: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   141: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   144: aload           8
        //   146: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getQuickFixes:()[Lcom/intellij/codeInsight/intention/IntentionAction;
        //   149: astore          11
        //   151: aload           11
        //   153: ifnull          196
        //   156: aload           11
        //   158: astore          12
        //   160: aload           12
        //   162: arraylength    
        //   163: istore          13
        //   165: iconst_0       
        //   166: istore          14
        //   168: iload           14
        //   170: iload           13
        //   172: if_icmpge       196
        //   175: aload           12
        //   177: iload           14
        //   179: aaload         
        //   180: astore          15
        //   182: aload_0        
        //   183: aload           10
        //   185: aload           15
        //   187: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   190: iinc            14, 1
        //   193: goto            168
        //   196: return         
        //   197: aload_1        
        //   198: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   201: ifeq            313
        //   204: aload_2        
        //   205: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   208: ifeq            313
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: aload_1        
        //   219: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   222: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   225: astore          9
        //   227: aload_2        
        //   228: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   231: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   234: astore          10
        //   236: aload           9
        //   238: aload           10
        //   240: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isAncestorOf:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Z
        //   243: ifne            313
        //   246: aload           10
        //   248: aload           9
        //   250: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isAncestorOf:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Z
        //   253: ifne            313
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: new             Ljava/lang/StringBuilder;
        //   266: dup            
        //   267: invokespecial   java/lang/StringBuilder.<init>:()V
        //   270: ldc             "Casting expression of type '"
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: aload           6
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: ldc             "' to incompatible type '"
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload           7
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   290: ldc             "'"
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   298: astore          11
        //   300: aload_0        
        //   301: aload_3        
        //   302: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //   304: ldc             "CIDRobjc_incompatible_pointers"
        //   306: aload           11
        //   308: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   311: pop            
        //   312: return         
        //   313: aload_1        
        //   314: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
        //   317: ifnonnull       642
        //   320: aload_2        
        //   321: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
        //   324: ifnonnull       642
        //   327: goto            334
        //   330: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload           4
        //   336: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   339: ifne            642
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: aload           4
        //   351: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   356: astore_2       
        //   357: aload_2        
        //   358: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   361: ifeq            408
        //   364: aload_1        
        //   365: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   368: ifeq            403
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload_1        
        //   379: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   382: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   385: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //   388: invokeinterface java/util/List.isEmpty:()Z
        //   393: ifne            408
        //   396: goto            403
        //   399: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   402: athrow         
        //   403: return         
        //   404: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aconst_null    
        //   409: astore          9
        //   411: aload_1        
        //   412: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   415: ifeq            540
        //   418: aload_1        
        //   419: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   422: ifne            540
        //   425: goto            432
        //   428: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   431: athrow         
        //   432: aload           8
        //   434: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   437: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   440: if_acmpne       540
        //   443: goto            450
        //   446: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   449: athrow         
        //   450: aload_2        
        //   451: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   454: ifeq            484
        //   457: goto            464
        //   460: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: aload_2        
        //   465: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   468: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   471: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   474: ifne            540
        //   477: goto            484
        //   480: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   483: athrow         
        //   484: new             Ljava/lang/StringBuilder;
        //   487: dup            
        //   488: invokespecial   java/lang/StringBuilder.<init>:()V
        //   491: ldc             "Casting expression of type '"
        //   493: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   496: aload           6
        //   498: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   501: ldc             "' to '"
        //   503: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   506: aload           7
        //   508: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   511: ldc             "' is redundant"
        //   513: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   516: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   519: astore          10
        //   521: aload_0        
        //   522: aload_3        
        //   523: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$RedundantCast;.class
        //   525: ldc             "CIDR"
        //   527: aload           10
        //   529: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   532: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   535: astore          9
        //   537: goto            601
        //   540: aload_1        
        //   541: aload_2        
        //   542: iconst_0       
        //   543: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   546: dup            
        //   547: aload           4
        //   549: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   552: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   555: ifeq            601
        //   558: new             Ljava/lang/StringBuilder;
        //   561: dup            
        //   562: invokespecial   java/lang/StringBuilder.<init>:()V
        //   565: ldc             "Casting expression to '"
        //   567: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   570: aload           7
        //   572: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   575: ldc             "' is redundant"
        //   577: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   580: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   583: astore          10
        //   585: aload_0        
        //   586: aload_3        
        //   587: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$RedundantCast;.class
        //   589: ldc             "CIDR"
        //   591: aload           10
        //   593: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   596: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   599: astore          9
        //   601: aload           9
        //   603: ifnull          642
        //   606: aload           5
        //   608: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   611: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   614: astore          10
        //   616: aload           4
        //   618: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   621: astore          11
        //   623: aload_0        
        //   624: aload           9
        //   626: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeElementIntentionAction;
        //   629: dup            
        //   630: aload           10
        //   632: aload           11
        //   634: ldc             "Remove redundant cast"
        //   636: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeElementIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   639: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   642: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  16     27     27     31     Ljava/lang/IllegalArgumentException;
        //  42     64     67     71     Ljava/lang/IllegalArgumentException;
        //  197    211    214    218    Ljava/lang/IllegalArgumentException;
        //  236    256    259    263    Ljava/lang/IllegalArgumentException;
        //  313    327    330    334    Ljava/lang/IllegalArgumentException;
        //  320    342    345    349    Ljava/lang/IllegalArgumentException;
        //  357    371    374    378    Ljava/lang/IllegalArgumentException;
        //  364    396    399    403    Ljava/lang/IllegalArgumentException;
        //  378    404    404    408    Ljava/lang/IllegalArgumentException;
        //  411    425    428    432    Ljava/lang/IllegalArgumentException;
        //  418    443    446    450    Ljava/lang/IllegalArgumentException;
        //  432    457    460    464    Ljava/lang/IllegalArgumentException;
        //  450    477    480    484    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0378:
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
    
    public boolean checkARCPointerTypes(@Nullable final OCType p0, @Nullable final PsiElement p1, @Nullable final OCSymbol p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          23
        //     4: aload_2        
        //     5: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    10: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    13: ifne            29
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    22: athrow         
        //    23: iconst_1       
        //    24: ireturn        
        //    25: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_1        
        //    30: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    33: ifeq            369
        //    36: aload_1        
        //    37: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    40: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    43: ifeq            369
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_1        
        //    54: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    57: astore          4
        //    59: iconst_0       
        //    60: istore          5
        //    62: iconst_0       
        //    63: istore          6
        //    65: aload           4
        //    67: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    70: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    73: ifeq            134
        //    76: aload           4
        //    78: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    81: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    87: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    90: ifeq            134
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: aload           4
        //   102: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   105: ifne            118
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: iconst_1       
        //   116: istore          6
        //   118: aload           4
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   126: astore          4
        //   128: iinc            5, 1
        //   131: goto            65
        //   134: aload           4
        //   136: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   139: ifeq            154
        //   142: iload           6
        //   144: ifeq            369
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           4
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   159: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   162: ifeq            369
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload           4
        //   174: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //   177: ifne            369
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: aload           4
        //   189: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   192: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   195: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   198: ifnonnull       369
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_3        
        //   209: ifnull          238
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload_3        
        //   220: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   225: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   228: if_acmpeq       369
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/daemon/OCCppChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: aload_0        
        //   239: aload_2        
        //   240: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //   242: ldc             "err_arc_indirect_no_ownership"
        //   244: new             Ljava/lang/StringBuilder;
        //   247: dup            
        //   248: invokespecial   java/lang/StringBuilder.<init>:()V
        //   251: ldc             "Pointer to non-const type '"
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: aload_1        
        //   257: aload_2        
        //   258: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: ldc             "' with no explicit lifetime"
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   272: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   275: astore          7
        //   277: aload_3        
        //   278: ifnull          367
        //   281: aload_0        
        //   282: aload           7
        //   284: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   287: dup            
        //   288: aload_3        
        //   289: aload           4
        //   291: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   294: aload_2        
        //   295: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   300: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   303: aconst_null    
        //   304: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/ARCAttribute;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   307: iload           5
        //   309: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   312: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   315: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   318: invokestatic    com/jetbrains/cidr/lang/types/ARCAttribute.values:()[Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   321: astore          8
        //   323: aload           8
        //   325: arraylength    
        //   326: istore          9
        //   328: iconst_0       
        //   329: istore          10
        //   331: iload           10
        //   333: iload           9
        //   335: if_icmpge       367
        //   338: aload           8
        //   340: iload           10
        //   342: aaload         
        //   343: astore          11
        //   345: aload_0        
        //   346: aload           7
        //   348: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction;
        //   351: dup            
        //   352: aload_3        
        //   353: aload           11
        //   355: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/ARCAttribute;)V
        //   358: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   361: iinc            10, 1
        //   364: goto            331
        //   367: iconst_0       
        //   368: ireturn        
        //   369: iconst_1       
        //   370: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     19     23     Ljava/lang/IllegalArgumentException;
        //  4      25     25     29     Ljava/lang/IllegalArgumentException;
        //  29     46     49     53     Ljava/lang/IllegalArgumentException;
        //  65     93     96     100    Ljava/lang/IllegalArgumentException;
        //  76     108    111    115    Ljava/lang/IllegalArgumentException;
        //  134    147    150    154    Ljava/lang/IllegalArgumentException;
        //  142    165    168    172    Ljava/lang/IllegalArgumentException;
        //  154    180    183    187    Ljava/lang/IllegalArgumentException;
        //  172    201    204    208    Ljava/lang/IllegalArgumentException;
        //  187    212    215    219    Ljava/lang/IllegalArgumentException;
        //  208    231    234    238    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0154:
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
    
    public static String getCantResolveCtorMessage(final OCSymbol ocSymbol, final OCFunctionType ocFunctionType, final PsiElement psiElement) {
        return ocSymbol.getKindUppercase() + " '" + ocSymbol.getType().getBestNameInContext(psiElement) + "' doesn't have a constructor '" + OCTypeNameVisitor.getFunctionSignature(psiElement, ocFunctionType, ocSymbol.getName()) + "'";
    }
    
    @NotNull
    private static String a(final String s, final OCType.TypeCheckResult typeCheckResult) {
        String string = null;
        Label_0047: {
            StringBuilder append = null;
            Label_0034: {
                try {
                    append = new StringBuilder().append(s);
                    if (s.endsWith(": ")) {
                        break Label_0034;
                    }
                    final String s2 = s;
                    final boolean b = s2.isEmpty();
                    if (b) {
                        break Label_0034;
                    }
                    break Label_0034;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final String s2 = s;
                    final boolean b = s2.isEmpty();
                    if (b) {
                        final String message = typeCheckResult.getMessage();
                        break Label_0047;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            final String message = "";
            try {
                string = append.append(message).toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCCppChecker", "getMessage"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return string;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
