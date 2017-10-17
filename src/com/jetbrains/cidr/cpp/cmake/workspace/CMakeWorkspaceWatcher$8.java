// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import java.io.File;
import com.intellij.util.NotNullFunction;

class CMakeWorkspaceWatcher$8 implements NotNullFunction<File, FileStampFunctionAdapter> {
    FileDocumentManager myDocumentManager;
    final /* synthetic */ ProgressIndicator val$indicator;
    
    @NotNull
    public FileStampFunctionAdapter fun(final File file) {
        this.val$indicator.checkCanceled();
        final VirtualFile fileByIoFile = VfsUtil.findFileByIoFile(file, false);
        Label_0074: {
            FileStampFunctionAdapter fileStampFunctionAdapter = null;
            Label_0039: {
                try {
                    if (fileByIoFile != null) {
                        break Label_0074;
                    }
                    final NotNullFunction<File, FileStampFunctionAdapter> notNullFunction = (NotNullFunction<File, FileStampFunctionAdapter>)this;
                    final boolean b = true;
                    fileStampFunctionAdapter = new FileStampFunctionAdapter() {
                        @Nullable
                        @Override
                        public CMakeFile getCMakeFile(@NotNull final Project project) {
                            try {
                                if (project == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$1", "getCMakeFile"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return null;
                        }
                        
                        private static IllegalArgumentException a(final IllegalArgumentException ex) {
                            return ex;
                        }
                    };
                    if (fileStampFunctionAdapter == null) {
                        break Label_0039;
                    }
                    return fileStampFunctionAdapter;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final NotNullFunction<File, FileStampFunctionAdapter> notNullFunction = (NotNullFunction<File, FileStampFunctionAdapter>)this;
                    final boolean b = true;
                    fileStampFunctionAdapter = new FileStampFunctionAdapter() {
                        @Nullable
                        @Override
                        public CMakeFile getCMakeFile(@NotNull final Project project) {
                            try {
                                if (project == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$1", "getCMakeFile"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return null;
                        }
                        
                        private static IllegalArgumentException a(final IllegalArgumentException ex) {
                            return ex;
                        }
                    };
                    if (fileStampFunctionAdapter == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8", "fun"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return fileStampFunctionAdapter;
            try {
                if (this.myDocumentManager == null) {
                    this.myDocumentManager = FileDocumentManager.getInstance();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final Document cachedDocument = this.myDocumentManager.getCachedDocument(fileByIoFile);
        boolean b3 = false;
        Label_0139: {
            Label_0130: {
                try {
                    if (cachedDocument == null) {
                        break Label_0130;
                    }
                    final Document document = cachedDocument;
                    final Key<Boolean> key = CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT;
                    final Object o = document.getUserData((Key)key);
                    final Boolean b2 = Boolean.TRUE;
                    if (o == b2) {
                        break Label_0130;
                    }
                    break Label_0130;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final Document document = cachedDocument;
                    final Key<Boolean> key = CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT;
                    final Object o = document.getUserData((Key)key);
                    final Boolean b2 = Boolean.TRUE;
                    if (o == b2) {
                        b3 = true;
                        break Label_0139;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            b3 = false;
        }
        final boolean b4 = b3;
        try {
            if (cachedDocument != null) {
                cachedDocument.putUserData((Key)CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT, (Object)null);
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        boolean b5 = false;
        Label_0181: {
            try {
                if (!b4) {
                    b5 = true;
                    break Label_0181;
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
            b5 = false;
        }
        final FileStampFunctionAdapter fileStampFunctionAdapter2 = new FileStampFunctionAdapter(b5) {
            @Nullable
            @Override
            public CMakeFile getCMakeFile(@NotNull final Project project) {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$2", "getCMakeFile"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (b4) {
                        return FileStamp.createOrGetCMakeFile(project, cachedDocument);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return FileStamp.createOrGetCMakeFile(project, fileByIoFile);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        if (fileStampFunctionAdapter2 == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8", "fun"));
        }
        return fileStampFunctionAdapter2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}