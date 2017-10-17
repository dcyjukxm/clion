// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import org.jetbrains.annotations.NotNull;

class CMakeNewFileHandler$1$1 implements CommandDependencyHandler {
    final /* synthetic */ String val$targetName;
    
    @Override
    public void handleDependency(@NotNull final CommandInfo commandInfo) {
        try {
            if (commandInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandInfo", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1$1", "handleDependency"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Listener.this.val$commands2Targets.putValue((Object)commandInfo, (Object)this.val$targetName);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}