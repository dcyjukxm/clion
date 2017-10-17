// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.reference.SoftReference;
import com.intellij.CommonBundle;
import org.jetbrains.annotations.PropertyKey;
import org.jetbrains.annotations.NotNull;
import java.util.ResourceBundle;
import java.lang.ref.Reference;

public class CPPBundle
{
    private static Reference<ResourceBundle> ourBundle;
    private static final String BUNDLE = "com.jetbrains.cidr.cpp.CPPBundle";
    
    public static String message(@NotNull @PropertyKey(resourceBundle = "com.jetbrains.cidr.cpp.CPPBundle") final String s, @NotNull final Object... array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/cpp/CPPBundle", "message"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/cpp/CPPBundle", "message"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return CommonBundle.message(getBundle(), s, array);
    }
    
    public static ResourceBundle getBundle() {
        ResourceBundle bundle = (ResourceBundle)SoftReference.dereference((Reference)CPPBundle.ourBundle);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("com.jetbrains.cidr.cpp.CPPBundle");
            CPPBundle.ourBundle = new java.lang.ref.SoftReference<ResourceBundle>(bundle);
        }
        return bundle;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
