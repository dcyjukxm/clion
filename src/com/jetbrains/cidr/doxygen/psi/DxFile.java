// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import javax.swing.Icon;
import com.jetbrains.cidr.doxygen.DxFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.doxygen.DxLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.FileViewProvider;
import com.intellij.extapi.psi.PsiFileBase;

public class DxFile extends PsiFileBase
{
    public DxFile(@NotNull final FileViewProvider viewProvider) {
        if (viewProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "viewProvider", "com/jetbrains/cidr/doxygen/psi/DxFile", "<init>"));
        }
        super(viewProvider, DxLanguage.INSTANCE);
    }
    
    @NotNull
    public FileType getFileType() {
        DxFileType instance;
        try {
            instance = DxFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/DxFile", "getFileType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (FileType)instance;
    }
    
    @Override
    public String toString() {
        return "Doxygen File";
    }
    
    public Icon getIcon(final int n) {
        return super.getIcon(n);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
