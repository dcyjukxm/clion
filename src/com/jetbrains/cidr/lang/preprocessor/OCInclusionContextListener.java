// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;

public interface OCInclusionContextListener
{
    public static final Topic<OCInclusionContextListener> TOPIC = Topic.create("Inclusion context notifications", (Class)OCInclusionContextListener.class);
    
    default void resolveRootAndActiveConfigurationChanged(@NotNull final VirtualFile virtualFile, @NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextListener", "resolveRootAndActiveConfigurationChanged"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootAndConfiguration", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextListener", "resolveRootAndActiveConfigurationChanged"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
