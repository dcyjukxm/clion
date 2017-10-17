// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import java.util.List;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;

public class OCCppTargetClass extends OCTargetClass<OCStruct, OCStructSymbol>
{
    private OCFile myImplementationFile;
    
    public OCCppTargetClass(final String s, final Set<VirtualFile> set, final OCStructSymbol ocStructSymbol, final OCStructSymbol ocStructSymbol2, final Project project) {
        super(s, set, ocStructSymbol, ocStructSymbol2, project);
    }
    
    @Override
    public void locateTargetClass() {
        super.locateTargetClass();
        final OCFile containingOCFile = ((OCStruct)this.myPsi).getContainingOCFile();
        if (containingOCFile.isHeader()) {
            this.myImplementationFile = containingOCFile.getAssociatedFile();
            if (this.myImplementationFile == null) {
                ((OCStructSymbol)this.mySymbol).processMembers(null, (Processor<OCSymbol>)(associatedSymbol -> {
                    if (associatedSymbol instanceof OCSymbolWithParent) {
                        associatedSymbol = associatedSymbol.getAssociatedSymbol();
                        if (associatedSymbol != null) {
                            this.myImplementationFile = associatedSymbol.getContainingOCFile();
                        }
                        if (this.myImplementationFile != null && this.myImplementationFile != containingOCFile) {
                            return false;
                        }
                    }
                    return true;
                }));
            }
        }
        if (this.myImplementationFile == null) {
            this.myImplementationFile = containingOCFile;
        }
    }
    
    @Nullable
    public VirtualFile getTargetFile(final PsiElement psiElement, final OCSymbol ocSymbol) {
        if (((OCFile)psiElement.getContainingFile()).isHeader()) {
            return ((OCStruct)this.myPsi).getContainingFile().getVirtualFile();
        }
        return this.myImplementationFile.getVirtualFile();
    }
    
    public OCSymbolDeclarator addMember(final PsiElement psiElement, final OCSymbol ocSymbol, final OCVisibility ocVisibility) {
        if (ocSymbol instanceof OCStructSymbol && ((OCStructSymbol)ocSymbol).getParent() != this.mySourceSymbol) {
            this.myMover.addBaseClass((OCStruct)this.myPsi, (OCStructSymbol)ocSymbol, ocVisibility, false);
            return null;
        }
        OCDeclaration addClassMemberVariable;
        if (ocSymbol instanceof OCSymbolWithParent && !(((OCStructSymbol)ocSymbol).getParent() instanceof OCStructSymbol)) {
            addClassMemberVariable = OCChangeUtil.add((PsiElement)this.myImplementationFile, (OCDeclaration)psiElement);
        }
        else {
            addClassMemberVariable = this.myMover.addClassMemberVariable((OCStruct)this.myPsi, (OCSymbolWithQualifiedName)ocSymbol, (OCDeclaration)psiElement);
        }
        if (ocSymbol.getKind().isConstructorOrDestructor()) {
            addClassMemberVariable.getDeclarators().get(0).setName(this.myName);
            final PsiElement nameIdentifier = addClassMemberVariable.getDeclarators().get(0).getNameIdentifier();
            if (nameIdentifier != null) {
                CodeEditUtil.markToReformatBefore(nameIdentifier.getNode(), true);
            }
        }
        final List<OCDeclarator> declarators = addClassMemberVariable.getDeclarators();
        if (declarators.isEmpty()) {
            final OCTypeElement typeElement = addClassMemberVariable.getTypeElement();
            if (typeElement != null) {
                for (final PsiElement psiElement2 : typeElement.getChildren()) {
                    if (psiElement2 instanceof OCSymbolDeclarator) {
                        return (OCSymbolDeclarator)psiElement2;
                    }
                }
            }
            return null;
        }
        return declarators.get(0);
    }
}
