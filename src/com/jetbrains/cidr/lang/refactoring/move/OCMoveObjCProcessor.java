// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.ArrayList;
import java.util.Iterator;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;

public abstract class OCMoveObjCProcessor extends OCMoveProcessor<OCClassDeclaration, OCClassSymbol, OCObjcTargetClass>
{
    protected OCClassDeclaration myMainInterface;
    
    public OCMoveObjCProcessor(final OCClassDeclaration ocClassDeclaration, final List<OCMemberInfo> list, @Nullable final String s, final Collection<OCClassSymbol> collection) {
        super((PsiElement)ocClassDeclaration, list, s, collection);
        this.myMainInterface = OCElementUtil.resolveClassDeclaration(((OCClassSymbol)this.mySourceClassSymbol).getMainInterface());
        this.myMainInterface = (OCClassDeclaration)((this.myMainInterface != null) ? this.myMainInterface : this.mySourceClass);
    }
    
    @Override
    protected OCObjcTargetClass createTargetClass(final OCFile ocFile, final OCFile ocFile2, final String s, final Set<VirtualFile> set, final OCClassSymbol ocClassSymbol, final OCClassSymbol ocClassSymbol2, final Project project) {
        return new OCObjcTargetClass(s, set, ocClassSymbol, ocClassSymbol2, project);
    }
    
    @Override
    protected void removeMember(final PsiElement psiElement, final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCProtocolSymbol) {
            this.myMover.removeBaseProtocol((OCClassDeclaration)this.mySourceClass, ocSymbol.getName());
        }
        else {
            OCChangeUtil.delete(psiElement);
        }
    }
    
    @Override
    protected void addMissingImports(final MultiMap<OCObjcTargetClass, PsiElement> multiMap) {
        super.addMissingImports(multiMap);
        for (final OCObjcTargetClass ocObjcTargetClass : this.myTargetClasses) {
            OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)((OCTargetClass<OCClassDeclaration, S>)ocObjcTargetClass).getPsi().getSuperClassRef());
            OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)((OCTargetClass<OCClassDeclaration, S>)ocObjcTargetClass).getPsi().getProtocolList());
        }
    }
    
    @Override
    protected List<Member> getAssociatedSymbols(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        final ArrayList<Member> list = new ArrayList<Member>();
        OCMoveProcessor.addAssociatedMember(list, ocSymbol, ocMemberInfo);
        final OCSymbol<PsiElement> associatedSymbol = ocSymbol.getAssociatedSymbol();
        if (!(associatedSymbol instanceof OCInstanceVariableSymbol) || ((OCInstanceVariableSymbol)associatedSymbol).getGeneratedFromProperty() == null) {
            OCMoveProcessor.addAssociatedMember(list, associatedSymbol, ocMemberInfo);
        }
        if (ocSymbol instanceof OCPropertySymbol) {
            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
            ((OCPropertySymbol)ocSymbol).processSynthesizes((Processor<? super OCSynthesizeSymbol>)collectProcessor);
            final Iterator iterator = collectProcessor.getResults().iterator();
            while (iterator.hasNext()) {
                OCMoveProcessor.addAssociatedMember(list, iterator.next(), ocMemberInfo);
            }
        }
        return list;
    }
}
