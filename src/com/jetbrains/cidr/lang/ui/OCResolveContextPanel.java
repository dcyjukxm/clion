// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.JComponent;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import com.intellij.openapi.actionSystem.ex.ActionUtil;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.workspace.OCResolveLanguageAndConfiguration;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.progress.util.ReadTask;
import com.intellij.concurrency.SensitiveProgressWrapper;
import com.jetbrains.cidr.lang.psi.OCConfigurationOwner;
import com.intellij.util.ui.UIUtil;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import com.intellij.ui.GuiUtils;
import com.intellij.openapi.application.ModalityState;
import java.util.Iterator;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.actionSystem.AnAction;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import java.util.Collection;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.awt.RelativePoint;
import java.awt.Point;
import com.intellij.ide.DataManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextListener;
import com.intellij.openapi.project.DumbService;
import java.awt.event.MouseEvent;
import com.intellij.ui.ClickListener;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.ui.AsyncProcessIcon;
import javax.swing.JLabel;
import com.intellij.openapi.wm.impl.status.TextPanel;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedWidget;

public class OCResolveContextPanel extends EditorBasedWidget implements StatusBarWidget.Multiframe, CustomStatusBarWidget
{
    public static final String ID = "ActiveOCBuildConfiguration";
    private final TextPanel myComponent;
    private volatile boolean myActionEnabled;
    private volatile boolean myHasList;
    private JLabel myArrowsIcon;
    private final AsyncProcessIcon myRepoActivityIcon;
    private ProgressIndicator myCurrentProgress;
    @Nullable
    private volatile VirtualFile myCurrentFile;
    private volatile boolean isCalculatingContext;
    
    @NotNull
    private static AsyncProcessIcon a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "createActivityIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final AsyncProcessIcon setUseMask = new AsyncProcessIcon(s).setUseMask(false);
        AsyncProcessIcon asyncProcessIcon;
        try {
            setUseMask.setPaintPassiveIcon(false);
            setUseMask.setOpaque(false);
            setUseMask.suspend();
            asyncProcessIcon = setUseMask;
            if (asyncProcessIcon == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "createActivityIcon"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return asyncProcessIcon;
    }
    
    public OCResolveContextPanel(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "<init>"));
        }
        super(project);
        this.myArrowsIcon = new JLabel(AllIcons.Ide.Statusbar_arrows);
        this.myRepoActivityIcon = a("Resolve Context Activity");
        (this.myComponent = new TextPanel() {
            @Override
            public void doLayout() {
                super.doLayout();
                final Rectangle bounds = this.getBounds();
                final Insets insets = this.getInsets();
                final Dimension minimumSize = OCResolveContextPanel.this.myRepoActivityIcon.getMinimumSize();
                final int n = (int)minimumSize.getWidth();
                final int n2 = (int)minimumSize.getHeight();
                OCResolveContextPanel.this.myRepoActivityIcon.setBounds(bounds.width - insets.right - n, bounds.height / 2 - n2 / 2, n, n2);
                final Icon icon = OCResolveContextPanel.this.myArrowsIcon.getIcon();
                final int iconWidth = icon.getIconWidth();
                final int iconHeight = icon.getIconHeight();
                OCResolveContextPanel.this.myArrowsIcon.setBounds(bounds.width - insets.right - iconWidth - 2, bounds.height / 2 - iconHeight / 2, iconWidth, iconHeight);
            }
        }).add(this.myArrowsIcon);
        this.myComponent.add((Component)this.myRepoActivityIcon);
        this.myComponent.setRightPadding((int)(this.myRepoActivityIcon.getMinimumSize().getWidth() + 6.0));
        new ClickListener() {
            public boolean onClick(@NotNull final MouseEvent mouseEvent, final int n) {
                try {
                    if (mouseEvent == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$2", "onClick"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (OCResolveContextPanel.this.myHasList) {
                        OCResolveContextPanel.this.a(mouseEvent);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return true;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }.installOn((Component)this.myComponent);
        this.myComponent.setBorder(StatusBarWidget.WidgetBorder.INSTANCE);
        final OCResolveContextPanel.1Listener 1Listener = new OCResolveContextPanel.1Listener();
        this.myConnection.subscribe(DumbService.DUMB_MODE, (Object)1Listener);
        this.myConnection.subscribe((Topic)OCInclusionContextListener.TOPIC, (Object)1Listener);
        this.scheduleUpdate();
    }
    
    public void selectionChanged(@NotNull final FileEditorManagerEvent fileEditorManagerEvent) {
        try {
            if (fileEditorManagerEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "event", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "selectionChanged"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.scheduleUpdate();
    }
    
    public void fileOpened(@NotNull final FileEditorManager fileEditorManager, @NotNull final VirtualFile virtualFile) {
        try {
            if (fileEditorManager == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "fileOpened"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "fileOpened"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.scheduleUpdate();
    }
    
    public void fileClosed(@NotNull final FileEditorManager fileEditorManager, @NotNull final VirtualFile virtualFile) {
        try {
            if (fileEditorManager == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "fileClosed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "fileClosed"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.scheduleUpdate();
    }
    
    public StatusBarWidget copy() {
        return (StatusBarWidget)new OCResolveContextPanel(this.myProject);
    }
    
    @NotNull
    public String ID() {
        String s;
        try {
            s = "ActiveOCBuildConfiguration";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "ID"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    public TextPanel getComponent() {
        return this.myComponent;
    }
    
    public StatusBarWidget.WidgetPresentation getPresentation(@NotNull final StatusBarWidget.PlatformType platformType) {
        try {
            if (platformType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "getPresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    private void a(final MouseEvent mouseEvent) {
        final ListPopup popupStep = this.getPopupStep(DataManager.getInstance().getDataContext((Component)this.myStatusBar));
        try {
            if (popupStep == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        popupStep.show(new RelativePoint(mouseEvent.getComponent(), new Point(0, -popupStep.getContent().getPreferredSize().height)));
        Disposer.register((Disposable)this, (Disposable)popupStep);
    }
    
    public ListPopup getPopupStep(@NotNull final DataContext dataContext) {
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "getPopupStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final VirtualFile selectedFile = this.getSelectedFile();
        try {
            if (selectedFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final PsiFile file = PsiManager.getInstance(this.myProject).findFile(selectedFile);
        try {
            if (!a(file)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final Pair<Collection<? extends OCResolveConfiguration>, Boolean> a = a(file, true, null);
        final DefaultActionGroup defaultActionGroup = new DefaultActionGroup();
        try {
            if (!(boolean)a.second) {
                defaultActionGroup.addSeparator("All project contexts are listed");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final Iterator iterator = ((Collection)a.first).iterator();
        while (iterator.hasNext()) {
            defaultActionGroup.add((AnAction)new ConfigurationItem(file, iterator.next()));
        }
        final ListPopup actionGroupPopup = JBPopupFactory.getInstance().createActionGroupPopup("Available Resolve Contexts", (ActionGroup)defaultActionGroup, dataContext, false, false, false, (Runnable)null, 15, anAction -> {
            try {
                if (((ConfigurationItem)anAction).getConfig() == ocResolveConfiguration) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return false;
        });
        actionGroupPopup.setAdText("File will be resolved in the selected context", 10);
        return actionGroupPopup;
    }
    
    private static void a(final PsiFile psiFile, final OCResolveConfiguration ocResolveConfiguration) {
        GuiUtils.invokeLaterIfNeeded(() -> OCInclusionContextUtil.setUserSelectedConfiguration(psiFile, ocResolveConfiguration), ModalityState.defaultModalityState());
    }
    
    public void scheduleUpdate() {
        final VirtualFile myCurrentFile;
        final Object o;
        final PsiFile psiFile;
        UIUtil.invokeLaterIfNeeded(() -> {
            try {
                if (this.myCurrentProgress != null) {
                    this.myCurrentProgress.cancel();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (this.isDisposed()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            this.getSelectedFile();
            Label_0132_1: {
                Label_0063_1: {
                    try {
                        this.myCurrentFile = myCurrentFile;
                        if (this.a()) {
                            if (o != null) {
                                break Label_0063_1;
                            }
                            else {
                                break Label_0132_1;
                            }
                        }
                        else {
                            break Label_0132_1;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    try {
                        if (o != null) {
                            if (!myCurrentFile.isValid()) {
                                break Label_0132_1;
                            }
                        }
                        else {
                            break Label_0132_1;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                PsiManager.getInstance(this.myProject).findFile(myCurrentFile);
                try {
                    if (a(psiFile)) {
                        this.myCurrentProgress = (ProgressIndicator)new EmptyProgressIndicator();
                        this.a(myCurrentFile, null, false, false, null, true);
                        this.a(myCurrentFile, psiFile, this.myCurrentProgress);
                        return;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            this.a(null, null, false, false, null, false);
        });
    }
    
    private static boolean a(final PsiFile psiFile) {
        return psiFile instanceof OCConfigurationOwner;
    }
    
    private void a(@NotNull final VirtualFile virtualFile, @NotNull final PsiFile psiFile, @NotNull final ProgressIndicator progressIndicator) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "scheduleReloadFileContexts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiFile", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "scheduleReloadFileContexts"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalProgress", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "scheduleReloadFileContexts"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final SensitiveProgressWrapper sensitiveProgressWrapper = new SensitiveProgressWrapper(progressIndicator);
        final ReadTask readTask = new ReadTask() {
            final /* synthetic */ OCResolveContextPanel this$0;
            
            @Override
            public void computeInReadAction(@NotNull final ProgressIndicator progressIndicator) {
                try {
                    if (progressIndicator == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$3", "computeInReadAction"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                Label_0110: {
                    Label_0081: {
                        try {
                            if (OCResolveContextPanel.this.isDisposed()) {
                                return;
                            }
                            final ReadTask readTask = this;
                            final OCResolveContextPanel ocResolveContextPanel = readTask.this$0;
                            final Project project = ocResolveContextPanel.myProject;
                            final boolean b = project.isDisposed();
                            if (b) {
                                return;
                            }
                            break Label_0081;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final ReadTask readTask = this;
                            final OCResolveContextPanel ocResolveContextPanel = readTask.this$0;
                            final Project project = ocResolveContextPanel.myProject;
                            final boolean b = project.isDisposed();
                            if (b) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            if (!psiFile.isValid()) {
                                break Label_0110;
                            }
                            final ReadTask readTask2 = this;
                            final OCResolveContextPanel ocResolveContextPanel2 = readTask2.this$0;
                            final boolean b2 = ocResolveContextPanel2.a();
                            if (!b2) {
                                break Label_0110;
                            }
                            break Label_0110;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        final ReadTask readTask2 = this;
                        final OCResolveContextPanel ocResolveContextPanel2 = readTask2.this$0;
                        final boolean b2 = ocResolveContextPanel2.a();
                        if (!b2) {
                            OCResolveContextPanel.this.a(virtualFile, null, false, false, null, true);
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                ((ProgressIndicator)sensitiveProgressWrapper).checkCanceled();
                OCResolveContextPanel.this.isCalculatingContext = true;
                OCResolveLanguageAndConfiguration orCalculateParsedResolveLanguageAndConfiguration;
                try {
                    orCalculateParsedResolveLanguageAndConfiguration = OCInclusionContextUtil.getOrCalculateParsedResolveLanguageAndConfiguration(psiFile);
                }
                finally {
                    OCResolveContextPanel.this.isCalculatingContext = false;
                }
                final OCResolveConfiguration configuration = orCalculateParsedResolveLanguageAndConfiguration.getConfiguration();
                final OCLanguageKind languageKind = orCalculateParsedResolveLanguageAndConfiguration.getLanguageKind();
                ((ProgressIndicator)sensitiveProgressWrapper).checkCanceled();
                OCResolveContextPanel.this.a(virtualFile, configuration, false, false, languageKind, true);
                final Collection collection = (Collection)a(psiFile, false, sensitiveProgressWrapper).first;
                OCResolveContextPanel this$0 = null;
                VirtualFile val$file = null;
                OCResolveConfiguration ocResolveConfiguration2 = null;
                boolean b4 = false;
                boolean b5 = false;
                Label_0342: {
                    Label_0322: {
                        Label_0300: {
                            Label_0278: {
                                try {
                                    ((ProgressIndicator)sensitiveProgressWrapper).checkCanceled();
                                    if (collection.size() <= 0) {
                                        break Label_0300;
                                    }
                                    final Collection collection2 = collection;
                                    final OCResolveConfiguration ocResolveConfiguration = configuration;
                                    final boolean b3 = collection2.contains(ocResolveConfiguration);
                                    if (!b3) {
                                        break Label_0278;
                                    }
                                    break Label_0300;
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw a(ex6);
                                }
                                try {
                                    final Collection collection2 = collection;
                                    final OCResolveConfiguration ocResolveConfiguration = configuration;
                                    final boolean b3 = collection2.contains(ocResolveConfiguration);
                                    if (!b3) {
                                        a(psiFile, (OCResolveConfiguration)ContainerUtil.getFirstItem(collection));
                                        return;
                                    }
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                            }
                            try {
                                this$0 = OCResolveContextPanel.this;
                                val$file = virtualFile;
                                if ((ocResolveConfiguration2 = configuration) != null) {
                                    b4 = true;
                                    break Label_0322;
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw a(ex8);
                            }
                        }
                        b4 = false;
                        try {
                            if (collection.size() > 1) {
                                b5 = true;
                                break Label_0342;
                            }
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                    }
                    b5 = false;
                }
                this$0.a(val$file, ocResolveConfiguration2, b4, b5, languageKind, false);
            }
            
            @Override
            public void onCanceled(@NotNull final ProgressIndicator progressIndicator) {
                try {
                    if (progressIndicator == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$3", "onCanceled"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (!progressIndicator.isCanceled()) {
                        OCResolveContextPanel.this.a(virtualFile, psiFile, progressIndicator);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        try {
            if (ApplicationManager.getApplication().isUnitTestMode()) {
                final IllegalArgumentException ex4;
                final ReadTask readTask2;
                ApplicationManager.getApplication().runReadAction(() -> {
                    try {
                        if (progressIndicator == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalProgress", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "lambda$scheduleReloadFileContexts$3"));
                            throw ex4;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    readTask2.computeInReadAction(progressIndicator);
                });
                return;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        ProgressIndicatorUtils.scheduleWithWriteActionPriority((ProgressIndicator)sensitiveProgressWrapper, readTask);
    }
    
    private void a(@Nullable final VirtualFile p0, @Nullable final OCResolveConfiguration p1, final boolean p2, final boolean p3, @Nullable final OCLanguageKind p4, final boolean p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: iload_3        
        //     3: iload           4
        //     5: aload_2        
        //     6: aload           5
        //     8: iload           6
        //    10: invokedynamic   run:(Lcom/jetbrains/cidr/lang/ui/OCResolveContextPanel;Lcom/intellij/openapi/vfs/VirtualFile;ZZLcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Z)Ljava/lang/Runnable;
        //    15: invokestatic    com/intellij/util/ui/UIUtil.invokeLaterIfNeeded:(Ljava/lang/Runnable;)V
        //    18: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0212_1:
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
    
    private boolean a() {
        Label_0030: {
            try {
                if (DumbService.getInstance(this.myProject).isDumb()) {
                    return false;
                }
                final OCResolveContextPanel ocResolveContextPanel = this;
                final Project project = ocResolveContextPanel.myProject;
                final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project);
                if (b) {
                    break Label_0030;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCResolveContextPanel ocResolveContextPanel = this;
                final Project project = ocResolveContextPanel.myProject;
                final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    private static Pair<String, String> a(@Nullable final OCResolveConfiguration ocResolveConfiguration, @Nullable final OCLanguageKind ocLanguageKind, final boolean b, final boolean b2) {
        Pair create = null;
        Label_0132: {
            String displayName;
            String s;
            if (b) {
                displayName = "Indexing...";
                s = ActionUtil.getUnavailableMessage("Resolve context", false);
            }
            else {
                Label_0056: {
                    Label_0045: {
                        try {
                            if (ocResolveConfiguration != null) {
                                break Label_0056;
                            }
                            if (!b2) {
                                break Label_0045;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        displayName = "Loading...";
                        s = "Loading resolve contexts...";
                        break Label_0132;
                    }
                    displayName = "<no context>";
                    s = "No resolve context for current file";
                    break Label_0132;
                }
                displayName = ocResolveConfiguration.getDisplayName(true);
                s = "Current file is resolved in context of " + ocResolveConfiguration.getDisplayName(false);
                if (ocLanguageKind != null) {
                    s = s + " (" + ocLanguageKind.getDisplayName() + ")";
                }
            }
            try {
                create = Pair.create((Object)("Context: " + displayName), (Object)s);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "getTitleWithDescription"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return (Pair<String, String>)create;
    }
    
    @NotNull
    private static Pair<Collection<? extends OCResolveConfiguration>, Boolean> a(@NotNull final PsiFile psiFile, final boolean b, @Nullable final ProgressIndicator progressIndicator) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiFile", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "getFileConfigurations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        boolean b2 = true;
        Collection<? extends OCResolveConfiguration> collection = OCInclusionContextUtil.getAllBuildConfigurationsForFile(psiFile, progressIndicator);
        if (collection.isEmpty()) {
            b2 = false;
            collection = OCWorkspaceManager.getWorkspace(psiFile.getProject()).getConfigurations();
        }
        Label_0140: {
            try {
                if (b) {
                    break Label_0140;
                }
                final Pair pair = new Pair((Object)collection, (Object)b2);
                if (pair != null) {
                    return (Pair<Collection<? extends OCResolveConfiguration>, Boolean>)pair;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "getFileConfigurations"));
        }
        final ArrayList list = new ArrayList<Comparable>(collection);
        Pair pair2;
        try {
            Collections.sort((List<Comparable>)list);
            pair2 = new Pair((Object)list, (Object)b2);
            if (pair2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel", "getFileConfigurations"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return (Pair<Collection<? extends OCResolveConfiguration>, Boolean>)pair2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class ConfigurationItem extends AnAction
    {
        @NotNull
        private final PsiFile myFile;
        @NotNull
        private final OCResolveConfiguration myConfig;
        
        public ConfigurationItem(@NotNull final PsiFile myFile, @NotNull final OCResolveConfiguration myConfig) {
            if (myFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$ConfigurationItem", "<init>"));
            }
            if (myConfig == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$ConfigurationItem", "<init>"));
            }
            super(myConfig.getDisplayName(false));
            this.myFile = myFile;
            this.myConfig = myConfig;
        }
        
        public void actionPerformed(final AnActionEvent anActionEvent) {
            a(this.myFile, this.myConfig);
        }
        
        @NotNull
        public OCResolveConfiguration getConfig() {
            OCResolveConfiguration myConfig;
            try {
                myConfig = this.myConfig;
                if (myConfig == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$ConfigurationItem", "getConfig"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myConfig;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
