// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.jetbrains.cidr.lang.psi.impl.OCAsmStatementPartImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAsmStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCObjCErrorKeywordImpl;
import com.jetbrains.cidr.lang.psi.impl.OCObjCKeywordImpl;
import com.jetbrains.cidr.lang.psi.impl.OCUnknownCppCodeImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppStaticAssertImpl;
import com.jetbrains.cidr.lang.psi.impl.OCExceptionSpecificationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCNoexceptSpecifierImpl;
import com.jetbrains.cidr.lang.psi.impl.OCLambdaIntroducerImpl;
import com.jetbrains.cidr.lang.psi.impl.OCLambdaExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCConstructorFieldInitializerImpl;
import com.jetbrains.cidr.lang.psi.impl.OCConstructorInitializationListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCTypeParameterDeclarationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCTemplateParameterListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCTemplateArgumentListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppBaseClauseImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppBaseClauseListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppUsingStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppQualifiedPointerImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppNamespaceQualifierImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppNamespaceAliasImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppNamespaceImpl;
import com.jetbrains.cidr.lang.psi.impl.OCExternBlockImpl;
import java.util.Collection;
import java.util.Arrays;
import com.jetbrains.cidr.lang.psi.impl.OCGenericArgumentImpl;
import com.jetbrains.cidr.lang.psi.impl.OCGenericArgumentsListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCGenericParameterImpl;
import com.jetbrains.cidr.lang.psi.impl.OCGenericParametersListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCArgumentSelectorImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMessageArgumentImpl;
import com.jetbrains.cidr.lang.psi.impl.OCArgumentListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCVariadicPackExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppTypeidExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppDeleteExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppNewExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCNSDictionaryLiteralImpl;
import com.jetbrains.cidr.lang.psi.impl.OCNSArrayLiteralImpl;
import com.jetbrains.cidr.lang.psi.impl.OCGenericSelectionAssociationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCGenericSelectionExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDeclarationOrExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCStatementExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCBlockExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCThrowExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCProtocolExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCEncodeExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCBoxedExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAvailabilityExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSelectorExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSendMessageExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCLiteralExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCUDLiteralExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCParenthesizedExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCReferenceExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCQualifiedExpressionAccessorImpl;
import com.jetbrains.cidr.lang.psi.impl.OCQualifiedExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCallExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCArraySelectionExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCPostfixExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCPrefixExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSizeofExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCastExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCUnaryExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCBinaryExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCConditionalExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAssignmentExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCommaExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCFinallySectionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCatchSectionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCTryStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAutoReleasePoolStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSynchornizedStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCForeachStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCForStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCIfStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSwitchStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCWhileStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDoWhileStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCGotoStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCBreakStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCContinueStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCReturnStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCEmptyStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDeclarationStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCExpressionStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCaseStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCLabeledStatementImpl;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.impl.OCEagerBlockStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCQualifiedDesignatorImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDesignatedInitializerImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCompoundInitializerImpl;
import com.jetbrains.cidr.lang.psi.impl.OCParameterDeclarationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCParameterListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCTypeElementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCReferenceElementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCEmptyName;
import com.jetbrains.cidr.lang.psi.impl.OCMacroCallArgumentImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMacroReferenceElementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMacroCallImpl;
import com.jetbrains.cidr.lang.psi.impl.OCUnionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCEnumImpl;
import com.jetbrains.cidr.lang.psi.impl.OCStructImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMethodSelectorPartImpl;
import com.jetbrains.cidr.lang.psi.impl.OCPropertyAttributeImpl;
import com.jetbrains.cidr.lang.psi.impl.OCPropertyAttributesListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCPropertyImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMethodImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCategoryNameImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSuperClassRefImpl;
import com.jetbrains.cidr.lang.psi.impl.OCInstanceVariablesListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCProtocolListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAttributeParametersImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAttributeImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAttributeListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSynthesizePropertyImpl;
import com.jetbrains.cidr.lang.psi.impl.OCSynthesizePropertiesListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCClassPredeclarationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCClassPredeclarationListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCImplementationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCProtocolImpl;
import com.jetbrains.cidr.lang.psi.impl.OCInterfaceImpl;
import com.jetbrains.cidr.lang.psi.impl.OCImportModuleStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCIncludeDirectiveImpl;
import com.jetbrains.cidr.lang.psi.impl.OCUndefDirectiveImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDefineDirectiveImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMacroParameterListImpl;
import com.jetbrains.cidr.lang.psi.impl.OCMacroParameterImpl;
import com.jetbrains.cidr.lang.psi.impl.OCFunctionDeclarationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCFunctionDefinitionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDeclaratorImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDeclarationImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCompatibilityAliasImpl;
import com.jetbrains.cidr.lang.psi.impl.OCPragmaImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCppPragmaImpl;
import com.jetbrains.cidr.lang.psi.impl.OCDefinedDirectiveImpl;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.impl.OCDirectiveImpl;
import gnu.trove.THashSet;
import com.intellij.psi.tree.TokenSet;

public interface OCElementTypes
{
    public static final OCElementType DIRECTIVE = new OCPsiElementType("DIRECTIVE", OCDirectiveImpl.class);
    public static final OCElementType DEFINED_DIRECTIVE = new OCPsiElementType("DIRECTIVE", OCDefinedDirectiveImpl.class);
    public static final OCElementType CPP_PRAGMA = new OCPsiElementType("CPP_PRAGMA", OCCppPragmaImpl.class);
    public static final OCElementType PRAGMA = new OCPsiElementType("PRAGMA", OCPragmaImpl.class);
    public static final OCElementType COMPATIBILITY_ALIAS = new OCPsiElementType("COMPATIBILITY_ALIAS", OCCompatibilityAliasImpl.class);
    public static final OCElementType DECLARATION = new OCPsiElementType("DECLARATION", OCDeclarationImpl.class);
    public static final OCElementType DECLARATOR = new OCPsiElementType("DECLARATOR", OCDeclaratorImpl.class);
    public static final OCElementType FUNCTION_KR_DEFINITION = new OCPsiElementType("FUNCTION_KR_DEFINITION", OCFunctionDefinitionImpl.class);
    public static final OCElementType FUNCTION_DEFINITION = new OCPsiElementType("FUNCTION_DEFINITION", OCFunctionDefinitionImpl.class);
    public static final OCElementType FUNCTION_DECLARATION = new OCPsiElementType("FUNCTION_PREDEFINITION", OCFunctionDeclarationImpl.class);
    public static final OCElementType MACRO_PARAMETER = new OCPsiElementType("MACRO_PARAMETER", OCMacroParameterImpl.class);
    public static final OCElementType MACRO_PARAMETER_LIST = new OCPsiElementType("MACRO_PARAMETER_LIST", OCMacroParameterListImpl.class);
    public static final OCElementType MACRO_DEFINITION = new OCPsiElementType("MACRO_DEFINITION", OCDefineDirectiveImpl.class);
    public static final OCElementType MACRO_UNDEFINITION = new OCPsiElementType("MACRO_UNDEFINITION", OCUndefDirectiveImpl.class);
    public static final OCElementType IMPORT_DIRECTIVE = new OCPsiElementType("IMPORT_DIRECTIVE", OCIncludeDirectiveImpl.class);
    public static final OCElementType IMPORT_MODULE_STATEMENT = new OCPsiElementType("IMPORT_MODULE_STATEMENT", OCImportModuleStatementImpl.class);
    public static final OCElementType INTERFACE = new OCPsiElementType("INTERFACE", OCInterfaceImpl.class);
    public static final OCElementType PROTOCOL = new OCPsiElementType("PROTOCOL", OCProtocolImpl.class);
    public static final OCElementType IMPLEMENTATION = new OCPsiElementType("IMPLEMENTATION", OCImplementationImpl.class);
    public static final OCElementType CLASS_PREDEF_LIST = new OCPsiElementType("CLASS_PREDEF_LIST", OCClassPredeclarationListImpl.class);
    public static final OCElementType CLASS_PREDEF = new OCPsiElementType("CLASS_PREDEF", OCClassPredeclarationImpl.class);
    public static final OCElementType SYNTHESIZED_PROPERTIES_LIST = new OCPsiElementType("SYNTHESIZED_PROPERTY_LIST", OCSynthesizePropertiesListImpl.class);
    public static final OCElementType SYNTHESIZED_PROPERTY = new OCPsiElementType("SYNTHESIZED_PROPERTY", OCSynthesizePropertyImpl.class);
    public static final OCElementType ATTRIBUTES = new OCPsiElementType("ATTRIBUTES", OCAttributeListImpl.class);
    public static final OCElementType ATTRIBUTE = new OCPsiElementType("ATTRIBUTE", OCAttributeImpl.class);
    public static final OCElementType ATTRIBUTE_PARAMETERS = new OCPsiElementType("ATTRIBUTE_PARAMETERS", OCAttributeParametersImpl.class);
    public static final OCElementType PROTOCOL_LIST = new OCPsiElementType("PROTOCOL_LIST", OCProtocolListImpl.class) {
        public boolean isLeftBound() {
            return true;
        }
    };
    public static final OCElementType INSTANCE_VARIABLES_LIST = new OCPsiElementType("INSTANCE_VARIABLES_LIST", OCInstanceVariablesListImpl.class) {
        public boolean isLeftBound() {
            return true;
        }
    };
    public static final OCElementType SUPER_CLASS_REF = new OCPsiElementType("SUPER_CLASS_REF", OCSuperClassRefImpl.class) {
        public boolean isLeftBound() {
            return true;
        }
    };
    public static final OCElementType CATEGORY_NAME = new OCPsiElementType("CATEGORY_NAME", OCCategoryNameImpl.class);
    public static final OCElementType METHOD = new OCPsiElementType("METHOD", OCMethodImpl.class);
    public static final OCElementType PROPERTY = new OCPsiElementType("PROPERTY", OCPropertyImpl.class);
    public static final OCElementType PROPERTY_ATTRIBUTES_LIST = new OCPsiElementType("PROPERTY_ATTRIBUTES_LIST", OCPropertyAttributesListImpl.class);
    public static final OCElementType PROPERTY_ATTRIBUTE = new OCPsiElementType("PROPERTY_ATTRIBUTE", OCPropertyAttributeImpl.class);
    public static final OCElementType METHOD_SELECTOR_PART = new OCPsiElementType("METHOD_SELECTOR_PART", OCMethodSelectorPartImpl.class);
    public static final OCElementType STRUCT = new OCPsiElementType("STRUCT", OCStructImpl.class);
    public static final OCElementType ENUM = new OCPsiElementType("ENUM", OCEnumImpl.class);
    public static final OCElementType UNION = new OCPsiElementType("UNION", OCUnionImpl.class);
    public static final OCElementType MACRO_CALL = new OCPsiElementType("MACRO_CALL", OCMacroCallImpl.class);
    public static final OCElementType MACRO_REF = new OCPsiElementType("MACRO_REF", OCMacroReferenceElementImpl.class);
    public static final OCElementType MACRO_ARGUMENT = new OCPsiElementType("MACRO_ARGUMENT", OCMacroCallArgumentImpl.class);
    public static final OCElementType EMPTY_NAME = new OCPsiElementType("EMPTY_NAME", OCEmptyName.class);
    public static final OCElementType REFERENCE_ELEMENT = new OCPsiElementType("REFERENCE_ELEMENT", OCReferenceElementImpl.class);
    public static final OCElementType TYPE_ELEMENT = new OCPsiElementType("TYPE_ELEMENT", OCTypeElementImpl.class);
    public static final OCElementType ANONYMOUS_DECLARATOR = new OCElementType("ANONYMOUS_DECLARATOR");
    public static final OCElementType PARAMETER_LIST = new OCPsiElementType("PARAMETER_LIST", OCParameterListImpl.class);
    public static final OCElementType PARAMETER_DECLARATION = new OCPsiElementType("PARAMETER_DECLARATION", OCParameterDeclarationImpl.class);
    public static final OCElementType COMPOUND_INITIALIZER = new OCPsiElementType("COMPOUND_INITIALIZER", OCCompoundInitializerImpl.class);
    public static final OCElementType DESIGNATED_INITIALIZER = new OCPsiElementType("DESIGNATED_INITIALIZER", OCDesignatedInitializerImpl.class);
    public static final OCElementType QUALIFIED_DESIGNATOR = new OCPsiElementType("QUALIFIED_DESIGNATOR", OCQualifiedDesignatorImpl.class);
    public static final OCElementType EAGER_BLOCK_STATEMENT = new OCPsiElementType("EAGER_BLOCK", OCEagerBlockStatementImpl.class);
    public static final OCLazyBlockStatementElementType LAZY_BLOCK_STATEMENT = new OCLazyBlockStatementElementType();
    public static final TokenSet BLOCK_STATEMENTS = TokenSet.create(new IElementType[] { OCElementTypes.EAGER_BLOCK_STATEMENT, OCElementTypes.LAZY_BLOCK_STATEMENT });
    public static final OCElementType LABELED_STATEMENT = new OCPsiElementType("LABELED_STATEMENT", OCLabeledStatementImpl.class);
    public static final OCElementType CASE_STATEMENT = new OCPsiElementType("CASE_STATEMENT", OCCaseStatementImpl.class);
    public static final OCElementType EXPRESSION_STATEMENT = new OCPsiElementType("EXPRESSION_STATEMENT", OCExpressionStatementImpl.class);
    public static final OCElementType DECLARATION_STATEMENT = new OCPsiElementType("DECLARATION_STATEMENT", OCDeclarationStatementImpl.class);
    public static final OCElementType EMPTY_STATEMENT = new OCPsiElementType("EMPTY_STATEMENT", OCEmptyStatementImpl.class);
    public static final OCElementType RETURN_STATEMENT = new OCPsiElementType("RETURN_STATEMENT", OCReturnStatementImpl.class);
    public static final OCElementType CONTINUE_STATEMENT = new OCPsiElementType("CONTINUE_STATEMENT", OCContinueStatementImpl.class);
    public static final OCElementType BREAK_STATEMENT = new OCPsiElementType("BREAK_STATEMENT", OCBreakStatementImpl.class);
    public static final OCElementType GOTO_STATEMENT = new OCPsiElementType("GOTO_STATEMENT", OCGotoStatementImpl.class);
    public static final OCElementType DO_WHILE_STATEMENT = new OCPsiElementType("DO_WHILE_STATEMENT", OCDoWhileStatementImpl.class);
    public static final OCElementType WHILE_STATEMENT = new OCPsiElementType("WHILE_STATEMENT", OCWhileStatementImpl.class);
    public static final OCElementType SWITCH_STATEMENT = new OCPsiElementType("SWITCH_STATEMENT", OCSwitchStatementImpl.class);
    public static final OCElementType IF_STATEMENT = new OCPsiElementType("IF_STATEMENT", OCIfStatementImpl.class);
    public static final OCElementType FOR_STATEMENT = new OCPsiElementType("FOR_STATEMENT", OCForStatementImpl.class);
    public static final OCElementType FOREACH_STATEMENT = new OCPsiElementType("FOREACH_STATEMENT", OCForeachStatementImpl.class);
    public static final OCElementType SYNCHRONIZED_STATEMENT = new OCPsiElementType("SYNCHRONIZED_STATEMENT", OCSynchornizedStatementImpl.class);
    public static final OCElementType AUTO_RELEASE_POOL_STATEMENT = new OCPsiElementType("AUTO_RELEASE_POOL_STATEMENT", OCAutoReleasePoolStatementImpl.class);
    public static final OCElementType TRY_STATEMENT = new OCPsiElementType("TRY_STATEMENT", OCTryStatementImpl.class);
    public static final OCElementType CATCH_SECTION = new OCPsiElementType("CATCH_SECTION", OCCatchSectionImpl.class);
    public static final OCElementType FINALLY_SECTION = new OCPsiElementType("FINALLY_SECTION", OCFinallySectionImpl.class);
    public static final OCElementType COMMA_EXPRESSION = new OCPsiElementType("COMMA_EXPRESSION", OCCommaExpressionImpl.class);
    public static final OCElementType ASSIGNMENT_EXPRESSION = new OCPsiElementType("ASSIGNMENT_EXPRESSION", OCAssignmentExpressionImpl.class);
    public static final OCElementType CONDITIONAL_EXPRESSION = new OCPsiElementType("CONDITIONAL_EXPRESSION", OCConditionalExpressionImpl.class);
    public static final OCElementType BINARY_EXPRESSION = new OCPsiElementType("BINARY_EXPRESSION", OCBinaryExpressionImpl.class);
    public static final OCElementType UNARY_EXPRESSION = new OCPsiElementType("UNARY_EXPRESSION", OCUnaryExpressionImpl.class);
    public static final OCElementType CAST_EXPRESSION = new OCPsiElementType("CAST_EXPRESSION", OCCastExpressionImpl.class);
    public static final OCElementType SIZEOF_EXPRESSION = new OCPsiElementType("SIZEOF_EXPRESSION", OCSizeofExpressionImpl.class);
    public static final OCElementType PREFIX_EXPRESSION = new OCPsiElementType("PREFIX_EXPRESSION", OCPrefixExpressionImpl.class);
    public static final OCElementType POSTFIX_EXPRESSION = new OCPsiElementType("POSTFIX_EXPRESSION", OCPostfixExpressionImpl.class);
    public static final OCElementType ARRAY_INDEX_EXPRESSION = new OCPsiElementType("ARRAY_INDEX_EXPRESSION", OCArraySelectionExpressionImpl.class);
    public static final OCElementType CALL_EXPRESSION = new OCPsiElementType("CALL_EXPRESSION", OCCallExpressionImpl.class);
    public static final OCElementType QUALIFIED_EXPRESSION = new OCPsiElementType("QUALIFIED_EXPRESSION", OCQualifiedExpressionImpl.class);
    public static final OCElementType QUALIFIED_EXPRESSION_ACCESSOR = new OCPsiElementType("QUALIFIED_EXPRESSION_ACCESSOR", OCQualifiedExpressionAccessorImpl.class);
    public static final OCElementType REFERENCE_EXPRESSION = new OCPsiElementType("REFERENCE_EXPRESSION", OCReferenceExpressionImpl.class);
    public static final OCElementType PAREN_EXPRESSION = new OCPsiElementType("PAREN_EXPRESSION", OCParenthesizedExpressionImpl.class);
    public static final OCElementType CPP_UDL_EXPRESSION = new OCPsiElementType("UD_LITERAL_EXPRESSION", OCUDLiteralExpressionImpl.class);
    public static final OCElementType LITERAL_EXPRESSION = new OCPsiElementType("LITERAL_EXPRESSION", OCLiteralExpressionImpl.class);
    public static final OCElementType MESSAGE_EXPRESSION = new OCPsiElementType("MESSAGE_EXPRESSION", OCSendMessageExpressionImpl.class);
    public static final OCElementType SELECTOR_EXPRESSION = new OCPsiElementType("SELECTOR_EXPRESSION", OCSelectorExpressionImpl.class);
    public static final OCElementType AVAILABILITY_EXPRESSION = new OCPsiElementType("AVAILABILITY_EXPRESSION", OCAvailabilityExpressionImpl.class);
    public static final OCElementType BOXED_EXPRESSION = new OCPsiElementType("BOXED_EXPRESSION", OCBoxedExpressionImpl.class);
    public static final OCElementType ENCODE_TYPE_EXPRESSION = new OCPsiElementType("ENCODE_TYPE_EXPRESSION", OCEncodeExpressionImpl.class);
    public static final OCElementType PROTOCOL_EXPRESSION = new OCPsiElementType("PROTOCOL_EXPRESSION", OCProtocolExpressionImpl.class);
    public static final OCElementType THROW_EXPRESSION = new OCPsiElementType("THROW_EXPRESSION", OCThrowExpressionImpl.class);
    public static final OCElementType BLOCK_EXPRESSION = new OCPsiElementType("BLOCK_EXPRESSION", OCBlockExpressionImpl.class);
    public static final OCElementType STATEMENT_EXPRESSION = new OCPsiElementType("STATEMENT_EXPRESSION", OCStatementExpressionImpl.class);
    public static final OCElementType DECLARATION_OR_EXPRESSION = new OCPsiElementType("DECLARATION_OR_EXPRESSION", OCDeclarationOrExpressionImpl.class);
    public static final OCElementType GENERIC_SELECTION_EXPRESSION = new OCPsiElementType("GENERIC_SELECTION_EXPRESSION", OCGenericSelectionExpressionImpl.class);
    public static final OCElementType GENERIC_SELECTION_ASSOCIATION = new OCPsiElementType("GENERIC_SELECTION_ASSOCIATION", OCGenericSelectionAssociationImpl.class);
    public static final OCElementType NS_ARRAY_LITERAL = new OCPsiElementType("NS_ARRAY_LITERAL", OCNSArrayLiteralImpl.class);
    public static final OCElementType NS_DICTIONARY_LITERAL = new OCPsiElementType("NS_DICTIONARY_LITERAL", OCNSDictionaryLiteralImpl.class);
    public static final OCElementType CPP_NEW_EXPRESSION = new OCPsiElementType("CPP_NEW_EXPRESSION", OCCppNewExpressionImpl.class);
    public static final OCElementType CPP_DELETE_EXPRESSION = new OCPsiElementType("CPP_DELETE_EXPRESSION", OCCppDeleteExpressionImpl.class);
    public static final OCElementType CPP_TYPEID_EXPRESSION = new OCPsiElementType("CPP_TYPEID_EXPRESSION", OCCppTypeidExpressionImpl.class);
    public static final OCElementType CPP_VARIADIC_PACK_EXPRESSION = new OCPsiElementType("CPP_VARIADIC_PACK_EXPRESSION", OCVariadicPackExpressionImpl.class);
    public static final OCElementType ARGUMENT_LIST = new OCPsiElementType("ARGUMENT_LIST", OCArgumentListImpl.class);
    public static final OCElementType MESSAGE_ARGUMENT = new OCPsiElementType("MESSAGE_ARGUMENT", OCMessageArgumentImpl.class);
    public static final OCElementType ARGUMENT_SELECTOR = new OCPsiElementType("ARGUMENT_SELECTOR", OCArgumentSelectorImpl.class);
    public static final OCElementType GENERIC_PARAMETERS_LIST = new OCPsiElementType("GENERIC_PARAMETERS_LIST", OCGenericParametersListImpl.class);
    public static final OCElementType GENERIC_PARAMETER = new OCPsiElementType("GENERIC_PARAMETER", OCGenericParameterImpl.class);
    public static final OCElementType GENERIC_ARGUMENTS_LIST = new OCPsiElementType("GENERIC_ARGUMENTS_LIST", OCGenericArgumentsListImpl.class);
    public static final OCElementType GENERIC_ARGUMENT = new OCPsiElementType("GENERIC_ARGUMENT", OCGenericArgumentImpl.class);
    public static final TokenSet CLASSES = TokenSet.create(new IElementType[] { OCElementTypes.INTERFACE, OCElementTypes.IMPLEMENTATION, OCElementTypes.PROTOCOL });
    public static final TokenSet STRUCTURE_TYPES = TokenSet.create(new IElementType[] { OCElementTypes.STRUCT, OCElementTypes.UNION, OCElementTypes.ENUM });
    public static final TokenSet STATEMENTS = TokenSet.create(new IElementType[] { OCElementTypes.EAGER_BLOCK_STATEMENT, OCElementTypes.LAZY_BLOCK_STATEMENT, OCElementTypes.LABELED_STATEMENT, OCElementTypes.CASE_STATEMENT, OCElementTypes.EXPRESSION_STATEMENT, OCElementTypes.DECLARATION_STATEMENT, OCElementTypes.EMPTY_STATEMENT, OCElementTypes.RETURN_STATEMENT, OCElementTypes.CONTINUE_STATEMENT, OCElementTypes.BREAK_STATEMENT, OCElementTypes.GOTO_STATEMENT, OCElementTypes.DO_WHILE_STATEMENT, OCElementTypes.WHILE_STATEMENT, OCElementTypes.SWITCH_STATEMENT, OCElementTypes.IF_STATEMENT, OCElementTypes.FOR_STATEMENT, OCElementTypes.FOREACH_STATEMENT, OCElementTypes.SYNCHRONIZED_STATEMENT, OCElementTypes.AUTO_RELEASE_POOL_STATEMENT, OCElementTypes.TRY_STATEMENT });
    public static final TokenSet DIRECTIVES = TokenSet.create(new IElementType[] { OCElementTypes.DIRECTIVE, OCElementTypes.MACRO_DEFINITION, OCElementTypes.MACRO_UNDEFINITION, OCElementTypes.IMPORT_DIRECTIVE, OCElementTypes.DEFINED_DIRECTIVE, OCElementTypes.PRAGMA });
    public static final TokenSet IMPORTANT_DIRECTIVES = TokenSet.create(new IElementType[] { OCElementTypes.MACRO_DEFINITION, OCElementTypes.MACRO_UNDEFINITION, OCElementTypes.IMPORT_DIRECTIVE });
    public static final TokenSet NS_COLLECTION_LITERALS = TokenSet.create(new IElementType[] { OCElementTypes.NS_ARRAY_LITERAL, OCElementTypes.NS_DICTIONARY_LITERAL });
    public static final OCFileElementType EXPRESSION_CODE_FRAGMENT = new OCExpressionCodeFragmentType("EXPRESSION_CODE_FRAGMENT");
    public static final OCFileElementType EXPRESSION_OR_STATEMENTS_CODE_FRAGMENT = new OCExpressionOrStatementsCodeFragmentType("EXPRESSION_OR_STATEMENTS_CODE_FRAGMENT");
    public static final OCFileElementType TYPE_CODE_FRAGMENT = new OCTypeCodeFragmentType("TYPE_CODE_FRAGMENT");
    public static final THashSet<String> PARAMETER_TYPE_QUALIFIERS = new THashSet((Collection)Arrays.asList("oneway", "in", "out", "inout", "bycopy", "byref"));
    public static final THashSet<String> PARAMETER_TYPE_NULLABILITY_QUALIFIERS = new THashSet((Collection)Arrays.asList("nullable", "nonnull"));
    public static final String NULL_UNSPECIFIED_NULLABILITY_QUALIFIER = "null_unspecified";
    public static final OCElementType CPP_EXTERN_BLOCK = new OCPsiElementType("CPP_EXTERN_BLOCK", OCExternBlockImpl.class);
    public static final OCElementType CPP_NAMESPACE = new OCPsiElementType("CPP_NAMESPACE", OCCppNamespaceImpl.class);
    public static final OCElementType CPP_NAMESPACE_ALIAS = new OCPsiElementType("CPP_NAMESPACE_ALIAS", OCCppNamespaceAliasImpl.class);
    public static final OCElementType CPP_NAMESPACE_QUALIFIER = new OCPsiElementType("CPP_NAMESPACE_QUALIFIER", OCCppNamespaceQualifierImpl.class);
    public static final OCElementType CPP_QUALIFIED_POINTER = new OCPsiElementType("CPP_QUALIFIED_POINTER", OCCppQualifiedPointerImpl.class);
    public static final OCElementType CPP_USING_STATEMENT = new OCPsiElementType("CPP_USING_STATEMENT", OCCppUsingStatementImpl.class);
    public static final OCElementType CPP_BASE_CLAUSE_LIST = new OCPsiElementType("CPP_BASE_CLAUSE_LIST", OCCppBaseClauseListImpl.class);
    public static final OCElementType CPP_BASE_CLAUSE = new OCPsiElementType("CPP_BASE_CLAUSE", OCCppBaseClauseImpl.class);
    public static final OCElementType TEMPLATE_ARGUMENT_LIST = new OCPsiElementType("TEMPLATE_ARGUMENT_LIST", OCTemplateArgumentListImpl.class);
    public static final OCElementType CPP_TEMPLATE_PARAMETER_LIST = new OCPsiElementType("CPP_TEMPLATE_PARAMETER_LIST", OCTemplateParameterListImpl.class);
    public static final OCElementType CPP_TYPE_PARAMETER_DECLARATION = new OCPsiElementType("CPP_TEMPLATE_PARAMETER_DECLARATION", OCTypeParameterDeclarationImpl.class);
    public static final OCElementType CPP_CONSTRUCTOR_INITIALIZATION_LIST = new OCPsiElementType("CPP_CONSTRUCTOR_INITIALIZATION_LIST", OCConstructorInitializationListImpl.class);
    public static final OCElementType CPP_CONSTRUCTOR_FIELD_INITIALIZER = new OCPsiElementType("CPP_CONSTRUCTOR_FIELD_INITIALIZER", OCConstructorFieldInitializerImpl.class);
    public static final OCElementType CPP_LAMBDA_EXPRESSION = new OCPsiElementType("CPP_LAMBDA_EXPRESSION", OCLambdaExpressionImpl.class);
    public static final OCElementType CPP_LAMBDA_INTRODUCER = new OCPsiElementType("CPP_LAMBDA_INTRODUCER", OCLambdaIntroducerImpl.class);
    public static final OCElementType CPP_NOEXCEPT_SPECIFIER = new OCPsiElementType("CPP_NOEXCEPT_SPECIFIER", OCNoexceptSpecifierImpl.class);
    public static final OCElementType CPP_EXCEPTION_SPECIFICATION = new OCPsiElementType("CPP_EXCEPTION_SPECIFICATION", OCExceptionSpecificationImpl.class);
    public static final OCElementType CPP_STATIC_ASSERT_STATEMENT = new OCPsiElementType("CPP_STATIC_ASSERT_STATEMENT", OCCppStaticAssertImpl.class);
    public static final OCElementType UNKNOWN_CPP_CODE = new OCPsiElementType("UNKNOWN_CPP_CODE", OCUnknownCppCodeImpl.class);
    public static final OCElementType OBJC_KEYWORD = new OCPsiElementType("OBJC_KEYWORD", OCObjCKeywordImpl.class);
    public static final OCElementType OBJC_ERROR_KEYWORD = new OCPsiElementType("OBJC_ERROR_KEYWORD", OCObjCErrorKeywordImpl.class);
    public static final OCElementType ASM_STATEMENT = new OCPsiElementType("ASM_STATEMENT", OCAsmStatementImpl.class);
    public static final OCElementType ASM_STATEMENT_PART = new OCPsiElementType("ASM_STATEMENT_PART", OCAsmStatementPartImpl.class);
    public static final TokenSet EXPRESSIONS = TokenSet.create(new IElementType[] { OCElementTypes.COMMA_EXPRESSION, OCElementTypes.ASSIGNMENT_EXPRESSION, OCElementTypes.CONDITIONAL_EXPRESSION, OCElementTypes.BINARY_EXPRESSION, OCElementTypes.UNARY_EXPRESSION, OCElementTypes.CAST_EXPRESSION, OCElementTypes.PREFIX_EXPRESSION, OCElementTypes.POSTFIX_EXPRESSION, OCElementTypes.ARRAY_INDEX_EXPRESSION, OCElementTypes.CALL_EXPRESSION, OCElementTypes.QUALIFIED_EXPRESSION, OCElementTypes.REFERENCE_EXPRESSION, OCElementTypes.PAREN_EXPRESSION, OCElementTypes.LITERAL_EXPRESSION, OCElementTypes.MESSAGE_EXPRESSION, OCElementTypes.SELECTOR_EXPRESSION, OCElementTypes.ENCODE_TYPE_EXPRESSION, OCElementTypes.PROTOCOL_EXPRESSION, OCElementTypes.SIZEOF_EXPRESSION, OCElementTypes.COMPOUND_INITIALIZER, OCElementTypes.BLOCK_EXPRESSION, OCElementTypes.STATEMENT_EXPRESSION, OCElementTypes.CPP_NEW_EXPRESSION, OCElementTypes.CPP_DELETE_EXPRESSION, OCElementTypes.THROW_EXPRESSION, OCElementTypes.DEFINED_DIRECTIVE, OCElementTypes.CPP_LAMBDA_EXPRESSION, OCElementTypes.BOXED_EXPRESSION, OCElementTypes.CPP_TYPEID_EXPRESSION, OCElementTypes.CPP_VARIADIC_PACK_EXPRESSION, OCElementTypes.GENERIC_SELECTION_EXPRESSION, OCElementTypes.CPP_UDL_EXPRESSION, OCElementTypes.AVAILABILITY_EXPRESSION });
    public static final TokenSet COMPOUND_INITIALIZER_EXPRESSIONS = TokenSet.orSet(new TokenSet[] { OCElementTypes.EXPRESSIONS, TokenSet.create(new IElementType[] { OCElementTypes.DESIGNATED_INITIALIZER }) });
    public static final TokenSet TYPE_ARGUMENT_LIST = TokenSet.create(new IElementType[] { OCElementTypes.TEMPLATE_ARGUMENT_LIST, OCElementTypes.GENERIC_ARGUMENTS_LIST });
    public static final TokenSet LIST_OWNER_SET = TokenSet.create(new IElementType[] { OCElementTypes.CALL_EXPRESSION, OCElementTypes.SELECTOR_EXPRESSION, OCElementTypes.AVAILABILITY_EXPRESSION, OCElementTypes.ENCODE_TYPE_EXPRESSION, OCElementTypes.CPP_NOEXCEPT_SPECIFIER, OCElementTypes.CPP_EXCEPTION_SPECIFICATION, OCElementTypes.TYPE_ELEMENT, OCElementTypes.CPP_TYPEID_EXPRESSION, OCElementTypes.SIZEOF_EXPRESSION, OCElementTypes.CPP_STATIC_ASSERT_STATEMENT });
    
    public enum SelfSuperToken
    {
        SELF, 
        SUPER;
    }
}
