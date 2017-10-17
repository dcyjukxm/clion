// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;

public class OCVoidType extends OCType
{
    private static final OCVoidType INSTANCE;
    private static final OCVoidType CONST_INSTANCE;
    private static final OCVoidType VOLATILE_INSTANCE;
    private static final OCVoidType CONST_VOLATILE_INSTANCE;
    
    private OCVoidType(final boolean b, final boolean b2) {
        super(b, b2);
    }
    
    public static OCVoidType instance() {
        return OCVoidType.INSTANCE;
    }
    
    public static OCVoidType instance(final boolean b, final boolean b2) {
        Label_0029: {
            Label_0015: {
                try {
                    if (!b2) {
                        break Label_0029;
                    }
                    final boolean b3 = b;
                    if (b3) {
                        break Label_0015;
                    }
                    return OCVoidType.VOLATILE_INSTANCE;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final boolean b3 = b;
                    if (b3) {
                        return OCVoidType.CONST_VOLATILE_INSTANCE;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return OCVoidType.VOLATILE_INSTANCE;
            try {
                if (b) {
                    return OCVoidType.CONST_INSTANCE;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return OCVoidType.INSTANCE;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitVoidType(this);
    }
    
    @Override
    public boolean isVoid() {
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode();
    }
    
    @Override
    public void attachAliasName(@Nullable final String s) {
    }
    
    @Override
    public void attachGuessedType(@Nullable final OCType ocType) {
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCVoidType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCVoidType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCVoidType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (o == o2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return false;
    }
    
    @NotNull
    @Override
    public String getDefaultValue(@Nullable final PsiElement psiElement) {
        String s;
        try {
            s = "";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCVoidType", "getDefaultValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    static {
        INSTANCE = new OCVoidType(false, false);
        CONST_INSTANCE = new OCVoidType(true, false);
        VOLATILE_INSTANCE = new OCVoidType(false, true);
        CONST_VOLATILE_INSTANCE = new OCVoidType(true, true);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
