// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter;

import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CodeStyleSettings;

public class OCQtCodeStyle extends OCPredefinedCodeStyleBase
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCQtCodeStyle() {
        super("Qt", "qt");
    }
    
    @Override
    public void apply(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCQtCodeStyle", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final CommonCodeStyleSettings.IndentOptions indentOptions = commonSettings.getIndentOptions();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        Label_0822: {
            try {
                commonSettings.KEEP_LINE_BREAKS = true;
                commonSettings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE = true;
                commonSettings.KEEP_BLANK_LINES_IN_DECLARATIONS = 1;
                commonSettings.KEEP_BLANK_LINES_IN_CODE = 2;
                commonSettings.KEEP_BLANK_LINES_BEFORE_RBRACE = 1;
                commonSettings.BLANK_LINES_BEFORE_IMPORTS = 0;
                commonSettings.BLANK_LINES_AFTER_IMPORTS = 0;
                commonSettings.BLANK_LINES_AROUND_CLASS = 0;
                commonSettings.BLANK_LINES_AROUND_FIELD = 1;
                commonSettings.BLANK_LINES_AROUND_METHOD = 0;
                commonSettings.BLANK_LINES_BEFORE_METHOD_BODY = 0;
                commonSettings.BLANK_LINES_AROUND_METHOD_IN_INTERFACE = 0;
                commonSettings.BLANK_LINES_AROUND_FIELD_IN_INTERFACE = 0;
                ocCodeStyleSettings.BLANK_LINES_AROUND_NAMESPACE = 0;
                commonSettings.ALIGN_MULTILINE_BINARY_OPERATION = false;
                commonSettings.ALIGN_GROUP_FIELD_DECLARATIONS = false;
                commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
                commonSettings.SPACE_AROUND_BITWISE_OPERATORS = true;
                commonSettings.SPACE_AROUND_EQUALITY_OPERATORS = true;
                commonSettings.SPACE_AROUND_LOGICAL_OPERATORS = true;
                commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS = true;
                commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS = true;
                commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS = true;
                commonSettings.SPACE_AROUND_SHIFT_OPERATORS = true;
                commonSettings.SPACE_AROUND_UNARY_OPERATOR = false;
                commonSettings.SPACE_AFTER_COMMA = true;
                commonSettings.SPACE_BEFORE_COMMA = false;
                commonSettings.SPACE_AFTER_SEMICOLON = true;
                commonSettings.SPACE_BEFORE_SEMICOLON = false;
                commonSettings.SPACE_WITHIN_PARENTHESES = false;
                commonSettings.SPACE_BEFORE_QUEST = true;
                commonSettings.SPACE_AFTER_QUEST = true;
                commonSettings.SPACE_BEFORE_COLON = true;
                commonSettings.SPACE_AFTER_COLON = true;
                final CommonCodeStyleSettings commonCodeStyleSettings = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings2 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings3 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings4 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings5 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings6 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings7 = commonSettings;
                final boolean space_WITHIN_METHOD_PARENTHESES = false;
                commonCodeStyleSettings7.SPACE_WITHIN_SWITCH_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings6.SPACE_WITHIN_CATCH_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings5.SPACE_WITHIN_TRY_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings4.SPACE_WITHIN_FOR_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings3.SPACE_WITHIN_WHILE_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings2.SPACE_WITHIN_IF_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings.SPACE_WITHIN_METHOD_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                final CommonCodeStyleSettings commonCodeStyleSettings8 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings9 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings10 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings11 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings12 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings13 = commonSettings;
                final boolean b = true;
                commonCodeStyleSettings13.SPACE_BEFORE_SWITCH_PARENTHESES = b;
                commonCodeStyleSettings12.SPACE_BEFORE_CATCH_PARENTHESES = b;
                commonCodeStyleSettings11.SPACE_BEFORE_TRY_PARENTHESES = b;
                commonCodeStyleSettings10.SPACE_BEFORE_FOR_PARENTHESES = b;
                commonCodeStyleSettings9.SPACE_BEFORE_WHILE_PARENTHESES = b;
                commonCodeStyleSettings8.SPACE_BEFORE_IF_PARENTHESES = b;
                final CommonCodeStyleSettings commonCodeStyleSettings14 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings15 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings16 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings17 = commonSettings;
                final boolean b2 = true;
                commonCodeStyleSettings17.FINALLY_ON_NEW_LINE = b2;
                commonCodeStyleSettings16.CATCH_ON_NEW_LINE = b2;
                commonCodeStyleSettings15.WHILE_ON_NEW_LINE = b2;
                commonCodeStyleSettings14.ELSE_ON_NEW_LINE = b2;
                final CommonCodeStyleSettings commonCodeStyleSettings18 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings19 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings20 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings21 = commonSettings;
                final boolean b3 = false;
                commonCodeStyleSettings21.FOR_BRACE_FORCE = (b3 ? 1 : 0);
                commonCodeStyleSettings20.WHILE_BRACE_FORCE = (b3 ? 1 : 0);
                commonCodeStyleSettings19.DOWHILE_BRACE_FORCE = (b3 ? 1 : 0);
                commonCodeStyleSettings18.IF_BRACE_FORCE = (b3 ? 1 : 0);
                commonSettings.SPACE_WITHIN_BRACKETS = false;
                commonSettings.SPACE_WITHIN_BRACES = true;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_BRACES = false;
                commonSettings.SPACE_WITHIN_ARRAY_INITIALIZER_BRACES = false;
                commonSettings.SPACE_WITHIN_EMPTY_ARRAY_INITIALIZER_BRACES = false;
                commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES = false;
                commonSettings.SPACE_BEFORE_METHOD_PARENTHESES = false;
                final CommonCodeStyleSettings commonCodeStyleSettings22 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings23 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings24 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings25 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings26 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings27 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings28 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings29 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings30 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings31 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings32 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings33 = commonSettings;
                final boolean b4 = true;
                commonCodeStyleSettings33.SPACE_BEFORE_SYNCHRONIZED_LBRACE = b4;
                commonCodeStyleSettings32.SPACE_BEFORE_FINALLY_LBRACE = b4;
                commonCodeStyleSettings31.SPACE_BEFORE_CATCH_LBRACE = b4;
                commonCodeStyleSettings30.SPACE_BEFORE_TRY_LBRACE = b4;
                commonCodeStyleSettings29.SPACE_BEFORE_SWITCH_LBRACE = b4;
                commonCodeStyleSettings28.SPACE_BEFORE_DO_LBRACE = b4;
                commonCodeStyleSettings27.SPACE_BEFORE_FOR_LBRACE = b4;
                commonCodeStyleSettings26.SPACE_BEFORE_WHILE_LBRACE = b4;
                commonCodeStyleSettings25.SPACE_BEFORE_ELSE_LBRACE = b4;
                commonCodeStyleSettings24.SPACE_BEFORE_IF_LBRACE = b4;
                commonCodeStyleSettings23.SPACE_BEFORE_METHOD_LBRACE = b4;
                commonCodeStyleSettings22.SPACE_BEFORE_CLASS_LBRACE = b4;
                final CommonCodeStyleSettings commonCodeStyleSettings34 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings35 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings36 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings37 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings38 = commonSettings;
                final boolean method_CALL_CHAIN_WRAP = true;
                commonCodeStyleSettings38.BINARY_OPERATION_WRAP = (method_CALL_CHAIN_WRAP ? 1 : 0);
                commonCodeStyleSettings37.ARRAY_INITIALIZER_WRAP = (method_CALL_CHAIN_WRAP ? 1 : 0);
                commonCodeStyleSettings36.ASSIGNMENT_WRAP = (method_CALL_CHAIN_WRAP ? 1 : 0);
                commonCodeStyleSettings35.TERNARY_OPERATION_WRAP = (method_CALL_CHAIN_WRAP ? 1 : 0);
                commonCodeStyleSettings34.METHOD_CALL_CHAIN_WRAP = (method_CALL_CHAIN_WRAP ? 1 : 0);
                commonSettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE = true;
                commonSettings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = true;
                commonSettings.KEEP_SIMPLE_METHODS_IN_ONE_LINE = true;
                commonSettings.FOR_STATEMENT_WRAP = 1;
                commonSettings.FOR_STATEMENT_LPAREN_ON_NEXT_LINE = false;
                commonSettings.FOR_STATEMENT_RPAREN_ON_NEXT_LINE = false;
                ocCodeStyleSettings.INDENT_NAMESPACE_MEMBERS = 0;
                ocCodeStyleSettings.INDENT_C_STRUCT_MEMBERS = 4;
                ocCodeStyleSettings.INDENT_CLASS_MEMBERS = 4;
                ocCodeStyleSettings.INDENT_VISIBILITY_KEYWORDS = 0;
                ocCodeStyleSettings.INDENT_INSIDE_CODE_BLOCK = 4;
                ocCodeStyleSettings.INDENT_PREPROCESSOR_DIRECTIVE = 0;
                ocCodeStyleSettings.KEEP_STRUCTURES_IN_ONE_LINE = true;
                commonSettings.BRACE_STYLE = 1;
                final CommonCodeStyleSettings commonCodeStyleSettings39 = commonSettings;
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings4 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings5 = ocCodeStyleSettings;
                final int class_BRACE_STYLE = 2;
                ocCodeStyleSettings5.BLOCK_BRACE_PLACEMENT = class_BRACE_STYLE;
                ocCodeStyleSettings4.METHOD_BRACE_PLACEMENT = class_BRACE_STYLE;
                ocCodeStyleSettings3.FUNCTION_BRACE_PLACEMENT = class_BRACE_STYLE;
                ocCodeStyleSettings2.NAMESPACE_BRACE_PLACEMENT = class_BRACE_STYLE;
                commonCodeStyleSettings39.CLASS_BRACE_STYLE = class_BRACE_STYLE;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_WRAP = 5;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_WRAP = 5;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_WRAP = 5;
                ocCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.SPACE_BEFORE_SUPERCLASS_COLON = false;
                ocCodeStyleSettings.SPACE_AFTER_SUPERCLASS_COLON = true;
                ocCodeStyleSettings.SPACE_BEFORE_INIT_LIST_COLON = true;
                ocCodeStyleSettings.SPACE_AFTER_INIT_LIST_COLON = true;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON = 1;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON = 0;
                ocCodeStyleSettings.SPACE_BEFORE_TEMPLATE_CALL_LT = false;
                ocCodeStyleSettings.SPACE_WITHIN_TEMPLATE_CALL_LTGT = false;
                ocCodeStyleSettings.ALIGN_INIT_LIST_IN_COLUMNS = false;
                ocCodeStyleSettings.ENUM_CONSTANTS_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.SPACE_BEFORE_NAMESPACE_LBRACE = true;
                ocCodeStyleSettings.SPACE_WITHIN_FUNCTION_DECLARATION_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_DECLARATION_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_FUNCTION_CALL_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_PROTOCOLS_BRACKETS = false;
                ocCodeStyleSettings.SPACE_BEFORE_INIT_LIST = false;
                ocCodeStyleSettings.SPACE_BEFORE_PROTOCOLS_BRACKETS = false;
                ocCodeStyleSettings.SPACE_BEFORE_POINTER_IN_DECLARATION = true;
                ocCodeStyleSettings.SPACE_BEFORE_REFERENCE_IN_DECLARATION = true;
                ocCodeStyleSettings.SPACE_AFTER_POINTER_IN_DECLARATION = false;
                ocCodeStyleSettings.SPACE_AFTER_REFERENCE_IN_DECLARATION = false;
                ocCodeStyleSettings.SPACE_AFTER_REFERENCE_IN_RVALUE = false;
                ocCodeStyleSettings.SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR = false;
                ocCodeStyleSettings.SPACE_AROUND_PM_OPERATORS = false;
                if (OCQtCodeStyle.$assertionsDisabled) {
                    break Label_0822;
                }
                final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                if (indentOptions2 == null) {
                    break Label_0822;
                }
                break Label_0822;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                if (indentOptions2 == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        indentOptions.INDENT_SIZE = 4;
        indentOptions.CONTINUATION_INDENT_SIZE = 4;
        indentOptions.TAB_SIZE = 4;
        indentOptions.KEEP_INDENTS_ON_EMPTY_LINES = false;
        super.apply(codeStyleSettings);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCQtCodeStyle.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
