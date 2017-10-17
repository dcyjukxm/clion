// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.io.IOException;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.StateStorage;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;
import com.intellij.openapi.roots.impl.storage.ClasspathStorageProvider;

class CidrClasspathStorageProvider$1 implements ClasspathConverter {
    @NotNull
    @Override
    public List<String> getFilePaths() {
        List<String> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/project/CidrClasspathStorageProvider$1", "getFilePaths"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return emptyList;
    }
    
    @NotNull
    @Override
    public StateStorage.ExternalizationSession startExternalization() {
        StateStorage.ExternalizationSession externalizationSession;
        try {
            externalizationSession = new StateStorage.ExternalizationSession() {
                @Nullable
                public StateStorage.SaveSession createSaveSession() {
                    return null;
                }
            };
            if (externalizationSession == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/project/CidrClasspathStorageProvider$1", "startExternalization"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return (StateStorage.ExternalizationSession)externalizationSession;
    }
    
    @Override
    public void readClasspath(@NotNull final ModifiableRootModel modifiableRootModel) throws IOException {
        try {
            if (modifiableRootModel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "model", "com/jetbrains/cidr/project/CidrClasspathStorageProvider$1", "readClasspath"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        CidrClasspathStorageProvider.this.fillModel(modifiableRootModel);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}