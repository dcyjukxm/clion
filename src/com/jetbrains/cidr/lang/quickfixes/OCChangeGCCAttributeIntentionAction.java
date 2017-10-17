// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInspection.SuppressIntentionAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import java.util.List;
import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.util.Processor;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCChangeGCCAttributeIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private String myAttribute;
    private String myAttributeText;
    private boolean myEnable;
    private String myText;
    
    public OCChangeGCCAttributeIntentionAction(@Nullable final OCSymbol ocSymbol, final String myAttribute, final String myAttributeText, final boolean myEnable) {
        super(ocSymbol);
        this.myAttribute = myAttribute;
        this.myAttributeText = myAttributeText;
        this.myEnable = myEnable;
        if (ocSymbol != null) {
            StringBuilder sb = null;
            String s = null;
            Label_0049: {
                try {
                    sb = new StringBuilder();
                    if (myEnable) {
                        s = "Enable '";
                        break Label_0049;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                s = "Disable '";
            }
            this.myText = sb.append(s).append(this.myAttributeText).append("' for ").append(ocSymbol.getNameWithKindLowercase()).toString();
        }
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        Label_0037: {
            try {
                if (!OCCodeInsightUtil.isValid(ocSymbol.locateDefinition())) {
                    return false;
                }
                final OCChangeGCCAttributeIntentionAction ocChangeGCCAttributeIntentionAction = this;
                final boolean b = ocChangeGCCAttributeIntentionAction.myEnable;
                final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                final OCChangeGCCAttributeIntentionAction ocChangeGCCAttributeIntentionAction2 = this;
                final String s = ocChangeGCCAttributeIntentionAction2.myAttribute;
                final boolean b2 = ocSymbol2.hasAttribute(s);
                final boolean b3 = b ^ b2;
                if (b3) {
                    break Label_0037;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCChangeGCCAttributeIntentionAction ocChangeGCCAttributeIntentionAction = this;
                final boolean b = ocChangeGCCAttributeIntentionAction.myEnable;
                final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                final OCChangeGCCAttributeIntentionAction ocChangeGCCAttributeIntentionAction2 = this;
                final String s = ocChangeGCCAttributeIntentionAction2.myAttribute;
                final boolean b2 = ocSymbol2.hasAttribute(s);
                final boolean b3 = b ^ b2;
                if (b3) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return this.myText;
    }
    
    @NotNull
    public String getFamilyName() {
        String string;
        try {
            string = "Change attribute '" + this.myAttributeText + "'";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Override
    protected void invoke(final OCSymbol<?> ocSymbol) {
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        ocSymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<?>>)(ocSymbol -> {
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            try {
                if (locateDefinition != null) {
                    list.add(locateDefinition);
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return true;
        }));
        for (final PsiElement psiElement : list) {
            this.a(psiElement);
            int n = -1;
            if (psiElement instanceof OCDeclarator) {
                n = psiElement.getTextRange().getStartOffset();
            }
            else if (psiElement instanceof OCStructLike) {
                n = psiElement.getParent().getTextRange().getEndOffset();
            }
            else if (psiElement instanceof OCMethod) {
                final List<OCMethodSelectorPart> parameters = ((OCMethod)psiElement).getParameters();
                if (!parameters.isEmpty()) {
                    n = parameters.get(parameters.size() - 1).getTextRange().getEndOffset();
                }
            }
            else if (psiElement instanceof OCMethodSelectorPart) {
                final OCTypeElement typeElement = ((OCMethodSelectorPart)psiElement).getTypeElement();
                if (typeElement != null) {
                    n = typeElement.getTextOffset();
                }
            }
            else if (psiElement instanceof OCLabeledStatement) {
                n = psiElement.getTextOffset();
            }
            else {
                try {
                    assert false : ((OCMethodSelectorPart)psiElement).getClass();
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
            }
            Label_0270: {
                try {
                    if (n == -1) {
                        continue;
                    }
                    final OCChangeGCCAttributeIntentionAction ocChangeGCCAttributeIntentionAction = this;
                    final boolean b = ocChangeGCCAttributeIntentionAction.myEnable;
                    if (b) {
                        break Label_0270;
                    }
                    break Label_0270;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCChangeGCCAttributeIntentionAction ocChangeGCCAttributeIntentionAction = this;
                    final boolean b = ocChangeGCCAttributeIntentionAction.myEnable;
                    if (b) {
                        OCChangeUtil.changeText(psiElement.getProject(), psiElement.getContainingFile(), n, 0, " " + this.myAttributeText + " ", true);
                        continue;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            final int index = psiElement.getText().indexOf(this.myAttributeText);
            try {
                if (index == -1) {
                    continue;
                }
                OCChangeUtil.changeText(psiElement.getProject(), psiElement.getContainingFile(), psiElement.getTextRange().getStartOffset() + index, this.myAttributeText.length(), "", true);
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
    }
    
    private void a(final PsiElement psiElement) {
        final PsiFile containingFile = psiElement.getContainingFile();
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)containingFile);
        ocResolveContext.setProcessNonImported(true);
        for (final OCSymbol ocSymbol : ocResolveContext.resolveToSymbols(OCSymbolReference.getGlobalReference(this.myAttributeText, null))) {
            Label_0118: {
                try {
                    if (ocSymbol.getKind() != OCSymbolKind.MACRO) {
                        continue;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final OCImportSymbolFix ocImportSymbolFix = new OCImportSymbolFix((PsiElement)psiFile, ocSymbol2);
                    final PsiFile psiFile2 = containingFile;
                    final Project project = psiFile2.getProject();
                    final PsiFile psiFile3 = containingFile;
                    final boolean b = ocImportSymbolFix.fixFirstItem(project, psiFile3);
                    if (b) {
                        break Label_0118;
                    }
                    continue;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final OCImportSymbolFix ocImportSymbolFix = new OCImportSymbolFix((PsiElement)psiFile, ocSymbol2);
                    final PsiFile psiFile2 = containingFile;
                    final Project project = psiFile2.getProject();
                    final PsiFile psiFile3 = containingFile;
                    final boolean b = ocImportSymbolFix.fixFirstItem(project, psiFile3);
                    if (b) {
                        break;
                    }
                    continue;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCChangeGCCAttributeIntentionAction.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public static class SuppressFix extends SuppressIntentionAction
    {
        private OCChangeGCCAttributeIntentionAction myAction;
        
        public SuppressFix(final OCSymbol ocSymbol, final String s, final String s2, final boolean b) {
            this.myAction = new OCChangeGCCAttributeIntentionAction(ocSymbol, s, s2, b) {
                @Override
                protected boolean isAvailable(final OCSymbol ocSymbol) {
                    return super.isAvailable(ocSymbol) && SuppressFix.this.isAvailable(ocSymbol);
                }
            };
        }
        
        public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "invoke"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "invoke"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            this.myAction.invoke(project, editor, psiElement.getContainingFile());
        }
        
        public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "isAvailable"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "isAvailable"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            return this.myAction.isAvailable(project, editor, psiElement.getContainingFile());
        }
        
        protected boolean isAvailable(final OCSymbol ocSymbol) {
            return true;
        }
        
        @NotNull
        public String getFamilyName() {
            String familyName;
            try {
                familyName = this.myAction.getFamilyName();
                if (familyName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "getFamilyName"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return familyName;
        }
        
        @NotNull
        public String getText() {
            String text;
            try {
                text = this.myAction.getText();
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "getText"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return text;
        }
        
        public boolean startInWriteAction() {
            return this.myAction.startInWriteAction();
        }
        
        private static IncorrectOperationException a(final IncorrectOperationException ex) {
            return ex;
        }
    }
}
