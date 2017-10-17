// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import java.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.OCBundle;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.DifferenceFilter;
import com.intellij.openapi.util.WriteExternalException;
import java.lang.reflect.Field;
import java.util.Iterator;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.InvalidDataException;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.util.PlatformUtils;
import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.util.containers.ImmutableList;
import com.jetbrains.cidr.lang.util.OCDeclarationKind;
import java.util.List;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class OCCodeStyleSettings extends CustomCodeStyleSettings
{
    private static final Logger LOG;
    public static final int NEW_LINE_NEVER = 0;
    public static final int NEW_LINE_ALWAYS = 1;
    public static final int NEW_LINE_IF_LONG = 2;
    public static final String[] LEW_LINE_OPTIONS;
    public static final int[] LEW_LINE_VALUES;
    public boolean DO_NOT_ADD_BREAKS;
    public int INDENT_NAMESPACE_MEMBERS;
    public int INDENT_C_STRUCT_MEMBERS;
    public int INDENT_CLASS_MEMBERS;
    public int INDENT_INTERFACE_MEMBERS;
    public boolean INDENT_BLOCK_COMMENT;
    public boolean INDENT_INTERFACE_MEMBERS_EXCEPT_IVARS_BLOCK;
    public int INDENT_IMPLEMENTATION_MEMBERS;
    public int INDENT_VISIBILITY_KEYWORDS;
    public int INDENT_INSIDE_CODE_BLOCK;
    public int INDENT_PREPROCESSOR_DIRECTIVE;
    public boolean INDENT_DIRECTIVE_AS_CODE;
    public boolean KEEP_STRUCTURES_IN_ONE_LINE;
    public boolean KEEP_CASE_EXPRESSIONS_IN_ONE_LINE;
    public boolean KEEP_NESTED_NAMESPACES_IN_ONE_LINE;
    public boolean KEEP_DIRECTIVE_AT_FIRST_COLUMN;
    @CommonCodeStyleSettings.BraceStyleConstant
    public int NAMESPACE_BRACE_PLACEMENT;
    @CommonCodeStyleSettings.BraceStyleConstant
    public int METHOD_BRACE_PLACEMENT;
    @CommonCodeStyleSettings.BraceStyleConstant
    public int FUNCTION_BRACE_PLACEMENT;
    @CommonCodeStyleSettings.BraceStyleConstant
    public int BLOCK_BRACE_PLACEMENT;
    public int METHOD_PARAMETERS_WRAP;
    public boolean METHOD_PARAMETERS_ALIGN_MULTILINE;
    public boolean METHOD_PARAMETERS_ALIGN_BY_COLONS;
    public int METHOD_CALL_ARGUMENTS_WRAP;
    public boolean METHOD_CALL_ARGUMENTS_ALIGN_MULTILINE;
    public boolean METHOD_CALL_ARGUMENTS_ALIGN_BY_COLONS;
    public boolean METHOD_CALL_ARGUMENTS_SPECIAL_DICTIONARY_PAIRS_TREATMENT;
    public int FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP;
    public int FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP;
    public int FUNCTION_PARAMETERS_WRAP;
    public boolean FUNCTION_PARAMETERS_ALIGN_MULTILINE;
    public boolean FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR;
    public boolean FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR;
    public boolean FUNCTION_PARAMETERS_ALIGN_MULTILINE_PARS;
    public boolean FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE;
    public int LAMBDA_CAPTURE_LIST_WRAP;
    public boolean LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE;
    public boolean LAMBDA_CAPTURE_LIST_NEW_LINE_AFTER_LBRACKET;
    public boolean LAMBDA_CAPTURE_LIST_NEW_LINE_BEFORE_RBRACKET;
    public boolean LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE_BRACKET;
    public boolean LAMBDA_CAPTURE_LIST_COMMA_ON_NEXT_LINE;
    public int FUNCTION_CALL_ARGUMENTS_WRAP;
    public boolean FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE;
    public boolean FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR;
    public boolean FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR;
    public boolean FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS;
    public boolean FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE;
    public int SHIFT_OPERATION_WRAP;
    public boolean SHIFT_OPERATION_ALIGN_MULTILINE;
    public int TEMPLATE_DECLARATION_STRUCT_WRAP;
    public boolean TEMPLATE_DECLARATION_STRUCT_BODY_INDENT;
    public int TEMPLATE_DECLARATION_FUNCTION_WRAP;
    public boolean TEMPLATE_DECLARATION_FUNCTION_BODY_INDENT;
    public int TEMPLATE_PARAMETERS_WRAP;
    public boolean TEMPLATE_PARAMETERS_ALIGN_MULTILINE;
    public boolean TEMPLATE_PARAMETERS_NEW_LINE_AFTER_LT;
    public boolean TEMPLATE_PARAMETERS_NEW_LINE_BEFORE_GT;
    public boolean TEMPLATE_PARAMETERS_ALIGN_MULTILINE_PARS;
    public boolean TEMPLATE_PARAMETERS_COMMA_ON_NEXT_LINE;
    public int TEMPLATE_CALL_ARGUMENTS_WRAP;
    public boolean TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE;
    public boolean TEMPLATE_CALL_ARGUMENTS_NEW_LINE_AFTER_LT;
    public boolean TEMPLATE_CALL_ARGUMENTS_NEW_LINE_BEFORE_GT;
    public boolean TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS;
    public boolean TEMPLATE_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE;
    public int CLASS_CONSTRUCTOR_INIT_LIST_WRAP;
    public boolean CLASS_CONSTRUCTOR_INIT_LIST_ALIGN_MULTILINE;
    public boolean CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE;
    @NewLineConstant
    public int CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON;
    @NewLineConstant
    public int CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON;
    public int SUPERCLASS_LIST_WRAP;
    public boolean SUPERCLASS_LIST_ALIGN_MULTILINE;
    public boolean SUPERCLASS_LIST_COMMA_ON_NEXT_LINE;
    @NewLineConstant
    public int SUPERCLASS_LIST_BEFORE_COLON;
    @NewLineConstant
    public int SUPERCLASS_LIST_AFTER_COLON;
    public boolean SPACE_BEFORE_TEMPLATE_DECLARATION_LT;
    public boolean SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT;
    public boolean SPACE_WITHIN_EMPTY_TEMPLATE_DECLARATION_LTGT;
    public boolean SPACE_BEFORE_TEMPLATE_CALL_LT;
    public boolean SPACE_WITHIN_TEMPLATE_CALL_LTGT;
    public boolean SPACE_WITHIN_EMPTY_TEMPLATE_CALL_LTGT;
    public boolean SPACE_WITHIN_TEMPLATE_DOUBLE_GT;
    public int WRAP_PROPERTY_DECLARATION;
    public boolean ARRAY_INITIALIZER_COMMA_ON_NEXT_LINE;
    public boolean ALIGN_DICTIONARY_PAIR_VALUES;
    public boolean ALIGN_INIT_LIST_IN_COLUMNS;
    public boolean ENUM_CONSTANTS_COMMA_ON_NEXT_LINE;
    public boolean SPACE_WITHIN_EMPTY_BRACES;
    public boolean SPACE_BEFORE_NAMESPACE_LBRACE;
    public boolean SPACE_BEFORE_AUTORELEASE_POOL_LBRACE;
    public boolean SPACE_AFTER_STRUCTURES_RBRACE;
    public boolean SPACE_WITHIN_METHOD_RETURN_TYPE_PARENTHESES;
    public boolean SPACE_AFTER_METHOD_RETURN_TYPE_PARENTHESES;
    public boolean SPACE_WITHIN_METHOD_PARAMETER_TYPE_PARENTHESES;
    public boolean SPACE_AFTER_METHOD_PARAMETER_TYPE_PARENTHESES;
    public boolean SPACE_BEFORE_PROPERTY_ATTRIBUTES_PARENTHESES;
    public boolean SPACE_WITHIN_PROPERTY_ATTRIBUTES_PARENTHESES;
    public boolean SPACE_WITHIN_FUNCTION_DECLARATION_PARENTHESES;
    public boolean SPACE_WITHIN_EMPTY_FUNCTION_DECLARATION_PARENTHESES;
    public boolean SPACE_WITHIN_FUNCTION_CALL_PARENTHESES;
    public boolean SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES;
    public boolean SPACE_WITHIN_CATEGORY_PARENTHESES;
    public boolean SPACE_WITHIN_PROTOCOLS_BRACKETS;
    public boolean SPACE_WITHIN_SEND_MESSAGE_BRACKETS;
    public boolean SPACE_WITHIN_LAMBDA_CAPTURE_LIST_BRACKET;
    public boolean SPACE_WITHIN_EMPTY_LAMBDA_CAPTURE_LIST_BRACKET;
    public boolean SPACE_BEFORE_SUPERCLASS_COLON;
    public boolean SPACE_AFTER_SUPERCLASS_COLON;
    public boolean SPACE_BEFORE_INIT_LIST_COLON;
    public boolean SPACE_AFTER_INIT_LIST_COLON;
    public boolean SPACE_BEFORE_INIT_LIST;
    public boolean SPACE_BEFORE_CATEGORY_PARENTHESES;
    public boolean SPACE_BEFORE_PROTOCOLS_BRACKETS;
    public boolean SPACE_AFTER_VISIBILITY_SIGN_IN_METHOD_DECLARATION;
    public boolean SPACE_AFTER_COLON_IN_SELECTOR;
    public boolean SPACE_BEFORE_CHAINED_SEND_MESSAGE;
    public boolean SPACE_BEFORE_POINTER_IN_DECLARATION;
    public boolean SPACE_AFTER_POINTER_IN_DECLARATION;
    public boolean SPACE_BEFORE_REFERENCE_IN_DECLARATION;
    public boolean SPACE_AFTER_REFERENCE_IN_DECLARATION;
    public boolean SPACE_AFTER_REFERENCE_IN_RVALUE;
    public boolean SPACE_AFTER_CUP_IN_BLOCKS;
    public boolean SPACE_BETWEEN_ADJACENT_BRACKETS;
    public boolean SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR;
    @Deprecated
    public boolean SPACE_AROUND_DICTIONARY_LITERAL_COLON;
    public boolean SPACE_BEFORE_DICTIONARY_LITERAL_COLON;
    public boolean SPACE_AFTER_DICTIONARY_LITERAL_COLON;
    public boolean SPACE_AROUND_PM_OPERATORS;
    public boolean DISCHARGED_SHORT_TERNARY_OPERATOR;
    public boolean IN_LINE_SHORT_TERNARY_OPERATOR;
    public int KEEP_BLANK_LINES_BEFORE_END;
    public int BLANK_LINES_AROUND_NAMESPACE;
    public int BLANK_LINES_AROUND_PROPERTIES_IN_INTERFACE;
    public int BLANK_LINES_AROUND_PROPERTIES_IN_DECLARATION;
    public boolean RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR;
    public RememberedOption GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES;
    public boolean REFACTOR_PROPERTIES_AND_IVARS;
    public boolean REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES;
    public boolean USE_SETTERS_IN_CONSTRUCTOR;
    public boolean GENERATE_CLASS_CONSTRUCTOR;
    public boolean CREATE_INTERFACE_FOR_CATEGORIES;
    public boolean PUT_IVARS_TO_IMPLEMENTATION;
    public boolean SEMICOLON_AFTER_METHOD_SIGNATURE;
    public ReleaseStyle RELEASE_STYLE;
    public boolean DECLARE_GENERATED_METHODS;
    public boolean DESCRIPTION_INCLUDE_MEMBER_NAMES;
    public boolean COPY_IS_DEEP;
    public boolean PROPERTY_NONATOMIC;
    public boolean INSERT_OVERRIDE;
    public boolean INSERT_VIRTUAL_WITH_OVERRIDE;
    public boolean USE_MODERN_CASTS;
    public boolean ADD_BRIEF_TAG;
    public DocTagPrefix TAG_PREFIX_OF_BLOCK_COMMENT;
    public DocTagPrefix TAG_PREFIX_OF_LINE_COMMENT;
    public String IVARS_PREFIX;
    public String IVARS_SUFFIX;
    public String FIELDS_PREFIX;
    public String FIELDS_SUFFIX;
    public String GETTERS_PREFIX;
    public String SETTERS_PREFIX;
    public boolean GENERATE_ADDITIONAL_EQ_OPERATORS;
    public boolean GENERATE_ADDITIONAL_REL_OPERATORS;
    public boolean GENERATE_OPERATORS_AS_MEMBERS;
    public boolean GENERATE_COMPARISON_OPERATORS_USE_STD_TIE;
    public List<OCDeclarationKind> FILE_DECLARATIONS_ORDER;
    public List<OCDeclarationKind> CLASS_DECLARATIONS_ORDER;
    public boolean INTRODUCE_CONST_VARS;
    public boolean INTRODUCE_CONST_PARAMS;
    public boolean INTRODUCE_AUTO_VARS;
    public boolean INTRODUCE_STATIC_CONSTS;
    public boolean INTRODUCE_GLOBALS_TO_HEADER;
    public boolean INTRODUCE_GENERATE_PROPERTY;
    public boolean INTRODUCE_GENERATE_SYNTHESIZE;
    public boolean INTRODUCE_PROP_TO_PRIVATE_CATEGORY;
    public boolean INTRODUCE_USE_NS_TYPES;
    public Placement TYPE_QUALIFIERS_PLACEMENT;
    public ImmutableList<FileExtensionPair> FILE_EXTENSION_PAIRS_ORDERED;
    
    protected OCCodeStyleSettings(final CodeStyleSettings codeStyleSettings) {
        super("Objective-C", codeStyleSettings);
        this.DO_NOT_ADD_BREAKS = false;
        this.INDENT_NAMESPACE_MEMBERS = this.getContainer().getIndentOptions((FileType)OCFileType.INSTANCE).INDENT_SIZE;
        this.INDENT_C_STRUCT_MEMBERS = this.getContainer().getIndentOptions((FileType)OCFileType.INSTANCE).INDENT_SIZE;
        this.INDENT_CLASS_MEMBERS = this.getContainer().getIndentOptions((FileType)OCFileType.INSTANCE).INDENT_SIZE;
        this.INDENT_INTERFACE_MEMBERS = 0;
        this.INDENT_BLOCK_COMMENT = true;
        this.INDENT_INTERFACE_MEMBERS_EXCEPT_IVARS_BLOCK = false;
        this.INDENT_IMPLEMENTATION_MEMBERS = 0;
        this.INDENT_VISIBILITY_KEYWORDS = 0;
        this.INDENT_INSIDE_CODE_BLOCK = this.getContainer().getIndentOptions((FileType)OCFileType.INSTANCE).INDENT_SIZE;
        this.INDENT_PREPROCESSOR_DIRECTIVE = 0;
        this.INDENT_DIRECTIVE_AS_CODE = false;
        this.KEEP_STRUCTURES_IN_ONE_LINE = false;
        this.KEEP_CASE_EXPRESSIONS_IN_ONE_LINE = false;
        this.KEEP_NESTED_NAMESPACES_IN_ONE_LINE = false;
        this.KEEP_DIRECTIVE_AT_FIRST_COLUMN = true;
        this.NAMESPACE_BRACE_PLACEMENT = 1;
        this.METHOD_BRACE_PLACEMENT = 1;
        this.FUNCTION_BRACE_PLACEMENT = 1;
        this.BLOCK_BRACE_PLACEMENT = this.FUNCTION_BRACE_PLACEMENT;
        this.METHOD_PARAMETERS_WRAP = 0;
        this.METHOD_PARAMETERS_ALIGN_MULTILINE = false;
        this.METHOD_PARAMETERS_ALIGN_BY_COLONS = true;
        this.METHOD_CALL_ARGUMENTS_WRAP = 0;
        this.METHOD_CALL_ARGUMENTS_ALIGN_MULTILINE = false;
        this.METHOD_CALL_ARGUMENTS_ALIGN_BY_COLONS = true;
        this.METHOD_CALL_ARGUMENTS_SPECIAL_DICTIONARY_PAIRS_TREATMENT = true;
        this.FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP = 0;
        this.FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP = 0;
        this.FUNCTION_PARAMETERS_WRAP = 0;
        this.FUNCTION_PARAMETERS_ALIGN_MULTILINE = false;
        this.FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR = false;
        this.FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR = false;
        this.FUNCTION_PARAMETERS_ALIGN_MULTILINE_PARS = false;
        this.FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE = false;
        this.LAMBDA_CAPTURE_LIST_WRAP = 0;
        this.LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE = false;
        this.LAMBDA_CAPTURE_LIST_NEW_LINE_AFTER_LBRACKET = false;
        this.LAMBDA_CAPTURE_LIST_NEW_LINE_BEFORE_RBRACKET = false;
        this.LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE_BRACKET = false;
        this.LAMBDA_CAPTURE_LIST_COMMA_ON_NEXT_LINE = false;
        this.FUNCTION_CALL_ARGUMENTS_WRAP = 0;
        this.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE = false;
        this.FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR = false;
        this.FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR = false;
        this.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS = false;
        this.FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE = false;
        this.SHIFT_OPERATION_WRAP = 1;
        this.SHIFT_OPERATION_ALIGN_MULTILINE = true;
        this.TEMPLATE_DECLARATION_STRUCT_WRAP = 2;
        this.TEMPLATE_DECLARATION_STRUCT_BODY_INDENT = false;
        this.TEMPLATE_DECLARATION_FUNCTION_WRAP = 2;
        this.TEMPLATE_DECLARATION_FUNCTION_BODY_INDENT = false;
        this.TEMPLATE_PARAMETERS_WRAP = this.FUNCTION_PARAMETERS_WRAP;
        this.TEMPLATE_PARAMETERS_ALIGN_MULTILINE = this.FUNCTION_PARAMETERS_ALIGN_MULTILINE;
        this.TEMPLATE_PARAMETERS_NEW_LINE_AFTER_LT = this.FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR;
        this.TEMPLATE_PARAMETERS_NEW_LINE_BEFORE_GT = this.FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR;
        this.TEMPLATE_PARAMETERS_ALIGN_MULTILINE_PARS = this.FUNCTION_PARAMETERS_ALIGN_MULTILINE_PARS;
        this.TEMPLATE_PARAMETERS_COMMA_ON_NEXT_LINE = this.FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE;
        this.TEMPLATE_CALL_ARGUMENTS_WRAP = this.FUNCTION_CALL_ARGUMENTS_WRAP;
        this.TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE = this.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE;
        this.TEMPLATE_CALL_ARGUMENTS_NEW_LINE_AFTER_LT = this.FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR;
        this.TEMPLATE_CALL_ARGUMENTS_NEW_LINE_BEFORE_GT = this.FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR;
        this.TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS = this.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS;
        this.TEMPLATE_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE = this.FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE;
        this.CLASS_CONSTRUCTOR_INIT_LIST_WRAP = 5;
        this.CLASS_CONSTRUCTOR_INIT_LIST_ALIGN_MULTILINE = true;
        this.CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE = false;
        this.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON = 2;
        this.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON = 0;
        this.SUPERCLASS_LIST_WRAP = 5;
        this.SUPERCLASS_LIST_ALIGN_MULTILINE = true;
        this.SUPERCLASS_LIST_COMMA_ON_NEXT_LINE = false;
        this.SUPERCLASS_LIST_BEFORE_COLON = 2;
        this.SUPERCLASS_LIST_AFTER_COLON = 0;
        this.SPACE_BEFORE_TEMPLATE_DECLARATION_LT = false;
        this.SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT = false;
        this.SPACE_WITHIN_EMPTY_TEMPLATE_DECLARATION_LTGT = false;
        this.SPACE_BEFORE_TEMPLATE_CALL_LT = false;
        this.SPACE_WITHIN_TEMPLATE_CALL_LTGT = false;
        this.SPACE_WITHIN_EMPTY_TEMPLATE_CALL_LTGT = false;
        this.SPACE_WITHIN_TEMPLATE_DOUBLE_GT = true;
        this.WRAP_PROPERTY_DECLARATION = 0;
        this.ARRAY_INITIALIZER_COMMA_ON_NEXT_LINE = false;
        this.ALIGN_DICTIONARY_PAIR_VALUES = false;
        this.ALIGN_INIT_LIST_IN_COLUMNS = false;
        this.ENUM_CONSTANTS_COMMA_ON_NEXT_LINE = false;
        this.SPACE_WITHIN_EMPTY_BRACES = false;
        this.SPACE_BEFORE_NAMESPACE_LBRACE = true;
        this.SPACE_BEFORE_AUTORELEASE_POOL_LBRACE = true;
        this.SPACE_AFTER_STRUCTURES_RBRACE = true;
        this.SPACE_WITHIN_METHOD_RETURN_TYPE_PARENTHESES = false;
        this.SPACE_AFTER_METHOD_RETURN_TYPE_PARENTHESES = false;
        this.SPACE_WITHIN_METHOD_PARAMETER_TYPE_PARENTHESES = false;
        this.SPACE_AFTER_METHOD_PARAMETER_TYPE_PARENTHESES = false;
        this.SPACE_BEFORE_PROPERTY_ATTRIBUTES_PARENTHESES = false;
        this.SPACE_WITHIN_PROPERTY_ATTRIBUTES_PARENTHESES = false;
        this.SPACE_WITHIN_FUNCTION_DECLARATION_PARENTHESES = false;
        this.SPACE_WITHIN_EMPTY_FUNCTION_DECLARATION_PARENTHESES = false;
        this.SPACE_WITHIN_FUNCTION_CALL_PARENTHESES = false;
        this.SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES = false;
        this.SPACE_WITHIN_CATEGORY_PARENTHESES = false;
        this.SPACE_WITHIN_PROTOCOLS_BRACKETS = false;
        this.SPACE_WITHIN_SEND_MESSAGE_BRACKETS = false;
        this.SPACE_WITHIN_LAMBDA_CAPTURE_LIST_BRACKET = false;
        this.SPACE_WITHIN_EMPTY_LAMBDA_CAPTURE_LIST_BRACKET = false;
        this.SPACE_BEFORE_SUPERCLASS_COLON = true;
        this.SPACE_AFTER_SUPERCLASS_COLON = true;
        this.SPACE_BEFORE_INIT_LIST_COLON = true;
        this.SPACE_AFTER_INIT_LIST_COLON = true;
        this.SPACE_BEFORE_INIT_LIST = false;
        this.SPACE_BEFORE_CATEGORY_PARENTHESES = true;
        this.SPACE_BEFORE_PROTOCOLS_BRACKETS = true;
        this.SPACE_AFTER_VISIBILITY_SIGN_IN_METHOD_DECLARATION = true;
        this.SPACE_AFTER_COLON_IN_SELECTOR = false;
        this.SPACE_BEFORE_CHAINED_SEND_MESSAGE = true;
        this.SPACE_BEFORE_POINTER_IN_DECLARATION = true;
        this.SPACE_AFTER_POINTER_IN_DECLARATION = false;
        this.SPACE_BEFORE_REFERENCE_IN_DECLARATION = true;
        this.SPACE_AFTER_REFERENCE_IN_DECLARATION = false;
        this.SPACE_AFTER_REFERENCE_IN_RVALUE = false;
        this.SPACE_AFTER_CUP_IN_BLOCKS = false;
        this.SPACE_BETWEEN_ADJACENT_BRACKETS = true;
        this.SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR = false;
        this.SPACE_AROUND_DICTIONARY_LITERAL_COLON = true;
        this.SPACE_BEFORE_DICTIONARY_LITERAL_COLON = false;
        this.SPACE_AFTER_DICTIONARY_LITERAL_COLON = true;
        this.SPACE_AROUND_PM_OPERATORS = false;
        this.DISCHARGED_SHORT_TERNARY_OPERATOR = false;
        this.IN_LINE_SHORT_TERNARY_OPERATOR = true;
        this.KEEP_BLANK_LINES_BEFORE_END = 2;
        this.BLANK_LINES_AROUND_NAMESPACE = 0;
        this.BLANK_LINES_AROUND_PROPERTIES_IN_INTERFACE = 0;
        this.BLANK_LINES_AROUND_PROPERTIES_IN_DECLARATION = 0;
        this.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR = true;
        this.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES = RememberedOption.ASK;
        this.REFACTOR_PROPERTIES_AND_IVARS = true;
        this.REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES = true;
        this.USE_SETTERS_IN_CONSTRUCTOR = true;
        this.GENERATE_CLASS_CONSTRUCTOR = true;
        this.CREATE_INTERFACE_FOR_CATEGORIES = true;
        this.PUT_IVARS_TO_IMPLEMENTATION = true;
        this.SEMICOLON_AFTER_METHOD_SIGNATURE = false;
        this.RELEASE_STYLE = ReleaseStyle.IVAR;
        this.DECLARE_GENERATED_METHODS = true;
        this.DESCRIPTION_INCLUDE_MEMBER_NAMES = true;
        this.COPY_IS_DEEP = false;
        this.PROPERTY_NONATOMIC = true;
        this.INSERT_OVERRIDE = true;
        this.INSERT_VIRTUAL_WITH_OVERRIDE = false;
        this.USE_MODERN_CASTS = true;
        this.ADD_BRIEF_TAG = false;
        this.TAG_PREFIX_OF_BLOCK_COMMENT = DocTagPrefix.AT;
        this.TAG_PREFIX_OF_LINE_COMMENT = (PlatformUtils.isAppCode() ? DocTagPrefix.AT : DocTagPrefix.BACK_SLASH);
        this.IVARS_PREFIX = "_";
        this.IVARS_SUFFIX = "";
        this.FIELDS_PREFIX = "";
        this.FIELDS_SUFFIX = "";
        this.GETTERS_PREFIX = "get";
        this.SETTERS_PREFIX = "set";
        this.GENERATE_ADDITIONAL_EQ_OPERATORS = true;
        this.GENERATE_ADDITIONAL_REL_OPERATORS = true;
        this.GENERATE_OPERATORS_AS_MEMBERS = true;
        this.GENERATE_COMPARISON_OPERATORS_USE_STD_TIE = false;
        this.FILE_DECLARATIONS_ORDER = new ArrayList<OCDeclarationKind>(OCDeclarationKind.ourFileDeclarationKinds);
        this.CLASS_DECLARATIONS_ORDER = new ArrayList<OCDeclarationKind>(OCDeclarationKind.ourClassDeclarationKinds);
        this.INTRODUCE_CONST_VARS = false;
        this.INTRODUCE_CONST_PARAMS = false;
        this.INTRODUCE_AUTO_VARS = false;
        this.INTRODUCE_STATIC_CONSTS = true;
        this.INTRODUCE_GLOBALS_TO_HEADER = true;
        this.INTRODUCE_GENERATE_PROPERTY = false;
        this.INTRODUCE_GENERATE_SYNTHESIZE = true;
        this.INTRODUCE_PROP_TO_PRIVATE_CATEGORY = false;
        this.INTRODUCE_USE_NS_TYPES = false;
        this.TYPE_QUALIFIERS_PLACEMENT = Placement.BEFORE;
        final ArrayList<FileExtensionPair> list = new ArrayList<FileExtensionPair>(3);
        try {
            if (PlatformUtils.isAppCode()) {
                list.add(new FileExtensionPair("mm", "h"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
        ContainerUtil.addAll((Collection)list, (Object[])new FileExtensionPair[] { new FileExtensionPair("cpp", "h"), new FileExtensionPair("c", "h") });
        this.FILE_EXTENSION_PAIRS_ORDERED = (ImmutableList<FileExtensionPair>)ContainerUtil.immutableList((List)list);
        OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.configureCustomSettings(this);
    }
    
    public Object clone() {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)super.clone();
        ocCodeStyleSettings.FILE_DECLARATIONS_ORDER = new ArrayList<OCDeclarationKind>(this.FILE_DECLARATIONS_ORDER);
        ocCodeStyleSettings.CLASS_DECLARATIONS_ORDER = new ArrayList<OCDeclarationKind>(this.CLASS_DECLARATIONS_ORDER);
        return ocCodeStyleSettings;
    }
    
    @NotNull
    public List<String> getKnownTagNames() {
        List concat;
        try {
            concat = ContainerUtil.concat(super.getKnownTagNames(), (List)Collections.singletonList(this.a()));
            if (concat == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings", "getKnownTagNames"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
        return (List<String>)concat;
    }
    
    @NotNull
    private String a() {
        String string;
        try {
            string = this.getTagName() + "-extensions";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings", "getExtensionTagName"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
        return string;
    }
    
    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        final Element child = element.getChild(this.a());
        try {
            if (child == null) {
                return;
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
        this.a(child);
        a(child, "file", this.FILE_DECLARATIONS_ORDER);
        a(child, "class", this.CLASS_DECLARATIONS_ORDER);
        this.b(child);
    }
    
    private void a(final Element element) {
        for (final Element element2 : element.getChildren("option")) {
            final String attributeValue = element2.getAttributeValue("name");
            final String attributeValue2 = element2.getAttributeValue("value");
            try {
                final Field field = this.getClass().getField(attributeValue);
                Label_0086: {
                    try {
                        if (!field.getType().isEnum()) {
                            continue;
                        }
                        final String s = attributeValue2;
                        if (s != null) {
                            break Label_0086;
                        }
                        continue;
                    }
                    catch (IllegalAccessException ex) {
                        throw b(ex);
                    }
                    try {
                        final String s = attributeValue2;
                        if (s == null) {
                            continue;
                        }
                        field.set(this, Enum.valueOf(field.getType(), attributeValue2));
                    }
                    catch (IllegalAccessException ex2) {
                        throw b(ex2);
                    }
                }
            }
            catch (IllegalAccessException ex3) {
                throw new InvalidDataException((Throwable)ex3);
            }
            catch (NoSuchFieldException ex4) {
                OCCodeStyleSettings.LOG.debug((Throwable)ex4);
            }
        }
    }
    
    private static void a(final Element element, final String s, final List<OCDeclarationKind> list) {
        final Element child = element.getChild(s);
        try {
            if (child == null) {
                return;
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
        list.clear();
        final Iterator iterator = child.getChildren("option").iterator();
        while (iterator.hasNext()) {
            final OCDeclarationKind ocDeclarationKind = Enum.valueOf(OCDeclarationKind.class, iterator.next().getAttributeValue("value"));
            try {
                if (!ocDeclarationKind.isAvailable()) {
                    continue;
                }
                list.add(ocDeclarationKind);
            }
            catch (InvalidDataException ex2) {
                throw b((Exception)ex2);
            }
        }
    }
    
    private void b(final Element element) {
        final Element child = element.getChild("extensions");
        if (child != null) {
            final ArrayList arrayList = ContainerUtil.newArrayList();
            for (final Element element2 : child.getChildren("pair")) {
                final String attributeValue = element2.getAttributeValue("source");
                final String attributeValue2 = element2.getAttributeValue("header");
                Label_0085: {
                    try {
                        if (attributeValue == null) {
                            continue;
                        }
                        final String s = attributeValue2;
                        if (s != null) {
                            break Label_0085;
                        }
                        continue;
                    }
                    catch (InvalidDataException ex) {
                        throw b((Exception)ex);
                    }
                    try {
                        final String s = attributeValue2;
                        if (s == null) {
                            continue;
                        }
                        arrayList.add(new FileExtensionPair(attributeValue, attributeValue2));
                    }
                    catch (InvalidDataException ex2) {
                        throw b((Exception)ex2);
                    }
                }
            }
            try {
                if (!arrayList.isEmpty()) {
                    this.FILE_EXTENSION_PAIRS_ORDERED = (ImmutableList<FileExtensionPair>)ContainerUtil.immutableList((List)arrayList);
                }
            }
            catch (InvalidDataException ex3) {
                throw b((Exception)ex3);
            }
        }
    }
    
    public void writeExternal(final Element element, @NotNull final CustomCodeStyleSettings customCodeStyleSettings) throws WriteExternalException {
        try {
            if (customCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentSettings", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings", "writeExternal"));
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
        super.writeExternal(element, customCodeStyleSettings);
        final Element element2 = new Element(this.a());
        this.a(element2, (DifferenceFilter<CustomCodeStyleSettings>)new DifferenceFilter((Object)this, (Object)customCodeStyleSettings));
        b(element2, "file", this.FILE_DECLARATIONS_ORDER);
        b(element2, "class", this.CLASS_DECLARATIONS_ORDER);
        c(element2, "extensions", (List<? extends JDOMExternalizable>)this.FILE_EXTENSION_PAIRS_ORDERED);
        element.addContent(element2);
    }
    
    private void a(final Element element, final DifferenceFilter<CustomCodeStyleSettings> differenceFilter) {
        for (final Field field : this.getClass().getFields()) {
            Label_0089: {
                try {
                    if (!field.getType().isEnum() || !differenceFilter.isAccept(field)) {
                        break Label_0089;
                    }
                }
                catch (IllegalAccessException ex) {
                    throw b(ex);
                }
                Element a;
                try {
                    a = a(field.getName(), (Enum)field.get(this));
                }
                catch (IllegalAccessException ex2) {
                    break Label_0089;
                }
                element.addContent(a);
            }
        }
    }
    
    protected void importLegacySettings() {
        try {
            super.importLegacySettings();
            if (!this.SPACE_AROUND_DICTIONARY_LITERAL_COLON) {
                this.SPACE_AROUND_DICTIONARY_LITERAL_COLON = true;
                this.SPACE_BEFORE_DICTIONARY_LITERAL_COLON = false;
                this.SPACE_AFTER_DICTIONARY_LITERAL_COLON = false;
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
    }
    
    private static void c(final Element element, final String s, final List<? extends JDOMExternalizable> list) throws WriteExternalException {
        final Element element2 = new Element(s);
        final Iterator<? extends JDOMExternalizable> iterator = list.iterator();
        while (iterator.hasNext()) {
            ((JDOMExternalizable)iterator.next()).writeExternal(element2);
        }
        try {
            if (!element2.getContent().isEmpty()) {
                element.addContent(element2);
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
    }
    
    private static void b(final Element element, final String s, final List<OCDeclarationKind> list) {
        final Element element2 = new Element(s);
        final Iterator<OCDeclarationKind> iterator = list.iterator();
        while (iterator.hasNext()) {
            element2.addContent(a(OCDeclarationKind.class.getName(), iterator.next()));
        }
        element.addContent(element2);
    }
    
    private static Element a(final String s, final Enum enum1) {
        final Element element = new Element("option");
        element.setAttribute("name", s);
        element.setAttribute("value", enum1.name());
        return element;
    }
    
    @Nullable
    public static RememberedOption askAndSave(final RememberedOption rememberedOption, final String s, final String s2, final Ref<Boolean> ref) {
        try {
            if (ApplicationManager.getApplication().isUnitTestMode()) {
                ref.set((Object)true);
                return rememberedOption;
            }
        }
        catch (InvalidDataException ex) {
            throw b((Exception)ex);
        }
        if (rememberedOption == RememberedOption.ASK) {
            final Ref create = Ref.create((Object)Boolean.FALSE);
            final int show = ((MessageDialogBuilder.YesNoCancel)MessageDialogBuilder.yesNoCancel(s2, s).doNotAsk((DialogWrapper.DoNotAskOption)new DialogWrapper.DoNotAskOption() {
                public boolean isToBeShown() {
                    return true;
                }
                
                public void setToBeShown(final boolean b, final int n) {
                    Ref val$save = null;
                    boolean b2 = false;
                    Label_0017: {
                        try {
                            val$save = create;
                            if (!b) {
                                b2 = true;
                                break Label_0017;
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        b2 = false;
                    }
                    val$save.set((Object)b2);
                }
                
                public boolean canBeHidden() {
                    return true;
                }
                
                public boolean shouldSaveOptionsOnCancel() {
                    return true;
                }
                
                @NotNull
                public String getDoNotShowMessage() {
                    String s;
                    try {
                        s = "Remember the choice";
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$1", "getDoNotShowMessage"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return s;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            })).show();
            try {
                if (show == 2) {
                    return null;
                }
            }
            catch (InvalidDataException ex2) {
                throw b((Exception)ex2);
            }
            boolean b = false;
            Label_0091: {
                try {
                    if (show == 0) {
                        b = true;
                        break Label_0091;
                    }
                }
                catch (InvalidDataException ex3) {
                    throw b((Exception)ex3);
                }
                b = false;
            }
            final boolean b2 = b;
            Label_0128: {
                try {
                    ref.set((Object)b2);
                    if (!(boolean)create.get()) {
                        return rememberedOption;
                    }
                    final boolean b3 = b2;
                    if (b3) {
                        break Label_0128;
                    }
                    return RememberedOption.NO;
                }
                catch (InvalidDataException ex4) {
                    throw b((Exception)ex4);
                }
                try {
                    final boolean b3 = b2;
                    if (b3) {
                        return RememberedOption.YES;
                    }
                }
                catch (InvalidDataException ex5) {
                    throw b((Exception)ex5);
                }
            }
            return RememberedOption.NO;
        }
        boolean b4 = false;
        Label_0162: {
            try {
                if (rememberedOption == RememberedOption.YES) {
                    b4 = true;
                    break Label_0162;
                }
            }
            catch (InvalidDataException ex6) {
                throw b((Exception)ex6);
            }
            b4 = false;
        }
        ref.set((Object)b4);
        return rememberedOption;
    }
    
    static {
        LOG = Logger.getInstance("#com.intellij.openapi.util.DefaultJDOMExternalizer");
        LEW_LINE_OPTIONS = new String[] { OCBundle.message("wrapping.new.line.never", new Object[0]), OCBundle.message("wrapping.new.line.always", new Object[0]), OCBundle.message("wrapping.new.line.if.long", new Object[0]) };
        LEW_LINE_VALUES = new int[] { 0, 1, 2 };
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public enum HeaderImportStyle
    {
        PREDEFINE, 
        IMPORT;
    }
    
    public enum ReleaseStyle
    {
        IVAR, 
        IVAR_2, 
        PROPERTY;
    }
    
    public enum RememberedOption
    {
        YES, 
        NO, 
        ASK;
    }
    
    public enum Placement
    {
        BEFORE, 
        AFTER;
    }
    
    public enum DocTagPrefix
    {
        AT, 
        BACK_SLASH;
    }
    
    public static class FileExtensionPair implements JDOMExternalizable
    {
        @NotNull
        public final String mySourceExt;
        @NotNull
        public final String myHeaderExt;
        
        public FileExtensionPair(@NotNull final String mySourceExt, @NotNull final String myHeaderExt) {
            if (mySourceExt == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceExt", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair", "<init>"));
            }
            if (myHeaderExt == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerExt", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair", "<init>"));
            }
            this.mySourceExt = mySourceExt;
            this.myHeaderExt = myHeaderExt;
        }
        
        @Override
        public String toString() {
            return "." + this.mySourceExt + " / ." + this.myHeaderExt;
        }
        
        @Override
        public int hashCode() {
            return 31 * this.mySourceExt.hashCode() + this.myHeaderExt.hashCode();
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (InvalidDataException ex) {
                throw b(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final FileExtensionPair fileExtensionPair = this;
                    final Class<? extends FileExtensionPair> clazz = fileExtensionPair.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (InvalidDataException ex2) {
                    throw b(ex2);
                }
                try {
                    final FileExtensionPair fileExtensionPair = this;
                    final Class<? extends FileExtensionPair> clazz = fileExtensionPair.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (InvalidDataException ex3) {
                    throw b(ex3);
                }
            }
            final FileExtensionPair fileExtensionPair2 = (FileExtensionPair)o;
            try {
                if (!this.mySourceExt.equals(fileExtensionPair2.mySourceExt)) {
                    return false;
                }
            }
            catch (InvalidDataException ex4) {
                throw b(ex4);
            }
            try {
                if (!this.myHeaderExt.equals(fileExtensionPair2.myHeaderExt)) {
                    return false;
                }
            }
            catch (InvalidDataException ex5) {
                throw b(ex5);
            }
            return true;
        }
        
        public void readExternal(final Element element) throws InvalidDataException {
        }
        
        public void writeExternal(final Element element) throws WriteExternalException {
            final Element element2 = new Element("pair");
            element2.setAttribute("source", this.mySourceExt);
            element2.setAttribute("header", this.myHeaderExt);
            element.addContent(element2);
        }
        
        private static InvalidDataException b(final InvalidDataException ex) {
            return ex;
        }
    }
    
    public @interface NewLineConstant {
    }
}
