// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCAnnotatorHelper
{
    public static final ExtensionPointName<OCAnnotatorHelper> EP_NAME = ExtensionPointName.create("cidr.lang.annotatorHelper");
    
    boolean shouldHighlight(@NotNull final PsiFile p0);
    
    int getClangVersion();
    
    @Nullable
    String checkAvailability(@NotNull final OCSymbol p0, @NotNull final OCResolveConfiguration p1);
    
    void checkDynamicIVar(@NotNull final OCAnnotatorSink p0, @NotNull final OCSynthesizeProperty p1, @NotNull final OCPropertySymbol p2, @NotNull final OCReferenceElement p3);
    
    boolean processReference(@NotNull final PsiReference p0, @NotNull final Ref<Class<? extends OCInspection>> p1, @NotNull final Ref<String> p2, @NotNull final Ref<OCClassSymbol> p3, @NotNull final Ref<String> p4);
}
