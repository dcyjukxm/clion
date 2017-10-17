// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

static final class ModuleMapParser$1 implements GeneratedParserUtilBase.Parser {
    @Override
    public boolean parse(final PsiBuilder psiBuilder, final int n) {
        return ModuleMapParser.module_members_recoverer(psiBuilder, n + 1);
    }
}