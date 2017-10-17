// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import java.util.Iterator;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.codeStyle.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OCWorkspaceUtil
{
    @NotNull
    public static String getConfigurationDisplayName(@Nullable final String s, @NotNull String join, final boolean b) {
        try {
            if (join == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configName", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getConfigurationDisplayName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0098: {
            String s3 = null;
            Label_0063: {
                try {
                    if (s != null) {
                        break Label_0098;
                    }
                    final String s2 = join;
                    s3 = a(s2);
                    if (s3 == null) {
                        break Label_0063;
                    }
                    return s3;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final String s2 = join;
                    s3 = a(s2);
                    if (s3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getConfigurationDisplayName"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return s3;
        }
        if (b) {
            join = StringUtil.join((Collection)ContainerUtil.map((Object[])NameUtil.splitNameIntoWords(join), trim -> {
                trim = trim.trim();
                if (!trim.isEmpty()) {
                    boolean b = true;
                    boolean b2 = true;
                    for (int i = 0; i < trim.length(); ++i) {
                        final char char1 = trim.charAt(i);
                        if (!Character.isAlphabetic(char1)) {
                            b2 = false;
                        }
                        if (!Character.isDigit(char1)) {
                            b = false;
                        }
                    }
                    try {
                        if (b2) {
                            return trim.substring(0, 1);
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (b) {
                            return trim;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return "";
            }), "");
        }
        String string = null;
        Label_0179: {
            String s5 = null;
            Label_0144: {
                try {
                    if (!StringUtil.isEmptyOrSpaces(join)) {
                        break Label_0179;
                    }
                    final String s4 = s;
                    s5 = a(s4);
                    if (s5 == null) {
                        break Label_0144;
                    }
                    return s5;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final String s4 = s;
                    s5 = a(s4);
                    if (s5 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getConfigurationDisplayName"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return s5;
            try {
                string = s + " [" + join + "]";
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getConfigurationDisplayName"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return string;
    }
    
    @NotNull
    private static String a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configName", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getOrUnnamed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String notNullize;
        try {
            notNullize = StringUtil.notNullize(StringUtil.nullize(s, true), "Unnamed");
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getOrUnnamed"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return notNullize;
    }
    
    public static int compareConfigurations(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final OCResolveConfiguration ocResolveConfiguration2) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "a", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "compareConfigurations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveConfiguration2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "b", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "compareConfigurations"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocResolveConfiguration.getDisplayName(false).compareToIgnoreCase(ocResolveConfiguration2.getDisplayName(false));
    }
    
    @NotNull
    public static OCLanguageKind getMaximumLanguageKind(@NotNull final Collection<OCLanguageKind> collection, @NotNull final Project project) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "all", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getMaximumLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getMaximumLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Enum enum1 = null;
        for (final OCLanguageKind ocLanguageKind : collection) {
            Label_0136: {
                try {
                    if (enum1 == null) {
                        break Label_0136;
                    }
                    final OCLanguageKind ocLanguageKind2 = ocLanguageKind;
                    final boolean b = ocLanguageKind2 instanceof CLanguageKind;
                    if (b) {
                        break Label_0136;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCLanguageKind ocLanguageKind2 = ocLanguageKind;
                    final boolean b = ocLanguageKind2 instanceof CLanguageKind;
                    if (!b) {
                        continue;
                    }
                    if (((CLanguageKind)ocLanguageKind).ordinal() <= enum1.ordinal()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            enum1 = (CLanguageKind)ocLanguageKind;
        }
        Enum enum2 = null;
        Label_0183: {
            try {
                if (enum1 == null) {
                    final Enum maxLanguage;
                    enum2 = (maxLanguage = CLanguageKind.maxLanguage(project));
                    break Label_0183;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            Enum maxLanguage;
            enum2 = (maxLanguage = enum1);
            try {
                if (maxLanguage == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceUtil", "getMaximumLanguageKind"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return (OCLanguageKind)enum2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
