// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.psi.tree.IElementType;

public class CMakeKeywordProvider extends AbstractCMakeCaseSensitiveCompletionProvider
{
    public CMakeKeywordProvider(final IElementType[] array, final boolean b, final boolean b2) {
        super(array, b, b2);
    }
    
    public CMakeKeywordProvider(final String[] array, final boolean b, final boolean b2) {
        super(array, b, b2);
    }
}
