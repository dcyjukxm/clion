// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.psi.OCCommaExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.patterns.ObjectPattern;
import com.intellij.codeInsight.completion.CompletionData;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.TailTypeDecorator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.util.Conditions;
import com.intellij.psi.PsiComment;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.lang.psi.OCCppTypeidExpression;
import com.jetbrains.cidr.lang.psi.OCSizeofExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import java.util.Collections;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.util.Collection;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.psi.OCInterface;
import java.util.Iterator;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.AddSpaceInsertHandler;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.completion.CompletionResultSet;
import java.util.List;
import com.jetbrains.cidr.lang.psi.impl.OCObjCErrorKeywordImpl;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.intellij.psi.PsiErrorElement;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.util.ProcessingContext;
import com.intellij.patterns.PatternCondition;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.patterns.ElementPattern;
import com.intellij.openapi.util.Condition;

public class OCKeywordCompletionContributor extends OCCompletionContributorBase
{
    public static final LookupDecorator TYPE_ATTRIBUTE_DECORATOR;
    public static final String[] ALTERNATIVE_TOKENS_UNARY;
    public static final String[] ALTERNATIVE_TOKENS;
    public static final String KEYWORD_ALIGNAS = "alignas(<caret>)";
    public static final String KEYWORD_ASM = "asm(<caret>)";
    public static final String KEYWORD_NAMESPACE = "namespace ";
    public static final String KEYWORD_EXTERN = "extern ";
    private static Condition<OCFilteredCompletionProvider.Filter> NULLABILITY_FEATURE_ENABLED_FILTER;
    private static Condition<OCFilteredCompletionProvider.Filter> GCC_AUTO_TYPE_FEATURE_ENABLED_FILTER;
    private static Condition<OCFilteredCompletionProvider.Filter> CLASS_PROPERTY_FEATURE_ENABLED_FILTER;
    public static final ElementPattern HAS_FEATURE_BUILTIN_AVAILABLE;
    public static final PsiElementPattern.Capture<PsiElement> AT_EXPRESSION_LEVEL;
    public static final PsiElementPattern.Capture<PsiElement> AT_EXPRESSION_LEVEL_CPP;
    
    private static ElementPattern a(@NotNull final OCCompilerFeatures.Feature feature) {
        try {
            if (feature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "feature", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "hasFeature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (ElementPattern)OCCompletionPatterns.ELEMENT.with((PatternCondition)new PatternCondition<PsiElement>("HAS_FEATURE_" + feature.name()) {
            public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$2", "accepts"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return OCCompilerFeatures.isFeatureEnabled(psiElement.getContainingFile(), feature);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    public OCKeywordCompletionContributor() {
        this.a();
        this.i();
        this.k();
        this.m();
        this.e();
        this.q();
        this.r();
        this.j();
        this.g();
        this.h();
        this.c();
        this.t();
        this.d();
        this.l();
        this.s();
        this.b();
        this.f();
        this.n();
        this.p();
    }
    
    private void f() {
        final PsiElementPattern.Capture capture = (PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCFunctionDeclaration.class).withLastChild((ElementPattern)PlatformPatterns.psiElement((Class)PsiErrorElement.class));
        this.a((PsiElementPattern.Capture<? extends OCFunctionDeclaration>)capture, "!noexcept ", "!throw(<caret>)", "!try ");
        this.a((PsiElementPattern.Capture<? extends OCFunctionDeclaration>)capture.withParent((ElementPattern)PlatformPatterns.psiElement((Class)OCStruct.class)), "!final ", "!override ");
        this.a((PsiElementPattern.Capture<? extends OCFunctionDeclaration>)PlatformPatterns.psiElement((Class)OCFunctionDefinition.class).withChild((ElementPattern)PlatformPatterns.psiElement((Class)OCDeclarator.class).with((PatternCondition)new PatternCondition<OCDeclarator>("function-try-block") {
            public boolean accepts(@NotNull final OCDeclarator ocDeclarator, final ProcessingContext processingContext) {
                try {
                    if (ocDeclarator == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$3", "accepts"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (TreeUtil.findChildBackward(ocDeclarator.getNode(), OCTokenTypes.TRY_KEYWORD) != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return false;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        })), "!catch");
    }
    
    private void a(final PsiElementPattern.Capture<? extends OCFunctionDeclaration> capture, final String... array) {
        this.b((PsiElementPattern.Capture<PsiElement>)((PsiElementPattern.Capture)OCCompletionPatterns.TYPE_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN)).withSuperParent(2, (ElementPattern)PlatformPatterns.psiElement((Class)OCDeclaration.class).afterSiblingSkipping((ElementPattern)PlatformPatterns.psiElement().withElementType(OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET), (ElementPattern)capture)), array);
    }
    
    private void b() {
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.REFERENCE_ELEMENT.withSuperParent(2, (Class)OCCppBaseClause.class), "!public ", "!protected ", "!private ", "!virtual ");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.REFERENCE_ELEMENT.and((ElementPattern)OCCompletionPatterns.AT_CLASS_FUNCTION_BODY_PLACEHOLDER), "!0", "!default", "!delete");
    }
    
    private void d() {
        this.b((PsiElementPattern.Capture<PsiElement>)PlatformPatterns.psiElement().and((ElementPattern)OCCompletionPatterns.ELSE_POSITION), "!else");
    }
    
    private void l() {
        this.b(OCCompletionPatterns.AT_STATEMENT_LEVEL, "switch", "goto ", "while", "for", "if", "do", "asm(<caret>)");
        this.a(OCCompletionPatterns.AT_STATEMENT_LEVEL, o());
        this.b(OCCompletionPatterns.AT_STATEMENT_LEVEL, "_Static_assert(<caret>)");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.AT_STATEMENT_LEVEL.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), ArrayUtil.mergeArrays(OCKeywordCompletionContributor.ALTERNATIVE_TOKENS_UNARY, new String[] { "static_assert(<caret>)", "try {\n<caret>\n}\n", "throw ", "using " }));
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.AT_STATEMENT_LEVEL.and(OCKeywordCompletionContributor.HAS_FEATURE_BUILTIN_AVAILABLE), "__builtin_available(<caret>*)");
        this.a(OCCompletionPatterns.AT_STATEMENT_LEVEL, (ElementPattern<PsiElement>)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withParent((Class)OCBlockStatement.class)).and((ElementPattern)OCCompletionPatterns.like((Class<? extends PsiElement>)OCObjCErrorKeywordImpl.class)), 1, "try {\n<caret>\n}\n", "autoreleasepool {\n<caret>\n}\n", "throw ", "synchronized(<#lock#>) {\n}", "selector(<caret>)", "protocol(<caret>)");
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.AT_STATEMENT_LEVEL.and(OCKeywordCompletionContributor.HAS_FEATURE_BUILTIN_AVAILABLE), (ElementPattern<PsiElement>)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withParent((Class)OCBlockStatement.class)).and((ElementPattern)OCCompletionPatterns.like((Class<? extends PsiElement>)OCObjCErrorKeywordImpl.class)), 1, "available(<caret>*)");
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.OBJC_ERROR_KEYWORD.andOr(new ElementPattern[] { PlatformPatterns.psiElement().withSuperParent(2, (Class)OCDeclarator.class), PlatformPatterns.psiElement().inside((Class)OCBlockStatement.class) }), a(null, Character.valueOf('@'), a(new String[] { "selector(<caret>)" })));
        this.a((PsiElementPattern.Capture<PsiElement>)((PsiElementPattern.Capture)OCCompletionPatterns.OBJC_ERROR_KEYWORD.and(OCKeywordCompletionContributor.HAS_FEATURE_BUILTIN_AVAILABLE)).andOr(new ElementPattern[] { PlatformPatterns.psiElement().withSuperParent(2, (Class)OCDeclarator.class), PlatformPatterns.psiElement().inside((Class)OCBlockStatement.class) }), a(null, Character.valueOf('@'), a(new String[] { "available(<caret>*)" })));
    }
    
    private void s() {
        this.b(OCKeywordCompletionContributor.AT_EXPRESSION_LEVEL, "sizeof(<caret>)", "typeof(<caret>)");
        this.b(OCKeywordCompletionContributor.AT_EXPRESSION_LEVEL, "_Alignof(<caret>)", "_Generic(<caret>)");
        this.b(OCKeywordCompletionContributor.AT_EXPRESSION_LEVEL_CPP, OCKeywordCompletionContributor.ALTERNATIVE_TOKENS);
        this.b(OCKeywordCompletionContributor.AT_EXPRESSION_LEVEL_CPP, "alignof(<caret>)", "noexcept(<caret>)", "new ", "nullptr", "true", "delete ", "false", "const_cast<<caret>>", "dynamic_cast<<caret>>", "reinterpret_cast<<caret>>", "static_cast<<caret>>", "typeid(<caret>)");
    }
    
    private void g() {
        this.b(OCCompletionPatterns.IN_LOOP, "!break", "!continue");
    }
    
    private void h() {
        this.b(OCCompletionPatterns.IN_FOR, "!in ");
    }
    
    private void c() {
        this.b(OCCompletionPatterns.IN_SWITCH, "!break", "!case <caret>:", "!default:");
    }
    
    private void t() {
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.AT_STATEMENT_LEVEL.and((ElementPattern)OCCompletionPatterns.afterTry(false)), (ElementPattern<PsiElement>)PlatformPatterns.and(new ElementPattern[] { OCCompletionPatterns.like((Class<? extends PsiElement>)OCObjCErrorKeywordImpl.class), OCCompletionPatterns.afterTry(false) }), 1, "!finally", "!catch");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.AT_STATEMENT_LEVEL.and((ElementPattern)OCCompletionPatterns.afterTry(true)), "!catch");
    }
    
    private void n() {
        this.b(OCCompletionPatterns.IN_GENERIC_ASSOCIATION, "default:");
    }
    
    private void p() {
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.IN_AVAILABILITY_EXPRESSION.andNot((ElementPattern)OCCompletionPatterns.ELEMENT.afterLeaf((ElementPattern)OCCompletionPatterns.IDENTIFIER)), new OCCompletionProvider() {
            final /* synthetic */ List val$keywords = a(new String[] { "macOS", "?macOSApplicationExtension", "iOS", "?iOSApplicationExtension", "tvOS", "?tvOSApplicationExtension", "watchOS", "?watchOSApplicationExtension" });
            
            @Override
            protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$4", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                for (final Keyword keyword : this.val$keywords) {
                    set.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)LookupElementBuilder.create(keyword.lookUpString).withInsertHandler((InsertHandler)AddSpaceInsertHandler.INSTANCE), keyword.priority));
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    private void a(@NotNull final Class<? extends PsiElement> clazz, final String... array) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentClass", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "registerDeclarationProviderWithAt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.withSuperParent(3, (ElementPattern)OCCompletionPatterns.like(clazz)), OCCompletionPatterns.like(clazz), 2, array);
    }
    
    private void q() {
        this.a((Class<? extends PsiElement>)OCInterface.class, "!property ", "!end");
    }
    
    private void r() {
        this.a((Class<? extends PsiElement>)OCInstanceVariablesList.class, "public ", "protected ", "private ", "package ");
    }
    
    private void e() {
        this.a((Class<? extends PsiElement>)OCProtocol.class, "!optional ", "!required ", "!end");
    }
    
    private void m() {
        this.a((Class<? extends PsiElement>)OCImplementation.class, "synthesize ", "dynamic ", "!end");
    }
    
    private void k() {
        final String[] array = { "volatile ", "void ", "char ", "!int ", "signed ", "unsigned ", "float ", "double ", "short ", "long ", "enum ", "struct ", "union ", "static ", "const " };
        final String[] array2 = { "_Atomic ", "_Bool ", "_Complex ", "_Imaginary " };
        final String[] array3 = { "!__block ", "!__strong ", "!__weak ", "!__unsafe_unretained " };
        this.b(OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION, ArrayUtil.mergeArrays(array, new String[] { "auto ", "typedef ", "register " }));
        this.b(OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION, array2);
        this.a(OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION, filtered(a(null, null, ContainerUtil.list((Object[])new Keyword[] { a("__auto_type", false) })), OCKeywordCompletionContributor.GCC_AUTO_TYPE_FEATURE_ENABLED_FILTER));
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN), ArrayUtil.mergeArrays(array3, new String[] { "!__autoreleasing " }));
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN), filtered(a(null, null, ContainerUtil.list((Object[])new Keyword[] { a(OCTokenTypes._NULLABLE_KEYWORD.getName(), true), a(OCTokenTypes._NONNULL_KEYWORD.getName(), true), a(OCTokenTypes._NULL_UNSPECIFIED_KEYWORD.getName(), true), a("__nullable", false), a("__nonnull", false), a("__null_unspecified", false) })), OCKeywordCompletionContributor.NULLABILITY_FEATURE_ENABLED_FILTER));
        final PsiElementPattern.Capture capture = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.withSuperParent(2, (Class)OCTypeElement.class)).with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN)).andOr(new ElementPattern[] { OCCompletionPatterns.ELEMENT.withSuperParent(3, (Class)OCMethod.class), OCCompletionPatterns.ELEMENT.withSuperParent(3, (Class)OCMethodSelectorPart.class) });
        this.a((PsiElementPattern.Capture<PsiElement>)capture, a(null, null, ContainerUtil.map((Collection)OCElementTypes.PARAMETER_TYPE_QUALIFIERS, s -> a(s, true))));
        this.a((PsiElementPattern.Capture<PsiElement>)capture, filtered(a(null, null, ContainerUtil.map((Collection)OCElementTypes.PARAMETER_TYPE_NULLABILITY_QUALIFIERS, s -> a(s, true))), OCKeywordCompletionContributor.NULLABILITY_FEATURE_ENABLED_FILTER));
        this.b((PsiElementPattern.Capture<PsiElement>)((PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN)).withSuperParent(3, (Class)OCMethod.class)).with((PatternCondition)new PatternCondition<PsiElement>("instancetype compatibility check") {
            public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$5", "accepts"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return OCCompilerFeatures.supportsInstancetype(psiElement.getContainingFile());
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }), "instancetype");
        final String[] array4 = { "bool ", "char16_t ", "char32_t ", "class ", "decltype(<caret>)", "wchar_t " };
        this.b(OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION, "_Alignas(<caret>)", "_Noreturn ", "_Thread_local ");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), ArrayUtil.mergeArrays(array4, new String[] { "alignas(<caret>)", "constexpr ", "template ", "thread_local", "typename ", "using " }));
        this.b(OCCompletionPatterns.SIMPLE_TYPE_IN_CAST, array);
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_CAST.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN), ArrayUtil.mergeArrays(array3, new String[] { "!__bridge ", "!__bridge_retained ", "!__bridge_transfer " }));
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_CAST.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), array4);
        final PsiElementPattern.Capture capture2 = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.NAME_IN_DECLARATION.andNot((ElementPattern)OCCompletionPatterns.AFTER_TILDE)).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT);
        this.b((PsiElementPattern.Capture<PsiElement>)capture2, "const ", "_Atomic ", "_Complex ", "_Imaginary ", "_Thread_local ", "_Noreturn ", "_Alignas(<caret>)");
        this.b((PsiElementPattern.Capture<PsiElement>)((PsiElementPattern.Capture)capture2.with((PatternCondition)OCCompletionPatterns.C_OR_OBJC_ELEMENT_PATTERN)).afterLeaf(new String[] { "*" }), "restrict ");
        final PsiElementPattern.Capture capture3 = (PsiElementPattern.Capture)capture2.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN);
        this.b((PsiElementPattern.Capture<PsiElement>)capture3.withParent((ElementPattern)PlatformPatterns.psiElement().afterSiblingSkipping((ElementPattern)OCCompletionPatterns.WHITESPACES_AND_COMMENTS, (ElementPattern)PlatformPatterns.psiElement((Class)OCTypeElement.class).withChild((ElementPattern)PlatformPatterns.psiElement((Class)OCStruct.class)))), "final");
        this.a((PsiElementPattern.Capture<PsiElement>)capture3.inside(true, (ElementPattern)PlatformPatterns.instanceOf((Class)OCStruct.class), (ElementPattern)PlatformPatterns.psiElement((Class)OCBlockStatement.class)), a(null, null, Collections.singletonList(new Keyword("operator", OCCompletionPriority.NORMAL_KEYWORDS_PRIORITY, new LookupDecorator() {
            @NotNull
            @Override
            public LookupElementBuilder decorate(@NotNull final OCCompletionParameters ocCompletionParameters, @NotNull LookupElementBuilder withInsertHandler) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$6", "decorate"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (withInsertHandler == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$6", "decorate"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                if (((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocCompletionParameters.getOriginalFile().getProject()).getCustomSettings((Class)OCCodeStyleSettings.class)).SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR) {
                    withInsertHandler = withInsertHandler.withInsertHandler((InsertHandler)new AddSpaceInsertHandler(false));
                }
                LookupElementBuilder lookupElementBuilder;
                try {
                    lookupElementBuilder = withInsertHandler;
                    if (lookupElementBuilder == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$6", "decorate"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                return lookupElementBuilder;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }))));
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.NAME_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN), ArrayUtil.mergeArrays(array3, new String[] { "!__autoreleasing " }));
        this.a((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.NAME_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN), filtered(a(null, null, ContainerUtil.list((Object[])new Keyword[] { a(OCTokenTypes._NULLABLE_KEYWORD.getName(), true), a(OCTokenTypes._NONNULL_KEYWORD.getName(), true), a(OCTokenTypes._NULL_UNSPECIFIED_KEYWORD.getName(), true), a("__nullable", false), a("__nonnull", false), a("__null_unspecified", false) })), OCKeywordCompletionContributor.NULLABILITY_FEATURE_ENABLED_FILTER));
        final PsiElementPattern.Capture capture4 = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.REFERENCE_ELEMENT.withSuperParent(2, (ElementPattern)PlatformPatterns.psiElement((Class)OCReferenceExpression.class).withParent(PlatformPatterns.or(new ElementPattern[] { PlatformPatterns.psiElement((Class)OCSizeofExpression.class), PlatformPatterns.psiElement((Class)OCCppTypeidExpression.class) })))).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)capture4, b(array));
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)capture4.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), b(array4));
        this.b((PsiElementPattern.Capture<PsiElement>)((PsiElementPattern.Capture)OCCompletionPatterns.REFERENCE_ELEMENT.withSuperParent(2, (ElementPattern)PlatformPatterns.psiElement((Class)OCStructLike.class))).with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), "alignas(<caret>)");
    }
    
    private void j() {
        this.b(OCCompletionPatterns.AT_PROPERTY_ATTRIBUTE, OCPropertySymbol.PropertyAttribute.ASSIGN.getTokenName(), OCPropertySymbol.PropertyAttribute.COPY.getTokenName(), OCPropertySymbol.PropertyAttribute.UNSAFE_UNRETAINED.getTokenName(), OCPropertySymbol.PropertyAttribute.WEAK.getTokenName(), OCPropertySymbol.PropertyAttribute.STRONG.getTokenName(), OCPropertySymbol.PropertyAttribute.GETTER.getTokenName() + "=<#method#>", OCPropertySymbol.PropertyAttribute.SETTER.getTokenName() + "=<#method#>", OCPropertySymbol.PropertyAttribute.ATOMIC.getTokenName(), OCPropertySymbol.PropertyAttribute.NONATOMIC.getTokenName(), OCPropertySymbol.PropertyAttribute.READONLY.getTokenName(), OCPropertySymbol.PropertyAttribute.READWRITE.getTokenName(), OCPropertySymbol.PropertyAttribute.RETAIN.getTokenName());
        this.a(OCCompletionPatterns.AT_ANY_PROPERTY_ATTRIBUTE, OCKeywordCompletionContributor.CLASS_PROPERTY_FEATURE_ENABLED_FILTER, OCPropertySymbol.PropertyAttribute.CLASS.getTokenName());
        this.a(OCCompletionPatterns.AT_PROPERTY_ATTRIBUTE, OCKeywordCompletionContributor.NULLABILITY_FEATURE_ENABLED_FILTER, OCPropertySymbol.PropertyAttribute.NONNULL.getTokenName(), OCPropertySymbol.PropertyAttribute.NULLABLE.getTokenName(), OCPropertySymbol.PropertyAttribute.NULL_RESETTABLE.getTokenName());
    }
    
    private void a() {
        final PsiElementPattern.Capture psiElement = PlatformPatterns.psiElement((IElementType)OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT);
        final PsiElementPattern.Capture capture = (PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.andOr(new ElementPattern[] { OCCompletionPatterns.TOP_LEVEL_ELEMENT, OCCompletionPatterns.ELEMENT.with((PatternCondition)new PatternCondition<PsiElement>("afterNewLine") {
                public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
                    try {
                        if (psiElement == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$7", "accepts"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    PsiElement prevLeaf = psiElement;
                    try {
                        if (!psiElement.getText().startsWith("#")) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                Label_0098_Outer:
                    while (true) {
                        while (true) {
                            if (!((prevLeaf = OCElementUtil.getPrevLeaf(prevLeaf)) instanceof PsiWhiteSpace)) {
                                Label_0121: {
                                    try {
                                        if (prevLeaf == null) {
                                            break Label_0121;
                                        }
                                        final PsiElement psiElement2 = prevLeaf;
                                        final int n = psiElement2.getTextLength();
                                        if (n == 0) {
                                            break Label_0098;
                                        }
                                        break Label_0121;
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                    try {
                                        final PsiElement psiElement2 = prevLeaf;
                                        final int n = psiElement2.getTextLength();
                                        if (n != 0) {
                                            break Label_0121;
                                        }
                                        if (!prevLeaf.getText().contains("\n")) {
                                            continue Label_0098_Outer;
                                        }
                                    }
                                    catch (IllegalArgumentException ex4) {
                                        throw a(ex4);
                                    }
                                    return true;
                                    try {
                                        if (prevLeaf == null) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw a(ex5);
                                    }
                                }
                                return false;
                            }
                            continue;
                        }
                    }
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }) });
        final String[] array = { "#import \"<caret>\"", "#import <<caret>>", "#include \"<caret>\"", "#include <<caret>>", "#pragma ", "#define ", "#undef", "#if ", "#ifdef ", "#ifndef ", "#else ", "#elif ", "#endif", "#error ", "#warning " };
        this.b((PsiElementPattern.Capture<PsiElement>)capture, array);
        this.extend(CompletionType.BASIC, (ElementPattern)psiElement, (CompletionProvider)a(OCCompletionPriority.NORMAL_KEYWORDS_PRIORITY, array));
    }
    
    private void a(final PsiElementPattern.Capture<PsiElement> capture, final ElementPattern<PsiElement> elementPattern, final int n, final String... array) {
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)capture.with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN), a(Character.valueOf('@'), null, a(array)));
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.OBJC_ERROR_KEYWORD.withSuperParent(n, (ElementPattern)elementPattern), a(null, Character.valueOf('@'), a(array)));
    }
    
    private void a(final PsiElementPattern.Capture<PsiElement> capture, final OCCompletionProvider ocCompletionProvider) {
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)capture, ocCompletionProvider);
    }
    
    private void b(final PsiElementPattern.Capture<PsiElement> capture, final String... array) {
        this.a(capture, b(array));
    }
    
    private void a(final PsiElementPattern.Capture<PsiElement> capture, final Condition<OCFilteredCompletionProvider.Filter> condition, final String... array) {
        this.a(capture, filtered(b(array), condition));
    }
    
    private void i() {
        this.a((Class<? extends PsiElement>)OCFile.class, "protocol ", "interface ", "implementation ", "import ", "class ", "compatibility_alias ");
        this.b(OCCompletionPatterns.SIMPLE_TYPE_IN_TOP_LEVEL_DECLARATION, "extern ");
        this.b(OCCompletionPatterns.SIMPLE_TYPE_IN_NON_BLOCK_DECLARATION, "inline ", "typedef ", "register ", "volatile ", "enum ", "struct ", "union ", "asm(<caret>)");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_TOP_LEVEL_DECLARATION.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), "namespace ");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_NON_BLOCK_DECLARATION.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), "explicit ", "class ");
        this.b((PsiElementPattern.Capture<PsiElement>)OCCompletionPatterns.SIMPLE_TYPE_IN_NAMESPACE_DECLARATION.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN), "extern ", "namespace ");
        this.b(OCCompletionPatterns.IN_STRUCT, "_Static_assert(<caret>)");
        this.b(OCCompletionPatterns.IN_CPP_STRUCT, "friend ", "mutable ", "!virtual ", "!public:", "!protected:", "!private:", "static_assert(<caret>)");
    }
    
    private static OCCompletionProvider o() {
        return new OCCompletionProvider() {
            @Override
            protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$8", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (ocCompletionParameters.getPosition() instanceof PsiComment) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final OCExpression access$000 = a(ocCompletionParameters);
                try {
                    if (access$000 != null) {
                        MethodSelectorCompletionContributor.addCompletionsForReceiver(access$000, "", ocCompletionParameters, set, (Condition<OCMethodSymbol>)Conditions.alwaysTrue());
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, ocCompletionParameters.getRealPosition(), ocCompletionParameters.getOffset());
                final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType(ocCompletionParameters.getPosition(), new Class[] { OCCallable.class });
                if (ocCallable != null) {
                    withOCPrefixMather.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)new TailTypeDecorator<LookupElement>(TemplateInsertHandler.lookup("return").bold()) {
                        final /* synthetic */ boolean val$isVoid = ocCallable.getReturnType().equalsAfterResolving(OCVoidType.instance(), (PsiElement)ocCallable.getContainingOCFile());
                        
                        @Nullable
                        @Override
                        protected TailType computeTailType(final InsertionContext insertionContext) {
                            final char completionChar = insertionContext.getCompletionChar();
                            if (this.val$isVoid) {
                                return (completionChar == ' ') ? TailType.HUMBLE_SPACE_BEFORE_WORD : TailType.SEMICOLON;
                            }
                            return (completionChar == ';') ? TailType.SEMICOLON : TailType.HUMBLE_SPACE_BEFORE_WORD;
                        }
                    }, OCCompletionPriority.HIGH_KEYWORDS_PRIORITY));
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    @NotNull
    private static OCCompletionProvider b(@NotNull final String... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "keywords", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "provider"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCCompletionProvider a;
        try {
            a = a(null, null, a(array));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "provider"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    static OCCompletionProvider filtered(@NotNull final OCCompletionProvider ocCompletionProvider, @NotNull final Condition<OCFilteredCompletionProvider.Filter> condition) {
        try {
            if (ocCompletionProvider == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "provider", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "filtered"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (condition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "filtered"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCFilteredCompletionProvider ocFilteredCompletionProvider;
        try {
            ocFilteredCompletionProvider = new OCFilteredCompletionProvider(ocCompletionProvider, condition);
            if (ocFilteredCompletionProvider == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "filtered"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return ocFilteredCompletionProvider;
    }
    
    @NotNull
    private static List<Keyword> a(@NotNull final String... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "keywords", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "keywords"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List map;
        try {
            map = ContainerUtil.map((Object[])array, s -> {
                OCCompletionPriority ocCompletionPriority;
                if (s.startsWith("!")) {
                    s = s.substring(1, s.length());
                    ocCompletionPriority = OCCompletionPriority.HIGH_KEYWORDS_PRIORITY;
                }
                else if (s.startsWith("?")) {
                    s = s.substring(1, s.length());
                    ocCompletionPriority = OCCompletionPriority.LOW_KEYWORDS_PRIORITY;
                }
                else {
                    ocCompletionPriority = OCCompletionPriority.NORMAL_KEYWORDS_PRIORITY;
                }
                return new Keyword(s, ocCompletionPriority, null);
            });
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "keywords"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (List<Keyword>)map;
    }
    
    @NotNull
    private static Keyword a(final String s, final boolean b) {
        OCCompletionPriority ocCompletionPriority = null;
        Label_0022: {
            try {
                if (b) {
                    ocCompletionPriority = OCCompletionPriority.HIGH_KEYWORDS_PRIORITY;
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ocCompletionPriority = OCCompletionPriority.NORMAL_KEYWORDS_PRIORITY;
        }
        final Keyword keyword = new Keyword(s, ocCompletionPriority, OCKeywordCompletionContributor.TYPE_ATTRIBUTE_DECORATOR);
        if (keyword == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "typeAttributeKeyword"));
        }
        return keyword;
    }
    
    @NotNull
    private static OCCompletionProvider a(@Nullable final Character c, @Nullable final Character c2, @NotNull final List<Keyword> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "keywords", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "provider"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCCompletionProvider ocCompletionProvider;
        try {
            ocCompletionProvider = new OCCompletionProvider() {
                @Override
                protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
                    try {
                        if (ocCompletionParameters == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$9", "addCompletions"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    Label_0073: {
                        try {
                            if (ocCompletionParameters.getPosition() instanceof PsiComment) {
                                return;
                            }
                            final OCCompletionParameters ocCompletionParameters2 = ocCompletionParameters;
                            final OCExpression ocExpression = a(ocCompletionParameters2);
                            if (ocExpression != null) {
                                return;
                            }
                            break Label_0073;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCCompletionParameters ocCompletionParameters2 = ocCompletionParameters;
                            final OCExpression ocExpression = a(ocCompletionParameters2);
                            if (ocExpression != null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, ocCompletionParameters.getRealPosition(), ocCompletionParameters.getOffset());
                    final String string = "@" + s;
                    final boolean startsWith = s.startsWith("_");
                    for (final Keyword keyword : list) {
                        String s2 = keyword.lookUpString;
                        if (c != null) {
                            s2 = c + s2;
                        }
                        Label_0243: {
                            Label_0221: {
                                Label_0214: {
                                    try {
                                        if (StringUtil.startsWithIgnoreCase(s2, s)) {
                                            break Label_0221;
                                        }
                                        final String s3 = s2;
                                        final String s4 = string;
                                        final boolean b = StringUtil.startsWithIgnoreCase(s3, s4);
                                        if (!b) {
                                            break Label_0214;
                                        }
                                        break Label_0221;
                                    }
                                    catch (IllegalArgumentException ex4) {
                                        throw a(ex4);
                                    }
                                    try {
                                        final String s3 = s2;
                                        final String s4 = string;
                                        final boolean b = StringUtil.startsWithIgnoreCase(s3, s4);
                                        if (!b) {
                                            continue;
                                        }
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw a(ex5);
                                    }
                                }
                                try {
                                    if (startsWith) {
                                        break Label_0243;
                                    }
                                    final String s5 = s2;
                                    final String s6 = "_";
                                    final boolean b2 = s5.startsWith(s6);
                                    if (b2) {
                                        break Label_0243;
                                    }
                                    break Label_0243;
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw a(ex6);
                                }
                            }
                            try {
                                final String s5 = s2;
                                final String s6 = "_";
                                final boolean b2 = s5.startsWith(s6);
                                if (b2) {
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException ex7) {
                                throw a(ex7);
                            }
                        }
                        LookupElementBuilder lookupElementBuilder = TemplateInsertHandler.lookup(s2);
                        if (c2 != null) {
                            lookupElementBuilder = lookupElementBuilder.withPresentableText(c2 + lookupElementBuilder.getLookupString());
                        }
                        if (keyword.decorator != null) {
                            lookupElementBuilder = keyword.decorator.decorate(ocCompletionParameters, lookupElementBuilder);
                        }
                        withOCPrefixMather.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)lookupElementBuilder, keyword.priority));
                    }
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            if (ocCompletionProvider == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "provider"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocCompletionProvider;
    }
    
    @NotNull
    private static CompletionProvider<CompletionParameters> a(final OCCompletionPriority ocCompletionPriority, @NotNull final String... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "keywords", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "rawProvider"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CompletionProvider<CompletionParameters> completionProvider;
        try {
            completionProvider = new CompletionProvider<CompletionParameters>() {
                protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
                    try {
                        if (completionParameters == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$10", "addCompletions"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (set == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$10", "addCompletions"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, completionParameters.getPosition(), completionParameters.getOffset());
                    final String[] val$keywords = array;
                    for (int length = val$keywords.length, i = 0; i < length; ++i) {
                        withOCPrefixMather.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)TemplateInsertHandler.lookup(val$keywords[i]), ocCompletionPriority));
                    }
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            if (completionProvider == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "rawProvider"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return completionProvider;
    }
    
    public static CompletionResultSet withOCPrefixMather(final CompletionResultSet set, final PsiElement psiElement, final int n) {
        final ObjectPattern.Capture not = PlatformPatterns.not(PlatformPatterns.or(new ElementPattern[] { PlatformPatterns.character().javaIdentifierPart(), PlatformPatterns.character().oneOf((Object[])new Character[] { '@', '#' }) }));
        Label_0089: {
            try {
                if (!(psiElement instanceof PsiComment) || ((PsiComment)psiElement).getTokenType() != OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT) {
                    break Label_0089;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final String s = CompletionData.findPrefixDefault(psiElement, n, (ElementPattern)not);
            return set.withPrefixMatcher(s);
        }
        final String s = CompletionData.findPrefixStatic(psiElement, n, (ElementPattern<Character>)not);
        return set.withPrefixMatcher(s);
    }
    
    private static OCExpression a(final OCCompletionParameters ocCompletionParameters) {
        final PsiFile originalFile = ocCompletionParameters.getOriginalFile();
        Label_0031: {
            try {
                if (!(originalFile instanceof OCCodeFragment)) {
                    break Label_0031;
                }
                final OCCodeFragment ocCodeFragment = (OCCodeFragment)originalFile;
                final OCCodeFragment ocCodeFragment2 = ocCodeFragment;
                final Condition<OCSymbol> condition = ocCodeFragment2.getCompletionFilter();
                if (condition != null) {
                    break Label_0031;
                }
                break Label_0031;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCCodeFragment ocCodeFragment = (OCCodeFragment)originalFile;
                final OCCodeFragment ocCodeFragment2 = ocCodeFragment;
                final Condition<OCSymbol> condition = ocCodeFragment2.getCompletionFilter();
                if (condition != null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(ocCompletionParameters.getPosition(), (Class)OCStatement.class);
        try {
            if (!(ocStatement instanceof OCDeclarationStatement)) {
                if (!(ocStatement instanceof OCExpressionStatement)) {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        PsiElement psiElement = ocStatement.getPrevSibling();
        Label_0142: {
            Label_0114: {
                try {
                    if (!(psiElement instanceof PsiWhiteSpace) || psiElement.getText().indexOf(10) != -1) {
                        break Label_0114;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                psiElement = psiElement.getPrevSibling();
                try {
                    if (!(psiElement instanceof OCExpressionStatement)) {
                        return null;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final String s = psiElement2.getText();
                    final String s2 = ";";
                    final boolean b = s.endsWith(s2);
                    if (!b) {
                        break Label_0142;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final String s = psiElement2.getText();
                final String s2 = ";";
                final boolean b = s.endsWith(s2);
                if (!b) {
                    return a(((OCExpressionStatement)psiElement).getExpression());
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    @Nullable
    private static OCExpression a(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor", "getSelectorReceiver"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCExpression ocExpression2 = ocExpression;
        OCCommaExpression ocCommaExpression = null;
        while (ocCommaExpression != ocExpression2) {
            ocCommaExpression = (OCCommaExpression)ocExpression2;
            if (ocCommaExpression instanceof OCBinaryExpression) {
                ocExpression2 = ((OCBinaryExpression)ocCommaExpression).getRight();
            }
            else if (ocCommaExpression instanceof OCConditionalExpression) {
                ocExpression2 = ((OCConditionalExpression)ocCommaExpression).getNegativeExpression();
            }
            else if (ocCommaExpression instanceof OCAssignmentExpression) {
                ocExpression2 = ((OCAssignmentExpression)ocCommaExpression).getSourceExpression();
            }
            else {
                if (!(ocCommaExpression instanceof OCCommaExpression)) {
                    continue;
                }
                ocExpression2 = ocCommaExpression.getTailExpression();
            }
        }
        return ocExpression2;
    }
    
    static {
        TYPE_ATTRIBUTE_DECORATOR = new LookupDecorator() {
            private final AddSpaceInsertHandler myHandler = new AddSpaceInsertHandler(false);
            
            @NotNull
            @Override
            public LookupElementBuilder decorate(@NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final LookupElementBuilder lookupElementBuilder) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$1", "decorate"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (lookupElementBuilder == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$1", "decorate"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                LookupElementBuilder withInsertHandler;
                try {
                    withInsertHandler = lookupElementBuilder.withInsertHandler((InsertHandler)this.myHandler);
                    if (withInsertHandler == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$1", "decorate"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                return withInsertHandler;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        ALTERNATIVE_TOKENS_UNARY = new String[] { "?compl", "?not" };
        ALTERNATIVE_TOKENS = ArrayUtil.mergeArrays(OCKeywordCompletionContributor.ALTERNATIVE_TOKENS_UNARY, new String[] { "?and", "?and_eq", "?bitand", "?bitor", "?not_eq", "?or", "?or_eq", "?xor", "?xor_eq" });
        OCKeywordCompletionContributor.NULLABILITY_FEATURE_ENABLED_FILTER = (Condition<OCFilteredCompletionProvider.Filter>)(filter -> OCCompilerFeatures.supportsNullability(filter.myParameters.getOriginalFile()));
        OCKeywordCompletionContributor.GCC_AUTO_TYPE_FEATURE_ENABLED_FILTER = (Condition<OCFilteredCompletionProvider.Filter>)(filter -> OCCompilerFeatures.supportsGccAutoType(filter.myParameters.getOriginalFile()));
        OCKeywordCompletionContributor.CLASS_PROPERTY_FEATURE_ENABLED_FILTER = (Condition<OCFilteredCompletionProvider.Filter>)(filter -> OCCompilerFeatures.supportsClassProperty(filter.myParameters.getOriginalFile()));
        HAS_FEATURE_BUILTIN_AVAILABLE = a(OCCompilerFeatures.Feature.BUILTIN_AVAILABLE);
        AT_EXPRESSION_LEVEL = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.REFERENCE_ELEMENT.andOr(new ElementPattern[] { PlatformPatterns.psiElement().withSuperParent(2, (Class)OCReferenceExpression.class), ((PsiElementPattern.Capture)PlatformPatterns.psiElement().withSuperParent(2, (Class)OCTypeElement.class)).withSuperParent(3, (ElementPattern)PlatformPatterns.psiElement((Class)OCDeclaration.class).inside(true, (ElementPattern)PlatformPatterns.psiElement((Class)OCBlockStatement.class), (ElementPattern)PlatformPatterns.psiElement((Class)OCDeclarator.class))) })).andNot((ElementPattern)OCCompletionPatterns.WITH_QUALIFIER)).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT)).andNot((ElementPattern)OCCompletionPatterns.AT_CLASS_FUNCTION_BODY_PLACEHOLDER);
        AT_EXPRESSION_LEVEL_CPP = (PsiElementPattern.Capture)OCKeywordCompletionContributor.AT_EXPRESSION_LEVEL.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class Keyword
    {
        @NotNull
        final String lookUpString;
        @NotNull
        final OCCompletionPriority priority;
        @Nullable
        final LookupDecorator decorator;
        
        public Keyword(@NotNull final String lookUpString, @NotNull final OCCompletionPriority priority, @Nullable final LookupDecorator decorator) {
            if (lookUpString == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lookUpString", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$Keyword", "<init>"));
            }
            if (priority == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "priority", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$Keyword", "<init>"));
            }
            this.lookUpString = lookUpString;
            this.priority = priority;
            this.decorator = decorator;
        }
    }
    
    private interface LookupDecorator
    {
        @NotNull
        LookupElementBuilder decorate(@NotNull final OCCompletionParameters p0, @NotNull final LookupElementBuilder p1);
    }
}
