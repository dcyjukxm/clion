// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class OCMagicType extends OCType
{
    public static final String UNKNOWN = "<unknown>";
    @NotNull
    private String myName;
    @Nullable
    private OCType myGuessedType;
    
    public OCMagicType(@NotNull final String myName, @Nullable final OCType myGuessedType, final boolean b, final boolean b2) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/OCMagicType", "<init>"));
        }
        super(b, b2);
        this.myName = myName;
        this.myGuessedType = myGuessedType;
    }
    
    public OCMagicType(final String s) {
        this(s, false, false);
    }
    
    public OCMagicType(final String s, final boolean b, final boolean b2) {
        this((s != null) ? s : "<unknown>", null, b, b2);
    }
    
    public OCMagicType() {
        this("<unknown>", null, false, false);
    }
    
    public OCMagicType(final OCType ocType) {
        this(ocType.getName(), ocType, false, false);
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitMagicType(this);
    }
    
    @NotNull
    public String getMagicName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCMagicType", "getMagicName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @Override
    public boolean isInstanceable() {
        return true;
    }
    
    @Override
    public boolean isUnknown() {
        return true;
    }
    
    @Override
    public boolean isCppStructType() {
        return true;
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCMagicType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    public OCType getRefType() {
        final OCType guessedUnmagicType = this.getGuessedUnmagicType();
        try {
            if (guessedUnmagicType instanceof OCPointerType) {
                return new OCMagicType(((OCPointerType)guessedUnmagicType).getRefType());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this;
    }
    
    @NotNull
    @Override
    public OCType getTerminalType() {
        final OCType guessedUnmagicType = this.getGuessedUnmagicType();
        Label_0069: {
            OCMagicType ocMagicType = null;
            Label_0034: {
                try {
                    if (!(guessedUnmagicType instanceof OCPointerType)) {
                        break Label_0069;
                    }
                    final OCType ocType = guessedUnmagicType;
                    final OCType ocType2 = ocType.getTerminalType();
                    ocMagicType = new OCMagicType(ocType2);
                    if (ocMagicType == null) {
                        break Label_0034;
                    }
                    return ocMagicType;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCType ocType = guessedUnmagicType;
                    final OCType ocType2 = ocType.getTerminalType();
                    ocMagicType = new OCMagicType(ocType2);
                    if (ocMagicType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCMagicType", "getTerminalType"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocMagicType;
            try {
                if (this == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCMagicType", "getTerminalType"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return this;
    }
    
    @NotNull
    @Override
    public OCType getGuessedUnmagicType() {
        OCType ocType = null;
        Label_0022: {
            try {
                if (this.myGuessedType != null) {
                    final OCType guessedUnmagicType;
                    ocType = (guessedUnmagicType = this.myGuessedType.getGuessedUnmagicType());
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ocType = this;
            OCType guessedUnmagicType = this;
            try {
                if (guessedUnmagicType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCMagicType", "getGuessedUnmagicType"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCMagicType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCMagicType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCMagicType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCMagicType ocMagicType = (OCMagicType)o;
        final OCMagicType ocMagicType2 = (OCMagicType)o2;
        try {
            if (!ocMagicType.myName.equals(ocMagicType2.myName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalObjects(ocMagicType.myGuessedType, (DeepEqual.Equality<Object>)ocMagicType2.myGuessedType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode() * 31 + this.myName.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
