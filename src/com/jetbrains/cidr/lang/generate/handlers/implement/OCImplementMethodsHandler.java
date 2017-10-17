// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.generate.handlers.OCPresentableActionHandler;

public class OCImplementMethodsHandler extends OCPresentableActionHandler
{
    private final OCImplementCppFunctionsHandler CPP_HANDLER;
    private final OCImplementOCMethodsHandler OC_HANDLER;
    
    public OCImplementMethodsHandler() {
        this.CPP_HANDLER = new OCImplementCppFunctionsHandler();
        this.OC_HANDLER = new OCImplementOCMethodsHandler();
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
