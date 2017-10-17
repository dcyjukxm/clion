// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCGeneratePropertiesHandler;

public class OCGeneratePropertyIntentionAction extends OCGeneratePropertiesHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Generate property";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @Nullable
    protected OCInstanceVariableSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCDeclarator ocDeclarator = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCDeclarator>)OCDeclarator.class);
        OCInstanceVariableSymbol symbol = null;
        Label_0095: {
            try {
                if (ocDeclarator != null) {
                    symbol = ocDeclarator.getSymbol();
                    break Label_0095;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            symbol = null;
        }
        final OCInstanceVariableSymbol ocInstanceVariableSymbol = symbol;
        try {
            if (!(ocInstanceVariableSymbol instanceof OCInstanceVariableSymbol)) {
                return null;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
        try {
            if (ocInstanceVariableSymbol2.getAssociatedProperty() == null) {
                return ocInstanceVariableSymbol2;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return OCSearchScope.isInProjectSources(this.locateCandidate(project, editor, psiFile));
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCGeneratePropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCInstanceVariableSymbol locateCandidate = this.locateCandidate(project, editor, psiFile);
        try {
            if (locateCandidate != null) {
                return ((OCSymbolWithParent<T, OCClassSymbol>)locateCandidate).getParent().getMainInterface();
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Override
    protected boolean replaceAllIvars() {
        return false;
    }
    
    protected boolean enableChooseDialog(final Collection<OCInstanceVariableSymbol> collection) {
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
