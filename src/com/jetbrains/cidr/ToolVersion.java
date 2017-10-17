// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.util.VersionUtil;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Version;

public class ToolVersion extends Version
{
    public final String versionString;
    public static final String UNKNOWN_VERSION = "unknown";
    public static final ToolVersion UNKNOWN;
    
    public ToolVersion(final int n, final int n2, final int n3) {
        this(Version.toCompactString(n, n2, n3), n, n2, n3);
    }
    
    public ToolVersion(final String versionString, final int n, final int n2, final int n3) {
        super(n, n2, n3);
        this.versionString = versionString;
    }
    
    public ToolVersion(final String s, final Version version) {
        this(s, version.major, version.minor, version.bugfix);
    }
    
    @NotNull
    public static ToolVersion parse(@NotNull final String s, @NotNull final Pattern... array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "versionString", "com/jetbrains/cidr/ToolVersion", "parse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "patterns", "com/jetbrains/cidr/ToolVersion", "parse"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Version version = VersionUtil.parseVersion(s, array);
        ToolVersion toolVersion2 = null;
        Label_0147: {
            ToolVersion toolVersion = null;
            Label_0112: {
                try {
                    if (version != null) {
                        break Label_0147;
                    }
                    toolVersion = ToolVersion.UNKNOWN;
                    if (toolVersion == null) {
                        break Label_0112;
                    }
                    return toolVersion;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    toolVersion = ToolVersion.UNKNOWN;
                    if (toolVersion == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ToolVersion", "parse"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return toolVersion;
            try {
                toolVersion2 = new ToolVersion(s, version);
                if (toolVersion2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ToolVersion", "parse"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return toolVersion2;
    }
    
    public String toString() {
        return this.versionString;
    }
    
    public String toCompactString() {
        try {
            if (this.isUnknown()) {
                return "unknown";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return super.toCompactString();
    }
    
    public boolean isUnknown() {
        try {
            if (this == ToolVersion.UNKNOWN) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
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
                final ToolVersion toolVersion = this;
                final Class<? extends ToolVersion> clazz = toolVersion.getClass();
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
                final ToolVersion toolVersion = this;
                final Class<? extends ToolVersion> clazz = toolVersion.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final ToolVersion toolVersion2 = (ToolVersion)o;
        Label_0093: {
            Label_0086: {
                try {
                    if (this.versionString == null) {
                        break Label_0093;
                    }
                    final ToolVersion toolVersion3 = this;
                    final String s = toolVersion3.versionString;
                    final ToolVersion toolVersion4 = toolVersion2;
                    final String s2 = toolVersion4.versionString;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        break Label_0086;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final ToolVersion toolVersion3 = this;
                    final String s = toolVersion3.versionString;
                    final ToolVersion toolVersion4 = toolVersion2;
                    final String s2 = toolVersion4.versionString;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                if (toolVersion2.versionString != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return true;
    }
    
    public int hashCode() {
        final int hashCode = super.hashCode();
        int n;
        try {
            n = 31 * hashCode;
            if (this.versionString != null) {
                final int hashCode2 = this.versionString.hashCode();
                return n + hashCode2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int hashCode2 = 0;
        return n + hashCode2;
    }
    
    static {
        UNKNOWN = new ToolVersion("unknown", 0, 0, 0);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
