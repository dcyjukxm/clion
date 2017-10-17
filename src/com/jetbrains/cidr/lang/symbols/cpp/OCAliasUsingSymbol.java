// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;
import java.io.Serializable;

public class OCAliasUsingSymbol extends OCTemplateSymbolImpl implements Serializable
{
    private OCType myType;
    @NotNull
    private List<OCTypeParameterSymbol> myTemplateParameters;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    @Nullable
    private TextRange myScope;
    
    public OCAliasUsingSymbol() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCAliasUsingSymbol(final Project project, final VirtualFile virtualFile, final long n, @NotNull final String s, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final List<OCTypeParameterSymbol> myTemplateParameters, @Nullable final TextRange myScope) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "<init>"));
        }
        if (myTemplateParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateParameters", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, OCQualifiedName.with(s), Collections.emptyList(), null);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myScope = myScope;
        this.myTemplateParameters = myTemplateParameters;
    }
    
    public OCAliasUsingSymbol(final OCAliasUsingSymbol ocAliasUsingSymbol, final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "<init>"));
        }
        super(ocAliasUsingSymbol.getProject(), ocAliasUsingSymbol.getContainingFile(), ocAliasUsingSymbol.getOffset(), ocAliasUsingSymbol.getParent(), ocAliasUsingSymbol.getQualifiedName(), Collections.emptyList(), null);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myType = ocAliasUsingSymbol.myType;
        this.myTemplateParameters = ocAliasUsingSymbol.myTemplateParameters;
        this.mySubstitution = OCTypeSubstitution.compose(ocAliasUsingSymbol.mySubstitution, ocTypeSubstitution, ocResolveContext);
        this.myScope = ocAliasUsingSymbol.myScope;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType substitute;
        try {
            substitute = this.mySubstitution.substitute(this.myType, new OCResolveContext());
            if (substitute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return substitute;
    }
    
    public void setType(final OCType myType) {
        this.myType = myType;
    }
    
    @Nullable
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    @NotNull
    @Override
    public List<OCTypeParameterSymbol> getTemplateParameters() {
        List<OCTypeParameterSymbol> myTemplateParameters;
        try {
            myTemplateParameters = this.myTemplateParameters;
            if (myTemplateParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "getTemplateParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return myTemplateParameters;
    }
    
    @Nullable
    @Override
    public List<OCTypeArgument> getTemplateSpecialization() {
        return null;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySubstitution;
    }
    
    @Override
    public boolean isTemplateSymbol() {
        try {
            if (!this.myTemplateParameters.isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw d(ex4);
        }
        final OCAliasUsingSymbol ocAliasUsingSymbol = (OCAliasUsingSymbol)o;
        final OCAliasUsingSymbol ocAliasUsingSymbol2 = (OCAliasUsingSymbol)o2;
        try {
            if (!Comparing.equal((Object)ocAliasUsingSymbol.myScope, (Object)ocAliasUsingSymbol2.myScope)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw d(ex5);
        }
        try {
            if (!comparator.equalObjects(ocAliasUsingSymbol.mySubstitution, (DeepEqual.Equality<Object>)ocAliasUsingSymbol2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw d(ex6);
        }
        try {
            if (!comparator.equalIterable(ocAliasUsingSymbol.myTemplateParameters, ocAliasUsingSymbol2.myTemplateParameters)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw d(ex7);
        }
        try {
            if (!comparator.equalObjects(ocAliasUsingSymbol.myType, (DeepEqual.Equality<Object>)ocAliasUsingSymbol2.myType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw d(ex8);
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "using " + this.myName + ' ' + this.myType.getName();
    }
    
    @Override
    public boolean isGlobal() {
        try {
            if (this.myScope == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind using_SYMBOL_ALIAS;
        try {
            using_SYMBOL_ALIAS = OCSymbolKind.USING_SYMBOL_ALIAS;
            if (using_SYMBOL_ALIAS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return using_SYMBOL_ALIAS;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
