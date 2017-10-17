// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class HeadersSearchPathTree extends PathTree<HeadersSearchPathTree, HeadersSearchPath>
{
    @Nullable
    private final HeadersSearchPathTree myParent;
    @NotNull
    private final List<HeadersSearchPath> myCompactedPaths;
    
    public HeadersSearchPathTree() {
        this(null);
    }
    
    private HeadersSearchPathTree(@Nullable final HeadersSearchPathTree myParent) {
        this.myCompactedPaths = new ArrayList<HeadersSearchPath>();
        this.myParent = myParent;
    }
    
    @Override
    protected HeadersSearchPathTree createNewTree(@Nullable final HeadersSearchPathTree headersSearchPathTree) {
        return new HeadersSearchPathTree(headersSearchPathTree);
    }
    
    public void addSearchPathCompacting(@NotNull final HeadersSearchPath headersSearchPath) {
        try {
            if (headersSearchPath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPathTree", "addSearchPathCompacting"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final String path = headersSearchPath.getFile().getPath();
        for (HeadersSearchPathTree myParent = ((PathTree<HeadersSearchPathTree, T>)this).getSubTree(path, SearchStrategy.DEEPEST_EXISTING_PREFIX); myParent != null; myParent = myParent.myParent) {
            for (final HeadersSearchPath headersSearchPath2 : ((PathTree<Self, HeadersSearchPath>)myParent).getItems()) {
                try {
                    if (headersSearchPath.isUserHeaders() != headersSearchPath2.isUserHeaders()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    if (headersSearchPath.isFrameworksSearchPath() != headersSearchPath2.isFrameworksSearchPath()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    if (headersSearchPath2.isRecursive()) {
                        return;
                    }
                    final HeadersSearchPath headersSearchPath3 = headersSearchPath;
                    final HeadersSearchPath headersSearchPath4 = headersSearchPath2;
                    final boolean b = headersSearchPath3.equals(headersSearchPath4);
                    if (b) {
                        return;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final HeadersSearchPath headersSearchPath3 = headersSearchPath;
                    final HeadersSearchPath headersSearchPath4 = headersSearchPath2;
                    final boolean b = headersSearchPath3.equals(headersSearchPath4);
                    if (b) {
                        return;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
        }
        ((PathTree<Self, HeadersSearchPath>)this).addItem(path, headersSearchPath);
        this.myCompactedPaths.add(headersSearchPath);
    }
    
    @NotNull
    public List<HeadersSearchPath> getCompactedPaths() {
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends HeadersSearchPath>)this.myCompactedPaths);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPathTree", "getCompactedPaths"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<HeadersSearchPath>)unmodifiableList;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
