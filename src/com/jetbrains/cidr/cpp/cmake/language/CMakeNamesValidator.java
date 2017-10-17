// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.language;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeLexer;
import com.intellij.lang.refactoring.NamesValidator;

public class CMakeNamesValidator implements NamesValidator
{
    private final CMakeLexer myCMakeLexer;
    
    public CMakeNamesValidator() {
        this.myCMakeLexer = new CMakeLexer();
    }
    
    public boolean isKeyword(@NotNull final String s, final Project project) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/language/CMakeNamesValidator", "isKeyword"));
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public synchronized boolean isIdentifier(@NotNull final String s, final Project project) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/language/CMakeNamesValidator", "isIdentifier"));
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw b(ex);
        }
        try {
            try {
                this.myCMakeLexer.start((CharSequence)s);
                if (this.myCMakeLexer.getTokenType() != CMakeTokenTypes.ID) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                this.myCMakeLexer.advance();
                if (this.myCMakeLexer.getTokenType() == CMakeTokenTypes.EOL) {
                    return true;
                }
            }
            catch (StringIndexOutOfBoundsException ex3) {
                throw b(ex3);
            }
            return false;
        }
        catch (StringIndexOutOfBoundsException ex4) {
            return false;
        }
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
