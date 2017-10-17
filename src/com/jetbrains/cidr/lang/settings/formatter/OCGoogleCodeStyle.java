// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter;

import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class OCGoogleCodeStyle extends OCPredefinedCodeStyleBase
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCGoogleCodeStyle() {
        super("Google", "google");
    }
    
    public OCGoogleCodeStyle(@NotNull final String s, @NotNull final String s2) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/settings/formatter/OCGoogleCodeStyle", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/lang/settings/formatter/OCGoogleCodeStyle", "<init>"));
        }
        super(s, s2);
    }
    
    @Override
    public void apply(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCGoogleCodeStyle", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.customize(codeStyleSettings);
        super.apply(codeStyleSettings);
    }
    
    protected void customize(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCGoogleCodeStyle", "customize"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final CommonCodeStyleSettings.IndentOptions indentOptions = commonSettings.getIndentOptions();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        Label_0949: {
            try {
                commonSettings.KEEP_LINE_BREAKS = true;
                commonSettings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE = true;
                final CommonCodeStyleSettings commonCodeStyleSettings = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings2 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings3 = commonSettings;
                final boolean keep_BLANK_LINES_IN_DECLARATIONS = true;
                commonCodeStyleSettings3.KEEP_BLANK_LINES_BEFORE_RBRACE = (keep_BLANK_LINES_IN_DECLARATIONS ? 1 : 0);
                commonCodeStyleSettings2.KEEP_BLANK_LINES_IN_CODE = (keep_BLANK_LINES_IN_DECLARATIONS ? 1 : 0);
                commonCodeStyleSettings.KEEP_BLANK_LINES_IN_DECLARATIONS = (keep_BLANK_LINES_IN_DECLARATIONS ? 1 : 0);
                commonSettings.BLANK_LINES_BEFORE_IMPORTS = 0;
                commonSettings.BLANK_LINES_AFTER_IMPORTS = 0;
                commonSettings.BLANK_LINES_AROUND_CLASS = 0;
                commonSettings.BLANK_LINES_AROUND_FIELD = 0;
                commonSettings.BLANK_LINES_AROUND_METHOD = 0;
                commonSettings.BLANK_LINES_BEFORE_METHOD_BODY = 0;
                commonSettings.BLANK_LINES_AROUND_METHOD_IN_INTERFACE = 0;
                commonSettings.ALIGN_MULTILINE_BINARY_OPERATION = false;
                commonSettings.ALIGN_GROUP_FIELD_DECLARATIONS = false;
                commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
                commonSettings.SPACE_AROUND_LOGICAL_OPERATORS = true;
                commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS = true;
                commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS = true;
                commonSettings.SPACE_AROUND_SHIFT_OPERATORS = true;
                commonSettings.SPACE_AROUND_UNARY_OPERATOR = false;
                commonSettings.SPACE_AFTER_COMMA = true;
                commonSettings.SPACE_BEFORE_COMMA = false;
                commonSettings.SPACE_AFTER_SEMICOLON = true;
                commonSettings.SPACE_BEFORE_SEMICOLON = false;
                commonSettings.SPACE_WITHIN_PARENTHESES = false;
                final CommonCodeStyleSettings commonCodeStyleSettings4 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings5 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings6 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings7 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings8 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings9 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings10 = commonSettings;
                final boolean space_WITHIN_METHOD_PARENTHESES = false;
                commonCodeStyleSettings10.SPACE_WITHIN_SWITCH_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings9.SPACE_WITHIN_CATCH_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings8.SPACE_WITHIN_TRY_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings7.SPACE_WITHIN_FOR_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings6.SPACE_WITHIN_WHILE_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings5.SPACE_WITHIN_IF_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings4.SPACE_WITHIN_METHOD_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                final CommonCodeStyleSettings commonCodeStyleSettings11 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings12 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings13 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings14 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings15 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings16 = commonSettings;
                final boolean b = true;
                commonCodeStyleSettings16.SPACE_BEFORE_SWITCH_PARENTHESES = b;
                commonCodeStyleSettings15.SPACE_BEFORE_CATCH_PARENTHESES = b;
                commonCodeStyleSettings14.SPACE_BEFORE_TRY_PARENTHESES = b;
                commonCodeStyleSettings13.SPACE_BEFORE_FOR_PARENTHESES = b;
                commonCodeStyleSettings12.SPACE_BEFORE_WHILE_PARENTHESES = b;
                commonCodeStyleSettings11.SPACE_BEFORE_IF_PARENTHESES = b;
                final CommonCodeStyleSettings commonCodeStyleSettings17 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings18 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings19 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings20 = commonSettings;
                final boolean b2 = false;
                commonCodeStyleSettings20.FINALLY_ON_NEW_LINE = b2;
                commonCodeStyleSettings19.CATCH_ON_NEW_LINE = b2;
                commonCodeStyleSettings18.WHILE_ON_NEW_LINE = b2;
                commonCodeStyleSettings17.ELSE_ON_NEW_LINE = b2;
                commonSettings.SPECIAL_ELSE_IF_TREATMENT = true;
                final CommonCodeStyleSettings commonCodeStyleSettings21 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings22 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings23 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings24 = commonSettings;
                final boolean b3 = true;
                commonCodeStyleSettings24.SPACE_BEFORE_FINALLY_KEYWORD = b3;
                commonCodeStyleSettings23.SPACE_BEFORE_CATCH_KEYWORD = b3;
                commonCodeStyleSettings22.SPACE_BEFORE_WHILE_KEYWORD = b3;
                commonCodeStyleSettings21.SPACE_BEFORE_ELSE_KEYWORD = b3;
                commonSettings.INDENT_CASE_FROM_SWITCH = true;
                final CommonCodeStyleSettings commonCodeStyleSettings25 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings26 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings27 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings28 = commonSettings;
                final boolean b4 = false;
                commonCodeStyleSettings28.FOR_BRACE_FORCE = (b4 ? 1 : 0);
                commonCodeStyleSettings27.WHILE_BRACE_FORCE = (b4 ? 1 : 0);
                commonCodeStyleSettings26.DOWHILE_BRACE_FORCE = (b4 ? 1 : 0);
                commonCodeStyleSettings25.IF_BRACE_FORCE = (b4 ? 1 : 0);
                commonSettings.SPACE_WITHIN_BRACKETS = false;
                commonSettings.SPACE_WITHIN_BRACES = true;
                commonSettings.SPACE_WITHIN_ARRAY_INITIALIZER_BRACES = false;
                commonSettings.SPACE_WITHIN_EMPTY_ARRAY_INITIALIZER_BRACES = false;
                commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES = false;
                commonSettings.SPACE_BEFORE_METHOD_PARENTHESES = false;
                final CommonCodeStyleSettings commonCodeStyleSettings29 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings30 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings31 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings32 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings33 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings34 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings35 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings36 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings37 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings38 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings39 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings40 = commonSettings;
                final boolean b5 = true;
                commonCodeStyleSettings40.SPACE_BEFORE_SYNCHRONIZED_LBRACE = b5;
                commonCodeStyleSettings39.SPACE_BEFORE_FINALLY_LBRACE = b5;
                commonCodeStyleSettings38.SPACE_BEFORE_CATCH_LBRACE = b5;
                commonCodeStyleSettings37.SPACE_BEFORE_TRY_LBRACE = b5;
                commonCodeStyleSettings36.SPACE_BEFORE_SWITCH_LBRACE = b5;
                commonCodeStyleSettings35.SPACE_BEFORE_DO_LBRACE = b5;
                commonCodeStyleSettings34.SPACE_BEFORE_FOR_LBRACE = b5;
                commonCodeStyleSettings33.SPACE_BEFORE_WHILE_LBRACE = b5;
                commonCodeStyleSettings32.SPACE_BEFORE_ELSE_LBRACE = b5;
                commonCodeStyleSettings31.SPACE_BEFORE_IF_LBRACE = b5;
                commonCodeStyleSettings30.SPACE_BEFORE_METHOD_LBRACE = b5;
                commonCodeStyleSettings29.SPACE_BEFORE_CLASS_LBRACE = b5;
                commonSettings.BINARY_OPERATION_WRAP = 1;
                commonSettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE = true;
                final CommonCodeStyleSettings commonCodeStyleSettings41 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings42 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings43 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings44 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings45 = commonSettings;
                final boolean keep_SIMPLE_BLOCKS_IN_ONE_LINE = true;
                commonCodeStyleSettings45.KEEP_MULTIPLE_EXPRESSIONS_IN_ONE_LINE = keep_SIMPLE_BLOCKS_IN_ONE_LINE;
                commonCodeStyleSettings44.KEEP_SIMPLE_CLASSES_IN_ONE_LINE = keep_SIMPLE_BLOCKS_IN_ONE_LINE;
                commonCodeStyleSettings43.KEEP_SIMPLE_LAMBDAS_IN_ONE_LINE = keep_SIMPLE_BLOCKS_IN_ONE_LINE;
                commonCodeStyleSettings42.KEEP_SIMPLE_METHODS_IN_ONE_LINE = keep_SIMPLE_BLOCKS_IN_ONE_LINE;
                commonCodeStyleSettings41.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = keep_SIMPLE_BLOCKS_IN_ONE_LINE;
                commonSettings.FOR_STATEMENT_WRAP = 1;
                commonSettings.FOR_STATEMENT_LPAREN_ON_NEXT_LINE = false;
                commonSettings.FOR_STATEMENT_RPAREN_ON_NEXT_LINE = false;
                commonSettings.ARRAY_INITIALIZER_WRAP = 1;
                commonSettings.ASSIGNMENT_WRAP = 1;
                ocCodeStyleSettings.INDENT_NAMESPACE_MEMBERS = 0;
                ocCodeStyleSettings.INDENT_C_STRUCT_MEMBERS = 2;
                ocCodeStyleSettings.INDENT_CLASS_MEMBERS = 2;
                ocCodeStyleSettings.INDENT_VISIBILITY_KEYWORDS = 1;
                ocCodeStyleSettings.INDENT_INSIDE_CODE_BLOCK = 2;
                ocCodeStyleSettings.INDENT_PREPROCESSOR_DIRECTIVE = 0;
                ocCodeStyleSettings.KEEP_STRUCTURES_IN_ONE_LINE = true;
                ocCodeStyleSettings.KEEP_CASE_EXPRESSIONS_IN_ONE_LINE = true;
                final CommonCodeStyleSettings commonCodeStyleSettings46 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings47 = commonSettings;
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings4 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings5 = ocCodeStyleSettings;
                final boolean b6 = true;
                ocCodeStyleSettings5.BLOCK_BRACE_PLACEMENT = (b6 ? 1 : 0);
                ocCodeStyleSettings4.METHOD_BRACE_PLACEMENT = (b6 ? 1 : 0);
                ocCodeStyleSettings3.FUNCTION_BRACE_PLACEMENT = (b6 ? 1 : 0);
                ocCodeStyleSettings2.NAMESPACE_BRACE_PLACEMENT = (b6 ? 1 : 0);
                commonCodeStyleSettings47.CLASS_BRACE_STYLE = (b6 ? 1 : 0);
                commonCodeStyleSettings46.BRACE_STYLE = (b6 ? 1 : 0);
                ocCodeStyleSettings.FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP = 0;
                ocCodeStyleSettings.FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP = 0;
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
                ocCodeStyleSettings.LAMBDA_CAPTURE_LIST_WRAP = 0;
                ocCodeStyleSettings.LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE = false;
                ocCodeStyleSettings.LAMBDA_CAPTURE_LIST_NEW_LINE_AFTER_LBRACKET = false;
                ocCodeStyleSettings.LAMBDA_CAPTURE_LIST_NEW_LINE_BEFORE_RBRACKET = false;
                ocCodeStyleSettings.LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE_BRACKET = false;
                ocCodeStyleSettings.LAMBDA_CAPTURE_LIST_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.SPACE_WITHIN_LAMBDA_CAPTURE_LIST_BRACKET = false;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_LAMBDA_CAPTURE_LIST_BRACKET = false;
                ocCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_WRAP = 5;
                ocCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE = true;
                final OCCodeStyleSettings ocCodeStyleSettings6 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings7 = ocCodeStyleSettings;
                final int n = 2;
                ocCodeStyleSettings7.TEMPLATE_DECLARATION_FUNCTION_WRAP = n;
                ocCodeStyleSettings6.TEMPLATE_DECLARATION_STRUCT_WRAP = n;
                ocCodeStyleSettings.SPACE_BEFORE_SUPERCLASS_COLON = true;
                ocCodeStyleSettings.SPACE_AFTER_SUPERCLASS_COLON = true;
                ocCodeStyleSettings.SPACE_BEFORE_INIT_LIST_COLON = true;
                ocCodeStyleSettings.SPACE_AFTER_INIT_LIST_COLON = true;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON = 2;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON = 0;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_WRAP = 5;
                ocCodeStyleSettings.SHIFT_OPERATION_WRAP = 1;
                ocCodeStyleSettings.SHIFT_OPERATION_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.SPACE_BEFORE_TEMPLATE_CALL_LT = false;
                ocCodeStyleSettings.SPACE_WITHIN_TEMPLATE_CALL_LTGT = false;
                ocCodeStyleSettings.ALIGN_INIT_LIST_IN_COLUMNS = false;
                ocCodeStyleSettings.ENUM_CONSTANTS_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.SPACE_BEFORE_NAMESPACE_LBRACE = true;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_BRACES = false;
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
                ocCodeStyleSettings.BLANK_LINES_AROUND_NAMESPACE = 0;
                ocCodeStyleSettings.SPACE_BEFORE_PROPERTY_ATTRIBUTES_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_BEFORE_PROTOCOLS_BRACKETS = false;
                ocCodeStyleSettings.SPACE_BEFORE_CHAINED_SEND_MESSAGE = true;
                ocCodeStyleSettings.SPACE_AFTER_VISIBILITY_SIGN_IN_METHOD_DECLARATION = true;
                ocCodeStyleSettings.SPACE_BEFORE_AUTORELEASE_POOL_LBRACE = true;
                ocCodeStyleSettings.KEEP_BLANK_LINES_BEFORE_END = 1;
                if (OCGoogleCodeStyle.$assertionsDisabled) {
                    break Label_0949;
                }
                final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                if (indentOptions2 == null) {
                    break Label_0949;
                }
                break Label_0949;
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
        indentOptions.INDENT_SIZE = 2;
        indentOptions.CONTINUATION_INDENT_SIZE = 4;
        indentOptions.TAB_SIZE = 4;
        indentOptions.KEEP_INDENTS_ON_EMPTY_LINES = false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGoogleCodeStyle.class.desiredAssertionStatus()) {
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
