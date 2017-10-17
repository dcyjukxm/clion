// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorSink;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorHelper;

public class CPPAnnotationHelper implements OCAnnotatorHelper
{
    @Override
    public boolean shouldHighlight(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "shouldHighlight"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @Nullable
    @Override
    public String checkAvailability(@NotNull final OCSymbol ocSymbol, @NotNull final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolToCheck", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "checkAvailability"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inConfiguration", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "checkAvailability"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Override
    public void checkDynamicIVar(@NotNull final OCAnnotatorSink ocAnnotatorSink, @NotNull final OCSynthesizeProperty ocSynthesizeProperty, @NotNull final OCPropertySymbol ocPropertySymbol, @NotNull final OCReferenceElement ocReferenceElement) {
        try {
            if (ocAnnotatorSink == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sink", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "checkDynamicIVar"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSynthesizeProperty == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "property", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "checkDynamicIVar"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocPropertySymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "propertySymbol", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "checkDynamicIVar"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ocReferenceElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarRef", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "checkDynamicIVar"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    @Override
    public boolean processReference(@NotNull final PsiReference psiReference, @NotNull final Ref<Class<? extends OCInspection>> ref, @NotNull final Ref<String> ref2, @NotNull final Ref<OCClassSymbol> ref3, @NotNull final Ref<String> ref4) {
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "processReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inspectionClass", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "processReference"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "processReference"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ref3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "receiverClassRef", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "processReference"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (ref4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "referencedName", "com/jetbrains/cidr/lang/CPPAnnotationHelper", "processReference"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return false;
    }
    
    @Override
    public int getClangVersion() {
        return -1;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
