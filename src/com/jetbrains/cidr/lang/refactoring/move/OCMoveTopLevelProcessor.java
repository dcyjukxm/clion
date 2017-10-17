// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;

public abstract class OCMoveTopLevelProcessor extends OCMoveProcessor<PsiElement, OCSymbol, OCTopLevelTarget>
{
    protected OCMoveTopLevelProcessor(final PsiElement psiElement, final String s, @Nullable final OCFile ocFile, final List<OCMemberInfo> list) {
        super(psiElement, list, s);
        if (ocFile != null) {
            this.addTarget(ocFile, ocFile.getName(), null);
        }
    }
    
    @Override
    protected OCTopLevelTarget createTargetClass(final OCFile ocFile, final OCFile ocFile2, final String s, final Set<VirtualFile> set, @Nullable final OCSymbol ocSymbol, final OCSymbol ocSymbol2, final Project project) {
        final OCFile associatedFile = ocFile2.getAssociatedFile();
        if (!ocFile2.isHeader() && associatedFile != null && associatedFile.isHeader() && !this.mySourceFile.equals(associatedFile)) {
            return new OCTopLevelTarget(ocFile, associatedFile, set, project);
        }
        return new OCTopLevelTarget(ocFile, ocFile2, set, project);
    }
    
    @Override
    protected void removeMember(@Nullable final PsiElement psiElement, final OCSymbol ocSymbol) {
        OCChangeUtil.delete(psiElement);
    }
}
