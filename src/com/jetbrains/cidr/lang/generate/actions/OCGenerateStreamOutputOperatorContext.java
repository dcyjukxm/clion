// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import kotlin.jvm.internal.Intrinsics;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import java.util.ArrayList;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016" }, d2 = { "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorContext;", "Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;", "parent", "context", "Lcom/intellij/psi/PsiElement;", "(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/intellij/psi/PsiElement;)V", "declarationFile", "Lcom/jetbrains/cidr/lang/psi/OCFile;", "getDeclarationFile", "()Lcom/jetbrains/cidr/lang/psi/OCFile;", "setDeclarationFile", "(Lcom/jetbrains/cidr/lang/psi/OCFile;)V", "hasRequiredIncludes", "", "getHasRequiredIncludes", "()Z", "setHasRequiredIncludes", "(Z)V", "getMemberCandidates", "", "cidr-lang" })
public final class OCGenerateStreamOutputOperatorContext extends OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol>
{
    @Nullable
    private OCFile declarationFile;
    private boolean hasRequiredIncludes;
    
    @Nullable
    public final OCFile getDeclarationFile() {
        return this.declarationFile;
    }
    
    public final void setDeclarationFile(@Nullable final OCFile declarationFile) {
        this.declarationFile = declarationFile;
    }
    
    public final boolean getHasRequiredIncludes() {
        return this.hasRequiredIncludes;
    }
    
    public final void setHasRequiredIncludes(final boolean hasRequiredIncludes) {
        this.hasRequiredIncludes = hasRequiredIncludes;
    }
    
    @NotNull
    @Override
    public Collection<OCDeclaratorSymbol> getMemberCandidates() {
        final ArrayList<OCDeclaratorSymbol> list = new ArrayList<OCDeclaratorSymbol>();
        ((OCActionContext<OCStructSymbol, M>)this).getParent().processFields((Processor<OCDeclaratorSymbol>)new OCGenerateStreamOutputOperatorContext$getMemberCandidates.OCGenerateStreamOutputOperatorContext$getMemberCandidates$1((ArrayList)list));
        return list;
    }
    
    public OCGenerateStreamOutputOperatorContext(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final PsiElement psiElement) {
        Intrinsics.checkParameterIsNotNull((Object)ocStructSymbol, "parent");
        Intrinsics.checkParameterIsNotNull((Object)psiElement, "context");
        super(ocStructSymbol, psiElement);
    }
}
