// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.jetbrains.cidr.modulemap.ModuleMapLanguage;
import com.intellij.psi.tree.IFileElementType;

public class ModuleMapFileElementType extends IFileElementType
{
    public ModuleMapFileElementType() {
        super((Language)ModuleMapLanguage.INSTANCE);
    }
    
    @Nullable
    public ASTNode createNode(final CharSequence text) {
        return (ASTNode)new FileElement((IElementType)this, text);
    }
}
