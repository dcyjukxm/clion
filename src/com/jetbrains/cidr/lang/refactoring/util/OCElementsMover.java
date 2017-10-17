// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.quickfixes.OCChangeVisibilityIntentionAction;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCSuperClassRef;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.esotericsoftware.minlog.Log;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCCppBaseClauseList;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;
import java.util.Iterator;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;

public class OCElementsMover
{
    private boolean myAutoImport;
    
    public OCElementsMover(final boolean myAutoImport) {
        this.myAutoImport = myAutoImport;
    }
    
    public OCElementsMover() {
        this(true);
    }
    
    public OCReferenceElement addBaseProtocol(final OCClassDeclaration ocClassDeclaration, final String s) {
        final OCReferenceElement referenceElementFromText = OCElementFactory.referenceElementFromText(s, (PsiElement)ocClassDeclaration, true);
        Logger log = null;
        boolean b = false;
        Label_0023: {
            try {
                log = OCLog.LOG;
                if (referenceElementFromText != null) {
                    b = true;
                    break Label_0023;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        final OCReferenceElement ocReferenceElement = OCChangeUtil.add((PsiElement)ocClassDeclaration.getProtocolList(), referenceElementFromText);
        try {
            if (this.myAutoImport) {
                OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)ocReferenceElement);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocReferenceElement;
    }
    
    public void removeBaseProtocol(final OCClassDeclaration ocClassDeclaration, final String s) {
        for (final OCReferenceElement ocReferenceElement : ocClassDeclaration.getProtocolList().getProtocols()) {
            try {
                if (s.equals(ocReferenceElement.getName())) {
                    OCChangeUtil.delete((PsiElement)ocReferenceElement);
                    return;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        try {
            assert false : "Protocol " + s + " was not found in " + ocClassDeclaration.getName();
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    public OCCppBaseClause addBaseClass(final OCStruct ocStruct, final OCStructSymbol ocStructSymbol, final OCVisibility ocVisibility, final boolean b) {
        String string = null;
        Label_0031: {
            Label_0018: {
                try {
                    if (ocVisibility == null) {
                        break Label_0018;
                    }
                    final OCVisibility ocVisibility2 = ocVisibility;
                    final OCVisibility ocVisibility3 = OCVisibility.NULL;
                    if (ocVisibility2 != ocVisibility3) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCVisibility ocVisibility2 = ocVisibility;
                    final OCVisibility ocVisibility3 = OCVisibility.NULL;
                    if (ocVisibility2 != ocVisibility3) {
                        string = ocVisibility.toString();
                        break Label_0031;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            string = "";
        }
        final String s = string;
        String s2 = null;
        Label_0057: {
            try {
                if (b) {
                    s2 = ocStructSymbol.getName();
                    break Label_0057;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            s2 = ocStructSymbol.getType().getCanonicalName((PsiElement)ocStruct);
        }
        final OCCppBaseClauseList baseClausesList = ((OCStruct)OCElementFactory.typeElementFromText("struct a: " + s + " " + s2 + "{}", (PsiElement)ocStruct).getFirstChild()).getBaseClausesList();
        try {
            if (baseClausesList == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCCppBaseClause ocCppBaseClause = OCChangeUtil.add((PsiElement)ocStruct.getBaseClausesList(), baseClausesList.getBaseClauses().get(0));
        try {
            if (this.myAutoImport) {
                OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)ocCppBaseClause);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        OCBindUtil.setShortestPossibleName(ocCppBaseClause.getReferenceElement());
        return ocCppBaseClause;
    }
    
    public void removeBaseClass(final OCStruct ocStruct, @NotNull final OCStructSymbol ocStructSymbol, final boolean b) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseSymbol", "com/jetbrains/cidr/lang/refactoring/util/OCElementsMover", "removeBaseClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCQualifiedName qualifiedName = ocStructSymbol.getQualifiedName();
        final OCCppBaseClauseList baseClausesList = ocStruct.getBaseClausesList();
        try {
            if (baseClausesList == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCCppBaseClause ocCppBaseClause : baseClausesList.getBaseClauses()) {
            final OCReferenceElement referenceElement = ocCppBaseClause.getReferenceElement();
            OCSymbol resolveToSymbol = null;
            Label_0133: {
                try {
                    if (referenceElement != null) {
                        resolveToSymbol = referenceElement.resolveToSymbol();
                        break Label_0133;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                resolveToSymbol = null;
            }
            final OCSymbol ocSymbol = resolveToSymbol;
            Label_0166: {
                try {
                    if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                        continue;
                    }
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol2;
                    final OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName.getQualifiedName();
                    final OCQualifiedName ocQualifiedName2 = qualifiedName;
                    final boolean b2 = ocQualifiedName.equals(ocQualifiedName2);
                    if (b2) {
                        break Label_0166;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol2;
                    final OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName.getQualifiedName();
                    final OCQualifiedName ocQualifiedName2 = qualifiedName;
                    final boolean b2 = ocQualifiedName.equals(ocQualifiedName2);
                    if (b2) {
                        OCChangeUtil.delete((PsiElement)ocCppBaseClause);
                        return;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        try {
            if (b) {
                Log.error("Base " + ocStructSymbol.getNameWithKindLowercase() + " was not found in " + ocStruct.getName());
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
    }
    
    public OCReferenceElement setSuperClass(final OCClassDeclaration ocClassDeclaration, final String s) {
        final OCSuperClassRef ocSuperClassRef = (OCSuperClassRef)ocClassDeclaration.getSuperClassRef().replace((PsiElement)((OCInterface)OCElementFactory.topLevelDeclarationFromText("@interface c: " + s + " @end", (PsiElement)ocClassDeclaration)).getSuperClassRef());
        try {
            if (this.myAutoImport) {
                OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)ocSuperClassRef.getReferenceElement());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocSuperClassRef.getReferenceElement();
    }
    
    public OCDeclarator addInstanceVariable(final OCClassDeclaration ocClassDeclaration, final OCInstanceVariableSymbol ocInstanceVariableSymbol, OCDeclaration putToParent) {
        final OCChangeVisibilityIntentionAction ocChangeVisibilityIntentionAction = new OCChangeVisibilityIntentionAction(ocInstanceVariableSymbol, ocInstanceVariableSymbol.getVisibility());
        putToParent = OCChangeUtil.add((PsiElement)ocClassDeclaration.getInstanceVariablesList(), putToParent);
        putToParent = ocChangeVisibilityIntentionAction.putToParent(ocInstanceVariableSymbol, putToParent, (PsiElement)ocClassDeclaration.getInstanceVariablesList());
        try {
            if (this.myAutoImport) {
                OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)putToParent);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return putToParent.getDeclarators().get(0);
    }
    
    public OCDeclaration addClassMemberVariable(final OCStruct ocStruct, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, OCDeclaration putToParent) {
        final OCVisibility visibility = ocSymbolWithQualifiedName.getVisibility();
        if (visibility != null) {
            final OCChangeVisibilityIntentionAction ocChangeVisibilityIntentionAction = new OCChangeVisibilityIntentionAction(ocSymbolWithQualifiedName, visibility);
            putToParent = OCChangeUtil.add((PsiElement)ocStruct, putToParent);
            putToParent = ocChangeVisibilityIntentionAction.putToParent(ocSymbolWithQualifiedName, putToParent, (PsiElement)ocStruct);
        }
        else {
            putToParent = OCChangeUtil.add((PsiElement)ocStruct, putToParent);
        }
        try {
            if (this.myAutoImport) {
                OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)putToParent);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return putToParent;
    }
    
    public void removeSuperClass(final OCClassDeclaration ocClassDeclaration) {
        OCChangeUtil.clear((PsiElement)ocClassDeclaration.getSuperClassRef());
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCElementsMover.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
