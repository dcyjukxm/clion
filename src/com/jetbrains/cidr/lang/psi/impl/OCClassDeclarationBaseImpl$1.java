// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import javax.swing.Icon;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.navigation.ItemPresentation;

class OCClassDeclarationBaseImpl$1 implements ItemPresentation {
    public String getPresentableText() {
        return OCCodeInsightUtil.getClassNameWithCategory(OCClassDeclarationBaseImpl.this.getName(), OCClassDeclarationBaseImpl.this.getCategory());
    }
    
    public String getLocationString() {
        final String access$000 = OCClassDeclarationBaseImpl.access$000(OCClassDeclarationBaseImpl.this.getContainingFile().getVirtualFile());
        return (access$000.length() == 0) ? OCClassDeclarationBaseImpl.this.getContainingFile().getVirtualFile().getName() : access$000;
    }
    
    public Icon getIcon(final boolean b) {
        return OCClassDeclarationBaseImpl.this.getIcon(0);
    }
}