// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.impl.LoadTextUtil;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.util.io.FileUtilRt;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiFile;

public abstract class SymbolTableProvider<FILE extends PsiFile>
{
    private static final Logger LOG;
    public static final ExtensionPointName<SymbolTableProvider> INSTANCE;
    
    protected abstract boolean isSource(@NotNull final PsiFile p0);
    
    public abstract boolean isSource(@NotNull final VirtualFile p0);
    
    public abstract void onOutOfCodeBlockModification(@NotNull final PsiFile p0);
    
    @NotNull
    public abstract FileSymbolTable calcTableUsingPSI(@NotNull final FILE p0, @NotNull final VirtualFile p1, @NotNull final OCInclusionContext p2);
    
    @NotNull
    public abstract FileSymbolTable calcTable(@NotNull final FILE p0, @NotNull final VirtualFile p1, @NotNull final OCInclusionContext p2);
    
    @Nullable
    public OCSymbolTablesBuildingActivity.TaskProvider<?> getItemProviderAndWorkerForAdditionalSymbolLoading(@NotNull final Project project, @NotNull final ProgressIndicator progressIndicator, @NotNull final Collection<VirtualFile> collection) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getItemProviderAndWorkerForAdditionalSymbolLoading"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getItemProviderAndWorkerForAdditionalSymbolLoading"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allFiles", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getItemProviderAndWorkerForAdditionalSymbolLoading"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    public static void fireOutOfCodeBlockModification(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "fireOutOfCodeBlockModification"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final SymbolTableProvider[] array = (SymbolTableProvider[])SymbolTableProvider.INSTANCE.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].onOutOfCodeBlockModification(psiFile);
        }
    }
    
    @NotNull
    public static <FILE extends PsiFile> SymbolTableProvider<FILE> getProvider(final FILE file) {
        for (final SymbolTableProvider symbolTableProvider : (SymbolTableProvider[])SymbolTableProvider.INSTANCE.getExtensions()) {
            Label_0087: {
                SymbolTableProvider symbolTableProvider2 = null;
                Label_0052: {
                    try {
                        if (!symbolTableProvider.isSource(file)) {
                            break Label_0087;
                        }
                        symbolTableProvider2 = symbolTableProvider;
                        if (symbolTableProvider2 == null) {
                            break Label_0052;
                        }
                        return (SymbolTableProvider<FILE>)symbolTableProvider2;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        symbolTableProvider2 = symbolTableProvider;
                        if (symbolTableProvider2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getProvider"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return (SymbolTableProvider<FILE>)symbolTableProvider2;
            }
        }
        SymbolTableProvider<FILE> symbolTableProvider3;
        try {
            SymbolTableProvider.LOG.error(file.getClass() + "; file extension: " + FileUtilRt.getExtension(file.getName()));
            symbolTableProvider3 = null;
            if (symbolTableProvider3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getProvider"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return symbolTableProvider3;
    }
    
    public static boolean isSourceFile(@Nullable final VirtualFile virtualFile) {
        Label_0024: {
            try {
                if (virtualFile == null) {
                    return false;
                }
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = virtualFile2.isValid();
                if (!b) {
                    return false;
                }
                break Label_0024;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = virtualFile2.isValid();
                if (!b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        for (final SymbolTableProvider symbolTableProvider : (SymbolTableProvider[])SymbolTableProvider.INSTANCE.getExtensions()) {
            try {
                if (symbolTableProvider.isSource(virtualFile)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isSourceFile(@Nullable final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final SymbolTableProvider symbolTableProvider : (SymbolTableProvider[])SymbolTableProvider.INSTANCE.getExtensions()) {
            try {
                if (symbolTableProvider.isSource(psiFile)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    protected static String getFileText(@NotNull final PsiFile psiFile, @NotNull final VirtualFile virtualFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getFileText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getFileText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Document cachedDocument = PsiDocumentManager.getInstance(psiFile.getProject()).getCachedDocument(psiFile);
        String string = null;
        Label_0160: {
            String s = null;
            Label_0125: {
                try {
                    if (cachedDocument == null) {
                        break Label_0160;
                    }
                    final Document document = cachedDocument;
                    s = document.getText();
                    if (s == null) {
                        break Label_0125;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final Document document = cachedDocument;
                    s = document.getText();
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getFileText"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return s;
            try {
                string = LoadTextUtil.loadText(virtualFile).toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider", "getFileText"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return string;
    }
    
    static {
        LOG = Logger.getInstance((Class)SymbolTableProvider.class);
        INSTANCE = ExtensionPointName.create("cidr.lang.symbolTableProvider");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
