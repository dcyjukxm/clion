// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.codeStyle.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public class OCInspections
{
    abstract static class GeneralCpp extends Cpp
    {
        @Nls
        @NotNull
        @Override
        public String getGroupDisplayName() {
            String s;
            try {
                s = "General";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$GeneralCpp", "getGroupDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            return s;
        }
        
        private static IllegalStateException c(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class FormatSpecifiers extends GeneralCpp
    {
    }
    
    public static class ResourceNotFoundInspection extends GeneralCpp
    {
    }
    
    public static class LocalValueEscapesScope extends GeneralCpp
    {
    }
    
    public static class ConstructionIsNotAllowed extends GeneralCpp implements Hidden
    {
    }
    
    public static class InitializerIssues extends GeneralCpp implements Hidden
    {
    }
    
    public static class DuplicateSwitchCase extends GeneralCpp
    {
    }
    
    public static class MissingSwitchCase extends GeneralCpp
    {
    }
    
    public static class DeprecatedAPI extends GeneralCpp
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Usage of deprecated API";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$DeprecatedAPI", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class EqualityInConditionalOperator extends GeneralCpp
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "'=' in conditional expression";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$EqualityInConditionalOperator", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    private static class Functions extends Cpp
    {
    }
    
    public static class FunctionParameterCountMismatch extends Functions implements Hidden
    {
    }
    
    public static class KRUnspecifiedParameters extends Functions
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Passing arguments to function with K&R unspecified parameters syntax";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$KRUnspecifiedParameters", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            return s;
        }
        
        private static IllegalStateException c(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class NotImplementedFunctions extends Functions
    {
    }
    
    public static class HidingNonVirtualFunction extends Functions
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Hiding non-virtual function";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$HidingNonVirtualFunction", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            return s;
        }
        
        private static IllegalStateException c(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class DerivedFunctionsReturnTypeMismatch extends Functions implements Hidden
    {
    }
    
    public static class NoDefaultBaseConstructor extends Functions implements Hidden
    {
    }
    
    public static class FieldMustBeInitialized extends Functions implements Hidden
    {
    }
    
    public abstract static class DataFlowAnalysis extends Cpp
    {
    }
    
    public static class UnreachableCode extends DataFlowAnalysis
    {
    }
    
    public static class EndlessLoop extends DataFlowAnalysis
    {
    }
    
    public static class InfiniteRecursion extends DataFlowAnalysis
    {
    }
    
    public static class NotInitializedVariable extends DataFlowAnalysis
    {
    }
    
    public static class MissingReturn extends DataFlowAnalysis
    {
    }
    
    public static class UnusedImportStatement extends OCUnusedCodeInspection
    {
    }
    
    public static class UnusedLocalVariable extends OCUnusedCodeInspection
    {
    }
    
    public static class UnusedParameter extends OCUnusedCodeInspection
    {
    }
    
    public static class UnusedValue extends OCUnusedCodeInspection
    {
    }
    
    public static class UnusedExpressionResult extends OCUnusedCodeInspection
    {
    }
    
    public static class UnusedLocalization extends OCUnusedCodeInspection
    {
    }
    
    private static class TypeChecks extends Cpp
    {
    }
    
    public static class RedundantCast extends TypeChecks
    {
    }
    
    public static class SignednessMismatch extends TypeChecks
    {
    }
    
    public static class ValueMayNotFitIntoReceiver extends TypeChecks
    {
    }
    
    public static class ImplicitPointerAndIntegerConversion extends TypeChecks
    {
    }
    
    public static class IncompatiblePointers extends TypeChecks
    {
    }
    
    public static class IncompatibleInitializers extends TypeChecks
    {
    }
    
    public static class IncompatibleEnums extends TypeChecks
    {
    }
    
    public static class ImplicitIntegerAndEnumConversion extends TypeChecks
    {
    }
    
    public static class NotSuperclass extends TypeChecks
    {
    }
    
    public static class IncompatibleTypes extends TypeChecks implements Hidden
    {
    }
    
    public static class NotAssignable extends TypeChecks implements Hidden
    {
    }
    
    public static class ScalarTypeRequired extends TypeChecks implements Hidden
    {
    }
    
    public static class PointerTypeRequired extends TypeChecks implements Hidden
    {
    }
    
    public static class IntegerTypeRequired extends TypeChecks implements Hidden
    {
    }
    
    public static class ArrayIssues extends TypeChecks implements Hidden
    {
    }
    
    public static class ConstExpressionRequired extends TypeChecks implements Hidden
    {
    }
    
    public static class TemplateArgumentsIssues extends TypeChecks implements Hidden
    {
    }
    
    private static class DeclarationOrder extends Cpp
    {
    }
    
    public static class HidesUpperScope extends DeclarationOrder
    {
    }
    
    public static class FunctionImplicitDeclarationInspection extends DeclarationOrder
    {
    }
    
    public static class DuplicateDeclarations extends DeclarationOrder implements Hidden
    {
    }
    
    public static class CannotResolve extends DeclarationOrder implements Hidden
    {
    }
    
    public static class MemberVisibility extends DeclarationOrder implements Hidden
    {
    }
    
    public static class StaticnessMismatch extends DeclarationOrder implements Hidden
    {
    }
    
    abstract static class GeneralObjC extends ObjC
    {
        @Nls
        @NotNull
        @Override
        public String getGroupDisplayName() {
            String s;
            try {
                s = "General";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$GeneralObjC", "getGroupDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            return s;
        }
        
        private static IllegalStateException c(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class ImplicitDeclarationOfIvar extends GeneralObjC
    {
        @Override
        public boolean isEnabledByDefault() {
            return false;
        }
    }
    
    public static class NotReleasedValue extends GeneralObjC
    {
    }
    
    public static class KeyValueCodingInspection extends GeneralObjC
    {
    }
    
    public static class StringLocalizationInspection extends GeneralObjC
    {
    }
    
    public static class UsingZeroAsNil extends GeneralObjC
    {
    }
    
    public static class AlwaysNilVariable extends GeneralObjC
    {
    }
    
    public static class NotImplementsProtocol extends GeneralObjC
    {
    }
    
    public static class BridgeCastIssues extends GeneralObjC implements Hidden
    {
    }
    
    public static class ARCIssues extends GeneralObjC implements Hidden
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "ARC Issues";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ARCIssues", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class ObsoleteNSLiteral extends GeneralObjC
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Collection literal syntax is not used";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ObsoleteNSLiteral", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class CallDealloc extends GeneralObjC
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Sending 'dealloc'";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$CallDealloc", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class UnavailableInDeploymentTarget extends GeneralObjC
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Usage of API unavailable for the deployment target";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$UnavailableInDeploymentTarget", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class EqualityInConditionalOperatorWithSelf extends GeneralObjC
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "'=' in conditional expression with \"self\"";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$EqualityInConditionalOperatorWithSelf", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        @Override
        public boolean isEnabledByDefault() {
            return false;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    private static class MessageResolution extends ObjC
    {
    }
    
    public static class UnresolvedMessage extends MessageResolution
    {
    }
    
    public static class UnresolvedCollectionMessage extends MessageResolution
    {
    }
    
    public static class NotInHierarchyMessage extends MessageResolution
    {
    }
    
    public static class SeveralTargetsMessage extends MessageResolution
    {
        @Override
        public boolean isEnabledByDefault() {
            return false;
        }
    }
    
    private static class Methods extends ObjC
    {
    }
    
    public static class AssociatedTypeMismatch extends Methods
    {
    }
    
    public static class OverriddenTypeMismatch extends Methods
    {
    }
    
    public static class LastArgumentMustBeNull extends Methods
    {
    }
    
    public static class MethodParameterCountMismatch extends Methods implements Hidden
    {
    }
    
    public static class MethodIsLaterInTheScope extends Methods
    {
    }
    
    private static class Classes extends ObjC
    {
    }
    
    public static class InterfaceHasNoImplementation extends Classes
    {
    }
    
    public static class ImplementationHasNoInterface extends Classes
    {
    }
    
    public static class NoClassDefinition extends Classes
    {
    }
    
    public static class NotImplementedMethods extends Classes
    {
    }
    
    public static class NotVisibleClass extends Classes
    {
    }
    
    public static class PrivateCategoryShouldBeNearImplementation extends Classes
    {
    }
    
    public static class HidesClassScope extends Classes
    {
    }
    
    private static class Properties extends ObjC
    {
    }
    
    public static class NoAttributeForProperty extends Properties
    {
    }
    
    public static class DuplicateAttribute extends Properties
    {
    }
    
    public static class NoGetterOrSetter extends Properties
    {
    }
    
    public static class SetterForReadonlyProperty extends Properties
    {
    }
    
    public static class AccessorsWereOverridden extends Properties
    {
    }
    
    public static class PropertyAndIvarTypeMismatch extends Properties
    {
    }
    
    public static class OverriddenAttributeMismatch extends Properties
    {
    }
    
    public static class ReleasingOfAssignProperties extends Properties
    {
    }
    
    public abstract static class ClangCompilerIssues extends ObjC
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            final String[] nameToWords = NameUtil.nameToWords(this.getClass().getSimpleName());
            String join;
            try {
                join = StringUtil.join(nameToWords, " ");
                if (join == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ClangCompilerIssues", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            return join;
        }
        
        private static IllegalStateException c(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class EmptyCategory extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class InlineAssemblyIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class DocumentationIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ParseIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class SemanticIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ModulesIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class CocoaAPIIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class LambdaIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class RelatedResultTypeIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class InstrumentationIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ASTDeserializationIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class NullabilityIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class GenericsIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCParseIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCSemanticIssue extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCWeakReferences extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCRestrictions extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCRetainCycle extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCCastingRules extends ClangCompilerIssues implements Hidden
    {
    }
    
    public static class ARCAndProperties extends ClangCompilerIssues implements Hidden
    {
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "ARC and @properties";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ARCAndProperties", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class LexicalOrPreprocessorIssue extends ClangCompilerIssues implements Hidden
    {
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Lexical or Preprocessor Issue";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$LexicalOrPreprocessorIssue", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class APINotesIssue extends ClangCompilerIssues implements Hidden
    {
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "API Notes Issue";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$APINotesIssue", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class OpenMPIssue extends ClangCompilerIssues implements Hidden
    {
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "OpenMP Issue";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$OpenMPIssue", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class VTableABIIssue extends ClangCompilerIssues implements Hidden
    {
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "VTable ABI Issue";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$VTableABIIssue", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class CoroutinesIssue extends ClangCompilerIssues implements Hidden
    {
        @NotNull
        @Override
        public String getDisplayName() {
            String s;
            try {
                s = "Coroutines Issue";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$CoroutinesIssue", "getDisplayName"));
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            return s;
        }
        
        private static IllegalStateException d(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public abstract static class ClangAnalyzerIssue extends ObjC implements Hidden
    {
        @Nls
        @NotNull
        @Override
        public String getDisplayName() {
            final String fakeInspectionName = OCInspectionToolProvider.getInstance().getFakeInspectionName(this.getClass());
            String s = null;
            Label_0027: {
                try {
                    if (fakeInspectionName != null) {
                        final String displayName;
                        s = (displayName = fakeInspectionName);
                        break Label_0027;
                    }
                }
                catch (IllegalStateException ex) {
                    throw c(ex);
                }
                String displayName;
                s = (displayName = super.getDisplayName());
                try {
                    if (displayName == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$ClangAnalyzerIssue", "getDisplayName"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw c(ex2);
                }
            }
            return s;
        }
        
        private static IllegalStateException c(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class FakeIssue0 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue1 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue2 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue3 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue4 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue5 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue6 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue7 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue8 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue9 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue10 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue11 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue12 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue13 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue14 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue15 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue16 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue17 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue18 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue19 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue20 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue21 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue22 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue23 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue24 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue25 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue26 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue27 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue28 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue29 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public static class FakeIssue30 extends ClangAnalyzerIssue implements Hidden
    {
    }
    
    public interface Hidden
    {
    }
}
