// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.references.OCResourceFilesProvider;
import com.jetbrains.cidr.lang.psi.OCStringsFile;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.search.FilenameIndex;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCLocalizedStringSymbol extends OCSymbolImpl<OCLocalizedString>
{
    public OCLocalizedStringSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, @Nullable final String s, @NotNull final List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCLocalizedStringSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, list);
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind localized_STRING;
        try {
            localized_STRING = OCSymbolKind.LOCALIZED_STRING;
            if (localized_STRING == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCLocalizedStringSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return localized_STRING;
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        for (final PsiFile psiFile : FilenameIndex.getFilesByName(this.myProject, this.myFile.getName(), OCSearchScope.getProjectSourcesScope(this.myProject))) {
            ProgressManager.checkCanceled();
            Label_0144: {
                if (psiFile instanceof OCStringsFile) {
                    final OCStringsFile ocStringsFile = (OCStringsFile)psiFile;
                    try {
                        if (!OCResourceFilesProvider.isAccessible((PsiElement)ocStringsFile, this.myFile)) {
                            break Label_0144;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    final OCLocalizedString stringPair = ocStringsFile.findStringPair(this.myName);
                    OCLocalizedStringSymbol ocLocalizedStringSymbol = null;
                    Label_0113: {
                        try {
                            if (stringPair != null) {
                                ocLocalizedStringSymbol = stringPair.getSymbol();
                                break Label_0113;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        ocLocalizedStringSymbol = null;
                    }
                    final OCLocalizedStringSymbol ocLocalizedStringSymbol2 = ocLocalizedStringSymbol;
                    try {
                        if (ocLocalizedStringSymbol2 == null) {
                            break Label_0144;
                        }
                        final Processor<OCSymbol> processor2 = processor;
                        final OCLocalizedStringSymbol ocLocalizedStringSymbol3 = ocLocalizedStringSymbol2;
                        final boolean b = processor2.process((Object)ocLocalizedStringSymbol3);
                        if (!b) {
                            return false;
                        }
                        break Label_0144;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final Processor<OCSymbol> processor2 = processor;
                        final OCLocalizedStringSymbol ocLocalizedStringSymbol3 = ocLocalizedStringSymbol2;
                        final boolean b = processor2.process((Object)ocLocalizedStringSymbol3);
                        if (!b) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
            }
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
