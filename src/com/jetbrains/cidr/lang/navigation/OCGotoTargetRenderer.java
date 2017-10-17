// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import org.jetbrains.annotations.Nullable;
import com.intellij.ide.util.ModuleRendererFactory;
import com.intellij.ide.ui.UISettings;
import com.intellij.psi.PsiElement;
import javax.swing.DefaultListCellRenderer;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;

public class OCGotoTargetRenderer extends DefaultPsiElementCellRenderer
{
    @Nullable
    @Override
    protected DefaultListCellRenderer getRightCellRenderer(final Object o) {
        DefaultListCellRenderer defaultListCellRenderer = super.getRightCellRenderer(o);
        if (defaultListCellRenderer == null && o instanceof PsiElement && UISettings.getInstance().getShowIconInQuickNavigation()) {
            final ModuleRendererFactory instance = ModuleRendererFactory.findInstance(o);
            if (instance != null) {
                defaultListCellRenderer = instance.getModuleRenderer();
            }
        }
        return defaultListCellRenderer;
    }
    
    @Nullable
    @Override
    protected String getContainerTextForLeftComponent(final PsiElement psiElement, final String s) {
        return null;
    }
}
