// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.documentation.doxygen.api.InfoItem;

public class InfoItemImpl implements InfoItem
{
    private final String content;
    
    public InfoItemImpl(final String content) {
        this.content = content;
    }
    
    @NotNull
    @Override
    public String getDescription() {
        String content;
        try {
            content = this.content;
            if (content == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/InfoItemImpl", "getDescription"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return content;
    }
    
    @Override
    public String toString() {
        return this.getDescription();
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final InfoItemImpl infoItemImpl = this;
                final Class<? extends InfoItemImpl> clazz = infoItemImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final InfoItemImpl infoItemImpl = this;
                final Class<? extends InfoItemImpl> clazz = infoItemImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final InfoItemImpl infoItemImpl2 = (InfoItemImpl)o;
        Label_0079: {
            Label_0072: {
                try {
                    if (this.content == null) {
                        break Label_0079;
                    }
                    final InfoItemImpl infoItemImpl3 = this;
                    final String s = infoItemImpl3.content;
                    final InfoItemImpl infoItemImpl4 = infoItemImpl2;
                    final String s2 = infoItemImpl4.content;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        break Label_0072;
                    }
                    return true;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final InfoItemImpl infoItemImpl3 = this;
                    final String s = infoItemImpl3.content;
                    final InfoItemImpl infoItemImpl4 = infoItemImpl2;
                    final String s2 = infoItemImpl4.content;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (infoItemImpl2.content != null) {
                    return false;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        try {
            if (this.content != null) {
                return this.content.hashCode();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return 0;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
