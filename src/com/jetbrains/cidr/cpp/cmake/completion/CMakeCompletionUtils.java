// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.jetbrains.annotations.Contract;
import icons.CLionIcons;
import javax.swing.Icon;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.Locale;
import com.jetbrains.cidr.cpp.cmake.settings.Case;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.cpp.cmake.settings.CMakeCodeStyleSettings;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CMakeCompletionUtils
{
    @NotNull
    public static LookupElementBuilder createStandardCommandItem(@NotNull final String s, final boolean b, final boolean b2, @NotNull final List<CMakeArgument> list) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createStandardCommandItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentList", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createStandardCommandItem"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        LookupElementBuilder a;
        try {
            a = a(s, b, true, b2, list, null);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createStandardCommandItem"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return a;
    }
    
    @NotNull
    private static LookupElementBuilder a(@NotNull final String s, final boolean b, final boolean b2, final boolean b3, @NotNull final List<CMakeArgument> list, @Nullable final Object o) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createStandardCommandItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentList", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createStandardCommandItem"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        LookupElementBuilder lookupElementBuilder = null;
        Label_0299: {
            Label_0285: {
                try {
                    if (!b2) {
                        if (!b3) {
                            break Label_0285;
                        }
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                final StringBuilder sb = new StringBuilder();
                sb.append('(');
                final Iterator<CMakeArgument> iterator = list.iterator();
                while (iterator.hasNext()) {
                    sb.append(iterator.next().getLiteralNotNull().getText()).append(' ');
                }
                try {
                    if (!list.isEmpty()) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                StringBuilder append = null;
                String string = null;
                Label_0255: {
                    try {
                        sb.append(')');
                        append = new StringBuilder().append(s).append((Object)sb);
                        if (o != null) {
                            string = o.toString();
                            break Label_0255;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    string = "";
                }
                lookupElementBuilder = LookupElementBuilder.create((Object)new String(append.append(string).toString()), s).withTailText(sb.toString());
                break Label_0299;
            }
            lookupElementBuilder = LookupElementBuilder.create((Object)new String(s), s);
        }
        if (b) {
            lookupElementBuilder = lookupElementBuilder.bold();
        }
        LookupElementBuilder lookupElementBuilder2 = null;
        Label_0375: {
            Label_0360: {
                Label_0325: {
                    try {
                        if (!b2) {
                            break Label_0375;
                        }
                        final boolean b4 = b3;
                        if (!b4) {
                            break Label_0325;
                        }
                        break Label_0325;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    try {
                        final boolean b4 = b3;
                        if (!b4) {
                            if (list.isEmpty()) {
                                break Label_0360;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                lookupElementBuilder = lookupElementBuilder.withInsertHandler((InsertHandler)ParenthesesInsertHandler.getInstance(true, false, false, true, false));
                break Label_0375;
            }
            lookupElementBuilder = lookupElementBuilder.withInsertHandler((InsertHandler)ParenthesesInsertHandler.getInstance(false, false, false, true, false));
            try {
                lookupElementBuilder2 = lookupElementBuilder;
                if (lookupElementBuilder2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createStandardCommandItem"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return lookupElementBuilder2;
    }
    
    @NotNull
    public static String convertCommandToProperCase(@Nullable final Project project, @NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textToComplete", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "convertCommandToProperCase"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeCodeStyleSettings cMakeCodeStyleSettings = (CMakeCodeStyleSettings)CodeStyleSettingsManager.getSettings(project).getCustomSettings((Class)CMakeCodeStyleSettings.class);
        String s7 = null;
        Label_0249: {
            String s6 = null;
            Label_0214: {
                Label_0183: {
                    String s4 = null;
                    Label_0148: {
                        Label_0117: {
                            String s2 = null;
                            Label_0082: {
                                try {
                                    if (cMakeCodeStyleSettings.FORCE_COMMANDS_CASE != Case.DO_NOT_CHANGE.getValue()) {
                                        break Label_0117;
                                    }
                                    s2 = s;
                                    if (s2 == null) {
                                        break Label_0082;
                                    }
                                    return s2;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    s2 = s;
                                    if (s2 == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "convertCommandToProperCase"));
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            return s2;
                            try {
                                if (cMakeCodeStyleSettings.FORCE_COMMANDS_CASE != Case.TO_LOWER.getValue()) {
                                    break Label_0183;
                                }
                                final String s3 = s;
                                final Locale locale = Locale.getDefault();
                                s4 = s3.toLowerCase(locale);
                                if (s4 == null) {
                                    break Label_0148;
                                }
                                return s4;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        try {
                            final String s3 = s;
                            final Locale locale = Locale.getDefault();
                            s4 = s3.toLowerCase(locale);
                            if (s4 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "convertCommandToProperCase"));
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    return s4;
                    try {
                        if (cMakeCodeStyleSettings.FORCE_COMMANDS_CASE != Case.TO_UPPER.getValue()) {
                            break Label_0249;
                        }
                        final String s5 = s;
                        final Locale locale2 = Locale.getDefault();
                        s6 = s5.toUpperCase(locale2);
                        if (s6 == null) {
                            break Label_0214;
                        }
                        return s6;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    final String s5 = s;
                    final Locale locale2 = Locale.getDefault();
                    s6 = s5.toUpperCase(locale2);
                    if (s6 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "convertCommandToProperCase"));
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            return s6;
            try {
                s7 = "";
                if (s7 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "convertCommandToProperCase"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return s7;
    }
    
    public static LookupElement createCommandItem(@NotNull final String s, @NotNull final String s2, @NotNull final List<CMakeArgument> list, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textToComplete", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createCommandItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filename", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createCommandItem"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentsInCompletion", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "createCommandItem"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (LookupElement)a(s, false, b2, false, list, s2 + b).withIcon(getRoutineIcon(b)).appendTailText(a(s2), true);
    }
    
    @NotNull
    private static String a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filename", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "getStandardCMakeCompletionTailText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String string;
        try {
            string = " (in " + s + ")";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "getStandardCMakeCompletionTailText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return string;
    }
    
    @NotNull
    public static Icon getRoutineIcon(final boolean b) {
        Icon icon = null;
        Label_0017: {
            try {
                if (b) {
                    final Icon icon2;
                    icon = (icon2 = getFunctionIcon());
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Icon icon2;
            icon = (icon2 = getMacroIcon());
            try {
                if (icon2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "getRoutineIcon"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return icon;
    }
    
    @Contract(pure = true)
    @NotNull
    public static Icon getMacroIcon() {
        Icon cMake_Macro;
        try {
            cMake_Macro = CLionIcons.CMake_Macro;
            if (cMake_Macro == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "getMacroIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cMake_Macro;
    }
    
    @Contract(pure = true)
    @NotNull
    public static Icon getFunctionIcon() {
        Icon cMake_Function;
        try {
            cMake_Function = CLionIcons.CMake_Function;
            if (cMake_Function == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils", "getFunctionIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cMake_Function;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
