// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

public static class ResourceReader
{
    public static String readExampleString(@NotNull final Class clazz, @NotNull final String s) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resourceClass", "com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader", "readExampleString"));
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resourcePath", "com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader", "readExampleString"));
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        String replace;
        try {
            replace = StreamUtil.readText(clazz.getResourceAsStream(s), CharsetToolkit.UTF8_CHARSET).replace("\r", "");
        }
        catch (IOException ex3) {
            OCLog.LOG.error(ex3.getMessage());
            replace = "<Resource reading error>";
        }
        return replace;
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}
