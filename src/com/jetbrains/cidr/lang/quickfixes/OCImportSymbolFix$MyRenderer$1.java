// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.speedSearch.SpeedSearchUtil;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;
import javax.swing.JList;
import com.intellij.ui.speedSearch.SpeedSearch;
import com.intellij.ui.ColoredListCellRenderer;

class OCImportSymbolFix$MyRenderer$1 extends ColoredListCellRenderer<AutoImportItem> {
    final /* synthetic */ SpeedSearch val$speedSearch;
    
    protected void customizeCellRenderer(@NotNull final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$MyRenderer$1", "customizeCellRenderer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        MyRenderer.this.myLeft.clear();
        final Pair<String, String> titleAndLocation = autoImportItem.getTitleAndLocation();
        MyRenderer.this.myLeft.setIcon(autoImportItem.mySymbolToImport.getIcon());
        SpeedSearchUtil.appendColoredFragmentForMatcher((String)titleAndLocation.first, (SimpleColoredComponent)this, SimpleTextAttributes.REGULAR_ATTRIBUTES, this.val$speedSearch.getMatcher(), MyRenderer.this.getBackground(), b);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}