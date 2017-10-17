// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.util.Set;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.headerRoots.PathTree;

private static class RootTree extends PathTree<RootTree, RootItem>
{
    @Override
    protected RootTree createNewTree(@Nullable final RootTree rootTree) {
        return new RootTree();
    }
    
    public void addAll(@NotNull final Collection<File> collection, @NotNull final RootKind rootKind) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootTree", "addAll"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (rootKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootTree", "addAll"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        for (final File file : collection) {
            ((PathTree<Self, RootItem>)this).addItem(file.getPath(), new RootItem(file, rootKind));
        }
    }
    
    public void accept(@NotNull final Visitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootTree", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        visitor.enter();
        if (visitor.visit(((PathTree<Self, RootItem>)this).getItems())) {
            final Iterator<RootTree> iterator = ((PathTree<RootTree, T>)this).getChildren().iterator();
            while (iterator.hasNext()) {
                iterator.next().accept(visitor);
            }
        }
        visitor.exit();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public interface Visitor
    {
        void enter();
        
        boolean visit(@NotNull final Set<RootItem> p0);
        
        void exit();
    }
}
