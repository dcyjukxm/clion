// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public static class OCFunctionGroupSymbol extends OCFunctionSymbol
{
    private List<OCFunctionSymbol> myOverloads;
    private Cause myCause;
    
    public OCFunctionGroupSymbol(@NotNull final List<OCFunctionSymbol> myOverloads, @Nullable final OCType ocType, @NotNull final Cause myCause) {
        if (myOverloads == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "overloads", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "<init>"));
        }
        if (myCause == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cause", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "<init>"));
        }
        super(myOverloads.get(0).getProject(), myOverloads.get(0).getContainingFile(), 0L, myOverloads.get(0).getParent(), myOverloads.get(0).getQualifiedName(), (List<OCTypeParameterSymbol>)Collections.emptyList(), Collections.emptyList(), 0, OCSymbolAttribute.STATIC.getMask(), Collections.emptyList(), a(myOverloads, ocType), a(myOverloads), a(myOverloads, OCSymbolKind.FUNCTION_PREDECLARATION), null);
        this.myOverloads = myOverloads;
        this.myCause = myCause;
    }
    
    private static List<OCDeclaratorSymbol> a(final List<OCFunctionSymbol> list) {
        return list.get(0).getParameterSymbols();
    }
    
    private static OCFunctionType a(final List<OCFunctionSymbol> list, @Nullable OCType ocType) {
        if (ocType == null) {
            ocType = new OCMagicType(list.get(0).getType().getReturnType());
        }
        return new OCFunctionType(ocType, (List<OCType>)Collections.singletonList(OCEllipsisType.instance()));
    }
    
    private static OCSymbolKind a(final List<OCFunctionSymbol> list, final OCSymbolKind ocSymbolKind) {
        OCSymbolKind kind = null;
        for (final OCFunctionSymbol ocFunctionSymbol : list) {
            if (kind == null) {
                kind = ocFunctionSymbol.getKind();
            }
            else {
                try {
                    if (!ocFunctionSymbol.getKind().isSame(kind)) {
                        return ocSymbolKind;
                    }
                    continue;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
            }
        }
        try {
            if (kind != null) {
                return kind;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return ocSymbolKind;
    }
    
    public List<OCFunctionSymbol> getOverloads() {
        return this.myOverloads;
    }
    
    public Cause getCause() {
        return this.myCause;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "deepEqualStep"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "deepEqualStep"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "deepEqualStep"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        throw new UnsupportedOperationException("this symbol is synthetic and should not be interned");
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
    
    public enum Cause
    {
        Ambiguous, 
        NoViable, 
        Magic;
    }
}
