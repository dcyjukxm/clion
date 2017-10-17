// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import java.util.Optional;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.lang.ASTNode;
import java.util.Arrays;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCStatementWithExpression;
import java.util.HashSet;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiComment;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.daemon.OCAnnotator;
import com.intellij.lang.annotation.ProblemGroup;
import com.intellij.codeInsight.intention.EmptyIntentionAction;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.inspections.ClangTidyInspection;
import com.intellij.openapi.progress.ProgressManager;
import java.io.File;
import com.intellij.openapi.editor.Document;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.HighlightSeverity;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import java.util.List;

public class ClangTidyAnnotationApplier
{
    private static final List<String> DIAGNOSTIC_PREFIXES_TO_SUPPRESS;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public void applyFail(@NotNull final PsiFile psiFile, @NotNull final String s, final HighlightSeverity highlightSeverity, @NotNull final AnnotationHolder annotationHolder) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applyFail"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applyFail"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (annotationHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applyFail"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Annotation annotation = annotationHolder.createAnnotation(highlightSeverity, psiFile.getTextRange(), "Unable to execute Clang-Tidy: " + s);
        annotation.registerFix((IntentionAction)new OpenClangTidySettingsAction());
        annotation.setFileLevelAnnotation(true);
    }
    
    public void applySuccess(@NotNull final PsiFile psiFile, @NotNull final Document document, @NotNull final File file, @NotNull final List<ClangTidyDiagnostic> list, @NotNull final AnnotationHolder annotationHolder) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applySuccess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applySuccess"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFilePath", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applySuccess"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostics", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applySuccess"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (annotationHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "applySuccess"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final List<ClangTidyDiagnostic> a = a(file, list);
        ProgressManager.checkCanceled();
        for (final ClangTidyDiagnostic clangTidyDiagnostic : a) {
            final PsiElement a2 = a(psiFile, clangTidyDiagnostic.getFileOffset());
            try {
                if (a2 == null) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            final TextRange a3 = a(a2);
            final String message = clangTidyDiagnostic.getMessage();
            final List<ClangTidyReplacement> replacements = clangTidyDiagnostic.getReplacements();
            final String diagnosticName = clangTidyDiagnostic.getDiagnosticName();
            final Annotation warningAnnotation = annotationHolder.createWarningAnnotation(a3, message);
            try {
                if (warningAnnotation == null) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            Object o = null;
            Label_0383: {
                try {
                    if (replacements.isEmpty()) {
                        o = new EmptyIntentionAction(OCCodeInsightUtil.getPrettyNameFromClassName(ClangTidyInspection.class));
                        break Label_0383;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                o = new ClangTidyIntentionAction(document, message, diagnosticName, replacements);
            }
            final Object o2 = o;
            try {
                warningAnnotation.registerFix((IntentionAction)o2);
                warningAnnotation.setProblemGroup((ProblemGroup)new ClangTidyProblemGroup(diagnosticName));
                if (!(annotationHolder instanceof OCAnnotator.AnnotationAndQuickFixHolder)) {
                    continue;
                }
                ((OCAnnotator.AnnotationAndQuickFixHolder)annotationHolder).createQuickFix(warningAnnotation, (IntentionAction)o2);
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
    }
    
    private static List<ClangTidyDiagnostic> a(@NotNull final File file, @NotNull final List<ClangTidyDiagnostic> list) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFilePath", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getApplicableDiagnostics"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostics", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getApplicableDiagnostics"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final IllegalArgumentException ex3;
        return list.stream().filter(clangTidyDiagnostic -> {
            try {
                if (file == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFilePath", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "lambda$getApplicableDiagnostics$1"));
                    throw ex3;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (ClangTidyAnnotationApplier.DIAGNOSTIC_PREFIXES_TO_SUPPRESS.stream().anyMatch(s -> clangTidyDiagnostic.getDiagnosticName().startsWith(s))) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return FileUtil.pathsEqual(file.getPath(), clangTidyDiagnostic.getFilePath());
        }).collect((Collector<? super Object, ?, List<ClangTidyDiagnostic>>)Collectors.toList());
    }
    
    @Nullable
    private static PsiElement a(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getElementToAnnotate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiElement psiElement = null;
        Label_0074: {
            try {
                if (psiFile.getTextLength() > n) {
                    psiElement = psiFile.findElementAt(n);
                    break Label_0074;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            psiElement = psiFile.getLastChild();
        }
        PsiElement prevSignificantSibling = psiElement;
        try {
            if (prevSignificantSibling instanceof PsiComment) {
                return prevSignificantSibling;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        if (!OCElementUtil.isElementSignificant(prevSignificantSibling)) {
            prevSignificantSibling = OCElementUtil.getPrevSignificantSibling(prevSignificantSibling);
        }
        try {
            if (prevSignificantSibling == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (a(prevSignificantSibling.getNode().getElementType())) {
                return prevSignificantSibling;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (PsiTreeUtil.getNonStrictParentOfType(prevSignificantSibling, new Class[] { OCMacroCall.class }) != null) {
                return prevSignificantSibling;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        final OCElement ocElement = (OCElement)PsiTreeUtil.getNonStrictParentOfType(prevSignificantSibling, new Class[] { OCStatement.class, OCExpression.class, OCTypeElement.class, OCDeclarator.class, OCDeclaration.class, OCStructLike.class });
        Label_0262: {
            Label_0250: {
                Label_0230: {
                    Label_0218: {
                        try {
                            if (ClangTidyAnnotationApplier.$assertionsDisabled) {
                                break Label_0230;
                            }
                            final OCElement ocElement2 = ocElement;
                            final boolean b = ocElement2 instanceof PsiFile;
                            if (b) {
                                break Label_0218;
                            }
                            break Label_0230;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        try {
                            final OCElement ocElement2 = ocElement;
                            final boolean b = ocElement2 instanceof PsiFile;
                            if (b) {
                                throw new AssertionError();
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                    }
                    try {
                        if (ClangTidyAnnotationApplier.$assertionsDisabled) {
                            break Label_0262;
                        }
                        final PsiElement psiElement2 = prevSignificantSibling;
                        final boolean b2 = psiElement2 instanceof PsiFile;
                        if (b2) {
                            break Label_0250;
                        }
                        break Label_0262;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                }
                try {
                    final PsiElement psiElement2 = prevSignificantSibling;
                    final boolean b2 = psiElement2 instanceof PsiFile;
                    if (b2) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            try {
                if (ocElement != null) {
                    final Object o = ocElement;
                    return (PsiElement)o;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw a(ex11);
            }
        }
        final Object o = prevSignificantSibling;
        return (PsiElement)o;
    }
    
    private static boolean a(@NotNull final IElementType elementType) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementType", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "isMakeSenseToHighlight"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0104: {
            Label_0071: {
                try {
                    if (elementType == OCTokenTypes.LBRACE) {
                        return true;
                    }
                    final IElementType elementType2 = elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACE;
                    if (elementType2 == ocPunctuatorElementType) {
                        return true;
                    }
                    break Label_0071;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final IElementType elementType2 = elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACE;
                    if (elementType2 == ocPunctuatorElementType) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (!OCTokenTypes.KEYWORDS.contains(elementType)) {
                        return false;
                    }
                    final TokenSet set = OCTokenTypes.SIMPLE_TYPE_SPECIFIERS;
                    final IElementType elementType3 = elementType;
                    final boolean b = set.contains(elementType3);
                    if (b) {
                        return false;
                    }
                    break Label_0104;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final TokenSet set = OCTokenTypes.SIMPLE_TYPE_SPECIFIERS;
                final IElementType elementType3 = elementType;
                final boolean b = set.contains(elementType3);
                if (b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final HashSet hashSet = ContainerUtil.newHashSet((Object[])new IElementType[] { OCTokenTypes.OPERATOR_CPP_KEYWORD, OCTokenTypes.VIRTUAL_CPP_KEYWORD, OCTokenTypes.EXPLICIT_CPP_KEYWORD, OCTokenTypes.INLINE_KEYWORD, OCTokenTypes.EXTERN_KEYWORD, OCTokenTypes.REGISTER_KEYWORD, OCTokenTypes.STATIC_KEYWORD, OCTokenTypes.CONST_KEYWORD, OCTokenTypes.VOLATILE_KEYWORD });
        try {
            if (!hashSet.contains(elementType)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return false;
    }
    
    @NotNull
    private static TextRange a(@NotNull PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getRangeToHighlight"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCElementType ocElementType = null;
        if (psiElement instanceof OCStatementWithExpression) {
            ocElementType = ((OCStatementWithExpression)psiElement).getKeywordType();
        }
        else if (psiElement instanceof OCIfStatement) {
            ocElementType = OCTokenTypes.IF_KEYWORD;
        }
        else if (psiElement instanceof OCCppNamespace) {
            ocElementType = OCTokenTypes.NAMESPACE_CPP_KEYWORD;
        }
        Label_0194: {
            if (ocElementType != null) {
                final IElementType elementType;
                final boolean b;
                final Optional<ASTNode> first = Arrays.stream(psiElement.getNode().getChildren((TokenSet)null)).filter(astNode -> {
                    try {
                        if (astNode.getElementType() == elementType) {
                            return b;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return b;
                }).findFirst();
                TextRange textRange = null;
                Label_0159: {
                    try {
                        if (!first.isPresent()) {
                            break Label_0194;
                        }
                        final Optional<ASTNode> optional = first;
                        final ASTNode astNode2 = optional.get();
                        final ASTNode astNode3 = astNode2;
                        textRange = astNode3.getTextRange();
                        if (textRange == null) {
                            break Label_0159;
                        }
                        return textRange;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final Optional<ASTNode> optional = first;
                        final ASTNode astNode2 = optional.get();
                        final ASTNode astNode3 = astNode2;
                        textRange = astNode3.getTextRange();
                        if (textRange == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getRangeToHighlight"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return textRange;
            }
        }
        Label_0265: {
            if (psiElement instanceof OCStructLike) {
                final PsiElement nameIdentifier = ((OCStructLike)psiElement).getNameIdentifier();
                TextRange textRange2 = null;
                Label_0230: {
                    try {
                        if (nameIdentifier == null) {
                            break Label_0265;
                        }
                        final PsiElement psiElement2 = nameIdentifier;
                        textRange2 = OCElementUtil.getRangeWithMacros(psiElement2);
                        if (textRange2 == null) {
                            break Label_0230;
                        }
                        return textRange2;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final PsiElement psiElement2 = nameIdentifier;
                        textRange2 = OCElementUtil.getRangeWithMacros(psiElement2);
                        if (textRange2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getRangeToHighlight"));
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                return textRange2;
            }
        }
        Label_0330: {
            if (psiElement instanceof OCDeclaration) {
                final List<OCDeclarator> declarators = ((OCDeclaration)psiElement).getDeclarators();
                try {
                    if (declarators.size() != 1 || declarators.get(0).getName() == "<unnamed>") {
                        break Label_0330;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                psiElement = (PsiElement)declarators.get(0);
            }
        }
        TextRange rangeWithMacros = null;
        Label_0401: {
            if (psiElement instanceof OCDeclarator) {
                final PsiElement nameIdentifier2 = ((OCDeclarator)psiElement).getNameIdentifier();
                TextRange textRange3 = null;
                Label_0366: {
                    try {
                        if (nameIdentifier2 == null) {
                            break Label_0401;
                        }
                        final PsiElement psiElement3 = nameIdentifier2;
                        textRange3 = OCElementUtil.getRangeWithMacros(psiElement3);
                        if (textRange3 == null) {
                            break Label_0366;
                        }
                        return textRange3;
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    try {
                        final PsiElement psiElement3 = nameIdentifier2;
                        textRange3 = OCElementUtil.getRangeWithMacros(psiElement3);
                        if (textRange3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getRangeToHighlight"));
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                }
                return textRange3;
            }
            try {
                rangeWithMacros = OCElementUtil.getRangeWithMacros(psiElement);
                if (rangeWithMacros == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationApplier", "getRangeToHighlight"));
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
        }
        return rangeWithMacros;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!ClangTidyAnnotationApplier.class.desiredAssertionStatus()) {
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
        DIAGNOSTIC_PREFIXES_TO_SUPPRESS = Arrays.asList("clang-diagnostic", "llvm-header-guard");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
