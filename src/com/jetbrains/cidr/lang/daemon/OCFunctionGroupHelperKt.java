// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n" }, d2 = { "annotateAmbig", "", "sink", "Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;", "element", "Lcom/intellij/psi/PsiElement;", "group", "Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;", "noMatchingMessage", "", "cidr-lang" })
public final class OCFunctionGroupHelperKt
{
    public static final boolean annotateAmbig(@NotNull final OCAnnotatorSink ocAnnotatorSink, @NotNull final PsiElement psiElement, @NotNull final OCResolveOverloadsUtil.OCFunctionGroupSymbol ocFunctionGroupSymbol, @NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)ocAnnotatorSink, "sink");
        Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
        Intrinsics.checkParameterIsNotNull((Object)ocFunctionGroupSymbol, "group");
        Intrinsics.checkParameterIsNotNull((Object)s, "noMatchingMessage");
        final OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause cause = ocFunctionGroupSymbol.getCause();
        if (cause != null) {
            switch (OCFunctionGroupHelperKt$WhenMappings.$EnumSwitchMapping$0[cause.ordinal()]) {
                case 1: {
                    ocAnnotatorSink.addErrorAnnotation(psiElement, "err_ovl_ambiguous_call", "Call to \"" + ocFunctionGroupSymbol.getName() + "\" is ambiguous");
                    return false;
                }
                case 2: {
                    ocAnnotatorSink.addErrorAnnotation(psiElement, "err_ovl_no_viable_member_function_in_call", s);
                    return false;
                }
            }
        }
        return true;
    }
}
