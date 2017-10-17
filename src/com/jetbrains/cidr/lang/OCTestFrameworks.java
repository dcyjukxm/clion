// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;

public class OCTestFrameworks
{
    @NotNull
    public static OCTestFramework[] getFrameworks() {
        OCTestFramework[] array;
        try {
            array = (OCTestFramework[])Extensions.getExtensions((ExtensionPointName)OCTestFramework.EP_NAME);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCTestFrameworks", "getFrameworks"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    public static boolean isTestClassOrStruct(@Nullable final OCSymbol ocSymbol) {
        for (final OCTestFramework ocTestFramework : getFrameworks()) {
            try {
                if (ocTestFramework.isTestClassOrStruct(ocSymbol)) {
                    return true;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    public static boolean isTestClass(@Nullable final PsiElement psiElement) {
        for (final OCTestFramework ocTestFramework : getFrameworks()) {
            try {
                if (ocTestFramework.isTestClass(psiElement)) {
                    return true;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    public static boolean isTestMethodOrFunction(@Nullable final OCSymbol ocSymbol) {
        for (final OCTestFramework ocTestFramework : getFrameworks()) {
            try {
                if (ocTestFramework.isTestMethodOrFunction(ocSymbol)) {
                    return true;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
