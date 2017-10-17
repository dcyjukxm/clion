// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.StringBuilderSpinAllocator;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.List;

public class OCQualifiedNameWithArguments extends OCQualifiedName
{
    @NotNull
    private List<OCTypeArgument> myArguments;
    
    public OCQualifiedNameWithArguments(@Nullable final OCQualifiedName myQualifier, @Nullable final String myName, @NotNull final List<OCTypeArgument> myArguments) {
        if (myArguments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "<init>"));
        }
        this.myQualifier = myQualifier;
        this.myName = myName;
        this.myArguments = myArguments;
    }
    
    public OCQualifiedNameWithArguments() {
    }
    
    public OCQualifiedNameWithArguments(@NotNull final OCQualifiedName ocQualifiedName, @NotNull final List<OCTypeArgument> list) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "<init>"));
        }
        this(ocQualifiedName.getQualifier(), ocQualifiedName.getName(), list);
    }
    
    @NotNull
    public List<OCTypeArgument> getArguments() {
        List<OCTypeArgument> myArguments;
        try {
            myArguments = this.myArguments;
            if (myArguments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "getArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myArguments;
    }
    
    @Override
    public OCQualifiedName dropArguments() {
        return OCQualifiedName.with(this.myQualifier, this.myName);
    }
    
    @NotNull
    @Override
    public OCQualifiedName changeQualifier(@Nullable final OCQualifiedName ocQualifiedName) {
        OCQualifiedNameWithArguments ocQualifiedNameWithArguments;
        try {
            ocQualifiedNameWithArguments = new OCQualifiedNameWithArguments(ocQualifiedName, this.myName, this.myArguments);
            if (ocQualifiedNameWithArguments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "changeQualifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocQualifiedNameWithArguments;
    }
    
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + this.myArguments.hashCode();
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCQualifiedName ocQualifiedName, @NotNull final OCQualifiedName ocQualifiedName2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocQualifiedName2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, ocQualifiedName, ocQualifiedName2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return comparator.equalIterable(((OCQualifiedNameWithArguments)ocQualifiedName).myArguments, ((OCQualifiedNameWithArguments)ocQualifiedName2).myArguments);
    }
    
    @Override
    public String getCanonicalName(@NotNull final OCType.Presentation presentation, final boolean b, @NotNull final OCResolveContext ocResolveContext, final int n) {
        try {
            if (presentation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "presentation", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "getCanonicalName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "getCanonicalName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        String string;
        try {
            alloc.append(super.getCanonicalName(presentation, b, ocResolveContext, n));
            alloc.append('<');
            if (n < 10) {
                final String join = StringUtil.join((Collection)this.myArguments, ocTypeArgument -> {
                    try {
                        if (presentation == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "presentation", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "lambda$getCanonicalName$0"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        if (ocResolveContext == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments", "lambda$getCanonicalName$0"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    return ocTypeArgument.getNameForPresentation(presentation, ocResolveContext, b, n + 1);
                }, ", ");
                try {
                    if (join.startsWith("::")) {
                        alloc.append(" ");
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                alloc.append(join);
            }
            else {
                alloc.append("...");
            }
            alloc.append('>');
            string = alloc.toString();
        }
        finally {
            StringBuilderSpinAllocator.dispose(alloc);
        }
        return string;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
