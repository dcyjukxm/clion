// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import com.intellij.openapi.util.InvalidDataException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.JDOMExternalizable;

public static class FileExtensionPair implements JDOMExternalizable
{
    @NotNull
    public final String mySourceExt;
    @NotNull
    public final String myHeaderExt;
    
    public FileExtensionPair(@NotNull final String mySourceExt, @NotNull final String myHeaderExt) {
        if (mySourceExt == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceExt", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair", "<init>"));
        }
        if (myHeaderExt == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerExt", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair", "<init>"));
        }
        this.mySourceExt = mySourceExt;
        this.myHeaderExt = myHeaderExt;
    }
    
    @Override
    public String toString() {
        return "." + this.mySourceExt + " / ." + this.myHeaderExt;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.mySourceExt.hashCode() + this.myHeaderExt.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (InvalidDataException ex) {
            throw b(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final FileExtensionPair fileExtensionPair = this;
                final Class<? extends FileExtensionPair> clazz = fileExtensionPair.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (InvalidDataException ex2) {
                throw b(ex2);
            }
            try {
                final FileExtensionPair fileExtensionPair = this;
                final Class<? extends FileExtensionPair> clazz = fileExtensionPair.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (InvalidDataException ex3) {
                throw b(ex3);
            }
        }
        final FileExtensionPair fileExtensionPair2 = (FileExtensionPair)o;
        try {
            if (!this.mySourceExt.equals(fileExtensionPair2.mySourceExt)) {
                return false;
            }
        }
        catch (InvalidDataException ex4) {
            throw b(ex4);
        }
        try {
            if (!this.myHeaderExt.equals(fileExtensionPair2.myHeaderExt)) {
                return false;
            }
        }
        catch (InvalidDataException ex5) {
            throw b(ex5);
        }
        return true;
    }
    
    public void readExternal(final Element element) throws InvalidDataException {
    }
    
    public void writeExternal(final Element element) throws WriteExternalException {
        final Element element2 = new Element("pair");
        element2.setAttribute("source", this.mySourceExt);
        element2.setAttribute("header", this.myHeaderExt);
        element.addContent(element2);
    }
    
    private static InvalidDataException b(final InvalidDataException ex) {
        return ex;
    }
}
