// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.codeInspection.ex.InspectionProfileImpl;
import com.intellij.psi.PsiElement;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.InspectionManagerBase;
import com.intellij.profile.codeInspection.ProjectInspectionProfileManager;
import com.jetbrains.cidr.lang.inspections.ClangTidyInspection;
import java.util.Iterator;
import com.jetbrains.cidr.lang.workspace.headerRoots.FrameworksSearchRoot;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.function.BiFunction;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import com.intellij.openapi.application.Application;
import java.io.Writer;
import java.io.FileWriter;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.toolchains.CidrSwitchBuilder;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lang.annotation.AnnotationHolder;
import java.util.List;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.Collections;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.configurations.GeneralCommandLine;
import java.io.File;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.PathUtil;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.error.YAMLException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.diagnostic.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import com.intellij.openapi.util.Key;
import com.intellij.lang.annotation.ExternalAnnotator;

public class ClangTidyAnnotator extends ExternalAnnotator<ClangTidyAnnotatorInfo, ClangTidyAnnotationHolder>
{
    public static final Key<Boolean> RUN_IN_UNIT_TEST_MODE;
    public static final Key<Boolean> COLLECT_DEBUG_INFORMATION;
    public static final Key<String> DEBUG_INFORMATION;
    public static final Key<AtomicBoolean> HAS_DOCUMENT_LISTENER;
    private static final Logger LOG;
    private static final ClangTidyDocumentListener DOCUMENT_LISTENER;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Nullable
    public ClangTidyAnnotatorInfo collectInformation(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final boolean b) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "collectInformation"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "collectInformation"));
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        return this.collectInformation(psiFile);
    }
    
    @Nullable
    public ClangTidyAnnotatorInfo collectInformation(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "collectInformation"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        final Project project = psiFile.getProject();
        Label_0084: {
            try {
                if (!ApplicationManager.getApplication().isUnitTestMode()) {
                    break Label_0084;
                }
                final Project project2 = project;
                final Key<Boolean> key = ClangTidyAnnotator.RUN_IN_UNIT_TEST_MODE;
                final Object o = project2.getUserData((Key)key);
                final Boolean b = Boolean.TRUE;
                if (o != b) {
                    break Label_0084;
                }
                break Label_0084;
            }
            catch (YAMLException ex2) {
                throw b((Exception)ex2);
            }
            try {
                final Project project2 = project;
                final Key<Boolean> key = ClangTidyAnnotator.RUN_IN_UNIT_TEST_MODE;
                final Object o = project2.getUserData((Key)key);
                final Boolean b = Boolean.TRUE;
                if (o != b) {
                    return null;
                }
            }
            catch (YAMLException ex3) {
                throw b((Exception)ex3);
            }
        }
        final VirtualFile virtualFile = psiFile.getVirtualFile();
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (YAMLException ex4) {
            throw b((Exception)ex4);
        }
        final String fileName = PathUtil.getFileName(virtualFile.getPath());
        try {
            if (!(psiFile instanceof OCFile)) {
                return null;
            }
        }
        catch (YAMLException ex5) {
            throw b((Exception)ex5);
        }
        final OCFile ocFile = (OCFile)psiFile;
        try {
            if (!ocFile.isInProjectSources()) {
                return null;
            }
        }
        catch (YAMLException ex6) {
            throw b((Exception)ex6);
        }
        final OCResolveRootAndConfiguration resolveRootAndActiveConfiguration = OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile, project);
        final OCResolveConfiguration configuration = resolveRootAndActiveConfiguration.getConfiguration();
        final VirtualFile rootFile = resolveRootAndActiveConfiguration.getRootFile();
        Label_0189: {
            try {
                if (configuration == null) {
                    break Label_0189;
                }
                final VirtualFile virtualFile2 = rootFile;
                if (virtualFile2 == null) {
                    break Label_0189;
                }
                break Label_0189;
            }
            catch (YAMLException ex7) {
                throw b((Exception)ex7);
            }
            try {
                final VirtualFile virtualFile2 = rootFile;
                if (virtualFile2 == null) {
                    return null;
                }
            }
            catch (YAMLException ex8) {
                throw b((Exception)ex8);
            }
        }
        final HeaderRoots libraryHeadersRoots = resolveRootAndActiveConfiguration.getLibraryHeadersRoots();
        final Document document = PsiDocumentManager.getInstance(project).getDocument(psiFile);
        try {
            if (document == null) {
                return null;
            }
        }
        catch (YAMLException ex9) {
            throw b((Exception)ex9);
        }
        final String a = a(project, psiFile);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (YAMLException ex10) {
            throw b((Exception)ex10);
        }
        return new ClangTidyAnnotatorInfo(fileName, project, ocFile, configuration, rootFile, libraryHeadersRoots, document, a);
    }
    
    @Nullable
    public ClangTidyAnnotationHolder doAnnotate(@Nullable final ClangTidyAnnotatorInfo clangTidyAnnotatorInfo) {
        try {
            if (clangTidyAnnotatorInfo == null) {
                return null;
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        a(clangTidyAnnotatorInfo);
        File tempDirectory = null;
        try {
            tempDirectory = FileUtil.createTempDirectory("clion-clang-tidy", (String)null, true);
            return a(clangTidyAnnotatorInfo, tempDirectory);
        }
        catch (IOException ex4) {}
        catch (ExecutionException ex5) {}
        catch (YAMLException ex2) {
            ClangTidyAnnotator.LOG.warn((Throwable)ex2);
            return ClangTidyAnnotationHolder.error(((Throwable)ex2).getMessage());
        }
        finally {
            try {
                if (tempDirectory != null) {
                    FileUtil.delete(tempDirectory);
                }
            }
            catch (IOException ex3) {
                throw b(ex3);
            }
        }
    }
    
    public static boolean isAvailable() {
        return a(getBuiltinClangTidyPath());
    }
    
    private static ClangTidyAnnotationHolder a(@NotNull final ClangTidyAnnotatorInfo clangTidyAnnotatorInfo, @NotNull final File file) throws IOException, ExecutionException {
        try {
            if (clangTidyAnnotatorInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "executeClangTidy"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workingDirectory", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "executeClangTidy"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        boolean b = false;
        Label_0115: {
            try {
                if (clangTidyAnnotatorInfo.getFile().getUserData((Key)ClangTidyAnnotator.COLLECT_DEBUG_INFORMATION) == Boolean.TRUE) {
                    b = true;
                    break Label_0115;
                }
            }
            catch (IOException ex3) {
                throw b(ex3);
            }
            b = false;
        }
        final boolean b2 = b;
        final StringBuilder sb = new StringBuilder();
        final File b3 = b();
        try {
            if (!a(b3)) {
                return ClangTidyAnnotationHolder.error("Clang-Tidy is not found or cannot be executed");
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        Label_0177: {
            Label_0165: {
                try {
                    if (ClangTidyAnnotator.$assertionsDisabled) {
                        break Label_0177;
                    }
                    final File file2 = b3;
                    if (file2 == null) {
                        break Label_0165;
                    }
                    break Label_0177;
                }
                catch (IOException ex5) {
                    throw b(ex5);
                }
                try {
                    final File file2 = b3;
                    if (file2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IOException ex6) {
                    throw b(ex6);
                }
            }
            try {
                if (b2) {
                    a(sb, "Clang-Tidy Path", b3.getAbsolutePath());
                }
            }
            catch (IOException ex7) {
                throw b(ex7);
            }
        }
        final Document document = clangTidyAnnotatorInfo.getDocument();
        final long modificationStamp = document.getModificationStamp();
        final File b4 = b(clangTidyAnnotatorInfo, file);
        final File file3 = new File(file, "output.yaml");
        final File a = a(clangTidyAnnotatorInfo, file, b4);
        try {
            if (b2) {
                a(sb, "Compilation Database", FileUtil.loadFile(a));
            }
        }
        catch (IOException ex8) {
            throw b(ex8);
        }
        final GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        generalCommandLine.setExePath(b3.getAbsolutePath());
        generalCommandLine.addParameter("-checks=" + clangTidyAnnotatorInfo.getClangTidyChecks());
        generalCommandLine.addParameter("-p=" + file);
        generalCommandLine.addParameter("-export-fixes=" + file3);
        generalCommandLine.addParameter(b4.getAbsolutePath());
        final CapturingProcessHandler capturingProcessHandler = new CapturingProcessHandler(generalCommandLine);
        final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
        try {
            if (modificationStamp != document.getModificationStamp()) {
                return null;
            }
        }
        catch (IOException ex9) {
            throw b(ex9);
        }
        Label_0488: {
            StringBuilder sb2 = null;
            String s = null;
            String loadFile = null;
            Label_0469: {
                Label_0455: {
                    Label_0433: {
                        try {
                            if (progressIndicator != null) {
                                capturingProcessHandler.runProcessWithProgressIndicator(progressIndicator);
                                break Label_0433;
                            }
                        }
                        catch (IOException ex10) {
                            throw b(ex10);
                        }
                        capturingProcessHandler.runProcess();
                        try {
                            if (!b2) {
                                break Label_0488;
                            }
                            sb2 = sb;
                            s = "Output";
                            final File file4 = file3;
                            final boolean b5 = file4.exists();
                            if (b5) {
                                break Label_0455;
                            }
                            break Label_0455;
                        }
                        catch (IOException ex11) {
                            throw b(ex11);
                        }
                    }
                    try {
                        sb2 = sb;
                        s = "Output";
                        final File file4 = file3;
                        final boolean b5 = file4.exists();
                        if (b5) {
                            loadFile = FileUtil.loadFile(file3);
                            break Label_0469;
                        }
                    }
                    catch (IOException ex12) {
                        throw b(ex12);
                    }
                }
                loadFile = "";
            }
            a(sb2, s, loadFile);
            clangTidyAnnotatorInfo.getFile().putUserData((Key)ClangTidyAnnotator.DEBUG_INFORMATION, (Object)sb.toString());
            try {
                if (file3.exists()) {
                    final List<ClangTidyDiagnostic> list = a(file3, clangTidyAnnotatorInfo.getDocument());
                    return ClangTidyAnnotationHolder.success(b4, list);
                }
            }
            catch (IOException ex13) {
                throw b(ex13);
            }
        }
        final List<ClangTidyDiagnostic> list = Collections.emptyList();
        return ClangTidyAnnotationHolder.success(b4, list);
    }
    
    public void apply(@NotNull final PsiFile psiFile, @Nullable final ClangTidyAnnotationHolder clangTidyAnnotationHolder, @NotNull final AnnotationHolder annotationHolder) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "apply"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        try {
            if (annotationHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "apply"));
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        final Document document = PsiDocumentManager.getInstance(psiFile.getProject()).getDocument(psiFile);
        Label_0124: {
            try {
                if (clangTidyAnnotationHolder == null) {
                    return;
                }
                final Document document2 = document;
                if (document2 == null) {
                    return;
                }
                break Label_0124;
            }
            catch (YAMLException ex3) {
                throw b((Exception)ex3);
            }
            try {
                final Document document2 = document;
                if (document2 == null) {
                    return;
                }
            }
            catch (YAMLException ex4) {
                throw b((Exception)ex4);
            }
        }
        final ClangTidyAnnotationApplier clangTidyAnnotationApplier = new ClangTidyAnnotationApplier();
        try {
            if (clangTidyAnnotationHolder.isSuccess()) {
                clangTidyAnnotationApplier.applySuccess(psiFile, document, clangTidyAnnotationHolder.getSourceFile(), clangTidyAnnotationHolder.getDiagnostics(), annotationHolder);
                return;
            }
        }
        catch (YAMLException ex5) {
            throw b((Exception)ex5);
        }
        HighlightSeverity highlightSeverity = null;
        Label_0184: {
            try {
                if (clangTidyAnnotationHolder.isError()) {
                    highlightSeverity = HighlightSeverity.ERROR;
                    break Label_0184;
                }
            }
            catch (YAMLException ex6) {
                throw b((Exception)ex6);
            }
            highlightSeverity = HighlightSeverity.WARNING;
        }
        clangTidyAnnotationApplier.applyFail(psiFile, clangTidyAnnotationHolder.getFailReason(), highlightSeverity, annotationHolder);
    }
    
    @NotNull
    private static File a(@NotNull final ClangTidyAnnotatorInfo clangTidyAnnotatorInfo, @NotNull final File file, @NotNull final File file2) throws IOException {
        try {
            if (clangTidyAnnotatorInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createCompilationDatabase"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workingDirectory", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createCompilationDatabase"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (file2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inputFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createCompilationDatabase"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        final CidrSwitchBuilder cidrSwitchBuilder = new CidrSwitchBuilder();
        final Application application = ApplicationManager.getApplication();
        final Ref create = Ref.create();
        final Ref create2 = Ref.create();
        application.runReadAction((ThrowableComputable)new ThrowableComputable<Void, IOException>() {
            public Void compute() throws IOException {
                try {
                    if (clangTidyAnnotatorInfo.getProject().isDisposed()) {
                        throw new ProcessCanceledException();
                    }
                }
                catch (IOException ex) {
                    throw a(ex);
                }
                final OCResolveConfiguration configuration = clangTidyAnnotatorInfo.getConfiguration();
                final OCCompilerSettings compilerSettings = configuration.getCompilerSettings();
                final OCLanguageKind languageKind = clangTidyAnnotatorInfo.getLanguageKind();
                final VirtualFile rootVirtualFile = clangTidyAnnotatorInfo.getRootVirtualFile();
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
                            create.set((Object)"clang++");
                            break Label_0132;
                        }
                    }
                    catch (IOException ex3) {
                        throw a(ex3);
                    }
                    create.set((Object)compilerExecutable.getAbsolutePath());
                }
                a(compilerSettings, languageKind, rootVirtualFile, cidrSwitchBuilder);
                a(clangTidyAnnotatorInfo.getHeaderRoots(), rootVirtualFile, cidrSwitchBuilder);
                create2.set((Object)configuration.getPreprocessorDefines(languageKind, rootVirtualFile));
                return null;
            }
            
            private static IOException a(final IOException ex) {
                return ex;
            }
        });
        cidrSwitchBuilder.addSingleRaw("-ferror-limit=0");
        if (!create2.isNull()) {
            final File file3 = new File(file, "macros");
            FileUtil.writeToFile(file3, (String)create2.get());
            cidrSwitchBuilder.addSingleRaw("-imacros");
            cidrSwitchBuilder.addSingleRaw(file3.getAbsolutePath());
        }
        final File file4 = new File(file, "compile_commands.json");
        final FileWriter fileWriter = new FileWriter(file4);
        File file5;
        try {
            ClangTidyUtil.writeCompilationDatabase(fileWriter, (String)create.get(), file.getAbsolutePath(), cidrSwitchBuilder.getArgs(), file2.getAbsolutePath());
            file5 = file4;
            if (file5 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createCompilationDatabase"));
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        return file5;
    }
    
    private static void a(@NotNull final OCCompilerSettings ocCompilerSettings, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final VirtualFile virtualFile, @NotNull final CidrSwitchBuilder cidrSwitchBuilder) throws IOException {
        try {
            if (ocCompilerSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerSettings", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addCompilerSwitches"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addCompilerSwitches"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addCompilerSwitches"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        try {
            if (cidrSwitchBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "switchBuilder", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addCompilerSwitches"));
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        final CidrCompilerSwitches compilerSwitches = ocCompilerSettings.getCompilerSwitches(ocLanguageKind, virtualFile);
        final File compilerWorkingDir = ocCompilerSettings.getCompilerWorkingDir();
        final OCCompilerKind compiler = ocCompilerSettings.getCompiler(ocLanguageKind);
        CidrCompilerSwitches.Format format = null;
        Label_0224: {
            try {
                if (compiler == OCCompilerKind.MSVC) {
                    format = CidrCompilerSwitches.Format.MSVC_RESPONSE_FILE;
                    break Label_0224;
                }
            }
            catch (IOException ex5) {
                throw b(ex5);
            }
            format = CidrCompilerSwitches.Format.GCC_RESPONSE_FILE;
        }
        cidrSwitchBuilder.addAll(a(compilerSwitches.expandResponseFiles(compilerWorkingDir, ocCompilerSettings.getEnvironment(), format)));
    }
    
    private static CidrCompilerSwitches a(@NotNull final CidrCompilerSwitches cidrCompilerSwitches) {
        try {
            if (cidrCompilerSwitches == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerSwitches", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "filterCompilerSwitches"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        return cidrCompilerSwitches.filterOptions(new BiFunction<String, String, Boolean>() {
            private boolean skipOptionValue = false;
            
            @Override
            public Boolean apply(final String s, final String s2) {
                if (this.skipOptionValue) {
                    this.skipOptionValue = false;
                    return false;
                }
                if (s.equals("-F") || s.equals("-I") || s.equals("-iquote")) {
                    this.skipOptionValue = true;
                    return false;
                }
                if (s.startsWith("-F") || s.startsWith("-I") || s.startsWith("-iquote")) {
                    return false;
                }
                return true;
            }
        });
    }
    
    private static void a(@NotNull final HeaderRoots headerRoots, @NotNull final VirtualFile virtualFile, @NotNull final CidrSwitchBuilder cidrSwitchBuilder) {
        try {
            if (headerRoots == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerRoots", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addIncludeDirectories"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addIncludeDirectories"));
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (cidrSwitchBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "switchBuilder", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "addIncludeDirectories"));
            }
        }
        catch (YAMLException ex3) {
            throw b((Exception)ex3);
        }
        final VirtualFile parent = virtualFile.getParent();
        Label_0155: {
            try {
                if (parent == null) {
                    break Label_0155;
                }
                final VirtualFile virtualFile2 = parent;
                final boolean b = virtualFile2.isDirectory();
                if (b) {
                    break Label_0155;
                }
                break Label_0155;
            }
            catch (YAMLException ex4) {
                throw b((Exception)ex4);
            }
            try {
                final VirtualFile virtualFile2 = parent;
                final boolean b = virtualFile2.isDirectory();
                if (b) {
                    cidrSwitchBuilder.addSingleRaw("-iquote");
                    cidrSwitchBuilder.addSingleRaw(parent.getPath());
                }
            }
            catch (YAMLException ex5) {
                throw b((Exception)ex5);
            }
        }
        for (final HeadersSearchRoot headersSearchRoot : headerRoots.getRoots()) {
            String s = null;
            Label_0248: {
                try {
                    if (headersSearchRoot.isUserHeaders()) {
                        s = "-iquote";
                        break Label_0248;
                    }
                }
                catch (YAMLException ex6) {
                    throw b((Exception)ex6);
                }
                try {
                    if (headersSearchRoot instanceof FrameworksSearchRoot) {
                        s = "-F";
                        break Label_0248;
                    }
                }
                catch (YAMLException ex7) {
                    throw b((Exception)ex7);
                }
                s = "-I";
            }
            cidrSwitchBuilder.addSingleRaw(s);
            cidrSwitchBuilder.addSingleRaw(headersSearchRoot.getVirtualFile().getPath());
        }
    }
    
    @Nullable
    private static String a(@NotNull final Project project, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "getClangTidyChecks"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "getClangTidyChecks"));
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        final ClangTidyInspection b = b(project, psiFile);
        try {
            if (b != null) {
                return b.getClangTidyChecks();
            }
        }
        catch (YAMLException ex3) {
            throw b((Exception)ex3);
        }
        return null;
    }
    
    @Nullable
    private static ClangTidyInspection b(@NotNull final Project project, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "getPairedInspection"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "getPairedInspection"));
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        final InspectionProfileImpl profile = ProjectInspectionProfileManager.getInstance(project).getProfile(((InspectionManagerBase)InspectionManager.getInstance(project)).getCurrentProfile());
        try {
            if (profile == null) {
                return null;
            }
        }
        catch (YAMLException ex3) {
            throw b((Exception)ex3);
        }
        final InspectionToolWrapper toolById = profile.getToolById("ClangTidyInspection", (PsiElement)psiFile);
        Label_0155: {
            try {
                if (toolById == null) {
                    break Label_0155;
                }
                final InspectionToolWrapper inspectionToolWrapper = toolById;
                final InspectionProfileEntry inspectionProfileEntry = inspectionToolWrapper.getTool();
                final boolean b = inspectionProfileEntry instanceof ClangTidyInspection;
                if (!b) {
                    break Label_0155;
                }
                return (ClangTidyInspection)toolById.getTool();
            }
            catch (YAMLException ex4) {
                throw b((Exception)ex4);
            }
            try {
                final InspectionToolWrapper inspectionToolWrapper = toolById;
                final InspectionProfileEntry inspectionProfileEntry = inspectionToolWrapper.getTool();
                final boolean b = inspectionProfileEntry instanceof ClangTidyInspection;
                if (!b) {
                    return null;
                }
            }
            catch (YAMLException ex5) {
                throw b((Exception)ex5);
            }
        }
        return (ClangTidyInspection)toolById.getTool();
    }
    
    @NotNull
    private static File b(@NotNull final ClangTidyAnnotatorInfo clangTidyAnnotatorInfo, @NotNull final File file) throws IOException {
        try {
            if (clangTidyAnnotatorInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createInputFile"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workingDirectory", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createInputFile"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        final File file2 = new File(file, clangTidyAnnotatorInfo.getInputFileName());
        File file3;
        try {
            FileUtil.writeToFile(file2, clangTidyAnnotatorInfo.getDocument().getText());
            file3 = file2;
            if (file3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "createInputFile"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        return file3;
    }
    
    @Nullable
    private static File b() {
        File builtinClangTidyPath = (File)ApplicationManager.getApplication().runReadAction(() -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySettings.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySettings;
            //     3: astore_0       
            //     4: aload_0        
            //     5: ifnull          54
            //     8: aload_0        
            //     9: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySettings.getUseExternalClangTidy:()Z
            //    12: ifeq            54
            //    15: goto            22
            //    18: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    21: athrow         
            //    22: aload_0        
            //    23: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySettings.getExternalClangTidyPath:()Ljava/lang/String;
            //    26: ifnull          54
            //    29: goto            36
            //    32: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    35: athrow         
            //    36: new             Ljava/io/File;
            //    39: dup            
            //    40: aload_0        
            //    41: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidySettings.getExternalClangTidyPath:()Ljava/lang/String;
            //    44: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
            //    47: goto            55
            //    50: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    53: athrow         
            //    54: aconst_null    
            //    55: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                    
            //  -----  -----  -----  -----  ----------------------------------------
            //  4      15     18     22     Lorg/yaml/snakeyaml/error/YAMLException;
            //  8      29     32     36     Lorg/yaml/snakeyaml/error/YAMLException;
            //  22     50     50     54     Lorg/yaml/snakeyaml/error/YAMLException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        });
        if (builtinClangTidyPath == null) {
            builtinClangTidyPath = getBuiltinClangTidyPath();
        }
        return builtinClangTidyPath;
    }
    
    @Nullable
    public static File getBuiltinClangTidyPath() {
        String s = null;
        Label_0017: {
            try {
                if (SystemInfo.isWindows) {
                    s = "clang-tidy.exe";
                    break Label_0017;
                }
            }
            catch (YAMLException ex) {
                throw b((Exception)ex);
            }
            s = "clang-tidy";
        }
        final String s2 = s;
        if (ApplicationImpl.isRunningFromSources()) {
            String s3 = null;
            if (SystemInfo.isWindows) {
                s3 = "win";
            }
            else if (SystemInfo.isLinux) {
                s3 = "linux";
            }
            else if (SystemInfo.isMac) {
                s3 = "mac";
            }
            try {
                if (s3 == null) {
                    return null;
                }
            }
            catch (YAMLException ex2) {
                throw b((Exception)ex2);
            }
            return new File(PathManager.getHomePath(), "CIDR/clion/bin/clang/" + s3 + "/" + s2);
        }
        return new File(PathManager.getBinPath(), "clang/" + s2);
    }
    
    private static boolean a(@Nullable final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          40
        //     4: aload_0        
        //     5: invokevirtual   java/io/File.exists:()Z
        //     8: ifeq            40
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    17: athrow         
        //    18: aload_0        
        //    19: invokevirtual   java/io/File.canExecute:()Z
        //    22: ifeq            40
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    31: athrow         
        //    32: iconst_1       
        //    33: goto            41
        //    36: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    39: athrow         
        //    40: iconst_0       
        //    41: ireturn        
        //    42: astore_1       
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  18     36     36     40     Ljava/lang/SecurityException;
        //  4      25     28     32     Ljava/lang/SecurityException;
        //  0      11     14     18     Ljava/lang/SecurityException;
        //  0      41     42     45     Ljava/lang/SecurityException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void a(@NotNull final StringBuilder sb, @NotNull final String s, @NotNull final String s2) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "putDebugInformation"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "informationHeader", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "putDebugInformation"));
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "content", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "putDebugInformation"));
            }
        }
        catch (YAMLException ex3) {
            throw b((Exception)ex3);
        }
        sb.append("=== ").append(s).append(" ===").append("\n");
        sb.append(s2).append("\n\n");
    }
    
    @NotNull
    private static List<ClangTidyDiagnostic> a(@NotNull final File file, @NotNull final Document document) throws IOException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "loadDiagnosticsFromYaml"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "loadDiagnosticsFromYaml"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        final String loadFile = FileUtil.loadFile(file);
        final ClangTidyYamlLoader clangTidyYamlLoader = new ClangTidyYamlLoader();
        List<ClangTidyDiagnostic> load;
        try {
            load = clangTidyYamlLoader.load(loadFile, document);
            if (load == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "loadDiagnosticsFromYaml"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        return load;
    }
    
    private static void a(@NotNull final ClangTidyAnnotatorInfo clangTidyAnnotatorInfo) {
        try {
            if (clangTidyAnnotatorInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotator", "tryRegisterDocumentListener"));
            }
        }
        catch (YAMLException ex) {
            throw b((Exception)ex);
        }
        final Document document = clangTidyAnnotatorInfo.getDocument();
        try {
            if (!(document instanceof UserDataHolderEx)) {
                return;
            }
        }
        catch (YAMLException ex2) {
            throw b((Exception)ex2);
        }
        final UserDataHolderEx userDataHolderEx = (UserDataHolderEx)document;
        AtomicBoolean atomicBoolean = (AtomicBoolean)userDataHolderEx.getUserData((Key)ClangTidyAnnotator.HAS_DOCUMENT_LISTENER);
        if (atomicBoolean == null) {
            atomicBoolean = new AtomicBoolean(false);
            try {
                if (userDataHolderEx.putUserDataIfAbsent((Key)ClangTidyAnnotator.HAS_DOCUMENT_LISTENER, (Object)atomicBoolean) != atomicBoolean) {
                    return;
                }
            }
            catch (YAMLException ex3) {
                throw b((Exception)ex3);
            }
        }
        try {
            if (atomicBoolean.compareAndSet(false, true)) {
                document.addDocumentListener((DocumentListener)ClangTidyAnnotator.DOCUMENT_LISTENER);
            }
        }
        catch (YAMLException ex4) {
            throw b((Exception)ex4);
        }
    }
    
    public String getPairedBatchInspectionShortName() {
        return "ClangTidyInspection";
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!ClangTidyAnnotator.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (YAMLException ex) {
                throw b((Exception)ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        RUN_IN_UNIT_TEST_MODE = Key.create("CLANG_TIDY_ANNOTATOR_RUN_IN_UNIT_TEST_MODE");
        COLLECT_DEBUG_INFORMATION = Key.create("CLANG_TIDY_COLLECT_DEBUG_INFORMATION");
        DEBUG_INFORMATION = Key.create("CLANG_TIDY_DEBUG_INFORMATION");
        HAS_DOCUMENT_LISTENER = Key.create("CLANG_TIDY_HAS_DOCUMENT_LISTENER");
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.daemon.clang.tidy.ClangTidyAnnotator");
        DOCUMENT_LISTENER = new ClangTidyDocumentListener();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
