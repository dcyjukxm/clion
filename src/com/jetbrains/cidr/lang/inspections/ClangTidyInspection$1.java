// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.net.URI;
import java.awt.Desktop;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

static final class ClangTidyInspection$1 implements HyperlinkListener {
    @Override
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://clang.llvm.org/extra/clang-tidy/#using-clang-tidy"));
            }
            catch (Exception ex) {}
        }
    }
}