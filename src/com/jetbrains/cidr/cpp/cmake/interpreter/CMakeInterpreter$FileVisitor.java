// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeForeachCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeWhileCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeBodyBlock;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeIfCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeEndMacroCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeMacroCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeEndFunctionCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFunctionCommand;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.Stack;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeVisitor;

private static class FileVisitor extends CMakeVisitor
{
    @NotNull
    private final Stack<CMakeScope> myScopes;
    @NotNull
    private final Listener myListener;
    
    public FileVisitor(@NotNull final Listener myListener) {
        if (myListener == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "<init>"));
        }
        this.myScopes = (Stack<CMakeScope>)new Stack();
        this.myListener = myListener;
        this.myScopes.push((Object)new CMakeScope());
    }
    
    public void visitElement(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiElement[] children = psiElement.getChildren();
        for (int length = children.length, i = 0; i < length; ++i) {
            children[i].accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitCMakeFunctionCommand(@NotNull final CMakeFunctionCommand cMakeFunctionCommand) {
        try {
            if (cMakeFunctionCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeFunctionCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myListener.onEnterFunction();
        this.myScopes.push((Object)new CMakeScope());
    }
    
    @Override
    public void visitCMakeEndFunctionCommand(@NotNull final CMakeEndFunctionCommand cMakeEndFunctionCommand) {
        try {
            if (cMakeEndFunctionCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeEndFunctionCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myListener.onExitFunction();
        this.myScopes.pop();
    }
    
    @Override
    public void visitCMakeMacroCommand(@NotNull final CMakeMacroCommand cMakeMacroCommand) {
        try {
            if (cMakeMacroCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeMacroCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myListener.onEnterMacro();
        this.myScopes.push((Object)new CMakeScope());
    }
    
    @Override
    public void visitCMakeEndMacroCommand(@NotNull final CMakeEndMacroCommand cMakeEndMacroCommand) {
        try {
            if (cMakeEndMacroCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeEndMacroCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myListener.onExitMacro();
        this.myScopes.pop();
    }
    
    @Override
    public void visitCMakeCommand(@NotNull final CMakeCommand cMakeCommand) {
        try {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final String lowerCase = cMakeCommand.getName().toLowerCase(Locale.getDefault());
        final CMakeScope cMakeScope = (CMakeScope)this.myScopes.peek();
        this.myListener.beforeCommand(cMakeCommand, cMakeScope);
        if ("set".equals(lowerCase)) {
            final List<CMakeArgument> cMakeArgumentList = cMakeCommand.getCMakeArgumentList();
            final ArrayList<String> list = new ArrayList<String>();
            final boolean expandArguments = CMakeInterpreter.expandArguments(cMakeArgumentList, cMakeScope, list);
            try {
                if (!expandArguments || list.isEmpty()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final String s = list.get(0);
            List<String> list2 = list.subList(1, list.size());
            if (list2.indexOf("PARENT_SCOPE") < 0) {
                final int index = list2.indexOf("CACHE");
                if (index >= 0) {
                    list2 = list2.subList(0, index);
                }
                CMakeScope cMakeScope2 = null;
                String s2 = null;
                String join = null;
                Label_0231: {
                    try {
                        cMakeScope2 = cMakeScope;
                        s2 = s;
                        if (list2.isEmpty()) {
                            join = null;
                            break Label_0231;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    join = StringUtil.join((Collection)list2, ";");
                }
                cMakeScope2.setVariableValue(s2, join);
            }
        }
    }
    
    @Override
    public void visitCMakeIfCommand(@NotNull final CMakeIfCommand cMakeIfCommand) {
        try {
            if (cMakeIfCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeIfCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        super.visitCMakeIfCommand(cMakeIfCommand);
        this.myListener.onEnterIf();
        final List<CMakeBodyBlock> cMakeBodyBlockList = cMakeIfCommand.getCMakeBodyBlockList();
        for (int i = 0; i < cMakeBodyBlockList.size(); ++i) {
            final CMakeBodyBlock cMakeBodyBlock = cMakeBodyBlockList.get(i);
            try {
                if (this.myListener.onEnterBranch(i)) {
                    cMakeBodyBlock.accept((PsiElementVisitor)this);
                    this.myListener.onExitBranch();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.myListener.onExitIf();
    }
    
    @Override
    public void visitCMakeWhileCommand(@NotNull final CMakeWhileCommand cMakeWhileCommand) {
        try {
            if (cMakeWhileCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeWhileCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        super.visitCMakeWhileCommand(cMakeWhileCommand);
        this.myListener.onEnterWhile();
        this.a(cMakeWhileCommand.getCMakeBodyBlock());
        this.myListener.onExitWhile();
    }
    
    @Override
    public void visitCMakeForeachCommand(@NotNull final CMakeForeachCommand cMakeForeachCommand) {
        try {
            if (cMakeForeachCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter$FileVisitor", "visitCMakeForeachCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        super.visitCMakeForeachCommand(cMakeForeachCommand);
        this.myListener.onEnterForeach();
        this.a(cMakeForeachCommand.getCMakeBodyBlock());
        this.myListener.onExitForeach();
    }
    
    private void a(@Nullable final CMakeBodyBlock cMakeBodyBlock) {
        try {
            if (cMakeBodyBlock == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = 0;
        while (true) {
            Label_0036: {
                try {
                    if (n >= 2) {
                        break;
                    }
                    final FileVisitor fileVisitor = this;
                    final Listener listener = fileVisitor.myListener;
                    final int n2 = n;
                    final boolean b = listener.onEnterLoop(n2);
                    if (b) {
                        break Label_0036;
                    }
                    break Label_0036;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final FileVisitor fileVisitor = this;
                    final Listener listener = fileVisitor.myListener;
                    final int n2 = n;
                    final boolean b = listener.onEnterLoop(n2);
                    if (b) {
                        cMakeBodyBlock.accept((PsiElementVisitor)this);
                        this.myListener.onExitLoop();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            ++n;
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
