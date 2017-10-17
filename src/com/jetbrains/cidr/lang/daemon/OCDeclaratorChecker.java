// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.quickfixes.OCAddTypeModifierIntentionAction;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.quickfixes.OCChangePropertyAttributeIntentionAction;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCArrayToPointerChanger;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCMethod;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;

public class OCDeclaratorChecker extends OCAnnotatorSinkWrapper
{
    @NotNull
    private final OCCppChecker myCppChecker;
    
    public OCDeclaratorChecker(@NotNull final OCAnnotatorSink ocAnnotatorSink, @NotNull final OCCppChecker myCppChecker) {
        if (ocAnnotatorSink == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "impl", "com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker", "<init>"));
        }
        if (myCppChecker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checker", "com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker", "<init>"));
        }
        super(ocAnnotatorSink);
        this.myCppChecker = myCppChecker;
    }
    
    public boolean checkDuplicates(@NotNull final PsiNameIdentifierOwner psiNameIdentifierOwner) {
        try {
            if (psiNameIdentifierOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker", "checkDuplicates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.checkDuplicates(OCElementUtil.getSymbolName((PsiElement)psiNameIdentifierOwner), (PsiElement)psiNameIdentifierOwner, Collections.singletonList(psiNameIdentifierOwner.getNameIdentifier()));
    }
    
    public boolean checkDuplicates(final String s, final PsiElement psiElement, final List<? extends PsiElement> list) {
        return this.checkDuplicates(s, null, psiElement, list);
    }
    
    public boolean checkDuplicates(final String p0, final OCSymbol p1, final PsiElement p2, final List<? extends PsiElement> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       10
        //     4: iconst_1       
        //     5: ireturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_3        
        //    11: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    16: astore          5
        //    18: aload_1        
        //    19: aload_3        
        //    20: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
        //    23: iconst_0       
        //    24: iconst_0       
        //    25: aload           5
        //    27: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference.resolveToSymbols:(ZZLcom/intellij/psi/PsiFile;)Ljava/util/List;
        //    30: astore          6
        //    32: aload           6
        //    34: invokedynamic   compare:()Ljava/util/Comparator;
        //    39: invokeinterface java/util/List.sort:(Ljava/util/Comparator;)V
        //    44: aload_2        
        //    45: ifnonnull       134
        //    48: aload           6
        //    50: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    55: astore          7
        //    57: aload           7
        //    59: invokeinterface java/util/Iterator.hasNext:()Z
        //    64: ifeq            134
        //    67: aload           7
        //    69: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    74: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    77: astore          8
        //    79: aload           8
        //    81: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getComplexOffset:()J
        //    86: aload_3        
        //    87: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getVirtualComplexOffset:(Lcom/intellij/psi/PsiElement;)J
        //    90: lcmp           
        //    91: ifne            131
        //    94: aload           8
        //    96: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   101: aload_3        
        //   102: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   107: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   112: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   115: ifeq            131
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload           8
        //   127: astore_2       
        //   128: goto            134
        //   131: goto            57
        //   134: aload_2        
        //   135: ifnonnull       144
        //   138: iconst_1       
        //   139: ireturn        
        //   140: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: aload_2        
        //   145: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   150: astore          7
        //   152: aload           6
        //   154: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   159: astore          8
        //   161: aload           8
        //   163: invokeinterface java/util/Iterator.hasNext:()Z
        //   168: ifeq            2219
        //   171: aload           8
        //   173: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   178: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   181: astore          9
        //   183: aload           9
        //   185: aload_2        
        //   186: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   189: ifeq            199
        //   192: goto            161
        //   195: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload           9
        //   201: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   206: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   209: aload_2        
        //   210: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   215: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   218: if_icmpeq       228
        //   221: goto            161
        //   224: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: aload           9
        //   230: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   235: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNDEF_MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   238: if_acmpne       248
        //   241: goto            2219
        //   244: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aconst_null    
        //   249: astore          10
        //   251: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   254: astore          11
        //   256: aload           9
        //   258: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   261: ifeq            364
        //   264: aload_2        
        //   265: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   268: ifeq            364
        //   271: goto            278
        //   274: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: aload           9
        //   280: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   285: ifnull          364
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: aload           9
        //   297: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   302: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //   305: ifeq            364
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: aload_2        
        //   316: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   321: ifnull          364
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload_2        
        //   332: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   337: aload           9
        //   339: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   344: invokevirtual   com/intellij/openapi/util/TextRange.equals:(Ljava/lang/Object;)Z
        //   347: ifeq            364
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: goto            161
        //   360: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: aload           9
        //   366: aload_2        
        //   367: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isDuplicate:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   370: ifeq            1534
        //   373: aload           9
        //   375: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   380: astore          12
        //   382: ldc             ""
        //   384: astore          13
        //   386: aload           12
        //   388: ifnull          447
        //   391: aload           12
        //   393: aload_3        
        //   394: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   399: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   404: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   407: ifne            447
        //   410: goto            417
        //   413: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: new             Ljava/lang/StringBuilder;
        //   420: dup            
        //   421: invokespecial   java/lang/StringBuilder.<init>:()V
        //   424: ldc             " in '"
        //   426: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   429: aload           12
        //   431: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   434: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: ldc             "'"
        //   439: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   442: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   445: astore          13
        //   447: aload           9
        //   449: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   454: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   457: ifeq            548
        //   460: aload_2        
        //   461: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   466: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   469: ifeq            548
        //   472: goto            479
        //   475: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   478: athrow         
        //   479: aload           9
        //   481: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   486: aload_2        
        //   487: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   492: if_acmpeq       548
        //   495: goto            502
        //   498: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   501: athrow         
        //   502: new             Ljava/lang/StringBuilder;
        //   505: dup            
        //   506: invokespecial   java/lang/StringBuilder.<init>:()V
        //   509: ldc             "Duplicate declaration of tag '"
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: aload_2        
        //   515: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolImpl.getTagOfStructLike:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/lang/String;
        //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   521: ldc             "' with different type"
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   529: astore          14
        //   531: aload_0        
        //   532: aload           4
        //   534: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateDeclarations;.class
        //   536: ldc             "err_use_with_wrong_tag"
        //   538: aload           14
        //   540: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   543: astore          10
        //   545: goto            1531
        //   548: aload_2        
        //   549: aload_3        
        //   550: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //   553: ifeq            1029
        //   556: aload           9
        //   558: aload_3        
        //   559: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //   562: ifeq            1029
        //   565: goto            572
        //   568: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: aload_2        
        //   573: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   578: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   581: if_acmpne       599
        //   584: goto            591
        //   587: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   590: athrow         
        //   591: iconst_1       
        //   592: goto            600
        //   595: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   598: athrow         
        //   599: iconst_0       
        //   600: istore          14
        //   602: aload           9
        //   604: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   609: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   612: if_acmpne       623
        //   615: iconst_1       
        //   616: goto            624
        //   619: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   622: athrow         
        //   623: iconst_0       
        //   624: istore          15
        //   626: iload           14
        //   628: ifeq            643
        //   631: iload           15
        //   633: ifne            706
        //   636: goto            643
        //   639: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: iload           14
        //   645: ifeq            675
        //   648: goto            655
        //   651: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   654: athrow         
        //   655: aload           9
        //   657: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   662: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   665: ifne            706
        //   668: goto            675
        //   671: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   674: athrow         
        //   675: iload           15
        //   677: ifeq            877
        //   680: goto            687
        //   683: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   686: athrow         
        //   687: aload_2        
        //   688: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   693: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   696: ifeq            877
        //   699: goto            706
        //   702: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: iload           14
        //   708: ifeq            769
        //   711: goto            718
        //   714: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   717: athrow         
        //   718: iload           15
        //   720: ifeq            769
        //   723: goto            730
        //   726: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   729: athrow         
        //   730: aload_2        
        //   731: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   736: aload_3        
        //   737: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   740: aload           9
        //   742: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   747: aload           5
        //   749: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   752: aload_3        
        //   753: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   756: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   759: ifne            1026
        //   762: goto            769
        //   765: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   768: athrow         
        //   769: iload           14
        //   771: ifeq            811
        //   774: goto            781
        //   777: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   780: athrow         
        //   781: aload_2        
        //   782: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   787: aload_3        
        //   788: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   791: aload           9
        //   793: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   798: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   801: ifne            1026
        //   804: goto            811
        //   807: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   810: athrow         
        //   811: iload           15
        //   813: ifeq            860
        //   816: goto            823
        //   819: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   822: athrow         
        //   823: aload           9
        //   825: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   830: aload_3        
        //   831: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   834: aload_2        
        //   835: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   840: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   843: ifeq            860
        //   846: goto            853
        //   849: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   852: athrow         
        //   853: goto            1026
        //   856: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   859: athrow         
        //   860: aload_0        
        //   861: aload           4
        //   863: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateDeclarations;.class
        //   865: ldc             "err_use_of_tag_name_without_tag"
        //   867: ldc             "Typedef redefinition with different types"
        //   869: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   872: astore          10
        //   874: goto            1026
        //   877: new             Ljava/lang/StringBuilder;
        //   880: dup            
        //   881: invokespecial   java/lang/StringBuilder.<init>:()V
        //   884: ldc             "Duplicate declaration of "
        //   886: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   889: aload_2        
        //   890: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   895: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   898: aload           13
        //   900: invokevirtual   java/lang/String.isEmpty:()Z
        //   903: ifne            933
        //   906: new             Ljava/lang/StringBuilder;
        //   909: dup            
        //   910: invokespecial   java/lang/StringBuilder.<init>:()V
        //   913: ldc             "; previous one was"
        //   915: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   918: aload           13
        //   920: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   923: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   926: goto            935
        //   929: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   932: athrow         
        //   933: ldc             ""
        //   935: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   938: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   941: astore          16
        //   943: aload           9
        //   945: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   948: ifeq            987
        //   951: aload           9
        //   953: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   956: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   961: ifnull          987
        //   964: goto            971
        //   967: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   970: athrow         
        //   971: aload_0        
        //   972: aload           4
        //   974: aconst_null    
        //   975: ldc             "warn_dup_category_def"
        //   977: aload           16
        //   979: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   982: astore          10
        //   984: goto            1026
        //   987: aload           9
        //   989: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   992: ifeq            1012
        //   995: aload_0        
        //   996: aload           4
        //   998: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$HidesUpperScope;.class
        //  1000: ldc             "err_redefinition"
        //  1002: aload           16
        //  1004: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //  1007: astore          10
        //  1009: goto            1026
        //  1012: aload_0        
        //  1013: aload           4
        //  1015: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateDeclarations;.class
        //  1017: ldc             "err_redefinition"
        //  1019: aload           16
        //  1021: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //  1024: astore          10
        //  1026: goto            1531
        //  1029: aload           9
        //  1031: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1036: aload           5
        //  1038: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1041: astore          14
        //  1043: new             Ljava/lang/StringBuilder;
        //  1046: dup            
        //  1047: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1050: ldc             "all declarations of '"
        //  1052: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1055: aload_2        
        //  1056: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1061: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1064: ldc             "'"
        //  1066: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1069: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1072: astore          15
        //  1074: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //  1077: dup            
        //  1078: aload_2        
        //  1079: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //  1084: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //  1087: astore          16
        //  1089: aload           14
        //  1091: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //  1094: ifeq            1163
        //  1097: aload           14
        //  1099: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //  1102: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //  1105: ifne            1163
        //  1108: goto            1115
        //  1111: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1114: athrow         
        //  1115: aload           7
        //  1117: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //  1120: ifeq            1163
        //  1123: goto            1130
        //  1126: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1129: athrow         
        //  1130: aload           9
        //  1132: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1135: ifeq            1163
        //  1138: goto            1145
        //  1141: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1144: athrow         
        //  1145: aload           9
        //  1147: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1150: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isExtern:()Z
        //  1153: ifne            1171
        //  1156: goto            1163
        //  1159: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1162: athrow         
        //  1163: iconst_1       
        //  1164: goto            1172
        //  1167: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1170: athrow         
        //  1171: iconst_0       
        //  1172: istore          17
        //  1174: aload           9
        //  1176: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1179: ifeq            1260
        //  1182: aload           9
        //  1184: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1187: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isExplicitInstantiation:()Z
        //  1192: ifeq            1260
        //  1195: goto            1202
        //  1198: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1201: athrow         
        //  1202: aload_2        
        //  1203: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1206: ifeq            1260
        //  1209: goto            1216
        //  1212: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1215: athrow         
        //  1216: aload_2        
        //  1217: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1220: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isExplicitInstantiation:()Z
        //  1225: ifeq            1260
        //  1228: goto            1235
        //  1231: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1234: athrow         
        //  1235: ldc             "inspections.duplicate.explicitInstantiation"
        //  1237: iconst_0       
        //  1238: anewarray       Ljava/lang/Object;
        //  1241: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1244: astore          18
        //  1246: aload_0        
        //  1247: aload           4
        //  1249: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateDeclarations;.class
        //  1251: ldc             "err_explicit_instantiation_duplicate"
        //  1253: aload           18
        //  1255: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //  1258: astore          10
        //  1260: aload           9
        //  1262: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1265: aload_2        
        //  1266: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1269: if_icmpeq       1335
        //  1272: new             Ljava/lang/StringBuilder;
        //  1275: dup            
        //  1276: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1279: aload_2        
        //  1280: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //  1285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1288: ldc             " was redeclared with different kind of symbol, previous one was \""
        //  1290: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1293: aload           9
        //  1295: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKindUppercase:()Ljava/lang/String;
        //  1300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1303: ldc             "\""
        //  1305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1308: aload           13
        //  1310: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1313: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1316: astore          18
        //  1318: aload_0        
        //  1319: aload           4
        //  1321: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateDeclarations;.class
        //  1323: ldc             "err_redefinition_different_kind"
        //  1325: aload           18
        //  1327: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //  1330: astore          10
        //  1332: goto            1531
        //  1335: aload           9
        //  1337: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1340: ifne            1531
        //  1343: aload_2        
        //  1344: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1347: ifeq            1364
        //  1350: goto            1357
        //  1353: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1356: athrow         
        //  1357: goto            1531
        //  1360: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1363: athrow         
        //  1364: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
        //  1367: dup            
        //  1368: aload           14
        //  1370: iconst_0       
        //  1371: iconst_1       
        //  1372: iconst_0       
        //  1373: iconst_0       
        //  1374: iconst_1       
        //  1375: aload           16
        //  1377: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //  1380: aload           7
        //  1382: iload           17
        //  1384: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;Z)Z
        //  1387: ifne            1531
        //  1390: new             Ljava/lang/StringBuilder;
        //  1393: dup            
        //  1394: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1397: aload_2        
        //  1398: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //  1403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1406: ldc             " was redeclared with different type '"
        //  1408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1411: aload           7
        //  1413: aload           16
        //  1415: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/String;
        //  1418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1421: ldc             "'; previous was '"
        //  1423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1426: aload           14
        //  1428: aload           16
        //  1430: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/String;
        //  1433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1436: ldc             "'"
        //  1438: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1441: aload           13
        //  1443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1446: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1449: astore          18
        //  1451: aload_0        
        //  1452: aload           4
        //  1454: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateDeclarations;.class
        //  1456: ldc             "err_redefinition_different_type"
        //  1458: aload           18
        //  1460: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //  1463: astore          10
        //  1465: aload_2        
        //  1466: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1469: ifeq            1531
        //  1472: aload           9
        //  1474: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1477: ifeq            1531
        //  1480: goto            1487
        //  1483: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1486: athrow         
        //  1487: iconst_2       
        //  1488: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1491: dup            
        //  1492: iconst_0       
        //  1493: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //  1496: dup            
        //  1497: aload_2        
        //  1498: aload           9
        //  1500: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1505: aload           15
        //  1507: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)V
        //  1510: aastore        
        //  1511: dup            
        //  1512: iconst_1       
        //  1513: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //  1516: dup            
        //  1517: aload_2        
        //  1518: aload           7
        //  1520: aload           15
        //  1522: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)V
        //  1525: aastore        
        //  1526: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //  1529: astore          11
        //  1531: goto            2023
        //  1534: aload_2        
        //  1535: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1538: ifeq            1556
        //  1541: aload_0        
        //  1542: aload           4
        //  1544: aload_2        
        //  1545: aload           9
        //  1547: aload_3        
        //  1548: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //  1551: astore          10
        //  1553: goto            2023
        //  1556: aload_3        
        //  1557: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1560: ifeq            1616
        //  1563: aload_2        
        //  1564: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1567: ifeq            1616
        //  1570: goto            1577
        //  1573: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1576: athrow         
        //  1577: aload           9
        //  1579: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1582: ifeq            1616
        //  1585: goto            1592
        //  1588: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1591: athrow         
        //  1592: aload_0        
        //  1593: aload_3        
        //  1594: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1597: aload_2        
        //  1598: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1601: aload           9
        //  1603: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1606: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCMethod;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //  1609: goto            2023
        //  1612: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1615: athrow         
        //  1616: aload_2        
        //  1617: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1620: ifeq            1696
        //  1623: aload           9
        //  1625: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1628: ifeq            1696
        //  1631: goto            1638
        //  1634: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1637: athrow         
        //  1638: aload_2        
        //  1639: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1642: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1647: aload           9
        //  1649: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1652: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1657: if_acmpeq       1696
        //  1660: goto            1667
        //  1663: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1666: athrow         
        //  1667: aload_0        
        //  1668: aload_3        
        //  1669: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1674: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //  1677: aload_2        
        //  1678: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1681: aload           9
        //  1683: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1686: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //  1689: goto            2023
        //  1692: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1695: athrow         
        //  1696: aload_2        
        //  1697: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1700: ifeq            1847
        //  1703: aload           9
        //  1705: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1708: ifeq            1847
        //  1711: goto            1718
        //  1714: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1717: athrow         
        //  1718: aload           9
        //  1720: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1723: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1728: ifnonnull       1847
        //  1731: goto            1738
        //  1734: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1737: athrow         
        //  1738: aload           9
        //  1740: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //  1745: astore          12
        //  1747: aload_2        
        //  1748: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1751: astore          13
        //  1753: new             Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker$1;
        //  1756: dup            
        //  1757: aload_0        
        //  1758: aload           13
        //  1760: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker$1.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //  1763: astore          14
        //  1765: aload           13
        //  1767: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1772: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1775: aload           9
        //  1777: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1782: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //  1784: aload           14
        //  1786: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //  1791: pop            
        //  1792: aload           12
        //  1794: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1797: ifeq            1844
        //  1800: aload           14
        //  1802: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
        //  1805: ifeq            1844
        //  1808: goto            1815
        //  1811: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1814: athrow         
        //  1815: aload_0        
        //  1816: aload           12
        //  1818: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1821: aload           9
        //  1823: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1826: aload           14
        //  1828: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //  1831: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1834: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCMethod;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //  1837: goto            1844
        //  1840: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1843: athrow         
        //  1844: goto            2023
        //  1847: aload_2        
        //  1848: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1851: ifeq            2023
        //  1854: aload           9
        //  1856: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1859: ifeq            2023
        //  1862: goto            1869
        //  1865: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1868: athrow         
        //  1869: aload           9
        //  1871: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1874: astore          12
        //  1876: aload_2        
        //  1877: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1880: astore          13
        //  1882: aload           12
        //  1884: aload           5
        //  1886: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:(Lcom/intellij/psi/PsiFile;)Z
        //  1891: ifne            2023
        //  1894: aload           12
        //  1896: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1901: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1904: aload           13
        //  1906: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1911: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1914: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSameClass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
        //  1919: ifne            2023
        //  1922: goto            1929
        //  1925: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1928: athrow         
        //  1929: aload           12
        //  1931: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1936: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1939: if_acmpeq       2023
        //  1942: goto            1949
        //  1945: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1948: athrow         
        //  1949: new             Ljava/lang/StringBuilder;
        //  1952: dup            
        //  1953: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1956: aload           13
        //  1958: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //  1963: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1966: ldc             " overrides "
        //  1968: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1971: aload           12
        //  1973: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //  1978: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1981: ldc             " in inherited "
        //  1983: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1986: aload           12
        //  1988: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1993: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  1996: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //  2001: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2004: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2007: astore          14
        //  2009: aload_0        
        //  2010: aload           4
        //  2012: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$HidesUpperScope;.class
        //  2014: ldc             "err_duplicate_member"
        //  2016: aload           14
        //  2018: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //  2021: astore          10
        //  2023: aload           11
        //  2025: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  2030: astore          12
        //  2032: aload           12
        //  2034: invokeinterface java/util/Iterator.hasNext:()Z
        //  2039: ifeq            2065
        //  2042: aload           12
        //  2044: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2049: checkcast       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  2052: astore          13
        //  2054: aload_0        
        //  2055: aload           10
        //  2057: aload           13
        //  2059: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  2062: goto            2032
        //  2065: aload_2        
        //  2066: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2071: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2074: if_acmpne       2123
        //  2077: aload_0        
        //  2078: aload           10
        //  2080: new             Lcom/jetbrains/cidr/lang/quickfixes/OCReuseDeclarationIntentionAction;
        //  2083: dup            
        //  2084: aload_2        
        //  2085: new             Ljava/lang/StringBuilder;
        //  2088: dup            
        //  2089: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2092: ldc             "Reuse previous declaration of "
        //  2094: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2097: aload           9
        //  2099: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //  2104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2107: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2110: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReuseDeclarationIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;)V
        //  2113: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  2116: goto            2151
        //  2119: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2122: athrow         
        //  2123: aload_0        
        //  2124: aload           10
        //  2126: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //  2129: dup            
        //  2130: aload_2        
        //  2131: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  2134: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  2137: aload_0        
        //  2138: aload           10
        //  2140: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction;
        //  2143: dup            
        //  2144: aload_2        
        //  2145: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  2148: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  2151: aload           9
        //  2153: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2158: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2161: if_acmpeq       2205
        //  2164: aload_0        
        //  2165: aload           10
        //  2167: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //  2170: dup            
        //  2171: ldc             "previous declaration"
        //  2173: aload           9
        //  2175: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  2178: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  2181: aload_0        
        //  2182: aload           10
        //  2184: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction;
        //  2187: dup            
        //  2188: ldc             "previous declaration"
        //  2190: aload           9
        //  2192: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  2195: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  2198: goto            2205
        //  2201: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2204: athrow         
        //  2205: aload           10
        //  2207: ifnull          2216
        //  2210: iconst_0       
        //  2211: ireturn        
        //  2212: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2215: athrow         
        //  2216: goto            161
        //  2219: iconst_1       
        //  2220: ireturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Ljava/util/List<+Lcom/intellij/psi/PsiElement;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  79     118    121    125    Ljava/lang/IllegalArgumentException;
        //  134    140    140    144    Ljava/lang/IllegalArgumentException;
        //  183    195    195    199    Ljava/lang/IllegalArgumentException;
        //  199    224    224    228    Ljava/lang/IllegalArgumentException;
        //  228    244    244    248    Ljava/lang/IllegalArgumentException;
        //  256    271    274    278    Ljava/lang/IllegalArgumentException;
        //  264    288    291    295    Ljava/lang/IllegalArgumentException;
        //  278    308    311    315    Ljava/lang/IllegalArgumentException;
        //  295    324    327    331    Ljava/lang/IllegalArgumentException;
        //  315    350    353    357    Ljava/lang/IllegalArgumentException;
        //  331    360    360    364    Ljava/lang/IllegalArgumentException;
        //  386    410    413    417    Ljava/lang/IllegalArgumentException;
        //  447    472    475    479    Ljava/lang/IllegalArgumentException;
        //  460    495    498    502    Ljava/lang/IllegalArgumentException;
        //  548    565    568    572    Ljava/lang/IllegalArgumentException;
        //  556    584    587    591    Ljava/lang/IllegalArgumentException;
        //  572    595    595    599    Ljava/lang/IllegalArgumentException;
        //  602    619    619    623    Ljava/lang/IllegalArgumentException;
        //  626    636    639    643    Ljava/lang/IllegalArgumentException;
        //  631    648    651    655    Ljava/lang/IllegalArgumentException;
        //  643    668    671    675    Ljava/lang/IllegalArgumentException;
        //  655    680    683    687    Ljava/lang/IllegalArgumentException;
        //  675    699    702    706    Ljava/lang/IllegalArgumentException;
        //  687    711    714    718    Ljava/lang/IllegalArgumentException;
        //  706    723    726    730    Ljava/lang/IllegalArgumentException;
        //  718    762    765    769    Ljava/lang/IllegalArgumentException;
        //  730    774    777    781    Ljava/lang/IllegalArgumentException;
        //  769    804    807    811    Ljava/lang/IllegalArgumentException;
        //  781    816    819    823    Ljava/lang/IllegalArgumentException;
        //  811    846    849    853    Ljava/lang/IllegalArgumentException;
        //  823    856    856    860    Ljava/lang/IllegalArgumentException;
        //  877    929    929    933    Ljava/lang/IllegalArgumentException;
        //  943    964    967    971    Ljava/lang/IllegalArgumentException;
        //  1089   1108   1111   1115   Ljava/lang/IllegalArgumentException;
        //  1097   1123   1126   1130   Ljava/lang/IllegalArgumentException;
        //  1115   1138   1141   1145   Ljava/lang/IllegalArgumentException;
        //  1130   1156   1159   1163   Ljava/lang/IllegalArgumentException;
        //  1145   1167   1167   1171   Ljava/lang/IllegalArgumentException;
        //  1174   1195   1198   1202   Ljava/lang/IllegalArgumentException;
        //  1182   1209   1212   1216   Ljava/lang/IllegalArgumentException;
        //  1202   1228   1231   1235   Ljava/lang/IllegalArgumentException;
        //  1335   1350   1353   1357   Ljava/lang/IllegalArgumentException;
        //  1343   1360   1360   1364   Ljava/lang/IllegalArgumentException;
        //  1465   1480   1483   1487   Ljava/lang/IllegalArgumentException;
        //  1556   1570   1573   1577   Ljava/lang/IllegalArgumentException;
        //  1563   1585   1588   1592   Ljava/lang/IllegalArgumentException;
        //  1577   1612   1612   1616   Ljava/lang/IllegalArgumentException;
        //  1616   1631   1634   1638   Ljava/lang/IllegalArgumentException;
        //  1623   1660   1663   1667   Ljava/lang/IllegalArgumentException;
        //  1638   1692   1692   1696   Ljava/lang/IllegalArgumentException;
        //  1696   1711   1714   1718   Ljava/lang/IllegalArgumentException;
        //  1703   1731   1734   1738   Ljava/lang/IllegalArgumentException;
        //  1765   1808   1811   1815   Ljava/lang/IllegalArgumentException;
        //  1800   1837   1840   1844   Ljava/lang/IllegalArgumentException;
        //  1847   1862   1865   1869   Ljava/lang/IllegalArgumentException;
        //  1882   1922   1925   1929   Ljava/lang/IllegalArgumentException;
        //  1894   1942   1945   1949   Ljava/lang/IllegalArgumentException;
        //  2065   2119   2119   2123   Ljava/lang/IllegalArgumentException;
        //  2151   2198   2201   2205   Ljava/lang/IllegalArgumentException;
        //  2205   2212   2212   2216   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0278:
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
    
    private static boolean a(final OCSymbol p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //     6: ifeq            150
        //     9: aload_0        
        //    10: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    13: ifne            150
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    22: athrow         
        //    23: aload_0        
        //    24: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    27: ifeq            85
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_1        
        //    38: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //    41: ifne            85
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_0        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isExtern:()Z
        //    58: ifeq            150
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_0        
        //    69: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    72: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getInitializer:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    75: ifnonnull       150
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_0        
        //    86: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    89: ifeq            116
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_0        
        //   100: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
        //   106: ifeq            130
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_0        
        //   117: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   120: ifeq            158
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_0        
        //   131: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   134: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   137: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   140: ifeq            158
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: iconst_1       
        //   151: goto            159
        //   154: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: iconst_0       
        //   159: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     19     23     Ljava/lang/IllegalArgumentException;
        //  9      30     33     37     Ljava/lang/IllegalArgumentException;
        //  23     44     47     51     Ljava/lang/IllegalArgumentException;
        //  37     61     64     68     Ljava/lang/IllegalArgumentException;
        //  51     78     81     85     Ljava/lang/IllegalArgumentException;
        //  68     92     95     99     Ljava/lang/IllegalArgumentException;
        //  85     109    112    116    Ljava/lang/IllegalArgumentException;
        //  99     123    126    130    Ljava/lang/IllegalArgumentException;
        //  116    143    146    150    Ljava/lang/IllegalArgumentException;
        //  130    154    154    158    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    private List<Annotation> a(final List<? extends PsiElement> p0, final OCSymbol p1, final OCSymbol p2, final PsiElement p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_3        
        //     4: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //     7: ifeq            117
        //    10: aload_2        
        //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    16: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    19: if_acmpeq       117
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_2        
        //    30: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //    35: astore          6
        //    37: aload_3        
        //    38: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //    43: astore          7
        //    45: aload           6
        //    47: ifnull          114
        //    50: aload           7
        //    52: ifnull          114
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload           7
        //    64: aload           6
        //    66: invokevirtual   com/intellij/openapi/util/TextRange.contains:(Lcom/intellij/openapi/util/TextRange;)Z
        //    69: ifeq            114
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: aload_1        
        //    81: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$HidesUpperScope;.class
        //    83: ldc             "warn_decl_shadow"
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: aload_2        
        //    93: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: ldc             " hides previous declaration in the upper scope"
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   109: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   112: astore          5
        //   114: goto            367
        //   117: aload_3        
        //   118: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   121: ifeq            367
        //   124: aload_3        
        //   125: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   128: astore          6
        //   130: aload           4
        //   132: iconst_1       
        //   133: anewarray       Ljava/lang/Class;
        //   136: dup            
        //   137: iconst_0       
        //   138: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   140: aastore        
        //   141: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   144: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   147: astore          7
        //   149: aload           7
        //   151: ifnonnull       160
        //   154: aconst_null    
        //   155: areturn        
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload           7
        //   162: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   167: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   170: astore          8
        //   172: ldc             ""
        //   174: astore          9
        //   176: aload           8
        //   178: ifnull          218
        //   181: aload           8
        //   183: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isStatic:()Z
        //   188: ifne            218
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: aload           8
        //   200: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   205: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   208: ifne            224
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: aconst_null    
        //   219: areturn        
        //   220: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload           8
        //   226: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   231: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   234: astore          10
        //   236: aload           6
        //   238: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   243: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   246: astore          11
        //   248: aload           10
        //   250: aload           11
        //   252: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   257: ifne            306
        //   260: new             Ljava/lang/StringBuilder;
        //   263: dup            
        //   264: invokespecial   java/lang/StringBuilder.<init>:()V
        //   267: ldc             " declared in "
        //   269: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   272: aload           11
        //   274: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   285: astore          9
        //   287: aload           6
        //   289: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   294: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   297: if_acmpne       306
        //   300: aconst_null    
        //   301: areturn        
        //   302: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: new             Ljava/lang/StringBuilder;
        //   309: dup            
        //   310: invokespecial   java/lang/StringBuilder.<init>:()V
        //   313: aload_2        
        //   314: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: ldc             " hides the "
        //   324: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   327: aload_3        
        //   328: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   333: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: ldc             " with the same name"
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: aload           9
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   352: astore          12
        //   354: aload_0        
        //   355: aload_1        
        //   356: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$HidesClassScope;.class
        //   358: ldc             "warn_ivar_use_hidden"
        //   360: aload           12
        //   362: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   365: astore          5
        //   367: aload_0        
        //   368: aload           5
        //   370: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRenameSymbolIntentionAction;
        //   373: dup            
        //   374: aload_2        
        //   375: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRenameSymbolIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   378: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   381: aload           5
        //   383: areturn        
        //    Signature:
        //  (Ljava/util/List<+Lcom/intellij/psi/PsiElement;>;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Ljava/util/List<Lcom/intellij/lang/annotation/Annotation;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  3      22     25     29     Ljava/lang/IllegalArgumentException;
        //  45     55     58     62     Ljava/lang/IllegalArgumentException;
        //  50     72     75     79     Ljava/lang/IllegalArgumentException;
        //  149    156    156    160    Ljava/lang/IllegalArgumentException;
        //  176    191    194    198    Ljava/lang/IllegalArgumentException;
        //  181    211    214    218    Ljava/lang/IllegalArgumentException;
        //  198    220    220    224    Ljava/lang/IllegalArgumentException;
        //  287    302    302    306    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0198:
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
    
    private void a(final OCMethod ocMethod, final OCMethodSymbol ocMethodSymbol, final OCMethodSymbol ocMethodSymbol2) {
        try {
            if (ocMethodSymbol2.equals(ocMethodSymbol.getAssociatedSymbol())) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocMethodSymbol.isStatic() != ocMethodSymbol2.isStatic()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCPropertySymbol generatedFromProperty = ocMethodSymbol2.getGeneratedFromProperty();
        String string = "";
        Label_0133: {
            StringBuilder sb2 = null;
            String s2 = null;
            Label_0103: {
                Label_0092: {
                    try {
                        if (Comparing.equal(((OCSymbolWithParent<T, Object>)ocMethodSymbol).getParent(), ((OCSymbolWithParent<T, Object>)ocMethodSymbol2).getParent())) {
                            break Label_0133;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final String s = "; ";
                        sb2 = sb.append(s);
                        final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                        if (ocPropertySymbol != null) {
                            break Label_0092;
                        }
                        break Label_0092;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final StringBuilder sb = new StringBuilder();
                        final String s = "; ";
                        sb2 = sb.append(s);
                        final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                        if (ocPropertySymbol != null) {
                            s2 = "property";
                            break Label_0103;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                s2 = "base method";
            }
            string = sb2.append(s2).append(" was declared in ").append(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol2).getParent().getNameWithKindLowercase()).toString();
        }
        final OCType returnType = ocMethodSymbol.getReturnType();
        final OCType returnType2 = ocMethodSymbol2.getReturnType();
        Label_0529: {
            Label_0386: {
                Label_0320: {
                    Label_0172: {
                        try {
                            if (returnType2.isSuperType(returnType, (PsiElement)ocMethod)) {
                                break Label_0529;
                            }
                            final OCPropertySymbol ocPropertySymbol2 = generatedFromProperty;
                            if (ocPropertySymbol2 != null) {
                                break Label_0172;
                            }
                            break Label_0320;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                        try {
                            final OCPropertySymbol ocPropertySymbol2 = generatedFromProperty;
                            if (ocPropertySymbol2 == null) {
                                break Label_0320;
                            }
                            if (!OCNameSuggester.isObjCGetter(ocMethodSymbol.getName())) {
                                break Label_0320;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw b(ex6);
                        }
                    }
                    final Annotation addWarningAnnotation = this.addWarningAnnotation((PsiElement)ocMethod.getReturnTypeElement(), OCInspections.AssociatedTypeMismatch.class, "warn_non_covariant_overriding_ret_types", "Return type (" + returnType.getName((PsiElement)ocMethod) + ") of getter method for " + generatedFromProperty.getNameWithKindLowercase() + " doesn't match the type (" + generatedFromProperty.getType().getName((PsiElement)ocMethod) + ") of property" + string);
                    this.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol, generatedFromProperty.getType()));
                    this.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCChangeTypeIntentionAction(generatedFromProperty, returnType));
                    break Label_0529;
                    try {
                        if (generatedFromProperty == null || !OCNameSuggester.isObjCSetter(ocMethodSymbol.getName())) {
                            break Label_0386;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                this.registerQuickFix(this.addWarningAnnotation((PsiElement)ocMethod.getReturnTypeElement(), OCInspections.AssociatedTypeMismatch.class, "warn_non_covariant_overriding_ret_types", "Return type of setter method must be 'void'"), (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol, OCVoidType.instance()));
                break Label_0529;
            }
            final Annotation addWarningAnnotation2 = this.addWarningAnnotation((PsiElement)ocMethod.getReturnTypeElement(), OCInspections.OverriddenTypeMismatch.class, "warn_non_covariant_overriding_ret_types", "Return type (" + returnType.getName((PsiElement)ocMethod) + ") of overridden method doesn't match the return type (" + returnType2.getName((PsiElement)ocMethod) + ") of base method");
            this.registerQuickFix(addWarningAnnotation2, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol, returnType2, "overridden " + ocMethodSymbol.getNameWithKindLowercase()));
            this.registerQuickFix(addWarningAnnotation2, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol2, returnType, "base " + ocMethodSymbol2.getNameWithKindLowercase()));
        }
        for (int i = 0; i < ocMethodSymbol.getSelectors().size(); ++i) {
            final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(i).getParameter();
            final OCDeclaratorSymbol parameter2 = ocMethodSymbol2.getSelectors().get(i).getParameter();
            Label_0611: {
                try {
                    if (parameter == null) {
                        continue;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol = parameter2;
                    if (ocDeclaratorSymbol == null) {
                        break Label_0611;
                    }
                    break Label_0611;
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
                try {
                    final OCDeclaratorSymbol ocDeclaratorSymbol = parameter2;
                    if (ocDeclaratorSymbol == null) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
            }
            final OCType type = parameter.getType();
            final OCType type2 = parameter2.getType();
            if (!type.isSuperType(type2, (PsiElement)ocMethod)) {
                final OCTypeElement typeElement = ocMethod.getParameters().get(i).getTypeElement();
                if (generatedFromProperty != null) {
                    final Annotation addWarningAnnotation3 = this.addWarningAnnotation((PsiElement)typeElement, OCInspections.AssociatedTypeMismatch.class, "warn_non_contravariant_overriding_param_types", "Parameter type (" + type.getName((PsiElement)ocMethod) + ") of setter method for " + generatedFromProperty.getNameWithKindLowercase() + " doesn't match the type (" + generatedFromProperty.getType().getName((PsiElement)ocMethod) + ") of property" + string);
                    this.registerQuickFix(addWarningAnnotation3, (IntentionAction)new OCChangeTypeIntentionAction(parameter, generatedFromProperty.getType()));
                    this.registerQuickFix(addWarningAnnotation3, (IntentionAction)new OCChangeTypeIntentionAction(generatedFromProperty, type));
                }
                else {
                    final Annotation addWarningAnnotation4 = this.addWarningAnnotation((PsiElement)typeElement, OCInspections.OverriddenTypeMismatch.class, "warn_non_contravariant_overriding_param_types", "Parameter type (" + type.getName((PsiElement)ocMethod) + ") of overridden method doesn't match the parameter type (" + type2.getName((PsiElement)ocMethod) + ") of base method");
                    this.registerQuickFix(addWarningAnnotation4, (IntentionAction)new OCChangeTypeIntentionAction(parameter, type2, "overridden " + parameter.getNameWithKindLowercase()));
                    this.registerQuickFix(addWarningAnnotation4, (IntentionAction)new OCChangeTypeIntentionAction(parameter2, type, "base " + parameter2.getNameWithKindLowercase()));
                }
            }
        }
    }
    
    private void a(final OCDeclaration p0, final OCPropertySymbol p1, final OCPropertySymbol p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //     6: astore          4
        //     8: aload_3        
        //     9: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: astore          5
        //    16: aload_0        
        //    17: aload_3        
        //    18: ldc             "base property"
        //    20: aload_2        
        //    21: ldc             "overridden property"
        //    23: aload_1        
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    29: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$OverriddenTypeMismatch;.class
        //    31: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Z
        //    34: pop            
        //    35: iconst_3       
        //    36: anewarray       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    39: dup            
        //    40: iconst_0       
        //    41: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ATOMIC:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    44: aastore        
        //    45: dup            
        //    46: iconst_1       
        //    47: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READWRITE:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    50: aastore        
        //    51: dup            
        //    52: iconst_2       
        //    53: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.STRONG:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    56: aastore        
        //    57: astore          6
        //    59: aload_2        
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    65: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    68: astore          7
        //    70: aload_3        
        //    71: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    76: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    79: astore          8
        //    81: aload_1        
        //    82: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getParent:()Lcom/intellij/psi/PsiElement;
        //    87: astore          9
        //    89: aconst_null    
        //    90: astore          10
        //    92: aload           9
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    97: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getPropertyAttributesList:()Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   102: ifnull          117
        //   105: aload           9
        //   107: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   110: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getPropertyAttributesList:()Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   115: astore          9
        //   117: aload           6
        //   119: astore          11
        //   121: aload           11
        //   123: arraylength    
        //   124: istore          12
        //   126: iconst_0       
        //   127: istore          13
        //   129: iload           13
        //   131: iload           12
        //   133: if_icmpge       667
        //   136: aload           11
        //   138: iload           13
        //   140: aaload         
        //   141: astore          14
        //   143: aload_2        
        //   144: aload           14
        //   146: aload           4
        //   148: aload_1        
        //   149: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAttributeOfGroup:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   154: astore          15
        //   156: aload_3        
        //   157: aload           14
        //   159: aload           5
        //   161: aload_1        
        //   162: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAttributeOfGroup:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   167: astore          16
        //   169: iconst_0       
        //   170: istore          17
        //   172: iconst_0       
        //   173: istore          18
        //   175: new             Ljava/lang/StringBuilder;
        //   178: dup            
        //   179: invokespecial   java/lang/StringBuilder.<init>:()V
        //   182: ldc             "Overridden property is "
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: aload           15
        //   189: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.toString:()Ljava/lang/String;
        //   192: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: ldc             "; the base property is "
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: aload           16
        //   205: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.toString:()Ljava/lang/String;
        //   208: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   217: astore          19
        //   219: ldc             "err_objc_property_attr_mutually_exclusive"
        //   221: astore          20
        //   223: iconst_2       
        //   224: anewarray       Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   227: dup            
        //   228: iconst_0       
        //   229: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   232: dup            
        //   233: aload_2        
        //   234: aload           15
        //   236: aload           16
        //   238: aconst_null    
        //   239: ldc             "overridden property"
        //   241: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;Ljava/lang/String;)V
        //   244: aastore        
        //   245: dup            
        //   246: iconst_1       
        //   247: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   250: dup            
        //   251: aload_3        
        //   252: aload           16
        //   254: aload           15
        //   256: aconst_null    
        //   257: ldc             "base property"
        //   259: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;Ljava/lang/String;)V
        //   262: aastore        
        //   263: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   266: astore          21
        //   268: aload           14
        //   270: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ATOMIC:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   273: if_acmpne       304
        //   276: aload           15
        //   278: aload           16
        //   280: if_acmpeq       298
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: iconst_1       
        //   291: goto            299
        //   294: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: iconst_0       
        //   299: istore          17
        //   301: goto            569
        //   304: aload           14
        //   306: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READWRITE:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   309: if_acmpne       530
        //   312: ldc             ""
        //   314: aload           7
        //   316: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   321: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   324: ifeq            491
        //   327: goto            334
        //   330: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload           8
        //   336: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   341: ifnonnull       491
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   350: athrow         
        //   351: aload           7
        //   353: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   358: aload           8
        //   360: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   365: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   368: ifeq            491
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload_3        
        //   379: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   384: ifne            440
        //   387: goto            394
        //   390: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   393: athrow         
        //   394: iconst_1       
        //   395: anewarray       Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   398: dup            
        //   399: iconst_0       
        //   400: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   403: dup            
        //   404: aload_3        
        //   405: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READWRITE:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   408: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READONLY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   411: aconst_null    
        //   412: ldc             "base property"
        //   414: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;Ljava/lang/String;)V
        //   417: aastore        
        //   418: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   421: astore          21
        //   423: ldc             "Overridden properties must be 'readonly' in the interface"
        //   425: astore          19
        //   427: ldc             "err_use_continuation_class_redeclaration_readwrite"
        //   429: astore          20
        //   431: iconst_1       
        //   432: dup            
        //   433: istore          18
        //   435: istore          17
        //   437: goto            569
        //   440: aload_2        
        //   441: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   446: ifeq            569
        //   449: iconst_1       
        //   450: anewarray       Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   453: dup            
        //   454: iconst_0       
        //   455: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   458: dup            
        //   459: aload_2        
        //   460: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READONLY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   463: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READWRITE:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   466: aconst_null    
        //   467: ldc             "category property"
        //   469: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;Ljava/lang/String;)V
        //   472: aastore        
        //   473: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   476: astore          21
        //   478: ldc             "Overridden properties must be 'readwrite' in the private category"
        //   480: astore          19
        //   482: iconst_1       
        //   483: dup            
        //   484: istore          18
        //   486: istore          17
        //   488: goto            569
        //   491: aload_2        
        //   492: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   497: ifeq            524
        //   500: aload_3        
        //   501: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   506: ifne            524
        //   509: goto            516
        //   512: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   515: athrow         
        //   516: iconst_1       
        //   517: goto            525
        //   520: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   523: athrow         
        //   524: iconst_0       
        //   525: istore          17
        //   527: goto            569
        //   530: aload           14
        //   532: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.STRONG:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   535: if_acmpne       569
        //   538: aload           15
        //   540: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getSemanticsGroup:()I
        //   543: aload           16
        //   545: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getSemanticsGroup:()I
        //   548: if_icmpeq       566
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: iconst_1       
        //   559: goto            567
        //   562: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   565: athrow         
        //   566: iconst_0       
        //   567: istore          17
        //   569: iload           17
        //   571: ifeq            661
        //   574: iload           18
        //   576: ifeq            605
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: aload_0        
        //   587: aload           9
        //   589: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$OverriddenAttributeMismatch;.class
        //   591: aload           20
        //   593: aload           19
        //   595: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   598: goto            617
        //   601: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   604: athrow         
        //   605: aload_0        
        //   606: aload           9
        //   608: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$OverriddenAttributeMismatch;.class
        //   610: aload           20
        //   612: aload           19
        //   614: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   617: astore          10
        //   619: aload           21
        //   621: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   626: astore          22
        //   628: aload           22
        //   630: invokeinterface java/util/Iterator.hasNext:()Z
        //   635: ifeq            661
        //   638: aload           22
        //   640: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   645: checkcast       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   648: astore          23
        //   650: aload_0        
        //   651: aload           10
        //   653: aload           23
        //   655: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   658: goto            628
        //   661: iinc            13, 1
        //   664: goto            129
        //   667: aload           10
        //   669: ifnull          695
        //   672: aload_0        
        //   673: aload           10
        //   675: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //   678: dup            
        //   679: ldc             "overridden part"
        //   681: aload_2        
        //   682: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   685: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   688: goto            695
        //   691: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   694: athrow         
        //   695: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  268    283    286    290    Ljava/lang/IllegalArgumentException;
        //  276    294    294    298    Ljava/lang/IllegalArgumentException;
        //  304    327    330    334    Ljava/lang/IllegalArgumentException;
        //  312    344    347    351    Ljava/lang/IllegalArgumentException;
        //  334    371    374    378    Ljava/lang/IllegalArgumentException;
        //  351    387    390    394    Ljava/lang/IllegalArgumentException;
        //  491    509    512    516    Ljava/lang/IllegalArgumentException;
        //  500    520    520    524    Ljava/lang/IllegalArgumentException;
        //  530    551    554    558    Ljava/lang/IllegalArgumentException;
        //  538    562    562    566    Ljava/lang/IllegalArgumentException;
        //  569    579    582    586    Ljava/lang/IllegalArgumentException;
        //  574    601    601    605    Ljava/lang/IllegalArgumentException;
        //  667    688    691    695    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0334:
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
    
    public void checkDeclarator(@Nullable final OCType p0, @NotNull final PsiNameIdentifierOwner p1, @Nullable final OCDeclarator p2, final OCSymbol p3) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkDeclarator"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getSymbolName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    48: astore          5
        //    50: aload_0        
        //    51: aload_2        
        //    52: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Lcom/intellij/psi/PsiNameIdentifierOwner;)Z
        //    55: ifne            63
        //    58: return         
        //    59: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_2        
        //    64: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;.class
        //    66: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    69: ifnonnull       161
        //    72: aload           5
        //    74: aload_2        
        //    75: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    78: dup            
        //    79: aload_2        
        //    80: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    83: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getSelfSuperToken:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //    86: ifnull          161
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: aload_2        
        //    98: ldc             "err_redefinition_different_type"
        //   100: new             Ljava/lang/StringBuilder;
        //   103: dup            
        //   104: invokespecial   java/lang/StringBuilder.<init>:()V
        //   107: ldc             "Can't create a variable with name '"
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: aload           5
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: ldc             "' inside the method"
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   125: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   128: astore          6
        //   130: aload_0        
        //   131: aload           6
        //   133: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //   136: dup            
        //   137: aload           4
        //   139: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   142: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   145: aload_0        
        //   146: aload           6
        //   148: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction;
        //   151: dup            
        //   152: aload           4
        //   154: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   157: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   160: return         
        //   161: aload_1        
        //   162: ifnull          177
        //   165: aload           4
        //   167: ifnonnull       182
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: return         
        //   178: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_3        
        //   183: ifnull          392
        //   186: aload_3        
        //   187: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   192: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   195: ifeq            392
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aload_1        
        //   206: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.hasAutoInside:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   209: ifeq            392
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload_3        
        //   220: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   225: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   230: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   235: astore          6
        //   237: aload_3        
        //   238: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   243: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   246: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   251: astore          7
        //   253: aload           6
        //   255: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   260: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   263: ifeq            347
        //   266: aload_3        
        //   267: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNode:()Lcom/intellij/lang/ASTNode;
        //   272: aconst_null    
        //   273: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //   278: invokestatic    java/util/Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //   281: invokedynamic   test:()Ljava/util/function/Predicate;
        //   286: invokeinterface java/util/stream/Stream.noneMatch:(Ljava/util/function/Predicate;)Z
        //   291: ifeq            347
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: aload_0        
        //   302: aload           7
        //   304: ldc             "err_auto_not_allowed"
        //   306: new             Ljava/lang/StringBuilder;
        //   309: dup            
        //   310: invokespecial   java/lang/StringBuilder.<init>:()V
        //   313: ldc             "'"
        //   315: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   318: aload           7
        //   320: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getText:()Ljava/lang/String;
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: ldc             "' not allowed in function prototype"
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   336: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   339: pop            
        //   340: goto            392
        //   343: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   346: athrow         
        //   347: aload           6
        //   349: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   352: ifeq            392
        //   355: aload_3        
        //   356: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   361: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsCxxGenericLambdas:(Lcom/intellij/psi/PsiFile;)Z
        //   364: ifne            392
        //   367: goto            374
        //   370: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: aload_0        
        //   375: aload           7
        //   377: ldc             "err_auto_not_allowed"
        //   379: ldc             "Generic lambdas are not supported by the compiler"
        //   381: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   384: pop            
        //   385: goto            392
        //   388: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: aload_2        
        //   393: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   398: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   401: astore          6
        //   403: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   406: dup            
        //   407: aload_2        
        //   408: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   411: astore          7
        //   413: aload_3        
        //   414: ifnull          430
        //   417: aload_3        
        //   418: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   423: goto            431
        //   426: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   429: athrow         
        //   430: aconst_null    
        //   431: astore          8
        //   433: aload_1        
        //   434: aload           7
        //   436: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   439: astore_1       
        //   440: aload_0        
        //   441: aload_1        
        //   442: aload_3        
        //   443: aload           4
        //   445: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   448: ifne            465
        //   451: aload_1        
        //   452: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   455: ifeq            470
        //   458: goto            465
        //   461: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   464: athrow         
        //   465: return         
        //   466: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   469: athrow         
        //   470: aload_3        
        //   471: ifnull          837
        //   474: aload_1        
        //   475: aload           4
        //   477: aload           7
        //   479: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.requiresInitializer:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   482: ifeq            837
        //   485: goto            492
        //   488: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   491: athrow         
        //   492: aload           4
        //   494: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   497: ifeq            525
        //   500: goto            507
        //   503: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   506: athrow         
        //   507: aload           4
        //   509: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   512: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isExtern:()Z
        //   515: ifne            837
        //   518: goto            525
        //   521: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   524: athrow         
        //   525: aload_3        
        //   526: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializers:()Ljava/util/List;
        //   531: invokeinterface java/util/List.isEmpty:()Z
        //   536: ifeq            837
        //   539: goto            546
        //   542: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   545: athrow         
        //   546: aload_3        
        //   547: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getArrayLengths:()Ljava/util/List;
        //   552: invokeinterface java/util/List.isEmpty:()Z
        //   557: ifne            583
        //   560: goto            567
        //   563: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: aload_3        
        //   568: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   573: ifnonnull       837
        //   576: goto            583
        //   579: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   582: athrow         
        //   583: aload           8
        //   585: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   590: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   595: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLoopStatement;
        //   598: ifne            837
        //   601: goto            608
        //   604: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   607: athrow         
        //   608: getstatic       com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker$4.$SwitchMap$com$jetbrains$cidr$lang$symbols$OCSymbolKind:[I
        //   611: aload           4
        //   613: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   618: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.ordinal:()I
        //   621: iaload         
        //   622: tableswitch {
        //                2: 656
        //                3: 656
        //                4: 656
        //                5: 797
        //          default: 837
        //        }
        //   652: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   655: athrow         
        //   656: new             Ljava/lang/StringBuilder;
        //   659: dup            
        //   660: invokespecial   java/lang/StringBuilder.<init>:()V
        //   663: ldc             "Declaration of "
        //   665: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   668: aload_1        
        //   669: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   672: ifeq            691
        //   675: goto            682
        //   678: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   681: athrow         
        //   682: ldc             "reference"
        //   684: goto            693
        //   687: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   690: athrow         
        //   691: ldc             "const"
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: ldc             " variable requires an initializer"
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   704: astore          9
        //   706: aload_1        
        //   707: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   710: ifeq            735
        //   713: new             Ljava/lang/StringBuilder;
        //   716: dup            
        //   717: invokespecial   java/lang/StringBuilder.<init>:()V
        //   720: aload           9
        //   722: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   725: ldc             " or an explicit default constructor"
        //   727: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   730: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   733: astore          9
        //   735: aload_0        
        //   736: aload_2        
        //   737: ldc             "err_reference_var_requires_init"
        //   739: aload           9
        //   741: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   744: astore          10
        //   746: aload_0        
        //   747: aload           10
        //   749: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction;
        //   752: dup            
        //   753: aload_3        
        //   754: aload           4
        //   756: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   759: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   762: aload_1        
        //   763: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   766: ifeq            837
        //   769: aload_0        
        //   770: aload           10
        //   772: new             Lcom/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix;
        //   775: dup            
        //   776: aload_1        
        //   777: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   780: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   783: iconst_0       
        //   784: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Z)V
        //   787: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   790: goto            837
        //   793: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   796: athrow         
        //   797: aload_1        
        //   798: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   801: ifeq            837
        //   804: aload_0        
        //   805: aload_2        
        //   806: ldc             "err_ivar_reference_type"
        //   808: ldc             "Instance variables can't have reference type"
        //   810: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   813: astore          11
        //   815: aload_0        
        //   816: aload           11
        //   818: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   821: dup            
        //   822: aload           4
        //   824: aload_1        
        //   825: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   828: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   831: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   834: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   837: aload           6
        //   839: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   844: ifne            1037
        //   847: aload_2        
        //   848: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   853: ifnonnull       1037
        //   856: goto            863
        //   859: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   862: athrow         
        //   863: aload_2        
        //   864: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   869: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   872: ifeq            1037
        //   875: goto            882
        //   878: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   881: athrow         
        //   882: aload_2        
        //   883: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   888: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   893: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   896: ifeq            1037
        //   899: goto            906
        //   902: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   905: athrow         
        //   906: aload_2        
        //   907: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   912: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   917: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   920: astore          9
        //   922: aload           9
        //   924: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParent:()Lcom/intellij/psi/PsiElement;
        //   929: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   934: astore          10
        //   936: aload           10
        //   938: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   941: ifeq            1037
        //   944: aload           10
        //   946: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   949: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   954: ifnull          1037
        //   957: goto            964
        //   960: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   963: athrow         
        //   964: aload_1        
        //   965: instanceof      Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   968: ifne            1037
        //   971: goto            978
        //   974: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   977: athrow         
        //   978: aload_1        
        //   979: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   982: ifeq            1015
        //   985: goto            992
        //   988: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   991: athrow         
        //   992: aload           9
        //   994: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //   999: invokeinterface java/util/List.size:()I
        //  1004: iconst_1       
        //  1005: if_icmpeq       1037
        //  1008: goto            1015
        //  1011: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1014: athrow         
        //  1015: aload_0        
        //  1016: aload_2        
        //  1017: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //  1022: ldc             "err_parameter_name_omitted"
        //  1024: ldc             "Parameter name is omitted"
        //  1026: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1029: pop            
        //  1030: goto            1037
        //  1033: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1036: athrow         
        //  1037: aload_1        
        //  1038: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  1041: ifne            1073
        //  1044: aload_0        
        //  1045: aload_1        
        //  1046: aload_2        
        //  1047: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //  1052: aload           4
        //  1054: iconst_0       
        //  1055: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkInstanceable:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Z
        //  1058: ifne            1073
        //  1061: goto            1068
        //  1064: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1067: athrow         
        //  1068: return         
        //  1069: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1072: athrow         
        //  1073: aload_1        
        //  1074: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  1077: ifeq            1164
        //  1080: aload_1        
        //  1081: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  1084: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1087: astore          9
        //  1089: aload_2        
        //  1090: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //  1095: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //  1098: ifeq            1122
        //  1101: aload_2        
        //  1102: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //  1107: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //  1110: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //  1115: goto            1128
        //  1118: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1121: athrow         
        //  1122: aload_2        
        //  1123: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //  1128: astore          10
        //  1130: aload           9
        //  1132: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //  1135: ifne            1164
        //  1138: aload_0        
        //  1139: aload           9
        //  1141: aload           10
        //  1143: aload           4
        //  1145: iconst_1       
        //  1146: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkInstanceable:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Z
        //  1149: ifne            1164
        //  1152: goto            1159
        //  1155: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1158: athrow         
        //  1159: return         
        //  1160: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1163: athrow         
        //  1164: aload_0        
        //  1165: aload_2        
        //  1166: aload           4
        //  1168: aload           6
        //  1170: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //  1173: aload           6
        //  1175: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //  1180: ifeq            1237
        //  1183: aload           8
        //  1185: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //  1188: ifeq            1237
        //  1191: goto            1198
        //  1194: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1197: athrow         
        //  1198: aload           4
        //  1200: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1203: ifeq            1237
        //  1206: goto            1213
        //  1209: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1212: athrow         
        //  1213: aload_0        
        //  1214: getfield        com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //  1217: aload           8
        //  1219: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //  1222: aload           4
        //  1224: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1227: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkCppFunction:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //  1230: goto            1237
        //  1233: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1236: athrow         
        //  1237: aload_0        
        //  1238: aload_1        
        //  1239: aload_2        
        //  1240: aload           4
        //  1242: aload           6
        //  1244: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/psi/OCFile;)Z
        //  1247: ifne            1255
        //  1250: return         
        //  1251: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1254: athrow         
        //  1255: aload           4
        //  1257: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1260: ifeq            1280
        //  1263: aload           4
        //  1265: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1268: astore          9
        //  1270: aload_0        
        //  1271: aload_2        
        //  1272: aload           9
        //  1274: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //  1277: goto            1406
        //  1280: aload           4
        //  1282: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1285: ifeq            1406
        //  1288: aload           4
        //  1290: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1293: astore          9
        //  1295: aload           9
        //  1297: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1302: astore          10
        //  1304: aload           10
        //  1306: ifnull          1373
        //  1309: aload           6
        //  1311: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //  1314: ifeq            1342
        //  1317: goto            1324
        //  1320: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1323: athrow         
        //  1324: aload           10
        //  1326: iconst_0       
        //  1327: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAllAccessorsImplemented:(Z)Z
        //  1332: ifne            1373
        //  1335: goto            1342
        //  1338: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1341: athrow         
        //  1342: aload_0        
        //  1343: aload           10
        //  1345: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1348: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //  1351: aload           9
        //  1353: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1356: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //  1359: aload_2        
        //  1360: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PropertyAndIvarTypeMismatch;.class
        //  1362: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Z
        //  1365: pop            
        //  1366: goto            1373
        //  1369: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1372: athrow         
        //  1373: aload           6
        //  1375: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //  1378: ifeq            1406
        //  1381: aload_0        
        //  1382: aload_2        
        //  1383: aload           9
        //  1385: aload           9
        //  1387: aload           6
        //  1389: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getARCAttribute:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  1394: aload           10
        //  1396: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //  1399: goto            1406
        //  1402: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1405: athrow         
        //  1406: aload_3        
        //  1407: ifnonnull       1415
        //  1410: return         
        //  1411: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1414: athrow         
        //  1415: aload_0        
        //  1416: aload_3        
        //  1417: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;)V
        //  1420: aload           8
        //  1422: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1427: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //  1430: ifeq            1463
        //  1433: aload           8
        //  1435: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1440: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //  1443: astore          9
        //  1445: aload           9
        //  1447: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1452: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //  1455: ifeq            1463
        //  1458: return         
        //  1459: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1462: athrow         
        //  1463: aload_3        
        //  1464: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1469: astore          9
        //  1471: aload           9
        //  1473: ifnull          1501
        //  1476: aload_0        
        //  1477: aload_3        
        //  1478: aload           4
        //  1480: aload_1        
        //  1481: aload_2        
        //  1482: aconst_null    
        //  1483: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //  1486: ifne            1501
        //  1489: goto            1496
        //  1492: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1495: athrow         
        //  1496: return         
        //  1497: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1500: athrow         
        //  1501: aload_3        
        //  1502: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getReference:()Lcom/intellij/psi/PsiReference;
        //  1507: astore          10
        //  1509: aconst_null    
        //  1510: astore          11
        //  1512: aload           6
        //  1514: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //  1519: ifeq            1649
        //  1522: aload           10
        //  1524: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReference;
        //  1527: ifeq            1649
        //  1530: goto            1537
        //  1533: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1536: athrow         
        //  1537: aload           4
        //  1539: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1544: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1547: if_acmpeq       1649
        //  1550: goto            1557
        //  1553: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1556: athrow         
        //  1557: aload           4
        //  1559: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1564: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CATCH_EXCEPTION_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1567: if_acmpeq       1649
        //  1570: goto            1577
        //  1573: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1576: athrow         
        //  1577: aload_1        
        //  1578: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //  1581: ifeq            1613
        //  1584: goto            1591
        //  1587: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1590: athrow         
        //  1591: aload_3        
        //  1592: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializers:()Ljava/util/List;
        //  1597: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //  1600: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //  1603: ifeq            1649
        //  1606: goto            1613
        //  1609: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1612: athrow         
        //  1613: aload           10
        //  1615: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReference;
        //  1618: invokeinterface com/jetbrains/cidr/lang/psi/OCReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1623: astore          11
        //  1625: aload_0        
        //  1626: getfield        com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //  1629: aload_3        
        //  1630: aload_3        
        //  1631: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializers:()Ljava/util/List;
        //  1636: aload           11
        //  1638: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkConstructorCall:(Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //  1641: ifne            1649
        //  1644: return         
        //  1645: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1648: athrow         
        //  1649: aload           9
        //  1651: ifnonnull       1673
        //  1654: aload_0        
        //  1655: aload_3        
        //  1656: aload           4
        //  1658: aload_1        
        //  1659: aload_2        
        //  1660: aload           11
        //  1662: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //  1665: pop            
        //  1666: goto            1673
        //  1669: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1672: athrow         
        //  1673: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  50     59     59     63     Ljava/lang/IllegalArgumentException;
        //  63     89     92     96     Ljava/lang/IllegalArgumentException;
        //  161    170    173    177    Ljava/lang/IllegalArgumentException;
        //  165    178    178    182    Ljava/lang/IllegalArgumentException;
        //  182    198    201    205    Ljava/lang/IllegalArgumentException;
        //  186    212    215    219    Ljava/lang/IllegalArgumentException;
        //  253    294    297    301    Ljava/lang/IllegalArgumentException;
        //  266    343    343    347    Ljava/lang/IllegalArgumentException;
        //  347    367    370    374    Ljava/lang/IllegalArgumentException;
        //  355    385    388    392    Ljava/lang/IllegalArgumentException;
        //  413    426    426    430    Ljava/lang/IllegalArgumentException;
        //  440    458    461    465    Ljava/lang/IllegalArgumentException;
        //  451    466    466    470    Ljava/lang/IllegalArgumentException;
        //  470    485    488    492    Ljava/lang/IllegalArgumentException;
        //  474    500    503    507    Ljava/lang/IllegalArgumentException;
        //  492    518    521    525    Ljava/lang/IllegalArgumentException;
        //  507    539    542    546    Ljava/lang/IllegalArgumentException;
        //  525    560    563    567    Ljava/lang/IllegalArgumentException;
        //  546    576    579    583    Ljava/lang/IllegalArgumentException;
        //  567    601    604    608    Ljava/lang/IllegalArgumentException;
        //  583    652    652    656    Ljava/lang/IllegalArgumentException;
        //  608    675    678    682    Ljava/lang/IllegalArgumentException;
        //  656    687    687    691    Ljava/lang/IllegalArgumentException;
        //  746    793    793    797    Ljava/lang/IllegalArgumentException;
        //  837    856    859    863    Ljava/lang/IllegalArgumentException;
        //  847    875    878    882    Ljava/lang/IllegalArgumentException;
        //  863    899    902    906    Ljava/lang/IllegalArgumentException;
        //  936    957    960    964    Ljava/lang/IllegalArgumentException;
        //  944    971    974    978    Ljava/lang/IllegalArgumentException;
        //  964    985    988    992    Ljava/lang/IllegalArgumentException;
        //  978    1008   1011   1015   Ljava/lang/IllegalArgumentException;
        //  992    1030   1033   1037   Ljava/lang/IllegalArgumentException;
        //  1037   1061   1064   1068   Ljava/lang/IllegalArgumentException;
        //  1044   1069   1069   1073   Ljava/lang/IllegalArgumentException;
        //  1089   1118   1118   1122   Ljava/lang/IllegalArgumentException;
        //  1130   1152   1155   1159   Ljava/lang/IllegalArgumentException;
        //  1138   1160   1160   1164   Ljava/lang/IllegalArgumentException;
        //  1164   1191   1194   1198   Ljava/lang/IllegalArgumentException;
        //  1183   1206   1209   1213   Ljava/lang/IllegalArgumentException;
        //  1198   1230   1233   1237   Ljava/lang/IllegalArgumentException;
        //  1237   1251   1251   1255   Ljava/lang/IllegalArgumentException;
        //  1304   1317   1320   1324   Ljava/lang/IllegalArgumentException;
        //  1309   1335   1338   1342   Ljava/lang/IllegalArgumentException;
        //  1324   1366   1369   1373   Ljava/lang/IllegalArgumentException;
        //  1373   1399   1402   1406   Ljava/lang/IllegalArgumentException;
        //  1406   1411   1411   1415   Ljava/lang/IllegalArgumentException;
        //  1445   1459   1459   1463   Ljava/lang/IllegalArgumentException;
        //  1471   1489   1492   1496   Ljava/lang/IllegalArgumentException;
        //  1476   1497   1497   1501   Ljava/lang/IllegalArgumentException;
        //  1512   1530   1533   1537   Ljava/lang/IllegalArgumentException;
        //  1522   1550   1553   1557   Ljava/lang/IllegalArgumentException;
        //  1537   1570   1573   1577   Ljava/lang/IllegalArgumentException;
        //  1557   1584   1587   1591   Ljava/lang/IllegalArgumentException;
        //  1577   1606   1609   1613   Ljava/lang/IllegalArgumentException;
        //  1625   1645   1645   1649   Ljava/lang/IllegalArgumentException;
        //  1649   1666   1669   1673   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0492:
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
    
    protected static boolean requiresInitializer(final OCType p0, final OCSymbol p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "requiresInitializer"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    48: ifeq            65
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getDeclarationInParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    58: goto            66
        //    61: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aconst_null    
        //    66: astore_3       
        //    67: aload_3        
        //    68: ifnull          91
        //    71: aload_3        
        //    72: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getInitializer:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    75: ifnull          91
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    87: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_0        
        //    92: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    95: ifeq            124
        //    98: aload_0        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   102: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   105: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   108: ifne            124
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: iconst_1       
        //   119: ireturn        
        //   120: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: aload_2        
        //   125: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //   128: ifeq            207
        //   131: aload_0        
        //   132: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   135: ifeq            207
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_0        
        //   146: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   149: ifne            207
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_0        
        //   160: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   163: ifeq            205
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload_0        
        //   174: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   177: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   180: aload_2        
        //   181: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/intellij/util/Processor;
        //   186: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processConstructors:(Lcom/intellij/util/Processor;)Z
        //   189: ifne            205
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: iconst_0       
        //   200: ireturn        
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: iconst_1       
        //   206: ireturn        
        //   207: iconst_0       
        //   208: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     61     61     65     Ljava/lang/IllegalArgumentException;
        //  67     78     81     85     Ljava/lang/IllegalArgumentException;
        //  71     87     87     91     Ljava/lang/IllegalArgumentException;
        //  91     111    114    118    Ljava/lang/IllegalArgumentException;
        //  98     120    120    124    Ljava/lang/IllegalArgumentException;
        //  124    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    152    155    159    Ljava/lang/IllegalArgumentException;
        //  145    166    169    173    Ljava/lang/IllegalArgumentException;
        //  159    192    195    199    Ljava/lang/IllegalArgumentException;
        //  173    201    201    205    Ljava/lang/IllegalArgumentException;
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
    
    private boolean a(final OCType p0, final PsiNameIdentifierOwner p1, final OCSymbol p2, final OCFile p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //     5: ifeq            205
        //     8: aload_3        
        //     9: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: astore          5
        //    16: aload_3        
        //    17: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpne       186
        //    28: aload           5
        //    30: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    33: ifeq            186
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload           4
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    50: ifne            186
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aload           5
        //    62: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    65: ifeq            88
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload           5
        //    77: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    80: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    83: astore          5
        //    85: goto            60
        //    88: aload           5
        //    90: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    93: ifeq            184
        //    96: aload           5
        //    98: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   101: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   104: ifnonnull       184
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   117: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getUsageOfArcObjectInStruct:()Ljava/lang/String;
        //   120: astore          6
        //   122: aload_0        
        //   123: aload_2        
        //   124: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //   126: aload           6
        //   128: ldc             "Object struct fields must have an attribute in ARC"
        //   130: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   133: astore          7
        //   135: invokestatic    com/jetbrains/cidr/lang/types/ARCAttribute.values:()[Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   138: astore          8
        //   140: aload           8
        //   142: arraylength    
        //   143: istore          9
        //   145: iconst_0       
        //   146: istore          10
        //   148: iload           10
        //   150: iload           9
        //   152: if_icmpge       184
        //   155: aload           8
        //   157: iload           10
        //   159: aaload         
        //   160: astore          11
        //   162: aload_0        
        //   163: aload           7
        //   165: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction;
        //   168: dup            
        //   169: aload_3        
        //   170: aload           11
        //   172: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/ARCAttribute;)V
        //   175: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   178: iinc            10, 1
        //   181: goto            148
        //   184: iconst_0       
        //   185: ireturn        
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload_3        
        //   193: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkARCPointerTypes:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   196: ifne            205
        //   199: iconst_0       
        //   200: ireturn        
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: iconst_1       
        //   206: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  16     36     39     43     Ljava/lang/IllegalArgumentException;
        //  28     53     56     60     Ljava/lang/IllegalArgumentException;
        //  43     68     71     75     Ljava/lang/IllegalArgumentException;
        //  88     107    110    114    Ljava/lang/IllegalArgumentException;
        //  186    201    201    205    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    private static boolean b(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker", "isMemberFunction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0071: {
            try {
                if (!(ocSymbol instanceof OCFunctionSymbol)) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol2.getParent();
                final boolean b = ocSymbolWithQualifiedName instanceof OCStructSymbol;
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol2.getParent();
                final boolean b = ocSymbolWithQualifiedName instanceof OCStructSymbol;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    private void a(final PsiNameIdentifierOwner p0, final OCSymbol<?> p1, final OCFile p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //     8: astore          4
        //    10: aload_2        
        //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    16: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    19: if_acmpeq       69
        //    22: aload_2        
        //    23: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    28: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    31: if_acmpeq       69
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_2        
        //    42: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    45: ifne            69
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_2        
        //    56: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    59: ifeq            94
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_2        
        //    70: aload_0        
        //    71: aload_2        
        //    72: aload           4
        //    74: aload_1        
        //    75: aload_3        
        //    76: invokedynamic   process:(Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/psi/OCFile;)Lcom/intellij/util/Processor;
        //    81: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //    86: pop            
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol<*>;Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     34     37     41     Ljava/lang/IllegalArgumentException;
        //  22     48     51     55     Ljava/lang/IllegalArgumentException;
        //  41     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     87     90     94     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    private boolean a(final OCType p0, final OCDeclarator p1, final OCSymbol p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          22
        //     4: aload_2        
        //     5: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getArrayLengths:()Ljava/util/List;
        //    10: invokeinterface java/util/List.size:()I
        //    15: goto            23
        //    18: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    21: athrow         
        //    22: iconst_0       
        //    23: istore          4
        //    25: iload           4
        //    27: ifle            58
        //    30: aload_1        
        //    31: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    34: ifeq            58
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iinc            4, -1
        //    47: aload_1        
        //    48: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    51: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    54: astore_1       
        //    55: goto            25
        //    58: aload_1        
        //    59: aload_2        
        //    60: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    63: astore          5
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //    69: ifeq            123
        //    72: aload           5
        //    74: ldc             "struct"
        //    76: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    79: ifne            154
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           5
        //    91: ldc             "union"
        //    93: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    96: ifne            154
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload           5
        //   108: ldc             "enum"
        //   110: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   113: ifne            154
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_1        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   127: ifeq            424
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload_1        
        //   138: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPredeclaration:()Z
        //   144: ifeq            424
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_3        
        //   155: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   158: ifeq            193
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload_3        
        //   169: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   172: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isExtern:()Z
        //   175: ifeq            193
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: iconst_1       
        //   186: goto            194
        //   189: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: iconst_0       
        //   194: istore          6
        //   196: aload_1        
        //   197: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   200: ifeq            257
        //   203: aload_1        
        //   204: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   207: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   210: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   213: if_acmpne       257
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_1        
        //   224: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   227: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   230: aload_2        
        //   231: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getBaseCppClasses:(Lcom/intellij/psi/PsiElement;)Ljava/util/Collection;
        //   234: invokeinterface java/util/Collection.isEmpty:()Z
        //   239: ifne            257
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: iconst_1       
        //   250: goto            258
        //   253: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: iconst_0       
        //   258: istore          7
        //   260: aload_3        
        //   261: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   266: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   269: if_acmpeq       424
        //   272: iload           6
        //   274: ifne            424
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: iload           7
        //   286: ifne            424
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload_0        
        //   297: aload_2        
        //   298: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$CannotResolve;.class
        //   300: ldc             "err_typecheck_decl_incomplete_type"
        //   302: ldc             "Instantiating an unknown structure without a reference"
        //   304: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   307: astore          8
        //   309: aload_1        
        //   310: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   313: ifeq            339
        //   316: aload_2        
        //   317: ifnull          339
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: aload_1        
        //   328: aload_2        
        //   329: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   334: iconst_1       
        //   335: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   338: astore_1       
        //   339: aload_1        
        //   340: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   343: ifeq            422
        //   346: aload_1        
        //   347: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   350: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   353: iconst_0       
        //   354: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   359: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   362: astore          9
        //   364: aload           9
        //   366: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   369: astore          10
        //   371: aload           10
        //   373: ifnull          422
        //   376: aload_2        
        //   377: ifnull          422
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: aload_2        
        //   388: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   393: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   396: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   401: astore          11
        //   403: aload_0        
        //   404: aload           8
        //   406: new             Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix;
        //   409: dup            
        //   410: aload           11
        //   412: aload           10
        //   414: iconst_0       
        //   415: iconst_0       
        //   416: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;ZZ)V
        //   419: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   422: iconst_1       
        //   423: ireturn        
        //   424: iconst_0       
        //   425: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     18     22     Ljava/lang/IllegalArgumentException;
        //  25     37     40     44     Ljava/lang/IllegalArgumentException;
        //  65     82     85     89     Ljava/lang/IllegalArgumentException;
        //  72     99     102    106    Ljava/lang/IllegalArgumentException;
        //  89     116    119    123    Ljava/lang/IllegalArgumentException;
        //  106    130    133    137    Ljava/lang/IllegalArgumentException;
        //  123    147    150    154    Ljava/lang/IllegalArgumentException;
        //  137    161    164    168    Ljava/lang/IllegalArgumentException;
        //  154    178    181    185    Ljava/lang/IllegalArgumentException;
        //  168    189    189    193    Ljava/lang/IllegalArgumentException;
        //  196    216    219    223    Ljava/lang/IllegalArgumentException;
        //  203    242    245    249    Ljava/lang/IllegalArgumentException;
        //  223    253    253    257    Ljava/lang/IllegalArgumentException;
        //  260    277    280    284    Ljava/lang/IllegalArgumentException;
        //  272    289    292    296    Ljava/lang/IllegalArgumentException;
        //  309    320    323    327    Ljava/lang/IllegalArgumentException;
        //  371    380    383    387    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
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
    
    private void a(final PsiNameIdentifierOwner p0, final OCPropertySymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     6: astore_3       
        //     7: aload_2        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    13: aload_3        
        //    14: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    17: astore          4
        //    19: aload           4
        //    21: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    24: ifne            42
        //    27: aload           4
        //    29: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    32: ifeq            57
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_0        
        //    43: aload_1        
        //    44: ldc             "err_property_type"
        //    46: ldc             "Properties can't have the array or function type"
        //    48: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    51: pop            
        //    52: return         
        //    53: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_1        
        //    58: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    63: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsExplicitAtomic:(Lcom/intellij/psi/PsiFile;)Z
        //    66: ifne            349
        //    69: aload           4
        //    71: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    74: ifeq            349
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_2        
        //    85: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.STRONG:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    88: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //    93: ifne            349
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_2        
        //   104: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.WEAK:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   107: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   112: ifne            349
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_2        
        //   123: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   126: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   131: ifne            349
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: aload_2        
        //   142: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.RETAIN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   145: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   150: ifne            349
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload_2        
        //   161: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   164: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   169: ifne            349
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload_2        
        //   180: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.UNSAFE_UNRETAINED:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   183: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   188: ifne            349
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: aload_2        
        //   199: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.READONLY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   202: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   207: ifne            349
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: aload_0        
        //   218: aload_1        
        //   219: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   224: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   229: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NoAttributeForProperty;.class
        //   231: ldc             "warn_objc_property_no_assignment_attribute"
        //   233: ldc             "No 'strong', 'weak', 'assign', 'retain', or 'copy' attribute is specified - 'assign' is assumed"
        //   235: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   238: astore          5
        //   240: aload_3        
        //   241: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   244: ifeq            292
        //   247: aload_0        
        //   248: aload           5
        //   250: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   253: dup            
        //   254: aload_2        
        //   255: aconst_null    
        //   256: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.STRONG:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   259: aconst_null    
        //   260: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   263: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   266: aload_0        
        //   267: aload           5
        //   269: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   272: dup            
        //   273: aload_2        
        //   274: aconst_null    
        //   275: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.WEAK:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   278: aconst_null    
        //   279: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   282: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload_0        
        //   293: aload           5
        //   295: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   298: dup            
        //   299: aload_2        
        //   300: aconst_null    
        //   301: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   304: aconst_null    
        //   305: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   308: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   311: aload_0        
        //   312: aload           5
        //   314: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   317: dup            
        //   318: aload_2        
        //   319: aconst_null    
        //   320: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.RETAIN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   323: aconst_null    
        //   324: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   327: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   330: aload_0        
        //   331: aload           5
        //   333: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   336: dup            
        //   337: aload_2        
        //   338: aconst_null    
        //   339: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   342: aconst_null    
        //   343: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   346: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   349: aload_2        
        //   350: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   355: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   358: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   363: ifnonnull       402
        //   366: aload_2        
        //   367: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedPropertyInPrivateCategory:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   372: astore          5
        //   374: aload           5
        //   376: ifnull          402
        //   379: aload_0        
        //   380: aload_1        
        //   381: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   386: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   389: aload           5
        //   391: aload_2        
        //   392: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: aload_2        
        //   403: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedIvar:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   408: astore          5
        //   410: aload_3        
        //   411: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   414: ifeq            482
        //   417: aload           5
        //   419: ifnull          452
        //   422: goto            429
        //   425: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: aload_0        
        //   430: aload_1        
        //   431: aload           5
        //   433: aload           5
        //   435: aload_3        
        //   436: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getARCAttribute:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   441: aload_2        
        //   442: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //   445: goto            452
        //   448: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: aload           4
        //   454: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   457: ifeq            482
        //   460: aload_0        
        //   461: aload_1        
        //   462: aload_2        
        //   463: aload           4
        //   465: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   468: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   471: aload_2        
        //   472: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/intellij/psi/PsiNameIdentifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //   475: goto            482
        //   478: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   481: athrow         
        //   482: aload_3        
        //   483: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //   486: ifeq            506
        //   489: aload_2        
        //   490: iconst_0       
        //   491: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAllAccessorsImplemented:(Z)Z
        //   496: ifne            536
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload_0        
        //   507: aload_2        
        //   508: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   511: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   514: aload           5
        //   516: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   519: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   522: aload_1        
        //   523: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PropertyAndIvarTypeMismatch;.class
        //   525: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Z
        //   528: pop            
        //   529: goto            536
        //   532: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   535: athrow         
        //   536: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     35     38     42     Ljava/lang/IllegalArgumentException;
        //  27     53     53     57     Ljava/lang/IllegalArgumentException;
        //  57     77     80     84     Ljava/lang/IllegalArgumentException;
        //  69     96     99     103    Ljava/lang/IllegalArgumentException;
        //  84     115    118    122    Ljava/lang/IllegalArgumentException;
        //  103    134    137    141    Ljava/lang/IllegalArgumentException;
        //  122    153    156    160    Ljava/lang/IllegalArgumentException;
        //  141    172    175    179    Ljava/lang/IllegalArgumentException;
        //  160    191    194    198    Ljava/lang/IllegalArgumentException;
        //  179    210    213    217    Ljava/lang/IllegalArgumentException;
        //  240    285    288    292    Ljava/lang/IllegalArgumentException;
        //  374    395    398    402    Ljava/lang/IllegalArgumentException;
        //  410    422    425    429    Ljava/lang/IllegalArgumentException;
        //  417    445    448    452    Ljava/lang/IllegalArgumentException;
        //  452    475    478    482    Ljava/lang/IllegalArgumentException;
        //  482    499    502    506    Ljava/lang/IllegalArgumentException;
        //  489    529    532    536    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
    
    private void a(final PsiNameIdentifierOwner p0, final OCSymbol p1, final ARCAttribute p2, final OCPropertySymbol p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: ifnull          19
        //     5: aload           4
        //     7: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    12: goto            20
        //    15: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: aconst_null    
        //    20: astore          5
        //    22: aload           4
        //    24: ifnull          350
        //    27: aload_2        
        //    28: ifnull          350
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_3        
        //    39: ifnull          350
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload           5
        //    51: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //    54: ifeq            350
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload           4
        //    66: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    69: aload           5
        //    71: aload_1        
        //    72: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAttributeOfGroup:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    77: astore          6
        //    79: aload_3        
        //    80: aload           6
        //    82: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getIvarCompatibleARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //    85: if_acmpeq       350
        //    88: aload           6
        //    90: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    93: if_acmpne       120
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload           4
        //   105: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   110: ifne            350
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload_2        
        //   121: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   124: ifeq            201
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: new             Ljava/lang/StringBuilder;
        //   137: dup            
        //   138: invokespecial   java/lang/StringBuilder.<init>:()V
        //   141: ldc             "Ivar for "
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: aload           6
        //   148: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getTokenName:()Ljava/lang/String;
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: ldc             " "
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: aload           4
        //   161: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: ldc             " must be "
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: aload           6
        //   176: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getIvarCompatibleARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   179: invokevirtual   com/jetbrains/cidr/lang/types/ARCAttribute.getTokenName:()Ljava/lang/String;
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: astore          7
        //   190: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   193: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getWeakProperty:()Ljava/lang/String;
        //   196: astore          8
        //   198: goto            258
        //   201: aload_2        
        //   202: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   205: ifeq            257
        //   208: new             Ljava/lang/StringBuilder;
        //   211: dup            
        //   212: invokespecial   java/lang/StringBuilder.<init>:()V
        //   215: ldc             "Attributes '"
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: aload           6
        //   222: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getTokenName:()Ljava/lang/String;
        //   225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   228: ldc             "' and '"
        //   230: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: aload_3        
        //   234: invokevirtual   com/jetbrains/cidr/lang/types/ARCAttribute.getTokenName:()Ljava/lang/String;
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: ldc             "' are mutually exclusive"
        //   242: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   245: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   248: astore          7
        //   250: ldc             "err_objc_property_attr_mutually_exclusive"
        //   252: astore          8
        //   254: goto            258
        //   257: return         
        //   258: aload_1        
        //   259: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getProject:()Lcom/intellij/openapi/project/Project;
        //   264: invokestatic    com/jetbrains/cidr/lang/workspace/OCWorkspaceManager.getWorkspace:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/workspace/OCWorkspace;
        //   267: aload_2        
        //   268: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   273: invokeinterface com/jetbrains/cidr/lang/workspace/OCWorkspace.isInSDK:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   278: ifeq            286
        //   281: return         
        //   282: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: aload_0        
        //   287: aload_1        
        //   288: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues;.class
        //   290: aload           8
        //   292: aload           7
        //   294: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   297: astore          9
        //   299: aload_0        
        //   300: aload           9
        //   302: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction;
        //   305: dup            
        //   306: aload_2        
        //   307: aload           6
        //   309: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getIvarCompatibleARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   312: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/ARCAttribute;)V
        //   315: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   318: aload_3        
        //   319: aload           5
        //   321: aload_1        
        //   322: invokevirtual   com/jetbrains/cidr/lang/types/ARCAttribute.getPropertyCompatibleSemantics:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //   325: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics.getPropertyAttribute:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   328: astore          10
        //   330: aload_0        
        //   331: aload           9
        //   333: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   336: dup            
        //   337: aload           4
        //   339: aload           6
        //   341: aload           10
        //   343: aconst_null    
        //   344: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   347: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   350: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      15     15     19     Ljava/lang/IllegalArgumentException;
        //  22     31     34     38     Ljava/lang/IllegalArgumentException;
        //  27     42     45     49     Ljava/lang/IllegalArgumentException;
        //  38     57     60     64     Ljava/lang/IllegalArgumentException;
        //  79     96     99     103    Ljava/lang/IllegalArgumentException;
        //  88     113    116    120    Ljava/lang/IllegalArgumentException;
        //  103    127    130    134    Ljava/lang/IllegalArgumentException;
        //  258    282    282    286    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    private boolean a(final OCPropertySymbol ocPropertySymbol, final String s, final OCSymbol ocSymbol, final String s2, final PsiElement psiElement, final Class<? extends OCInspection> clazz) {
        Label_0021: {
            try {
                if (ocPropertySymbol == null) {
                    return true;
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                if (ocSymbol2 == null) {
                    return true;
                }
                break Label_0021;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                if (ocSymbol2 == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCType resolve = ocPropertySymbol.getType().resolve(psiElement.getContainingFile());
        final OCType resolve2 = ocSymbol.getType().resolve(psiElement.getContainingFile());
        final String name = resolve.getName(psiElement);
        final String name2 = resolve2.getName(psiElement);
        final Annotation checkAssignment = this.myCppChecker.checkAssignment(null, ocSymbol, s2, psiElement, resolve, resolve2, ocPropertySymbol, s, resolve2.transformType(OCArrayToPointerChanger.INSTANCE), false, clazz, "warn_property_types_are_incompatible", "The type of " + s + " (" + name + ") isn't assignable from the type of " + s2 + " (" + name2 + ")");
        Label_0198: {
            try {
                if (checkAssignment != null) {
                    return true;
                }
                final OCPropertySymbol ocPropertySymbol2 = ocPropertySymbol;
                final boolean b = ocPropertySymbol2.isReadonly();
                if (b) {
                    return true;
                }
                break Label_0198;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCPropertySymbol ocPropertySymbol2 = ocPropertySymbol;
                final boolean b = ocPropertySymbol2.isReadonly();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        this.registerQuickFix(this.myCppChecker.checkAssignment(null, ocPropertySymbol, s, psiElement, resolve2, resolve, ocSymbol, s2, resolve.transformType(OCArrayToPointerChanger.INSTANCE), false, clazz, "CIDRcovariant_properties", "The type of " + s2 + " (" + name2 + ") isn't assignable from the type of " + s + " (" + name + ") - probably you forgot to mark the " + s + " 'readonly'"), (IntentionAction)new OCChangePropertyAttributeIntentionAction(ocPropertySymbol, null, OCPropertySymbol.PropertyAttribute.READONLY, null));
        return false;
    }
    
    private boolean a(final OCDeclarator p0, final OCSymbol p1, final OCType p2, final PsiNameIdentifierOwner p3, final OCSymbol p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializers:()Ljava/util/List;
        //     6: astore          6
        //     8: aload           6
        //    10: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    16: astore          7
        //    18: aload_3        
        //    19: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    22: ifne            70
        //    25: aload           6
        //    27: invokeinterface java/util/List.size:()I
        //    32: iconst_1       
        //    33: if_icmple       70
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_0        
        //    44: aload           6
        //    46: iconst_1       
        //    47: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    52: checkcast       Lcom/intellij/psi/PsiElement;
        //    55: ldc             "err_excess_initializers"
        //    57: ldc             "Only one initializer is permitted"
        //    59: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    62: pop            
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload           7
        //    72: ifnonnull       81
        //    75: iconst_0       
        //    76: ireturn        
        //    77: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_2        
        //    82: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    85: ifeq            138
        //    88: aload_2        
        //    89: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    92: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isExtern:()Z
        //    95: ifeq            138
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_0        
        //   106: aload           7
        //   108: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleInitializers;.class
        //   110: ldc             "warn_extern_init"
        //   112: ldc             "Extern variable can't have an initializer"
        //   114: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   117: astore          8
        //   119: aload_0        
        //   120: aload           8
        //   122: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   125: dup            
        //   126: aload           7
        //   128: ldc             "Remove initializer"
        //   130: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   133: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   136: iconst_0       
        //   137: ireturn        
        //   138: aload_1        
        //   139: iconst_5       
        //   140: anewarray       Ljava/lang/Class;
        //   143: dup            
        //   144: iconst_0       
        //   145: ldc             Lcom/jetbrains/cidr/lang/psi/OCProperty;.class
        //   147: aastore        
        //   148: dup            
        //   149: iconst_1       
        //   150: ldc             Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;.class
        //   152: aastore        
        //   153: dup            
        //   154: iconst_2       
        //   155: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   157: aastore        
        //   158: dup            
        //   159: iconst_3       
        //   160: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterList;.class
        //   162: aastore        
        //   163: dup            
        //   164: iconst_4       
        //   165: ldc             Lcom/jetbrains/cidr/lang/psi/OCStructLike;.class
        //   167: aastore        
        //   168: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   171: astore          8
        //   173: aload           8
        //   175: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   178: ifeq            214
        //   181: aload_0        
        //   182: aload           7
        //   184: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //   186: ldc             "err_expected_semi_decl_list"
        //   188: ldc             "Property can't have an initializer"
        //   190: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   193: astore          9
        //   195: aload_0        
        //   196: aload           9
        //   198: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   201: dup            
        //   202: aload           7
        //   204: ldc             "Remove initializer"
        //   206: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   209: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   212: iconst_0       
        //   213: ireturn        
        //   214: aload           4
        //   216: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //   221: astore          9
        //   223: aload           7
        //   225: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   230: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   235: ifeq            279
        //   238: aload           9
        //   240: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   243: ifeq            279
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: aload           9
        //   255: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   260: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   263: ifeq            279
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: iconst_0       
        //   274: ireturn        
        //   275: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: aload           9
        //   281: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   284: ifeq            393
        //   287: aload           9
        //   289: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   294: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   297: ifeq            393
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: aload           9
        //   309: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   314: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   317: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   322: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   325: if_acmpeq       393
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: aload           9
        //   337: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   340: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   345: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   350: ifne            393
        //   353: goto            360
        //   356: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   359: athrow         
        //   360: aload_0        
        //   361: aload           7
        //   363: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //   365: ldc             "CIDR"
        //   367: ldc             "Structure field can't have an initializer"
        //   369: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   372: astore          10
        //   374: aload_0        
        //   375: aload           10
        //   377: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   380: dup            
        //   381: aload           7
        //   383: ldc             "Remove initializer"
        //   385: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   388: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   391: iconst_0       
        //   392: ireturn        
        //   393: aload           8
        //   395: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   398: ifeq            434
        //   401: aload_0        
        //   402: aload           7
        //   404: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //   406: ldc             "CIDR"
        //   408: ldc             "Instance variable can't have an initializer"
        //   410: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   413: astore          10
        //   415: aload_0        
        //   416: aload           10
        //   418: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   421: dup            
        //   422: aload           7
        //   424: ldc             "Remove initializer"
        //   426: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   429: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   432: iconst_0       
        //   433: ireturn        
        //   434: aload           7
        //   436: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //   439: ifeq            557
        //   442: aload           8
        //   444: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   447: ifeq            488
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: aload_2        
        //   458: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   461: ifeq            557
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_2        
        //   472: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   475: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   478: ifeq            557
        //   481: goto            488
        //   484: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload_0        
        //   489: aload           7
        //   491: ldc             "CIDR"
        //   493: new             Ljava/lang/StringBuilder;
        //   496: dup            
        //   497: invokespecial   java/lang/StringBuilder.<init>:()V
        //   500: ldc             "Initializer of the "
        //   502: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   505: aload           8
        //   507: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   510: ifeq            529
        //   513: goto            520
        //   516: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   519: athrow         
        //   520: ldc             "static "
        //   522: goto            531
        //   525: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: ldc             ""
        //   531: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: aload_2        
        //   535: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   540: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   543: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   546: ldc             " must be const"
        //   548: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   551: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   554: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Ljava/lang/String;Ljava/lang/String;)V
        //   557: aload           6
        //   559: invokeinterface java/util/List.size:()I
        //   564: iconst_1       
        //   565: if_icmpne       807
        //   568: aload           7
        //   570: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   573: ifne            807
        //   576: goto            583
        //   579: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   582: athrow         
        //   583: aload_1        
        //   584: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   589: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   594: ifne            628
        //   597: goto            604
        //   600: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   603: athrow         
        //   604: aload           7
        //   606: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParenthesesAndCasts:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   609: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   612: ifeq            628
        //   615: goto            622
        //   618: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: iconst_1       
        //   623: ireturn        
        //   624: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   627: athrow         
        //   628: aload           7
        //   630: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   635: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   638: astore          10
        //   640: aload           10
        //   642: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isUnresolvedLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   645: ifeq            665
        //   648: aload_3        
        //   649: aload           10
        //   651: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   654: dup            
        //   655: aload           7
        //   657: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   660: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.resolveLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   663: astore          10
        //   665: aload           7
        //   667: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   670: astore          11
        //   672: aload_3        
        //   673: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //   676: ifeq            736
        //   679: aload           11
        //   681: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   684: ifeq            736
        //   687: goto            694
        //   690: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   693: athrow         
        //   694: aload           10
        //   696: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //   699: ifeq            736
        //   702: goto            709
        //   705: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   708: athrow         
        //   709: new             Lcom/jetbrains/cidr/OCAnnotatingArgumentsChecker;
        //   712: dup            
        //   713: aload_0        
        //   714: getfield        com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   717: invokespecial   com/jetbrains/cidr/OCAnnotatingArgumentsChecker.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;)V
        //   720: aload_3        
        //   721: aload           11
        //   723: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   726: invokevirtual   com/jetbrains/cidr/OCAnnotatingArgumentsChecker.checkCharArrayInit:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;)V
        //   729: goto            762
        //   732: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   735: athrow         
        //   736: aload_3        
        //   737: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   740: ifeq            762
        //   743: aload_0        
        //   744: aload           4
        //   746: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   748: ldc             "err_array_init_not_init_list"
        //   750: ldc             "Compound array initializer is expected"
        //   752: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   755: pop            
        //   756: iconst_0       
        //   757: ireturn        
        //   758: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   761: athrow         
        //   762: aload           5
        //   764: ifnonnull       807
        //   767: aload           10
        //   769: getstatic       com/jetbrains/cidr/lang/types/visitors/OCArrayToPointerChanger.INSTANCE:Lcom/jetbrains/cidr/lang/types/visitors/OCArrayToPointerChanger;
        //   772: invokevirtual   com/jetbrains/cidr/lang/types/OCType.transformType:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   775: astore          12
        //   777: aload_0        
        //   778: getfield        com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   781: aload           7
        //   783: aload           4
        //   785: aload_3        
        //   786: aload           10
        //   788: aload_2        
        //   789: aload           12
        //   791: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkAssignment:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/intellij/lang/annotation/Annotation;
        //   794: ifnonnull       805
        //   797: iconst_1       
        //   798: goto            806
        //   801: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   804: athrow         
        //   805: iconst_0       
        //   806: ireturn        
        //   807: iconst_1       
        //   808: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  18     36     39     43     Ljava/lang/IllegalArgumentException;
        //  25     63     66     70     Ljava/lang/IllegalArgumentException;
        //  70     77     77     81     Ljava/lang/IllegalArgumentException;
        //  81     98     101    105    Ljava/lang/IllegalArgumentException;
        //  223    246    249    253    Ljava/lang/IllegalArgumentException;
        //  238    266    269    273    Ljava/lang/IllegalArgumentException;
        //  253    275    275    279    Ljava/lang/IllegalArgumentException;
        //  279    300    303    307    Ljava/lang/IllegalArgumentException;
        //  287    328    331    335    Ljava/lang/IllegalArgumentException;
        //  307    353    356    360    Ljava/lang/IllegalArgumentException;
        //  434    450    453    457    Ljava/lang/IllegalArgumentException;
        //  442    464    467    471    Ljava/lang/IllegalArgumentException;
        //  457    481    484    488    Ljava/lang/IllegalArgumentException;
        //  471    513    516    520    Ljava/lang/IllegalArgumentException;
        //  488    525    525    529    Ljava/lang/IllegalArgumentException;
        //  557    576    579    583    Ljava/lang/IllegalArgumentException;
        //  568    597    600    604    Ljava/lang/IllegalArgumentException;
        //  583    615    618    622    Ljava/lang/IllegalArgumentException;
        //  604    624    624    628    Ljava/lang/IllegalArgumentException;
        //  672    687    690    694    Ljava/lang/IllegalArgumentException;
        //  679    702    705    709    Ljava/lang/IllegalArgumentException;
        //  694    732    732    736    Ljava/lang/IllegalArgumentException;
        //  736    758    758    762    Ljava/lang/IllegalArgumentException;
        //  777    801    801    805    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0253:
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
    
    public boolean checkInstanceable(final OCType p0, @Nullable final PsiElement p1, final OCSymbol p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       10
        //     4: iconst_1       
        //     5: ireturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_1        
        //    11: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    14: ifeq            53
        //    17: aload_1        
        //    18: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    21: invokevirtual   com/jetbrains/cidr/lang/types/OCBlockPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    24: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    27: ifne            53
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_0        
        //    38: aload_2        
        //    39: ldc             "err_nonfunction_block_type"
        //    41: ldc             "Block pointer to non-function is invalid"
        //    43: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    46: pop            
        //    47: iconst_0       
        //    48: ireturn        
        //    49: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_3        
        //    54: ifnull          335
        //    57: aload_3        
        //    58: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    63: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    66: if_acmpeq       335
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_1        
        //    77: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    80: ifne            335
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_1        
        //    91: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isInstanceable:()Z
        //    94: ifne            335
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_1        
        //   105: instanceof      Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   108: ifne            132
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: aload_1        
        //   119: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   122: ifeq            170
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   138: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   141: if_acmpeq       335
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_3        
        //   152: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   157: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   160: if_acmpeq       335
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_3        
        //   171: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isCallable:()Z
        //   176: ifeq            223
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: new             Ljava/lang/StringBuilder;
        //   189: dup            
        //   190: invokespecial   java/lang/StringBuilder.<init>:()V
        //   193: ldc             "Can't return type '"
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: aload_1        
        //   199: aload_2        
        //   200: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: ldc             "'"
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: astore          5
        //   216: ldc             "err_object_cannot_be_passed_returned_by_value"
        //   218: astore          6
        //   220: goto            257
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: ldc             "Can't instantiate the variables of type '"
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload_1        
        //   236: aload_2        
        //   237: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   243: ldc             "'"
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   251: astore          5
        //   253: ldc             "err_statically_allocated_object"
        //   255: astore          6
        //   257: aload_1        
        //   258: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   261: ifne            278
        //   264: aload_1        
        //   265: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   268: ifeq            300
        //   271: goto            278
        //   274: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: new             Ljava/lang/StringBuilder;
        //   281: dup            
        //   282: invokespecial   java/lang/StringBuilder.<init>:()V
        //   285: aload           5
        //   287: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   290: ldc             " (probably you forgot '*')"
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   298: astore          5
        //   300: aload_0        
        //   301: aload_2        
        //   302: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PointerTypeRequired;.class
        //   304: aload           6
        //   306: aload           5
        //   308: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   311: astore          7
        //   313: aload_0        
        //   314: aload           7
        //   316: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   319: dup            
        //   320: aload_3        
        //   321: aload_1        
        //   322: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   325: iload           4
        //   327: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //   330: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   333: iconst_0       
        //   334: ireturn        
        //   335: iconst_1       
        //   336: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  10     30     33     37     Ljava/lang/IllegalArgumentException;
        //  17     49     49     53     Ljava/lang/IllegalArgumentException;
        //  53     69     72     76     Ljava/lang/IllegalArgumentException;
        //  57     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     97     100    104    Ljava/lang/IllegalArgumentException;
        //  90     111    114    118    Ljava/lang/IllegalArgumentException;
        //  104    125    128    132    Ljava/lang/IllegalArgumentException;
        //  118    144    147    151    Ljava/lang/IllegalArgumentException;
        //  132    163    166    170    Ljava/lang/IllegalArgumentException;
        //  151    179    182    186    Ljava/lang/IllegalArgumentException;
        //  257    271    274    278    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    
    private void a(final OCDeclarator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_2       
        //     2: aload_1        
        //     3: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getArrayLengths:()Ljava/util/List;
        //     8: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    13: astore_3       
        //    14: aload_3        
        //    15: invokeinterface java/util/Iterator.hasNext:()Z
        //    20: ifeq            363
        //    23: aload_3        
        //    24: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    29: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    32: astore          4
        //    34: aload           4
        //    36: ifnonnull       163
        //    39: iload_2        
        //    40: ifne            69
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_0        
        //    51: aload_1        
        //    52: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //    54: ldc             "err_illegal_decl_array_incomplete_type"
        //    56: ldc             "Empty array length is allowed only at the first dimension"
        //    58: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    61: pop            
        //    62: goto            363
        //    65: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_1        
        //    70: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //    75: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    78: ifne            158
        //    81: aload_1        
        //    82: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //    84: iconst_1       
        //    85: iconst_1       
        //    86: anewarray       Ljava/lang/Class;
        //    89: dup            
        //    90: iconst_0       
        //    91: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterList;.class
        //    93: aastore        
        //    94: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    97: ifnull          158
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_1        
        //   108: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   113: ifnonnull       158
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_1        
        //   124: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializerList:()Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   129: ifnonnull       158
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_0        
        //   140: aload_1        
        //   141: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   143: ldc             "err_typecheck_incomplete_array_needs_initializer"
        //   145: ldc             "Local incomplete array must be initialized"
        //   147: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   150: pop            
        //   151: goto            363
        //   154: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: iconst_0       
        //   159: istore_2       
        //   160: goto            14
        //   163: aload_1        
        //   164: iconst_1       
        //   165: anewarray       Ljava/lang/Class;
        //   168: dup            
        //   169: iconst_0       
        //   170: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   172: aastore        
        //   173: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   176: ifnonnull       219
        //   179: aload_1        
        //   180: iconst_1       
        //   181: anewarray       Ljava/lang/Class;
        //   184: dup            
        //   185: iconst_0       
        //   186: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterList;.class
        //   188: aastore        
        //   189: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   192: ifnonnull       219
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: aload_0        
        //   203: aload           4
        //   205: ldc             "err_vla_decl_in_file_scope"
        //   207: ldc             "Array length expression must be const"
        //   209: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Ljava/lang/String;Ljava/lang/String;)V
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload           4
        //   221: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   226: astore          5
        //   228: aload           5
        //   230: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   233: ifne            311
        //   236: aload           5
        //   238: aload_1        
        //   239: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   242: ifne            311
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: new             Ljava/lang/StringBuilder;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuilder.<init>:()V
        //   259: ldc             "Array length expression must have an integer type instead of '"
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: aload           5
        //   266: aload_1        
        //   267: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: ldc             "'"
        //   275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   278: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   281: astore          6
        //   283: aload_0        
        //   284: aload           4
        //   286: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IntegerTypeRequired;.class
        //   288: ldc             "err_array_size_non_int"
        //   290: aload           6
        //   292: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   295: astore          7
        //   297: aload_0        
        //   298: aload           7
        //   300: aload           4
        //   302: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   305: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.getAction:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   308: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   311: aload           4
        //   313: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Number;
        //   316: astore          6
        //   318: aload           6
        //   320: ifnull          358
        //   323: aload           6
        //   325: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   328: ifge            358
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload_0        
        //   339: aload           4
        //   341: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   343: ldc             "err_decl_negative_array_size"
        //   345: ldc             "Array length can't be negative"
        //   347: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   350: pop            
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: iconst_0       
        //   359: istore_2       
        //   360: goto            14
        //   363: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  34     43     46     50     Ljava/lang/IllegalArgumentException;
        //  39     65     65     69     Ljava/lang/IllegalArgumentException;
        //  69     100    103    107    Ljava/lang/IllegalArgumentException;
        //  81     116    119    123    Ljava/lang/IllegalArgumentException;
        //  107    132    135    139    Ljava/lang/IllegalArgumentException;
        //  123    154    154    158    Ljava/lang/IllegalArgumentException;
        //  163    195    198    202    Ljava/lang/IllegalArgumentException;
        //  179    212    215    219    Ljava/lang/IllegalArgumentException;
        //  228    245    248    252    Ljava/lang/IllegalArgumentException;
        //  318    331    334    338    Ljava/lang/IllegalArgumentException;
        //  323    351    354    358    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0107:
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
    
    private void a(final OCExpression ocExpression, final String s, final String s2) {
        ocExpression.accept((PsiElementVisitor)new OCConstantExpressionVisitor() {
            @Override
            protected void nonConstExpression(final OCExpression ocExpression) {
                final Annotation addErrorAnnotation = OCDeclaratorChecker.this.addErrorAnnotation((PsiElement)ocExpression, OCInspections.ConstExpressionRequired.class, s, s2);
                if (ocExpression instanceof OCReferenceExpression) {
                    final OCSymbol resolveToSymbol = ((OCReferenceExpression)ocExpression).resolveToSymbol();
                    if (resolveToSymbol instanceof OCDeclaratorSymbol) {
                        OCDeclaratorChecker.this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCAddTypeModifierIntentionAction((OCSymbolWithQualifiedName)resolveToSymbol, OCTokenTypes.CONST_KEYWORD));
                    }
                }
            }
        });
    }
    
    public void checkReferenceElement(final OCReferenceElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnonnull       16
        //    11: return         
        //    12: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: aload_1        
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    22: astore_3       
        //    23: aload_2        
        //    24: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //    29: ifne            46
        //    32: aload_2        
        //    33: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    36: ifeq            64
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_3        
        //    47: aload_2        
        //    48: aload_1        
        //    49: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    54: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_2        
        //    65: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    70: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    73: if_acmpne       351
        //    76: new             Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker$3;
        //    79: dup            
        //    80: aload_0        
        //    81: aload_1        
        //    82: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker$3.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;)V
        //    85: astore          4
        //    87: aload           4
        //    89: aload_2        
        //    90: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.process:(Ljava/lang/Object;)Z
        //    93: ifeq            114
        //    96: aload_2        
        //    97: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   100: aload           4
        //   102: aload_3        
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.processSameSymbols:(Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiFile;)Z
        //   106: pop            
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload           4
        //   116: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
        //   119: ifne            351
        //   122: aconst_null    
        //   123: astore          5
        //   125: aload_3        
        //   126: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   131: ifeq            260
        //   134: aload_1        
        //   135: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   143: ifeq            260
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_1        
        //   154: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   159: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   162: astore          6
        //   164: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   167: dup            
        //   168: aload_1        
        //   169: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   174: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   177: astore          7
        //   179: aload           6
        //   181: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   186: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   189: ifeq            257
        //   192: aload           6
        //   194: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   199: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   202: aload           7
        //   204: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isDependentCode:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   207: ifne            257
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: aload_0        
        //   218: aload_1        
        //   219: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$CannotResolve;.class
        //   221: ldc             "CIDR"
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: ldc             "Undeclared function '"
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload_2        
        //   236: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: ldc             "'"
        //   246: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   249: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   252: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   255: astore          5
        //   257: goto            300
        //   260: aload_0        
        //   261: aload_1        
        //   262: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$FunctionImplicitDeclarationInspection;.class
        //   264: ldc             "ext_implicit_function_decl"
        //   266: new             Ljava/lang/StringBuilder;
        //   269: dup            
        //   270: invokespecial   java/lang/StringBuilder.<init>:()V
        //   273: ldc             "Implicit declaration of function '"
        //   275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   278: aload_2        
        //   279: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   284: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: ldc             "'"
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   295: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   298: astore          5
        //   300: aload           5
        //   302: ifnull          351
        //   305: aload_0        
        //   306: aload           5
        //   308: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateFunctionPredeclarationIntentionAction;
        //   311: dup            
        //   312: aload_1        
        //   313: aload_2        
        //   314: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   317: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateFunctionPredeclarationIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //   320: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   323: aload_0        
        //   324: aload           5
        //   326: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction;
        //   329: dup            
        //   330: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   333: aload_1        
        //   334: aconst_null    
        //   335: aload_2        
        //   336: ldc             " above"
        //   338: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;)V
        //   341: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   350: athrow         
        //   351: aload_3        
        //   352: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   357: ifne            509
        //   360: aload_2        
        //   361: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   364: ifeq            509
        //   367: goto            374
        //   370: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: aload_1        
        //   375: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   380: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   383: ifne            509
        //   386: goto            393
        //   389: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   392: athrow         
        //   393: aload_2        
        //   394: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   399: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   402: astore          4
        //   404: aload_0        
        //   405: aload_1        
        //   406: ldc             "err_use_of_tag_name_without_tag"
        //   408: new             Ljava/lang/StringBuilder;
        //   411: dup            
        //   412: invokespecial   java/lang/StringBuilder.<init>:()V
        //   415: ldc             "Missing '"
        //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   420: aload           4
        //   422: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: ldc             "' keyword"
        //   427: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   430: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   433: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   436: astore          5
        //   438: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   441: dup            
        //   442: aload_3        
        //   443: aload_1        
        //   444: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getTextOffset:()I
        //   449: iconst_0       
        //   450: new             Ljava/lang/StringBuilder;
        //   453: dup            
        //   454: invokespecial   java/lang/StringBuilder.<init>:()V
        //   457: aload           4
        //   459: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   462: ldc             " "
        //   464: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   467: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   470: new             Ljava/lang/StringBuilder;
        //   473: dup            
        //   474: invokespecial   java/lang/StringBuilder.<init>:()V
        //   477: ldc             "Insert '"
        //   479: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   482: aload           4
        //   484: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   487: ldc             "'"
        //   489: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   495: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;)V
        //   498: astore          6
        //   500: aload_0        
        //   501: aload           5
        //   503: aload           6
        //   505: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   508: return         
        //   509: aload_2        
        //   510: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   513: ifeq            533
        //   516: aload_2        
        //   517: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   520: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   523: ifne            558
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload_0        
        //   534: getfield        com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //   537: aload_2        
        //   538: aload_1        
        //   539: aconst_null    
        //   540: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkFieldVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   543: ifne            558
        //   546: goto            553
        //   549: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   552: athrow         
        //   553: return         
        //   554: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: aload_0        
        //   559: aload_1        
        //   560: aload_2        
        //   561: invokespecial   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   564: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      12     12     16     Ljava/lang/IllegalArgumentException;
        //  23     39     42     46     Ljava/lang/IllegalArgumentException;
        //  32     57     60     64     Ljava/lang/IllegalArgumentException;
        //  87     107    110    114    Ljava/lang/IllegalArgumentException;
        //  125    146    149    153    Ljava/lang/IllegalArgumentException;
        //  179    210    213    217    Ljava/lang/IllegalArgumentException;
        //  300    344    347    351    Ljava/lang/IllegalArgumentException;
        //  351    367    370    374    Ljava/lang/IllegalArgumentException;
        //  360    386    389    393    Ljava/lang/IllegalArgumentException;
        //  509    526    529    533    Ljava/lang/IllegalArgumentException;
        //  516    546    549    553    Ljava/lang/IllegalArgumentException;
        //  533    554    554    558    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0533:
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
    
    private void a(final OCReferenceElement p0, final OCSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //     4: ifne            21
        //     7: aload_2        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    11: ifeq            179
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: iconst_2       
        //    23: anewarray       Ljava/lang/Class;
        //    26: dup            
        //    27: iconst_0       
        //    28: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //    30: aastore        
        //    31: dup            
        //    32: iconst_1       
        //    33: ldc             Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;.class
        //    35: aastore        
        //    36: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    39: astore_3       
        //    40: aload_3        
        //    41: ifnonnull       85
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //    48: ldc             "CIDR"
        //    50: new             Ljava/lang/StringBuilder;
        //    53: dup            
        //    54: invokespecial   java/lang/StringBuilder.<init>:()V
        //    57: aload_2        
        //    58: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    66: ldc             " is accessed outside of a method"
        //    68: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    71: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    74: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    77: pop            
        //    78: goto            176
        //    81: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_3        
        //    86: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    89: ifeq            176
        //    92: aload_3        
        //    93: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    96: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.isInstanceMethod:()Z
        //   101: ifne            176
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_0        
        //   112: aload_1        
        //   113: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$StaticnessMismatch;.class
        //   115: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   118: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getIvarUseInClassMethod:()Ljava/lang/String;
        //   121: new             Ljava/lang/StringBuilder;
        //   124: dup            
        //   125: invokespecial   java/lang/StringBuilder.<init>:()V
        //   128: aload_2        
        //   129: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: ldc             " is accessed from the class method"
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   145: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   148: astore          4
        //   150: aload_0        
        //   151: aload           4
        //   153: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction;
        //   156: dup            
        //   157: aload_3        
        //   158: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   161: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   166: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   169: iconst_0       
        //   170: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeMethodStaticnessIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Z)V
        //   173: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   176: goto            756
        //   179: aload_1        
        //   180: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   185: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //   188: ifeq            196
        //   191: return         
        //   192: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: iconst_0       
        //   197: istore_3       
        //   198: aconst_null    
        //   199: astore          4
        //   201: aload_1        
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.isCppThis:()Z
        //   207: ifeq            215
        //   210: iconst_1       
        //   211: istore_3       
        //   212: goto            254
        //   215: aload_2        
        //   216: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   219: ifeq            253
        //   222: aload_2        
        //   223: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   226: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   229: astore          5
        //   231: aload           5
        //   233: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   236: ifeq            249
        //   239: aload           5
        //   241: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   244: astore          4
        //   246: goto            250
        //   249: return         
        //   250: goto            254
        //   253: return         
        //   254: aload_1        
        //   255: iconst_2       
        //   256: anewarray       Ljava/lang/Class;
        //   259: dup            
        //   260: iconst_0       
        //   261: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   263: aastore        
        //   264: dup            
        //   265: iconst_1       
        //   266: ldc             Lcom/jetbrains/cidr/lang/psi/OCStruct;.class
        //   268: aastore        
        //   269: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   272: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   275: astore          5
        //   277: aload           5
        //   279: ifnull          296
        //   282: aload           5
        //   284: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   289: goto            297
        //   292: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aconst_null    
        //   297: astore          6
        //   299: aload           6
        //   301: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   304: ifeq            319
        //   307: aload           6
        //   309: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   312: goto            320
        //   315: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aconst_null    
        //   320: astore          7
        //   322: aload           7
        //   324: ifnull          386
        //   327: aload           7
        //   329: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   332: astore          8
        //   334: iload_3        
        //   335: ifne            373
        //   338: aload           8
        //   340: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   343: ifeq            381
        //   346: goto            353
        //   349: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   352: athrow         
        //   353: aload           4
        //   355: aload           8
        //   357: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   360: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isAncestor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
        //   363: ifeq            381
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: iconst_1       
        //   374: goto            382
        //   377: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   380: athrow         
        //   381: iconst_0       
        //   382: istore_3       
        //   383: goto            459
        //   386: aload_1        
        //   387: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   392: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsInClassInitialization:(Lcom/intellij/psi/PsiFile;)Z
        //   395: ifeq            457
        //   398: aload           6
        //   400: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   403: ifeq            457
        //   406: goto            413
        //   409: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   412: athrow         
        //   413: iload_3        
        //   414: ifne            444
        //   417: goto            424
        //   420: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: aload           4
        //   426: aload           6
        //   428: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   431: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isAncestor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
        //   434: ifeq            452
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: iconst_1       
        //   445: goto            453
        //   448: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: iconst_0       
        //   453: istore_3       
        //   454: goto            459
        //   457: iconst_0       
        //   458: istore_3       
        //   459: aload_1        
        //   460: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   465: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   470: astore          8
        //   472: aload           8
        //   474: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   477: ifeq            508
        //   480: aload           8
        //   482: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   485: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.isGetAddress:()Z
        //   490: ifeq            508
        //   493: goto            500
        //   496: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   499: athrow         
        //   500: iconst_1       
        //   501: goto            509
        //   504: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   507: athrow         
        //   508: iconst_0       
        //   509: istore          9
        //   511: iload_3        
        //   512: ifeq            542
        //   515: aload           7
        //   517: ifnull          756
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   526: athrow         
        //   527: aload           7
        //   529: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.resolveIsStatic:()Z
        //   532: ifeq            756
        //   535: goto            542
        //   538: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   541: athrow         
        //   542: aload_1        
        //   543: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.isCppThis:()Z
        //   548: ifne            572
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: aload_2        
        //   559: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   562: ifeq            756
        //   565: goto            572
        //   568: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: new             Ljava/lang/StringBuilder;
        //   575: dup            
        //   576: invokespecial   java/lang/StringBuilder.<init>:()V
        //   579: aload_2        
        //   580: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   585: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   588: iload_3        
        //   589: ifeq            608
        //   592: goto            599
        //   595: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   598: athrow         
        //   599: ldc             " is accessed from the static function"
        //   601: goto            610
        //   604: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   607: athrow         
        //   608: ldc             " must be static"
        //   610: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   613: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   616: astore          10
        //   618: iload           9
        //   620: ifne            756
        //   623: aload           8
        //   625: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //   628: ifne            756
        //   631: goto            638
        //   634: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   637: athrow         
        //   638: aload_0        
        //   639: aload_1        
        //   640: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$StaticnessMismatch;.class
        //   642: ldc             "CIDR"
        //   644: aload           10
        //   646: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   649: astore          11
        //   651: iload_3        
        //   652: ifeq            683
        //   655: aload_0        
        //   656: aload           11
        //   658: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction;
        //   661: dup            
        //   662: aload           7
        //   664: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getDeclarationInParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   667: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STATIC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   670: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;)V
        //   673: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   676: goto            683
        //   679: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   682: athrow         
        //   683: aload_2        
        //   684: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   687: ifeq            721
        //   690: aload_0        
        //   691: aload           11
        //   693: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction;
        //   696: dup            
        //   697: aload_2        
        //   698: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   701: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getDeclarationInParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   704: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STATIC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   707: iconst_0       
        //   708: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;Z)V
        //   711: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   714: goto            756
        //   717: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   720: athrow         
        //   721: aload_2        
        //   722: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   725: ifeq            756
        //   728: aload_0        
        //   729: aload           11
        //   731: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction;
        //   734: dup            
        //   735: aload_2        
        //   736: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   739: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STATIC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   742: iconst_0       
        //   743: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/parser/OCElementType;Z)V
        //   746: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   749: goto            756
        //   752: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   755: athrow         
        //   756: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  40     81     81     85     Ljava/lang/IllegalArgumentException;
        //  85     104    107    111    Ljava/lang/IllegalArgumentException;
        //  179    192    192    196    Ljava/lang/IllegalArgumentException;
        //  277    292    292    296    Ljava/lang/IllegalArgumentException;
        //  299    315    315    319    Ljava/lang/IllegalArgumentException;
        //  334    346    349    353    Ljava/lang/IllegalArgumentException;
        //  338    366    369    373    Ljava/lang/IllegalArgumentException;
        //  353    377    377    381    Ljava/lang/IllegalArgumentException;
        //  386    406    409    413    Ljava/lang/IllegalArgumentException;
        //  398    417    420    424    Ljava/lang/IllegalArgumentException;
        //  413    437    440    444    Ljava/lang/IllegalArgumentException;
        //  424    448    448    452    Ljava/lang/IllegalArgumentException;
        //  472    493    496    500    Ljava/lang/IllegalArgumentException;
        //  480    504    504    508    Ljava/lang/IllegalArgumentException;
        //  511    520    523    527    Ljava/lang/IllegalArgumentException;
        //  515    535    538    542    Ljava/lang/IllegalArgumentException;
        //  527    551    554    558    Ljava/lang/IllegalArgumentException;
        //  542    565    568    572    Ljava/lang/IllegalArgumentException;
        //  558    592    595    599    Ljava/lang/IllegalArgumentException;
        //  572    604    604    608    Ljava/lang/IllegalArgumentException;
        //  618    631    634    638    Ljava/lang/IllegalArgumentException;
        //  651    676    679    683    Ljava/lang/IllegalArgumentException;
        //  683    717    717    721    Ljava/lang/IllegalArgumentException;
        //  721    749    752    756    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0353:
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
    
    private static boolean a(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //     4: ifeq            64
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //    14: ifne            64
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    28: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //    31: ifne            64
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_0        
        //    42: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.resolveIsStatic:()Z
        //    48: ifne            64
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_1       
        //    59: ireturn        
        //    60: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_0        
        //    65: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    68: ifeq            132
        //    71: aload_0        
        //    72: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    75: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.resolveIsStatic:()Z
        //    78: ifne            132
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    94: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    97: if_acmpeq       126
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_0        
        //   108: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   113: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isGlobalVariable:()Z
        //   116: ifeq            132
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_1       
        //   127: ireturn        
        //   128: invokestatic    com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: iconst_0       
        //   133: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      34     37     41     Ljava/lang/IllegalArgumentException;
        //  24     51     54     58     Ljava/lang/IllegalArgumentException;
        //  41     60     60     64     Ljava/lang/IllegalArgumentException;
        //  64     81     84     88     Ljava/lang/IllegalArgumentException;
        //  71     100    103    107    Ljava/lang/IllegalArgumentException;
        //  88     119    122    126    Ljava/lang/IllegalArgumentException;
        //  107    128    128    132    Ljava/lang/IllegalArgumentException;
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
