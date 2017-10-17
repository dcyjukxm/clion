// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.lang.NodeStructure;

public interface NamedNodeStructure<T> extends NodeStructure<T>
{
    String getNodeText(final T p0);
}
