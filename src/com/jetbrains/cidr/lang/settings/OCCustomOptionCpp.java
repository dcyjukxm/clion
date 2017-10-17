// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.editor.colors.OCHighlightingKeys;
import com.intellij.openapi.options.OptionsBundle;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.openapi.options.colors.AttributesDescriptor;

public class OCCustomOptionCpp implements OCCustomOption
{
    private static final String WRAPPING_AND_BRACES_OPTIONS = "KEEP_LINE_BREAKS,KEEP_FIRST_COLUMN_COMMENT,KEEP_CONTROL_STATEMENT_IN_ONE_LINE,KEEP_SIMPLE_BLOCKS_IN_ONE_LINE,KEEP_SIMPLE_METHODS_IN_ONE_LINE,CLASS_BRACE_STYLE,BRACE_STYLE,METHOD_CALL_CHAIN_WRAP,ALIGN_MULTILINE_CHAINED_METHODS,ALIGN_GROUP_FIELD_DECLARATIONS,IF_BRACE_FORCE,ELSE_ON_NEW_LINE,SPECIAL_ELSE_IF_TREATMENT,FOR_STATEMENT_WRAP,ALIGN_MULTILINE_FOR,FOR_STATEMENT_LPAREN_ON_NEXT_LINE,FOR_STATEMENT_RPAREN_ON_NEXT_LINE,FOR_BRACE_FORCE,WHILE_BRACE_FORCE,DOWHILE_BRACE_FORCE,WHILE_ON_NEW_LINE,INDENT_CASE_FROM_SWITCH,CATCH_ON_NEW_LINE,BINARY_OPERATION_WRAP,ALIGN_MULTILINE_BINARY_OPERATION,BINARY_OPERATION_SIGN_ON_NEXT_LINE,PARENTHESES_EXPRESSION_LPAREN_WRAP,PARENTHESES_EXPRESSION_RPAREN_WRAP,ASSIGNMENT_WRAP,ALIGN_MULTILINE_ASSIGNMENT,PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE,TERNARY_OPERATION_WRAP,ALIGN_MULTILINE_TERNARY_OPERATION,TERNARY_OPERATION_SIGNS_ON_NEXT_LINE,ARRAY_INITIALIZER_WRAP,ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION,ARRAY_INITIALIZER_LBRACE_ON_NEXT_LINE,ARRAY_INITIALIZER_RBRACE_ON_NEXT_LINE,ENUM_CONSTANTS_WRAP";
    private static final RenameAction[] WRAPPING_AND_BRACES_RA;
    private static final CustomOption[] WRAPPING_AND_BRACES_CO;
    private static final String SPACING_OPTIONS = "SPACE_BEFORE_METHOD_CALL_PARENTHESES,SPACE_BEFORE_METHOD_PARENTHESES,SPACE_BEFORE_IF_PARENTHESES,SPACE_BEFORE_FOR_PARENTHESES,SPACE_BEFORE_WHILE_PARENTHESES,SPACE_BEFORE_SWITCH_PARENTHESES,SPACE_BEFORE_CATCH_PARENTHESES,SPACE_AROUND_ASSIGNMENT_OPERATORS,SPACE_AROUND_LOGICAL_OPERATORS,SPACE_AROUND_EQUALITY_OPERATORS,SPACE_AROUND_RELATIONAL_OPERATORS,SPACE_AROUND_BITWISE_OPERATORS,SPACE_AROUND_ADDITIVE_OPERATORS,SPACE_AROUND_MULTIPLICATIVE_OPERATORS,SPACE_AROUND_SHIFT_OPERATORS,SPACE_AROUND_UNARY_OPERATOR,SPACE_AROUND_LAMBDA_ARROW,SPACE_BEFORE_CLASS_LBRACE,SPACE_BEFORE_METHOD_LBRACE,SPACE_BEFORE_IF_LBRACE,SPACE_BEFORE_ELSE_LBRACE,SPACE_BEFORE_FOR_LBRACE,SPACE_BEFORE_WHILE_LBRACE,SPACE_BEFORE_DO_LBRACE,SPACE_BEFORE_SWITCH_LBRACE,SPACE_BEFORE_TRY_LBRACE,SPACE_BEFORE_CATCH_LBRACE,SPACE_BEFORE_ELSE_KEYWORD,SPACE_BEFORE_WHILE_KEYWORD,SPACE_BEFORE_CATCH_KEYWORD,SPACE_WITHIN_BRACES,SPACE_WITHIN_BRACKETS,SPACE_WITHIN_ARRAY_INITIALIZER_BRACES,SPACE_WITHIN_EMPTY_ARRAY_INITIALIZER_BRACES,SPACE_WITHIN_PARENTHESES,SPACE_WITHIN_IF_PARENTHESES,SPACE_WITHIN_FOR_PARENTHESES,SPACE_WITHIN_WHILE_PARENTHESES,SPACE_WITHIN_SWITCH_PARENTHESES,SPACE_WITHIN_CATCH_PARENTHESES,SPACE_WITHIN_CAST_PARENTHESES,SPACE_AFTER_TYPE_CAST,SPACE_BEFORE_QUEST,SPACE_AFTER_QUEST,SPACE_BEFORE_COLON,SPACE_AFTER_COLON,SPACE_BEFORE_COMMA,SPACE_AFTER_COMMA,SPACE_BEFORE_COLON,SPACE_AFTER_COLON,SPACE_BEFORE_COMMA,SPACE_AFTER_COMMA,SPACE_BEFORE_SEMICOLON,SPACE_AFTER_SEMICOLON,SPACE_BEFORE_TEMPLATE_DECLARATION_LT,SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT,SPACE_WITHIN_EMPTY_TEMPLATE_DECLARATION_LTGT,SPACE_BEFORE_TEMPLATE_CALL_LT,SPACE_WITHIN_TEMPLATE_CALL_LTGT,SPACE_WITHIN_EMPTY_TEMPLATE_CALL_LTGT,SPACE_BEFORE_INIT_LIST_COLON,SPACE_AFTER_INIT_LIST_COLON,SPACE_BEFORE_SUPERCLASS_COLON,SPACE_AFTER_SUPERCLASS_COLON";
    private static final RenameAction[] SPACING_RA;
    private static final CustomOption[] SPACING_CO;
    private static final RenameAction[] BLANK_LINKES_RA;
    private static final CustomOption[] FOLDING_CO;
    private static final CustomOption[] INDENT_CO;
    private static final AttributesDescriptor[] COLOR_ATTRS;
    
    @Override
    public void configureDefaultSettings(@NotNull final CommonCodeStyleSettings commonCodeStyleSettings) {
        try {
            if (commonCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "configureDefaultSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                commonCodeStyleSettings.ALIGN_MULTILINE_BINARY_OPERATION = true;
                commonCodeStyleSettings.ALIGN_MULTILINE_ASSIGNMENT = true;
                commonCodeStyleSettings.ALIGN_MULTILINE_TERNARY_OPERATION = true;
                commonCodeStyleSettings.ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION = true;
                commonCodeStyleSettings.SPACE_WITHIN_BRACES = true;
                commonCodeStyleSettings.BINARY_OPERATION_WRAP = 1;
                commonCodeStyleSettings.TERNARY_OPERATION_WRAP = 1;
                commonCodeStyleSettings.TERNARY_OPERATION_SIGNS_ON_NEXT_LINE = true;
                commonCodeStyleSettings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = true;
                commonCodeStyleSettings.KEEP_SIMPLE_METHODS_IN_ONE_LINE = true;
                commonCodeStyleSettings.ARRAY_INITIALIZER_WRAP = 1;
                commonCodeStyleSettings.ENUM_CONSTANTS_WRAP = 5;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void configureCustomSettings(@NotNull final OCCodeStyleSettings ocCodeStyleSettings) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ocSettings", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "configureCustomSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                ocCodeStyleSettings.FUNCTION_PARAMETERS_WRAP = 1;
                ocCodeStyleSettings.FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP = 1;
                ocCodeStyleSettings.FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP = 1;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_WRAP = 1;
                ocCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE = true;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_WRAP = 1;
                ocCodeStyleSettings.ALIGN_INIT_LIST_IN_COLUMNS = true;
                ocCodeStyleSettings.SPACE_BETWEEN_ADJACENT_BRACKETS = false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @NotNull
    @Override
    public CustomOption[] getIndentCustomOptions() {
        CustomOption[] indent_CO;
        try {
            indent_CO = OCCustomOptionCpp.INDENT_CO;
            if (indent_CO == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getIndentCustomOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return indent_CO;
    }
    
    @NotNull
    @Override
    public CustomOption[] getFoldingCustomOptions() {
        CustomOption[] folding_CO;
        try {
            folding_CO = OCCustomOptionCpp.FOLDING_CO;
            if (folding_CO == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getFoldingCustomOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return folding_CO;
    }
    
    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        AttributesDescriptor[] color_ATTRS;
        try {
            color_ATTRS = OCCustomOptionCpp.COLOR_ATTRS;
            if (color_ATTRS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getAttributeDescriptors"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return color_ATTRS;
    }
    
    @NotNull
    @Override
    public String[] getWrappingAndBracesOptions() {
        String[] split;
        try {
            split = "KEEP_LINE_BREAKS,KEEP_FIRST_COLUMN_COMMENT,KEEP_CONTROL_STATEMENT_IN_ONE_LINE,KEEP_SIMPLE_BLOCKS_IN_ONE_LINE,KEEP_SIMPLE_METHODS_IN_ONE_LINE,CLASS_BRACE_STYLE,BRACE_STYLE,METHOD_CALL_CHAIN_WRAP,ALIGN_MULTILINE_CHAINED_METHODS,ALIGN_GROUP_FIELD_DECLARATIONS,IF_BRACE_FORCE,ELSE_ON_NEW_LINE,SPECIAL_ELSE_IF_TREATMENT,FOR_STATEMENT_WRAP,ALIGN_MULTILINE_FOR,FOR_STATEMENT_LPAREN_ON_NEXT_LINE,FOR_STATEMENT_RPAREN_ON_NEXT_LINE,FOR_BRACE_FORCE,WHILE_BRACE_FORCE,DOWHILE_BRACE_FORCE,WHILE_ON_NEW_LINE,INDENT_CASE_FROM_SWITCH,CATCH_ON_NEW_LINE,BINARY_OPERATION_WRAP,ALIGN_MULTILINE_BINARY_OPERATION,BINARY_OPERATION_SIGN_ON_NEXT_LINE,PARENTHESES_EXPRESSION_LPAREN_WRAP,PARENTHESES_EXPRESSION_RPAREN_WRAP,ASSIGNMENT_WRAP,ALIGN_MULTILINE_ASSIGNMENT,PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE,TERNARY_OPERATION_WRAP,ALIGN_MULTILINE_TERNARY_OPERATION,TERNARY_OPERATION_SIGNS_ON_NEXT_LINE,ARRAY_INITIALIZER_WRAP,ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION,ARRAY_INITIALIZER_LBRACE_ON_NEXT_LINE,ARRAY_INITIALIZER_RBRACE_ON_NEXT_LINE,ENUM_CONSTANTS_WRAP".split(",");
            if (split == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getWrappingAndBracesOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return split;
    }
    
    @NotNull
    @Override
    public RenameAction[] getWrappingAndBracesRenames() {
        RenameAction[] wrapping_AND_BRACES_RA;
        try {
            wrapping_AND_BRACES_RA = OCCustomOptionCpp.WRAPPING_AND_BRACES_RA;
            if (wrapping_AND_BRACES_RA == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getWrappingAndBracesRenames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return wrapping_AND_BRACES_RA;
    }
    
    @NotNull
    @Override
    public CustomOption[] getWrappingAndBracesCustomOptions() {
        CustomOption[] wrapping_AND_BRACES_CO;
        try {
            wrapping_AND_BRACES_CO = OCCustomOptionCpp.WRAPPING_AND_BRACES_CO;
            if (wrapping_AND_BRACES_CO == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getWrappingAndBracesCustomOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return wrapping_AND_BRACES_CO;
    }
    
    @NotNull
    @Override
    public String[] getSpacingOptions() {
        String[] split;
        try {
            split = "SPACE_BEFORE_METHOD_CALL_PARENTHESES,SPACE_BEFORE_METHOD_PARENTHESES,SPACE_BEFORE_IF_PARENTHESES,SPACE_BEFORE_FOR_PARENTHESES,SPACE_BEFORE_WHILE_PARENTHESES,SPACE_BEFORE_SWITCH_PARENTHESES,SPACE_BEFORE_CATCH_PARENTHESES,SPACE_AROUND_ASSIGNMENT_OPERATORS,SPACE_AROUND_LOGICAL_OPERATORS,SPACE_AROUND_EQUALITY_OPERATORS,SPACE_AROUND_RELATIONAL_OPERATORS,SPACE_AROUND_BITWISE_OPERATORS,SPACE_AROUND_ADDITIVE_OPERATORS,SPACE_AROUND_MULTIPLICATIVE_OPERATORS,SPACE_AROUND_SHIFT_OPERATORS,SPACE_AROUND_UNARY_OPERATOR,SPACE_AROUND_LAMBDA_ARROW,SPACE_BEFORE_CLASS_LBRACE,SPACE_BEFORE_METHOD_LBRACE,SPACE_BEFORE_IF_LBRACE,SPACE_BEFORE_ELSE_LBRACE,SPACE_BEFORE_FOR_LBRACE,SPACE_BEFORE_WHILE_LBRACE,SPACE_BEFORE_DO_LBRACE,SPACE_BEFORE_SWITCH_LBRACE,SPACE_BEFORE_TRY_LBRACE,SPACE_BEFORE_CATCH_LBRACE,SPACE_BEFORE_ELSE_KEYWORD,SPACE_BEFORE_WHILE_KEYWORD,SPACE_BEFORE_CATCH_KEYWORD,SPACE_WITHIN_BRACES,SPACE_WITHIN_BRACKETS,SPACE_WITHIN_ARRAY_INITIALIZER_BRACES,SPACE_WITHIN_EMPTY_ARRAY_INITIALIZER_BRACES,SPACE_WITHIN_PARENTHESES,SPACE_WITHIN_IF_PARENTHESES,SPACE_WITHIN_FOR_PARENTHESES,SPACE_WITHIN_WHILE_PARENTHESES,SPACE_WITHIN_SWITCH_PARENTHESES,SPACE_WITHIN_CATCH_PARENTHESES,SPACE_WITHIN_CAST_PARENTHESES,SPACE_AFTER_TYPE_CAST,SPACE_BEFORE_QUEST,SPACE_AFTER_QUEST,SPACE_BEFORE_COLON,SPACE_AFTER_COLON,SPACE_BEFORE_COMMA,SPACE_AFTER_COMMA,SPACE_BEFORE_COLON,SPACE_AFTER_COLON,SPACE_BEFORE_COMMA,SPACE_AFTER_COMMA,SPACE_BEFORE_SEMICOLON,SPACE_AFTER_SEMICOLON,SPACE_BEFORE_TEMPLATE_DECLARATION_LT,SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT,SPACE_WITHIN_EMPTY_TEMPLATE_DECLARATION_LTGT,SPACE_BEFORE_TEMPLATE_CALL_LT,SPACE_WITHIN_TEMPLATE_CALL_LTGT,SPACE_WITHIN_EMPTY_TEMPLATE_CALL_LTGT,SPACE_BEFORE_INIT_LIST_COLON,SPACE_AFTER_INIT_LIST_COLON,SPACE_BEFORE_SUPERCLASS_COLON,SPACE_AFTER_SUPERCLASS_COLON".split(",");
            if (split == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getSpacingOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return split;
    }
    
    @NotNull
    @Override
    public RenameAction[] getSpacingRenames() {
        RenameAction[] spacing_RA;
        try {
            spacing_RA = OCCustomOptionCpp.SPACING_RA;
            if (spacing_RA == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getSpacingRenames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return spacing_RA;
    }
    
    @NotNull
    @Override
    public CustomOption[] getSpacingCustomOptions() {
        CustomOption[] spacing_CO;
        try {
            spacing_CO = OCCustomOptionCpp.SPACING_CO;
            if (spacing_CO == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getSpacingCustomOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return spacing_CO;
    }
    
    @NotNull
    @Override
    public RenameAction[] getBlankLinesRenames() {
        RenameAction[] blank_LINKES_RA;
        try {
            blank_LINKES_RA = OCCustomOptionCpp.BLANK_LINKES_RA;
            if (blank_LINKES_RA == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getBlankLinesRenames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return blank_LINKES_RA;
    }
    
    @NotNull
    @Override
    public String getExampleExtension() {
        String s;
        try {
            s = "cpp";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCustomOptionCpp", "getExampleExtension"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    static {
        WRAPPING_AND_BRACES_RA = new RenameAction[] { new RenameAction("KEEP_SIMPLE_METHODS_IN_ONE_LINE", OCBundle.message("wrapping.keep.simple.methods.in.one.line", new Object[0])), new RenameAction("KEEP_SIMPLE_BLOCKS_IN_ONE_LINE", OCBundle.message("wrapping.keep.simple.blocks.in.one.line", new Object[0])), new RenameAction("CLASS_BRACE_STYLE", OCBundle.message("wrapping.brace.placement.class.declaration", new Object[0])), new RenameAction(CodeStyleSettingsCustomizable.WRAPPING_TRY_STATEMENT, OCBundle.message("wrapping.try.statement", new Object[0])), new RenameAction("CATCH_ON_NEW_LINE", OCBundle.message("wrapping.catch.on.new.line", new Object[0])), new RenameAction("ARRAY_INITIALIZER_WRAP", OCBundle.message("wrapping.array.initializer", new Object[0])), new RenameAction(CodeStyleSettingsCustomizable.WRAPPING_FIELDS_VARIABLES_GROUPS, OCBundle.message("wrapping.align.multiline.fields.groups", new Object[0])), new RenameAction("ALIGN_GROUP_FIELD_DECLARATIONS", OCBundle.message("wrapping.align.in.columns", new Object[0])) };
        WRAPPING_AND_BRACES_CO = new CustomOption[] { new CustomOption("KEEP_STRUCTURES_IN_ONE_LINE", OCBundle.message("wrapping.structures.in.one.line", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_KEEP, new Object[0]), new CustomOption("KEEP_NESTED_NAMESPACES_IN_ONE_LINE", OCBundle.message("wrapping.keep.nested.namespaces.in.one.line", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_KEEP, new Object[0]), new CustomOption("KEEP_DIRECTIVE_AT_FIRST_COLUMN", OCBundle.message("wrapping.keep.directive.at.first.column", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_KEEP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "KEEP_FIRST_COLUMN_COMMENT", new Object[0]), new CustomOption("NAMESPACE_BRACE_PLACEMENT", OCBundle.message("wrapping.before.brace.namespace", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_BRACES, CodeStyleSettingsCustomizable.OptionAnchor.BEFORE, "BRACE_STYLE", new Object[] { CodeStyleSettingsCustomizable.BRACE_PLACEMENT_OPTIONS, CodeStyleSettingsCustomizable.BRACE_PLACEMENT_VALUES }), new CustomOption("SUPERCLASS_LIST_WRAP", OCCustomOptionCpp.SUPERCLASS_LIST_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "BRACE_STYLE", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("SUPERCLASS_LIST_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.SUPERCLASS_LIST_GROUP, new Object[0]), new CustomOption("SUPERCLASS_LIST_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.SUPERCLASS_LIST_GROUP, new Object[0]), new CustomOption("SUPERCLASS_LIST_BEFORE_COLON", OCBundle.message("wrapping.new.line.before.colon", new Object[0]), OCCustomOptionCpp.SUPERCLASS_LIST_GROUP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "SUPERCLASS_LIST_COMMA_ON_NEXT_LINE", new Object[] { OCCodeStyleSettings.LEW_LINE_OPTIONS, OCCodeStyleSettings.LEW_LINE_VALUES }), new CustomOption("SUPERCLASS_LIST_AFTER_COLON", OCBundle.message("wrapping.new.line.after.colon", new Object[0]), OCCustomOptionCpp.SUPERCLASS_LIST_GROUP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "SUPERCLASS_LIST_COMMA_ON_NEXT_LINE", new Object[] { OCCodeStyleSettings.LEW_LINE_OPTIONS, OCCodeStyleSettings.LEW_LINE_VALUES }), new CustomOption("CLASS_CONSTRUCTOR_INIT_LIST_WRAP", OCCustomOptionCpp.CLASS_CONSTRUCTOR_INIT_LIST_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "BRACE_STYLE", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("CLASS_CONSTRUCTOR_INIT_LIST_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.CLASS_CONSTRUCTOR_INIT_LIST_GROUP, new Object[0]), new CustomOption("CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.CLASS_CONSTRUCTOR_INIT_LIST_GROUP, new Object[0]), new CustomOption("CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON", OCBundle.message("wrapping.new.line.before.colon", new Object[0]), OCCustomOptionCpp.CLASS_CONSTRUCTOR_INIT_LIST_GROUP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE", new Object[] { OCCodeStyleSettings.LEW_LINE_OPTIONS, OCCodeStyleSettings.LEW_LINE_VALUES }), new CustomOption("CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON", OCBundle.message("wrapping.new.line.after.colon", new Object[0]), OCCustomOptionCpp.CLASS_CONSTRUCTOR_INIT_LIST_GROUP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE", new Object[] { OCCodeStyleSettings.LEW_LINE_OPTIONS, OCCodeStyleSettings.LEW_LINE_VALUES }), new CustomOption("FUNCTION_BRACE_PLACEMENT", OCBundle.message("wrapping.function.brace.placement", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_BRACES, CodeStyleSettingsCustomizable.OptionAnchor.BEFORE, "BRACE_STYLE", new Object[] { CodeStyleSettingsCustomizable.BRACE_PLACEMENT_OPTIONS, CodeStyleSettingsCustomizable.BRACE_PLACEMENT_VALUES }), new CustomOption("BLOCK_BRACE_PLACEMENT", OCBundle.message("wrapping.block.brace.placement", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_BRACES, CodeStyleSettingsCustomizable.OptionAnchor.BEFORE, "BRACE_STYLE", new Object[] { CodeStyleSettingsCustomizable.BRACE_PLACEMENT_OPTIONS, CodeStyleSettingsCustomizable.BRACE_PLACEMENT_VALUES }), new CustomOption("FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP", OCCustomOptionCpp.FUNCTION_TOP_AFTER_RETURN_TYPE, OCCustomOptionCpp.FUNCTION_RETURN_TYPE_GROUP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS_FOR_SINGLETON, CodeStyleSettingsCustomizable.WRAP_VALUES_FOR_SINGLETON }), new CustomOption("FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP", OCCustomOptionCpp.FUNCTION_NON_TOP_AFTER_RETURN_TYPE, OCCustomOptionCpp.FUNCTION_RETURN_TYPE_GROUP, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS_FOR_SINGLETON, CodeStyleSettingsCustomizable.WRAP_VALUES_FOR_SINGLETON }), new CustomOption("FUNCTION_PARAMETERS_WRAP", OCCustomOptionCpp.FUNCTION_PARAMETERS_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("FUNCTION_PARAMETERS_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.FUNCTION_PARAMETERS_GROUP, new Object[0]), new CustomOption("FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.FUNCTION_PARAMETERS_GROUP, new Object[0]), new CustomOption("FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR", ApplicationBundle.message("wrapping.new.line.after.lpar", new Object[0]), OCCustomOptionCpp.FUNCTION_PARAMETERS_GROUP, new Object[0]), new CustomOption("FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR", ApplicationBundle.message("wrapping.rpar.on.new.line", new Object[0]), OCCustomOptionCpp.FUNCTION_PARAMETERS_GROUP, new Object[0]), new CustomOption("FUNCTION_PARAMETERS_ALIGN_MULTILINE_PARS", OCBundle.message("wrapping.align.multiline.parentheses", new Object[0]), OCCustomOptionCpp.FUNCTION_PARAMETERS_GROUP, new Object[0]), new CustomOption("FUNCTION_CALL_ARGUMENTS_WRAP", OCCustomOptionCpp.FUNCTION_CALL_ARGUMENTS_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.FUNCTION_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.FUNCTION_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR", ApplicationBundle.message("wrapping.new.line.after.lpar", new Object[0]), OCCustomOptionCpp.FUNCTION_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR", ApplicationBundle.message("wrapping.rpar.on.new.line", new Object[0]), OCCustomOptionCpp.FUNCTION_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS", OCBundle.message("wrapping.align.multiline.parentheses", new Object[0]), OCCustomOptionCpp.FUNCTION_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("LAMBDA_CAPTURE_LIST_WRAP", OCCustomOptionCpp.LAMBDA_CAPTURE_LIST_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.LAMBDA_CAPTURE_LIST_GROUP, new Object[0]), new CustomOption("LAMBDA_CAPTURE_LIST_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.LAMBDA_CAPTURE_LIST_GROUP, new Object[0]), new CustomOption("LAMBDA_CAPTURE_LIST_NEW_LINE_AFTER_LBRACKET", ApplicationBundle.message("wrapping.new.line.after.lbracket", new Object[0]), OCCustomOptionCpp.LAMBDA_CAPTURE_LIST_GROUP, new Object[0]), new CustomOption("LAMBDA_CAPTURE_LIST_NEW_LINE_BEFORE_RBRACKET", ApplicationBundle.message("wrapping.rbracket.on.new.line", new Object[0]), OCCustomOptionCpp.LAMBDA_CAPTURE_LIST_GROUP, new Object[0]), new CustomOption("LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE_BRACKET", OCBundle.message("wrapping.align.multiline.brackets", new Object[0]), OCCustomOptionCpp.LAMBDA_CAPTURE_LIST_GROUP, new Object[0]), new CustomOption("TEMPLATE_DECLARATION_STRUCT_WRAP", OCCustomOptionCpp.TEMPLATE_DECLARATION_STRUCT_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("TEMPLATE_DECLARATION_STRUCT_BODY_INDENT", OCBundle.message("wrapping.indent.template.body.if.wrapped", new Object[0]), OCCustomOptionCpp.TEMPLATE_DECLARATION_STRUCT_GROUP, new Object[0]), new CustomOption("TEMPLATE_DECLARATION_FUNCTION_WRAP", OCCustomOptionCpp.TEMPLATE_DECLARATION_FUNCTION_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("TEMPLATE_DECLARATION_FUNCTION_BODY_INDENT", OCBundle.message("wrapping.indent.template.body.if.wrapped", new Object[0]), OCCustomOptionCpp.TEMPLATE_DECLARATION_FUNCTION_GROUP, new Object[0]), new CustomOption("TEMPLATE_PARAMETERS_WRAP", OCCustomOptionCpp.TEMPLATE_PARAMETERS_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("TEMPLATE_PARAMETERS_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.TEMPLATE_PARAMETERS_GROUP, new Object[0]), new CustomOption("TEMPLATE_PARAMETERS_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.TEMPLATE_PARAMETERS_GROUP, new Object[0]), new CustomOption("TEMPLATE_PARAMETERS_NEW_LINE_AFTER_LT", OCBundle.message("wrapping.new.line.after.lt", new Object[0]), OCCustomOptionCpp.TEMPLATE_PARAMETERS_GROUP, new Object[0]), new CustomOption("TEMPLATE_PARAMETERS_NEW_LINE_BEFORE_GT", OCBundle.message("wrapping.gt.on.new.line", new Object[0]), OCCustomOptionCpp.TEMPLATE_PARAMETERS_GROUP, new Object[0]), new CustomOption("TEMPLATE_PARAMETERS_ALIGN_MULTILINE_PARS", OCBundle.message("wrapping.align.multiline.lt.gt", new Object[0]), OCCustomOptionCpp.TEMPLATE_PARAMETERS_GROUP, new Object[0]), new CustomOption("TEMPLATE_CALL_ARGUMENTS_WRAP", OCCustomOptionCpp.TEMPLATE_CALL_ARGUMENTS_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "METHOD_CALL_CHAIN_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.TEMPLATE_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("TEMPLATE_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), OCCustomOptionCpp.TEMPLATE_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("TEMPLATE_CALL_ARGUMENTS_NEW_LINE_AFTER_LT", OCBundle.message("wrapping.new.line.after.lt", new Object[0]), OCCustomOptionCpp.TEMPLATE_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("TEMPLATE_CALL_ARGUMENTS_NEW_LINE_BEFORE_GT", OCBundle.message("wrapping.gt.on.new.line", new Object[0]), OCCustomOptionCpp.TEMPLATE_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS", OCBundle.message("wrapping.align.multiline.lt.gt", new Object[0]), OCCustomOptionCpp.TEMPLATE_CALL_ARGUMENTS_GROUP, new Object[0]), new CustomOption("KEEP_CASE_EXPRESSIONS_IN_ONE_LINE", OCBundle.message("wrapping.keep.simple.case.inline", new Object[0]), ApplicationBundle.message("wrapping.switch.statement", new Object[0]), new Object[0]), new CustomOption("SHIFT_OPERATION_WRAP", OCCustomOptionCpp.SHIFT_OPERATORS_GROUP, null, CodeStyleSettingsCustomizable.OptionAnchor.BEFORE, "BINARY_OPERATION_WRAP", new Object[] { CodeStyleSettingsCustomizable.WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES }), new CustomOption("SHIFT_OPERATION_ALIGN_MULTILINE", ApplicationBundle.message("wrapping.align.when.multiline", new Object[0]), OCCustomOptionCpp.SHIFT_OPERATORS_GROUP, new Object[0]), new CustomOption("IN_LINE_SHORT_TERNARY_OPERATOR", OCBundle.message("wrapping.ternary.short.inline", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_TERNARY_OPERATION, new Object[0]), new CustomOption("ARRAY_INITIALIZER_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_ARRAY_INITIALIZER, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION", new Object[0]), new CustomOption("ALIGN_INIT_LIST_IN_COLUMNS", OCBundle.message("align.init.list.values", new Object[0]), CodeStyleSettingsCustomizable.WRAPPING_ARRAY_INITIALIZER, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION", new Object[0]), new CustomOption("ENUM_CONSTANTS_COMMA_ON_NEXT_LINE", ApplicationBundle.message("wrapping.comma.on.next.line", new Object[0]), ApplicationBundle.message("wrapping.enum.constants", new Object[0]), new Object[0]) };
        SPACING_RA = new RenameAction[] { new RenameAction("SPACE_BEFORE_METHOD_CALL_PARENTHESES", OCBundle.message("space.before.function.call.parentheses", new Object[0])), new RenameAction("SPACE_BEFORE_METHOD_PARENTHESES", OCBundle.message("space.before.function.parentheses", new Object[0])), new RenameAction("SPACE_AROUND_SHIFT_OPERATORS", OCBundle.message("space.around.shift.operators", new Object[0])), new RenameAction("SPACE_AROUND_LAMBDA_ARROW", OCBundle.message("space.around.lambda.arrow", new Object[0])), new RenameAction("SPACE_BEFORE_CLASS_LBRACE", OCBundle.message("space.before.class.lbrace", new Object[0])), new RenameAction("SPACE_BEFORE_METHOD_LBRACE", OCBundle.message("space.before.method.lbrace", new Object[0])), new RenameAction("SPACE_WITHIN_BRACKETS", OCBundle.message("space.within.bracket", new Object[0])), new RenameAction("SPACE_WITHIN_ARRAY_INITIALIZER_BRACES", OCBundle.message("space.within.array.initializer.braces", new Object[0])), new RenameAction("SPACE_WITHIN_EMPTY_ARRAY_INITIALIZER_BRACES", OCBundle.message("space.within.array.empty.initializer.braces", new Object[0])), new RenameAction("SPACE_WITHIN_METHOD_PARENTHESES", OCBundle.message("space.within.function.declaration.parentheses", new Object[0])), new RenameAction(CodeStyleSettingsCustomizable.SPACES_IN_TYPE_ARGUMENTS, OCBundle.message("space.within.template.declaration", new Object[0])), new RenameAction(CodeStyleSettingsCustomizable.SPACES_IN_TYPE_PARAMETERS, OCBundle.message("space.within.template.inst", new Object[0])), new RenameAction(CodeStyleSettingsCustomizable.SPACES_WITHIN_TYPE_ARGUMENTS, OCBundle.message("space.within.class", new Object[0])) };
        SPACING_CO = new CustomOption[] { new CustomOption("SPACE_WITHIN_LAMBDA_CAPTURE_LIST_BRACKET", OCBundle.message("space.within.lambda.capture.list.brackets", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "SPACE_WITHIN_BRACKETS", new Object[0]), new CustomOption("SPACE_WITHIN_EMPTY_LAMBDA_CAPTURE_LIST_BRACKET", OCBundle.message("space.within.empty.lambda.capture.list.brackets", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "SPACE_WITHIN_BRACKETS", new Object[0]), new CustomOption("SPACE_BEFORE_NAMESPACE_LBRACE", OCBundle.message("space.before.namespace.lbrace", new Object[0]), CodeStyleSettingsCustomizable.SPACES_BEFORE_LEFT_BRACE, CodeStyleSettingsCustomizable.OptionAnchor.BEFORE, "SPACE_BEFORE_CLASS_LBRACE", new Object[0]), new CustomOption("SPACE_BEFORE_INIT_LIST", OCBundle.message("space.before.init.list.lbrace", new Object[0]), CodeStyleSettingsCustomizable.SPACES_BEFORE_LEFT_BRACE, CodeStyleSettingsCustomizable.OptionAnchor.BEFORE, "SPACE_BEFORE_CLASS_LBRACE", new Object[0]), new CustomOption("SPACE_WITHIN_EMPTY_BRACES", OCBundle.message("space.within.empty.code.blocks", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, CodeStyleSettingsCustomizable.OptionAnchor.AFTER, "SPACE_WITHIN_BRACES", new Object[0]), new CustomOption("SPACE_WITHIN_FUNCTION_DECLARATION_PARENTHESES", OCBundle.message("space.within.function.declaration.parentheses", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]), new CustomOption("SPACE_WITHIN_EMPTY_FUNCTION_DECLARATION_PARENTHESES", OCBundle.message("space.within.empty.function.declaration.parentheses", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]), new CustomOption("SPACE_WITHIN_FUNCTION_CALL_PARENTHESES", OCBundle.message("space.within.function.call.parentheses", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]), new CustomOption("SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES", OCBundle.message("space.within.empty.function.call.parentheses", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN, new Object[0]), new CustomOption("SPACE_BEFORE_TEMPLATE_DECLARATION_LT", OCBundle.message("space.before.lt.template", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT", OCBundle.message("space.within.template", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_WITHIN_EMPTY_TEMPLATE_DECLARATION_LTGT", OCBundle.message("space.within.empty.diamond", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_BEFORE_TEMPLATE_CALL_LT", OCBundle.message("space.before.lt.template", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TYPE_PARAMETERS, new Object[0]), new CustomOption("SPACE_WITHIN_TEMPLATE_CALL_LTGT", OCBundle.message("space.within.template", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TYPE_PARAMETERS, new Object[0]), new CustomOption("SPACE_WITHIN_EMPTY_TEMPLATE_CALL_LTGT", OCBundle.message("space.within.empty.diamond", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TYPE_PARAMETERS, new Object[0]), new CustomOption("SPACE_WITHIN_TEMPLATE_DOUBLE_GT", OCBundle.message("space.within.template.double.gt", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_BEFORE_SUPERCLASS_COLON", OCBundle.message("space.before.superclass.colon", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_AFTER_SUPERCLASS_COLON", OCBundle.message("space.after.superclass.colon", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_BEFORE_INIT_LIST_COLON", OCBundle.message("space.before.initialization.lists.colon", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_AFTER_INIT_LIST_COLON", OCBundle.message("space.after.initialization.lists.colon", new Object[0]), CodeStyleSettingsCustomizable.SPACES_WITHIN_TYPE_ARGUMENTS, new Object[0]), new CustomOption("SPACE_AFTER_STRUCTURES_RBRACE", OCBundle.message("space.after.structures.rbrace", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_BEFORE_POINTER_IN_DECLARATION", OCBundle.message("space.before.pointer.in.declaration", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_AFTER_POINTER_IN_DECLARATION", OCBundle.message("space.after.pointer.in.declaration", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_BEFORE_REFERENCE_IN_DECLARATION", OCBundle.message("space.before.reference.in.declaration", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_AFTER_REFERENCE_IN_DECLARATION", OCBundle.message("space.after.reference.in.declaration", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_AFTER_REFERENCE_IN_RVALUE", OCBundle.message("space.after.reference.in.rvalue", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_BETWEEN_ADJACENT_BRACKETS", OCBundle.message("space.between.adjacent.brackets", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR", OCBundle.message("space.between.operator.punctuator", new Object[0]), CodeStyleSettingsCustomizable.SPACES_OTHER, new Object[0]), new CustomOption("DISCHARGED_SHORT_TERNARY_OPERATOR", OCBundle.message("space.discharged.short.ternary.operator", new Object[0]), CodeStyleSettingsCustomizable.SPACES_IN_TERNARY_OPERATOR, new Object[0]), new CustomOption("SPACE_AROUND_PM_OPERATORS", OCBundle.message("space.around.pm.operators", new Object[0]), CodeStyleSettingsCustomizable.SPACES_AROUND_OPERATORS, new Object[0]) };
        BLANK_LINKES_RA = new RenameAction[] { new RenameAction("BLANK_LINES_BEFORE_IMPORTS", OCBundle.message("blank.lines.before.includes", new Object[0])), new RenameAction("BLANK_LINES_AFTER_IMPORTS", OCBundle.message("blank.lines.after.includes", new Object[0])), new RenameAction("BLANK_LINES_AROUND_CLASS", OCBundle.message("blank.lines.around.classes", new Object[0])), new RenameAction("BLANK_LINES_AFTER_CLASS_HEADER", OCBundle.message("blank.lines.after.class.header", new Object[0])), new RenameAction("BLANK_LINES_AROUND_FIELD", OCBundle.message("blank.lines.around.global.variable", new Object[0])), new RenameAction("BLANK_LINES_AROUND_FIELD_IN_INTERFACE", OCBundle.message("blank.lines.around.member.variable", new Object[0])), new RenameAction("BLANK_LINES_AROUND_METHOD", OCBundle.message("blank.lines.around.function.definition", new Object[0])), new RenameAction("BLANK_LINES_AROUND_METHOD_IN_INTERFACE", OCBundle.message("blank.lines.around.function.declaration", new Object[0])), new RenameAction("BLANK_LINES_BEFORE_METHOD_BODY", OCBundle.message("blank.lines.before.function.body", new Object[0])) };
        FOLDING_CO = new CustomOption[] { new CustomOption("COLLAPSE_MULTILINE_COMMENTS", OCBundle.message("collapse.multiline.comments", new Object[0])), new CustomOption("COLLAPSE_BLOCK_EXPRESSIONS", OCBundle.message("collapse.block.expressions", new Object[0])), new CustomOption("COLLAPSE_TEMPLATE_PARAM_LIST", OCBundle.message("collapse.template.param.list", new Object[0])), new CustomOption("COLLAPSE_CONDITIONALLY_NOT_COMPILED", OCBundle.message("collapse.conditionally.non-compiled", new Object[0])) };
        INDENT_CO = new CustomOption[] { new CustomOption("INDENT_INSIDE_CODE_BLOCK", OCBundle.message("indent.block", new Object[0])), new CustomOption("INDENT_C_STRUCT_MEMBERS", OCBundle.message("indent.c.struct", new Object[0])), new CustomOption("INDENT_CLASS_MEMBERS", OCBundle.message("indent.class", new Object[0])), new CustomOption("INDENT_VISIBILITY_KEYWORDS", OCBundle.message("indent.visibility.keywords", new Object[0])), new CustomOption("INDENT_NAMESPACE_MEMBERS", OCBundle.message("indent.namespace", new Object[0])), new CustomOption("INDENT_PREPROCESSOR_DIRECTIVE", OCBundle.message("indent.preprocessor.directive", new Object[0])), new CustomOption("INDENT_DIRECTIVE_AS_CODE", OCBundle.message("indent.preprocessor.directive.as.code", new Object[0])) };
        COLOR_ATTRS = new AttributesDescriptor[] { new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.keyword", new Object[0]), OCHighlightingKeys.OC_KEYWORD), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.number", new Object[0]), OCHighlightingKeys.OC_NUMBER), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.string", new Object[0]), OCHighlightingKeys.OC_STRING), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.valid.escape.in.string", new Object[0]), OCHighlightingKeys.OC_VALID_STRING_ESCAPE), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.invalid.escape.in.string", new Object[0]), OCHighlightingKeys.OC_INVALID_STRING_ESCAPE), new AttributesDescriptor(OCBundle.message("color.format.string.token", new Object[0]), OCHighlightingKeys.OC_FORMAT_STRING_TOKEN), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.operator.sign", new Object[0]), OCHighlightingKeys.OC_OPERATION_SIGN), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.parentheses", new Object[0]), OCHighlightingKeys.OC_PARENTHS), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.braces", new Object[0]), OCHighlightingKeys.OC_BRACES), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.brackets", new Object[0]), OCHighlightingKeys.OC_BRACKETS), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.comma", new Object[0]), OCHighlightingKeys.OC_COMMA), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.semicolon", new Object[0]), OCHighlightingKeys.OC_SEMICOLON), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.dot", new Object[0]), OCHighlightingKeys.OC_DOT), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.bad.character", new Object[0]), OCHighlightingKeys.OC_BAD_CHARACTER), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.line.comment", new Object[0]), OCHighlightingKeys.OC_LINE_COMMENT), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.block.comment", new Object[0]), OCHighlightingKeys.OC_BLOCK_COMMENT), new AttributesDescriptor(OCBundle.message("color.namespace", new Object[0]), OCHighlightingKeys.NAMESPACE_LIKE), new AttributesDescriptor(OCBundle.message("color.struct.union", new Object[0]), OCHighlightingKeys.STRUCT_LIKE), new AttributesDescriptor(OCBundle.message("color.struct.field", new Object[0]), OCHighlightingKeys.STRUCT_FIELD), new AttributesDescriptor(OCBundle.message("color.enum.const", new Object[0]), OCHighlightingKeys.ENUM_CONST), new AttributesDescriptor(OCBundle.message("color.typedef", new Object[0]), OCHighlightingKeys.TYPEDEF), new AttributesDescriptor(OCBundle.message("color.this.keywords", new Object[0]), OCHighlightingKeys.SELFSUPERTHIS), new AttributesDescriptor(OCBundle.message("color.function", new Object[0]), OCHighlightingKeys.FUNCTION), new AttributesDescriptor(OCBundle.message("color.overloaded.operator", new Object[0]), OCHighlightingKeys.OVERLOADED_OPERATOR), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.parameter", new Object[0]), OCHighlightingKeys.PARAMETER), new AttributesDescriptor(OCBundle.message("color.global.variable", new Object[0]), OCHighlightingKeys.GLOBAL_VARIABLE), new AttributesDescriptor(OCBundle.message("color.extern.variable", new Object[0]), OCHighlightingKeys.EXTERN_VARIABLE), new AttributesDescriptor(OptionsBundle.message("options.java.attribute.descriptor.local.variable", new Object[0]), OCHighlightingKeys.LOCAL_VARIABLE), new AttributesDescriptor(OCBundle.message("color.template.type", new Object[0]), OCHighlightingKeys.TEMPLATE_TYPE), new AttributesDescriptor(OCBundle.message("color.template.value", new Object[0]), OCHighlightingKeys.TEMPLATE_VALUE), new AttributesDescriptor(OCBundle.message("color.label", new Object[0]), OCHighlightingKeys.LABEL), new AttributesDescriptor(OCBundle.message("color.preprocessor.directive", new Object[0]), OCHighlightingKeys.OC_DIRECTIVE), new AttributesDescriptor(OCBundle.message("color.preprocessor.path", new Object[0]), OCHighlightingKeys.OC_HEADER_PATH), new AttributesDescriptor(OCBundle.message("color.macro.name", new Object[0]), OCHighlightingKeys.MACRONAME), new AttributesDescriptor(OCBundle.message("color.macro.parameter", new Object[0]), OCHighlightingKeys.MACRO_PARAMETER), new AttributesDescriptor(OCBundle.message("color.conditionally.non-compiled", new Object[0]), OCHighlightingKeys.CONDITIONALLY_NOT_COMPILED) };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
