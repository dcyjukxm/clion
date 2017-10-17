// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import java.util.Arrays;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.io.File;
import com.intellij.openapi.vfs.VirtualFile;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.projectImport.ProjectOpenProcessor;

public abstract class CidrProjectOpenProcessor extends ProjectOpenProcessor
{
    @NotNull
    private final String myName;
    @NotNull
    private final ProjectOpenHelper myHelper;
    
    protected CidrProjectOpenProcessor(@NotNull final String myName, @NotNull final ProjectOpenHelper myHelper) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/CidrProjectOpenProcessor", "<init>"));
        }
        if (myHelper == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "helper", "com/jetbrains/cidr/CidrProjectOpenProcessor", "<init>"));
        }
        this.myName = myName;
        this.myHelper = myHelper;
    }
    
    @NotNull
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/CidrProjectOpenProcessor", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    public Icon getIcon() {
        return null;
    }
    
    public boolean canOpenProject(final VirtualFile virtualFile) {
        return this.myHelper.isSupportedFile(virtualFile);
    }
    
    public boolean isStrongProjectInfoHolder() {
        return true;
    }
    
    public void refreshProjectFiles(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseDir", "com/jetbrains/cidr/CidrProjectOpenProcessor", "refreshProjectFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final File[] listFiles = file.listFiles((p0, s) -> this.myHelper.isSupportedFile(s));
        try {
            if (listFiles != null) {
                LocalFileSystem.getInstance().refreshIoFiles((Iterable)Arrays.asList(listFiles), false, true, (Runnable)null);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
