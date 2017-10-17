// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.CodeInsightActionHandler;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\u000e" }, d2 = { "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorAction;", "Lcom/jetbrains/cidr/lang/generate/actions/OCCppGenerateAction;", "()V", "getHandler", "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorHandler;", "isValidForFile", "", "project", "Lcom/intellij/openapi/project/Project;", "editor", "Lcom/intellij/openapi/editor/Editor;", "file", "Lcom/intellij/psi/PsiFile;", "Companion", "cidr-lang" })
public final class OCGenerateStreamOutputOperatorAction extends OCCppGenerateAction
{
    private static final OCGenerateStreamOutputOperatorHandler HANDLER;
    public static final Companion Companion;
    
    @NotNull
    protected OCGenerateStreamOutputOperatorHandler getHandler() {
        return OCGenerateStreamOutputOperatorAction.Companion.a();
    }
    
    protected boolean isValidForFile(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)editor, "editor");
        Intrinsics.checkParameterIsNotNull((Object)psiFile, "file");
        return OCGenerateStreamOutputOperatorAction.Companion.a().isValidFor(editor, psiFile);
    }
    
    static {
        Companion = new Companion(null);
        HANDLER = new OCGenerateStreamOutputOperatorHandler();
    }
    
    @NotNull
    public static final /* synthetic */ OCGenerateStreamOutputOperatorHandler access$getHANDLER$cp() {
        return OCGenerateStreamOutputOperatorAction.HANDLER;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorAction$Companion;", "", "()V", "HANDLER", "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorHandler;", "getHANDLER", "()Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorHandler;", "cidr-lang" })
    public static final class Companion
    {
        private final OCGenerateStreamOutputOperatorHandler a() {
            return OCGenerateStreamOutputOperatorAction.access$getHANDLER$cp();
        }
    }
}
