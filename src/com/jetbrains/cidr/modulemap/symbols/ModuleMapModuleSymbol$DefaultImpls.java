// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import org.jetbrains.annotations.Nullable;
import java.util.List;
import kotlin._Assertions;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3)
public static final class DefaultImpls
{
    @NotNull
    public static String getQualifiedName(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        String s;
        if (moduleMapModuleSymbol.getParent() == null) {
            s = moduleMapModuleSymbol.getName();
        }
        else {
            final StringBuilder append = new StringBuilder().append("");
            final ModuleMapModuleSymbol parent = moduleMapModuleSymbol.getParent();
            if (parent == null) {
                Intrinsics.throwNpe();
            }
            s = append.append(parent.getQualifiedName()).append('.').append(moduleMapModuleSymbol.getName()).toString();
        }
        return s;
    }
    
    @NotNull
    public static ModuleMapModuleSymbol getTopmostParentOrThis(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        ModuleMapModuleSymbol topmostParentOrThis;
        if (moduleMapModuleSymbol.getParent() == null) {
            topmostParentOrThis = moduleMapModuleSymbol;
        }
        else {
            final ModuleMapModuleSymbol parent = moduleMapModuleSymbol.getParent();
            if (parent == null) {
                Intrinsics.throwNpe();
            }
            topmostParentOrThis = parent.getTopmostParentOrThis();
        }
        return topmostParentOrThis;
    }
    
    public static boolean isWildcardExport(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        return Intrinsics.areEqual((Object)moduleMapModuleSymbol.getExportModules(), (Object)ModuleMapModuleSymbol.Companion.getMODULE_EXPORT_WILDCARD());
    }
    
    @Nullable
    public static ModuleMapModuleSymbol findSubModule(@NotNull final ModuleMapModuleSymbol moduleMapModuleSymbol, final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "partlyQualifiedName");
        if (!StringsKt.contains$default((CharSequence)s, '.', false, 2, (Object)null)) {
            return a(moduleMapModuleSymbol, s);
        }
        final List split$default = StringsKt.split$default((CharSequence)s, new char[] { '.' }, false, 2, 2, (Object)null);
        final boolean b = split$default.size() == 2;
        if (_Assertions.ENABLED && !b) {
            throw new AssertionError((Object)"Assertion failed");
        }
        final ModuleMapModuleSymbol a = a(moduleMapModuleSymbol, split$default.get(0));
        return (a != null) ? a.findSubModule(split$default.get(1)) : null;
    }
    
    private static ModuleMapModuleSymbol a(final ModuleMapModuleSymbol moduleMapModuleSymbol, final String s) {
        return Intrinsics.areEqual((Object)moduleMapModuleSymbol.getName(), (Object)s) ? moduleMapModuleSymbol : moduleMapModuleSymbol.getSubModules().get(s);
    }
}
