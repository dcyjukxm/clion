// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.daemon.OCCppChecker;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import com.jetbrains.cidr.lang.daemon.OCArgumentsChecker;

public class OCTypeCheckArgumentsChecker extends OCArgumentsChecker
{
    @NotNull
    private final List<OCType.TypeCheckResult> myResults;
    @NotNull
    private final OCResolveContext myContext;
    
    public OCTypeCheckArgumentsChecker(@NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeCheckArgumentsChecker", "<init>"));
        }
        this.myResults = new ArrayList<OCType.TypeCheckResult>();
        this.myContext = myContext;
    }
    
    @NotNull
    public List<OCType.TypeCheckResult> getResults() {
        List<OCType.TypeCheckResult> myResults;
        try {
            myResults = this.myResults;
            if (myResults == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeCheckArgumentsChecker", "getResults"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myResults;
    }
    
    @Override
    protected void checkAssignment(final OCExpression annotationElement, final PsiElement psiElement, final OCType ocType, final OCType ocType2, final OCSymbol ocSymbol, final OCType ocType3, final boolean b, final String s) {
        final OCType.TypeCheckResult checkCompatible = ocType.checkCompatible(ocType2, annotationElement, (PsiElement)annotationElement, b, true, this.myContext);
        Label_0043: {
            try {
                if (checkCompatible.getState().isOK()) {
                    break Label_0043;
                }
                final OCType.TypeCheckResult typeCheckResult = checkCompatible;
                final PsiElement psiElement2 = typeCheckResult.getAnnotationElement();
                if (psiElement2 == null) {
                    break Label_0043;
                }
                break Label_0043;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCType.TypeCheckResult typeCheckResult = checkCompatible;
                final PsiElement psiElement2 = typeCheckResult.getAnnotationElement();
                if (psiElement2 == null) {
                    checkCompatible.setAnnotationElement((PsiElement)annotationElement);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.myResults.add(checkCompatible);
    }
    
    @Nullable
    @Override
    protected Annotation addWarningAnnotation(@Nullable final PsiElement annotationElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/types/visitors/OCTypeCheckArgumentsChecker", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (problemHighlightType == ProblemHighlightType.LIKE_UNUSED_SYMBOL) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCType.TypeCheckResult typeCheckResult = new OCType.TypeCheckResult(OCType.TypeCheckState.WARNING, clazz, s, annotationElement, new IntentionAction[0]) {
            @Override
            public String getMessage() {
                return s2;
            }
        };
        Label_0107: {
            try {
                if (typeCheckResult.getState().isOK()) {
                    break Label_0107;
                }
                final OCType.TypeCheckResult typeCheckResult2 = typeCheckResult;
                final PsiElement psiElement = typeCheckResult2.getAnnotationElement();
                if (psiElement == null) {
                    break Label_0107;
                }
                break Label_0107;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCType.TypeCheckResult typeCheckResult2 = typeCheckResult;
                final PsiElement psiElement = typeCheckResult2.getAnnotationElement();
                if (psiElement == null) {
                    typeCheckResult.setAnnotationElement(annotationElement);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        this.myResults.add(typeCheckResult);
        return null;
    }
    
    @Nullable
    @Override
    protected Annotation addErrorAnnotation(@Nullable final PsiElement annotationElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, final IntentionAction... array) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/types/visitors/OCTypeCheckArgumentsChecker", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType.TypeCheckResult typeCheckResult = new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR, clazz, s, annotationElement, new IntentionAction[0]) {
            @Override
            public String getMessage() {
                return s2;
            }
        };
        Label_0093: {
            try {
                if (typeCheckResult.getState().isOK()) {
                    break Label_0093;
                }
                final OCType.TypeCheckResult typeCheckResult2 = typeCheckResult;
                final PsiElement psiElement = typeCheckResult2.getAnnotationElement();
                if (psiElement == null) {
                    break Label_0093;
                }
                break Label_0093;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCType.TypeCheckResult typeCheckResult2 = typeCheckResult;
                final PsiElement psiElement = typeCheckResult2.getAnnotationElement();
                if (psiElement == null) {
                    typeCheckResult.setAnnotationElement(annotationElement);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        this.myResults.add(typeCheckResult);
        return null;
    }
    
    @Override
    protected void checkConstructor(final OCCompoundInitializer ocCompoundInitializer, OCSymbol ocFunctionSymbol) {
        Label_0072: {
            Label_0038: {
                try {
                    if (!(ocFunctionSymbol instanceof OCFunctionSymbol)) {
                        break Label_0072;
                    }
                    if (!(ocFunctionSymbol instanceof OCResolveOverloadsUtil.OCFunctionGroupSymbol)) {
                        break Label_0038;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                ocFunctionSymbol = ((OCResolveOverloadsUtil.OCFunctionGroupSymbol)ocFunctionSymbol).getOverloads().get(0);
            }
            this.checkFunctionArguments(ocCompoundInitializer, (OCFunctionType)ocFunctionSymbol.getType().resolve(ocCompoundInitializer.getContainingFile()), (List<OCExpression>)Collections.singletonList(ocCompoundInitializer), ocFunctionSymbol);
            return;
        }
        if (ocFunctionSymbol instanceof OCStructSymbol) {
            final List<OCExpression> initializerExpressions = ocCompoundInitializer.getInitializerExpressions();
            try {
                if (initializerExpressions.size() == 1) {
                    this.checkAssignment(initializerExpressions.get(0), (PsiElement)ocCompoundInitializer, ocFunctionSymbol.getType(), initializerExpressions.get(0).getResolvedType(), null, null, true, "Parameter type mismatch: ");
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (initializerExpressions.size() <= 0) {
                    if (((OCStructSymbol)ocFunctionSymbol).hasDefaultConstructor()) {
                        return;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            this.addErrorAnnotation((PsiElement)ocCompoundInitializer, OCInspections.CannotResolve.class, "CIDR", OCCppChecker.getCantResolveCtorMessage(ocFunctionSymbol, new OCFunctionType(OCVoidType.instance(), ContainerUtil.map((Collection)initializerExpressions, ocExpression -> OCExpectedTypeUtil.getExpressionType(ocExpression, true))), (PsiElement)ocCompoundInitializer), new IntentionAction[0]);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
