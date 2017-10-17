// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.ConcurrencyUtil;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.concurrent.ConcurrentMap;
import com.jetbrains.cidr.lang.OCInternator;

public class OCNamesInternary
{
    private static final OCInternator<String> STRING_INTERNATOR;
    private static final ConcurrentMap<OCQualifiedName, OCQualifiedName> ourQualifiedNamesCache;
    
    @Contract("null->null; !null->!null")
    public static String intern(final String s) {
        return (s != null) ? OCNamesInternary.STRING_INTERNATOR.intern(s) : null;
    }
    
    public static OCQualifiedName intern(final OCQualifiedName ocQualifiedName) {
        for (OCQualifiedName qualifier = ocQualifiedName; qualifier != null; qualifier = qualifier.getQualifier()) {
            if (qualifier instanceof OCQualifiedNameWithArguments) {
                return ocQualifiedName;
            }
        }
        final OCQualifiedName ocQualifiedName2 = OCNamesInternary.ourQualifiedNamesCache.get(ocQualifiedName);
        if (ocQualifiedName2 != null) {
            return ocQualifiedName2;
        }
        return (OCQualifiedName)ConcurrencyUtil.cacheOrGet((ConcurrentMap)OCNamesInternary.ourQualifiedNamesCache, (Object)ocQualifiedName, (Object)ocQualifiedName);
    }
    
    static {
        STRING_INTERNATOR = new OCInternator<String>() {
            @NotNull
            @Override
            protected String valueToStore(@NotNull final String s) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary$1", "valueToStore"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                String s2;
                try {
                    s2 = new String(s);
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary$1", "valueToStore"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                return s2;
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        };
        ourQualifiedNamesCache = ContainerUtil.createConcurrentWeakKeyWeakValueMap();
    }
}
