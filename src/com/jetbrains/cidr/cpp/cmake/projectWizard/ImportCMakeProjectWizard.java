// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import org.jetbrains.annotations.Nullable;
import java.awt.Component;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.ide.impl.ProjectUtil;
import javax.swing.Icon;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.project.Project;
import java.io.IOException;
import com.jetbrains.cidr.cpp.CPPLog;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;

public class ImportCMakeProjectWizard extends CMakeProjectWizard
{
    private static final String LAST_IMPORTED_LOCATION = "last.imported.location";
    private final ImportCMakeProjectStepAdapter myStep;
    private final VirtualFile myImportDir;
    
    private ImportCMakeProjectWizard(@NotNull final VirtualFile myImportDir) {
        if (myImportDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "importDir", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectWizard", "<init>"));
        }
        super("Import CMake Project", "ImportCMakeProject");
        this.myStep = new ImportCMakeProjectStepAdapter();
        this.myImportDir = myImportDir;
        this.initWithStep(this.myStep);
    }
    
    @Override
    protected boolean tryFinish() {
        try {
            this.myStep.createProject();
            return true;
        }
        catch (IOException ex) {
            CPPLog.LOG.warn((Throwable)ex);
            this.setErrorText("Cannot create CMake project");
            this.getNextButton().setEnabled(false);
            return false;
        }
    }
    
    public static void startRunWizard() {
        final VirtualFile a = a();
        if (a != null) {
            final VirtualFile child = a.findChild("CMakeLists.txt");
            if (child != null) {
                final int showYesNoCancelDialog = Messages.showYesNoCancelDialog((Project)null, "Directory " + a.getPresentableName() + " contains " + "CMakeLists.txt", "Import Project", "Open Project", "Overwrite CMakeLists.txt", Messages.CANCEL_BUTTON, (Icon)null);
                Label_0103: {
                    Label_0085: {
                        try {
                            if (showYesNoCancelDialog == 1) {
                                break Label_0103;
                            }
                            final int n = showYesNoCancelDialog;
                            if (n == 0) {
                                break Label_0085;
                            }
                            return;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            final int n = showYesNoCancelDialog;
                            if (n == 0) {
                                ProjectUtil.openOrImport(child.getPath(), null, false);
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    return;
                }
                FileDocumentManager.getInstance().saveAllDocuments();
            }
            new ImportCMakeProjectWizard(a).runWizard();
        }
    }
    
    @Override
    protected void beforeRunWizard() {
        this.myStep.prepareDirectories(this.myImportDir);
    }
    
    @Override
    protected void doRunWizard() {
        CLionProjectWizardUtils.refreshProjectDir(this.myImportDir);
        final VirtualFile child = this.myImportDir.findChild("CMakeLists.txt");
        Logger log = null;
        boolean b = false;
        Label_0033: {
            try {
                log = CPPLog.LOG;
                if (child != null) {
                    b = true;
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        final String path = child.getPath();
        Project project = ProjectUtil.findAndFocusExistingProjectForPath(path);
        Label_0089: {
            try {
                if (project != null) {
                    CMakeWorkspace.getInstance(project).scheduleReload(true);
                    break Label_0089;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            CMakeWorkspace.forceReloadOnOpening(this.myImportDir);
            project = ProjectUtil.openOrImport(path, null, false);
            try {
                if (project == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        CLionProjectWizardUtils.reformatProjectFiles(project, child, false, new VirtualFile[0]);
        new OpenFileDescriptor(project, child).navigate(false);
    }
    
    @Nullable
    private static VirtualFile a() {
        final FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(true, true, false, false, false, false) {
            FileChooserDescriptor myDelegate = new OpenProjectFileChooserDescriptor(true);
            
            public Icon getIcon(final VirtualFile virtualFile) {
                final Icon icon = this.myDelegate.getIcon(virtualFile);
                return (icon == null) ? super.getIcon(virtualFile) : icon;
            }
            
            public boolean isFileSelectable(final VirtualFile virtualFile) {
                return virtualFile != null && virtualFile.isDirectory() && virtualFile.getChildren().length > 0;
            }
        };
        fileChooserDescriptor.setHideIgnored(true);
        fileChooserDescriptor.setTitle("Select Directory to Import");
        VirtualFile refreshAndFindFileByPath = null;
        final String value = PropertiesComponent.getInstance().getValue("last.imported.location");
        if (value != null) {
            refreshAndFindFileByPath = LocalFileSystem.getInstance().refreshAndFindFileByPath(value);
        }
        final VirtualFile[] choose = FileChooserFactory.getInstance().createFileChooser((FileChooserDescriptor)fileChooserDescriptor, (Project)null, (Component)null).choose((Project)null, new VirtualFile[] { refreshAndFindFileByPath });
        try {
            if (choose.length == 0) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        PropertiesComponent.getInstance().setValue("last.imported.location", choose[0].getPath());
        return choose[0];
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
