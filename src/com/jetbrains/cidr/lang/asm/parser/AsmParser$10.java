// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

static final class AsmParser$10 implements GeneratedParserUtilBase.Parser {
    @Override
    public boolean parse(final PsiBuilder psiBuilder, final int n) {
        return AsmParser.atLeastOneOf3(psiBuilder, n + 1, AsmParser.register_parser_, AsmParser.memory_indirect_0_0_1_parser_, AsmParser.memory_indirect_0_0_2_parser_);
    }
}