// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.symbols.OCSymbolHolderBase;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.navigation.OCGotoClassContributor;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.ide.util.TreeChooser;
import java.awt.event.ActionEvent;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import com.intellij.util.Function;
import com.intellij.ui.EditorTextField;
import java.awt.event.ActionListener;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.ui.ReferenceEditorWithBrowseButton;

public class OCTypeReferenceEditor extends ReferenceEditorWithBrowseButton
{
    private boolean myOnlyInProject;
    private Condition<OCSymbol> myCondition;
    
    public OCTypeReferenceEditor(final ActionListener actionListener, final EditorTextField editorTextField, final Function<String, Document> function, final boolean myOnlyInProject, final Condition<OCSymbol> myCondition) {
        super(actionListener, editorTextField, function);
        this.myOnlyInProject = myOnlyInProject;
        this.myCondition = myCondition;
    }
    
    public static OCTypeReferenceEditor create(@Nullable final OCSymbol ocSymbol, @NotNull final Condition<OCSymbol> condition, @Nullable final PsiElement psiElement, final boolean b, @NotNull final Project project) {
        try {
            if (condition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor", "create"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final Ref create = Ref.create((Object)null);
        final Function function = s -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor", "lambda$create$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return PsiDocumentManager.getInstance(project).getDocument((PsiFile)OCElementFactory.getTypeCodeFragmentInWriteAction(s.trim(), project, psiElement));
        };
        final Condition condition2 = ocSymbol -> {
            try {
                if (condition == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor", "lambda$create$1"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (!condition.value((Object)ocSymbol)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            return OCSearchScope.isInProjectSources(ocSymbol);
        };
        final OCTextFieldWithSymbolAutoCompletion<Object> create2 = OCTextFieldWithSymbolAutoCompletion.create(project, (OCFieldAdapter<Object>)OCFieldAdapterForSymbolName.INSTANCE, (com.intellij.openapi.util.Condition<Object>)condition2);
        try {
            create.set((Object)new OCTypeReferenceEditor(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    final OCSymbol classDeclaration = ((OCTypeReferenceEditor)create.get()).getClassDeclaration(project);
                    final OCClassChooserDialog ocClassChooserDialog = new OCClassChooserDialog("Choose Containing Class", project, (TreeChooser.Filter<OCSymbolHolderVirtualPsiElement>)new TreeChooser.Filter<OCSymbolHolderVirtualPsiElement>() {
                        public boolean isAccepted(final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement) {
                            return condition2.value(((OCSymbolHolderBase<Object>)ocSymbolHolderVirtualPsiElement).getSymbol());
                        }
                    }, (classDeclaration != null) ? new OCSymbolHolderVirtualPsiElement(classDeclaration) : null, (Condition<OCSymbol>)condition2);
                    ocClassChooserDialog.showDialog();
                    if (ocClassChooserDialog.isOK()) {
                        ((OCTypeReferenceEditor)create.get()).setText(ocClassChooserDialog.getSelected().getSymbol().getPresentableName());
                    }
                }
            }, create2, (Function<String, Document>)function, b, (Condition<OCSymbol>)condition2));
            if (ocSymbol != null) {
                create2.setText(ocSymbol.getPresentableName());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return (OCTypeReferenceEditor)create.get();
    }
    
    @Nullable
    public OCSymbol getClassDeclaration(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor", "getClassDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCGotoClassContributor ocGotoClassContributor = null;
        String text = null;
        boolean b = false;
        Label_0072: {
            try {
                ocGotoClassContributor = new OCGotoClassContributor();
                text = this.getText();
                if (!this.myOnlyInProject) {
                    b = true;
                    break Label_0072;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            b = false;
        }
        final OCSymbol[] symbolsByName = ocGotoClassContributor.getSymbolsByName(text, project, b, this.myCondition, true);
        try {
            if (symbolsByName.length > 0) {
                return symbolsByName[0];
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
