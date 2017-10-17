// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;

public class OCTryCatchFinallySurrounder extends OCTryCatchSurrounder
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return super.getStatementTemplate(project, psiElement) + "\n@finally {\n}";
    }
    
    @Override
    public String getTemplateDescription() {
        return "@try / @catch / @finally";
    }
}
