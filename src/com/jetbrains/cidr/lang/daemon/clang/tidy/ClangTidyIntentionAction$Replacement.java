// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.RangeMarker;

static class Replacement
{
    @NotNull
    private final RangeMarker myRangeMarker;
    @NotNull
    private final String myOldText;
    @NotNull
    private final String myReplacementText;
    
    public Replacement(@NotNull final RangeMarker myRangeMarker, @NotNull final String myOldText, @NotNull final String myReplacementText) {
        if (myRangeMarker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeMarker", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement", "<init>"));
        }
        if (myOldText == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldText", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement", "<init>"));
        }
        if (myReplacementText == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacementText", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement", "<init>"));
        }
        this.myRangeMarker = myRangeMarker;
        this.myOldText = myOldText;
        this.myReplacementText = myReplacementText;
    }
    
    public boolean isValid() {
        try {
            if (!this.myRangeMarker.isValid()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myRangeMarker.getDocument().getText(new TextRange(this.myRangeMarker.getStartOffset(), this.myRangeMarker.getEndOffset())).equals(this.myOldText);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
