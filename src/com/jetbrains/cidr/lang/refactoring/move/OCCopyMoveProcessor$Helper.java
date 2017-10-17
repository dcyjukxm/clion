// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.openapi.project.Project;
import java.util.Collection;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;

public interface Helper<M, R, G extends R, P>
{
    boolean isGroupElement(final PsiElement p0);
    
    Pair<G, P> takeFromGroupElement(final PsiElement p0);
    
    @Nullable
    R getSourceRefFromGroupElement(final PsiElement p0, final G p1);
    
    M getManipulator(final P p0);
    
    void completeManipulation(@NotNull final M p0);
    
    Collection<R> findReferences(final VirtualFile p0, final P p1);
    
    boolean areFromSameParent(final R p0, final R p1);
    
    P getProjectFile(final Project p0, final VirtualFile p1);
    
    G getTargetGroup(final Collection<R> p0, final R p1);
    
    boolean badTargetGroup(final G p0);
}
