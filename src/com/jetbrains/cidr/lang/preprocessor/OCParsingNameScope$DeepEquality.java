// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;

private static class DeepEquality implements DeepEqual.Equality<OCParsingNameScope>
{
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCParsingNameScope ocParsingNameScope, @NotNull final OCParsingNameScope ocParsingNameScope2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$DeepEquality", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocParsingNameScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$DeepEquality", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocParsingNameScope2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$DeepEquality", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!OCParsingNameScope.access$200(ocParsingNameScope).equals(OCParsingNameScope.access$200(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!OCParsingNameScope.access$300(ocParsingNameScope).equals((Object)OCParsingNameScope.access$300(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!OCParsingNameScope.access$400(ocParsingNameScope).equals((Object)OCParsingNameScope.access$400(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!OCParsingNameScope.access$500(ocParsingNameScope).equals((Object)OCParsingNameScope.access$500(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (!comparator.equalIterable(OCParsingNameScope.access$600(ocParsingNameScope), OCParsingNameScope.access$600(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        try {
            if (!comparator.equalObjects(OCParsingNameScope.access$700(ocParsingNameScope), OCParsingNameScope.access$700(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        try {
            if (!comparator.equalMaps((Map)OCParsingNameScope.access$800(ocParsingNameScope), (Map)OCParsingNameScope.access$800(ocParsingNameScope2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw a(ex10);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
