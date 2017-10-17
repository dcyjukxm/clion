// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Set;
import java.util.Map;

class 2Checker
{
    final /* synthetic */ Map val$result;
    final /* synthetic */ Set val$set;
    
    2Checker(final Map val$result, final Set val$set) {
        this.val$result = val$result;
        this.val$set = val$set;
    }
    
    void add(final OCCompilerFeatures.Diagnostic diagnostic, final boolean b, final boolean b2, final String s) {
        final OCCompilerFeatures.DiagnosticLevel a = this.a(b, b2, s);
        this.val$result.put(diagnostic, (a != null) ? a : OCCompilerFeatures.DiagnosticLevel.DISABLED);
    }
    
    @Nullable
    private OCCompilerFeatures.DiagnosticLevel a(final boolean b, final boolean b2, final String s) {
        if ((b && b2) || this.val$set.contains("-Werror=" + s)) {
            return OCCompilerFeatures.DiagnosticLevel.ERROR;
        }
        if (b || this.val$set.contains("-W" + s)) {
            return OCCompilerFeatures.DiagnosticLevel.WARNING;
        }
        return null;
    }
}
