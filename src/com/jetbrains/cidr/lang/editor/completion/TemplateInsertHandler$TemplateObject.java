// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import org.jetbrains.annotations.NotNull;

public static class TemplateObject
{
    @NotNull
    private String myData;
    
    public TemplateObject(@NotNull final String myData) {
        if (myData == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/lang/editor/completion/TemplateInsertHandler$TemplateObject", "<init>"));
        }
        this.myData = myData;
    }
    
    @Override
    public String toString() {
        return this.myData;
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
                final TemplateObject templateObject = this;
                final Class<? extends TemplateObject> clazz = templateObject.getClass();
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
                final TemplateObject templateObject = this;
                final Class<? extends TemplateObject> clazz = templateObject.getClass();
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
        final TemplateObject templateObject2 = (TemplateObject)o;
        try {
            if (!this.myData.equals(templateObject2.myData)) {
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
        return this.myData.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
