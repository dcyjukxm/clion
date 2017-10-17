// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import com.jetbrains.cidr.modulemap.psi.ModuleMapFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.Metadata;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.PsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004" }, d2 = { "<anonymous>", "", "element", "Lcom/intellij/psi/PsiFileSystemItem;", "execute" })
static final class ModuleMapDiskCache$calculateHashCode$1<T extends PsiElement> implements PsiElementProcessor<PsiFileSystemItem> {
    public final boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
        Intrinsics.checkParameterIsNotNull((Object)psiFileSystemItem, "element");
        if (psiFileSystemItem instanceof OCFile || psiFileSystemItem instanceof ModuleMapFile) {
            final Ref$LongRef $hash = this.$hash;
            $hash.element += psiFileSystemItem.getVirtualFile().getTimeStamp();
        }
        return true;
    }
}