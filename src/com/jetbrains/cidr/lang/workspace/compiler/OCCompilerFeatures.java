// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.concurrent.ConcurrentHashMap;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import java.util.Map;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.OCLanguageStandard;

public class OCCompilerFeatures
{
    public static final Type<OCLanguageStandard> LANGUAGE_STANDARD;
    public static final short BIT_8 = 1;
    public static final short BIT_16 = 2;
    public static final short BIT_32 = 4;
    public static final short BIT_64 = 8;
    public static final short BIT_128 = 16;
    private static final Key<Map<Type, Object>> ENABLED_FEATURES;
    
    @NotNull
    public static <T> T getFeatureForFile(@Nullable final PsiFile p0, @Nullable final OCImmutableInclusionContext p1, @NotNull final Type<T> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "key"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getFeatureForFile"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_2        
        //    46: invokedynamic   apply:(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type;)Ljava/util/function/Function;
        //    51: astore_3       
        //    52: aload_3        
        //    53: aload_1        
        //    54: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //    59: astore          4
        //    61: aload           4
        //    63: ifnonnull       79
        //    66: aload_2        
        //    67: invokeinterface com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type.getDefault:()Ljava/lang/Object;
        //    72: goto            81
        //    75: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload           4
        //    81: dup            
        //    82: ifnonnull       119
        //    85: new             Ljava/lang/IllegalStateException;
        //    88: dup            
        //    89: ldc             "@NotNull method %s.%s must not return null"
        //    91: ldc             2
        //    93: anewarray       Ljava/lang/Object;
        //    96: dup            
        //    97: ldc             0
        //    99: ldc             "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures"
        //   101: aastore        
        //   102: dup            
        //   103: ldc             1
        //   105: ldc             "getFeatureForFile"
        //   107: aastore        
        //   108: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   111: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   114: athrow         
        //   115: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: areturn        
        //    Signature:
        //  <T:Ljava/lang/Object;>(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type<TT;>;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  61     75     75     79     Ljava/lang/IllegalArgumentException;
        //  81     115    115    119    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    @Nullable
    private static String a(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext, @NotNull final String s) {
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCMacroSymbol definition = ocImmutableInclusionContext.getDefinition(s);
        try {
            if (definition == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return definition.getSubstitution();
    }
    
    public static boolean isFeatureEnabled(@Nullable final PsiFile psiFile, @NotNull final Feature feature) {
        try {
            if (feature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "feature", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "isFeatureEnabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (getFeatureForFile(psiFile, null, (Type<Object>)feature) == Boolean.TRUE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public static boolean isFeatureEnabled(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext, @NotNull final Feature feature) {
        try {
            if (feature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "feature", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "isFeatureEnabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (getFeatureForFile(null, ocImmutableInclusionContext, (Type<Object>)feature) == Boolean.TRUE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @NotNull
    public static DiagnosticLevel getDiagnosticLevel(@Nullable final PsiFile psiFile, @NotNull final Diagnostic diagnostic) {
        try {
            if (diagnostic == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostic", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "getDiagnosticLevel"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        DiagnosticLevel diagnosticLevel;
        try {
            diagnosticLevel = getFeatureForFile(psiFile, null, (Type<DiagnosticLevel>)diagnostic);
            if (diagnosticLevel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "getDiagnosticLevel"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return diagnosticLevel;
    }
    
    public static boolean isArcDisabled(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "isArcDisabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (getFeatureForFile(psiFile, null, (Type<Object>)Feature.OBJC_ARC) == Boolean.FALSE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public static boolean isArcEnabled(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "isArcEnabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (getFeatureForFile(psiFile, null, (Type<Object>)Feature.OBJC_ARC) == Boolean.TRUE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public static boolean supportsIvarsInImplementation() {
        return true;
    }
    
    public static boolean supportsIvarsInCategories() {
        return true;
    }
    
    public static boolean supportsLaterMethodDeclaration() {
        return true;
    }
    
    public static boolean supportsAutosynthesis(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.OBJC_DEFAULT_SYNTHESIZE_PROPERTIES);
    }
    
    public static boolean supportsAutosynthesis(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext) {
        return isFeatureEnabled(ocImmutableInclusionContext, Feature.OBJC_DEFAULT_SYNTHESIZE_PROPERTIES);
    }
    
    public static boolean supportsNullability(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.NULLABILITY);
    }
    
    public static boolean supportsNullability(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext) {
        return isFeatureEnabled(ocImmutableInclusionContext, Feature.NULLABILITY);
    }
    
    public static boolean supportsGccAutoType(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.GCC_AUTO_TYPE);
    }
    
    public static boolean supportsGccAutoType(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext) {
        return isFeatureEnabled(ocImmutableInclusionContext, Feature.GCC_AUTO_TYPE);
    }
    
    public static boolean supportsAvailableExpression(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.BUILTIN_AVAILABLE);
    }
    
    public static boolean supportsAvailableExpression(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext) {
        return isFeatureEnabled(ocImmutableInclusionContext, Feature.BUILTIN_AVAILABLE);
    }
    
    public static boolean supportsClassProperty(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.OBJC_CLASS_PROPERTY);
    }
    
    public static boolean supportsClassProperty(@Nullable final OCImmutableInclusionContext ocImmutableInclusionContext) {
        return isFeatureEnabled(ocImmutableInclusionContext, Feature.OBJC_CLASS_PROPERTY);
    }
    
    public static boolean supportsMicrosoftAttributes(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.MS_EXTENSIONS);
    }
    
    public static boolean supportsExplicitAtomic(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.OBJC_PROPERTY_EXPLICIT_ATOMIC);
    }
    
    public static boolean supportsInstancetype(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.OBJC_INSTANCETYPE);
    }
    
    public static boolean supportsSubscripting(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.OBJC_SUBSCRIPTING);
    }
    
    public static boolean supportsObjectLiterals(@Nullable final PsiFile psiFile) {
        Label_0027: {
            try {
                if (!isFeatureEnabled(psiFile, Feature.OBJC_ARRAY_LITERALS)) {
                    return false;
                }
                final PsiFile psiFile2 = psiFile;
                final Feature feature = Feature.OBJC_DICTIONARY_LITERALS;
                final boolean b = isFeatureEnabled(psiFile2, feature);
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final Feature feature = Feature.OBJC_DICTIONARY_LITERALS;
                final boolean b = isFeatureEnabled(psiFile2, feature);
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
    
    public static boolean supportsCxxAutoType(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_AUTO_TYPE);
    }
    
    public static boolean supportsCxxReturnTypeDeduction(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_RETURN_TYPE_DEDUCTION);
    }
    
    public static boolean supportsCxxGenericLambdas(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_GENERIC_LAMBDAS);
    }
    
    public static boolean supportsOverrideControl(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_OVERRIDE_CONTROL);
    }
    
    public static boolean supportsInitializerLists(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_GENERALIZED_INITIALIZERS);
    }
    
    public static boolean supportsNullptr(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_NULLPTR);
    }
    
    public static boolean supportsInClassInitialization(@Nullable final PsiFile psiFile) {
        return isFeatureEnabled(psiFile, Feature.CXX_NONSTATIC_MEMBER_INIT);
    }
    
    @Nullable
    private static <T> T a(@NotNull final Project project, @NotNull final Type<T> type) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "getForTests"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (type == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "getForTests"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Map map = (Map)OCCompilerFeatures.ENABLED_FEATURES.get((UserDataHolder)project);
        try {
            if (map == null) {
                final Object value = null;
                return (T)value;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Object value = map.get(type);
        return (T)value;
    }
    
    public static <T> void set(@NotNull final Project project, final Type<T> type, final T t) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "set"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Map<Type<T>, T> map = (Map<Type<T>, T>)OCCompilerFeatures.ENABLED_FEATURES.get((UserDataHolder)project);
        if (map == null) {
            map = new ConcurrentHashMap<Type<T>, T>();
            project.putUserData((Key)OCCompilerFeatures.ENABLED_FEATURES, (Object)map);
        }
        map.put(type, t);
    }
    
    public static void resetFeatures(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures", "resetFeatures"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        project.putUserData((Key)OCCompilerFeatures.ENABLED_FEATURES, (Object)null);
    }
    
    static {
        LANGUAGE_STANDARD = new Type<OCLanguageStandard>() {
            @Override
            public String toString() {
                return "LANGUAGE_STANDARD";
            }
            
            @NotNull
            @Override
            public OCLanguageStandard getDefault() {
                OCLanguageStandard cpp98;
                try {
                    cpp98 = OCLanguageStandard.CPP98;
                    if (cpp98 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "getDefault"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return cpp98;
            }
            
            @NotNull
            @Override
            public OCLanguageStandard compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
                try {
                    if (ocImmutableInclusionContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "compute"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                OCLanguageStandard cStandard = null;
                Label_0113: {
                    OCLanguageStandard ocLanguageStandard = null;
                    Label_0078: {
                        try {
                            if (!ocImmutableInclusionContext.getLanguageKind().isCpp()) {
                                break Label_0113;
                            }
                            final OCImmutableInclusionContext ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                            final String s = "__cplusplus";
                            final String s2 = a(ocImmutableInclusionContext2, s);
                            ocLanguageStandard = OCLanguageStandard.getCppStandard(s2);
                            if (ocLanguageStandard == null) {
                                break Label_0078;
                            }
                            return ocLanguageStandard;
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCImmutableInclusionContext ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                            final String s = "__cplusplus";
                            final String s2 = a(ocImmutableInclusionContext2, s);
                            ocLanguageStandard = OCLanguageStandard.getCppStandard(s2);
                            if (ocLanguageStandard == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "compute"));
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                    }
                    return ocLanguageStandard;
                    try {
                        cStandard = OCLanguageStandard.getCStandard(a(ocImmutableInclusionContext, "__STDC_VERSION__"));
                        if (cStandard == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "compute"));
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
                return cStandard;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
        ENABLED_FEATURES = Key.create("ENABLED_FEATURES");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public interface Type<T>
    {
        @NotNull
        T getDefault();
        
        @Nullable
        default T compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
            try {
                if (ocImmutableInclusionContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type", "compute"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return null;
        }
        
        default IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
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
                    value = "1".equals(a(ocImmutableInclusionContext, "_MSC_EXTENSIONS"));
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
    
    public enum TypeSize implements Type<Short>
    {
        BOOL((short)1), 
        CHAR((short)1), 
        CHAR16_T((short)2), 
        CHAR32_T((short)4), 
        WCHAR_T((short)4), 
        SHORT((short)2), 
        INT((short)4), 
        LONG((short)4), 
        SIZE_T(TypeSize.INT), 
        PTRDIFF_T(TypeSize.SIZE_T), 
        POINTER(TypeSize.SIZE_T), 
        LONG_LONG((short)8), 
        INT128_T((short)16), 
        FLOAT((short)4), 
        DOUBLE((short)8), 
        LONG_DOUBLE((short)8);
        
        @Nullable
        private final TypeSize myBaseType;
        private final short myDefault;
        
        private TypeSize(final short myDefault) {
            this.myBaseType = null;
            this.myDefault = myDefault;
        }
        
        private TypeSize(final TypeSize myBaseType) {
            if (myBaseType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseType", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$TypeSize", "<init>"));
            }
            super(s, n);
            this.myBaseType = myBaseType;
            this.myDefault = this.myBaseType.getDefault();
        }
        
        @NotNull
        @Override
        public Short getDefault() {
            Short value;
            try {
                value = this.myDefault;
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$TypeSize", "getDefault"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return value;
        }
        
        @Nullable
        @Override
        public Short compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
            try {
                if (ocImmutableInclusionContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$TypeSize", "compute"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (this.myBaseType != null) {
                    return ocImmutableInclusionContext.getCompilerFeature((Type<Short>)this.myBaseType);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return null;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum DiagnosticLevel
    {
        DISABLED, 
        WARNING, 
        ERROR;
        
        public boolean isEnabled() {
            return this != DiagnosticLevel.DISABLED;
        }
    }
    
    public enum Diagnostic implements Type<DiagnosticLevel>
    {
        MISSING_RETURN_FROM_NON_VOID(DiagnosticLevel.WARNING), 
        FOLDING_CONSTANT(DiagnosticLevel.ERROR);
        
        @NotNull
        private final DiagnosticLevel myDefault;
        
        private Diagnostic(final DiagnosticLevel myDefault) {
            if (myDefault == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "def", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic", "<init>"));
            }
            super(s, n);
            this.myDefault = myDefault;
        }
        
        @NotNull
        @Override
        public DiagnosticLevel getDefault() {
            DiagnosticLevel myDefault;
            try {
                myDefault = this.myDefault;
                if (myDefault == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic", "getDefault"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return myDefault;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
