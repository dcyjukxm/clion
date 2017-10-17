// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElementVisitor;

public class ModuleMapVisitor extends PsiElementVisitor
{
    public void visitAttribute(@NotNull final ModuleMapAttribute moduleMapAttribute) {
        try {
            if (moduleMapAttribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitAttribute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapAttribute);
    }
    
    public void visitAttributes(@NotNull final ModuleMapAttributes moduleMapAttributes) {
        try {
            if (moduleMapAttributes == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitAttributes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapAttributes);
    }
    
    public void visitConfigMacroList(@NotNull final ModuleMapConfigMacroList list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitConfigMacroList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(list);
    }
    
    public void visitConfigMacrosDeclaration(@NotNull final ModuleMapConfigMacrosDeclaration moduleMapConfigMacrosDeclaration) {
        try {
            if (moduleMapConfigMacrosDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitConfigMacrosDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapConfigMacrosDeclaration);
    }
    
    public void visitConflictDeclaration(@NotNull final ModuleMapConflictDeclaration moduleMapConflictDeclaration) {
        try {
            if (moduleMapConflictDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitConflictDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapConflictDeclaration);
    }
    
    public void visitExportDeclaration(@NotNull final ModuleMapExportDeclaration moduleMapExportDeclaration) {
        try {
            if (moduleMapExportDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitExportDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapExportDeclaration);
    }
    
    public void visitExternModuleDeclaration(@NotNull final ModuleMapExternModuleDeclaration moduleMapExternModuleDeclaration) {
        try {
            if (moduleMapExternModuleDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitExternModuleDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapExternModuleDeclaration);
    }
    
    public void visitFeature(@NotNull final ModuleMapFeature moduleMapFeature) {
        try {
            if (moduleMapFeature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitFeature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapFeature);
    }
    
    public void visitFeatureList(@NotNull final ModuleMapFeatureList list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitFeatureList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(list);
    }
    
    public void visitHeaderDeclaration(@NotNull final ModuleMapHeaderDeclaration moduleMapHeaderDeclaration) {
        try {
            if (moduleMapHeaderDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitHeaderDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapHeaderDeclaration);
    }
    
    public void visitHeaderName(@NotNull final ModuleMapHeaderName moduleMapHeaderName) {
        try {
            if (moduleMapHeaderName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitHeaderName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapHeaderName);
    }
    
    public void visitInferredSubmoduleDeclaration(@NotNull final ModuleMapInferredSubmoduleDeclaration moduleMapInferredSubmoduleDeclaration) {
        try {
            if (moduleMapInferredSubmoduleDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitInferredSubmoduleDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitAttributesHolder(moduleMapInferredSubmoduleDeclaration);
    }
    
    public void visitInferredSubmoduleMember(@NotNull final ModuleMapInferredSubmoduleMember moduleMapInferredSubmoduleMember) {
        try {
            if (moduleMapInferredSubmoduleMember == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitInferredSubmoduleMember"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapInferredSubmoduleMember);
    }
    
    public void visitLinkDeclaration(@NotNull final ModuleMapLinkDeclaration moduleMapLinkDeclaration) {
        try {
            if (moduleMapLinkDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitLinkDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapLinkDeclaration);
    }
    
    public void visitModuleDeclaration(@NotNull final ModuleMapModuleDeclaration moduleMapModuleDeclaration) {
        try {
            if (moduleMapModuleDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitModuleDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapModuleDeclaration);
    }
    
    public void visitModuleId(@NotNull final ModuleMapModuleId moduleMapModuleId) {
        try {
            if (moduleMapModuleId == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitModuleId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapModuleId);
    }
    
    public void visitRequiresDeclaration(@NotNull final ModuleMapRequiresDeclaration moduleMapRequiresDeclaration) {
        try {
            if (moduleMapRequiresDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitRequiresDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapRequiresDeclaration);
    }
    
    public void visitUmbrellaDirDeclaration(@NotNull final ModuleMapUmbrellaDirDeclaration moduleMapUmbrellaDirDeclaration) {
        try {
            if (moduleMapUmbrellaDirDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitUmbrellaDirDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapUmbrellaDirDeclaration);
    }
    
    public void visitUseDeclaration(@NotNull final ModuleMapUseDeclaration moduleMapUseDeclaration) {
        try {
            if (moduleMapUseDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitUseDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapUseDeclaration);
    }
    
    public void visitWildcardModuleId(@NotNull final ModuleMapWildcardModuleId moduleMapWildcardModuleId) {
        try {
            if (moduleMapWildcardModuleId == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitWildcardModuleId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapWildcardModuleId);
    }
    
    public void visitAttributesHolder(@NotNull final ModuleMapAttributesHolder moduleMapAttributesHolder) {
        try {
            if (moduleMapAttributesHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitAttributesHolder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement(moduleMapAttributesHolder);
    }
    
    public void visitPsiElement(@NotNull final ModuleMapPsiElement moduleMapPsiElement) {
        try {
            if (moduleMapPsiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/modulemap/psi/ModuleMapVisitor", "visitPsiElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)moduleMapPsiElement);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
