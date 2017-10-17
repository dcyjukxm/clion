// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.DataProvider;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.application.ModalityState;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.openapi.util.Pair;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Collections;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.util.text.DateFormatUtil;
import com.intellij.openapi.util.io.FileUtilRt;
import java.io.IOException;
import com.intellij.openapi.actionSystem.ActionToolbar;
import java.util.ArrayList;
import java.awt.Component;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.ui.IdeBorderFactory;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.intellij.execution.ui.ConsoleView;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleMessageType;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleBuilder;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleViewImpl;
import com.intellij.ide.actions.ContextHelpAction;
import com.jetbrains.cidr.cpp.cmake.actions.ShowCMakeGeneratedDirAction;
import com.jetbrains.cidr.cpp.cmake.actions.OpenCMakeSettingsAction;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.cpp.cmake.actions.EditCMakeCacheAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import javax.swing.JComponent;
import icons.CLionIcons;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.ui.content.Content;
import com.intellij.util.ContentsUtil;
import com.intellij.ui.content.impl.ContentImpl;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.ui.content.ContentManager;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.model.CMakeMessage;
import java.util.List;
import com.intellij.execution.process.ProcessHandler;
import java.io.File;
import com.intellij.openapi.util.Key;

public class CMakeOutputConsole
{
    private static final Key<String> CONSOLE_INDEX;
    private static final Key<String> CONSOLE_TITLE;
    private static final Key<File> CONSOLE_GENERATION_DIR;
    private static final Key<ProcessHandler> CONSOLE_PROCESS_HANDLER;
    private static final Key<List<CMakeMessage>> CONSOLE_MESSAGES;
    private static final Key<String> MESSAGES_COLLECTED;
    private static final Key<String> CURRENT_MESSAGE_TYPE;
    private static final Key<?> CONSOLE_HISTORY_KEY;
    public static final String HELP_ID = "Cmake_Output";
    private final Project myProject;
    private ContentManager myContentManagerCache;
    private boolean isOnceActivated;
    @Nullable
    private ProgressIndicator myCurrentReloadingIndicator;
    
    public CMakeOutputConsole(final Project myProject) {
        this.myProject = myProject;
    }
    
    void listenForChanges() {
        this.myProject.getMessageBus().connect().subscribe((Topic)CMakeWorkspaceListener.TOPIC, (Object)new CMakeWorkspaceListener() {
            @Override
            public void reloadingFinished(final boolean b) {
                CMakeOutputConsole.this.reloadingFinished(b);
            }
        });
    }
    
    public void updateTabs(@Nullable final File file, @NotNull final List<CMakeConfigurationInfo> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "updateTabs"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.a(file, list, null);
    }
    
    private void a(@Nullable final File file, @NotNull final List<CMakeConfigurationInfo> list, @Nullable final Runnable runnable) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "updateTabs"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        final StopCMakeReloadAction stopCMakeReloadAction;
        final ContentManager contentManager;
        int i;
        CMakeConfigurationInfo cMakeConfigurationInfo;
        final String s;
        final JComponent component;
        ContentImpl contentImpl;
        final int n;
        final ContentManager contentManager2;
        final boolean b;
        final Content[] array;
        int length;
        int j;
        Content content;
        this.a(() -> {
            try {
                if (list == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "lambda$updateTabs$0"));
                    throw ex2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            stopCMakeReloadAction = new StopCMakeReloadAction();
            this.a();
            ArrayUtil.indexOf((Object[])contentManager.getContents(), (Object)contentManager.getSelectedContent());
            contentManager.removeAllContents(true);
            if (list.isEmpty()) {
                ContentsUtil.addContent(contentManager, (Content)new ContentImpl(this.a("Output", 0, null, null, null, stopCMakeReloadAction), "Output", false), true);
                this.a(0).print(CPPBundle.message("cmake.console.noConfigs", new Object[0]), ConsoleViewContentType.SYSTEM_OUTPUT);
            }
            else {
                while (i < list.size()) {
                    cMakeConfigurationInfo = list.get(i);
                    cMakeConfigurationInfo.getConfigurationName();
                    this.a(s, i, file, cMakeConfigurationInfo.getGenerationDir(), cMakeConfigurationInfo.getEnvironment(), stopCMakeReloadAction);
                    Label_0240_1: {
                        try {
                            contentImpl = new ContentImpl(component, s, false);
                            if (i == n) {
                                break Label_0240_1;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                    }
                    ContentsUtil.addContent(contentManager2, (Content)contentImpl, b);
                    ++i;
                }
            }
            contentManager.getContents();
            for (length = array.length; j < length; ++j) {
                content = array[j];
                content.putUserData(ToolWindow.SHOW_CONTENT_ICON, (Object)Boolean.TRUE);
                content.setIcon(CLionIcons.CMake);
            }
            try {
                if (runnable != null) {
                    runnable.run();
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        });
    }
    
    public void restoreTabs(@Nullable final File p0, @NotNull final List<CMakeConfigurationInfo> p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "configurations"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "restoreTabs"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_0        
        //    48: iload_3        
        //    49: aload_2        
        //    50: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole;ZLjava/util/List;)Ljava/lang/Runnable;
        //    55: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.a:(Ljava/io/File;Ljava/util/List;Ljava/lang/Runnable;)V
        //    58: return         
        //    Signature:
        //  (Ljava/io/File;Ljava/util/List<Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeConfigurationInfo;>;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    @NotNull
    private JComponent a(@NotNull final String s, final int n, @Nullable final File file, @Nullable final File file2, @Nullable final CidrToolEnvironment cidrToolEnvironment, @NotNull final StopCMakeReloadAction stopCMakeReloadAction) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "doCreateConsole"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (stopCMakeReloadAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopAction", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "doCreateConsole"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final DefaultActionGroup defaultActionGroup = new DefaultActionGroup();
        defaultActionGroup.add(ActionManager.getInstance().getAction("CMake.ReloadCMakeProject"));
        defaultActionGroup.add((AnAction)stopCMakeReloadAction);
        defaultActionGroup.addSeparator();
        defaultActionGroup.add((AnAction)new EditCMakeCacheAction(file2));
        final DefaultActionGroup defaultActionGroup2 = new DefaultActionGroup();
        defaultActionGroup2.setPopup(true);
        defaultActionGroup2.getTemplatePresentation().setIcon(AllIcons.General.SecondaryGroup);
        defaultActionGroup2.add((AnAction)new OpenCMakeSettingsAction(n));
        defaultActionGroup2.add(ActionManager.getInstance().getAction("CMake.ChangeCMakeProjectContentRoot"));
        defaultActionGroup2.add((AnAction)new ShowCMakeGeneratedDirAction(file2));
        defaultActionGroup2.addSeparator();
        defaultActionGroup2.add(ActionManager.getInstance().getAction("CMake.ClearCacheAndReload"));
        defaultActionGroup.add((AnAction)defaultActionGroup2);
        defaultActionGroup.addSeparator();
        defaultActionGroup.add((AnAction)new ContextHelpAction("Cmake_Output"));
        final CMakeConsoleViewImpl cMakeConsoleViewImpl = (CMakeConsoleViewImpl)new CMakeConsoleBuilder(this.myProject, cidrToolEnvironment, file).getConsole();
        Disposer.register((Disposable)this.myProject, (Disposable)cMakeConsoleViewImpl);
        cMakeConsoleViewImpl.addMessageListener(new CMakeConsoleViewImpl.MessageListener() {
            @Override
            public void messageReported(@NotNull final CMakeConsoleMessageType cMakeConsoleMessageType) {
                try {
                    if (cMakeConsoleMessageType == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole$2", "messageReported"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                CMakeOutputConsole.this.a((ConsoleView)cMakeConsoleViewImpl, cMakeConsoleMessageType);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
        final DefaultActionGroup defaultActionGroup3 = new DefaultActionGroup(cMakeConsoleViewImpl.createConsoleActions());
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(IdeBorderFactory.createBorder(4));
        final ActionManager instance = ActionManager.getInstance();
        final ActionToolbar actionToolbar = instance.createActionToolbar("CMakeOutputLeft", (ActionGroup)defaultActionGroup, false);
        final ActionToolbar actionToolbar2 = instance.createActionToolbar("CMakeOutputCenter", (ActionGroup)defaultActionGroup3, false);
        panel.add(actionToolbar.getComponent(), "West");
        panel.add(actionToolbar2.getComponent(), "Center");
        final JComponent component = cMakeConsoleViewImpl.getComponent();
        DataProviderPanel dataProviderPanel;
        try {
            component.add(panel, "West");
            component.putClientProperty(CMakeOutputConsole.CONSOLE_INDEX, n);
            component.putClientProperty(CMakeOutputConsole.CONSOLE_TITLE, s);
            component.putClientProperty(CMakeOutputConsole.CONSOLE_MESSAGES, new ArrayList());
            component.putClientProperty(CMakeOutputConsole.CONSOLE_GENERATION_DIR, file2);
            dataProviderPanel = new DataProviderPanel(cMakeConsoleViewImpl);
            if (dataProviderPanel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "doCreateConsole"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return dataProviderPanel;
    }
    
    private static void a(final ConsoleView consoleView, @NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "logDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "doLoadLogFile"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        ((CMakeConsoleViewImpl)consoleView).dimHighlighting();
        final ConsoleViewContentType consoleViewType = ConsoleViewContentType.getConsoleViewType((Key)CMakeOutputConsole.CONSOLE_HISTORY_KEY);
        try {
            final File a = a(file);
            consoleView.print(FileUtilRt.loadFile(a), consoleViewType);
            consoleView.print("\n" + CPPBundle.message("cmake.console.outputRestored", DateFormatUtil.formatDateTime(a.lastModified())) + "\n", consoleViewType);
        }
        catch (IOException ex2) {
            consoleView.print(CPPBundle.message("cmake.console.noOutput", new Object[0]) + "\n", consoleViewType);
        }
        ((ConsoleViewImpl)consoleView).requestScrollingToEnd();
        ((ConsoleViewImpl)consoleView).addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(final HierarchyEvent hierarchyEvent) {
                if ((hierarchyEvent.getChangeFlags() & 0x4L) != 0x0L) {
                    ((ConsoleViewImpl)consoleView).removeHierarchyListener(this);
                    ((ConsoleViewImpl)consoleView).requestScrollingToEnd();
                }
            }
        });
    }
    
    public void reportMessage(final int n, @NotNull final CMakeMessage cMakeMessage) {
        try {
            if (cMakeMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "reportMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.reportMessages(n, Collections.singletonList(cMakeMessage));
    }
    
    public void reportMessages(final int p0, @NotNull final List<CMakeMessage> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "messages"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "reportMessages"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_0        
        //    46: iload_1        
        //    47: aload_2        
        //    48: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole;ILjava/util/List;)Ljava/lang/Runnable;
        //    53: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.a:(Ljava/lang/Runnable;)V
        //    56: return         
        //    Signature:
        //  (ILjava/util/List<Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;>;)V
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void attachConsoleToProcess(final int n, @NotNull final ProcessHandler processHandler) {
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "attachConsoleToProcess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                final IllegalArgumentException ex2;
                final ConsoleView consoleView;
                this.d(() -> {
                    try {
                        if (processHandler == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "lambda$attachConsoleToProcess$3"));
                            throw ex2;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    this.a(n);
                    consoleView.attachToProcess(processHandler);
                    a(consoleView, processHandler);
                });
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final IllegalArgumentException ex5;
        this.a(() -> {
            try {
                if (processHandler == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "lambda$attachConsoleToProcess$4"));
                    throw ex5;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            a(this.a(n), processHandler);
            return;
        });
        processHandler.addProcessListener((ProcessListener)new ProcessAdapter() {
            public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
                CMakeOutputConsole.this.a(() -> CMakeOutputConsole.this.a(n).print(processEvent.getText(), ConsoleViewContentType.NORMAL_OUTPUT));
            }
        });
    }
    
    public List<Pair<String, List<CMakeMessage>>> getMessages() {
        ApplicationManager.getApplication().assertIsDispatchThread();
        final ArrayList<Pair<String, List<CMakeMessage>>> list = new ArrayList<Pair<String, List<CMakeMessage>>>();
        for (final ConsoleView consoleView : this.getConsoles()) {
            final List<CMakeMessage> b = b(consoleView);
            try {
                if (consoleView.getComponent().getClientProperty(CMakeOutputConsole.MESSAGES_COLLECTED) != Boolean.TRUE) {
                    b.addAll(((CMakeConsoleViewImpl)consoleView).finishAndGetMessages());
                    consoleView.getComponent().putClientProperty(CMakeOutputConsole.MESSAGES_COLLECTED, Boolean.TRUE);
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            list.add((Pair<String, List<CMakeMessage>>)Pair.create((Object)getConsoleTitle(consoleView), (Object)Collections.unmodifiableList((List<?>)b)));
        }
        return (List<Pair<String, List<CMakeMessage>>>)Collections.unmodifiableList((List<?>)list);
    }
    
    @NotNull
    private static File a(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getOutputLogFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        File cMakeFilesProductFile;
        try {
            cMakeFilesProductFile = CMakeGenerator.getCMakeFilesProductFile(file, "log.txt");
            if (cMakeFilesProductFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getOutputLogFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return cMakeFilesProductFile;
    }
    
    void reloadingStarted(@NotNull final ProgressIndicator myCurrentReloadingIndicator) {
        try {
            if (myCurrentReloadingIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "reloadingStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        this.a(() -> {
            try {
                if (myCurrentReloadingIndicator == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "lambda$reloadingStarted$5"));
                    throw ex2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            this.isOnceActivated = false;
            this.myCurrentReloadingIndicator = myCurrentReloadingIndicator;
        });
    }
    
    void reloadingFinished(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_0        
        //     2: iload_1        
        //     3: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole;Z)Ljava/lang/Runnable;
        //     8: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.a:(Ljava/lang/Runnable;)V
        //    11: return         
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
    
    private void a(@NotNull final ConsoleView p0, @NotNull final CMakeConsoleMessageType p1) {
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
        //    18: ldc             "console"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doUpdateTabIconAndActivate"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "type"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doUpdateTabIconAndActivate"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.a:()Lcom/intellij/ui/content/ContentManager;
        //    92: astore_3       
        //    93: aload_3        
        //    94: aload_1        
        //    95: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.c:(Lcom/intellij/execution/ui/ConsoleView;)I
        //    98: invokeinterface com/intellij/ui/content/ContentManager.getContent:(I)Lcom/intellij/ui/content/Content;
        //   103: astore          4
        //   105: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.$assertionsDisabled:Z
        //   108: ifne            135
        //   111: aload           4
        //   113: ifnonnull       135
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   122: athrow         
        //   123: new             Ljava/lang/AssertionError;
        //   126: dup            
        //   127: invokespecial   java/lang/AssertionError.<init>:()V
        //   130: athrow         
        //   131: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   134: athrow         
        //   135: aload_1        
        //   136: invokeinterface com/intellij/execution/ui/ConsoleView.getComponent:()Ljavax/swing/JComponent;
        //   141: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.CURRENT_MESSAGE_TYPE:Lcom/intellij/openapi/util/Key;
        //   144: invokevirtual   javax/swing/JComponent.getClientProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   147: checkcast       Lcom/jetbrains/cidr/cpp/cmake/console/CMakeConsoleMessageType;
        //   150: astore          5
        //   152: aload           5
        //   154: ifnull          176
        //   157: aload           5
        //   159: invokevirtual   com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleMessageType.getLevel:()I
        //   162: aload_2        
        //   163: invokevirtual   com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleMessageType.getLevel:()I
        //   166: if_icmpge       227
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   175: athrow         
        //   176: aload_1        
        //   177: invokeinterface com/intellij/execution/ui/ConsoleView.getComponent:()Ljavax/swing/JComponent;
        //   182: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.CURRENT_MESSAGE_TYPE:Lcom/intellij/openapi/util/Key;
        //   185: aload_2        
        //   186: invokevirtual   javax/swing/JComponent.putClientProperty:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   189: aload_2        
        //   190: getstatic       com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleMessageType.ERROR:Lcom/jetbrains/cidr/cpp/cmake/console/CMakeConsoleMessageType;
        //   193: if_acmpne       213
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   202: athrow         
        //   203: getstatic       com/intellij/icons/AllIcons$General.BalloonError:Ljavax/swing/Icon;
        //   206: goto            216
        //   209: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   212: athrow         
        //   213: getstatic       com/intellij/icons/AllIcons$General.BalloonWarning:Ljavax/swing/Icon;
        //   216: astore          6
        //   218: aload           4
        //   220: aload           6
        //   222: invokeinterface com/intellij/ui/content/Content.setIcon:(Ljavax/swing/Icon;)V
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.isOnceActivated:Z
        //   231: ifne            270
        //   234: aload_0        
        //   235: iconst_1       
        //   236: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.isOnceActivated:Z
        //   239: aload_3        
        //   240: aload           4
        //   242: iconst_0       
        //   243: invokeinterface com/intellij/ui/content/ContentManager.setSelectedContent:(Lcom/intellij/ui/content/Content;Z)V
        //   248: aload_0        
        //   249: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.myProject:Lcom/intellij/openapi/project/Project;
        //   252: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.getToolWindow:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/wm/ToolWindow;
        //   255: aconst_null    
        //   256: iconst_0       
        //   257: iconst_0       
        //   258: invokeinterface com/intellij/openapi/wm/ToolWindow.activate:(Ljava/lang/Runnable;ZZ)V
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   269: athrow         
        //   270: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  105    116    119    123    Ljava/lang/IllegalArgumentException;
        //  111    131    131    135    Ljava/lang/IllegalArgumentException;
        //  152    169    172    176    Ljava/lang/IllegalArgumentException;
        //  157    196    199    203    Ljava/lang/IllegalArgumentException;
        //  176    209    209    213    Ljava/lang/IllegalArgumentException;
        //  227    263    266    270    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0176:
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
    
    @NotNull
    private ContentManager a() {
        ContentManager contentManager = null;
        Label_0035: {
            try {
                if (this.myContentManagerCache != null) {
                    final ContentManager myContentManagerCache;
                    contentManager = (myContentManagerCache = this.myContentManagerCache);
                    break Label_0035;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            ContentManager myContentManagerCache = this.myContentManagerCache = (contentManager = CMakeWorkspace.getToolWindow(this.myProject).getContentManager());
            try {
                if (myContentManagerCache == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getContentManager"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return contentManager;
    }
    
    @NotNull
    private ConsoleView a(final int n) {
        final List<ConsoleView> consoles = this.getConsoles();
        try {
            if (n >= consoles.size()) {
                throw new AssertionError((Object)("Console " + n + " not available among " + ContainerUtil.map((Collection)consoles, consoleView -> getConsoleTitle(consoleView))));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ConsoleView consoleView;
        try {
            consoleView = consoles.get(n);
            if (consoleView == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsole"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return consoleView;
    }
    
    @NotNull
    public List<ConsoleView> getConsoles() {
        List map;
        try {
            ApplicationManager.getApplication().assertIsDispatchThread();
            map = ContainerUtil.map((Object[])this.a().getContents(), content -> ((DataProviderPanel)content.getComponent()).getConsole());
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ConsoleView>)map;
    }
    
    private static int c(@NotNull final ConsoleView consoleView) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleIndex"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (int)consoleView.getComponent().getClientProperty(CMakeOutputConsole.CONSOLE_INDEX);
    }
    
    @NotNull
    public static String getConsoleTitle(@NotNull final ConsoleView consoleView) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleTitle"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String s;
        try {
            s = (String)consoleView.getComponent().getClientProperty(CMakeOutputConsole.CONSOLE_TITLE);
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleTitle"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return s;
    }
    
    @Nullable
    public static File getConsoleGenerationDir(@NotNull final ConsoleView consoleView) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleGenerationDir"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (File)consoleView.getComponent().getClientProperty(CMakeOutputConsole.CONSOLE_GENERATION_DIR);
    }
    
    @Nullable
    private static ProcessHandler d(@NotNull final ConsoleView consoleView) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleProcessHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (ProcessHandler)consoleView.getComponent().getClientProperty(CMakeOutputConsole.CONSOLE_PROCESS_HANDLER);
    }
    
    private static void a(@NotNull final ConsoleView consoleView, @NotNull final ProcessHandler processHandler) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "setConsoleProcessHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "setConsoleProcessHandler"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        consoleView.getComponent().putClientProperty(CMakeOutputConsole.CONSOLE_PROCESS_HANDLER, processHandler);
    }
    
    @NotNull
    public static String getFlushedConsoleText(@NotNull final ConsoleView consoleView) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "console", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getFlushedConsoleText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ConsoleViewImpl consoleViewImpl = (ConsoleViewImpl)consoleView;
        String text;
        try {
            consoleViewImpl.flushDeferredText();
            text = consoleViewImpl.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getFlushedConsoleText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return text;
    }
    
    @NotNull
    private static List<CMakeMessage> b(@NotNull final ConsoleView consoleView) {
        try {
            if (consoleView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleMessages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        List list;
        try {
            list = (List)consoleView.getComponent().getClientProperty(CMakeOutputConsole.CONSOLE_MESSAGES);
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole", "getConsoleMessages"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (List<CMakeMessage>)list;
    }
    
    private void d(final Runnable runnable) {
        ApplicationManager.getApplication().invokeAndWait(() -> {
            try {
                if (this.myProject.isDisposed()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            runnable.run();
        }, ModalityState.any());
    }
    
    private void a(final Runnable runnable) {
        ApplicationManager.getApplication().invokeLater(() -> {
            try {
                if (this.myProject.isDisposed()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            runnable.run();
        }, ModalityState.any());
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CMakeOutputConsole.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        CONSOLE_INDEX = Key.create("CONSOLE_INDEX");
        CONSOLE_TITLE = Key.create("CONSOLE_TITLE");
        CONSOLE_GENERATION_DIR = Key.create("CONSOLE_GENERATION_DIR");
        CONSOLE_PROCESS_HANDLER = Key.create("CONSOLE_PROCESS_HANDLER");
        CONSOLE_MESSAGES = Key.create("CONSOLE_MESSAGES");
        MESSAGES_COLLECTED = Key.create("MESSAGES_COLLECTED");
        CURRENT_MESSAGE_TYPE = Key.create("MESSAGE_TYPE");
        ConsoleViewContentType.registerNewConsoleViewType(CONSOLE_HISTORY_KEY = Key.create("CONSOLE_HISTORY_KEY"), ConsoleViewContentType.LOG_EXPIRED_ENTRY);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class DataProviderPanel extends JPanel implements DataProvider
    {
        private final CMakeConsoleViewImpl myConsole;
        
        private DataProviderPanel(final CMakeConsoleViewImpl myConsole) {
            super(new BorderLayout());
            this.add(this.myConsole = myConsole, "Center");
        }
        
        @NotNull
        public CMakeConsoleViewImpl getConsole() {
            CMakeConsoleViewImpl myConsole;
            try {
                myConsole = this.myConsole;
                if (myConsole == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole$DataProviderPanel", "getConsole"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return myConsole;
        }
        
        @Nullable
        public Object getData(final String s) {
            try {
                if (PlatformDataKeys.HELP_ID.is(s)) {
                    return "Cmake_Output";
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return null;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    private class StopCMakeReloadAction extends DumbAwareAction
    {
        public StopCMakeReloadAction() {
            super(CPPBundle.message("cmake.action.stopReload", new Object[0]), (String)null, AllIcons.Actions.Suspend);
        }
        
        public void update(final AnActionEvent anActionEvent) {
            super.update(anActionEvent);
            anActionEvent.getPresentation().setEnabled(CMakeOutputConsole.this.myCurrentReloadingIndicator != null && CMakeOutputConsole.this.myCurrentReloadingIndicator.isRunning());
        }
        
        public void actionPerformed(final AnActionEvent anActionEvent) {
            if (CMakeOutputConsole.this.myCurrentReloadingIndicator != null) {
                CMakeOutputConsole.this.myCurrentReloadingIndicator.cancel();
            }
        }
    }
}
