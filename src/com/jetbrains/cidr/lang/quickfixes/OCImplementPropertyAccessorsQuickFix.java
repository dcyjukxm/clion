// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.implement.OCImplementOCMethodsHandler;

public class OCImplementPropertyAccessorsQuickFix extends OCImplementOCMethodsHandler implements IntentionAction
{
    private OCPropertySymbol myProperty;
    
    public OCImplementPropertyAccessorsQuickFix(final OCPropertySymbol myProperty) {
        this.myProperty = myProperty;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return text;
    }
    
    @NotNull
    public String getText() {
        String string;
        try {
            string = "Implement accessor methods for " + this.myProperty.getNameWithKindLowercase();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return string;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return OCSearchScope.isInProjectSources(this.myProperty);
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getParent"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return ((OCSymbolWithParent<T, OCClassSymbol>)this.myProperty).getParent();
    }
    
    @Override
    protected boolean defaultShowSynthesizedAccessors() {
        return true;
    }
    
    @NotNull
    @Override
    protected Collection<OCMethodSymbol> getSelectedCandidates(@NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext, final Editor editor, @NotNull final PsiFile psiFile, @NotNull final List<OCMethodSymbol> list) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        final CommonProcessors.CollectProcessor<OCMethodSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCMethodSymbol>() {
            public boolean process(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.getGeneratedFromProperty() != OCImplementPropertyAccessorsQuickFix.this.myProperty || super.process((Object)ocMethodSymbol);
            }
        };
        Collection results;
        try {
            ((OCSymbolWithParent<T, OCClassSymbol>)this.myProperty).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)collectProcessor);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementPropertyAccessorsQuickFix", "getSelectedCandidates"));
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        return (Collection<OCMethodSymbol>)results;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
