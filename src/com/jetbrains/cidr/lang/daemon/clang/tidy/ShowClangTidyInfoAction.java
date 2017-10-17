// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class ShowClangTidyInfoAction extends AnAction implements DumbAware
{
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        boolean enabledAndVisible = true;
        if (!ApplicationManager.getApplication().isInternal() && !Registry.is("cidr.show.clangtidy.info")) {
            enabledAndVisible = false;
        }
        if (anActionEvent.getData(CommonDataKeys.PSI_FILE) == null) {
            enabledAndVisible = false;
        }
        anActionEvent.getPresentation().setEnabledAndVisible(enabledAndVisible);
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final PsiFile psiFile = (PsiFile)anActionEvent.getData(CommonDataKeys.PSI_FILE);
        assert psiFile != null;
        try {
            psiFile.putUserData((Key)ClangTidyAnnotator.COLLECT_DEBUG_INFORMATION, (Object)true);
            final ClangTidyAnnotator clangTidyAnnotator = new ClangTidyAnnotator();
            clangTidyAnnotator.doAnnotate(clangTidyAnnotator.collectInformation(psiFile));
            final String s = (String)psiFile.getUserData((Key)ClangTidyAnnotator.DEBUG_INFORMATION);
            if (s == null) {
                return;
            }
            FileEditorManager.getInstance(psiFile.getProject()).openFile((VirtualFile)new LightVirtualFile("Clang-Tidy Info for " + psiFile.getName(), (FileType)PlainTextFileType.INSTANCE, (CharSequence)s), false);
        }
        finally {
            psiFile.putUserData((Key)ClangTidyAnnotator.COLLECT_DEBUG_INFORMATION, (Object)null);
            psiFile.putUserData((Key)ClangTidyAnnotator.DEBUG_INFORMATION, (Object)null);
        }
    }
}
