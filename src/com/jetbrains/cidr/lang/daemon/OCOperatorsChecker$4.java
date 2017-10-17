// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.intentions.OCDeclareMethodInInterfaceIntentionAction;

class OCOperatorsChecker$4 extends OCDeclareMethodInInterfaceIntentionAction {
    final /* synthetic */ OCClassSymbol val$receiverClass;
    final /* synthetic */ OCMethodSymbol val$target;
    
    @NotNull
    @Override
    public String getText() {
        String string;
        try {
            string = "Declare method in " + this.val$receiverClass.getNameWithKindLowercase();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return string;
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return this.val$receiverClass;
    }
    
    @Override
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return OCSearchScope.isInProjectSources(this.val$target);
    }
    
    @Override
    protected OCMethodSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$4", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return this.val$target;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}