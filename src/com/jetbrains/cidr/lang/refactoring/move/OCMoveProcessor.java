// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.psi.OCQualifiedDesignator;
import com.jetbrains.cidr.lang.psi.OCExternalReference;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.refactoring.util.OCNormalizeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.intellij.util.containers.hash.HashMap;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.openapi.command.WriteCommandAction;
import javax.swing.Icon;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.OCImportsOptimizer;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import java.util.ArrayList;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.psi.PsiReference;
import com.intellij.util.Processor;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.search.OCMethodReferencesSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.OCUsageViewDescriptor;
import com.intellij.usageView.UsageViewDescriptor;
import org.jetbrains.annotations.NotNull;
import com.intellij.usageView.UsageInfo;
import com.intellij.openapi.project.Project;
import java.util.HashSet;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.jetbrains.cidr.lang.refactoring.util.OCElementsMover;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.intellij.refactoring.BaseRefactoringProcessor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;

public abstract class OCMoveProcessor<C extends PsiElement, S extends OCSymbol, T extends OCTargetClass<C, S>> extends BaseRefactoringProcessor
{
    protected C mySourceClass;
    protected S mySourceClassSymbol;
    protected List<T> myTargetClasses;
    protected String myTargetClassName;
    protected List<OCMemberInfo> mySelectedMemberInfos;
    protected List<Member> myMembers;
    protected OCFile mySourceFile;
    private Set<VirtualFile> myAllFiles;
    private Set<VirtualFile> mySourceFiles;
    protected Set<VirtualFile> myTargetFiles;
    protected OCElementsMover myMover;
    private Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> myElemsToEscalateVisibility;
    protected MultiMap<PsiElement, String> myConflicts;
    private Boolean myProcessStructMembers;
    private boolean myImportsHandlingSkipped;
    
    protected OCMoveProcessor(final C p0, final List<OCMemberInfo> p1, @Nullable final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //     7: invokespecial   com/intellij/refactoring/BaseRefactoringProcessor.<init>:(Lcom/intellij/openapi/project/Project;)V
        //    10: aload_0        
        //    11: new             Ljava/util/HashSet;
        //    14: dup            
        //    15: invokespecial   java/util/HashSet.<init>:()V
        //    18: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myAllFiles:Ljava/util/Set;
        //    21: aload_0        
        //    22: new             Ljava/util/HashSet;
        //    25: dup            
        //    26: invokespecial   java/util/HashSet.<init>:()V
        //    29: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceFiles:Ljava/util/Set;
        //    32: aload_0        
        //    33: new             Ljava/util/HashSet;
        //    36: dup            
        //    37: invokespecial   java/util/HashSet.<init>:()V
        //    40: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetFiles:Ljava/util/Set;
        //    43: aload_0        
        //    44: new             Lcom/jetbrains/cidr/lang/refactoring/util/OCElementsMover;
        //    47: dup            
        //    48: iconst_0       
        //    49: invokespecial   com/jetbrains/cidr/lang/refactoring/util/OCElementsMover.<init>:(Z)V
        //    52: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myMover:Lcom/jetbrains/cidr/lang/refactoring/util/OCElementsMover;
        //    55: aload_0        
        //    56: aload_1        
        //    57: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClass:Lcom/intellij/psi/PsiElement;
        //    60: aload_0        
        //    61: aload_0        
        //    62: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClass:Lcom/intellij/psi/PsiElement;
        //    65: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    68: ifeq            86
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClass:Lcom/intellij/psi/PsiElement;
        //    75: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    78: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    83: goto            87
        //    86: aconst_null    
        //    87: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    90: aload_0        
        //    91: aload_2        
        //    92: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySelectedMemberInfos:Ljava/util/List;
        //    95: aload_0        
        //    96: new             Ljava/util/ArrayList;
        //    99: dup            
        //   100: invokespecial   java/util/ArrayList.<init>:()V
        //   103: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetClasses:Ljava/util/List;
        //   106: aload_0        
        //   107: aload_3        
        //   108: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetClassName:Ljava/lang/String;
        //   111: getstatic       com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.$assertionsDisabled:Z
        //   114: ifne            157
        //   117: aload_1        
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   121: ifne            157
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_0        
        //   132: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   135: ifnonnull       157
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: new             Ljava/lang/AssertionError;
        //   148: dup            
        //   149: invokespecial   java/lang/AssertionError.<init>:()V
        //   152: athrow         
        //   153: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload_0        
        //   158: aload_0        
        //   159: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClass:Lcom/intellij/psi/PsiElement;
        //   162: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   167: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   170: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   173: aload_0        
        //   174: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceFiles:Ljava/util/Set;
        //   177: aload_0        
        //   178: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   181: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   186: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   191: pop            
        //   192: aload_0        
        //   193: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:()Ljava/util/List;
        //   196: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   201: astore          4
        //   203: aload           4
        //   205: invokeinterface java/util/Iterator.hasNext:()Z
        //   210: ifeq            269
        //   213: aload           4
        //   215: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   220: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member;
        //   223: astore          5
        //   225: aload           5
        //   227: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member.getElement:()Lcom/intellij/psi/PsiElement;
        //   230: astore          6
        //   232: aload           6
        //   234: ifnull          266
        //   237: aload_0        
        //   238: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceFiles:Ljava/util/Set;
        //   241: aload           6
        //   243: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   248: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   253: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   258: pop            
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: goto            203
        //   269: return         
        //    Signature:
        //  (TC;Ljava/util/List<Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;>;Ljava/lang/String;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  87     124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    153    153    157    Ljava/lang/IllegalArgumentException;
        //  232    259    262    266    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public OCMoveProcessor(final C c, final List<OCMemberInfo> list, @Nullable final String s, final Collection<S> collection) {
        this(c, list, s);
        for (final OCSymbol ocSymbol : collection) {
            final OCFile containingOCFile = ocSymbol.getContainingOCFile();
            OCMoveProcessor ocMoveProcessor = null;
            OCFile ocFile = null;
            String s2 = null;
            Label_0091: {
                Label_0070: {
                    try {
                        if (containingOCFile == null) {
                            continue;
                        }
                        ocMoveProcessor = this;
                        ocFile = containingOCFile;
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = ocSymbol2 instanceof OCClassSymbol;
                        if (b) {
                            break Label_0070;
                        }
                        break Label_0070;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        ocMoveProcessor = this;
                        ocFile = containingOCFile;
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = ocSymbol2 instanceof OCClassSymbol;
                        if (b) {
                            s2 = ocSymbol.getPresentableName();
                            break Label_0091;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                s2 = ocSymbol.getName();
            }
            ocMoveProcessor.addTarget(ocFile, s2, (S)ocSymbol);
        }
    }
    
    protected void addTarget(final OCFile ocFile, final String s, @Nullable final S n) {
        final HashSet<VirtualFile> set = new HashSet<VirtualFile>();
        set.add(ocFile.getVirtualFile());
        final OCFile associatedFile = ocFile.getAssociatedFile();
        try {
            if (associatedFile != null) {
                set.add(associatedFile.getVirtualFile());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myTargetClasses.add(this.createTargetClass(this.mySourceFile, ocFile, s, set, n, this.mySourceClassSymbol, this.myProject));
        this.myTargetFiles.addAll(set);
    }
    
    protected abstract T createTargetClass(final OCFile p0, final OCFile p1, final String p2, final Set<VirtualFile> p3, @Nullable final S p4, final S p5, final Project p6);
    
    @NotNull
    @Override
    protected UsageViewDescriptor createUsageViewDescriptor(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Object o = null;
        Label_0070: {
            try {
                if (this.mySourceClass != null) {
                    o = this.mySourceClass;
                    break Label_0070;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            o = this.mySourceFile;
        }
        final OCUsageViewDescriptor ocUsageViewDescriptor = new OCUsageViewDescriptor((PsiElement)o, this.getCommandName());
        if (ocUsageViewDescriptor == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor", "createUsageViewDescriptor"));
        }
        return (UsageViewDescriptor)ocUsageViewDescriptor;
    }
    
    @NotNull
    @Override
    protected UsageInfo[] findUsages() {
        final HashSet set = new HashSet();
        final List<Member> a = this.a();
        final HashSet<OCSymbol> set2 = new HashSet<OCSymbol>();
        final Iterator<Member> iterator = a.iterator();
        while (iterator.hasNext()) {
            set2.add(iterator.next().getSymbol());
        }
        for (final Member member : a) {
            final OCSymbol symbol = member.getSymbol();
            if (!member.isInnerClassMember() && !(symbol instanceof OCClassSymbol)) {
                Label_0149: {
                    try {
                        if (!(symbol instanceof OCStructSymbol)) {
                            break Label_0149;
                        }
                        final OCSymbol ocSymbol = symbol;
                        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                        final OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName = ocStructSymbol.getParent();
                        final OCMoveProcessor ocMoveProcessor = this;
                        final OCSymbol ocSymbol2 = ocMoveProcessor.mySourceClassSymbol;
                        if (ocSymbolWithQualifiedName != ocSymbol2) {
                            break Label_0149;
                        }
                        break Label_0149;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCSymbol ocSymbol = symbol;
                        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                        final OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName = ocStructSymbol.getParent();
                        final OCMoveProcessor ocMoveProcessor = this;
                        final OCSymbol ocSymbol2 = ocMoveProcessor.mySourceClassSymbol;
                        if (ocSymbolWithQualifiedName != ocSymbol2) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                final PsiElement locateDefinition = symbol.locateDefinition();
                try {
                    if (locateDefinition == null) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                final Processor processor = psiReference -> {
                    final PsiElement element = psiReference.getElement();
                    Label_0140: {
                        Label_0048: {
                            try {
                                if (!this.mySourceFiles.contains(element.getContainingFile().getVirtualFile())) {
                                    break Label_0140;
                                }
                                final PsiReference psiReference2 = psiReference;
                                final boolean b = psiReference2 instanceof OCExternalReference;
                                if (!b) {
                                    break Label_0048;
                                }
                                break Label_0140;
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                final PsiReference psiReference2 = psiReference;
                                final boolean b = psiReference2 instanceof OCExternalReference;
                                if (b) {
                                    break Label_0140;
                                }
                                if (element instanceof OCQualifiedDesignator) {
                                    break Label_0140;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                        }
                        for (final Member member : a) {
                            PsiElement psiElement = member.getElement();
                            if (member.getSymbol() instanceof OCFunctionSymbol) {
                                psiElement = psiElement.getParent();
                            }
                            try {
                                if (PsiTreeUtil.isAncestor(psiElement, element, true)) {
                                    return true;
                                }
                                continue;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                    }
                    set.add(new MoveUsage(psiReference, symbol));
                    return true;
                };
                if (symbol instanceof OCMethodSymbol) {
                    new OCMethodReferencesSearch().execute(new ReferencesSearch.SearchParameters(locateDefinition, (SearchScope)OCSearchScope.getProjectSourcesScope(this.myProject), false), (Processor<PsiReference>)processor);
                }
                else {
                    ReferencesSearch.search(locateDefinition).forEach(processor);
                }
                symbol.processSameSymbols(ocSymbol -> {
                    final PsiElement locateDefinition = ocSymbol.locateDefinition();
                    if (locateDefinition instanceof OCDeclarator) {
                        final OCCppNamespaceQualifier namespaceQualifier = ((OCDeclarator)locateDefinition).getNamespaceQualifier();
                        if (namespaceQualifier != null) {
                            final PsiReference reference = namespaceQualifier.getReference();
                            Label_0068: {
                                try {
                                    if (reference == null) {
                                        return true;
                                    }
                                    final PsiReference psiReference = reference;
                                    final OCMoveProcessor ocMoveProcessor = this;
                                    final PsiElement psiElement = ocMoveProcessor.mySourceClass;
                                    final boolean b = psiReference.isReferenceTo(psiElement);
                                    if (b) {
                                        break Label_0068;
                                    }
                                    return true;
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                try {
                                    final PsiReference psiReference = reference;
                                    final OCMoveProcessor ocMoveProcessor = this;
                                    final PsiElement psiElement = ocMoveProcessor.mySourceClass;
                                    final boolean b = psiReference.isReferenceTo(psiElement);
                                    if (b) {
                                        set.add(new QualifierRebindUsage(reference, ocSymbol, set2.contains(ocSymbol)));
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                            }
                        }
                    }
                    return true;
                });
            }
        }
        UsageInfo[] array;
        try {
            array = (UsageInfo[])set.toArray(new UsageInfo[set.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor", "findUsages"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return array;
    }
    
    @Override
    protected void performRefactoring(@NotNull final UsageInfo[] p0) {
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
        //    18: ldc             "usages"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performRefactoring"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.b:()Z
        //    48: ifne            56
        //    51: return         
        //    52: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_0        
        //    57: aload_1        
        //    58: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.d:([Lcom/intellij/usageView/UsageInfo;)V
        //    61: aload_0        
        //    62: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.locateTargetClasses:()V
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetClasses:Ljava/util/List;
        //    69: invokeinterface java/util/List.size:()I
        //    74: iconst_1       
        //    75: if_icmpne       105
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetClasses:Ljava/util/List;
        //    82: iconst_0       
        //    83: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    88: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCTargetClass;
        //    91: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCTargetClass.getPsi:()Lcom/intellij/psi/PsiElement;
        //    94: invokestatic    com/intellij/ide/util/EditorHelper.openInEditor:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/Editor;
        //    97: pop            
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_0        
        //   106: aload_1        
        //   107: invokedynamic   run:(Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor;[Lcom/intellij/usageView/UsageInfo;)Ljava/lang/Runnable;
        //   112: astore_2       
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   117: invokestatic    com/intellij/openapi/project/DumbService.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/project/DumbService;
        //   120: astore_3       
        //   121: aload_3        
        //   122: invokevirtual   com/intellij/openapi/project/DumbService.isDumb:()Z
        //   125: ifeq            146
        //   128: aload_3        
        //   129: aload_0        
        //   130: aload_2        
        //   131: invokedynamic   run:(Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor;Ljava/lang/Runnable;)Ljava/lang/Runnable;
        //   136: invokevirtual   com/intellij/openapi/project/DumbService.smartInvokeLater:(Ljava/lang/Runnable;)V
        //   139: goto            152
        //   142: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload_2        
        //   147: invokeinterface java/lang/Runnable.run:()V
        //   152: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     52     52     56     Ljava/lang/IllegalArgumentException;
        //  56     98     101    105    Ljava/lang/IllegalArgumentException;
        //  121    142    142    146    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    protected abstract void removeMember(@Nullable final PsiElement p0, final OCSymbol p1);
    
    protected void locateTargetClasses() {
        final Iterator<T> iterator = this.myTargetClasses.iterator();
        while (iterator.hasNext()) {
            iterator.next().locateTargetClass();
        }
    }
    
    @Override
    protected void performPsiSpoilingRefactoring() {
        OCBindUtil.escalateVisibilities(this.myProject, this.myElemsToEscalateVisibility, (VirtualFile[])this.myAllFiles.toArray(new VirtualFile[this.myAllFiles.size()]));
    }
    
    protected void addMissingImports(final MultiMap<T, PsiElement> multiMap) {
        final Iterator<PsiElement> iterator = multiMap.values().iterator();
        while (iterator.hasNext()) {
            OCImportSymbolFix.fixAllSymbolsRecursively(iterator.next());
        }
    }
    
    private void a(final UsageInfo[] array, final MultiMap<OCSymbol, OCSymbolDeclarator> multiMap, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        for (final UsageInfo usageInfo : array) {
            if (usageInfo instanceof MoveUsage) {
                final OCSymbol formerSymbol = ((QualifierRebindUsage)usageInfo).getFormerSymbol();
                final Collection value = multiMap.get((Object)formerSymbol);
                Object o = null;
                Label_0085: {
                    try {
                        if (value.size() == 1) {
                            o = value.iterator().next();
                            break Label_0085;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    o = null;
                }
                Object o2 = o;
                OCSymbolWithParent<?, OCSymbol> symbol = null;
                Label_0107: {
                    try {
                        if (o2 != null) {
                            symbol = ((OCSymbolDeclarator<OCSymbolWithParent<T, OCSymbol>>)o2).getSymbol();
                            break Label_0107;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    symbol = null;
                }
                OCSymbolWithParent<?, OCSymbol> ocSymbolWithParent = symbol;
                final OCSymbolWithParent<?, OCSymbol> symbol2 = ((OCTargetClass<C, OCSymbolWithParent<?, OCSymbol>>)this.myTargetClasses.get(0)).getSymbol();
                boolean b = false;
                Label_0271: {
                    Label_0262: {
                        Label_0232: {
                            Label_0163: {
                                try {
                                    if (!(usageInfo instanceof QualifierRebindUsage)) {
                                        break Label_0163;
                                    }
                                    if (((QualifierRebindUsage)usageInfo).isMoved()) {
                                        break Label_0232;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                ocSymbolWithParent = symbol2;
                                o2 = null;
                                break Label_0232;
                                try {
                                    if (ocSymbolWithParent == null || !formerSymbol.isDefinition()) {
                                        break Label_0232;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            final OCSymbol<PsiElement> associatedSymbol = ocSymbolWithParent.getAssociatedSymbol();
                            try {
                                if (associatedSymbol == null || !associatedSymbol.isPredeclaration()) {
                                    break Label_0232;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                            ocSymbolWithParent = (OCSymbolWithParent<?, OCSymbol>)associatedSymbol;
                            o2 = associatedSymbol.locateDefinition();
                            try {
                                if (!(ocSymbolWithParent instanceof OCSymbolWithParent)) {
                                    break Label_0262;
                                }
                                final OCSymbolWithParent<?, OCSymbol> ocSymbolWithParent2 = ocSymbolWithParent;
                                final OCSymbolWithParent<?, OCSymbol> ocSymbolWithParent3 = ocSymbolWithParent2;
                                final OCSymbol<PsiElement> ocSymbol = ocSymbolWithParent3.getParent();
                                final Object o3 = symbol2;
                                if (ocSymbol == o3) {
                                    break Label_0262;
                                }
                                break Label_0262;
                            }
                            catch (IllegalArgumentException ex6) {
                                throw a(ex6);
                            }
                        }
                        try {
                            final OCSymbolWithParent<?, OCSymbol> ocSymbolWithParent2 = ocSymbolWithParent;
                            final OCSymbolWithParent<?, OCSymbol> ocSymbolWithParent3 = ocSymbolWithParent2;
                            final OCSymbol<PsiElement> ocSymbol = ocSymbolWithParent3.getParent();
                            final Object o3 = symbol2;
                            if (ocSymbol == o3) {
                                b = true;
                                break Label_0271;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                    b = false;
                }
                OCBindUtil.bindReferenceAndMakeVisible(usageInfo.getReference(), ocSymbolWithParent, (PsiElement)o2, map, b);
            }
        }
    }
    
    protected void addSuperClasses() {
    }
    
    public OCFile[] getCreatedFiles() {
        final HashSet set = new HashSet((Collection<? extends E>)this.myTargetFiles);
        set.removeAll(this.mySourceFiles);
        return ContainerUtil.map((Collection)set, virtualFile -> (OCFile)PsiManager.getInstance(this.myProject).findFile(virtualFile)).toArray(new OCFile[set.size()]);
    }
    
    @NotNull
    @Override
    protected Collection<? extends PsiElement> getElementsToWrite(@NotNull final UsageViewDescriptor usageViewDescriptor) {
        try {
            if (usageViewDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor", "getElementsToWrite"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final HashSet<OCFile> set = (HashSet<OCFile>)new HashSet<PsiElement>();
        set.add(this.mySourceFile);
        final Iterator<VirtualFile> iterator = this.myTargetFiles.iterator();
        while (iterator.hasNext()) {
            final PsiFile file = PsiManager.getInstance(this.myProject).findFile((VirtualFile)iterator.next());
            try {
                if (file == null) {
                    continue;
                }
                set.add((OCFile)file);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final Iterator<Member> iterator2 = this.a().iterator();
        while (iterator2.hasNext()) {
            final PsiElement element = iterator2.next().getElement();
            try {
                if (element == null) {
                    continue;
                }
                set.add((OCFile)element);
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        HashSet<OCFile> set2;
        try {
            set2 = set;
            if (set2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor", "getElementsToWrite"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (Collection<? extends PsiElement>)set2;
    }
    
    private List<Member> a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myMembers:Ljava/util/List;
        //     4: ifnull          16
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myMembers:Ljava/util/List;
        //    11: areturn        
        //    12: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: new             Ljava/util/ArrayList;
        //    19: dup            
        //    20: invokespecial   java/util/ArrayList.<init>:()V
        //    23: astore_1       
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySelectedMemberInfos:Ljava/util/List;
        //    28: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    33: astore_2       
        //    34: aload_2        
        //    35: invokeinterface java/util/Iterator.hasNext:()Z
        //    40: ifeq            198
        //    43: aload_2        
        //    44: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    49: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;
        //    52: astore_3       
        //    53: aload_3        
        //    54: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    57: astore          4
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    63: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    66: ifeq            109
        //    69: aload           4
        //    71: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //    74: ifeq            109
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_1        
        //    85: new             Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member;
        //    88: dup            
        //    89: aload           4
        //    91: aconst_null    
        //    92: aload_3        
        //    93: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;)V
        //    96: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   101: pop            
        //   102: goto            195
        //   105: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   113: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   116: ifeq            181
        //   119: aload           4
        //   121: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   124: ifeq            181
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload           4
        //   136: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   146: if_acmpeq       181
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_1        
        //   157: new             Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member;
        //   160: dup            
        //   161: aload           4
        //   163: aconst_null    
        //   164: aload_3        
        //   165: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;)V
        //   168: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   173: pop            
        //   174: goto            195
        //   177: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_1        
        //   182: aload_0        
        //   183: aload_3        
        //   184: aload           4
        //   186: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.getAssociatedSymbols:(Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/util/List;
        //   189: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   194: pop            
        //   195: goto            34
        //   198: aload_0        
        //   199: aload_1        
        //   200: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myMembers:Ljava/util/List;
        //   203: aload_1        
        //   204: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$Member;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      12     12     16     Ljava/lang/IllegalArgumentException;
        //  59     77     80     84     Ljava/lang/IllegalArgumentException;
        //  69     105    105    109    Ljava/lang/IllegalArgumentException;
        //  109    127    130    134    Ljava/lang/IllegalArgumentException;
        //  119    149    152    156    Ljava/lang/IllegalArgumentException;
        //  134    177    177    181    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0134:
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
    
    protected List<Member> getAssociatedSymbols(final OCMemberInfo ocMemberInfo, final OCSymbol<?> ocSymbol) {
        final HashSet<Object> set = new HashSet<Object>();
        final VirtualFile virtualFile = this.mySourceFile.getVirtualFile();
        final OCFile associatedFile = this.mySourceFile.getAssociatedFile();
        VirtualFile virtualFile2 = null;
        Label_0050: {
            try {
                if (associatedFile != null) {
                    virtualFile2 = associatedFile.getVirtualFile();
                    break Label_0050;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            virtualFile2 = null;
        }
        final VirtualFile virtualFile3 = virtualFile2;
        try {
            ocSymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(p5 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload           6
                //     2: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
                //     5: ifeq            64
                //     8: aload           6
                //    10: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
                //    13: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    18: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //    21: ifeq            64
                //    24: goto            31
                //    27: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    30: athrow         
                //    31: aload           6
                //    33: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
                //    36: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    41: aload_0        
                //    42: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    45: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //    48: ifne            64
                //    51: goto            58
                //    54: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    57: athrow         
                //    58: iconst_1       
                //    59: ireturn        
                //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    63: athrow         
                //    64: aload           6
                //    66: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                //    69: ifeq            99
                //    72: aload           6
                //    74: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                //    77: aload_1        
                //    78: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSameCategory:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
                //    83: ifne            99
                //    86: goto            93
                //    89: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    92: athrow         
                //    93: iconst_1       
                //    94: ireturn        
                //    95: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    98: athrow         
                //    99: aload           6
                //   101: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDefinition:()Z
                //   106: ifne            173
                //   109: aload_2        
                //   110: ifnull          141
                //   113: goto            120
                //   116: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   119: athrow         
                //   120: aload_2        
                //   121: aload           6
                //   123: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //   128: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //   131: ifne            173
                //   134: goto            141
                //   137: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   140: athrow         
                //   141: aload_3        
                //   142: ifnull          189
                //   145: goto            152
                //   148: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   151: athrow         
                //   152: aload_3        
                //   153: aload           6
                //   155: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //   160: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //   163: ifeq            189
                //   166: goto            173
                //   169: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   172: athrow         
                //   173: aload           4
                //   175: aload           6
                //   177: aload           5
                //   179: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.addAssociatedMember:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;)V
                //   182: goto            189
                //   185: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   188: athrow         
                //   189: iconst_1       
                //   190: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      24     27     31     Ljava/lang/IllegalArgumentException;
                //  8      51     54     58     Ljava/lang/IllegalArgumentException;
                //  31     60     60     64     Ljava/lang/IllegalArgumentException;
                //  64     86     89     93     Ljava/lang/IllegalArgumentException;
                //  72     95     95     99     Ljava/lang/IllegalArgumentException;
                //  99     113    116    120    Ljava/lang/IllegalArgumentException;
                //  109    134    137    141    Ljava/lang/IllegalArgumentException;
                //  120    145    148    152    Ljava/lang/IllegalArgumentException;
                //  141    166    169    173    Ljava/lang/IllegalArgumentException;
                //  152    182    185    189    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
            }));
            if (ocSymbol instanceof OCStructSymbol) {
                ((OCStructSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)(ocSymbol -> {
                    final List<Member> associatedSymbols = this.getAssociatedSymbols(ocMemberInfo, ocSymbol);
                    Label_0089: {
                        try {
                            if (associatedSymbols.isEmpty()) {
                                return true;
                            }
                            if (this.myProcessStructMembers != null) {
                                break Label_0089;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        final String s = "Do you want to move the class member definitions?";
                        boolean b = false;
                        Label_0083: {
                            Label_0074: {
                                try {
                                    if (ApplicationManager.getApplication().isUnitTestMode()) {
                                        break Label_0074;
                                    }
                                    final OCMoveProcessor ocMoveProcessor = this;
                                    final Project project = ocMoveProcessor.myProject;
                                    final String s2 = s;
                                    final OCMoveProcessor ocMoveProcessor2 = this;
                                    final String s3 = ocMoveProcessor2.getCommandName();
                                    final Icon icon = Messages.getQuestionIcon();
                                    final int n = Messages.showYesNoDialog(project, s2, s3, icon);
                                    if (n == 0) {
                                        break Label_0074;
                                    }
                                    break Label_0074;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    final OCMoveProcessor ocMoveProcessor = this;
                                    final Project project = ocMoveProcessor.myProject;
                                    final String s2 = s;
                                    final OCMoveProcessor ocMoveProcessor2 = this;
                                    final String s3 = ocMoveProcessor2.getCommandName();
                                    final Icon icon = Messages.getQuestionIcon();
                                    final int n = Messages.showYesNoDialog(project, s2, s3, icon);
                                    if (n == 0) {
                                        b = true;
                                        break Label_0083;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            b = false;
                        }
                        this.myProcessStructMembers = b;
                    }
                    if (this.myProcessStructMembers) {
                        for (final Member member : associatedSymbols) {
                            member.setInnerClassMember(true);
                            set.add(member);
                        }
                    }
                    return true;
                }));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList list = new ArrayList<Object>(set);
        Collections.sort((List<E>)list, (member, member2) -> OCSymbolOffsetUtil.compare(member.getSymbol().getComplexOffset(), member2.getSymbol().getComplexOffset()));
        return (List<Member>)list;
    }
    
    protected static void addAssociatedMember(final Collection<Member> collection, final OCSymbol ocSymbol, final OCMemberInfo ocMemberInfo) {
        PsiElement locateDefinition = null;
        Label_0018: {
            try {
                if (ocSymbol != null) {
                    locateDefinition = ocSymbol.locateDefinition();
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            locateDefinition = null;
        }
        final PsiElement psiElement = locateDefinition;
        Label_0039: {
            try {
                if (psiElement == null) {
                    return;
                }
                final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                final boolean b = ocSymbol2.isSynthetic();
                if (!b) {
                    break Label_0039;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                final boolean b = ocSymbol2.isSynthetic();
                if (!b) {
                    collection.add(new Member(ocSymbol, psiElement, ocMemberInfo));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private boolean b() {
        final OCNewFileActionBase newClassAction = this.getNewClassAction();
        Label_0034: {
            try {
                if (newClassAction == null) {
                    return true;
                }
                final OCMoveProcessor ocMoveProcessor = this;
                final List<T> list = ocMoveProcessor.myTargetClasses;
                final boolean b = list.isEmpty();
                if (!b) {
                    return true;
                }
                break Label_0034;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCMoveProcessor ocMoveProcessor = this;
                final List<T> list = ocMoveProcessor.myTargetClasses;
                final boolean b = list.isEmpty();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        OCFile mySourceFile = this.mySourceFile;
        if (mySourceFile.isHeader()) {
            final OCFile associatedFile = mySourceFile.getAssociatedFile();
            OCFile ocFile = null;
            Label_0068: {
                try {
                    if (associatedFile != null) {
                        ocFile = associatedFile;
                        break Label_0068;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                ocFile = mySourceFile;
            }
            mySourceFile = ocFile;
        }
        final PsiFile[] performActionWithoutDialog = newClassAction.performActionWithoutDialog(this.myTargetClassName, (PsiFile)mySourceFile, true);
        try {
            if (performActionWithoutDialog == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        this.myTargetFiles = new HashSet<VirtualFile>();
        OCFile ocFile2 = null;
        for (final PsiFile psiFile : performActionWithoutDialog) {
            final VirtualFile virtualFile = psiFile.getVirtualFile();
            Label_0185: {
                try {
                    this.myTargetFiles.add(virtualFile);
                    if (!(psiFile instanceof OCFile) || !((OCFile)psiFile).isHeader()) {
                        break Label_0185;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                ocFile2 = (OCFile)psiFile;
            }
        }
        this.myTargetClasses.add(this.createTargetClass(this.mySourceFile, ocFile2, this.myTargetClassName, new HashSet<VirtualFile>(this.myTargetFiles), null, this.mySourceClassSymbol, this.myProject));
        return true;
    }
    
    @Nullable
    protected OCNewFileActionBase getNewClassAction() {
        return null;
    }
    
    private boolean a(final Collection<VirtualFile> collection, final FileProcessor fileProcessor, final String s, final boolean b) {
        try {
            if (this.myImportsHandlingSkipped) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)false);
        final Task.Modal modal = new Task.Modal(this.myProject, s, b) {
            public void run(@NotNull final ProgressIndicator progressIndicator) {
                try {
                    if (progressIndicator == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$1", "run"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final int size = collection.size();
                int n = 0;
                for (final VirtualFile virtualFile : collection) {
                    progressIndicator.setText2(virtualFile.getPresentableUrl());
                    progressIndicator.setFraction(n++ / size);
                    final PsiFile file = PsiManager.getInstance(this.myProject).findFile(virtualFile);
                    try {
                        if (!(file instanceof OCFile)) {
                            continue;
                        }
                        fileProcessor.process(virtualFile, (OCFile)file);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                create.set((Object)true);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        try {
            if (b) {
                modal.setCancelText("Skip");
                modal.setCancelTooltipText("Skip");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0105: {
            try {
                ProgressManager.getInstance().run((Task)modal);
                if (!b) {
                    return (boolean)create.get();
                }
                final Ref ref = create;
                final Object o = ref.get();
                final Boolean b2 = (Boolean)o;
                final boolean b3 = b2;
                if (!b3) {
                    break Label_0105;
                }
                return (boolean)create.get();
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Ref ref = create;
                final Object o = ref.get();
                final Boolean b2 = (Boolean)o;
                final boolean b3 = b2;
                if (!b3) {
                    this.myImportsHandlingSkipped = true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return (boolean)create.get();
    }
    
    private void d() {
        final PsiManager instance = PsiManager.getInstance(this.myProject);
        final MultiMap multiMap = new MultiMap();
        final MultiMap set = MultiMap.createSet();
        final MultiMap multiMap2 = new MultiMap();
        for (final Member member : this.a()) {
            for (final OCTargetClass<C, S> ocTargetClass : this.myTargetClasses) {
                final PsiElement element = member.getElement();
                if (element != null) {
                    final VirtualFile targetFile = ocTargetClass.getTargetFile(element, member.getSymbol());
                    try {
                        if (targetFile == null) {
                            continue;
                        }
                        multiMap.putValue((Object)targetFile, (Object)element);
                        set.putValue((Object)targetFile, (Object)element.getContainingFile());
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                }
                else {
                    final VirtualFile virtualFile2 = ocTargetClass.getPsi().getContainingFile().getVirtualFile();
                    multiMap.putValue((Object)virtualFile2, (Object)new OCSymbolHolderVirtualPsiElement(member.getSymbol()));
                    set.putValue((Object)virtualFile2, (Object)this.mySourceFile);
                }
            }
        }
        List<OCIncludeDirective> list = Collections.emptyList();
        for (final VirtualFile virtualFile3 : this.myTargetFiles) {
            StringBuilder sb = null;
            String s = null;
            Label_0295: {
                Label_0284: {
                    try {
                        if (!OCFileImpl.isHeaderFile(virtualFile3.getName())) {
                            continue;
                        }
                        sb = new StringBuilder();
                        final OCMoveProcessor ocMoveProcessor = this;
                        final OCSymbol ocSymbol = ocMoveProcessor.mySourceClassSymbol;
                        final boolean b = ocSymbol instanceof OCClassSymbol;
                        if (b) {
                            break Label_0284;
                        }
                        break Label_0284;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        sb = new StringBuilder();
                        final OCMoveProcessor ocMoveProcessor = this;
                        final OCSymbol ocSymbol = ocMoveProcessor.mySourceClassSymbol;
                        final boolean b = ocSymbol instanceof OCClassSymbol;
                        if (b) {
                            s = "#import \"";
                            break Label_0295;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                s = "#include \"";
            }
            list = Collections.singletonList((OCIncludeDirective)OCElementFactory.topLevelDeclarationFromText(sb.append(s).append(virtualFile3.getName()).append("\"").toString(), (PsiElement)this.mySourceFile));
        }
        Label_0758: {
            Label_0645: {
                StringBuilder sb2 = null;
                String s2 = null;
                Label_0509: {
                    Label_0498: {
                        Label_0446: {
                            try {
                                if (this.importTargetFromSource()) {
                                    this.a(this.mySourceFile, list, -1);
                                    break Label_0446;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            final Iterator<VirtualFile> iterator4 = this.mySourceFiles.iterator();
                            while (iterator4.hasNext()) {
                                final OCFile ocFile2 = (OCFile)instance.findFile((VirtualFile)iterator4.next());
                                Label_0427: {
                                    try {
                                        if (ocFile2 == null) {
                                            continue;
                                        }
                                        final OCFile ocFile3 = ocFile2;
                                        final boolean b2 = ocFile3.isHeader();
                                        if (!b2) {
                                            break Label_0427;
                                        }
                                        continue;
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw a(ex5);
                                    }
                                    try {
                                        final OCFile ocFile3 = ocFile2;
                                        final boolean b2 = ocFile3.isHeader();
                                        if (b2) {
                                            continue;
                                        }
                                        this.a(ocFile2, list, -1);
                                    }
                                    catch (IllegalArgumentException ex6) {
                                        throw a(ex6);
                                    }
                                }
                            }
                            try {
                                final MultiMap multiMap3;
                                final Iterator<OCFile> iterator5;
                                OCFile ocFile4;
                                final MultiMap multiMap4;
                                final MultiMap multiMap5;
                                this.a(multiMap.keySet(), (virtualFile, ocFile) -> {
                                    multiMap3.get((Object)virtualFile).iterator();
                                    while (iterator5.hasNext()) {
                                        ocFile4 = iterator5.next();
                                        multiMap4.putValues((Object)ocFile, (Collection)OCImportsOptimizer.getUsedImports(ocFile4, ContainerUtil.filter(multiMap5.get((Object)virtualFile), psiElement -> {
                                            Label_0024: {
                                                try {
                                                    if (psiElement instanceof OCSymbolHolderVirtualPsiElement) {
                                                        break Label_0024;
                                                    }
                                                    final PsiElement psiElement2 = psiElement;
                                                    final PsiFile psiFile = psiElement2.getContainingFile();
                                                    final OCFile ocFile2 = ocFile4;
                                                    if (psiFile == ocFile2) {
                                                        break Label_0024;
                                                    }
                                                    return false;
                                                }
                                                catch (IllegalArgumentException ex) {
                                                    throw a(ex);
                                                }
                                                try {
                                                    final PsiElement psiElement2 = psiElement;
                                                    final PsiFile psiFile = psiElement2.getContainingFile();
                                                    final OCFile ocFile2 = ocFile4;
                                                    if (psiFile == ocFile2) {
                                                        return true;
                                                    }
                                                }
                                                catch (IllegalArgumentException ex2) {
                                                    throw a(ex2);
                                                }
                                            }
                                            return false;
                                        })));
                                    }
                                    return;
                                }, "Adding imports", false);
                                if (!this.importSourceFromTarget()) {
                                    break Label_0645;
                                }
                                sb2 = new StringBuilder();
                                final OCMoveProcessor ocMoveProcessor2 = this;
                                final OCSymbol ocSymbol2 = ocMoveProcessor2.mySourceClassSymbol;
                                final boolean b3 = ocSymbol2 instanceof OCClassSymbol;
                                if (b3) {
                                    break Label_0498;
                                }
                                break Label_0498;
                            }
                            catch (IllegalArgumentException ex7) {
                                throw a(ex7);
                            }
                        }
                        try {
                            sb2 = new StringBuilder();
                            final OCMoveProcessor ocMoveProcessor2 = this;
                            final OCSymbol ocSymbol2 = ocMoveProcessor2.mySourceClassSymbol;
                            final boolean b3 = ocSymbol2 instanceof OCClassSymbol;
                            if (b3) {
                                s2 = "#import \"";
                                break Label_0509;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                    }
                    s2 = "#include \"";
                }
                final List<OCIncludeDirective> singletonList = Collections.singletonList((OCIncludeDirective)OCElementFactory.topLevelDeclarationFromText(sb2.append(s2).append(this.mySourceFile.getName()).append("\"").toString(), (PsiElement)this.mySourceFile));
                final Iterator<VirtualFile> iterator6 = this.myTargetFiles.iterator();
                while (iterator6.hasNext()) {
                    final OCFile ocFile5 = (OCFile)PsiManager.getInstance(this.myProject).findFile((VirtualFile)iterator6.next());
                    Label_0623: {
                        try {
                            if (ocFile5 == null) {
                                continue;
                            }
                            final OCFile ocFile6 = ocFile5;
                            final boolean b4 = ocFile6.isHeader();
                            if (b4) {
                                break Label_0623;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                        try {
                            final OCFile ocFile6 = ocFile5;
                            final boolean b4 = ocFile6.isHeader();
                            if (!b4) {
                                continue;
                            }
                            this.a(ocFile5, singletonList, -1);
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                    }
                }
                break Label_0758;
            }
            for (final OCFile ocFile7 : multiMap2.keySet()) {
                final List<OCIncludeDirective> includeDirectives = ocFile7.findIncludeDirectives();
                OCFile ocFile8 = null;
                List list2 = null;
                int textOffset = 0;
                Label_0752: {
                    Label_0728: {
                        try {
                            ocFile8 = ocFile7;
                            list2 = (List)multiMap2.get((Object)ocFile7);
                            if (ocFile7.isHeader()) {
                                break Label_0728;
                            }
                            final List<OCIncludeDirective> list3 = includeDirectives;
                            final boolean b5 = list3.isEmpty();
                            if (b5) {
                                break Label_0728;
                            }
                            break Label_0728;
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                        try {
                            final List<OCIncludeDirective> list3 = includeDirectives;
                            final boolean b5 = list3.isEmpty();
                            if (b5) {
                                textOffset = -1;
                                break Label_0752;
                            }
                        }
                        catch (IllegalArgumentException ex12) {
                            throw a(ex12);
                        }
                    }
                    textOffset = includeDirectives.get(0).getTextOffset();
                }
                this.a(ocFile8, list2, textOffset);
            }
        }
        for (final VirtualFile virtualFile4 : this.myAllFiles) {
            if (!this.mySourceFiles.contains(virtualFile4)) {
                try {
                    if (this.myTargetFiles.contains(virtualFile4)) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                final PsiFile file = instance.findFile(virtualFile4);
                try {
                    if (!(file instanceof OCFile)) {
                        continue;
                    }
                    this.a((OCFile)file, list, -1);
                }
                catch (IllegalArgumentException ex14) {
                    throw a(ex14);
                }
            }
        }
    }
    
    protected boolean importTargetFromSource() {
        return false;
    }
    
    protected boolean importSourceFromTarget() {
        return false;
    }
    
    private void a(final OCFile p0, final List<OCIncludeDirective> p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore          4
        //     9: new             Ljava/util/HashSet;
        //    12: dup            
        //    13: invokespecial   java/util/HashSet.<init>:()V
        //    16: astore          5
        //    18: aload_1        
        //    19: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.findIncludeDirectives:()Ljava/util/List;
        //    24: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    29: astore          6
        //    31: aload           6
        //    33: invokeinterface java/util/Iterator.hasNext:()Z
        //    38: ifeq            71
        //    41: aload           6
        //    43: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    48: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //    51: astore          7
        //    53: aload           5
        //    55: aload           7
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.getReferenceText:()Ljava/lang/String;
        //    62: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    67: pop            
        //    68: goto            31
        //    71: aload_2        
        //    72: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    77: astore          6
        //    79: aload           6
        //    81: invokeinterface java/util/Iterator.hasNext:()Z
        //    86: ifeq            265
        //    89: aload           6
        //    91: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    96: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //    99: astore          7
        //   101: aload           7
        //   103: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.getReferenceText:()Ljava/lang/String;
        //   108: astore          8
        //   110: aload           5
        //   112: aload           8
        //   114: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   119: ifne            252
        //   122: aload           8
        //   124: aload_1        
        //   125: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getName:()Ljava/lang/String;
        //   130: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/String;Ljava/lang/String;)Z
        //   133: ifne            252
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload_1        
        //   144: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
        //   149: ifeq            222
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetFiles:Ljava/util/Set;
        //   163: aload_1        
        //   164: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   169: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   174: ifeq            222
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: aload           8
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   190: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getName:()Ljava/lang/String;
        //   195: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/String;Ljava/lang/String;)Z
        //   198: ifeq            222
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_0        
        //   209: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.importSourceFromTarget:()Z
        //   212: ifeq            252
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload           4
        //   224: aload_1        
        //   225: aload           7
        //   227: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.getText:()Ljava/lang/String;
        //   232: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.INCLUDE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   235: iload_3        
        //   236: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.addImportToFile:(Lcom/jetbrains/cidr/lang/psi/OCFile;Ljava/lang/String;Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;I)Lcom/intellij/psi/PsiElement;
        //   239: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   244: pop            
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: aload           5
        //   254: aload           8
        //   256: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   261: pop            
        //   262: goto            79
        //   265: aload_1        
        //   266: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   271: invokestatic    com/intellij/psi/codeStyle/CodeStyleManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleManager;
        //   274: aload_1        
        //   275: aload           4
        //   277: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   282: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   285: invokevirtual   com/intellij/psi/codeStyle/CodeStyleManager.reformatText:(Lcom/intellij/psi/PsiFile;Ljava/util/Collection;)V
        //   288: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCFile;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;>;I)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  110    136    139    143    Ljava/lang/IllegalArgumentException;
        //  122    152    155    159    Ljava/lang/IllegalArgumentException;
        //  143    177    180    184    Ljava/lang/IllegalArgumentException;
        //  159    201    204    208    Ljava/lang/IllegalArgumentException;
        //  184    215    218    222    Ljava/lang/IllegalArgumentException;
        //  208    245    248    252    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    
    private static boolean a(final String s, final String s2) {
        Label_0032: {
            try {
                if (!s.endsWith(s2)) {
                    return false;
                }
                final String s3 = s;
                final int n = s3.length();
                final String s4 = s2;
                final int n2 = s4.length();
                if (n == n2) {
                    return true;
                }
                break Label_0032;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s3 = s;
                final int n = s3.length();
                final String s4 = s2;
                final int n2 = s4.length();
                if (n == n2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (!Character.isJavaIdentifierPart(s.charAt(s.length() - s2.length()))) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private MultiMap<VirtualFile, Integer> c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/intellij/util/containers/MultiMap;
        //     3: dup            
        //     4: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: aload_0        
        //    10: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myAllFiles:Ljava/util/Set;
        //    13: aload_0        
        //    14: aload_1        
        //    15: invokedynamic   process:(Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor;Lcom/intellij/util/containers/MultiMap;)Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$FileProcessor;
        //    20: ldc             "Analyzing imports"
        //    22: iconst_1       
        //    23: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$FileProcessor;Ljava/lang/String;Z)Z
        //    26: istore_2       
        //    27: iload_2        
        //    28: ifeq            39
        //    31: aload_1        
        //    32: goto            40
        //    35: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aconst_null    
        //    40: areturn        
        //    Signature:
        //  ()Lcom/intellij/util/containers/MultiMap<Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/Integer;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  27     35     35     39     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    private void a(final MultiMap<VirtualFile, Integer> multiMap) {
        final ArrayList<Object> list = new ArrayList<Object>();
        final HashSet set;
        int i;
        final List<OCIncludeDirective> list2;
        OCIncludeDirective ocIncludeDirective;
        final MultiMap multiMap2;
        final Object o;
        final Object o2;
        final Collection collection;
        final Object o3;
        final Object o4;
        final List<OCIncludeDirective> list3;
        if (this.a(this.myAllFiles, (virtualFile, ocFile) -> {
            try {
                if (this.myTargetFiles.contains(virtualFile)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            set = new HashSet(OCImportsOptimizer.getUnusedImports(ocFile));
            ocFile.findIncludeDirectives();
            while (i < list2.size()) {
                ocIncludeDirective = list2.get(i);
                Label_0107_1: {
                    try {
                        if (set.contains(ocIncludeDirective)) {
                            multiMap2.get(o);
                            (int)o2;
                            collection.contains(o3);
                            if (!o4) {
                                break Label_0107_1;
                            }
                            else {
                                break Label_0107_1;
                            }
                        }
                        else {
                            break Label_0107_1;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        multiMap2.get(o);
                        (int)o2;
                        collection.contains(o3);
                        if (!o4) {
                            list3.add(ocIncludeDirective);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                ++i;
            }
        }, "Removing redundant imports", true)) {
            final Iterator<OCIncludeDirective> iterator = list.iterator();
            while (iterator.hasNext()) {
                OCChangeUtil.delete((PsiElement)iterator.next());
            }
        }
    }
    
    private void d(final UsageInfo[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.myAllFiles.add(array[i].getFile().getVirtualFile());
        }
        this.myAllFiles.addAll(this.mySourceFiles);
        this.myAllFiles.addAll(this.myTargetFiles);
    }
    
    public void setConflicts(final MultiMap<PsiElement, String> myConflicts) {
        this.myConflicts = myConflicts;
    }
    
    @Override
    protected boolean preprocessUsages(@NotNull final Ref<UsageInfo[]> p0) {
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
        //    18: ldc             "refUsages"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "preprocessUsages"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    48: checkcast       [Lcom/intellij/usageView/UsageInfo;
        //    51: astore_2       
        //    52: new             Ljava/util/HashSet;
        //    55: dup            
        //    56: invokespecial   java/util/HashSet.<init>:()V
        //    59: astore_3       
        //    60: aload_0        
        //    61: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySelectedMemberInfos:Ljava/util/List;
        //    64: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    69: astore          4
        //    71: aload           4
        //    73: invokeinterface java/util/Iterator.hasNext:()Z
        //    78: ifeq            501
        //    81: aload           4
        //    83: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    88: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;
        //    91: astore          5
        //    93: aload           5
        //    95: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isAbstract:()Z
        //    98: ifeq            108
        //   101: goto            71
        //   104: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetClasses:Ljava/util/List;
        //   112: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   117: astore          6
        //   119: aload           6
        //   121: invokeinterface java/util/Iterator.hasNext:()Z
        //   126: ifeq            340
        //   129: aload           6
        //   131: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   136: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCTargetClass;
        //   139: astore          7
        //   141: aload           7
        //   143: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCTargetClass.getMemberStorage:()Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfoStorage;
        //   146: astore          8
        //   148: aload           8
        //   150: ifnonnull       160
        //   153: goto            119
        //   156: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload           8
        //   162: aload           7
        //   164: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCTargetClass.getPsi:()Lcom/intellij/psi/PsiElement;
        //   167: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfoStorage.getClassMemberInfos:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   170: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   175: astore          9
        //   177: aload           9
        //   179: invokeinterface java/util/Iterator.hasNext:()Z
        //   184: ifeq            337
        //   187: aload           9
        //   189: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   194: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;
        //   197: astore          10
        //   199: aload           10
        //   201: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isAbstract:()Z
        //   204: ifne            334
        //   207: aload           8
        //   209: aload           10
        //   211: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getMember:()Lcom/intellij/psi/PsiElement;
        //   214: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   217: aload           5
        //   219: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getMember:()Lcom/intellij/psi/PsiElement;
        //   222: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   225: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfoStorage.memberConflict:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;)Z
        //   228: ifeq            334
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: aload           10
        //   240: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getMember:()Lcom/intellij/psi/PsiElement;
        //   243: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   246: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   249: astore          11
        //   251: aload           11
        //   253: ifnull          270
        //   256: aload           11
        //   258: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   263: goto            271
        //   266: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: aconst_null    
        //   271: astore          12
        //   273: aload           12
        //   275: ifnull          334
        //   278: aload_0        
        //   279: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myConflicts:Lcom/intellij/util/containers/MultiMap;
        //   282: aload           12
        //   284: new             Ljava/lang/StringBuilder;
        //   287: dup            
        //   288: invokespecial   java/lang/StringBuilder.<init>:()V
        //   291: aload           10
        //   293: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getDisplayNameWithKind:()Ljava/lang/String;
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: ldc             " is already defined in the "
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: aload           7
        //   306: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCTargetClass.getDisplayName:()Ljava/lang/String;
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   315: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   318: aload_3        
        //   319: aload           5
        //   321: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   326: pop            
        //   327: goto            337
        //   330: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: goto            177
        //   337: goto            119
        //   340: new             Lcom/jetbrains/cidr/lang/refactoring/move/OCDependentMembersCollector;
        //   343: dup            
        //   344: aload_0        
        //   345: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySourceClass:Lcom/intellij/psi/PsiElement;
        //   348: aconst_null    
        //   349: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCDependentMembersCollector.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   352: astore          6
        //   354: aload           6
        //   356: aload           5
        //   358: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getMember:()Lcom/intellij/psi/PsiElement;
        //   361: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   364: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCDependentMembersCollector.collect:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;)V
        //   367: aload           6
        //   369: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCDependentMembersCollector.getDependenciesFromSameFile:()Ljava/util/Set;
        //   372: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   377: astore          7
        //   379: aload           7
        //   381: invokeinterface java/util/Iterator.hasNext:()Z
        //   386: ifeq            498
        //   389: aload           7
        //   391: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   396: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   399: astore          8
        //   401: aload           8
        //   403: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   408: astore          9
        //   410: aload           8
        //   412: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   417: astore          10
        //   419: aload           10
        //   421: ifnull          495
        //   424: aload           9
        //   426: ifnull          495
        //   429: goto            436
        //   432: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   435: athrow         
        //   436: aload           10
        //   438: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   441: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isHeaderFile:(Ljava/lang/String;)Z
        //   444: ifne            495
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: aload_0        
        //   455: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myConflicts:Lcom/intellij/util/containers/MultiMap;
        //   458: aload           9
        //   460: new             Ljava/lang/StringBuilder;
        //   463: dup            
        //   464: invokespecial   java/lang/StringBuilder.<init>:()V
        //   467: aload           8
        //   469: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   474: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: ldc             " will be inaccessible in the moved code"
        //   479: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   482: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   485: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   488: goto            495
        //   491: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   494: athrow         
        //   495: goto            379
        //   498: goto            71
        //   501: new             Ljava/util/HashSet;
        //   504: dup            
        //   505: invokespecial   java/util/HashSet.<init>:()V
        //   508: astore          4
        //   510: aload_2        
        //   511: astore          5
        //   513: aload           5
        //   515: arraylength    
        //   516: istore          6
        //   518: iconst_0       
        //   519: istore          7
        //   521: iload           7
        //   523: iload           6
        //   525: if_icmpge       839
        //   528: aload           5
        //   530: iload           7
        //   532: aaload         
        //   533: astore          8
        //   535: aload           8
        //   537: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$MoveUsage;
        //   540: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$MoveUsage.getFormerSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   543: astore          9
        //   545: aload           8
        //   547: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   550: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedDesignator;
        //   553: ifeq            624
        //   556: aload           4
        //   558: aload           9
        //   560: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   565: ifeq            833
        //   568: goto            575
        //   571: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   574: athrow         
        //   575: aload_0        
        //   576: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myConflicts:Lcom/intellij/util/containers/MultiMap;
        //   579: aload           8
        //   581: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   584: new             Ljava/lang/StringBuilder;
        //   587: dup            
        //   588: invokespecial   java/lang/StringBuilder.<init>:()V
        //   591: ldc             "Qualified designators for the "
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: aload           9
        //   598: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   603: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   606: ldc             " will become unresolved"
        //   608: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   611: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   614: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   617: goto            833
        //   620: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   623: athrow         
        //   624: aload           8
        //   626: invokevirtual   com/intellij/usageView/UsageInfo.getReference:()Lcom/intellij/psi/PsiReference;
        //   629: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExternalReference;
        //   632: ifeq            719
        //   635: aload           4
        //   637: aload           9
        //   639: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   644: ifeq            833
        //   647: goto            654
        //   650: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: aload           8
        //   656: invokevirtual   com/intellij/usageView/UsageInfo.getReference:()Lcom/intellij/psi/PsiReference;
        //   659: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExternalReference;
        //   662: invokeinterface com/jetbrains/cidr/lang/psi/OCExternalReference.getExternalComponentName:()Ljava/lang/String;
        //   667: astore          10
        //   669: aload_0        
        //   670: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myConflicts:Lcom/intellij/util/containers/MultiMap;
        //   673: aload           8
        //   675: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   678: new             Ljava/lang/StringBuilder;
        //   681: dup            
        //   682: invokespecial   java/lang/StringBuilder.<init>:()V
        //   685: aload           10
        //   687: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   690: ldc             " references for the "
        //   692: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   695: aload           9
        //   697: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   702: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   705: ldc             " may become unresolved"
        //   707: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   710: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   713: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   716: goto            833
        //   719: aload_0        
        //   720: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myTargetClasses:Ljava/util/List;
        //   723: invokeinterface java/util/List.size:()I
        //   728: iconst_1       
        //   729: if_icmple       833
        //   732: aload           8
        //   734: instanceof      Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$QualifierRebindUsage;
        //   737: ifeq            765
        //   740: goto            747
        //   743: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   746: athrow         
        //   747: aload           8
        //   749: checkcast       Lcom/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$QualifierRebindUsage;
        //   752: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$QualifierRebindUsage.isMoved:()Z
        //   755: ifne            833
        //   758: goto            765
        //   761: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   764: athrow         
        //   765: aload           4
        //   767: aload           9
        //   769: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   774: ifeq            833
        //   777: goto            784
        //   780: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   783: athrow         
        //   784: aload_0        
        //   785: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myConflicts:Lcom/intellij/util/containers/MultiMap;
        //   788: aload           8
        //   790: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   793: new             Ljava/lang/StringBuilder;
        //   796: dup            
        //   797: invokespecial   java/lang/StringBuilder.<init>:()V
        //   800: ldc             "Some references to the "
        //   802: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   805: aload           9
        //   807: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   812: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   815: ldc             " will become unresolved"
        //   817: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   820: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   823: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   826: goto            833
        //   829: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   832: athrow         
        //   833: iinc            7, 1
        //   836: goto            521
        //   839: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   842: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   847: ifeq            893
        //   850: aload_0        
        //   851: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.removeConflictingMembers:()Z
        //   854: ifeq            887
        //   857: goto            864
        //   860: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   863: athrow         
        //   864: aload_0        
        //   865: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.mySelectedMemberInfos:Ljava/util/List;
        //   868: aload_3        
        //   869: invokeinterface java/util/List.removeAll:(Ljava/util/Collection;)Z
        //   874: pop            
        //   875: aload_0        
        //   876: aconst_null    
        //   877: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myMembers:Ljava/util/List;
        //   880: goto            887
        //   883: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   886: athrow         
        //   887: aload_0        
        //   888: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.prepareSuccessful:()V
        //   891: iconst_1       
        //   892: ireturn        
        //   893: aload_0        
        //   894: aload_0        
        //   895: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.myConflicts:Lcom/intellij/util/containers/MultiMap;
        //   898: aload_2        
        //   899: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor.showConflicts:(Lcom/intellij/util/containers/MultiMap;[Lcom/intellij/usageView/UsageInfo;)Z
        //   902: ireturn        
        //    Signature:
        //  (Lcom/intellij/openapi/util/Ref<[Lcom/intellij/usageView/UsageInfo;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  93     104    104    108    Ljava/lang/IllegalArgumentException;
        //  148    156    156    160    Ljava/lang/IllegalArgumentException;
        //  199    231    234    238    Ljava/lang/IllegalArgumentException;
        //  251    266    266    270    Ljava/lang/IllegalArgumentException;
        //  273    330    330    334    Ljava/lang/IllegalArgumentException;
        //  419    429    432    436    Ljava/lang/IllegalArgumentException;
        //  424    447    450    454    Ljava/lang/IllegalArgumentException;
        //  436    488    491    495    Ljava/lang/IllegalArgumentException;
        //  545    568    571    575    Ljava/lang/IllegalArgumentException;
        //  556    620    620    624    Ljava/lang/IllegalArgumentException;
        //  624    647    650    654    Ljava/lang/IllegalArgumentException;
        //  719    740    743    747    Ljava/lang/IllegalArgumentException;
        //  732    758    761    765    Ljava/lang/IllegalArgumentException;
        //  747    777    780    784    Ljava/lang/IllegalArgumentException;
        //  765    826    829    833    Ljava/lang/IllegalArgumentException;
        //  839    857    860    864    Ljava/lang/IllegalArgumentException;
        //  850    880    883    887    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0436:
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
    
    protected boolean removeConflictingMembers() {
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCMoveProcessor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    protected static class Member
    {
        private OCSymbol mySymbol;
        private PsiElement myElement;
        private OCMemberInfo myMemberInfo;
        private boolean isInnerClassMember;
        
        public Member(final OCSymbol mySymbol, @Nullable final PsiElement myElement, final OCMemberInfo myMemberInfo) {
            this.mySymbol = mySymbol;
            this.myElement = myElement;
            this.myMemberInfo = myMemberInfo;
        }
        
        public OCSymbol getSymbol() {
            return this.mySymbol;
        }
        
        @Nullable
        public PsiElement getElement() {
            return this.myElement;
        }
        
        public OCMemberInfo getMemberInfo() {
            return this.myMemberInfo;
        }
        
        @Nullable
        public OCVisibility getVisibility() {
            return this.myMemberInfo.getVisibility();
        }
        
        public boolean isInnerClassMember() {
            return this.isInnerClassMember;
        }
        
        public void setInnerClassMember(final boolean isInnerClassMember) {
            this.isInnerClassMember = isInnerClassMember;
        }
    }
    
    private static class MoveUsage extends UsageInfo
    {
        private OCSymbol myFormerSymbol;
        
        public MoveUsage(@NotNull final PsiReference psiReference, final OCSymbol myFormerSymbol) {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$MoveUsage", "<init>"));
            }
            super(psiReference);
            this.myFormerSymbol = myFormerSymbol;
        }
        
        public OCSymbol getFormerSymbol() {
            return this.myFormerSymbol;
        }
    }
    
    private static class QualifierRebindUsage extends MoveUsage
    {
        private boolean myMoved;
        
        public QualifierRebindUsage(@NotNull final PsiReference psiReference, final OCSymbol ocSymbol, final boolean myMoved) {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifier", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$QualifierRebindUsage", "<init>"));
            }
            super(psiReference, ocSymbol);
            this.myMoved = myMoved;
        }
        
        public boolean isMoved() {
            return this.myMoved;
        }
    }
    
    @FunctionalInterface
    private interface FileProcessor
    {
        void process(@NotNull final VirtualFile p0, @NotNull final OCFile p1);
    }
}
