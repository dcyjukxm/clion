// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import java.util.Iterator;
import com.intellij.psi.MultiRangeReference;
import com.intellij.psi.PsiReference;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.codeInspection.SuppressIntentionAction;
import com.intellij.lang.annotation.ProblemGroup;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.EmptyIntentionAction;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.daemon.clang.OCClangMessageDescriptor;
import com.jetbrains.cidr.lang.daemon.clang.OCClangMessageFinder;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

public abstract class OCAnnotator extends OCVisitor implements Annotator, OCAnnotatorSink
{
    private static final String ERROR_AFTER_MACRO_SUBSTITUTION = "Error after macro substitution: ";
    public static final String CIDR_DIAG = "CIDR";
    @Nullable
    private AnnotationHolder myHolder;
    private PsiElement myAnnotatingElement;
    
    @NotNull
    public static OCAnnotator[] getAnnotators() {
        OCAnnotator[] array;
        try {
            array = new OCAnnotator[] { new OCErrorAnnotator(), new OCResolveAnnotator() };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "getAnnotators"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    @NotNull
    public static OCAnnotatorHelper[] getAnnotatorHelpers() {
        OCAnnotatorHelper[] array;
        try {
            array = (OCAnnotatorHelper[])Extensions.getExtensions((ExtensionPointName)OCAnnotatorHelper.EP_NAME);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "getAnnotatorHelpers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    @Nullable
    public AnnotationHolder getHolder() {
        return this.myHolder;
    }
    
    public void annotate(@NotNull final PsiElement psiElement, @NotNull final AnnotationHolder annotationHolder) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "annotate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (annotationHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "annotate"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        this.annotate(psiElement, annotationHolder, annotationHolder.getCurrentAnnotationSession());
    }
    
    public void annotate(@NotNull final PsiElement myAnnotatingElement, @Nullable final AnnotationHolder myHolder, @NotNull final AnnotationSession annotationSession) {
        try {
            if (myAnnotatingElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "annotate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (annotationSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "annotate"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final PsiFile file = annotationSession.getFile();
        try {
            if (!(file instanceof OCFile)) {
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCFile ocFile = (OCFile)file;
        try {
            if (!isAnnotated(ocFile)) {
                return;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        ocFile.pushAnnotationSession(annotationSession);
        this.myHolder = myHolder;
        this.myAnnotatingElement = myAnnotatingElement;
        try {
            myAnnotatingElement.accept((PsiElementVisitor)this);
        }
        finally {
            this.myHolder = null;
            this.myAnnotatingElement = null;
            ocFile.popAnnotationSession();
        }
    }
    
    public static boolean isAnnotated(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ocFile", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "isAnnotated"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocFile.isInProjectSourcesOrLibraries();
    }
    
    @Nullable
    public Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "addErrorAnnotation"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCElementsRange ocElementsRange;
        if (psiElement instanceof OCFunctionDeclaration) {
            ocElementsRange = ((OCFunctionDeclaration)psiElement).getHeaderRange();
        }
        else {
            ocElementsRange = new OCElementsRange(psiElement, psiElement).trim(OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
        }
        if (ocElementsRange != null) {
            return this.addErrorAnnotation(psiElement, ocElementsRange.getTextRange(), clazz, s, s2, problemHighlightType);
        }
        return null;
    }
    
    @Nullable
    public Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull String string, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (string == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "addErrorAnnotation"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(psiElement);
        final OCElement ocElement = (OCElement)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCMacroCallArgument.class, OCDirective.class });
        Label_0154: {
            try {
                if (ocElement == null) {
                    break Label_0154;
                }
                if (!(ocElement instanceof OCDirective)) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final IElementType elementType = ((OCDirective)ocElement).getHeaderToken().getNode().getElementType();
            Label_0143: {
                try {
                    if (elementType == OCTokenTypes.ERROR_DIRECTIVE) {
                        break Label_0143;
                    }
                    final OCElementType ocElementType = (OCElementType)elementType;
                    final OCElementType ocElementType2 = OCTokenTypes.WARNING_DIRECTIVE;
                    if (ocElementType != ocElementType2) {
                        break Label_0143;
                    }
                    break Label_0143;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCElementType ocElementType = (OCElementType)elementType;
                    final OCElementType ocElementType2 = OCTokenTypes.WARNING_DIRECTIVE;
                    if (ocElementType != ocElementType2) {
                        return null;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            try {
                if (rangeInMacroCall == null) {
                    return this.addErrorAnnotation(textRange, clazz, s, string, problemHighlightType);
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        Label_0198: {
            try {
                if (rangeInMacroCall.mapsToArguments()) {
                    return this.addErrorAnnotation(rangeInMacroCall.getArgumentRange(), clazz, s, string, problemHighlightType);
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCParenthesizedExpression;
                if (b) {
                    break Label_0198;
                }
                break Label_0198;
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCParenthesizedExpression;
                if (b) {
                    return this.addErrorAnnotation((PsiElement)((OCParenthesizedExpression)psiElement).getOperand(), textRange, clazz, s, string, problemHighlightType);
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
        }
        final OCMacroCall macroCall = rangeInMacroCall.getMacroCall();
        if (!string.startsWith("Error after macro substitution: ")) {
            string = "Error after macro substitution: " + string;
        }
        return this.addErrorAnnotation((PsiElement)macroCall, macroCall.getTextRange(), clazz, s, string, ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
    }
    
    @Nullable
    public Annotation addErrorAnnotation(@Nullable final TextRange p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final String p2, @NotNull final String p3, @Nullable final ProblemHighlightType p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "message"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/daemon/OCAnnotator"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "addErrorAnnotation"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    44: athrow         
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myHolder:Lcom/intellij/lang/annotation/AnnotationHolder;
        //    49: ifnull          77
        //    52: aload_1        
        //    53: ifnull          77
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    62: athrow         
        //    63: aload_1        
        //    64: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //    67: ifeq            83
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    76: athrow         
        //    77: aconst_null    
        //    78: areturn        
        //    79: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    82: athrow         
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myAnnotatingElement:Lcom/intellij/psi/PsiElement;
        //    87: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    95: astore          6
        //    97: aload           6
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload_3        
        //   102: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.getProblemGroup:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/daemon/OCProblemGroup;
        //   105: astore          7
        //   107: aload           7
        //   109: ifnull          124
        //   112: aload           7
        //   114: invokevirtual   com/jetbrains/cidr/lang/daemon/OCProblemGroup.getSuppressOption:()Ljava/lang/String;
        //   117: goto            125
        //   120: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   123: athrow         
        //   124: aconst_null    
        //   125: astore          8
        //   127: aload           6
        //   129: aload_1        
        //   130: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   133: aload           8
        //   135: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.getDiagnosticMode:(Lcom/jetbrains/cidr/lang/psi/OCFile;ILjava/lang/String;)Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   138: astore          9
        //   140: aload           9
        //   142: ifnull          202
        //   145: getstatic       com/jetbrains/cidr/lang/daemon/OCAnnotator$1.$SwitchMap$com$jetbrains$cidr$lang$psi$OCPragma$Mode:[I
        //   148: aload           9
        //   150: invokevirtual   com/jetbrains/cidr/lang/psi/OCPragma$Mode.ordinal:()I
        //   153: iaload         
        //   154: tableswitch {
        //                2: 188
        //                3: 194
        //                4: 202
        //                5: 202
        //          default: 202
        //        }
        //   184: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   187: athrow         
        //   188: aconst_null    
        //   189: areturn        
        //   190: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   193: athrow         
        //   194: getstatic       com/intellij/codeInspection/ProblemHighlightType.WEAK_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   197: astore          5
        //   199: goto            202
        //   202: aload_0        
        //   203: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myHolder:Lcom/intellij/lang/annotation/AnnotationHolder;
        //   206: aload_1        
        //   207: aload           4
        //   209: invokeinterface com/intellij/lang/annotation/AnnotationHolder.createErrorAnnotation:(Lcom/intellij/openapi/util/TextRange;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   214: astore          10
        //   216: aload_0        
        //   217: aload           10
        //   219: aload           5
        //   221: aload_2        
        //   222: aload           7
        //   224: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInspection/ProblemHighlightType;Ljava/lang/Class;Lcom/jetbrains/cidr/lang/daemon/OCProblemGroup;)V
        //   227: aload           10
        //   229: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class<+Lcom/jetbrains/cidr/lang/inspections/OCInspection;>;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      41     41     45     Ljava/lang/IllegalStateException;
        //  45     56     59     63     Ljava/lang/IllegalStateException;
        //  52     70     73     77     Ljava/lang/IllegalStateException;
        //  63     79     79     83     Ljava/lang/IllegalStateException;
        //  107    120    120    124    Ljava/lang/IllegalStateException;
        //  140    184    184    188    Ljava/lang/IllegalStateException;
        //  145    190    190    194    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
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
    public Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "addWarningAnnotation"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCElementsRange ocElementsRange;
        if (psiElement instanceof OCFunctionDeclaration) {
            ocElementsRange = ((OCFunctionDeclaration)psiElement).getHeaderRange();
        }
        else {
            ocElementsRange = new OCElementsRange(psiElement, psiElement).trim(OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
        }
        if (ocElementsRange != null) {
            return this.addWarningAnnotation(psiElement, ocElementsRange.getTextRange(), clazz, s, s2, problemHighlightType);
        }
        return null;
    }
    
    @Nullable
    public Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "addWarningAnnotation"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(psiElement);
        try {
            if (rangeInMacroCall == null) {
                return this.a(psiElement, textRange, clazz, s, s2, problemHighlightType);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        Label_0132: {
            Label_0106: {
                try {
                    if (rangeInMacroCall.mapsToArguments()) {
                        return this.a(psiElement, rangeInMacroCall.getTextRange(), clazz, s, s2, problemHighlightType);
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCParenthesizedExpression;
                    if (b) {
                        break Label_0106;
                    }
                    break Label_0132;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCParenthesizedExpression;
                    if (b) {
                        return this.addWarningAnnotation((PsiElement)((OCParenthesizedExpression)psiElement).getOperand(), textRange, clazz, s, s2, problemHighlightType);
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (OCElementUtil.getRangeInMacroCall(this.myAnnotatingElement) == null) {
                    return this.a((PsiElement)rangeInMacroCall.getMacroCall(), rangeInMacroCall.getMacroCall().getTextRange(), clazz, s, s2, problemHighlightType);
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    @Nullable
    private Annotation a(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "doAddWarningAnnotation"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0069: {
            Label_0063: {
                try {
                    if (textRange == null) {
                        break Label_0063;
                    }
                    final TextRange textRange2 = textRange;
                    final boolean b = textRange2.isEmpty();
                    if (b) {
                        break Label_0063;
                    }
                    break Label_0069;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final TextRange textRange2 = textRange;
                    final boolean b = textRange2.isEmpty();
                    if (b) {
                        return null;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCMacroCallArgument.class) != null) {
                    return null;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        try {
            if (this.myHolder == null) {
                return null;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        ProblemHighlightType generic_ERROR_OR_WARNING = null;
        Label_0116: {
            try {
                if (problemHighlightType != null) {
                    generic_ERROR_OR_WARNING = problemHighlightType;
                    break Label_0116;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            generic_ERROR_OR_WARNING = ProblemHighlightType.GENERIC_ERROR_OR_WARNING;
        }
        problemHighlightType = getInspectionHighlightType(psiElement, clazz, generic_ERROR_OR_WARNING);
        try {
            if (problemHighlightType == null) {
                return null;
            }
        }
        catch (IllegalStateException ex7) {
            throw a(ex7);
        }
        final OCFile ocFile = (OCFile)this.myAnnotatingElement.getContainingFile();
        final OCProblemGroup problemGroup = getProblemGroup(ocFile, textRange, clazz, s);
        String suppressOption = null;
        Label_0175: {
            try {
                if (problemGroup != null) {
                    suppressOption = problemGroup.getSuppressOption();
                    break Label_0175;
                }
            }
            catch (IllegalStateException ex8) {
                throw a(ex8);
            }
            suppressOption = null;
        }
        final OCPragma.Mode diagnosticMode = OCPragmaSuppressionUtils.getDiagnosticMode(ocFile, textRange.getStartOffset(), suppressOption);
        Annotation annotation = null;
        Label_0293: {
            Label_0254: {
                Label_0240: {
                    try {
                        if (diagnosticMode == null) {
                            break Label_0254;
                        }
                        final int[] array = OCAnnotator$1.$SwitchMap$com$jetbrains$cidr$lang$psi$OCPragma$Mode;
                        final OCPragma.Mode mode = diagnosticMode;
                        final int n = mode.ordinal();
                        final int n2 = array[n];
                        switch (n2) {
                            case 1: {
                                break Label_0240;
                                break Label_0240;
                            }
                            case 3:
                            case 4: {
                                break Label_0240;
                            }
                            default: {
                                break Label_0254;
                            }
                        }
                    }
                    catch (IllegalStateException ex9) {
                        throw a(ex9);
                    }
                    try {
                        final int[] array = OCAnnotator$1.$SwitchMap$com$jetbrains$cidr$lang$psi$OCPragma$Mode;
                        final OCPragma.Mode mode = diagnosticMode;
                        final int n = mode.ordinal();
                        final int n2 = array[n];
                        switch (n2) {
                            case 1: {
                                return null;
                            }
                            case 3:
                            case 4: {
                                break;
                            }
                            default: {
                                break Label_0254;
                            }
                        }
                    }
                    catch (IllegalStateException ex10) {
                        throw a(ex10);
                    }
                }
                problemHighlightType = ProblemHighlightType.GENERIC_ERROR;
                try {
                    if (problemHighlightType == ProblemHighlightType.INFORMATION) {
                        annotation = this.myHolder.createInfoAnnotation(textRange, s2);
                        break Label_0293;
                    }
                }
                catch (IllegalStateException ex11) {
                    throw a(ex11);
                }
            }
            annotation = this.myHolder.createWarningAnnotation(textRange, s2);
        }
        final Annotation annotation2 = annotation;
        this.a(annotation2, problemHighlightType, clazz, problemGroup);
        return annotation2;
    }
    
    @Nullable
    public static OCProblemGroup getProblemGroup(@Nullable final OCFile ocFile, @NotNull final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "getProblemGroup"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0124: {
            OCSuppressionGroup ocSuppressionGroup = null;
            String s3 = null;
            String s6 = null;
            Label_0116: {
                Label_0096: {
                    Label_0065: {
                        Label_0059: {
                            try {
                                if (clazz == null) {
                                    break Label_0059;
                                }
                                final String s2 = s;
                                if (s2 == null) {
                                    break Label_0059;
                                }
                                break Label_0065;
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                            try {
                                final String s2 = s;
                                if (s2 == null) {
                                    return null;
                                }
                            }
                            catch (IllegalStateException ex3) {
                                throw a(ex3);
                            }
                        }
                        try {
                            if (!s.startsWith("CIDR")) {
                                break Label_0124;
                            }
                            ocSuppressionGroup = new(com.jetbrains.cidr.lang.daemon.OCSuppressionGroup.class);
                            s3 = "ide";
                            final String s4 = s;
                            final String s5 = "CIDR";
                            final boolean b = s4.equals(s5);
                            if (b) {
                                break Label_0096;
                            }
                            break Label_0096;
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        ocSuppressionGroup = new(com.jetbrains.cidr.lang.daemon.OCSuppressionGroup.class);
                        s3 = "ide";
                        final String s4 = s;
                        final String s5 = "CIDR";
                        final boolean b = s4.equals(s5);
                        if (b) {
                            s6 = clazz.getSimpleName();
                            break Label_0116;
                        }
                    }
                    catch (IllegalStateException ex5) {
                        throw a(ex5);
                    }
                }
                s6 = s.substring("CIDR".length());
            }
            new OCSuppressionGroup(s3, s6);
            final OCSuppressionGroup ocSuppressionGroup2 = ocSuppressionGroup;
            return new OCProblemGroup(clazz.getSimpleName(), ocSuppressionGroup2, ocFile, textRange);
        }
        final OCClangMessageDescriptor supportedMessageDescriptor = OCClangMessageFinder.getInstance().getSupportedMessageDescriptor(s);
        OCSuppressionGroup suppressionGroup = null;
        Label_0151: {
            try {
                if (supportedMessageDescriptor != null) {
                    suppressionGroup = supportedMessageDescriptor.getSuppressionGroup();
                    break Label_0151;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            suppressionGroup = null;
        }
        final OCSuppressionGroup ocSuppressionGroup2 = suppressionGroup;
        return new OCProblemGroup(clazz.getSimpleName(), ocSuppressionGroup2, ocFile, textRange);
    }
    
    private void a(@Nullable final Annotation annotation, @Nullable final ProblemHighlightType problemHighlightType, @Nullable final Class<? extends OCInspection> clazz, @Nullable final OCProblemGroup problemGroup) {
        ProblemHighlightType generic_ERROR_OR_WARNING = null;
        Label_0133: {
            Label_0117: {
                Label_0038: {
                    Label_0015: {
                        try {
                            if (annotation == null) {
                                return;
                            }
                            final Class<? extends OCInspection> clazz2 = clazz;
                            if (clazz2 != null) {
                                break Label_0015;
                            }
                            break Label_0038;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final Class<? extends OCInspection> clazz2 = clazz;
                            if (clazz2 != null) {
                                this.registerQuickFix(annotation, (IntentionAction)new EmptyIntentionAction(OCCodeInsightUtil.getPrettyNameFromClassName(clazz)));
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    try {
                        if (problemGroup == null) {
                            break Label_0117;
                        }
                        annotation.setProblemGroup((ProblemGroup)problemGroup);
                        if (!(this.myHolder instanceof AnnotationAndQuickFixHolder)) {
                            break Label_0117;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                final SuppressIntentionAction[] suppressActions = problemGroup.getSuppressActions(null);
                for (int length = suppressActions.length, i = 0; i < length; ++i) {
                    ((AnnotationAndQuickFixHolder)this.myHolder).createQuickFix(annotation, (IntentionAction)suppressActions[i]);
                }
                try {
                    if (problemHighlightType != null) {
                        generic_ERROR_OR_WARNING = problemHighlightType;
                        break Label_0133;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            generic_ERROR_OR_WARNING = ProblemHighlightType.GENERIC_ERROR_OR_WARNING;
        }
        annotation.setHighlightType(generic_ERROR_OR_WARNING);
    }
    
    @Nullable
    public static ProblemHighlightType getInspectionHighlightType(@Nullable final PsiElement p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final ProblemHighlightType p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          200
        //     4: aload_0        
        //     5: ifnull          200
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    14: athrow         
        //    15: aload_0        
        //    16: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //    21: invokestatic    com/intellij/profile/codeInspection/InspectionProjectProfileManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/profile/codeInspection/InspectionProjectProfileManager;
        //    24: astore_3       
        //    25: aload_3        
        //    26: invokevirtual   com/intellij/profile/codeInspection/InspectionProjectProfileManager.getCurrentProfile:()Lcom/intellij/codeInspection/ex/InspectionProfileImpl;
        //    29: astore          4
        //    31: aload_1        
        //    32: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //    35: invokestatic    com/intellij/codeInsight/daemon/HighlightDisplayKey.find:(Ljava/lang/String;)Lcom/intellij/codeInsight/daemon/HighlightDisplayKey;
        //    38: astore          5
        //    40: aload           5
        //    42: ifnull          200
        //    45: aload           4
        //    47: aload           5
        //    49: aload_0        
        //    50: invokeinterface com/intellij/codeInspection/InspectionProfile.isToolEnabled:(Lcom/intellij/codeInsight/daemon/HighlightDisplayKey;Lcom/intellij/psi/PsiElement;)Z
        //    55: ifne            89
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    64: athrow         
        //    65: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    68: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    73: ifne            89
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    82: athrow         
        //    83: aconst_null    
        //    84: areturn        
        //    85: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    88: athrow         
        //    89: aload           4
        //    91: aload           5
        //    93: aload_0        
        //    94: invokeinterface com/intellij/codeInspection/InspectionProfile.getErrorLevel:(Lcom/intellij/codeInsight/daemon/HighlightDisplayKey;Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeHighlighting/HighlightDisplayLevel;
        //    99: astore          6
        //   101: aload           6
        //   103: getstatic       com/intellij/codeHighlighting/HighlightDisplayLevel.WARNING:Lcom/intellij/codeHighlighting/HighlightDisplayLevel;
        //   106: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   109: ifne            200
        //   112: aload           6
        //   114: invokevirtual   com/intellij/codeHighlighting/HighlightDisplayLevel.getSeverity:()Lcom/intellij/lang/annotation/HighlightSeverity;
        //   117: getstatic       com/intellij/lang/annotation/HighlightSeverity.ERROR:Lcom/intellij/lang/annotation/HighlightSeverity;
        //   120: invokevirtual   com/intellij/lang/annotation/HighlightSeverity.equals:(Ljava/lang/Object;)Z
        //   123: ifeq            140
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   132: athrow         
        //   133: getstatic       com/intellij/codeInspection/ProblemHighlightType.ERROR:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   136: astore_2       
        //   137: goto            200
        //   140: aload           6
        //   142: invokevirtual   com/intellij/codeHighlighting/HighlightDisplayLevel.getSeverity:()Lcom/intellij/lang/annotation/HighlightSeverity;
        //   145: getstatic       com/intellij/lang/annotation/HighlightSeverity.GENERIC_SERVER_ERROR_OR_WARNING:Lcom/intellij/lang/annotation/HighlightSeverity;
        //   148: invokevirtual   com/intellij/lang/annotation/HighlightSeverity.equals:(Ljava/lang/Object;)Z
        //   151: ifeq            161
        //   154: getstatic       com/intellij/codeInspection/ProblemHighlightType.GENERIC_ERROR_OR_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   157: astore_2       
        //   158: goto            200
        //   161: aload           6
        //   163: invokevirtual   com/intellij/codeHighlighting/HighlightDisplayLevel.getSeverity:()Lcom/intellij/lang/annotation/HighlightSeverity;
        //   166: getstatic       com/intellij/lang/annotation/HighlightSeverity.INFORMATION:Lcom/intellij/lang/annotation/HighlightSeverity;
        //   169: invokevirtual   com/intellij/lang/annotation/HighlightSeverity.equals:(Ljava/lang/Object;)Z
        //   172: ifeq            182
        //   175: getstatic       com/intellij/codeInspection/ProblemHighlightType.INFORMATION:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   178: astore_2       
        //   179: goto            200
        //   182: aload           6
        //   184: invokevirtual   com/intellij/codeHighlighting/HighlightDisplayLevel.getSeverity:()Lcom/intellij/lang/annotation/HighlightSeverity;
        //   187: getstatic       com/intellij/lang/annotation/HighlightSeverity.WEAK_WARNING:Lcom/intellij/lang/annotation/HighlightSeverity;
        //   190: invokevirtual   com/intellij/lang/annotation/HighlightSeverity.equals:(Ljava/lang/Object;)Z
        //   193: ifeq            200
        //   196: getstatic       com/intellij/codeInspection/ProblemHighlightType.WEAK_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   199: astore_2       
        //   200: aload_2        
        //   201: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/lang/Class<+Lcom/jetbrains/cidr/lang/inspections/OCInspection;>;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/codeInspection/ProblemHighlightType;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      8      11     15     Ljava/lang/IllegalStateException;
        //  40     58     61     65     Ljava/lang/IllegalStateException;
        //  45     76     79     83     Ljava/lang/IllegalStateException;
        //  65     85     85     89     Ljava/lang/IllegalStateException;
        //  101    126    129    133    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
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
    
    public void registerQuickFix(@Nullable final Annotation p0, @NotNull final IntentionAction p1) {
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
        //    18: ldc             "intentionAction"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCAnnotator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "registerQuickFix"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnull          238
        //    48: aload_2        
        //    49: instanceof      Lcom/intellij/codeInspection/SuppressIntentionAction;
        //    52: ifeq            92
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: aload_1        
        //    63: invokevirtual   com/intellij/lang/annotation/Annotation.getProblemGroup:()Lcom/intellij/lang/annotation/ProblemGroup;
        //    66: checkcast       Lcom/jetbrains/cidr/lang/daemon/OCProblemGroup;
        //    69: astore_3       
        //    70: aload_3        
        //    71: ifnull          89
        //    74: aload_3        
        //    75: aload_2        
        //    76: checkcast       Lcom/intellij/codeInspection/SuppressIntentionAction;
        //    79: invokevirtual   com/jetbrains/cidr/lang/daemon/OCProblemGroup.addSuppressAction:(Lcom/intellij/codeInspection/SuppressIntentionAction;)V
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    88: athrow         
        //    89: goto            207
        //    92: aload_1        
        //    93: invokevirtual   com/intellij/lang/annotation/Annotation.getQuickFixes:()Ljava/util/List;
        //    96: astore_3       
        //    97: aload_3        
        //    98: ifnull          120
        //   101: aload_3        
        //   102: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   107: invokestatic    com/intellij/util/containers/ContainerUtil.find:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Ljava/lang/Object;
        //   110: checkcast       Lcom/intellij/lang/annotation/Annotation$QuickFixInfo;
        //   113: goto            121
        //   116: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   119: athrow         
        //   120: aconst_null    
        //   121: astore          4
        //   123: aload           4
        //   125: ifnull          144
        //   128: aload_3        
        //   129: aload           4
        //   131: invokeinterface java/util/List.remove:(Ljava/lang/Object;)Z
        //   136: pop            
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   143: athrow         
        //   144: aload_1        
        //   145: aload_2        
        //   146: invokevirtual   com/intellij/lang/annotation/Annotation.registerFix:(Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   149: aload_2        
        //   150: instanceof      Lcom/jetbrains/cidr/lang/quickfixes/OCQuickFix;
        //   153: ifeq            207
        //   156: aload_2        
        //   157: checkcast       Lcom/jetbrains/cidr/lang/quickfixes/OCQuickFix;
        //   160: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCQuickFix.isSupportedInBatchMode:()Z
        //   163: ifeq            207
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   172: athrow         
        //   173: aload_2        
        //   174: checkcast       Lcom/jetbrains/cidr/lang/quickfixes/OCQuickFix;
        //   177: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCQuickFix.isAvailable:()Z
        //   180: ifeq            207
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   189: athrow         
        //   190: aload_1        
        //   191: aload_2        
        //   192: checkcast       Lcom/jetbrains/cidr/lang/quickfixes/OCQuickFix;
        //   195: aconst_null    
        //   196: aconst_null    
        //   197: invokevirtual   com/intellij/lang/annotation/Annotation.registerBatchFix:(Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/intellij/openapi/util/TextRange;Lcom/intellij/codeInsight/daemon/HighlightDisplayKey;)V
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   206: athrow         
        //   207: aload_0        
        //   208: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myHolder:Lcom/intellij/lang/annotation/AnnotationHolder;
        //   211: instanceof      Lcom/jetbrains/cidr/lang/daemon/OCAnnotator$AnnotationAndQuickFixHolder;
        //   214: ifeq            238
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myHolder:Lcom/intellij/lang/annotation/AnnotationHolder;
        //   221: checkcast       Lcom/jetbrains/cidr/lang/daemon/OCAnnotator$AnnotationAndQuickFixHolder;
        //   224: aload_1        
        //   225: aload_2        
        //   226: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotator$AnnotationAndQuickFixHolder.createQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   237: athrow         
        //   238: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     55     58     62     Ljava/lang/IllegalStateException;
        //  70     82     85     89     Ljava/lang/IllegalStateException;
        //  97     116    116    120    Ljava/lang/IllegalStateException;
        //  123    137    140    144    Ljava/lang/IllegalStateException;
        //  144    166    169    173    Ljava/lang/IllegalStateException;
        //  156    183    186    190    Ljava/lang/IllegalStateException;
        //  173    200    203    207    Ljava/lang/IllegalStateException;
        //  207    231    234    238    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
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
    public Annotation highlight(@Nullable final PsiElement p0, @Nullable final TextAttributesKey p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          64
        //     4: aload_1        
        //     5: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //    10: ifeq            64
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    19: athrow         
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myHolder:Lcom/intellij/lang/annotation/AnnotationHolder;
        //    24: ifnull          64
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    33: athrow         
        //    34: aload_2        
        //    35: ifnull          64
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/OCAnnotator.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    44: athrow         
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/daemon/OCAnnotator.myHolder:Lcom/intellij/lang/annotation/AnnotationHolder;
        //    49: aload_1        
        //    50: aconst_null    
        //    51: invokeinterface com/intellij/lang/annotation/AnnotationHolder.createInfoAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    56: astore_3       
        //    57: aload_3        
        //    58: aload_2        
        //    59: invokevirtual   com/intellij/lang/annotation/Annotation.setTextAttributes:(Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //    62: aload_3        
        //    63: areturn        
        //    64: aconst_null    
        //    65: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      13     16     20     Ljava/lang/IllegalStateException;
        //  4      27     30     34     Ljava/lang/IllegalStateException;
        //  20     38     41     45     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
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
    
    public void highlight(@NotNull final TextRange textRange, @Nullable final TextAttributesKey textAttributes) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "highlight"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0065: {
            try {
                if (textRange.getLength() == 0) {
                    return;
                }
                final OCAnnotator ocAnnotator = this;
                final AnnotationHolder annotationHolder = ocAnnotator.myHolder;
                if (annotationHolder != null) {
                    break Label_0065;
                }
                return;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCAnnotator ocAnnotator = this;
                final AnnotationHolder annotationHolder = ocAnnotator.myHolder;
                if (annotationHolder == null) {
                    return;
                }
                if (textAttributes == null) {
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        this.myHolder.createInfoAnnotation(textRange, (String)null).setTextAttributes(textAttributes);
    }
    
    protected void highlight(@NotNull final PsiReference psiReference, @Nullable final TextAttributesKey textAttributesKey) {
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/daemon/OCAnnotator", "highlight"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final int startOffset = psiReference.getElement().getTextRange().getStartOffset();
        if (psiReference instanceof MultiRangeReference) {
            final Iterator<TextRange> iterator = ((MultiRangeReference)psiReference).getRanges().iterator();
            while (iterator.hasNext()) {
                this.highlight(iterator.next().shiftRight(startOffset), textAttributesKey);
            }
        }
        else {
            this.highlight(psiReference.getRangeInElement().shiftRight(startOffset), textAttributesKey);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public interface AnnotationAndQuickFixHolder extends AnnotationHolder
    {
        void createQuickFix(@NotNull final Annotation p0, @NotNull final IntentionAction p1);
    }
}
