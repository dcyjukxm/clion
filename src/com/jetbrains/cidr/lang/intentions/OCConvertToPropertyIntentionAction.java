// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseInplaceIntroducer;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.refactoring.introduce.OCPropertyInplaceIntroducer;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseLocalConvertibleHandler;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPointerManager;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.List;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateFromIvarsActionContext;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCGeneratePropertiesHandler;

public class OCConvertToPropertyIntentionAction extends OCGeneratePropertiesHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Convert to property";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getText"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @Nullable
    public OCInstanceVariableSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCQualifiedExpression adjacentParentOfType = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class);
        OCSymbol ocSymbol = null;
        if (adjacentParentOfType instanceof OCReferenceElement) {
            ocSymbol = ((OCReferenceElement)adjacentParentOfType).resolveToSymbol();
        }
        else if (adjacentParentOfType instanceof OCDeclarator) {
            ocSymbol = ((OCDeclarator)adjacentParentOfType).getSymbol();
        }
        else if (adjacentParentOfType instanceof OCQualifiedExpression) {
            ocSymbol = adjacentParentOfType.resolveToSymbol();
        }
        try {
            if (ocSymbol instanceof OCInstanceVariableSymbol) {
                return (OCInstanceVariableSymbol)ocSymbol;
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "isAvailable"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getParent"));
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
        return true;
    }
    
    protected boolean enableChooseDialog(final Collection<OCInstanceVariableSymbol> collection) {
        return false;
    }
    
    @NotNull
    @Override
    protected Condition<OCInstanceVariableSymbol> getCandidatesFilter(@NotNull final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext) {
        try {
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getCandidatesFilter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Condition alwaysTrue;
        try {
            alwaysTrue = Conditions.alwaysTrue();
            if (alwaysTrue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "getCandidatesFilter"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (Condition<OCInstanceVariableSymbol>)alwaysTrue;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext, @NotNull final List<OCInstanceVariableSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivars", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final OCInstanceVariableSymbol locateCandidate = this.locateCandidate(project, editor, psiFile);
        try {
            if (locateCandidate == null) {
                return;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        final OCPropertySymbol associatedProperty = locateCandidate.getAssociatedProperty();
        final OCElement ocElement = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class);
        String s = null;
        Label_0279: {
            try {
                if (associatedProperty != null) {
                    s = associatedProperty.getName();
                    break Label_0279;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            s = OCNameSuggester.getNonCollidingName(locateCandidate, true);
        }
        final String s2 = s;
        new OCBaseLocalConvertibleHandler("Introduce Property") {
            final /* synthetic */ SmartPsiElementPointer val$pointer = SmartPointerManager.getInstance(project).createSmartPsiElementPointer((PsiElement)ocElement);
            
            @Override
            protected OCPropertyInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
                return new OCPropertyInplaceIntroducer(project, editor, psiElement, list, s) {
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
                        OCConvertToPropertyIntentionAction.this.performAction(psiFile, ocGenerateFromIvarsActionContext, list, this.getInputName(), (OCPropertySymbol.PropertySemantics)this.mySemantics.compute(), this.myReadonly, this.isConvertUsages(), this.myPutToPrivateCategoryCB != null && this.myPutToPrivateCategoryCB.isSelected());
                    }
                    
                    @Override
                    protected boolean askToGenerateSynthesize() {
                        return false;
                    }
                    
                    @Override
                    protected boolean askToConvertUsages() {
                        return OCCompilerFeatures.supportsAutosynthesis(psiFile) && !(OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), OCReferenceElement.class, OCDeclarator.class, OCQualifiedExpression.class) instanceof OCQualifiedExpression);
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
                    s = "refactoring.convertToProperty";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCConvertToPropertyIntentionAction$1", "getFeatureID"));
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
