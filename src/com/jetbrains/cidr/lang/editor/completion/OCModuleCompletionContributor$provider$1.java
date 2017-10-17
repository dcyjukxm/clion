// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Function;
import java.util.List;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapManager;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.impl.OCImportModuleStatementImpl;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r" }, d2 = { "com/jetbrains/cidr/lang/editor/completion/OCModuleCompletionContributor$provider$1", "Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionProvider;", "(Lcom/jetbrains/cidr/lang/editor/completion/OCModuleCompletionContributor;)V", "addCompletions", "", "prefix", "", "parameters", "Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionParameters;", "context", "Lcom/intellij/util/ProcessingContext;", "result", "Lcom/intellij/codeInsight/completion/CompletionResultSet;", "cidr-lang" })
public static final class OCModuleCompletionContributor$provider$1 extends OCCompletionProvider {
    @Override
    protected void addCompletions(@NotNull final String s, @NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
        Intrinsics.checkParameterIsNotNull((Object)s, "prefix");
        Intrinsics.checkParameterIsNotNull((Object)ocCompletionParameters, "parameters");
        Intrinsics.checkParameterIsNotNull((Object)processingContext, "context");
        Intrinsics.checkParameterIsNotNull((Object)set, "result");
        PsiElement parent;
        if (!((parent = ocCompletionParameters.getRealPosition().getParent()) instanceof OCImportModuleStatementImpl)) {
            parent = null;
        }
        final OCImportModuleStatementImpl ocImportModuleStatementImpl = (OCImportModuleStatementImpl)parent;
        if (ocImportModuleStatementImpl == null) {
            return;
        }
        final OCImportModuleStatementImpl ocImportModuleStatementImpl2 = ocImportModuleStatementImpl;
        final OCResolveConfiguration activeConfiguration = OCInclusionContextUtil.getActiveConfiguration((PsiElement)ocImportModuleStatementImpl2);
        if (activeConfiguration != null) {
            final OCResolveConfiguration ocResolveConfiguration = activeConfiguration;
            final ModuleMapManager.Companion companion = ModuleMapManager.Companion;
            final Project project = ocImportModuleStatementImpl2.getProject();
            Intrinsics.checkExpressionValueIsNotNull((Object)project, "element.project");
            final ModuleMapManager instance = companion.getInstance(project);
            final OCResolveConfiguration ocResolveConfiguration2 = ocResolveConfiguration;
            Intrinsics.checkExpressionValueIsNotNull((Object)ocResolveConfiguration2, "configuration");
            set.addAllElements((Iterable)ContainerUtil.mapNotNull((Collection)instance.getModules(ocResolveConfiguration2).getModules(), (Function)new OCModuleCompletionContributor$provider$1$addCompletions.OCModuleCompletionContributor$provider$1$addCompletions$1(this, set)));
        }
    }
}