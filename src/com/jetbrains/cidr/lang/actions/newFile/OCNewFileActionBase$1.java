// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.util.Iterator;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiDirectory;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.command.CommandProcessor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiFile;
import java.util.List;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.command.WriteCommandAction;

class OCNewFileActionBase$1 extends WriteCommandAction {
    final /* synthetic */ Project val$project;
    final /* synthetic */ boolean val$useHelperAndCreateFiles;
    final /* synthetic */ String val$path;
    final /* synthetic */ boolean[] val$dirFailed;
    final /* synthetic */ List val$fileNames;
    final /* synthetic */ PsiFile[] val$resultElements;
    final /* synthetic */ DialogWrapper val$dialog;
    final /* synthetic */ PsiFile val$sampleFile;
    
    protected UndoConfirmationPolicy getUndoConfirmationPolicy() {
        return UndoConfirmationPolicy.REQUEST_CONFIRMATION;
    }
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        CommandProcessor.getInstance().markCurrentCommandAsGlobal(this.val$project);
        if (this.val$useHelperAndCreateFiles) {
            VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(this.val$path);
            if (virtualFile == null) {
                virtualFile = VfsUtil.createDirectoryIfMissing(this.val$path);
            }
            try {
                if (virtualFile == null) {
                    this.val$dirFailed[0] = true;
                    return;
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
            PsiManager instance = null;
            VirtualFile parent = null;
            Label_0126: {
                try {
                    instance = PsiManager.getInstance(this.val$project);
                    if (virtualFile.isDirectory()) {
                        parent = virtualFile;
                        break Label_0126;
                    }
                }
                catch (Throwable t3) {
                    throw a(t3);
                }
                parent = virtualFile.getParent();
            }
            final PsiDirectory directory = instance.findDirectory(parent);
            Logger log = null;
            boolean b = false;
            Label_0146: {
                try {
                    log = OCLog.LOG;
                    if (directory != null) {
                        b = true;
                        break Label_0146;
                    }
                }
                catch (Throwable t4) {
                    throw a(t4);
                }
                b = false;
            }
            log.assertTrue(b);
            OCNewFileActionBase.this.myHelper.doCreateFiles(this.val$project, directory, ArrayUtil.toStringArray((Collection)this.val$fileNames), this.val$resultElements, this.val$dialog, this.val$sampleFile);
        }
        final Iterator<AuxAction> iterator = (Iterator<AuxAction>)OCNewFileActionBase.access$000(OCNewFileActionBase.this).iterator();
        while (iterator.hasNext()) {
            iterator.next().run(this.val$resultElements);
        }
        OCNewFileActionBase.access$000(OCNewFileActionBase.this).clear();
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}