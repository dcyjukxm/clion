// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.generate.handlers.OCPresentableActionHandler;

public class OCOverrideMethodsHandler extends OCPresentableActionHandler
{
    private final OCOverrideImplementCppFunctionsHandlerBase CPP_HANDLER;
    private final OCOverrideImplementOCMethodHandler OC_HANDLER;
    
    public OCOverrideMethodsHandler() {
        this.CPP_HANDLER = new OCOverrideImplementCppFunctionsHandler();
        this.OC_HANDLER = new OCOverrideImplementOCMethodHandler();
    }
    
    @Nullable
    @Override
    protected OCClassActionHandlerBase getHandler(final Editor editor, final PsiFile psiFile) {
        if (this.OC_HANDLER.isValidFor(editor, psiFile)) {
            return this.OC_HANDLER;
        }
        if (this.CPP_HANDLER.isValidFor(editor, psiFile)) {
            return this.CPP_HANDLER;
        }
        return null;
    }
    
    public boolean startInWriteAction() {
        return false;
    }
}
