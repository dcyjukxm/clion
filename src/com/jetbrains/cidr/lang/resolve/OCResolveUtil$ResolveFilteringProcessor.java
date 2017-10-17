// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public static class ResolveFilteringProcessor<T extends OCSymbol> implements Processor<T>
{
    private Processor<? super T> myProcessor;
    private PsiFile myFile;
    private int myTextOffset;
    private final VirtualFile myVirtualFile;
    private boolean myIgnoringImports;
    
    public ResolveFilteringProcessor(final Processor<? super T> myProcessor, @Nullable PsiFile containingFile, final int myTextOffset, final boolean myIgnoringImports) {
        this.myProcessor = myProcessor;
        this.myFile = containingFile;
        if (containingFile instanceof OCCodeFragment) {
            final PsiElement context = containingFile.getContext();
            if (context != null) {
                containingFile = context.getContainingFile();
                this.myTextOffset = context.getTextOffset();
            }
        }
        else {
            this.myTextOffset = myTextOffset;
        }
        this.myVirtualFile = OCInclusionContextUtil.getVirtualFile(containingFile);
        this.myIgnoringImports = myIgnoringImports;
    }
    
    public ResolveFilteringProcessor(final Processor<? super T> processor, final PsiElement psiElement) {
        this(processor, psiElement.getContainingFile(), psiElement.getTextOffset(), false);
    }
    
    public boolean process(final T t) {
        final OCSymbolKind kind = t.getKind();
        return (kind != OCSymbolKind.LABEL && kind != OCSymbolKind.FUNCTION_DECLARATION && !kind.isStructLike() && kind != OCSymbolKind.PARAMETER && kind != OCSymbolKind.STRUCT_FIELD && kind != OCSymbolKind.ENUM_CONST && kind != OCSymbolKind.SYMBOL_USING_SYMBOL && kind != OCSymbolKind.TEMPLATE_TYPE_PARAMETER && (!(t instanceof OCInstanceVariableSymbol) || ((OCInstanceVariableSymbol)t).getGeneratedFromProperty() == null) && !this.myIgnoringImports && (!OCResolveUtil.isInSameStructInCode(t, this.myTextOffset) || (kind == OCSymbolKind.TYPEDEF && !OCResolveUtil.access$000(this.myTextOffset, this.myFile))) && !OCResolveUtil.isEarlierInCode(t, this.myVirtualFile, this.myTextOffset) && (this.myFile == null || !OCResolveUtil.access$100(t, this.myFile, this.myVirtualFile, this.myTextOffset))) || (this.myFile != null && OCResolveUtil.isDisabledSymbol(t, this.myFile)) || this.myProcessor.process((Object)t);
    }
}
