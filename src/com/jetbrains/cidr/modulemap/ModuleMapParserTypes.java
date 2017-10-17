// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

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
import com.jetbrains.cidr.modulemap.psi.ModuleMapTokenType;
import com.jetbrains.cidr.modulemap.psi.ModuleMapElementType;
import com.intellij.psi.tree.IElementType;

public interface ModuleMapParserTypes
{
    public static final IElementType ATTRIBUTE = new ModuleMapElementType("ATTRIBUTE");
    public static final IElementType ATTRIBUTES = new ModuleMapElementType("ATTRIBUTES");
    public static final IElementType CONFIG_MACROS_DECLARATION = new ModuleMapElementType("CONFIG_MACROS_DECLARATION");
    public static final IElementType CONFIG_MACRO_LIST = new ModuleMapElementType("CONFIG_MACRO_LIST");
    public static final IElementType CONFLICT_DECLARATION = new ModuleMapElementType("CONFLICT_DECLARATION");
    public static final IElementType EXPORT_DECLARATION = new ModuleMapElementType("EXPORT_DECLARATION");
    public static final IElementType EXTERN_MODULE_DECLARATION = new ModuleMapElementType("EXTERN_MODULE_DECLARATION");
    public static final IElementType FEATURE = new ModuleMapElementType("FEATURE");
    public static final IElementType FEATURE_LIST = new ModuleMapElementType("FEATURE_LIST");
    public static final IElementType HEADER_DECLARATION = new ModuleMapElementType("HEADER_DECLARATION");
    public static final IElementType HEADER_NAME = new ModuleMapElementType("HEADER_NAME");
    public static final IElementType INFERRED_SUBMODULE_DECLARATION = new ModuleMapElementType("INFERRED_SUBMODULE_DECLARATION");
    public static final IElementType INFERRED_SUBMODULE_MEMBER = new ModuleMapElementType("INFERRED_SUBMODULE_MEMBER");
    public static final IElementType LINK_DECLARATION = new ModuleMapElementType("LINK_DECLARATION");
    public static final IElementType MODULE_DECLARATION = new ModuleMapElementType("MODULE_DECLARATION");
    public static final IElementType MODULE_ID = new ModuleMapElementType("MODULE_ID");
    public static final IElementType REQUIRES_DECLARATION = new ModuleMapElementType("REQUIRES_DECLARATION");
    public static final IElementType UMBRELLA_DIR_DECLARATION = new ModuleMapElementType("UMBRELLA_DIR_DECLARATION");
    public static final IElementType USE_DECLARATION = new ModuleMapElementType("USE_DECLARATION");
    public static final IElementType WILDCARD_MODULE_ID = new ModuleMapElementType("WILDCARD_MODULE_ID");
    public static final IElementType BLOCK_COMMENT = new ModuleMapTokenType("BLOCK_COMMENT");
    public static final IElementType COMMA = new ModuleMapTokenType(",");
    public static final IElementType CONFIG_MACROS = new ModuleMapTokenType("config_macros");
    public static final IElementType CONFLICT = new ModuleMapTokenType("conflict");
    public static final IElementType DOT = new ModuleMapTokenType(".");
    public static final IElementType EOL_COMMENT = new ModuleMapTokenType("EOL_COMMENT");
    public static final IElementType EXCL = new ModuleMapTokenType("!");
    public static final IElementType EXCLUDE = new ModuleMapTokenType("exclude");
    public static final IElementType EXPLICIT = new ModuleMapTokenType("explicit");
    public static final IElementType EXPORT = new ModuleMapTokenType("export");
    public static final IElementType EXTERN = new ModuleMapTokenType("extern");
    public static final IElementType FRAMEWORK = new ModuleMapTokenType("framework");
    public static final IElementType HEADER = new ModuleMapTokenType("header");
    public static final IElementType IDENTIFIER = new ModuleMapTokenType("IDENTIFIER");
    public static final IElementType LINK = new ModuleMapTokenType("link");
    public static final IElementType L_BRACKET = new ModuleMapTokenType("[");
    public static final IElementType L_CURLY = new ModuleMapTokenType("{");
    public static final IElementType MODULE = new ModuleMapTokenType("module");
    public static final IElementType PRIVATE = new ModuleMapTokenType("private");
    public static final IElementType REQUIRES = new ModuleMapTokenType("requires");
    public static final IElementType R_BRACKET = new ModuleMapTokenType("]");
    public static final IElementType R_CURLY = new ModuleMapTokenType("}");
    public static final IElementType SLASH = new ModuleMapTokenType("/");
    public static final IElementType STRING = new ModuleMapTokenType("STRING");
    public static final IElementType TEXTUAL = new ModuleMapTokenType("textual");
    public static final IElementType UMBRELLA = new ModuleMapTokenType("umbrella");
    public static final IElementType USE = new ModuleMapTokenType("use");
    public static final IElementType WILDCARD = new ModuleMapTokenType("*");
    
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
}
