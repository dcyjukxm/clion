// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.implement.OCImplementOCMethodsHandler;

public class OCImplementAllMethodsIntentionAction extends OCImplementOCMethodsHandler implements IntentionAction
{
    private OCClassSymbol myParent;
    
    public OCImplementAllMethodsIntentionAction(final OCClassSymbol myParent) {
        this.myParent = myParent;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "getFamilyName"));
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
                    if (this.myParent != null) {
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
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "getText"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            return s;
            try {
                string = "Implement required methods of interface '" + this.myParent.getName() + "'";
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "getText"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return OCSearchScope.isInProjectSources(this.myParent);
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImplementAllMethodsIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return this.myParent;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
