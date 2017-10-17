// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.PsiComment;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.util.ui.EmptyIcon;
import javax.swing.Icon;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ColoredItemPresentation;

private static class OCItemPresentation implements ColoredItemPresentation
{
    private final PsiElement myElement;
    private final OCSymbol mySymbol;
    
    private OCItemPresentation(final PsiElement myElement) {
        this.myElement = myElement;
        if (this.myElement instanceof OCSymbolDeclarator && this.myElement.isValid()) {
            this.mySymbol = ((OCSymbolDeclarator)this.myElement).getSymbol();
        }
        else {
            this.mySymbol = null;
        }
    }
    
    public String getPresentableText() {
        if (this.mySymbol instanceof OCMacroSymbol) {
            return ((OCMacroSymbol)this.mySymbol).getPresentableSignature();
        }
        if (this.mySymbol instanceof OCFunctionSymbol) {
            return ((OCFunctionSymbol)this.mySymbol).getSignatureWithoutParamNames();
        }
        if (this.mySymbol instanceof OCDeclaratorSymbol) {
            return a((OCDeclaratorSymbol)this.mySymbol);
        }
        if (this.mySymbol instanceof OCStructSymbol) {
            return a((OCStructSymbol)this.mySymbol);
        }
        if (this.myElement instanceof PsiFile) {
            return ((PsiFile)this.myElement).getName();
        }
        if (this.myElement instanceof OCMethod) {
            return ((OCMethod)this.myElement).getSelector();
        }
        if (this.myElement instanceof OCClassDeclaration) {
            final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)this.myElement;
            final String name = ocClassDeclaration.getName();
            if (name == null) {
                return "<unnamed>";
            }
            return OCCodeInsightUtil.getClassNameWithCategory(name, ocClassDeclaration.getCategory());
        }
        else {
            if (this.a()) {
                final OCMark fromElement = OCMark.createFromElement(this.myElement);
                return (fromElement != null) ? fromElement.getText() : "<unnamed>";
            }
            if (!(this.myElement instanceof PsiNameIdentifierOwner)) {
                return "<unknown>";
            }
            final String name2 = ((PsiNameIdentifierOwner)this.myElement).getName();
            if (name2 == null && this.myElement instanceof OCEnum) {
                final StringBuilder sb = new StringBuilder("enum {");
                int n = 1;
                for (final OCDeclaration ocDeclaration : ((OCEnum)this.myElement).getFields()) {
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        sb.append(", ");
                    }
                    if (sb.length() > 30) {
                        sb.append("...");
                        break;
                    }
                    sb.append(ocDeclaration.getDeclarators().get(0).getName());
                }
                sb.append("}");
                return sb.toString();
            }
            return (name2 == null) ? "<unnamed>" : name2;
        }
    }
    
    private static String a(final OCStructSymbol ocStructSymbol) {
        return ocStructSymbol.getQualifiedName().toString();
    }
    
    private static String a(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final StringBuilder sb = new StringBuilder();
        final String canonicalName = ocDeclaratorSymbol.getQualifiedName().getCanonicalName(true);
        sb.append(canonicalName);
        if (ocDeclaratorSymbol.getKind() != OCSymbolKind.ENUM_CONST) {
            final String name = ocDeclaratorSymbol.getType().getName();
            if (!Comparing.equal(name, canonicalName)) {
                sb.append(" : ");
                sb.append(name);
            }
        }
        return sb.toString();
    }
    
    public String getLocationString() {
        return null;
    }
    
    public Icon getIcon(final boolean b) {
        return this.a() ? EmptyIcon.ICON_16 : (this.myElement.isValid() ? this.myElement.getIcon(0) : null);
    }
    
    public TextAttributesKey getTextAttributesKey() {
        return this.a() ? OCStructureViewElement.OC_PRAGMA_MARK : null;
    }
    
    private boolean a() {
        return this.myElement instanceof OCPragma || this.myElement instanceof PsiComment;
    }
}
