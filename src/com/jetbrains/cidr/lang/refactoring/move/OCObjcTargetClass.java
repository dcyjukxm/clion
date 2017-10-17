// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;

public class OCObjcTargetClass extends OCTargetClass<OCClassDeclaration, OCClassSymbol>
{
    @Nullable
    private OCClassDeclaration myImplementation;
    private OCClassDeclaration myPrivateCategory;
    
    public OCObjcTargetClass(final String s, final Set<VirtualFile> set, final OCClassSymbol ocClassSymbol, final OCClassSymbol ocClassSymbol2, final Project project) {
        super(s, set, ocClassSymbol, ocClassSymbol2, project);
    }
    
    @Override
    public void locateTargetClass() {
        super.locateTargetClass();
        final OCSymbol<OCClassDeclarationBase> associatedSymbol = ((OCClassSymbol)this.mySymbol).getAssociatedSymbol();
        this.myImplementation = ((associatedSymbol != null) ? associatedSymbol.locateDefinition() : null);
    }
    
    @Nullable
    @Override
    protected VirtualFile getTargetFile(final PsiElement psiElement, final OCSymbol ocSymbol) {
        if (!(ocSymbol instanceof OCMemberSymbol)) {
            return null;
        }
        if ("".equals(((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent().getCategoryName())) {
            return (this.myImplementation != null) ? this.myImplementation.getContainingFile().getVirtualFile() : null;
        }
        if (((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent().getKind() == ((OCClassSymbol)this.mySymbol).getKind()) {
            return ((OCClassDeclaration)this.myPsi).getContainingFile().getVirtualFile();
        }
        return (this.myImplementation != null) ? this.myImplementation.getContainingFile().getVirtualFile() : null;
    }
    
    @Nullable
    public OCSymbolDeclarator addMember(PsiElement parentOfType, final OCSymbol ocSymbol, final OCVisibility ocVisibility) {
        OCSymbolDeclarator addInstanceVariable = null;
        if (ocSymbol instanceof OCMemberSymbol) {
            OCClassDeclaration ocClassDeclaration;
            if ("".equals(((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent().getCategoryName())) {
                if (this.myPrivateCategory == null && this.myImplementation != null) {
                    this.myPrivateCategory = OCElementFactory.interfaceByName(((OCClassSymbol)this.mySymbol).getName() + "()", (PsiElement)this.myImplementation);
                    this.myPrivateCategory = OCChangeUtil.addBefore(this.myImplementation.getParent(), this.myPrivateCategory, (PsiElement)this.myImplementation);
                }
                ocClassDeclaration = this.myPrivateCategory;
            }
            else if (((OCInstanceVariableSymbol)ocSymbol).getParent() instanceof OCImplementationSymbol) {
                ocClassDeclaration = this.myImplementation;
            }
            else {
                ocClassDeclaration = (OCClassDeclaration)this.myPsi;
            }
            if (ocClassDeclaration == null) {
                return null;
            }
            if (ocSymbol instanceof OCInstanceVariableSymbol) {
                addInstanceVariable = this.myMover.addInstanceVariable(ocClassDeclaration, (OCInstanceVariableSymbol)ocSymbol, (OCDeclaration)parentOfType);
            }
            else {
                if (ocSymbol instanceof OCPropertySymbol) {
                    parentOfType = PsiTreeUtil.getParentOfType(parentOfType, (Class)OCProperty.class);
                }
                final PsiElement add = OCChangeUtil.add((PsiElement)ocClassDeclaration, parentOfType);
                if (add instanceof OCProperty) {
                    addInstanceVariable = ((OCProperty)add).getDeclaration().getDeclarators().get(0);
                }
                else if (add instanceof OCMethod) {
                    addInstanceVariable = (OCSymbolDeclarator)add;
                }
                else if (add instanceof OCSynthesizePropertiesList) {
                    addInstanceVariable = ((OCSynthesizePropertiesList)add).getProperties().get(0);
                }
            }
        }
        else if (ocSymbol instanceof OCProtocolSymbol) {
            this.myMover.addBaseProtocol((OCClassDeclaration)this.myPsi, ocSymbol.getName());
        }
        return addInstanceVariable;
    }
}
