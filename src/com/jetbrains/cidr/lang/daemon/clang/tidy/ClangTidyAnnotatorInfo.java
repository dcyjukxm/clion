// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ClangTidyAnnotatorInfo
{
    @NotNull
    private final String myInputFileName;
    @NotNull
    private final Project myProject;
    @NotNull
    private final OCFile myFile;
    @NotNull
    private final OCResolveConfiguration myConfiguration;
    @NotNull
    private final VirtualFile myRootVirtualFile;
    @NotNull
    private final HeaderRoots myHeaderRoots;
    @NotNull
    private final OCLanguageKind myLanguageKind;
    @NotNull
    private final Document myDocument;
    @NotNull
    private final String myClangTidyChecks;
    
    public ClangTidyAnnotatorInfo(@NotNull final String myInputFileName, @NotNull final Project myProject, @NotNull final OCFile myFile, @NotNull final OCResolveConfiguration myConfiguration, @NotNull final VirtualFile myRootVirtualFile, @NotNull final HeaderRoots myHeaderRoots, @NotNull final Document myDocument, @NotNull final String myClangTidyChecks) {
        if (myInputFileName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inputFileName", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myRootVirtualFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootVirtualFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myHeaderRoots == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerRoots", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myDocument == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        if (myClangTidyChecks == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clangTidyChecks", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "<init>"));
        }
        this.myInputFileName = myInputFileName;
        this.myProject = myProject;
        this.myFile = myFile;
        this.myConfiguration = myConfiguration;
        this.myRootVirtualFile = myRootVirtualFile;
        this.myHeaderRoots = myHeaderRoots;
        this.myLanguageKind = this.myFile.getKind();
        this.myDocument = myDocument;
        this.myClangTidyChecks = myClangTidyChecks;
    }
    
    @NotNull
    public OCFile getFile() {
        OCFile myFile;
        try {
            myFile = this.myFile;
            if (myFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFile;
    }
    
    @NotNull
    public OCLanguageKind getLanguageKind() {
        OCLanguageKind myLanguageKind;
        try {
            myLanguageKind = this.myLanguageKind;
            if (myLanguageKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLanguageKind;
    }
    
    @NotNull
    public Project getProject() {
        Project myProject;
        try {
            myProject = this.myProject;
            if (myProject == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProject;
    }
    
    @NotNull
    public Document getDocument() {
        Document myDocument;
        try {
            myDocument = this.myDocument;
            if (myDocument == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getDocument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDocument;
    }
    
    @NotNull
    public String getClangTidyChecks() {
        String myClangTidyChecks;
        try {
            myClangTidyChecks = this.myClangTidyChecks;
            if (myClangTidyChecks == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getClangTidyChecks"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myClangTidyChecks;
    }
    
    @NotNull
    public String getInputFileName() {
        String myInputFileName;
        try {
            myInputFileName = this.myInputFileName;
            if (myInputFileName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getInputFileName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myInputFileName;
    }
    
    @NotNull
    public OCResolveConfiguration getConfiguration() {
        OCResolveConfiguration myConfiguration;
        try {
            myConfiguration = this.myConfiguration;
            if (myConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfiguration;
    }
    
    @NotNull
    public VirtualFile getRootVirtualFile() {
        VirtualFile myRootVirtualFile;
        try {
            myRootVirtualFile = this.myRootVirtualFile;
            if (myRootVirtualFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getRootVirtualFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRootVirtualFile;
    }
    
    @NotNull
    public HeaderRoots getHeaderRoots() {
        HeaderRoots myHeaderRoots;
        try {
            myHeaderRoots = this.myHeaderRoots;
            if (myHeaderRoots == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotatorInfo", "getHeaderRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myHeaderRoots;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
