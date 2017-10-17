// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.util.Processor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;

public class OCTopLevelTarget extends OCTargetClass<PsiElement, OCSymbol>
{
    private OCFile myImplementationFile;
    private OCFile mySourceFile;
    
    public OCTopLevelTarget(final OCFile mySourceFile, final OCFile myPsi, final Set<VirtualFile> set, final Project project) {
        super(myPsi.getName(), set, null, null, project);
        this.mySourceFile = mySourceFile;
        this.myPsi = (C)myPsi;
        this.mySymbol = (S)OCNamespaceSymbol.createGlobalNamespaceSymbol((PsiFile)myPsi);
        ((AbstractMemberInfoStorage<PsiElement, PsiElement, MemberInfoBase>)(this.myStorage = new OCMemberInfoStorage((PsiElement)myPsi))).getClassMemberInfos((PsiElement)myPsi);
    }
    
    @Override
    public String getDisplayName() {
        return "file \"" + ((OCFile)this.myPsi).getName() + "\"";
    }
    
    @Override
    public void locateTargetClass() {
        this.myStorage = new OCMemberInfoStorage(this.myPsi);
        this.myMembersMap.clear();
        for (final OCMemberInfo ocMemberInfo : this.myStorage.getClassMemberInfos(this.myPsi)) {
            final PsiElement locateDefinition = ocMemberInfo.getSymbol().locateDefinition();
            if (locateDefinition != null) {
                this.myMembersMap.put(ocMemberInfo, locateDefinition);
            }
        }
        if (((OCFile)this.myPsi).isHeader()) {
            final OCFile associatedFile = ((OCFile)this.myPsi).getAssociatedFile();
            if (!this.mySourceFile.equals(associatedFile)) {
                this.myImplementationFile = associatedFile;
                if (this.myImplementationFile == null) {
                    ((OCFile)this.myPsi).getMembersContainer(false).processMembers(null, (com.intellij.util.Processor<OCSymbol>)(associatedSymbol -> {
                        if (!Comparing.equal((Object)associatedSymbol.getContainingFile(), (Object)((OCFile)this.myPsi).getVirtualFile())) {
                            return true;
                        }
                        if (associatedSymbol instanceof OCSymbolWithParent) {
                            associatedSymbol = associatedSymbol.getAssociatedSymbol();
                            if (associatedSymbol != null) {
                                this.myImplementationFile = associatedSymbol.getContainingOCFile();
                            }
                            if (this.myImplementationFile != null && this.myImplementationFile != this.myPsi) {
                                return false;
                            }
                        }
                        return true;
                    }));
                }
            }
        }
        if (this.myImplementationFile == null) {
            this.myImplementationFile = (OCFile)this.myPsi;
        }
    }
    
    @Nullable
    @Override
    protected VirtualFile getTargetFile(final PsiElement psiElement, final OCSymbol ocSymbol) {
        if (((OCFile)psiElement.getContainingFile()).isHeader()) {
            return this.myPsi.getContainingFile().getVirtualFile();
        }
        return this.myImplementationFile.getVirtualFile();
    }
    
    @Nullable
    @Override
    protected OCSymbolDeclarator addMember(final PsiElement psiElement, final OCSymbol ocSymbol, @Nullable final OCVisibility ocVisibility) {
        final PsiElement add = OCChangeUtil.add((PsiElement)(((OCFile)psiElement.getContainingFile()).isHeader() ? this.myPsi : this.myImplementationFile), psiElement);
        if (add instanceof OCSymbolDeclarator) {
            return (OCSymbolDeclarator)add;
        }
        if (add instanceof OCDeclaration) {
            final List<OCDeclarator> declarators = ((OCDeclaration)add).getDeclarators();
            if (!declarators.isEmpty()) {
                return declarators.get(0);
            }
            final OCTypeElement typeElement = ((OCDeclaration)add).getTypeElement();
            if (typeElement != null) {
                for (final PsiElement psiElement2 : typeElement.getChildren()) {
                    if (psiElement2 instanceof OCSymbolDeclarator) {
                        return (OCSymbolDeclarator)psiElement2;
                    }
                }
            }
        }
        return null;
    }
}
