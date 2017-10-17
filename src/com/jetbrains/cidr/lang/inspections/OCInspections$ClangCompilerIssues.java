// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.codeStyle.NameUtil;

public abstract static class ClangCompilerIssues extends ObjC
{
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        final String[] nameToWords = NameUtil.nameToWords(this.getClass().getSimpleName());
        String join;
        try {
            join = StringUtil.join(nameToWords, " ");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ClangCompilerIssues", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return join;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
