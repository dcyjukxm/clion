// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCMethodDotCallUsage;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCPropertyAttributeUsage;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCSelectorExpressionUsage;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCMethodCallUsage;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCExternalReferenceUsage;
import com.jetbrains.cidr.lang.psi.OCExternalReference;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.lang.Language;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCConstructorInEmptyInitializationUsage;
import com.jetbrains.cidr.lang.search.constructors.OCBaseConstructorReference;
import com.jetbrains.cidr.lang.search.constructors.OCConstructorImplicitReferenceSearch;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCFunctionUsage;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.usageView.UsageInfo;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Condition;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.FilteringIterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class OCSearchUtil
{
    @NotNull
    public static Collection<VirtualFile> getProjectVirtualFilesByName(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getProjectVirtualFilesByName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getProjectVirtualFilesByName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Collection<VirtualFile> virtualFilesByName;
        try {
            virtualFilesByName = FilenameIndex.getVirtualFilesByName(project, s, SystemInfo.isFileSystemCaseSensitive, GlobalSearchScope.projectScope(project));
            if (virtualFilesByName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getProjectVirtualFilesByName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return virtualFilesByName;
    }
    
    @NotNull
    public static List<OCFile> getProjectOCFilesByName(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getProjectOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getProjectOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<OCFile> ocFilesByName;
        try {
            ocFilesByName = getOCFilesByName(project, s, GlobalSearchScope.projectScope(project));
            if (ocFilesByName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getProjectOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return ocFilesByName;
    }
    
    @NotNull
    public static List<OCFile> getOCFilesByName(@NotNull final Project project, @NotNull final String s, @NotNull final GlobalSearchScope globalSearchScope) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (globalSearchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        List filter;
        try {
            filter = ContainerUtil.filter((Object[])FilenameIndex.getFilesByName(project, s, globalSearchScope), (Condition)new FilteringIterator.InstanceOf((Class)OCFile.class));
            if (filter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getOCFilesByName"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (List<OCFile>)filter;
    }
    
    public static <T extends OCSymbolWithParent> boolean processMembersHierarchy(final T t, final Processor<? super T> processor, final boolean b, final boolean b2) {
        return processMembersHierarchy(t, processor, b, b2, true);
    }
    
    public static <T extends OCSymbolWithParent> boolean processMembersHierarchy(final T p0, final Processor<? super T> p1, final boolean p2, final boolean p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //     4: ifeq            61
        //     7: iload_3        
        //     8: ifeq            61
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_0        
        //    19: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    22: aload_0        
        //    23: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    28: iconst_1       
        //    29: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/psi/OCFile;Z)Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;
        //    32: astore          5
        //    34: aload           5
        //    36: iload           4
        //    38: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.setIncludeSameSymbols:(Z)V
        //    41: aload           5
        //    43: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //    46: aload_1        
        //    47: invokeinterface com/intellij/util/Query.forEach:(Lcom/intellij/util/Processor;)Z
        //    52: ifne            61
        //    55: iconst_0       
        //    56: ireturn        
        //    57: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: aload_0        
        //    62: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    65: ifeq            113
        //    68: iload_2        
        //    69: ifeq            113
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: new             Lcom/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery;
        //    82: dup            
        //    83: aload_0        
        //    84: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    87: iconst_1       
        //    88: iload           4
        //    90: invokespecial   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZZ)V
        //    93: aload_1        
        //    94: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.forEach:(Lcom/intellij/util/Processor;)Z
        //    97: ifne            113
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //   109: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   117: ifeq            168
        //   120: aload_0        
        //   121: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   124: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;)Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;
        //   127: astore          5
        //   129: aload           5
        //   131: iload           4
        //   133: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setIncludeSelfImplementation:(Z)V
        //   136: aload           5
        //   138: iload_3        
        //   139: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setInheritors:(Z)V
        //   142: aload           5
        //   144: iload_2        
        //   145: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setAncestors:(Z)V
        //   148: aload           5
        //   150: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //   153: aload_1        
        //   154: invokeinterface com/intellij/util/Query.forEach:(Lcom/intellij/util/Processor;)Z
        //   159: ifne            168
        //   162: iconst_0       
        //   163: ireturn        
        //   164: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: iconst_1       
        //   169: ireturn        
        //    Signature:
        //  <T::Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;>(TT;Lcom/intellij/util/Processor<-TT;>;ZZZ)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  34     57     57     61     Ljava/lang/IllegalArgumentException;
        //  61     72     75     79     Ljava/lang/IllegalArgumentException;
        //  68     100    103    107    Ljava/lang/IllegalArgumentException;
        //  79     109    109    113    Ljava/lang/IllegalArgumentException;
        //  129    164    164    168    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
    
    public static boolean processMembersHierarchyPsi(final OCSymbolWithParent ocSymbolWithParent, final Processor<PsiElement> processor, final boolean b, final boolean b2) {
        return processMembersHierarchy(ocSymbolWithParent, (com.intellij.util.Processor<? super OCSymbolWithParent>)(ocSymbolWithParent -> {
            PsiElement psiElement = ocSymbolWithParent.locateDefinition();
            if (psiElement instanceof OCDeclarator) {
                psiElement = psiElement.getParent();
            }
            try {
                if (psiElement != null) {
                    return processor.process((Object)psiElement);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return true;
        }), b, b2);
    }
    
    @Nullable
    public static Ancestor findSomeAncestor(final OCSymbolWithParent ocSymbolWithParent) {
        final Ancestor ancestor = new Ancestor();
        try {
            processMembersHierarchy(ocSymbolWithParent, (com.intellij.util.Processor<? super OCSymbolWithParent>)(symbol -> {
                try {
                    ancestor.symbol = symbol;
                    if (!OCSearchScope.isInProjectSources(symbol)) {
                        ancestor.isOutOfProject = true;
                        return false;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return true;
            }), true, false, false);
            if (ancestor.getSymbol() != null) {
                return ancestor;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public static boolean processMemberAncestors(final OCSymbolWithParent ocSymbolWithParent, final Processor<? super OCSymbolWithParent> processor, final boolean b) {
        return processMembersHierarchy(ocSymbolWithParent, (com.intellij.util.Processor<? super OCSymbolWithParent>)(p3 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: iload_0        
            //     1: ifeq            36
            //     4: aload_3        
            //     5: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.isDefinition:()Z
            //    10: ifeq            36
            //    13: goto            20
            //    16: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    19: athrow         
            //    20: aload_3        
            //    21: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getFirstPredeclaration:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    26: ifnonnull       51
            //    29: goto            36
            //    32: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    35: athrow         
            //    36: aload_3        
            //    37: aload_1        
            //    38: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //    41: ifeq            57
            //    44: goto            51
            //    47: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    50: athrow         
            //    51: iconst_1       
            //    52: ireturn        
            //    53: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    56: athrow         
            //    57: aload_2        
            //    58: aload_3        
            //    59: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //    64: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      13     16     20     Ljava/lang/IllegalArgumentException;
            //  4      29     32     36     Ljava/lang/IllegalArgumentException;
            //  20     44     47     51     Ljava/lang/IllegalArgumentException;
            //  36     53     53     57     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
        }), true, false, true);
    }
    
    public static boolean findAllMemberUsages(final OCSymbolWithParent ocSymbolWithParent, final Set<UsageInfo> set, final boolean b, final boolean b2) {
        return processMembersHierarchyPsi(ocSymbolWithParent, (Processor<PsiElement>)(psiElement -> {
            findOneMethodUsages(psiElement, set);
            return true;
        }), b2, b);
    }
    
    public static void findOneMethodUsages(final PsiElement psiElement, final Set<UsageInfo> set) {
        final GlobalSearchScope projectSourcesScope = OCSearchScope.getProjectSourcesScope(psiElement.getProject());
        if (psiElement instanceof OCFunctionDeclaration) {
            final OCDeclarator declarator = ((OCFunctionDeclaration)psiElement).getDeclarator();
            if (declarator != null) {
                for (final PsiReference psiReference : ReferencesSearch.search((PsiElement)declarator, (SearchScope)projectSourcesScope).findAll()) {
                    Label_0108: {
                        try {
                            if (psiReference.getElement() == null) {
                                continue;
                            }
                            final OCLanguage ocLanguage = OCLanguage.getInstance();
                            final PsiReference psiReference2 = psiReference;
                            final PsiElement psiElement2 = psiReference2.getElement();
                            final Language language = psiElement2.getLanguage();
                            final boolean b = ocLanguage.equals(language);
                            if (b) {
                                break Label_0108;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCLanguage ocLanguage = OCLanguage.getInstance();
                            final PsiReference psiReference2 = psiReference;
                            final PsiElement psiElement2 = psiReference2.getElement();
                            final Language language = psiElement2.getLanguage();
                            final boolean b = ocLanguage.equals(language);
                            if (!b) {
                                continue;
                            }
                            set.add(new OCFunctionUsage(psiReference));
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                }
                final Iterator iterator2 = OCConstructorImplicitReferenceSearch.search((PsiElement)declarator, (SearchScope)projectSourcesScope).iterator();
                while (iterator2.hasNext()) {
                    set.add(new OCConstructorInEmptyInitializationUsage((OCBaseConstructorReference)iterator2.next()));
                }
            }
        }
        else if (psiElement instanceof OCMethod) {
            ReferencesSearch.search(psiElement, (SearchScope)projectSourcesScope, false).forEach(psiReference -> {
                try {
                    if (psiReference instanceof OCExternalReference) {
                        set.add(new OCExternalReferenceUsage((OCExternalReference)psiReference));
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final PsiElement element = psiReference.getElement();
                try {
                    if (element instanceof OCSendMessageExpression) {
                        set.add(new OCMethodCallUsage(psiReference));
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (element instanceof OCSelectorExpression) {
                        set.add(new OCSelectorExpressionUsage(psiReference));
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (element instanceof OCPropertyAttribute) {
                        set.add(new OCPropertyAttributeUsage(psiReference));
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    if (element instanceof OCQualifiedExpression) {
                        set.add(new OCMethodDotCallUsage(psiReference));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                return true;
            });
        }
    }
    
    public static boolean hasRelatedSymbols(@NotNull final OCStructSymbol ocStructSymbol) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/search/OCSearchUtil", "hasRelatedSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)false);
        ocStructSymbol.processSameSymbols((Processor<OCSymbol>)(p2 -> {
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
            //    18: ldc             "symbol"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/search/OCSearchUtil"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "lambda$hasRelatedSymbols$5"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_2        
            //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    48: ifeq            96
            //    51: aload_2        
            //    52: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    57: ifne            96
            //    60: goto            67
            //    63: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    66: athrow         
            //    67: aload_0        
            //    68: aload_2        
            //    69: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.equals:(Ljava/lang/Object;)Z
            //    72: ifne            96
            //    75: goto            82
            //    78: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    81: athrow         
            //    82: aload_1        
            //    83: iconst_1       
            //    84: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    87: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
            //    90: iconst_0       
            //    91: ireturn        
            //    92: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    95: athrow         
            //    96: iconst_1       
            //    97: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     60     63     67     Ljava/lang/IllegalArgumentException;
            //  51     75     78     82     Ljava/lang/IllegalArgumentException;
            //  67     92     92     96     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
        return (boolean)create.get();
    }
    
    @NotNull
    public static List<OCSymbol> getRelatedSymbols(@NotNull final OCStructSymbol ocStructSymbol) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getRelatedSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<OCSymbol> relatedSymbols;
        try {
            relatedSymbols = getRelatedSymbols(ocStructSymbol, false);
            if (relatedSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getRelatedSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return relatedSymbols;
    }
    
    @NotNull
    public static List<OCSymbol> getRelatedSymbols(@NotNull final OCStructSymbol ocStructSymbol, final boolean b) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getRelatedSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList list = new ArrayList<OCSymbol>();
        ArrayList list2;
        try {
            ocStructSymbol.processSameSymbols((Processor<OCSymbol>)(p3 -> {
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
                //    18: ldc             "symbol"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/search/OCSearchUtil"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "lambda$getRelatedSymbols$6"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: iload_0        
                //    45: ifeq            69
                //    48: aload_1        
                //    49: aload_3        
                //    50: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.equals:(Ljava/lang/Object;)Z
                //    53: ifeq            69
                //    56: goto            63
                //    59: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    62: athrow         
                //    63: iconst_1       
                //    64: ireturn        
                //    65: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    68: athrow         
                //    69: aload_3        
                //    70: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //    73: ifeq            144
                //    76: aload_3        
                //    77: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
                //    82: ifne            144
                //    85: goto            92
                //    88: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    91: athrow         
                //    92: aload_3        
                //    93: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
                //    99: ifeq            138
                //   102: goto            109
                //   105: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   108: athrow         
                //   109: aload_3        
                //   110: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
                //   115: ifne            144
                //   118: goto            125
                //   121: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   124: athrow         
                //   125: aload_2        
                //   126: aload_3        
                //   127: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
                //   130: pop            
                //   131: goto            144
                //   134: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   137: athrow         
                //   138: aload_2        
                //   139: iconst_0       
                //   140: aload_3        
                //   141: invokevirtual   java/util/ArrayList.add:(ILjava/lang/Object;)V
                //   144: iconst_1       
                //   145: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  44     56     59     63     Ljava/lang/IllegalArgumentException;
                //  48     65     65     69     Ljava/lang/IllegalArgumentException;
                //  69     85     88     92     Ljava/lang/IllegalArgumentException;
                //  76     102    105    109    Ljava/lang/IllegalArgumentException;
                //  92     118    121    125    Ljava/lang/IllegalArgumentException;
                //  109    134    134    138    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCSearchUtil", "getRelatedSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (List<OCSymbol>)list2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Ancestor
    {
        OCSymbolWithParent symbol;
        boolean isOutOfProject;
        
        public OCSymbolWithParent getSymbol() {
            return this.symbol;
        }
        
        public boolean isOutOfProject() {
            return this.isOutOfProject;
        }
    }
}
