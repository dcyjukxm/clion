// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class CMakeMessage implements Serializable
{
    @NotNull
    private final MessageLevel myLevel;
    @Nullable
    private final File myFile;
    @Nullable
    private final Integer myLine;
    @NotNull
    private final String myText;
    
    @NotNull
    public static CMakeMessage fatalError(@NotNull final String s, @Nullable final File file) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "fatalError"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeMessage cMakeMessage;
        try {
            cMakeMessage = new CMakeMessage(MessageLevel.FATAL_ERROR, file, null, s);
            if (cMakeMessage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "fatalError"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return cMakeMessage;
    }
    
    @NotNull
    public static CMakeMessage fromException(@NotNull final CMakeException ex, @Nullable final File file) {
        try {
            if (ex == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ex", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "fromException"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        CMakeMessage cMakeMessage;
        try {
            cMakeMessage = new CMakeMessage(MessageLevel.FATAL_ERROR, file, null, ex.getMessage());
            if (cMakeMessage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "fromException"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return cMakeMessage;
    }
    
    public CMakeMessage(@NotNull final MessageLevel myLevel, @Nullable final File myFile, @Nullable final Integer myLine, @NotNull final String myText) {
        if (myLevel == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "level", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "<init>"));
        }
        if (myText == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "<init>"));
        }
        this.myLevel = myLevel;
        this.myFile = myFile;
        this.myLine = myLine;
        this.myText = myText;
    }
    
    @NotNull
    public MessageLevel getLevel() {
        MessageLevel myLevel;
        try {
            myLevel = this.myLevel;
            if (myLevel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "getLevel"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLevel;
    }
    
    @Nullable
    public File getFile() {
        return this.myFile;
    }
    
    @Nullable
    public Integer getLine() {
        return this.myLine;
    }
    
    @NotNull
    public String getText() {
        String myText;
        try {
            myText = this.myText;
            if (myText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMessage", "getText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myText;
    }
    
    @Override
    public String toString() {
        StringBuilder append;
        try {
            append = new StringBuilder().append(this.myLevel).append(" ").append(this.myFile);
            if (this.myLine != null) {
                final String string = ":" + this.myLine;
                return append.append(string).append(": ").append(this.myText).toString();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String string = "";
        return append.append(string).append(": ").append(this.myText).toString();
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
                final CMakeMessage cMakeMessage = this;
                final Class<? extends CMakeMessage> clazz = cMakeMessage.getClass();
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
                final CMakeMessage cMakeMessage = this;
                final Class<? extends CMakeMessage> clazz = cMakeMessage.getClass();
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
        final CMakeMessage cMakeMessage2 = (CMakeMessage)o;
        Label_0092: {
            Label_0079: {
                Label_0072: {
                    try {
                        if (this.myLine == null) {
                            break Label_0079;
                        }
                        final CMakeMessage cMakeMessage3 = this;
                        final Integer n = cMakeMessage3.myLine;
                        final CMakeMessage cMakeMessage4 = cMakeMessage2;
                        final Integer n2 = cMakeMessage4.myLine;
                        final boolean b = n.equals(n2);
                        if (!b) {
                            break Label_0072;
                        }
                        break Label_0092;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final CMakeMessage cMakeMessage3 = this;
                        final Integer n = cMakeMessage3.myLine;
                        final CMakeMessage cMakeMessage4 = cMakeMessage2;
                        final Integer n2 = cMakeMessage4.myLine;
                        final boolean b = n.equals(n2);
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
                    if (cMakeMessage2.myLine != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                if (this.myLevel != cMakeMessage2.myLevel) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        Label_0157: {
            Label_0144: {
                Label_0137: {
                    try {
                        if (this.myFile == null) {
                            break Label_0144;
                        }
                        final CMakeMessage cMakeMessage5 = this;
                        final File file = cMakeMessage5.myFile;
                        final CMakeMessage cMakeMessage6 = cMakeMessage2;
                        final File file2 = cMakeMessage6.myFile;
                        final boolean b2 = FileUtil.filesEqual(file, file2);
                        if (!b2) {
                            break Label_0137;
                        }
                        break Label_0157;
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    try {
                        final CMakeMessage cMakeMessage5 = this;
                        final File file = cMakeMessage5.myFile;
                        final CMakeMessage cMakeMessage6 = cMakeMessage2;
                        final File file2 = cMakeMessage6.myFile;
                        final boolean b2 = FileUtil.filesEqual(file, file2);
                        if (!b2) {
                            return false;
                        }
                        break Label_0157;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                }
                try {
                    if (cMakeMessage2.myFile != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            try {
                if (!this.myText.equals(cMakeMessage2.myText)) {
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
        final int n = 31 * this.myLevel.hashCode() + FileUtil.fileHashCode(this.myFile);
        int n2;
        try {
            n2 = 31 * n;
            if (this.myLine != null) {
                final int hashCode = this.myLine.hashCode();
                return 31 * (n2 + hashCode) + this.myText.hashCode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int hashCode = 0;
        return 31 * (n2 + hashCode) + this.myText.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum MessageLevel
    {
        WARNING, 
        AUTHOR_WARNING, 
        ERROR, 
        FATAL_ERROR;
        
        public boolean isWarning() {
            return this.ordinal() >= MessageLevel.WARNING.ordinal() && this.ordinal() <= MessageLevel.AUTHOR_WARNING.ordinal();
        }
        
        public boolean isError() {
            return this.ordinal() >= MessageLevel.ERROR.ordinal();
        }
    }
}
