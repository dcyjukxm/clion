// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import java.awt.Component;
import javax.swing.JList;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.ide.util.NavigationItemListCellRenderer;
import com.intellij.ide.actions.SearchEverywhereClassifier;

public class OCSearchEverywhereClassifier implements SearchEverywhereClassifier
{
    private NavigationItemListCellRenderer myRenderer;
    
    @Override
    public boolean isClass(@Nullable final Object o) {
        Label_0021: {
            try {
                if (o instanceof OCClassSymbol) {
                    break Label_0021;
                }
                final Object o2 = o;
                final boolean b = o2 instanceof OCStructSymbol;
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final Object o2 = o;
                final boolean b = o2 instanceof OCStructSymbol;
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
    
    @Override
    public boolean isSymbol(@Nullable final Object o) {
        return o instanceof OCSymbol;
    }
    
    @Nullable
    @Override
    public VirtualFile getVirtualFile(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/search/OCSearchEverywhereClassifier", "getVirtualFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.isSymbol(o)) {
                return ((OCSymbol)o).getContainingFile();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        Label_0022: {
            try {
                if (!this.isSymbol(o)) {
                    return null;
                }
                final OCSearchEverywhereClassifier ocSearchEverywhereClassifier = this;
                final NavigationItemListCellRenderer navigationItemListCellRenderer = ocSearchEverywhereClassifier.myRenderer;
                if (navigationItemListCellRenderer == null) {
                    break Label_0022;
                }
                return this.myRenderer.getListCellRendererComponent(list, o, n, b, b2);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSearchEverywhereClassifier ocSearchEverywhereClassifier = this;
                final NavigationItemListCellRenderer navigationItemListCellRenderer = ocSearchEverywhereClassifier.myRenderer;
                if (navigationItemListCellRenderer == null) {
                    this.myRenderer = new NavigationItemListCellRenderer();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.myRenderer.getListCellRendererComponent(list, o, n, b, b2);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
