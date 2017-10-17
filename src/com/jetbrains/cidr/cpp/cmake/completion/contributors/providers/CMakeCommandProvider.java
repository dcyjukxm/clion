// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import java.util.Locale;
import java.util.Set;

public class CMakeCommandProvider extends AbstractCMakeCaseSensitiveCompletionProvider
{
    public static final String[] CMAKE_COMMANDS;
    private static final Set<String> CMAKE_COMMANDS_AS_SET;
    
    public CMakeCommandProvider() {
        super(CMakeCommandProvider.CMAKE_COMMANDS);
    }
    
    public static boolean isStandardCommand(final String s) {
        return CMakeCommandProvider.CMAKE_COMMANDS_AS_SET.contains(s.toLowerCase(Locale.US));
    }
    
    public static boolean isIncludeCommand(@NotNull final CMakeCommandName cMakeCommandName) {
        try {
            if (cMakeCommandName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeCommandName", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider", "isIncludeCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.INCLUDE_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isAddLibraryCommand(@NotNull final CMakeCommandName cMakeCommandName) {
        try {
            if (cMakeCommandName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeCommandName", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider", "isAddLibraryCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.ADD_LIBRARY_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isAddExecutableCommand(@NotNull final CMakeCommandName cMakeCommandName) {
        try {
            if (cMakeCommandName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeCommandName", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider", "isAddExecutableCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.ADD_EXECUTABLE_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isSetCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.SET_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isAddCustomTargetCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.ADD_CUSTOM_TARGET_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isFltkWrapUiCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.FLTK_WRAP_UI_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isQtWrapCppCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.QT_WRAP_CPP_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isQtWrapUiCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.QT_WRAP_UI_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isSetPropertyCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.SET_PROPERTY_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isSetSourceFilesPropertiesCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.SET_SOURCE_FILES_PROPERTIES_COMMAND_NAME, cMakeCommandName);
    }
    
    public static boolean isTryCompileCommand(final CMakeCommandName cMakeCommandName) {
        return CMakeStandardCommands.isCommand(CMakeStandardCommands.TRY_COMPILE_COMMAND_NAME, cMakeCommandName);
    }
    
    static {
        CMAKE_COMMANDS = (String[])ContainerUtil.map((Object[])ContainerUtil.filter((Object[])CMakeStandardCommands.values(), cMakeStandardCommands -> {
            try {
                if (!cMakeStandardCommands.isLexerToken()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return false;
        }).toArray(new CMakeStandardCommands[0]), cMakeStandardCommands -> cMakeStandardCommands.getCommandName(), (Object[])ArrayUtil.EMPTY_STRING_ARRAY);
        CMAKE_COMMANDS_AS_SET = ContainerUtil.newHashSet((Object[])CMakeCommandProvider.CMAKE_COMMANDS);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
