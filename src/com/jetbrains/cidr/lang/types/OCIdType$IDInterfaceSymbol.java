// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbolImpl;

public static class IDInterfaceSymbol extends OCInterfaceSymbolImpl
{
    public IDInterfaceSymbol() {
    }
    
    public IDInterfaceSymbol(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/types/OCIdType$IDInterfaceSymbol", "<init>"));
        }
        super(project, null, 0L, "id", Collections.emptyList(), null, null, Collections.emptyList(), OCReferenceType.fromText(""), Collections.emptyList());
    }
    
    @Override
    public boolean isPredeclaration() {
        return false;
    }
}
