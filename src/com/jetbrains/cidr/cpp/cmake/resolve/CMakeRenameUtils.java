// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.resolve;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementTypes;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementFactory;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class CMakeRenameUtils
{
    public static void renameArgument(@NotNull final Project project, @NotNull final String s, @NotNull final ASTNode astNode) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newName", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameArgument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentToRename", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameArgument"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        a(astNode, CMakeElementFactory.createArgument(project, s).getLiteralNotNull().getNode(), CMakeTokenTypes.C_MAKE_LITERAL);
    }
    
    public static void renameCommandCall(@NotNull final Project project, @NotNull final String s, @NotNull final ASTNode astNode) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newName", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameCommandCall"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentToRename", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameCommandCall"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        a(astNode, CMakeElementFactory.createCommandName(project, s).getFirstChild().getNode(), CMakeElementTypes.COMMAND_ELEMENTS.getTypes());
    }
    
    private static void a(@NotNull final ASTNode astNode, final ASTNode astNode2, final IElementType... array) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentToSearch", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeRenameUtils", "renameNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            final ASTNode childByType = astNode.findChildByType(array[i]);
            try {
                if (childByType != null) {
                    astNode.replaceChild(childByType, astNode2);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
