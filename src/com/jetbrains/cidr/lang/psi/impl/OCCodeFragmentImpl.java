// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SingleRootFileViewProvider;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.lang.Language;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.impl.PsiManagerEx;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;

public class OCCodeFragmentImpl extends OCFileImpl implements OCCodeFragment
{
    private PsiElement myContext;
    private boolean myPhysical;
    private FileViewProvider myViewProvider;
    private GlobalSearchScope myResolveScope;
    private Condition<OCSymbol> myFilter;
    
    public OCCodeFragmentImpl(@NotNull final Project project, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final CharSequence charSequence, final boolean myPhysical, @NotNull final IElementType contentElementType) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "<init>"));
        }
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "<init>"));
        }
        if (charSequence == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "<init>"));
        }
        if (contentElementType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementType", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "<init>"));
        }
        super(PsiManagerEx.getInstanceEx(project).getFileManager().createFileViewProvider((VirtualFile)new LightVirtualFile("fragment." + ocLanguageKind.getDefaultSourceExtension(), (Language)OCLanguage.getInstance(), charSequence), myPhysical));
        this.myFilter = null;
        this.myPhysical = myPhysical;
        ((SingleRootFileViewProvider)this.getViewProvider()).forceCachedPsi((PsiFile)this);
        this.init(TokenType.CODE_FRAGMENT, contentElementType);
    }
    
    public OCCodeFragmentImpl(final FileViewProvider myViewProvider, final boolean myPhysical, final IElementType contentElementType) {
        super(myViewProvider);
        this.myFilter = null;
        this.myViewProvider = myViewProvider;
        this.myPhysical = myPhysical;
        this.init(TokenType.CODE_FRAGMENT, contentElementType);
    }
    
    @Override
    protected OCCodeFragmentImpl clone() {
        final OCCodeFragmentImpl ocCodeFragmentImpl = (OCCodeFragmentImpl)this.cloneImpl((FileElement)this.calcTreeElement().clone());
        ocCodeFragmentImpl.myPhysical = false;
        ocCodeFragmentImpl.myOriginalFile = (PsiFile)this;
        final SingleRootFileViewProvider myViewProvider = (SingleRootFileViewProvider)((PsiManagerEx)this.getManager()).getFileManager().createFileViewProvider((VirtualFile)new LightVirtualFile(this.getName(), this.getLanguage(), (CharSequence)this.getText()), false);
        myViewProvider.forceCachedPsi((PsiFile)ocCodeFragmentImpl);
        ocCodeFragmentImpl.myViewProvider = (FileViewProvider)myViewProvider;
        return ocCodeFragmentImpl;
    }
    
    @NotNull
    public PsiFile[] getPsiRoots() {
        PsiFile[] array;
        try {
            array = new PsiFile[] { this };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "getPsiRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return array;
    }
    
    public PsiElement getContext() {
        Label_0052: {
            Label_0020: {
                Logger log;
                try {
                    log = OCLog.LOG;
                    if (this.myContext != this) {
                        final boolean b = true;
                        break Label_0020;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final boolean b = false;
                try {
                    log.assertTrue(b, (Object)"Context cannot be the same element");
                    if (this.myContext == null) {
                        return super.getContext();
                    }
                    final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                    final PsiElement psiElement = ocCodeFragmentImpl.myContext;
                    final boolean b2 = psiElement.isValid();
                    if (b2) {
                        break Label_0052;
                    }
                    return super.getContext();
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                final PsiElement psiElement = ocCodeFragmentImpl.myContext;
                final boolean b2 = psiElement.isValid();
                if (b2) {
                    return this.myContext;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return super.getContext();
    }
    
    @Override
    public void setContext(final PsiElement myContext) {
        this.myContext = myContext;
    }
    
    @NotNull
    public FileViewProvider getViewProvider() {
        FileViewProvider viewProvider = null;
        Label_0057: {
            FileViewProvider fileViewProvider = null;
            Label_0022: {
                try {
                    if (this.myViewProvider == null) {
                        break Label_0057;
                    }
                    final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                    fileViewProvider = ocCodeFragmentImpl.myViewProvider;
                    if (fileViewProvider == null) {
                        break Label_0022;
                    }
                    return fileViewProvider;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                    fileViewProvider = ocCodeFragmentImpl.myViewProvider;
                    if (fileViewProvider == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "getViewProvider"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return fileViewProvider;
            try {
                viewProvider = super.getViewProvider();
                if (viewProvider == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "getViewProvider"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return viewProvider;
    }
    
    public boolean isPhysical() {
        Label_0021: {
            try {
                if (!super.isPhysical()) {
                    return false;
                }
                final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                final boolean b = ocCodeFragmentImpl.myPhysical;
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                final boolean b = ocCodeFragmentImpl.myPhysical;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public void forceResolveScope(final GlobalSearchScope myResolveScope) {
        this.myResolveScope = myResolveScope;
    }
    
    public GlobalSearchScope getForcedResolveScope() {
        return this.myResolveScope;
    }
    
    @NotNull
    public GlobalSearchScope getResolveScope() {
        GlobalSearchScope resolveScope = null;
        Label_0057: {
            GlobalSearchScope globalSearchScope = null;
            Label_0022: {
                try {
                    if (this.myResolveScope == null) {
                        break Label_0057;
                    }
                    final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                    globalSearchScope = ocCodeFragmentImpl.myResolveScope;
                    if (globalSearchScope == null) {
                        break Label_0022;
                    }
                    return globalSearchScope;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCCodeFragmentImpl ocCodeFragmentImpl = this;
                    globalSearchScope = ocCodeFragmentImpl.myResolveScope;
                    if (globalSearchScope == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "getResolveScope"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return globalSearchScope;
            try {
                resolveScope = super.getResolveScope();
                if (resolveScope == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl", "getResolveScope"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return resolveScope;
    }
    
    @Override
    public Condition<OCSymbol> getCompletionFilter() {
        return this.myFilter;
    }
    
    @Override
    public void setCompletionFilter(final Condition<OCSymbol> myFilter) {
        this.myFilter = myFilter;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
