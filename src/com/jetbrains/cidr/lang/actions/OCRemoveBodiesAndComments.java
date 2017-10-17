// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.command.WriteCommandAction;
import kotlin.jvm.internal.Ref$IntRef;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.Pair;
import com.intellij.openapi.util.TextRange;
import java.util.Comparator;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import kotlin.Metadata;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0017\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u000e" }, d2 = { "Lcom/jetbrains/cidr/lang/actions/OCRemoveBodiesAndComments;", "Lcom/intellij/openapi/actionSystem/AnAction;", "Lcom/intellij/openapi/project/DumbAware;", "()V", "actionPerformed", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "getCaretPos", "", "(Lcom/intellij/openapi/actionSystem/AnActionEvent;)Ljava/lang/Integer;", "getOCFile", "Lcom/jetbrains/cidr/lang/psi/OCFile;", "update", "cidr-lang" })
public final class OCRemoveBodiesAndComments extends AnAction implements DumbAware
{
    public void actionPerformed(@NotNull final AnActionEvent anActionEvent) {
        Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
        final OCFile a = this.a(anActionEvent);
        if (a == null) {
            return;
        }
        final PsiElement psiElement = (PsiElement)a;
        final Project eventProject = AnAction.getEventProject(anActionEvent);
        if (eventProject == null) {
            return;
        }
        final Project project = eventProject;
        final PsiDocumentManager instance = PsiDocumentManager.getInstance(project);
        final Document document = instance.getDocument((PsiFile)psiElement);
        if (document != null) {
            final Document document2 = document;
            final Integer b = this.b(anActionEvent);
            final ArrayList list = new ArrayList();
            PsiTreeUtil.processElements((PsiElement)psiElement, (PsiElementProcessor)new OCRemoveBodiesAndComments$actionPerformed.OCRemoveBodiesAndComments$actionPerformed$1(b, list));
            final ArrayList list2 = list;
            if (list2.size() > 1) {
                CollectionsKt.sortWith((List)list2, (Comparator)new Comparator<T>() {
                    @Override
                    public final int compare(final T t, final T t2) {
                        return ComparisonsKt.compareValues((Comparable)((TextRange)((Pair)t).getFirst()).getStartOffset(), (Comparable)((TextRange)((Pair)t2).getFirst()).getStartOffset());
                    }
                });
            }
            final Ref$IntRef ref$IntRef = new Ref$IntRef();
            ref$IntRef.element = 0;
            WriteCommandAction.runWriteCommandAction(project, (Runnable)new OCRemoveBodiesAndComments$actionPerformed.OCRemoveBodiesAndComments$actionPerformed$3(list, document2, ref$IntRef, instance));
        }
    }
    
    public void update(@NotNull final AnActionEvent anActionEvent) {
        Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(this.a(anActionEvent) != null);
    }
    
    private final OCFile a(final AnActionEvent anActionEvent) {
        Object data;
        if (!((data = anActionEvent.getData(CommonDataKeys.PSI_FILE)) instanceof OCFile)) {
            data = null;
        }
        return (OCFile)data;
    }
    
    private final Integer b(final AnActionEvent anActionEvent) {
        final Editor editor = (Editor)anActionEvent.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            final CaretModel caretModel = editor.getCaretModel();
            if (caretModel != null) {
                return caretModel.getOffset();
            }
        }
        return null;
    }
}
