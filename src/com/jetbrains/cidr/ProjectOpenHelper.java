// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.platform.PlatformProjectOpenProcessor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Key;

public class ProjectOpenHelper<T>
{
    private final Key<T> myOpenDataKey;
    private final SupportedFileChecker mySupportedFileChecker;
    
    public ProjectOpenHelper(@NotNull final Key<T> myOpenDataKey, @NotNull final SupportedFileChecker mySupportedFileChecker) {
        if (myOpenDataKey == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "openDataKey", "com/jetbrains/cidr/ProjectOpenHelper", "<init>"));
        }
        if (mySupportedFileChecker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "supportedFileChecker", "com/jetbrains/cidr/ProjectOpenHelper", "<init>"));
        }
        this.myOpenDataKey = myOpenDataKey;
        this.mySupportedFileChecker = mySupportedFileChecker;
    }
    
    public boolean isSupportedFile(@Nullable final VirtualFile virtualFile) {
        Label_0024: {
            try {
                if (virtualFile == null) {
                    return false;
                }
                final ProjectOpenHelper projectOpenHelper = this;
                final SupportedFileChecker supportedFileChecker = projectOpenHelper.mySupportedFileChecker;
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = supportedFileChecker.isSupportedFile(virtualFile2);
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ProjectOpenHelper projectOpenHelper = this;
                final SupportedFileChecker supportedFileChecker = projectOpenHelper.mySupportedFileChecker;
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = supportedFileChecker.isSupportedFile(virtualFile2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isSupportedFile(@Nullable final String s) {
        Label_0024: {
            try {
                if (s == null) {
                    return false;
                }
                final ProjectOpenHelper projectOpenHelper = this;
                final SupportedFileChecker supportedFileChecker = projectOpenHelper.mySupportedFileChecker;
                final String s2 = s;
                final boolean b = supportedFileChecker.isSupportedFile(s2);
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ProjectOpenHelper projectOpenHelper = this;
                final SupportedFileChecker supportedFileChecker = projectOpenHelper.mySupportedFileChecker;
                final String s2 = s;
                final boolean b = supportedFileChecker.isSupportedFile(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public Project openProject(@NotNull final VirtualFile virtualFile, @Nullable final Project project, final boolean b, final T t) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/ProjectOpenHelper", "openProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.isSupportedFile(virtualFile)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.markFileToOpen(virtualFile, t);
        return PlatformProjectOpenProcessor.getInstance().doOpenProject(virtualFile.getParent(), project, b);
    }
    
    public void markFileToOpen(@NotNull final VirtualFile virtualFile, @NotNull final T t) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/ProjectOpenHelper", "markFileToOpen"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/ProjectOpenHelper", "markFileToOpen"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        virtualFile.getParent().putUserData((Key)this.myOpenDataKey, (Object)t);
    }
    
    @Nullable
    public T getAndClearFileToOpenData(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/ProjectOpenHelper", "getAndClearFileToOpenData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile baseDir = project.getBaseDir();
        try {
            if (baseDir == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Object userData = baseDir.getUserData((Key)this.myOpenDataKey);
        baseDir.putUserData((Key)this.myOpenDataKey, (Object)null);
        return (T)userData;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public interface SupportedFileChecker
    {
        boolean isSupportedFile(@NotNull final VirtualFile p0);
        
        boolean isSupportedFile(@NotNull final String p0);
    }
}
