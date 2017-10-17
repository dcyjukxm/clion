// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.Arrays;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.tree.IElementType;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import java.util.Iterator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import com.intellij.patterns.ElementPattern;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCNumericType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.TokenSet;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PsiElementPattern;

public class OCReferenceCompletionContributor extends OCCompletionContributorBase
{
    public static final PsiElementPattern.Capture<PsiElement> DECLARATOR_WITH_QUALIFIER;
    public static final PsiElementPattern.Capture<PsiElement> DESTRUCTOR_DECLARATOR;
    public static final PsiElementPattern.Capture<PsiElement> NESTED_NAMESPACE;
    private static boolean ourSuggestBuiltinMacrosInTests;
    private static final List<String> BUILT_IN_MACROS_EXPRESSION;
    private static final List<String> BUILT_IN_MACROS_IB;
    private static final TokenSet IF_DIRECTIVES_WITH_CONDITION;
    
    public OCReferenceCompletionContributor() {
        final OCCompletionProvider ocCompletionProvider = new OCCompletionProvider() {
            @Override
            protected void addCompletions(final String p0, @NotNull final OCCompletionParameters p1, final ProcessingContext p2, final CompletionResultSet p3) {
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
                //    18: ldc             "parameters"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "addCompletions"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_2        
                //    45: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
                //    48: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //    53: astore          5
                //    55: aload_2        
                //    56: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getInvocationCount:()I
                //    59: iconst_2       
                //    60: invokestatic    java/lang/Math.min:(II)I
                //    63: istore          6
                //    65: aload           5
                //    67: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //    70: ifne            108
                //    73: aload           5
                //    75: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
                //    78: ifne            108
                //    81: goto            88
                //    84: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    87: athrow         
                //    88: aload           5
                //    90: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
                //    93: ifne            108
                //    96: goto            103
                //    99: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   102: athrow         
                //   103: return         
                //   104: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   107: athrow         
                //   108: aload           5
                //   110: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.getMethodReceiverExpression:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
                //   113: ifnull          121
                //   116: return         
                //   117: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   120: athrow         
                //   121: aload           5
                //   123: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
                //   128: invokeinterface com/intellij/psi/PsiFile.getOriginalFile:()Lcom/intellij/psi/PsiFile;
                //   133: astore          7
                //   135: aload           7
                //   137: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
                //   140: ifeq            160
                //   143: aload           7
                //   145: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
                //   148: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
                //   153: goto            170
                //   156: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   159: athrow         
                //   160: aload           7
                //   162: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
                //   167: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.maxLanguage:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/CLanguageKind;
                //   170: astore          8
                //   172: aload           7
                //   174: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
                //   177: ifeq            197
                //   180: aload           7
                //   182: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
                //   185: invokeinterface com/jetbrains/cidr/lang/psi/OCCodeFragment.getCompletionFilter:()Lcom/intellij/openapi/util/Condition;
                //   190: goto            198
                //   193: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   196: athrow         
                //   197: aconst_null    
                //   198: astore          9
                //   200: aload           5
                //   202: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //   207: astore          10
                //   209: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter$ConverterState;
                //   212: dup            
                //   213: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter$ConverterState.<init>:()V
                //   216: astore          11
                //   218: aload           5
                //   220: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
                //   222: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
                //   225: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
                //   228: astore          13
                //   230: aconst_null    
                //   231: astore          14
                //   233: aload           5
                //   235: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //   240: astore          15
                //   242: aload           5
                //   244: iconst_1       
                //   245: anewarray       Ljava/lang/Class;
                //   248: dup            
                //   249: iconst_0       
                //   250: ldc             Lcom/jetbrains/cidr/lang/psi/OCDirective;.class
                //   252: aastore        
                //   253: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
                //   256: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDirective;
                //   259: astore          16
                //   261: aload           15
                //   263: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
                //   266: ifeq            344
                //   269: aload           15
                //   271: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
                //   274: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
                //   277: aload           7
                //   279: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
                //   282: astore          17
                //   284: aload           17
                //   286: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
                //   289: ifeq            314
                //   292: aload           4
                //   294: aload           17
                //   296: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
                //   299: aload           5
                //   301: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.createBlockTemplateElement:(Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/lookup/LookupElement;
                //   304: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
                //   307: goto            344
                //   310: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   313: athrow         
                //   314: aload           17
                //   316: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
                //   319: ldc             "SEL"
                //   321: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   324: ifeq            344
                //   327: aload           4
                //   329: aload           15
                //   331: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
                //   334: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.addSelectorCompletions:(Lcom/intellij/codeInsight/completion/CompletionResultSet;Lcom/jetbrains/cidr/lang/psi/OCExpression;)V
                //   337: goto            344
                //   340: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   343: athrow         
                //   344: aload           16
                //   346: ifnull          498
                //   349: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.MACRO_OR_UNDEF_OR_MACRO_PARAMETER_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   352: astore          12
                //   354: aload           16
                //   356: invokeinterface com/jetbrains/cidr/lang/psi/OCDirective.getHeaderToken:()Lcom/intellij/psi/PsiElement;
                //   361: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
                //   366: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
                //   371: astore          17
                //   373: aload           17
                //   375: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
                //   378: if_acmpeq       396
                //   381: aload           17
                //   383: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELIF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
                //   386: if_acmpne       446
                //   389: goto            396
                //   392: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   395: athrow         
                //   396: aload           5
                //   398: ldc             Lcom/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl;.class
                //   400: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
                //   403: checkcast       Lcom/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl;
                //   406: astore          18
                //   408: aload           18
                //   410: ifnonnull       446
                //   413: aload           4
                //   415: ldc             "defined"
                //   417: invokestatic    com/jetbrains/cidr/lang/editor/completion/TemplateInsertHandler.lookup:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   420: ldc             "()"
                //   422: iconst_1       
                //   423: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withTailText:(Ljava/lang/String;Z)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   426: iconst_1       
                //   427: invokestatic    com/intellij/codeInsight/completion/util/ParenthesesInsertHandler.getInstance:(Z)Lcom/intellij/codeInsight/completion/util/ParenthesesInsertHandler;
                //   430: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   433: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.bold:()Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   436: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
                //   439: goto            446
                //   442: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   445: athrow         
                //   446: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$000:()Lcom/intellij/psi/tree/TokenSet;
                //   449: aload           17
                //   451: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
                //   454: ifeq            495
                //   457: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter;
                //   460: dup            
                //   461: aload           12
                //   463: aload           11
                //   465: aload           4
                //   467: iconst_1       
                //   468: aload           5
                //   470: aconst_null    
                //   471: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter$ConverterState;Lcom/intellij/codeInsight/completion/CompletionResultSet;ZLcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Condition;)V
                //   474: astore          18
                //   476: aload           7
                //   478: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.headerContext:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
                //   481: astore          19
                //   483: ldc             "__cplusplus"
                //   485: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
                //   488: aload           19
                //   490: aload           18
                //   492: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$100:(Ljava/util/List;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;Lcom/intellij/util/Processor;)V
                //   495: goto            1302
                //   498: aload           5
                //   500: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
                //   503: ifeq            739
                //   506: aload           8
                //   508: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   511: iconst_2       
                //   512: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   515: dup            
                //   516: iconst_0       
                //   517: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   520: aastore        
                //   521: dup            
                //   522: iconst_1       
                //   523: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   526: aastore        
                //   527: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.union:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   530: astore          12
                //   532: aload           5
                //   534: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
                //   537: astore          17
                //   539: getstatic       com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns.AFTER_TILDE:Lcom/intellij/patterns/PsiElementPattern$Capture;
                //   542: aload           17
                //   544: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
                //   549: invokevirtual   com/intellij/patterns/PsiElementPattern$Capture.accepts:(Ljava/lang/Object;)Z
                //   552: ifeq            687
                //   555: aload           5
                //   557: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //   562: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //   567: astore          18
                //   569: aload           18
                //   571: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
                //   574: ifeq            643
                //   577: aload           17
                //   579: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
                //   584: ifnonnull       643
                //   587: goto            594
                //   590: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   593: athrow         
                //   594: aload           4
                //   596: new             Ljava/lang/StringBuilder;
                //   599: dup            
                //   600: invokespecial   java/lang/StringBuilder.<init>:()V
                //   603: aload           18
                //   605: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
                //   608: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getName:()Ljava/lang/String;
                //   613: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   616: ldc             "()"
                //   618: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   621: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   624: invokestatic    com/jetbrains/cidr/lang/editor/completion/TemplateInsertHandler.lookup:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   627: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.bold:()Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   630: ldc             "()"
                //   632: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withTailText:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //   635: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
                //   638: return         
                //   639: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   642: athrow         
                //   643: aload           4
                //   645: new             Ljava/lang/StringBuilder;
                //   648: dup            
                //   649: invokespecial   java/lang/StringBuilder.<init>:()V
                //   652: ldc             "~"
                //   654: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   657: aload           4
                //   659: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.getPrefixMatcher:()Lcom/intellij/codeInsight/completion/PrefixMatcher;
                //   662: invokevirtual   com/intellij/codeInsight/completion/PrefixMatcher.getPrefix:()Ljava/lang/String;
                //   665: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   668: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   671: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.withPrefixMatcher:(Ljava/lang/String;)Lcom/intellij/codeInsight/completion/CompletionResultSet;
                //   674: astore          4
                //   676: aload           12
                //   678: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   681: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   684: goto            736
                //   687: aload           17
                //   689: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
                //   694: ifnull          736
                //   697: aload           12
                //   699: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   702: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   705: aload           12
                //   707: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   710: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   713: aload           12
                //   715: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   718: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   721: aload           12
                //   723: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   726: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   729: goto            736
                //   732: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   735: athrow         
                //   736: goto            1302
                //   739: aload           5
                //   741: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
                //   744: ifeq            773
                //   747: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   750: dup            
                //   751: ldc             "completion context"
                //   753: iconst_0       
                //   754: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   757: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   760: astore          12
                //   762: aload           12
                //   764: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   767: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   770: goto            1302
                //   773: aload           5
                //   775: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //   778: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getSymbolContext:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   783: astore          17
                //   785: aload           15
                //   787: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
                //   790: ifne            808
                //   793: aload           15
                //   795: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
                //   798: ifeq            1001
                //   801: goto            808
                //   804: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   807: athrow         
                //   808: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   811: dup            
                //   812: ldc             "completion context"
                //   814: iconst_0       
                //   815: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   818: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   821: astore          12
                //   823: aload           15
                //   825: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
                //   828: ifne            854
                //   831: aload           12
                //   833: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   836: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   839: aload           12
                //   841: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   844: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   847: goto            854
                //   850: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   853: athrow         
                //   854: aload           17
                //   856: ifnull          884
                //   859: aload           12
                //   861: aload           17
                //   863: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
                //   866: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
                //   869: aload           12
                //   871: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //   874: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //   877: goto            884
                //   880: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   883: athrow         
                //   884: aload           15
                //   886: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.isTypePossible:(Lcom/intellij/psi/PsiElement;)Z
                //   889: ifeq            912
                //   892: aload           12
                //   894: aload           8
                //   896: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //   899: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
                //   902: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
                //   905: goto            912
                //   908: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   911: athrow         
                //   912: iload           6
                //   914: iconst_1       
                //   915: if_icmpgt       948
                //   918: aload           15
                //   920: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //   925: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
                //   928: ifeq            948
                //   931: goto            938
                //   934: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   937: athrow         
                //   938: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
                //   943: astore          14
                //   945: goto            1302
                //   948: iload           6
                //   950: iconst_1       
                //   951: if_icmpgt       1302
                //   954: aload           15
                //   956: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
                //   959: ifeq            1302
                //   962: goto            969
                //   965: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   968: athrow         
                //   969: aload           15
                //   971: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                //   976: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
                //   979: ifne            1302
                //   982: goto            989
                //   985: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   988: athrow         
                //   989: aload           15
                //   991: invokedynamic   value:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Condition;
                //   996: astore          14
                //   998: goto            1302
                //  1001: aload           15
                //  1003: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
                //  1006: ifeq            1107
                //  1009: iload           6
                //  1011: ifne            1026
                //  1014: goto            1021
                //  1017: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1020: athrow         
                //  1021: return         
                //  1022: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1025: athrow         
                //  1026: aload           15
                //  1028: invokestatic    com/intellij/psi/util/PsiTreeUtil.firstChild:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
                //  1031: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
                //  1036: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
                //  1041: astore          18
                //  1043: aload           18
                //  1045: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ENUM_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
                //  1048: if_acmpne       1061
                //  1051: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1054: goto            1082
                //  1057: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1060: athrow         
                //  1061: aload           18
                //  1063: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNION_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
                //  1066: if_acmpne       1079
                //  1069: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1072: goto            1082
                //  1075: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1078: athrow         
                //  1079: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1082: astore          19
                //  1084: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //  1087: dup            
                //  1088: ldc             "struct like"
                //  1090: iconst_1       
                //  1091: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1094: dup            
                //  1095: iconst_0       
                //  1096: aload           19
                //  1098: aastore        
                //  1099: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //  1102: astore          12
                //  1104: goto            1302
                //  1107: aload           15
                //  1109: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //  1112: ifeq            1123
                //  1115: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.PROTOCOL_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //  1118: astore          12
                //  1120: goto            1302
                //  1123: aload           15
                //  1125: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
                //  1128: ifeq            1217
                //  1131: aload           17
                //  1133: astore          12
                //  1135: aload           15
                //  1137: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
                //  1140: astore          18
                //  1142: new             Ljava/util/HashSet;
                //  1145: dup            
                //  1146: invokespecial   java/util/HashSet.<init>:()V
                //  1149: astore          19
                //  1151: aload           18
                //  1153: invokeinterface com/jetbrains/cidr/lang/psi/OCProtocolList.getProtocols:()Ljava/util/List;
                //  1158: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
                //  1163: astore          20
                //  1165: aload           20
                //  1167: invokeinterface java/util/Iterator.hasNext:()Z
                //  1172: ifeq            1205
                //  1175: aload           20
                //  1177: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
                //  1182: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //  1185: astore          21
                //  1187: aload           19
                //  1189: aload           21
                //  1191: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
                //  1196: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
                //  1201: pop            
                //  1202: goto            1165
                //  1205: aload           19
                //  1207: invokedynamic   value:(Ljava/util/Set;)Lcom/intellij/openapi/util/Condition;
                //  1212: astore          14
                //  1214: goto            1302
                //  1217: aload           10
                //  1219: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
                //  1222: ifeq            1279
                //  1225: aload           10
                //  1227: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
                //  1230: invokeinterface com/jetbrains/cidr/lang/psi/OCCppUsingStatement.isNamespaceUsing:()Z
                //  1235: ifeq            1273
                //  1238: goto            1245
                //  1241: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1244: athrow         
                //  1245: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //  1248: dup            
                //  1249: iconst_2       
                //  1250: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1253: dup            
                //  1254: iconst_0       
                //  1255: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1258: aastore        
                //  1259: dup            
                //  1260: iconst_1       
                //  1261: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1264: aastore        
                //  1265: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:([Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //  1268: astore          12
                //  1270: goto            1302
                //  1273: aconst_null    
                //  1274: astore          12
                //  1276: goto            1302
                //  1279: aload           17
                //  1281: iconst_2       
                //  1282: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1285: dup            
                //  1286: iconst_0       
                //  1287: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1290: aastore        
                //  1291: dup            
                //  1292: iconst_1       
                //  1293: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1296: aastore        
                //  1297: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.union:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
                //  1300: astore          12
                //  1302: aload           12
                //  1304: ifnull          1322
                //  1307: aload           12
                //  1309: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1312: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
                //  1315: goto            1322
                //  1318: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1321: athrow         
                //  1322: aload           5
                //  1324: invokedynamic   value:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Condition;
                //  1329: astore          17
                //  1331: aload           5
                //  1333: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
                //  1336: ifeq            1348
                //  1339: aload           14
                //  1341: goto            1355
                //  1344: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1347: athrow         
                //  1348: aload           14
                //  1350: aload           17
                //  1352: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$200:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/openapi/util/Condition;)Lcom/intellij/openapi/util/Condition;
                //  1355: astore          18
                //  1357: aload           12
                //  1359: aload           11
                //  1361: aload           4
                //  1363: iconst_1       
                //  1364: aload           5
                //  1366: aload           18
                //  1368: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter.createLookupConverter:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter$ConverterState;Lcom/intellij/codeInsight/completion/CompletionResultSet;ZLcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Condition;)Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter;
                //  1371: astore          19
                //  1373: iload           6
                //  1375: istore          20
                //  1377: iload           20
                //  1379: iconst_2       
                //  1380: if_icmpgt       2008
                //  1383: aload           5
                //  1385: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //  1388: ifeq            1414
                //  1391: goto            1398
                //  1394: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1397: athrow         
                //  1398: aload           5
                //  1400: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //  1403: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getQualifiedName:(Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //  1406: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //  1409: astore          21
                //  1411: goto            1508
                //  1414: aload           5
                //  1416: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
                //  1419: ifeq            1476
                //  1422: aload           10
                //  1424: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
                //  1427: ifeq            1476
                //  1430: goto            1437
                //  1433: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1436: athrow         
                //  1437: aload           10
                //  1439: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
                //  1442: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespace.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //  1447: astore          22
                //  1449: aload           22
                //  1451: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
                //  1454: ifeq            1470
                //  1457: aload           22
                //  1459: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
                //  1462: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //  1465: astore          21
                //  1467: goto            1473
                //  1470: aconst_null    
                //  1471: astore          21
                //  1473: goto            1508
                //  1476: aload           5
                //  1478: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;.class
                //  1480: invokestatic    com/intellij/psi/util/PsiTreeUtil.getChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
                //  1483: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
                //  1486: astore          22
                //  1488: aload           22
                //  1490: ifnull          1505
                //  1493: aload           22
                //  1495: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getQualifiedName:(Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //  1498: goto            1506
                //  1501: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1504: athrow         
                //  1505: aconst_null    
                //  1506: astore          21
                //  1508: aload           21
                //  1510: ifnull          1524
                //  1513: aload           21
                //  1515: aconst_null    
                //  1516: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //  1519: astore          22
                //  1521: goto            1696
                //  1524: aconst_null    
                //  1525: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //  1528: astore          22
                //  1530: aload           5
                //  1532: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //  1535: ifeq            1556
                //  1538: aload           5
                //  1540: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
                //  1543: aload           19
                //  1545: iconst_0       
                //  1546: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol.processThisSelfSuperInContext:(Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;Lcom/intellij/util/Processor;Z)V
                //  1549: goto            1556
                //  1552: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1555: athrow         
                //  1556: aload           10
                //  1558: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
                //  1561: ifeq            1604
                //  1564: aload           10
                //  1566: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
                //  1569: invokeinterface com/jetbrains/cidr/lang/psi/OCCppUsingStatement.isNamespaceUsing:()Z
                //  1574: ifne            1604
                //  1577: goto            1584
                //  1580: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1583: athrow         
                //  1584: aload           4
                //  1586: ldc             "namespace"
                //  1588: getstatic       com/jetbrains/cidr/lang/editor/completion/OCCompletionPriority.NORMAL_KEYWORDS_PRIORITY:Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionPriority;
                //  1591: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCCompletionPriority.keywordWithPriority:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionPriority;)Lcom/intellij/codeInsight/lookup/LookupElement;
                //  1594: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
                //  1597: goto            1604
                //  1600: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1603: athrow         
                //  1604: aload           9
                //  1606: ifnonnull       1696
                //  1609: aload           7
                //  1611: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.headerContext:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
                //  1614: astore          23
                //  1616: aload           13
                //  1618: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
                //  1621: ifne            1639
                //  1624: aload           13
                //  1626: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
                //  1629: ifeq            1656
                //  1632: goto            1639
                //  1635: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1638: athrow         
                //  1639: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$300:()Ljava/util/List;
                //  1642: aload           23
                //  1644: aload           19
                //  1646: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$100:(Ljava/util/List;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;Lcom/intellij/util/Processor;)V
                //  1649: goto            1656
                //  1652: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1655: athrow         
                //  1656: aload           12
                //  1658: ifnull          1679
                //  1661: aload           12
                //  1663: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //  1666: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.isSuitableSymbolKind:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Z
                //  1669: ifeq            1696
                //  1672: goto            1679
                //  1675: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1678: athrow         
                //  1679: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$400:()Ljava/util/List;
                //  1682: aload           23
                //  1684: aload           19
                //  1686: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$100:(Ljava/util/List;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;Lcom/intellij/util/Processor;)V
                //  1689: goto            1696
                //  1692: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1695: athrow         
                //  1696: aload           4
                //  1698: new             Ljava/lang/StringBuilder;
                //  1701: dup            
                //  1702: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1705: ldc             "Press "
                //  1707: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1710: ldc             "SmartTypeCompletion"
                //  1712: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$500:(Ljava/lang/String;)Ljava/lang/String;
                //  1715: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1718: ldc             " to filter results by type"
                //  1720: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1723: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1726: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addLookupAdvertisement:(Ljava/lang/String;)V
                //  1729: ldc             "_"
                //  1731: aload_1        
                //  1732: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //  1735: ifeq            1831
                //  1738: iload           20
                //  1740: iconst_1       
                //  1741: if_icmpne       1831
                //  1744: goto            1751
                //  1747: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1750: athrow         
                //  1751: aload           15
                //  1753: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
                //  1756: ifne            1831
                //  1759: goto            1766
                //  1762: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1765: athrow         
                //  1766: iload           20
                //  1768: iconst_1       
                //  1769: if_icmpne       1819
                //  1772: goto            1779
                //  1775: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1778: athrow         
                //  1779: aload           4
                //  1781: new             Ljava/lang/StringBuilder;
                //  1784: dup            
                //  1785: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1788: ldc             "Press "
                //  1790: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1793: ldc             "CodeCompletion"
                //  1795: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$600:(Ljava/lang/String;)Ljava/lang/String;
                //  1798: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1801: ldc             " again for global symbols"
                //  1803: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1806: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1809: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addLookupAdvertisement:(Ljava/lang/String;)V
                //  1812: goto            1819
                //  1815: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1818: athrow         
                //  1819: aconst_null    
                //  1820: aload           5
                //  1822: aload           19
                //  1824: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.processLocalAndMemberSymbols:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Processor;)Z
                //  1827: pop            
                //  1828: goto            1978
                //  1831: iload           20
                //  1833: iconst_1       
                //  1834: if_icmpgt       1877
                //  1837: aload           4
                //  1839: new             Ljava/lang/StringBuilder;
                //  1842: dup            
                //  1843: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1846: ldc             "Press "
                //  1848: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1851: ldc             "CodeCompletion"
                //  1853: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$700:(Ljava/lang/String;)Ljava/lang/String;
                //  1856: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1859: ldc             " again for not-imported symbols"
                //  1861: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1864: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1867: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addLookupAdvertisement:(Ljava/lang/String;)V
                //  1870: goto            1877
                //  1873: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1876: athrow         
                //  1877: aload           22
                //  1879: aload           5
                //  1881: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
                //  1884: astore          23
                //  1886: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
                //  1889: dup            
                //  1890: aload           5
                //  1892: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
                //  1895: astore          24
                //  1897: aload           24
                //  1899: iconst_1       
                //  1900: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setIncompleteMode:(Z)V
                //  1903: aload           23
                //  1905: aload           19
                //  1907: aload           24
                //  1909: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.processPossibleSymbols:(Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
                //  1912: pop            
                //  1913: iload           20
                //  1915: iconst_1       
                //  1916: if_icmple       1978
                //  1919: aload           14
                //  1921: astore          25
                //  1923: aload           25
                //  1925: invokedynamic   value:(Lcom/intellij/openapi/util/Condition;)Lcom/intellij/openapi/util/Condition;
                //  1930: astore          14
                //  1932: aload           24
                //  1934: iconst_1       
                //  1935: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setProcessNonImported:(Z)V
                //  1938: aload           12
                //  1940: aload           11
                //  1942: aload           4
                //  1944: iconst_0       
                //  1945: aload           5
                //  1947: aload           14
                //  1949: aload           9
                //  1951: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.access$200:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/openapi/util/Condition;)Lcom/intellij/openapi/util/Condition;
                //  1954: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter.createLookupConverter:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter$ConverterState;Lcom/intellij/codeInsight/completion/CompletionResultSet;ZLcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Condition;)Lcom/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter;
                //  1957: astore          26
                //  1959: aload           24
                //  1961: aload           23
                //  1963: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Ljava/util/List;
                //  1966: aload           26
                //  1968: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
                //  1971: pop            
                //  1972: aload           26
                //  1974: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter.finish:()Z
                //  1977: pop            
                //  1978: aload           19
                //  1980: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter.finish:()Z
                //  1983: pop            
                //  1984: aload           19
                //  1986: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCSymbolsToLookupConverter.isEmpty:()Z
                //  1989: ifne            1999
                //  1992: goto            2008
                //  1995: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //  1998: athrow         
                //  1999: aconst_null    
                //  2000: astore          14
                //  2002: iinc            20, 1
                //  2005: goto            1377
                //  2008: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  65     81     84     88     Ljava/lang/IllegalArgumentException;
                //  73     96     99     103    Ljava/lang/IllegalArgumentException;
                //  88     104    104    108    Ljava/lang/IllegalArgumentException;
                //  108    117    117    121    Ljava/lang/IllegalArgumentException;
                //  135    156    156    160    Ljava/lang/IllegalArgumentException;
                //  172    193    193    197    Ljava/lang/IllegalArgumentException;
                //  284    310    310    314    Ljava/lang/IllegalArgumentException;
                //  314    337    340    344    Ljava/lang/IllegalArgumentException;
                //  373    389    392    396    Ljava/lang/IllegalArgumentException;
                //  408    439    442    446    Ljava/lang/IllegalArgumentException;
                //  569    587    590    594    Ljava/lang/IllegalArgumentException;
                //  577    639    639    643    Ljava/lang/IllegalArgumentException;
                //  687    729    732    736    Ljava/lang/IllegalArgumentException;
                //  785    801    804    808    Ljava/lang/IllegalArgumentException;
                //  823    847    850    854    Ljava/lang/IllegalArgumentException;
                //  854    877    880    884    Ljava/lang/IllegalArgumentException;
                //  884    905    908    912    Ljava/lang/IllegalArgumentException;
                //  912    931    934    938    Ljava/lang/IllegalArgumentException;
                //  948    962    965    969    Ljava/lang/IllegalArgumentException;
                //  954    982    985    989    Ljava/lang/IllegalArgumentException;
                //  1001   1014   1017   1021   Ljava/lang/IllegalArgumentException;
                //  1009   1022   1022   1026   Ljava/lang/IllegalArgumentException;
                //  1043   1057   1057   1061   Ljava/lang/IllegalArgumentException;
                //  1061   1075   1075   1079   Ljava/lang/IllegalArgumentException;
                //  1217   1238   1241   1245   Ljava/lang/IllegalArgumentException;
                //  1302   1315   1318   1322   Ljava/lang/IllegalArgumentException;
                //  1331   1344   1344   1348   Ljava/lang/IllegalArgumentException;
                //  1377   1391   1394   1398   Ljava/lang/IllegalArgumentException;
                //  1414   1430   1433   1437   Ljava/lang/IllegalArgumentException;
                //  1488   1501   1501   1505   Ljava/lang/IllegalArgumentException;
                //  1530   1549   1552   1556   Ljava/lang/IllegalArgumentException;
                //  1556   1577   1580   1584   Ljava/lang/IllegalArgumentException;
                //  1564   1597   1600   1604   Ljava/lang/IllegalArgumentException;
                //  1616   1632   1635   1639   Ljava/lang/IllegalArgumentException;
                //  1624   1649   1652   1656   Ljava/lang/IllegalArgumentException;
                //  1656   1672   1675   1679   Ljava/lang/IllegalArgumentException;
                //  1661   1689   1692   1696   Ljava/lang/IllegalArgumentException;
                //  1696   1744   1747   1751   Ljava/lang/IllegalArgumentException;
                //  1738   1759   1762   1766   Ljava/lang/IllegalArgumentException;
                //  1751   1772   1775   1779   Ljava/lang/IllegalArgumentException;
                //  1766   1812   1815   1819   Ljava/lang/IllegalArgumentException;
                //  1831   1870   1873   1877   Ljava/lang/IllegalArgumentException;
                //  1978   1995   1995   1999   Ljava/lang/IllegalArgumentException;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
        };
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)((PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.REFERENCE_ELEMENT.andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT)).andNot((ElementPattern)OCCompletionPatterns.AT_CLASS_FUNCTION_BODY_PLACEHOLDER)).andNot((ElementPattern)OCNewExpressionCompletionContributor.TYPE_IN_NEW_EXPRESSION), ocCompletionProvider);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.TYPE_IN_DECLARATION.andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT), ocCompletionProvider);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCReferenceCompletionContributor.DECLARATOR_WITH_QUALIFIER, ocCompletionProvider);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCReferenceCompletionContributor.DESTRUCTOR_DECLARATOR, ocCompletionProvider);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCReferenceCompletionContributor.NESTED_NAMESPACE, ocCompletionProvider);
    }
    
    public static boolean isTypePossible(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //     9: ifne            111
        //    12: aload_0        
        //    13: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    16: ifeq            45
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //    35: ifne            111
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    51: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //    54: ifeq            119
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_0        
        //    65: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    70: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    75: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    78: ifeq            119
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: iconst_1       
        //    90: anewarray       Ljava/lang/Class;
        //    93: dup            
        //    94: iconst_0       
        //    95: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;.class
        //    97: aastore        
        //    98: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   101: ifnonnull       119
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: iconst_1       
        //   112: goto            120
        //   115: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: iconst_0       
        //   120: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  12     38     41     45     Ljava/lang/IllegalArgumentException;
        //  26     57     60     64     Ljava/lang/IllegalArgumentException;
        //  45     81     84     88     Ljava/lang/IllegalArgumentException;
        //  64     104    107    111    Ljava/lang/IllegalArgumentException;
        //  88     115    115    119    Ljava/lang/IllegalArgumentException;
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
    
    private static Condition<OCSymbol> a(final Condition<OCSymbol> condition, final Condition<OCSymbol> condition2) {
        try {
            if (condition == null) {
                return condition2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (condition2 == null) {
                return condition;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Condition<OCSymbol>)(ocSymbol -> {
            Label_0027: {
                try {
                    if (!condition.value((Object)ocSymbol)) {
                        return false;
                    }
                    final Condition condition3 = condition2;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = condition3.value((Object)ocSymbol2);
                    if (b) {
                        break Label_0027;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final Condition condition3 = condition2;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = condition3.value((Object)ocSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        });
    }
    
    private static void a(@NotNull final List<String> list, @NotNull final OCImmutableInclusionContext ocImmutableInclusionContext, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "macros", "com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor", "processBuiltinMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inclusionContext", "com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor", "processBuiltinMacros"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor", "processBuiltinMacros"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Label_0167: {
            try {
                if (!ApplicationManager.getApplication().isUnitTestMode()) {
                    break Label_0167;
                }
                final boolean b = OCReferenceCompletionContributor.ourSuggestBuiltinMacrosInTests;
                final Boolean b2 = Boolean.TRUE;
                final boolean b3 = b2;
                if (b != b3) {
                    return;
                }
                break Label_0167;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final boolean b = OCReferenceCompletionContributor.ourSuggestBuiltinMacrosInTests;
                final Boolean b2 = Boolean.TRUE;
                final boolean b3 = b2;
                if (b != b3) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final OCMacroSymbol definition = ocImmutableInclusionContext.getDefinition(iterator.next());
            try {
                if (definition == null) {
                    continue;
                }
                processor.process((Object)definition);
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
    }
    
    public static void setSuggestBuiltInMacrosInTests(final boolean ourSuggestBuiltinMacrosInTests) {
        OCReferenceCompletionContributor.ourSuggestBuiltinMacrosInTests = ourSuggestBuiltinMacrosInTests;
    }
    
    public boolean invokeAutoPopup(@NotNull final PsiElement p0, final char p1) {
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
        //    18: ldc             "position"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invokeAutoPopup"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    50: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    55: astore_3       
        //    56: bipush          62
        //    58: iload_2        
        //    59: if_icmpne       85
        //    62: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    65: aload_3        
        //    66: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    69: ifeq            85
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: iconst_1       
        //    80: ireturn        
        //    81: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: bipush          58
        //    87: iload_2        
        //    88: if_icmpne       133
        //    91: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    94: aload_3        
        //    95: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    98: ifeq            133
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_1        
        //   109: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   114: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   117: ifne            133
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: iconst_1       
        //   128: ireturn        
        //   129: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   139: astore          4
        //   141: bipush          42
        //   143: iload_2        
        //   144: if_icmpne       246
        //   147: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   150: aload_3        
        //   151: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   154: ifne            181
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   167: aload_3        
        //   168: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   171: ifeq            246
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload           4
        //   183: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   186: ifeq            246
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload_1        
        //   197: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   202: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.areSymbolsLoaded:(Lcom/intellij/openapi/project/Project;)Z
        //   205: ifeq            246
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload           4
        //   217: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   220: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   225: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //   230: ifeq            246
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: iconst_1       
        //   241: ireturn        
        //   242: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCReferenceCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: iconst_0       
        //   247: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     72     75     79     Ljava/lang/IllegalArgumentException;
        //  62     81     81     85     Ljava/lang/IllegalArgumentException;
        //  85     101    104    108    Ljava/lang/IllegalArgumentException;
        //  91     120    123    127    Ljava/lang/IllegalArgumentException;
        //  108    129    129    133    Ljava/lang/IllegalArgumentException;
        //  141    157    160    164    Ljava/lang/IllegalArgumentException;
        //  147    174    177    181    Ljava/lang/IllegalArgumentException;
        //  164    189    192    196    Ljava/lang/IllegalArgumentException;
        //  181    208    211    215    Ljava/lang/IllegalArgumentException;
        //  196    233    236    240    Ljava/lang/IllegalArgumentException;
        //  215    242    242    246    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    static {
        DECLARATOR_WITH_QUALIFIER = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement((IElementType)OCTokenTypes.IDENTIFIER).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT)).withParent((Class)OCDeclarator.class)).afterLeaf(new String[] { "::" })).andNot((ElementPattern)OCCompletionPatterns.TYPE_IN_DECLARATION);
        DESTRUCTOR_DECLARATOR = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement((IElementType)OCTokenTypes.IDENTIFIER).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT)).withParent((Class)OCDeclarator.class)).and((ElementPattern)OCCompletionPatterns.AFTER_TILDE);
        NESTED_NAMESPACE = (PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement((IElementType)OCTokenTypes.IDENTIFIER).afterLeaf(new String[] { "::" })).withParent((Class)OCCppNamespace.class);
        OCReferenceCompletionContributor.ourSuggestBuiltinMacrosInTests = false;
        BUILT_IN_MACROS_EXPRESSION = Arrays.asList("__PRETTY_FUNCTION__", "__FUNCTION__", "__func__", "__DATE__", "__TIME__", "__TIMESTAMP__", "__FILE__", "__LINE__", "__COUNTER__", "__BASE_FILE__", "__EXCEPTIONS", "va_start", "va_end", "va_arg", "va_copy");
        BUILT_IN_MACROS_IB = Arrays.asList("IBOutlet", "IBOutletCollection", "IBAction");
        IF_DIRECTIVES_WITH_CONDITION = TokenSet.create(new IElementType[] { OCTokenTypes.IFDEF_DIRECTIVE, OCTokenTypes.IFNDEF_DIRECTIVE, OCTokenTypes.IF_DIRECTIVE, OCTokenTypes.ELIF_DIRECTIVE });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
