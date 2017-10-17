// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.psi.ResolveResult;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.OCIncludeHelpers;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference;

class OCIncludeDirectiveImpl$1$1 extends FileReference {
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
        final OCIncludeDirectiveImpl$1$1.1ImportProcessor 1ImportProcessor = new OCIncludeDirectiveImpl$1$1.1ImportProcessor();
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
        final VirtualFile virtualFile3 = OCInclusionContextUtil.getVirtualFile((PsiFile)FileReferenceSet.this.this$0.getContainingOCFile());
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
                OCIncludeHelpers.processImportSpecifications(FileReferenceSet.this.this$0.getProject(), OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile3, FileReferenceSet.this.this$0.getProject()), virtualFile3, virtualFile2, (Processor<OCAutoImportHelper.ImportSpecification>)1ImportProcessor);
                try {
                    if (1ImportProcessor.best == null) {
                        break Label_0168;
                    }
                    final OCIncludeDirectiveImpl$1$1.1ImportProcessor 1ImportProcessor2 = 1ImportProcessor;
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
                final OCIncludeDirectiveImpl$1$1.1ImportProcessor 1ImportProcessor2 = 1ImportProcessor;
                final boolean b = 1ImportProcessor2.exactMatch;
                if (b) {
                    return (PsiElement)FileReferenceSet.this.this$0;
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
        final OCIncludeSymbol ocIncludeSymbol = FileReferenceSet.this.this$0.getContainingOCFile().findSymbol(FileReferenceSet.this.this$0, OCIncludeSymbol.class);
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
            final PsiFile file = PsiManager.getInstance(FileReferenceSet.this.this$0.getProject()).findFile(ocIncludeSymbol.getTargetFile());
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
}