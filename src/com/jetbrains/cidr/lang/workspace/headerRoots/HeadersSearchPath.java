// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class HeadersSearchPath
{
    @NotNull
    private final File myFile;
    private final boolean isRecursive;
    private final boolean isUserHeaders;
    private final boolean isFrameworksSearchPath;
    
    public HeadersSearchPath(@NotNull final File file, final boolean b, final boolean b2) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath", "<init>"));
        }
        this(file, b, b2, false);
    }
    
    public HeadersSearchPath(@NotNull final File myFile, final boolean isRecursive, final boolean isUserHeaders, final boolean isFrameworksSearchPath) {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath", "<init>"));
        }
        this.myFile = myFile;
        this.isRecursive = isRecursive;
        this.isUserHeaders = isUserHeaders;
        this.isFrameworksSearchPath = isFrameworksSearchPath;
    }
    
    @NotNull
    public File getFile() {
        File myFile;
        try {
            myFile = this.myFile;
            if (myFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath", "getFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFile;
    }
    
    @NotNull
    public String getPath() {
        String path;
        try {
            path = this.myFile.getPath();
            if (path == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath", "getPath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return path;
    }
    
    public boolean isRecursive() {
        return this.isRecursive;
    }
    
    public boolean isUserHeaders() {
        return this.isUserHeaders;
    }
    
    public boolean isFrameworksSearchPath() {
        return this.isFrameworksSearchPath;
    }
    
    @Override
    public String toString() {
        StringBuilder append3 = null;
        Label_0068: {
            StringBuilder append2 = null;
            Label_0042: {
                StringBuilder append;
                try {
                    append = new StringBuilder().append("HeadersSearchPath{").append(this.myFile).append(", ");
                    if (this.isUserHeaders) {
                        final String s = "user";
                        break Label_0042;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final String s = "system";
                try {
                    append2 = append.append(s).append(", ");
                    if (this.isRecursive) {
                        final String s2 = "recursive";
                        break Label_0068;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            final String s2 = "non-recursive";
            try {
                append3 = append2.append(s2);
                if (this.isFrameworksSearchPath) {
                    final String s3 = ", frameworks";
                    return append3.append(s3).append('}').toString();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final String s3 = "";
        return append3.append(s3).append('}').toString();
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
                final HeadersSearchPath headersSearchPath = this;
                final Class<? extends HeadersSearchPath> clazz = headersSearchPath.getClass();
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
                final HeadersSearchPath headersSearchPath = this;
                final Class<? extends HeadersSearchPath> clazz = headersSearchPath.getClass();
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
        final HeadersSearchPath headersSearchPath2 = (HeadersSearchPath)o;
        try {
            if (this.isRecursive != headersSearchPath2.isRecursive) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (this.isUserHeaders != headersSearchPath2.isUserHeaders) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (this.isFrameworksSearchPath != headersSearchPath2.isFrameworksSearchPath) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!FileUtil.filesEqual(this.myFile, headersSearchPath2.myFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int fileHashCode = FileUtil.fileHashCode(this.myFile);
        int n = 0;
        int n2 = 0;
        Label_0028: {
            try {
                n = 31 * fileHashCode;
                if (this.isRecursive) {
                    n2 = 1;
                    break Label_0028;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n2 = 0;
        }
        final int n3 = n + n2;
        int n4 = 0;
        int n5 = 0;
        Label_0050: {
            try {
                n4 = 31 * n3;
                if (this.isUserHeaders) {
                    n5 = 1;
                    break Label_0050;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            n5 = 0;
        }
        final int n6 = n4 + n5;
        int n7;
        try {
            n7 = 31 * n6;
            if (this.isFrameworksSearchPath) {
                final int n8 = 1;
                return n7 + n8;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int n8 = 0;
        return n7 + n8;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
