// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.implement.OCImplementOCMethodsHandler;

public class OCImplementMethodIntentionAction extends OCImplementOCMethodsHandler implements IntentionAction
{
    private OCMethodSymbol myMethod;
    
    public OCImplementMethodIntentionAction(final OCMethodSymbol myMethod) {
        this.myMethod = myMethod;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return text;
    }
    
    @NotNull
    public String getText() {
        String string = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (this.myMethod != null) {
                        break Label_0055;
                    }
                    s = "Invalid";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                try {
                    s = "Invalid";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "getText"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            return s;
            try {
                string = "Implement " + this.myMethod.getNameWithKindLowercase();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "getText"));
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return string;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return OCSearchScope.isInProjectSources(this.myMethod);
    }
    
    protected boolean enableChooseDialog(final Collection<OCMethodSymbol> collection) {
        return false;
    }
    
    @Override
    protected boolean selectAllCandidates() {
        return false;
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return ((OCSymbolWithParent<T, OCClassSymbol>)this.myMethod).getParent();
    }
    
    @NotNull
    @Override
    protected OCOverrideImplementActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCOverrideImplementActionContext ocOverrideImplementActionContext;
        try {
            ocOverrideImplementActionContext = new OCOverrideImplementActionContext(ocClassSymbol, ocClassSymbol.getResolvedType(true), psiElement) {
                @NotNull
                @Override
                public Collection<OCMethodSymbol> getMemberCandidates() {
                    List<OCMethodSymbol> singletonList;
                    try {
                        singletonList = Collections.singletonList(OCImplementMethodIntentionAction.this.myMethod);
                        if (singletonList == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction$1", "getMemberCandidates"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw b(ex);
                    }
                    return singletonList;
                }
                
                private static IllegalStateException b(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return ocOverrideImplementActionContext;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
