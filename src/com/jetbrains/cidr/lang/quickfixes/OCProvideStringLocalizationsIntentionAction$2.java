// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.actions.newFile.OCNewStringsFileAction;

class OCProvideStringLocalizationsIntentionAction$2 extends OCNewStringsFileAction {
    @Override
    protected boolean isNameFieldEnabled() {
        return false;
    }
    
    @Override
    protected void openCreatedFiles(@NotNull final PsiFile[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "createdElements", "com/jetbrains/cidr/lang/quickfixes/OCProvideStringLocalizationsIntentionAction$2", "openCreatedFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}