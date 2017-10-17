// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import java.util.Collections;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCTypedefInplaceIntroducer extends OCBaseInplaceIntroducer<OCDeclarator, PsiElement>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCTypedefInplaceIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        super(project, editor, psiElement, list, OCDeclarator.class, PsiElement.class, s);
    }
    
    @Override
    protected String checkExpression(final PsiElement psiElement) {
        if (psiElement instanceof OCDeclaration) {
            final String a = a(((OCDeclaration)psiElement).getTypeElement());
            try {
                if (a != null) {
                    return a;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (((OCDeclaration)psiElement).getDeclarators().size() != 1) {
                    return "Declaration must have one declarator to introduce the type";
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        else {
            try {
                if (psiElement instanceof OCTypeElement) {
                    return a((OCTypeElement)psiElement);
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                if (!(psiElement instanceof OCCppNamespaceQualifier)) {
                    return null;
                }
                final PsiElement psiElement2 = psiElement;
                final OCCppNamespaceQualifier ocCppNamespaceQualifier = (OCCppNamespaceQualifier)psiElement2;
                final OCType ocType = a(ocCppNamespaceQualifier);
                if (ocType == null) {
                    return "Namespace qualifier should be resolved to the class";
                }
                return null;
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final OCCppNamespaceQualifier ocCppNamespaceQualifier = (OCCppNamespaceQualifier)psiElement2;
                final OCType ocType = a(ocCppNamespaceQualifier);
                if (ocType == null) {
                    return "Namespace qualifier should be resolved to the class";
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return null;
    }
    
    @Nullable
    private static OCType a(final OCCppNamespaceQualifier ocCppNamespaceQualifier) {
        for (final OCSymbol ocSymbol : ocCppNamespaceQualifier.resolveToSymbols()) {
            try {
                if (ocSymbol instanceof OCStructSymbol) {
                    return ocSymbol.getType();
                }
                continue;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    private static String a(final OCTypeElement ocTypeElement) {
        Label_0025: {
            try {
                if (ocTypeElement == null) {
                    return "The type is empty";
                }
                final OCTypeElement ocTypeElement2 = ocTypeElement;
                final boolean b = OCElementUtil.isElementEmpty((PsiElement)ocTypeElement2);
                if (b) {
                    return "The type is empty";
                }
                break Label_0025;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCTypeElement ocTypeElement2 = ocTypeElement;
                final boolean b = OCElementUtil.isElementEmpty((PsiElement)ocTypeElement2);
                if (b) {
                    return "The type is empty";
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCType type = ocTypeElement.getType();
        Label_0056: {
            try {
                if (!(type instanceof OCStructType)) {
                    return null;
                }
                final OCType ocType = type;
                final OCStructType ocStructType = (OCStructType)ocType;
                final boolean b2 = ocStructType.isPredeclaration();
                if (!b2) {
                    break Label_0056;
                }
                return null;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final OCType ocType = type;
                final OCStructType ocStructType = (OCStructType)ocType;
                final boolean b2 = ocStructType.isPredeclaration();
                if (!b2) {
                    return "Can't extract type of " + ((OCStructType)type).getKind().getNameLowercase() + " definition";
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    @Override
    public PsiElement evaluateAnchor() {
        final OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType(this.getCommonContext(), false, new Class[] { OCCppNamespace.class, OCFile.class });
        try {
            if (ocElement != null) {
                return this.findAnchor((PsiElement)ocElement);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    protected String[] suggestNames(final boolean b, @Nullable final OCDeclarator ocDeclarator) {
        try {
            if (this.myUsageName != null) {
                return new String[] { this.myUsageName };
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCType ocType;
        if (this.myExpr instanceof OCDeclaration) {
            ocType = ((OCDeclaration)this.myExpr).getDeclarators().get(0).getType();
        }
        else if (this.myExpr instanceof OCTypeElement) {
            ocType = ((OCTypeElement)this.myExpr).getType();
        }
        else {
            ocType = a((OCCppNamespaceQualifier)this.myExpr);
        }
        Label_0117: {
            try {
                if (OCTypedefInplaceIntroducer.$assertionsDisabled) {
                    break Label_0117;
                }
                final OCType ocType2 = ocType;
                if (ocType2 == null) {
                    break Label_0117;
                }
                break Label_0117;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType2 = ocType;
                if (ocType2 == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final Collection<String> suggestForType = OCNameSuggester.suggestForType(ocType.resolve(this.myExpr.getContainingFile()), this.myExpr, (Collection<String>)Collections.emptyList());
        String[] array = null;
        try {
            if (suggestForType.isEmpty()) {
                array = new String[] { "x" };
                return array;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final String[] array2 = suggestForType.toArray(new String[suggestForType.size()]);
        return array;
    }
    
    private OCDeclaration a(final String s, final boolean b) {
        final OCDeclaration mainExpression = ((OCBaseInplaceIntroducer<V, OCDeclaration>)this).getMainExpression();
        Object anchor = this.getAnchor();
        boolean b2 = false;
        Label_0026: {
            try {
                if (anchor == mainExpression) {
                    b2 = true;
                    break Label_0026;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b2 = false;
        }
        final boolean b3 = b2;
        OCType ocType;
        if (mainExpression instanceof OCTypeElement) {
            ocType = ((OCTypeElement)mainExpression).getType();
        }
        else if (mainExpression instanceof OCCppNamespaceQualifier) {
            ocType = a((OCCppNamespaceQualifier)mainExpression);
        }
        else {
            final OCDeclaration ocDeclaration = mainExpression;
            final OCDeclarator ocDeclarator = ocDeclaration.getDeclarators().get(0);
            final String name = ocDeclarator.getName();
            ocType = ocDeclarator.getType();
            String s2 = s + " " + name;
            if (ocDeclaration.isTypedef()) {
                s2 = "typedef " + s2;
            }
            if (ocDeclarator.getInitializer() != null) {
                s2 = s2 + " = " + ocDeclarator.getInitializer().getTextWithMacros();
            }
            OCDeclaration ocDeclaration2 = null;
            Label_0244: {
                try {
                    if (ocDeclaration instanceof OCParameterDeclaration) {
                        ocDeclaration2 = OCElementFactory.paramDeclarationFromText(s2, (PsiElement)anchor);
                        break Label_0244;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                ocDeclaration2 = OCElementFactory.declarationFromText(s2, (PsiElement)anchor);
            }
            final OCDeclaration ocDeclaration3 = (OCDeclaration)OCChangeUtil.replaceHandlingMacros((PsiElement)mainExpression, (PsiElement)ocDeclaration2);
            if (b3) {
                anchor = ocDeclaration3;
            }
            this.myOccurrences[0] = (PsiElement)ocDeclaration3.getTypeElement();
        }
        if (ocType instanceof OCFunctionType) {
            ocType = OCPointerType.to(ocType);
        }
        Object prevSibling = mainExpression;
        Label_0328: {
            try {
                if (anchor == null || ((PsiElement)anchor).getPrevSibling() == null) {
                    break Label_0328;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            prevSibling = ((PsiElement)anchor).getPrevSibling();
        }
        final OCDeclaration declaration = OCElementFactory.declaration("typedef", s, ocType, (PsiElement)prevSibling);
        final PsiElement anchorParent = this.getAnchorParent((PsiElement)anchor, b);
        OCDeclaration ocDeclaration4 = null;
        Label_0418: {
            Label_0404: {
                PsiElement psiElement3 = null;
                Label_0384: {
                    Label_0374: {
                        try {
                            if (!(mainExpression instanceof OCCppNamespaceQualifier)) {
                                break Label_0404;
                            }
                            final PsiElement psiElement = anchorParent;
                            final PsiElement psiElement2 = (PsiElement)anchor;
                            final boolean b4 = false;
                            final boolean b5 = PsiTreeUtil.isAncestor(psiElement, psiElement2, b4);
                            if (b5) {
                                break Label_0374;
                            }
                            break Label_0374;
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final PsiElement psiElement = anchorParent;
                            final PsiElement psiElement2 = (PsiElement)anchor;
                            final boolean b4 = false;
                            final boolean b5 = PsiTreeUtil.isAncestor(psiElement, psiElement2, b4);
                            if (b5) {
                                psiElement3 = (PsiElement)anchor;
                                break Label_0384;
                            }
                        }
                        catch (IllegalStateException ex5) {
                            throw a(ex5);
                        }
                    }
                    psiElement3 = null;
                }
                ocDeclaration4 = (OCDeclaration)OCChangeUtil.addHandlingMacros(anchorParent, (PsiElement)declaration, psiElement3, true);
                break Label_0418;
            }
            ocDeclaration4 = OCChangeUtil.addBefore(anchorParent, declaration, (PsiElement)anchor);
            try {
                if (b) {
                    ((OCBaseInplaceIntroducer<OCDeclarator, E>)this).setVariable(ocDeclaration4.getDeclarators().get(0));
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return ocDeclaration4;
    }
    
    @Override
    protected void introduceForPreview(final String s) {
        this.a(s, true);
    }
    
    @Override
    protected void introduceForReal(final String s) {
        OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)this.a(s, false));
        for (final PsiElement psiElement : this.myOccurrences) {
            if (psiElement instanceof OCTypeElement) {
                String string = s;
                Label_0107: {
                    try {
                        if (!(psiElement.getParent() instanceof OCDeclaration) || !((OCDeclaration)psiElement.getParent()).isTypedef()) {
                            break Label_0107;
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    string = "typedef " + string;
                }
                OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)OCElementFactory.typeElementFromText(string, psiElement));
            }
            else if (psiElement instanceof OCCppNamespaceQualifier) {
                OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)OCElementFactory.declarationFromText("int " + s + "::x;", psiElement).getDeclarators().get(0).getNamespaceQualifier());
            }
            else {
                OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)OCElementFactory.declarationFromText(s + " " + ((OCDeclaration)psiElement).getDeclarators().get(0).getName(), psiElement));
            }
        }
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceTypedef";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCTypedefInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public PsiElement getPreviewElement(final OCDeclarator ocDeclarator) {
        return ocDeclarator.getParent();
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCTypedefInplaceIntroducer.class.desiredAssertionStatus()) {
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
}
