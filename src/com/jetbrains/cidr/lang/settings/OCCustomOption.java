// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import java.io.IOException;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;

public interface OCCustomOption
{
    public static final String LAMBDA_CAPTURE_LIST_GROUP = OCBundle.message("title.capture.list.group", new Object[0]);
    public static final String FUNCTION_PARAMETERS_GROUP = OCBundle.message("title.function.parameters.group", new Object[0]);
    public static final String FUNCTION_RETURN_TYPE_GROUP = OCBundle.message("title.function.return.type.group", new Object[0]);
    public static final String FUNCTION_NON_TOP_AFTER_RETURN_TYPE = OCBundle.message("title.function.nontop.return.type", new Object[0]);
    public static final String FUNCTION_TOP_AFTER_RETURN_TYPE = OCBundle.message("title.function.top.return.type", new Object[0]);
    public static final String FUNCTION_CALL_ARGUMENTS_GROUP = OCBundle.message("title.function.call.arguments.group", new Object[0]);
    public static final String TEMPLATE_DECLARATION_FUNCTION_GROUP = OCBundle.message("title.template.decl.func.group", new Object[0]);
    public static final String TEMPLATE_PARAMETERS_GROUP = OCBundle.message("title.template.parameters.group", new Object[0]);
    public static final String TEMPLATE_CALL_ARGUMENTS_GROUP = OCBundle.message("title.template.call.arguments.group", new Object[0]);
    public static final String TEMPLATE_DECLARATION_STRUCT_GROUP = OCBundle.message("title.template.decl.class.group", new Object[0]);
    public static final String CLASS_CONSTRUCTOR_INIT_LIST_GROUP = OCBundle.message("title.ctor.init.list.group", new Object[0]);
    public static final String SUPERCLASS_LIST_GROUP = OCBundle.message("title.superclass.list.group", new Object[0]);
    public static final String SHIFT_OPERATORS_GROUP = OCBundle.message("title.shift.operators.group", new Object[0]);
    public static final String SPACE_SEPARATOR = "SPACE_SEPARATOR";
    
    void configureDefaultSettings(@NotNull final CommonCodeStyleSettings p0);
    
    void configureCustomSettings(@NotNull final OCCodeStyleSettings p0);
    
    @NotNull
    CustomOption[] getIndentCustomOptions();
    
    @NotNull
    CustomOption[] getFoldingCustomOptions();
    
    @NotNull
    AttributesDescriptor[] getAttributeDescriptors();
    
    @NotNull
    String[] getWrappingAndBracesOptions();
    
    @NotNull
    RenameAction[] getWrappingAndBracesRenames();
    
    @NotNull
    CustomOption[] getWrappingAndBracesCustomOptions();
    
    @NotNull
    String[] getSpacingOptions();
    
    @NotNull
    RenameAction[] getSpacingRenames();
    
    @NotNull
    CustomOption[] getSpacingCustomOptions();
    
    @NotNull
    RenameAction[] getBlankLinesRenames();
    
    @NotNull
    String getExampleExtension();
    
    public static class RenameAction
    {
        public final String from;
        public final String to;
        
        public RenameAction(final String from, final String to) {
            this.from = from;
            this.to = to;
        }
    }
    
    public static class MoveAction
    {
        public final String group;
        public final String[] options;
        
        public MoveAction(final String group, final String... options) {
            this.group = group;
            this.options = options;
        }
    }
    
    public static class CustomOption
    {
        public static final CustomOption OPTION_SEPARATOR;
        @NotNull
        public final String fieldName;
        @NotNull
        public final String title;
        @Nullable
        public final String groupName;
        @Nullable
        public final CodeStyleSettingsCustomizable.OptionAnchor anchor;
        @Nullable
        public final String anchorFieldName;
        public final Object[] options;
        
        public CustomOption(@NotNull final String fieldName, @NotNull final String title, @Nullable final String groupName, @Nullable final CodeStyleSettingsCustomizable.OptionAnchor anchor, @Nullable final String anchorFieldName, final Object... options) {
            if (fieldName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
            }
            if (title == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
            }
            this.fieldName = fieldName;
            this.title = title;
            this.groupName = groupName;
            this.anchor = anchor;
            this.anchorFieldName = anchorFieldName;
            this.options = options;
        }
        
        public CustomOption(@NotNull final String s, @NotNull final String s2, @Nullable final String s3, final Object... array) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
            }
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
            }
            this(s, s2, s3, null, null, array);
        }
        
        public CustomOption(@NotNull final String s, @NotNull final String s2) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
            }
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
            }
            this(s, s2, null, null, null, new Object[0]);
        }
        
        static {
            OPTION_SEPARATOR = new CustomOption("SPACE_SEPARATOR", "SPACE_SEPARATOR");
        }
    }
    
    public static class ResourceReader
    {
        public static String readExampleString(@NotNull final Class clazz, @NotNull final String s) {
            try {
                if (clazz == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resourceClass", "com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader", "readExampleString"));
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resourcePath", "com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader", "readExampleString"));
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            String replace;
            try {
                replace = StreamUtil.readText(clazz.getResourceAsStream(s), CharsetToolkit.UTF8_CHARSET).replace("\r", "");
            }
            catch (IOException ex3) {
                OCLog.LOG.error(ex3.getMessage());
                replace = "<Resource reading error>";
            }
            return replace;
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
    }
}
