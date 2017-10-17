// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.psi.PsiElement;
import java.awt.Component;
import javax.swing.JList;
import com.intellij.codeInsight.navigation.GotoTargetHandler;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;

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
