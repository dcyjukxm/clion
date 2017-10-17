// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

abstract class OCTemplateSymbolImpl<T extends PsiElement> extends OCSymbolWithQualifiedName<T> implements OCTemplateSymbol<T>
{
    public OCTemplateSymbolImpl() {
    }
    
    public OCTemplateSymbolImpl(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCQualifiedName ocQualifiedName, @NotNull final List<String> list, @Nullable final OCVisibility ocVisibility) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbolImpl", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, ocVisibility);
    }
    
    public static int getRequiredTemplateArgumentsCnt(@NotNull final OCTemplateSymbol<?> ocTemplateSymbol) {
        try {
            if (ocTemplateSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbolImpl", "getRequiredTemplateArgumentsCnt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        int n = 0;
        for (final OCTypeParameterSymbol ocTypeParameterSymbol : ocTemplateSymbol.getTemplateParameters()) {
            try {
                if (ocTypeParameterSymbol.getDefaultValue() != null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            ++n;
        }
        try {
            if (ocTemplateSymbol.isVariadicTemplate()) {
                return n - 1;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return n;
    }
    
    @Override
    public int getRequiredTemplateArgumentsCnt() {
        return getRequiredTemplateArgumentsCnt(this);
    }
    
    @Override
    public boolean isSpecialization() {
        try {
            if (this.getTemplateSpecialization() != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Override
    public boolean isExplicitInstantiation() {
        Label_0026: {
            try {
                if (!this.isTemplateSymbol()) {
                    return false;
                }
                final OCTemplateSymbolImpl ocTemplateSymbolImpl = this;
                final List<OCTypeParameterSymbol> list = ocTemplateSymbolImpl.getTemplateParameters();
                final boolean b = list.isEmpty();
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCTemplateSymbolImpl ocTemplateSymbolImpl = this;
                final List<OCTypeParameterSymbol> list = ocTemplateSymbolImpl.getTemplateParameters();
                final boolean b = list.isEmpty();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    public static boolean isVariadicTemplate(@NotNull final OCTemplateSymbol<?> ocTemplateSymbol) {
        try {
            if (ocTemplateSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbolImpl", "isVariadicTemplate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final List templateParameters = ocTemplateSymbol.getTemplateParameters();
        int n = templateParameters.size() - 1;
        while (true) {
            Label_0095: {
                try {
                    if (n < 0) {
                        break;
                    }
                    final List<OCTypeParameterSymbol> list = (List<OCTypeParameterSymbol>)templateParameters;
                    final int n2 = n;
                    final OCTypeParameterSymbol ocTypeParameterSymbol = list.get(n2);
                    final OCTypeParameterSymbol ocTypeParameterSymbol2 = ocTypeParameterSymbol;
                    final boolean b = ocTypeParameterSymbol2.isVariadic();
                    if (b) {
                        return true;
                    }
                    break Label_0095;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final List<OCTypeParameterSymbol> list = (List<OCTypeParameterSymbol>)templateParameters;
                    final int n2 = n;
                    final OCTypeParameterSymbol ocTypeParameterSymbol = list.get(n2);
                    final OCTypeParameterSymbol ocTypeParameterSymbol2 = ocTypeParameterSymbol;
                    final boolean b = ocTypeParameterSymbol2.isVariadic();
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            --n;
        }
        return false;
    }
    
    @Override
    public boolean isVariadicTemplate() {
        return isVariadicTemplate(this);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
