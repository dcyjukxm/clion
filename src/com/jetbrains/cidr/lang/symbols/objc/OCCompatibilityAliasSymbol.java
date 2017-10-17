// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import java.util.Collections;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.OCType;
import java.io.Serializable;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCCompatibilityAliasSymbol extends OCSymbolImpl implements Serializable
{
    private OCType mySubstitution;
    
    public OCCompatibilityAliasSymbol() {
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        Label_0172: {
            try {
                if (!super.deepEqualStep(comparator, o, o2)) {
                    return false;
                }
                final DeepEqual.Comparator comparator2 = comparator;
                final Object o3 = o;
                final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol = (OCCompatibilityAliasSymbol)o3;
                final OCType ocType = ocCompatibilityAliasSymbol.mySubstitution;
                final Object o4 = o2;
                final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol2 = (OCCompatibilityAliasSymbol)o4;
                final OCType ocType2 = ocCompatibilityAliasSymbol2.mySubstitution;
                final boolean b = comparator2.equalObjects(ocType, (DeepEqual.Equality<Object>)ocType2);
                if (b) {
                    break Label_0172;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                final DeepEqual.Comparator comparator2 = comparator;
                final Object o3 = o;
                final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol = (OCCompatibilityAliasSymbol)o3;
                final OCType ocType = ocCompatibilityAliasSymbol.mySubstitution;
                final Object o4 = o2;
                final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol2 = (OCCompatibilityAliasSymbol)o4;
                final OCType ocType2 = ocCompatibilityAliasSymbol2.mySubstitution;
                final boolean b = comparator2.equalObjects(ocType, (DeepEqual.Equality<Object>)ocType2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return false;
    }
    
    public OCCompatibilityAliasSymbol(final Project project, final VirtualFile virtualFile, final long n, @NotNull final String s, @NotNull final OCType mySubstitution) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "<init>"));
        }
        if (mySubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, Collections.emptyList());
        this.mySubstitution = mySubstitution;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return mySubstitution;
    }
    
    @Override
    public String toString() {
        return "@compatibility_alias " + this.myName + ' ' + this.mySubstitution.getName();
    }
    
    @Override
    public boolean isGlobal() {
        return true;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind compatibility_ALIAS;
        try {
            compatibility_ALIAS = OCSymbolKind.COMPATIBILITY_ALIAS;
            if (compatibility_ALIAS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return compatibility_ALIAS;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
