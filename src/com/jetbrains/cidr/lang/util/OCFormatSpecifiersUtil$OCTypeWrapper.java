// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

private static class OCTypeWrapper
{
    final String myTypeName;
    final boolean myTypeNameIsPointer;
    final OCType myType;
    
    public OCTypeWrapper(@NotNull final OCType myType) {
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$OCTypeWrapper", "<init>"));
        }
        this.myType = myType;
        this.myTypeName = null;
        this.myTypeNameIsPointer = false;
    }
    
    public OCTypeWrapper(@NotNull final String myTypeName, final boolean myTypeNameIsPointer) {
        if (myTypeName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$OCTypeWrapper", "<init>"));
        }
        this.myType = null;
        this.myTypeName = myTypeName;
        this.myTypeNameIsPointer = myTypeNameIsPointer;
    }
    
    public OCType getTypeFromContext(@Nullable final PsiElement psiElement) {
        try {
            if (this.myType != null) {
                return this.myType;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String myTypeName = null;
        PsiFile containingFile = null;
        Label_0038: {
            try {
                myTypeName = this.myTypeName;
                if (psiElement == null) {
                    containingFile = null;
                    break Label_0038;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            containingFile = psiElement.getContainingFile();
        }
        final OCType resolvedFromText = OCReferenceType.resolvedFromText(myTypeName, containingFile);
        try {
            if (this.myTypeNameIsPointer) {
                return OCPointerType.to(resolvedFromText);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return resolvedFromText;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
