// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.annotation.Annotation;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;

public abstract class OCArgumentsChecker
{
    public void checkCompoundInitializer(final OCCompoundInitializer p0, final OCType p1, final boolean p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializers:()Ljava/util/List;
        //     6: astore          5
        //     8: aload_1        
        //     9: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    14: astore          6
        //    16: aload_2        
        //    17: astore          7
        //    19: aload_2        
        //    20: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    23: ifeq            34
        //    26: aload_2        
        //    27: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    30: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    33: astore_2       
        //    34: aload_2        
        //    35: aload           6
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInitializerListType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiFile;)Z
        //    40: ifeq            57
        //    43: aload_0        
        //    44: aload_1        
        //    45: aload_2        
        //    46: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    49: invokespecial   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCStructType;)V
        //    52: return         
        //    53: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_2        
        //    58: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    61: ifeq            170
        //    64: aload_2        
        //    65: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    68: ifeq            170
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_1        
        //    79: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    84: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    87: astore          8
        //    89: aload_1        
        //    90: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    93: astore          9
        //    95: new             Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //    98: dup            
        //    99: aload           8
        //   101: aload           9
        //   103: invokespecial   com/jetbrains/cidr/lang/resolve/OCArgumentsList.<init>:(Ljava/util/List;Ljava/util/List;)V
        //   106: astore          10
        //   108: aload_2        
        //   109: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   112: aload           10
        //   114: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   117: dup            
        //   118: aload           6
        //   120: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   123: iload           4
        //   125: iconst_0       
        //   126: aconst_null    
        //   127: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.findConstructor:(Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZLcom/intellij/util/Producer;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   130: astore          11
        //   132: aload           11
        //   134: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   137: ifne            158
        //   140: aload_2        
        //   141: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   144: iconst_0       
        //   145: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPOD:(Z)Z
        //   148: ifne            170
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_0        
        //   159: aload_1        
        //   160: aload           11
        //   162: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.checkConstructor:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   165: return         
        //   166: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_2        
        //   171: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   174: ifeq            394
        //   177: aload_2        
        //   178: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   181: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   184: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   187: if_acmpne       394
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aconst_null    
        //   198: astore          8
        //   200: aload_2        
        //   201: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   204: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   207: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getMembersList:()Ljava/util/List;
        //   210: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   215: astore          9
        //   217: aload           9
        //   219: invokeinterface java/util/Iterator.hasNext:()Z
        //   224: ifeq            260
        //   227: aload           9
        //   229: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   234: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   237: astore          10
        //   239: aload           10
        //   241: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   244: ifeq            257
        //   247: aload           10
        //   249: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   252: astore          8
        //   254: goto            260
        //   257: goto            217
        //   260: aload           8
        //   262: ifnull          357
        //   265: aload           8
        //   267: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   270: aload_1        
        //   271: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   276: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   279: dup            
        //   280: astore_2       
        //   281: astore          7
        //   283: aload_1        
        //   284: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializerExpressions:()Ljava/util/List;
        //   289: invokeinterface java/util/List.size:()I
        //   294: iconst_1       
        //   295: if_icmpne       391
        //   298: aload_1        
        //   299: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializerExpressions:()Ljava/util/List;
        //   304: iconst_0       
        //   305: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   310: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   313: astore          9
        //   315: aload           9
        //   317: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   320: ifeq            332
        //   323: aload           9
        //   325: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   328: astore_1       
        //   329: goto            354
        //   332: aload           7
        //   334: aload           9
        //   336: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   341: aload           9
        //   343: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   346: ifeq            354
        //   349: return         
        //   350: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: goto            391
        //   357: aload           5
        //   359: invokeinterface java/util/List.size:()I
        //   364: ifle            390
        //   367: aload_0        
        //   368: aload_1        
        //   369: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //   371: ldc             "err_excess_initializers"
        //   373: ldc             "Excess elements in scalar initializer"
        //   375: iconst_0       
        //   376: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   379: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   382: pop            
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: return         
        //   391: goto            170
        //   394: aload_2        
        //   395: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   398: ifeq            421
        //   401: aload_2        
        //   402: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   405: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   408: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   411: if_acmpeq       435
        //   414: goto            421
        //   417: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   420: athrow         
        //   421: aload_2        
        //   422: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   425: ifeq            930
        //   428: goto            435
        //   431: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   434: athrow         
        //   435: iconst_0       
        //   436: istore          8
        //   438: iconst_0       
        //   439: istore          9
        //   441: new             Ljava/util/HashMap;
        //   444: dup            
        //   445: invokespecial   java/util/HashMap.<init>:()V
        //   448: astore          10
        //   450: aload           10
        //   452: aload_1        
        //   453: new             Lcom/intellij/openapi/util/Pair;
        //   456: dup            
        //   457: aload_2        
        //   458: aconst_null    
        //   459: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   462: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   465: pop            
        //   466: aload_1        
        //   467: aload           10
        //   469: new             Ljava/util/HashSet;
        //   472: dup            
        //   473: invokespecial   java/util/HashSet.<init>:()V
        //   476: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.inferChildTypes:(Ljava/util/Map;Ljava/util/Set;)V
        //   481: aload           5
        //   483: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   488: astore          11
        //   490: aload           11
        //   492: invokeinterface java/util/Iterator.hasNext:()Z
        //   497: ifeq            927
        //   500: aload           11
        //   502: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   507: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializerMember;
        //   510: astore          12
        //   512: aload           12
        //   514: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   517: ifeq            817
        //   520: aload           10
        //   522: aload           12
        //   524: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   527: checkcast       Lcom/intellij/openapi/util/Pair;
        //   530: astore          13
        //   532: aload           13
        //   534: ifnull          552
        //   537: aload           13
        //   539: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   542: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   545: goto            553
        //   548: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   551: athrow         
        //   552: aconst_null    
        //   553: astore          14
        //   555: aload           14
        //   557: ifnull          731
        //   560: aload           14
        //   562: aload           6
        //   564: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   567: astore          14
        //   569: aload           12
        //   571: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   574: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   579: astore          15
        //   581: aload           12
        //   583: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   586: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   589: astore          16
        //   591: aload_2        
        //   592: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //   595: ifeq            661
        //   598: aload           14
        //   600: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   603: ifeq            661
        //   606: goto            613
        //   609: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: aload           16
        //   615: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   618: ifeq            661
        //   621: goto            628
        //   624: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   627: athrow         
        //   628: aload           15
        //   630: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //   633: ifeq            661
        //   636: goto            643
        //   639: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aload_0        
        //   644: aload           14
        //   646: aload           16
        //   648: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   651: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.checkCharArrayInit:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;)V
        //   654: goto            661
        //   657: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   660: athrow         
        //   661: aload           12
        //   663: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   666: ifeq            700
        //   669: iload_3        
        //   670: ifeq            700
        //   673: goto            680
        //   676: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   679: athrow         
        //   680: aload_0        
        //   681: aload           12
        //   683: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   686: aload           14
        //   688: iconst_1       
        //   689: iconst_1       
        //   690: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.checkCompoundInitializer:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCType;ZZ)V
        //   693: goto            728
        //   696: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   699: athrow         
        //   700: aload_0        
        //   701: aload           12
        //   703: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   706: aload           12
        //   708: aload           14
        //   710: aload           15
        //   712: aload           13
        //   714: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   717: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   720: aload           15
        //   722: iconst_1       
        //   723: ldc             "Incompatible types in initializer: "
        //   725: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)V
        //   728: goto            814
        //   731: iload           9
        //   733: ifne            814
        //   736: iconst_1       
        //   737: istore          9
        //   739: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction;
        //   742: dup            
        //   743: aload           5
        //   745: iload           8
        //   747: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.<init>:(Ljava/util/List;I)V
        //   750: astore          15
        //   752: aload           6
        //   754: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   759: ifeq            791
        //   762: aload_0        
        //   763: aload           12
        //   765: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleInitializers;.class
        //   767: ldc             "err_excess_initializers"
        //   769: ldc             "Excess elements in initializer"
        //   771: iconst_1       
        //   772: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   775: dup            
        //   776: iconst_0       
        //   777: aload           15
        //   779: aastore        
        //   780: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   783: pop            
        //   784: goto            814
        //   787: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   790: athrow         
        //   791: aload_0        
        //   792: aload           12
        //   794: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleInitializers;.class
        //   796: ldc             "ext_excess_initializers"
        //   798: ldc             "Excess elements in initializer"
        //   800: aconst_null    
        //   801: iconst_1       
        //   802: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   805: dup            
        //   806: iconst_0       
        //   807: aload           15
        //   809: aastore        
        //   810: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   813: pop            
        //   814: goto            921
        //   817: aload           12
        //   819: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDesignatedInitializer;
        //   822: ifeq            904
        //   825: aload           12
        //   827: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDesignatedInitializer;
        //   830: astore          13
        //   832: aload           13
        //   834: invokeinterface com/jetbrains/cidr/lang/psi/OCDesignatedInitializer.getDesignation:()Lcom/jetbrains/cidr/lang/psi/OCQualifiedDesignator;
        //   839: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedDesignator.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   844: astore          14
        //   846: aload           14
        //   848: ifnull          901
        //   851: aload           13
        //   853: invokeinterface com/jetbrains/cidr/lang/psi/OCDesignatedInitializer.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   858: astore          15
        //   860: aload           15
        //   862: ifnull          901
        //   865: aload           15
        //   867: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   872: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   875: astore          16
        //   877: aload_0        
        //   878: aload           15
        //   880: aload           12
        //   882: aload           14
        //   884: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   889: aload           16
        //   891: aload           14
        //   893: aload           16
        //   895: iconst_1       
        //   896: ldc             ""
        //   898: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)V
        //   901: goto            921
        //   904: aload_0        
        //   905: aload           12
        //   907: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //   909: ldc             "err_typecheck_convert_incompatible"
        //   911: ldc             "Invalid initializer for struct field"
        //   913: iconst_0       
        //   914: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   917: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   920: pop            
        //   921: iinc            8, 1
        //   924: goto            490
        //   927: goto            1234
        //   930: aload           7
        //   932: ifnull          1234
        //   935: aload           7
        //   937: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //   940: ifeq            1234
        //   943: goto            950
        //   946: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   949: athrow         
        //   950: aload           5
        //   952: invokeinterface java/util/List.size:()I
        //   957: ifne            1007
        //   960: goto            967
        //   963: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   966: athrow         
        //   967: aload           6
        //   969: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   974: ifne            1234
        //   977: goto            984
        //   980: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   983: athrow         
        //   984: aload_0        
        //   985: aload_1        
        //   986: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //   988: ldc             "err_empty_scalar_initializer"
        //   990: ldc             "Empty initializer for scalar type"
        //   992: iconst_0       
        //   993: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   996: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   999: pop            
        //  1000: goto            1234
        //  1003: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1006: athrow         
        //  1007: aload           5
        //  1009: invokeinterface java/util/List.size:()I
        //  1014: iconst_1       
        //  1015: if_icmple       1041
        //  1018: aload_0        
        //  1019: aload_1        
        //  1020: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //  1022: ldc             "err_excess_initializers"
        //  1024: ldc             "Excess elements in scalar initializer"
        //  1026: iconst_0       
        //  1027: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1030: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //  1033: pop            
        //  1034: goto            1234
        //  1037: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1040: athrow         
        //  1041: aload           5
        //  1043: iconst_0       
        //  1044: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  1049: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializerMember;
        //  1052: astore          8
        //  1054: aload           8
        //  1056: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1059: ifeq            1218
        //  1062: aload_0        
        //  1063: aload           8
        //  1065: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1068: aload           8
        //  1070: aload           7
        //  1072: aload           8
        //  1074: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1077: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1082: aconst_null    
        //  1083: aconst_null    
        //  1084: iconst_1       
        //  1085: ldc             "Incompatible types in initializer: "
        //  1087: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;)V
        //  1090: aload_1        
        //  1091: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getParent:()Lcom/intellij/psi/PsiElement;
        //  1096: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //  1099: ifeq            1234
        //  1102: goto            1109
        //  1105: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1108: athrow         
        //  1109: aload_1        
        //  1110: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getNode:()Lcom/intellij/lang/ASTNode;
        //  1115: aconst_null    
        //  1116: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //  1121: astore          9
        //  1123: aload           9
        //  1125: arraylength    
        //  1126: istore          10
        //  1128: iconst_0       
        //  1129: istore          11
        //  1131: iload           11
        //  1133: iload           10
        //  1135: if_icmpge       1215
        //  1138: aload           9
        //  1140: iload           11
        //  1142: aaload         
        //  1143: astore          12
        //  1145: aload           12
        //  1147: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //  1152: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1155: if_acmpeq       1178
        //  1158: aload           12
        //  1160: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //  1165: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1168: if_acmpne       1209
        //  1171: goto            1178
        //  1174: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1177: athrow         
        //  1178: aload_0        
        //  1179: aload           12
        //  1181: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //  1186: aconst_null    
        //  1187: ldc             "warn_braces_around_scalar_init"
        //  1189: ldc             "Redundant braces"
        //  1191: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
        //  1194: iconst_0       
        //  1195: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1198: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //  1201: pop            
        //  1202: goto            1209
        //  1205: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1208: athrow         
        //  1209: iinc            11, 1
        //  1212: goto            1131
        //  1215: goto            1234
        //  1218: aload_0        
        //  1219: aload_1        
        //  1220: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //  1222: ldc             "ext_many_braces_around_scalar_init"
        //  1224: ldc             "Invalid initializer for scalar type"
        //  1226: iconst_0       
        //  1227: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1230: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //  1233: pop            
        //  1234: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  34     53     53     57     Ljava/lang/IllegalArgumentException;
        //  57     71     74     78     Ljava/lang/IllegalArgumentException;
        //  132    151    154    158    Ljava/lang/IllegalArgumentException;
        //  140    166    166    170    Ljava/lang/IllegalArgumentException;
        //  170    190    193    197    Ljava/lang/IllegalArgumentException;
        //  332    350    350    354    Ljava/lang/IllegalArgumentException;
        //  357    383    386    390    Ljava/lang/IllegalArgumentException;
        //  394    414    417    421    Ljava/lang/IllegalArgumentException;
        //  401    428    431    435    Ljava/lang/IllegalArgumentException;
        //  532    548    548    552    Ljava/lang/IllegalArgumentException;
        //  591    606    609    613    Ljava/lang/IllegalArgumentException;
        //  598    621    624    628    Ljava/lang/IllegalArgumentException;
        //  613    636    639    643    Ljava/lang/IllegalArgumentException;
        //  628    654    657    661    Ljava/lang/IllegalArgumentException;
        //  661    673    676    680    Ljava/lang/IllegalArgumentException;
        //  669    696    696    700    Ljava/lang/IllegalArgumentException;
        //  752    787    787    791    Ljava/lang/IllegalArgumentException;
        //  930    943    946    950    Ljava/lang/IllegalArgumentException;
        //  935    960    963    967    Ljava/lang/IllegalArgumentException;
        //  950    977    980    984    Ljava/lang/IllegalArgumentException;
        //  967    1003   1003   1007   Ljava/lang/IllegalArgumentException;
        //  1007   1037   1037   1041   Ljava/lang/IllegalArgumentException;
        //  1054   1102   1105   1109   Ljava/lang/IllegalArgumentException;
        //  1145   1171   1174   1178   Ljava/lang/IllegalArgumentException;
        //  1158   1202   1205   1209   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0613:
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
    
    private void a(final OCCompoundInitializer ocCompoundInitializer, final OCStructType ocStructType) {
        final OCStructSymbol symbol = ocStructType.getSymbol();
        if (symbol.getTemplateParameters().size() == 1) {
            final OCTypeArgument substitution = symbol.getSubstitution().getSubstitutionFor(symbol.getTemplateParameters().get(0));
            if (substitution instanceof OCType) {
                final OCArgumentsList<OCExpression> argumentList = OCArgumentsList.getArgumentList(ocCompoundInitializer.getInitializerExpressions());
                for (int i = 0; i < argumentList.getTypes().size(); ++i) {
                    final OCExpression ocExpression = argumentList.getExprs().get(i);
                    this.checkAssignment(ocExpression, (PsiElement)ocExpression, (OCType)substitution, argumentList.getTypes().get(i), null, null, true, "");
                }
            }
        }
    }
    
    public List<Annotation> checkFunctionArguments(final OCElement p0, final OCFunctionType p1, @NotNull final List<OCExpression> p2, final OCSymbol p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "arguments"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCArgumentsChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkFunctionArguments"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //    48: astore          5
        //    50: iconst_1       
        //    51: istore          6
        //    53: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    56: dup            
        //    57: aload_1        
        //    58: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    61: astore          7
        //    63: aload           4
        //    65: ifnull          302
        //    68: aload           4
        //    70: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    75: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //    78: ifeq            302
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: invokeinterface java/util/List.size:()I
        //    94: iconst_1       
        //    95: if_icmpne       302
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_3        
        //   106: iconst_0       
        //   107: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   112: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   115: ifeq            302
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload_3        
        //   126: iconst_0       
        //   127: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   132: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   135: astore          8
        //   137: aload           8
        //   139: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializerExpressions:()Ljava/util/List;
        //   144: astore          9
        //   146: aload           5
        //   148: invokeinterface java/util/List.size:()I
        //   153: ifle            211
        //   156: aload           5
        //   158: iconst_0       
        //   159: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   164: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   167: aload_1        
        //   168: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   173: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInitializerListType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiFile;)Z
        //   176: ifeq            211
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_0        
        //   187: aload           8
        //   189: aload           5
        //   191: iconst_0       
        //   192: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   197: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   200: invokespecial   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCStructType;)V
        //   203: invokestatic    com/intellij/util/containers/ContainerUtil.emptyList:()Ljava/util/List;
        //   206: areturn        
        //   207: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload           5
        //   213: invokeinterface java/util/List.size:()I
        //   218: iconst_1       
        //   219: if_icmpne       293
        //   222: aload           9
        //   224: invokeinterface java/util/List.size:()I
        //   229: iconst_1       
        //   230: if_icmpne       299
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: aload           5
        //   242: iconst_0       
        //   243: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   248: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   251: aload           8
        //   253: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   258: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   261: aload           9
        //   263: iconst_0       
        //   264: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   269: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   272: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   277: aload           8
        //   279: iconst_0       
        //   280: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Z
        //   283: ifeq            299
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload           9
        //   295: astore_3       
        //   296: goto            302
        //   299: iconst_0       
        //   300: istore          6
        //   302: new             Ljava/util/LinkedList;
        //   305: dup            
        //   306: invokespecial   java/util/LinkedList.<init>:()V
        //   309: astore          8
        //   311: aload_3        
        //   312: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.getArgumentList:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   315: astore          9
        //   317: aload           5
        //   319: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getNonVariadicParametersCount:(Ljava/util/List;)I
        //   322: istore          10
        //   324: iload           10
        //   326: istore          11
        //   328: aload_2        
        //   329: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVararg:()Z
        //   332: istore          12
        //   334: aload           4
        //   336: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   339: ifeq            354
        //   342: aload           4
        //   344: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   347: aload           7
        //   349: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getNonInitializedParametersCount:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   352: istore          10
        //   354: aload           9
        //   356: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //   359: iload           10
        //   361: if_icmpge       449
        //   364: aload           9
        //   366: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.hasNonExpandedVariadics:()Z
        //   369: ifne            449
        //   372: goto            379
        //   375: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   378: athrow         
        //   379: aload           8
        //   381: aload_0        
        //   382: aload_1        
        //   383: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FunctionParameterCountMismatch;.class
        //   385: ldc             "err_typecheck_call_too_few_args"
        //   387: new             Ljava/lang/StringBuilder;
        //   390: dup            
        //   391: invokespecial   java/lang/StringBuilder.<init>:()V
        //   394: ldc             "Too few arguments, expected "
        //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   399: iload           12
        //   401: ifeq            420
        //   404: goto            411
        //   407: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   410: athrow         
        //   411: ldc             "at least "
        //   413: goto            422
        //   416: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: ldc             ""
        //   422: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: iload           10
        //   427: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   430: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   433: iconst_0       
        //   434: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   437: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   440: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   445: pop            
        //   446: goto            594
        //   449: iload           12
        //   451: ifne            594
        //   454: aload           9
        //   456: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //   459: iload           11
        //   461: if_icmple       594
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_1        
        //   472: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //   475: ifeq            553
        //   478: goto            485
        //   481: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   484: athrow         
        //   485: aload_2        
        //   486: iconst_1       
        //   487: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:(Z)Ljava/util/List;
        //   490: invokeinterface java/util/List.isEmpty:()Z
        //   495: ifeq            553
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   504: athrow         
        //   505: aload           8
        //   507: aload_0        
        //   508: aload_1        
        //   509: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$KRUnspecifiedParameters;.class
        //   511: ldc             "CIDR"
        //   513: new             Ljava/lang/StringBuilder;
        //   516: dup            
        //   517: invokespecial   java/lang/StringBuilder.<init>:()V
        //   520: ldc             "Too many arguments, expected "
        //   522: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   525: iload           10
        //   527: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   530: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   533: aconst_null    
        //   534: getstatic       com/intellij/codeInsight/intention/IntentionAction.EMPTY_ARRAY:[Lcom/intellij/codeInsight/intention/IntentionAction;
        //   537: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   540: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   545: pop            
        //   546: goto            594
        //   549: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   552: athrow         
        //   553: aload           8
        //   555: aload_0        
        //   556: aload_1        
        //   557: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FunctionParameterCountMismatch;.class
        //   559: ldc             "err_typecheck_call_too_many_args"
        //   561: new             Ljava/lang/StringBuilder;
        //   564: dup            
        //   565: invokespecial   java/lang/StringBuilder.<init>:()V
        //   568: ldc             "Too many arguments, expected "
        //   570: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   573: iload           10
        //   575: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   578: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   581: iconst_0       
        //   582: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   585: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   588: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   593: pop            
        //   594: aload           8
        //   596: invokeinterface java/util/List.size:()I
        //   601: ifne            676
        //   604: aconst_null    
        //   605: astore          13
        //   607: aload           4
        //   609: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   612: ifeq            650
        //   615: aload           4
        //   617: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   620: astore          14
        //   622: aload           14
        //   624: aload           7
        //   626: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   629: astore          15
        //   631: aload           15
        //   633: invokeinterface java/util/List.size:()I
        //   638: aload           9
        //   640: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //   643: if_icmpne       650
        //   646: aload           15
        //   648: astore          13
        //   650: iload           6
        //   652: istore          14
        //   654: aload           5
        //   656: aload           13
        //   658: aload           9
        //   660: aload           7
        //   662: aload_0        
        //   663: aload           7
        //   665: iload           14
        //   667: invokedynamic   process:(Lcom/jetbrains/cidr/lang/daemon/OCArgumentsChecker;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor$ArgumentsProcessor;
        //   672: invokestatic    com/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor.processArguments:(Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor$ArgumentsProcessor;)Z
        //   675: pop            
        //   676: aload           8
        //   678: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/util/List<Lcom/intellij/lang/annotation/Annotation;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  63     81     84     88     Ljava/lang/IllegalArgumentException;
        //  68     98     101    105    Ljava/lang/IllegalArgumentException;
        //  88     118    121    125    Ljava/lang/IllegalArgumentException;
        //  146    179    182    186    Ljava/lang/IllegalArgumentException;
        //  156    207    207    211    Ljava/lang/IllegalArgumentException;
        //  211    233    236    240    Ljava/lang/IllegalArgumentException;
        //  222    286    289    293    Ljava/lang/IllegalArgumentException;
        //  354    372    375    379    Ljava/lang/IllegalArgumentException;
        //  364    404    407    411    Ljava/lang/IllegalArgumentException;
        //  379    416    416    420    Ljava/lang/IllegalArgumentException;
        //  449    464    467    471    Ljava/lang/IllegalArgumentException;
        //  454    478    481    485    Ljava/lang/IllegalArgumentException;
        //  471    498    501    505    Ljava/lang/IllegalArgumentException;
        //  485    549    549    553    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    protected abstract void checkAssignment(final OCExpression p0, final PsiElement p1, final OCType p2, final OCType p3, final OCSymbol p4, final OCType p5, final boolean p6, final String p7);
    
    @Nullable
    protected abstract Annotation addWarningAnnotation(@Nullable final PsiElement p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final String p2, @NotNull final String p3, @Nullable final ProblemHighlightType p4, final IntentionAction... p5);
    
    @Nullable
    protected abstract Annotation addErrorAnnotation(@Nullable final PsiElement p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final String p2, @NotNull final String p3, final IntentionAction... p4);
    
    protected abstract void checkConstructor(final OCCompoundInitializer p0, final OCSymbol p1);
    
    public void checkCharArrayInit(@NotNull final OCType p0, @NotNull final OCLiteralExpression p1) {
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
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCArgumentsChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkCharArrayInit"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "literal"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/OCArgumentsChecker"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkCharArrayInit"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    92: ifeq            197
        //    95: aload_1        
        //    96: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    99: astore_3       
        //   100: aload_3        
        //   101: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //   104: istore          4
        //   106: aload_2        
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   112: astore          5
        //   114: aload           5
        //   116: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   119: ifeq            197
        //   122: aload           5
        //   124: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   127: astore          6
        //   129: aload_3        
        //   130: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //   133: ifeq            197
        //   136: iload           4
        //   138: aload           6
        //   140: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //   143: if_icmpge       197
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_0        
        //   154: aload_2        
        //   155: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleInitializers;.class
        //   157: ldc             "ext_initializer_string_for_char_array_too_long"
        //   159: iload           4
        //   161: aload           6
        //   163: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //   166: iconst_1       
        //   167: isub           
        //   168: if_icmpne       187
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: ldc             "Initializer string is too long, null-terminator doesn't fit"
        //   180: goto            189
        //   183: invokestatic    com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: ldc             "Initializer string is too long for array of chars"
        //   189: aconst_null    
        //   190: getstatic       com/intellij/codeInsight/intention/IntentionAction.EMPTY_ARRAY:[Lcom/intellij/codeInsight/intention/IntentionAction;
        //   193: invokevirtual   com/jetbrains/cidr/lang/daemon/OCArgumentsChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;[Lcom/intellij/codeInsight/intention/IntentionAction;)Lcom/intellij/lang/annotation/Annotation;
        //   196: pop            
        //   197: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  129    146    149    153    Ljava/lang/IllegalArgumentException;
        //  136    171    174    178    Ljava/lang/IllegalArgumentException;
        //  153    183    183    187    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0153:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
