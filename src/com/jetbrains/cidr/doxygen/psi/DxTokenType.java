// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import com.intellij.lang.Language;
import com.jetbrains.cidr.doxygen.DxLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class DxTokenType extends IElementType
{
    public DxTokenType(@NotNull @NonNls final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/doxygen/psi/DxTokenType", "<init>"));
        }
        super(s, (Language)DxLanguage.INSTANCE);
    }
    
    public String toString() {
        return "DxTokenType." + super.toString();
    }
}
