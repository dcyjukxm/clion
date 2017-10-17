// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import org.jetbrains.annotations.NotNull;
import java.util.List;

class CMakeNewFileHandler$1$2 implements CommandDependencyHandler {
    final /* synthetic */ List val$deps;
    
    @Override
    public void handleDependency(@NotNull final CommandInfo commandInfo) {
        try {
            if (commandInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandInfo", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1$2", "handleDependency"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.val$deps.add(commandInfo);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}