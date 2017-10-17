// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import java.util.List;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.intellij.openapi.project.Project;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.intentions.OCAddParametersToConstructorIntentionAction;

class OCCppChecker$4 extends OCAddParametersToConstructorIntentionAction {
    final /* synthetic */ OCDeclaratorSymbol val$field;
    final /* synthetic */ OCSymbolWithQualifiedName val$parent;
    final /* synthetic */ OCFunctionDeclaration val$function;
    final /* synthetic */ OCFunctionSymbol val$symbol;
    
    @Nullable
    @Override
    protected OCDeclaratorSymbol getField(final Editor editor, final PsiFile psiFile) {
        return this.val$field;
    }
    
    @NotNull
    @Override
    public String getText() {
        String string;
        try {
            string = "Add " + this.val$field.getNameWithKindLowercase() + " as a parameter to constructor";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCCppChecker$4", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return string;
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCFunctionSymbol> collection) {
        return false;
    }
    
    @Nullable
    @Override
    protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        return new OCCppActionContext<OCStructSymbol, OCFunctionSymbol>((OCStructSymbol)this.val$parent, this.val$function) {
            @NotNull
            @Override
            public Collection<OCFunctionSymbol> getMemberCandidates() {
                List<OCFunctionSymbol> singletonList;
                try {
                    singletonList = Collections.singletonList(OCAddParametersToConstructorIntentionAction.this.val$symbol);
                    if (singletonList == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCCppChecker$4$1", "getMemberCandidates"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return singletonList;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}