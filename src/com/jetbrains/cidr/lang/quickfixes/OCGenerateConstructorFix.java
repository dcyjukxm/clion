// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateConstructorContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateConstructorHandler;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCGenerateConstructorFix implements IntentionAction
{
    private final OCStructSymbol myClass;
    private boolean myAddParametersForBaseClasses;
    
    public OCGenerateConstructorFix(final OCStructSymbol myClass, final boolean myAddParametersForBaseClasses) {
        this.myClass = myClass;
        this.myAddParametersForBaseClasses = myAddParametersForBaseClasses;
    }
    
    @NotNull
    public String getText() {
        String string = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (!this.myAddParametersForBaseClasses) {
                        break Label_0055;
                    }
                    s = "Create constructor matching base class(es)";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    s = "Create constructor matching base class(es)";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                string = "Create default constructor in " + this.myClass.getNameWithKindLowercase();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix", "getText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return string;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSearchScope.isInProjectSources(this.myClass);
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        new OCGenerateConstructorHandler() {
            @NotNull
            @Override
            protected List<? extends OCStructSymbol> getParents(@NotNull final PsiElement psiElement) {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "at", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getParents"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                List<OCStructSymbol> singletonList;
                try {
                    singletonList = Collections.singletonList(OCGenerateConstructorFix.this.myClass);
                    if (singletonList == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getParents"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                return singletonList;
            }
            
            @Override
            protected boolean addParametersForBaseClasses() {
                return OCGenerateConstructorFix.this.myAddParametersForBaseClasses;
            }
            
            @Override
            protected boolean enableChooseDialog(final Collection<OCDeclaratorSymbol> collection) {
                return false;
            }
            
            @NotNull
            @Override
            protected Condition<OCDeclaratorSymbol> getCandidatesFilter(@NotNull final OCGenerateConstructorContext ocGenerateConstructorContext) {
                try {
                    if (ocGenerateConstructorContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getCandidatesFilter"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                Condition alwaysFalse;
                try {
                    alwaysFalse = Conditions.alwaysFalse();
                    if (alwaysFalse == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCGenerateConstructorFix$1", "getCandidatesFilter"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                return (Condition<OCDeclaratorSymbol>)alwaysFalse;
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        }.invoke(project, null, psiFile);
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
