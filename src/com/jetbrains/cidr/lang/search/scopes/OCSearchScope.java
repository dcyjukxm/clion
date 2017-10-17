// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.scopes;

import java.util.Collections;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.util.Set;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import gnu.trove.THashSet;
import com.intellij.psi.util.CachedValueProvider;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.psi.PsiCodeFragment;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import java.util.Arrays;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.search.DelegatingGlobalSearchScope;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.search.SearchScope;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.psi.search.ProjectScope;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.util.Key;

public class OCSearchScope
{
    private static final Key<GlobalSearchScope> PROJECT_SOURCES_SCOPE;
    private static final Key<CachedValue<Collection<VirtualFile>>> EXPLICIT_PROJECT_SOURCE_FILES;
    
    @NotNull
    public static GlobalSearchScope getGotoSymbolScope(@NotNull final Project project, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getGotoSymbolScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        GlobalSearchScope globalSearchScope = null;
        Label_0063: {
            try {
                if (b) {
                    final GlobalSearchScope globalSearchScope2;
                    globalSearchScope = (globalSearchScope2 = ProjectScope.getAllScope(project));
                    break Label_0063;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            GlobalSearchScope globalSearchScope2;
            globalSearchScope = (globalSearchScope2 = getProjectSourcesScope(project));
            try {
                if (globalSearchScope2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getGotoSymbolScope"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return globalSearchScope;
    }
    
    @NotNull
    public static GlobalSearchScope getProjectSourcesScope(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getProjectSourcesScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final GlobalSearchScope globalSearchScope = (GlobalSearchScope)project.getUserData((Key)OCSearchScope.PROJECT_SOURCES_SCOPE);
        GlobalSearchScope globalSearchScope2 = null;
        Label_0088: {
            try {
                if (globalSearchScope != null) {
                    final GlobalSearchScope globalSearchScope3;
                    globalSearchScope2 = (globalSearchScope3 = globalSearchScope);
                    break Label_0088;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            GlobalSearchScope globalSearchScope3;
            globalSearchScope2 = (globalSearchScope3 = (GlobalSearchScope)((UserDataHolderEx)project).putUserDataIfAbsent((Key)OCSearchScope.PROJECT_SOURCES_SCOPE, (Object)a(project)));
            try {
                if (globalSearchScope3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getProjectSourcesScope"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return globalSearchScope2;
    }
    
    @NotNull
    public static SearchScope restrictScopeToOCFiles(@NotNull final SearchScope searchScope) {
        try {
            if (searchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalScope", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "restrictScopeToOCFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0114: {
            GlobalSearchScope globalSearchScope2 = null;
            Label_0079: {
                try {
                    if (!(searchScope instanceof GlobalSearchScope)) {
                        break Label_0114;
                    }
                    final SearchScope searchScope2 = searchScope;
                    final GlobalSearchScope globalSearchScope = (GlobalSearchScope)searchScope2;
                    final int n = 1;
                    final FileType[] array = new FileType[n];
                    final int n2 = 0;
                    final OCFileType ocFileType = OCFileType.INSTANCE;
                    array[n2] = (FileType)ocFileType;
                    globalSearchScope2 = GlobalSearchScope.getScopeRestrictedByFileTypes(globalSearchScope, array);
                    if (globalSearchScope2 == null) {
                        break Label_0079;
                    }
                    return (SearchScope)globalSearchScope2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final SearchScope searchScope2 = searchScope;
                    final GlobalSearchScope globalSearchScope = (GlobalSearchScope)searchScope2;
                    final int n = 1;
                    final FileType[] array = new FileType[n];
                    final int n2 = 0;
                    final OCFileType ocFileType = OCFileType.INSTANCE;
                    array[n2] = (FileType)ocFileType;
                    globalSearchScope2 = GlobalSearchScope.getScopeRestrictedByFileTypes(globalSearchScope, array);
                    if (globalSearchScope2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "restrictScopeToOCFiles"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return (SearchScope)globalSearchScope2;
            try {
                if (searchScope == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "restrictScopeToOCFiles"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return searchScope;
    }
    
    @NotNull
    private static DelegatingGlobalSearchScope a(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "createProjectSourcesScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
        DelegatingGlobalSearchScope delegatingGlobalSearchScope;
        try {
            delegatingGlobalSearchScope = new DelegatingGlobalSearchScope(ProjectScope.getContentScope(project)) {
                public boolean contains(@NotNull final VirtualFile virtualFile) {
                    try {
                        if (virtualFile == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope$1", "contains"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return a(fileIndex, virtualFile);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            if (delegatingGlobalSearchScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "createProjectSourcesScope"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return delegatingGlobalSearchScope;
    }
    
    private static boolean a(final ProjectFileIndex p0, final VirtualFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokeinterface com/intellij/openapi/roots/ProjectFileIndex.isInSourceContent:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //     7: ifeq            52
        //    10: aload_0        
        //    11: aload_1        
        //    12: invokeinterface com/intellij/openapi/roots/ProjectFileIndex.isInLibrarySource:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //    17: ifne            52
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: aload_1        
        //    29: invokeinterface com/intellij/openapi/roots/ProjectFileIndex.isInLibraryClasses:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //    34: ifne            52
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_1       
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_0       
        //    53: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     37     40     44     Ljava/lang/IllegalArgumentException;
        //  27     48     48     52     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    @NotNull
    public static Collection<VirtualFile> getExplicitlySpecifiedProjectSourceFiles(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getExplicitlySpecifiedProjectSourceFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Collection collection;
        try {
            collection = (Collection)CachedValuesManager.getManager(project).getCachedValue((UserDataHolder)project, (Key)OCSearchScope.EXPLICIT_PROJECT_SOURCE_FILES, () -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "lambda$getExplicitlySpecifiedProjectSourceFiles$0"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final THashSet set = new THashSet();
                final ProjectRootManager instance = ProjectRootManager.getInstance(project);
                final ProjectFileIndex fileIndex = instance.getFileIndex();
                final FileTypeRegistry instance2 = FileTypeRegistry.getInstance();
                final VirtualFile[] contentSourceRoots = instance.getContentSourceRoots();
                for (int length = contentSourceRoots.length, i = 0; i < length; ++i) {
                    VfsUtilCore.visitChildrenRecursively(contentSourceRoots[i], (VirtualFileVisitor)new VirtualFileVisitor(new VirtualFileVisitor.Option[0]) {
                        final /* synthetic */ ProjectFileIndex val$index;
                        final /* synthetic */ FileTypeRegistry val$typeRegistry;
                        final /* synthetic */ Set val$result;
                        
                        public boolean visitFile(@NotNull final VirtualFile virtualFile) {
                            try {
                                if (virtualFile == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope$2", "visitFile"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            Label_0106: {
                                Label_0081: {
                                    try {
                                        if (this.val$index.isExcluded(virtualFile)) {
                                            return false;
                                        }
                                        final VirtualFileVisitor virtualFileVisitor = this;
                                        final FileTypeRegistry fileTypeRegistry = virtualFileVisitor.val$typeRegistry;
                                        final VirtualFile virtualFile2 = virtualFile;
                                        final boolean b = fileTypeRegistry.isFileIgnored(virtualFile2);
                                        if (b) {
                                            return false;
                                        }
                                        break Label_0081;
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                    try {
                                        final VirtualFileVisitor virtualFileVisitor = this;
                                        final FileTypeRegistry fileTypeRegistry = virtualFileVisitor.val$typeRegistry;
                                        final VirtualFile virtualFile2 = virtualFile;
                                        final boolean b = fileTypeRegistry.isFileIgnored(virtualFile2);
                                        if (b) {
                                            return false;
                                        }
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                    try {
                                        if (virtualFile.isDirectory()) {
                                            return true;
                                        }
                                        final VirtualFileVisitor virtualFileVisitor2 = this;
                                        final ProjectFileIndex projectFileIndex = virtualFileVisitor2.val$index;
                                        final VirtualFile virtualFile3 = virtualFile;
                                        final boolean b2 = a(projectFileIndex, virtualFile3);
                                        if (b2) {
                                            break Label_0106;
                                        }
                                        return true;
                                    }
                                    catch (IllegalArgumentException ex4) {
                                        throw a(ex4);
                                    }
                                }
                                try {
                                    final VirtualFileVisitor virtualFileVisitor2 = this;
                                    final ProjectFileIndex projectFileIndex = virtualFileVisitor2.val$index;
                                    final VirtualFile virtualFile3 = virtualFile;
                                    final boolean b2 = a(projectFileIndex, virtualFile3);
                                    if (b2) {
                                        this.val$result.add(virtualFile);
                                    }
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw a(ex5);
                                }
                            }
                            return true;
                        }
                        
                        private static IllegalArgumentException a(final IllegalArgumentException ex) {
                            return ex;
                        }
                    });
                }
                return CachedValueProvider.Result.create((Object)Collections.unmodifiableCollection((Collection<?>)set), (Collection)getProjectSourcesCacheDependencies(project));
            }, false);
            if (collection == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getExplicitlySpecifiedProjectSourceFiles"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Collection<VirtualFile>)collection;
    }
    
    @NotNull
    public static Collection<?> getProjectSourcesCacheDependencies(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getProjectSourcesCacheDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<ModificationTracker> list;
        try {
            list = Arrays.asList(VirtualFileManager.getInstance(), ProjectRootManager.getInstance(project));
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "getProjectSourcesCacheDependencies"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list;
    }
    
    @Contract("null->false")
    public static boolean isInProjectSources(@Nullable final OCSymbol ocSymbol) {
        return isInProjectSources((PsiElement)a(ocSymbol));
    }
    
    @Nullable
    private static PsiFile a(@Nullable final OCSymbol ocSymbol) {
        Label_0023: {
            try {
                if (ocSymbol == null) {
                    break Label_0023;
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.BUILTIN_SYMBOL;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0023;
                }
                return ocSymbol.getContainingPsiFile();
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.BUILTIN_SYMBOL;
                if (ocSymbolKind == ocSymbolKind2) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocSymbol.getContainingPsiFile();
    }
    
    @Contract("null->false")
    public static boolean isInProjectSources(@Nullable final PsiElement psiElement) {
        final VirtualFile a = a(psiElement);
        Label_0032: {
            try {
                if (a == null) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final Project project = psiElement2.getProject();
                final GlobalSearchScope globalSearchScope = getProjectSourcesScope(project);
                final VirtualFile virtualFile = a;
                final boolean b = globalSearchScope.contains(virtualFile);
                if (b) {
                    break Label_0032;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final Project project = psiElement2.getProject();
                final GlobalSearchScope globalSearchScope = getProjectSourcesScope(project);
                final VirtualFile virtualFile = a;
                final boolean b = globalSearchScope.contains(virtualFile);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Contract("null->false")
    public static boolean isInProjectSourcesOrLibraries(@Nullable final OCSymbol ocSymbol) {
        return isInProjectSourcesOrLibraries((PsiElement)a(ocSymbol));
    }
    
    @Contract("null->false")
    public static boolean isInProjectSourcesOrLibraries(@Nullable final PsiElement psiElement) {
        final VirtualFile a = a(psiElement);
        try {
            if (a == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0054: {
            try {
                if (getProjectSourcesScope(psiElement.getProject()).contains(a)) {
                    break Label_0054;
                }
                final PsiElement psiElement2 = psiElement;
                final Project project = psiElement2.getProject();
                final GlobalSearchScope globalSearchScope = ProjectScope.getLibrariesScope(project);
                final VirtualFile virtualFile = a;
                final boolean b = globalSearchScope.contains(virtualFile);
                if (b) {
                    break Label_0054;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final Project project = psiElement2.getProject();
                final GlobalSearchScope globalSearchScope = ProjectScope.getLibrariesScope(project);
                final VirtualFile virtualFile = a;
                final boolean b = globalSearchScope.contains(virtualFile);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Contract("null->false")
    public static boolean isInLibraries(@Nullable final OCSymbol ocSymbol) {
        return isInLibraries((PsiElement)a(ocSymbol));
    }
    
    @Contract("null->false")
    public static boolean isInLibraries(@Nullable final PsiElement psiElement) {
        final VirtualFile a = a(psiElement);
        try {
            if (a == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return isInLibraries(a, psiElement.getProject());
    }
    
    public static boolean isInLibraries(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "isInLibraries"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope", "isInLibraries"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ProjectScope.getLibrariesScope(project).contains(virtualFile);
    }
    
    @Nullable
    private static VirtualFile a(@Nullable final PsiElement psiElement) {
        Label_0020: {
            try {
                if (psiElement == null) {
                    break Label_0020;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2.isValid();
                if (!b) {
                    break Label_0020;
                }
                break Label_0020;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2.isValid();
                if (!b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        PsiFile containingFile = psiElement.getContainingFile();
        if (containingFile instanceof PsiCodeFragment) {
            final PsiElement context = containingFile.getContext();
            PsiFile containingFile2 = null;
            Label_0065: {
                try {
                    if (context == null) {
                        containingFile2 = null;
                        break Label_0065;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                containingFile2 = context.getContainingFile();
            }
            containingFile = containingFile2;
        }
        try {
            if (containingFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return OCInclusionContextUtil.getVirtualFile(containingFile);
    }
    
    static {
        PROJECT_SOURCES_SCOPE = Key.create("PROJECT_SOURCES_SCOPE");
        EXPLICIT_PROJECT_SOURCE_FILES = Key.create("EXPLICIT_PROJECT_SOURCE_FILES");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
