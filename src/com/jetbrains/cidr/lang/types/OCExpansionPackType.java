// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class OCExpansionPackType extends OCType
{
    private List<OCTypeArgument> myExpansions;
    
    public OCExpansionPackType() {
        this.myExpansions = Collections.emptyList();
    }
    
    public OCExpansionPackType(@NotNull final List<OCTypeArgument> list) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expansions", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "<init>"));
        }
        this.myExpansions = Collections.emptyList();
        this.myExpansions = Collections.unmodifiableList((List<? extends OCTypeArgument>)list);
    }
    
    public OCExpansionPackType appendTypeArgument(@NotNull final OCTypeArgument ocTypeArgument) {
        try {
            if (ocTypeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeArgument", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "appendTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCExpansionPackType(ContainerUtil.append((List)this.myExpansions, (Object[])new OCTypeArgument[] { ocTypeArgument }));
    }
    
    @NotNull
    public List<OCTypeArgument> getExpansions() {
        List<OCTypeArgument> myExpansions;
        try {
            myExpansions = this.myExpansions;
            if (myExpansions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "getExpansions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExpansions;
    }
    
    public int getExpansionsCnt() {
        return this.myExpansions.size();
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "deepEqualStep"));
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
        return comparator.equalObjects(((OCExpansionPackType)o).myExpansions, ((OCExpansionPackType)o2).myExpansions);
    }
    
    @Override
    public int hashCode() {
        return this.myExpansions.hashCode();
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitExpansionPackType(this);
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCExpansionPackType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCTypeArgument ocTypeArgument : this.myExpansions) {
            try {
                if (!(ocTypeArgument instanceof OCType)) {
                    continue;
                }
                final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                final OCType ocType = (OCType)ocTypeArgument2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType.isMagicInside(ocResolveContext2);
                if (b) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                final OCType ocType = (OCType)ocTypeArgument2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType.isMagicInside(ocResolveContext2);
                if (b) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
