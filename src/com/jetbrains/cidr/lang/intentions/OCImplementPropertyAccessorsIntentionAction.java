// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateIvarsHandler;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.implement.OCImplementOCMethodsHandler;

public class OCImplementPropertyAccessorsIntentionAction extends OCImplementOCMethodsHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Implement accessor methods";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return text;
    }
    
    @Nullable
    protected static OCPropertySymbol locateProperty(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "locateProperty"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        final OCDeclarator ocDeclarator = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCDeclarator>)OCDeclarator.class);
        if (ocDeclarator != null) {
            final OCPropertySymbol symbol = ocDeclarator.getSymbol();
            Label_0123: {
                try {
                    if (!(symbol instanceof OCPropertySymbol)) {
                        return null;
                    }
                    final OCPropertySymbol ocPropertySymbol = symbol;
                    final OCPropertySymbol ocPropertySymbol2 = ocPropertySymbol;
                    final boolean b = OCGenerateIvarsHandler.isPropertyDefective(ocPropertySymbol2);
                    if (b) {
                        break Label_0123;
                    }
                    return null;
                }
                catch (IllegalStateException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCPropertySymbol ocPropertySymbol = symbol;
                    final OCPropertySymbol ocPropertySymbol2 = ocPropertySymbol;
                    final boolean b = OCGenerateIvarsHandler.isPropertyDefective(ocPropertySymbol2);
                    if (b) {
                        return symbol;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw b(ex4);
                }
            }
            return null;
        }
        return null;
    }
    
    @Override
    protected boolean defaultShowSynthesizedAccessors() {
        return true;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        Label_0070: {
            try {
                if (!OCCompilerFeatures.supportsAutosynthesis(psiFile)) {
                    return false;
                }
                final Project project2 = project;
                final Editor editor2 = editor;
                final PsiFile psiFile2 = psiFile;
                final OCPropertySymbol ocPropertySymbol = locateProperty(project2, editor2, psiFile2);
                final boolean b = OCSearchScope.isInProjectSources(ocPropertySymbol);
                if (b) {
                    break Label_0070;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                final Project project2 = project;
                final Editor editor2 = editor;
                final PsiFile psiFile2 = psiFile;
                final OCPropertySymbol ocPropertySymbol = locateProperty(project2, editor2, psiFile2);
                final boolean b = OCSearchScope.isInProjectSources(ocPropertySymbol);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        final OCPropertySymbol locateProperty = locateProperty(project, editor, psiFile);
        try {
            if (locateProperty != null) {
                return ((OCSymbolWithParent<T, OCClassSymbol>)locateProperty).getParent().getMainInterface();
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    @NotNull
    @Override
    protected Collection<OCMethodSymbol> getSelectedCandidates(@NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final List<OCMethodSymbol> list) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        final PsiElement context = ocOverrideImplementActionContext.getContext();
        final OCPropertySymbol locateProperty = locateProperty(context.getProject(), editor, context.getContainingFile());
        final CommonProcessors.CollectProcessor<OCMethodSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCMethodSymbol>() {
            public boolean process(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.getGeneratedFromProperty() != locateProperty || super.process((Object)ocMethodSymbol);
            }
        };
        try {
            if (locateProperty != null) {
                ((OCSymbolWithParent<T, OCClassSymbol>)locateProperty).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)collectProcessor);
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        Collection results;
        try {
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCImplementPropertyAccessorsIntentionAction", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex5) {
            throw b(ex5);
        }
        return (Collection<OCMethodSymbol>)results;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
