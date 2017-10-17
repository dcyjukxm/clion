// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCSymbolHolderBase;
import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.HashSet;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import org.jetbrains.annotations.Nullable;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;

public class OCMemberInfoStorage extends AbstractMemberInfoStorage<OCSymbolHolderVirtualPsiElement, PsiElement, OCMemberInfo>
{
    private MostlySingularMultiMap<OCSymbolDeclarator, Pair<OCSymbol, OCVisibility>> mySuperClassesMap;
    private LinkedHashSet<OCSymbolDeclarator> mySuperClasses;
    
    public OCMemberInfoStorage(@Nullable final PsiElement psiElement) {
        super(psiElement, (MemberInfoBase.Filter<PsiElement>)new MemberInfoBase.EmptyFilter());
    }
    
    @Override
    protected boolean isInheritor(final PsiElement psiElement, final PsiElement psiElement2) {
        return ((AbstractMemberInfoStorage<T, PsiElement, M>)this).getSubclasses(psiElement).contains(psiElement2);
    }
    
    @Override
    protected void buildSubClassesMap(final PsiElement psiElement) {
        if (psiElement instanceof OCSymbolDeclarator) {
            this.a((OCSymbolDeclarator)psiElement, new HashSet<OCSymbolDeclarator>());
        }
    }
    
    private void a(final OCSymbolDeclarator ocSymbolDeclarator, final HashSet<OCSymbolDeclarator> set) {
        if (set.contains(ocSymbolDeclarator)) {
            return;
        }
        set.add(ocSymbolDeclarator);
        final OCClassSymbol symbol = ocSymbolDeclarator.getSymbol();
        if (symbol == null) {
            return;
        }
        if (symbol instanceof OCClassSymbol) {
            final OCType resolve = symbol.getSuperType().resolve(ocSymbolDeclarator.getContainingFile(), true);
            if (resolve instanceof OCObjectType) {
                this.a(ocSymbolDeclarator, set, ((OCObjectType)resolve).getClassSymbol(), OCVisibility.PUBLIC);
            }
            for (final String s : symbol.getProtocolNames()) {
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                OCResolveUtil.processGlobalSymbols(s, (PsiElement)ocSymbolDeclarator, (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)findFirstProcessor, OCProtocolSymbol.class));
                this.a(ocSymbolDeclarator, set, (OCSymbol)findFirstProcessor.getFoundValue(), OCVisibility.PUBLIC);
            }
        }
        else if (symbol instanceof OCStructSymbol) {
            ((OCStructSymbol)symbol).processBaseClasses(new OCResolveContext((PsiElement)ocSymbolDeclarator.getContainingFile()), (ocSymbol, ocVisibility) -> {
                this.a(ocSymbolDeclarator, set, ocSymbol, ocVisibility);
                return true;
            });
        }
    }
    
    private void a(final OCSymbolDeclarator ocSymbolDeclarator, final HashSet<OCSymbolDeclarator> set, final OCSymbol ocSymbol, final OCVisibility ocVisibility) {
        if (this.mySuperClassesMap == null) {
            this.mySuperClassesMap = (MostlySingularMultiMap<OCSymbolDeclarator, Pair<OCSymbol, OCVisibility>>)new MostlySingularMultiMap();
            this.mySuperClasses = new LinkedHashSet<OCSymbolDeclarator>();
        }
        final OCSymbolDeclarator ocSymbolDeclarator2 = (ocSymbol != null) ? ocSymbol.locateDefinition() : null;
        if (ocSymbolDeclarator2 != null) {
            if (OCSearchScope.isInProjectSources(ocSymbol)) {
                this.a(ocSymbolDeclarator2, set);
                ((AbstractMemberInfoStorage<T, PsiElement, M>)this).getSubclasses((PsiElement)ocSymbolDeclarator2).add((PsiElement)ocSymbolDeclarator);
                this.mySuperClasses.add(ocSymbolDeclarator2);
            }
            this.mySuperClassesMap.add((Object)ocSymbolDeclarator, (Object)Pair.create((Object)ocSymbol, (Object)ocVisibility));
        }
    }
    
    @Override
    protected void extractClassMembers(final PsiElement psiElement, final ArrayList<OCMemberInfo> list) {
        final OCMembersContainer ocMembersContainer = (psiElement instanceof OCFile) ? ((OCFile)psiElement).getMembersContainer(false) : ((OCSymbolDeclarator)psiElement).getSymbol();
        if (ocMembersContainer == null) {
            return;
        }
        final Processor processor = ocSymbol -> {
            if (virtualFile != null && !virtualFile.equals(ocSymbol.getContainingFile())) {
                return true;
            }
            if (ocSymbol instanceof OCSynthesizeSymbol) {
                return true;
            }
            if (ocSymbol instanceof OCInstanceVariableSymbol && ((OCInstanceVariableSymbol)ocSymbol).getGeneratedFromProperty() != null) {
                return true;
            }
            if (ocSymbol instanceof OCMethodSymbol && ((OCMethodSymbol)ocSymbol).getGeneratedFromProperty() != null) {
                return true;
            }
            if (ocSymbol instanceof OCMethodSymbol && ((OCMethodSymbol)ocSymbol).getParent() instanceof OCInterfaceSymbol) {
                return true;
            }
            if (ocSymbol instanceof OCClassSymbol && ocSymbol.isPredeclaration()) {
                return true;
            }
            if (ocSymbol instanceof OCDeclaratorSymbol && ocSymbol.getKind() == OCSymbolKind.ENUM_CONST) {
                return true;
            }
            if (psiElement instanceof OCFile || psiElement instanceof OCCppNamespace) {
                Object o;
                if (ocSymbol instanceof OCInterfaceSymbol) {
                    o = ((OCInterfaceSymbol)ocSymbol).getImplementation(((OCInterfaceSymbol)ocSymbol).getCategoryName());
                }
                else if (ocSymbol instanceof OCClassSymbol) {
                    o = ocSymbol;
                }
                else {
                    o = ocSymbol.getDefinitionSymbol();
                }
                if (o != null && o != ocSymbol && virtualFile != null && virtualFile.equals(((ProjectAndVirtualFileOwner)o).getContainingFile())) {
                    return true;
                }
            }
            if (ocSymbol instanceof OCSymbolWithParent) {
                final OCSymbol<OCClassDeclarationBase> ocSymbol2 = (ocSymbol instanceof OCFunctionSymbol) ? ocSymbol.getDefinitionSymbol() : ocSymbol;
                list.add(new OCMemberInfo(ocSymbol, (ocSymbol2 != null) ? ocSymbol2 : ocSymbol, false, ocMembersContainer));
            }
            else {
                list.add(new OCMemberInfo(ocSymbol, ocMembersContainer));
            }
            return true;
        };
        if (ocMembersContainer instanceof OCClassSymbol) {
            ((OCClassSymbol)ocMembersContainer).processCategories((Processor<? super OCClassSymbol>)(ocClassSymbol -> {
                if (ocClassSymbol.isSameCategory((OCSymbol)ocMembersContainer)) {
                    ocClassSymbol.processMembers(OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                }
                return true;
            }), false, null);
        }
        else if (ocMembersContainer instanceof OCStructSymbol) {
            ((OCStructSymbol)ocMembersContainer).processMembers(null, (Processor<OCSymbol>)processor);
        }
        else if (psiElement instanceof OCCppNamespace) {
            ((OCStructSymbol)ocMembersContainer).processMembers(null, (Processor<OCSymbol>)processor);
        }
        else if (psiElement instanceof OCFile) {
            ((OCStructSymbol)ocMembersContainer).processMembers(null, (com.intellij.util.Processor<OCSymbol>)processor);
        }
        final ArrayList<OCMemberInfo> list2 = new ArrayList<OCMemberInfo>();
        if (this.mySuperClassesMap != null && psiElement instanceof OCSymbolDeclarator) {
            for (final Pair pair : this.mySuperClassesMap.get((Object)psiElement)) {
                final OCSymbol ocSymbol = (OCSymbol)pair.getFirst();
                if (ocSymbol instanceof OCProtocolSymbol) {
                    list2.add(new OCMemberInfo(ocSymbol, ocMembersContainer));
                }
                else {
                    if (!(ocSymbol instanceof OCStructSymbol)) {
                        continue;
                    }
                    list2.add(new OCMemberInfo((OCStructSymbol)ocSymbol, (OCVisibility)pair.getSecond(), ocMembersContainer));
                }
            }
        }
        Collections.sort((List<Object>)list, (ocMemberInfo, ocMemberInfo2) -> OCSymbolOffsetUtil.compare(ocMemberInfo.getSymbol().getComplexOffset(), ocMemberInfo2.getSymbol().getComplexOffset()));
        list.addAll(0, (Collection<?>)list2);
    }
    
    public boolean memberConflict(final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement, final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement2) {
        final OCFunctionSymbol symbol = ((OCSymbolHolderBase<OCFunctionSymbol>)ocSymbolHolderVirtualPsiElement).getSymbol();
        final OCFunctionSymbol symbol2 = ((OCSymbolHolderBase<OCFunctionSymbol>)ocSymbolHolderVirtualPsiElement2).getSymbol();
        if (symbol instanceof OCFunctionSymbol && symbol2 instanceof OCFunctionSymbol) {
            return a(symbol).equals(a(symbol2));
        }
        if (symbol instanceof OCMethodSymbol && symbol2 instanceof OCMethodSymbol) {
            return symbol.getSignature().equals(symbol2.getSignature());
        }
        if (symbol instanceof OCSymbolWithParent && !(symbol instanceof OCStructSymbol) && symbol2 instanceof OCSymbolWithParent && !(symbol2 instanceof OCStructSymbol)) {
            return symbol.getName().equals(symbol2.getName());
        }
        return symbol.equals(symbol2);
    }
    
    private static String a(final OCFunctionSymbol ocFunctionSymbol) {
        if (ocFunctionSymbol.isCppConstructor()) {
            return ocFunctionSymbol.getParamsSignatureWithoutNames();
        }
        if (ocFunctionSymbol.isCppDestructor()) {
            return "~";
        }
        return ocFunctionSymbol.getName() + ocFunctionSymbol.getParamsSignatureWithoutNames();
    }
    
    public List<OCSymbolDeclarator> getSuperClasses() {
        return (List<OCSymbolDeclarator>)((this.mySuperClasses != null) ? new ArrayList<OCSymbolDeclarator>(this.mySuperClasses) : Collections.emptyList());
    }
}
