// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeForeachCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeWhileCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeBodyBlock;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeIfCommand;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import java.util.ArrayList;
import java.util.Locale;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeEndMacroCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeMacroCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeEndFunctionCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFunctionCommand;
import com.intellij.util.containers.Stack;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeVisitor;
import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import java.util.List;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class CMakeInterpreter
{
    public static void interpret(@NotNull final PsiElement psiElement, @NotNull final Listener listener) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter", "interpret"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (listener == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter", "interpret"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        psiElement.accept((PsiElementVisitor)new FileVisitor(listener));
    }
    
    public static boolean expandArguments(@Nullable final List<CMakeArgument> list, @NotNull final CMakeScope cMakeScope, @NotNull final List<String> list2) {
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter", "expandArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter", "expandArguments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Iterator<CMakeArgument> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().expandIntoArgumentList(list2, cMakeScope);
        }
        return true;
    }
    
    public static void expandListArgument(@NotNull final String s, @NotNull final List<String> list) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listArgument", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter", "expandListArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeInterpreter", "expandListArgument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!s.contains(";")) {
                list.add(s);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final StringBuilder sb = new StringBuilder();
        int i;
        int n = i = 0;
        final int length = s.length();
        int n2 = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            Label_0292: {
                if (char1 == '\\') {
                    final int n3 = i + 1;
                    Label_0203: {
                        try {
                            if (n3 >= length || s.charAt(n3) != ';') {
                                break Label_0203;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        sb.append(s.substring(n, i));
                        i = (n = n3);
                    }
                }
                else {
                    try {
                        if (char1 == '[') {
                            ++n2;
                            break Label_0292;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        if (char1 == ']') {
                            --n2;
                            break Label_0292;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    try {
                        if (char1 != ';' || n2 != 0) {
                            break Label_0292;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                    sb.append(s.substring(n, i));
                    list.add(sb.toString());
                    sb.setLength(0);
                    n = i + 1;
                }
            }
            ++i;
        }
        try {
            if (n < length) {
                sb.append(s.substring(n, length));
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        try {
            if (sb.length() > 0) {
                list.add(sb.toString());
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
    
    public interface Listener
    {
        void beforeCommand(@NotNull final CMakeCommand p0, @NotNull final CMakeScope p1);
        
        void onEnterIf();
        
        void onExitIf();
        
        boolean onEnterBranch(final int p0);
        
        void onExitBranch();
        
        void onEnterWhile();
        
        void onExitWhile();
        
        void onEnterForeach();
        
        void onExitForeach();
        
        boolean onEnterLoop(final int p0);
        
        void onExitLoop();
        
        void onEnterFunction();
        
        void onExitFunction();
        
        void onEnterMacro();
        
        void onExitMacro();
    }
}
