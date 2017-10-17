// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.io.IOException;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.StateStorage;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModuleRootModel;
import org.jetbrains.annotations.Nls;
import com.intellij.openapi.roots.impl.storage.ClasspathStorageProvider;

public abstract class CidrClasspathStorageProvider implements ClasspathStorageProvider
{
    @Nls
    @Override
    public String getDescription() {
        return "";
    }
    
    @Override
    public void assertCompatible(final ModuleRootModel moduleRootModel) throws ConfigurationException {
    }
    
    @Override
    public void detach(@NotNull final Module module) {
        try {
            if (module == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "module", "com/jetbrains/cidr/project/CidrClasspathStorageProvider", "detach"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @NotNull
    @Override
    public ClasspathConverter createConverter(@NotNull final Module module) {
        try {
            if (module == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "module", "com/jetbrains/cidr/project/CidrClasspathStorageProvider", "createConverter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ClasspathConverter classpathConverter;
        try {
            classpathConverter = new ClasspathConverter() {
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
            };
            if (classpathConverter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/project/CidrClasspathStorageProvider", "createConverter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return classpathConverter;
    }
    
    @Override
    public String getContentRoot(@NotNull final ModuleRootModel moduleRootModel) {
        try {
            if (moduleRootModel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "model", "com/jetbrains/cidr/project/CidrClasspathStorageProvider", "getContentRoot"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    protected abstract void fillModel(@NotNull final ModifiableRootModel p0);
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
