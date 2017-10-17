// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public class OCCreateFunctionPredeclarationIntentionAction extends OCCreateDefinitionIntentionAction
{
    private OCFunctionSymbol myFunctionSymbol;
    
    public OCCreateFunctionPredeclarationIntentionAction(final PsiElement psiElement, final OCFunctionSymbol myFunctionSymbol) {
        super(OCSymbolKind.FUNCTION_DECLARATION, psiElement, null);
        this.myFunctionSymbol = myFunctionSymbol;
    }
    
    protected OCDeclaration getDefinition(final Project project, final Editor editor, final PsiFile psiFile) {
        final OCFunctionDeclaration locateFunctionDefinition = this.myFunctionSymbol.locateFunctionDefinition();
        try {
            if (locateFunctionDefinition == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String text = ((PsiElement)locateFunctionDefinition).getText();
        final int index = text.indexOf(123);
        try {
            if (index == -1) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return OCElementFactory.declarationFromText(text.substring(0, index), (PsiElement)psiFile);
    }
    
    @NotNull
    public String getText() {
        String string;
        try {
            string = "Predeclare " + this.myFunctionSymbol.getNameWithKindLowercase();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateFunctionPredeclarationIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
