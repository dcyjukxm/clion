// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.ASTNode;

public interface LazyParsingCallback
{
    void onParsingLazyBlock(final ASTNode p0, final PsiBuilder p1, final OCParsing p2);
}
