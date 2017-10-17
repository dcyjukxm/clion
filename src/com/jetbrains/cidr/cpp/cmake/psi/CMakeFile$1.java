// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

class CMakeFile$1 extends CMakeVisitor {
    final /* synthetic */ String val$name;
    final /* synthetic */ List val$result;
    
    @Override
    public void visitCMakeCommand(@NotNull final CMakeCommand cMakeCommand) {
        try {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile$1", "visitCMakeCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0071: {
            try {
                if (this.val$name == null) {
                    break Label_0071;
                }
                final CMakeCommand cMakeCommand2 = cMakeCommand;
                final CMakeVisitor cMakeVisitor = this;
                final String s = cMakeVisitor.val$name;
                final boolean b = cMakeCommand2.namesEqual(s);
                if (b) {
                    break Label_0071;
                }
                break Label_0071;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final CMakeCommand cMakeCommand2 = cMakeCommand;
                final CMakeVisitor cMakeVisitor = this;
                final String s = cMakeVisitor.val$name;
                final boolean b = cMakeCommand2.namesEqual(s);
                if (b) {
                    this.val$result.add(cMakeCommand);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        super.visitCMakeCommand(cMakeCommand);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}