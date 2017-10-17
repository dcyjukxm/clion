// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl.legacy;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderName;
import com.intellij.psi.impl.FakePsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyUmbrellaHeaderName;", "Lcom/intellij/psi/impl/FakePsiElement;", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapHeaderName;", "frameworkName", "", "(Ljava/lang/String;)V", "getFrameworkName", "()Ljava/lang/String;", "getParent", "Lcom/intellij/psi/PsiElement;", "getText", "cidr-lang" })
public final class ModuleMapLegacyUmbrellaHeaderName extends FakePsiElement implements ModuleMapHeaderName
{
    @NotNull
    private final String frameworkName;
    
    @Nullable
    public PsiElement getParent() {
        return null;
    }
    
    @Nullable
    @Override
    public String getText() {
        return "" + this.frameworkName + ".h";
    }
    
    @NotNull
    public final String getFrameworkName() {
        return this.frameworkName;
    }
    
    public ModuleMapLegacyUmbrellaHeaderName(@NotNull final String frameworkName) {
        Intrinsics.checkParameterIsNotNull((Object)frameworkName, "frameworkName");
        this.frameworkName = frameworkName;
    }
}
