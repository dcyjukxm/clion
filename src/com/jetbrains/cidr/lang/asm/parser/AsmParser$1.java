// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.parser;

import com.jetbrains.cidr.lang.asm.psi.AsmTypes;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

static final class AsmParser$1 implements GeneratedParserUtilBase.Parser {
    @Override
    public boolean parse(final PsiBuilder psiBuilder, final int n) {
        return GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.INTEGER);
    }
}