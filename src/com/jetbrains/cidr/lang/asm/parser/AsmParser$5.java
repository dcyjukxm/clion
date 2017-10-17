// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

static final class AsmParser$5 implements GeneratedParserUtilBase.Parser {
    @Override
    public boolean parse(final PsiBuilder psiBuilder, final int n) {
        return AsmParser.expression(psiBuilder, n + 1);
    }
}