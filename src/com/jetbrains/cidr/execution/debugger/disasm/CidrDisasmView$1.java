// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import com.intellij.openapi.project.Project;
import com.intellij.ui.GuiUtils;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.xdebugger.XDebugSessionListener;

class CidrDisasmView$1 implements XDebugSessionListener {
    public void sessionStopped() {
        final Project project;
        GuiUtils.invokeLaterIfNeeded(() -> {
            CidrDisasmView.access$000(CidrDisasmView.this).getProject();
            if (!project.isDisposed()) {
                FileEditorManager.getInstance(project).closeFile((VirtualFile)CidrDisasmView.access$100(CidrDisasmView.this));
            }
        }, ModalityState.defaultModalityState());
    }
}