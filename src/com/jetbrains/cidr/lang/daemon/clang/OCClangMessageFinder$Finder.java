// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang;

import org.jetbrains.annotations.Nullable;
import gnu.trove.TIntIterator;
import com.intellij.util.containers.IntArrayList;
import gnu.trove.TIntHashSet;
import com.intellij.openapi.util.text.StringUtil;
import java.util.List;

private class Finder
{
    private String myString;
    private List<String> myWords;
    
    Finder(final String s) {
        this.myString = s.toLowerCase();
        this.myWords = (List<String>)StringUtil.getWordsIn(this.myString);
    }
    
    private OCClangMessageDescriptor a() {
        final TIntHashSet set = new TIntHashSet(OCClangMessageFinder.access$000(OCClangMessageFinder.this).size());
        for (int i = 0; i < OCClangMessageFinder.access$000(OCClangMessageFinder.this).size(); ++i) {
            set.add(i);
        }
        if (!this.myWords.isEmpty()) {
            final Integer a = this.a(0, set, 0);
            if (a != null) {
                return (OCClangMessageDescriptor)OCClangMessageFinder.access$000(OCClangMessageFinder.this).get(a);
            }
        }
        else {
            OCClangMessageFinder.access$100().warn("No words found in the message \"" + this.myString + "\"");
        }
        return null;
    }
    
    @Nullable
    private Integer a(final int n, final TIntHashSet set, final int n2) {
        if (n < this.myWords.size()) {
            final TIntHashSet set2 = new TIntHashSet(set.toArray());
            final IntArrayList list = OCClangMessageFinder.access$200(OCClangMessageFinder.this).get(this.myWords.get(n));
            if (list != null) {
                set2.retainAll(list.toArray());
            }
            else {
                set2.clear();
            }
            if (!set2.isEmpty()) {
                final Integer a = this.a(n + 1, set2, n2 + 1);
                if (a != null) {
                    return a;
                }
            }
            final Integer a2 = this.a(n + 1, set, n2);
            if (a2 != null) {
                return a2;
            }
        }
        else {
            final TIntIterator iterator = set.iterator();
            while (iterator.hasNext()) {
                if (((OCClangMessageDescriptor)OCClangMessageFinder.access$000(OCClangMessageFinder.this).get(iterator.next())).getWordsCnt() > n2) {
                    iterator.remove();
                }
            }
            if (set.size() > 30) {
                OCClangMessageFinder.access$100().error("Too many possible patterns for \"" + this.myString + "\"");
                return null;
            }
            for (final int next : set) {
                if (((OCClangMessageDescriptor)OCClangMessageFinder.access$000(OCClangMessageFinder.this).get(next)).getPattern().matcher(this.myString).matches()) {
                    return next;
                }
            }
        }
        return null;
    }
}
