// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.lang.Language;
import com.intellij.psi.FileViewProvider;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.extapi.psi.PsiFileBase;

class DBParserDefinition$1 extends PsiFileBase {
    final /* synthetic */ LanguageFileType val$type;
    
    @NotNull
    public FileType getFileType() {
        LanguageFileType val$type;
        try {
            val$type = this.val$type;
            if (val$type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lang/DBParserDefinition$1", "getFileType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (FileType)val$type;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}