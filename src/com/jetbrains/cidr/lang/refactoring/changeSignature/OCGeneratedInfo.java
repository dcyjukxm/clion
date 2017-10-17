// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiFile;
import java.util.Collections;
import com.intellij.openapi.util.Key;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;

public class OCGeneratedInfo
{
    private PsiElement myMethodReference;
    private OCSymbol myMethodParent;
    private OCTargetSymbolPanel.TargetSymbolsMode myTargetSymbolsMode;
    private boolean myAllowChangeCategories;
    private List<? extends OCClassSymbol> myAuxParents;
    private List<PsiElement> myMethodStatements;
    private String myCallString;
    private List<PsiElement> myBeforeCallStatements;
    private List<PsiElement> myAfterCallStatements;
    private List<Pair<Integer, Runnable>> myCallbacks;
    private boolean myStatic;
    private boolean mySelectMethod;
    
    public OCGeneratedInfo(@Nullable final Project project) {
        this.myCallbacks = new ArrayList<Pair<Integer, Runnable>>();
        if (project != null) {
            this.myTargetSymbolsMode = (OCTargetSymbolPanel.TargetSymbolsMode)project.getUserData((Key)OCTargetSymbolPanel.VISIBILITY_KEY);
        }
        if (this.myTargetSymbolsMode == null) {
            this.myTargetSymbolsMode = OCTargetSymbolPanel.TargetSymbolsMode.INTERFACE;
        }
    }
    
    public List<PsiElement> getBeforeCallStatements() {
        return this.myBeforeCallStatements;
    }
    
    public void setBeforeCallStatements(final List<PsiElement> myBeforeCallStatements) {
        this.myBeforeCallStatements = myBeforeCallStatements;
    }
    
    public List<PsiElement> getAfterCallStatements() {
        return this.myAfterCallStatements;
    }
    
    public void setAfterCallStatements(final List<PsiElement> myAfterCallStatements) {
        this.myAfterCallStatements = myAfterCallStatements;
    }
    
    public String getCallString() {
        return this.myCallString;
    }
    
    public void setCallString(final String myCallString) {
        this.myCallString = myCallString;
    }
    
    public OCSymbol getMethodParent() {
        return this.myMethodParent;
    }
    
    public boolean isAllowChangeCategories() {
        return this.myAllowChangeCategories;
    }
    
    public List<? extends OCClassSymbol> getAuxParents() {
        return this.myAuxParents;
    }
    
    public void setMethodParent(final OCSymbol ocSymbol) {
        this.setMethodParent(ocSymbol, false, Collections.emptyList());
    }
    
    public void setMethodParent(final OCSymbol myMethodParent, final boolean myAllowChangeCategories, final List<? extends OCClassSymbol> myAuxParents) {
        this.myMethodParent = myMethodParent;
        this.myAllowChangeCategories = myAllowChangeCategories;
        this.myAuxParents = myAuxParents;
    }
    
    public OCTargetSymbolPanel.TargetSymbolsMode getTargetSymbolsMode() {
        return this.myTargetSymbolsMode;
    }
    
    public void setTargetSymbolsMode(final OCTargetSymbolPanel.TargetSymbolsMode myTargetSymbolsMode) {
        this.myTargetSymbolsMode = myTargetSymbolsMode;
    }
    
    public PsiElement getMethodReference() {
        return this.myMethodReference;
    }
    
    public void setMethodReference(final PsiElement myMethodReference) {
        this.myMethodReference = myMethodReference;
    }
    
    public List<PsiElement> getMethodStatements() {
        return this.myMethodStatements;
    }
    
    public void setMethodStatements(final List<PsiElement> myMethodStatements) {
        this.myMethodStatements = myMethodStatements;
    }
    
    public void runOnSuccess(final Runnable runnable, final int n) {
        this.myCallbacks.add((Pair<Integer, Runnable>)new Pair((Object)n, (Object)runnable));
    }
    
    public List<Pair<Integer, Runnable>> getCallbacks() {
        return this.myCallbacks;
    }
    
    public void runOnSuccess(final Runnable runnable) {
        this.runOnSuccess(runnable, -1000);
    }
    
    public boolean isStatic() {
        return this.myStatic;
    }
    
    public void setStatic(final boolean myStatic) {
        this.myStatic = myStatic;
    }
    
    public boolean isSelectMethod() {
        return this.mySelectMethod;
    }
    
    public void setSelectMethod(final boolean mySelectMethod) {
        this.mySelectMethod = mySelectMethod;
    }
    
    public List<PsiFile> getFilesToWrite() {
        final ArrayList<PsiFile> list = new ArrayList<PsiFile>();
        if (this.myMethodParent == null) {
            return list;
        }
        if (this.myMethodParent instanceof OCClassSymbol) {
            final OCImplementationSymbol implementation = ((OCClassSymbol)this.myMethodParent).getImplementation();
            if (implementation != null) {
                list.add((PsiFile)implementation.getContainingOCFile());
            }
            if (this.myTargetSymbolsMode == OCTargetSymbolPanel.TargetSymbolsMode.INTERFACE) {
                final OCClassSymbol interfaceOrProtocol = ((OCClassSymbol)this.myMethodParent).getInterfaceOrProtocol();
                if (interfaceOrProtocol != null) {
                    ContainerUtil.addIfNotNull((Collection)list, (Object)interfaceOrProtocol.getContainingOCFile());
                }
            }
        }
        else {
            list.add((PsiFile)this.myMethodParent.getContainingOCFile());
        }
        return list;
    }
}
