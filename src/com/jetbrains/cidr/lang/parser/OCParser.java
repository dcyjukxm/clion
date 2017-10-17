// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.editor.impl.LineSet;
import com.intellij.openapi.util.Getter;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.Iterator;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.preprocessor.OCParsingNameScope;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.preprocessor.OCContextChangeSet;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.intellij.lang.PsiBuilder;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.util.NotNullFunction;
import java.util.ArrayList;
import com.intellij.util.ThreeState;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.ASTNode;
import com.intellij.util.TripleFunction;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import java.util.List;
import com.intellij.reference.SoftReference;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.NotNullLazyKey;
import com.intellij.lang.PsiParser;

public class OCParser implements PsiParser
{
    private static final NotNullLazyKey<CachedValue<OCImmutableInclusionContext>, OCFile> FRAGMENT_CONTEXTS;
    private static final NotNullLazyKey<CachedValue<OCImmutableInclusionContext>, OCFile> FORCED_OBJCPP_FRAGMENT_CONTEXTS;
    private static final Key<SoftReference<OCPartialContext>> PARTIAL_CONTEXT;
    private static final Key<SoftReference<FileTextInfo>> FILE_TEXT;
    private static final OCParser instance;
    private final List<Processor<PsiElement>> myListeners;
    private boolean isLazyParsingEnabled;
    private static Boolean ourForceContextChangeCacheUsage;
    private static final TripleFunction<ASTNode, LighterASTNode, FlyweightCapableTreeStructure<LighterASTNode>, ThreeState> GLOBAL_REPARSE;
    
    private OCParser() {
        this.myListeners = new ArrayList<Processor<PsiElement>>();
        this.isLazyParsingEnabled = true;
    }
    
    public static OCParser getInstance() {
        return OCParser.instance;
    }
    
    public void setLazyParsingEnabled(final boolean isLazyParsingEnabled) {
        this.isLazyParsingEnabled = isLazyParsingEnabled;
    }
    
    private static NotNullFunction<OCFile, CachedValue<OCImmutableInclusionContext>> a(final boolean b) {
        return (NotNullFunction<OCFile, CachedValue<OCImmutableInclusionContext>>)(ocFile -> {
            final Project project = ocFile.getProject();
            return CachedValuesManager.getManager(project).createCachedValue(() -> {
                OCInclusionContext ocInclusionContext = null;
                Label_0027: {
                    try {
                        if (b) {
                            ocInclusionContext = OCInclusionContextUtil.calculateHeaderContext((PsiFile)ocFile, OCLanguageKind.OBJ_CPP);
                            break Label_0027;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    ocInclusionContext = OCInclusionContextUtil.headerContext((PsiFile)ocFile).derive();
                }
                final OCInclusionContext ocInclusionContext2 = ocInclusionContext;
                OCFile ocFile3;
                for (OCFile ocFile2 = ocFile; ocFile2 != null; ocFile2 = ocFile3) {
                    ocInclusionContext2.preprocessInclude((PsiFile)ocFile2, true);
                    final PsiElement context = ocFile2.getContext();
                    try {
                        if (context != null) {
                            ocFile3 = (OCFile)context.getContainingFile();
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    ocFile3 = null;
                }
                return CachedValueProvider.Result.create((Object)ocInclusionContext2, new Object[] { FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker() });
            }, false);
        });
    }
    
    public static OCImmutableInclusionContext getCachedInclusionContext(final OCFile ocFile, final boolean b) {
        try {
            if (b) {
                final NotNullLazyKey<CachedValue<OCImmutableInclusionContext>, OCFile> notNullLazyKey = OCParser.FORCED_OBJCPP_FRAGMENT_CONTEXTS;
                return (OCImmutableInclusionContext)((CachedValue)notNullLazyKey.getValue((UserDataHolder)ocFile)).getValue();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final NotNullLazyKey<CachedValue<OCImmutableInclusionContext>, OCFile> notNullLazyKey = OCParser.FRAGMENT_CONTEXTS;
        return (OCImmutableInclusionContext)((CachedValue)notNullLazyKey.getValue((UserDataHolder)ocFile)).getValue();
    }
    
    public void addListener(final Processor<PsiElement> processor) {
        this.myListeners.add(processor);
    }
    
    public void clearListeners() {
        this.myListeners.clear();
    }
    
    @NotNull
    public ASTNode parse(@NotNull final IElementType elementType, @NotNull final PsiBuilder psiBuilder) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/parser/OCParser", "parse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/parser/OCParser", "parse"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCParsing.BlockParsingMode blockParsingMode = null;
        Label_0133: {
            try {
                psiBuilder.setDebugMode(false);
                psiBuilder.putUserDataUnprotected((Key)PsiBuilderImpl.CUSTOM_COMPARATOR, (Object)OCParser.GLOBAL_REPARSE);
                if (this.isLazyParsingEnabled) {
                    blockParsingMode = OCParsing.BlockParsingMode.LAZY;
                    break Label_0133;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            blockParsingMode = OCParsing.BlockParsingMode.EAGER;
        }
        final ASTNode parse = new OCParsing(psiBuilder, elementType, blockParsingMode).parse();
        if (parse == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParser", "parse"));
        }
        return parse;
    }
    
    public ASTNode parseDumbFile(final IElementType elementType, final PsiBuilder psiBuilder) {
        psiBuilder.setDebugMode(false);
        return new OCParsing(psiBuilder, elementType).parseDumpFile();
    }
    
    public ASTNode parse(final OCRootElementType p0, final ASTNode p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //     6: astore_3       
        //     7: aload_3        
        //     8: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    13: astore          4
        //    15: aload           4
        //    17: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    22: astore          5
        //    24: aload           4
        //    26: invokeinterface com/intellij/psi/PsiFile.getOriginalFile:()Lcom/intellij/psi/PsiFile;
        //    31: astore          6
        //    33: aload           4
        //    35: invokeinterface com/intellij/psi/PsiFile.getContext:()Lcom/intellij/psi/PsiElement;
        //    40: astore          7
        //    42: aconst_null    
        //    43: astore          8
        //    45: iconst_0       
        //    46: istore          9
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/parser/OCParser.myListeners:Ljava/util/List;
        //    52: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    57: astore          10
        //    59: aload           10
        //    61: invokeinterface java/util/Iterator.hasNext:()Z
        //    66: ifeq            93
        //    69: aload           10
        //    71: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    76: checkcast       Lcom/intellij/util/Processor;
        //    79: astore          11
        //    81: aload           11
        //    83: aload_3        
        //    84: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    89: pop            
        //    90: goto            59
        //    93: iconst_m1      
        //    94: istore          10
        //    96: aload_3        
        //    97: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCLazyBlockStatementImpl;
        //   100: ifeq            142
        //   103: aload           7
        //   105: ifnonnull       123
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_3        
        //   116: goto            125
        //   119: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload           7
        //   125: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   130: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   133: istore          10
        //   135: aload_3        
        //   136: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   141: astore_3       
        //   142: aload_3        
        //   143: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   148: istore          11
        //   150: aload           7
        //   152: ifnull          207
        //   155: aload_3        
        //   156: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   159: ifne            207
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload           4
        //   171: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   174: ifne            207
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: aload           7
        //   186: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   191: astore          4
        //   193: aload           7
        //   195: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   200: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   205: istore          11
        //   207: iconst_1       
        //   208: istore          12
        //   210: aload           4
        //   212: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.partialContextHolder:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/psi/PsiFile;
        //   215: dup            
        //   216: astore          13
        //   218: monitorenter   
        //   219: aconst_null    
        //   220: astore          14
        //   222: aload           5
        //   224: invokeinterface com/intellij/openapi/project/Project.isDefault:()Z
        //   229: ifne            247
        //   232: aload           4
        //   234: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
        //   237: ifeq            393
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aload           5
        //   249: invokeinterface com/intellij/openapi/project/Project.isDefault:()Z
        //   254: ifne            378
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: aload           7
        //   266: ifnull          378
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: aload           7
        //   278: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   283: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   286: ifeq            378
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload           4
        //   298: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
        //   301: invokeinterface com/jetbrains/cidr/lang/psi/OCCodeFragment.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   306: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   309: if_acmpne       355
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aload           7
        //   321: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   326: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   329: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   334: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   337: if_acmpeq       355
        //   340: goto            347
        //   343: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   346: athrow         
        //   347: iconst_1       
        //   348: goto            356
        //   351: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: iconst_0       
        //   356: istore          15
        //   358: aload           7
        //   360: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   365: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   368: iload           15
        //   370: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.getCachedInclusionContext:(Lcom/jetbrains/cidr/lang/psi/OCFile;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   373: astore          8
        //   375: goto            529
        //   378: aload           4
        //   380: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.calculateLanguageKindFast:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   383: aload           4
        //   385: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.empty:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   388: astore          8
        //   390: goto            529
        //   393: aload           5
        //   395: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.areSymbolsLoaded:(Lcom/intellij/openapi/project/Project;)Z
        //   398: ifeq            526
        //   401: aload_2        
        //   402: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Lcom/intellij/lang/ASTNode;)Z
        //   405: ifeq            526
        //   408: goto            415
        //   411: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   414: athrow         
        //   415: aload_3        
        //   416: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   419: ifne            444
        //   422: goto            429
        //   425: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: aload           4
        //   431: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   434: ifne            489
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: aload           4
        //   446: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.headerContext:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   449: astore          8
        //   451: aload           4
        //   453: iconst_0       
        //   454: iconst_m1      
        //   455: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Lcom/intellij/psi/PsiFile;II)Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //   458: astore          14
        //   460: aload           8
        //   462: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getInclusionLevel:()I
        //   467: aload           5
        //   469: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getMaxInclusionLevel:(Lcom/intellij/openapi/project/Project;)I
        //   472: if_icmplt       483
        //   475: iconst_1       
        //   476: goto            484
        //   479: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   482: athrow         
        //   483: iconst_0       
        //   484: istore          9
        //   486: goto            529
        //   489: aload           4
        //   491: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   494: iload           11
        //   496: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.evaluatePartialContext:(Lcom/jetbrains/cidr/lang/psi/OCFile;I)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   499: astore          8
        //   501: iload           10
        //   503: iflt            529
        //   506: aload           4
        //   508: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/parser/OCParser$FileTextInfo;
        //   511: getfield        com/jetbrains/cidr/lang/parser/OCParser$FileTextInfo.lineSet:Lcom/intellij/openapi/editor/impl/LineSet;
        //   514: iload           10
        //   516: invokevirtual   com/intellij/openapi/editor/impl/LineSet.findLineIndex:(I)I
        //   519: iconst_1       
        //   520: iadd           
        //   521: istore          12
        //   523: goto            529
        //   526: iconst_1       
        //   527: istore          9
        //   529: iload           9
        //   531: ifeq            604
        //   534: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   537: aload_3        
        //   538: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   541: aload_3        
        //   542: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   545: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(ZLjava/lang/Object;)Z
        //   548: pop            
        //   549: invokestatic    com/intellij/lang/PsiBuilderFactory.getInstance:()Lcom/intellij/lang/PsiBuilderFactory;
        //   552: astore          15
        //   554: aload           15
        //   556: aload           5
        //   558: aload_2        
        //   559: invokestatic    com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives.createDefault:()Lcom/jetbrains/cidr/lang/lexer/OCLexerWithDirectives;
        //   562: aload_1        
        //   563: invokeinterface com/jetbrains/cidr/lang/parser/OCRootElementType.getLanguage:()Lcom/intellij/lang/Language;
        //   568: aload_2        
        //   569: invokeinterface com/intellij/lang/ASTNode.getChars:()Ljava/lang/CharSequence;
        //   574: invokevirtual   com/intellij/lang/PsiBuilderFactory.createBuilder:(Lcom/intellij/openapi/project/Project;Lcom/intellij/lang/ASTNode;Lcom/intellij/lexer/Lexer;Lcom/intellij/lang/Language;Ljava/lang/CharSequence;)Lcom/intellij/lang/PsiBuilder;
        //   577: astore          16
        //   579: aload_0        
        //   580: aload_2        
        //   581: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   586: aload           16
        //   588: invokevirtual   com/jetbrains/cidr/lang/parser/OCParser.parseDumbFile:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/PsiBuilder;)Lcom/intellij/lang/ASTNode;
        //   591: astore          17
        //   593: aload           17
        //   595: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //   600: aload           13
        //   602: monitorexit    
        //   603: areturn        
        //   604: aload           8
        //   606: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.derive:()Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   611: astore          15
        //   613: aload_3        
        //   614: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;
        //   617: ifeq            662
        //   620: aload           4
        //   622: aconst_null    
        //   623: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/parser/OCPartialContext;)V
        //   626: aload           4
        //   628: checkcast       Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;
        //   631: new             Lcom/jetbrains/cidr/lang/workspace/OCResolveLanguageAndConfiguration;
        //   634: dup            
        //   635: aload           15
        //   637: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getConfiguration:()Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;
        //   642: aload           15
        //   644: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getLanguageKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   649: invokespecial   com/jetbrains/cidr/lang/workspace/OCResolveLanguageAndConfiguration.<init>:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;)V
        //   652: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCFileImpl.setParsedResolveLanguageAndConfiguration:(Lcom/jetbrains/cidr/lang/workspace/OCResolveLanguageAndConfiguration;)V
        //   655: goto            689
        //   658: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   661: athrow         
        //   662: aload           7
        //   664: ifnull          681
        //   667: aload           7
        //   669: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   674: goto            682
        //   677: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   680: athrow         
        //   681: aload_3        
        //   682: aload           15
        //   684: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.updatePartialContext:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   687: astore          15
        //   689: aload           7
        //   691: ifnull          708
        //   694: aload           7
        //   696: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   701: goto            710
        //   704: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   707: athrow         
        //   708: aload           6
        //   710: astore          16
        //   712: new             Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer;
        //   715: dup            
        //   716: aload           15
        //   718: aload           16
        //   720: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   723: ifeq            738
        //   726: aload           16
        //   728: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   731: goto            739
        //   734: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   737: athrow         
        //   738: aconst_null    
        //   739: iload           12
        //   741: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/jetbrains/cidr/lang/psi/OCFile;I)V
        //   744: astore          17
        //   746: aload           17
        //   748: aload           14
        //   750: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.setChangeSet:(Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;)V
        //   753: invokestatic    com/intellij/lang/PsiBuilderFactory.getInstance:()Lcom/intellij/lang/PsiBuilderFactory;
        //   756: astore          18
        //   758: aload           18
        //   760: aload           5
        //   762: aload_2        
        //   763: aload           17
        //   765: aload_1        
        //   766: invokeinterface com/jetbrains/cidr/lang/parser/OCRootElementType.getLanguage:()Lcom/intellij/lang/Language;
        //   771: aload_2        
        //   772: invokeinterface com/intellij/lang/ASTNode.getChars:()Ljava/lang/CharSequence;
        //   777: invokevirtual   com/intellij/lang/PsiBuilderFactory.createBuilder:(Lcom/intellij/openapi/project/Project;Lcom/intellij/lang/ASTNode;Lcom/intellij/lexer/Lexer;Lcom/intellij/lang/Language;Ljava/lang/CharSequence;)Lcom/intellij/lang/PsiBuilder;
        //   780: astore          19
        //   782: aload           17
        //   784: aconst_null    
        //   785: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.setChangeSet:(Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;)V
        //   788: aload           4
        //   790: aload           14
        //   792: invokestatic    com/jetbrains/cidr/lang/parser/OCParser.a:(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;)V
        //   795: aload_1        
        //   796: aload_2        
        //   797: aload           19
        //   799: invokeinterface com/jetbrains/cidr/lang/parser/OCRootElementType.doParse:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/PsiBuilder;)Lcom/intellij/lang/ASTNode;
        //   804: astore          20
        //   806: aload           20
        //   808: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //   813: aload           13
        //   815: monitorexit    
        //   816: areturn        
        //   817: astore          21
        //   819: aload           13
        //   821: monitorexit    
        //   822: aload           21
        //   824: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  460    479    479    483    Ljava/lang/IllegalArgumentException;
        //  415    437    440    444    Ljava/lang/IllegalArgumentException;
        //  401    422    425    429    Ljava/lang/IllegalArgumentException;
        //  393    408    411    415    Ljava/lang/IllegalArgumentException;
        //  319    351    351    355    Ljava/lang/IllegalArgumentException;
        //  296    340    343    347    Ljava/lang/IllegalArgumentException;
        //  276    312    315    319    Ljava/lang/IllegalArgumentException;
        //  264    289    292    296    Ljava/lang/IllegalArgumentException;
        //  247    269    272    276    Ljava/lang/IllegalArgumentException;
        //  232    257    260    264    Ljava/lang/IllegalArgumentException;
        //  222    240    243    247    Ljava/lang/IllegalArgumentException;
        //  155    177    180    184    Ljava/lang/IllegalArgumentException;
        //  150    162    165    169    Ljava/lang/IllegalArgumentException;
        //  103    119    119    123    Ljava/lang/IllegalArgumentException;
        //  96     108    111    115    Ljava/lang/IllegalArgumentException;
        //  219    603    817    825    Any
        //  712    734    734    738    Ljava/lang/IllegalArgumentException;
        //  689    704    704    708    Ljava/lang/IllegalArgumentException;
        //  662    677    677    681    Ljava/lang/IllegalArgumentException;
        //  613    658    658    662    Ljava/lang/IllegalArgumentException;
        //  604    816    817    825    Any
        //  817    822    817    825    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0247:
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
    
    private static boolean a(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/parser/OCParser", "isCodeInsightAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement psi = astNode.getPsi();
        if (psi != null) {
            final PsiFile containingFile = psi.getContainingFile();
            try {
                if (containingFile != null) {
                    return OCCodeInsightUtil.isCodeInsightAvailable(containingFile);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return OCCodeInsightUtil.isCodeInsightAvailable(astNode.getTextLength(), false);
    }
    
    private static void a(final PsiFile psiFile, final OCContextChangeSet set) {
        try {
            if (set == null || !set.isUpdated()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile(psiFile);
        try {
            if (virtualFile != null) {
                OCInclusionContextUtil.storeCachedContextChangeSet(psiFile.getProject(), virtualFile, set);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static OCContextChangeSet a(final PsiFile psiFile, final int n, final int n2) {
        Label_0022: {
            try {
                if (OCParser.ourForceContextChangeCacheUsage == null) {
                    break Label_0022;
                }
                final Boolean b = OCParser.ourForceContextChangeCacheUsage;
                final boolean b2 = b;
                if (!b2) {
                    break Label_0022;
                }
                break Label_0022;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final Boolean b = OCParser.ourForceContextChangeCacheUsage;
                final boolean b2 = b;
                if (!b2) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile(psiFile);
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Document document = FileDocumentManager.getInstance().getDocument(virtualFile);
        try {
            if (document == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return OCContextChangeSet.reuse(a(psiFile).text, OCInclusionContextUtil.cachedContextChangeSet(psiFile.getProject(), virtualFile), n, n2);
    }
    
    protected static OCImmutableInclusionContext evaluatePartialContext(final OCFile ocFile, final int finalOffset) {
        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile);
        final FileSymbolTablesCache fileSymbolTablesCache = (FileSymbolTablesCache)ocFile.getProject().getComponent((Class)FileSymbolTablesCache.class);
        try {
            if (virtualFile == null || fileSymbolTablesCache == null) {
                return OCInclusionContextUtil.headerContext((PsiFile)ocFile);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCPartialContext ocPartialContext = b((PsiFile)ocFile);
        if (ocPartialContext == null) {
            final OCInclusionContext derive = OCInclusionContextUtil.headerContext((PsiFile)ocFile).derive();
            a((PsiFile)ocFile, new OCPartialContext(fileSymbolTablesCache.calcTableUsingPSI((PsiFile)ocFile, virtualFile, derive), derive, 0));
            ensureLazyBlocksParsed((PsiFile)ocFile, 0, finalOffset - 1);
            ocPartialContext = b((PsiFile)ocFile);
        }
        Label_0217: {
            try {
                if (ocPartialContext != null) {
                    if (ocPartialContext.getFinalOffset() <= finalOffset) {
                        break Label_0217;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCInclusionContext derive2 = OCInclusionContextUtil.headerContext((PsiFile)ocFile).derive();
            final FileSymbolTable calcTableUsingPSI = fileSymbolTablesCache.calcTableUsingPSI((PsiFile)ocFile, virtualFile, derive2);
            final OCContextChangeSet a = a((PsiFile)ocFile, 0, finalOffset);
            final OCInclusionContext preprocessFile = derive2.preprocessFile(ocFile, virtualFile, null, derive2.getInclusionLevel(), 0, finalOffset, calcTableUsingPSI.getContents(), a);
            a((PsiFile)ocFile, a);
            a((PsiFile)ocFile, new OCPartialContext(calcTableUsingPSI, preprocessFile, finalOffset));
            return preprocessFile;
        }
        final OCInclusionContext inclusionContext = ocPartialContext.getInclusionContext();
        ensureLazyBlocksParsed((PsiFile)ocFile, ocPartialContext.getFinalOffset(), finalOffset - 1);
        final OCContextChangeSet a2 = a((PsiFile)ocFile, ocPartialContext.getFinalOffset(), finalOffset);
        final OCInclusionContext preprocessFile2 = inclusionContext.preprocessFile(ocFile, virtualFile, null, inclusionContext.getInclusionLevel(), ocPartialContext.getFinalOffset(), finalOffset, ocPartialContext.getSymbolTable().getContents(), a2);
        a((PsiFile)ocFile, a2);
        ocPartialContext.setFinalOffset(finalOffset);
        ocPartialContext.setInclusionContext(preprocessFile2);
        return preprocessFile2;
        headerContext = OCInclusionContextUtil.headerContext((PsiFile)ocFile);
        return headerContext;
    }
    
    @NotNull
    public static PsiFile partialContextHolder(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/parser/OCParser", "partialContextHolder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiFile originalFile;
        try {
            originalFile = psiFile.getOriginalFile();
            if (originalFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParser", "partialContextHolder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return originalFile;
    }
    
    private static void a(@NotNull final PsiFile psiFile, @Nullable final OCPartialContext ocPartialContext) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/parser/OCParser", "setPartialContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCParser.PARTIAL_CONTEXT.set((UserDataHolder)partialContextHolder(psiFile), (Object)new SoftReference((Object)ocPartialContext));
    }
    
    @Nullable
    private static OCPartialContext b(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/parser/OCParser", "getPartialContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final SoftReference softReference = (SoftReference)OCParser.PARTIAL_CONTEXT.get((UserDataHolder)partialContextHolder(psiFile));
        try {
            if (softReference == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (OCPartialContext)softReference.get();
    }
    
    protected static void ensureLazyBlocksParsed(final PsiFile psiFile, final int n, final int n2) {
        try {
            if (n > n2) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiElement psiElement = PsiTreeUtil.findPrevParent((PsiElement)psiFile, psiFile.findElementAt(n));
        final PsiElement prevParent = PsiTreeUtil.findPrevParent((PsiElement)psiFile, psiFile.findElementAt(n2));
        final OCRecursiveVisitor ocRecursiveVisitor = new OCRecursiveVisitor(new TextRange(n, n2)) {
            @Override
            protected TextRange getTextRange(final PsiElement psiElement) {
                return psiElement.getTextRange();
            }
        };
        while (true) {
            try {
                if (psiElement == prevParent || psiElement == null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            psiElement.accept((PsiElementVisitor)ocRecursiveVisitor);
            psiElement = psiElement.getNextSibling();
        }
        try {
            if (psiElement != null) {
                psiElement.accept((PsiElementVisitor)ocRecursiveVisitor);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        OCLog.LOG.error("Assertion failed", new String[] { psiFile.getText() });
    }
    
    protected static OCInclusionContext updatePartialContext(final PsiElement psiElement, final OCInclusionContext ocInclusionContext) {
        OCParsingNameScope ocParsingNameScope = ocInclusionContext.getNameScope();
        if (psiElement instanceof OCMethod) {
            ocParsingNameScope = ocParsingNameScope.defineLocalScope();
            final Iterator<OCMethodSelectorPart> iterator = ((OCMethod)psiElement).getParameters().iterator();
            while (iterator.hasNext()) {
                final String parameterName = iterator.next().getParameterName();
                try {
                    if (parameterName == null) {
                        continue;
                    }
                    ocParsingNameScope.defineValue(parameterName, false);
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
        else if (psiElement instanceof OCCallable) {
            OCDeclarator declarator = null;
            OCFunctionDeclaration ocFunctionDeclaration = null;
            if (psiElement instanceof OCFunctionDeclaration) {
                ocFunctionDeclaration = (OCFunctionDeclaration)psiElement;
                declarator = ocFunctionDeclaration.getDeclarator();
            }
            OCCppNamespaceQualifier namespaceQualifier = null;
            Label_0140: {
                try {
                    if (declarator != null) {
                        namespaceQualifier = declarator.getNamespaceQualifier();
                        break Label_0140;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                namespaceQualifier = null;
            }
            OCCppNamespaceQualifier namespaceQualifier2 = namespaceQualifier;
            final ArrayList<String> list = new ArrayList<String>();
            while (namespaceQualifier2 != null) {
                final String name = namespaceQualifier2.getName();
                namespaceQualifier2 = namespaceQualifier2.getNamespaceQualifier();
                try {
                    if (name == null) {
                        continue;
                    }
                    list.add(0, name);
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final OCParsingNameScope ocParsingNameScope2 = ocParsingNameScope;
            for (final String s : list) {
                if (s != null) {
                    ocParsingNameScope = ocParsingNameScope.defineNamespace(s);
                }
            }
            ocParsingNameScope = ocParsingNameScope.defineLocalScope();
            String name2 = null;
            Label_0271: {
                try {
                    if (declarator != null) {
                        name2 = declarator.getName();
                        break Label_0271;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                name2 = null;
            }
            final String s2 = name2;
            Label_0475: {
                if (s2 != null) {
                    final OCParsingNameScope.Kind kind = ocParsingNameScope.getKind(s2);
                    final boolean template = OCParsingNameScope.isTemplate(kind);
                    boolean b = false;
                    boolean b2 = false;
                    Label_0459: {
                        Label_0442: {
                            Label_0342: {
                                Label_0322: {
                                    try {
                                        if (!OCParsingNameScope.isType(kind)) {
                                            break Label_0442;
                                        }
                                        final OCFunctionDeclaration ocFunctionDeclaration2 = ocFunctionDeclaration;
                                        final boolean b3 = OCElementUtil.hasNoReturnType(ocFunctionDeclaration2);
                                        if (b3) {
                                            break Label_0322;
                                        }
                                        break Label_0442;
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw a(ex5);
                                    }
                                    try {
                                        final OCFunctionDeclaration ocFunctionDeclaration2 = ocFunctionDeclaration;
                                        final boolean b3 = OCElementUtil.hasNoReturnType(ocFunctionDeclaration2);
                                        if (!b3) {
                                            break Label_0442;
                                        }
                                        if (!OCElementUtil.isOperatorDeclaration(declarator)) {
                                            break Label_0342;
                                        }
                                    }
                                    catch (IllegalArgumentException ex6) {
                                        throw a(ex6);
                                    }
                                }
                                b2 = true;
                                break Label_0442;
                            }
                            if (list.isEmpty()) {
                                final OCStruct ocStruct = (OCStruct)PsiTreeUtil.getParentOfType(psiElement, (Class)OCStruct.class, true, new Class[] { OCLocalScopeable.class });
                                Label_0403: {
                                    try {
                                        if (ocStruct == null || !s2.equals(ocStruct.getName())) {
                                            break Label_0403;
                                        }
                                    }
                                    catch (IllegalArgumentException ex7) {
                                        throw a(ex7);
                                    }
                                    b = true;
                                }
                            }
                            else {
                                try {
                                    if (!s2.equals(ContainerUtil.getLastItem((List)list)) || !OCParsingNameScope.isType(ocParsingNameScope2.getKind(list))) {
                                        break Label_0442;
                                    }
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                                b = true;
                            }
                            try {
                                if (b) {
                                    break Label_0475;
                                }
                                final boolean b4 = b2;
                                if (!b4) {
                                    break Label_0459;
                                }
                                break Label_0475;
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                        }
                        try {
                            final boolean b4 = b2;
                            if (!b4) {
                                ocParsingNameScope.defineValue(s2, template);
                            }
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                    }
                }
            }
            final List parameters = ((OCCallable)psiElement).getParameters();
            final OCTemplateParameterList list2 = (OCTemplateParameterList)PsiTreeUtil.getChildOfType(psiElement, (Class)OCTemplateParameterList.class);
            if (list2 != null) {
                final Iterator<OCTypeParameterDeclaration> iterator3 = list2.getTypeParameterDeclarations().iterator();
                while (iterator3.hasNext()) {
                    final String name3 = iterator3.next().getName();
                    try {
                        if (name3 == null) {
                            continue;
                        }
                        ocParsingNameScope.defineType(name3, true);
                    }
                    catch (IllegalArgumentException ex11) {
                        throw a(ex11);
                    }
                }
                final Iterator<OCParameterDeclaration> iterator4 = list2.getParameterDeclarations().iterator();
                while (iterator4.hasNext()) {
                    final OCDeclarator declarator2 = iterator4.next().getDeclarator();
                    try {
                        if (declarator2 == null) {
                            continue;
                        }
                        ocParsingNameScope.defineValue(declarator2.getName(), false);
                    }
                    catch (IllegalArgumentException ex12) {
                        throw a(ex12);
                    }
                }
            }
            if (parameters != null) {
                final Iterator<PsiNamedElement> iterator5 = parameters.iterator();
                while (iterator5.hasNext()) {
                    ocParsingNameScope.defineValue(iterator5.next().getName(), false);
                }
            }
        }
        return ocInclusionContext.derive(ocParsingNameScope);
    }
    
    public static void setForceContextChangeCacheUsage(@Nullable final Boolean ourForceContextChangeCacheUsage) {
        OCParser.ourForceContextChangeCacheUsage = ourForceContextChangeCacheUsage;
    }
    
    @NotNull
    private static FileTextInfo a(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/parser/OCParser", "getCachedFileTextInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        FileTextInfo fileTextInfo = (FileTextInfo)SoftReference.deref((Getter)OCParser.FILE_TEXT.get((UserDataHolder)psiFile));
        final long modificationStamp = psiFile.getModificationStamp();
        FileTextInfo fileTextInfo2 = null;
        Label_0130: {
            try {
                if (fileTextInfo != null) {
                    if (fileTextInfo.timestamp == modificationStamp) {
                        break Label_0130;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final String text = psiFile.getText();
            fileTextInfo = new FileTextInfo(text, LineSet.createLineSet(text), modificationStamp);
            OCParser.FILE_TEXT.set((UserDataHolder)psiFile, (Object)new SoftReference((Object)fileTextInfo));
            try {
                fileTextInfo2 = fileTextInfo;
                if (fileTextInfo2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCParser", "getCachedFileTextInfo"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return fileTextInfo2;
    }
    
    static {
        FRAGMENT_CONTEXTS = NotNullLazyKey.create("FRAGMENT_CONTEXTS", (NotNullFunction)a(false));
        FORCED_OBJCPP_FRAGMENT_CONTEXTS = NotNullLazyKey.create("FRAGMENT_CONTEXTS", (NotNullFunction)a(true));
        PARTIAL_CONTEXT = Key.create("PARTIAL_CONTEXT");
        FILE_TEXT = Key.create("FILE_TEXT");
        instance = new OCParser();
        GLOBAL_REPARSE = ((astNode, lighterASTNode, flyweightCapableTreeStructure) -> {
            Label_0031: {
                try {
                    if (astNode.getElementType() != OCElementTypes.LAZY_BLOCK_STATEMENT) {
                        return ThreeState.UNSURE;
                    }
                    final LighterASTNode lighterASTNode2 = lighterASTNode;
                    final IElementType elementType = lighterASTNode2.getTokenType();
                    final OCLazyBlockStatementElementType ocLazyBlockStatementElementType = OCElementTypes.LAZY_BLOCK_STATEMENT;
                    if (elementType == ocLazyBlockStatementElementType) {
                        break Label_0031;
                    }
                    return ThreeState.UNSURE;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final LighterASTNode lighterASTNode2 = lighterASTNode;
                    final IElementType elementType = lighterASTNode2.getTokenType();
                    final OCLazyBlockStatementElementType ocLazyBlockStatementElementType = OCElementTypes.LAZY_BLOCK_STATEMENT;
                    if (elementType == ocLazyBlockStatementElementType) {
                        return ThreeState.NO;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ThreeState.UNSURE;
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class FileTextInfo
    {
        @NotNull
        final String text;
        @NotNull
        final LineSet lineSet;
        final long timestamp;
        
        private FileTextInfo(@NotNull final String text, @NotNull final LineSet lineSet, final long timestamp) {
            if (text == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/parser/OCParser$FileTextInfo", "<init>"));
            }
            if (lineSet == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lineSet", "com/jetbrains/cidr/lang/parser/OCParser$FileTextInfo", "<init>"));
            }
            this.text = text;
            this.lineSet = lineSet;
            this.timestamp = timestamp;
        }
    }
}
