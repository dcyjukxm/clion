// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.toolchains.MinGW;
import com.jetbrains.cidr.cpp.toolchains.Cygwin;
import com.jetbrains.cidr.ToolVersion;
import javax.swing.SwingUtilities;
import com.jetbrains.cidr.cpp.toolchains.GDB;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.SystemInfo;
import com.jetbrains.cidr.execution.debugger.backend.LLDBDriverConfiguration;
import com.intellij.openapi.util.Disposer;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Window;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.intellij.ui.components.JBLabel;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.TextComponentAccessor;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import com.intellij.util.Function;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import com.intellij.ui.DocumentAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Arrays;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.intellij.util.NullableConsumer;
import java.io.File;
import com.intellij.util.NullableFunction;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.intellij.ui.TitledSeparator;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import com.intellij.util.ui.UIUtil;
import java.awt.Insets;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.intellij.util.Alarm;
import com.intellij.ui.HyperlinkLabel;
import java.awt.Component;
import java.util.List;
import java.util.Map;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBRadioButton;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.Disposable;
import javax.swing.JPanel;

public class CPPToolchainsPanel extends JPanel implements Disposable
{
    private static final String GDB_KEY = "GDB";
    @Nullable
    private final CPPToolchainsConfigurable.EnvironmentChangeListener myListener;
    private final JBRadioButton myUseMSVC;
    private final JBRadioButton myUseMinGW;
    private final JBRadioButton myUseCygwin;
    private final TextFieldWithBrowseButton myMSVCPath;
    private final TextFieldWithBrowseButton myMinGWPath;
    private final TextFieldWithBrowseButton myCygwinPath;
    private final JBRadioButton myUseBundledCMake;
    private final JBRadioButton myUseSpecifiedCMake;
    private final TextFieldWithBrowseButton myCMakePath;
    private final Map<String, List<Component>> myGeneralComponents;
    private final Map<String, List<Component>> myEnvComponents;
    private final HyperlinkLabel myEnvGDBLabel;
    private final JBRadioButton myUseBundledGDB;
    private final JBRadioButton myUseBundledLLDB;
    private final JBRadioButton myUseSpecifiedGDB;
    private final TextFieldWithBrowseButton myGDBPath;
    private final CPPToolchainsCheckPanel myCheckPanel;
    private boolean myAutoRecheck;
    private long myDeactivatedTime;
    private final Alarm myEnvironmentUpdateAlarm;
    private static final boolean isWindows;
    private static final boolean isMac;
    private static final boolean isLinux;
    private boolean myUpdating;
    
    public CPPToolchainsPanel(@Nullable final CPPToolchainsConfigurable.EnvironmentChangeListener myListener) {
        super(new GridBagLayout());
        this.myUpdating = false;
        this.myListener = myListener;
        final GridBag setDefaultFill = new GridBag().setDefaultWeightX(1, 1.0).setDefaultAnchor(10).setDefaultFill(2);
        try {
            if (ApplicationManager.getApplication() != null) {
                setDefaultFill.setDefaultInsets((Insets)UIUtil.PANEL_SMALL_INSETS.clone());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myEnvironmentUpdateAlarm = this.e();
        this.myMSVCPath = new TextFieldWithBrowseButton();
        this.myMinGWPath = new TextFieldWithBrowseButton();
        this.myCygwinPath = new TextFieldWithBrowseButton();
        this.myUseMSVC = new JBRadioButton("");
        this.myUseMinGW = new JBRadioButton("");
        this.myUseCygwin = new JBRadioButton("");
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add((AbstractButton)this.myUseMSVC);
        buttonGroup.add((AbstractButton)this.myUseMinGW);
        buttonGroup.add((AbstractButton)this.myUseCygwin);
        if (CPPToolchainsPanel.isWindows) {
            final ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    final boolean selected = CPPToolchainsPanel.this.myUseMSVC.isSelected();
                    final boolean selected2 = CPPToolchainsPanel.this.myUseMinGW.isSelected();
                    final boolean selected3 = CPPToolchainsPanel.this.myUseCygwin.isSelected();
                    CPPToolchainsPanel.this.myMSVCPath.setEnabled(selected);
                    CPPToolchainsPanel.this.myMinGWPath.setEnabled(selected2);
                    CPPToolchainsPanel.this.myCygwinPath.setEnabled(selected3);
                    CPPToolchainsPanel.this.c();
                    CPPToolchainsPanel.this.a(CPPToolchainsCheckPanel.UpdateType.All);
                }
            };
            this.myUseMSVC.addItemListener((ItemListener)itemListener);
            this.myUseMinGW.addItemListener((ItemListener)itemListener);
            this.myUseCygwin.addItemListener((ItemListener)itemListener);
            this.add((Component)new TitledSeparator(CPPBundle.message("cmake.settings.environment", new Object[0])), setDefaultFill.nextLine().coverLine());
            final NullableConsumer nullableConsumer = s -> this.c();
            try {
                if (MSVC.isEnabled()) {
                    this.a(this.myUseMSVC, buttonGroup, this.myMSVCPath, setDefaultFill, CPPBundle.message("msvc", new Object[0]), (NullableFunction<File, String>)(file -> MSVC.readVersion(file)), (NullableConsumer<String>)nullableConsumer, CPPToolchainsCheckPanel.UpdateType.All);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            this.a(this.myUseMinGW, buttonGroup, this.myMinGWPath, setDefaultFill, CPPBundle.message("mingw", new Object[0]), (NullableFunction<File, String>)(file -> MinGW.readVersion(file)), (NullableConsumer<String>)nullableConsumer, CPPToolchainsCheckPanel.UpdateType.All);
            this.a(this.myUseCygwin, buttonGroup, this.myCygwinPath, setDefaultFill, CPPBundle.message("cygwin", new Object[0]), (NullableFunction<File, String>)(file -> Cygwin.readVersion(file)), (NullableConsumer<String>)nullableConsumer, CPPToolchainsCheckPanel.UpdateType.All);
        }
        this.add((Component)new TitledSeparator(CPPBundle.message("cmake", new Object[0]) + " executable:"), setDefaultFill.nextLine().coverLine());
        final int componentCount = this.getComponentCount();
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add((AbstractButton)(this.myUseBundledCMake = new JBRadioButton(a(CPPBundle.message("cmake", new Object[0]), "", false))));
        buttonGroup2.add((AbstractButton)(this.myUseSpecifiedCMake = new JBRadioButton(CPPBundle.message("settings.select.UseSpecified", new Object[0]))));
        this.add((Component)this.myUseBundledCMake, setDefaultFill.nextLine().next().coverLine());
        this.add((Component)this.myUseSpecifiedCMake, setDefaultFill.nextLine().next());
        this.add((Component)(this.myCMakePath = new TextFieldWithBrowseButton()), setDefaultFill.next());
        this.a(this.myCMakePath, setDefaultFill.nextLine().next().next(), CPPBundle.message("cmake", new Object[0]), (NullableFunction<File, String>)(file -> CMake.readVersion(file, this.d())), null, CPPToolchainsCheckPanel.UpdateType.CMake);
        a(this.myCMakePath, CPPBundle.message("cmake", new Object[0]) + " executable", true, CMake.EXECUTABLE_NAME);
        Arrays.asList(this.getComponents()).subList(componentCount, this.getComponentCount());
        final ItemListener itemListener2 = new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                CPPToolchainsPanel.this.myCMakePath.setEnabled(CPPToolchainsPanel.this.myUseSpecifiedCMake.isSelected());
                CPPToolchainsPanel.this.a(CPPToolchainsCheckPanel.UpdateType.CMake);
            }
        };
        this.myUseBundledCMake.addItemListener((ItemListener)itemListener2);
        this.myUseSpecifiedCMake.addItemListener((ItemListener)itemListener2);
        this.add((Component)new TitledSeparator(CPPBundle.message("cpp.toolchains.debugger", new Object[0]) + ":"), setDefaultFill.nextLine().coverLine());
        final ButtonGroup buttonGroup3 = new ButtonGroup();
        buttonGroup3.add((AbstractButton)(this.myUseBundledLLDB = new JBRadioButton(a(CPPBundle.message("lldb", new Object[0]), "", true))));
        buttonGroup3.add((AbstractButton)(this.myUseBundledGDB = new JBRadioButton(a(CPPBundle.message("gdb", new Object[0]), "", false))));
        buttonGroup3.add((AbstractButton)(this.myUseSpecifiedGDB = new JBRadioButton(CPPBundle.message("settings.select.UseSpecifiedThing", CPPBundle.message("gdb", new Object[0])))));
        final boolean isMac = CPPToolchainsPanel.isMac;
        try {
            if (isMac) {
                this.add((Component)this.myUseBundledLLDB, setDefaultFill.nextLine().next().coverLine());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final int componentCount2 = this.getComponentCount();
        this.add((Component)this.myUseBundledGDB, setDefaultFill.nextLine().next().coverLine());
        this.add((Component)this.myUseSpecifiedGDB, setDefaultFill.nextLine().next());
        a(this.myGDBPath = new TextFieldWithBrowseButton(), CPPBundle.message("gdb", new Object[0]) + " executable", true, null);
        this.add((Component)this.myGDBPath, setDefaultFill.next());
        this.a(this.myGDBPath, setDefaultFill.nextLine().next().next(), CPPBundle.message("gdb", new Object[0]), (NullableFunction<File, String>)(file -> {
            final ToolVersion version = GDB.readVersion(file, this.d());
            try {
                if (version != null) {
                    return version.toString();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return null;
        }), null, CPPToolchainsCheckPanel.UpdateType.GDB);
        final List<Component> subList = Arrays.asList(this.getComponents()).subList(componentCount2, this.getComponentCount());
        final int componentCount3 = this.getComponentCount();
        this.add((Component)(this.myEnvGDBLabel = new HyperlinkLabel()), setDefaultFill.nextLine().next().coverLine());
        this.myEnvGDBLabel.setVisible(CPPToolchainsPanel.isWindows);
        final List<Component> subList2 = Arrays.asList(this.getComponents()).subList(componentCount3, this.getComponentCount());
        try {
            if (!isMac) {
                this.add((Component)this.myUseBundledLLDB, setDefaultFill.nextLine().next().coverLine());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final ItemListener itemListener3 = new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                CPPToolchainsPanel.this.myGDBPath.setEnabled(CPPToolchainsPanel.this.myUseSpecifiedGDB.isSelected());
                CPPToolchainsPanel.this.a(CPPToolchainsCheckPanel.UpdateType.GDB);
            }
        };
        this.myUseBundledLLDB.addItemListener((ItemListener)itemListener3);
        this.myUseBundledGDB.addItemListener((ItemListener)itemListener3);
        this.myUseSpecifiedGDB.addItemListener((ItemListener)itemListener3);
        (this.myGeneralComponents = new HashMap<String, List<Component>>()).put("GDB", subList);
        (this.myEnvComponents = new HashMap<String, List<Component>>()).put("GDB", subList2);
        setDefaultFill.nextLine().next().coverLine().fillCell().weighty(1.0).insets.top = 30;
        this.add(this.myCheckPanel = new CPPToolchainsCheckPanel((Disposable)this, CPPToolchainsPanel.isWindows), setDefaultFill);
    }
    
    private void a(final JBRadioButton jbRadioButton, final ButtonGroup buttonGroup, final TextFieldWithBrowseButton textFieldWithBrowseButton, final GridBag gridBag, final String s, @NotNull final NullableFunction<File, String> nullableFunction, @Nullable final NullableConsumer<String> nullableConsumer, final CPPToolchainsCheckPanel.UpdateType updateType) {
        try {
            if (nullableFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "readVersion", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "initPathControl"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        jbRadioButton.setText(CPPBundle.message("settings.select.UseHome", s));
        this.add((Component)jbRadioButton, gridBag.nextLine().next());
        this.add((Component)textFieldWithBrowseButton, gridBag.next());
        a(textFieldWithBrowseButton, s + " home", false, null);
        this.a(textFieldWithBrowseButton, gridBag.nextLine().next().next(), s, nullableFunction, nullableConsumer, updateType);
        textFieldWithBrowseButton.getTextField().getDocument().addDocumentListener((DocumentListener)new DocumentAdapter() {
            protected void textChanged(final DocumentEvent documentEvent) {
                if (buttonGroup.getSelection() == null) {
                    jbRadioButton.setSelected(true);
                }
            }
        });
    }
    
    private static void a(@NotNull final TextFieldWithBrowseButton textFieldWithBrowseButton, @NotNull String title, final boolean b, @Nullable final String s) {
        try {
            if (textFieldWithBrowseButton == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "field", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "addBrowseListener"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (title == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "addBrowseListener"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        title = "Select " + title;
        if (CPPToolchainsPanel.isMac) {
            title += " or application bundle";
        }
        final Function function = p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: getstatic       com/jetbrains/cidr/cpp/CPPToolchainsPanel.isMac:Z
            //     3: ifeq            59
            //     6: aload_0        
            //     7: ifnull          59
            //    10: goto            17
            //    13: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    16: athrow         
            //    17: aload_1        
            //    18: invokevirtual   java/io/File.isDirectory:()Z
            //    21: ifeq            59
            //    24: goto            31
            //    27: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    30: athrow         
            //    31: aload_1        
            //    32: invokevirtual   java/io/File.getName:()Ljava/lang/String;
            //    35: ldc             "app"
            //    37: invokestatic    com/intellij/openapi/util/io/FileUtilRt.extensionEquals:(Ljava/lang/String;Ljava/lang/String;)Z
            //    40: ifeq            59
            //    43: goto            50
            //    46: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    49: athrow         
            //    50: iconst_1       
            //    51: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    54: areturn        
            //    55: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    58: athrow         
            //    59: iconst_0       
            //    60: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    63: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      10     13     17     Ljava/lang/IllegalArgumentException;
            //  6      24     27     31     Ljava/lang/IllegalArgumentException;
            //  17     43     46     50     Ljava/lang/IllegalArgumentException;
            //  31     55     55     59     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
        };
        final Function function2 = file -> {
            final File file2 = new File(file, "/Contents/bin/" + s);
            try {
                if (file2.exists()) {
                    return file2;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return file;
        };
        try {
            if (ApplicationManager.getApplication() == null) {
                textFieldWithBrowseButton.addActionListener((ActionListener)new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent actionEvent) {
                        final JFileChooser fileChooser = new JFileChooser(textFieldWithBrowseButton.getText());
                        fileChooser.setFileSelectionMode(b ? 0 : 2);
                        fileChooser.setFileHidingEnabled(!CPPToolchainsPanel.isLinux);
                        if (fileChooser.showOpenDialog((Component)textFieldWithBrowseButton) == 0) {
                            File selectedFile = fileChooser.getSelectedFile();
                            if (selectedFile != null) {
                                if (function.fun((Object)selectedFile)) {
                                    selectedFile = (File)function2.fun((Object)selectedFile);
                                }
                                TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT.setText((Component)textFieldWithBrowseButton.getChildComponent(), selectedFile.getAbsolutePath());
                            }
                        }
                    }
                });
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        boolean b2 = false;
        boolean b3 = false;
        Label_0196: {
            try {
                b2 = true;
                if (!b) {
                    b3 = true;
                    break Label_0196;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            b3 = false;
        }
        final FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(b2, b3, false, false, false, false) {
            public boolean isFileSelectable(final VirtualFile virtualFile) {
                return super.isFileSelectable(virtualFile) || (boolean)function.fun((Object)VfsUtilCore.virtualToIoFile(virtualFile));
            }
        };
        fileChooserDescriptor.setTitle(title);
        textFieldWithBrowseButton.addBrowseFolderListener((TextBrowseFolderListener)new TextBrowseFolderListener(fileChooserDescriptor, null) {
            protected void onFileChosen(@NotNull VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenFile", "com/jetbrains/cidr/cpp/CPPToolchainsPanel$7", "onFileChosen"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final File virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
                if (function.fun((Object)virtualToIoFile)) {
                    final VirtualFile fileByIoFile = VfsUtil.findFileByIoFile((File)function2.fun((Object)virtualToIoFile), false);
                    if (fileByIoFile != null) {
                        virtualFile = fileByIoFile;
                    }
                }
                super.onFileChosen(virtualFile);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    private void a(final TextFieldWithBrowseButton textFieldWithBrowseButton, final GridBag gridBag, final String s, @NotNull final NullableFunction<File, String> nullableFunction, @Nullable final NullableConsumer<String> nullableConsumer, final CPPToolchainsCheckPanel.UpdateType updateType) {
        try {
            if (nullableFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "readVersion", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "addVersionField"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final JBLabel jbLabel = new JBLabel(" ", UIUtil.ComponentStyle.SMALL);
        this.add((Component)jbLabel, gridBag.anchor(1280).insets(0, -1, -1, -1));
        textFieldWithBrowseButton.getTextField().getDocument().addDocumentListener((DocumentListener)new DocumentAdapter() {
            final /* synthetic */ Alarm val$alarm = CPPToolchainsPanel.this.e();
            
            protected void textChanged(final DocumentEvent documentEvent) {
                CPPToolchainsPanel.this.a(updateType);
                final String preparePath = CPPToolchains.preparePath(textFieldWithBrowseButton.getText());
                try {
                    this.val$alarm.cancelAllRequests();
                    final NullableFunction val$readVersion;
                    final String val$tool;
                    final JBLabel val$versionField;
                    final IllegalArgumentException ex;
                    final StringBuilder sb;
                    final String s;
                    this.val$alarm.addRequest(() -> {
                        val$readVersion = nullableFunction;
                        val$tool = s;
                        val$versionField = jbLabel;
                        try {
                            if (val$readVersion == null) {
                                new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "readVersion", "com/jetbrains/cidr/cpp/CPPToolchainsPanel$8", "lambda$textChanged$1"));
                                throw ex;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        sb = new StringBuilder();
                        Label_0111_1: {
                            try {
                                if (s == null) {
                                    sb.append(' ');
                                    break Label_0111_1;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            sb.append(val$tool).append(" ").append(b((String)val$readVersion.fun((Object)new File(s))));
                        }
                        UIUtil.invokeLaterIfNeeded(() -> val$versionField.setText(sb.toString()));
                        return;
                    }, 300);
                    if (nullableConsumer != null) {
                        nullableConsumer.consume((Object)preparePath);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    @NotNull
    private Alarm e() {
        Alarm alarm;
        try {
            alarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, (Disposable)this);
            if (alarm == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "createAlarm"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return alarm;
    }
    
    @NotNull
    private static String b(@Nullable final String s) {
        String s2 = null;
        Label_0032: {
            try {
                if (s != null) {
                    final String string;
                    s2 = (string = "version: " + s);
                    break Label_0032;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            String string;
            s2 = (string = "not found");
            try {
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "getVersionText"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return s2;
    }
    
    public boolean isModified(@NotNull final CPPToolchains p0) {
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
        //    18: ldc             "settings"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/CPPToolchainsPanel"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isModified"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.getState:()Lcom/jetbrains/cidr/cpp/CPPToolchains$Settings;
        //    48: astore_2       
        //    49: aload_2        
        //    50: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getWinEnvironment:()Lcom/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment;
        //    53: aload_0        
        //    54: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchainsPanel.getSelectedWinEnvironment:()Lcom/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment;
        //    57: if_acmpne       258
        //    60: aload_2        
        //    61: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getMSVCHomePath:()Ljava/lang/String;
        //    64: aload_0        
        //    65: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myMSVCPath:Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;
        //    68: invokevirtual   com/intellij/openapi/ui/TextFieldWithBrowseButton.getText:()Ljava/lang/String;
        //    71: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.preparePath:(Ljava/lang/String;)Ljava/lang/String;
        //    74: invokestatic    com/intellij/openapi/util/io/FileUtil.pathsEqual:(Ljava/lang/String;Ljava/lang/String;)Z
        //    77: ifeq            258
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    86: athrow         
        //    87: aload_2        
        //    88: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getMinGWHomePath:()Ljava/lang/String;
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myMinGWPath:Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;
        //    95: invokevirtual   com/intellij/openapi/ui/TextFieldWithBrowseButton.getText:()Ljava/lang/String;
        //    98: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.preparePath:(Ljava/lang/String;)Ljava/lang/String;
        //   101: invokestatic    com/intellij/openapi/util/io/FileUtil.pathsEqual:(Ljava/lang/String;Ljava/lang/String;)Z
        //   104: ifeq            258
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   113: athrow         
        //   114: aload_2        
        //   115: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getCygwinHomePath:()Ljava/lang/String;
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myCygwinPath:Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;
        //   122: invokevirtual   com/intellij/openapi/ui/TextFieldWithBrowseButton.getText:()Ljava/lang/String;
        //   125: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.preparePath:(Ljava/lang/String;)Ljava/lang/String;
        //   128: invokestatic    com/intellij/openapi/util/io/FileUtil.pathsEqual:(Ljava/lang/String;Ljava/lang/String;)Z
        //   131: ifeq            258
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   140: athrow         
        //   141: aload_2        
        //   142: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.isUseBundledCMake:()Z
        //   145: aload_0        
        //   146: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myUseBundledCMake:Lcom/intellij/ui/components/JBRadioButton;
        //   149: invokevirtual   com/intellij/ui/components/JBRadioButton.isSelected:()Z
        //   152: if_icmpne       258
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   161: athrow         
        //   162: aload_2        
        //   163: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getSpecifiedCMakeExecutablePath:()Ljava/lang/String;
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myCMakePath:Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;
        //   170: invokevirtual   com/intellij/openapi/ui/TextFieldWithBrowseButton.getText:()Ljava/lang/String;
        //   173: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.preparePath:(Ljava/lang/String;)Ljava/lang/String;
        //   176: invokestatic    com/intellij/openapi/util/io/FileUtil.pathsEqual:(Ljava/lang/String;Ljava/lang/String;)Z
        //   179: ifeq            258
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   188: athrow         
        //   189: aload_2        
        //   190: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.isUseBundledLLDB:()Z
        //   193: aload_0        
        //   194: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myUseBundledLLDB:Lcom/intellij/ui/components/JBRadioButton;
        //   197: invokevirtual   com/intellij/ui/components/JBRadioButton.isSelected:()Z
        //   200: if_icmpne       258
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   209: athrow         
        //   210: aload_2        
        //   211: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.isUseBundledGDB:()Z
        //   214: aload_0        
        //   215: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myUseBundledGDB:Lcom/intellij/ui/components/JBRadioButton;
        //   218: invokevirtual   com/intellij/ui/components/JBRadioButton.isSelected:()Z
        //   221: if_icmpne       258
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   230: athrow         
        //   231: aload_2        
        //   232: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getSpecifiedGDBExecutablePath:()Ljava/lang/String;
        //   235: aload_0        
        //   236: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myGDBPath:Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;
        //   239: invokevirtual   com/intellij/openapi/ui/TextFieldWithBrowseButton.getText:()Ljava/lang/String;
        //   242: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.preparePath:(Ljava/lang/String;)Ljava/lang/String;
        //   245: invokestatic    com/intellij/openapi/util/io/FileUtil.pathsEqual:(Ljava/lang/String;Ljava/lang/String;)Z
        //   248: ifne            266
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   257: athrow         
        //   258: iconst_1       
        //   259: goto            267
        //   262: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   265: athrow         
        //   266: iconst_0       
        //   267: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     80     83     87     Ljava/lang/IllegalArgumentException;
        //  60     107    110    114    Ljava/lang/IllegalArgumentException;
        //  87     134    137    141    Ljava/lang/IllegalArgumentException;
        //  114    155    158    162    Ljava/lang/IllegalArgumentException;
        //  141    182    185    189    Ljava/lang/IllegalArgumentException;
        //  162    203    206    210    Ljava/lang/IllegalArgumentException;
        //  189    224    227    231    Ljava/lang/IllegalArgumentException;
        //  210    251    254    258    Ljava/lang/IllegalArgumentException;
        //  231    262    262    266    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    
    public void apply(@NotNull final CPPToolchains cppToolchains) {
        try {
            if (cppToolchains == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        cppToolchains.getState().setWinEnvironment(this.getSelectedWinEnvironment());
        cppToolchains.getState().setMSVCHomePath(this.myMSVCPath.getText());
        cppToolchains.getState().setMinGWHomePath(this.myMinGWPath.getText());
        cppToolchains.getState().setCygwinHomePath(this.myCygwinPath.getText());
        cppToolchains.getState().setUseBundledCMake(this.myUseBundledCMake.isSelected());
        cppToolchains.getState().setSpecifiedCMakeExecutablePath(this.myCMakePath.getText());
        cppToolchains.getState().setUseBundledLLDB(this.myUseBundledLLDB.isSelected());
        cppToolchains.getState().setUseBundledGDB(this.myUseBundledGDB.isSelected());
        cppToolchains.getState().setSpecifiedGDBExecutablePath(this.myGDBPath.getText());
    }
    
    public void reset(@NotNull final CPPToolchains cppToolchains) {
        try {
            if (cppToolchains == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "reset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            this.myUpdating = true;
            this.a(cppToolchains);
        }
        finally {
            this.myUpdating = false;
            this.c();
            this.a(CPPToolchainsCheckPanel.UpdateType.All);
        }
    }
    
    private void a(@NotNull final CPPToolchains cppToolchains) {
        try {
            if (cppToolchains == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "doReset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CPPToolchains.Settings state = cppToolchains.getState();
        final CPPToolchains.WinEnvironment winEnvironment = state.getWinEnvironment();
        Label_0186: {
            Label_0120: {
                JBRadioButton myUseCygwin = null;
                Label_0097: {
                    JBRadioButton myUseMinGW = null;
                    Label_0074: {
                        JBRadioButton myUseMSVC;
                        try {
                            myUseMSVC = this.myUseMSVC;
                            if (winEnvironment == CPPToolchains.WinEnvironment.MSVC) {
                                final boolean selected = true;
                                break Label_0074;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        final boolean selected = false;
                        try {
                            myUseMSVC.setSelected(selected);
                            myUseMinGW = this.myUseMinGW;
                            if (winEnvironment == CPPToolchains.WinEnvironment.MINGW) {
                                final boolean selected2 = true;
                                break Label_0097;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    final boolean selected2 = false;
                    try {
                        myUseMinGW.setSelected(selected2);
                        myUseCygwin = this.myUseCygwin;
                        if (winEnvironment == CPPToolchains.WinEnvironment.CYGWIN) {
                            final boolean selected3 = true;
                            break Label_0120;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                final boolean selected3 = false;
                try {
                    myUseCygwin.setSelected(selected3);
                    this.myMSVCPath.setText(state.getMSVCHomePath());
                    this.myMinGWPath.setText(state.getMinGWHomePath());
                    this.myCygwinPath.setText(state.getCygwinHomePath());
                    if (state.isUseBundledCMake()) {
                        this.myUseBundledCMake.setSelected(true);
                        break Label_0186;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            this.myUseSpecifiedCMake.setSelected(true);
        }
        this.myCMakePath.setText(state.getSpecifiedCMakeExecutablePath());
        final boolean lldbAvailable = cppToolchains.isLLDBAvailable();
        Label_0285: {
            Label_0255: {
                Label_0240: {
                    try {
                        this.myUseBundledLLDB.setVisible(lldbAvailable);
                        this.myUseBundledLLDB.setEnabled(lldbAvailable);
                        if (!lldbAvailable) {
                            break Label_0255;
                        }
                        final CPPToolchains.Settings settings = state;
                        final boolean b = settings.isUseBundledLLDB();
                        if (b) {
                            break Label_0240;
                        }
                        break Label_0255;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    try {
                        final CPPToolchains.Settings settings = state;
                        final boolean b = settings.isUseBundledLLDB();
                        if (b) {
                            this.myUseBundledLLDB.setSelected(true);
                            break Label_0285;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                try {
                    if (state.isUseBundledGDB()) {
                        this.myUseBundledGDB.setSelected(true);
                        break Label_0285;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
            }
            this.myUseSpecifiedGDB.setSelected(true);
        }
        this.myGDBPath.setText(state.getSpecifiedGDBExecutablePath());
    }
    
    @Nullable
    protected CPPToolchains.WinEnvironment getSelectedWinEnvironment() {
        try {
            if (this.myUseMSVC.isSelected()) {
                return CPPToolchains.WinEnvironment.MSVC;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myUseMinGW.isSelected()) {
                return CPPToolchains.WinEnvironment.MINGW;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (this.myUseCygwin.isSelected()) {
                return CPPToolchains.WinEnvironment.CYGWIN;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Nullable
    private CidrToolSet d() {
        CidrToolSet create = null;
        if (CPPToolchainsPanel.isWindows) {
            final CPPToolchains.WinEnvironment selectedWinEnvironment = this.getSelectedWinEnvironment();
            String s;
            if (selectedWinEnvironment == CPPToolchains.WinEnvironment.MSVC) {
                s = this.myMSVCPath.getText();
            }
            else if (selectedWinEnvironment == CPPToolchains.WinEnvironment.MINGW) {
                s = this.myMinGWPath.getText();
            }
            else {
                if (selectedWinEnvironment != CPPToolchains.WinEnvironment.CYGWIN) {
                    return null;
                }
                s = this.myCygwinPath.getText();
            }
            create = selectedWinEnvironment.create(new File(StringUtil.notNullize(CPPToolchains.preparePath(s))));
        }
        return create;
    }
    
    private void c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myUpdating:Z
        //     4: ifeq            12
        //     7: return         
        //     8: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    11: athrow         
        //    12: aload_0        
        //    13: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsPanel.d:()Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //    16: astore_1       
        //    17: aload_1        
        //    18: ifnull          43
        //    21: aload_1        
        //    22: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.forceToolSetGDB:()Z
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    42: athrow         
        //    43: iconst_0       
        //    44: istore_2       
        //    45: aload_1        
        //    46: invokestatic    com/jetbrains/cidr/cpp/toolchains/MSVC.isDebugSupportDisabled:(Lcom/jetbrains/cidr/toolchains/CidrToolSet;)Z
        //    49: istore_3       
        //    50: iload_2        
        //    51: ifne            65
        //    54: iload_3        
        //    55: ifeq            73
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    64: athrow         
        //    65: iconst_1       
        //    66: goto            74
        //    69: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    72: athrow         
        //    73: iconst_0       
        //    74: istore          4
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myEnvironmentUpdateAlarm:Lcom/intellij/util/Alarm;
        //    80: invokevirtual   com/intellij/util/Alarm.cancelAllRequests:()I
        //    83: pop            
        //    84: aload_0        
        //    85: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myEnvironmentUpdateAlarm:Lcom/intellij/util/Alarm;
        //    88: aload_0        
        //    89: aload_1        
        //    90: iload_2        
        //    91: iload_3        
        //    92: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/CPPToolchainsPanel;Lcom/jetbrains/cidr/toolchains/CidrToolSet;ZZ)Ljava/lang/Runnable;
        //    97: sipush          300
        //   100: invokevirtual   com/intellij/util/Alarm.addRequest:(Ljava/lang/Runnable;I)V
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myGeneralComponents:Ljava/util/Map;
        //   107: ldc             "GDB"
        //   109: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   114: checkcast       Ljava/util/List;
        //   117: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   122: astore          5
        //   124: aload           5
        //   126: invokeinterface java/util/Iterator.hasNext:()Z
        //   131: ifeq            168
        //   134: aload           5
        //   136: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   141: checkcast       Ljava/awt/Component;
        //   144: astore          6
        //   146: aload           6
        //   148: iload           4
        //   150: ifne            161
        //   153: iconst_1       
        //   154: goto            162
        //   157: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   160: athrow         
        //   161: iconst_0       
        //   162: invokevirtual   java/awt/Component.setVisible:(Z)V
        //   165: goto            124
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myEnvComponents:Ljava/util/Map;
        //   172: ldc             "GDB"
        //   174: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   179: checkcast       Ljava/util/List;
        //   182: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   187: astore          5
        //   189: aload           5
        //   191: invokeinterface java/util/Iterator.hasNext:()Z
        //   196: ifeq            221
        //   199: aload           5
        //   201: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   206: checkcast       Ljava/awt/Component;
        //   209: astore          6
        //   211: aload           6
        //   213: iload           4
        //   215: invokevirtual   java/awt/Component.setVisible:(Z)V
        //   218: goto            189
        //   221: aload_0        
        //   222: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myListener:Lcom/jetbrains/cidr/cpp/CPPToolchainsConfigurable$EnvironmentChangeListener;
        //   225: ifnull          248
        //   228: aload_0        
        //   229: getfield        com/jetbrains/cidr/cpp/CPPToolchainsPanel.myListener:Lcom/jetbrains/cidr/cpp/CPPToolchainsConfigurable$EnvironmentChangeListener;
        //   232: aload_0        
        //   233: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchainsPanel.getSelectedWinEnvironment:()Lcom/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment;
        //   236: invokeinterface com/jetbrains/cidr/cpp/CPPToolchainsConfigurable$EnvironmentChangeListener.environmentChanged:(Lcom/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment;)V
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   247: athrow         
        //   248: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      8      12     Ljava/lang/IllegalArgumentException;
        //  17     28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        //  50     58     61     65     Ljava/lang/IllegalArgumentException;
        //  54     69     69     73     Ljava/lang/IllegalArgumentException;
        //  146    157    157    161    Ljava/lang/IllegalArgumentException;
        //  221    241    244    248    Ljava/lang/IllegalArgumentException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @NotNull
    private static String a(final String s, @Nullable final String s2, final boolean b) {
        Label_0063: {
            String s5 = null;
            Label_0028: {
                try {
                    if (s2 != null) {
                        break Label_0063;
                    }
                    final String s3 = "settings.select.UseBundled.doesNotWork";
                    final int n = 1;
                    final Object[] array = new Object[n];
                    final int n2 = 0;
                    final String s4 = s;
                    array[n2] = s4;
                    s5 = CPPBundle.message(s3, array);
                    if (s5 == null) {
                        break Label_0028;
                    }
                    return s5;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final String s3 = "settings.select.UseBundled.doesNotWork";
                    final int n = 1;
                    final Object[] array = new Object[n];
                    final int n2 = 0;
                    final String s4 = s;
                    array[n2] = s4;
                    s5 = CPPBundle.message(s3, array);
                    if (s5 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "getBundledToolText"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return s5;
        }
        String s6 = CPPBundle.message("settings.select.UseBundled", s) + " " + s2;
        String s7 = null;
        Label_0136: {
            try {
                if (!b || !CPPToolchainsPanel.isMac) {
                    break Label_0136;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            s6 += " (Recommended)";
            try {
                s7 = s6;
                if (s7 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "getBundledToolText"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return s7;
    }
    
    private void a(@NotNull final CPPToolchainsCheckPanel.UpdateType updateType) {
        try {
            if (updateType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "doCheck"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myUpdating) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final CPPToolchainsCheckSettings cppToolchainsCheckSettings = new CPPToolchainsCheckSettings();
        cppToolchainsCheckSettings.environment = this.getSelectedWinEnvironment();
        String s;
        if (cppToolchainsCheckSettings.environment == CPPToolchains.WinEnvironment.MSVC) {
            s = this.myMSVCPath.getText();
        }
        else if (cppToolchainsCheckSettings.environment == CPPToolchains.WinEnvironment.MINGW) {
            s = this.myMinGWPath.getText();
        }
        else {
            s = this.myCygwinPath.getText();
        }
        cppToolchainsCheckSettings.toolSetHome = textPathToFile(s);
        cppToolchainsCheckSettings.useBundledCMake = this.myUseBundledCMake.isSelected();
        cppToolchainsCheckSettings.cmakeFile = textPathToFile(this.myCMakePath.getText());
        cppToolchainsCheckSettings.useBundledLLDB = this.myUseBundledLLDB.isSelected();
        cppToolchainsCheckSettings.useBundledGDB = this.myUseBundledGDB.isSelected();
        cppToolchainsCheckSettings.gdbFile = textPathToFile(this.myGDBPath.getText());
        this.myCheckPanel.scheduleCheck(cppToolchainsCheckSettings, updateType);
    }
    
    @Nullable
    public static File textPathToFile(@Nullable final String s) {
        final String preparePath = CPPToolchains.preparePath(s);
        try {
            if (preparePath == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new File(preparePath);
    }
    
    public void autoRecheckWithApplication() {
        ApplicationManager.getApplication().getMessageBus().connect((Disposable)this).subscribe(ApplicationActivationListener.TOPIC, (Object)new ApplicationActivationListener() {
            public void applicationActivated(final IdeFrame ideFrame) {
                CPPToolchainsPanel.this.b();
            }
            
            public void applicationDeactivated(final IdeFrame ideFrame) {
                CPPToolchainsPanel.this.a();
            }
        });
    }
    
    public void autoRecheckWithWizard(@NotNull final Window window) {
        try {
            if (window == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "welcomeWizard", "com/jetbrains/cidr/cpp/CPPToolchainsPanel", "autoRecheckWithWizard"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowActivated(final WindowEvent windowEvent) {
                CPPToolchainsPanel.this.b();
            }
            
            @Override
            public void windowDeactivated(final WindowEvent windowEvent) {
                CPPToolchainsPanel.this.a();
            }
        };
        window.addWindowListener(windowAdapter);
        Disposer.register((Disposable)this, (Disposable)new Disposable() {
            public void dispose() {
                window.removeWindowListener(windowAdapter);
            }
        });
    }
    
    private void a() {
        this.myAutoRecheck = true;
        this.myDeactivatedTime = System.currentTimeMillis();
    }
    
    private void b() {
        try {
            if (!this.myAutoRecheck) {
                return;
            }
            this.myAutoRecheck = false;
            if (System.currentTimeMillis() - this.myDeactivatedTime <= 2000L) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            this.myUpdating = true;
            this.myMSVCPath.setText(this.myMSVCPath.getText());
            this.myMinGWPath.setText(this.myMinGWPath.getText());
            this.myCygwinPath.setText(this.myCygwinPath.getText());
            this.myCMakePath.setText(this.myCMakePath.getText());
            this.myGDBPath.setText(this.myGDBPath.getText());
        }
        finally {
            this.myUpdating = false;
        }
        this.a(CPPToolchainsCheckPanel.UpdateType.All);
    }
    
    public void dispose() {
    }
    
    static {
        isWindows = SystemInfo.isWindows;
        isMac = SystemInfo.isMac;
        isLinux = SystemInfo.isLinux;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
