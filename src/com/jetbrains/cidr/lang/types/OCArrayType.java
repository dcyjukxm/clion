// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.HashSet;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

public class OCArrayType extends OCPointerType
{
    private int myLength;
    
    public OCArrayType() {
        this(null, 0, false, false, null);
    }
    
    private OCArrayType(final OCType ocType, final int myLength, final boolean b, final boolean b2, @Nullable final ARCAttribute arcAttribute) {
        super(ocType, arcAttribute, null, OCNullability.NONNULL, b, b2);
        this.myLength = myLength;
    }
    
    public int getLength() {
        return this.myLength;
    }
    
    public boolean hasLength() {
        try {
            if (this.myLength != -1) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Override
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        try {
            if (this.hasLength()) {
                return this.myLength * this.myRefType.getSizeInBytes(psiFile, ocInclusionContext);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.getSizeInBytes(psiFile, ocInclusionContext);
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCArrayType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCArrayType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCArrayType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCArrayType ocArrayType = (OCArrayType)o;
        final OCArrayType ocArrayType2 = (OCArrayType)o2;
        Label_0174: {
            try {
                if (ocArrayType.myLength != ocArrayType2.myLength) {
                    return false;
                }
                final OCArrayType ocArrayType3 = this;
                final DeepEqual.Comparator comparator2 = comparator;
                final Object o3 = o;
                final Object o4 = o2;
                final boolean b = ocArrayType3.deepEqualStep(comparator2, o3, o4);
                if (b) {
                    break Label_0174;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                final OCArrayType ocArrayType3 = this;
                final DeepEqual.Comparator comparator2 = comparator;
                final Object o3 = o;
                final Object o4 = o2;
                final boolean b = ocArrayType3.deepEqualStep(comparator2, o3, o4);
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
    
    @Override
    public int hashCode() {
        return super.hashCode() * 19 + this.myLength;
    }
    
    public boolean isEmpty(final PsiFile psiFile, final HashSet<OCStructSymbol> set) {
        try {
            if (this.myLength == 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolve = this.myRefType.resolve(psiFile);
        Label_0054: {
            try {
                if (!(resolve instanceof OCArrayType)) {
                    break Label_0054;
                }
                final OCArrayType ocArrayType = (OCArrayType)resolve;
                final OCArrayType ocArrayType2 = ocArrayType;
                final PsiFile psiFile2 = psiFile;
                final HashSet<OCStructSymbol> set2 = set;
                final boolean b = ocArrayType2.isEmpty(psiFile2, set2);
                if (!b) {
                    return false;
                }
                return true;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCArrayType ocArrayType = (OCArrayType)resolve;
                final OCArrayType ocArrayType2 = ocArrayType;
                final PsiFile psiFile2 = psiFile;
                final HashSet<OCStructSymbol> set2 = set;
                final boolean b = ocArrayType2.isEmpty(psiFile2, set2);
                if (!b) {
                    return false;
                }
                return true;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                if (!(resolve instanceof OCStructType)) {
                    return false;
                }
                final OCArrayType ocArrayType3 = (OCArrayType)resolve;
                final OCStructType ocStructType = (OCStructType)ocArrayType3;
                final HashSet<OCStructSymbol> set3 = set;
                final boolean b2 = ocStructType.isEmpty(set3);
                if (!b2) {
                    return false;
                }
                return true;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        try {
            final OCArrayType ocArrayType3 = (OCArrayType)resolve;
            final OCStructType ocStructType = (OCStructType)ocArrayType3;
            final HashSet<OCStructSymbol> set3 = set;
            final boolean b2 = ocStructType.isEmpty(set3);
            if (!b2) {
                return false;
            }
            return true;
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return false;
    }
    
    public static OCArrayType to(final OCType ocType, final int n, @Nullable final ARCAttribute arcAttribute) {
        return new OCArrayType(ocType, n, false, false, arcAttribute);
    }
    
    public static OCArrayType to(final OCType ocType, final int n) {
        return to(ocType, n, null);
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitArrayType(this);
    }
    
    @NotNull
    @Override
    public OCType getArrayElementType() {
        OCType arrayElementType;
        try {
            arrayElementType = this.myRefType.getArrayElementType();
            if (arrayElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCArrayType", "getArrayElementType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return arrayElementType;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
