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

public class OCStroustrupCodeStyle extends OCPredefinedCodeStyleBase
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCStroustrupCodeStyle() {
        super("Stroustrup", "stroustrup");
    }
    
    @Override
    public void apply(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCStroustrupCodeStyle", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final CommonCodeStyleSettings.IndentOptions indentOptions = commonSettings.getIndentOptions();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        Label_0626: {
            try {
                commonSettings.KEEP_LINE_BREAKS = true;
                final CommonCodeStyleSettings commonCodeStyleSettings = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings2 = commonSettings;
                final boolean b = true;
                commonCodeStyleSettings2.KEEP_CONTROL_STATEMENT_IN_ONE_LINE = b;
                commonCodeStyleSettings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = b;
                commonSettings.KEEP_BLANK_LINES_IN_DECLARATIONS = 1;
                commonSettings.KEEP_BLANK_LINES_IN_CODE = 1;
                commonSettings.INDENT_CASE_FROM_SWITCH = false;
                commonSettings.ALIGN_MULTILINE_BINARY_OPERATION = false;
                commonSettings.ALIGN_GROUP_FIELD_DECLARATIONS = false;
                commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
                commonSettings.SPACE_AROUND_EQUALITY_OPERATORS = false;
                commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS = false;
                commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS = false;
                commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS = false;
                commonSettings.SPACE_AROUND_SHIFT_OPERATORS = true;
                commonSettings.SPACE_AROUND_UNARY_OPERATOR = false;
                commonSettings.SPACE_AFTER_COMMA = true;
                commonSettings.SPACE_BEFORE_COMMA = false;
                commonSettings.SPACE_AFTER_SEMICOLON = true;
                commonSettings.SPACE_BEFORE_SEMICOLON = false;
                commonSettings.SPACE_WITHIN_BRACKETS = false;
                commonSettings.SPACE_WITHIN_BRACES = true;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_BRACES = true;
                commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES = false;
                commonSettings.SPACE_BEFORE_METHOD_PARENTHESES = false;
                final CommonCodeStyleSettings commonCodeStyleSettings3 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings4 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings5 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings6 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings7 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings8 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings9 = commonSettings;
                final boolean space_WITHIN_METHOD_PARENTHESES = false;
                commonCodeStyleSettings9.SPACE_WITHIN_SWITCH_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings8.SPACE_WITHIN_CATCH_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings7.SPACE_WITHIN_TRY_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings6.SPACE_WITHIN_FOR_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings5.SPACE_WITHIN_WHILE_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings4.SPACE_WITHIN_IF_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                commonCodeStyleSettings3.SPACE_WITHIN_METHOD_PARENTHESES = space_WITHIN_METHOD_PARENTHESES;
                final CommonCodeStyleSettings commonCodeStyleSettings10 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings11 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings12 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings13 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings14 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings15 = commonSettings;
                final boolean b2 = true;
                commonCodeStyleSettings15.SPACE_BEFORE_SWITCH_PARENTHESES = b2;
                commonCodeStyleSettings14.SPACE_BEFORE_CATCH_PARENTHESES = b2;
                commonCodeStyleSettings13.SPACE_BEFORE_TRY_PARENTHESES = b2;
                commonCodeStyleSettings12.SPACE_BEFORE_FOR_PARENTHESES = b2;
                commonCodeStyleSettings11.SPACE_BEFORE_WHILE_PARENTHESES = b2;
                commonCodeStyleSettings10.SPACE_BEFORE_IF_PARENTHESES = b2;
                final CommonCodeStyleSettings commonCodeStyleSettings16 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings17 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings18 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings19 = commonSettings;
                final boolean b3 = true;
                commonCodeStyleSettings19.FINALLY_ON_NEW_LINE = b3;
                commonCodeStyleSettings18.CATCH_ON_NEW_LINE = b3;
                commonCodeStyleSettings17.WHILE_ON_NEW_LINE = b3;
                commonCodeStyleSettings16.ELSE_ON_NEW_LINE = b3;
                final CommonCodeStyleSettings commonCodeStyleSettings20 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings21 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings22 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings23 = commonSettings;
                final boolean b4 = false;
                commonCodeStyleSettings23.FOR_BRACE_FORCE = (b4 ? 1 : 0);
                commonCodeStyleSettings22.WHILE_BRACE_FORCE = (b4 ? 1 : 0);
                commonCodeStyleSettings21.DOWHILE_BRACE_FORCE = (b4 ? 1 : 0);
                commonCodeStyleSettings20.IF_BRACE_FORCE = (b4 ? 1 : 0);
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
                final CommonCodeStyleSettings commonCodeStyleSettings34 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings35 = commonSettings;
                final boolean b5 = true;
                commonCodeStyleSettings35.SPACE_BEFORE_SYNCHRONIZED_LBRACE = b5;
                commonCodeStyleSettings34.SPACE_BEFORE_FINALLY_LBRACE = b5;
                commonCodeStyleSettings33.SPACE_BEFORE_CATCH_LBRACE = b5;
                commonCodeStyleSettings32.SPACE_BEFORE_TRY_LBRACE = b5;
                commonCodeStyleSettings31.SPACE_BEFORE_SWITCH_LBRACE = b5;
                commonCodeStyleSettings30.SPACE_BEFORE_DO_LBRACE = b5;
                commonCodeStyleSettings29.SPACE_BEFORE_FOR_LBRACE = b5;
                commonCodeStyleSettings28.SPACE_BEFORE_WHILE_LBRACE = b5;
                commonCodeStyleSettings27.SPACE_BEFORE_ELSE_LBRACE = b5;
                commonCodeStyleSettings26.SPACE_BEFORE_IF_LBRACE = b5;
                commonCodeStyleSettings25.SPACE_BEFORE_METHOD_LBRACE = b5;
                commonCodeStyleSettings24.SPACE_BEFORE_CLASS_LBRACE = b5;
                commonSettings.BINARY_OPERATION_WRAP = 1;
                commonSettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE = true;
                commonSettings.KEEP_SIMPLE_METHODS_IN_ONE_LINE = true;
                commonSettings.FOR_STATEMENT_WRAP = 5;
                commonSettings.FOR_STATEMENT_LPAREN_ON_NEXT_LINE = false;
                commonSettings.FOR_STATEMENT_RPAREN_ON_NEXT_LINE = false;
                commonSettings.ASSIGNMENT_WRAP = 0;
                ocCodeStyleSettings.INDENT_CLASS_MEMBERS = 4;
                ocCodeStyleSettings.INDENT_VISIBILITY_KEYWORDS = 0;
                final CommonCodeStyleSettings commonCodeStyleSettings36 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings37 = commonSettings;
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings4 = ocCodeStyleSettings;
                final boolean brace_STYLE = true;
                ocCodeStyleSettings4.BLOCK_BRACE_PLACEMENT = (brace_STYLE ? 1 : 0);
                ocCodeStyleSettings3.METHOD_BRACE_PLACEMENT = (brace_STYLE ? 1 : 0);
                ocCodeStyleSettings2.NAMESPACE_BRACE_PLACEMENT = (brace_STYLE ? 1 : 0);
                commonCodeStyleSettings37.CLASS_BRACE_STYLE = (brace_STYLE ? 1 : 0);
                commonCodeStyleSettings36.BRACE_STYLE = (brace_STYLE ? 1 : 0);
                ocCodeStyleSettings.FUNCTION_BRACE_PLACEMENT = 5;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE_PARS = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_WRAP = 1;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE = false;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_WRAP = 1;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_WRAP = 1;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON = 1;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON = 0;
                ocCodeStyleSettings.SPACE_BEFORE_TEMPLATE_CALL_LT = false;
                ocCodeStyleSettings.SPACE_WITHIN_TEMPLATE_CALL_LTGT = false;
                ocCodeStyleSettings.SPACE_WITHIN_FUNCTION_DECLARATION_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_DECLARATION_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_FUNCTION_CALL_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES = false;
                ocCodeStyleSettings.SPACE_WITHIN_PROTOCOLS_BRACKETS = false;
                ocCodeStyleSettings.SPACE_AFTER_INIT_LIST_COLON = false;
                ocCodeStyleSettings.SPACE_BEFORE_PROTOCOLS_BRACKETS = false;
                ocCodeStyleSettings.SPACE_BEFORE_POINTER_IN_DECLARATION = false;
                ocCodeStyleSettings.SPACE_AFTER_POINTER_IN_DECLARATION = true;
                ocCodeStyleSettings.SPACE_BEFORE_REFERENCE_IN_DECLARATION = false;
                ocCodeStyleSettings.SPACE_AFTER_REFERENCE_IN_DECLARATION = true;
                ocCodeStyleSettings.SPACE_AROUND_PM_OPERATORS = false;
                if (OCStroustrupCodeStyle.$assertionsDisabled) {
                    break Label_0626;
                }
                final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                if (indentOptions2 == null) {
                    break Label_0626;
                }
                break Label_0626;
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
        indentOptions.CONTINUATION_INDENT_SIZE = 8;
        super.apply(codeStyleSettings);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCStroustrupCodeStyle.class.desiredAssertionStatus()) {
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
