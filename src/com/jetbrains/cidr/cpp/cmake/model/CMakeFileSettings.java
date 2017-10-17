// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.io.Serializable;

public class CMakeFileSettings implements Serializable
{
    @NotNull
    private final OCLanguageKind myLanguageKind;
    @NotNull
    private final List<String> myFlags;
    @NotNull
    private final List<String> myDefines;
    
    public CMakeFileSettings(@NotNull final OCLanguageKind myLanguageKind, @NotNull final List<String> list, @NotNull final List<String> list2) {
        if (myLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "flags", "com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defines", "com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings", "<init>"));
        }
        this.myLanguageKind = myLanguageKind;
        this.myFlags = Collections.unmodifiableList((List<? extends String>)list);
        this.myDefines = Collections.unmodifiableList((List<? extends String>)list2);
    }
    
    @NotNull
    public OCLanguageKind getLanguageKind() {
        OCLanguageKind myLanguageKind;
        try {
            myLanguageKind = this.myLanguageKind;
            if (myLanguageKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings", "getLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLanguageKind;
    }
    
    public boolean hasCustomSettings() {
        Label_0031: {
            try {
                if (!this.myFlags.isEmpty()) {
                    break Label_0031;
                }
                final CMakeFileSettings cMakeFileSettings = this;
                final List<String> list = cMakeFileSettings.myDefines;
                final boolean b = list.isEmpty();
                if (!b) {
                    break Label_0031;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CMakeFileSettings cMakeFileSettings = this;
                final List<String> list = cMakeFileSettings.myDefines;
                final boolean b = list.isEmpty();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public List<String> getFlags() {
        List<String> myFlags;
        try {
            myFlags = this.myFlags;
            if (myFlags == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings", "getFlags"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFlags;
    }
    
    @NotNull
    public List<String> getDefines() {
        List<String> myDefines;
        try {
            myDefines = this.myDefines;
            if (myDefines == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings", "getDefines"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDefines;
    }
    
    @Override
    public String toString() {
        return "FileSettings: {LanguageKind=" + this.myLanguageKind + '}';
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
