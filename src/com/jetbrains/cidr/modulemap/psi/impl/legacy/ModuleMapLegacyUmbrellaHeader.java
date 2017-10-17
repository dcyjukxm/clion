// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl.legacy;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderName;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;
import com.intellij.psi.impl.FakePsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyUmbrellaHeader;", "Lcom/intellij/psi/impl/FakePsiElement;", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapHeaderDeclaration;", "frameworkName", "", "(Ljava/lang/String;)V", "getFrameworkName", "()Ljava/lang/String;", "getHeaderName", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapHeaderName;", "getParent", "Lcom/intellij/psi/PsiElement;", "isExclude", "", "isPrivate", "isTextual", "isUmbrella", "cidr-lang" })
public final class ModuleMapLegacyUmbrellaHeader extends FakePsiElement implements ModuleMapHeaderDeclaration
{
    @NotNull
    private final String frameworkName;
    
    @Nullable
    public PsiElement getParent() {
        return null;
    }
    
    @Nullable
    @Override
    public ModuleMapHeaderName getHeaderName() {
        return new ModuleMapLegacyUmbrellaHeaderName(this.frameworkName);
    }
    
    @Override
    public boolean isUmbrella() {
        return true;
    }
    
    @Override
    public boolean isExclude() {
        return false;
    }
    
    @Override
    public boolean isPrivate() {
        return false;
    }
    
    @Override
    public boolean isTextual() {
        return false;
    }
    
    @NotNull
    public final String getFrameworkName() {
        return this.frameworkName;
    }
    
    public ModuleMapLegacyUmbrellaHeader(@NotNull final String frameworkName) {
        Intrinsics.checkParameterIsNotNull((Object)frameworkName, "frameworkName");
        this.frameworkName = frameworkName;
    }
}
