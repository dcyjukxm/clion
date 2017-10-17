// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.OCIdType;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.util.containers.MostlySingularMultiMap;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class OCProtocolSymbolImpl extends OCClassSymbolImpl implements OCProtocolSymbol
{
    public OCProtocolSymbolImpl() {
    }
    
    public OCProtocolSymbolImpl(@NotNull final Project project, @Nullable final VirtualFile virtualFile, final long n, @NotNull final String s, @NotNull final List<String> list, @Nullable final String s2, @Nullable final MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap, @NotNull final List<String> list2, @Nullable final OCReferenceType ocReferenceType) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolNames", "com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list, s2, mostlySingularMultiMap, list2, ocReferenceType);
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind protocol;
        try {
            protocol = OCSymbolKind.PROTOCOL;
            if (protocol == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return protocol;
    }
    
    @Override
    public OCClassSymbol getInterfaceOrProtocol() {
        return this;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCIdType ocIdType;
        try {
            ocIdType = new OCIdType((List<OCProtocolSymbol>)Collections.singletonList(this), Collections.emptyList(), this.getProject(), false, false);
            if (ocIdType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocIdType;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
