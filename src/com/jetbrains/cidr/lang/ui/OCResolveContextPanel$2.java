// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import org.jetbrains.annotations.NotNull;
import java.awt.event.MouseEvent;
import com.intellij.ui.ClickListener;

class OCResolveContextPanel$2 extends ClickListener {
    public boolean onClick(@NotNull final MouseEvent mouseEvent, final int n) {
        try {
            if (mouseEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$2", "onClick"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (OCResolveContextPanel.access$200(OCResolveContextPanel.this)) {
                OCResolveContextPanel.access$300(OCResolveContextPanel.this, mouseEvent);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}