// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl.legacy;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.psi.PsiElement;
import kotlin.jvm.internal.Intrinsics;
import java.util.Collections;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleMember;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapAttributes;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleDeclaration;
import com.intellij.psi.impl.FakePsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016¨\u0006\u000f" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyInferredSubmoduleDeclaration;", "Lcom/intellij/psi/impl/FakePsiElement;", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapInferredSubmoduleDeclaration;", "()V", "getAttributes", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapAttributes;", "getInferredSubmoduleMemberList", "", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapInferredSubmoduleMember;", "getParent", "Lcom/intellij/psi/PsiElement;", "isExplicit", "", "isFramework", "Companion", "cidr-lang" })
public final class ModuleMapLegacyInferredSubmoduleDeclaration extends FakePsiElement implements ModuleMapInferredSubmoduleDeclaration
{
    @NotNull
    private static final ModuleMapLegacyInferredSubmoduleDeclaration INSTANCE;
    public static final Companion Companion;
    
    @Nullable
    @Override
    public ModuleMapAttributes getAttributes() {
        return null;
    }
    
    @NotNull
    @Override
    public List<ModuleMapInferredSubmoduleMember> getInferredSubmoduleMemberList() {
        final List<ModuleMapLegacyInferredSubmoduleMember> singletonList = Collections.singletonList(new ModuleMapLegacyInferredSubmoduleMember());
        Intrinsics.checkExpressionValueIsNotNull((Object)singletonList, "Collections.singletonLis\u2026nferredSubmoduleMember())");
        return (List<ModuleMapInferredSubmoduleMember>)singletonList;
    }
    
    @Override
    public boolean isExplicit() {
        return false;
    }
    
    @Override
    public boolean isFramework() {
        return false;
    }
    
    @Nullable
    public PsiElement getParent() {
        return null;
    }
    
    static {
        Companion = new Companion(null);
        INSTANCE = new ModuleMapLegacyInferredSubmoduleDeclaration();
    }
    
    @NotNull
    public static final /* synthetic */ ModuleMapLegacyInferredSubmoduleDeclaration access$getINSTANCE$cp() {
        return ModuleMapLegacyInferredSubmoduleDeclaration.INSTANCE;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyInferredSubmoduleDeclaration$Companion;", "", "()V", "INSTANCE", "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyInferredSubmoduleDeclaration;", "getINSTANCE", "()Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyInferredSubmoduleDeclaration;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final ModuleMapLegacyInferredSubmoduleDeclaration getINSTANCE() {
            return ModuleMapLegacyInferredSubmoduleDeclaration.access$getINSTANCE$cp();
        }
    }
}
