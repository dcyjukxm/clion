// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;

class OCPreprocessingLexer$2 implements Function<CharSequence, Boolean> {
    public Boolean fun(final CharSequence charSequence) {
        final List wordsIn = StringUtil.getWordsIn(charSequence.toString());
        return !OCPreprocessingLexer.access$000(OCPreprocessingLexer.this).isDefined(wordsIn.isEmpty() ? charSequence.toString().trim() : wordsIn.get(0));
    }
}