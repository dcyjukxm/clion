// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u000b\u0012\u0002\b\u0003\u0018\u00010\u0003¨\u0006\u00010\u0003¨\u0006\u00012\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007H\n¢\u0006\u0002\b\b" }, d2 = { "<anonymous>", "", "symbol", "Lcom/jetbrains/cidr/lang/symbols/OCSymbol;", "Lcom/intellij/psi/PsiElement;", "kotlin.jvm.PlatformType", "visibility", "Lcom/jetbrains/cidr/lang/symbols/OCVisibility;", "process" })
static final class OCGenerateStreamOutputOperatorHandler$getReplacements$1 implements BaseClassProcessor {
    @Override
    public final boolean process(final OCSymbol<PsiElement> ocSymbol, final OCVisibility ocVisibility) {
        if (this.$exprBuilder.length() > 0) {
            this.$exprBuilder.append(" << ' '");
        }
        this.$exprBuilder.append(" << ").append("static_cast<const " + ocSymbol.getType().getBestNameInContext(OCCallableUtil.getCorrectContextToCalculateNames(this.$element)) + " &>(" + this.$varName + ')');
        return true;
    }
}