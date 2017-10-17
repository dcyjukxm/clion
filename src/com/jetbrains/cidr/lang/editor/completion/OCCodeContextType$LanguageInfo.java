// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.HashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Map;

private static class LanguageInfo
{
    private static final Map<OCLanguageKind, LanguageInfo> LANGUAGE_INFO;
    @NotNull
    @NonNls
    private final String languageContextName;
    @NotNull
    private final String languageSuffixName;
    @NotNull
    private final Class<? extends LanguageBase> baseContextType;
    
    private LanguageInfo(@NotNull @NonNls final String languageContextName, @NotNull final String languageSuffixName, @NotNull final Class<? extends LanguageBase> baseContextType) {
        if (languageContextName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "<init>"));
        }
        if (languageSuffixName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suffix", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "<init>"));
        }
        if (baseContextType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseClass", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "<init>"));
        }
        this.languageContextName = languageContextName;
        this.languageSuffixName = languageSuffixName;
        this.baseContextType = baseContextType;
    }
    
    @NotNull
    @NonNls
    public static String getContextName(@NotNull final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getContextName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String languageContextName;
        try {
            languageContextName = LanguageInfo.LANGUAGE_INFO.get(ocLanguageKind).languageContextName;
            if (languageContextName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getContextName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return languageContextName;
    }
    
    @NotNull
    public static String getSuffix(@NotNull final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getSuffix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String languageSuffixName;
        try {
            languageSuffixName = LanguageInfo.LANGUAGE_INFO.get(ocLanguageKind).languageSuffixName;
            if (languageSuffixName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getSuffix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return languageSuffixName;
    }
    
    @NotNull
    public static Class<? extends LanguageBase> getBaseContext(@NotNull final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getBaseContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Class<? extends LanguageBase> baseContextType;
        try {
            baseContextType = LanguageInfo.LANGUAGE_INFO.get(ocLanguageKind).baseContextType;
            if (baseContextType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getBaseContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return baseContextType;
    }
    
    static {
        (LANGUAGE_INFO = new HashMap<OCLanguageKind, LanguageInfo>(3)).put(OCLanguageKind.C, new LanguageInfo("c", "_C", LanguageC.class));
        LanguageInfo.LANGUAGE_INFO.put(OCLanguageKind.CPP, new LanguageInfo("cpp", "_CPP", LanguageCPP.class));
        LanguageInfo.LANGUAGE_INFO.put(OCLanguageKind.OBJ_C, new LanguageInfo("objc", "", LanguageObjC.class));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
