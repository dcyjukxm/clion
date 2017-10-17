// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import com.intellij.lang.Language;
import com.jetbrains.cidr.modulemap.ModuleMapLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class ModuleMapElementType extends IElementType
{
    public ModuleMapElementType(@NotNull @NonNls final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/modulemap/psi/ModuleMapElementType", "<init>"));
        }
        super(s, (Language)ModuleMapLanguage.INSTANCE);
    }
}
