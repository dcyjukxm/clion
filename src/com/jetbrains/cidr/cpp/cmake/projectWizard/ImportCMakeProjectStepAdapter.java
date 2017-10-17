// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.openapi.vcs.changes.RefreshablePanel;
import com.intellij.ui.SplitterWithSecondHideable;
import com.intellij.openapi.progress.TaskInfo;
import com.intellij.openapi.wm.ex.ProgressIndicatorEx;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorExBase;
import com.intellij.openapi.vfs.VFileProperty;
import javax.swing.tree.MutableTreeNode;
import javax.swing.JTree;
import com.intellij.util.ui.tree.TreeUtil;
import java.util.Enumeration;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeModelEvent;
import com.intellij.util.ui.tree.TreeModelAdapter;
import javax.swing.tree.TreePath;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import com.intellij.openapi.application.ModalityState;
import java.util.Iterator;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.toolchains.CPPEnvironment;
import java.io.File;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgumentManipulator;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.io.IOException;
import com.jetbrains.cidr.cpp.CPPLog;
import java.util.List;
import java.util.ArrayList;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.ui.UIUtil;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import java.awt.Dimension;
import com.intellij.util.ui.JBUI;
import com.intellij.openapi.util.DimensionService;
import javax.swing.JComponent;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.CheckboxTreeBase;
import com.intellij.ui.CheckedTreeNode;
import java.awt.Component;
import com.intellij.util.ui.GridBag;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.CheckboxTree;
import javax.swing.JLabel;
import com.intellij.openapi.progress.util.ProgressIndicatorBase;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;

public class ImportCMakeProjectStepAdapter extends CMakeProjectStepAdapter
{
    private static final String DIMENSION_KEY = "ImportCMakeProjectWizard";
    private static final String[] CXX_EXTENSIONS;
    private final FileChooserDescriptor myIconProvider;
    private final JPanel myPanel;
    private final JPanel myProgressPanel;
    private final JProgressBar myProgressBar;
    private final ProgressIndicatorBase myProgressIndicator;
    private final JLabel myTreeLabel;
    private CheckboxTree myTree;
    private CheckboxTree myIncludeDirTree;
    private VirtualFile myImportDir;
    private boolean myScanFinish;
    
    public ImportCMakeProjectStepAdapter() {
        this.myIconProvider = new OpenProjectFileChooserDescriptor(true);
        this.myPanel = new JPanel(new BorderLayout());
        this.myProgressPanel = new JPanel(new GridBagLayout());
        this.myProgressBar = new JProgressBar();
        this.myProgressIndicator = new MyProgressIndicator();
        this.myTreeLabel = new JLabel("Select project files:");
    }
    
    @Override
    protected void init() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag setDefaultWeightX = new GridBag().setDefaultAnchor(17).setDefaultFill(2).setDefaultWeightX(1.0);
        this.myProgressPanel.add(new JLabel("Scanning folders  "), setDefaultWeightX.nextLine().next().weightx(0.0));
        this.myProgressPanel.add(this.myProgressBar, setDefaultWeightX.next());
        this.myProgressPanel.setVisible(false);
        setDefaultWeightX.nextLine().insets.bottom = 5;
        panel.add(this.myProgressPanel, setDefaultWeightX);
        setDefaultWeightX.reset();
        panel.add(this.myTreeLabel, setDefaultWeightX.nextLine());
        this.myTree = new CheckboxTree(new MyTreeCellRenderer(true), new CheckedTreeNode(), new CheckboxTreeBase.CheckPolicy(true, true, true, false));
        this.myTree.getSelectionModel().setSelectionMode(4);
        this.myTree.getEmptyText().setText("Loading...");
        panel.add(ScrollPaneFactory.createScrollPane((Component)this.myTree), setDefaultWeightX.nextLine().weighty(1.0).fillCell());
        this.myIncludeDirTree = new CheckboxTree(new MyTreeCellRenderer(false), new CheckedTreeNode(), new CheckboxTreeBase.CheckPolicy(false, false, false, false));
        this.myIncludeDirTree.getSelectionModel().setSelectionMode(4);
        this.myIncludeDirTree.getEmptyText().setText("Loading...");
        this.myIncludeDirTree.setModel((TreeModel)new DefaultTreeModel(new DefaultMutableTreeNode()));
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(ScrollPaneFactory.createScrollPane((Component)this.myIncludeDirTree), "Center");
        this.myPanel.add((Component)new ExpandedPanel("User Include Directories", panel, panel2).getComponent(), "Center");
        final Dimension size = DimensionService.getInstance().getSize("ImportCMakeProjectWizard");
        JPanel myPanel = null;
        Object size2 = null;
        Label_0382: {
            try {
                myPanel = this.myPanel;
                if (size == null) {
                    size2 = JBUI.size(640, 480);
                    break Label_0382;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            size2 = size;
        }
        myPanel.setPreferredSize((Dimension)size2);
    }
    
    public JComponent getComponent() {
        return this.myPanel;
    }
    
    public void dispose() {
        this.myProgressIndicator.cancel();
        DimensionService.getInstance().setSize("ImportCMakeProjectWizard", this.myPanel.getSize());
    }
    
    @NotNull
    @Override
    public Boolean validate() {
        Boolean value = null;
        Label_0033: {
            Label_0024: {
                try {
                    if (!this.myScanFinish) {
                        break Label_0024;
                    }
                    final ImportCMakeProjectStepAdapter importCMakeProjectStepAdapter = this;
                    final CheckedTreeNode checkedTreeNode = importCMakeProjectStepAdapter.getRootNode();
                    final boolean b = a((Object)checkedTreeNode);
                    if (b) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                try {
                    final ImportCMakeProjectStepAdapter importCMakeProjectStepAdapter = this;
                    final CheckedTreeNode checkedTreeNode = importCMakeProjectStepAdapter.getRootNode();
                    final boolean b = a((Object)checkedTreeNode);
                    if (b) {
                        final boolean b2 = true;
                        break Label_0033;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            final boolean b2 = false;
            try {
                value = b2;
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter", "validate"));
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return value;
    }
    
    public void prepareDirectories(@NotNull final VirtualFile myImportDir) {
        try {
            if (myImportDir == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "importDir", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter", "prepareDirectories"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        this.myImportDir = myImportDir;
        final CheckedTreeNode checkedTreeNode;
        ApplicationManager.getApplication().executeOnPooledThread(() -> ProgressManager.getInstance().runProcess(() -> {
            this.c();
            try {
                if (this.myProgressIndicator.isCanceled()) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            UIUtil.invokeLaterIfNeeded(() -> this.b(checkedTreeNode));
        }, (ProgressIndicator)this.myProgressIndicator));
    }
    
    public void testPrepareDirectories(@NotNull final VirtualFile myImportDir) {
        try {
            if (myImportDir == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "importDir", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter", "testPrepareDirectories"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        this.myImportDir = myImportDir;
        this.b(this.c());
    }
    
    public CheckedTreeNode getRootNode() {
        return (CheckedTreeNode)this.myTree.getModel().getRoot();
    }
    
    public CheckedTreeNode getIncludesRootNode() {
        return (CheckedTreeNode)this.myIncludeDirTree.getModel().getRoot();
    }
    
    public void createProject() throws IOException {
        final StringBuilder sb = new StringBuilder(CMakeProjectWizard.getCMakeListsFileHeader(FileUtil.sanitizeFileName(this.myImportDir.getName())) + "set(SOURCE_FILES\n");
        final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
        Logger log = null;
        boolean b = false;
        Label_0080: {
            try {
                b(this.getRootNode(), list);
                a(list);
                log = CPPLog.LOG;
                if (!list.isEmpty()) {
                    b = true;
                    break Label_0080;
                }
            }
            catch (IOException ex) {
                throw b(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        for (final VirtualFile virtualFile : list) {
            final String relativePath = VfsUtilCore.getRelativePath(virtualFile, this.myImportDir);
            try {
                if (relativePath == null) {
                    CPPLog.LOG.error("getRelativePath(" + virtualFile.getPresentableUrl() + ", " + this.myImportDir.getPresentableUrl() + ") is null");
                    continue;
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            sb.append("    ").append(CMakeArgumentManipulator.getCMakeLiteralFromValue(relativePath)).append("\n");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")\n\n");
        final ArrayList<Object> list2 = new ArrayList<Object>();
        a(this.getIncludesRootNode(), (List<VirtualFile>)list2);
        for (final VirtualFile virtualFile2 : list2) {
            String relativePath2 = VfsUtilCore.getRelativePath(virtualFile2, this.myImportDir);
            try {
                if (relativePath2 == null) {
                    CPPLog.LOG.error("getRelativePath(" + virtualFile2.getPresentableUrl() + ", " + this.myImportDir.getPresentableUrl() + ") is null");
                    continue;
                }
            }
            catch (IOException ex3) {
                throw b(ex3);
            }
            if (relativePath2.length() == 0) {
                relativePath2 = ".";
            }
            sb.append("include_directories(").append(CMakeArgumentManipulator.getCMakeLiteralFromValue(relativePath2)).append(")\n");
        }
        try {
            if (!list2.isEmpty()) {
                sb.append("\n");
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        sb.append("add_executable(").append(FileUtil.sanitizeFileName(this.myImportDir.getName())).append(" ${SOURCE_FILES})");
        FileUtil.writeToFile(new File(VfsUtilCore.virtualToIoFile(this.myImportDir), "CMakeLists.txt"), sb.toString().getBytes(CPPEnvironment.getCharset()));
    }
    
    private CheckedTreeNode c() {
        ApplicationManager.getApplication().invokeAndWait(() -> this.myImportDir.refresh(false, true), ModalityState.stateForComponent((Component)this.myPanel));
        final CheckedTreeNode valueForChildren = new CheckedTreeNode((Object)this.myImportDir);
        final VirtualFileVisitor<CheckedTreeNode> virtualFileVisitor = new VirtualFileVisitor<CheckedTreeNode>(new VirtualFileVisitor.Option[0]) {
            @NotNull
            public VirtualFileVisitor.Result visitFileEx(@NotNull final VirtualFile p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: ifnonnull       44
                //     4: new             Ljava/lang/IllegalArgumentException;
                //     7: dup            
                //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
                //    10: ldc             3
                //    12: anewarray       Ljava/lang/Object;
                //    15: dup            
                //    16: ldc             0
                //    18: ldc             "file"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "visitFileEx"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;
                //    48: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$000:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;)Lcom/intellij/openapi/progress/util/ProgressIndicatorBase;
                //    51: invokevirtual   com/intellij/openapi/progress/util/ProgressIndicatorBase.isCanceled:()Z
                //    54: ifeq            113
                //    57: aload_0        
                //    58: getfield        com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;
                //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$100:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;)Lcom/intellij/openapi/vfs/VirtualFile;
                //    64: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //    67: dup            
                //    68: ifnonnull       112
                //    71: goto            78
                //    74: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    77: athrow         
                //    78: new             Ljava/lang/IllegalStateException;
                //    81: dup            
                //    82: ldc             "@NotNull method %s.%s must not return null"
                //    84: ldc             2
                //    86: anewarray       Ljava/lang/Object;
                //    89: dup            
                //    90: ldc             0
                //    92: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //    94: aastore        
                //    95: dup            
                //    96: ldc             1
                //    98: ldc             "visitFileEx"
                //   100: aastore        
                //   101: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   104: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   107: athrow         
                //   108: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   111: athrow         
                //   112: areturn        
                //   113: aload_0        
                //   114: getfield        com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;
                //   117: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$100:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;)Lcom/intellij/openapi/vfs/VirtualFile;
                //   120: aload_1        
                //   121: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //   124: ifeq            176
                //   127: getstatic       com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.CONTINUE:Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //   130: dup            
                //   131: ifnonnull       175
                //   134: goto            141
                //   137: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   140: athrow         
                //   141: new             Ljava/lang/IllegalStateException;
                //   144: dup            
                //   145: ldc             "@NotNull method %s.%s must not return null"
                //   147: ldc             2
                //   149: anewarray       Ljava/lang/Object;
                //   152: dup            
                //   153: ldc             0
                //   155: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //   157: aastore        
                //   158: dup            
                //   159: ldc             1
                //   161: ldc             "visitFileEx"
                //   163: aastore        
                //   164: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   167: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   170: athrow         
                //   171: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   174: athrow         
                //   175: areturn        
                //   176: invokestatic    com/intellij/openapi/fileTypes/FileTypeManager.getInstance:()Lcom/intellij/openapi/fileTypes/FileTypeManager;
                //   179: aload_1        
                //   180: invokevirtual   com/intellij/openapi/fileTypes/FileTypeManager.isFileIgnored:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
                //   183: ifne            200
                //   186: aload_1        
                //   187: invokestatic    com/intellij/openapi/fileChooser/FileElement.isFileHidden:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
                //   190: ifeq            250
                //   193: goto            200
                //   196: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   199: athrow         
                //   200: aload_1        
                //   201: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //   204: dup            
                //   205: ifnonnull       249
                //   208: goto            215
                //   211: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   214: athrow         
                //   215: new             Ljava/lang/IllegalStateException;
                //   218: dup            
                //   219: ldc             "@NotNull method %s.%s must not return null"
                //   221: ldc             2
                //   223: anewarray       Ljava/lang/Object;
                //   226: dup            
                //   227: ldc             0
                //   229: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //   231: aastore        
                //   232: dup            
                //   233: ldc             1
                //   235: ldc             "visitFileEx"
                //   237: aastore        
                //   238: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   241: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   244: athrow         
                //   245: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   248: athrow         
                //   249: areturn        
                //   250: aload_1        
                //   251: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
                //   254: ifeq            326
                //   257: aload_1        
                //   258: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
                //   261: ldc             "CMakeFiles"
                //   263: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   266: ifeq            326
                //   269: goto            276
                //   272: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   275: athrow         
                //   276: aload_1        
                //   277: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //   280: dup            
                //   281: ifnonnull       325
                //   284: goto            291
                //   287: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   290: athrow         
                //   291: new             Ljava/lang/IllegalStateException;
                //   294: dup            
                //   295: ldc             "@NotNull method %s.%s must not return null"
                //   297: ldc             2
                //   299: anewarray       Ljava/lang/Object;
                //   302: dup            
                //   303: ldc             0
                //   305: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //   307: aastore        
                //   308: dup            
                //   309: ldc             1
                //   311: ldc             "visitFileEx"
                //   313: aastore        
                //   314: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   317: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   320: athrow         
                //   321: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   324: athrow         
                //   325: areturn        
                //   326: new             Lcom/intellij/ui/CheckedTreeNode;
                //   329: dup            
                //   330: aload_1        
                //   331: invokespecial   com/intellij/ui/CheckedTreeNode.<init>:(Ljava/lang/Object;)V
                //   334: astore_2       
                //   335: aload_1        
                //   336: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
                //   339: ifne            403
                //   342: iconst_0       
                //   343: istore_3       
                //   344: aload_1        
                //   345: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getExtension:()Ljava/lang/String;
                //   348: astore          4
                //   350: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$200:()[Ljava/lang/String;
                //   353: astore          5
                //   355: aload           5
                //   357: arraylength    
                //   358: istore          6
                //   360: iconst_0       
                //   361: istore          7
                //   363: iload           7
                //   365: iload           6
                //   367: if_icmpge       398
                //   370: aload           5
                //   372: iload           7
                //   374: aaload         
                //   375: astore          8
                //   377: aload           8
                //   379: aload           4
                //   381: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
                //   384: ifeq            392
                //   387: iconst_1       
                //   388: istore_3       
                //   389: goto            398
                //   392: iinc            7, 1
                //   395: goto            363
                //   398: aload_2        
                //   399: iload_3        
                //   400: invokevirtual   com/intellij/ui/CheckedTreeNode.setChecked:(Z)V
                //   403: aload_0        
                //   404: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.getCurrentValue:()Ljava/lang/Object;
                //   407: checkcast       Lcom/intellij/ui/CheckedTreeNode;
                //   410: aload_2        
                //   411: invokevirtual   com/intellij/ui/CheckedTreeNode.add:(Ljavax/swing/tree/MutableTreeNode;)V
                //   414: aload_0        
                //   415: aload_2        
                //   416: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.setValueForChildren:(Ljava/lang/Object;)V
                //   419: getstatic       com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.CONTINUE:Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //   422: dup            
                //   423: ifnonnull       460
                //   426: new             Ljava/lang/IllegalStateException;
                //   429: dup            
                //   430: ldc             "@NotNull method %s.%s must not return null"
                //   432: ldc             2
                //   434: anewarray       Ljava/lang/Object;
                //   437: dup            
                //   438: ldc             0
                //   440: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //   442: aastore        
                //   443: dup            
                //   444: ldc             1
                //   446: ldc             "visitFileEx"
                //   448: aastore        
                //   449: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   452: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   455: athrow         
                //   456: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   459: athrow         
                //   460: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  44     71     74     78     Ljava/lang/IllegalArgumentException;
                //  57     108    108    112    Ljava/lang/IllegalArgumentException;
                //  113    134    137    141    Ljava/lang/IllegalArgumentException;
                //  127    171    171    175    Ljava/lang/IllegalArgumentException;
                //  176    193    196    200    Ljava/lang/IllegalArgumentException;
                //  186    208    211    215    Ljava/lang/IllegalArgumentException;
                //  200    245    245    249    Ljava/lang/IllegalArgumentException;
                //  250    269    272    276    Ljava/lang/IllegalArgumentException;
                //  257    284    287    291    Ljava/lang/IllegalArgumentException;
                //  276    321    321    325    Ljava/lang/IllegalArgumentException;
                //  403    456    456    460    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0200:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            
            @Nullable
            public Iterable<VirtualFile> getChildrenIterable(@NotNull final VirtualFile p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: ifnonnull       44
                //     4: new             Ljava/lang/IllegalArgumentException;
                //     7: dup            
                //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
                //    10: ldc             3
                //    12: anewarray       Ljava/lang/Object;
                //    15: dup            
                //    16: ldc             0
                //    18: ldc             "file"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "getChildrenIterable"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_1        
                //    45: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getChildren:()[Lcom/intellij/openapi/vfs/VirtualFile;
                //    48: astore_2       
                //    49: aload_2        
                //    50: invokedynamic   compare:()Ljava/util/Comparator;
                //    55: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;Ljava/util/Comparator;)V
                //    58: aload_2        
                //    59: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:([Ljava/lang/Object;)Ljava/util/ArrayList;
                //    62: areturn        
                //    Signature:
                //  (Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/lang/Iterable<Lcom/intellij/openapi/vfs/VirtualFile;>;
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.NullPointerException
                //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
                //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            
            public void afterChildrenVisited(@NotNull final VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1", "afterChildrenVisited"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (!virtualFile.isDirectory()) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final CheckedTreeNode checkedTreeNode = (CheckedTreeNode)this.getCurrentValue();
                final int childCount = checkedTreeNode.getChildCount();
                boolean checked = false;
                for (int i = 0; i < childCount; ++i) {
                    if (((CheckedTreeNode)checkedTreeNode.getChildAt(i)).isChecked()) {
                        checked = true;
                        break;
                    }
                }
                checkedTreeNode.setChecked(checked);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        virtualFileVisitor.setValueForChildren((Object)valueForChildren);
        VfsUtilCore.visitChildrenRecursively(this.myImportDir, (VirtualFileVisitor)virtualFileVisitor);
        return valueForChildren;
    }
    
    private void b(final CheckedTreeNode checkedTreeNode) {
        this.myTree.setRootVisible(true);
        this.myTree.setModel((TreeModel)new DefaultTreeModel((TreeNode)checkedTreeNode));
        final TreePath treePath = new TreePath(checkedTreeNode);
        this.myTree.expandPath(treePath);
        final Enumeration children = checkedTreeNode.children();
        while (children.hasMoreElements()) {
            final CheckedTreeNode checkedTreeNode2 = children.nextElement();
            try {
                if (!checkedTreeNode2.isChecked()) {
                    continue;
                }
                this.myTree.expandPath(treePath.pathByAddingChild(checkedTreeNode2));
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
        }
        this.b();
        this.myScanFinish = true;
        this.myValidateCallback.run();
        this.myTree.getModel().addTreeModelListener((TreeModelListener)new TreeModelAdapter() {
            public void treeNodesChanged(final TreeModelEvent treeModelEvent) {
                ImportCMakeProjectStepAdapter.this.b();
                ImportCMakeProjectStepAdapter.this.myValidateCallback.run();
            }
        });
    }
    
    private void b() {
        final CheckedTreeNode rootNode = this.getRootNode();
        if (rootNode.isChecked()) {
            final Object root = this.myIncludeDirTree.getModel().getRoot();
            Label_0100: {
                try {
                    if (root instanceof CheckedTreeNode) {
                        a(rootNode, (CheckedTreeNode)root);
                        ((DefaultTreeModel)this.myIncludeDirTree.getModel()).reload();
                        break Label_0100;
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                final CheckedTreeNode checkedTreeNode = new CheckedTreeNode((Object)this.myImportDir);
                b(rootNode, checkedTreeNode);
                this.myIncludeDirTree.setRootVisible(true);
                this.myIncludeDirTree.setModel((TreeModel)new DefaultTreeModel((TreeNode)checkedTreeNode));
            }
            TreeUtil.expand((JTree)this.myIncludeDirTree, 2);
        }
        else {
            this.myIncludeDirTree.setRootVisible(false);
            this.myIncludeDirTree.setModel((TreeModel)new DefaultTreeModel(new DefaultMutableTreeNode()));
        }
    }
    
    private static void b(final CheckedTreeNode checkedTreeNode, final CheckedTreeNode checkedTreeNode2) {
        checkedTreeNode2.setUserObject(checkedTreeNode.getUserObject());
        checkedTreeNode2.setChecked(false);
        for (int childCount = checkedTreeNode.getChildCount(), i = 0; i < childCount; ++i) {
            final CheckedTreeNode checkedTreeNode3 = (CheckedTreeNode)checkedTreeNode.getChildAt(i);
            try {
                if (!checkedTreeNode3.isChecked() || !((VirtualFile)checkedTreeNode3.getUserObject()).isDirectory()) {
                    continue;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            final CheckedTreeNode checkedTreeNode4 = new CheckedTreeNode();
            checkedTreeNode2.add((MutableTreeNode)checkedTreeNode4);
            b(checkedTreeNode3, checkedTreeNode4);
        }
    }
    
    private static void a(final CheckedTreeNode checkedTreeNode, final CheckedTreeNode checkedTreeNode2) {
        final int childCount = checkedTreeNode.getChildCount();
        TreeNode treeNode = null;
        for (int i = childCount - 1; i >= 0; --i) {
            final CheckedTreeNode checkedTreeNode3 = (CheckedTreeNode)checkedTreeNode.getChildAt(i);
            final VirtualFile virtualFile = (VirtualFile)checkedTreeNode3.getUserObject();
            if (virtualFile.isDirectory()) {
                CheckedTreeNode a = a(virtualFile, checkedTreeNode2);
                Label_0134: {
                    Label_0128: {
                        Label_0121: {
                            try {
                                if (!checkedTreeNode3.isChecked()) {
                                    break Label_0134;
                                }
                                if (a != null) {
                                    break Label_0121;
                                }
                            }
                            catch (IllegalStateException ex) {
                                throw b(ex);
                            }
                            a = new CheckedTreeNode();
                            Label_0111: {
                                try {
                                    if (treeNode == null) {
                                        checkedTreeNode2.add((MutableTreeNode)a);
                                        break Label_0111;
                                    }
                                }
                                catch (IllegalStateException ex2) {
                                    throw b(ex2);
                                }
                                checkedTreeNode2.insert((MutableTreeNode)a, checkedTreeNode2.getIndex(treeNode));
                            }
                            b(checkedTreeNode3, a);
                            break Label_0128;
                        }
                        a(checkedTreeNode3, a);
                    }
                    treeNode = (TreeNode)a;
                    continue;
                    try {
                        if (a != null) {
                            checkedTreeNode2.remove((MutableTreeNode)a);
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
    }
    
    private static void a(final List<VirtualFile> list) {
        final Iterator<VirtualFile> iterator = list.iterator();
        while (iterator.hasNext()) {
            final VirtualFile virtualFile = iterator.next();
            try {
                if (virtualFile.is(VFileProperty.SYMLINK)) {
                    a(list, iterator, virtualFile.getCanonicalFile());
                    continue;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            final VirtualFile parent = virtualFile.getParent();
            if (parent.is(VFileProperty.SYMLINK)) {
                final VirtualFile canonicalFile = parent.getCanonicalFile();
                try {
                    if (canonicalFile == null) {
                        continue;
                    }
                    a(list, iterator, canonicalFile.findChild(virtualFile.getName()));
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
        }
    }
    
    private static void a(final List<VirtualFile> list, final Iterator<VirtualFile> iterator, final VirtualFile virtualFile) {
        if (virtualFile != null) {
            for (final VirtualFile virtualFile2 : list) {
                try {
                    if (virtualFile.equals(virtualFile2)) {
                        iterator.remove();
                        break;
                    }
                    continue;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
            }
        }
    }
    
    private static CheckedTreeNode a(final Object o, final CheckedTreeNode checkedTreeNode) {
        for (int i = checkedTreeNode.getChildCount() - 1; i >= 0; --i) {
            final CheckedTreeNode checkedTreeNode2 = (CheckedTreeNode)checkedTreeNode.getChildAt(i);
            try {
                if (o.equals(checkedTreeNode2.getUserObject())) {
                    return checkedTreeNode2;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
        }
        return null;
    }
    
    private static void b(final Object o, final List<VirtualFile> list) {
        final CheckedTreeNode checkedTreeNode = (CheckedTreeNode)o;
        try {
            if (!checkedTreeNode.isChecked()) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final VirtualFile virtualFile = (VirtualFile)checkedTreeNode.getUserObject();
        Label_0046: {
            try {
                if (!checkedTreeNode.isLeaf()) {
                    break Label_0046;
                }
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = virtualFile2.isDirectory();
                if (!b) {
                    break Label_0046;
                }
                break Label_0046;
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = virtualFile2.isDirectory();
                if (!b) {
                    list.add(virtualFile);
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        final int childCount = checkedTreeNode.getChildCount();
        int i = 0;
        try {
            while (i < childCount) {
                b(checkedTreeNode.getChildAt(i), list);
                ++i;
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
    }
    
    private static void a(final Object o, final List<VirtualFile> list) {
        final CheckedTreeNode checkedTreeNode = (CheckedTreeNode)o;
        final VirtualFile virtualFile = (VirtualFile)checkedTreeNode.getUserObject();
        try {
            if (checkedTreeNode.isChecked()) {
                list.add(virtualFile);
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final int childCount = checkedTreeNode.getChildCount();
        int i = 0;
        try {
            while (i < childCount) {
                a(checkedTreeNode.getChildAt(i), list);
                ++i;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
    }
    
    private static boolean a(final Object o) {
        final CheckedTreeNode checkedTreeNode = (CheckedTreeNode)o;
        try {
            if (!checkedTreeNode.isChecked()) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        Label_0051: {
            try {
                if (!checkedTreeNode.isLeaf()) {
                    break Label_0051;
                }
                final CheckedTreeNode checkedTreeNode2 = checkedTreeNode;
                final Object o2 = checkedTreeNode2.getUserObject();
                final VirtualFile virtualFile = (VirtualFile)o2;
                final boolean b = virtualFile.isDirectory();
                if (!b) {
                    return true;
                }
                break Label_0051;
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                final CheckedTreeNode checkedTreeNode2 = checkedTreeNode;
                final Object o2 = checkedTreeNode2.getUserObject();
                final VirtualFile virtualFile = (VirtualFile)o2;
                final boolean b = virtualFile.isDirectory();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        final int childCount = checkedTreeNode.getChildCount();
        int n = 0;
        while (true) {
            Label_0087: {
                try {
                    if (n >= childCount) {
                        break;
                    }
                    final CheckedTreeNode checkedTreeNode3 = checkedTreeNode;
                    final int n2 = n;
                    final TreeNode treeNode = checkedTreeNode3.getChildAt(n2);
                    final boolean b2 = a(treeNode);
                    if (b2) {
                        return true;
                    }
                    break Label_0087;
                }
                catch (IllegalStateException ex4) {
                    throw b(ex4);
                }
                try {
                    final CheckedTreeNode checkedTreeNode3 = checkedTreeNode;
                    final int n2 = n;
                    final TreeNode treeNode = checkedTreeNode3.getChildAt(n2);
                    final boolean b2 = a(treeNode);
                    if (b2) {
                        return true;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw b(ex5);
                }
            }
            ++n;
        }
        return false;
    }
    
    static {
        CXX_EXTENSIONS = new String[] { "c", "cc", "cp", "cpp", "c++", "cxx", "h", "hh", "h++", "hxx", "hp", "hpp", "tcc", "i", "ii", "icc" };
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private class MyProgressIndicator extends ProgressIndicatorBase
    {
        public MyProgressIndicator() {
            this.addStateDelegate(new AbstractProgressIndicatorExBase() {
                @Override
                public void cancel() {
                    super.cancel();
                    MyProgressIndicator.this.c();
                }
            });
        }
        
        @Override
        public void start() {
            super.start();
            UIUtil.invokeAndWaitIfNeeded(() -> {
                ImportCMakeProjectStepAdapter.this.myProgressBar.setIndeterminate(true);
                ImportCMakeProjectStepAdapter.this.myProgressPanel.setVisible(true);
                ImportCMakeProjectStepAdapter.this.myTreeLabel.setVisible(false);
            });
        }
        
        private void c() {
            UIUtil.invokeAndWaitIfNeeded(() -> {
                ImportCMakeProjectStepAdapter.this.myProgressBar.setIndeterminate(false);
                ImportCMakeProjectStepAdapter.this.myProgressPanel.setVisible(false);
                ImportCMakeProjectStepAdapter.this.myTreeLabel.setVisible(true);
            });
        }
        
        @Override
        public void stop() {
            super.stop();
            this.c();
        }
        
        @Override
        public void finish(@NotNull final TaskInfo taskInfo) {
            try {
                if (taskInfo == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "task", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$MyProgressIndicator", "finish"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            super.finish(taskInfo);
            this.c();
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class ExpandedListener implements SplitterWithSecondHideable.OnOffListener<Integer>
    {
        private ExpandedPanel myExpandedPanel;
        private float myPreviousProportion;
        
        public void setExpandedPanel(final ExpandedPanel myExpandedPanel) {
            this.myExpandedPanel = myExpandedPanel;
        }
        
        @Override
        public void on(final Integer n) {
            this.myExpandedPanel.getComponent().setProportion((this.myPreviousProportion > 0.0f) ? this.myPreviousProportion : this.myExpandedPanel.getSplitterInitialProportion());
        }
        
        @Override
        public void off(final Integer n) {
            this.myPreviousProportion = this.myExpandedPanel.getUsedProportion();
        }
    }
    
    private static class ExpandedPanel extends SplitterWithSecondHideable
    {
        private final RefreshablePanel myRefreshablePanel;
        
        public ExpandedPanel(final String s, final JComponent component, final JPanel panel) {
            this(s, component, panel, new ExpandedListener());
        }
        
        private ExpandedPanel(final String s, final JComponent component, final JPanel panel, final ExpandedListener expandedListener) {
            super(true, s, component, expandedListener);
            this.myRefreshablePanel = new RefreshablePanel() {
                @Override
                public void refresh() {
                }
                
                @Override
                public JPanel getPanel() {
                    return panel;
                }
            };
            this.getComponent().setResizeEnabled(false);
            expandedListener.setExpandedPanel(this);
        }
        
        @Override
        protected RefreshablePanel createDetails() {
            return this.myRefreshablePanel;
        }
        
        @Override
        protected float getSplitterInitialProportion() {
            return 0.7f;
        }
    }
    
    private class MyTreeCellRenderer extends CheckboxTree.CheckboxTreeCellRenderer
    {
        private final boolean myRootFullName;
        
        public MyTreeCellRenderer(final boolean myRootFullName) {
            super(true, false);
            this.myRootFullName = myRootFullName;
        }
        
        public void customizeRenderer(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
            if (o instanceof CheckedTreeNode) {
                final Object userObject = ((CheckedTreeNode)o).getUserObject();
                if (userObject instanceof VirtualFile) {
                    final VirtualFile virtualFile = (VirtualFile)userObject;
                    final ColoredTreeCellRenderer textRenderer = this.getTextRenderer();
                    textRenderer.append((this.myRootFullName && ImportCMakeProjectStepAdapter.this.myImportDir.equals(virtualFile)) ? virtualFile.getPresentableUrl() : virtualFile.getPresentableName());
                    textRenderer.setIcon(ImportCMakeProjectStepAdapter.this.myIconProvider.getIcon(virtualFile));
                }
            }
        }
    }
}
