// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import java.util.Arrays;
import java.util.Collections;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import java.util.Set;

public class ClangFeatures
{
    private static final String[] FEATURES_AND_EXTENSIONS;
    private static final String[] TYPE_TRAITS;
    private static final String[] KNOWN_CLANG_OBJC_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_APPLE_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_GPU_REGISTER_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_FUNCTION_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_VARIABLE_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_TYPE_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_CALLING_CONVENTIONS_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_CONSUMED_ANNOTATION_CHECKING_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_TYPE_SAFETY_CHECKING_ATTRIBUTES;
    private static final String[] KNOWN_CLANG_TYPE_NULLABILITY_ATTRIBUTES;
    private static final String[] KNOWN_GCC_COMMON_ATTRIBUTES;
    private static final String[] KNOWN_GCC_WINDOWS_ATTRIBUTES;
    private static final String[] KNOWN_GCC_X86_ATTRIBUTES;
    private static final String[] KNOWN_GCC_FUNCTION_ATTRIBUTES;
    private static final String[] KNOWN_GCC_VARIABLE_ATTRIBUTES;
    private static final String[] KNOWN_GCC_TYPE_ATTRIBUTES;
    private static final String[] KNOWN_OTHER_ATTRIBUTES;
    private static final Set<String> ourFeatures;
    private static final Map<String, OCCompilerFeatures.Feature> ourClangIdsToCompilerFeatures;
    private static final Map<OCCompilerFeatures.Feature, String> ourCompilerFeaturesToClangIds;
    private static final Set<String> ourUnsupportedClangFeatures;
    private static final Set<String> ourAttributes;
    
    @NotNull
    public static Collection<String> getAllFeatures() {
        Set<String> ourFeatures;
        try {
            ourFeatures = ClangFeatures.ourFeatures;
            if (ourFeatures == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/ClangFeatures", "getAllFeatures"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ourFeatures;
    }
    
    @Nullable
    public static OCCompilerFeatures.Feature getFeatureForClangId(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clangId", "com/jetbrains/cidr/toolchains/ClangFeatures", "getFeatureForClangId"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ClangFeatures.ourClangIdsToCompilerFeatures.get(s);
    }
    
    @Nullable
    public static String getClangIdForFeature(@NotNull final OCCompilerFeatures.Feature feature) {
        try {
            if (feature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "feature", "com/jetbrains/cidr/toolchains/ClangFeatures", "getClangIdForFeature"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ClangFeatures.ourCompilerFeaturesToClangIds.get(feature);
    }
    
    @NotNull
    public static Set<String> getUnsupportedClangFeatures() {
        Set<String> ourUnsupportedClangFeatures;
        try {
            ourUnsupportedClangFeatures = ClangFeatures.ourUnsupportedClangFeatures;
            if (ourUnsupportedClangFeatures == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/ClangFeatures", "getUnsupportedClangFeatures"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ourUnsupportedClangFeatures;
    }
    
    @NotNull
    public static Collection<String> getAllAttributes() {
        Set<String> ourAttributes;
        try {
            ourAttributes = ClangFeatures.ourAttributes;
            if (ourAttributes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/ClangFeatures", "getAllAttributes"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ourAttributes;
    }
    
    static {
        FEATURES_AND_EXTENSIONS = new String[] { "address_sanitizer", "assume_nonnull", "attribute_analyzer_noreturn", "attribute_availability", "attribute_availability_with_message", "attribute_availability_app_extension", "attribute_availability_with_version_underscores", "attribute_availability_tvos", "attribute_availability_watchos", "attribute_availability_with_strict", "attribute_availability_with_replacement", "attribute_availability_in_templates", "attribute_cf_returns_not_retained", "attribute_cf_returns_retained", "attribute_cf_returns_on_parameters", "attribute_deprecated_with_message", "attribute_deprecated_with_replacement", "attribute_ext_vector_type", "attribute_ns_returns_not_retained", "attribute_ns_returns_retained", "attribute_ns_consumes_self", "attribute_ns_consumed", "attribute_cf_consumed", "attribute_objc_ivar_unused", "attribute_objc_method_family", "attribute_overloadable", "attribute_unavailable_with_message", "attribute_unused_on_fields", "blocks", "c_thread_safety_attributes", "cxx_exceptions", "cxx_rtti", "enumerator_attributes", "nullability", "nullability_on_arrays", "memory_sanitizer", "thread_sanitizer", "dataflow_sanitizer", "efficiency_sanitizer", "objc_arr", "objc_arc", "objc_arc_weak", "objc_default_synthesize_properties", "objc_fixed_enum", "objc_instancetype", "objc_kindof", "objc_modules", "objc_nonfragile_abi", "objc_property_explicit_atomic", "objc_protocol_qualifier_mangling", "objc_weak_class", "ownership_holds", "ownership_returns", "ownership_takes", "objc_bool", "objc_subscripting", "objc_array_literals", "objc_dictionary_literals", "objc_boxed_expressions", "objc_boxed_nsvalue_expressions", "arc_cf_code_audited", "objc_bridge_id", "objc_bridge_id_on_typedefs", "objc_generics", "objc_generics_variance", "objc_class_property", "c_alignas", "c_alignof", "c_atomic", "c_generic_selections", "c_static_assert", "c_thread_local", "cxx_access_control_sfinae", "cxx_alias_templates", "cxx_alignas", "cxx_alignof", "cxx_atomic", "cxx_attributes", "cxx_auto_type", "cxx_constexpr", "cxx_constexpr_string_builtins", "cxx_decltype", "cxx_decltype_incomplete_return_types", "cxx_default_function_template_args", "cxx_defaulted_functions", "cxx_delegating_constructors", "cxx_deleted_functions", "cxx_explicit_conversions", "cxx_generalized_initializers", "cxx_implicit_moves", "cxx_inheriting_constructors", "cxx_inline_namespaces", "cxx_lambdas", "cxx_local_type_template_args", "cxx_nonstatic_member_init", "cxx_noexcept", "cxx_nullptr", "cxx_override_control", "cxx_range_for", "cxx_raw_string_literals", "cxx_reference_qualified_functions", "cxx_rvalue_references", "cxx_strong_enums", "cxx_static_assert", "cxx_thread_local", "cxx_trailing_return", "cxx_unicode_literals", "cxx_unrestricted_unions", "cxx_user_literals", "cxx_variadic_templates", "cxx_aggregate_nsdmi", "cxx_binary_literals", "cxx_contextual_conversions", "cxx_decltype_auto", "cxx_generic_lambdas", "cxx_init_captures", "cxx_relaxed_constexpr", "cxx_return_type_deduction", "cxx_variable_templates", "cxx_runtime_arrays", "cxx_concepts", "raw_invocation_type", "c_alignas", "c_alignof", "c_atomic", "c_generic_selections", "c_static_assert", "c_thread_local", "cxx_atomic", "cxx_deleted_functions", "cxx_explicit_conversions", "cxx_inline_namespaces", "cxx_local_type_template_args", "cxx_nonstatic_member_init", "cxx_override_control", "cxx_range_for", "cxx_reference_qualified_functions", "cxx_rvalue_references", "cxx_variadic_templates", "cxx_binary_literals", "cxx_init_captures", "cxx_variable_templates", "modules", "safe_stack", "tls" };
        TYPE_TRAITS = new String[] { "has_nothrow_assign", "has_nothrow_copy", "has_nothrow_constructor", "has_trivial_assign", "has_trivial_copy", "has_trivial_constructor", "has_trivial_destructor", "has_virtual_destructor", "is_abstract", "is_base_of", "is_class", "is_constructible", "is_convertible_to", "is_empty", "is_enum", "is_final", "is_literal", "is_standard_layout", "is_pod", "is_polymorphic", "is_sealed", "is_trivial", "is_trivially_assignable", "is_trivially_constructible", "is_trivially_copyable", "is_union", "underlying_type" };
        KNOWN_CLANG_OBJC_ATTRIBUTES = new String[] { "objc_boxable", "objc_method_family", "objc_requires_super", "objc_runtime_name", "objc_arc_weak_reference_unavailable", "objc_bridge", "objc_designated_initializer", "objc_precise_lifetime", "objc_protocol_requires_explicit_implementation", "objc_requires_property_definitions", "objc_returns_inner_pointer", "objc_root_class" };
        KNOWN_CLANG_APPLE_ATTRIBUTES = new String[] { "ibaction", "iboutlet", "iboutletcollection", "ns_consumed", "ns_consumed_self", "ns_returns_not_retained", "ns_returns_retained", "swift_error", "swift_name", "swift_private", "swift_wrapper" };
        KNOWN_CLANG_GPU_REGISTER_ATTRIBUTES = new String[] { "amdgpu_num_sgpr", "amdgpu_num_vgpr" };
        KNOWN_CLANG_FUNCTION_ATTRIBUTES = new String[] { "interrupt", "acquire_capability", "acquire_shared_capability", "assume_aligned", "availability", "noreturn", "carries_dependency", "enable_if", "flatten", "format", "internal_linkage", "noduplicate", "no_sanitize", "no_sanitize_address", "no_address_safety_analysis", "no_sanitize_thread", "no_sanitize_memory", "no_split_stack", "optnone", "overloadable", "release_capability", "release_shared_capability", "target", "try_acquire_capability", "try_acquire_shared_capability" };
        KNOWN_CLANG_VARIABLE_ATTRIBUTES = new String[] { "section", "tls_model" };
        KNOWN_CLANG_TYPE_ATTRIBUTES = new String[] { "align_value", "flag_enum" };
        KNOWN_CLANG_CALLING_CONVENTIONS_ATTRIBUTES = new String[] { "fastcall", "ms_abi", "pcs", "regparm", "stdcall", "thiscall", "vectorcall" };
        KNOWN_CLANG_CONSUMED_ANNOTATION_CHECKING_ATTRIBUTES = new String[] { "callable_when", "consumable", "param_typestate", "return_typestate", "set_typestate", "test_typestate" };
        KNOWN_CLANG_TYPE_SAFETY_CHECKING_ATTRIBUTES = new String[] { "argument_with_type_tag", "pointer_with_type_tag", "type_tag_for_datatype" };
        KNOWN_CLANG_TYPE_NULLABILITY_ATTRIBUTES = new String[] { "nonnull", "returns_nonnull" };
        KNOWN_GCC_COMMON_ATTRIBUTES = new String[] { "aligned", "deprecated", "unused", "used", "hot", "cold", "section", "weak", "packed" };
        KNOWN_GCC_WINDOWS_ATTRIBUTES = new String[] { "dllexport", "dllimport", "selectany", "shared" };
        KNOWN_GCC_X86_ATTRIBUTES = new String[] { "ms_struct", "gcc_struct", "cdecl", "fastcall", "thiscall", "ms_abi", "sysv_abi", "callee_pop_aggregate_return", "ms_hook_prologue", "regparm", "sseregparm", "force_align_arg_pointer", "stdcall", "target" };
        KNOWN_GCC_FUNCTION_ATTRIBUTES = new String[] { "alias", "alloc_align", "alloc_size", "always_inline", "artificial", "assume_aligned", "bnd_instrument", "bnd_legacy", "const", "constructor", "destructor", "error", "warning", "externally_visible", "flatten", "format", "format_arg", "gnu_inline", "ifunc", "interrupt", "interrupt_handler", "leaf", "malloc", "no_icf", "no_instrument_function", "no_reorder", "no_sanitize_address", "no_address_safety_analysis", "no_sanitize_thread", "no_sanitize_undefined", "no_split_stack", "noclone", "noinline", "noreturn", "nothrow", "noplt", "optimize", "pure", "returns_nonnull", "returns_twice", "sentinel", "stack_protect", "target_clones", "target", "visibility", "warn_unused_result", "weakref", "lower", "upper", "either", "reentrant", "critical", "wakeup" };
        KNOWN_GCC_VARIABLE_ATTRIBUTES = new String[] { "cleanup", "common", "nocommon", "mode", "tls_model", "vector_size" };
        KNOWN_GCC_TYPE_ATTRIBUTES = new String[] { "aligned", "bnd_variable_size", "designated_init", "may_alias", "scalar_storage_order", "transparent_union", "visibility" };
        KNOWN_OTHER_ATTRIBUTES = new String[] { "ext_vector_type", "type_visibility", "unavailable" };
        final LinkedHashSet linkedHashSet = ContainerUtil.newLinkedHashSet();
        Collections.addAll(linkedHashSet, ClangFeatures.FEATURES_AND_EXTENSIONS);
        Collections.addAll(linkedHashSet, ClangFeatures.TYPE_TRAITS);
        ourFeatures = Collections.unmodifiableSet((Set<? extends String>)linkedHashSet);
        final LinkedHashSet linkedHashSet2 = ContainerUtil.newLinkedHashSet((Iterable)Arrays.asList(ClangFeatures.TYPE_TRAITS));
        final Iterator<String> iterator = OCExpressionEvaluator.BUILT_IN_TRAITS.keySet().iterator();
        while (iterator.hasNext()) {
            linkedHashSet2.remove(StringUtil.trimStart((String)iterator.next(), "__"));
        }
        final LinkedHashMap linkedHashMap = ContainerUtil.newLinkedHashMap();
        final LinkedHashMap linkedHashMap2 = ContainerUtil.newLinkedHashMap();
        for (final OCCompilerFeatures.Feature feature : OCCompilerFeatures.Feature.values()) {
            Label_2417: {
                try {
                    switch (feature) {
                        case MS_EXTENSIONS:
                        case GCC_AUTO_TYPE:
                        case BUILTIN_AVAILABLE: {
                            break Label_2417;
                        }
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final String lowerCase = StringUtil.toLowerCase(feature.name());
                OCLog.LOG.assertTrue(linkedHashSet.contains(lowerCase), (Object)("Clang's feature id doesn't exist for " + feature + " (expected id = " + lowerCase + ")\nPlease update ClangFeatures matcher."));
                linkedHashMap.put(feature, lowerCase);
                linkedHashMap2.put(lowerCase, feature);
                linkedHashSet2.remove(lowerCase);
            }
        }
        ourCompilerFeaturesToClangIds = Collections.unmodifiableMap((Map<? extends OCCompilerFeatures.Feature, ? extends String>)linkedHashMap);
        ourClangIdsToCompilerFeatures = Collections.unmodifiableMap((Map<? extends String, ? extends OCCompilerFeatures.Feature>)linkedHashMap2);
        ourUnsupportedClangFeatures = Collections.unmodifiableSet((Set<? extends String>)linkedHashSet2);
        final LinkedHashSet linkedHashSet3 = ContainerUtil.newLinkedHashSet();
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_OBJC_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_APPLE_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_GPU_REGISTER_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_FUNCTION_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_VARIABLE_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_TYPE_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_CALLING_CONVENTIONS_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_CONSUMED_ANNOTATION_CHECKING_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_TYPE_SAFETY_CHECKING_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_CLANG_TYPE_NULLABILITY_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_GCC_COMMON_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_GCC_WINDOWS_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_GCC_X86_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_GCC_FUNCTION_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_GCC_VARIABLE_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_GCC_TYPE_ATTRIBUTES);
        Collections.addAll(linkedHashSet3, ClangFeatures.KNOWN_OTHER_ATTRIBUTES);
        ourAttributes = Collections.unmodifiableSet((Set<? extends String>)linkedHashSet3);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
