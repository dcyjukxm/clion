// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import com.intellij.psi.tree.IElementType;

public interface TokenIterator
{
    Marker mark();
    
    IElementType getTokenType();
    
    boolean atEnd();
    
    void retreat();
    
    void skipWhitespaces();
    
    public interface Marker
    {
        void rollback();
    }
}
