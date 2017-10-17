// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeScope;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import java.util.Stack;
import com.intellij.util.containers.MultiMap;
import java.util.Set;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeInterpreter;

static final class CMakeNewFileHandler$1 implements CMakeInterpreter.Listener {
    final /* synthetic */ Set val$targetSet;
    final /* synthetic */ VarDependencies val$varDependencies;
    final /* synthetic */ MultiMap val$commands2Targets;
    final /* synthetic */ Stack val$controlVarDependenciesStack;
    final /* synthetic */ Stack val$branchVarDependenciesStack;
    
    @Override
    public void beforeCommand(@NotNull final CMakeCommand cMakeCommand, @NotNull final CMakeScope cMakeScope) {
        try {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmd", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1", "beforeCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1", "beforeCommand"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String lowerCase = cMakeCommand.getName().toLowerCase(Locale.getDefault());
        Label_0208: {
            Label_0126: {
                try {
                    if ("add_executable".equals(lowerCase)) {
                        break Label_0126;
                    }
                    final String s = "add_library";
                    final String s2 = lowerCase;
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0126;
                    }
                    break Label_0208;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final String s = "add_library";
                    final String s2 = lowerCase;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        break Label_0208;
                    }
                    if (!cMakeCommand.canAppendArguments()) {
                        break Label_0208;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final String access$000 = CMakeNewFileHandler.access$000(cMakeCommand, cMakeScope);
            try {
                if (access$000 != null) {
                    this.val$targetSet.add(new CMakeNewFileTarget(cMakeCommand, Collections.singletonList(access$000), access$000, 1));
                    CMakeNewFileHandler.access$100(cMakeCommand, this.val$varDependencies, new CommandDependencyHandler() {
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
                            Listener.this.val$commands2Targets.putValue((Object)commandInfo, (Object)access$000);
                        }
                        
                        private static IllegalArgumentException a(final IllegalArgumentException ex) {
                            return ex;
                        }
                    });
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return;
        }
        if ("set".equals(lowerCase)) {
            final String access$2 = CMakeNewFileHandler.access$000(cMakeCommand, cMakeScope);
            if (access$2 != null) {
                final String string = "${" + access$2 + "}";
                final ArrayList<CommandInfo> list = new ArrayList<CommandInfo>();
                list.add(new CommandInfo(string, cMakeCommand));
                CMakeNewFileHandler.access$100(cMakeCommand, this.val$varDependencies, new CommandDependencyHandler() {
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
                        list.add(commandInfo);
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                });
                this.val$varDependencies.set(string, list);
            }
        }
    }
    
    @Override
    public void onEnterIf() {
        this.a();
    }
    
    @Override
    public void onExitIf() {
        this.b();
    }
    
    @Override
    public void onEnterWhile() {
        this.a();
    }
    
    @Override
    public void onExitWhile() {
        this.b();
    }
    
    @Override
    public void onEnterForeach() {
        this.a();
    }
    
    @Override
    public void onExitForeach() {
        this.b();
    }
    
    private void b() {
        this.val$varDependencies.setAll(this.val$controlVarDependenciesStack.pop());
    }
    
    private void a() {
        this.val$controlVarDependenciesStack.push(new VarDependencies(this.val$varDependencies));
    }
    
    @Override
    public boolean onEnterLoop(final int n) {
        return true;
    }
    
    @Override
    public void onExitLoop() {
        this.val$controlVarDependenciesStack.peek().addAll(this.val$varDependencies);
    }
    
    @Override
    public void onEnterFunction() {
        this.a();
    }
    
    @Override
    public void onExitFunction() {
        this.b();
    }
    
    @Override
    public void onEnterMacro() {
        this.a();
    }
    
    @Override
    public void onExitMacro() {
        this.b();
    }
    
    @Override
    public boolean onEnterBranch(final int n) {
        this.val$branchVarDependenciesStack.push(new VarDependencies(this.val$varDependencies));
        return true;
    }
    
    @Override
    public void onExitBranch() {
        this.val$controlVarDependenciesStack.peek().addAll(this.val$varDependencies);
        this.val$varDependencies.setAll(this.val$branchVarDependenciesStack.pop());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}