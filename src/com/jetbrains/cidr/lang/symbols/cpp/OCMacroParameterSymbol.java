// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import java.util.Collections;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCMacroParameterSymbol extends OCSymbolImpl
{
    public OCMacroParameterSymbol() {
    }
    
    public OCMacroParameterSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, @Nullable final String s) {
        super(project, virtualFile, n, s, Collections.emptyList());
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind macro_PARAMETER;
        try {
            macro_PARAMETER = OCSymbolKind.MACRO_PARAMETER;
            if (macro_PARAMETER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroParameterSymbol", "getKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return macro_PARAMETER;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
