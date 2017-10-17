// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.util.TextRange;

private interface DocumentModifier
{
    TextRange change(final boolean p0, final String p1, final String p2);
}
