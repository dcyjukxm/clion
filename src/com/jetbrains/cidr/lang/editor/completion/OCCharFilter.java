// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.codeInsight.lookup.CharFilter;

public class OCCharFilter extends CharFilter
{
    @Override
    public Result acceptChar(final char c, final int n, final Lookup lookup) {
        final LookupElement currentItem = lookup.getCurrentItem();
        if (currentItem == null) {
            return null;
        }
        if (c == '@' && n == 0) {
            return Result.ADD_TO_PREFIX;
        }
        final Object object = currentItem.getObject();
        if ((object instanceof OCSymbol || object instanceof TemplateInsertHandler.TemplateObject) && (c == '*' || c == ']')) {
            return Result.SELECT_ITEM_AND_FINISH_LOOKUP;
        }
        return null;
    }
}
