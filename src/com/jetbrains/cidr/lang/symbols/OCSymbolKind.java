// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.text.StringUtil;
import icons.CidrLangIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCGenericParameter;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.psi.OCUnion;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCCompatibilityAlias;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.psi.OCMacroParameter;
import com.jetbrains.cidr.lang.psi.OCUndefDirective;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.PsiElement;

public enum OCSymbolKind
{
    CATCH_EXCEPTION_VARIABLE, 
    LOCAL_VARIABLE, 
    PARAMETER, 
    LABEL, 
    STRUCT_FIELD, 
    PROPERTY, 
    INSTANCE_VARIABLE, 
    METHOD, 
    FUNCTION_PREDECLARATION, 
    FUNCTION_DECLARATION, 
    TYPEDEF, 
    COMPATIBILITY_ALIAS, 
    INTERFACE, 
    PROTOCOL, 
    IMPLEMENTATION, 
    STRUCT, 
    USING_SYMBOL_ALIAS, 
    ENUM, 
    UNION, 
    NAMESPACE_ALIAS, 
    NAMESPACE, 
    NAMESPACE_USING_SYMBOL, 
    SYMBOL_USING_SYMBOL, 
    ENUM_CONST, 
    GLOBAL_VARIABLE, 
    GLOBAL_VARIABLE_PREDECLARATION, 
    CPP_CONSTRUCTOR_PREDECLARATION, 
    CPP_CONSTRUCTOR_DECLARATION, 
    MACRO, 
    MACRO_PARAMETER, 
    UNDEF_MACRO, 
    TEMPLATE_TYPE_PARAMETER, 
    TEMPLATE_VALUE_PARAMETER, 
    BUILTIN_SYMBOL, 
    SYNTHESIZE, 
    BLOCK, 
    LAMBDA, 
    IMPORT, 
    LOCALIZED_STRING, 
    EXPRESSION, 
    GENERIC_PARAMETER, 
    FOREIGN_ELEMENT;
    
    public String getName() {
        try {
            switch (this) {
                case TYPEDEF: {
                    return "Type";
                }
                case ENUM_CONST: {
                    break;
                }
                case STRUCT_FIELD: {
                    return "Field";
                }
                case FUNCTION_PREDECLARATION:
                case FUNCTION_DECLARATION: {
                    return "Function";
                }
                case CPP_CONSTRUCTOR_DECLARATION:
                case CPP_CONSTRUCTOR_PREDECLARATION: {
                    return "Constructor";
                }
                case PARAMETER: {
                    return "Parameter";
                }
                case GLOBAL_VARIABLE:
                case GLOBAL_VARIABLE_PREDECLARATION: {
                    return "Global variable";
                }
                case BUILTIN_SYMBOL: {
                    return "Built-in symbol";
                }
                case LOCAL_VARIABLE: {
                    return "Local variable";
                }
                case CATCH_EXCEPTION_VARIABLE: {
                    return "Exception variable";
                }
                case IMPLEMENTATION: {
                    return "Implementation";
                }
                case INSTANCE_VARIABLE: {
                    return "Instance variable";
                }
                case INTERFACE: {
                    return "Interface";
                }
                case LABEL: {
                    return "Label";
                }
                case MACRO:
                case UNDEF_MACRO: {
                    return "Macro";
                }
                case MACRO_PARAMETER: {
                    return "Macro parameter";
                }
                case IMPORT: {
                    return "Import";
                }
                case COMPATIBILITY_ALIAS: {
                    return "Compatibility alias";
                }
                case METHOD: {
                    return "Method";
                }
                case BLOCK: {
                    return "Block";
                }
                case LAMBDA: {
                    return "Lambda";
                }
                case PROPERTY: {
                    return "Property";
                }
                case SYNTHESIZE: {
                    return "Synthesize statement";
                }
                case PROTOCOL: {
                    return "Protocol";
                }
                case STRUCT: {
                    return "Struct";
                }
                case UNION: {
                    return "Union";
                }
                case ENUM: {
                    return "Enum";
                }
                case NAMESPACE:
                case NAMESPACE_ALIAS: {
                    return "Namespace";
                }
                case TEMPLATE_TYPE_PARAMETER: {
                    return "Type parameter";
                }
                case TEMPLATE_VALUE_PARAMETER: {
                    return "Template parameter";
                }
                case SYMBOL_USING_SYMBOL:
                case NAMESPACE_USING_SYMBOL:
                case USING_SYMBOL_ALIAS: {
                    return "Using";
                }
                case LOCALIZED_STRING: {
                    return "Localized string";
                }
                case EXPRESSION: {
                    return "Expression";
                }
                case GENERIC_PARAMETER: {
                    return "Generic parameter";
                }
                default: {
                    return "Symbol";
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "Enum constant";
    }
    
    public Class getPsiElementClass() {
        try {
            switch (this) {
                case BUILTIN_SYMBOL: {
                    return PsiElement.class;
                }
                case IMPLEMENTATION: {
                    break;
                }
                case INTERFACE:
                case PROTOCOL: {
                    return OCClassDeclaration.class;
                }
                case LABEL: {
                    return OCLabeledStatement.class;
                }
                case MACRO: {
                    return OCDefineDirective.class;
                }
                case UNDEF_MACRO: {
                    return OCUndefDirective.class;
                }
                case MACRO_PARAMETER: {
                    return OCMacroParameter.class;
                }
                case IMPORT: {
                    return OCIncludeDirective.class;
                }
                case COMPATIBILITY_ALIAS: {
                    return OCCompatibilityAlias.class;
                }
                case METHOD: {
                    return OCMethod.class;
                }
                case BLOCK: {
                    return OCBlockExpression.class;
                }
                case SYNTHESIZE: {
                    return OCSynthesizeProperty.class;
                }
                case STRUCT: {
                    return OCStruct.class;
                }
                case UNION: {
                    return OCUnion.class;
                }
                case ENUM: {
                    return OCEnum.class;
                }
                case NAMESPACE: {
                    return OCCppNamespace.class;
                }
                case NAMESPACE_ALIAS: {
                    return OCCppNamespaceAlias.class;
                }
                case TEMPLATE_TYPE_PARAMETER: {
                    return OCTypeParameterDeclaration.class;
                }
                case SYMBOL_USING_SYMBOL:
                case NAMESPACE_USING_SYMBOL:
                case USING_SYMBOL_ALIAS: {
                    return OCCppUsingStatement.class;
                }
                case LOCALIZED_STRING: {
                    return OCLocalizedString.class;
                }
                case EXPRESSION: {
                    return OCExpression.class;
                }
                case GENERIC_PARAMETER: {
                    return OCGenericParameter.class;
                }
                default: {
                    return OCDeclarator.class;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCImplementation.class;
    }
    
    public Icon getIcon() {
        try {
            switch (this) {
                case NAMESPACE:
                case NAMESPACE_ALIAS:
                case NAMESPACE_USING_SYMBOL: {
                    return CidrLangIcons.CodeAssistantNamespace;
                }
                case TYPEDEF:
                case TEMPLATE_TYPE_PARAMETER:
                case SYMBOL_USING_SYMBOL:
                case USING_SYMBOL_ALIAS: {
                    break;
                }
                case ENUM_CONST: {
                    return CidrLangIcons.CodeAssistantEnumConst;
                }
                case FUNCTION_PREDECLARATION:
                case FUNCTION_DECLARATION:
                case CPP_CONSTRUCTOR_DECLARATION:
                case CPP_CONSTRUCTOR_PREDECLARATION: {
                    return CidrLangIcons.CodeAssistantFunction;
                }
                case STRUCT_FIELD:
                case INSTANCE_VARIABLE: {
                    return CidrLangIcons.CodeAssistantField;
                }
                case IMPLEMENTATION:
                case INTERFACE: {
                    return CidrLangIcons.CodeAssistantClass;
                }
                case MACRO:
                case UNDEF_MACRO:
                case MACRO_PARAMETER:
                case IMPORT: {
                    return CidrLangIcons.CodeAssistantMacro;
                }
                case COMPATIBILITY_ALIAS: {
                    return CidrLangIcons.CodeAssistantClass;
                }
                case METHOD: {
                    return CidrLangIcons.CodeAssistantMember;
                }
                case PROPERTY: {
                    return CidrLangIcons.CodeAssistantProperty;
                }
                case PROTOCOL: {
                    return CidrLangIcons.CodeAssistantProtocol;
                }
                case STRUCT: {
                    return CidrLangIcons.CodeAssistantStruct;
                }
                case UNION: {
                    return CidrLangIcons.CodeAssistantUnion;
                }
                case ENUM: {
                    return CidrLangIcons.CodeAssistantEnum;
                }
                case PARAMETER:
                case TEMPLATE_VALUE_PARAMETER:
                case GENERIC_PARAMETER: {
                    return CidrLangIcons.CodeAssistantParameter;
                }
                case LOCAL_VARIABLE:
                case CATCH_EXCEPTION_VARIABLE:
                case LABEL: {
                    return CidrLangIcons.CodeAssistantLocal;
                }
                case GLOBAL_VARIABLE:
                case GLOBAL_VARIABLE_PREDECLARATION:
                case BUILTIN_SYMBOL: {
                    return CidrLangIcons.CodeAssistantGlobal;
                }
                case BLOCK:
                case LAMBDA:
                case SYNTHESIZE:
                case LOCALIZED_STRING:
                case EXPRESSION: {
                    return null;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return CidrLangIcons.CodeAssistantType;
    }
    
    public String getNameUppercase() {
        return this.getName();
    }
    
    public String getNameLowercase() {
        return StringUtil.decapitalize(this.getName());
    }
    
    public boolean isClass() {
        try {
            switch (this) {
                case IMPLEMENTATION:
                case INTERFACE:
                case COMPATIBILITY_ALIAS:
                case PROTOCOL: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isClassOrTypedef() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClass:()Z
        //     4: ifne            35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.USING_SYMBOL_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isType() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClassOrTypedef:()Z
        //     4: ifne            63
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //    11: ifne            63
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpeq       63
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.COMPATIBILITY_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    39: if_acmpeq       63
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GENERIC_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    53: if_acmpne       71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isStructLike() {
        try {
            switch (this) {
                case STRUCT:
                case UNION:
                case ENUM: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isFunction() {
        Label_0021: {
            try {
                if (this == OCSymbolKind.FUNCTION_PREDECLARATION) {
                    break Label_0021;
                }
                final OCSymbolKind ocSymbolKind = this;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.FUNCTION_DECLARATION;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbolKind ocSymbolKind = this;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.FUNCTION_DECLARATION;
                if (ocSymbolKind == ocSymbolKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isFunctionOrConstructor() {
        Label_0021: {
            try {
                if (this.isFunction()) {
                    break Label_0021;
                }
                final OCSymbolKind ocSymbolKind = this;
                final boolean b = ocSymbolKind.isConstructorOrDestructor();
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbolKind ocSymbolKind = this;
                final boolean b = ocSymbolKind.isConstructorOrDestructor();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isCallable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //     4: ifne            77
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       77
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BLOCK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpeq       77
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    39: if_acmpeq       77
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    53: if_acmpeq       77
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_0        
        //    64: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LAMBDA:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    67: if_acmpne       85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     81     81     85     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isPredeclaration() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: if_acmpeq       49
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       49
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpeq       49
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    39: if_acmpne       57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     53     53     57     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isLocal() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: if_acmpeq       49
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CATCH_EXCEPTION_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       49
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LABEL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpeq       49
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    39: if_acmpne       57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     53     53     57     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isConst() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: if_acmpeq       49
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       49
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GENERIC_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpeq       49
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //    39: ifeq            57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     53     53     57     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public OCSymbolKind toDeclarationKind() {
        try {
            switch (this) {
                case FUNCTION_PREDECLARATION: {
                    return OCSymbolKind.FUNCTION_DECLARATION;
                }
                case CPP_CONSTRUCTOR_PREDECLARATION: {
                    break;
                }
                case GLOBAL_VARIABLE_PREDECLARATION: {
                    return OCSymbolKind.GLOBAL_VARIABLE;
                }
                default: {
                    return this;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION;
    }
    
    public boolean isExpression() {
        try {
            switch (this) {
                case ENUM_CONST:
                case STRUCT_FIELD:
                case FUNCTION_PREDECLARATION:
                case FUNCTION_DECLARATION:
                case CPP_CONSTRUCTOR_DECLARATION:
                case CPP_CONSTRUCTOR_PREDECLARATION:
                case PARAMETER:
                case GLOBAL_VARIABLE:
                case GLOBAL_VARIABLE_PREDECLARATION:
                case BUILTIN_SYMBOL:
                case LOCAL_VARIABLE:
                case CATCH_EXCEPTION_VARIABLE:
                case INSTANCE_VARIABLE:
                case LAMBDA:
                case PROPERTY:
                case TEMPLATE_VALUE_PARAMETER: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isVariable() {
        try {
            switch (this) {
                case ENUM_CONST:
                case PARAMETER:
                case GLOBAL_VARIABLE:
                case GLOBAL_VARIABLE_PREDECLARATION:
                case LOCAL_VARIABLE:
                case CATCH_EXCEPTION_VARIABLE:
                case INSTANCE_VARIABLE:
                case PROPERTY:
                case TEMPLATE_VALUE_PARAMETER: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isGlobalVariable() {
        Label_0021: {
            try {
                if (this == OCSymbolKind.GLOBAL_VARIABLE) {
                    break Label_0021;
                }
                final OCSymbolKind ocSymbolKind = this;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbolKind ocSymbolKind = this;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION;
                if (ocSymbolKind == ocSymbolKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isConstructorOrDestructor() {
        Label_0021: {
            try {
                if (this == OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION) {
                    break Label_0021;
                }
                final OCSymbolKind ocSymbolKind = this;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbolKind ocSymbolKind = this;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION;
                if (ocSymbolKind == ocSymbolKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isTemplateParameter() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: if_acmpeq       35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GENERIC_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isTypedefOrAlias() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: if_acmpeq       35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.COMPATIBILITY_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.USING_SYMBOL_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean canBeNamespace() {
        try {
            switch (this) {
                case TYPEDEF:
                case STRUCT:
                case UNION:
                case ENUM:
                case NAMESPACE:
                case NAMESPACE_ALIAS:
                case TEMPLATE_TYPE_PARAMETER: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public static OCSymbolKind parse(final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0043: {
            Label_0035: {
                try {
                    if (s.equals("struct")) {
                        break Label_0035;
                    }
                    final String s2 = s;
                    final String s3 = "class";
                    final boolean b = s2.equals(s3);
                    if (b) {
                        break Label_0035;
                    }
                    break Label_0043;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final String s2 = s;
                    final String s3 = "class";
                    final boolean b = s2.equals(s3);
                    if (b) {
                        return OCSymbolKind.STRUCT;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (s.equals("enum")) {
                    return OCSymbolKind.ENUM;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        try {
            if (s.equals("union")) {
                return OCSymbolKind.UNION;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    public boolean isSame(@NotNull final OCSymbolKind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "kind"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolKind"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isSame"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: if_acmpeq       217
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    53: if_acmpne       77
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_1        
        //    64: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    67: if_acmpeq       217
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    81: if_acmpne       105
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_1        
        //    92: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    95: if_acmpeq       217
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_0        
        //   106: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   109: if_acmpne       133
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_1        
        //   120: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   123: if_acmpeq       217
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_0        
        //   134: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   137: if_acmpne       161
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_1        
        //   148: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   151: if_acmpeq       217
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload_0        
        //   162: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   165: if_acmpne       189
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_1        
        //   176: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   179: if_acmpeq       217
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   188: athrow         
        //   189: aload_0        
        //   190: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   193: if_acmpne       225
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aload_1        
        //   204: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   207: if_acmpne       225
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iconst_1       
        //   218: goto            226
        //   221: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: iconst_0       
        //   226: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     84     87     91     Ljava/lang/IllegalArgumentException;
        //  77     98     101    105    Ljava/lang/IllegalArgumentException;
        //  91     112    115    119    Ljava/lang/IllegalArgumentException;
        //  105    126    129    133    Ljava/lang/IllegalArgumentException;
        //  119    140    143    147    Ljava/lang/IllegalArgumentException;
        //  133    154    157    161    Ljava/lang/IllegalArgumentException;
        //  147    168    171    175    Ljava/lang/IllegalArgumentException;
        //  161    182    185    189    Ljava/lang/IllegalArgumentException;
        //  175    196    199    203    Ljava/lang/IllegalArgumentException;
        //  189    210    213    217    Ljava/lang/IllegalArgumentException;
        //  203    221    221    225    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
