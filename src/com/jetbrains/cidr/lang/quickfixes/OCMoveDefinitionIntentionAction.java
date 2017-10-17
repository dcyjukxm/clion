// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.refactoring.util.OCNormalizeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCMoveDefinitionIntentionAction extends OCCreateDefinitionIntentionAction
{
    private OCSymbol myOldDefinitionSymbol;
    private String myIntentionNameSuffix;
    private PsiElement myOldDefinition;
    
    public OCMoveDefinitionIntentionAction(final OCSymbolKind ocSymbolKind, final PsiElement psiElement, final OCSymbol ocSymbol, final OCSymbol myOldDefinitionSymbol, final String myIntentionNameSuffix) {
        super(ocSymbolKind, psiElement, ocSymbol);
        this.myOldDefinitionSymbol = myOldDefinitionSymbol;
        this.myIntentionNameSuffix = myIntentionNameSuffix;
    }
    
    public OCMoveDefinitionIntentionAction(final OCSymbolKind ocSymbolKind, final PsiElement psiElement, @NotNull final OCSymbol ocSymbol, final OCSymbol myOldDefinitionSymbol) {
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction", "<init>"));
        }
        super(ocSymbolKind, psiElement, ocSymbol);
        this.myOldDefinitionSymbol = myOldDefinitionSymbol;
        this.myIntentionNameSuffix = " to " + ocSymbol.getNameWithKindLowercase();
    }
    
    public PsiElement getDefinition(final Project project, final Editor editor, final PsiFile psiFile) {
        Object myOldDefinition = this.myOldDefinitionSymbol.locateDefinition();
        try {
            if (myOldDefinition == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (myOldDefinition instanceof OCDeclarator) {
            myOldDefinition = OCNormalizeUtil.normalizeDeclarator((OCDeclarator)myOldDefinition);
        }
        try {
            if (myOldDefinition == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (this.mySymbolKind == OCSymbolKind.PROPERTY) {
            myOldDefinition = PsiTreeUtil.getContextOfType((PsiElement)myOldDefinition, new Class[] { OCProperty.class });
        }
        return this.myOldDefinition = (PsiElement)myOldDefinition;
    }
    
    @NotNull
    public String getText() {
        String string = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (this.myOldDefinitionSymbol != null) {
                        break Label_0055;
                    }
                    s = "Invalid";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    s = "Invalid";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction", "getText"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                string = "Move declaration of " + this.myOldDefinitionSymbol.getNameWithKindLowercase() + this.myIntentionNameSuffix;
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction", "getText"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return string;
    }
    
    @Override
    protected boolean doCreate(final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (super.doCreate(project, editor, psiFile)) {
                OCChangeUtil.delete(this.myOldDefinition);
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
