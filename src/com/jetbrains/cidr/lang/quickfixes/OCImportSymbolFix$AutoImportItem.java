// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class AutoImportItem
{
    @NotNull
    private final OCSymbol mySymbolToImport;
    @Nullable
    private final PsiElement myInsertBefore;
    @NotNull
    private final VirtualFile myFileToImport;
    @NotNull
    private final OCAutoImportHelper.ImportSpecification myFileNameToImport;
    @Nullable
    private Pair<String, String> myCachedTitleAndLocation;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public AutoImportItem(@Nullable final OCSymbol mySymbolToImport, @NotNull final PsiElement myInsertBefore, @NotNull final VirtualFile myFileToImport, final OCAutoImportHelper.ImportSpecification myFileNameToImport) {
        if (mySymbolToImport == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "<init>"));
        }
        if (myFileToImport == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "<init>"));
        }
        if (myFileNameToImport == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNameToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "<init>"));
        }
        this.mySymbolToImport = mySymbolToImport;
        this.myInsertBefore = myInsertBefore;
        this.myFileToImport = myFileToImport;
        this.myFileNameToImport = myFileNameToImport;
    }
    
    @Override
    public String toString() {
        final Pair<String, String> titleAndLocation = this.getTitleAndLocation();
        return (String)titleAndLocation.first + "@" + (String)titleAndLocation.second;
    }
    
    @NotNull
    public Pair<String, String> getTitleAndLocation() {
        Label_0057: {
            Pair<String, String> pair = null;
            Label_0022: {
                try {
                    if (this.myCachedTitleAndLocation == null) {
                        break Label_0057;
                    }
                    final AutoImportItem autoImportItem = this;
                    pair = autoImportItem.myCachedTitleAndLocation;
                    if (pair == null) {
                        break Label_0022;
                    }
                    return pair;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final AutoImportItem autoImportItem = this;
                    pair = autoImportItem.myCachedTitleAndLocation;
                    if (pair == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getTitleAndLocation"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return pair;
        }
        String s = this.mySymbolToImport.getKindLowercase();
        if (this.mySymbolToImport instanceof OCClassSymbol) {
            s = "@" + s;
        }
        String s2 = this.mySymbolToImport.getName();
        if (this.mySymbolToImport instanceof OCSymbolWithQualifiedName) {
            final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)this.mySymbolToImport).getResolvedQualifiedName(true, false);
            if (resolvedQualifiedName != null) {
                s2 = resolvedQualifiedName.getCanonicalName(true, false);
            }
        }
        Pair create;
        try {
            create = Pair.create((Object)(s + " '" + s2 + "'"), (Object)this.a().getImportText());
            if ((this.myCachedTitleAndLocation = (Pair<String, String>)create) == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getTitleAndLocation"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return (Pair<String, String>)create;
    }
    
    @NotNull
    private OCAutoImportHelper.ImportSpecification a() {
        OCAutoImportHelper.ImportSpecification myFileNameToImport;
        try {
            myFileNameToImport = this.myFileNameToImport;
            if (myFileNameToImport == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getFileSpecToImport"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myFileNameToImport;
    }
    
    @NotNull
    public VirtualFile getFileToImport() {
        VirtualFile myFileToImport;
        try {
            myFileToImport = this.myFileToImport;
            if (myFileToImport == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getFileToImport"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myFileToImport;
    }
    
    public void invoke(@NotNull final Project project, @NotNull final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final VirtualFile fileToImport = this.getFileToImport();
        final VirtualFile virtualFile = psiFile.getVirtualFile();
        final OCAutoImportHelper[] array = (OCAutoImportHelper[])Extensions.getExtensions((ExtensionPointName)OCAutoImportHelper.EP_NAME);
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].addHeaderSearchPath(project, virtualFile, fileToImport);
        }
        final PsiElement access$200 = OCImportSymbolFix.access$200(OCImportSymbolFix.this);
        Label_0185: {
            try {
                if (AutoImportItem.$assertionsDisabled) {
                    break Label_0185;
                }
                final PsiElement psiElement = access$200;
                if (psiElement == null) {
                    break Label_0185;
                }
                break Label_0185;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final PsiElement psiElement = access$200;
                if (psiElement == null) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        final ImportStyle access$201 = OCImportSymbolFix.access$400(this, access$200, OCImportSymbolFix.access$300(OCImportSymbolFix.this));
        OCFile associatedFile = null;
        Label_0237: {
            try {
                if (access$201 == ImportStyle.PREDECLARE) {
                    associatedFile = ((OCFile)psiFile).getAssociatedFile();
                    break Label_0237;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            associatedFile = null;
        }
        final OCFile ocFile = associatedFile;
        new WriteCommandAction(project, "Import " + (String)this.getTitleAndLocation().first, new PsiFile[] { psiFile, ocFile }) {
            public void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                int n = access$200.getTextOffset();
                if (AutoImportItem.this.myInsertBefore != null) {
                    n = Math.min(n, AutoImportItem.this.myInsertBefore.getTextOffset());
                }
                try {
                    OCImportSymbolFix.addImportToFile((OCFile)psiFile, OCImportSymbolFix.access$600(AutoImportItem.this, access$201, access$200), access$201, n);
                    if (ocFile != null) {
                        OCImportSymbolFix.addImportToFile(ocFile, OCImportSymbolFix.access$600(AutoImportItem.this, ImportStyle.INCLUDE, access$200), ImportStyle.INCLUDE, n);
                    }
                }
                catch (Throwable t2) {
                    throw a(t2);
                }
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCImportSymbolFix.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
