// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;

public abstract class OCMoveCppProcessor extends OCMoveProcessor<OCStruct, OCStructSymbol, OCCppTargetClass>
{
    public OCMoveCppProcessor(final OCStruct ocStruct, final List<OCMemberInfo> list, @Nullable final String s, final Collection<OCStructSymbol> collection) {
        super((PsiElement)ocStruct, list, s, collection);
    }
    
    @Override
    protected OCCppTargetClass createTargetClass(final OCFile ocFile, final OCFile ocFile2, final String s, final Set<VirtualFile> set, final OCStructSymbol ocStructSymbol, final OCStructSymbol ocStructSymbol2, final Project project) {
        return new OCCppTargetClass(s, set, ocStructSymbol, ocStructSymbol2, project);
    }
    
    @Override
    protected void removeMember(final PsiElement psiElement, final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCStructSymbol && ((OCStructSymbol)ocSymbol).getParent() != this.mySourceClassSymbol) {
            this.myMover.removeBaseClass((OCStruct)this.mySourceClass, (OCStructSymbol)ocSymbol, true);
        }
        else {
            OCChangeUtil.delete(psiElement);
        }
    }
}
