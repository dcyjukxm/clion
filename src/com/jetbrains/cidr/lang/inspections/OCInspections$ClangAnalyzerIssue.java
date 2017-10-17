// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public abstract static class ClangAnalyzerIssue extends ObjC implements Hidden
{
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        final String fakeInspectionName = OCInspectionToolProvider.getInstance().getFakeInspectionName(this.getClass());
        String s = null;
        Label_0027: {
            try {
                if (fakeInspectionName != null) {
                    final String displayName;
                    s = (displayName = fakeInspectionName);
                    break Label_0027;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            String displayName;
            s = (displayName = super.getDisplayName());
            try {
                if (displayName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ClangAnalyzerIssue", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
        }
        return s;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
