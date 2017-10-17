// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;

class OCFormatProcessor extends OCFormatterUtil
{
    protected final CommonCodeStyleSettings settings;
    protected final OCCodeStyleSettings ocSettings;
    protected final ASTNode thisNode;
    protected final IElementType thisType;
    protected final ASTNode parentNode;
    protected final IElementType parentType;
    
    public OCFormatProcessor(final CommonCodeStyleSettings settings, final OCCodeStyleSettings ocSettings, final ASTNode thisNode) {
        this.settings = settings;
        this.ocSettings = ocSettings;
        this.thisNode = thisNode;
        this.thisType = this.thisNode.getElementType();
        this.parentNode = this.thisNode.getTreeParent();
        this.parentType = ((this.parentNode == null) ? null : OCElementUtil.getElementType(this.parentNode));
    }
    
    public OCCodeStyleSettings getOCSettings() {
        return this.ocSettings;
    }
    
    public CommonCodeStyleSettings getSettings() {
        return this.settings;
    }
}
