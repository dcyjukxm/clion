// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.Iterator;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import com.intellij.psi.search.PsiElementProcessor;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Map;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import java.util.List;
import com.intellij.openapi.util.UserDataHolder;
import java.util.Collections;
import com.intellij.psi.PsiFileSystemItem;
import java.util.Collection;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import java.util.HashMap;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.NotNullLazyKey;

private static class HeaderRootsSegmentCache
{
    private static final NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project> RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE;
    private static final NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project> RESOLVE_INCLUDE_CACHE;
    @NotNull
    private final HashMap<HeaderRoots, FirstSegmentCache> myCache;
    private final boolean mySupportsRelative;
    
    private HeaderRootsSegmentCache(final boolean mySupportsRelative) {
        this.myCache = new HashMap<HeaderRoots, FirstSegmentCache>();
        this.mySupportsRelative = mySupportsRelative;
    }
    
    @NotNull
    static Collection<PsiFileSystemItem> getFirstSegmentsInLibraryRoots(final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, final String s) {
        final Project project = ocResolveRootAndConfiguration.getProject();
        Label_0058: {
            List<PsiFileSystemItem> list = null;
            Label_0023: {
                try {
                    if (project != null) {
                        break Label_0058;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0023;
                    }
                    return list;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInLibraryRoots"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return list;
        }
        final FirstSegmentCache a = ((HeaderRootsSegmentCache)((CachedValue)HeaderRootsSegmentCache.RESOLVE_INCLUDE_CACHE.getValue((UserDataHolder)project)).getValue()).a(ocResolveRootAndConfiguration.getLibraryHeadersRoots());
        Collection access$000;
        try {
            access$000 = a.a(s);
            if (access$000 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInLibraryRoots"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (Collection<PsiFileSystemItem>)access$000;
    }
    
    @NotNull
    static Collection<PsiFileSystemItem> getFirstSegmentsInProjectHeader(@NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, @NotNull final String s) {
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerResolveCtx", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "firstSegment", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Project project = ocResolveRootAndConfiguration.getProject();
        Label_0146: {
            List<PsiFileSystemItem> list = null;
            Label_0111: {
                try {
                    if (project != null) {
                        break Label_0146;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0111;
                    }
                    return list;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            return list;
        }
        final FirstSegmentCache a = ((HeaderRootsSegmentCache)((CachedValue)HeaderRootsSegmentCache.RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE.getValue((UserDataHolder)project)).getValue()).a(ocResolveRootAndConfiguration.getProjectHeadersRoots());
        Collection access$000;
        try {
            access$000 = a.a(s);
            if (access$000 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return (Collection<PsiFileSystemItem>)access$000;
    }
    
    private synchronized FirstSegmentCache a(@NotNull final HeaderRoots headerRoots) {
        try {
            if (headerRoots == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentCache"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        FirstSegmentCache firstSegmentCache = this.myCache.get(headerRoots);
        if (firstSegmentCache == null) {
            firstSegmentCache = new FirstSegmentCache((List)headerRoots.getRoots(), this.mySupportsRelative);
            this.myCache.put(headerRoots, firstSegmentCache);
        }
        return firstSegmentCache;
    }
    
    private static NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project> a(@NotNull final String s, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "createCacheKey"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project>)NotNullLazyKey.create(s, project -> CachedValuesManager.getManager(project).createCachedValue(() -> {
            final OCWorkspaceModificationTrackers instance = OCWorkspaceModificationTrackers.getInstance(project);
            return new CachedValueProvider.Result((Object)new HeaderRootsSegmentCache(b), new Object[] { instance.getProjectsListTracker(), instance.getProjectFilesListTracker(), instance.getSourceFilesListTracker(), instance.getSelectedResolveConfigurationTracker() });
        }, false));
    }
    
    static {
        RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE = a("RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE", false);
        RESOLVE_INCLUDE_CACHE = a("RESOLVE_INCLUDE_CACHE", true);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
