// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.lang.TokenWrapper;

public class OCPragmaOnceContentElementType extends TokenWrapper
{
    @Nullable
    private final VirtualFile myFile;
    
    public OCPragmaOnceContentElementType(@Nullable final VirtualFile myFile) {
        super(OCTokenTypes.PRAGMA_ONCE_LITERAL, OCTokenTypes.PRAGMA_ONCE_LITERAL.getName());
        this.myFile = myFile;
    }
    
    @Nullable
    public VirtualFile getFile() {
        return this.myFile;
    }
}
