// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.generation.MemberChooserObject;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import javax.swing.Icon;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.generation.ClassMember;
import com.intellij.codeInsight.generation.MemberChooserObjectBase;

public class OCMemberChooserObject extends MemberChooserObjectBase implements ClassMember
{
    private OCSymbol mySymbol;
    private Map<OCSymbol, OCSymbol> myParentsMap;
    
    public OCMemberChooserObject(final OCSymbol mySymbol, final String s, final Icon icon) {
        super(s, icon);
        this.mySymbol = mySymbol;
    }
    
    private OCMemberChooserObject(final OCSymbol ocSymbol, final OCSymbol ocSymbol2, final Map<OCSymbol, OCSymbol> myParentsMap) {
        this(ocSymbol, b(ocSymbol2), ocSymbol2.getIcon());
        this.myParentsMap = myParentsMap;
    }
    
    public OCMemberChooserObject(final OCSymbol ocSymbol, final Icon icon, final Map<OCSymbol, OCSymbol> myParentsMap) {
        this(ocSymbol, b(ocSymbol), icon);
        this.myParentsMap = myParentsMap;
    }
    
    public OCMemberChooserObject(final OCSymbol ocSymbol, final Map<OCSymbol, OCSymbol> map) {
        this(ocSymbol, a(ocSymbol), map);
    }
    
    private static OCSymbol a(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCInstanceVariableSymbol && ((OCInstanceVariableSymbol)ocSymbol).isClang4ImplicitIvar()) {
            final OCPropertySymbol associatedProperty = ((OCInstanceVariableSymbol)ocSymbol).getAssociatedProperty();
            return (associatedProperty != null) ? associatedProperty : ocSymbol;
        }
        return ocSymbol;
    }
    
    private static String b(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCFunctionSymbol) {
            return ((OCFunctionSymbol)ocSymbol).getSignatureWithoutParamNames();
        }
        if (ocSymbol instanceof OCNamespaceSymbol && ((OCNamespaceSymbol)ocSymbol).isGlobalNamespace()) {
            final VirtualFile containingFile = ocSymbol.getContainingFile();
            return (containingFile != null) ? containingFile.getName() : "";
        }
        return ocSymbol.getPresentableName();
    }
    
    public OCSymbol getSymbol() {
        return this.mySymbol;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final OCMemberChooserObject ocMemberChooserObject = (OCMemberChooserObject)o;
        if (this.mySymbol != null) {
            if (this.mySymbol.equals(ocMemberChooserObject.mySymbol)) {
                return true;
            }
        }
        else if (ocMemberChooserObject.mySymbol == null) {
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        return (this.mySymbol != null) ? this.mySymbol.hashCode() : 0;
    }
    
    @Nullable
    public MemberChooserObject getParentNodeDelegate() {
        final OCSymbol ocSymbol = (this.myParentsMap != null) ? this.myParentsMap.get(this.mySymbol) : null;
        return (MemberChooserObject)((ocSymbol != null) ? new OCMemberChooserObject(ocSymbol, this.myParentsMap) : null);
    }
}
