// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateIvarsHandler;

public abstract class OCSynthesizePropertyQuickFixBase extends OCGenerateIvarsHandler implements IntentionAction
{
    protected OCImplementationSymbol myImplementationSymbol;
    protected OCPropertySymbol myProperty;
    
    public OCSynthesizePropertyQuickFixBase(final OCImplementationSymbol myImplementationSymbol, final OCPropertySymbol myProperty) {
        this.myImplementationSymbol = myImplementationSymbol;
        this.myProperty = myProperty;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
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
                    if (this.myProperty != null) {
                        break Label_0055;
                    }
                    s = "Invalid";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    s = "Invalid";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "getText"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                string = "Synthesize " + this.myProperty.getNameWithKindLowercase();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "getText"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return string;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0068: {
            try {
                if (!OCSearchScope.isInProjectSources(this.myImplementationSymbol)) {
                    return false;
                }
                final OCSynthesizePropertyQuickFixBase ocSynthesizePropertyQuickFixBase = this;
                final OCPropertySymbol ocPropertySymbol = ocSynthesizePropertyQuickFixBase.myProperty;
                if (ocPropertySymbol != null) {
                    break Label_0068;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCSynthesizePropertyQuickFixBase ocSynthesizePropertyQuickFixBase = this;
                final OCPropertySymbol ocPropertySymbol = ocSynthesizePropertyQuickFixBase.myProperty;
                if (ocPropertySymbol != null) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    protected OCPropertySymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.myProperty;
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFixBase", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return this.myImplementationSymbol;
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
