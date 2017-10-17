// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.speedSearch.SpeedSearchUtil;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;
import javax.swing.JList;
import com.intellij.ui.speedSearch.SpeedSearch;
import com.intellij.ui.ColoredListCellRenderer;

class OCImportSymbolFix$MyRenderer$2 extends ColoredListCellRenderer<AutoImportItem> {
    final /* synthetic */ SpeedSearch val$speedSearch;
    
    protected void customizeCellRenderer(@NotNull final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$MyRenderer$2", "customizeCellRenderer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        SpeedSearchUtil.appendColoredFragmentForMatcher((String)autoImportItem.getTitleAndLocation().second, (SimpleColoredComponent)this, SimpleTextAttributes.GRAY_ATTRIBUTES, this.val$speedSearch.getMatcher(), MyRenderer.this.getBackground(), b);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}