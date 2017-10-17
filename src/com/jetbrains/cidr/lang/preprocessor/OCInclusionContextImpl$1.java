// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.util.Map;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;

class OCInclusionContextImpl$1 implements DeepEqual.Equality<OCInclusionContextImpl> {
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCInclusionContextImpl ocInclusionContextImpl, @NotNull final OCInclusionContextImpl ocInclusionContextImpl2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocInclusionContextImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocInclusionContextImpl2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (OCInclusionContextImpl.access$100(ocInclusionContextImpl) != OCInclusionContextImpl.access$100(ocInclusionContextImpl2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (OCInclusionContextImpl.access$200(ocInclusionContextImpl) != OCInclusionContextImpl.access$200(ocInclusionContextImpl2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!Comparing.equal((Object)OCInclusionContextImpl.access$300(ocInclusionContextImpl), (Object)OCInclusionContextImpl.access$300(ocInclusionContextImpl2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (OCInclusionContextImpl.access$400(ocInclusionContextImpl) != OCInclusionContextImpl.access$400(ocInclusionContextImpl2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (OCInclusionContextImpl.access$500(ocInclusionContextImpl) != OCInclusionContextImpl.access$500(ocInclusionContextImpl2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        try {
            if (!OCInclusionContextImpl.access$600(ocInclusionContextImpl).equals(OCInclusionContextImpl.access$600(ocInclusionContextImpl2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        try {
            if (!OCInclusionContextImpl.access$700(ocInclusionContextImpl).equals(OCInclusionContextImpl.access$700(ocInclusionContextImpl2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw a(ex10);
        }
        try {
            if (!Comparing.equal((Object)OCInclusionContextImpl.access$800(ocInclusionContextImpl), (Object)OCInclusionContextImpl.access$800(ocInclusionContextImpl2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex11) {
            throw a(ex11);
        }
        try {
            if (!comparator.equalObjects(OCInclusionContextImpl.access$900(ocInclusionContextImpl), OCInclusionContextImpl.access$900(ocInclusionContextImpl2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex12) {
            throw a(ex12);
        }
        try {
            if (!comparator.equalMaps((Map)OCInclusionContextImpl.access$1000(ocInclusionContextImpl), (Map)OCInclusionContextImpl.access$1000(ocInclusionContextImpl2))) {
                return false;
            }
        }
        catch (IllegalArgumentException ex13) {
            throw a(ex13);
        }
        return comparator.equalObjects(OCInclusionContextImpl.access$1100(ocInclusionContextImpl), OCInclusionContextImpl.access$1100(ocInclusionContextImpl2));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}