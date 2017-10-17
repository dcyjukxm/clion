// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import java.util.HashMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Map;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Collection;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public abstract class OCCppActionContext<P extends OCMembersContainer, M extends OCSymbolWithQualifiedName<?>> extends OCActionContext<P, M>
{
    @Nullable
    private Editor myEditor;
    @Nullable
    private PsiFile myEditorFile;
    private boolean myReplaceExisting;
    private Collection<OCFunctionSymbol> myExisting;
    
    public OCCppActionContext(final P p2, final PsiElement psiElement) {
        super(p2, psiElement);
        this.myReplaceExisting = false;
    }
    
    @Override
    public Map<OCSymbol, OCSymbol> createParentsMap(final Collection<M> collection) {
        final HashMap<OCSymbolWithQualifiedName<?>, OCNamespaceSymbol> hashMap = new HashMap<OCSymbolWithQualifiedName<?>, OCNamespaceSymbol>();
        OCNamespaceSymbol globalNamespaceSymbol = null;
        Label_0041: {
            try {
                if (this.getParent() instanceof OCNamespaceLikeSymbol.FileSymbolsProvider) {
                    globalNamespaceSymbol = OCNamespaceSymbol.createGlobalNamespaceSymbol((PsiFile)this.getParent().getFile());
                    break Label_0041;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            globalNamespaceSymbol = null;
        }
        final OCNamespaceSymbol ocNamespaceSymbol = globalNamespaceSymbol;
        final Iterator<M> iterator = collection.iterator();
        while (iterator.hasNext()) {
            while (true) {
                OCSymbolWithQualifiedName<?> parent = iterator.next();
                Label_0116: {
                    while (true) {
                        try {
                            if (parent.getParent() == null) {
                                break Label_0116;
                            }
                            if (parent == null) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        hashMap.put(parent, parent.getParent());
                        parent = parent.getParent();
                        continue;
                    }
                    try {
                        if (ocNamespaceSymbol != null) {
                            hashMap.put(parent, ocNamespaceSymbol);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                break;
            }
        }
        return (Map<OCSymbol, OCSymbol>)hashMap;
    }
    
    public boolean allFunctionsExist() {
        return true;
    }
    
    public void storeEditor(@Nullable final Editor myEditor, @NotNull final PsiFile myEditorFile) {
        try {
            if (myEditorFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editorFile", "com/jetbrains/cidr/lang/generate/actions/OCCppActionContext", "storeEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myEditor = myEditor;
        this.myEditorFile = myEditorFile;
    }
    
    @Nullable
    public Editor getEditor() {
        return this.myEditor;
    }
    
    @Nullable
    public PsiFile getEditorFile() {
        return this.myEditorFile;
    }
    
    public boolean isReplaceExisting() {
        return this.myReplaceExisting;
    }
    
    public void setReplaceExisting(final boolean myReplaceExisting) {
        this.myReplaceExisting = myReplaceExisting;
    }
    
    public void setExisting(final Collection<OCFunctionSymbol> myExisting) {
        this.myExisting = myExisting;
    }
    
    public Collection<OCFunctionSymbol> getExisting() {
        return this.myExisting;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
