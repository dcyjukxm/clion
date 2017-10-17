// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveTypeModifierIntentionAction;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCChangePropertyAttributeIntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;

public class OCOperatorsChecker extends OCAnnotatorSinkWrapper
{
    @NotNull
    private final OCCppChecker myCppChecker;
    
    public OCOperatorsChecker(@NotNull final OCAnnotatorSink ocAnnotatorSink, @NotNull final OCCppChecker myCppChecker) {
        if (ocAnnotatorSink == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "impl", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "<init>"));
        }
        if (myCppChecker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checker", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "<init>"));
        }
        super(ocAnnotatorSink);
        this.myCppChecker = myCppChecker;
    }
    
    public void checkBinaryOperatorApplicable(final OCExpression p0, final OCExpression p1, final OCElementType p2, final OCExpression p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          38
        //     4: aload_2        
        //     5: ifnull          38
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: aload_3        
        //    16: ifnull          38
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload           4
        //    28: ifnonnull       43
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: return         
        //    39: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ASSIGNMENT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //    46: aload_3        
        //    47: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    50: ifeq            73
        //    53: aload_0        
        //    54: aload_1        
        //    55: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    58: ifne            73
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: return         
        //    69: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_1        
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    79: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    82: astore          5
        //    84: aload           5
        //    86: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    89: ifeq            104
        //    92: aload           5
        //    94: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    97: aload           4
        //    99: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   102: astore          5
        //   104: aload_2        
        //   105: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   110: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   113: astore          6
        //   115: aload           6
        //   117: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   120: ifeq            135
        //   123: aload           6
        //   125: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   128: aload           4
        //   130: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   133: astore          6
        //   135: aload           6
        //   137: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isUnresolvedLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   140: ifeq            161
        //   143: aload           5
        //   145: aload           6
        //   147: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   150: dup            
        //   151: aload           4
        //   153: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   156: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.resolveLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   159: astore          6
        //   161: aload           4
        //   163: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   168: astore          7
        //   170: aload           5
        //   172: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   175: ifne            227
        //   178: aload           6
        //   180: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   183: ifne            227
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: aload           5
        //   195: aload           4
        //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/intellij/psi/PsiElement;)Z
        //   200: ifne            227
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aload           6
        //   212: aload           4
        //   214: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/intellij/psi/PsiElement;)Z
        //   217: ifeq            232
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: return         
        //   228: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload_3        
        //   233: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   236: if_acmpne       836
        //   239: aload           4
        //   241: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   246: astore          8
        //   248: aload           4
        //   250: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   253: aload           4
        //   255: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   258: ifeq            447
        //   261: aload           8
        //   263: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   266: ifne            447
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: aload           8
        //   278: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   281: ifne            447
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload           8
        //   293: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   296: ifne            447
        //   299: goto            306
        //   302: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$EqualityInConditionalOperator;.class
        //   308: astore          9
        //   310: ldc             "Using '=' in conditional expression"
        //   312: astore          10
        //   314: ldc             "self"
        //   316: aload_1        
        //   317: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getText:()Ljava/lang/String;
        //   322: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   325: ifeq            354
        //   328: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$EqualityInConditionalOperatorWithSelf;.class
        //   330: astore          9
        //   332: new             Ljava/lang/StringBuilder;
        //   335: dup            
        //   336: invokespecial   java/lang/StringBuilder.<init>:()V
        //   339: aload           10
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: ldc             " with \"self\""
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   352: astore          10
        //   354: aload_0        
        //   355: aload           4
        //   357: aload           9
        //   359: ldc             "warn_condition_is_assignment"
        //   361: aload           10
        //   363: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   366: astore          11
        //   368: aload           4
        //   370: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   375: astore          12
        //   377: aload_0        
        //   378: aload           11
        //   380: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   383: dup            
        //   384: aload           7
        //   386: aload           4
        //   388: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextOffset:()I
        //   393: aload           12
        //   395: invokevirtual   java/lang/String.length:()I
        //   398: new             Ljava/lang/StringBuilder;
        //   401: dup            
        //   402: invokespecial   java/lang/StringBuilder.<init>:()V
        //   405: bipush          40
        //   407: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   410: aload           12
        //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   415: bipush          41
        //   417: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   423: ldc             "Place parentheses around"
        //   425: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;)V
        //   428: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   431: aload_0        
        //   432: aload           11
        //   434: new             Lcom/jetbrains/cidr/lang/quickfixes/OCExtractAssignmentIntentionAction;
        //   437: dup            
        //   438: aload           4
        //   440: aload_1        
        //   441: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCExtractAssignmentIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)V
        //   444: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   447: aload_1        
        //   448: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   451: ifeq            538
        //   454: aload_1        
        //   455: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   458: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   463: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SELF:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   466: if_acmpne       538
        //   469: goto            476
        //   472: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   475: athrow         
        //   476: aload           4
        //   478: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   480: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   483: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   486: astore          9
        //   488: aload           9
        //   490: ifnull          538
        //   493: aload           9
        //   495: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelector:()Ljava/lang/String;
        //   500: ldc             "init"
        //   502: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //   505: ifeq            538
        //   508: goto            515
        //   511: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   514: athrow         
        //   515: aload           6
        //   517: aload           5
        //   519: aload_1        
        //   520: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   523: ifeq            538
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: return         
        //   534: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   537: athrow         
        //   538: new             Lcom/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor;
        //   541: dup            
        //   542: invokespecial   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.<init>:()V
        //   545: astore          9
        //   547: aload_1        
        //   548: aload           9
        //   550: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   555: aload           6
        //   557: getstatic       com/jetbrains/cidr/lang/types/visitors/OCArrayToPointerChanger.INSTANCE:Lcom/jetbrains/cidr/lang/types/visitors/OCArrayToPointerChanger;
        //   560: invokevirtual   com/jetbrains/cidr/lang/types/OCType.transformType:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   563: aload           9
        //   565: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getNumOfDereferences:()I
        //   568: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   571: astore          10
        //   573: aload_0        
        //   574: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   577: aload_2        
        //   578: aload           4
        //   580: aload           5
        //   582: aload           6
        //   584: aload           9
        //   586: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   589: aload           10
        //   591: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/intellij/lang/annotation/Annotation;
        //   594: pop            
        //   595: aload           5
        //   597: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   600: ifeq            671
        //   603: aload           6
        //   605: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   608: ifeq            671
        //   611: goto            618
        //   614: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   617: athrow         
        //   618: aload           5
        //   620: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   623: ifeq            671
        //   626: goto            633
        //   629: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   632: athrow         
        //   633: aload           6
        //   635: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   638: ifne            671
        //   641: goto            648
        //   644: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   647: athrow         
        //   648: aload_0        
        //   649: aload           4
        //   651: ldc             "err_ovl_no_viable_oper"
        //   653: ldc             "inspections.typeChecks.volatileConflict"
        //   655: iconst_0       
        //   656: anewarray       Ljava/lang/Object;
        //   659: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   662: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   665: pop            
        //   666: return         
        //   667: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   670: athrow         
        //   671: aload_1        
        //   672: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   675: ifeq            835
        //   678: aload_2        
        //   679: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   682: ifeq            835
        //   685: goto            692
        //   688: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   691: athrow         
        //   692: aload_1        
        //   693: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   696: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   701: astore          11
        //   703: aload_2        
        //   704: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   707: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   712: astore          12
        //   714: aload           11
        //   716: ifnull          733
        //   719: aload           11
        //   721: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   726: goto            734
        //   729: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   732: athrow         
        //   733: aconst_null    
        //   734: astore          13
        //   736: aload           12
        //   738: ifnull          755
        //   741: aload           12
        //   743: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   748: goto            756
        //   751: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   754: athrow         
        //   755: aconst_null    
        //   756: astore          14
        //   758: aload           13
        //   760: ifnull          835
        //   763: aload           14
        //   765: ifnull          835
        //   768: goto            775
        //   771: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   774: athrow         
        //   775: aload           13
        //   777: aload           14
        //   779: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   782: ifeq            835
        //   785: goto            792
        //   788: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   791: athrow         
        //   792: new             Ljava/lang/StringBuilder;
        //   795: dup            
        //   796: invokespecial   java/lang/StringBuilder.<init>:()V
        //   799: aload           13
        //   801: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   806: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   809: ldc             " is assigned to itself"
        //   811: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   814: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   817: astore          15
        //   819: aload_0        
        //   820: aload           4
        //   822: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedValue;.class
        //   824: ldc             "warn_self_assignment"
        //   826: aload           15
        //   828: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   831: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   834: pop            
        //   835: return         
        //   836: aload           5
        //   838: aload           4
        //   840: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   843: istore          8
        //   845: aload           6
        //   847: aload           4
        //   849: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   852: istore          9
        //   854: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   857: dup            
        //   858: aload_1        
        //   859: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   862: astore          10
        //   864: aload           5
        //   866: aload           4
        //   868: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   871: ifne            890
        //   874: aload_1        
        //   875: aload           10
        //   877: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.getPointerType:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   880: ifnull          898
        //   883: goto            890
        //   886: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   889: athrow         
        //   890: iconst_1       
        //   891: goto            899
        //   894: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   897: athrow         
        //   898: iconst_0       
        //   899: istore          11
        //   901: aload           6
        //   903: aload           4
        //   905: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   908: ifne            927
        //   911: aload_2        
        //   912: aload           10
        //   914: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.getPointerType:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   917: ifnull          935
        //   920: goto            927
        //   923: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   926: athrow         
        //   927: iconst_1       
        //   928: goto            936
        //   931: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   934: athrow         
        //   935: iconst_0       
        //   936: istore          12
        //   938: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ARITHMETIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   941: aload_3        
        //   942: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   945: ifne            965
        //   948: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   951: aload_3        
        //   952: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   955: ifeq            1021
        //   958: goto            965
        //   961: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   964: athrow         
        //   965: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ONLY_INTEGER_ARITHMETIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   968: aload_3        
        //   969: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   972: ifne            1021
        //   975: goto            982
        //   978: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   981: athrow         
        //   982: aload           5
        //   984: aload           4
        //   986: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   989: ifeq            1021
        //   992: goto            999
        //   995: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   998: athrow         
        //   999: aload           6
        //  1001: aload           4
        //  1003: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //  1006: ifeq            1021
        //  1009: goto            1016
        //  1012: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1015: athrow         
        //  1016: return         
        //  1017: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1020: athrow         
        //  1021: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1024: aload_3        
        //  1025: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1028: ifeq            1070
        //  1031: aload           5
        //  1033: aload           4
        //  1035: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalarOrConvertibleToScalar:(Lcom/intellij/psi/PsiElement;)Z
        //  1038: ifeq            1070
        //  1041: goto            1048
        //  1044: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1047: athrow         
        //  1048: aload           6
        //  1050: aload           4
        //  1052: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalarOrConvertibleToScalar:(Lcom/intellij/psi/PsiElement;)Z
        //  1055: ifeq            1070
        //  1058: goto            1065
        //  1061: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1064: athrow         
        //  1065: return         
        //  1066: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1069: athrow         
        //  1070: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BITLOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1073: aload_3        
        //  1074: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1077: ifne            1097
        //  1080: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ONLY_INTEGER_ARITHMETIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1083: aload_3        
        //  1084: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1087: ifeq            1126
        //  1090: goto            1097
        //  1093: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1096: athrow         
        //  1097: iload           8
        //  1099: ifeq            1126
        //  1102: goto            1109
        //  1105: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1108: athrow         
        //  1109: iload           9
        //  1111: ifeq            1126
        //  1114: goto            1121
        //  1117: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1120: athrow         
        //  1121: return         
        //  1122: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1125: athrow         
        //  1126: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMPARISON_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1129: aload_3        
        //  1130: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1133: ifeq            1194
        //  1136: aload           5
        //  1138: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1141: ifeq            1194
        //  1144: goto            1151
        //  1147: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1150: athrow         
        //  1151: aload           5
        //  1153: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1156: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //  1159: ifeq            1194
        //  1162: goto            1169
        //  1165: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1168: athrow         
        //  1169: aload           6
        //  1171: aload           5
        //  1173: iconst_0       
        //  1174: aload           10
        //  1176: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1179: ifeq            1194
        //  1182: goto            1189
        //  1185: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1188: athrow         
        //  1189: return         
        //  1190: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1193: athrow         
        //  1194: aconst_null    
        //  1195: astore          13
        //  1197: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMPARISON_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1200: aload_3        
        //  1201: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1204: ifeq            1293
        //  1207: iload           11
        //  1209: ifeq            1293
        //  1212: goto            1219
        //  1215: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1218: athrow         
        //  1219: iload           12
        //  1221: ifeq            1293
        //  1224: goto            1231
        //  1227: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1230: athrow         
        //  1231: iload           8
        //  1233: ifne            1255
        //  1236: goto            1243
        //  1239: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1242: athrow         
        //  1243: iload           9
        //  1245: ifeq            1262
        //  1248: goto            1255
        //  1251: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1254: athrow         
        //  1255: ldc             "Comparison between pointer and integer"
        //  1257: astore          13
        //  1259: goto            1439
        //  1262: aload           5
        //  1264: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1267: ifeq            1292
        //  1270: aload           6
        //  1272: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1275: ifeq            1292
        //  1278: goto            1285
        //  1281: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1284: athrow         
        //  1285: ldc             "Comparison between distinct pointer types"
        //  1287: astore          13
        //  1289: goto            1439
        //  1292: return         
        //  1293: aload_3        
        //  1294: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1297: if_acmpeq       1314
        //  1300: aload_3        
        //  1301: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1304: if_acmpne       1439
        //  1307: goto            1314
        //  1310: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1313: athrow         
        //  1314: aload           5
        //  1316: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1319: ifeq            1346
        //  1322: goto            1329
        //  1325: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1328: athrow         
        //  1329: aload           5
        //  1331: aload           4
        //  1333: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalarOrConvertibleToScalar:(Lcom/intellij/psi/PsiElement;)Z
        //  1336: ifeq            1378
        //  1339: goto            1346
        //  1342: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1345: athrow         
        //  1346: aload           6
        //  1348: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1351: ifeq            1394
        //  1354: goto            1361
        //  1357: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1360: athrow         
        //  1361: aload           6
        //  1363: aload           4
        //  1365: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalarOrConvertibleToScalar:(Lcom/intellij/psi/PsiElement;)Z
        //  1368: ifne            1394
        //  1371: goto            1378
        //  1374: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1377: athrow         
        //  1378: aload_0        
        //  1379: aload           4
        //  1381: ldc             "err_typecheck_invalid_operands"
        //  1383: ldc             "Can't compare structures"
        //  1385: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1388: pop            
        //  1389: return         
        //  1390: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1393: athrow         
        //  1394: new             Ljava/lang/StringBuilder;
        //  1397: dup            
        //  1398: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1401: ldc             "Types '"
        //  1403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1406: aload           5
        //  1408: aload_1        
        //  1409: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1415: ldc             "' and '"
        //  1417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1420: aload           6
        //  1422: aload_2        
        //  1423: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1426: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1429: ldc             "' are not compatible"
        //  1431: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1434: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1437: astore          13
        //  1439: aload           13
        //  1441: ifnull          1463
        //  1444: aload_0        
        //  1445: aload           5
        //  1447: aload           6
        //  1449: aload_1        
        //  1450: aload_2        
        //  1451: aload           4
        //  1453: aload           13
        //  1455: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.checkTypesEquivalence:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Ljava/lang/String;)V
        //  1458: return         
        //  1459: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1462: athrow         
        //  1463: iload           11
        //  1465: ifeq            1488
        //  1468: iload           9
        //  1470: ifeq            1488
        //  1473: goto            1480
        //  1476: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1479: athrow         
        //  1480: iconst_1       
        //  1481: goto            1489
        //  1484: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1487: athrow         
        //  1488: iconst_0       
        //  1489: istore          14
        //  1491: iload           8
        //  1493: ifeq            1516
        //  1496: iload           12
        //  1498: ifeq            1516
        //  1501: goto            1508
        //  1504: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1507: athrow         
        //  1508: iconst_1       
        //  1509: goto            1517
        //  1512: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1515: athrow         
        //  1516: iconst_0       
        //  1517: istore          15
        //  1519: aload_3        
        //  1520: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1523: if_acmpne       1550
        //  1526: iload           14
        //  1528: ifne            1689
        //  1531: goto            1538
        //  1534: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1537: athrow         
        //  1538: iload           15
        //  1540: ifne            1689
        //  1543: goto            1550
        //  1546: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1549: athrow         
        //  1550: aload_3        
        //  1551: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1554: if_acmpne       1642
        //  1557: goto            1564
        //  1560: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1563: athrow         
        //  1564: aload           5
        //  1566: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1569: ifeq            1642
        //  1572: goto            1579
        //  1575: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1578: athrow         
        //  1579: aload           6
        //  1581: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1584: ifeq            1642
        //  1587: goto            1594
        //  1590: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1593: athrow         
        //  1594: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
        //  1597: dup            
        //  1598: aload           6
        //  1600: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1603: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1606: iconst_0       
        //  1607: iconst_0       
        //  1608: iconst_1       
        //  1609: iconst_1       
        //  1610: iconst_1       
        //  1611: aload           4
        //  1613: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //  1618: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZLcom/intellij/psi/PsiFile;)V
        //  1621: aload           5
        //  1623: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1626: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1629: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //  1632: ifne            1689
        //  1635: goto            1642
        //  1638: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1641: athrow         
        //  1642: aload_3        
        //  1643: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1646: if_acmpeq       1677
        //  1649: aload_3        
        //  1650: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1653: if_acmpeq       1677
        //  1656: goto            1663
        //  1659: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1662: athrow         
        //  1663: aload_3        
        //  1664: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1667: if_acmpne       1755
        //  1670: goto            1677
        //  1673: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1676: athrow         
        //  1677: iload           14
        //  1679: ifeq            1755
        //  1682: goto            1689
        //  1685: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1688: athrow         
        //  1689: aload           7
        //  1691: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //  1694: ifeq            1754
        //  1697: goto            1704
        //  1700: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1703: athrow         
        //  1704: aload           5
        //  1706: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //  1709: ifne            1734
        //  1712: goto            1719
        //  1715: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1718: athrow         
        //  1719: aload           6
        //  1721: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //  1724: ifeq            1754
        //  1727: goto            1734
        //  1730: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1733: athrow         
        //  1734: aload_0        
        //  1735: aload           4
        //  1737: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //  1739: ldc             "err_arithmetic_nonfragile_interface"
        //  1741: ldc             "Object pointer arithmetic is forbidden in ARC"
        //  1743: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1746: pop            
        //  1747: goto            1754
        //  1750: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1753: athrow         
        //  1754: return         
        //  1755: aload_3        
        //  1756: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1759: if_acmpne       1777
        //  1762: aload           5
        //  1764: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1767: ifne            1863
        //  1770: goto            1777
        //  1773: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1776: athrow         
        //  1777: aload_3        
        //  1778: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1781: if_acmpne       1868
        //  1784: goto            1791
        //  1787: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1790: athrow         
        //  1791: aload           5
        //  1793: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1796: ifeq            1868
        //  1799: goto            1806
        //  1802: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1805: athrow         
        //  1806: aload           6
        //  1808: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
        //  1811: ifeq            1868
        //  1814: goto            1821
        //  1817: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1820: athrow         
        //  1821: aload           5
        //  1823: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1826: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1829: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1832: ifne            1863
        //  1835: goto            1842
        //  1838: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1841: athrow         
        //  1842: aload           5
        //  1844: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1847: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1850: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //  1853: ifeq            1868
        //  1856: goto            1863
        //  1859: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1862: athrow         
        //  1863: return         
        //  1864: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1867: athrow         
        //  1868: aload_0        
        //  1869: aload           4
        //  1871: ldc             "err_typecheck_invalid_operands"
        //  1873: new             Ljava/lang/StringBuilder;
        //  1876: dup            
        //  1877: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1880: ldc             "Binary operator '"
        //  1882: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1885: aload_3        
        //  1886: invokevirtual   com/jetbrains/cidr/lang/parser/OCElementType.getName:()Ljava/lang/String;
        //  1889: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1892: ldc             "' can't be applied to the expressions of type '"
        //  1894: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1897: aload           5
        //  1899: aload_1        
        //  1900: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1903: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1906: ldc             "' and '"
        //  1908: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1911: aload           6
        //  1913: aload_2        
        //  1914: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1917: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1920: ldc             "'"
        //  1922: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1925: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1928: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1931: pop            
        //  1932: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      19     22     26     Ljava/lang/IllegalArgumentException;
        //  15     31     34     38     Ljava/lang/IllegalArgumentException;
        //  26     39     39     43     Ljava/lang/IllegalArgumentException;
        //  43     61     64     68     Ljava/lang/IllegalArgumentException;
        //  53     69     69     73     Ljava/lang/IllegalArgumentException;
        //  170    186    189    193    Ljava/lang/IllegalArgumentException;
        //  178    203    206    210    Ljava/lang/IllegalArgumentException;
        //  193    220    223    227    Ljava/lang/IllegalArgumentException;
        //  210    228    228    232    Ljava/lang/IllegalArgumentException;
        //  248    269    272    276    Ljava/lang/IllegalArgumentException;
        //  261    284    287    291    Ljava/lang/IllegalArgumentException;
        //  276    299    302    306    Ljava/lang/IllegalArgumentException;
        //  447    469    472    476    Ljava/lang/IllegalArgumentException;
        //  488    508    511    515    Ljava/lang/IllegalArgumentException;
        //  493    526    529    533    Ljava/lang/IllegalArgumentException;
        //  515    534    534    538    Ljava/lang/IllegalArgumentException;
        //  573    611    614    618    Ljava/lang/IllegalArgumentException;
        //  603    626    629    633    Ljava/lang/IllegalArgumentException;
        //  618    641    644    648    Ljava/lang/IllegalArgumentException;
        //  633    667    667    671    Ljava/lang/IllegalArgumentException;
        //  671    685    688    692    Ljava/lang/IllegalArgumentException;
        //  714    729    729    733    Ljava/lang/IllegalArgumentException;
        //  736    751    751    755    Ljava/lang/IllegalArgumentException;
        //  758    768    771    775    Ljava/lang/IllegalArgumentException;
        //  763    785    788    792    Ljava/lang/IllegalArgumentException;
        //  864    883    886    890    Ljava/lang/IllegalArgumentException;
        //  874    894    894    898    Ljava/lang/IllegalArgumentException;
        //  901    920    923    927    Ljava/lang/IllegalArgumentException;
        //  911    931    931    935    Ljava/lang/IllegalArgumentException;
        //  938    958    961    965    Ljava/lang/IllegalArgumentException;
        //  948    975    978    982    Ljava/lang/IllegalArgumentException;
        //  965    992    995    999    Ljava/lang/IllegalArgumentException;
        //  982    1009   1012   1016   Ljava/lang/IllegalArgumentException;
        //  999    1017   1017   1021   Ljava/lang/IllegalArgumentException;
        //  1021   1041   1044   1048   Ljava/lang/IllegalArgumentException;
        //  1031   1058   1061   1065   Ljava/lang/IllegalArgumentException;
        //  1048   1066   1066   1070   Ljava/lang/IllegalArgumentException;
        //  1070   1090   1093   1097   Ljava/lang/IllegalArgumentException;
        //  1080   1102   1105   1109   Ljava/lang/IllegalArgumentException;
        //  1097   1114   1117   1121   Ljava/lang/IllegalArgumentException;
        //  1109   1122   1122   1126   Ljava/lang/IllegalArgumentException;
        //  1126   1144   1147   1151   Ljava/lang/IllegalArgumentException;
        //  1136   1162   1165   1169   Ljava/lang/IllegalArgumentException;
        //  1151   1182   1185   1189   Ljava/lang/IllegalArgumentException;
        //  1169   1190   1190   1194   Ljava/lang/IllegalArgumentException;
        //  1197   1212   1215   1219   Ljava/lang/IllegalArgumentException;
        //  1207   1224   1227   1231   Ljava/lang/IllegalArgumentException;
        //  1219   1236   1239   1243   Ljava/lang/IllegalArgumentException;
        //  1231   1248   1251   1255   Ljava/lang/IllegalArgumentException;
        //  1262   1278   1281   1285   Ljava/lang/IllegalArgumentException;
        //  1293   1307   1310   1314   Ljava/lang/IllegalArgumentException;
        //  1300   1322   1325   1329   Ljava/lang/IllegalArgumentException;
        //  1314   1339   1342   1346   Ljava/lang/IllegalArgumentException;
        //  1329   1354   1357   1361   Ljava/lang/IllegalArgumentException;
        //  1346   1371   1374   1378   Ljava/lang/IllegalArgumentException;
        //  1361   1390   1390   1394   Ljava/lang/IllegalArgumentException;
        //  1439   1459   1459   1463   Ljava/lang/IllegalArgumentException;
        //  1463   1473   1476   1480   Ljava/lang/IllegalArgumentException;
        //  1468   1484   1484   1488   Ljava/lang/IllegalArgumentException;
        //  1491   1501   1504   1508   Ljava/lang/IllegalArgumentException;
        //  1496   1512   1512   1516   Ljava/lang/IllegalArgumentException;
        //  1519   1531   1534   1538   Ljava/lang/IllegalArgumentException;
        //  1526   1543   1546   1550   Ljava/lang/IllegalArgumentException;
        //  1538   1557   1560   1564   Ljava/lang/IllegalArgumentException;
        //  1550   1572   1575   1579   Ljava/lang/IllegalArgumentException;
        //  1564   1587   1590   1594   Ljava/lang/IllegalArgumentException;
        //  1579   1635   1638   1642   Ljava/lang/IllegalArgumentException;
        //  1642   1656   1659   1663   Ljava/lang/IllegalArgumentException;
        //  1649   1670   1673   1677   Ljava/lang/IllegalArgumentException;
        //  1663   1682   1685   1689   Ljava/lang/IllegalArgumentException;
        //  1677   1697   1700   1704   Ljava/lang/IllegalArgumentException;
        //  1689   1712   1715   1719   Ljava/lang/IllegalArgumentException;
        //  1704   1727   1730   1734   Ljava/lang/IllegalArgumentException;
        //  1719   1747   1750   1754   Ljava/lang/IllegalArgumentException;
        //  1755   1770   1773   1777   Ljava/lang/IllegalArgumentException;
        //  1762   1784   1787   1791   Ljava/lang/IllegalArgumentException;
        //  1777   1799   1802   1806   Ljava/lang/IllegalArgumentException;
        //  1791   1814   1817   1821   Ljava/lang/IllegalArgumentException;
        //  1806   1835   1838   1842   Ljava/lang/IllegalArgumentException;
        //  1821   1856   1859   1863   Ljava/lang/IllegalArgumentException;
        //  1842   1864   1864   1868   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    
    protected void checkTypesEquivalence(OCType to, OCType to2, final OCExpression ocExpression, final OCExpression ocExpression2, final OCExpression ocExpression3, final String s) {
        if (to instanceof OCArrayType) {
            to = OCPointerType.to(((OCArrayType)to).getRefType(), ((OCArrayType)to).getARCAttribute());
        }
        if (to2 instanceof OCArrayType) {
            to2 = OCPointerType.to(((OCArrayType)to2).getRefType(), ((OCArrayType)to2).getARCAttribute());
        }
        try {
            if (!to2.isCompatible(to, ocExpression, (PsiElement)ocExpression3)) {
                this.myCppChecker.checkAssignment(ocExpression2, (PsiElement)ocExpression3, to, to2, s);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    public void checkUnaryOperatorApplicable(final OCExpression p0, final OCElementType p1, final PsiElement p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          40
        //     4: aload_2        
        //     5: ifnull          40
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: aload_3        
        //    16: ifnull          40
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_2        
        //    27: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UDL_SUFFIX:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    30: if_acmpne       45
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: return         
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_1        
        //    46: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    51: astore          4
        //    53: aload_1        
        //    54: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    59: astore          5
        //    61: aload           5
        //    63: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    66: ifeq            79
        //    69: aload           5
        //    71: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    77: astore          5
        //    79: aload_2        
        //    80: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    83: if_acmpne       238
        //    86: aload           5
        //    88: aload_1        
        //    89: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/intellij/psi/PsiElement;)Z
        //    92: ifeq            107
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: return         
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload           5
        //   109: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   112: ifeq            210
        //   115: aload           5
        //   117: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: astore          6
        //   125: aload           6
        //   127: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   130: ifeq            182
        //   133: aload           6
        //   135: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   138: ifne            182
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload           6
        //   150: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   153: ifne            182
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: aload_1        
        //   165: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$CannotResolve;.class
        //   167: ldc             "err_incomplete_type"
        //   169: ldc             "Dereferencing pointer to unknown type"
        //   171: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   174: pop            
        //   175: goto            207
        //   178: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload           6
        //   184: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   187: ifeq            207
        //   190: aload_0        
        //   191: aload_3        
        //   192: ldc             "CIDR"
        //   194: ldc             "Dereferencing 'void *' pointer"
        //   196: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   199: pop            
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: goto            237
        //   210: aload           5
        //   212: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   215: ifne            237
        //   218: aload_0        
        //   219: aload_3        
        //   220: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PointerTypeRequired;.class
        //   222: ldc             "err_typecheck_indirection_requires_pointer"
        //   224: ldc             "Pointer type is required"
        //   226: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   229: pop            
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: return         
        //   238: aload           5
        //   240: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   243: ifeq            251
        //   246: return         
        //   247: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_2        
        //   252: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   255: if_acmpeq       272
        //   258: aload_2        
        //   259: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   262: if_acmpne       405
        //   265: goto            272
        //   268: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   271: athrow         
        //   272: aload_0        
        //   273: aload_1        
        //   274: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   277: ifne            292
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: return         
        //   288: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload           4
        //   294: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   297: ifeq            334
        //   300: aload           5
        //   302: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   305: ifeq            334
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: aload_0        
        //   316: aload_1        
        //   317: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //   319: ldc             "err_arithmetic_nonfragile_interface"
        //   321: ldc             "Object pointer arithmetic is forbidden in ARC"
        //   323: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   326: pop            
        //   327: goto            404
        //   330: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload           5
        //   336: aload_3        
        //   337: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   340: ifne            404
        //   343: aload           5
        //   345: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   348: ifne            404
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload_0        
        //   359: aload_1        
        //   360: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IntegerTypeRequired;.class
        //   362: ldc             "err_typecheck_illegal_increment_decrement"
        //   364: new             Ljava/lang/StringBuilder;
        //   367: dup            
        //   368: invokespecial   java/lang/StringBuilder.<init>:()V
        //   371: ldc             "Expression of type '"
        //   373: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: aload           5
        //   378: aload_1        
        //   379: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   382: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   385: ldc             "' is neither numeric nor a pointer"
        //   387: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   390: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   393: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   396: pop            
        //   397: goto            404
        //   400: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: return         
        //   405: aload_2        
        //   406: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   409: if_acmpeq       426
        //   412: aload_2        
        //   413: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   416: if_acmpne       489
        //   419: goto            426
        //   422: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: aload           5
        //   428: aload_3        
        //   429: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   432: ifne            488
        //   435: goto            442
        //   438: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   441: athrow         
        //   442: aload_0        
        //   443: aload_1        
        //   444: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IntegerTypeRequired;.class
        //   446: ldc             "err_typecheck_unary_expr"
        //   448: new             Ljava/lang/StringBuilder;
        //   451: dup            
        //   452: invokespecial   java/lang/StringBuilder.<init>:()V
        //   455: ldc             "Expression of type '"
        //   457: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: aload           5
        //   462: aload_1        
        //   463: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   466: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: ldc             "' is not numeric"
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   477: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   480: pop            
        //   481: goto            488
        //   484: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: return         
        //   489: aload_2        
        //   490: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   493: if_acmpne       533
        //   496: aload_1        
        //   497: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   500: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //   503: ifne            532
        //   506: goto            513
        //   509: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   512: athrow         
        //   513: aload_0        
        //   514: aload_1        
        //   515: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //   517: ldc             "err_typecheck_invalid_lvalue_addrof"
        //   519: ldc             "Address expression must be lvalue"
        //   521: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   524: pop            
        //   525: goto            532
        //   528: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   531: athrow         
        //   532: return         
        //   533: aload_2        
        //   534: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   537: if_acmpne       561
        //   540: aload           5
        //   542: aload_1        
        //   543: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalarOrConvertibleToScalar:(Lcom/intellij/psi/PsiElement;)Z
        //   546: ifeq            561
        //   549: goto            556
        //   552: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   555: athrow         
        //   556: return         
        //   557: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   560: athrow         
        //   561: aload_2        
        //   562: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TILDE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   565: if_acmpne       589
        //   568: aload           5
        //   570: aload_3        
        //   571: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   574: ifeq            589
        //   577: goto            584
        //   580: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   583: athrow         
        //   584: return         
        //   585: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   588: athrow         
        //   589: aload_2        
        //   590: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__IMAG__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   593: if_acmpeq       610
        //   596: aload_2        
        //   597: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__REAL__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   600: if_acmpne       630
        //   603: goto            610
        //   606: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   609: athrow         
        //   610: aload           5
        //   612: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   615: ifeq            630
        //   618: goto            625
        //   621: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   624: athrow         
        //   625: return         
        //   626: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: new             Ljava/lang/StringBuilder;
        //   633: dup            
        //   634: invokespecial   java/lang/StringBuilder.<init>:()V
        //   637: ldc             "Unary operator '"
        //   639: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   642: aload_2        
        //   643: invokevirtual   com/jetbrains/cidr/lang/parser/OCElementType.getName:()Ljava/lang/String;
        //   646: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   649: ldc             "' can't be applied to the expression of type '"
        //   651: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   654: aload           5
        //   656: aload_1        
        //   657: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   660: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   663: ldc             "'"
        //   665: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   668: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   671: astore          6
        //   673: aload_0        
        //   674: aload_3        
        //   675: ldc             "err_typecheck_unary_expr"
        //   677: aload           6
        //   679: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   682: pop            
        //   683: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      19     22     26     Ljava/lang/IllegalArgumentException;
        //  15     33     36     40     Ljava/lang/IllegalArgumentException;
        //  26     41     41     45     Ljava/lang/IllegalArgumentException;
        //  79     95     98     102    Ljava/lang/IllegalArgumentException;
        //  86     103    103    107    Ljava/lang/IllegalArgumentException;
        //  125    141    144    148    Ljava/lang/IllegalArgumentException;
        //  133    156    159    163    Ljava/lang/IllegalArgumentException;
        //  148    178    178    182    Ljava/lang/IllegalArgumentException;
        //  182    200    203    207    Ljava/lang/IllegalArgumentException;
        //  210    230    233    237    Ljava/lang/IllegalArgumentException;
        //  238    247    247    251    Ljava/lang/IllegalArgumentException;
        //  251    265    268    272    Ljava/lang/IllegalArgumentException;
        //  258    280    283    287    Ljava/lang/IllegalArgumentException;
        //  272    288    288    292    Ljava/lang/IllegalArgumentException;
        //  292    308    311    315    Ljava/lang/IllegalArgumentException;
        //  300    330    330    334    Ljava/lang/IllegalArgumentException;
        //  334    351    354    358    Ljava/lang/IllegalArgumentException;
        //  343    397    400    404    Ljava/lang/IllegalArgumentException;
        //  405    419    422    426    Ljava/lang/IllegalArgumentException;
        //  412    435    438    442    Ljava/lang/IllegalArgumentException;
        //  426    481    484    488    Ljava/lang/IllegalArgumentException;
        //  489    506    509    513    Ljava/lang/IllegalArgumentException;
        //  496    525    528    532    Ljava/lang/IllegalArgumentException;
        //  533    549    552    556    Ljava/lang/IllegalArgumentException;
        //  540    557    557    561    Ljava/lang/IllegalArgumentException;
        //  561    577    580    584    Ljava/lang/IllegalArgumentException;
        //  568    585    585    589    Ljava/lang/IllegalArgumentException;
        //  589    603    606    610    Ljava/lang/IllegalArgumentException;
        //  596    618    621    625    Ljava/lang/IllegalArgumentException;
        //  610    626    626    630    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    
    public void checkCastExperssion(final OCCastExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getCastType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //     6: aload_1        
        //     7: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    12: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: astore_2       
        //    16: aload_1        
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    22: astore_3       
        //    23: aload_3        
        //    24: ifnonnull       110
        //    27: aload_1        
        //    28: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getArgumentList:()Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //    33: astore          4
        //    35: aload           4
        //    37: ifnonnull       45
        //    40: return         
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload           4
        //    47: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentList.getArguments:()Ljava/util/List;
        //    52: invokeinterface java/util/List.size:()I
        //    57: iconst_1       
        //    58: if_icmpne       81
        //    61: aload           4
        //    63: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentList.getArguments:()Ljava/util/List;
        //    68: iconst_0       
        //    69: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    74: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    77: astore_3       
        //    78: goto            110
        //    81: aload_0        
        //    82: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //    85: aload_1        
        //    86: aload_1        
        //    87: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    92: aload           4
        //    94: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentList.getArguments:()Ljava/util/List;
        //    99: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.getArgumentList:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   102: aconst_null    
        //   103: aload_2        
        //   104: iconst_1       
        //   105: aload_1        
        //   106: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkTypeInitialization:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/psi/PsiElement;)V
        //   109: return         
        //   110: aload_1        
        //   111: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getBridgeCastModifier:()Lcom/intellij/psi/PsiElement;
        //   116: astore          4
        //   118: aload_3        
        //   119: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   124: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   127: astore          5
        //   129: aload           4
        //   131: ifnull          667
        //   134: aload           4
        //   136: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   139: checkcast       Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   142: astore          6
        //   144: aload           6
        //   146: invokevirtual   com/jetbrains/cidr/lang/parser/OCElementType.getName:()Ljava/lang/String;
        //   149: astore          7
        //   151: aload           5
        //   153: aload_3        
        //   154: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   157: astore          8
        //   159: aload_2        
        //   160: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   163: istore          9
        //   165: iload           9
        //   167: ifne            192
        //   170: aload_2        
        //   171: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   174: ifeq            192
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: iconst_1       
        //   185: goto            193
        //   188: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: iconst_0       
        //   193: istore          10
        //   195: aload           5
        //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   200: istore          11
        //   202: iload           11
        //   204: ifne            230
        //   207: aload           5
        //   209: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   212: ifeq            230
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: iconst_1       
        //   223: goto            231
        //   226: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: iconst_0       
        //   231: istore          12
        //   233: aload           6
        //   235: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BRIDGE_RETAINED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   238: if_acmpne       352
        //   241: iload           10
        //   243: ifne            295
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: aload_0        
        //   254: aload_1        
        //   255: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   260: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   262: ldc             "CIDR"
        //   264: new             Ljava/lang/StringBuilder;
        //   267: dup            
        //   268: invokespecial   java/lang/StringBuilder.<init>:()V
        //   271: aload           7
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: ldc             " requires CF pointer type"
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   284: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   287: pop            
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: iload           11
        //   297: ifne            666
        //   300: aload_0        
        //   301: aload_1        
        //   302: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   307: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   309: ldc             "CIDR"
        //   311: new             Ljava/lang/StringBuilder;
        //   314: dup            
        //   315: invokespecial   java/lang/StringBuilder.<init>:()V
        //   318: aload           7
        //   320: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: ldc             " requires object pointer type instead of '"
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: aload           8
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: ldc             "'"
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   341: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   344: pop            
        //   345: goto            666
        //   348: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: aload           6
        //   354: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BRIDGE_TRANSFER_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   357: if_acmpne       471
        //   360: iload           9
        //   362: ifne            414
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload_0        
        //   373: aload_1        
        //   374: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   379: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   381: ldc             "CIDR"
        //   383: new             Ljava/lang/StringBuilder;
        //   386: dup            
        //   387: invokespecial   java/lang/StringBuilder.<init>:()V
        //   390: aload           7
        //   392: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   395: ldc             " requires object pointer type"
        //   397: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   400: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   403: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   406: pop            
        //   407: goto            414
        //   410: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: iload           12
        //   416: ifne            666
        //   419: aload_0        
        //   420: aload_1        
        //   421: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   426: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   428: ldc             "CIDR"
        //   430: new             Ljava/lang/StringBuilder;
        //   433: dup            
        //   434: invokespecial   java/lang/StringBuilder.<init>:()V
        //   437: aload           7
        //   439: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: ldc             " requires CF pointer type instead of '"
        //   444: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   447: aload           8
        //   449: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   452: ldc             "'"
        //   454: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   457: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   460: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   463: pop            
        //   464: goto            666
        //   467: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload           6
        //   473: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BRIDGE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   476: if_acmpne       666
        //   479: iload           9
        //   481: ifne            545
        //   484: goto            491
        //   487: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   490: athrow         
        //   491: iload           10
        //   493: ifne            545
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload_0        
        //   504: aload_1        
        //   505: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   510: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   512: ldc             "CIDR"
        //   514: new             Ljava/lang/StringBuilder;
        //   517: dup            
        //   518: invokespecial   java/lang/StringBuilder.<init>:()V
        //   521: aload           7
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: ldc             " requires object or CF pointer type"
        //   528: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   531: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   534: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   537: pop            
        //   538: goto            545
        //   541: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   544: athrow         
        //   545: iload           10
        //   547: ifeq            562
        //   550: iload           11
        //   552: ifne            666
        //   555: goto            562
        //   558: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   561: athrow         
        //   562: iload           9
        //   564: ifeq            586
        //   567: goto            574
        //   570: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   573: athrow         
        //   574: iload           12
        //   576: ifne            666
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: new             Ljava/lang/StringBuilder;
        //   589: dup            
        //   590: invokespecial   java/lang/StringBuilder.<init>:()V
        //   593: aload           7
        //   595: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   598: ldc             " requires "
        //   600: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   603: iload           9
        //   605: ifeq            624
        //   608: goto            615
        //   611: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   614: athrow         
        //   615: ldc             "CF"
        //   617: goto            626
        //   620: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   623: athrow         
        //   624: ldc             "object"
        //   626: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   629: ldc             " pointer type instead of '"
        //   631: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   634: aload           8
        //   636: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   639: ldc             "'"
        //   641: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   644: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   647: astore          13
        //   649: aload_0        
        //   650: aload_1        
        //   651: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   656: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   658: ldc             "CIDR"
        //   660: aload           13
        //   662: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   665: pop            
        //   666: return         
        //   667: aload_0        
        //   668: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   671: aload_2        
        //   672: aload           5
        //   674: aload_1        
        //   675: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   680: aload_3        
        //   681: aload_1        
        //   682: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkTypeCast:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;)V
        //   685: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  35     41     41     45     Ljava/lang/IllegalArgumentException;
        //  165    177    180    184    Ljava/lang/IllegalArgumentException;
        //  170    188    188    192    Ljava/lang/IllegalArgumentException;
        //  202    215    218    222    Ljava/lang/IllegalArgumentException;
        //  207    226    226    230    Ljava/lang/IllegalArgumentException;
        //  233    246    249    253    Ljava/lang/IllegalArgumentException;
        //  241    288    291    295    Ljava/lang/IllegalArgumentException;
        //  295    348    348    352    Ljava/lang/IllegalArgumentException;
        //  352    365    368    372    Ljava/lang/IllegalArgumentException;
        //  360    407    410    414    Ljava/lang/IllegalArgumentException;
        //  414    467    467    471    Ljava/lang/IllegalArgumentException;
        //  471    484    487    491    Ljava/lang/IllegalArgumentException;
        //  479    496    499    503    Ljava/lang/IllegalArgumentException;
        //  491    538    541    545    Ljava/lang/IllegalArgumentException;
        //  545    555    558    562    Ljava/lang/IllegalArgumentException;
        //  550    567    570    574    Ljava/lang/IllegalArgumentException;
        //  562    579    582    586    Ljava/lang/IllegalArgumentException;
        //  574    608    611    615    Ljava/lang/IllegalArgumentException;
        //  586    620    620    624    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0491:
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
    
    private boolean a(@Nullable final OCExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       10
        //     4: iconst_1       
        //     5: ireturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_1        
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    16: astore_2       
        //    17: aload_1        
        //    18: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParenthesesAndCasts:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    21: astore_1       
        //    22: aload_1        
        //    23: ifnonnull       32
        //    26: iconst_1       
        //    27: ireturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_2        
        //    33: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    36: ifeq            45
        //    39: iconst_0       
        //    40: ireturn        
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_1        
        //    46: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //    49: ifeq            156
        //    52: aload_1        
        //    53: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    58: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    63: ifeq            156
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: aload_1        
        //    75: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //    78: iconst_1       
        //    79: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getPositiveExpression:(Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    84: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    87: ifeq            120
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload_0        
        //    98: aload_1        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //   102: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getNegativeExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   107: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   110: ifne            126
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: iconst_0       
        //   121: ireturn        
        //   122: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_1        
        //   127: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   130: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //   133: ifne            154
        //   136: aload_0        
        //   137: aload_1        
        //   138: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //   140: ldc             "CIDR"
        //   142: ldc             "Expression is not assignable"
        //   144: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   147: pop            
        //   148: iconst_0       
        //   149: ireturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: iconst_1       
        //   155: ireturn        
        //   156: new             Lcom/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor;
        //   159: dup            
        //   160: invokespecial   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.<init>:()V
        //   163: astore_3       
        //   164: aload_1        
        //   165: aload_3        
        //   166: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   171: aload_3        
        //   172: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   175: astore          4
        //   177: aload           4
        //   179: ifnull          388
        //   182: aload           4
        //   184: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   187: ifeq            388
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload           4
        //   199: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   204: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   207: if_acmpne       388
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: aload_1        
        //   218: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   221: ifeq            388
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload           4
        //   233: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   236: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isMutable:()Z
        //   239: ifne            388
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload_1        
        //   250: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   253: astore          5
        //   255: aload           5
        //   257: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   262: astore          6
        //   264: aload           5
        //   266: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   271: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   274: if_acmpne       299
        //   277: aload_0        
        //   278: aload           6
        //   280: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   283: ifne            388
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: iconst_0       
        //   294: ireturn        
        //   295: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload           6
        //   301: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   306: astore          7
        //   308: aload           7
        //   310: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   313: ifeq            388
        //   316: aload           7
        //   318: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   321: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //   324: ifeq            388
        //   327: goto            334
        //   330: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload           6
        //   336: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   339: ifeq            369
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: aload           6
        //   351: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   354: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.isCppThis:()Z
        //   359: ifne            388
        //   362: goto            369
        //   365: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: aload_0        
        //   370: aload           6
        //   372: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //   374: ldc             "CIDR"
        //   376: ldc             "Expression is not assignable"
        //   378: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   381: pop            
        //   382: iconst_0       
        //   383: ireturn        
        //   384: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: iconst_0       
        //   389: istore          5
        //   391: aconst_null    
        //   392: astore          6
        //   394: ldc             "Expression is not assignable"
        //   396: astore          7
        //   398: aload_3        
        //   399: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getNumOfDereferences:()I
        //   402: ifle            411
        //   405: iconst_1       
        //   406: istore          5
        //   408: goto            1152
        //   411: aload           4
        //   413: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   416: ifeq            583
        //   419: aload           4
        //   421: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   424: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   429: ifne            447
        //   432: goto            439
        //   435: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   438: athrow         
        //   439: iconst_1       
        //   440: goto            448
        //   443: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: iconst_0       
        //   448: istore          5
        //   450: new             Ljava/util/ArrayList;
        //   453: dup            
        //   454: iconst_2       
        //   455: invokespecial   java/util/ArrayList.<init>:(I)V
        //   458: astore          6
        //   460: aload           6
        //   462: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   465: dup            
        //   466: aload           4
        //   468: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   471: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READONLY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   474: aconst_null    
        //   475: aconst_null    
        //   476: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   479: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   484: pop            
        //   485: aload_1        
        //   486: iconst_1       
        //   487: anewarray       Ljava/lang/Class;
        //   490: dup            
        //   491: iconst_0       
        //   492: ldc             Lcom/jetbrains/cidr/lang/psi/OCImplementation;.class
        //   494: aastore        
        //   495: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   498: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   501: astore          8
        //   503: aload           8
        //   505: ifnull          580
        //   508: aload           8
        //   510: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   515: astore          9
        //   517: aload           9
        //   519: ifnull          580
        //   522: aload           4
        //   524: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   527: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   532: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   535: aload           9
        //   537: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   542: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   545: ifeq            580
        //   548: goto            555
        //   551: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   554: athrow         
        //   555: aload           6
        //   557: new             Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker$1;
        //   560: dup            
        //   561: aload_0        
        //   562: aload           4
        //   564: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$1.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   567: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   572: pop            
        //   573: goto            580
        //   576: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   579: athrow         
        //   580: goto            1152
        //   583: aload           4
        //   585: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   588: ifeq            627
        //   591: aload           4
        //   593: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   598: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
        //   601: istore          5
        //   603: aload_0        
        //   604: aload_1        
        //   605: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   608: aload           4
        //   610: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   613: ldc             "Calling"
        //   615: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.checkReadonlyAccess:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/lang/String;)Z
        //   618: ifne            1152
        //   621: iconst_0       
        //   622: ireturn        
        //   623: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   626: athrow         
        //   627: aload           4
        //   629: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   632: ifeq            641
        //   635: iconst_1       
        //   636: istore          5
        //   638: goto            1152
        //   641: aload           4
        //   643: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   646: ifeq            655
        //   649: iconst_0       
        //   650: istore          5
        //   652: goto            1152
        //   655: aload           4
        //   657: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   660: ifeq            1040
        //   663: aload           4
        //   665: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   668: astore          8
        //   670: new             Lcom/intellij/openapi/util/Ref;
        //   673: dup            
        //   674: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   677: astore          9
        //   679: iconst_1       
        //   680: istore          5
        //   682: aload_2        
        //   683: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   686: ifeq            713
        //   689: aload_2        
        //   690: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   693: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //   696: ifeq            713
        //   699: goto            706
        //   702: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: iconst_0       
        //   707: istore          5
        //   709: ldc             "Array is not assignable"
        //   711: astore          7
        //   713: iload           5
        //   715: ifne            725
        //   718: goto            1037
        //   721: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   724: athrow         
        //   725: aload           8
        //   727: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   730: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   733: if_acmpne       742
        //   736: iconst_0       
        //   737: istore          5
        //   739: goto            1037
        //   742: aload_2        
        //   743: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   746: ifne            782
        //   749: aload           8
        //   751: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConstexpr:()Z
        //   754: ifne            782
        //   757: goto            764
        //   760: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   763: athrow         
        //   764: aload           8
        //   766: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   769: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   772: if_acmpne       831
        //   775: goto            782
        //   778: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   781: athrow         
        //   782: iconst_0       
        //   783: istore          5
        //   785: ldc             "Variable is declared constant and is not assignable"
        //   787: astore          7
        //   789: aload           8
        //   791: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConstexpr:()Z
        //   794: ifeq            807
        //   797: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONSTEXPR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   800: goto            810
        //   803: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   806: athrow         
        //   807: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   810: astore          10
        //   812: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction;
        //   815: dup            
        //   816: aload           8
        //   818: aload           10
        //   820: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;)V
        //   823: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   826: astore          6
        //   828: goto            1037
        //   831: aload_2        
        //   832: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   835: ifeq            928
        //   838: aload_2        
        //   839: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   842: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //   845: ifeq            928
        //   848: goto            855
        //   851: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   854: athrow         
        //   855: iconst_0       
        //   856: istore          5
        //   858: new             Ljava/lang/StringBuilder;
        //   861: dup            
        //   862: invokespecial   java/lang/StringBuilder.<init>:()V
        //   865: ldc             "'"
        //   867: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   870: aload_2        
        //   871: aload_1        
        //   872: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   875: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   878: ldc             "' is read-only reference"
        //   880: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   883: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   886: astore          7
        //   888: aload_2        
        //   889: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   892: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   895: aload_1        
        //   896: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getProject:()Lcom/intellij/openapi/project/Project;
        //   901: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   904: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   907: astore          10
        //   909: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   912: dup            
        //   913: aload           4
        //   915: aload           10
        //   917: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   920: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   923: astore          6
        //   925: goto            1037
        //   928: aload_1        
        //   929: aload           8
        //   931: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //   934: ifeq            964
        //   937: iconst_0       
        //   938: istore          5
        //   940: ldc             "Variable is declared outside the block and is not assignable"
        //   942: astore          7
        //   944: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction;
        //   947: dup            
        //   948: aload           8
        //   950: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   953: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;)V
        //   956: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   959: astore          6
        //   961: goto            1037
        //   964: aload_1        
        //   965: aload           8
        //   967: aload           9
        //   969: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;Lcom/intellij/openapi/util/Ref;)Z
        //   972: ifeq            1024
        //   975: iconst_0       
        //   976: istore          5
        //   978: new             Ljava/lang/StringBuilder;
        //   981: dup            
        //   982: invokespecial   java/lang/StringBuilder.<init>:()V
        //   985: aload           8
        //   987: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   990: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   993: ldc             " is assigned inside a const function"
        //   995: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   998: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1001: astore          7
        //  1003: aload_0        
        //  1004: aload           9
        //  1006: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //  1009: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1012: iconst_0       
        //  1013: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.changeFunctionConstQuickFix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //  1016: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1019: astore          6
        //  1021: goto            1037
        //  1024: aload_1        
        //  1025: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //  1028: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //  1031: ifne            1037
        //  1034: iconst_0       
        //  1035: istore          5
        //  1037: goto            1152
        //  1040: aload_2        
        //  1041: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1044: ifeq            1098
        //  1047: aload_2        
        //  1048: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //  1051: ifne            1092
        //  1054: goto            1061
        //  1057: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1060: athrow         
        //  1061: ldc             "="
        //  1063: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.INFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //  1066: aload_1        
        //  1067: aload_2        
        //  1068: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1071: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.resolveOperator:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1074: ifnull          1092
        //  1077: goto            1084
        //  1080: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1083: athrow         
        //  1084: iconst_1       
        //  1085: goto            1093
        //  1088: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1091: athrow         
        //  1092: iconst_0       
        //  1093: istore          5
        //  1095: goto            1152
        //  1098: aload_3        
        //  1099: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.wasUnresolvedSymbol:()Z
        //  1102: ifeq            1111
        //  1105: iconst_1       
        //  1106: istore          5
        //  1108: goto            1152
        //  1111: aload_1        
        //  1112: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //  1115: ifeq            1143
        //  1118: aload_1        
        //  1119: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //  1122: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.isStringLiteral:()Z
        //  1127: ifeq            1143
        //  1130: goto            1137
        //  1133: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1136: athrow         
        //  1137: iconst_0       
        //  1138: istore          5
        //  1140: goto            1152
        //  1143: aload_1        
        //  1144: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //  1147: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //  1150: istore          5
        //  1152: aconst_null    
        //  1153: astore          8
        //  1155: aload_1        
        //  1156: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //  1159: ifeq            1198
        //  1162: aload_1        
        //  1163: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //  1166: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1171: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1174: if_acmpne       1198
        //  1177: goto            1184
        //  1180: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1183: athrow         
        //  1184: aload_1        
        //  1185: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //  1188: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1193: astore          8
        //  1195: goto            1216
        //  1198: aload_1        
        //  1199: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //  1202: ifeq            1216
        //  1205: aload_1        
        //  1206: checkcast       Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //  1209: invokeinterface com/jetbrains/cidr/lang/psi/OCArraySelectionExpression.getArrayExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1214: astore          8
        //  1216: aload           8
        //  1218: ifnull          1387
        //  1221: aload           8
        //  1223: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1228: astore          9
        //  1230: aload           9
        //  1232: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1235: ifeq            1387
        //  1238: aload           9
        //  1240: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1243: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //  1246: ifeq            1387
        //  1249: goto            1256
        //  1252: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1255: athrow         
        //  1256: iconst_0       
        //  1257: istore          5
        //  1259: new             Ljava/lang/StringBuilder;
        //  1262: dup            
        //  1263: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1266: ldc             "'"
        //  1268: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1271: aload           9
        //  1273: aload           8
        //  1275: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1281: ldc             "' is read-only pointer"
        //  1283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1286: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1289: astore          7
        //  1291: aload           4
        //  1293: ifnull          1387
        //  1296: aload           9
        //  1298: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //  1301: ifne            1387
        //  1304: goto            1311
        //  1307: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1310: athrow         
        //  1311: aload           9
        //  1313: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //  1316: istore          10
        //  1318: aload           9
        //  1320: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1323: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1326: aload_1        
        //  1327: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getProject:()Lcom/intellij/openapi/project/Project;
        //  1332: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1335: aload_3        
        //  1336: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getNumOfDereferences:()I
        //  1339: iconst_1       
        //  1340: isub           
        //  1341: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;I)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1344: astore          11
        //  1346: aload           11
        //  1348: aload_1        
        //  1349: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getProject:()Lcom/intellij/openapi/project/Project;
        //  1354: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1357: aconst_null    
        //  1358: aconst_null    
        //  1359: iload           10
        //  1361: aload           11
        //  1363: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //  1366: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/types/OCType;ZZ)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1369: astore          12
        //  1371: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //  1374: dup            
        //  1375: aload           4
        //  1377: aload           12
        //  1379: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  1382: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1385: astore          6
        //  1387: iload           5
        //  1389: ifne            1452
        //  1392: aload_0        
        //  1393: aload_1        
        //  1394: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //  1396: ldc             "CIDR"
        //  1398: aload           7
        //  1400: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1403: astore          9
        //  1405: aload           6
        //  1407: ifnull          1452
        //  1410: aload           6
        //  1412: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1417: astore          10
        //  1419: aload           10
        //  1421: invokeinterface java/util/Iterator.hasNext:()Z
        //  1426: ifeq            1452
        //  1429: aload           10
        //  1431: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1436: checkcast       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1439: astore          11
        //  1441: aload_0        
        //  1442: aload           9
        //  1444: aload           11
        //  1446: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1449: goto            1419
        //  1452: iload           5
        //  1454: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  22     28     28     32     Ljava/lang/IllegalArgumentException;
        //  32     41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     66     69     73     Ljava/lang/IllegalArgumentException;
        //  52     90     93     97     Ljava/lang/IllegalArgumentException;
        //  73     113    116    120    Ljava/lang/IllegalArgumentException;
        //  97     122    122    126    Ljava/lang/IllegalArgumentException;
        //  126    150    150    154    Ljava/lang/IllegalArgumentException;
        //  177    190    193    197    Ljava/lang/IllegalArgumentException;
        //  182    210    213    217    Ljava/lang/IllegalArgumentException;
        //  197    224    227    231    Ljava/lang/IllegalArgumentException;
        //  217    242    245    249    Ljava/lang/IllegalArgumentException;
        //  264    286    289    293    Ljava/lang/IllegalArgumentException;
        //  277    295    295    299    Ljava/lang/IllegalArgumentException;
        //  308    327    330    334    Ljava/lang/IllegalArgumentException;
        //  316    342    345    349    Ljava/lang/IllegalArgumentException;
        //  334    362    365    369    Ljava/lang/IllegalArgumentException;
        //  349    384    384    388    Ljava/lang/IllegalArgumentException;
        //  411    432    435    439    Ljava/lang/IllegalArgumentException;
        //  419    443    443    447    Ljava/lang/IllegalArgumentException;
        //  517    548    551    555    Ljava/lang/IllegalArgumentException;
        //  522    573    576    580    Ljava/lang/IllegalArgumentException;
        //  603    623    623    627    Ljava/lang/IllegalArgumentException;
        //  682    699    702    706    Ljava/lang/IllegalArgumentException;
        //  713    721    721    725    Ljava/lang/IllegalArgumentException;
        //  742    757    760    764    Ljava/lang/IllegalArgumentException;
        //  749    775    778    782    Ljava/lang/IllegalArgumentException;
        //  789    803    803    807    Ljava/lang/IllegalArgumentException;
        //  831    848    851    855    Ljava/lang/IllegalArgumentException;
        //  1040   1054   1057   1061   Ljava/lang/IllegalArgumentException;
        //  1047   1077   1080   1084   Ljava/lang/IllegalArgumentException;
        //  1061   1088   1088   1092   Ljava/lang/IllegalArgumentException;
        //  1111   1130   1133   1137   Ljava/lang/IllegalArgumentException;
        //  1155   1177   1180   1184   Ljava/lang/IllegalArgumentException;
        //  1230   1249   1252   1256   Ljava/lang/IllegalArgumentException;
        //  1291   1304   1307   1311   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    private static boolean a(@NotNull final OCExpression p0, @NotNull final OCDeclaratorSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "expression"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isOutsideBlock"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "declaratorSymbol"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isOutsideBlock"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    92: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //    95: ifeq            126
        //    98: aload_1        
        //    99: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isBlockModifiable:()Z
        //   102: ifne            126
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_1        
        //   113: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   116: ifeq            132
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: ireturn        
        //   128: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: iconst_1       
        //   134: anewarray       Ljava/lang/Class;
        //   137: dup            
        //   138: iconst_0       
        //   139: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;.class
        //   141: aastore        
        //   142: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   145: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   148: astore_2       
        //   149: aload_2        
        //   150: ifnull          188
        //   153: aload_1        
        //   154: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   157: astore_3       
        //   158: aload_3        
        //   159: ifnull          182
        //   162: aload_2        
        //   163: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockExpression.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   168: aload_3        
        //   169: invokevirtual   com/intellij/openapi/util/TextRange.contains:(Lcom/intellij/openapi/util/TextRange;)Z
        //   172: ifne            188
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: iconst_1       
        //   183: ireturn        
        //   184: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: iconst_0       
        //   189: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     105    108    112    Ljava/lang/IllegalArgumentException;
        //  98     119    122    126    Ljava/lang/IllegalArgumentException;
        //  112    128    128    132    Ljava/lang/IllegalArgumentException;
        //  158    175    178    182    Ljava/lang/IllegalArgumentException;
        //  162    184    184    188    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    private static boolean a(@Nullable final OCExpression ocExpression, @NotNull final OCDeclaratorSymbol ocDeclaratorSymbol, @NotNull final Ref<OCFunctionSymbol> ref) {
        try {
            if (ocDeclaratorSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaratorSymbol", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "isInConstMemberFunction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functionSymbol", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "isInConstMemberFunction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final CVQualifiers outerFunctionCVQualifiers = OCCodeInsightUtil.getOuterFunctionCVQualifiers(ocDeclaratorSymbol, ocExpression, ref);
        Label_0113: {
            try {
                if (outerFunctionCVQualifiers == null) {
                    return false;
                }
                final CVQualifiers cvQualifiers = outerFunctionCVQualifiers;
                final boolean b = cvQualifiers.isConst();
                if (b) {
                    break Label_0113;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final CVQualifiers cvQualifiers = outerFunctionCVQualifiers;
                final boolean b = cvQualifiers.isConst();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    @NotNull
    public OCChangeTypeIntentionAction changeFunctionConstQuickFix(@NotNull final OCFunctionSymbol ocFunctionSymbol, final boolean b) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functionSymbol", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "changeFunctionConstQuickFix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCFunctionType type = ocFunctionSymbol.getType();
        final OCFunctionType ocFunctionType = new OCFunctionType(type.getReturnType(), type.getParameterTypes(true), type.getParameterNames(true), b, type.isVolatile(), type.isLValueRef(), type.isRValueRef());
        OCChangeTypeIntentionAction ocChangeTypeIntentionAction;
        try {
            ocChangeTypeIntentionAction = new OCChangeTypeIntentionAction(ocFunctionSymbol, ocFunctionType) {
                @Override
                protected String getTextInternal() {
                    StringBuilder append;
                    try {
                        append = new StringBuilder().append("Make ").append(ocFunctionSymbol.getNameWithKindLowercase());
                        if (b) {
                            final String s = " const";
                            return append.append(s).toString();
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    final String s = " non-const";
                    return append.append(s).toString();
                }
                
                @NotNull
                @Override
                public String getFamilyName() {
                    String s;
                    try {
                        s = "Change const qualifier";
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$2", "getFamilyName"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return s;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocChangeTypeIntentionAction == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "changeFunctionConstQuickFix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return ocChangeTypeIntentionAction;
    }
    
    @NotNull
    public OCChangeTypeIntentionAction changeFunctionVolatileQuickFix(@NotNull final OCFunctionSymbol ocFunctionSymbol, final boolean b) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functionSymbol", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "changeFunctionVolatileQuickFix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCFunctionType type = ocFunctionSymbol.getType();
        final OCFunctionType ocFunctionType = new OCFunctionType(type.getReturnType(), type.getParameterTypes(true), type.getParameterNames(true), type.isConst(), b, type.isLValueRef(), type.isRValueRef());
        OCChangeTypeIntentionAction ocChangeTypeIntentionAction;
        try {
            ocChangeTypeIntentionAction = new OCChangeTypeIntentionAction(ocFunctionSymbol, ocFunctionType) {
                @Override
                protected String getTextInternal() {
                    StringBuilder append;
                    try {
                        append = new StringBuilder().append("Make ").append(ocFunctionSymbol.getNameWithKindLowercase());
                        if (b) {
                            final String s = " volatile";
                            return append.append(s).toString();
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    final String s = " non-volatile";
                    return append.append(s).toString();
                }
                
                @NotNull
                @Override
                public String getFamilyName() {
                    String s;
                    try {
                        s = "Change volatile qualifier";
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$3", "getFamilyName"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return s;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocChangeTypeIntentionAction == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker", "changeFunctionVolatileQuickFix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return ocChangeTypeIntentionAction;
    }
    
    public boolean checkReadonlyAccess(final List<? extends PsiElement> list, final OCMethodSymbol ocMethodSymbol, final String s) {
        final OCClassSymbol interfaceOrProtocol = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getInterfaceOrProtocol();
        try {
            if (interfaceOrProtocol == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        if (ocMethodSymbol.isSetter()) {
            final String objCGetterFromSetter = OCNameSuggester.getObjCGetterFromSetter(ocMethodSymbol.getName());
            try {
                if (objCGetterFromSetter != null) {
                    interfaceOrProtocol.processMembers(objCGetterFromSetter, (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        try {
            if (!findFirstProcessor.isFound() || !((OCPropertySymbol)findFirstProcessor.getFoundValue()).isReadonly()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)list.get(0), new Class[] { OCImplementation.class });
        Label_0187: {
            if (ocClassDeclaration != null) {
                final OCClassSymbol symbol = ocClassDeclaration.getSymbol();
                try {
                    if (symbol == null) {
                        break Label_0187;
                    }
                    final OCClassSymbol ocClassSymbol = interfaceOrProtocol;
                    final OCClassSymbol ocClassSymbol2 = symbol;
                    final OCInterfaceSymbol ocInterfaceSymbol = ocClassSymbol2.getInterface();
                    final boolean b = ocClassSymbol.equals(ocInterfaceSymbol);
                    if (b) {
                        return true;
                    }
                    break Label_0187;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCClassSymbol ocClassSymbol = interfaceOrProtocol;
                    final OCClassSymbol ocClassSymbol2 = symbol;
                    final OCInterfaceSymbol ocInterfaceSymbol = ocClassSymbol2.getInterface();
                    final boolean b = ocClassSymbol.equals(ocInterfaceSymbol);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
        }
        this.registerQuickFixes(this.addWarningAnnotations(list, OCInspections.SetterForReadonlyProperty.class, "CIDR", s + " the setter method for readonly " + ((OCPropertySymbol)findFirstProcessor.getFoundValue()).getNameWithKindLowercase()), (IntentionAction)new OCChangePropertyAttributeIntentionAction((OCPropertySymbol)findFirstProcessor.getFoundValue(), OCPropertySymbol.PropertyAttribute.READONLY, null, null));
        return false;
    }
    
    public void checkCallExpression(final OCCallExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getFunctionReferenceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     6: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     9: astore_2       
        //    10: aload_2        
        //    11: ifnonnull       19
        //    14: return         
        //    15: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: aload_1        
        //    20: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    23: dup            
        //    24: aload_1        
        //    25: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    28: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getCalleeType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    33: astore_3       
        //    34: aload_3        
        //    35: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    38: ifeq            49
        //    41: aload_3        
        //    42: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: astore_3       
        //    49: aload_1        
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    60: ifeq            387
        //    63: aload_2        
        //    64: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    67: ifeq            219
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_2        
        //    78: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    81: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    86: astore          4
        //    88: aload           4
        //    90: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    93: ifeq            114
        //    96: aload           4
        //    98: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   101: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   104: ifne            129
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload           4
        //   116: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   119: ifeq            151
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aload_0        
        //   130: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   133: aload_1        
        //   134: aload_1        
        //   135: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   140: aload           4
        //   142: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkConstructorCall:(Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   145: pop            
        //   146: return         
        //   147: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload           4
        //   153: ifnull          219
        //   156: aload           4
        //   158: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   163: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
        //   166: ifeq            219
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload           4
        //   178: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   183: aload_1        
        //   184: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   189: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   192: astore          5
        //   194: aload_0        
        //   195: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload_1        
        //   201: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   206: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.getArgumentList:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   209: aload           4
        //   211: aload           5
        //   213: iconst_1       
        //   214: aload_1        
        //   215: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkTypeInitialization:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/psi/PsiElement;)V
        //   218: return         
        //   219: aload_1        
        //   220: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getReference:()Lcom/intellij/psi/PsiReference;
        //   225: instanceof      Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
        //   228: ifeq            330
        //   231: aload_1        
        //   232: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getReference:()Lcom/intellij/psi/PsiReference;
        //   237: checkcast       Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
        //   240: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.resolveToSymbols:()Ljava/util/List;
        //   243: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   248: astore          4
        //   250: aload           4
        //   252: invokeinterface java/util/Iterator.hasNext:()Z
        //   257: ifeq            306
        //   260: aload           4
        //   262: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   267: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   270: astore          5
        //   272: aload           5
        //   274: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   277: ifeq            303
        //   280: aload_0        
        //   281: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   284: aload           5
        //   286: aload_1        
        //   287: aconst_null    
        //   288: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFieldVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   291: pop            
        //   292: aload           5
        //   294: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   299: astore_3       
        //   300: goto            306
        //   303: goto            250
        //   306: aload_3        
        //   307: ifnull          330
        //   310: aload_3        
        //   311: aload_1        
        //   312: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/intellij/psi/PsiElement;)Z
        //   315: ifeq            330
        //   318: goto            325
        //   321: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: return         
        //   326: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: aload_2        
        //   331: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   334: ifeq            387
        //   337: aload_2        
        //   338: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   341: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getName:()Ljava/lang/String;
        //   346: ldc             "~"
        //   348: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   351: ifeq            387
        //   354: goto            361
        //   357: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: aload_1        
        //   362: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   367: invokeinterface java/util/List.isEmpty:()Z
        //   372: ifeq            387
        //   375: goto            382
        //   378: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: return         
        //   383: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: aload_3        
        //   388: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   391: ifeq            402
        //   394: aload_3        
        //   395: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   398: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   401: astore_3       
        //   402: aload_3        
        //   403: ifnull          420
        //   406: aload_3        
        //   407: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   410: ifeq            425
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: return         
        //   421: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_3        
        //   426: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   429: ifne            447
        //   432: aload_0        
        //   433: aload_2        
        //   434: ldc             "err_typecheck_call_not_function"
        //   436: ldc             "Called object is not a function"
        //   438: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   441: pop            
        //   442: return         
        //   443: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: new             Lcom/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor;
        //   450: dup            
        //   451: invokespecial   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.<init>:()V
        //   454: astore          4
        //   456: aload_2        
        //   457: aload           4
        //   459: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   464: aload           4
        //   466: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   469: astore          5
        //   471: aload           5
        //   473: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   476: ifeq            499
        //   479: aload_0        
        //   480: aload_2        
        //   481: aload           5
        //   483: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   486: ldc             "No matching function"
        //   488: invokestatic    com/jetbrains/cidr/lang/daemon/OCFunctionGroupHelperKt.annotateAmbig:(Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;Ljava/lang/String;)Z
        //   491: pop            
        //   492: goto            520
        //   495: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   498: athrow         
        //   499: aload_0        
        //   500: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   503: aload_1        
        //   504: aload_3        
        //   505: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   508: aload_1        
        //   509: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   514: aload           5
        //   516: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFunctionArguments:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   519: pop            
        //   520: aload_1        
        //   521: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   526: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   531: ifeq            613
        //   534: aload           5
        //   536: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   539: ifeq            613
        //   542: goto            549
        //   545: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   548: athrow         
        //   549: aload           5
        //   551: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   554: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   557: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   560: ifeq            613
        //   563: goto            570
        //   566: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   569: athrow         
        //   570: aload           5
        //   572: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   575: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   578: dup            
        //   579: aload_1        
        //   580: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   583: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppNonMemberOperator:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   586: ifne            613
        //   589: goto            596
        //   592: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   595: athrow         
        //   596: aload_0        
        //   597: aload_2        
        //   598: aload           5
        //   600: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   603: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.checkConstFunction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //   606: goto            613
        //   609: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     15     15     19     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  88     107    110    114    Ljava/lang/IllegalArgumentException;
        //  96     122    125    129    Ljava/lang/IllegalArgumentException;
        //  114    147    147    151    Ljava/lang/IllegalArgumentException;
        //  151    169    172    176    Ljava/lang/IllegalArgumentException;
        //  306    318    321    325    Ljava/lang/IllegalArgumentException;
        //  310    326    326    330    Ljava/lang/IllegalArgumentException;
        //  330    354    357    361    Ljava/lang/IllegalArgumentException;
        //  337    375    378    382    Ljava/lang/IllegalArgumentException;
        //  361    383    383    387    Ljava/lang/IllegalArgumentException;
        //  402    413    416    420    Ljava/lang/IllegalArgumentException;
        //  406    421    421    425    Ljava/lang/IllegalArgumentException;
        //  425    443    443    447    Ljava/lang/IllegalArgumentException;
        //  471    495    495    499    Ljava/lang/IllegalArgumentException;
        //  520    542    545    549    Ljava/lang/IllegalArgumentException;
        //  534    563    566    570    Ljava/lang/IllegalArgumentException;
        //  549    589    592    596    Ljava/lang/IllegalArgumentException;
        //  570    606    609    613    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0114:
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
    
    protected void checkConstFunction(final OCExpression ocExpression, final OCFunctionSymbol ocFunctionSymbol) {
        CVQualifiers cvQualifiers = null;
        Object changeFunctionConstQuickFix = null;
        Object changeFunctionVolatileQuickFix = null;
        if (ocExpression instanceof OCQualifiedExpression) {
            final OCQualifiedExpression ocQualifiedExpression = (OCQualifiedExpression)ocExpression;
            final OCExpression qualifier = ocQualifiedExpression.getQualifier();
            final OCPunctuatorElementType qualifyingTokenKind = ocQualifiedExpression.getQualifyingTokenKind();
            cvQualifiers = ocQualifiedExpression.getCVQualifiers();
            Label_0118: {
                try {
                    if (!cvQualifiers.isConst() || qualifyingTokenKind != OCTokenTypes.DOT) {
                        break Label_0118;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(qualifier);
                if (symbol instanceof OCSymbolWithQualifiedName) {
                    changeFunctionConstQuickFix = new OCRemoveTypeModifierIntentionAction((OCSymbolWithQualifiedName)symbol, OCTokenTypes.CONST_KEYWORD);
                    changeFunctionVolatileQuickFix = new OCRemoveTypeModifierIntentionAction((OCSymbolWithQualifiedName)symbol, OCTokenTypes.VOLATILE_KEYWORD);
                }
            }
        }
        else if (ocExpression instanceof OCReferenceExpression) {
            final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getContextOfType((PsiElement)ocExpression, false, new Class[] { OCFunctionDefinition.class });
            OCFunctionSymbol symbol2 = null;
            Label_0167: {
                try {
                    if (ocFunctionDefinition != null) {
                        symbol2 = ocFunctionDefinition.getSymbol();
                        break Label_0167;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                symbol2 = null;
            }
            final OCFunctionSymbol ocFunctionSymbol2 = symbol2;
            if (ocFunctionSymbol2 != null) {
                cvQualifiers = ocFunctionSymbol2.getType().getCVQualifiers();
                changeFunctionConstQuickFix = this.changeFunctionConstQuickFix(ocFunctionSymbol2, false);
                changeFunctionVolatileQuickFix = this.changeFunctionVolatileQuickFix(ocFunctionSymbol2, false);
            }
        }
        Label_0222: {
            try {
                if (ocFunctionSymbol.isCppConstructor()) {
                    return;
                }
                final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol;
                final boolean b = ocFunctionSymbol3.resolveIsStatic();
                if (!b) {
                    break Label_0222;
                }
                return;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol;
                final boolean b = ocFunctionSymbol3.resolveIsStatic();
                if (!b) {
                    this.a(ocExpression, ocFunctionSymbol, cvQualifiers, true, "const", (IntentionAction)changeFunctionConstQuickFix);
                    this.a(ocExpression, ocFunctionSymbol, cvQualifiers, false, "volatile", (IntentionAction)changeFunctionVolatileQuickFix);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
    }
    
    private void a(final OCExpression p0, final OCFunctionSymbol p1, final CVQualifiers p2, final boolean p3, final String p4, final IntentionAction p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnull          290
        //     4: iload           4
        //     6: ifeq            44
        //     9: goto            16
        //    12: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: aload_3        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isConst:()Z
        //    20: ifeq            44
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_2        
        //    31: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isConst:()Z
        //    34: ifeq            87
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iload           4
        //    46: ifne            290
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_3        
        //    57: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isVolatile:()Z
        //    60: ifeq            290
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_2        
        //    71: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVolatile:()Z
        //    77: ifne            290
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: new             Ljava/lang/StringBuilder;
        //    90: dup            
        //    91: invokespecial   java/lang/StringBuilder.<init>:()V
        //    94: ldc             "Non-"
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: aload           5
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: ldc             " "
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: aload_2        
        //   110: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: ldc             " is called "
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: aload_1        
        //   122: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   125: ifeq            167
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: new             Ljava/lang/StringBuilder;
        //   138: dup            
        //   139: invokespecial   java/lang/StringBuilder.<init>:()V
        //   142: ldc             "from the "
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: aload           5
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: ldc             " function"
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   160: goto            192
        //   163: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: new             Ljava/lang/StringBuilder;
        //   170: dup            
        //   171: invokespecial   java/lang/StringBuilder.<init>:()V
        //   174: ldc             "on the "
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: aload           5
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: ldc             " object"
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   198: astore          7
        //   200: aload_0        
        //   201: aload_1        
        //   202: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstExpressionRequired;.class
        //   204: ldc             "err_member_function_call_bad_cvr"
        //   206: aload           7
        //   208: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   211: astore          8
        //   213: aload_0        
        //   214: aload           8
        //   216: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction;
        //   219: dup            
        //   220: aload_2        
        //   221: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getDeclarationInParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   224: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STATIC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   227: iconst_0       
        //   228: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;Z)V
        //   231: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   234: iload           4
        //   236: ifeq            258
        //   239: aload_0        
        //   240: aload           8
        //   242: aload_0        
        //   243: aload_2        
        //   244: iconst_1       
        //   245: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.changeFunctionConstQuickFix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   248: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   251: goto            270
        //   254: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: aload_0        
        //   259: aload           8
        //   261: aload_0        
        //   262: aload_2        
        //   263: iconst_1       
        //   264: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.changeFunctionVolatileQuickFix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   267: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   270: aload           6
        //   272: ifnull          290
        //   275: aload_0        
        //   276: aload           8
        //   278: aload           6
        //   280: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      12     16     Ljava/lang/IllegalArgumentException;
        //  4      23     26     30     Ljava/lang/IllegalArgumentException;
        //  16     37     40     44     Ljava/lang/IllegalArgumentException;
        //  30     49     52     56     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  56     80     83     87     Ljava/lang/IllegalArgumentException;
        //  70     128    131    135    Ljava/lang/IllegalArgumentException;
        //  87     163    163    167    Ljava/lang/IllegalArgumentException;
        //  213    254    254    258    Ljava/lang/IllegalArgumentException;
        //  270    283    286    290    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0016:
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
    
    public void checkMacroCall(final OCMacroCall ocMacroCall) {
        try {
            if (ocMacroCall.getTextLength() == 0) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCMacroSymbol resolveToSymbol = ocMacroCall.resolveToSymbol();
        Label_0044: {
            try {
                if (resolveToSymbol == null) {
                    return;
                }
                final OCMacroSymbol ocMacroSymbol = resolveToSymbol;
                final boolean b = ocMacroSymbol.hasParameterList();
                if (!b) {
                    return;
                }
                break Label_0044;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCMacroSymbol ocMacroSymbol = resolveToSymbol;
                final boolean b = ocMacroSymbol.hasParameterList();
                if (!b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        final OCMacroCall.ParameterCheckResult checkParameters = ocMacroCall.checkParameters(resolveToSymbol);
        final int actualCount = checkParameters.getActualCount();
        final int allowedCount = checkParameters.getAllowedCount();
        Label_0132: {
            OCOperatorsChecker ocOperatorsChecker = null;
            OCMacroCall ocMacroCall2 = null;
            Class<OCInspections.FunctionParameterCountMismatch> clazz = null;
            String s = null;
            StringBuilder sb2 = null;
            String s3 = null;
            Label_0114: {
                Label_0103: {
                    try {
                        if (actualCount >= allowedCount) {
                            break Label_0132;
                        }
                        ocOperatorsChecker = this;
                        ocMacroCall2 = ocMacroCall;
                        clazz = OCInspections.FunctionParameterCountMismatch.class;
                        s = "err_too_few_args_in_macro_invoc";
                        final StringBuilder sb = new StringBuilder();
                        final String s2 = "Too few arguments for a macro call, expected ";
                        sb2 = sb.append(s2);
                        final OCMacroSymbol ocMacroSymbol2 = resolveToSymbol;
                        final boolean b2 = ocMacroSymbol2.isVararg();
                        if (b2) {
                            break Label_0103;
                        }
                        break Label_0103;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    try {
                        ocOperatorsChecker = this;
                        ocMacroCall2 = ocMacroCall;
                        clazz = OCInspections.FunctionParameterCountMismatch.class;
                        s = "err_too_few_args_in_macro_invoc";
                        final StringBuilder sb = new StringBuilder();
                        final String s2 = "Too few arguments for a macro call, expected ";
                        sb2 = sb.append(s2);
                        final OCMacroSymbol ocMacroSymbol2 = resolveToSymbol;
                        final boolean b2 = ocMacroSymbol2.isVararg();
                        if (b2) {
                            s3 = "at least ";
                            break Label_0114;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                }
                s3 = "";
            }
            ocOperatorsChecker.addErrorAnnotation((PsiElement)ocMacroCall2, clazz, s, sb2.append(s3).append(allowedCount).toString());
            return;
            try {
                if (actualCount > allowedCount) {
                    this.addErrorAnnotation((PsiElement)ocMacroCall, OCInspections.FunctionParameterCountMismatch.class, "err_too_many_args_in_macro_invoc", "Too many arguments for a macro call, expected " + allowedCount);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
    }
    
    public void checkSendMessageExpression(final OCSendMessageExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //     6: astore_2       
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    13: astore_3       
        //    14: aload_1        
        //    15: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //    20: invokeinterface java/util/List.size:()I
        //    25: ifne            45
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MethodParameterCountMismatch;.class
        //    32: ldc             "CIDRobjc_message_selector_missing"
        //    34: ldc             "Message selector expected"
        //    36: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    39: pop            
        //    40: return         
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_2        
        //    46: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.isStaticnessMismatch:()Z
        //    49: ifeq            242
        //    52: aload_2        
        //    53: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.isStaticContext:()Z
        //    56: ifeq            75
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: ldc             "Instance method is called from the class context"
        //    68: goto            77
        //    71: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: ldc             "Class method is called from the instance context"
        //    77: astore          4
        //    79: aload_0        
        //    80: aload_1        
        //    81: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$StaticnessMismatch;.class
        //    83: ldc             "CIDR"
        //    85: aload           4
        //    87: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    90: astore          5
        //    92: aload_2        
        //    93: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getAllResponders:()Ljava/util/List;
        //    96: invokeinterface java/util/List.size:()I
        //   101: iconst_1       
        //   102: if_icmpne       241
        //   105: aload_2        
        //   106: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getAllResponders:()Ljava/util/List;
        //   109: iconst_0       
        //   110: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   115: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   118: astore          6
        //   120: aload_0        
        //   121: aload           5
        //   123: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction;
        //   126: dup            
        //   127: aload           6
        //   129: aload_2        
        //   130: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.isStaticContext:()Z
        //   133: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Z)V
        //   136: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   139: aload_1        
        //   140: iconst_1       
        //   141: anewarray       Ljava/lang/Class;
        //   144: dup            
        //   145: iconst_0       
        //   146: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   148: aastore        
        //   149: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   152: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   155: astore          7
        //   157: aload_3        
        //   158: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   161: ifeq            241
        //   164: aload           7
        //   166: ifnull          241
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload_3        
        //   177: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   180: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   185: ifnull          241
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_0        
        //   196: aload           5
        //   198: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction;
        //   201: dup            
        //   202: aload           7
        //   204: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   209: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   212: aload_2        
        //   213: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.isStaticContext:()Z
        //   216: ifne            234
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: iconst_1       
        //   227: goto            235
        //   230: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: iconst_0       
        //   235: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Z)V
        //   238: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   241: return         
        //   242: aload_2        
        //   243: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   246: astore          4
        //   248: aload           4
        //   250: ifnull          326
        //   253: aload_1        
        //   254: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.isVarargCall:()Z
        //   259: ifeq            326
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload           4
        //   271: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isVararg:()Z
        //   276: ifne            326
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: aload_0        
        //   287: aload_1        
        //   288: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MethodParameterCountMismatch;.class
        //   290: ldc             "CIDRobjc_not_vararg_method"
        //   292: new             Ljava/lang/StringBuilder;
        //   295: dup            
        //   296: invokespecial   java/lang/StringBuilder.<init>:()V
        //   299: aload           4
        //   301: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: ldc             " is not a vararg method"
        //   311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   317: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   320: pop            
        //   321: return         
        //   322: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: aload_1        
        //   327: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   332: astore          5
        //   334: aload_1        
        //   335: aload_2        
        //   336: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getFilteredByStaticnessResponders:()Ljava/util/List;
        //   339: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.findTargetMethod:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Ljava/util/List;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   342: astore          6
        //   344: aload_2        
        //   345: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   348: ifnonnull       399
        //   351: aload_2        
        //   352: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getAllResponders:()Ljava/util/List;
        //   355: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   360: astore          7
        //   362: aload           7
        //   364: invokeinterface java/util/Iterator.hasNext:()Z
        //   369: ifeq            399
        //   372: aload           7
        //   374: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   379: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   382: astore          8
        //   384: aload_1        
        //   385: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   390: aload           8
        //   392: aload_1        
        //   393: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   396: goto            362
        //   399: aload           6
        //   401: ifnonnull       409
        //   404: return         
        //   405: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   408: athrow         
        //   409: aload_0        
        //   410: aload_1        
        //   411: aload_2        
        //   412: aload           6
        //   414: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //   417: aload_0        
        //   418: aload_1        
        //   419: aload           6
        //   421: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //   424: aload_1        
        //   425: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   430: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   433: ifne            806
        //   436: aload           5
        //   438: ldc             "dealloc"
        //   440: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   443: ifeq            611
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: aload_1        
        //   454: iconst_1       
        //   455: anewarray       Ljava/lang/Class;
        //   458: dup            
        //   459: iconst_0       
        //   460: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   462: aastore        
        //   463: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   466: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   469: astore          7
        //   471: aload           7
        //   473: ifnull          539
        //   476: aload           7
        //   478: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelector:()Ljava/lang/String;
        //   483: ldc             "dealloc"
        //   485: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   488: ifeq            539
        //   491: goto            498
        //   494: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: aload_3        
        //   499: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   502: ifeq            539
        //   505: goto            512
        //   508: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: aload_3        
        //   513: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   516: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   521: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SUPER:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   524: if_acmpne       539
        //   527: goto            534
        //   530: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   533: athrow         
        //   534: return         
        //   535: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: aload_0        
        //   540: aload_1        
        //   541: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$CallDealloc;.class
        //   543: ldc             "CIDR"
        //   545: ldc             "Sending 'dealloc' directly"
        //   547: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   550: astore          8
        //   552: aload_1        
        //   553: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentSelectors:()Ljava/util/List;
        //   558: iconst_0       
        //   559: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   564: checkcast       Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //   567: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentSelector.getTextOffset:()I
        //   572: istore          9
        //   574: ldc             "Send \"release\" message instead of \"dealloc\""
        //   576: astore          10
        //   578: aload_0        
        //   579: aload           8
        //   581: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   584: dup            
        //   585: aload_1        
        //   586: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   591: iload           9
        //   593: ldc             "dealloc"
        //   595: invokevirtual   java/lang/String.length:()I
        //   598: ldc             "release"
        //   600: aload           10
        //   602: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;)V
        //   605: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   608: goto            806
        //   611: aload           5
        //   613: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isReleaseSelector:(Ljava/lang/String;)Z
        //   616: ifeq            806
        //   619: aconst_null    
        //   620: astore          7
        //   622: aload_3        
        //   623: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   626: ifeq            643
        //   629: aload_3        
        //   630: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   633: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   638: astore          7
        //   640: goto            661
        //   643: aload_3        
        //   644: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   647: ifeq            661
        //   650: aload_3        
        //   651: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   654: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   659: astore          7
        //   661: aload           7
        //   663: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   666: ifeq            681
        //   669: aload           7
        //   671: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   674: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   679: astore          7
        //   681: aload           7
        //   683: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   686: ifeq            806
        //   689: aload           7
        //   691: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   694: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   699: ifne            806
        //   702: goto            709
        //   705: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   708: athrow         
        //   709: aload           7
        //   711: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   714: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   717: aload           7
        //   719: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   724: aload_1        
        //   725: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAttributeOfGroup:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   730: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   733: if_acmpne       806
        //   736: goto            743
        //   739: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   742: athrow         
        //   743: aload_0        
        //   744: aload_1        
        //   745: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ReleasingOfAssignProperties;.class
        //   747: ldc             "CIDR"
        //   749: ldc             "Releasing of the property with 'assign' attribute"
        //   751: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   754: astore          8
        //   756: aload_0        
        //   757: aload           8
        //   759: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   762: dup            
        //   763: aload           7
        //   765: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   768: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   771: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.RETAIN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   774: aconst_null    
        //   775: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   778: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   781: aload_0        
        //   782: aload           8
        //   784: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   787: dup            
        //   788: aload           7
        //   790: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   793: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   796: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   799: aconst_null    
        //   800: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   803: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   806: aload_0        
        //   807: aload_1        
        //   808: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentSelectors:()Ljava/util/List;
        //   813: aload           6
        //   815: ldc             "Calling"
        //   817: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.checkReadonlyAccess:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/lang/String;)Z
        //   820: pop            
        //   821: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     59     62     66     Ljava/lang/IllegalArgumentException;
        //  52     71     71     75     Ljava/lang/IllegalArgumentException;
        //  157    169    172    176    Ljava/lang/IllegalArgumentException;
        //  164    188    191    195    Ljava/lang/IllegalArgumentException;
        //  176    219    222    226    Ljava/lang/IllegalArgumentException;
        //  195    230    230    234    Ljava/lang/IllegalArgumentException;
        //  248    262    265    269    Ljava/lang/IllegalArgumentException;
        //  253    279    282    286    Ljava/lang/IllegalArgumentException;
        //  269    322    322    326    Ljava/lang/IllegalArgumentException;
        //  399    405    405    409    Ljava/lang/IllegalArgumentException;
        //  409    446    449    453    Ljava/lang/IllegalArgumentException;
        //  471    491    494    498    Ljava/lang/IllegalArgumentException;
        //  476    505    508    512    Ljava/lang/IllegalArgumentException;
        //  498    527    530    534    Ljava/lang/IllegalArgumentException;
        //  512    535    535    539    Ljava/lang/IllegalArgumentException;
        //  681    702    705    709    Ljava/lang/IllegalArgumentException;
        //  689    736    739    743    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0176:
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
    
    private void a(final OCSendMessageExpression p0, final OCMethodSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //     6: astore_3       
        //     7: new             Ljava/util/ArrayList;
        //    10: dup            
        //    11: invokespecial   java/util/ArrayList.<init>:()V
        //    14: astore          4
        //    16: aload_2        
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCType.isFunctionRequiringNil:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    20: istore          5
        //    22: iconst_0       
        //    23: istore          6
        //    25: aload_3        
        //    26: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    31: astore          7
        //    33: aload           7
        //    35: invokeinterface java/util/Iterator.hasNext:()Z
        //    40: ifeq            300
        //    43: aload           7
        //    45: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    50: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //    53: astore          8
        //    55: aload           8
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    62: astore          9
        //    64: aload           9
        //    66: ifnonnull       76
        //    69: goto            33
        //    72: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload           9
        //    78: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    83: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    86: astore          10
        //    88: aload           4
        //    90: aload           10
        //    92: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    97: pop            
        //    98: aload_2        
        //    99: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isVararg:()Z
        //   104: ifeq            223
        //   107: iload           6
        //   109: aload_2        
        //   110: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   115: invokeinterface java/util/List.size:()I
        //   120: if_icmplt       223
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload           10
        //   132: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   135: ifeq            163
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_0        
        //   146: aload           9
        //   148: ldc             "CIDR"
        //   150: ldc             "Invalid use of void expression"
        //   152: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   155: pop            
        //   156: goto            33
        //   159: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: iload           5
        //   165: ifeq            33
        //   168: aload_1        
        //   169: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   174: ldc             "Objects"
        //   176: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   179: ifeq            33
        //   182: aload           10
        //   184: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   187: ifne            33
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   194: aload           9
        //   196: aload           9
        //   198: aload           9
        //   200: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getProject:()Lcom/intellij/openapi/project/Project;
        //   205: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   208: aload           10
        //   210: aconst_null    
        //   211: aload           10
        //   213: iconst_1       
        //   214: ldc             "Parameter type mismatch: "
        //   216: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   219: pop            
        //   220: goto            33
        //   223: aload_2        
        //   224: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   229: iload           6
        //   231: iinc            6, 1
        //   234: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   239: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   242: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   247: astore          11
        //   249: aload           11
        //   251: ifnonnull       261
        //   254: goto            33
        //   257: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: aload           11
        //   263: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   266: aload_1        
        //   267: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   272: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   275: astore          12
        //   277: aload_0        
        //   278: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   281: aload           9
        //   283: aload           9
        //   285: aload           12
        //   287: aload           10
        //   289: aload           11
        //   291: ldc             "Parameter type mismatch: "
        //   293: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   296: pop            
        //   297: goto            33
        //   300: aload_0        
        //   301: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   304: aload_2        
        //   305: aload_1        
        //   306: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //   311: aload           4
        //   313: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFormatSpecifiers:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/List;Ljava/util/List;)V
        //   316: iload           5
        //   318: ifeq            444
        //   321: aload_3        
        //   322: invokeinterface java/util/List.size:()I
        //   327: ifle            444
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: aload_3        
        //   338: aload_3        
        //   339: invokeinterface java/util/List.size:()I
        //   344: iconst_1       
        //   345: isub           
        //   346: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   351: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   354: astore          7
        //   356: aload           7
        //   358: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   363: astore          8
        //   365: aload           8
        //   367: ifnull          384
        //   370: aload           8
        //   372: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   377: goto            385
        //   380: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: aconst_null    
        //   385: astore          9
        //   387: aload           9
        //   389: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isLikeNull:(Ljava/lang/String;)Z
        //   392: ifne            444
        //   395: aload_0        
        //   396: aload           7
        //   398: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$LastArgumentMustBeNull;.class
        //   400: ldc             "CIDR"
        //   402: ldc             "Last argument must be \"nil\""
        //   404: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   407: astore          10
        //   409: aload_0        
        //   410: aload           10
        //   412: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   415: dup            
        //   416: aload           7
        //   418: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   423: aload           7
        //   425: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   430: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   433: iconst_0       
        //   434: ldc             ", nil"
        //   436: ldc             "Append \"nil\" argument"
        //   438: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;)V
        //   441: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   444: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  64     72     72     76     Ljava/lang/IllegalArgumentException;
        //  88     123    126    130    Ljava/lang/IllegalArgumentException;
        //  107    138    141    145    Ljava/lang/IllegalArgumentException;
        //  130    159    159    163    Ljava/lang/IllegalArgumentException;
        //  249    257    257    261    Ljava/lang/IllegalArgumentException;
        //  300    330    333    337    Ljava/lang/IllegalArgumentException;
        //  365    380    380    384    Ljava/lang/IllegalArgumentException;
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
    
    private void a(final OCSendMessageExpression p0, final OCSendMessageExpression.ProbableResponders p1, final OCMethodSymbol p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     6: astore          4
        //     8: aload_1        
        //     9: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    14: astore          5
        //    16: aload           4
        //    18: aload_3        
        //    19: aload_1        
        //    20: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //    23: aload_2        
        //    24: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getReceiverType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    27: astore          6
        //    29: aload_2        
        //    30: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getReceiverOriginalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    33: astore          7
        //    35: aload           6
        //    37: ifnull          52
        //    40: aload           6
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aconst_null    
        //    53: astore          8
        //    55: aload           8
        //    57: ifnull          91
        //    60: aload           8
        //    62: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isPredeclaration:()Z
        //    67: ifne            91
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload           4
        //    79: aload           8
        //    81: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markImportNeeded:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_2        
        //    92: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    95: ifnull          103
        //    98: return         
        //    99: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload           8
        //   105: ifnull          191
        //   108: aload           8
        //   110: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isPredeclaration:()Z
        //   115: ifeq            191
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: new             Ljava/lang/StringBuilder;
        //   128: dup            
        //   129: invokespecial   java/lang/StringBuilder.<init>:()V
        //   132: aload           8
        //   134: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: ldc             " definition is not visible"
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   150: astore          9
        //   152: aload_0        
        //   153: aload           5
        //   155: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MemberVisibility;.class
        //   157: ldc             "CIDRobjc_definition_not_visible"
        //   159: aload           9
        //   161: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   164: astore          10
        //   166: aload_0        
        //   167: aload           10
        //   169: new             Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix;
        //   172: dup            
        //   173: aload           5
        //   175: aload           8
        //   177: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   182: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   185: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   188: goto            794
        //   191: aload           6
        //   193: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   196: ifne            226
        //   199: aload           7
        //   201: ifnull          306
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload           7
        //   213: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   216: ifeq            306
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload           4
        //   228: aload_3        
        //   229: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   234: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.isSymbolImported:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   237: ifne            794
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aload_0        
        //   248: aload_1        
        //   249: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MemberVisibility;.class
        //   251: ldc             "CIDRobjc_definition_not_visible"
        //   253: new             Ljava/lang/StringBuilder;
        //   256: dup            
        //   257: invokespecial   java/lang/StringBuilder.<init>:()V
        //   260: aload_3        
        //   261: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: ldc             " is not visible"
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   277: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   280: astore          9
        //   282: aload_0        
        //   283: aload           9
        //   285: new             Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix;
        //   288: dup            
        //   289: aload           5
        //   291: aload_3        
        //   292: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   297: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   300: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   303: goto            794
        //   306: aload           8
        //   308: ifnull          353
        //   311: aload           8
        //   313: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   318: aload_3        
        //   319: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   324: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   327: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   332: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   335: ifeq            353
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: iconst_1       
        //   346: goto            354
        //   349: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   352: athrow         
        //   353: iconst_0       
        //   354: istore          9
        //   356: aload           8
        //   358: ifnull          450
        //   361: iload           9
        //   363: ifne            450
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: aload_3        
        //   374: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   379: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   382: aload           8
        //   384: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSubclass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
        //   389: ifeq            450
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: new             Ljava/lang/StringBuilder;
        //   402: dup            
        //   403: invokespecial   java/lang/StringBuilder.<init>:()V
        //   406: aload_3        
        //   407: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   415: ldc             " is defined in subclass '"
        //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   420: aload_3        
        //   421: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   426: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   429: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getPresentableName:()Ljava/lang/String;
        //   434: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: ldc             "'"
        //   439: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   445: astore          10
        //   447: goto            498
        //   450: new             Ljava/lang/StringBuilder;
        //   453: dup            
        //   454: invokespecial   java/lang/StringBuilder.<init>:()V
        //   457: aload_3        
        //   458: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   463: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   466: ldc             " is defined in class '"
        //   468: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   471: aload_3        
        //   472: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   477: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   480: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getPresentableName:()Ljava/lang/String;
        //   485: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   488: ldc             "' and is not visible"
        //   490: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   493: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   496: astore          10
        //   498: aload           4
        //   500: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   503: ifeq            524
        //   506: aload_0        
        //   507: aload_1        
        //   508: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotInHierarchyMessage;.class
        //   510: ldc             "warn_inst_method_not_found"
        //   512: aload           10
        //   514: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   517: goto            535
        //   520: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   523: athrow         
        //   524: aload_0        
        //   525: aload_1        
        //   526: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotInHierarchyMessage;.class
        //   528: ldc             "warn_inst_method_not_found"
        //   530: aload           10
        //   532: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   535: astore          11
        //   537: aload_0        
        //   538: aload           11
        //   540: new             Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix;
        //   543: dup            
        //   544: aload           5
        //   546: aload_3        
        //   547: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   552: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   555: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   558: aload_1        
        //   559: iconst_1       
        //   560: anewarray       Ljava/lang/Class;
        //   563: dup            
        //   564: iconst_0       
        //   565: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   567: aastore        
        //   568: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   571: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   574: astore          12
        //   576: aload           12
        //   578: ifnull          595
        //   581: aload           12
        //   583: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   588: goto            596
        //   591: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: aconst_null    
        //   596: astore          13
        //   598: aload           8
        //   600: ifnull          734
        //   603: aload_0        
        //   604: aload           11
        //   606: new             Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4;
        //   609: dup            
        //   610: aload_0        
        //   611: aload           8
        //   613: aload_3        
        //   614: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //   617: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   620: aload           5
        //   622: ifnull          734
        //   625: goto            632
        //   628: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   631: athrow         
        //   632: iload           9
        //   634: ifne            734
        //   637: goto            644
        //   640: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   643: athrow         
        //   644: aload_3        
        //   645: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   650: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   653: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   658: aload           4
        //   660: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   663: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   666: astore          14
        //   668: aload_0        
        //   669: aload           11
        //   671: new             Lcom/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction;
        //   674: dup            
        //   675: aload           5
        //   677: aload           14
        //   679: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCInsertCastIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   682: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   685: aload_0        
        //   686: aload           11
        //   688: aload           5
        //   690: aload           14
        //   692: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   695: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   698: aload_0        
        //   699: aload           11
        //   701: new             Lcom/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction;
        //   704: dup            
        //   705: aload           5
        //   707: aload           14
        //   709: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   712: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   715: aload_0        
        //   716: aload           11
        //   718: new             Lcom/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction;
        //   721: dup            
        //   722: aload           5
        //   724: aload           14
        //   726: aload           6
        //   728: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   731: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   734: aload           8
        //   736: ifnull          794
        //   739: aload           13
        //   741: ifnull          794
        //   744: goto            751
        //   747: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   750: athrow         
        //   751: aload           8
        //   753: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   758: aload           13
        //   760: if_acmpne       794
        //   763: goto            770
        //   766: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   769: athrow         
        //   770: aload_0        
        //   771: aload           11
        //   773: new             Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker$5;
        //   776: dup            
        //   777: aload_0        
        //   778: aload           8
        //   780: aload_3        
        //   781: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$5.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //   784: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   787: goto            794
        //   790: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  35     48     48     52     Ljava/lang/IllegalArgumentException;
        //  55     70     73     77     Ljava/lang/IllegalArgumentException;
        //  60     84     87     91     Ljava/lang/IllegalArgumentException;
        //  91     99     99     103    Ljava/lang/IllegalArgumentException;
        //  103    118    121    125    Ljava/lang/IllegalArgumentException;
        //  191    204    207    211    Ljava/lang/IllegalArgumentException;
        //  199    219    222    226    Ljava/lang/IllegalArgumentException;
        //  211    240    243    247    Ljava/lang/IllegalArgumentException;
        //  306    338    341    345    Ljava/lang/IllegalArgumentException;
        //  311    349    349    353    Ljava/lang/IllegalArgumentException;
        //  356    366    369    373    Ljava/lang/IllegalArgumentException;
        //  361    392    395    399    Ljava/lang/IllegalArgumentException;
        //  498    520    520    524    Ljava/lang/IllegalArgumentException;
        //  576    591    591    595    Ljava/lang/IllegalArgumentException;
        //  598    625    628    632    Ljava/lang/IllegalArgumentException;
        //  603    637    640    644    Ljava/lang/IllegalArgumentException;
        //  734    744    747    751    Ljava/lang/IllegalArgumentException;
        //  739    763    766    770    Ljava/lang/IllegalArgumentException;
        //  751    787    790    794    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0211:
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
    
    public void checkForeach(final OCForeachStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getVariableExpression:()Lcom/jetbrains/cidr/lang/psi/OCElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //    11: ifeq            30
        //    14: aload_2        
        //    15: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //    18: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    23: goto            34
        //    26: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_2        
        //    31: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    34: astore_3       
        //    35: aload_1        
        //    36: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getCollectionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    41: astore          4
        //    43: aload_1        
        //    44: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getVariableDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    49: astore          5
        //    51: aload           5
        //    53: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //    56: ifeq            76
        //    59: aload           5
        //    61: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationStatement.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    69: goto            81
        //    72: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload           5
        //    78: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    81: astore          6
        //    83: aload_3        
        //    84: ifnull          207
        //    87: aload_0        
        //    88: aload_3        
        //    89: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    92: ifne            107
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: return         
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_3        
        //   108: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   113: astore          7
        //   115: aload_2        
        //   116: astore          8
        //   118: aload_3        
        //   119: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   122: astore          9
        //   124: aload_1        
        //   125: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.isCpp11Foreach:()Z
        //   130: ifne            468
        //   133: aload           7
        //   135: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   138: ifne            468
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload           7
        //   150: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   153: ifne            468
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: aload_3        
        //   165: ldc             "CIDR"
        //   167: new             Ljava/lang/StringBuilder;
        //   170: dup            
        //   171: invokespecial   java/lang/StringBuilder.<init>:()V
        //   174: ldc             "Expression has non-object type '"
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: aload           7
        //   181: aload_3        
        //   182: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: ldc             "'"
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   196: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   199: pop            
        //   200: goto            468
        //   203: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload           6
        //   209: ifnull          467
        //   212: aload           6
        //   214: astore          8
        //   216: aload           6
        //   218: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   223: invokeinterface java/util/List.size:()I
        //   228: iconst_1       
        //   229: if_icmpeq       309
        //   232: aload_0        
        //   233: aload           6
        //   235: ldc             "err_toomany_element_decls"
        //   237: ldc             "Only one variable declarator is allowed"
        //   239: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   242: astore          10
        //   244: aload           6
        //   246: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   251: invokeinterface java/util/List.size:()I
        //   256: iconst_1       
        //   257: isub           
        //   258: istore          11
        //   260: iload           11
        //   262: iconst_1       
        //   263: if_icmplt       308
        //   266: aload_0        
        //   267: aload           10
        //   269: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   272: dup            
        //   273: aload           6
        //   275: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   280: iload           11
        //   282: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   287: checkcast       Lcom/intellij/psi/PsiElement;
        //   290: ldc             "Remove extra declarators"
        //   292: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   295: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   298: iinc            11, -1
        //   301: goto            260
        //   304: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: return         
        //   309: aload           6
        //   311: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   316: iconst_0       
        //   317: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   322: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   325: astore          10
        //   327: aload           10
        //   329: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   334: astore          9
        //   336: aload           10
        //   338: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   343: astore          7
        //   345: aload           10
        //   347: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   352: ifnull          380
        //   355: aload_0        
        //   356: aload           10
        //   358: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   363: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //   365: ldc             "CIDR"
        //   367: ldc             "Initializer is not allowed here"
        //   369: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   372: pop            
        //   373: goto            464
        //   376: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: aload_1        
        //   381: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.isCpp11Foreach:()Z
        //   386: ifne            464
        //   389: aload           7
        //   391: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   394: ifne            464
        //   397: goto            404
        //   400: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: aload           7
        //   406: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   409: ifne            464
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: aload_0        
        //   420: aload           10
        //   422: ldc             "err_selector_element_type"
        //   424: new             Ljava/lang/StringBuilder;
        //   427: dup            
        //   428: invokespecial   java/lang/StringBuilder.<init>:()V
        //   431: ldc             "Variable has non-object type '"
        //   433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   436: aload           7
        //   438: aload_3        
        //   439: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: ldc             "'"
        //   447: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   450: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   453: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   456: pop            
        //   457: goto            464
        //   460: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: goto            468
        //   467: return         
        //   468: aload           4
        //   470: ifnonnull       478
        //   473: return         
        //   474: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: aload           4
        //   480: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   485: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   488: astore          10
        //   490: aload           10
        //   492: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   495: ifeq            503
        //   498: return         
        //   499: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload_1        
        //   504: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.isCpp11Foreach:()Z
        //   509: ifeq            596
        //   512: aload           4
        //   514: aload           10
        //   516: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getCollectionElementType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   519: astore          11
        //   521: aload           11
        //   523: ifnull          552
        //   526: aload_0        
        //   527: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   530: aconst_null    
        //   531: aload           8
        //   533: aload           7
        //   535: aload           11
        //   537: aload           9
        //   539: ldc             ""
        //   541: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   544: pop            
        //   545: goto            593
        //   548: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   551: athrow         
        //   552: aload_0        
        //   553: aload           4
        //   555: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   557: ldc             "err_for_range_invalid"
        //   559: new             Ljava/lang/StringBuilder;
        //   562: dup            
        //   563: invokespecial   java/lang/StringBuilder.<init>:()V
        //   566: ldc             "'"
        //   568: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   571: aload           10
        //   573: aload           4
        //   575: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   578: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   581: ldc             "' is not a valid range type"
        //   583: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   586: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   589: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   592: pop            
        //   593: goto            858
        //   596: aload           10
        //   598: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   601: ifeq            819
        //   604: aload           10
        //   606: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   609: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   612: astore          11
        //   614: aload           11
        //   616: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   619: ifne            627
        //   622: return         
        //   623: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   626: athrow         
        //   627: aload           11
        //   629: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   632: astore          12
        //   634: aload           12
        //   636: aload_1        
        //   637: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getCollectionItemType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   640: astore          13
        //   642: aload           13
        //   644: ifnull          673
        //   647: aload_0        
        //   648: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   651: aconst_null    
        //   652: aload           8
        //   654: aload           7
        //   656: aload           13
        //   658: aload           9
        //   660: ldc             ""
        //   662: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   665: pop            
        //   666: goto            673
        //   669: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   672: athrow         
        //   673: ldc             "countByEnumeratingWithState:objects:count:"
        //   675: astore          14
        //   677: aload           12
        //   679: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   682: ifeq            705
        //   685: aload           12
        //   687: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //   690: invokeinterface java/util/List.isEmpty:()Z
        //   695: ifne            816
        //   698: goto            705
        //   701: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   704: athrow         
        //   705: aload           12
        //   707: ldc             "countByEnumeratingWithState:objects:count:"
        //   709: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   711: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.findMember:(Ljava/lang/String;Ljava/lang/Class;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   714: ifnonnull       816
        //   717: goto            724
        //   720: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   723: athrow         
        //   724: new             Ljava/lang/StringBuilder;
        //   727: dup            
        //   728: invokespecial   java/lang/StringBuilder.<init>:()V
        //   731: ldc             "Collection expression of type '"
        //   733: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   736: aload           10
        //   738: aload           4
        //   740: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   743: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   746: ldc             "' doesn't respond to '"
        //   748: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   751: ldc             "countByEnumeratingWithState:objects:count:"
        //   753: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   756: ldc             "'"
        //   758: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   761: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   764: astore          15
        //   766: aload_0        
        //   767: aload           4
        //   769: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnresolvedCollectionMessage;.class
        //   771: ldc             "warn_collection_expr_type"
        //   773: aload           15
        //   775: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   778: astore          16
        //   780: aload           12
        //   782: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   785: ifnull          816
        //   788: aload_0        
        //   789: aload           16
        //   791: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction;
        //   794: dup            
        //   795: aload           12
        //   797: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   800: ldc             "NSFastEnumeration"
        //   802: iconst_0       
        //   803: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/lang/String;Z)V
        //   806: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   809: goto            816
        //   812: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   815: athrow         
        //   816: goto            858
        //   819: aload_0        
        //   820: aload           4
        //   822: ldc             "CIDR"
        //   824: new             Ljava/lang/StringBuilder;
        //   827: dup            
        //   828: invokespecial   java/lang/StringBuilder.<init>:()V
        //   831: ldc             "Expression has non-object type '"
        //   833: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   836: aload           10
        //   838: aload           4
        //   840: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   843: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   846: ldc             "'"
        //   848: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   851: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   854: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   857: pop            
        //   858: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      26     26     30     Ljava/lang/IllegalArgumentException;
        //  51     72     72     76     Ljava/lang/IllegalArgumentException;
        //  83     95     98     102    Ljava/lang/IllegalArgumentException;
        //  87     103    103    107    Ljava/lang/IllegalArgumentException;
        //  124    141    144    148    Ljava/lang/IllegalArgumentException;
        //  133    156    159    163    Ljava/lang/IllegalArgumentException;
        //  148    203    203    207    Ljava/lang/IllegalArgumentException;
        //  260    304    304    308    Ljava/lang/IllegalArgumentException;
        //  345    376    376    380    Ljava/lang/IllegalArgumentException;
        //  380    397    400    404    Ljava/lang/IllegalArgumentException;
        //  389    412    415    419    Ljava/lang/IllegalArgumentException;
        //  404    457    460    464    Ljava/lang/IllegalArgumentException;
        //  468    474    474    478    Ljava/lang/IllegalArgumentException;
        //  490    499    499    503    Ljava/lang/IllegalArgumentException;
        //  521    548    548    552    Ljava/lang/IllegalArgumentException;
        //  614    623    623    627    Ljava/lang/IllegalArgumentException;
        //  642    666    669    673    Ljava/lang/IllegalArgumentException;
        //  677    698    701    705    Ljava/lang/IllegalArgumentException;
        //  685    717    720    724    Ljava/lang/IllegalArgumentException;
        //  780    809    812    816    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0148:
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
    public static OCMethodSymbol findTargetMethod(final OCSendMessageExpression ocSendMessageExpression, final List<OCMethodSymbol> list) {
        final List<OCMessageArgument> arguments = ocSendMessageExpression.getArguments();
        OCMethodSymbol ocMethodSymbol = null;
        int n = 0;
    Label_0020:
        for (final OCMethodSymbol ocMethodSymbol2 : list) {
            int n2 = 0;
            int n3 = 0;
            final Iterator<OCMessageArgument> iterator2 = arguments.iterator();
            while (iterator2.hasNext()) {
                final OCExpression argumentExpression = iterator2.next().getArgumentExpression();
                try {
                    if (argumentExpression == null) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                final OCType guessedType = argumentExpression.getResolvedType().getGuessedType();
                final List<OCMethodSymbol.SelectorPartSymbol> selectors = ocMethodSymbol2.getSelectors();
                Label_0156: {
                    Label_0149: {
                        try {
                            if (!ocMethodSymbol2.isVararg()) {
                                break Label_0156;
                            }
                            final int n4 = n2;
                            final List<OCMethodSymbol.SelectorPartSymbol> list2 = selectors;
                            final int n5 = list2.size();
                            if (n4 >= n5) {
                                break Label_0149;
                            }
                            break Label_0156;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final int n4 = n2;
                            final List<OCMethodSymbol.SelectorPartSymbol> list2 = selectors;
                            final int n5 = list2.size();
                            if (n4 >= n5) {
                                continue;
                            }
                            break Label_0156;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    try {
                        if (selectors.size() != arguments.size()) {
                            continue Label_0020;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                final OCDeclaratorSymbol parameter = selectors.get(n2).getParameter();
                try {
                    if (parameter == null) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                final OCType.TypeCheckState state = parameter.getType().resolve(ocSendMessageExpression.getContainingFile()).checkCompatible(guessedType, argumentExpression, (PsiElement)argumentExpression).getState();
                Label_0282: {
                    try {
                        if (state.isError((PsiElement)ocSendMessageExpression)) {
                            n3 += 2000;
                            break Label_0282;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    try {
                        if (state != OCType.TypeCheckState.OK) {
                            ++n3;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                ++n2;
            }
            try {
                if (!OCFileSymbols.isSymbolImported(ocSendMessageExpression.getContainingOCFile(), ocMethodSymbol2.getParent())) {
                    n3 += 1000;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            Label_0337: {
                try {
                    if (ocMethodSymbol == null) {
                        break Label_0337;
                    }
                    final int n6 = n3;
                    final int n7 = -1;
                    if (n6 != n7) {
                        break Label_0337;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
                try {
                    final int n6 = n3;
                    final int n7 = -1;
                    if (n6 == n7) {
                        continue;
                    }
                    if (n3 >= n) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
            }
            n = n3;
            ocMethodSymbol = ocMethodSymbol2;
        }
        return ocMethodSymbol;
    }
    
    public void checkQualifiedExpression(final OCQualifiedExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    11: astore_2       
        //    12: aload_2        
        //    13: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    16: ifeq            24
        //    19: return         
        //    20: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_2        
        //    25: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    28: ifeq            39
        //    31: aload_2        
        //    32: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    38: astore_2       
        //    39: aload_1        
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    45: astore_3       
        //    46: aload_1        
        //    47: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    52: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    55: if_acmpne       267
        //    58: new             Lcom/intellij/openapi/util/Ref;
        //    61: dup            
        //    62: iconst_0       
        //    63: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    66: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //    69: astore          4
        //    71: aload_1        
        //    72: aload           4
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifierContainerType:(Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    79: astore          5
        //    81: aload           5
        //    83: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    86: if_acmpne       206
        //    89: aload           4
        //    91: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    94: checkcast       Ljava/lang/Boolean;
        //    97: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   100: ifeq            206
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: ldc             "Applying '->' operator to '"
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: aload_2        
        //   123: aload_1        
        //   124: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: ldc             "' instead of a pointer"
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   138: astore          6
        //   140: aload_0        
        //   141: aload_1        
        //   142: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   147: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PointerTypeRequired;.class
        //   149: ldc             "err_typecheck_member_reference_arrow"
        //   151: aload           6
        //   153: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   156: astore          7
        //   158: aload_2        
        //   159: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   162: ifne            179
        //   165: aload_2        
        //   166: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   169: ifeq            205
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload_0        
        //   180: aload           7
        //   182: aload_1        
        //   183: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   188: aload_2        
        //   189: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   192: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   195: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: return         
        //   206: new             Lcom/intellij/openapi/util/Ref;
        //   209: dup            
        //   210: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   213: astore          6
        //   215: aload_1        
        //   216: aload_1        
        //   217: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getSymbolName:()Ljava/lang/String;
        //   222: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
        //   225: dup            
        //   226: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:()V
        //   229: iconst_1       
        //   230: aconst_null    
        //   231: iconst_0       
        //   232: iconst_0       
        //   233: aload           6
        //   235: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.processTargets:(Ljava/lang/String;Lcom/intellij/util/Processor;ZLcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;ZZLcom/intellij/openapi/util/Ref;)Z
        //   240: pop            
        //   241: aload           6
        //   243: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
        //   246: ifeq            258
        //   249: aload           5
        //   251: goto            266
        //   254: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: aload           6
        //   260: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   263: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   266: astore_2       
        //   267: aload_2        
        //   268: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   271: ifne            395
        //   274: aload_2        
        //   275: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   278: ifne            395
        //   281: goto            288
        //   284: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: aload_2        
        //   289: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   292: ifne            395
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload_2        
        //   303: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   306: ifne            395
        //   309: goto            316
        //   312: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: aload_1        
        //   317: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   322: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   325: if_acmpne       349
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: aload_2        
        //   336: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   339: ifne            395
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: aload_0        
        //   350: aload_1        
        //   351: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   356: ldc             "err_typecheck_member_reference_struct_union"
        //   358: new             Ljava/lang/StringBuilder;
        //   361: dup            
        //   362: invokespecial   java/lang/StringBuilder.<init>:()V
        //   365: ldc             "Structure type required instead of '"
        //   367: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   370: aload_2        
        //   371: aload_1        
        //   372: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   375: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: ldc             "'"
        //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   383: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   386: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   389: pop            
        //   390: return         
        //   391: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //   398: dup            
        //   399: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //   402: astore          4
        //   404: aload_1        
        //   405: aload_1        
        //   406: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getSymbolName:()Ljava/lang/String;
        //   411: aload           4
        //   413: iconst_0       
        //   414: aconst_null    
        //   415: iconst_1       
        //   416: iconst_0       
        //   417: aconst_null    
        //   418: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.processTargets:(Ljava/lang/String;Lcom/intellij/util/Processor;ZLcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;ZZLcom/intellij/openapi/util/Ref;)Z
        //   423: pop            
        //   424: aload           4
        //   426: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   429: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   432: astore          5
        //   434: aload           5
        //   436: ifnonnull       762
        //   439: aload_1        
        //   440: aload_1        
        //   441: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getSymbolName:()Ljava/lang/String;
        //   446: aload           4
        //   448: iconst_1       
        //   449: aconst_null    
        //   450: iconst_1       
        //   451: iconst_0       
        //   452: aconst_null    
        //   453: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.processTargets:(Ljava/lang/String;Lcom/intellij/util/Processor;ZLcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;ZZLcom/intellij/openapi/util/Ref;)Z
        //   458: pop            
        //   459: aload           4
        //   461: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   464: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   467: astore          5
        //   469: aload           5
        //   471: ifnull          781
        //   474: aload_3        
        //   475: aload           5
        //   477: aload_1        
        //   478: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   481: aload           5
        //   483: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   486: ifeq            524
        //   489: goto            496
        //   492: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   495: athrow         
        //   496: aload           5
        //   498: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   501: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.isStatic:()Z
        //   506: ifeq            524
        //   509: goto            516
        //   512: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   515: athrow         
        //   516: iconst_1       
        //   517: goto            525
        //   520: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   523: athrow         
        //   524: iconst_0       
        //   525: istore          6
        //   527: iload           6
        //   529: ifeq            569
        //   532: new             Ljava/lang/StringBuilder;
        //   535: dup            
        //   536: invokespecial   java/lang/StringBuilder.<init>:()V
        //   539: ldc             "Class "
        //   541: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   544: aload           5
        //   546: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   551: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   554: ldc             " is accessed from the instance context"
        //   556: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   562: goto            599
        //   565: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   568: athrow         
        //   569: new             Ljava/lang/StringBuilder;
        //   572: dup            
        //   573: invokespecial   java/lang/StringBuilder.<init>:()V
        //   576: ldc             "Instance "
        //   578: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   581: aload           5
        //   583: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   588: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   591: ldc             " is accessed from the class context"
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   599: astore          7
        //   601: aload_0        
        //   602: aload_1        
        //   603: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$StaticnessMismatch;.class
        //   605: ldc             "CIDR"
        //   607: aload           7
        //   609: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   612: astore          8
        //   614: aload_1        
        //   615: iconst_1       
        //   616: anewarray       Ljava/lang/Class;
        //   619: dup            
        //   620: iconst_0       
        //   621: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   623: aastore        
        //   624: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   627: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   630: astore          9
        //   632: aload_1        
        //   633: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   638: astore          10
        //   640: aload           5
        //   642: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   645: ifeq            687
        //   648: aload_0        
        //   649: aload           8
        //   651: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction;
        //   654: dup            
        //   655: aload           5
        //   657: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   660: iload           6
        //   662: ifne            680
        //   665: goto            672
        //   668: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   671: athrow         
        //   672: iconst_1       
        //   673: goto            681
        //   676: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   679: athrow         
        //   680: iconst_0       
        //   681: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Z)V
        //   684: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   687: aload           9
        //   689: ifnull          759
        //   692: aload           10
        //   694: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   697: ifeq            759
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: aload           10
        //   709: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   712: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   717: ifnull          759
        //   720: goto            727
        //   723: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   726: athrow         
        //   727: aload_0        
        //   728: aload           8
        //   730: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction;
        //   733: dup            
        //   734: aload           9
        //   736: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   741: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   744: iload           6
        //   746: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Z)V
        //   749: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   752: goto            759
        //   755: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   758: athrow         
        //   759: goto            781
        //   762: aload_3        
        //   763: aload           5
        //   765: aload_1        
        //   766: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   769: aload_0        
        //   770: getfield        com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   773: aload           5
        //   775: aload_1        
        //   776: aload_2        
        //   777: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFieldVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   780: pop            
        //   781: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     20     20     24     Ljava/lang/IllegalArgumentException;
        //  81     103    106    110    Ljava/lang/IllegalArgumentException;
        //  158    172    175    179    Ljava/lang/IllegalArgumentException;
        //  165    198    201    205    Ljava/lang/IllegalArgumentException;
        //  215    254    254    258    Ljava/lang/IllegalArgumentException;
        //  267    281    284    288    Ljava/lang/IllegalArgumentException;
        //  274    295    298    302    Ljava/lang/IllegalArgumentException;
        //  288    309    312    316    Ljava/lang/IllegalArgumentException;
        //  302    328    331    335    Ljava/lang/IllegalArgumentException;
        //  316    342    345    349    Ljava/lang/IllegalArgumentException;
        //  335    391    391    395    Ljava/lang/IllegalArgumentException;
        //  469    489    492    496    Ljava/lang/IllegalArgumentException;
        //  474    509    512    516    Ljava/lang/IllegalArgumentException;
        //  496    520    520    524    Ljava/lang/IllegalArgumentException;
        //  527    565    565    569    Ljava/lang/IllegalArgumentException;
        //  640    665    668    672    Ljava/lang/IllegalArgumentException;
        //  648    676    676    680    Ljava/lang/IllegalArgumentException;
        //  687    700    703    707    Ljava/lang/IllegalArgumentException;
        //  692    720    723    727    Ljava/lang/IllegalArgumentException;
        //  707    752    755    759    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0288:
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
    
    public void checkReturnStatement(final OCReturnStatement ocReturnStatement) {
        final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType((PsiElement)ocReturnStatement, new Class[] { OCCallable.class });
        final OCExpression expression = ocReturnStatement.getExpression();
        String kindLowercase = "function/method/block";
        try {
            if (ocCallable == null) {
                this.addErrorAnnotation((PsiElement)ocReturnStatement, OCInspections.ConstructionIsNotAllowed.class, "CIDRillegal_return", "Return statement is outside of a " + kindLowercase + " declaration");
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCExpressionSymbol ocExpressionSymbol = ocCallable.getSymbol();
        final OCType returnType = ocCallable.getReturnType();
        OCType ocType = returnType.resolve(ocReturnStatement.getContainingFile());
        if (ocExpressionSymbol != null) {
            kindLowercase = ocExpressionSymbol.getKindLowercase();
            if (ocExpressionSymbol.getKind().isConstructorOrDestructor()) {
                ocType = OCVoidType.instance();
            }
        }
        Label_0238: {
            try {
                if (expression != null) {
                    break Label_0238;
                }
                if (ocType instanceof OCVoidType) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)ocReturnStatement, OCInspections.IncompatibleTypes.class, "ext_return_missing_expr", "Returning 'void' from a " + kindLowercase + " returning '" + ocType.getName((PsiElement)ocReturnStatement) + "'");
            try {
                if (ocExpressionSymbol != null) {
                    this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCChangeTypeIntentionAction(ocExpressionSymbol, OCVoidType.instance(), true));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            return;
        }
        final OCType guessedType = expression.getResolvedType().getGuessedType();
        Label_0462: {
            Label_0297: {
                Label_0272: {
                    try {
                        if (ocType instanceof OCUnknownType) {
                            break Label_0272;
                        }
                        final OCType ocType2 = ocType;
                        final boolean b = ocType2 instanceof OCAutoType;
                        if (b) {
                            break Label_0272;
                        }
                        break Label_0462;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    try {
                        final OCType ocType2 = ocType;
                        final boolean b = ocType2 instanceof OCAutoType;
                        if (!b) {
                            break Label_0462;
                        }
                        if (!(returnType instanceof OCAutoType)) {
                            break Label_0297;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                }
                ocExpressionSymbol = ((OCAutoType)returnType).getExpressionSymbol();
            }
            if (ocExpressionSymbol instanceof OCLambdaExpressionSymbol) {
                final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)ocReturnStatement);
                final OCExpressionSymbol ocExpressionSymbol2 = (OCExpressionSymbol)ContainerUtil.getFirstItem((List)((OCLambdaExpressionSymbol)ocExpressionSymbol).getReturnExpressions());
                OCType resolvedType = null;
                Label_0351: {
                    try {
                        if (ocExpressionSymbol2 != null) {
                            resolvedType = ocExpressionSymbol2.getResolvedType(ocResolveContext);
                            break Label_0351;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    resolvedType = null;
                }
                final OCType ocType3 = resolvedType;
                Label_0375: {
                    try {
                        if (ocType3 == null) {
                            return;
                        }
                        final OCType ocType4 = ocType3;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b2 = ocType4.isMagicInside(ocResolveContext2);
                        if (!b2) {
                            break Label_0375;
                        }
                        return;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                    try {
                        final OCType ocType4 = ocType3;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b2 = ocType4.isMagicInside(ocResolveContext2);
                        if (b2) {
                            return;
                        }
                        if (new OCTypeEqualityVisitor(guessedType, true, false, false, false, false, true, false, ocResolveContext).equal(ocType3)) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw b(ex8);
                    }
                }
                this.addErrorAnnotation((PsiElement)ocReturnStatement, OCInspections.IncompatibleTypes.class, "err_auto_fn_different_deductions", "'auto' return type was previously deduced as '" + ocType3.getName((PsiElement)ocReturnStatement) + "' but here as '" + guessedType.getName((PsiElement)ocReturnStatement) + "'");
                return;
            }
        }
        final String string = "Returning '" + guessedType.getName((PsiElement)ocReturnStatement) + "' from a " + kindLowercase + " returning '" + ocType.getName((PsiElement)ocReturnStatement) + "'";
        Label_0589: {
            Label_0540: {
                try {
                    if (!(ocType instanceof OCVoidType)) {
                        break Label_0589;
                    }
                    final OCType ocType5 = guessedType;
                    final boolean b3 = ocType5 instanceof OCVoidType;
                    if (!b3) {
                        break Label_0540;
                    }
                    break Label_0589;
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
                try {
                    final OCType ocType5 = guessedType;
                    final boolean b3 = ocType5 instanceof OCVoidType;
                    if (b3) {
                        break Label_0589;
                    }
                    if (guessedType instanceof OCMagicType) {
                        break Label_0589;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
            }
            this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocReturnStatement, OCInspections.IncompatibleTypes.class, "ext_return_has_expr", string), (IntentionAction)new OCChangeTypeIntentionAction(ocExpressionSymbol, guessedType, true));
            return;
        }
        this.myCppChecker.checkAssignment(expression, (PsiElement)ocReturnStatement, ocType, guessedType, ocExpressionSymbol, string + ": ");
    }
    
    public void checkSwitchStatement(final OCSwitchStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //     6: astore_2       
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    13: astore_3       
        //    14: new             Ljava/util/ArrayList;
        //    17: dup            
        //    18: invokespecial   java/util/ArrayList.<init>:()V
        //    21: astore          4
        //    23: iconst_1       
        //    24: istore          6
        //    26: iconst_0       
        //    27: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    30: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //    33: astore          7
        //    35: aload_2        
        //    36: ifnull          52
        //    39: aload_2        
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aconst_null    
        //    53: astore          8
        //    55: aload           8
        //    57: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    60: ifeq            73
        //    63: aload           8
        //    65: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    68: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    71: astore          8
        //    73: aload           8
        //    75: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    78: ifeq            107
        //    81: aload           8
        //    83: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    86: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //    89: ifeq            107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: istore          9
        //   110: aload_3        
        //   111: ifnull          380
        //   114: new             Ljava/util/ArrayList;
        //   117: dup            
        //   118: invokespecial   java/util/ArrayList.<init>:()V
        //   121: astore          10
        //   123: aload_3        
        //   124: aload           4
        //   126: aload           10
        //   128: invokestatic    com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.findCaseStatements:(Lcom/jetbrains/cidr/lang/psi/OCStatement;Ljava/util/List;Ljava/util/List;)Z
        //   131: istore          6
        //   133: aload_1        
        //   134: aload           4
        //   136: aload           7
        //   138: invokestatic    com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.getMissingCases:(Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;Ljava/util/List;Lcom/intellij/openapi/util/Ref;)Ljava/util/List;
        //   141: astore          5
        //   143: iconst_0       
        //   144: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   147: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   150: astore          11
        //   152: aload_3        
        //   153: new             Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker$6;
        //   156: dup            
        //   157: aload_0        
        //   158: aload           11
        //   160: aload           5
        //   162: iload           9
        //   164: invokespecial   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$6.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker;Lcom/intellij/openapi/util/Ref;Ljava/util/List;Z)V
        //   167: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   172: iconst_0       
        //   173: istore          12
        //   175: iload           12
        //   177: aload           4
        //   179: invokeinterface java/util/List.size:()I
        //   184: if_icmpge       377
        //   187: aload           4
        //   189: iload           12
        //   191: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   196: checkcast       Lcom/intellij/openapi/util/Pair;
        //   199: astore          13
        //   201: aload           10
        //   203: iload           12
        //   205: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   210: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCaseStatement;
        //   213: astore          14
        //   215: aload           4
        //   217: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   222: astore          15
        //   224: aload           15
        //   226: invokeinterface java/util/Iterator.hasNext:()Z
        //   231: ifeq            371
        //   234: aload           15
        //   236: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   241: checkcast       Lcom/intellij/openapi/util/Pair;
        //   244: astore          16
        //   246: aload           13
        //   248: aload           16
        //   250: if_acmpeq       368
        //   253: aload           13
        //   255: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   258: checkcast       Ljava/lang/Integer;
        //   261: invokevirtual   java/lang/Integer.intValue:()I
        //   264: aload           16
        //   266: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   269: checkcast       Ljava/lang/Integer;
        //   272: invokevirtual   java/lang/Integer.intValue:()I
        //   275: invokestatic    java/lang/Math.max:(II)I
        //   278: aload           13
        //   280: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   283: checkcast       Ljava/lang/Integer;
        //   286: invokevirtual   java/lang/Integer.intValue:()I
        //   289: aload           16
        //   291: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   294: checkcast       Ljava/lang/Integer;
        //   297: invokevirtual   java/lang/Integer.intValue:()I
        //   300: invokestatic    java/lang/Math.min:(II)I
        //   303: if_icmpgt       368
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: aload           13
        //   315: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   318: checkcast       Ljava/lang/Integer;
        //   321: aload           13
        //   323: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   326: invokevirtual   java/lang/Integer.equals:(Ljava/lang/Object;)Z
        //   329: ifeq            348
        //   332: goto            339
        //   335: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   338: athrow         
        //   339: ldc             "Duplicate case value"
        //   341: goto            350
        //   344: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: ldc             "Overlapping case range"
        //   350: astore          17
        //   352: aload_0        
        //   353: aload           14
        //   355: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateSwitchCase;.class
        //   357: ldc             "err_duplicate_case"
        //   359: aload           17
        //   361: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   364: pop            
        //   365: goto            371
        //   368: goto            224
        //   371: iinc            12, 1
        //   374: goto            175
        //   377: goto            385
        //   380: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   383: astore          5
        //   385: aload_2        
        //   386: ifnull          670
        //   389: aload           8
        //   391: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   394: ifne            531
        //   397: goto            404
        //   400: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: aload           8
        //   406: aload_2        
        //   407: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   410: ifne            531
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: aload           8
        //   422: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   425: ifeq            453
        //   428: goto            435
        //   431: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   434: athrow         
        //   435: aload           8
        //   437: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   440: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //   443: ifne            531
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: new             Ljava/lang/StringBuilder;
        //   456: dup            
        //   457: invokespecial   java/lang/StringBuilder.<init>:()V
        //   460: ldc             "Integer expression is required in switch instead of '"
        //   462: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   465: aload           8
        //   467: aload_1        
        //   468: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: ldc             "'"
        //   476: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   479: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   482: astore          10
        //   484: aload_0        
        //   485: aload_1        
        //   486: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IntegerTypeRequired;.class
        //   488: ldc             "err_typecheck_statement_requires_integer"
        //   490: aload           10
        //   492: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   495: astore          11
        //   497: aload_2        
        //   498: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   503: astore          12
        //   505: aload           12
        //   507: ifnull          531
        //   510: aload_0        
        //   511: aload           11
        //   513: aload           12
        //   515: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   518: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   521: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   524: goto            531
        //   527: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   530: athrow         
        //   531: iload           6
        //   533: ifne            670
        //   536: aload           8
        //   538: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   541: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   544: ifne            623
        //   547: goto            554
        //   550: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   553: athrow         
        //   554: aload           7
        //   556: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   559: checkcast       Ljava/lang/Boolean;
        //   562: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   565: ifne            623
        //   568: goto            575
        //   571: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   574: athrow         
        //   575: aload           8
        //   577: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   580: ifne            623
        //   583: goto            590
        //   586: invokestatic    com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   589: athrow         
        //   590: aload_0        
        //   591: aload_1        
        //   592: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getSwitchToken:()Lcom/intellij/psi/PsiElement;
        //   597: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MissingSwitchCase;.class
        //   599: ldc             "CIDRmissing_default_case"
        //   601: ldc             "Default case is not handled"
        //   603: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   606: astore          10
        //   608: aload_0        
        //   609: aload           10
        //   611: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix;
        //   614: dup            
        //   615: aload_1        
        //   616: iconst_1       
        //   617: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.<init>:(Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;Z)V
        //   620: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   623: aload           5
        //   625: invokeinterface java/util/List.isEmpty:()Z
        //   630: ifne            670
        //   633: aload_0        
        //   634: aload_1        
        //   635: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getSwitchToken:()Lcom/intellij/psi/PsiElement;
        //   640: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MissingSwitchCase;.class
        //   642: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   645: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getMissingCase:()Ljava/lang/String;
        //   648: ldc             "Not all switch cases were handled"
        //   650: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   653: astore          10
        //   655: aload_0        
        //   656: aload           10
        //   658: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix;
        //   661: dup            
        //   662: aload_1        
        //   663: iconst_0       
        //   664: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.<init>:(Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;Z)V
        //   667: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   670: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  35     48     48     52     Ljava/lang/IllegalArgumentException;
        //  73     92     95     99     Ljava/lang/IllegalArgumentException;
        //  81     103    103    107    Ljava/lang/IllegalArgumentException;
        //  246    306    309    313    Ljava/lang/IllegalArgumentException;
        //  253    332    335    339    Ljava/lang/IllegalArgumentException;
        //  313    344    344    348    Ljava/lang/IllegalArgumentException;
        //  385    397    400    404    Ljava/lang/IllegalArgumentException;
        //  389    413    416    420    Ljava/lang/IllegalArgumentException;
        //  404    428    431    435    Ljava/lang/IllegalArgumentException;
        //  420    446    449    453    Ljava/lang/IllegalArgumentException;
        //  505    524    527    531    Ljava/lang/IllegalArgumentException;
        //  531    547    550    554    Ljava/lang/IllegalArgumentException;
        //  536    568    571    575    Ljava/lang/IllegalArgumentException;
        //  554    583    586    590    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0313:
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
