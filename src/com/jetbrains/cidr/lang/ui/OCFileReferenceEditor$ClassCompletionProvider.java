// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.intellij.openapi.vfs.VirtualFile;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.ui.TextFieldWithAutoCompletionListProvider;

private static class ClassCompletionProvider extends TextFieldWithAutoCompletionListProvider<OCFile>
{
    protected ClassCompletionProvider(@Nullable final Collection<OCFile> collection) {
        super(collection);
    }
    
    @Override
    protected Icon getIcon(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCFileReferenceEditor$ClassCompletionProvider", "getIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocFile.getIcon(0);
    }
    
    @NotNull
    @Override
    protected String getLookupString(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCFileReferenceEditor$ClassCompletionProvider", "getLookupString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String name;
        try {
            name = ocFile.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFileReferenceEditor$ClassCompletionProvider", "getLookupString"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return name;
    }
    
    @Override
    protected String getTailText(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCFileReferenceEditor$ClassCompletionProvider", "getTailText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final VirtualFile virtualFile = ocFile.getVirtualFile();
        VirtualFile parent = null;
        Label_0067: {
            try {
                if (virtualFile != null) {
                    parent = virtualFile.getParent();
                    break Label_0067;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            parent = null;
        }
        final VirtualFile virtualFile2 = parent;
        try {
            if (virtualFile2 != null) {
                return virtualFile2.getPath();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Override
    protected String getTypeText(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCFileReferenceEditor$ClassCompletionProvider", "getTypeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    public int compare(final OCFile ocFile, final OCFile ocFile2) {
        return ocFile.getName().compareTo(ocFile2.getName());
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
