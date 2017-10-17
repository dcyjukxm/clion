// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

private interface Handler
{
    boolean handle(final char p0, final PsiFile p1, final Editor p2, final CodeStyleSettings p3);
}
