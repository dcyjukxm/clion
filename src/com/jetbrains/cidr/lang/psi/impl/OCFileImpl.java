// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbolsCache;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import com.intellij.psi.PsiFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import java.util.Collection;
import java.util.HashSet;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.intellij.util.CommonProcessors;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.fileTypes.FileType;
import gnu.trove.THashSet;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.lang.workspace.OCResolveLanguageAndConfiguration;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Set;
import com.intellij.lang.annotation.AnnotationSession;
import com.jetbrains.cidr.lang.psi.OCConfigurationOwner;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.extapi.psi.PsiFileBase;

public class OCFileImpl extends PsiFileBase implements OCFile, OCConfigurationOwner
{
    private int myAnnotationSessionDepthsCounter;
    private AnnotationSession myCurrentAnnotationSession;
    private final Object myAnnotationSessionLock;
    private final Set<OCFile> myIncludedFrom;
    @Nullable
    private volatile OCLanguageKind myFileKindCache;
    @Nullable
    private volatile FileSymbolTable mySymbolTableCache;
    @Nullable
    private volatile OCResolveLanguageAndConfiguration myParsedResolveLanguageAndConfiguration;
    
    public OCFileImpl(final FileViewProvider viewProvider) {
        super(viewProvider, OCLanguage.getInstance());
        this.myAnnotationSessionDepthsCounter = 0;
        this.myCurrentAnnotationSession = null;
        this.myAnnotationSessionLock = new Object();
        this.myIncludedFrom = (Set<OCFile>)new THashSet();
    }
    
    @NotNull
    public FileType getFileType() {
        OCFileType instance;
        try {
            instance = OCFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "getFileType"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        return (FileType)instance;
    }
    
    @Override
    public boolean isHeader() {
        return isHeaderFile(this.getName());
    }
    
    public static boolean isHeaderFile(final String s) {
        return OCFileTypeHelpers.isHeaderFile(s);
    }
    
    public static boolean isSourceCodeFile(final String s) {
        return OCFileTypeHelpers.isSourceFile(s);
    }
    
    @Override
    public void subtreeChanged() {
        super.subtreeChanged();
    }
    
    @Nullable
    @Override
    public OCFile getAssociatedFile() {
        final String nameWithoutExtension = FileUtil.getNameWithoutExtension(this.getName());
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor((Processor)findFirstProcessor, true, new Condition[] { ocFile -> FileUtil.getNameWithoutExtension(ocFile.getName()).equals(nameWithoutExtension), Conditions.alwaysTrue() });
        try {
            this.accept(new OCRecursiveVisitor() {
                @Override
                public void visitFunctionDeclaration(final OCFunctionDeclaration ocFunctionDeclaration) {
                    this.a(((OCSymbolDeclarator<OCSymbol<?>>)ocFunctionDeclaration).getSymbol());
                    super.visitFunctionDeclaration(ocFunctionDeclaration);
                }
                
                @Override
                public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
                    this.a(ocFunctionDefinition.getSymbol());
                    super.visitFunctionDefinition(ocFunctionDefinition);
                }
                
                @Override
                public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
                    this.a(ocClassDeclaration.getSymbol());
                    super.visitClassDeclaration(ocClassDeclaration);
                }
                
                private void a(final OCSymbol<?> ocSymbol) {
                    try {
                        if (ocSymbol != null) {
                            ocSymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<?>>)(p1 -> {
                                // 
                                // This method could not be decompiled.
                                // 
                                // Original Bytecode:
                                // 
                                //     0: aload_2        
                                //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
                                //     6: astore_3       
                                //     7: aload_3        
                                //     8: ifnull          65
                                //    11: aload_3        
                                //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
                                //    17: aload_0        
                                //    18: getfield        com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.this$0:Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;
                                //    21: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isHeader:()Z
                                //    24: if_icmpeq       65
                                //    27: goto            34
                                //    30: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                //    33: athrow         
                                //    34: aload_1        
                                //    35: aload_3        
                                //    36: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.process:(Ljava/lang/Object;)Z
                                //    39: ifne            65
                                //    42: goto            49
                                //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                //    48: athrow         
                                //    49: new             Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                //    52: dup            
                                //    53: aload_0        
                                //    54: getfield        com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.this$0:Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;
                                //    57: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException.<init>:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;)V
                                //    60: athrow         
                                //    61: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                //    64: athrow         
                                //    65: iconst_1       
                                //    66: ireturn        
                                //    Exceptions:
                                //  Try           Handler
                                //  Start  End    Start  End    Type                                                        
                                //  -----  -----  -----  -----  ------------------------------------------------------------
                                //  7      27     30     34     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                //  11     42     45     49     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                //  34     61     61     65     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                                // 
                                // The error that occurred was:
                                // 
                                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                            }));
                        }
                    }
                    catch (1StopException ex) {
                        throw b(ex);
                    }
                }
                
                private static 1StopException b(final 1StopException ex) {
                    return ex;
                }
            });
        }
        catch (OCFileImpl.1StopException ex2) {}
        try {
            orderedProcessor.finish();
            if (findFirstProcessor.isFound()) {
                return (OCFile)findFirstProcessor.getFoundValue();
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        return this.getAssociatedFileWithSameName();
    }
    
    @Nullable
    @Override
    public OCFile getAssociatedFileWithSameName() {
        final OCWorkspace workspace = OCWorkspaceManager.getWorkspace(this.getProject());
        final VirtualFile virtualFile = this.getVirtualFile();
        Set<String> set = null;
        Label_0037: {
            try {
                if (this.isHeader()) {
                    set = OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS;
                    break Label_0037;
                }
            }
            catch (OCFileImpl.1StopException ex) {
                throw b(ex);
            }
            set = OCFileTypeHelpers.HEADER_FILE_EXTENSIONS;
        }
        final HashSet set2 = new HashSet<Object>(set);
        set2.remove("pch");
        final Iterator<String> iterator = (Iterator<String>)set2.iterator();
        while (iterator.hasNext()) {
            final String string = FileUtil.getNameWithoutExtension(this.getName()) + "." + iterator.next();
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor((com.intellij.util.Processor<Object>)findFirstProcessor, true, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { p1 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_1        
                    //     1: ifnull          36
                    //     4: aload_0        
                    //     5: ifnull          36
                    //     8: goto            15
                    //    11: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    14: athrow         
                    //    15: aload_0        
                    //    16: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
                    //    19: aload_1        
                    //    20: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
                    //    23: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
                    //    26: ifeq            44
                    //    29: goto            36
                    //    32: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    35: athrow         
                    //    36: iconst_1       
                    //    37: goto            45
                    //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    43: athrow         
                    //    44: iconst_0       
                    //    45: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                                        
                    //  -----  -----  -----  -----  ------------------------------------------------------------
                    //  0      8      11     15     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //  4      29     32     36     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //  15     40     40     44     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
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
                }, virtualFile2 -> workspace.areFromSameProject(virtualFile, virtualFile2), Conditions.alwaysTrue() });
            ContainerUtil.process((Iterable)OCSearchUtil.getProjectVirtualFilesByName(this.getProject(), string), virtualFile -> {
                Label_0022: {
                    try {
                        if (!virtualFile.isValid()) {
                            break Label_0022;
                        }
                        final OCCommonProcessors.OrderedProcessor<VirtualFile> orderedProcessor2 = (OCCommonProcessors.OrderedProcessor<VirtualFile>)orderedProcessor;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = orderedProcessor2.process(virtualFile2);
                        if (b) {
                            break Label_0022;
                        }
                        return false;
                    }
                    catch (OCFileImpl.1StopException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCCommonProcessors.OrderedProcessor<VirtualFile> orderedProcessor2 = (OCCommonProcessors.OrderedProcessor<VirtualFile>)orderedProcessor;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = orderedProcessor2.process(virtualFile2);
                        if (b) {
                            return true;
                        }
                    }
                    catch (OCFileImpl.1StopException ex2) {
                        throw b(ex2);
                    }
                }
                return false;
            });
            orderedProcessor.finish();
            if (findFirstProcessor.isFound()) {
                final PsiFile file = PsiManager.getInstance(this.getProject()).findFile((VirtualFile)findFirstProcessor.getFoundValue());
                try {
                    if (file instanceof OCFile) {
                        return (OCFile)file;
                    }
                }
                catch (OCFileImpl.1StopException ex2) {
                    throw b(ex2);
                }
                return null;
            }
        }
        return null;
    }
    
    @Override
    public boolean isCpp() {
        return this.getKind().isCpp();
    }
    
    @NotNull
    @Override
    public final OCLanguageKind getKind() {
        OCLanguageKind myFileKindCache = this.myFileKindCache;
        if (myFileKindCache == null) {
            myFileKindCache = (this.myFileKindCache = OCLanguageKindCalculator.calculateLanguageKind(this));
        }
        OCLanguageKind ocLanguageKind;
        try {
            ocLanguageKind = myFileKindCache;
            if (ocLanguageKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "getKind"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        return ocLanguageKind;
    }
    
    @Override
    public void clearCaches() {
        this.myFileKindCache = null;
        this.mySymbolTableCache = null;
        super.clearCaches();
    }
    
    @Override
    public boolean isInProjectSources() {
        return OCSearchScope.isInProjectSources((PsiElement)this);
    }
    
    @Override
    public boolean isInProjectSourcesOrLibraries() {
        return OCSearchScope.isInProjectSourcesOrLibraries((PsiElement)this);
    }
    
    @Override
    public boolean isInLibraries() {
        return OCSearchScope.isInLibraries((PsiElement)this);
    }
    
    @Override
    public boolean processSymbolsRecursively(@NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "processSymbolsRecursively"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        return this.processSymbolsRecursively(null, processor);
    }
    
    public boolean processSymbolsRecursively(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "processSymbolsRecursively"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        final PsiElement context = this.getContext();
        OCInclusionContext derive = null;
        Label_0106: {
            Label_0087: {
                try {
                    if (!(context instanceof OCElement)) {
                        break Label_0087;
                    }
                    final PsiElement psiElement = context;
                    final OCElement ocElement = (OCElement)psiElement;
                    final OCFile ocFile = ocElement.getContainingOCFile();
                    final Processor<OCSymbol> processor2 = processor;
                    final boolean b = ocFile.processSymbolsRecursively(processor2);
                    if (!b) {
                        return false;
                    }
                    break Label_0087;
                }
                catch (OCFileImpl.1StopException ex2) {
                    throw b(ex2);
                }
                try {
                    final PsiElement psiElement = context;
                    final OCElement ocElement = (OCElement)psiElement;
                    final OCFile ocFile = ocElement.getContainingOCFile();
                    final Processor<OCSymbol> processor2 = processor;
                    final boolean b = ocFile.processSymbolsRecursively(processor2);
                    if (!b) {
                        return false;
                    }
                }
                catch (OCFileImpl.1StopException ex3) {
                    throw b(ex3);
                }
                try {
                    if (ocImmutableInclusionContext == null) {
                        derive = null;
                        break Label_0106;
                    }
                }
                catch (OCFileImpl.1StopException ex4) {
                    throw b(ex4);
                }
            }
            derive = ocImmutableInclusionContext.derive();
        }
        final FileSymbolTable symbolTable = this.getSymbolTable(derive);
        Label_0140: {
            try {
                if (symbolTable == null) {
                    break Label_0140;
                }
                final FileSymbolTable fileSymbolTable = symbolTable;
                final OCFileImpl ocFileImpl = this;
                final Processor<OCSymbol> processor3 = processor;
                final Processor<OCSymbol> processor4 = (Processor<OCSymbol>)new Processor<OCSymbol>() {
                    final /* synthetic */ Processor val$processor;
                    
                    public boolean process(final OCSymbol ocSymbol) {
                        return this.val$processor.process((Object)ocSymbol) && (!(ocSymbol instanceof OCMembersContainer) || ((OCMembersContainer)ocSymbol).processMembers(null, (Processor)this));
                    }
                };
                final boolean b2 = fileSymbolTable.shallowProcessSymbols((Processor<OCSymbol>)processor4);
                if (b2) {
                    break Label_0140;
                }
                return false;
            }
            catch (OCFileImpl.1StopException ex5) {
                throw b(ex5);
            }
            try {
                final FileSymbolTable fileSymbolTable = symbolTable;
                final OCFileImpl ocFileImpl = this;
                final Processor<OCSymbol> processor3 = processor;
                final Processor<OCSymbol> processor4 = (Processor<OCSymbol>)new Processor<OCSymbol>() {
                    final /* synthetic */ Processor val$processor;
                    
                    public boolean process(final OCSymbol ocSymbol) {
                        return processor3.process((Object)ocSymbol) && (!(ocSymbol instanceof OCMembersContainer) || ((OCMembersContainer)ocSymbol).processMembers(null, (Processor)this));
                    }
                };
                final boolean b2 = fileSymbolTable.shallowProcessSymbols((Processor<OCSymbol>)processor4);
                if (b2) {
                    return true;
                }
            }
            catch (OCFileImpl.1StopException ex6) {
                throw b(ex6);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public <T extends OCSymbol> T findSymbol(@NotNull final OCElement ocElement, @NotNull final Class<T> clazz) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "findSymbol"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolClass", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "findSymbol"));
            }
        }
        catch (OCFileImpl.1StopException ex2) {
            throw b(ex2);
        }
        final PsiElement context = this.getContext();
        try {
            if (context instanceof OCElement) {
                return ((OCElement)context).getContainingOCFile().findSymbol(ocElement, clazz);
            }
        }
        catch (OCFileImpl.1StopException ex3) {
            throw b(ex3);
        }
        final FileSymbolTable symbolTable = this.getSymbolTable(null);
        try {
            if (symbolTable != null) {
                final OCSymbol symbol = symbolTable.findSymbol((PsiElement)ocElement, clazz);
                return (T)symbol;
            }
        }
        catch (OCFileImpl.1StopException ex4) {
            throw b(ex4);
        }
        final OCSymbol symbol = null;
        return (T)symbol;
    }
    
    @Nullable
    @Override
    public <T extends OCSymbol> T findSymbol(@Nullable final String s, final Class<T> clazz) {
        final PsiElement context = this.getContext();
        try {
            if (context instanceof OCElement) {
                return ((OCElement)context).getContainingOCFile().findSymbol(s, clazz);
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        final FileSymbolTable symbolTable = this.getSymbolTable(null);
        try {
            if (symbolTable != null) {
                final OCSymbol symbol = symbolTable.findSymbol(s, clazz);
                return (T)symbol;
            }
        }
        catch (OCFileImpl.1StopException ex2) {
            throw b(ex2);
        }
        final OCSymbol symbol = null;
        return (T)symbol;
    }
    
    @Nullable
    public FileSymbolTable getSymbolTable(@Nullable final OCInclusionContext ocInclusionContext) {
        try {
            if (ocInclusionContext != null) {
                return FileSymbolTable.forFile((PsiFile)this, ocInclusionContext);
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        FileSymbolTable mySymbolTableCache = this.mySymbolTableCache;
        if (mySymbolTableCache == null) {
            mySymbolTableCache = (this.mySymbolTableCache = FileSymbolTable.forFile((PsiFile)this, OCInclusionContextUtil.headerContext((PsiFile)this).derive()));
        }
        return mySymbolTableCache;
    }
    
    @NotNull
    @Override
    public OCNamespaceLikeSymbol getMembersContainer(final boolean b) {
        final OCFileGlobalSymbolsCache instance = OCFileGlobalSymbolsCache.getInstance(this.getProject());
        OCNamespaceLikeSymbol ocNamespaceLikeSymbol = null;
        Label_0029: {
            try {
                if (b) {
                    final OCNamespaceLikeSymbol ocNamespaceLikeSymbol2;
                    ocNamespaceLikeSymbol = (ocNamespaceLikeSymbol2 = instance.typesOnlyTableForFile(this));
                    break Label_0029;
                }
            }
            catch (OCFileImpl.1StopException ex) {
                throw b(ex);
            }
            OCNamespaceLikeSymbol ocNamespaceLikeSymbol2;
            ocNamespaceLikeSymbol = (ocNamespaceLikeSymbol2 = instance.lightTableForFile(this));
            try {
                if (ocNamespaceLikeSymbol2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "getMembersContainer"));
                }
            }
            catch (OCFileImpl.1StopException ex2) {
                throw b(ex2);
            }
        }
        return ocNamespaceLikeSymbol;
    }
    
    @Nullable
    @Override
    public OCSymbol getSameNamedClass() {
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            final /* synthetic */ String val$name = OCFileImpl.this.getMainClassName();
            
            protected boolean accept(final OCSymbol ocSymbol) {
                return (ocSymbol instanceof OCClassSymbol || (ocSymbol instanceof OCStructSymbol && !((OCStructSymbol)ocSymbol).isInnerClass())) && this.val$name.equals(ocSymbol.getName());
            }
        };
        this.processSymbolsRecursively((Processor<OCSymbol>)findFirstProcessor);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Override
    public String getMainClassName() {
        String s = FileUtil.getNameWithoutExtension(this.getName());
        final int index = s.indexOf(43);
        if (index != -1) {
            s = s.substring(0, index);
        }
        final int index2 = s.indexOf(45);
        if (index2 != -1) {
            s = s.substring(0, index2);
        }
        return s;
    }
    
    @Nullable
    @Override
    public OCSymbolDeclarator findFirstClass() {
        return this.findClass(null);
    }
    
    @Nullable
    @Override
    public OCSymbolDeclarator findClass(final String s) {
        final Ref ref = new Ref();
        this.accept(new OCRecursiveVisitor() {
            @Override
            public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
                if (!(ocClassDeclaration instanceof OCClassPredeclaration) && ref.isNull() && (s == null || s.equals(ocClassDeclaration.getCanonicalName()))) {
                    ref.set((Object)ocClassDeclaration);
                }
                super.visitClassDeclaration(ocClassDeclaration);
            }
            
            @Override
            public void visitStruct(final OCStruct ocStruct) {
                if (ref.isNull() && (s == null || s.equals(ocStruct.getName()))) {
                    ref.set((Object)ocStruct);
                }
                super.visitStruct(ocStruct);
            }
        });
        return (OCSymbolDeclarator)ref.get();
    }
    
    @Override
    public List<OCIncludeDirective> findIncludeDirectives() {
        final ArrayList<OCIncludeDirective> list = new ArrayList<OCIncludeDirective>();
        this.acceptChildren(new OCRecursiveVisitor() {
            @Override
            public void visitImportDirective(final OCIncludeDirective ocIncludeDirective) {
                list.add(ocIncludeDirective);
            }
        });
        return list;
    }
    
    @Override
    public void pushAnnotationSession(final AnnotationSession myCurrentAnnotationSession) {
        synchronized (this.myAnnotationSessionLock) {
            if (this.myAnnotationSessionDepthsCounter == 0) {
                this.myCurrentAnnotationSession = myCurrentAnnotationSession;
            }
            ++this.myAnnotationSessionDepthsCounter;
        }
    }
    
    @Override
    public void popAnnotationSession() {
        synchronized (this.myAnnotationSessionLock) {
            --this.myAnnotationSessionDepthsCounter;
            if (this.myAnnotationSessionDepthsCounter == 0) {
                this.myCurrentAnnotationSession = null;
            }
        }
    }
    
    @Nullable
    @Override
    public AnnotationSession getCurrentAnnotationSession() {
        return this.myCurrentAnnotationSession;
    }
    
    @NotNull
    @Override
    public Object getAnnotationSessionLock() {
        Object myAnnotationSessionLock;
        try {
            myAnnotationSessionLock = this.myAnnotationSessionLock;
            if (myAnnotationSessionLock == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "getAnnotationSessionLock"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        return myAnnotationSessionLock;
    }
    
    @Override
    public void markIncludedFrom(final OCFile ocFile) {
        if (ocFile != null) {
            synchronized (this.myIncludedFrom) {
                this.myIncludedFrom.add(ocFile);
            }
        }
    }
    
    @Override
    public Collection<OCFile> resetIncludingFiles() {
        synchronized (this.myIncludedFrom) {
            final ArrayList<OCFile> list = new ArrayList<OCFile>(this.myIncludedFrom);
            this.myIncludedFrom.clear();
            return list;
        }
    }
    
    @Override
    public Collection<OCFile> getIncludingFiles() {
        synchronized (this.myIncludedFrom) {
            return new ArrayList<OCFile>(this.myIncludedFrom);
        }
    }
    
    @Nullable
    @Override
    public OCIncludeDirective findIncludeDirective(@Nullable final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        for (final PsiElement psiElement : this.getChildren()) {
            if (psiElement instanceof OCIncludeDirective) {
                final OCIncludeDirective ocIncludeDirective = (OCIncludeDirective)psiElement;
                final PsiFile includedFile = ocIncludeDirective.getIncludedFile();
                try {
                    if (Comparing.equal((Object)virtualFile, (Object)OCElementUtil.getFilePath(includedFile))) {
                        return ocIncludeDirective;
                    }
                }
                catch (OCFileImpl.1StopException ex2) {
                    throw b(ex2);
                }
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "OCFile:" + this.getName();
    }
    
    @Override
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "accept"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        try {
            if (psiElementVisitor instanceof OCVisitor) {
                ((OCVisitor)psiElementVisitor).visitOCFile(this);
                return;
            }
        }
        catch (OCFileImpl.1StopException ex2) {
            throw b(ex2);
        }
        psiElementVisitor.visitFile((PsiFile)this);
    }
    
    @NotNull
    public TextRange getRangeWithMacros() {
        TextRange textRange;
        try {
            textRange = this.getTextRange();
            if (textRange == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFileImpl", "getRangeWithMacros"));
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        return textRange;
    }
    
    public OCFile getContainingOCFile() {
        return this;
    }
    
    public String getTextWithMacros() {
        return this.getText();
    }
    
    public boolean isEmpty() {
        for (final PsiElement psiElement : this.getChildren()) {
            Label_0049: {
                try {
                    if (psiElement instanceof OCDefineDirective) {
                        break Label_0049;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCMacroCall;
                    if (!b) {
                        return false;
                    }
                    break Label_0049;
                }
                catch (OCFileImpl.1StopException ex) {
                    throw b(ex);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCMacroCall;
                    if (!b) {
                        return false;
                    }
                }
                catch (OCFileImpl.1StopException ex2) {
                    throw b(ex2);
                }
            }
        }
        return true;
    }
    
    public long getComplexOffset() {
        return this.getTextOffset();
    }
    
    @Override
    public boolean hasExtraTopLevelDefinitions() {
        final OCSymbol sameNamedClass = this.getSameNamedClass();
        try {
            if (sameNamedClass == null) {
                return false;
            }
        }
        catch (OCFileImpl.1StopException ex) {
            throw b(ex);
        }
        for (final PsiElement psiElement : this.getChildren()) {
            Label_0073: {
                try {
                    if (!(psiElement instanceof OCClassDeclaration)) {
                        break Label_0073;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement2;
                    final OCClassSymbol ocClassSymbol = ocClassDeclaration.getSymbol();
                    final OCClassSymbol ocClassSymbol2 = (OCClassSymbol)sameNamedClass;
                    if (ocClassSymbol != ocClassSymbol2) {
                        return true;
                    }
                    break Label_0073;
                }
                catch (OCFileImpl.1StopException ex2) {
                    throw b(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement2;
                    final OCClassSymbol ocClassSymbol = ocClassDeclaration.getSymbol();
                    final OCClassSymbol ocClassSymbol2 = (OCClassSymbol)sameNamedClass;
                    if (ocClassSymbol != ocClassSymbol2) {
                        return true;
                    }
                }
                catch (OCFileImpl.1StopException ex3) {
                    throw b(ex3);
                }
            }
            Label_0129: {
                if (psiElement instanceof OCDeclaration) {
                    final OCType type = ((OCDeclaration)psiElement).getType();
                    try {
                        if (!(type instanceof OCStructType)) {
                            return true;
                        }
                        final OCStructType ocStructType = (OCStructType)type;
                        final OCStructType ocStructType2 = ocStructType;
                        final OCStructSymbol ocStructSymbol = ocStructType2.getSymbol();
                        final OCClassSymbol ocClassSymbol3 = (OCClassSymbol)sameNamedClass;
                        final boolean b = ocStructSymbol.equals(ocClassSymbol3);
                        if (!b) {
                            return true;
                        }
                        break Label_0129;
                    }
                    catch (OCFileImpl.1StopException ex4) {
                        throw b(ex4);
                    }
                    try {
                        final OCStructType ocStructType = (OCStructType)type;
                        final OCStructType ocStructType2 = ocStructType;
                        final OCStructSymbol ocStructSymbol = ocStructType2.getSymbol();
                        final OCClassSymbol ocClassSymbol3 = (OCClassSymbol)sameNamedClass;
                        final boolean b = ocStructSymbol.equals(ocClassSymbol3);
                        if (!b) {
                            return true;
                        }
                    }
                    catch (OCFileImpl.1StopException ex5) {
                        throw b(ex5);
                    }
                }
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCResolveLanguageAndConfiguration getParsedResolveLanguageAndConfiguration() {
        return this.myParsedResolveLanguageAndConfiguration;
    }
    
    public void setParsedResolveLanguageAndConfiguration(@Nullable final OCResolveLanguageAndConfiguration myParsedResolveLanguageAndConfiguration) {
        this.myParsedResolveLanguageAndConfiguration = myParsedResolveLanguageAndConfiguration;
    }
    
    private static OCFileImpl.1StopException b(final OCFileImpl.1StopException ex) {
        return ex;
    }
}
