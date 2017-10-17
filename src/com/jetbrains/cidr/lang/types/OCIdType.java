// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbolImpl;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import java.util.List;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class OCIdType extends OCObjectType
{
    public OCIdType() {
    }
    
    private OCIdType(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/types/OCIdType", "<init>"));
        }
        this(Collections.emptyList(), Collections.emptyList(), project, false, false);
    }
    
    public OCIdType(@NotNull final List<OCProtocolSymbol> list, @NotNull final List<OCProtocolSymbol> list2, @NotNull final Project project, final boolean b, final boolean b2) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allProtocols", "com/jetbrains/cidr/lang/types/OCIdType", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "augmentedProtocols", "com/jetbrains/cidr/lang/types/OCIdType", "<init>"));
        }
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/types/OCIdType", "<init>"));
        }
        super(new IDInterfaceSymbol(project), list, list2, b, b2);
    }
    
    public static OCPointerType pointerToID(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/types/OCIdType", "pointerToID"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCPointerType.to(new OCIdType(project));
    }
    
    public Project getProject() {
        return this.getInterface().getProject();
    }
    
    @NotNull
    @Override
    public String getClassName() {
        String s;
        try {
            s = "id";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIdType", "getClassName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitIdType(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
}
