// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.cpp.toolchains.Cygwin;
import com.jetbrains.cidr.cpp.cmake.model.CMakeCacheFile;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.io.Serializable;
import java.io.File;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.ui.Messages;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGeneratorType;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.ProjectOpenHelper;
import java.util.List;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.CidrProjectOpenProcessor;

public class CMakeProjectOpenProcessor extends CidrProjectOpenProcessor
{
    private static final Key<OpenProjectSpec> DATA_KEY;
    public static final List<String> SUPPORTED_FILES;
    private static final ProjectOpenHelper.SupportedFileChecker CHECKER;
    private static final ProjectOpenHelper<OpenProjectSpec> ourProjectOpenHelper;
    
    public CMakeProjectOpenProcessor() {
        super("CMake Project", CMakeProjectOpenProcessor.ourProjectOpenHelper);
    }
    
    @NotNull
    public static ProjectOpenHelper<OpenProjectSpec> getHelper() {
        ProjectOpenHelper<OpenProjectSpec> ourProjectOpenHelper;
        try {
            ourProjectOpenHelper = CMakeProjectOpenProcessor.ourProjectOpenHelper;
            if (ourProjectOpenHelper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor", "getHelper"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return ourProjectOpenHelper;
    }
    
    @Override
    public boolean canOpenProject(VirtualFile supportedSubFile) {
        supportedSubFile = findSupportedSubFile(supportedSubFile);
        try {
            if (supportedSubFile == null) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return super.canOpenProject(supportedSubFile);
    }
    
    @Nullable
    public static VirtualFile findSupportedSubFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor", "findSupportedSubFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        if (virtualFile.isDirectory()) {
            final Iterator<String> iterator = CMakeProjectOpenProcessor.SUPPORTED_FILES.iterator();
            while (iterator.hasNext()) {
                final VirtualFile child = virtualFile.findChild((String)iterator.next());
                try {
                    if (child != null) {
                        return child;
                    }
                    continue;
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            return null;
        }
        try {
            if (CMakeProjectOpenProcessor.CHECKER.isSupportedFile(virtualFile)) {
                return virtualFile;
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Nullable
    public Project doOpenProject(@NotNull VirtualFile supportedSubFile, @Nullable final Project project, final boolean b) {
        try {
            if (supportedSubFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor", "doOpenProject"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        supportedSubFile = findSupportedSubFile(supportedSubFile);
        try {
            if (supportedSubFile == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        final Pair<VirtualFile, OpenProjectSpec> a = a(supportedSubFile);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        Label_0108: {
            try {
                if (ApplicationManager.getApplication().isHeadlessEnvironment()) {
                    return CMakeProjectOpenProcessor.ourProjectOpenHelper.openProject((VirtualFile)a.first, project, b, (OpenProjectSpec)a.second);
                }
                final Pair<VirtualFile, OpenProjectSpec> pair = a;
                final Object o = pair.second;
                final OpenProjectSpec openProjectSpec = (OpenProjectSpec)o;
                final File file = openProjectSpec.generationDir;
                if (file != null) {
                    break Label_0108;
                }
                return CMakeProjectOpenProcessor.ourProjectOpenHelper.openProject((VirtualFile)a.first, project, b, (OpenProjectSpec)a.second);
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            try {
                final Pair<VirtualFile, OpenProjectSpec> pair = a;
                final Object o = pair.second;
                final OpenProjectSpec openProjectSpec = (OpenProjectSpec)o;
                final File file = openProjectSpec.generationDir;
                if (file == null) {
                    return CMakeProjectOpenProcessor.ourProjectOpenHelper.openProject((VirtualFile)a.first, project, b, (OpenProjectSpec)a.second);
                }
                if (((OpenProjectSpec)a.second).isInSourceGeneration()) {
                    return CMakeProjectOpenProcessor.ourProjectOpenHelper.openProject((VirtualFile)a.first, project, b, (OpenProjectSpec)a.second);
                }
            }
            catch (IllegalStateException ex5) {
                throw b(ex5);
            }
        }
        String string = "";
        final String fullProductName = ApplicationNamesInfo.getInstance().getFullProductName();
        final CMakeGeneratorType fromGeneratorSpec = CMakeGeneratorType.fromGeneratorSpec(((OpenProjectSpec)a.second).generator);
        Label_0236: {
            try {
                if (fromGeneratorSpec != null) {
                    if (fromGeneratorSpec.isSupported()) {
                        break Label_0236;
                    }
                }
            }
            catch (IllegalStateException ex6) {
                throw b(ex6);
            }
            string = "\nThis project was generated using " + ((OpenProjectSpec)a.second).generator + " generator, which is not supported by " + fullProductName + ".\nIt will be regenerated using " + CMakeGenerator.getGeneratorSpec(CPPToolchains.getInstance().getToolSet(), false);
        }
        final String relativePath = FileUtil.getRelativePath(((OpenProjectSpec)a.second).generationDir, ((OpenProjectSpec)a.second).sourceDir);
        Label_0301: {
            StringBuilder append;
            try {
                append = new StringBuilder().append("Selected directory contains generated CMake files.\nDo you want to open the source directory ");
                if (relativePath == null) {
                    final Serializable sourceDir = ((OpenProjectSpec)a.second).sourceDir;
                    break Label_0301;
                }
            }
            catch (IllegalStateException ex7) {
                throw b(ex7);
            }
            final Serializable sourceDir = relativePath;
            try {
                if (Messages.showOkCancelDialog(project, append.append(sourceDir).append("?\n").append(string).toString(), IdeBundle.message("title.open.project", new Object[0]), IdeBundle.message("button.yes", new Object[0]), IdeBundle.message("button.cancel", new Object[0]), Messages.getQuestionIcon()) != 0) {
                    return null;
                }
            }
            catch (IllegalStateException ex8) {
                throw b(ex8);
            }
        }
        return CMakeProjectOpenProcessor.ourProjectOpenHelper.openProject((VirtualFile)a.first, project, b, (OpenProjectSpec)a.second);
    }
    
    @Nullable
    private static Pair<VirtualFile, OpenProjectSpec> a(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor", "createOpenProjectSpec"));
            }
        }
        catch (CMakeException ex) {
            throw b(ex);
        }
        final File virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile.getParent());
        final File file = new File(virtualToIoFile, "CMakeCache.txt");
        try {
            if (!file.exists()) {
                return (Pair<VirtualFile, OpenProjectSpec>)Pair.create((Object)virtualFile, (Object)new OpenProjectSpec(virtualToIoFile, null, null, null));
            }
        }
        catch (CMakeException ex2) {
            throw b(ex2);
        }
        try {
            final VirtualFile refreshAndFindFileByIoFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
            File file2 = null;
            String name = null;
            Label_0123: {
                try {
                    file2 = file;
                    if (refreshAndFindFileByIoFile == null) {
                        name = "UTF-8";
                        break Label_0123;
                    }
                }
                catch (CMakeException ex3) {
                    throw b(ex3);
                }
                name = refreshAndFindFileByIoFile.getCharset().name();
            }
            final CMakeCacheFile cMakeCacheFile = new CMakeCacheFile(file2, name);
            final String localPath = Cygwin.toLocalPath(cMakeCacheFile.getVariableValue("CMAKE_HOME_DIRECTORY"), CPPToolchains.getInstance().getCygwin());
            if (localPath != null) {
                final String variableValue = cMakeCacheFile.getVariableValue("CMAKE_BUILD_TYPE");
                final String variableValue2 = cMakeCacheFile.getVariableValue("CMAKE_GENERATOR");
                final VirtualFile refreshAndFindFileByIoFile2 = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(localPath + "/" + "CMakeLists.txt"));
                if (refreshAndFindFileByIoFile2 != null) {
                    return (Pair<VirtualFile, OpenProjectSpec>)Pair.create((Object)refreshAndFindFileByIoFile2, (Object)new OpenProjectSpec(new File(localPath), virtualToIoFile, variableValue, variableValue2));
                }
            }
        }
        catch (CMakeException ex4) {}
        return null;
    }
    
    static {
        DATA_KEY = Key.create("CMAKE_FILE_TO_OPEN_KEY");
        SUPPORTED_FILES = (List)ContainerUtil.immutableList((Object[])new String[] { "CMakeLists.txt", "CMakeCache.txt" });
        CHECKER = new ProjectOpenHelper.SupportedFileChecker() {
            @Override
            public boolean isSupportedFile(@NotNull final VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$1", "isSupportedFile"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return this.isSupportedFile(virtualFile.getName());
            }
            
            @Override
            public boolean isSupportedFile(@NotNull final String s) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileName", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$1", "isSupportedFile"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                for (final String s2 : CMakeProjectOpenProcessor.SUPPORTED_FILES) {
                    try {
                        if (FileUtil.namesEqual(s2, s)) {
                            return true;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        ourProjectOpenHelper = new ProjectOpenHelper<OpenProjectSpec>(CMakeProjectOpenProcessor.DATA_KEY, CMakeProjectOpenProcessor.CHECKER);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public static class OpenProjectSpec
    {
        @NotNull
        public final File sourceDir;
        @Nullable
        public final File generationDir;
        @Nullable
        public final String configuration;
        @Nullable
        public final String generator;
        
        public OpenProjectSpec(@NotNull final File sourceDir, @Nullable final File generationDir, @Nullable final String configuration, @Nullable final String generator) {
            if (sourceDir == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceDir", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec", "<init>"));
            }
            this.sourceDir = sourceDir;
            this.generationDir = generationDir;
            this.configuration = configuration;
            this.generator = generator;
        }
        
        public boolean isInSourceGeneration() {
            return FileUtil.filesEqual(this.sourceDir, this.generationDir);
        }
    }
}
