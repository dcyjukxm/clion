// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import com.intellij.psi.search.PsiElementProcessor;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiFileSystemItem;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.List;

private static class FirstSegmentCache
{
    @NotNull
    private final List<HeadersSearchRoot> myRoots;
    private final boolean mySupportsRelative;
    private Map<String, List<PsiFileSystemItem>> myCache;
    
    private FirstSegmentCache(@NotNull final List<HeadersSearchRoot> myRoots, final boolean mySupportsRelative) {
        if (myRoots == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache", "<init>"));
        }
        this.mySupportsRelative = mySupportsRelative;
        this.myRoots = myRoots;
    }
    
    private void a() {
        try {
            if (this.myCache != null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myCache = (Map<String, List<PsiFileSystemItem>>)new THashMap(FileUtil.PATH_HASHING_STRATEGY);
        for (final PsiFileSystemItem psiFileSystemItem : this.myRoots) {
            try {
                if (!psiFileSystemItem.isValid()) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            psiFileSystemItem.processChildren((PsiElementProcessor)new PsiElementProcessor<PsiFileSystemItem>() {
                public boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
                    try {
                        if (psiFileSystemItem == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache$1", "execute"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final String name = psiFileSystemItem.getName();
                    List<PsiFileSystemItem> list = FirstSegmentCache.this.myCache.get(name);
                    if (list == null) {
                        list = new ArrayList<PsiFileSystemItem>();
                        FirstSegmentCache.this.myCache.put(name, list);
                    }
                    list.add(psiFileSystemItem);
                    return true;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            });
        }
    }
    
    private synchronized Collection<PsiFileSystemItem> a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "segment", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache", "getFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.a();
        List<PsiFileSystemItem> emptyList = this.myCache.get(s);
        try {
            if (emptyList != null) {
                return emptyList;
            }
            if (!this.mySupportsRelative) {
                return (Collection<PsiFileSystemItem>)Collections.emptyList();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        emptyList = new ArrayList<PsiFileSystemItem>();
        for (final PsiFileSystemItem psiFileSystemItem : this.myRoots) {
            try {
                if (!psiFileSystemItem.isValid()) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            emptyList.addAll(OCIncludeHelpers.access$300(psiFileSystemItem, s));
        }
        this.myCache.put(s, emptyList);
        return emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
