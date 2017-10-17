// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.asm.AsmLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class AsmElementType extends IElementType
{
    public AsmElementType(@NotNull @NonNls final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/lang/asm/psi/AsmElementType", "<init>"));
        }
        super(s, (Language)AsmLanguage.INSTANCE);
    }
}
