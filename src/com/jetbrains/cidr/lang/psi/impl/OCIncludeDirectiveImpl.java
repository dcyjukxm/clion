// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.resolve.OCImportManipulator;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.psi.util.CachedValueProvider;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
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
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PathUtil;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.ElementManipulator;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;

public class OCIncludeDirectiveImpl extends OCDirectiveImpl implements OCIncludeDirective
{
    private static final ElementManipulator<OCIncludeDirectiveImpl> ourManipulator;
    private CachedValue<PsiFile> myImportedFile;
    
    public OCIncludeDirectiveImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    public PsiReference[] getReferences() {
        FileReference[] allReferences;
        try {
            allReferences = this.a().getAllReferences();
            if (allReferences == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "getReferences"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (PsiReference[])allReferences;
    }
    
    private static boolean a(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidate", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "shouldChangePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "existing", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "shouldChangePath"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        Label_0120: {
            try {
                if (s2.contains("\\")) {
                    break Label_0120;
                }
                final String s3 = s;
                final boolean b = false;
                final String s4 = FileUtil.toCanonicalPath(s3, b);
                final String s5 = s2;
                final boolean b2 = false;
                final String s6 = FileUtil.toCanonicalPath(s5, b2);
                final boolean b3 = s4.equals(s6);
                if (!b3) {
                    break Label_0120;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            try {
                final String s3 = s;
                final boolean b = false;
                final String s4 = FileUtil.toCanonicalPath(s3, b);
                final String s5 = s2;
                final boolean b2 = false;
                final String s6 = FileUtil.toCanonicalPath(s5, b2);
                final boolean b3 = s4.equals(s6);
                if (!b3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return false;
    }
    
    private FileReferenceSet a() {
        final PsiElement childByType = this.findChildByType(OCTokenTypes.HEADER_PATH_LITERAL);
        final TextRange shiftRight = ((TextRange)this.getContent(true).second).shiftRight(-this.getTextRange().getStartOffset());
        FileReferenceSet set;
        if (childByType instanceof OCMacroForeignLeafElement) {
            set = new FileReferenceSet(this.b().getName(), (PsiElement)this, shiftRight.getStartOffset(), null, true, false);
        }
        else {
            set = FileReferenceSet.createSet((PsiElement)this, false, true, false);
        }
        return new FileReferenceSet(set.getPathString(), this, set.getStartInElement(), null, true, true) {
            @Override
            public FileReference createFileReference(final TextRange textRange, final int n, final String s) {
                return new FileReference(this, (childByType instanceof OCMacroForeignLeafElement) ? shiftRight : textRange, n, s) {
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
                                    if (!a(importSpecification.getImportPath(), OCIncludeDirectiveImpl.this.getReferenceText())) {
                                        this.exactMatch = true;
                                    }
                                    else if (!a(PathUtil.getParentPath(importSpecification.getImportPath()), PathUtil.getParentPath(OCIncludeDirectiveImpl.this.getReferenceText()))) {
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
        };
    }
    
    private OCSymbol b() {
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol instanceof OCIncludeSymbol && ocSymbol.getOffset() == OCIncludeDirectiveImpl.this.getTextOffset();
            }
        };
        final OCFile containingOCFile = this.getContainingOCFile();
        new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(this.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)BuilderDriverBase.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)findFirstProcessor).processImportDirective(this.getNode());
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @NotNull
    @Override
    public String getReferenceText() {
        final TextRange rangeInElement = OCIncludeDirectiveImpl.ourManipulator.getRangeInElement((PsiElement)this);
        String substring;
        try {
            substring = rangeInElement.substring(this.getText());
            if (substring == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "getReferenceText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return substring;
    }
    
    @NotNull
    @Override
    public Delimiters getDelimiters() {
        Delimiters delimiters = null;
        Label_0020: {
            try {
                if (this.isAngleBrackets()) {
                    final Delimiters delimiters2;
                    delimiters = (delimiters2 = Delimiters.ANGLE_BRACKETS);
                    break Label_0020;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            Delimiters delimiters2;
            delimiters = (delimiters2 = Delimiters.QUOTES);
            try {
                if (delimiters2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "getDelimiters"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return delimiters;
    }
    
    @Override
    public boolean isAngleBrackets() {
        final int startOffset = OCIncludeDirectiveImpl.ourManipulator.getRangeInElement((PsiElement)this).getStartOffset();
        Label_0039: {
            try {
                if (startOffset <= 0) {
                    return false;
                }
                final OCIncludeDirectiveImpl ocIncludeDirectiveImpl = this;
                final String s = ocIncludeDirectiveImpl.getText();
                final int n = startOffset;
                final int n2 = 1;
                final int n3 = n - n2;
                final char c = s.charAt(n3);
                final char c2 = '<';
                if (c == c2) {
                    break Label_0039;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCIncludeDirectiveImpl ocIncludeDirectiveImpl = this;
                final String s = ocIncludeDirectiveImpl.getText();
                final int n = startOffset;
                final int n2 = 1;
                final int n3 = n - n2;
                final char c = s.charAt(n3);
                final char c2 = '<';
                if (c == c2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isTopLevel() {
        return PsiTreeUtil.skipParentsOfType((PsiElement)this, new Class[] { OCExternBlockImpl.class }) instanceof OCFile;
    }
    
    @Override
    public PsiFile getIncludedFile() {
        if (this.myImportedFile == null) {
            final Project project = this.getProject();
            this.myImportedFile = (CachedValue<PsiFile>)CachedValuesManager.getManager(project).createCachedValue(() -> new CachedValueProvider.Result((Object)this.c(), new Object[] { FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker() }), true);
        }
        return (PsiFile)this.myImportedFile.getValue();
    }
    
    @Nullable
    private PsiFile c() {
        final FileReference lastReference = this.a().getLastReference();
        if (lastReference != null) {
            final PsiFileSystemItem resolve = lastReference.resolve();
            try {
                if (resolve instanceof PsiFile) {
                    return (PsiFile)resolve;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCIncludeDirectiveImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocVisitor.visitImportDirective(this);
    }
    
    @Override
    public boolean isValidDirective() {
        try {
            if (this.getReferenceText().isEmpty()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        for (final PsiReference psiReference : this.getReferences()) {
            try {
                if (psiReference.resolve() == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        final TextRange rangeInElement = OCIncludeDirectiveImpl.ourManipulator.getRangeInElement((PsiElement)this);
        final String text = this.getText();
        char char1 = '\0';
        char char2 = '\0';
        if (rangeInElement.getStartOffset() > 0) {
            char1 = text.charAt(rangeInElement.getStartOffset() - 1);
        }
        if (rangeInElement.getEndOffset() < text.length()) {
            char2 = text.charAt(rangeInElement.getEndOffset());
        }
        Label_0143: {
            try {
                if (char1 != '\"') {
                    break Label_0143;
                }
                final char c = char2;
                final char c2 = '\"';
                if (c != c2) {
                    return false;
                }
                break Label_0143;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            try {
                final char c = char2;
                final char c2 = '\"';
                if (c != c2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            try {
                if (char1 != '<') {
                    return true;
                }
                final char c3 = char2;
                final char c4 = '>';
                if (c3 != c4) {
                    return false;
                }
                return true;
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
        }
        try {
            final char c3 = char2;
            final char c4 = '>';
            if (c3 != c4) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        return true;
    }
    
    static {
        ourManipulator = (ElementManipulator)new OCImportManipulator();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
