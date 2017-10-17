// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiDirectory;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.psi.ResolveResult;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.OCIncludeHelpers;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PathUtil;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;

class OCIncludeDirectiveImpl$1 extends FileReferenceSet {
    final /* synthetic */ PsiElement val$headerPathContent;
    final /* synthetic */ TextRange val$allContent;
    
    @Override
    public FileReference createFileReference(final TextRange textRange, final int n, final String s) {
        return new FileReference(this, (this.val$headerPathContent instanceof OCMacroForeignLeafElement) ? this.val$allContent : textRange, n, s) {
            private OCAutoImportHelper.ImportSpecification newSpec = null;
            private Delimiters newDelimiters = null;
            
            @Override
            public PsiElement bindToElement(@NotNull final PsiElement element, final boolean absolute) throws IncorrectOperationException {
                try {
                    if (element == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl$1$1", "bindToElement"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                class 1ImportProcessor implements Processor<OCAutoImportHelper.ImportSpecification>
                {
                    public OCAutoImportHelper.ImportSpecification best;
                    public Delimiters bestDelimiters;
                    public boolean exactMatch;
                    public boolean renameOnly;
                    
                    1ImportProcessor() {
                        this.best = null;
                        this.bestDelimiters = null;
                        this.exactMatch = false;
                        this.renameOnly = false;
                    }
                    
                    public boolean process(final OCAutoImportHelper.ImportSpecification importSpecification) {
                        assert !this.exactMatch && !this.renameOnly;
                        final Delimiters delimiters = OCIncludeDirectiveImpl.this.getDelimiters();
                        if (importSpecification.areDelimitersAllowed(delimiters)) {
                            if (!OCIncludeDirectiveImpl.access$000(importSpecification.getImportPath(), OCIncludeDirectiveImpl.this.getReferenceText())) {
                                this.exactMatch = true;
                            }
                            else if (!OCIncludeDirectiveImpl.access$000(PathUtil.getParentPath(importSpecification.getImportPath()), PathUtil.getParentPath(OCIncludeDirectiveImpl.this.getReferenceText()))) {
                                this.renameOnly = true;
                            }
                            if (this.exactMatch || this.renameOnly) {
                                this.best = importSpecification;
                                this.bestDelimiters = delimiters;
                                return false;
                            }
                        }
                        final Delimiters bestDelimiters = importSpecification.areDelimitersAllowed(delimiters) ? delimiters : importSpecification.getPreferredDelimiters();
                        if (this.best == null || (this.bestDelimiters != delimiters && bestDelimiters == delimiters)) {
                            this.best = importSpecification;
                            this.bestDelimiters = bestDelimiters;
                        }
                        return true;
                    }
                }
                final 1ImportProcessor 1ImportProcessor = new 1ImportProcessor();
                VirtualFile virtualFile = null;
                Label_0075: {
                    try {
                        if (element instanceof PsiFile) {
                            virtualFile = OCInclusionContextUtil.getVirtualFile((PsiFile)element);
                            break Label_0075;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    virtualFile = null;
                }
                final VirtualFile virtualFile2 = virtualFile;
                final VirtualFile virtualFile3 = OCInclusionContextUtil.getVirtualFile((PsiFile)OCIncludeDirectiveImpl.this.getContainingOCFile());
                Label_0168: {
                    Label_0147: {
                        try {
                            if (virtualFile2 == null || virtualFile3 == null) {
                                break Label_0147;
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                        OCIncludeHelpers.processImportSpecifications(OCIncludeDirectiveImpl.this.getProject(), OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile3, OCIncludeDirectiveImpl.this.getProject()), virtualFile3, virtualFile2, (Processor<OCAutoImportHelper.ImportSpecification>)1ImportProcessor);
                        try {
                            if (1ImportProcessor.best == null) {
                                break Label_0168;
                            }
                            final 1ImportProcessor 1ImportProcessor2 = 1ImportProcessor;
                            final boolean b = 1ImportProcessor2.exactMatch;
                            if (b) {
                                break Label_0168;
                            }
                            break Label_0168;
                        }
                        catch (IncorrectOperationException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        final 1ImportProcessor 1ImportProcessor2 = 1ImportProcessor;
                        final boolean b = 1ImportProcessor2.exactMatch;
                        if (b) {
                            return (PsiElement)OCIncludeDirectiveImpl.this;
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                this.newSpec = 1ImportProcessor.best;
                this.newDelimiters = 1ImportProcessor.bestDelimiters;
                return super.bindToElement(element, absolute);
            }
            
            @Override
            protected PsiElement rename(final String newName) throws IncorrectOperationException {
                Label_0021: {
                    try {
                        if (this.newSpec == null) {
                            break Label_0021;
                        }
                        final FileReference fileReference = this;
                        final Delimiters delimiters = fileReference.newDelimiters;
                        if (delimiters == null) {
                            break Label_0021;
                        }
                        return super.rename(this.newSpec.getImportText(this.newDelimiters));
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final FileReference fileReference = this;
                        final Delimiters delimiters = fileReference.newDelimiters;
                        if (delimiters == null) {
                            OCLog.LOG.error("Invalid rename for include directive");
                            return super.rename(newName);
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return super.rename(this.newSpec.getImportText(this.newDelimiters));
            }
            
            @NotNull
            @Override
            protected ResolveResult[] innerResolve(final boolean caseSensitive, @NotNull final PsiFile containingFile) {
                try {
                    if (containingFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl$1$1", "innerResolve"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                final OCIncludeSymbol ocIncludeSymbol = OCIncludeDirectiveImpl.this.getContainingOCFile().findSymbol(OCIncludeDirectiveImpl.this, OCIncludeSymbol.class);
                ResolveResult[] innerResolve = null;
                Label_0196: {
                    Label_0090: {
                        try {
                            if (ocIncludeSymbol == null) {
                                break Label_0196;
                            }
                            final OCIncludeSymbol ocIncludeSymbol2 = ocIncludeSymbol;
                            final VirtualFile virtualFile = ocIncludeSymbol2.getTargetFile();
                            if (virtualFile != null) {
                                break Label_0090;
                            }
                            break Label_0196;
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCIncludeSymbol ocIncludeSymbol2 = ocIncludeSymbol;
                            final VirtualFile virtualFile = ocIncludeSymbol2.getTargetFile();
                            if (virtualFile == null) {
                                break Label_0196;
                            }
                            if (!ocIncludeSymbol.getTargetFile().isValid()) {
                                break Label_0196;
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                    }
                    final PsiFile file = PsiManager.getInstance(OCIncludeDirectiveImpl.this.getProject()).findFile(ocIncludeSymbol.getTargetFile());
                    ResolveResult[] array = null;
                    Label_0161: {
                        try {
                            if (file == null) {
                                break Label_0196;
                            }
                            final int n = 1;
                            array = new ResolveResult[n];
                            final int n2 = 0;
                            final Object o = file;
                            final PsiElementResolveResult psiElementResolveResult = new PsiElementResolveResult((PsiElement)o);
                            array[n2] = (ResolveResult)psiElementResolveResult;
                            if (array == null) {
                                break Label_0161;
                            }
                            return array;
                        }
                        catch (IncorrectOperationException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final int n = 1;
                            array = new ResolveResult[n];
                            final int n2 = 0;
                            final Object o = file;
                            final PsiElementResolveResult psiElementResolveResult = new PsiElementResolveResult((PsiElement)o);
                            array[n2] = (ResolveResult)psiElementResolveResult;
                            if (array == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl$1$1", "innerResolve"));
                            }
                        }
                        catch (IncorrectOperationException ex5) {
                            throw a(ex5);
                        }
                    }
                    return array;
                    try {
                        innerResolve = super.innerResolve(caseSensitive, containingFile);
                        if (innerResolve == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl$1$1", "innerResolve"));
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                }
                return innerResolve;
            }
            
            private static IncorrectOperationException a(final IncorrectOperationException ex) {
                return ex;
            }
        };
    }
    
    @Override
    public boolean isCaseSensitive() {
        return SystemInfo.isFileSystemCaseSensitive;
    }
    
    @Override
    protected Condition<PsiFileSystemItem> getReferenceCompletionFilter() {
        return (Condition<PsiFileSystemItem>)(psiFileSystemItem -> {
            final OCIncludeHelpers.ShowInCompletion showInCompletion = OCIncludeHelpers.showInCompletion(psiFileSystemItem);
            if (showInCompletion == OCIncludeHelpers.ShowInCompletion.SHOW) {
                return true;
            }
            if (showInCompletion == OCIncludeHelpers.ShowInCompletion.DON_NOT_SHOW) {
                return false;
            }
            if (psiFileSystemItem instanceof PsiFile) {
                return OCFileTypeHelpers.isHeaderFile(psiFileSystemItem.getName());
            }
            return psiFileSystemItem instanceof PsiDirectory && !".idea".equals(psiFileSystemItem.getName());
        });
    }
}