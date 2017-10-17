// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.lang.ASTNode;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCGenericArgument;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentsOwner;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifiedNameOwner;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.util.Processor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;

public class OCSymbolReferenceResolver
{
    @NotNull
    private final OCResolveContext myMemoization;
    private final boolean myProcessInsideUsings;
    private final boolean myProcessTypesOnly;
    private static final Condition<OCSymbol> NON_USING_SYMBOL;
    private static final Class<PsiElement>[] STOP_SET;
    
    public OCSymbolReferenceResolver(final boolean myProcessInsideUsings, final boolean myProcessTypesOnly, @NotNull final OCResolveContext myMemoization) {
        if (myMemoization == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "<init>"));
        }
        this.myProcessInsideUsings = myProcessInsideUsings;
        this.myProcessTypesOnly = myProcessTypesOnly;
        this.myMemoization = myMemoization;
    }
    
    public void processSymbolsForLocalRef(@Nullable final String s, @NotNull final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "processSymbolsForLocalRef"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "processSymbolsForLocalRef"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        PsiElement context;
        for (PsiElement psiElement2 = psiElement; psiElement2 != null; psiElement2 = context) {
            OCResolveUtil.processLocalAndMemberSymbols(s, psiElement2, processor, this.myMemoization);
            final PsiFile containingFile = psiElement2.getContainingFile();
            try {
                if (containingFile != null) {
                    context = containingFile.getContext();
                    continue;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            context = null;
        }
        this.a(s, psiElement, processor);
    }
    
    public OCCommonProcessors.OrderedProcessor<OCSymbol> getFilteredByKindProcessor(@Nullable final OCSymbolReference.SymbolFilter symbolFilter, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "getFilteredByKindProcessor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (symbolFilter == null) {
                final Processor processor2 = processor;
                return new OCSymbolReferenceResolver.1MyOrderedProcessor(processor2);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Processor processor2 = ocSymbol -> {
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "lambda$getFilteredByKindProcessor$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0071: {
                try {
                    if (!symbolFilter.accept(ocSymbol)) {
                        break Label_0071;
                    }
                    final Processor processor2 = processor;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = processor2.process((Object)ocSymbol2);
                    if (b) {
                        break Label_0071;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final Processor processor2 = processor;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = processor2.process((Object)ocSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return false;
        };
        return new OCSymbolReferenceResolver.1MyOrderedProcessor(processor2);
    }
    
    public static OCResolveUtil.ResolveFilteringProcessor<OCSymbol> createResolveFilteringProcessor(final Processor<OCSymbol> processor, final OCResolveContext ocResolveContext) {
        final PsiElement element = ocResolveContext.getElement();
        PsiFile containingFile = null;
        Label_0023: {
            try {
                if (element != null) {
                    containingFile = element.getContainingFile();
                    break Label_0023;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            containingFile = null;
        }
        final PsiFile psiFile = containingFile;
        try {
            if (element != null) {
                final int endOffset = element.getTextRange().getEndOffset();
                return new OCResolveUtil.ResolveFilteringProcessor<OCSymbol>(processor, psiFile, endOffset, ocResolveContext.isProcessNonImported());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int endOffset = 0;
        return new OCResolveUtil.ResolveFilteringProcessor<OCSymbol>(processor, psiFile, endOffset, ocResolveContext.isProcessNonImported());
    }
    
    public boolean processSymbolsForGlobalRef(@Nullable final String p0, @Nullable final OCSymbolReference.SymbolFilter p1, @Nullable final OCSymbolWithQualifiedName p2, final boolean p3, @NotNull final Processor<OCSymbol> p4, final boolean p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           5
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "processor"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "processSymbolsForGlobalRef"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: new             Ljava/util/ArrayList;
        //    48: dup            
        //    49: invokespecial   java/util/ArrayList.<init>:()V
        //    52: astore          7
        //    54: iconst_1       
        //    55: istore          8
        //    57: iconst_0       
        //    58: istore          9
        //    60: aload_3        
        //    61: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;
        //    64: ifeq            96
        //    67: aload_3        
        //    68: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    75: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.hasNonResolvedTemplateParameters:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    78: ifeq            96
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_1       
        //    89: goto            97
        //    92: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: iconst_0       
        //    97: istore          10
        //    99: aload_3        
        //   100: astore          11
        //   102: aload_3        
        //   103: astore          12
        //   105: aload           11
        //   107: ifnull          388
        //   110: aload           11
        //   112: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   115: ifnull          128
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: iconst_0       
        //   126: istore          8
        //   128: aload           11
        //   130: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   133: ifeq            213
        //   136: aload           11
        //   138: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   141: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isTemplateSymbol:()Z
        //   144: ifeq            213
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           11
        //   156: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   159: aload_1        
        //   160: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   163: ifeq            213
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: iload           6
        //   175: ifne            213
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload           5
        //   187: aload_0        
        //   188: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   191: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   194: aload           11
        //   196: aload_0        
        //   197: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   200: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   203: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   208: ireturn        
        //   209: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload           11
        //   215: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   218: ifeq            326
        //   221: aload           11
        //   223: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   226: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   231: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   236: astore          13
        //   238: aload           13
        //   240: invokeinterface java/util/Iterator.hasNext:()Z
        //   245: ifeq            326
        //   248: aload           13
        //   250: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   255: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   258: astore          14
        //   260: aload_1        
        //   261: ifnonnull       292
        //   264: aload           5
        //   266: aload           14
        //   268: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   271: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   276: ifne            292
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: iconst_0       
        //   287: ireturn        
        //   288: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload           14
        //   294: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getName:()Ljava/lang/String;
        //   299: aload_1        
        //   300: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   303: ifeq            323
        //   306: aload           5
        //   308: aload           14
        //   310: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   313: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   318: ireturn        
        //   319: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: goto            238
        //   326: iload           8
        //   328: ifeq            378
        //   331: aload           11
        //   333: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   336: ifne            361
        //   339: goto            346
        //   342: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: aload           11
        //   348: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   351: ifeq            378
        //   354: goto            361
        //   357: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: aload           7
        //   363: aload           11
        //   365: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   368: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   371: pop            
        //   372: aload           11
        //   374: astore_3       
        //   375: iconst_1       
        //   376: istore          9
        //   378: aload           11
        //   380: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   383: astore          11
        //   385: goto            105
        //   388: iload           4
        //   390: ifeq            422
        //   393: aload           7
        //   395: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   398: ifne            422
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aload           7
        //   410: iconst_0       
        //   411: invokevirtual   java/util/ArrayList.remove:(I)Ljava/lang/Object;
        //   414: pop            
        //   415: goto            422
        //   418: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: aload_3        
        //   423: ifnull          488
        //   426: aload_0        
        //   427: aload_3        
        //   428: aload           7
        //   430: iload           9
        //   432: iconst_1       
        //   433: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/util/List;ZZ)V
        //   436: aload_3        
        //   437: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   440: ifeq            488
        //   443: goto            450
        //   446: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   449: athrow         
        //   450: aload_3        
        //   451: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   454: aload_0        
        //   455: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   458: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppNonMemberOperator:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   461: ifeq            488
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_0        
        //   472: aload_3        
        //   473: aload           7
        //   475: iload           9
        //   477: iconst_0       
        //   478: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/util/List;ZZ)V
        //   481: goto            488
        //   484: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload           7
        //   490: aconst_null    
        //   491: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   494: pop            
        //   495: aload_3        
        //   496: astore          13
        //   498: aload           13
        //   500: ifnull          533
        //   503: aload           13
        //   505: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   508: ifne            533
        //   511: goto            518
        //   514: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   517: athrow         
        //   518: aload           13
        //   520: aload_0        
        //   521: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   524: iconst_1       
        //   525: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   528: astore          13
        //   530: goto            498
        //   533: aload           7
        //   535: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   538: astore          14
        //   540: aload           14
        //   542: invokeinterface java/util/Iterator.hasNext:()Z
        //   547: ifeq            1469
        //   550: aload           14
        //   552: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   557: checkcast       Ljava/util/Collection;
        //   560: astore          15
        //   562: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   565: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
        //   568: dup            
        //   569: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:()V
        //   572: astore          16
        //   574: aload_0        
        //   575: aload_2        
        //   576: aload           16
        //   578: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getFilteredByKindProcessor:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolFilter;Lcom/intellij/util/Processor;)Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor;
        //   581: astore          17
        //   583: aload           15
        //   585: ifnull          808
        //   588: aload           15
        //   590: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   595: astore          18
        //   597: aload           18
        //   599: invokeinterface java/util/Iterator.hasNext:()Z
        //   604: ifeq            805
        //   607: aload           18
        //   609: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   614: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   617: astore          19
        //   619: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   622: aload           19
        //   624: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   627: ifeq            713
        //   630: aload_0        
        //   631: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   634: iconst_0       
        //   635: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setNonImportedFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   638: istore          20
        //   640: aload_0        
        //   641: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   644: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeDependencies:()Ljava/util/Set;
        //   647: astore          21
        //   649: aload_0        
        //   650: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   653: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   656: aload           19
        //   658: aload_0        
        //   659: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   662: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   665: astore          22
        //   667: aload           22
        //   669: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   672: aload_1        
        //   673: aload_0        
        //   674: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myProcessInsideUsings:Z
        //   677: aload_0        
        //   678: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myProcessTypesOnly:Z
        //   681: aload           17
        //   683: aload_0        
        //   684: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   687: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.processMembersOfNamespace:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;Ljava/lang/String;ZZLcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   690: pop            
        //   691: aload_0        
        //   692: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   695: aload           21
        //   697: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setTypeDependencies:(Ljava/util/Set;)V
        //   700: aload_0        
        //   701: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   704: iload           20
        //   706: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setNonImportedFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   709: pop            
        //   710: goto            802
        //   713: aload_0        
        //   714: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myProcessTypesOnly:Z
        //   717: ifne            802
        //   720: aload           19
        //   722: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   725: ifeq            802
        //   728: goto            735
        //   731: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   734: athrow         
        //   735: aload           19
        //   737: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   740: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   743: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   748: astore          20
        //   750: aload           20
        //   752: invokeinterface java/util/Iterator.hasNext:()Z
        //   757: ifeq            802
        //   760: aload           20
        //   762: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   767: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   770: astore          21
        //   772: aload           21
        //   774: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   777: aload_1        
        //   778: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   781: ifeq            799
        //   784: aload           17
        //   786: aload           21
        //   788: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.process:(Ljava/lang/Object;)Z
        //   791: pop            
        //   792: goto            799
        //   795: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   798: athrow         
        //   799: goto            750
        //   802: goto            597
        //   805: goto            945
        //   808: aload_0        
        //   809: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   812: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   815: astore          18
        //   817: aload           18
        //   819: ifnull          842
        //   822: invokestatic    com/jetbrains/cidr/lang/OCLanguage.getInstance:()Lcom/jetbrains/cidr/lang/OCLanguage;
        //   825: aload           18
        //   827: invokeinterface com/intellij/psi/PsiFile.getLanguage:()Lcom/intellij/lang/Language;
        //   832: if_acmpeq       848
        //   835: goto            842
        //   838: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   841: athrow         
        //   842: iconst_0       
        //   843: ireturn        
        //   844: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   847: athrow         
        //   848: aload           18
        //   850: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   853: astore          19
        //   855: aload           19
        //   857: aload_0        
        //   858: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myProcessTypesOnly:Z
        //   861: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getMembersContainer:(Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
        //   866: astore          20
        //   868: aload           20
        //   870: aload_1        
        //   871: aload_0        
        //   872: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myProcessInsideUsings:Z
        //   875: aload_0        
        //   876: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myProcessTypesOnly:Z
        //   879: aload           17
        //   881: aload_0        
        //   882: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   885: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.processMembersOfNamespace:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;Ljava/lang/String;ZZLcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   888: pop            
        //   889: aload_0        
        //   890: aload_1        
        //   891: aload_0        
        //   892: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   895: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   898: aload           17
        //   900: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Processor;)Z
        //   903: pop            
        //   904: aload_0        
        //   905: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   908: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isProcessNonImported:()Z
        //   911: ifeq            945
        //   914: aload           19
        //   916: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   921: new             Lcom/intellij/util/FilteringProcessor;
        //   924: dup            
        //   925: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.NON_USING_SYMBOL:Lcom/intellij/openapi/util/Condition;
        //   928: aload           17
        //   930: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
        //   933: aload_1        
        //   934: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache.processTopLevelSymbols:(Lcom/intellij/openapi/project/Project;Lcom/intellij/util/Processor;Ljava/lang/String;)Z
        //   937: pop            
        //   938: goto            945
        //   941: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   944: athrow         
        //   945: aload           17
        //   947: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.finish:()Z
        //   950: pop            
        //   951: aload           16
        //   953: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   956: astore          18
        //   958: aload           18
        //   960: invokeinterface java/util/Collection.isEmpty:()Z
        //   965: ifne            1466
        //   968: iconst_0       
        //   969: istore          19
        //   971: iconst_0       
        //   972: istore          20
        //   974: iconst_0       
        //   975: istore          21
        //   977: aload           18
        //   979: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   984: astore          22
        //   986: aload           22
        //   988: invokeinterface java/util/Iterator.hasNext:()Z
        //   993: ifeq            1287
        //   996: aload           22
        //   998: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1003: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1006: astore          23
        //  1008: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //  1011: aload           23
        //  1013: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //  1018: ifne            1230
        //  1021: iconst_1       
        //  1022: istore          19
        //  1024: iload           6
        //  1026: ifeq            1056
        //  1029: aload           23
        //  1031: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1036: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1039: if_acmpne       1056
        //  1042: goto            1049
        //  1045: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1048: athrow         
        //  1049: goto            986
        //  1052: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1055: athrow         
        //  1056: aload           23
        //  1058: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1063: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //  1066: ifne            1266
        //  1069: aload           23
        //  1071: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1076: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTemplateParameter:()Z
        //  1079: ifne            1266
        //  1082: goto            1089
        //  1085: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1088: athrow         
        //  1089: aload           23
        //  1091: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1096: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1099: if_acmpeq       1266
        //  1102: goto            1109
        //  1105: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1108: athrow         
        //  1109: aload           23
        //  1111: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1116: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1119: if_acmpeq       1266
        //  1122: goto            1129
        //  1125: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1128: athrow         
        //  1129: aload           23
        //  1131: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1136: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1139: if_acmpne       1224
        //  1142: goto            1149
        //  1145: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1148: athrow         
        //  1149: aload           12
        //  1151: ifnull          1224
        //  1154: goto            1161
        //  1157: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1160: athrow         
        //  1161: aload           23
        //  1163: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getComplexOffset:()J
        //  1168: aload           12
        //  1170: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getComplexOffset:()J
        //  1173: lcmp           
        //  1174: iflt            1224
        //  1177: goto            1184
        //  1180: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1183: athrow         
        //  1184: aload           23
        //  1186: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //  1189: ifeq            1266
        //  1192: goto            1199
        //  1195: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1198: athrow         
        //  1199: aload           12
        //  1201: aload           23
        //  1203: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //  1206: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1211: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.equals:(Ljava/lang/Object;)Z
        //  1214: ifeq            1266
        //  1217: goto            1224
        //  1220: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1223: athrow         
        //  1224: iconst_1       
        //  1225: istore          20
        //  1227: goto            1266
        //  1230: aload           23
        //  1232: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1237: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //  1240: ifne            1266
        //  1243: aload           23
        //  1245: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1250: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //  1253: ifne            1266
        //  1256: goto            1263
        //  1259: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1262: athrow         
        //  1263: iconst_1       
        //  1264: istore          20
        //  1266: aload           5
        //  1268: aload           23
        //  1270: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //  1275: ifne            1284
        //  1278: iconst_0       
        //  1279: ireturn        
        //  1280: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1283: athrow         
        //  1284: goto            986
        //  1287: iload           19
        //  1289: ifne            1444
        //  1292: aload           16
        //  1294: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //  1297: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //  1302: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1307: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1310: astore          22
        //  1312: aload           22
        //  1314: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1317: ifeq            1444
        //  1320: aload_0        
        //  1321: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //  1324: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeDependencies:()Ljava/util/Set;
        //  1327: astore          23
        //  1329: aload_0        
        //  1330: aload           22
        //  1332: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1335: iload           10
        //  1337: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getImplementationsOfSymbol:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Z)Ljava/util/Collection;
        //  1340: astore          24
        //  1342: aload_0        
        //  1343: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //  1346: aload           23
        //  1348: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setTypeDependencies:(Ljava/util/Set;)V
        //  1351: aload           24
        //  1353: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //  1358: astore          25
        //  1360: aload           25
        //  1362: invokeinterface java/util/Iterator.hasNext:()Z
        //  1367: ifeq            1444
        //  1370: aload           25
        //  1372: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1377: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1380: astore          26
        //  1382: aload           26
        //  1384: ifnull          1441
        //  1387: iload           21
        //  1389: ifeq            1416
        //  1392: goto            1399
        //  1395: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1398: athrow         
        //  1399: aload           26
        //  1401: aload           13
        //  1403: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //  1406: ifne            1441
        //  1409: goto            1416
        //  1412: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1415: athrow         
        //  1416: aload           5
        //  1418: aload           26
        //  1420: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //  1425: ifne            1441
        //  1428: goto            1435
        //  1431: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1434: athrow         
        //  1435: iconst_0       
        //  1436: ireturn        
        //  1437: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1440: athrow         
        //  1441: goto            1360
        //  1444: iload           20
        //  1446: ifeq            1466
        //  1449: aload_1        
        //  1450: ifnull          1466
        //  1453: goto            1460
        //  1456: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1459: athrow         
        //  1460: iconst_1       
        //  1461: ireturn        
        //  1462: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1465: athrow         
        //  1466: goto            540
        //  1469: iconst_1       
        //  1470: ireturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolFilter;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;ZLcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  60     81     84     88     Ljava/lang/IllegalArgumentException;
        //  67     92     92     96     Ljava/lang/IllegalArgumentException;
        //  105    118    121    125    Ljava/lang/IllegalArgumentException;
        //  128    147    150    154    Ljava/lang/IllegalArgumentException;
        //  136    166    169    173    Ljava/lang/IllegalArgumentException;
        //  154    178    181    185    Ljava/lang/IllegalArgumentException;
        //  173    209    209    213    Ljava/lang/IllegalArgumentException;
        //  260    279    282    286    Ljava/lang/IllegalArgumentException;
        //  264    288    288    292    Ljava/lang/IllegalArgumentException;
        //  292    319    319    323    Ljava/lang/IllegalArgumentException;
        //  326    339    342    346    Ljava/lang/IllegalArgumentException;
        //  331    354    357    361    Ljava/lang/IllegalArgumentException;
        //  388    401    404    408    Ljava/lang/IllegalArgumentException;
        //  393    415    418    422    Ljava/lang/IllegalArgumentException;
        //  422    443    446    450    Ljava/lang/IllegalArgumentException;
        //  426    464    467    471    Ljava/lang/IllegalArgumentException;
        //  450    481    484    488    Ljava/lang/IllegalArgumentException;
        //  498    511    514    518    Ljava/lang/IllegalArgumentException;
        //  713    728    731    735    Ljava/lang/IllegalArgumentException;
        //  772    792    795    799    Ljava/lang/IllegalArgumentException;
        //  817    835    838    842    Ljava/lang/IllegalArgumentException;
        //  822    844    844    848    Ljava/lang/IllegalArgumentException;
        //  868    938    941    945    Ljava/lang/IllegalArgumentException;
        //  1024   1042   1045   1049   Ljava/lang/IllegalArgumentException;
        //  1029   1052   1052   1056   Ljava/lang/IllegalArgumentException;
        //  1056   1082   1085   1089   Ljava/lang/IllegalArgumentException;
        //  1069   1102   1105   1109   Ljava/lang/IllegalArgumentException;
        //  1089   1122   1125   1129   Ljava/lang/IllegalArgumentException;
        //  1109   1142   1145   1149   Ljava/lang/IllegalArgumentException;
        //  1129   1154   1157   1161   Ljava/lang/IllegalArgumentException;
        //  1149   1177   1180   1184   Ljava/lang/IllegalArgumentException;
        //  1161   1192   1195   1199   Ljava/lang/IllegalArgumentException;
        //  1184   1217   1220   1224   Ljava/lang/IllegalArgumentException;
        //  1230   1256   1259   1263   Ljava/lang/IllegalArgumentException;
        //  1266   1280   1280   1284   Ljava/lang/IllegalArgumentException;
        //  1382   1392   1395   1399   Ljava/lang/IllegalArgumentException;
        //  1387   1409   1412   1416   Ljava/lang/IllegalArgumentException;
        //  1399   1428   1431   1435   Ljava/lang/IllegalArgumentException;
        //  1416   1437   1437   1441   Ljava/lang/IllegalArgumentException;
        //  1444   1453   1456   1460   Ljava/lang/IllegalArgumentException;
        //  1449   1462   1462   1466   Ljava/lang/IllegalArgumentException;
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
    
    private void a(@NotNull final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final List<Collection<? extends OCSymbol>> list, final boolean b, final boolean b2) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "addWorksets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName.getResolvedQualifiedName(false, this.myMemoization, this.myProcessTypesOnly, true, false, true, b2);
        while (true) {
            Label_0080: {
                Label_0087: {
                    try {
                        if (ocQualifiedName == null || !b) {
                            break Label_0087;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    break Label_0080;
                    while (true) {
                        try {
                            if (ocQualifiedName == null || ocQualifiedName == OCQualifiedName.GLOBAL) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        list.add(this.myMemoization.doResolveToSymbols(OCSymbolReference.getGlobalReference(ocQualifiedName, OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE_LIKE), false, this.myProcessTypesOnly));
                        ocQualifiedName = ocQualifiedName.getQualifier();
                    }
                }
                return;
            }
            ocQualifiedName = ocQualifiedName.getQualifier();
            continue;
        }
    }
    
    private boolean a(@Nullable final String p0, @NotNull final PsiElement p1, final Processor<OCSymbol> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processBuiltInSymbols"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnull          453
        //    48: aload_1        
        //    49: ldc             "__builtin"
        //    51: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    54: ifne            115
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_1        
        //    65: ldc             "__sync_"
        //    67: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    70: ifne            115
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_1        
        //    81: ldc             "__atomic_"
        //    83: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    86: ifne            115
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: getstatic       com/jetbrains/cidr/lang/util/OCExpressionEvaluator.BUILT_IN_TRAITS:Ljava/util/Map;
        //    99: aload_1        
        //   100: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   105: ifeq            132
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   118: dup            
        //   119: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:()V
        //   122: astore          4
        //   124: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   127: astore          5
        //   129: goto            397
        //   132: aload_1        
        //   133: ldc             "__objc_yes"
        //   135: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   138: ifne            157
        //   141: aload_1        
        //   142: ldc             "__objc_no"
        //   144: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   147: ifeq            170
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   160: astore          4
        //   162: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   165: astore          5
        //   167: goto            397
        //   170: aload_1        
        //   171: ldc             "_cmd"
        //   173: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   176: ifeq            217
        //   179: aload_2        
        //   180: iconst_1       
        //   181: anewarray       Ljava/lang/Class;
        //   184: dup            
        //   185: iconst_0       
        //   186: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   188: aastore        
        //   189: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   192: ifnull          217
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: ldc             "SEL"
        //   204: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   207: astore          4
        //   209: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   212: astore          5
        //   214: goto            397
        //   217: aload_1        
        //   218: ldc             "defined"
        //   220: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   223: ifeq            262
        //   226: aload_2        
        //   227: iconst_1       
        //   228: anewarray       Ljava/lang/Class;
        //   231: dup            
        //   232: iconst_0       
        //   233: ldc             Lcom/jetbrains/cidr/lang/psi/OCDirective;.class
        //   235: aastore        
        //   236: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   239: ifnull          262
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   252: astore          4
        //   254: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   257: astore          5
        //   259: goto            397
        //   262: aload_2        
        //   263: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //   266: ifne            331
        //   269: aload_1        
        //   270: ldc             "typeid"
        //   272: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   275: ifeq            331
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: ldc             "std::type_info"
        //   287: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.parse:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   290: astore          6
        //   292: new             Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   295: dup            
        //   296: aload           6
        //   298: invokespecial   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)V
        //   301: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.build:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   304: astore          7
        //   306: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   309: dup            
        //   310: aload           7
        //   312: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   315: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   318: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   321: astore          4
        //   323: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   326: astore          5
        //   328: goto            397
        //   331: aload_1        
        //   332: ldc             "__"
        //   334: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   337: ifeq            389
        //   340: aload_2        
        //   341: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallExpression;.class
        //   343: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   346: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   349: astore          6
        //   351: aload           6
        //   353: ifnull          383
        //   356: aload           6
        //   358: aload_0        
        //   359: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.myMemoization:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   362: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluateGNUBuiltInTrait:(Lcom/jetbrains/cidr/lang/psi/OCCallExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Boolean;
        //   365: ifnull          383
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   378: astore          4
        //   380: goto            386
        //   383: aconst_null    
        //   384: astore          4
        //   386: goto            392
        //   389: aconst_null    
        //   390: astore          4
        //   392: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   395: astore          5
        //   397: aload           4
        //   399: ifnull          453
        //   402: aload_2        
        //   403: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   408: astore          6
        //   410: aload_2        
        //   411: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   416: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   419: istore          7
        //   421: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   424: dup            
        //   425: aload           6
        //   427: aconst_null    
        //   428: iload           7
        //   430: aconst_null    
        //   431: aload_1        
        //   432: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   435: aload           4
        //   437: aload           5
        //   439: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   442: astore          8
        //   444: aload_3        
        //   445: aload           8
        //   447: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   452: ireturn        
        //   453: iconst_1       
        //   454: ireturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     57     60     64     Ljava/lang/IllegalArgumentException;
        //  48     73     76     80     Ljava/lang/IllegalArgumentException;
        //  64     89     92     96     Ljava/lang/IllegalArgumentException;
        //  80     108    111    115    Ljava/lang/IllegalArgumentException;
        //  132    150    153    157    Ljava/lang/IllegalArgumentException;
        //  170    195    198    202    Ljava/lang/IllegalArgumentException;
        //  217    242    245    249    Ljava/lang/IllegalArgumentException;
        //  262    278    281    285    Ljava/lang/IllegalArgumentException;
        //  351    368    371    375    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
    public static OCSymbolWithQualifiedName getGlobalContextFromLocal(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     4: ifeq            14
        //     7: aload_0        
        //     8: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //    13: astore_0       
        //    14: aconst_null    
        //    15: astore_1       
        //    16: aload_0        
        //    17: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.STOP_SET:[Ljava/lang/Class;
        //    20: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    23: astore_2       
        //    24: iconst_0       
        //    25: istore_3       
        //    26: aload_2        
        //    27: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    30: ifne            47
        //    33: aload_2        
        //    34: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    37: ifeq            81
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: aload_2        
        //    48: iconst_1       
        //    49: anewarray       Ljava/lang/Class;
        //    52: dup            
        //    53: iconst_0       
        //    54: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;.class
        //    56: aastore        
        //    57: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    60: ifnull          81
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_2        
        //    71: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.STOP_SET:[Ljava/lang/Class;
        //    74: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    77: astore_2       
        //    78: goto            26
        //    81: aload_2        
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //    85: ifeq            98
        //    88: iconst_1       
        //    89: istore_3       
        //    90: aload_2        
        //    91: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.STOP_SET:[Ljava/lang/Class;
        //    94: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    97: astore_2       
        //    98: aload_2        
        //    99: ifnull          405
        //   102: aload_2        
        //   103: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   106: ifne            130
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_2        
        //   117: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   120: ifeq            156
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_2        
        //   131: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   136: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   139: ifeq            156
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_2        
        //   150: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   155: astore_2       
        //   156: aload_2        
        //   157: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //   160: ifeq            254
        //   163: aload_2        
        //   164: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   169: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   172: ifeq            254
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_2        
        //   183: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   188: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   191: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   196: astore          4
        //   198: aload           4
        //   200: ifnull          254
        //   203: aload           4
        //   205: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getChildren:()[Lcom/intellij/psi/PsiElement;
        //   210: astore          5
        //   212: aload           5
        //   214: arraylength    
        //   215: istore          6
        //   217: iconst_0       
        //   218: istore          7
        //   220: iload           7
        //   222: iload           6
        //   224: if_icmpge       254
        //   227: aload           5
        //   229: iload           7
        //   231: aaload         
        //   232: astore          8
        //   234: aload           8
        //   236: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   239: ifeq            248
        //   242: aload           8
        //   244: astore_2       
        //   245: goto            254
        //   248: iinc            7, 1
        //   251: goto            220
        //   254: aload_2        
        //   255: astore          4
        //   257: aload_2        
        //   258: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   261: ifeq            303
        //   264: aload_2        
        //   265: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   268: ifne            303
        //   271: goto            278
        //   274: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: aload_2        
        //   279: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   282: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   287: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   290: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   293: astore          5
        //   295: aload           5
        //   297: ifnull          303
        //   300: aload           5
        //   302: astore_2       
        //   303: aload_2        
        //   304: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   307: ifeq            326
        //   310: aload_2        
        //   311: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   314: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   319: goto            327
        //   322: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: aconst_null    
        //   327: astore          5
        //   329: aload           5
        //   331: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   334: ifeq            344
        //   337: goto            405
        //   340: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: aload           5
        //   346: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   349: ifeq            393
        //   352: aload           5
        //   354: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   357: astore_1       
        //   358: iload_3        
        //   359: ifeq            405
        //   362: aload_1        
        //   363: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   366: ifnonnull       405
        //   369: goto            376
        //   372: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   375: athrow         
        //   376: aload_1        
        //   377: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   380: astore          6
        //   382: aload           6
        //   384: ifnull          390
        //   387: aload           6
        //   389: astore_1       
        //   390: goto            405
        //   393: aload           4
        //   395: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.STOP_SET:[Ljava/lang/Class;
        //   398: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   401: astore_2       
        //   402: goto            98
        //   405: aload_1        
        //   406: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  26     40     43     47     Ljava/lang/IllegalArgumentException;
        //  33     63     66     70     Ljava/lang/IllegalArgumentException;
        //  98     109    112    116    Ljava/lang/IllegalArgumentException;
        //  102    123    126    130    Ljava/lang/IllegalArgumentException;
        //  116    142    145    149    Ljava/lang/IllegalArgumentException;
        //  156    175    178    182    Ljava/lang/IllegalArgumentException;
        //  257    271    274    278    Ljava/lang/IllegalArgumentException;
        //  303    322    322    326    Ljava/lang/IllegalArgumentException;
        //  329    340    340    344    Ljava/lang/IllegalArgumentException;
        //  358    369    372    376    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
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
    
    public static OCSymbolReference getGlobalReferenceFromLocal(final OCQualifiedName ocQualifiedName, PsiElement psiElement, final OCSymbolReference.SymbolFilter symbolFilter) {
        while (psiElement instanceof OCCppNamespaceQualifier) {
            psiElement = psiElement.getParent();
        }
        try {
            if (!(psiElement instanceof OCDeclarator) || !(psiElement.getParent() instanceof OCFunctionDefinition)) {
                return OCSymbolReference.getGlobalReference(ocQualifiedName, getGlobalContextFromLocal(psiElement), symbolFilter);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        psiElement = psiElement.getParent();
        return OCSymbolReference.getGlobalReference(ocQualifiedName, getGlobalContextFromLocal(psiElement), symbolFilter);
    }
    
    public static OCSymbolReference getGlobalReferenceFromLocal(final OCSymbolReference ocSymbolReference) {
        try {
            if (ocSymbolReference instanceof OCSymbolReference.LocalReference) {
                return getGlobalReferenceFromLocal(ocSymbolReference.getQualifiedName(), ((OCSymbolReference.LocalReference)ocSymbolReference).getLocalContext(), ocSymbolReference.getFilter());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocSymbolReference;
    }
    
    @NotNull
    public static OCQualifiedName getQualifiedName(@NotNull final OCNamespaceQualifierOwner ocNamespaceQualifierOwner) {
        try {
            if (ocNamespaceQualifierOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "getQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCCppNamespaceQualifier namespaceQualifier = ocNamespaceQualifierOwner.getNamespaceQualifier();
        OCQualifiedName ocQualifiedName = null;
        String s = null;
        Label_0164: {
            Label_0107: {
                if (namespaceQualifier != null) {
                    ocQualifiedName = getQualifiedName(namespaceQualifier);
                }
                else {
                    final PsiElement firstChild = ocNamespaceQualifierOwner.getFirstChild();
                    Label_0105: {
                        try {
                            if (firstChild == null || firstChild.getNode().getElementType() != OCTokenTypes.COLON2X) {
                                break Label_0105;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        ocQualifiedName = OCQualifiedName.GLOBAL;
                        break Label_0107;
                    }
                    ocQualifiedName = null;
                }
                try {
                    if (ocNamespaceQualifierOwner instanceof OCNamespaceQualifiedNameOwner) {
                        s = ((OCNamespaceQualifiedNameOwner)ocNamespaceQualifierOwner).getName();
                        break Label_0164;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (ocNamespaceQualifierOwner instanceof PsiNameIdentifierOwner) {
                    s = ((PsiNameIdentifierOwner)ocNamespaceQualifierOwner).getName();
                    break Label_0164;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            s = ocNamespaceQualifierOwner.getReference().getCanonicalText();
        }
        final String s2 = s;
        List<OCTypeArgument> typeArguments = null;
        Label_0187: {
            try {
                if (ocNamespaceQualifierOwner instanceof OCTemplateArgumentsOwner) {
                    typeArguments = getTypeArguments((OCTemplateArgumentsOwner)ocNamespaceQualifierOwner);
                    break Label_0187;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            typeArguments = null;
        }
        final List<OCTypeArgument> list = typeArguments;
        OCQualifiedName ocQualifiedName2 = null;
        Label_0217: {
            try {
                if (list != null) {
                    final OCQualifiedName with;
                    ocQualifiedName2 = (with = new OCQualifiedNameWithArguments(ocQualifiedName, s2, list));
                    break Label_0217;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            OCQualifiedName with;
            ocQualifiedName2 = (with = OCQualifiedName.with(ocQualifiedName, s2));
            try {
                if (with == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "getQualifiedName"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return ocQualifiedName2;
    }
    
    @Nullable
    public static List<OCTypeArgument> getTypeArguments(@NotNull final OCTemplateArgumentsOwner ocTemplateArgumentsOwner) {
        try {
            if (ocTemplateArgumentsOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "getTypeArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ArrayList<OCTypeArgument> list = null;
        final OCTypeArgumentList templateArgumentList = ocTemplateArgumentsOwner.getTemplateArgumentList();
        if (templateArgumentList != null) {
            list = new ArrayList<OCTypeArgument>();
            final Iterator iterator = templateArgumentList.getArguments().iterator();
            while (iterator.hasNext()) {
                a((PsiElement)ocTemplateArgumentsOwner, list, iterator.next());
            }
        }
        return list;
    }
    
    private static void a(@NotNull final PsiElement psiElement, final ArrayList<OCTypeArgument> list, final OCElement ocElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "doProcessTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocElement instanceof OCGenericArgument) {
                a(psiElement, list, ((OCGenericArgument)ocElement).getTypeElement());
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocElement instanceof OCTypeElement) {
                list.add(((OCTypeElement)ocElement).getType());
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        if (ocElement instanceof OCExpression) {
            final OCFile ocFile = (OCFile)psiElement.getContainingFile();
            list.add(new OCExpressionTypeArgument(new OCBuilderDriver<ASTNode>(ocFile, OCInclusionContext.empty(ocFile.getKind(), (PsiFile)ocFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(ocElement.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)BuilderDriverBase.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)CommonProcessors.alwaysTrue()).getExpressionSymbol(ocElement.getNode(), new BuilderDriverBase.DeclarationContext<ASTNode>(null, null, null, null, (PsiElement)ocElement, false))));
        }
    }
    
    @NotNull
    public Collection<OCSymbol> getImplementationsOfSymbol(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final boolean b) {
        Label_0076: {
            try {
                if (!(ocSymbolWithQualifiedName instanceof OCStructSymbol) || !b) {
                    break Label_0076;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                protected boolean accept(final OCSymbol ocSymbol) {
                    return !ocSymbol.isPredeclaration();
                }
            };
            Collection results;
            try {
                ocSymbolWithQualifiedName.processSameSymbols((Processor)collectProcessor);
                results = collectProcessor.getResults();
                if (results == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "getImplementationsOfSymbol"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return (Collection<OCSymbol>)results;
        }
        final OCSymbol visibleImplementationOfSymbol = this.getVisibleImplementationOfSymbol(ocSymbolWithQualifiedName);
        List<OCSymbol> list = null;
        Label_0100: {
            try {
                if (visibleImplementationOfSymbol != null) {
                    final List<OCSymbol> list2;
                    list = (list2 = Collections.singletonList(visibleImplementationOfSymbol));
                    break Label_0100;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            List<OCSymbol> list2;
            list = (list2 = Collections.emptyList());
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver", "getImplementationsOfSymbol"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return (Collection<OCSymbol>)list;
    }
    
    @Nullable
    public OCSymbol getVisibleImplementationOfSymbol(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        final Class<? extends OCSymbolWithQualifiedName> class1 = ocSymbolWithQualifiedName.getClass();
        final String name = ocSymbolWithQualifiedName.getName();
        final Processor<OCSymbol> processor = (Processor<OCSymbol>)new Processor<OCSymbol>() {
            private OCQualifiedName myResolvedNameWithArguments;
            private OCQualifiedName myResolvedNameWithoutArguments;
            final /* synthetic */ OCFile val$file = (OCFile)OCSymbolReferenceResolver.this.myMemoization.getFile();
            
            public boolean process(final OCSymbol ocSymbol) {
                if (ocSymbol.isPredeclaration()) {
                    return true;
                }
                if (!((OCSymbolWithQualifiedName)ocSymbol).getClass().equals(class1)) {
                    return true;
                }
                if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                    return true;
                }
                final OCNamespaceLikeSymbol membersContainer = this.val$file.getMembersContainer(OCSymbolReferenceResolver.this.myProcessTypesOnly);
                boolean b = false;
                for (OCSymbolWithQualifiedName parent = (OCSymbolWithQualifiedName)ocSymbol; parent != null; parent = parent.getParent()) {
                    if (!membersContainer.processMembers(parent.getName(), (com.intellij.util.Processor<OCSymbol>)(ocSymbol2 -> !ocSymbol2.equals(parent)))) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    return true;
                }
                if (this.myResolvedNameWithoutArguments == null) {
                    this.myResolvedNameWithoutArguments = OCSymbolReferenceResolver.this.getResolvedQualifiedName(ocSymbolWithQualifiedName, false);
                    if (this.myResolvedNameWithoutArguments == null) {
                        return false;
                    }
                }
                if (!this.myResolvedNameWithoutArguments.equals(OCSymbolReferenceResolver.this.getResolvedQualifiedName((OCSymbolWithQualifiedName)ocSymbol, false))) {
                    return true;
                }
                if (this.myResolvedNameWithArguments == null) {
                    this.myResolvedNameWithArguments = OCSymbolReferenceResolver.this.getResolvedQualifiedName(ocSymbolWithQualifiedName, true);
                    if (this.myResolvedNameWithArguments == null) {
                        return false;
                    }
                }
                return !this.myResolvedNameWithArguments.equals(OCSymbolReferenceResolver.this.getResolvedQualifiedName((OCSymbolWithQualifiedName)ocSymbol, true)) || findFirstProcessor.process((Object)ocSymbol);
            }
        };
        final Project project = ocSymbolWithQualifiedName.getProject();
        try {
            if (project != null) {
                OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)processor, name);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final boolean b) {
        return ocSymbolWithQualifiedName.getResolvedQualifiedName(false, this.myMemoization, this.myProcessTypesOnly, b, false, true, true);
    }
    
    static {
        NON_USING_SYMBOL = (ocSymbol -> {
            try {
                if (!(ocSymbol instanceof OCUsingSymbol)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        });
        STOP_SET = new Class[] { OCStructLike.class, OCDeclaration.class, OCDeclarator.class, OCCppNamespace.class, OCTemplateParameterList.class, OCCppUsingStatement.class, OCConstructorFieldInitializer.class, OCClassDeclaration.class, OCMethod.class };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
