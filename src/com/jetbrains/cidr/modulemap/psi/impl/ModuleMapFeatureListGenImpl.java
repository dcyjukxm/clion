// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapFeature;
import java.util.List;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.psi.ModuleMapVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapFeatureList;

public class ModuleMapFeatureListGenImpl extends ModuleMapPsiElementImpl implements ModuleMapFeatureList
{
    public ModuleMapFeatureListGenImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final ModuleMapVisitor moduleMapVisitor) {
        try {
            if (moduleMapVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFeatureListGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        moduleMapVisitor.visitFeatureList(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFeatureListGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (visitor instanceof ModuleMapVisitor) {
                this.accept((ModuleMapVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.accept(visitor);
    }
    
    @NotNull
    @Override
    public List<ModuleMapFeature> getFeatureList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapFeature.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFeatureListGenImpl", "getFeatureList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<ModuleMapFeature>)childrenOfTypeAsList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
