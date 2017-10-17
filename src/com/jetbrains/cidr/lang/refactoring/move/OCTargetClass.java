// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.util.Iterator;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.util.containers.hash.HashMap;
import java.util.HashSet;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import com.jetbrains.cidr.lang.refactoring.util.OCElementsMover;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;

public abstract class OCTargetClass<C extends PsiElement, S extends OCSymbol>
{
    protected Project myProject;
    protected String myName;
    protected C myPsi;
    protected S mySymbol;
    protected S mySourceSymbol;
    protected Set<VirtualFile> myFiles;
    protected OCElementsMover myMover;
    protected OCMemberInfoStorage myStorage;
    protected Map<OCMemberInfo, PsiElement> myMembersMap;
    
    public OCTargetClass(final String myName, final Set<VirtualFile> myFiles, @Nullable final S mySymbol, final S mySourceSymbol, final Project myProject) {
        this.myFiles = new HashSet<VirtualFile>();
        this.myMover = new OCElementsMover(false);
        this.myMembersMap = (Map<OCMemberInfo, PsiElement>)new HashMap();
        this.myProject = myProject;
        this.myName = myName;
        this.mySymbol = mySymbol;
        this.myFiles = myFiles;
        this.mySourceSymbol = mySourceSymbol;
        final PsiElement myPsi = (mySymbol != null) ? mySymbol.locateDefinition() : null;
        if (myPsi instanceof OCSymbolDeclarator) {
            this.myPsi = (C)myPsi;
            (this.myStorage = new OCMemberInfoStorage(this.myPsi)).getClassMemberInfos(this.myPsi);
        }
    }
    
    public C getPsi() {
        return this.myPsi;
    }
    
    public S getSymbol() {
        return this.mySymbol;
    }
    
    public String getDisplayName() {
        return this.mySymbol.getNameWithKindLowercase();
    }
    
    public Set<VirtualFile> getFiles() {
        return this.myFiles;
    }
    
    public void setName(final String myName) {
        this.myName = myName;
    }
    
    @Nullable
    public OCMemberInfoStorage getMemberStorage() {
        return this.myStorage;
    }
    
    public PsiElement getMemberElement(final OCMemberInfo ocMemberInfo) {
        return this.myMembersMap.get(ocMemberInfo);
    }
    
    public void locateTargetClass() {
        final Iterator<VirtualFile> iterator = this.myFiles.iterator();
        while (iterator.hasNext()) {
            final OCFile ocFile = (OCFile)PsiManager.getInstance(this.myProject).findFile((VirtualFile)iterator.next());
            if (ocFile != null && ocFile.isHeader()) {
                final OCSymbolDeclarator class1 = ocFile.findClass(this.myName);
                if (class1 != null) {
                    this.myPsi = (C)class1;
                    break;
                }
                break;
            }
        }
        assert this.myPsi != null;
        this.mySymbol = ((OCSymbolDeclarator)this.myPsi).getSymbol();
        assert this.mySymbol != null;
        this.myStorage = new OCMemberInfoStorage(this.myPsi);
        this.myMembersMap.clear();
        for (final OCMemberInfo ocMemberInfo : this.myStorage.getClassMemberInfos(this.myPsi)) {
            final PsiElement locateDefinition = ocMemberInfo.getSymbol().locateDefinition();
            if (locateDefinition != null) {
                this.myMembersMap.put(ocMemberInfo, locateDefinition);
            }
        }
    }
    
    @Nullable
    protected abstract OCSymbolDeclarator addMember(@Nullable final PsiElement p0, final OCSymbol p1, @Nullable final OCVisibility p2);
    
    @Nullable
    protected abstract VirtualFile getTargetFile(final PsiElement p0, final OCSymbol p1);
}
