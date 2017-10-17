// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.search.OCMemberInheritorsSearch;
import java.util.HashSet;
import com.jetbrains.cidr.lang.psi.OCGenericParameter;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.impl.OCMacroParameterImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDefineDirectiveImpl;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import com.intellij.lexer.Lexer;
import com.jetbrains.cidr.lang.quickfixes.OCProvideStringLocalizationsIntentionAction;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.resolve.references.OCStringResourceReference;
import com.jetbrains.cidr.lang.resolve.references.OCFileResourceReference;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.resolve.references.OCCompositeResourceReference;
import com.jetbrains.cidr.lang.editor.colors.OCFileHighlighter;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.quickfixes.OCCreateNewDefinitionIntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCChangeMethodSignatureIntentionAction;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCPointerType;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.resolve.OCSelectorAdHocResolver;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedDesignator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCPredeclareSymbolIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCCppQualifiedPointer;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.jetbrains.cidr.lang.editor.colors.OCHighlightingKeys;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.OCSymbolContext;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCResolveAnnotator extends OCAnnotator
{
    @Nullable
    private Annotation a(@NotNull final PsiElement p0, @Nullable final String p1, @Nullable final OCSymbolGroupContext p2, @Nullable final String p3, @Nullable final Class<? extends OCInspection> p4) {
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
        //    18: ldc             "psiElement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCResolveAnnotator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkReferences"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       73
        //    48: aload_3        
        //    49: ifnull          70
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_3        
        //    60: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getCannotResolveMessagePrefix:()Ljava/lang/String;
        //    63: goto            72
        //    66: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: ldc             "Can't resolve"
        //    72: astore_2       
        //    73: aload_1        
        //    74: invokeinterface com/intellij/psi/PsiElement.getReferences:()[Lcom/intellij/psi/PsiReference;
        //    79: astore          6
        //    81: aload           6
        //    83: astore          7
        //    85: aload           7
        //    87: arraylength    
        //    88: istore          8
        //    90: iconst_0       
        //    91: istore          9
        //    93: iload           9
        //    95: iload           8
        //    97: if_icmpge       1007
        //   100: aload           7
        //   102: iload           9
        //   104: aaload         
        //   105: astore          10
        //   107: aconst_null    
        //   108: astore          11
        //   110: aconst_null    
        //   111: astore          12
        //   113: aload           10
        //   115: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPolyVariantReference;
        //   118: ifeq            190
        //   121: aload           10
        //   123: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPolyVariantReference;
        //   126: invokeinterface com/jetbrains/cidr/lang/psi/OCPolyVariantReference.resolveToSymbols:()Ljava/util/List;
        //   131: astore          13
        //   133: aload           13
        //   135: invokeinterface java/util/List.size:()I
        //   140: ifne            174
        //   143: aload           10
        //   145: invokeinterface com/intellij/psi/PsiReference.isSoft:()Z
        //   150: ifne            187
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload_0        
        //   161: aload_2        
        //   162: aload           10
        //   164: aload           5
        //   166: invokespecial   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Ljava/lang/String;Lcom/intellij/psi/PsiReference;Ljava/lang/Class;)Lcom/intellij/lang/annotation/Annotation;
        //   169: astore          12
        //   171: goto            187
        //   174: aload           13
        //   176: iconst_0       
        //   177: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   182: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   185: astore          11
        //   187: goto            520
        //   190: aload           10
        //   192: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReference;
        //   195: ifeq            445
        //   198: aload           10
        //   200: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReference;
        //   203: invokeinterface com/jetbrains/cidr/lang/psi/OCReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   208: astore          11
        //   210: aload           11
        //   212: ifnonnull       520
        //   215: aload_1        
        //   216: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   221: astore          13
        //   223: aload_1        
        //   224: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   227: ifeq            276
        //   230: aload_1        
        //   231: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   234: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   239: astore          14
        //   241: aload           14
        //   243: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   248: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   251: ifeq            273
        //   254: aload_0        
        //   255: aload           10
        //   257: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   260: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   263: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiReference;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //   266: goto            1001
        //   269: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: goto            383
        //   276: aload_1        
        //   277: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   280: ifeq            383
        //   283: aload_1        
        //   284: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   287: astore          14
        //   289: iconst_0       
        //   290: istore          15
        //   292: aload           14
        //   294: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   299: astore          16
        //   301: aload           16
        //   303: ifnull          383
        //   306: aload           16
        //   308: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.resolveToSymbols:()Ljava/util/List;
        //   313: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   318: astore          17
        //   320: aload           17
        //   322: invokeinterface java/util/Iterator.hasNext:()Z
        //   327: ifeq            359
        //   330: aload           17
        //   332: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   337: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   340: astore          18
        //   342: aload           18
        //   344: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   347: ifeq            356
        //   350: iconst_1       
        //   351: istore          15
        //   353: goto            359
        //   356: goto            320
        //   359: iload           15
        //   361: ifeq            383
        //   364: aload_0        
        //   365: aload           10
        //   367: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   370: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   373: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiReference;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //   376: goto            1001
        //   379: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   382: athrow         
        //   383: aload           13
        //   385: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   388: ifne            442
        //   391: aload           10
        //   393: invokeinterface com/intellij/psi/PsiReference.isSoft:()Z
        //   398: ifne            442
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aload_1        
        //   409: iconst_1       
        //   410: anewarray       Ljava/lang/Class;
        //   413: dup            
        //   414: iconst_0       
        //   415: ldc             Lcom/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl;.class
        //   417: aastore        
        //   418: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   421: ifnonnull       442
        //   424: goto            431
        //   427: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   430: athrow         
        //   431: aload_0        
        //   432: aload_2        
        //   433: aload           10
        //   435: aload           5
        //   437: invokespecial   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Ljava/lang/String;Lcom/intellij/psi/PsiReference;Ljava/lang/Class;)Lcom/intellij/lang/annotation/Annotation;
        //   440: astore          12
        //   442: goto            520
        //   445: aload           10
        //   447: invokeinterface com/intellij/psi/PsiReference.resolve:()Lcom/intellij/psi/PsiElement;
        //   452: ifnonnull       520
        //   455: aload           10
        //   457: instanceof      Lcom/intellij/psi/PsiPolyVariantReference;
        //   460: ifeq            499
        //   463: goto            470
        //   466: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   469: athrow         
        //   470: aload           10
        //   472: checkcast       Lcom/intellij/psi/PsiPolyVariantReference;
        //   475: iconst_0       
        //   476: invokeinterface com/intellij/psi/PsiPolyVariantReference.multiResolve:(Z)[Lcom/intellij/psi/ResolveResult;
        //   481: arraylength    
        //   482: ifle            499
        //   485: goto            492
        //   488: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   491: athrow         
        //   492: goto            1001
        //   495: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   498: athrow         
        //   499: aload           10
        //   501: invokeinterface com/intellij/psi/PsiReference.isSoft:()Z
        //   506: ifne            520
        //   509: aload_0        
        //   510: aload_2        
        //   511: aload           10
        //   513: aload           5
        //   515: invokespecial   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Ljava/lang/String;Lcom/intellij/psi/PsiReference;Ljava/lang/Class;)Lcom/intellij/lang/annotation/Annotation;
        //   518: astore          12
        //   520: aload           12
        //   522: ifnull          623
        //   525: aload_3        
        //   526: ifnull          620
        //   529: goto            536
        //   532: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   535: athrow         
        //   536: aload_3        
        //   537: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //   540: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   545: astore          13
        //   547: aload           13
        //   549: invokeinterface java/util/Iterator.hasNext:()Z
        //   554: ifeq            620
        //   557: aload           13
        //   559: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   564: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   567: astore          14
        //   569: aload           4
        //   571: ifnull          583
        //   574: aload           4
        //   576: goto            590
        //   579: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   582: athrow         
        //   583: aload           10
        //   585: invokeinterface com/intellij/psi/PsiReference.getCanonicalText:()Ljava/lang/String;
        //   590: astore          15
        //   592: aload_0        
        //   593: aload_1        
        //   594: aload           12
        //   596: aload           14
        //   598: aload           15
        //   600: invokespecial   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/lang/annotation/Annotation;Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;Ljava/lang/String;)V
        //   603: aload_0        
        //   604: aload           12
        //   606: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction;
        //   609: dup            
        //   610: aload_1        
        //   611: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   614: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   617: goto            547
        //   620: aload           12
        //   622: areturn        
        //   623: aload           11
        //   625: ifnull          1001
        //   628: aload           11
        //   630: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   633: ifeq            659
        //   636: goto            643
        //   639: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aload           11
        //   645: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   648: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
        //   651: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   654: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   657: astore          11
        //   659: aload           11
        //   661: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol;
        //   664: ifeq            682
        //   667: aload_0        
        //   668: aload           10
        //   670: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.SELFSUPERTHIS:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   673: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiReference;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //   676: aconst_null    
        //   677: areturn        
        //   678: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   681: athrow         
        //   682: aload           11
        //   684: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   689: astore          13
        //   691: aload           13
        //   693: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   696: if_acmpne       734
        //   699: aload_1        
        //   700: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   705: ifle            787
        //   708: goto            715
        //   711: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   714: athrow         
        //   715: aload_0        
        //   716: aload_1        
        //   717: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   720: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   723: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/lang/annotation/Annotation;
        //   726: pop            
        //   727: goto            787
        //   730: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   733: athrow         
        //   734: aload           11
        //   736: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   739: ifeq            776
        //   742: aload           11
        //   744: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   747: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //   750: ifeq            776
        //   753: goto            760
        //   756: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   759: athrow         
        //   760: aload_0        
        //   761: aload           10
        //   763: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OVERLOADED_OPERATOR:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   766: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiReference;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //   769: goto            787
        //   772: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   775: athrow         
        //   776: aload_0        
        //   777: aload           10
        //   779: aload           13
        //   781: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   784: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiReference;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //   787: aload           11
        //   789: aload_1        
        //   790: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isForbiddenByARC:(Lcom/intellij/psi/PsiElement;)Z
        //   795: ifeq            895
        //   798: aload_0        
        //   799: aload_1        
        //   800: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //   802: ldc             "CIDR"
        //   804: new             Ljava/lang/StringBuilder;
        //   807: dup            
        //   808: invokespecial   java/lang/StringBuilder.<init>:()V
        //   811: ldc             "Explicit usage of '"
        //   813: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   816: aload           11
        //   818: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   823: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   826: ldc             "' is forbidden in ARC"
        //   828: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   831: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   834: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   837: astore          12
        //   839: aload_1        
        //   840: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   843: ifeq            870
        //   846: aload_0        
        //   847: aload           12
        //   849: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction;
        //   852: dup            
        //   853: aload_1        
        //   854: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   857: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;)V
        //   860: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   863: goto            870
        //   866: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   869: athrow         
        //   870: aload_0        
        //   871: aload           12
        //   873: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction;
        //   876: dup            
        //   877: aload_1        
        //   878: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   883: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   886: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //   889: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   892: goto            1007
        //   895: aload           11
        //   897: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isUnavailable:()Z
        //   902: ifeq            929
        //   905: aload_0        
        //   906: aload_1        
        //   907: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DeprecatedAPI;.class
        //   909: ldc             "CIDR"
        //   911: aload           11
        //   913: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getUnavailableMessage:()Ljava/lang/String;
        //   918: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   921: pop            
        //   922: goto            966
        //   925: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   928: athrow         
        //   929: aload           11
        //   931: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDeprecated:()Z
        //   936: ifeq            966
        //   939: aload_0        
        //   940: aload_1        
        //   941: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DeprecatedAPI;.class
        //   943: ldc             "warn_deprecated"
        //   945: aload           11
        //   947: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getDeprecatedMessage:()Ljava/lang/String;
        //   952: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_DEPRECATED:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   955: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   958: pop            
        //   959: goto            966
        //   962: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   965: athrow         
        //   966: aload           11
        //   968: aload_1        
        //   969: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.checkAvailability:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   972: astore          14
        //   974: aload           14
        //   976: ifnull          1001
        //   979: aload_0        
        //   980: aload_1        
        //   981: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnavailableInDeploymentTarget;.class
        //   983: ldc             "CIDR"
        //   985: aload           14
        //   987: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_DEPRECATED:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   990: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   993: pop            
        //   994: goto            1007
        //   997: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1000: athrow         
        //  1001: iinc            9, 1
        //  1004: goto            93
        //  1007: aconst_null    
        //  1008: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;Ljava/lang/String;Ljava/lang/Class<+Lcom/jetbrains/cidr/lang/inspections/OCInspection;>;)Lcom/intellij/lang/annotation/Annotation;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     52     55     59     Ljava/lang/IllegalArgumentException;
        //  48     66     66     70     Ljava/lang/IllegalArgumentException;
        //  133    153    156    160    Ljava/lang/IllegalArgumentException;
        //  241    269    269    273    Ljava/lang/IllegalArgumentException;
        //  359    379    379    383    Ljava/lang/IllegalArgumentException;
        //  383    401    404    408    Ljava/lang/IllegalArgumentException;
        //  391    424    427    431    Ljava/lang/IllegalArgumentException;
        //  445    463    466    470    Ljava/lang/IllegalArgumentException;
        //  455    485    488    492    Ljava/lang/IllegalArgumentException;
        //  470    495    495    499    Ljava/lang/IllegalArgumentException;
        //  520    529    532    536    Ljava/lang/IllegalArgumentException;
        //  569    579    579    583    Ljava/lang/IllegalArgumentException;
        //  623    636    639    643    Ljava/lang/IllegalArgumentException;
        //  659    678    678    682    Ljava/lang/IllegalArgumentException;
        //  691    708    711    715    Ljava/lang/IllegalArgumentException;
        //  699    730    730    734    Ljava/lang/IllegalArgumentException;
        //  734    753    756    760    Ljava/lang/IllegalArgumentException;
        //  742    772    772    776    Ljava/lang/IllegalArgumentException;
        //  839    863    866    870    Ljava/lang/IllegalArgumentException;
        //  895    925    925    929    Ljava/lang/IllegalArgumentException;
        //  929    959    962    966    Ljava/lang/IllegalArgumentException;
        //  974    997    997    1001   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0470:
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
    
    private void a(final PsiElement p0, final Annotation p1, final OCSymbolContext p2, final String p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolContext.getSymbolKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     7: if_acmpne       312
        //    10: aload_1        
        //    11: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    14: ifeq            312
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
        //    27: dup            
        //    28: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
        //    31: aload_1        
        //    32: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //    35: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //    38: if_acmpne       56
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iconst_1       
        //    49: goto            57
        //    52: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: iconst_0       
        //    57: istore          5
        //    59: aload_1        
        //    60: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    63: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    68: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTypeContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //    73: astore          6
        //    75: aload_3        
        //    76: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolContext.getExpectedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    79: astore          7
        //    81: aload           6
        //    83: ifnull          98
        //    86: aload           7
        //    88: ifnonnull       103
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: return         
        //    99: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload           7
        //   105: aload_1        
        //   106: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   114: astore          7
        //   116: iload           5
        //   118: ifeq            131
        //   121: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   124: goto            133
        //   127: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload           7
        //   133: astore          8
        //   135: iload           5
        //   137: ifeq            152
        //   140: aload           4
        //   142: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getObjCSetterFromGetter:(Ljava/lang/String;)Ljava/lang/String;
        //   145: goto            154
        //   148: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload           4
        //   154: astore          9
        //   156: aload           6
        //   158: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getStaticMode:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //   161: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //   164: if_acmpne       176
        //   167: ldc             "+"
        //   169: goto            178
        //   172: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: ldc             "-"
        //   178: astore          10
        //   180: new             Ljava/lang/StringBuilder;
        //   183: dup            
        //   184: invokespecial   java/lang/StringBuilder.<init>:()V
        //   187: astore          11
        //   189: aload           11
        //   191: aload           10
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: bipush          40
        //   198: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   201: aload           8
        //   203: aload_1        
        //   204: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: bipush          41
        //   212: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   215: aload           9
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop            
        //   221: iload           5
        //   223: ifeq            260
        //   226: aload           11
        //   228: bipush          40
        //   230: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   233: aload           7
        //   235: aload_1        
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: bipush          41
        //   244: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   247: aload           4
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: pop            
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload_0        
        //   261: aload_2        
        //   262: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   265: dup            
        //   266: aload_1        
        //   267: aload_3        
        //   268: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   271: new             Ljava/lang/StringBuilder;
        //   274: dup            
        //   275: invokespecial   java/lang/StringBuilder.<init>:()V
        //   278: aload           10
        //   280: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: aload           9
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: aload           11
        //   293: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   296: aload           8
        //   298: aload           6
        //   300: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   303: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCObjectType;)V
        //   306: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   309: goto            361
        //   312: aload_3        
        //   313: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolContext.getSymbolKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   316: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCHighPriorityCreateNewDefinitionIntentionAction.isHighPrioritySymbolKind:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Z
        //   319: ifeq            345
        //   322: aload_0        
        //   323: aload_2        
        //   324: new             Lcom/jetbrains/cidr/lang/quickfixes/OCHighPriorityCreateNewDefinitionIntentionAction;
        //   327: dup            
        //   328: aload_3        
        //   329: aload_1        
        //   330: aload           4
        //   332: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCHighPriorityCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   335: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   338: goto            361
        //   341: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload_0        
        //   346: aload_2        
        //   347: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   350: dup            
        //   351: aload_3        
        //   352: aload_1        
        //   353: aload           4
        //   355: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   358: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   361: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     41     44     48     Ljava/lang/IllegalArgumentException;
        //  24     52     52     56     Ljava/lang/IllegalArgumentException;
        //  81     91     94     98     Ljava/lang/IllegalArgumentException;
        //  86     99     99     103    Ljava/lang/IllegalArgumentException;
        //  116    127    127    131    Ljava/lang/IllegalArgumentException;
        //  135    148    148    152    Ljava/lang/IllegalArgumentException;
        //  156    172    172    176    Ljava/lang/IllegalArgumentException;
        //  189    253    256    260    Ljava/lang/IllegalArgumentException;
        //  312    341    341    345    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    private Annotation a(final String s, final PsiReference psiReference, @Nullable final Class<? extends OCInspection> clazz) {
        final TextRange rangeInElement = psiReference.getRangeInElement();
        final TextRange from = TextRange.from(psiReference.getElement().getTextOffset() + rangeInElement.getStartOffset(), rangeInElement.getLength());
        try {
            if (clazz != null) {
                return this.addWarningAnnotation(psiReference.getElement(), from, clazz, "CIDR", s + " '" + psiReference.getCanonicalText() + "'", ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.addErrorAnnotation(psiReference.getElement(), from, OCInspections.CannotResolve.class, "CIDR", s + " '" + psiReference.getCanonicalText() + "'", ProblemHighlightType.LIKE_UNKNOWN_SYMBOL);
    }
    
    @Override
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        final PsiElement nameIdentifier = ocClassDeclaration.getNameIdentifier();
        OCResolveAnnotator ocResolveAnnotator = null;
        TextRange textRange = null;
        TextAttributesKey textAttributesKey = null;
        Label_0045: {
            Label_0032: {
                try {
                    if (nameIdentifier == null) {
                        return;
                    }
                    ocResolveAnnotator = this;
                    final PsiElement psiElement = nameIdentifier;
                    textRange = psiElement.getTextRange();
                    final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                    final boolean b = ocClassDeclaration2 instanceof OCProtocol;
                    if (b) {
                        break Label_0032;
                    }
                    break Label_0032;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    ocResolveAnnotator = this;
                    final PsiElement psiElement = nameIdentifier;
                    textRange = psiElement.getTextRange();
                    final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                    final boolean b = ocClassDeclaration2 instanceof OCProtocol;
                    if (b) {
                        textAttributesKey = OCHighlightingKeys.PROTOCOL_REFERENCE;
                        break Label_0045;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            textAttributesKey = OCHighlightingKeys.CLASS_REFERENCE;
        }
        ocResolveAnnotator.highlight(textRange, textAttributesKey);
    }
    
    @Nullable
    public Annotation checkNamespaceQualifierOwnerElement(final OCCppNamespaceQualifier ocCppNamespaceQualifier) {
        final OCCppNamespaceQualifier namespaceQualifier = ocCppNamespaceQualifier.getNamespaceQualifier();
        if (namespaceQualifier != null) {
            final Annotation checkNamespaceQualifierOwnerElement = this.checkNamespaceQualifierOwnerElement(namespaceQualifier);
            try {
                if (checkNamespaceQualifierOwnerElement != null) {
                    return checkNamespaceQualifierOwnerElement;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
        return this.a((PsiElement)ocCppNamespaceQualifier, "Can't resolve container", null, null, null);
    }
    
    @Override
    public void visitCppQualifiedPointer(final OCCppQualifiedPointer ocCppQualifiedPointer) {
        this.checkNamespaceQualifierOwnerElement(ocCppQualifiedPointer.getNamespaceQualifier());
        super.visitCppQualifiedPointer(ocCppQualifiedPointer);
    }
    
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        final OCCppNamespaceQualifier namespaceQualifier = ocReferenceElement.getNamespaceQualifier();
        Annotation annotation = null;
        if (namespaceQualifier != null) {
            annotation = this.checkNamespaceQualifierOwnerElement(namespaceQualifier);
        }
        Label_0072: {
            if (annotation == null) {
                annotation = this.a((PsiElement)ocReferenceElement, null, ocReferenceElement.getSymbolContext(), null, null);
                Label_0058: {
                    try {
                        if (annotation == null) {
                            break Label_0072;
                        }
                        final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                        final OCSymbol ocSymbol = ocReferenceElement2.resolveToSymbolIgnoringSymbolContext();
                        if (ocSymbol != null) {
                            break Label_0058;
                        }
                        break Label_0072;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                        final OCSymbol ocSymbol = ocReferenceElement2.resolveToSymbolIgnoringSymbolContext();
                        if (ocSymbol != null) {
                            annotation.setHighlightType(ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
            }
        }
        if (annotation != null) {
            Object o = new OCImportSymbolFix(ocReferenceElement);
            if (!((IntentionAction)o).isAvailable(ocReferenceElement.getProject(), (Editor)null, ocReferenceElement.getContainingFile())) {
                o = new OCPredeclareSymbolIntentionAction(ocReferenceElement);
            }
            this.registerQuickFix(annotation, (IntentionAction)o);
        }
    }
    
    @Override
    public void visitQualifiedDesignator(final OCQualifiedDesignator ocQualifiedDesignator) {
        try {
            if (ocQualifiedDesignator.getNameIdentifier() != null) {
                this.a((PsiElement)ocQualifiedDesignator, null, OCSymbolGroupContext.STRUCT_FIELD_CONTEXT, null, null);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        this.a((PsiElement)ocQualifiedExpression.getQualifyingElement(), "Can't resolve operator", null, null, null);
        final Annotation a = this.a((PsiElement)ocQualifiedExpression, null, ocQualifiedExpression.getSymbolContext(), null, null);
        if (a != null) {
            final OCObjectTypeContext typeContext = ocQualifiedExpression.getQualifier().getTypeContext();
            try {
                if (typeContext == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final OCClassSymbol classSymbol = typeContext.getType().getClassSymbol();
            Label_0128: {
                try {
                    if (classSymbol == null || !classSymbol.isPredeclaration()) {
                        break Label_0128;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                final OCClassSymbol definitionSymbol = classSymbol.getDefinitionSymbol();
                try {
                    if (definitionSymbol != null) {
                        this.registerQuickFix(a, (IntentionAction)new OCImportSymbolFix(ocQualifiedExpression.getNameIdentifier(), definitionSymbol));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            for (final OCProtocolSymbol ocProtocolSymbol : typeContext.getType().getAllProtocols()) {
                if (ocProtocolSymbol.isPredeclaration()) {
                    final OCClassSymbol definitionSymbol2 = ocProtocolSymbol.getDefinitionSymbol();
                    try {
                        if (definitionSymbol2 == null) {
                            continue;
                        }
                        this.registerQuickFix(a, (IntentionAction)new OCImportSymbolFix(ocQualifiedExpression.getNameIdentifier(), definitionSymbol2));
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
            }
        }
    }
    
    @Override
    public void visitImportDirective(final OCIncludeDirective ocIncludeDirective) {
        try {
            if (ocIncludeDirective.getText().isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final VirtualFile virtualFile = ocIncludeDirective.getContainingFile().getVirtualFile();
        try {
            if (virtualFile == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final Annotation a = this.a((PsiElement)ocIncludeDirective, "Cannot find", null, null, null);
        final PsiReference[] references = ocIncludeDirective.getReferences();
        try {
            if (a == null || references.length != 1) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final String canonicalText = references[0].getCanonicalText();
        final OCAutoImportHelper[] array = (OCAutoImportHelper[])Extensions.getExtensions((ExtensionPointName)OCAutoImportHelper.EP_NAME);
        for (int length = array.length, i = 0; i < length; ++i) {
            final Iterator<IntentionAction> iterator = array[i].getAddHeaderSearchPathFixes(ocIncludeDirective.getProject(), virtualFile, canonicalText).iterator();
            while (iterator.hasNext()) {
                this.registerQuickFix(a, iterator.next());
            }
        }
    }
    
    @Override
    public void visitSelectorExpression(final OCSelectorExpression ocSelectorExpression) {
        Label_0063: {
            if (ocSelectorExpression.getParent() instanceof OCMessageArgument) {
                final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)PsiTreeUtil.getParentOfType((PsiElement)ocSelectorExpression, (Class)OCSendMessageExpression.class);
                if (ocSendMessageExpression != null) {
                    final String messageSelector = ocSendMessageExpression.getMessageSelector();
                    try {
                        if ("respondsToSelector:".equals(messageSelector)) {
                            return;
                        }
                        final String s = "instancesRespondToSelector:";
                        final String s2 = messageSelector;
                        final boolean b = s.equals(s2);
                        if (b) {
                            return;
                        }
                        break Label_0063;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final String s = "instancesRespondToSelector:";
                        final String s2 = messageSelector;
                        final boolean b = s.equals(s2);
                        if (b) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
            }
        }
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)ocSelectorExpression, (Class)OCClassDeclaration.class);
        OCClassSymbol symbol = null;
        Label_0091: {
            try {
                if (ocClassDeclaration != null) {
                    symbol = ocClassDeclaration.getSymbol();
                    break Label_0091;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            symbol = null;
        }
        OCClassSymbol classSymbol = symbol;
        final OCObjectTypeContext actionTargetContext = OCSelectorAdHocResolver.getActionTargetContext(ocSelectorExpression);
        OCObjectType type = null;
        Label_0116: {
            try {
                if (actionTargetContext != null) {
                    type = actionTargetContext.getType();
                    break Label_0116;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            type = null;
        }
        final OCObjectType ocObjectType = type;
        String string = null;
        Label_0212: {
            Label_0169: {
                Label_0134: {
                    try {
                        if (ocObjectType == null) {
                            break Label_0169;
                        }
                        final OCClassSymbol ocClassSymbol = classSymbol;
                        if (ocClassSymbol != null) {
                            break Label_0134;
                        }
                        break Label_0134;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final OCClassSymbol ocClassSymbol = classSymbol;
                        if (ocClassSymbol != null) {
                            if (classSymbol.getName().equals(ocObjectType.getClassSymbol().getName())) {
                                break Label_0169;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                classSymbol = ocObjectType.getClassSymbol();
                try {
                    if (ocObjectType != null) {
                        string = "Type '" + ocObjectType.getName((PsiElement)ocSelectorExpression) + "' doesn't have the method";
                        break Label_0212;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            string = "Cannot find the method";
        }
        final String s3 = string;
        final OCSymbolContext ocSymbolContext = new OCSymbolContext(null, OCSymbolKind.METHOD, classSymbol);
        final Annotation a = this.a((PsiElement)ocSelectorExpression, s3, new OCSymbolGroupContext(ocSymbolContext), null, OCInspections.UnresolvedMessage.class);
        if (a != null) {
            final ArrayList<String> list = new ArrayList<String>();
            final ArrayList<OCExpression> list2 = new ArrayList<OCExpression>();
            final ArrayList<OCPointerType> list3 = new ArrayList<OCPointerType>();
            final String selector = ocSelectorExpression.getSelector();
            int i = selector.indexOf(58);
            if (i != -1) {
                for (int n = 0; i != -1; i = selector.indexOf(58, n)) {
                    list.add(selector.substring(n, i + 1));
                    list2.add(null);
                    list3.add(null);
                    n = i + 1;
                }
            }
            else {
                list.add(selector);
                list2.add(null);
            }
            final OCMethodSymbol similarResponder = OCObjectTypeContext.findSimilarResponder(ocObjectType, list, (List<OCType>)list3, ocSelectorExpression.getContainingFile());
            if (similarResponder != null) {
                int j = 0;
                try {
                    while (j < list3.size()) {
                        list3.set(j, OCIdType.pointerToID(ocSelectorExpression.getProject()));
                        ++j;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
            }
            this.registerQuickFix(a, (IntentionAction)new OCChangeMethodSignatureIntentionAction(similarResponder, (List<OCType>)list3, list, list2));
            this.registerQuickFix(a, (IntentionAction)new OCCreateNewDefinitionIntentionAction((PsiElement)ocSelectorExpression, ocSymbolContext.getParent(), "-" + selector, ocSelectorExpression.getExpectedMethodSignature(), ocSelectorExpression.getExpectedReturnType(), ocObjectType));
        }
    }
    
    @Override
    public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
        OCSymbol ocSymbol = null;
        String s = null;
        String string = null;
        Class<? extends OCInspection> clazz = null;
        Object o = null;
        OCFileHighlighter ocFileHighlighter = null;
        Lexer highlightingLexer = null;
        CharSequence text = null;
        ASTNode astNode = ocLiteralExpression.getNode().getFirstChildNode();
        while (true) {
            Label_0204: {
                Label_0108: {
                    Label_0061: {
                        try {
                            if (astNode == null) {
                                break;
                            }
                            final TokenSet set = OCTokenTypes.RAW_STRING_LITERALS;
                            final ASTNode astNode2 = astNode;
                            final IElementType elementType = OCElementUtil.getElementType(astNode2);
                            final boolean b = set.contains(elementType);
                            if (b) {
                                break Label_0061;
                            }
                            break Label_0204;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            final TokenSet set = OCTokenTypes.RAW_STRING_LITERALS;
                            final ASTNode astNode2 = astNode;
                            final IElementType elementType = OCElementUtil.getElementType(astNode2);
                            final boolean b = set.contains(elementType);
                            if (!b) {
                                break Label_0204;
                            }
                            if (ocFileHighlighter != null) {
                                break Label_0108;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    ocFileHighlighter = new OCFileHighlighter(OCLanguageKind.CPP, false, false, false);
                    highlightingLexer = ocFileHighlighter.getHighlightingLexer();
                    text = ocLiteralExpression.getContainingFile().getText();
                }
                final TextRange textRange = astNode.getTextRange();
                highlightingLexer.start(text, textRange.getStartOffset(), textRange.getEndOffset());
                while (highlightingLexer.getTokenType() != null) {
                    final TextRange textRange2 = new TextRange(highlightingLexer.getTokenStart(), highlightingLexer.getTokenEnd());
                    final TextAttributesKey[] tokenHighlights = ocFileHighlighter.getTokenHighlights(highlightingLexer.getTokenType());
                    try {
                        if (tokenHighlights.length > 0) {
                            this.highlight(textRange2, tokenHighlights[0]);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    highlightingLexer.advance();
                }
            }
            astNode = astNode.getTreeNext();
        }
        for (PsiReference representative : ocLiteralExpression.getReferences()) {
            while (representative instanceof OCCompositeResourceReference) {
                representative = ((OCCompositeResourceReference)representative).getRepresentative();
            }
            boolean b2 = false;
            for (final OCAnnotatorHelper ocAnnotatorHelper : OCAnnotator.getAnnotatorHelpers()) {
                final Ref create = Ref.create();
                final Ref create2 = Ref.create();
                final Ref create3 = Ref.create();
                final Ref create4 = Ref.create();
                if (ocAnnotatorHelper.processReference(representative, (Ref<Class<? extends OCInspection>>)create, (Ref<String>)create3, (Ref<OCClassSymbol>)create2, (Ref<String>)create4)) {
                    clazz = (Class<? extends OCInspection>)create.get();
                    string = (String)create3.get();
                    ocSymbol = (OCClassSymbol)create2.get();
                    s = (String)create4.get();
                    b2 = true;
                    break;
                }
            }
            Label_0591: {
                Label_0418: {
                    try {
                        if (b2) {
                            break Label_0591;
                        }
                        if (!(representative instanceof OCFileResourceReference)) {
                            break Label_0418;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    clazz = OCInspections.ResourceNotFoundInspection.class;
                    string = "Can't find the resource";
                    break Label_0591;
                }
                if (representative instanceof OCStringResourceReference) {
                    clazz = OCInspections.StringLocalizationInspection.class;
                    final String tableFileName = ((OCStringResourceReference)representative).getTableFileName();
                    final List<String> notLocalizedLanguages = ((OCStringResourceReference)representative).getNotLocalizedLanguages();
                    StringBuilder sb2 = null;
                    String s4 = null;
                    Label_0506: {
                        Label_0504: {
                            Label_0550: {
                                Label_0495: {
                                    try {
                                        if (notLocalizedLanguages.isEmpty()) {
                                            break Label_0550;
                                        }
                                        final StringBuilder sb = new StringBuilder();
                                        final List<String> list = notLocalizedLanguages;
                                        final String s2 = ", ";
                                        final String s3 = StringUtil.join((Collection)list, s2);
                                        sb2 = sb.append(s3);
                                        final List<String> list2 = notLocalizedLanguages;
                                        final int n = list2.size();
                                        final int n2 = 1;
                                        if (n > n2) {
                                            break Label_0495;
                                        }
                                        break Label_0504;
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw b(ex5);
                                    }
                                    try {
                                        final StringBuilder sb = new StringBuilder();
                                        final List<String> list = notLocalizedLanguages;
                                        final String s2 = ", ";
                                        final String s3 = StringUtil.join((Collection)list, s2);
                                        sb2 = sb.append(s3);
                                        final List<String> list2 = notLocalizedLanguages;
                                        final int n = list2.size();
                                        final int n2 = 1;
                                        if (n > n2) {
                                            s4 = " localizations don't";
                                            break Label_0506;
                                        }
                                    }
                                    catch (IllegalArgumentException ex6) {
                                        throw b(ex6);
                                    }
                                }
                                break Label_0504;
                            }
                            if (tableFileName != null) {
                                string = tableFileName + " doesn't contain the value for";
                                o = new OCProvideStringLocalizationsIntentionAction((OCStringResourceReference)representative);
                            }
                            break Label_0591;
                        }
                        s4 = " localization doesn't";
                    }
                    this.registerQuickFix(this.a(sb2.append(s4).append(" contain the value for").toString(), representative, OCInspections.StringLocalizationInspection.class), (IntentionAction)new OCProvideStringLocalizationsIntentionAction((OCStringResourceReference)representative));
                    return;
                }
            }
        }
        OCSymbolContext ocSymbolContext = null;
        Label_0629: {
            try {
                if (ocSymbol != null) {
                    final OCExpectedTypeUtil.Expectable expectable;
                    ocSymbolContext = new OCSymbolContext(expectable, OCSymbolKind.PROPERTY, ocSymbol);
                    expectable = new OCExpectedTypeUtil.Expectable() {
                        @Override
                        public OCType getExpectedType() {
                            return OCIdType.pointerToID(ocLiteralExpression.getProject());
                        }
                    };
                    break Label_0629;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            ocSymbolContext = null;
        }
        final OCSymbolContext ocSymbolContext2 = ocSymbolContext;
        String s5 = null;
        OCSymbolGroupContext ocSymbolGroupContext = null;
        Label_0657: {
            try {
                s5 = string;
                if (ocSymbolContext2 != null) {
                    ocSymbolGroupContext = new OCSymbolGroupContext(ocSymbolContext2);
                    break Label_0657;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            ocSymbolGroupContext = null;
        }
        final Annotation a = this.a((PsiElement)ocLiteralExpression, s5, ocSymbolGroupContext, s, clazz);
        Label_0682: {
            try {
                if (a == null) {
                    return;
                }
                final Object o2 = o;
                if (o2 != null) {
                    break Label_0682;
                }
                return;
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
            try {
                final Object o2 = o;
                if (o2 != null) {
                    this.registerQuickFix(a, (IntentionAction)o);
                }
            }
            catch (IllegalArgumentException ex10) {
                throw b(ex10);
            }
        }
    }
    
    public void visitComment(final PsiComment psiComment) {
        final ASTNode node = psiComment.getNode();
        if (node != null) {
            final IElementType elementType = node.getElementType();
            Label_0039: {
                try {
                    if (elementType != OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT) {
                        return;
                    }
                    final OCResolveAnnotator ocResolveAnnotator = this;
                    final AnnotationHolder annotationHolder = ocResolveAnnotator.getHolder();
                    if (annotationHolder != null) {
                        break Label_0039;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCResolveAnnotator ocResolveAnnotator = this;
                    final AnnotationHolder annotationHolder = ocResolveAnnotator.getHolder();
                    if (annotationHolder != null) {
                        this.getHolder().createInfoAnnotation((PsiElement)psiComment, (String)null).setTextAttributes(OCHighlightingKeys.CONDITIONALLY_NOT_COMPILED);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
    }
    
    @Override
    public void visitDirective(final OCDirective ocDirective) {
        if (ocDirective.getHeaderToken().getNode().getElementType() == OCTokenTypes.UNKNOWN_DIRECTIVE) {
            this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocDirective, "CIDR", "Unknown preprocessor directive '" + ocDirective.getHeaderToken().getText() + "'"), (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocDirective, "Remove directive"));
        }
    }
    
    @Override
    public void visitStructLike(final OCStructLike ocStructLike) {
        final OCStructSymbol ocStructSymbol = ocStructLike.getSymbol();
        try {
            if (ocStructSymbol != null) {
                this.highlight(ocStructLike.getNameIdentifier(), a(ocStructSymbol.getKind()));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitDefineDirective(final OCDefineDirectiveImpl ocDefineDirectiveImpl) {
        this.highlight(ocDefineDirectiveImpl.getNameIdentifier(), a(OCSymbolKind.MACRO));
    }
    
    @Override
    public void visitMacroParameter(final OCMacroParameterImpl ocMacroParameterImpl) {
        this.highlight((PsiElement)ocMacroParameterImpl, a(OCSymbolKind.MACRO_PARAMETER));
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnonnull       16
        //    11: return         
        //    12: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: aload_1        
        //    17: iconst_3       
        //    18: anewarray       Ljava/lang/Class;
        //    21: dup            
        //    22: iconst_0       
        //    23: ldc             Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;.class
        //    25: aastore        
        //    26: dup            
        //    27: iconst_1       
        //    28: ldc             Lcom/jetbrains/cidr/lang/psi/OCEnum;.class
        //    30: aastore        
        //    31: dup            
        //    32: iconst_2       
        //    33: ldc             Lcom/jetbrains/cidr/lang/psi/OCProperty;.class
        //    35: aastore        
        //    36: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    39: astore_3       
        //    40: aload_3        
        //    41: ifnull          129
        //    44: aload_3        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //    48: ifeq            77
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: aload_2        
        //    60: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    63: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //    66: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/lang/annotation/Annotation;
        //    69: pop            
        //    70: goto            641
        //    73: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_3        
        //    78: instanceof      Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //    81: ifeq            103
        //    84: aload_0        
        //    85: aload_2        
        //    86: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    89: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //    92: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/lang/annotation/Annotation;
        //    95: pop            
        //    96: goto            641
        //    99: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_3        
        //   104: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   107: ifeq            641
        //   110: aload_0        
        //   111: aload_2        
        //   112: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   115: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   118: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/lang/annotation/Annotation;
        //   121: pop            
        //   122: goto            641
        //   125: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aload_1        
        //   130: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   135: astore          4
        //   137: aload_1        
        //   138: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   143: astore          5
        //   145: aload           4
        //   147: ifnull          173
        //   150: aload_0        
        //   151: aload_2        
        //   152: aload           4
        //   154: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   159: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   162: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.highlight:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)Lcom/intellij/lang/annotation/Annotation;
        //   165: pop            
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload           5
        //   175: ifnull          641
        //   178: aload_0        
        //   179: aload           5
        //   181: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.checkNamespaceQualifierOwnerElement:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;)Lcom/intellij/lang/annotation/Annotation;
        //   184: astore          6
        //   186: aload           6
        //   188: ifnull          320
        //   191: aload_1        
        //   192: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getQualifiedName:(Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   195: aload_1        
        //   196: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
        //   199: astore          7
        //   201: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   204: dup            
        //   205: aload_1        
        //   206: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   211: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   214: astore          8
        //   216: aload           8
        //   218: iconst_1       
        //   219: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setProcessNonImported:(Z)V
        //   222: aload           8
        //   224: aload           7
        //   226: iconst_1       
        //   227: iconst_0       
        //   228: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   231: astore          9
        //   233: aload           9
        //   235: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   240: astore          10
        //   242: aload           10
        //   244: invokeinterface java/util/Iterator.hasNext:()Z
        //   249: ifeq            317
        //   252: aload           10
        //   254: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   259: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   262: astore          11
        //   264: new             Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix;
        //   267: dup            
        //   268: aload_1        
        //   269: aload           11
        //   271: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   274: astore          12
        //   276: aload           12
        //   278: aload           5
        //   280: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.getProject:()Lcom/intellij/openapi/project/Project;
        //   285: aconst_null    
        //   286: aload           5
        //   288: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   293: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //   296: ifeq            314
        //   299: aload_0        
        //   300: aload           6
        //   302: aload           12
        //   304: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   307: goto            317
        //   310: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: goto            242
        //   317: goto            641
        //   320: aload           4
        //   322: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   325: ifeq            641
        //   328: aload_1        
        //   329: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   334: astore          7
        //   336: aload_1        
        //   337: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   342: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   345: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   350: astore          8
        //   352: aload           4
        //   354: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   357: ifeq            397
        //   360: aload           8
        //   362: ifnull          397
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload           8
        //   374: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.isEmpty:()Z
        //   379: ifeq            397
        //   382: goto            389
        //   385: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: iconst_1       
        //   390: goto            398
        //   393: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   396: athrow         
        //   397: iconst_0       
        //   398: istore          9
        //   400: aload           5
        //   402: aload           4
        //   404: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   407: iload           9
        //   409: ifne            420
        //   412: iconst_1       
        //   413: goto            421
        //   416: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: iconst_0       
        //   421: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.getPredeclarationInParent:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Z)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   426: astore          10
        //   428: aload           10
        //   430: ifnonnull       610
        //   433: aload           4
        //   435: invokedynamic   process:()Lcom/intellij/util/Processor;
        //   440: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   445: ifeq            610
        //   448: goto            455
        //   451: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   454: athrow         
        //   455: aload           5
        //   457: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   460: dup            
        //   461: aload           5
        //   463: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   466: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getAppropriateToAppendSymbol:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   469: astore          11
        //   471: aload           11
        //   473: ifnull          607
        //   476: new             Ljava/lang/StringBuilder;
        //   479: dup            
        //   480: invokespecial   java/lang/StringBuilder.<init>:()V
        //   483: aload           4
        //   485: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   490: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   493: ldc             " was not declared in "
        //   495: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   498: aload           11
        //   500: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   505: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   508: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   511: astore          12
        //   513: aload_0        
        //   514: aload_1        
        //   515: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   520: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$CannotResolve;.class
        //   522: ldc             "err_member_decl_does_not_match"
        //   524: aload           12
        //   526: getstatic       com/intellij/codeInspection/ProblemHighlightType.GENERIC_ERROR:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   529: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   532: astore          13
        //   534: aload           4
        //   536: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   539: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   542: astore          14
        //   544: aload           14
        //   546: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   549: if_acmpne       560
        //   552: iconst_1       
        //   553: goto            561
        //   556: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   559: athrow         
        //   560: iconst_0       
        //   561: istore          15
        //   563: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   566: dup            
        //   567: aload           14
        //   569: aload_1        
        //   570: aconst_null    
        //   571: aload           11
        //   573: aload_1        
        //   574: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   579: aload           4
        //   581: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   586: iload           15
        //   588: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //   591: astore          16
        //   593: aload           16
        //   595: iconst_1       
        //   596: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.setSilentMode:(Z)V
        //   599: aload_0        
        //   600: aload           13
        //   602: aload           16
        //   604: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   607: goto            641
        //   610: aload           10
        //   612: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   615: ifeq            641
        //   618: aload           7
        //   620: aload           10
        //   622: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   625: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   630: aload_1        
        //   631: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   634: goto            641
        //   637: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   640: athrow         
        //   641: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      12     12     16     Ljava/lang/IllegalArgumentException;
        //  40     51     54     58     Ljava/lang/IllegalArgumentException;
        //  44     73     73     77     Ljava/lang/IllegalArgumentException;
        //  77     99     99     103    Ljava/lang/IllegalArgumentException;
        //  103    125    125    129    Ljava/lang/IllegalArgumentException;
        //  145    166    169    173    Ljava/lang/IllegalArgumentException;
        //  276    310    310    314    Ljava/lang/IllegalArgumentException;
        //  352    365    368    372    Ljava/lang/IllegalArgumentException;
        //  360    382    385    389    Ljava/lang/IllegalArgumentException;
        //  372    393    393    397    Ljava/lang/IllegalArgumentException;
        //  400    416    416    420    Ljava/lang/IllegalArgumentException;
        //  428    448    451    455    Ljava/lang/IllegalArgumentException;
        //  544    556    556    560    Ljava/lang/IllegalArgumentException;
        //  610    634    637    641    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0372:
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
    public void visitTypeParameterDeclaration(final OCTypeParameterDeclaration ocTypeParameterDeclaration) {
        this.highlight(ocTypeParameterDeclaration.getNameIdentifier(), a(OCSymbolKind.TEMPLATE_TYPE_PARAMETER));
        super.visitTypeParameterDeclaration(ocTypeParameterDeclaration);
    }
    
    @Override
    public void visitNamespace(final OCCppNamespace ocCppNamespace) {
        this.highlight(ocCppNamespace.getNameIdentifier(), a(OCSymbolKind.NAMESPACE));
        super.visitNamespace(ocCppNamespace);
    }
    
    @Override
    public void visitNamespaceAlias(final OCCppNamespaceAlias ocCppNamespaceAlias) {
        this.highlight(ocCppNamespaceAlias.getNameIdentifier(), a(OCSymbolKind.NAMESPACE_ALIAS));
        super.visitNamespaceAlias(ocCppNamespaceAlias);
    }
    
    @Override
    public void visitUsingStatement(final OCCppUsingStatement ocCppUsingStatement) {
        this.highlight(ocCppUsingStatement.getNameIdentifier(), a(OCSymbolKind.USING_SYMBOL_ALIAS));
        super.visitUsingStatement(ocCppUsingStatement);
    }
    
    @Override
    public void visitMethodSelectorPart(final OCMethodSelectorPart ocMethodSelectorPart) {
        this.highlight(ocMethodSelectorPart.getSelectorIdentifier(), a(OCSymbolKind.METHOD));
        this.highlight(ocMethodSelectorPart.getParameter(), a(OCSymbolKind.PARAMETER));
        super.visitMethodSelectorPart(ocMethodSelectorPart);
    }
    
    @Override
    public void visitArgumentSelector(final OCArgumentSelector ocArgumentSelector) {
        try {
            if (ocArgumentSelector.getTextRange().getLength() <= 0 || this.getHolder() == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.getHolder().createInfoAnnotation((PsiElement)ocArgumentSelector, (String)null).setTextAttributes(OCHighlightingKeys.MESSAGE_ARGUMENT);
    }
    
    @Override
    public void visitPropertyAttribute(final OCPropertyAttribute ocPropertyAttribute) {
        this.highlight((PsiElement)ocPropertyAttribute, OCHighlightingKeys.PROPERTY_ATTRIBUTE);
    }
    
    @Override
    public void visitExpression(final OCExpression ocExpression) {
        this.a((PsiElement)ocExpression, "Can't resolve operator", null, null, null);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //     6: invokeinterface java/util/List.size:()I
        //    11: ifne            19
        //    14: return         
        //    15: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: aload_1        
        //    20: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //    25: astore_2       
        //    26: aload_1        
        //    27: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //    32: astore_3       
        //    33: aload_2        
        //    34: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getReceiverType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    37: astore          4
        //    39: aload           4
        //    41: ifnull          56
        //    44: aload           4
        //    46: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    49: goto            57
        //    52: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aconst_null    
        //    57: astore          5
        //    59: aconst_null    
        //    60: astore          6
        //    62: aconst_null    
        //    63: astore          7
        //    65: aconst_null    
        //    66: astore          8
        //    68: iconst_0       
        //    69: istore          9
        //    71: iconst_0       
        //    72: istore          10
        //    74: getstatic       com/intellij/codeInspection/ProblemHighlightType.GENERIC_ERROR_OR_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //    77: astore          11
        //    79: new             Ljava/util/ArrayList;
        //    82: dup            
        //    83: invokespecial   java/util/ArrayList.<init>:()V
        //    86: astore          12
        //    88: aload_2        
        //    89: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getAllResponders:()Ljava/util/List;
        //    92: invokeinterface java/util/List.isEmpty:()Z
        //    97: ifeq            931
        //   100: aload           4
        //   102: ifnull          770
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: new             Ljava/lang/StringBuilder;
        //   115: dup            
        //   116: invokespecial   java/lang/StringBuilder.<init>:()V
        //   119: ldc             "Cannot resolve method '"
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: aload_3        
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: ldc             "'"
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: aload           5
        //   135: ifnull          177
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: new             Ljava/lang/StringBuilder;
        //   148: dup            
        //   149: invokespecial   java/lang/StringBuilder.<init>:()V
        //   152: ldc             " for "
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: aload           5
        //   159: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   170: goto            179
        //   173: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: ldc             ""
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   185: astore          7
        //   187: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnresolvedMessage;.class
        //   189: astore          6
        //   191: ldc             "CIDR"
        //   193: astore          8
        //   195: iconst_1       
        //   196: istore          10
        //   198: aload_1        
        //   199: iconst_1       
        //   200: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   203: astore          13
        //   205: aload           13
        //   207: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   210: if_acmpne       218
        //   213: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   216: astore          13
        //   218: aload_1        
        //   219: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   224: astore          14
        //   226: aload           14
        //   228: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   231: ifeq            290
        //   234: aload           14
        //   236: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   239: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   244: ifnull          290
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_1        
        //   255: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   257: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   260: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   263: astore          16
        //   265: aload           16
        //   267: ifnull          284
        //   270: aload           16
        //   272: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   277: goto            285
        //   280: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: aconst_null    
        //   285: astore          15
        //   287: goto            297
        //   290: aload           4
        //   292: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   295: astore          15
        //   297: aload_1        
        //   298: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getExpectedMethodSignature:()Ljava/lang/String;
        //   303: astore          16
        //   305: aload           16
        //   307: ifnull          378
        //   310: aload           12
        //   312: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   315: dup            
        //   316: aload_1        
        //   317: aload           15
        //   319: new             Ljava/lang/StringBuilder;
        //   322: dup            
        //   323: invokespecial   java/lang/StringBuilder.<init>:()V
        //   326: aload           16
        //   328: iconst_0       
        //   329: invokevirtual   java/lang/String.charAt:(I)C
        //   332: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   335: aload_1        
        //   336: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   347: aload           16
        //   349: aload           13
        //   351: aload_1        
        //   352: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   357: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   360: aload           4
        //   362: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCObjectType;)V
        //   365: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   370: pop            
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: new             Ljava/util/ArrayList;
        //   381: dup            
        //   382: invokespecial   java/util/ArrayList.<init>:()V
        //   385: astore          17
        //   387: new             Ljava/util/ArrayList;
        //   390: dup            
        //   391: invokespecial   java/util/ArrayList.<init>:()V
        //   394: astore          18
        //   396: new             Ljava/util/ArrayList;
        //   399: dup            
        //   400: invokespecial   java/util/ArrayList.<init>:()V
        //   403: astore          19
        //   405: aload_1        
        //   406: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //   411: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   416: astore          20
        //   418: aload           20
        //   420: invokeinterface java/util/Iterator.hasNext:()Z
        //   425: ifeq            509
        //   428: aload           20
        //   430: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   435: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   438: astore          21
        //   440: aload           17
        //   442: aload           21
        //   444: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentSelector:()Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //   449: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentSelector.getSelectorName:()Ljava/lang/String;
        //   454: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   459: pop            
        //   460: aload           21
        //   462: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   467: astore          22
        //   469: aload           18
        //   471: aload           22
        //   473: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   478: pop            
        //   479: aload           19
        //   481: aload           22
        //   483: ifnull          499
        //   486: aload           22
        //   488: iconst_1       
        //   489: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpressionType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   492: goto            500
        //   495: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   498: athrow         
        //   499: aconst_null    
        //   500: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   505: pop            
        //   506: goto            418
        //   509: aload           4
        //   511: aload           17
        //   513: aload           19
        //   515: aload_1        
        //   516: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   521: invokestatic    com/jetbrains/cidr/lang/types/OCObjectTypeContext.findSimilarResponder:(Lcom/jetbrains/cidr/lang/types/OCObjectType;Ljava/util/List;Ljava/util/List;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   524: astore          20
        //   526: aload           12
        //   528: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeMethodSignatureIntentionAction;
        //   531: dup            
        //   532: aload           20
        //   534: aload           19
        //   536: aload           17
        //   538: aload           18
        //   540: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeMethodSignatureIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V
        //   543: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   548: pop            
        //   549: aload_1        
        //   550: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   555: astore          21
        //   557: aload_3        
        //   558: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCGetter:(Ljava/lang/String;)Z
        //   561: ifeq            656
        //   564: aload           21
        //   566: ifnull          594
        //   569: goto            576
        //   572: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   575: athrow         
        //   576: aload           21
        //   578: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getStaticMode:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //   581: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //   584: if_acmpeq       656
        //   587: goto            594
        //   590: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   593: athrow         
        //   594: aload           13
        //   596: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   599: ifeq            625
        //   602: goto            609
        //   605: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   608: athrow         
        //   609: aload_1        
        //   610: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProject:()Lcom/intellij/openapi/project/Project;
        //   615: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   618: goto            627
        //   621: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   624: athrow         
        //   625: aload           13
        //   627: astore          22
        //   629: aload           12
        //   631: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   634: dup            
        //   635: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   638: aload_1        
        //   639: aload           4
        //   641: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   644: aload_3        
        //   645: aload           22
        //   647: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   650: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   655: pop            
        //   656: aload_3        
        //   657: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
        //   660: ifeq            767
        //   663: aload_3        
        //   664: ldc             "set"
        //   666: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //   669: ifeq            767
        //   672: goto            679
        //   675: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   678: athrow         
        //   679: aload_1        
        //   680: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //   685: invokeinterface java/util/List.size:()I
        //   690: iconst_1       
        //   691: if_icmpne       767
        //   694: goto            701
        //   697: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   700: athrow         
        //   701: aload_1        
        //   702: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //   707: iconst_0       
        //   708: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   713: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   716: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   721: astore          22
        //   723: aload           22
        //   725: ifnull          767
        //   728: aload_3        
        //   729: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getObjCGetterFromSetter:(Ljava/lang/String;)Ljava/lang/String;
        //   732: astore          23
        //   734: aload           12
        //   736: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   739: dup            
        //   740: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   743: aload_1        
        //   744: aload           4
        //   746: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   749: aload           23
        //   751: aload           22
        //   753: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   758: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   761: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   766: pop            
        //   767: goto            1427
        //   770: aload_1        
        //   771: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   776: astore          13
        //   778: aload           13
        //   780: ifnull          797
        //   783: aload           13
        //   785: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   790: goto            798
        //   793: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   796: athrow         
        //   797: aconst_null    
        //   798: astore          14
        //   800: aload           14
        //   802: ifnull          930
        //   805: aload           14
        //   807: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   810: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   813: ifne            930
        //   816: goto            823
        //   819: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   822: athrow         
        //   823: new             Ljava/lang/StringBuilder;
        //   826: dup            
        //   827: invokespecial   java/lang/StringBuilder.<init>:()V
        //   830: ldc             "Can't send messages to expressions of type '"
        //   832: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   835: aload           14
        //   837: aload           13
        //   839: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   842: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   845: ldc             "'"
        //   847: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   850: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   853: astore          7
        //   855: aload           14
        //   857: aload           13
        //   859: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   862: ifeq            919
        //   865: aload           13
        //   867: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   872: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   875: ifeq            903
        //   878: goto            885
        //   881: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   884: athrow         
        //   885: aload_0        
        //   886: aload           13
        //   888: ldc             "err_bad_receiver_type"
        //   890: aload           7
        //   892: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   895: pop            
        //   896: goto            930
        //   899: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   902: athrow         
        //   903: aload_0        
        //   904: aload           13
        //   906: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //   908: ldc             "warn_bad_receiver_type"
        //   910: aload           7
        //   912: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   915: pop            
        //   916: goto            930
        //   919: aload_0        
        //   920: aload           13
        //   922: ldc             "err_bad_receiver_type"
        //   924: aload           7
        //   926: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   929: pop            
        //   930: return         
        //   931: aload_2        
        //   932: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getFilteredByStaticnessResponders:()Ljava/util/List;
        //   935: astore          13
        //   937: aload           13
        //   939: invokeinterface java/util/List.isEmpty:()Z
        //   944: ifeq            955
        //   947: aconst_null    
        //   948: goto            966
        //   951: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   954: athrow         
        //   955: aload           13
        //   957: iconst_0       
        //   958: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   963: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   966: astore          14
        //   968: aload           13
        //   970: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.a:(Ljava/util/List;)Z
        //   973: ifne            1016
        //   976: new             Ljava/lang/StringBuilder;
        //   979: dup            
        //   980: invokespecial   java/lang/StringBuilder.<init>:()V
        //   983: ldc             "Message '"
        //   985: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   988: aload_3        
        //   989: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   992: ldc             "' can be resolved to several methods"
        //   994: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   997: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1000: astore          7
        //  1002: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$SeveralTargetsMessage;.class
        //  1004: astore          6
        //  1006: ldc             "CIDR"
        //  1008: astore          8
        //  1010: iconst_1       
        //  1011: istore          9
        //  1013: goto            1427
        //  1016: aload           14
        //  1018: ifnull          1110
        //  1021: aload           14
        //  1023: aload_1        
        //  1024: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isForbiddenByARC:(Lcom/intellij/psi/PsiElement;)Z
        //  1029: ifeq            1110
        //  1032: goto            1039
        //  1035: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1038: athrow         
        //  1039: aload_0        
        //  1040: aload_1        
        //  1041: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //  1043: ldc             "CIDR"
        //  1045: new             Ljava/lang/StringBuilder;
        //  1048: dup            
        //  1049: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1052: ldc             "Explicit usage of '"
        //  1054: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1057: aload_3        
        //  1058: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1061: ldc             "' is forbidden in ARC"
        //  1063: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1066: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1069: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1072: astore          15
        //  1074: aload_0        
        //  1075: aload           15
        //  1077: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction;
        //  1080: dup            
        //  1081: aload_1        
        //  1082: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;)V
        //  1085: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1088: aload_0        
        //  1089: aload           15
        //  1091: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction;
        //  1094: dup            
        //  1095: aload_1        
        //  1096: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //  1101: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //  1104: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1107: goto            1427
        //  1110: aload           14
        //  1112: ifnull          1155
        //  1115: aload           14
        //  1117: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isUnavailable:()Z
        //  1122: ifeq            1155
        //  1125: goto            1132
        //  1128: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1131: athrow         
        //  1132: aload           14
        //  1134: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getUnavailableMessage:()Ljava/lang/String;
        //  1139: astore          7
        //  1141: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DeprecatedAPI;.class
        //  1143: astore          6
        //  1145: ldc             "CIDR"
        //  1147: astore          8
        //  1149: iconst_1       
        //  1150: istore          10
        //  1152: goto            1427
        //  1155: aload           14
        //  1157: ifnull          1202
        //  1160: aload           14
        //  1162: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isDeprecated:()Z
        //  1167: ifeq            1202
        //  1170: goto            1177
        //  1173: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1176: athrow         
        //  1177: aload           14
        //  1179: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getDeprecatedMessage:()Ljava/lang/String;
        //  1184: astore          7
        //  1186: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DeprecatedAPI;.class
        //  1188: astore          6
        //  1190: ldc             "warn_deprecated"
        //  1192: astore          8
        //  1194: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_DEPRECATED:Lcom/intellij/codeInspection/ProblemHighlightType;
        //  1197: astore          11
        //  1199: goto            1427
        //  1202: aload           14
        //  1204: ifnull          1427
        //  1207: aload           14
        //  1209: aload_1        
        //  1210: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.checkAvailability:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1213: astore          7
        //  1215: aload           7
        //  1217: ifnull          1233
        //  1220: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnavailableInDeploymentTarget;.class
        //  1222: astore          6
        //  1224: ldc             "CIDR"
        //  1226: astore          8
        //  1228: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_DEPRECATED:Lcom/intellij/codeInspection/ProblemHighlightType;
        //  1231: astore          11
        //  1233: aload           6
        //  1235: ifnonnull       1427
        //  1238: aload           14
        //  1240: aload_1        
        //  1241: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isEarlierInCode:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //  1244: ifne            1427
        //  1247: goto            1254
        //  1250: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1253: athrow         
        //  1254: aload_1        
        //  1255: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //  1257: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  1260: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //  1263: astore          15
        //  1265: aload           15
        //  1267: ifnull          1284
        //  1270: aload           15
        //  1272: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1277: goto            1285
        //  1280: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1283: athrow         
        //  1284: aconst_null    
        //  1285: astore          16
        //  1287: aload           16
        //  1289: aload           14
        //  1291: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1296: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //  1299: ifne            1331
        //  1302: new             Ljava/lang/StringBuilder;
        //  1305: dup            
        //  1306: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1309: ldc             "Method '"
        //  1311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1314: aload_3        
        //  1315: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1318: ldc             "' is declared in another category/implementation later in the scope"
        //  1320: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1323: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1326: astore          7
        //  1328: goto            1405
        //  1331: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsLaterMethodDeclaration:()Z
        //  1334: ifne            1405
        //  1337: new             Ljava/lang/StringBuilder;
        //  1340: dup            
        //  1341: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1344: ldc             "Method '"
        //  1346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1349: aload_3        
        //  1350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1353: ldc             "' is declared later in the scope"
        //  1355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1358: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1361: astore          7
        //  1363: aload           12
        //  1365: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction;
        //  1368: dup            
        //  1369: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1372: aload_1        
        //  1373: aconst_null    
        //  1374: aload           14
        //  1376: ldc             " above"
        //  1378: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;)V
        //  1381: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1386: pop            
        //  1387: aload           12
        //  1389: new             Lcom/jetbrains/cidr/lang/daemon/OCResolveAnnotator$2;
        //  1392: dup            
        //  1393: aload_0        
        //  1394: aload           14
        //  1396: invokespecial   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator$2.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCResolveAnnotator;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //  1399: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1404: pop            
        //  1405: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MethodIsLaterInTheScope;.class
        //  1407: astore          6
        //  1409: aload           12
        //  1411: new             Lcom/jetbrains/cidr/lang/daemon/OCResolveAnnotator$3;
        //  1414: dup            
        //  1415: aload_0        
        //  1416: aload           14
        //  1418: invokespecial   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator$3.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCResolveAnnotator;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //  1421: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1426: pop            
        //  1427: aload           7
        //  1429: ifnull          1534
        //  1432: iload           10
        //  1434: ifeq            1462
        //  1437: goto            1444
        //  1440: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1443: athrow         
        //  1444: aload_0        
        //  1445: aload_1        
        //  1446: aload           6
        //  1448: aload           8
        //  1450: aload           7
        //  1452: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1455: goto            1490
        //  1458: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1461: athrow         
        //  1462: aload_0        
        //  1463: aload_1        
        //  1464: aload           6
        //  1466: aload           8
        //  1468: aload           7
        //  1470: iload           9
        //  1472: ifeq            1485
        //  1475: getstatic       com/intellij/codeInspection/ProblemHighlightType.WEAK_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //  1478: goto            1487
        //  1481: invokestatic    com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1484: athrow         
        //  1485: aload           11
        //  1487: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //  1490: astore          13
        //  1492: aload           12
        //  1494: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1499: astore          14
        //  1501: aload           14
        //  1503: invokeinterface java/util/Iterator.hasNext:()Z
        //  1508: ifeq            1534
        //  1511: aload           14
        //  1513: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1518: checkcast       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1521: astore          15
        //  1523: aload_0        
        //  1524: aload           13
        //  1526: aload           15
        //  1528: invokevirtual   com/jetbrains/cidr/lang/daemon/OCResolveAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1531: goto            1501
        //  1534: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      15     15     19     Ljava/lang/IllegalArgumentException;
        //  39     52     52     56     Ljava/lang/IllegalArgumentException;
        //  88     105    108    112    Ljava/lang/IllegalArgumentException;
        //  100    138    141    145    Ljava/lang/IllegalArgumentException;
        //  112    173    173    177    Ljava/lang/IllegalArgumentException;
        //  226    247    250    254    Ljava/lang/IllegalArgumentException;
        //  265    280    280    284    Ljava/lang/IllegalArgumentException;
        //  305    371    374    378    Ljava/lang/IllegalArgumentException;
        //  469    495    495    499    Ljava/lang/IllegalArgumentException;
        //  557    569    572    576    Ljava/lang/IllegalArgumentException;
        //  564    587    590    594    Ljava/lang/IllegalArgumentException;
        //  576    602    605    609    Ljava/lang/IllegalArgumentException;
        //  594    621    621    625    Ljava/lang/IllegalArgumentException;
        //  656    672    675    679    Ljava/lang/IllegalArgumentException;
        //  663    694    697    701    Ljava/lang/IllegalArgumentException;
        //  778    793    793    797    Ljava/lang/IllegalArgumentException;
        //  800    816    819    823    Ljava/lang/IllegalArgumentException;
        //  855    878    881    885    Ljava/lang/IllegalArgumentException;
        //  865    899    899    903    Ljava/lang/IllegalArgumentException;
        //  937    951    951    955    Ljava/lang/IllegalArgumentException;
        //  1016   1032   1035   1039   Ljava/lang/IllegalArgumentException;
        //  1110   1125   1128   1132   Ljava/lang/IllegalArgumentException;
        //  1155   1170   1173   1177   Ljava/lang/IllegalArgumentException;
        //  1233   1247   1250   1254   Ljava/lang/IllegalArgumentException;
        //  1265   1280   1280   1284   Ljava/lang/IllegalArgumentException;
        //  1427   1437   1440   1444   Ljava/lang/IllegalArgumentException;
        //  1432   1458   1458   1462   Ljava/lang/IllegalArgumentException;
        //  1462   1481   1481   1485   Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public void visitGenericParameter(final OCGenericParameter ocGenericParameter) {
        this.highlight(ocGenericParameter.getNameIdentifier(), OCHighlightingKeys.GENERIC_PARAMETER);
    }
    
    private static boolean a(final List<OCMethodSymbol> list) {
        try {
            if (list.size() <= 1) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final HashSet set = new HashSet();
        set.addAll(list);
        final OCMemberInheritorsSearch.SearchParameters<OCMethodSymbol> parameters = OCMemberInheritorsSearch.getParameters(list.get(0));
        parameters.setInheritors(true);
        parameters.setAncestors(true);
        parameters.setIncludeSelfImplementation(true);
        OCMemberInheritorsSearch.search((OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol>)parameters).forEach(ocMethodSymbol -> {
            set.remove(ocMethodSymbol);
            return true;
        });
        return set.isEmpty();
    }
    
    @Nullable
    private static TextAttributesKey a(@NotNull final OCSymbolKind ocSymbolKind) {
        try {
            if (ocSymbolKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/daemon/OCResolveAnnotator", "getTextAttributesKeyByKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            switch (ocSymbolKind) {
                case LOCAL_VARIABLE: {
                    return OCHighlightingKeys.LOCAL_VARIABLE;
                }
                case PARAMETER: {
                    break;
                }
                case INTERFACE:
                case IMPLEMENTATION:
                case COMPATIBILITY_ALIAS: {
                    return OCHighlightingKeys.CLASS_REFERENCE;
                }
                case PROTOCOL: {
                    return OCHighlightingKeys.PROTOCOL_REFERENCE;
                }
                case INSTANCE_VARIABLE: {
                    return OCHighlightingKeys.INSTANCE_VARIABLE;
                }
                case PROPERTY: {
                    return OCHighlightingKeys.PROPERTY;
                }
                case METHOD: {
                    return OCHighlightingKeys.MESSAGE_ARGUMENT;
                }
                case CATCH_EXCEPTION_VARIABLE: {
                    return null;
                }
                case TYPEDEF:
                case USING_SYMBOL_ALIAS: {
                    return OCHighlightingKeys.TYPEDEF;
                }
                case FUNCTION_PREDECLARATION:
                case FUNCTION_DECLARATION:
                case CPP_CONSTRUCTOR_PREDECLARATION:
                case CPP_CONSTRUCTOR_DECLARATION: {
                    return OCHighlightingKeys.FUNCTION;
                }
                case TEMPLATE_TYPE_PARAMETER: {
                    return OCHighlightingKeys.TEMPLATE_TYPE;
                }
                case TEMPLATE_VALUE_PARAMETER: {
                    return OCHighlightingKeys.TEMPLATE_VALUE;
                }
                case NAMESPACE:
                case NAMESPACE_ALIAS: {
                    return OCHighlightingKeys.NAMESPACE_LIKE;
                }
                case MACRO:
                case UNDEF_MACRO: {
                    return OCHighlightingKeys.MACRONAME;
                }
                case MACRO_PARAMETER: {
                    return OCHighlightingKeys.MACRO_PARAMETER;
                }
                case STRUCT:
                case UNION:
                case ENUM: {
                    return OCHighlightingKeys.STRUCT_LIKE;
                }
                case ENUM_CONST: {
                    return OCHighlightingKeys.ENUM_CONST;
                }
                case STRUCT_FIELD: {
                    return OCHighlightingKeys.STRUCT_FIELD;
                }
                case GLOBAL_VARIABLE:
                case GLOBAL_VARIABLE_PREDECLARATION: {
                    return OCHighlightingKeys.GLOBAL_VARIABLE;
                }
                case BUILTIN_SYMBOL: {
                    return null;
                }
                case SYNTHESIZE: {
                    return OCHighlightingKeys.PROPERTY;
                }
                case BLOCK: {
                    return null;
                }
                case LABEL: {
                    return OCHighlightingKeys.LABEL;
                }
                case GENERIC_PARAMETER: {
                    return OCHighlightingKeys.GENERIC_PARAMETER;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return OCHighlightingKeys.PARAMETER;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
