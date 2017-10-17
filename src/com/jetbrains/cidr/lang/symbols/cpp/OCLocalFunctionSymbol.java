// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;

public class OCLocalFunctionSymbol extends OCFunctionSymbol
{
    private TextRange myScope;
    
    public OCLocalFunctionSymbol() {
    }
    
    public OCLocalFunctionSymbol(final Project project, final VirtualFile virtualFile, final long n, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCQualifiedName ocQualifiedName, final List<OCTypeParameterSymbol> list, @Nullable final List<OCTypeArgument> list2, final int n2, final int n3, @NotNull final List<String> list3, @NotNull final OCFunctionType ocFunctionType, final List<OCDeclaratorSymbol> list4, final OCSymbolKind ocSymbolKind, final TextRange myScope) {
        if (list3 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol", "<init>"));
        }
        if (ocFunctionType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, list2, n2, n3, list3, ocFunctionType, list4, ocSymbolKind, null);
        this.myScope = myScope;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw e(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw e(ex4);
        }
        return Comparing.equal((Object)((OCLocalFunctionSymbol)o).myScope, (Object)((OCLocalFunctionSymbol)o2).myScope);
    }
    
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    private static IllegalArgumentException e(final IllegalArgumentException ex) {
        return ex;
    }
}
