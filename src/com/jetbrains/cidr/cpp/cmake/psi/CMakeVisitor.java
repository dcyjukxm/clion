// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElementVisitor;

public class CMakeVisitor extends PsiElementVisitor
{
    public void visitCMakeArgument(@NotNull final CMakeArgument cMakeArgument) {
        try {
            if (cMakeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeArgument);
    }
    
    public void visitCMakeBodyBlock(@NotNull final CMakeBodyBlock cMakeBodyBlock) {
        try {
            if (cMakeBodyBlock == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeBodyBlock"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeBodyBlock);
    }
    
    public void visitCMakeCommand(@NotNull final CMakeCommand cMakeCommand) {
        try {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeCommand);
    }
    
    public void visitCMakeCommandArguments(@NotNull final CMakeCommandArguments cMakeCommandArguments) {
        try {
            if (cMakeCommandArguments == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeCommandArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandArgumentsMixin(cMakeCommandArguments);
    }
    
    public void visitCMakeCommandName(@NotNull final CMakeCommandName cMakeCommandName) {
        try {
            if (cMakeCommandName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeCommandName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeCommandName);
    }
    
    public void visitCMakeElseCommand(@NotNull final CMakeElseCommand cMakeElseCommand) {
        try {
            if (cMakeElseCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElseCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeElseCommand);
    }
    
    public void visitCMakeElseCommandCall(@NotNull final CMakeElseCommandCall cMakeElseCommandCall) {
        try {
            if (cMakeElseCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElseCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeElseCommandCall);
    }
    
    public void visitCMakeElseCommandCallName(@NotNull final CMakeElseCommandCallName cMakeElseCommandCallName) {
        try {
            if (cMakeElseCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElseCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeElseCommandCallName);
    }
    
    public void visitCMakeElseIfCommand(@NotNull final CMakeElseIfCommand cMakeElseIfCommand) {
        try {
            if (cMakeElseIfCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElseIfCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeElseIfCommand);
    }
    
    public void visitCMakeElseIfCommandCall(@NotNull final CMakeElseIfCommandCall cMakeElseIfCommandCall) {
        try {
            if (cMakeElseIfCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElseIfCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeElseIfCommandCall);
    }
    
    public void visitCMakeElseIfCommandCallName(@NotNull final CMakeElseIfCommandCallName cMakeElseIfCommandCallName) {
        try {
            if (cMakeElseIfCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElseIfCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeElseIfCommandCallName);
    }
    
    public void visitCMakeEndForeachCommand(@NotNull final CMakeEndForeachCommand cMakeEndForeachCommand) {
        try {
            if (cMakeEndForeachCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndForeachCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeEndForeachCommand);
    }
    
    public void visitCMakeEndForeachCommandCall(@NotNull final CMakeEndForeachCommandCall cMakeEndForeachCommandCall) {
        try {
            if (cMakeEndForeachCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndForeachCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeEndForeachCommandCall);
    }
    
    public void visitCMakeEndForeachCommandCallName(@NotNull final CMakeEndForeachCommandCallName cMakeEndForeachCommandCallName) {
        try {
            if (cMakeEndForeachCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndForeachCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeEndForeachCommandCallName);
    }
    
    public void visitCMakeEndFunctionCommand(@NotNull final CMakeEndFunctionCommand cMakeEndFunctionCommand) {
        try {
            if (cMakeEndFunctionCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndFunctionCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeEndFunctionCommand);
    }
    
    public void visitCMakeEndFunctionCommandCall(@NotNull final CMakeEndFunctionCommandCall cMakeEndFunctionCommandCall) {
        try {
            if (cMakeEndFunctionCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndFunctionCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeEndFunctionCommandCall);
    }
    
    public void visitCMakeEndFunctionCommandCallName(@NotNull final CMakeEndFunctionCommandCallName cMakeEndFunctionCommandCallName) {
        try {
            if (cMakeEndFunctionCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndFunctionCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeEndFunctionCommandCallName);
    }
    
    public void visitCMakeEndIfCommand(@NotNull final CMakeEndIfCommand cMakeEndIfCommand) {
        try {
            if (cMakeEndIfCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndIfCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeEndIfCommand);
    }
    
    public void visitCMakeEndIfCommandCall(@NotNull final CMakeEndIfCommandCall cMakeEndIfCommandCall) {
        try {
            if (cMakeEndIfCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndIfCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeEndIfCommandCall);
    }
    
    public void visitCMakeEndIfCommandCallName(@NotNull final CMakeEndIfCommandCallName cMakeEndIfCommandCallName) {
        try {
            if (cMakeEndIfCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndIfCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeEndIfCommandCallName);
    }
    
    public void visitCMakeEndMacroCommand(@NotNull final CMakeEndMacroCommand cMakeEndMacroCommand) {
        try {
            if (cMakeEndMacroCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndMacroCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeEndMacroCommand);
    }
    
    public void visitCMakeEndMacroCommandCall(@NotNull final CMakeEndMacroCommandCall cMakeEndMacroCommandCall) {
        try {
            if (cMakeEndMacroCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndMacroCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeEndMacroCommandCall);
    }
    
    public void visitCMakeEndMacroCommandCallName(@NotNull final CMakeEndMacroCommandCallName cMakeEndMacroCommandCallName) {
        try {
            if (cMakeEndMacroCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndMacroCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeEndMacroCommandCallName);
    }
    
    public void visitCMakeEndWhileCommand(@NotNull final CMakeEndWhileCommand cMakeEndWhileCommand) {
        try {
            if (cMakeEndWhileCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndWhileCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeEndWhileCommand);
    }
    
    public void visitCMakeEndWhileCommandCall(@NotNull final CMakeEndWhileCommandCall cMakeEndWhileCommandCall) {
        try {
            if (cMakeEndWhileCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndWhileCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeEndWhileCommandCall);
    }
    
    public void visitCMakeEndWhileCommandCallName(@NotNull final CMakeEndWhileCommandCallName cMakeEndWhileCommandCallName) {
        try {
            if (cMakeEndWhileCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeEndWhileCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeEndWhileCommandCallName);
    }
    
    public void visitCMakeForeachCommand(@NotNull final CMakeForeachCommand cMakeForeachCommand) {
        try {
            if (cMakeForeachCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeForeachCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeLoopCommand(cMakeForeachCommand);
    }
    
    public void visitCMakeForeachCommandCall(@NotNull final CMakeForeachCommandCall cMakeForeachCommandCall) {
        try {
            if (cMakeForeachCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeForeachCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeForeachCommandCall);
    }
    
    public void visitCMakeForeachCommandCallName(@NotNull final CMakeForeachCommandCallName cMakeForeachCommandCallName) {
        try {
            if (cMakeForeachCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeForeachCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeForeachCommandCallName);
    }
    
    public void visitCMakeFunctionCommand(@NotNull final CMakeFunctionCommand cMakeFunctionCommand) {
        try {
            if (cMakeFunctionCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeFunctionCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeRoutine(cMakeFunctionCommand);
    }
    
    public void visitCMakeFunctionCommandCall(@NotNull final CMakeFunctionCommandCall cMakeFunctionCommandCall) {
        try {
            if (cMakeFunctionCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeFunctionCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeFunctionCommandCall);
    }
    
    public void visitCMakeFunctionCommandCallName(@NotNull final CMakeFunctionCommandCallName cMakeFunctionCommandCallName) {
        try {
            if (cMakeFunctionCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeFunctionCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeFunctionCommandCallName);
    }
    
    public void visitCMakeIfCommand(@NotNull final CMakeIfCommand cMakeIfCommand) {
        try {
            if (cMakeIfCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeIfCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeIfCommand);
    }
    
    public void visitCMakeIfCommandCall(@NotNull final CMakeIfCommandCall cMakeIfCommandCall) {
        try {
            if (cMakeIfCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeIfCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeIfCommandCall);
    }
    
    public void visitCMakeIfCommandCallName(@NotNull final CMakeIfCommandCallName cMakeIfCommandCallName) {
        try {
            if (cMakeIfCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeIfCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeIfCommandCallName);
    }
    
    public void visitCMakeLiteral(@NotNull final CMakeLiteral cMakeLiteral) {
        try {
            if (cMakeLiteral == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeLiteral"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeLiteralMixin(cMakeLiteral);
    }
    
    public void visitCMakeLoopCommand(@NotNull final CMakeLoopCommand cMakeLoopCommand) {
        try {
            if (cMakeLoopCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeLoopCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommand(cMakeLoopCommand);
    }
    
    public void visitCMakeMacroCommand(@NotNull final CMakeMacroCommand cMakeMacroCommand) {
        try {
            if (cMakeMacroCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeMacroCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeRoutine(cMakeMacroCommand);
    }
    
    public void visitCMakeMacroCommandCall(@NotNull final CMakeMacroCommandCall cMakeMacroCommandCall) {
        try {
            if (cMakeMacroCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeMacroCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeMacroCommandCall);
    }
    
    public void visitCMakeMacroCommandCallName(@NotNull final CMakeMacroCommandCallName cMakeMacroCommandCallName) {
        try {
            if (cMakeMacroCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeMacroCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeMacroCommandCallName);
    }
    
    public void visitCMakeRoutine(@NotNull final CMakeRoutine cMakeRoutine) {
        try {
            if (cMakeRoutine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeRoutine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeRoutineCommandMixin(cMakeRoutine);
    }
    
    public void visitCMakeWhileCommand(@NotNull final CMakeWhileCommand cMakeWhileCommand) {
        try {
            if (cMakeWhileCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeWhileCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeLoopCommand(cMakeWhileCommand);
    }
    
    public void visitCMakeWhileCommandCall(@NotNull final CMakeWhileCommandCall cMakeWhileCommandCall) {
        try {
            if (cMakeWhileCommandCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeWhileCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeElement(cMakeWhileCommandCall);
    }
    
    public void visitCMakeWhileCommandCallName(@NotNull final CMakeWhileCommandCallName cMakeWhileCommandCallName) {
        try {
            if (cMakeWhileCommandCallName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeWhileCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCommandNameMixin(cMakeWhileCommandCallName);
    }
    
    public void visitCMakeCommandArgumentsMixin(@NotNull final CMakeCommandArgumentsMixin cMakeCommandArgumentsMixin) {
        try {
            if (cMakeCommandArgumentsMixin == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeCommandArgumentsMixin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeCommandArgumentsMixin);
    }
    
    public void visitCMakeCommandNameMixin(@NotNull final CMakeCommandNameMixin cMakeCommandNameMixin) {
        try {
            if (cMakeCommandNameMixin == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeCommandNameMixin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeCommandNameMixin);
    }
    
    public void visitCMakeLiteralMixin(@NotNull final CMakeLiteralMixin cMakeLiteralMixin) {
        try {
            if (cMakeLiteralMixin == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeLiteralMixin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeLiteralMixin);
    }
    
    public void visitCMakeRoutineCommandMixin(@NotNull final CMakeRoutineCommandMixin cMakeRoutineCommandMixin) {
        try {
            if (cMakeRoutineCommandMixin == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeRoutineCommandMixin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeRoutineCommandMixin);
    }
    
    public void visitCMakeElement(@NotNull final CMakeElement cMakeElement) {
        try {
            if (cMakeElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/psi/CMakeVisitor", "visitCMakeElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeElement);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
