// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import java.util.Iterator;
import com.intellij.openapi.util.TextRange;
import kotlin.Pair;
import com.intellij.psi.PsiDocumentManager;
import kotlin.jvm.internal.Ref$IntRef;
import com.intellij.openapi.editor.Document;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "", "run" })
static final class OCRemoveBodiesAndComments$actionPerformed$3 implements Runnable {
    @Override
    public final void run() {
        for (final Pair pair : this.$toReplace) {
            final TextRange textRange = (TextRange)pair.component1();
            final String s = (String)pair.component2();
            this.$document.replaceString(textRange.getStartOffset() - this.$correction.element, textRange.getEndOffset() - this.$correction.element, (CharSequence)s);
            final Ref$IntRef $correction = this.$correction;
            $correction.element += textRange.getLength() - s.length();
        }
        this.$documentManager.commitDocument(this.$document);
    }
}