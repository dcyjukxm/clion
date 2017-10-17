// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.toolchains.CidrToolSet;

protected abstract static class BasicOption implements Option
{
    public static final String TYPE_ATTR = "type";
    public static final String VALUE_ATTR = "value";
    @NotNull
    private final String myValue;
    
    public BasicOption(@NotNull final String myValue) {
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "<init>"));
        }
        this.myValue = myValue;
    }
    
    @NotNull
    @Override
    public String getValue() {
        String myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "getValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myValue;
    }
    
    @Override
    public void write(@NotNull final Element element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "write"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        element.setAttribute("type", this.getType());
        element.setAttribute("value", this.myValue);
    }
    
    @NotNull
    @Override
    public String getUniqueID() {
        String string;
        try {
            string = this.getType() + "=" + this.myValue;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "getUniqueID"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    protected abstract String getType();
    
    @Override
    public String toString() {
        return this.getType() + "=" + this.myValue;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final BasicOption basicOption = this;
                final Class<? extends BasicOption> clazz = basicOption.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final BasicOption basicOption = this;
                final Class<? extends BasicOption> clazz = basicOption.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final BasicOption basicOption2 = (BasicOption)o;
        try {
            if (!this.myValue.equals(basicOption2.myValue)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.myValue.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
