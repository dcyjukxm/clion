// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import java.awt.Component;
import javax.swing.JList;
import com.intellij.codeInsight.navigation.GotoTargetHandler;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.editor.Editor;
import java.awt.event.MouseEvent;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import javax.swing.Icon;
import com.intellij.psi.PsiElement;

public abstract class OCGotoAction
{
    protected PsiElement mySource;
    protected String myName;
    protected Icon myIcon;
    
    public OCGotoAction(@NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/navigation/OCGotoAction", "<init>"));
        }
        this(psiElement, null, null);
    }
    
    public OCGotoAction(@NotNull final PsiElement mySource, @Nullable final String myName, @Nullable final Icon myIcon) {
        if (mySource == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/navigation/OCGotoAction", "<init>"));
        }
        this.mySource = mySource;
        this.myName = myName;
        this.myIcon = myIcon;
    }
    
    public Icon getIcon() {
        return this.myIcon;
    }
    
    public String getName() {
        return this.myName;
    }
    
    @Nullable
    public abstract List<? extends OCSymbol> getTargets();
    
    public abstract void navigate(@Nullable final MouseEvent p0, final Editor p1);
    
    protected static class CellRenderer extends DefaultPsiElementCellRenderer
    {
        final GotoTargetHandler.GotoData myGotoData;
        
        public CellRenderer(final GotoTargetHandler.GotoData myGotoData) {
            this.myGotoData = myGotoData;
        }
        
        @Override
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            if (o == null) {
                return super.getListCellRendererComponent(list, null, n, b, b2);
            }
            return GotoTargetHandler.createRenderer(this.myGotoData, (PsiElement)o).getListCellRendererComponent(list, o, n, b, b2);
        }
    }
}
