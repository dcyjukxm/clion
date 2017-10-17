// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;

public class OCNamespaceAliasSymbol extends OCSymbolWithQualifiedName
{
    private OCSymbolReference myNamespaceReference;
    @Nullable
    private TextRange myScope;
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final OCNamespaceAliasSymbol ocNamespaceAliasSymbol = (OCNamespaceAliasSymbol)o;
        final OCNamespaceAliasSymbol ocNamespaceAliasSymbol2 = (OCNamespaceAliasSymbol)o2;
        try {
            if (!Comparing.equal((Object)ocNamespaceAliasSymbol.myScope, (Object)ocNamespaceAliasSymbol2.myScope)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocNamespaceAliasSymbol.myNamespaceReference, (DeepEqual.Equality<Object>)ocNamespaceAliasSymbol2.myNamespaceReference)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        return true;
    }
    
    public OCNamespaceAliasSymbol() {
    }
    
    public OCNamespaceAliasSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCQualifiedName ocQualifiedName, final OCSymbolReference myNamespaceReference, @Nullable final TextRange myScope) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, Collections.emptyList(), null);
        this.myScope = myScope;
        this.myNamespaceReference = myNamespaceReference;
    }
    
    public OCSymbolReference getNamespaceReference() {
        return this.myNamespaceReference;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind namespace_ALIAS;
        try {
            namespace_ALIAS = OCSymbolKind.NAMESPACE_ALIAS;
            if (namespace_ALIAS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return namespace_ALIAS;
    }
    
    @Nullable
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    @Override
    public boolean isGlobal() {
        try {
            if (this.myScope == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
