// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.OCIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.HashMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfoStorage;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;

public class OCDeclareActionContext extends OCObjCActionContext<OCMemberSymbol>
{
    private final OCClassDeclaration myClassDeclaration;
    protected final OCInterfaceSymbol myPrivateCategory;
    private Target myTarget;
    
    public OCDeclareActionContext(final OCClassSymbol ocClassSymbol, final OCClassDeclaration myClassDeclaration, @Nullable final OCObjectType ocObjectType) {
        super(ocClassSymbol, (PsiElement)myClassDeclaration, ocObjectType);
        this.myClassDeclaration = myClassDeclaration;
        OCInterfaceSymbol myPrivateCategory = null;
        Label_0088: {
            Label_0057: {
                if (this.myInterfaceSymbol != null) {
                    try {
                        if ("".equals(this.myInterfaceSymbol.getCategoryName())) {
                            final OCClassSymbol myInterfaceSymbol = this.myInterfaceSymbol.getMainInterface();
                            break Label_0057;
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw b(ex);
                    }
                }
                final OCClassSymbol myInterfaceSymbol = this.myInterfaceSymbol;
                try {
                    this.myInterfaceSymbol = myInterfaceSymbol;
                    if (ocObjectType != null) {
                        myPrivateCategory = (OCInterfaceSymbol)ContainerUtil.find((Iterable)ocObjectType.getCategoryInterfaces(), ocInterfaceSymbol -> "".equals(ocInterfaceSymbol.getCategoryName()));
                        break Label_0088;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            myPrivateCategory = null;
        }
        this.myPrivateCategory = myPrivateCategory;
    }
    
    @NotNull
    @Override
    public Collection<OCMemberSymbol> getMemberCandidates() {
        final OCMemberInfoStorage ocMemberInfoStorage = new OCMemberInfoStorage((PsiElement)this.myClassDeclaration);
        List mapNotNull;
        try {
            mapNotNull = ContainerUtil.mapNotNull((Collection)((AbstractMemberInfoStorage<PsiElement, PsiElement, MemberInfoBase>)ocMemberInfoStorage).getClassMemberInfos(this.getContext()), ocMemberInfo -> {
                try {
                    if (ocMemberInfo.getSymbol() instanceof OCMemberSymbol) {
                        return (OCMemberSymbol)ocMemberInfo.getSymbol();
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                return null;
            });
            if (mapNotNull == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return (Collection<OCMemberSymbol>)mapNotNull;
    }
    
    @Override
    public Map<OCSymbol, OCSymbol> createParentsMap(final Collection<OCMemberSymbol> collection) {
        final HashMap<OCMemberSymbol, OCSymbol> hashMap = new HashMap<OCMemberSymbol, OCSymbol>();
        for (final OCMemberSymbol ocMemberSymbol : collection) {
            if (ocMemberSymbol instanceof OCMethodSymbol) {
                final OCMemberSymbol associatedSymbol = ocMemberSymbol.getAssociatedSymbol();
                HashMap<OCMemberSymbol, OCSymbol> hashMap2 = null;
                OCMemberSymbol ocMemberSymbol2 = null;
                OCSymbol ocSymbol = null;
                Label_0098: {
                    Label_0077: {
                        try {
                            hashMap2 = hashMap;
                            ocMemberSymbol2 = ocMemberSymbol;
                            if (associatedSymbol == null) {
                                break Label_0077;
                            }
                            final OCMemberSymbol ocMemberSymbol3 = associatedSymbol;
                            final boolean b = ocMemberSymbol3.isSynthetic();
                            if (!b) {
                                break Label_0077;
                            }
                            break Label_0077;
                        }
                        catch (IllegalStateException ex) {
                            throw b(ex);
                        }
                        try {
                            final OCMemberSymbol ocMemberSymbol3 = associatedSymbol;
                            final boolean b = ocMemberSymbol3.isSynthetic();
                            if (!b) {
                                ocSymbol = ((OCSymbolWithParent<T, OCSymbol>)associatedSymbol).getParent();
                                break Label_0098;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw b(ex2);
                        }
                    }
                    ocSymbol = ((OCSymbolWithParent<T, OCSymbol>)ocMemberSymbol).getParent();
                }
                hashMap2.put(ocMemberSymbol2, ocSymbol);
            }
            else {
                hashMap.put(ocMemberSymbol, ((OCSymbolWithParent<T, OCSymbol>)ocMemberSymbol).getParent());
            }
        }
        return (Map<OCSymbol, OCSymbol>)hashMap;
    }
    
    public OCInterfaceSymbol getPrivateCategory() {
        return this.myPrivateCategory;
    }
    
    public Target getTarget() {
        return this.myTarget;
    }
    
    public Target[] getAvailableTargets() {
        try {
            if (this.myImplementationSymbol.getCategoryName() == null) {
                return new Target[] { Target.INTERFACE, Target.PRIVATE_CATEGORY, Target.IMPLEMENTATION };
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return new Target[] { Target.INTERFACE, Target.IMPLEMENTATION };
    }
    
    public OCClassSymbol getTargetSymbol() {
        try {
            switch (this.myTarget) {
                case INTERFACE: {
                    return this.myInterfaceSymbol;
                }
                case PRIVATE_CATEGORY: {
                    break;
                }
                case IMPLEMENTATION: {
                    return this.myImplementationSymbol;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return this.myPrivateCategory;
    }
    
    public void setTarget(final Target myTarget) {
        this.myTarget = myTarget;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
    
    public enum Target
    {
        INTERFACE("Interface", OCIcons.CodeAssistantClass), 
        PRIVATE_CATEGORY("Private Category", OCIcons.CodeAssistantClassExtension), 
        IMPLEMENTATION("Implementation (don't declare)", OCIcons.CodeAssistantClass);
        
        private final String myName;
        private Icon myIcon;
        
        private Target(final String myName, final Icon myIcon) {
            this.myName = myName;
            this.myIcon = myIcon;
        }
        
        public String getName() {
            return this.myName;
        }
        
        public Icon getIcon() {
            return this.myIcon;
        }
    }
}
