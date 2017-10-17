// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import com.jetbrains.cidr.lang.asm.AsmFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.asm.AsmLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.FileViewProvider;
import com.intellij.extapi.psi.PsiFileBase;

public class AsmFile extends PsiFileBase
{
    public AsmFile(@NotNull final FileViewProvider viewProvider) {
        if (viewProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "viewProvider", "com/jetbrains/cidr/lang/asm/psi/AsmFile", "<init>"));
        }
        super(viewProvider, AsmLanguage.INSTANCE);
    }
    
    @NotNull
    public FileType getFileType() {
        AsmFileType instance;
        try {
            instance = AsmFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/psi/AsmFile", "getFileType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (FileType)instance;
    }
    
    @Override
    public String toString() {
        return "Assembly file";
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
