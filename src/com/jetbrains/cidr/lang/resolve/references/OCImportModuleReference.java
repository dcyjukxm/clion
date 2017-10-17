// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.modulemap.ModuleMapModules;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapManager;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.util.ArrayUtil;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiManager;
import com.intellij.util.Function;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapResolveUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import com.intellij.psi.ResolveResult;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.psi.impl.OCImportModuleStatementImpl;
import com.intellij.psi.PsiPolyVariantReferenceBase;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011" }, d2 = { "Lcom/jetbrains/cidr/lang/resolve/references/OCImportModuleReference;", "Lcom/intellij/psi/PsiPolyVariantReferenceBase;", "Lcom/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl;", "element", "(Lcom/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl;)V", "getModule", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getVariants", "", "", "()[Ljava/lang/Object;", "isValid", "", "multiResolve", "Lcom/intellij/psi/ResolveResult;", "incompleteCode", "(Z)[Lcom/intellij/psi/ResolveResult;", "cidr-lang" })
public final class OCImportModuleReference extends PsiPolyVariantReferenceBase<OCImportModuleStatementImpl>
{
    @NotNull
    public ResolveResult[] multiResolve(final boolean b) {
        final ModuleMapModuleSymbol a = this.a();
        if (a != null) {
            final Object[] objectArray = ArrayUtil.toObjectArray((Collection)ContainerUtil.mapNotNull((Collection)ModuleMapResolveUtil.getIncludeHeaders(a), (Function)new OCImportModuleReference$multiResolve$psiFiles.OCImportModuleReference$multiResolve$psiFiles$1(PsiManager.getInstance(((OCImportModuleStatementImpl)this.myElement).getProject()))), (Class)ResolveResult.class);
            Intrinsics.checkExpressionValueIsNotNull((Object)objectArray, "ArrayUtil.toObjectArray(\u2026esolveResult::class.java)");
            return (ResolveResult[])objectArray;
        }
        final ResolveResult[] empty_ARRAY = ResolveResult.EMPTY_ARRAY;
        Intrinsics.checkExpressionValueIsNotNull((Object)empty_ARRAY, "ResolveResult.EMPTY_ARRAY");
        return empty_ARRAY;
    }
    
    @NotNull
    public Object[] getVariants() {
        final Object[] empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
        Intrinsics.checkExpressionValueIsNotNull((Object)empty_OBJECT_ARRAY, "ArrayUtil.EMPTY_OBJECT_ARRAY");
        return empty_OBJECT_ARRAY;
    }
    
    public final boolean isValid() {
        return this.a() != null;
    }
    
    private final ModuleMapModuleSymbol a() {
        final OCResolveConfiguration activeConfiguration = OCInclusionContextUtil.getActiveConfiguration(this.myElement);
        if (activeConfiguration != null) {
            final OCResolveConfiguration ocResolveConfiguration = activeConfiguration;
            final ModuleMapManager.Companion companion = ModuleMapManager.Companion;
            final Project project = ((OCImportModuleStatementImpl)this.myElement).getProject();
            Intrinsics.checkExpressionValueIsNotNull((Object)project, "myElement.project");
            final ModuleMapManager instance = companion.getInstance(project);
            final OCResolveConfiguration ocResolveConfiguration2 = ocResolveConfiguration;
            Intrinsics.checkExpressionValueIsNotNull((Object)ocResolveConfiguration2, "configuration");
            final ModuleMapModules modules = instance.getModules(ocResolveConfiguration2);
            final String referenceText = ((OCImportModuleStatementImpl)this.myElement).getReferenceText();
            Intrinsics.checkExpressionValueIsNotNull((Object)referenceText, "myElement.referenceText");
            return modules.getModule(referenceText);
        }
        return null;
    }
    
    public OCImportModuleReference(@NotNull final OCImportModuleStatementImpl ocImportModuleStatementImpl) {
        Intrinsics.checkParameterIsNotNull((Object)ocImportModuleStatementImpl, "element");
        super((PsiElement)ocImportModuleStatementImpl);
    }
}
