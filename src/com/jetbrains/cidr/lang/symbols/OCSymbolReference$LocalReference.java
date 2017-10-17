// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import java.util.List;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public static class LocalReference extends OCSymbolReference
{
    @Nullable
    private PsiElement myLocalContext;
    
    private LocalReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final PsiElement myLocalContext, final SymbolFilter symbolFilter) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "<init>"));
        }
        super(ocQualifiedName, symbolFilter);
        this.myLocalContext = myLocalContext;
    }
    
    @Override
    protected Object getCacheDependency(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "getCacheDependency"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return PsiModificationTracker.MODIFICATION_COUNT;
    }
    
    @Nullable
    public PsiElement getLocalContext() {
        return this.myLocalContext;
    }
    
    @Override
    public OCSymbolReference getSymbolReferenceToQualifier() {
        return new LocalReference(this.getQualifiedName().getQualifier(), this.myLocalContext, this.getFilterForQualifier());
    }
    
    @NotNull
    @Override
    public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        LocalReference localReference;
        try {
            localReference = new LocalReference(ocQualifiedName, this.myLocalContext, this.myFilter);
            if (localReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return localReference;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolReference2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, ocSymbolReference, ocSymbolReference2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final LocalReference localReference = (LocalReference)ocSymbolReference;
        final LocalReference localReference2 = (LocalReference)ocSymbolReference2;
        try {
            if (!Comparing.equal((Object)localReference.myLocalContext, (Object)localReference2.myLocalContext)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        int n;
        try {
            n = 31 * hashCode;
            if (this.myLocalContext != null) {
                final int hashCode2 = this.myLocalContext.hashCode();
                return n + hashCode2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int hashCode2 = 0;
        return n + hashCode2;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> doResolve(@NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "doResolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0124: {
            if (this.myLocalContext != null) {
                final OCSymbol tryResolveThisSelfSuper = OCThisSelfSuperSymbol.tryResolveThisSelfSuper(this.getQualifiedName().toString(), this.myLocalContext, ocResolveContext);
                List<OCSymbol> list = null;
                Label_0089: {
                    try {
                        if (tryResolveThisSelfSuper == null) {
                            break Label_0124;
                        }
                        final OCSymbol ocSymbol = tryResolveThisSelfSuper;
                        list = Collections.singletonList(ocSymbol);
                        if (list == null) {
                            break Label_0089;
                        }
                        return (List<OCSymbol>)list;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCSymbol ocSymbol = tryResolveThisSelfSuper;
                        list = Collections.singletonList(ocSymbol);
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "doResolve"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return (List<OCSymbol>)list;
            }
        }
        final PsiElement element = ocResolveContext.getElement();
        PsiElement myLocalContext = null;
        Label_0151: {
            try {
                if (this.myLocalContext != null) {
                    myLocalContext = this.myLocalContext;
                    break Label_0151;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            myLocalContext = element;
        }
        ocResolveContext.setElement(myLocalContext);
        final List<OCSymbol> doResolve = super.doResolve(ocResolveContext, b, b2, b3);
        List<OCSymbol> list2;
        try {
            ocResolveContext.setElement(element);
            list2 = doResolve;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "doResolve"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return list2;
    }
    
    @Override
    public String toString() {
        return "LOCAL (" + this.getQualifiedName() + "):" + this.myLocalContext;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
