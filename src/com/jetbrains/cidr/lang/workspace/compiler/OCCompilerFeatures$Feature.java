// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageStandard;

public enum Feature implements Type<Boolean>
{
    C_STATIC_ASSERT(OCLanguageStandard.C11), 
    CXX_EXCEPTIONS(OCLanguageStandard.CPP98), 
    CXX_AUTO_TYPE(OCLanguageStandard.CPP11), 
    CXX_OVERRIDE_CONTROL(OCLanguageStandard.CPP11), 
    CXX_GENERALIZED_INITIALIZERS(OCLanguageStandard.CPP11), 
    CXX_NULLPTR(OCLanguageStandard.CPP11), 
    CXX_NONSTATIC_MEMBER_INIT(OCLanguageStandard.CPP11), 
    CXX_CONSTEXPR(OCLanguageStandard.CPP11), 
    CXX_RAW_STRING_LITERALS(OCLanguageStandard.CPP11), 
    CXX_USER_LITERALS(OCLanguageStandard.CPP11), 
    CXX_BINARY_LITERALS(OCLanguageStandard.CPP14), 
    CXX_RETURN_TYPE_DEDUCTION(OCLanguageStandard.CPP14), 
    CXX_GENERIC_LAMBDAS(OCLanguageStandard.CPP14), 
    OBJC_ARC, 
    OBJC_DEFAULT_SYNTHESIZE_PROPERTIES, 
    OBJC_PROPERTY_EXPLICIT_ATOMIC, 
    OBJC_INSTANCETYPE, 
    OBJC_SUBSCRIPTING, 
    OBJC_ARRAY_LITERALS, 
    OBJC_DICTIONARY_LITERALS, 
    OBJC_CLASS_PROPERTY, 
    UNDERLYING_TYPE, 
    IS_BASE_OF, 
    NULLABILITY, 
    GCC_AUTO_TYPE, 
    BUILTIN_AVAILABLE, 
    MS_EXTENSIONS {
        @NotNull
        @Override
        public Boolean compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
            try {
                if (ocImmutableInclusionContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Feature$1", "compute"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            Boolean value;
            try {
                value = "1".equals(OCCompilerFeatures.access$000(ocImmutableInclusionContext, "_MSC_EXTENSIONS"));
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Feature$1", "compute"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return value;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    };
    
    @Nullable
    private final OCLanguageStandard myAvailableInStandard;
    
    private Feature() {
        this((OCLanguageStandard)null);
    }
    
    private Feature(final OCLanguageStandard myAvailableInStandard) {
        this.myAvailableInStandard = myAvailableInStandard;
    }
    
    @NotNull
    @Override
    public Boolean getDefault() {
        Boolean value = null;
        Label_0020: {
            try {
                if (this.a((OCImmutableInclusionContext)null) == Boolean.TRUE) {
                    final boolean b = true;
                    break Label_0020;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final boolean b = false;
            try {
                value = b;
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Feature", "getDefault"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return value;
    }
    
    @Nullable
    @Override
    public Boolean compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Feature", "compute"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.a(ocImmutableInclusionContext);
    }
    
    @Nullable
    private Boolean a(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext) {
        Label_0018: {
            try {
                if (this.myAvailableInStandard == null) {
                    return null;
                }
                final OCImmutableInclusionContext ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                if (ocImmutableInclusionContext2 == null) {
                    break Label_0018;
                }
                break Label_0018;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCImmutableInclusionContext ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                if (ocImmutableInclusionContext2 == null) {
                    final OCLanguageStandard ocLanguageStandard = OCCompilerFeatures.LANGUAGE_STANDARD.getDefault();
                    return ocLanguageStandard.supports(this.myAvailableInStandard);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCLanguageStandard ocLanguageStandard = OCCompilerFeatures.getFeatureForFile(null, ocImmutableInclusionContext, OCCompilerFeatures.LANGUAGE_STANDARD);
        return ocLanguageStandard.supports(this.myAvailableInStandard);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
