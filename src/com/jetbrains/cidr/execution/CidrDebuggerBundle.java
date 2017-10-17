// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.reference.SoftReference;
import com.intellij.CommonBundle;
import org.jetbrains.annotations.PropertyKey;
import org.jetbrains.annotations.NotNull;
import java.util.ResourceBundle;
import java.lang.ref.Reference;

public class CidrDebuggerBundle
{
    private static Reference<ResourceBundle> ourBundle;
    private static final String BUNDLE = "CidrDebuggerBundle";
    
    public static String message(@NotNull @PropertyKey(resourceBundle = "CidrDebuggerBundle") final String s, @NotNull final Object... array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/CidrDebuggerBundle", "message"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/execution/CidrDebuggerBundle", "message"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return CommonBundle.message(getBundle(), s, array);
    }
    
    public static ResourceBundle getBundle() {
        ResourceBundle bundle = (ResourceBundle)SoftReference.dereference((Reference)CidrDebuggerBundle.ourBundle);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("CidrDebuggerBundle");
            CidrDebuggerBundle.ourBundle = new java.lang.ref.SoftReference<ResourceBundle>(bundle);
        }
        return bundle;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
