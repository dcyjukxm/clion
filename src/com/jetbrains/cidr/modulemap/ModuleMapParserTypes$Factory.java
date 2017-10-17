// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapWildcardModuleIdGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapUseDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapUmbrellaDirDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapRequiresDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapModuleIdGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapModuleDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapLinkDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapInferredSubmoduleMemberGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapInferredSubmoduleDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapHeaderNameGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapHeaderDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapFeatureListGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapFeatureGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapExternModuleDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapExportDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapConflictDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapConfigMacroListGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapConfigMacrosDeclarationGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapAttributesGenImpl;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapAttributeGenImpl;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;

public static class Factory
{
    public static PsiElement createElement(final ASTNode astNode) {
        final IElementType elementType = astNode.getElementType();
        if (elementType == ModuleMapParserTypes.ATTRIBUTE) {
            return (PsiElement)new ModuleMapAttributeGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.ATTRIBUTES) {
            return (PsiElement)new ModuleMapAttributesGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.CONFIG_MACROS_DECLARATION) {
            return (PsiElement)new ModuleMapConfigMacrosDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.CONFIG_MACRO_LIST) {
            return (PsiElement)new ModuleMapConfigMacroListGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.CONFLICT_DECLARATION) {
            return (PsiElement)new ModuleMapConflictDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.EXPORT_DECLARATION) {
            return (PsiElement)new ModuleMapExportDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.EXTERN_MODULE_DECLARATION) {
            return (PsiElement)new ModuleMapExternModuleDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.FEATURE) {
            return (PsiElement)new ModuleMapFeatureGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.FEATURE_LIST) {
            return (PsiElement)new ModuleMapFeatureListGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.HEADER_DECLARATION) {
            return (PsiElement)new ModuleMapHeaderDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.HEADER_NAME) {
            return (PsiElement)new ModuleMapHeaderNameGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.INFERRED_SUBMODULE_DECLARATION) {
            return (PsiElement)new ModuleMapInferredSubmoduleDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.INFERRED_SUBMODULE_MEMBER) {
            return (PsiElement)new ModuleMapInferredSubmoduleMemberGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.LINK_DECLARATION) {
            return (PsiElement)new ModuleMapLinkDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.MODULE_DECLARATION) {
            return (PsiElement)new ModuleMapModuleDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.MODULE_ID) {
            return (PsiElement)new ModuleMapModuleIdGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.REQUIRES_DECLARATION) {
            return (PsiElement)new ModuleMapRequiresDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.UMBRELLA_DIR_DECLARATION) {
            return (PsiElement)new ModuleMapUmbrellaDirDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.USE_DECLARATION) {
            return (PsiElement)new ModuleMapUseDeclarationGenImpl(astNode);
        }
        if (elementType == ModuleMapParserTypes.WILDCARD_MODULE_ID) {
            return (PsiElement)new ModuleMapWildcardModuleIdGenImpl(astNode);
        }
        throw new AssertionError((Object)("Unknown element type: " + elementType));
    }
}
