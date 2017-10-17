// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import org.jetbrains.annotations.NotNull;

private static class CommandInfo
{
    @NotNull
    final String varRef;
    @NotNull
    final CMakeCommand command;
    
    private CommandInfo(@NotNull final String varRef, @NotNull final CMakeCommand command) {
        if (varRef == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CommandInfo", "<init>"));
        }
        if (command == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CommandInfo", "<init>"));
        }
        this.varRef = varRef;
        this.command = command;
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
                final CommandInfo commandInfo = this;
                final Class<? extends CommandInfo> clazz = commandInfo.getClass();
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
                final CommandInfo commandInfo = this;
                final Class<? extends CommandInfo> clazz = commandInfo.getClass();
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
        final CommandInfo commandInfo2 = (CommandInfo)o;
        try {
            if (!this.varRef.equals(commandInfo2.varRef)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.command.equals(commandInfo2.command)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.varRef.hashCode() + this.command.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
