// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class CMakeVariable implements Serializable
{
    @NotNull
    private final String myName;
    @Nullable
    private final String myDescription;
    @NotNull
    private final Type myType;
    @Nullable
    private final String myValue;
    
    public CMakeVariable(@NotNull final String myName, @Nullable final String s, @NotNull final Type myType, @Nullable final String s2) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/model/CMakeVariable", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/cpp/cmake/model/CMakeVariable", "<init>"));
        }
        this.myName = myName;
        String nullize;
        if (s == null) {
            nullize = null;
        }
        else {
            nullize = StringUtil.nullize(s.trim());
        }
        String nullize2 = null;
        Label_0136: {
            try {
                this.myDescription = nullize;
                this.myType = myType;
                if (s2 == null) {
                    nullize2 = null;
                    break Label_0136;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            nullize2 = StringUtil.nullize(StringUtil.trimTrailing(s2), true);
        }
        this.myValue = nullize2;
    }
    
    @NotNull
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeVariable", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @Nullable
    public String getDescription() {
        return this.myDescription;
    }
    
    @NotNull
    public Type getType() {
        Type myType;
        try {
            myType = this.myType;
            if (myType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeVariable", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myType;
    }
    
    @Nullable
    public String getValue() {
        return this.myValue;
    }
    
    @NotNull
    public String getFormattedNameAndType() {
        String string;
        try {
            string = this.myName + ":" + this.myType;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeVariable", "getFormattedNameAndType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    public boolean isInternal() {
        try {
            if (this.myType == Type.INTERNAL) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isNotFound() {
        return this.isNotFoundValue(this.myValue);
    }
    
    public boolean isNotFoundValue(@Nullable final String s) {
        return (this.myName + "-NOTFOUND").equals(s);
    }
    
    @Override
    public String toString() {
        StringBuilder append;
        try {
            append = new StringBuilder().append(this.myName).append(":").append(this.myType).append("=").append(StringUtil.notNullize(this.myValue));
            if (this.myDescription == null) {
                final String string = "";
                return append.append(string).toString();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String string = "//" + this.myDescription;
        return append.append(string).toString();
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
                final CMakeVariable cMakeVariable = this;
                final Class<? extends CMakeVariable> clazz = cMakeVariable.getClass();
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
                final CMakeVariable cMakeVariable = this;
                final Class<? extends CMakeVariable> clazz = cMakeVariable.getClass();
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
        final CMakeVariable cMakeVariable2 = (CMakeVariable)o;
        Label_0092: {
            Label_0079: {
                Label_0072: {
                    try {
                        if (this.myDescription == null) {
                            break Label_0079;
                        }
                        final CMakeVariable cMakeVariable3 = this;
                        final String s = cMakeVariable3.myDescription;
                        final CMakeVariable cMakeVariable4 = cMakeVariable2;
                        final String s2 = cMakeVariable4.myDescription;
                        final boolean b = s.equals(s2);
                        if (!b) {
                            break Label_0072;
                        }
                        break Label_0092;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final CMakeVariable cMakeVariable3 = this;
                        final String s = cMakeVariable3.myDescription;
                        final CMakeVariable cMakeVariable4 = cMakeVariable2;
                        final String s2 = cMakeVariable4.myDescription;
                        final boolean b = s.equals(s2);
                        if (!b) {
                            return false;
                        }
                        break Label_0092;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    if (cMakeVariable2.myDescription != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                if (!this.myName.equals(cMakeVariable2.myName)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        try {
            if (this.myType != cMakeVariable2.myType) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        Label_0164: {
            Label_0157: {
                try {
                    if (this.myValue == null) {
                        break Label_0164;
                    }
                    final CMakeVariable cMakeVariable5 = this;
                    final String s3 = cMakeVariable5.myValue;
                    final CMakeVariable cMakeVariable6 = cMakeVariable2;
                    final String s4 = cMakeVariable6.myValue;
                    final boolean b2 = s3.equals(s4);
                    if (!b2) {
                        break Label_0157;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                try {
                    final CMakeVariable cMakeVariable5 = this;
                    final String s3 = cMakeVariable5.myValue;
                    final CMakeVariable cMakeVariable6 = cMakeVariable2;
                    final String s4 = cMakeVariable6.myValue;
                    final boolean b2 = s3.equals(s4);
                    if (!b2) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            try {
                if (cMakeVariable2.myValue != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw a(ex11);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = this.myName.hashCode();
        int n = 0;
        int hashCode2 = 0;
        Label_0034: {
            try {
                n = 31 * hashCode;
                if (this.myDescription != null) {
                    hashCode2 = this.myDescription.hashCode();
                    break Label_0034;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode2 = 0;
        }
        final int n2 = 31 * (n + hashCode2) + this.myType.hashCode();
        int n3;
        try {
            n3 = 31 * n2;
            if (this.myValue != null) {
                final int hashCode3 = this.myValue.hashCode();
                return n3 + hashCode3;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int hashCode3 = 0;
        return n3 + hashCode3;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Type
    {
        BOOL, 
        PATH, 
        FILEPATH, 
        STRING, 
        INTERNAL, 
        STATIC, 
        UNINITIALIZED;
    }
}
