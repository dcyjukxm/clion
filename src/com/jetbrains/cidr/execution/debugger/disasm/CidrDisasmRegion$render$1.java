// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import org.jetbrains.annotations.NotNull;
import kotlin.text.StringsKt;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.debugger.backend.LLInstruction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\f\b\u0000\u0010\u0002*\u00060\u0003j\u0002`\u00042\u000e\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\b" }, d2 = { "<anonymous>", "", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "it", "Lcom/jetbrains/cidr/execution/debugger/backend/LLInstruction;", "kotlin.jvm.PlatformType", "invoke" })
static final class CidrDisasmRegion$render$1 extends Lambda implements Function1<LLInstruction, String> {
    public static final CidrDisasmRegion$render$1 INSTANCE;
    
    @NotNull
    public final String invoke(final LLInstruction llInstruction) {
        return '\t' + StringsKt.padEnd$default(llInstruction.getDisassembly(), 54, '\0', 2, (Object)null) + " ! " + llInstruction.getAddress() + '\n';
    }
    
    static {
        CidrDisasmRegion$render$1.INSTANCE = new CidrDisasmRegion$render$1();
    }
}