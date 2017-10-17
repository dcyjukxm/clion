// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import java.io.File;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.jetbrains.cidr.lang.toolchains.CidrSwitchBuilder;
import com.intellij.openapi.util.Ref;
import java.io.IOException;
import com.intellij.openapi.util.ThrowableComputable;

static final class ClangTidyAnnotator$1 implements ThrowableComputable<Void, IOException> {
    final /* synthetic */ ClangTidyAnnotatorInfo val$info;
    final /* synthetic */ Ref val$compilerExecutableRef;
    final /* synthetic */ CidrSwitchBuilder val$compilerOptions;
    final /* synthetic */ Ref val$preprocessorDefinesRef;
    
    public Void compute() throws IOException {
        try {
            if (this.val$info.getProject().isDisposed()) {
                throw new ProcessCanceledException();
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        final OCResolveConfiguration configuration = this.val$info.getConfiguration();
        final OCCompilerSettings compilerSettings = configuration.getCompilerSettings();
        final OCLanguageKind languageKind = this.val$info.getLanguageKind();
        final VirtualFile rootVirtualFile = this.val$info.getRootVirtualFile();
        final OCCompilerKind compiler = compilerSettings.getCompiler(languageKind);
        final File compilerExecutable = compilerSettings.getCompilerExecutable(languageKind);
        try {
            if (compilerExecutable == null) {
                throw new IllegalStateException("Compiler is not found");
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        Label_0132: {
            try {
                if (compiler == OCCompilerKind.MSVC) {
                    this.val$compilerExecutableRef.set((Object)"clang++");
                    break Label_0132;
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            this.val$compilerExecutableRef.set((Object)compilerExecutable.getAbsolutePath());
        }
        ClangTidyAnnotator.access$000(compilerSettings, languageKind, rootVirtualFile, this.val$compilerOptions);
        ClangTidyAnnotator.access$100(this.val$info.getHeaderRoots(), rootVirtualFile, this.val$compilerOptions);
        this.val$preprocessorDefinesRef.set((Object)configuration.getPreprocessorDefines(languageKind, rootVirtualFile));
        return null;
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}