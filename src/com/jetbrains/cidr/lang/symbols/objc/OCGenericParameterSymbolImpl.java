// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCGenericParameter;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCGenericParameterSymbolImpl extends OCSymbolImpl<OCGenericParameter> implements OCGenericParameterSymbol
{
    @NotNull
    private OCType myConstraintType;
    private Covariance myCovariance;
    
    public OCGenericParameterSymbolImpl() {
    }
    
    public OCGenericParameterSymbolImpl(final Project project, final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final List<String> list, @NotNull final Covariance myCovariance, @NotNull final OCType myConstraintType) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "<init>"));
        }
        if (myCovariance == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "covariance", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "<init>"));
        }
        if (myConstraintType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "constraintType", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list);
        this.myCovariance = myCovariance;
        this.myConstraintType = myConstraintType;
    }
    
    @NotNull
    @Override
    public Covariance getCovariance() {
        Covariance myCovariance;
        try {
            myCovariance = this.myCovariance;
            if (myCovariance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "getCovariance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myCovariance;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind generic_PARAMETER;
        try {
            generic_PARAMETER = OCSymbolKind.GENERIC_PARAMETER;
            if (generic_PARAMETER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return generic_PARAMETER;
    }
    
    @NotNull
    @Override
    public OCType getDefaultValue() {
        OCType myConstraintType;
        try {
            myConstraintType = this.myConstraintType;
            if (myConstraintType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "getDefaultValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myConstraintType;
    }
    
    @Override
    public boolean isVariadic() {
        return false;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbolImpl", "deepEqualStep"));
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
        final OCGenericParameterSymbolImpl ocGenericParameterSymbolImpl = (OCGenericParameterSymbolImpl)o;
        final OCGenericParameterSymbolImpl ocGenericParameterSymbolImpl2 = (OCGenericParameterSymbolImpl)o2;
        try {
            if (!comparator.equalObjects(ocGenericParameterSymbolImpl.myConstraintType, (DeepEqual.Equality<Object>)ocGenericParameterSymbolImpl2.myConstraintType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocGenericParameterSymbolImpl.myCovariance, (Object)ocGenericParameterSymbolImpl2.myCovariance)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
