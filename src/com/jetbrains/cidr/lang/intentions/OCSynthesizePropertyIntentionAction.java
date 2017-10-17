// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateIvarsHandler;

public class OCSynthesizePropertyIntentionAction extends OCGenerateIvarsHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Synthesize property";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "getText"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @Nullable
    protected OCPropertySymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCDeclarator adjacentParentOfType = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCDeclarator>)OCDeclarator.class);
        if (adjacentParentOfType instanceof OCDeclarator) {
            final OCSymbol symbol = adjacentParentOfType.getSymbol();
            Label_0118: {
                try {
                    if (!(symbol instanceof OCPropertySymbol)) {
                        return null;
                    }
                    final OCSymbol ocSymbol = symbol;
                    final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
                    final boolean b = OCGenerateIvarsHandler.isPropertyDefective(ocPropertySymbol);
                    if (b) {
                        break Label_0118;
                    }
                    return null;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCSymbol ocSymbol = symbol;
                    final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
                    final boolean b = OCGenerateIvarsHandler.isPropertyDefective(ocPropertySymbol);
                    if (b) {
                        return (OCPropertySymbol)symbol;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            return null;
        }
        return null;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (!OCCompilerFeatures.supportsAutosynthesis(psiFile)) {
                    return false;
                }
                final OCSynthesizePropertyIntentionAction ocSynthesizePropertyIntentionAction = this;
                final Project project2 = project;
                final Editor editor2 = editor;
                final PsiFile psiFile2 = psiFile;
                final OCPropertySymbol ocPropertySymbol = ocSynthesizePropertyIntentionAction.locateCandidate(project2, editor2, psiFile2);
                final boolean b = OCSearchScope.isInProjectSources(ocPropertySymbol);
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCSynthesizePropertyIntentionAction ocSynthesizePropertyIntentionAction = this;
                final Project project2 = project;
                final Editor editor2 = editor;
                final PsiFile psiFile2 = psiFile;
                final OCPropertySymbol ocPropertySymbol = ocSynthesizePropertyIntentionAction.locateCandidate(project2, editor2, psiFile2);
                final boolean b = OCSearchScope.isInProjectSources(ocPropertySymbol);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCSynthesizePropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCPropertySymbol locateCandidate = this.locateCandidate(project, editor, psiFile);
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
    protected boolean replaceAllProperties() {
        return false;
    }
    
    protected boolean enableChooseDialog(final Collection<OCPropertySymbol> collection) {
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
