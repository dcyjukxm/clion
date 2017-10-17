// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseInplaceIntroducer;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIvarInplaceIntroducer;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseLocalConvertibleHandler;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPointerManager;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.List;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateIvarsActionContext;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateIvarsHandler;

public class OCConvertToIvarIntentionAction extends OCGenerateIvarsHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Convert to instance variable";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "getText"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @Nullable
    public OCPropertySymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCSendMessageExpression adjacentParentOfType = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class, OCSendMessageExpression.class);
        OCSymbol ocSymbol = null;
        if (adjacentParentOfType instanceof OCReferenceElement) {
            ocSymbol = ((OCReferenceElement)adjacentParentOfType).resolveToSymbol();
        }
        else if (adjacentParentOfType instanceof OCDeclarator) {
            ocSymbol = ((OCDeclarator)adjacentParentOfType).getSymbol();
        }
        else if (adjacentParentOfType instanceof OCQualifiedExpression) {
            ocSymbol = ((OCQualifiedExpression)adjacentParentOfType).resolveToSymbol();
        }
        else if (adjacentParentOfType instanceof OCSendMessageExpression) {
            ocSymbol = adjacentParentOfType.getProbableResponders().getKnownResponder();
        }
        if (ocSymbol instanceof OCMethodSymbol) {
            ocSymbol = ((OCMethodSymbol)ocSymbol).getGeneratedFromProperty();
        }
        try {
            if (ocSymbol instanceof OCPropertySymbol) {
                return (OCPropertySymbol)ocSymbol;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "isAvailable"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "getParent"));
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
        return true;
    }
    
    protected boolean enableChooseDialog(final Collection<OCPropertySymbol> collection) {
        return false;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCGenerateIvarsActionContext ocGenerateIvarsActionContext, @NotNull final List<OCPropertySymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocGenerateIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "properties", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final OCPropertySymbol locateCandidate = this.locateCandidate(project, editor, psiFile);
        try {
            if (locateCandidate == null) {
                return;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        final OCInstanceVariableSymbol associatedIvar = locateCandidate.getAssociatedIvar();
        final OCElement ocElement = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class, OCSendMessageExpression.class);
        String s = null;
        Label_0284: {
            try {
                if (associatedIvar != null) {
                    s = associatedIvar.getName();
                    break Label_0284;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            s = OCNameSuggester.getNonCollidingName(locateCandidate, true);
        }
        final String s2 = s;
        new OCBaseLocalConvertibleHandler("Introduce Ivar") {
            final /* synthetic */ SmartPsiElementPointer val$pointer = SmartPointerManager.getInstance(project).createSmartPsiElementPointer((PsiElement)ocElement);
            
            @Override
            protected OCIvarInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
                return new OCIvarInplaceIntroducer(project, editor, psiElement, list, s) {
                    @Override
                    public PsiElement evaluateAnchor() {
                        return (PsiElement)ocElement;
                    }
                    
                    @Override
                    public PsiElement[] getOccurrences() {
                        final PsiElement element = OCBaseLocalConvertibleHandler.this.val$pointer.getElement();
                        final PsiElement[] array;
                        if (element != null) {
                            array = new PsiElement[] { element };
                        }
                        else {
                            final PsiElement[] empty_ARRAY = PsiElement.EMPTY_ARRAY;
                        }
                        return array;
                    }
                    
                    @Override
                    protected void performIntroduce() {
                        OCConvertToIvarIntentionAction.this.performAction(psiFile, ocGenerateIvarsActionContext, list, this.getInputName(), this.myDeclareInInterfaceCb != null && !this.myDeclareInInterfaceCb.isSelected());
                    }
                    
                    @Override
                    protected boolean askToGenerateProperty() {
                        return false;
                    }
                    
                    @Override
                    protected String[] suggestNames(final boolean b, @Nullable final OCDeclarator ocDeclarator) {
                        if (!s2.equals(locateCandidate.getName())) {
                            return new String[] { s2, locateCandidate.getName() };
                        }
                        return new String[] { s2 };
                    }
                };
            }
            
            @NotNull
            @Override
            protected String getFeatureID() {
                String s;
                try {
                    s = "refactoring.convertToIvar";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToIvarIntentionAction$1", "getFeatureID"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return s;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }.invoke(project, editor, (PsiElement)ocElement, locateCandidate.getResolvedType(), true, false, s2, locateCandidate.getParent());
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
