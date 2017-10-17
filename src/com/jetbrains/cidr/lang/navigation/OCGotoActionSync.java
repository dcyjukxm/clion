// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.codeInsight.navigation.NavigationUtil;
import com.intellij.codeInsight.navigation.GotoTargetHandler;
import java.util.Collections;
import java.util.Collection;
import com.intellij.psi.util.PsiUtilCore;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.intellij.openapi.editor.Editor;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;

public abstract class OCGotoActionSync extends OCGotoAction
{
    private List<? extends OCSymbol> myTargets;
    private long myModificationCounter;
    
    public OCGotoActionSync(@NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/navigation/OCGotoActionSync", "<init>"));
        }
        super(psiElement);
        this.getTargets();
    }
    
    public OCGotoActionSync(@NotNull final PsiElement psiElement, @Nullable final String s, @Nullable final Icon icon) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/navigation/OCGotoActionSync", "<init>"));
        }
        super(psiElement, s, icon);
    }
    
    @Nullable
    @Override
    public List<? extends OCSymbol> getTargets() {
        final long outOfCodeBlockModificationCount = this.mySource.getManager().getModificationTracker().getOutOfCodeBlockModificationCount();
        Label_0041: {
            try {
                if (this.myTargets == null) {
                    break Label_0041;
                }
                final long n = outOfCodeBlockModificationCount;
                final OCGotoActionSync ocGotoActionSync = this;
                final long n2 = ocGotoActionSync.myModificationCounter;
                final long n3 = lcmp(n, n2);
                if (n3 != 0) {
                    break Label_0041;
                }
                return this.myTargets;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final long n = outOfCodeBlockModificationCount;
                final OCGotoActionSync ocGotoActionSync = this;
                final long n2 = ocGotoActionSync.myModificationCounter;
                final long n3 = lcmp(n, n2);
                if (n3 != 0) {
                    this.myModificationCounter = outOfCodeBlockModificationCount;
                    this.myTargets = this.evaluateTargets();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.myTargets;
    }
    
    @Nullable
    protected abstract List<? extends OCSymbol> evaluateTargets();
    
    @Override
    public void navigate(@Nullable final MouseEvent mouseEvent, final Editor editor) {
        final List<? extends OCSymbol> targets = this.getTargets();
        try {
            if (targets == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (targets.size() != 0) {
            final OCSymbol ocSymbol = targets.iterator().next();
            try {
                if (ocSymbol != null) {
                    ocSymbol.navigate(true);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        else {
            final List<PsiElement> locateDefinitions = OCSymbolBase.locateDefinitions(targets);
            try {
                if (locateDefinitions.isEmpty()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final PsiElement[] psiElementArray = PsiUtilCore.toPsiElementArray((Collection)locateDefinitions);
            final JBPopup psiElementPopup = NavigationUtil.getPsiElementPopup(psiElementArray, new CellRenderer(new GotoTargetHandler.GotoData(this.mySource, psiElementArray, Collections.emptyList())), this.myName);
            try {
                if (mouseEvent != null) {
                    psiElementPopup.show(new RelativePoint(mouseEvent));
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            psiElementPopup.showInBestPositionFor(editor);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
