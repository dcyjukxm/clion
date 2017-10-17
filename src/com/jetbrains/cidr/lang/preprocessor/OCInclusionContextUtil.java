// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.openapi.util.Comparing;
import java.util.Locale;
import com.jetbrains.cidr.lang.symbols.symtable.SymbolTableProvider;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import java.util.concurrent.ConcurrentHashMap;
import com.intellij.openapi.vfs.VirtualFileWithId;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import com.intellij.psi.PsiManager;
import com.intellij.util.FileContentUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Iterator;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.Set;
import com.intellij.util.containers.HashSet;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCConfigurationOwner;
import com.jetbrains.cidr.lang.workspace.OCResolveLanguageAndConfiguration;
import com.intellij.util.messages.Topic;
import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import org.jetbrains.annotations.Contract;
import com.intellij.util.indexing.IndexingDataKeys;
import java.util.Collections;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceRunConfigurationListener;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Map;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.NotNullLazyKey;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.util.Key;

public class OCInclusionContextUtil
{
    private static final Key<OCResolveConfiguration> PROJECT_USER_SELECTED_CONFIGURATION;
    private static final NotNullLazyKey<CachedValue<OCResolveConfiguration>, PsiFile> FILE_ACTIVE_CONFIGURATION_CACHE;
    public static final NotNullLazyKey<Map<VirtualFile, OCResolveRootAndConfiguration>, Project> HEADER_ROOT_AND_CONFIGURATION_KEY;
    private static final NotNullLazyKey<Map<VirtualFile, OCImmutableInclusionContext>, Project> HEADER_CONTEXT_KEY;
    private static final NotNullLazyKey<Map<VirtualFile, OCContextChangeSet>, Project> CONTEXT_CHANGES_KEY;
    
    @Deprecated
    @Nullable
    public static OCResolveConfiguration computePreselectedConfiguration(@NotNull final Project project, @NotNull final Collection<? extends OCResolveConfiguration> collection) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "computePreselectedConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configs", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "computePreselectedConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (OCResolveConfiguration)a(project, collection).first;
    }
    
    @NotNull
    private static Pair<OCResolveConfiguration, Boolean> a(@NotNull final Project project, @NotNull final Collection<? extends OCResolveConfiguration> collection) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "findPreselectedOrSuitableConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configs", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "findPreselectedOrSuitableConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCResolveConfiguration a = a(project);
        Label_0157: {
            Pair pair = null;
            Label_0122: {
                try {
                    if (!collection.contains(a)) {
                        break Label_0157;
                    }
                    final OCResolveConfiguration ocResolveConfiguration = a;
                    final boolean b = true;
                    final Boolean b2 = b;
                    pair = Pair.create((Object)ocResolveConfiguration, (Object)b2);
                    if (pair == null) {
                        break Label_0122;
                    }
                    return (Pair<OCResolveConfiguration, Boolean>)pair;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCResolveConfiguration ocResolveConfiguration = a;
                    final boolean b = true;
                    final Boolean b2 = b;
                    pair = Pair.create((Object)ocResolveConfiguration, (Object)b2);
                    if (pair == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "findPreselectedOrSuitableConfiguration"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return (Pair<OCResolveConfiguration, Boolean>)pair;
        }
        final OCResolveConfiguration selectedResolveConfiguration = OCWorkspaceRunConfigurationListener.getSelectedResolveConfiguration(project);
        Pair create = null;
        Label_0250: {
            Label_0226: {
                Pair pair2 = null;
                Label_0191: {
                    try {
                        if (!collection.contains(selectedResolveConfiguration)) {
                            break Label_0226;
                        }
                        final OCResolveConfiguration ocResolveConfiguration2 = selectedResolveConfiguration;
                        final boolean b3 = true;
                        final Boolean b4 = b3;
                        pair2 = Pair.create((Object)ocResolveConfiguration2, (Object)b4);
                        if (pair2 == null) {
                            break Label_0191;
                        }
                        return (Pair<OCResolveConfiguration, Boolean>)pair2;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final OCResolveConfiguration ocResolveConfiguration2 = selectedResolveConfiguration;
                        final boolean b3 = true;
                        final Boolean b4 = b3;
                        pair2 = Pair.create((Object)ocResolveConfiguration2, (Object)b4);
                        if (pair2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "findPreselectedOrSuitableConfiguration"));
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                return (Pair<OCResolveConfiguration, Boolean>)pair2;
                try {
                    if (collection.isEmpty()) {
                        final OCResolveConfiguration ocResolveConfiguration3 = null;
                        break Label_0250;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            final OCResolveConfiguration ocResolveConfiguration3 = Collections.min(collection);
            try {
                create = Pair.create((Object)ocResolveConfiguration3, (Object)false);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "findPreselectedOrSuitableConfiguration"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return (Pair<OCResolveConfiguration, Boolean>)create;
    }
    
    @Nullable
    @Contract("null -> null")
    public static VirtualFile getVirtualFile(@Nullable final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile virtualFile = psiFile.getOriginalFile().getVirtualFile();
        try {
            if (virtualFile != null) {
                return virtualFile;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (VirtualFile)psiFile.getUserData((Key)IndexingDataKeys.VIRTUAL_FILE);
    }
    
    public static boolean isNeedToFindRoot(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "isNeedToFindRoot"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile virtualFile = getVirtualFile(psiFile);
        try {
            if (virtualFile == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return isNeedToFindRoot(virtualFile, psiFile.getProject());
    }
    
    public static boolean isNeedToFindRoot(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "isNeedToFindRoot"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "isNeedToFindRoot"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0113: {
            try {
                if (OCFileImpl.isSourceCodeFile(virtualFile.getName())) {
                    return false;
                }
                final VirtualFile virtualFile2 = virtualFile;
                final Project project2 = project;
                final boolean b = OCInclusionContext.isPrecompiledHeader(virtualFile2, project2);
                if (!b) {
                    break Label_0113;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final VirtualFile virtualFile2 = virtualFile;
                final Project project2 = project;
                final boolean b = OCInclusionContext.isPrecompiledHeader(virtualFile2, project2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @NotNull
    public static OCResolveRootAndConfiguration getResolveRootAndActiveConfiguration(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getResolveRootAndActiveConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getResolveRootAndActiveConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Map map = (Map)OCInclusionContextUtil.HEADER_ROOT_AND_CONFIGURATION_KEY.getValue((UserDataHolder)project);
        final OCResolveRootAndConfiguration ocResolveRootAndConfiguration = map.get(virtualFile);
        Label_0161: {
            OCResolveRootAndConfiguration ocResolveRootAndConfiguration2 = null;
            Label_0126: {
                try {
                    if (ocResolveRootAndConfiguration == null) {
                        break Label_0161;
                    }
                    ocResolveRootAndConfiguration2 = ocResolveRootAndConfiguration;
                    if (ocResolveRootAndConfiguration2 == null) {
                        break Label_0126;
                    }
                    return ocResolveRootAndConfiguration2;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    ocResolveRootAndConfiguration2 = ocResolveRootAndConfiguration;
                    if (ocResolveRootAndConfiguration2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getResolveRootAndActiveConfiguration"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ocResolveRootAndConfiguration2;
        }
        OCResolveRootAndConfiguration a = null;
        if (isNeedToFindRoot(virtualFile, project)) {
            a = a(virtualFile, project);
        }
        if (a == null) {
            a = new OCResolveRootAndConfiguration(b(virtualFile, project), virtualFile);
        }
        OCResolveRootAndConfiguration ocResolveRootAndConfiguration3;
        try {
            map.put(virtualFile, a);
            ((OCInclusionContextListener)project.getMessageBus().syncPublisher((Topic)OCInclusionContextListener.TOPIC)).resolveRootAndActiveConfigurationChanged(virtualFile, a);
            ocResolveRootAndConfiguration3 = a;
            if (ocResolveRootAndConfiguration3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getResolveRootAndActiveConfiguration"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return ocResolveRootAndConfiguration3;
    }
    
    @NotNull
    public static OCResolveLanguageAndConfiguration getOrCalculateParsedResolveLanguageAndConfiguration(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getOrCalculateParsedResolveLanguageAndConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCResolveLanguageAndConfiguration parsedResolveLanguageAndConfiguration = null;
        Label_0068: {
            try {
                if (psiFile instanceof OCConfigurationOwner) {
                    parsedResolveLanguageAndConfiguration = ((OCConfigurationOwner)psiFile).getParsedResolveLanguageAndConfiguration();
                    break Label_0068;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            parsedResolveLanguageAndConfiguration = null;
        }
        final OCResolveLanguageAndConfiguration ocResolveLanguageAndConfiguration = parsedResolveLanguageAndConfiguration;
        Label_0120: {
            OCResolveLanguageAndConfiguration ocResolveLanguageAndConfiguration2 = null;
            Label_0085: {
                try {
                    if (ocResolveLanguageAndConfiguration == null) {
                        break Label_0120;
                    }
                    ocResolveLanguageAndConfiguration2 = ocResolveLanguageAndConfiguration;
                    if (ocResolveLanguageAndConfiguration2 == null) {
                        break Label_0085;
                    }
                    return ocResolveLanguageAndConfiguration2;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    ocResolveLanguageAndConfiguration2 = ocResolveLanguageAndConfiguration;
                    if (ocResolveLanguageAndConfiguration2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getOrCalculateParsedResolveLanguageAndConfiguration"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ocResolveLanguageAndConfiguration2;
        }
        final OCImmutableInclusionContext headerContext = headerContext(psiFile);
        OCResolveLanguageAndConfiguration ocResolveLanguageAndConfiguration3;
        try {
            ocResolveLanguageAndConfiguration3 = new OCResolveLanguageAndConfiguration(headerContext.getConfiguration(), headerContext.getLanguageKind());
            if (ocResolveLanguageAndConfiguration3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getOrCalculateParsedResolveLanguageAndConfiguration"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return ocResolveLanguageAndConfiguration3;
    }
    
    @Nullable
    private static OCResolveRootAndConfiguration a(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualHeaderFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getResolveRootAndActiveConfigurationForHeader"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getResolveRootAndActiveConfigurationForHeader"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String nameWithoutExtension = virtualFile.getNameWithoutExtension();
        final ArrayList<Object> list = new ArrayList<Object>(OCImportGraph.getAllHeaderRoots(project, virtualFile));
        Collections.sort(list, (Comparator<? super Object>)new VirtualFileComparator(project, nameWithoutExtension));
        OCResolveRootAndConfiguration ocResolveRootAndConfiguration = null;
        for (final VirtualFile virtualFile2 : list) {
            final HashSet set = new HashSet();
            try {
                OCImportGraph.fillHeaderConfigurationsForRoot(project, virtualFile, virtualFile2, (Set<OCResolveConfiguration>)set, null);
                if (set.isEmpty()) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final Pair<OCResolveConfiguration, Boolean> a = a(project, (Collection<? extends OCResolveConfiguration>)set);
            try {
                if (a.second) {
                    return new OCResolveRootAndConfiguration((OCResolveConfiguration)a.first, virtualFile2);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (ocResolveRootAndConfiguration != null || a.first == null) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            ocResolveRootAndConfiguration = new OCResolveRootAndConfiguration((OCResolveConfiguration)a.first, virtualFile2);
        }
        return ocResolveRootAndConfiguration;
    }
    
    @Nullable
    private static OCResolveConfiguration b(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "doGetPreselectedConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "doGetPreselectedConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (OCResolveConfiguration)a(project, getAllBuildConfigurationsForIndexing(virtualFile, project)).first;
    }
    
    public static void invalidateHeaderRootAndActiveConfigurationForAllExcept(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "invalidateHeaderRootAndActiveConfigurationForAllExcept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        removeExcept((Map<VirtualFile, Object>)OCInclusionContextUtil.HEADER_ROOT_AND_CONFIGURATION_KEY.getValue((UserDataHolder)project), virtualFile);
        invalidateHeaderContextsExcept(virtualFile, project);
    }
    
    public static void invalidateHeaderContextsExcept(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "invalidateHeaderContextsExcept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        removeExcept((Map<VirtualFile, Object>)OCInclusionContextUtil.HEADER_CONTEXT_KEY.getValue((UserDataHolder)project), virtualFile);
        invalidateContextChangeCacheExcept(virtualFile, project);
    }
    
    public static void invalidateContextChangeCacheExcept(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "invalidateContextChangeCacheExcept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        removeExcept((Map<VirtualFile, Object>)OCInclusionContextUtil.CONTEXT_CHANGES_KEY.getValue((UserDataHolder)project), virtualFile);
    }
    
    public static <K, V> void removeExcept(@NotNull final Map<K, V> map, @Nullable final K k) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "map", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "removeExcept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (k == null) {
                map.clear();
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        map.keySet().retainAll(Collections.singleton(k));
    }
    
    public static void invalidateHeaderRootAndActiveConfigurationFor(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "invalidateHeaderRootAndActiveConfigurationFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ((Map)OCInclusionContextUtil.HEADER_ROOT_AND_CONFIGURATION_KEY.getValue((UserDataHolder)project)).remove(virtualFile);
        invalidateHeaderContextsExcept(null, project);
    }
    
    @NotNull
    public static OCImmutableInclusionContext headerContext(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "headerContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile b = b(psiFile);
        Label_0104: {
            OCInclusionContext ocInclusionContext = null;
            Label_0069: {
                try {
                    if (b != null) {
                        break Label_0104;
                    }
                    final OCLanguageKind ocLanguageKind = null;
                    final PsiFile psiFile2 = psiFile;
                    ocInclusionContext = OCInclusionContextImpl.emptyWithBuiltinMacros(ocLanguageKind, psiFile2);
                    if (ocInclusionContext == null) {
                        break Label_0069;
                    }
                    return ocInclusionContext;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCLanguageKind ocLanguageKind = null;
                    final PsiFile psiFile2 = psiFile;
                    ocInclusionContext = OCInclusionContextImpl.emptyWithBuiltinMacros(ocLanguageKind, psiFile2);
                    if (ocInclusionContext == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "headerContext"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return ocInclusionContext;
        }
        final Project project = b.getProject();
        final VirtualFile virtualFile = getVirtualFile(b);
        Map<K, OCInclusionContext> map = null;
        Label_0201: {
            if (virtualFile != null) {
                map = (Map<K, OCInclusionContext>)OCInclusionContextUtil.HEADER_CONTEXT_KEY.getValue((UserDataHolder)project);
                final OCInclusionContext ocInclusionContext2 = map.get(virtualFile);
                OCInclusionContext ocInclusionContext3 = null;
                Label_0166: {
                    try {
                        if (ocInclusionContext2 == null) {
                            break Label_0201;
                        }
                        ocInclusionContext3 = ocInclusionContext2;
                        if (ocInclusionContext3 == null) {
                            break Label_0166;
                        }
                        return ocInclusionContext3;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        ocInclusionContext3 = ocInclusionContext2;
                        if (ocInclusionContext3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "headerContext"));
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                return ocInclusionContext3;
            }
        }
        final OCInclusionContext a = a(b, null);
        try {
            if (virtualFile != null) {
                map.put((K)virtualFile, a);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        OCInclusionContext ocInclusionContext4;
        try {
            ocInclusionContext4 = a;
            if (ocInclusionContext4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "headerContext"));
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return ocInclusionContext4;
    }
    
    public static OCInclusionContext calculateHeaderContext(@NotNull final PsiFile psiFile, @Nullable final OCLanguageKind ocLanguageKind) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "calculateHeaderContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile b = b(psiFile);
        try {
            if (b == null) {
                return OCInclusionContextImpl.emptyWithBuiltinMacros(ocLanguageKind, psiFile);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(b, ocLanguageKind);
    }
    
    @Nullable
    private static PsiFile b(@Nullable final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile originalFile = psiFile.getOriginalFile();
        try {
            if (!(originalFile instanceof OCFile)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (originalFile.isPhysical()) {
                return psiFile;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        PsiElement context = (PsiElement)originalFile;
        while (true) {
            try {
                if (context == null || context.isPhysical()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            context = context.getContext();
        }
        PsiFile containingFile = null;
        Label_0095: {
            try {
                if (context != null) {
                    containingFile = context.getContainingFile();
                    break Label_0095;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            containingFile = null;
        }
        final PsiFile psiFile2 = containingFile;
        try {
            if (psiFile2 != null) {
                return psiFile2;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return null;
    }
    
    @NotNull
    private static OCInclusionContext a(@NotNull final PsiFile p0, @Nullable final OCLanguageKind p1) {
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
        //    18: ldc             "original"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "innerCalculateHeaderContext"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    50: astore_2       
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getVirtualFile:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //    55: astore_3       
        //    56: aload_3        
        //    57: ifnull          321
        //    60: aload_3        
        //    61: aload_2        
        //    62: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.isNeedToFindRoot:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/project/Project;)Z
        //    65: ifeq            321
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_3        
        //    76: aload_2        
        //    77: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getResolveRootAndActiveConfiguration:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;
        //    80: astore          4
        //    82: aload           4
        //    84: invokevirtual   com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration.getRootFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    87: astore          5
        //    89: aload           5
        //    91: ifnull          125
        //    94: aload           5
        //    96: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //    99: ifeq            125
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_2        
        //   110: invokestatic    com/intellij/psi/PsiManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiManager;
        //   113: aload           5
        //   115: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //   118: goto            126
        //   121: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aconst_null    
        //   126: ldc             Lcom/jetbrains/cidr/lang/psi/OCFile;.class
        //   128: invokestatic    com/intellij/util/ObjectUtils.tryCast:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   131: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   134: astore          6
        //   136: aload           6
        //   138: ifnull          321
        //   141: aload           5
        //   143: aload_3        
        //   144: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   147: ifne            321
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload           4
        //   159: invokevirtual   com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration.getConfiguration:()Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;
        //   162: astore          7
        //   164: aload           7
        //   166: ifnull          321
        //   169: aload           7
        //   171: aload_3        
        //   172: aload           5
        //   174: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.tryFindInCachedPCHPrecompiledContexts:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   177: astore          8
        //   179: aload           8
        //   181: ifnonnull       203
        //   184: aload           7
        //   186: aload           6
        //   188: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   193: aload           6
        //   195: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.initialPCHContextWithoutRoot:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   198: astore          8
        //   200: goto            259
        //   203: aload           8
        //   205: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getParent:()Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   210: astore          8
        //   212: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.$assertionsDisabled:Z
        //   215: ifne            259
        //   218: aload           8
        //   220: ifnull          247
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload           8
        //   232: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getParent:()Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   237: ifnull          259
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: new             Ljava/lang/AssertionError;
        //   250: dup            
        //   251: invokespecial   java/lang/AssertionError.<init>:()V
        //   254: athrow         
        //   255: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   258: athrow         
        //   259: aload           8
        //   261: aload           6
        //   263: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.derive:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   268: astore          9
        //   270: aload           9
        //   272: aload           6
        //   274: aload_3        
        //   275: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.preprocessContextOf:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/vfs/VirtualFile;)V
        //   280: aload           9
        //   282: dup            
        //   283: ifnonnull       320
        //   286: new             Ljava/lang/IllegalStateException;
        //   289: dup            
        //   290: ldc             "@NotNull method %s.%s must not return null"
        //   292: ldc             2
        //   294: anewarray       Ljava/lang/Object;
        //   297: dup            
        //   298: ldc             0
        //   300: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil"
        //   302: aastore        
        //   303: dup            
        //   304: ldc             1
        //   306: ldc             "innerCalculateHeaderContext"
        //   308: aastore        
        //   309: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   312: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   315: athrow         
        //   316: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: areturn        
        //   321: aload_0        
        //   322: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   325: aload_1        
        //   326: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   329: dup            
        //   330: ifnonnull       367
        //   333: new             Ljava/lang/IllegalStateException;
        //   336: dup            
        //   337: ldc             "@NotNull method %s.%s must not return null"
        //   339: ldc             2
        //   341: anewarray       Ljava/lang/Object;
        //   344: dup            
        //   345: ldc             0
        //   347: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil"
        //   349: aastore        
        //   350: dup            
        //   351: ldc             1
        //   353: ldc             "innerCalculateHeaderContext"
        //   355: aastore        
        //   356: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   359: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   362: athrow         
        //   363: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   366: athrow         
        //   367: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     68     71     75     Ljava/lang/IllegalArgumentException;
        //  89     102    105    109    Ljava/lang/IllegalArgumentException;
        //  94     121    121    125    Ljava/lang/IllegalArgumentException;
        //  136    150    153    157    Ljava/lang/IllegalArgumentException;
        //  212    223    226    230    Ljava/lang/IllegalArgumentException;
        //  218    240    243    247    Ljava/lang/IllegalArgumentException;
        //  230    255    255    259    Ljava/lang/IllegalArgumentException;
        //  270    316    316    320    Ljava/lang/IllegalArgumentException;
        //  321    363    363    367    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0230:
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
    private static OCInclusionContext a(@NotNull final OCFile ocFile, @Nullable OCLanguageKind kind) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "sourceFileContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (kind == null) {
            kind = ocFile.getKind();
        }
        final VirtualFile virtualFile = getVirtualFile((PsiFile)ocFile);
        OCInclusionContext emptyWithBuiltinMacros = null;
        Label_0131: {
            if (virtualFile != null) {
                final OCResolveConfiguration b = b(virtualFile, ocFile.getProject());
                OCInclusionContext ocInclusionContext = null;
                Label_0096: {
                    try {
                        if (b == null) {
                            break Label_0131;
                        }
                        final OCResolveConfiguration ocResolveConfiguration = b;
                        final OCLanguageKind ocLanguageKind = kind;
                        final OCFile ocFile2 = ocFile;
                        ocInclusionContext = OCInclusionContext.sourceParsingContext(ocResolveConfiguration, ocLanguageKind, (PsiFile)ocFile2);
                        if (ocInclusionContext == null) {
                            break Label_0096;
                        }
                        return ocInclusionContext;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCResolveConfiguration ocResolveConfiguration = b;
                        final OCLanguageKind ocLanguageKind = kind;
                        final OCFile ocFile2 = ocFile;
                        ocInclusionContext = OCInclusionContext.sourceParsingContext(ocResolveConfiguration, ocLanguageKind, (PsiFile)ocFile2);
                        if (ocInclusionContext == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "sourceFileContext"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocInclusionContext;
            }
            try {
                emptyWithBuiltinMacros = OCInclusionContextImpl.emptyWithBuiltinMacros(kind, (PsiFile)ocFile);
                if (emptyWithBuiltinMacros == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "sourceFileContext"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return emptyWithBuiltinMacros;
    }
    
    public static void setUserSelectedConfiguration(@NotNull final PsiFile psiFile, @Nullable final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "setUserSelectedConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = psiFile.getProject();
        project.putUserData((Key)OCInclusionContextUtil.PROJECT_USER_SELECTED_CONFIGURATION, (Object)ocResolveConfiguration);
        psiFile.putCopyableUserData((Key)OCInclusionContextUtil.FILE_ACTIVE_CONFIGURATION_CACHE, (Object)null);
        invalidateHeaderRootAndActiveConfigurationFor(getVirtualFile(psiFile), project);
        final VirtualFile virtualFile = psiFile.getVirtualFile();
        try {
            if (virtualFile != null) {
                FileContentUtil.reparseFiles(project, (Collection)Collections.singletonList(virtualFile), false);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Nullable
    private static OCResolveConfiguration a(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getUserSelectedConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCResolveConfiguration)project.getUserData((Key)OCInclusionContextUtil.PROJECT_USER_SELECTED_CONFIGURATION);
    }
    
    @Nullable
    public static OCResolveConfiguration getActiveConfiguration(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCResolveConfiguration)((CachedValue)OCInclusionContextUtil.FILE_ACTIVE_CONFIGURATION_CACHE.getValue((UserDataHolder)psiElement.getContainingFile())).getValue();
    }
    
    @Nullable
    public static OCResolveConfiguration getActiveConfiguration(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getActiveConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                final PsiFile file = null;
                return getActiveConfiguration((PsiElement)file);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
        return getActiveConfiguration((PsiElement)file);
    }
    
    @NotNull
    public static Collection<? extends OCResolveConfiguration> getAllBuildConfigurationsForFile(@NotNull final PsiFile p0, @Nullable final ProgressIndicator p1) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getAllBuildConfigurationsForFile"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getVirtualFile:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //    48: astore_2       
        //    49: aload_2        
        //    50: ifnull          135
        //    53: aload_0        
        //    54: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    57: ifeq            135
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.isNeedToFindRoot:(Lcom/intellij/psi/PsiFile;)Z
        //    71: ifeq            135
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_0        
        //    82: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    85: aload_1        
        //    86: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.getAllHeaderConfigurations:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/util/Set;
        //    89: dup            
        //    90: ifnonnull       134
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: new             Ljava/lang/IllegalStateException;
        //   103: dup            
        //   104: ldc             "@NotNull method %s.%s must not return null"
        //   106: ldc             2
        //   108: anewarray       Ljava/lang/Object;
        //   111: dup            
        //   112: ldc             0
        //   114: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             1
        //   120: ldc             "getAllBuildConfigurationsForFile"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: areturn        
        //   135: aload_2        
        //   136: aload_0        
        //   137: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   142: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getAllBuildConfigurationsOfTargetsOfFile:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/project/Project;)Ljava/util/Collection;
        //   145: dup            
        //   146: ifnonnull       183
        //   149: new             Ljava/lang/IllegalStateException;
        //   152: dup            
        //   153: ldc             "@NotNull method %s.%s must not return null"
        //   155: ldc             2
        //   157: anewarray       Ljava/lang/Object;
        //   160: dup            
        //   161: ldc             0
        //   163: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil"
        //   165: aastore        
        //   166: dup            
        //   167: ldc             1
        //   169: ldc             "getAllBuildConfigurationsForFile"
        //   171: aastore        
        //   172: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   175: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   178: athrow         
        //   179: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/util/Collection<+Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     60     63     67     Ljava/lang/IllegalArgumentException;
        //  53     74     77     81     Ljava/lang/IllegalArgumentException;
        //  67     93     96     100    Ljava/lang/IllegalArgumentException;
        //  81     130    130    134    Ljava/lang/IllegalArgumentException;
        //  135    179    179    183    Ljava/lang/IllegalArgumentException;
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
    }
    
    @NotNull
    public static Collection<? extends OCResolveConfiguration> getAllBuildConfigurationsOfTargetsOfFile(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getAllBuildConfigurationsOfTargetsOfFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Collection<OCResolveConfiguration> buildConfigurationByPchFile = OCInclusionContext.getBuildConfigurationByPchFile(virtualFile, project);
        List<? extends OCResolveConfiguration> configurationsForFile = null;
        Label_0106: {
            Collection<OCResolveConfiguration> collection = null;
            Label_0071: {
                try {
                    if (buildConfigurationByPchFile.isEmpty()) {
                        break Label_0106;
                    }
                    collection = buildConfigurationByPchFile;
                    if (collection == null) {
                        break Label_0071;
                    }
                    return collection;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    collection = buildConfigurationByPchFile;
                    if (collection == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getAllBuildConfigurationsOfTargetsOfFile"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return collection;
            try {
                configurationsForFile = OCWorkspaceManager.getWorkspace(project).getConfigurationsForFile(virtualFile);
                if (configurationsForFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getAllBuildConfigurationsOfTargetsOfFile"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return configurationsForFile;
    }
    
    @NotNull
    public static Collection<? extends OCResolveConfiguration> getAllBuildConfigurationsForIndexing(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getAllBuildConfigurationsForIndexing"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Collection<? extends OCResolveConfiguration> allBuildConfigurationsOfTargetsOfFile = getAllBuildConfigurationsOfTargetsOfFile(virtualFile, project);
        Label_0106: {
            Collection<? extends OCResolveConfiguration> collection = null;
            Label_0071: {
                try {
                    if (allBuildConfigurationsOfTargetsOfFile.isEmpty()) {
                        break Label_0106;
                    }
                    collection = allBuildConfigurationsOfTargetsOfFile;
                    if (collection == null) {
                        break Label_0071;
                    }
                    return collection;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    collection = allBuildConfigurationsOfTargetsOfFile;
                    if (collection == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getAllBuildConfigurationsForIndexing"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return collection;
        }
        final OCResolveConfiguration ocResolveConfiguration = (OCResolveConfiguration)a(project, OCWorkspaceManager.getWorkspace(project).getConfigurations()).first;
        List maybeSingletonList;
        try {
            maybeSingletonList = ContainerUtil.createMaybeSingletonList((Object)ocResolveConfiguration);
            if (maybeSingletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "getAllBuildConfigurationsForIndexing"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (Collection<? extends OCResolveConfiguration>)maybeSingletonList;
    }
    
    public static String pragmaOnceId(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "vFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "pragmaOnceId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(virtualFile, "__CIDR_pragma_once");
    }
    
    public static String inclusionId(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "vFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "inclusionId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(virtualFile, "__CIDR_included");
    }
    
    private static String a(final VirtualFile virtualFile, final String s) {
        try {
            if (virtualFile instanceof VirtualFileWithId) {
                return s + "_id_" + ((VirtualFileWithId)virtualFile).getId();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s + "_path_" + virtualFile.getPath();
    }
    
    public static void storeCachedContextChangeSet(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final OCContextChangeSet set) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "storeCachedContextChangeSet"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "storeCachedContextChangeSet"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeSet", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "storeCachedContextChangeSet"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        ((Map)OCInclusionContextUtil.CONTEXT_CHANGES_KEY.get((UserDataHolder)project)).put(virtualFile, set);
    }
    
    public static OCContextChangeSet cachedContextChangeSet(@NotNull final Project project, @Nullable final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil", "cachedContextChangeSet"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ((Map)OCInclusionContextUtil.CONTEXT_CHANGES_KEY.getValue((UserDataHolder)project)).get(virtualFile);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInclusionContextUtil.class.desiredAssertionStatus()) {
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
        PROJECT_USER_SELECTED_CONFIGURATION = Key.create("PROJECT_USER_SELECTED_CONFIGURATION");
        FILE_ACTIVE_CONFIGURATION_CACHE = NotNullLazyKey.create("FILE_ACTIVE_CONFIGURATION_CACHE", psiFile -> {
            final Project project = psiFile.getProject();
            return CachedValuesManager.getManager(project).createCachedValue((CachedValueProvider)new CachedValueProvider<OCResolveConfiguration>() {
                final /* synthetic */ Project val$project;
                final /* synthetic */ PsiFile val$file;
                
                public CachedValueProvider.Result<OCResolveConfiguration> compute() {
                    if (project.isDefault()) {
                        return (CachedValueProvider.Result<OCResolveConfiguration>)new CachedValueProvider.Result((Object)null, new Object[] { ModificationTracker.NEVER_CHANGED });
                    }
                    final OCWorkspaceModificationTrackers instance = OCWorkspaceModificationTrackers.getInstance(project);
                    return (CachedValueProvider.Result<OCResolveConfiguration>)new CachedValueProvider.Result((Object)this.a(), new Object[] { FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker(), instance.getProjectsListTracker(), instance.getProjectFilesListTracker(), instance.getSourceFilesListTracker(), instance.getSelectedResolveConfigurationTracker() });
                }
                
                private OCResolveConfiguration a() {
                    if (SymbolTableProvider.isSourceFile(psiFile)) {
                        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile(psiFile.getOriginalFile());
                        if (virtualFile != null) {
                            return OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile, psiFile.getProject()).getConfiguration();
                        }
                    }
                    return null;
                }
            }, false);
        });
        HEADER_ROOT_AND_CONFIGURATION_KEY = NotNullLazyKey.create("HEADER_ROOT_AND_CONFIGURATION_KEY", project -> new ConcurrentHashMap());
        HEADER_CONTEXT_KEY = NotNullLazyKey.create("HEADER_CONTEXT_KEY", project -> ContainerUtil.createConcurrentSoftValueMap());
        CONTEXT_CHANGES_KEY = NotNullLazyKey.create("CONTEXT_CHANGES_KEY", project -> ContainerUtil.createConcurrentSoftValueMap());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class VirtualFileComparator implements Comparator<VirtualFile>
    {
        private final Project myProject;
        private final String myHeaderNameWithoutExtension;
        
        public VirtualFileComparator(final Project myProject, final String s) {
            this.myProject = myProject;
            this.myHeaderNameWithoutExtension = s.toUpperCase(Locale.getDefault());
        }
        
        @Override
        public int compare(@NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root1", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil$VirtualFileComparator", "compare"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (virtualFile2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root2", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil$VirtualFileComparator", "compare"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final int compare = Comparing.compare(OCInclusionContext.isPrecompiledHeader(virtualFile, this.myProject), OCInclusionContext.isPrecompiledHeader(virtualFile2, this.myProject));
            try {
                if (compare != 0) {
                    return -compare;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final int compare2 = Comparing.compare(OCFileTypeHelpers.isSourceFile(virtualFile.getName()), OCFileTypeHelpers.isSourceFile(virtualFile2.getName()));
            try {
                if (compare2 != 0) {
                    return -compare2;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final String upperCase = virtualFile.getNameWithoutExtension().toUpperCase(Locale.getDefault());
            final String upperCase2 = virtualFile2.getNameWithoutExtension().toUpperCase(Locale.getDefault());
            final int compare3 = Comparing.compare(this.myHeaderNameWithoutExtension.equals(upperCase), this.myHeaderNameWithoutExtension.equals(upperCase2));
            try {
                if (compare3 != 0) {
                    return -compare3;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            final int compare4 = Comparing.compare((Comparable)upperCase, (Comparable)upperCase2);
            try {
                if (compare4 != 0) {
                    return compare4;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            return Comparing.compare((Comparable)virtualFile.getPath(), (Comparable)virtualFile2.getPath());
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
