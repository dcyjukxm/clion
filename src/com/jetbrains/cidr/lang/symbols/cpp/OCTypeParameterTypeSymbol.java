// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import java.util.Collections;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCTypeParameterTypeSymbol extends OCSymbolImpl<OCElement> implements OCTypeParameterSymbol<OCType>
{
    @Nullable
    private OCType myDefaultValue;
    private boolean myVariadic;
    private OCTypeParameterTypeSymbol myQualifierTypeParameter;
    private TextRange myScope;
    
    public OCTypeParameterTypeSymbol() {
        this(null, null, 0L, "<unnamed>", null, Collections.emptyList(), null, false);
    }
    
    public OCTypeParameterTypeSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @Nullable final OCTypeParameterTypeSymbol myQualifierTypeParameter, @Nullable final OCType myDefaultValue, @NotNull final List<String> list, final TextRange myScope, final boolean myVariadic) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, list);
        this.myQualifierTypeParameter = myQualifierTypeParameter;
        this.myScope = myScope;
        this.myDefaultValue = myDefaultValue;
        this.myVariadic = myVariadic;
    }
    
    public OCTypeParameterTypeSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @Nullable final OCType ocType, @NotNull final List<String> list, final TextRange textRange, final boolean b) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "<init>"));
        }
        this(project, virtualFile, n, s, null, ocType, list, textRange, b);
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCTypeParameterTypeSymbol ocTypeParameterTypeSymbol = (OCTypeParameterTypeSymbol)o;
        final OCTypeParameterTypeSymbol ocTypeParameterTypeSymbol2 = (OCTypeParameterTypeSymbol)o2;
        try {
            if (!comparator.equalObjects(ocTypeParameterTypeSymbol.myQualifierTypeParameter, (DeepEqual.Equality<Object>)ocTypeParameterTypeSymbol2.myQualifierTypeParameter)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocTypeParameterTypeSymbol.myScope, (Object)ocTypeParameterTypeSymbol2.myScope)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!comparator.equalObjects(ocTypeParameterTypeSymbol.myDefaultValue, (DeepEqual.Equality<Object>)ocTypeParameterTypeSymbol2.myDefaultValue)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        try {
            if (this.myVariadic != ocTypeParameterTypeSymbol.myVariadic) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        return true;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCTypeParameterType ocTypeParameterType;
        try {
            ocTypeParameterType = new OCTypeParameterType(this);
            if (ocTypeParameterType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocTypeParameterType;
    }
    
    public OCTypeParameterTypeSymbol getQualifierTypeParameter() {
        return this.myQualifierTypeParameter;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind template_TYPE_PARAMETER;
        try {
            template_TYPE_PARAMETER = OCSymbolKind.TEMPLATE_TYPE_PARAMETER;
            if (template_TYPE_PARAMETER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return template_TYPE_PARAMETER;
    }
    
    @Nullable
    @Override
    public OCType getDefaultValue() {
        return this.myDefaultValue;
    }
    
    @Override
    public boolean isVariadic() {
        return this.myVariadic;
    }
    
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
