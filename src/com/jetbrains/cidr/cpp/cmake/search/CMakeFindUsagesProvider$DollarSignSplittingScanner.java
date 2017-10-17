// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.search;

import com.intellij.lang.cacheBuilder.WordOccurrence;
import com.intellij.util.Processor;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lexer.Lexer;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;

private static class DollarSignSplittingScanner extends DefaultWordsScanner
{
    public DollarSignSplittingScanner(final Lexer lexer, final TokenSet set, final TokenSet set2, final TokenSet set3) {
        this(lexer, set, set2, set3, TokenSet.EMPTY);
    }
    
    public DollarSignSplittingScanner(final Lexer lexer, final TokenSet set, final TokenSet set2, final TokenSet set3, final TokenSet set4) {
        super(lexer, set, set2, set3, set4);
    }
    
    public void processWords(final CharSequence charSequence, final Processor<WordOccurrence> processor) {
        super.processWords(charSequence, wordOccurrence -> {
            final CharSequence subSequence = wordOccurrence.getBaseText().subSequence(wordOccurrence.getStart(), wordOccurrence.getEnd());
            if (!processor.process((Object)wordOccurrence)) {
                return false;
            }
            int start = wordOccurrence.getStart();
            for (int i = 0; i < subSequence.length(); ++i) {
                if (subSequence.charAt(i) == '$') {
                    if (wordOccurrence.getBaseText().subSequence(start, wordOccurrence.getStart() + i).length() > 0 && !processor.process((Object)new WordOccurrence(wordOccurrence.getBaseText(), start, wordOccurrence.getStart() + i, wordOccurrence.getKind()))) {
                        return false;
                    }
                    start = i + 1;
                }
            }
            return start == wordOccurrence.getStart() || wordOccurrence.getBaseText().subSequence(wordOccurrence.getStart() + start, wordOccurrence.getEnd()).length() <= 0 || processor.process((Object)new WordOccurrence(wordOccurrence.getBaseText(), wordOccurrence.getStart() + start, wordOccurrence.getEnd(), wordOccurrence.getKind()));
        });
    }
}
