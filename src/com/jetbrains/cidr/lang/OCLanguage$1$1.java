// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.editor.colors.OCFileHighlighter;
import com.intellij.util.containers.FactoryMap;

class OCLanguage$1$1 extends FactoryMap<HighlighterKey, OCFileHighlighter> {
    @Nullable
    protected OCFileHighlighter create(final HighlighterKey highlighterKey) {
        return new OCFileHighlighter(highlighterKey.myLanguageKind, highlighterKey.mySupportNullability, highlighterKey.myAllowGccAutoType, highlighterKey.myAllowAvailabilityExpression);
    }
}