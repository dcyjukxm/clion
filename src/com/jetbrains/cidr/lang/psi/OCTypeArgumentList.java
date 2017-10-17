// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;

public interface OCTypeArgumentList<T extends OCElement> extends OCElement
{
    List<? extends T> getArguments();
}
