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
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.ui.popup.PopupStep;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;

class OCImportSymbolFix$1 extends BaseListPopupStep<AutoImportItem> {
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiFile val$file;
    
    public boolean isAutoSelectionEnabled() {
        return false;
    }
    
    public boolean isSpeedSearchEnabled() {
        return true;
    }
    
    public PopupStep onChosen(final AutoImportItem autoImportItem, final boolean b) {
        try {
            if (autoImportItem == null) {
                return OCImportSymbolFix$1.FINAL_CHOICE;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                autoImportItem.invoke(this.val$project, this.val$file);
                return OCImportSymbolFix$1.FINAL_CHOICE;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return OCImportSymbolFix$1.FINAL_CHOICE;
    }
    
    public boolean hasSubstep(final AutoImportItem autoImportItem) {
        return false;
    }
    
    @NotNull
    public String getTextFor(final AutoImportItem autoImportItem) {
        final Pair<String, String> titleAndLocation = autoImportItem.getTitleAndLocation();
        String string;
        try {
            string = (String)titleAndLocation.first + " @ " + (String)titleAndLocation.second;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$1", "getTextFor"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    public Icon getIconFor(final AutoImportItem autoImportItem) {
        return autoImportItem.mySymbolToImport.getIcon();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}