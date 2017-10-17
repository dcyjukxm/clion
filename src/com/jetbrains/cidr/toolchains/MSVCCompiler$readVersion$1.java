// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import org.jetbrains.annotations.Nullable;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.collections.SetsKt;
import kotlin.text.RegexOption;
import kotlin.Metadata;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.util.Function;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "output", "Lcom/intellij/execution/process/ProcessOutput;", "kotlin.jvm.PlatformType", "fun" })
static final class MSVCCompiler$readVersion$1<Param, Result> implements Function<ProcessOutput, String> {
    public static final MSVCCompiler$readVersion.MSVCCompiler$readVersion$1 INSTANCE;
    
    @Nullable
    public final String fun(final ProcessOutput processOutput) {
        final Regex regex = new Regex(".+Version (.+?)$", SetsKt.mutableSetOf((Object[])new RegexOption[] { RegexOption.DOT_MATCHES_ALL, RegexOption.MULTILINE }));
        final String stderr = processOutput.getStderr();
        Intrinsics.checkExpressionValueIsNotNull((Object)stderr, "output.stderr");
        final MatchResult find$default = Regex.find$default(regex, (CharSequence)stderr, 0, 2, (Object)null);
        if (find$default != null) {
            final MatchGroupCollection groups = find$default.getGroups();
            if (groups != null) {
                final MatchGroup value = groups.get(1);
                if (value != null) {
                    return value.getValue();
                }
            }
        }
        return null;
    }
    
    static {
        MSVCCompiler$readVersion.MSVCCompiler$readVersion$1.INSTANCE = new MSVCCompiler$readVersion.MSVCCompiler$readVersion$1();
    }
}