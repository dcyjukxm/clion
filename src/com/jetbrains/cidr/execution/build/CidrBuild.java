// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import java.util.concurrent.TimeUnit;
import com.intellij.openapi.util.UserDataHolderEx;
import java.util.concurrent.Semaphore;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.Icon;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.lang.OCLog;
import java.io.FileWriter;
import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.util.Pair;
import com.intellij.notification.Notification;
import com.intellij.ui.SystemNotifications;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.util.ArrayList;
import com.intellij.execution.process.ProcessListener;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.openapi.ui.MessageType;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;
import java.io.File;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.notification.NotificationGroup;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.TransactionGuard;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.ui.content.MessageView;
import com.intellij.util.ui.UIUtil;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.ExecutionResult;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class CidrBuild
{
    public static ExecutionResult<Boolean> execute(@NotNull final Project project, @NotNull final BuildContext buildContext, @NotNull final String s, @NotNull final String text, @NotNull final Runnable runnable) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/CidrBuild", "execute"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (buildContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/build/CidrBuild", "execute"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "taskName", "com/jetbrains/cidr/execution/build/CidrBuild", "execute"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (text == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "progressTitle", "com/jetbrains/cidr/execution/build/CidrBuild", "execute"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "doExecute", "com/jetbrains/cidr/execution/build/CidrBuild", "execute"));
            }
        }
        catch (ExecutionException ex5) {
            throw b((Exception)ex5);
        }
        final Object o = new Object();
        Label_0335: {
            try {
                if (ApplicationManager.getApplication().isHeadlessEnvironment()) {
                    buildContext.indicator = (ProgressIndicator)new EmptyProgressIndicator();
                    break Label_0335;
                }
            }
            catch (ExecutionException ex6) {
                throw b((Exception)ex6);
            }
            final ExecutionResult<ProgressIndicator> executionResult = new ExecutionResult<ProgressIndicator>();
            final IllegalArgumentException ex7;
            final IllegalArgumentException ex9;
            final IllegalArgumentException ex11;
            final ExecutionResult executionResult2;
            final Object o2;
            UIUtil.invokeLaterIfNeeded(() -> {
                try {
                    if (project == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$execute$0"));
                        throw ex7;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
                try {
                    if (s == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "taskName", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$execute$0"));
                        throw ex9;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
                try {
                    if (buildContext == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$execute$0"));
                        throw ex11;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw b(ex12);
                }
                new Task.Backgroundable(project, s, true) {
                    final /* synthetic */ ExecutionResult val$indicatorResult;
                    final /* synthetic */ BuildContext val$context;
                    final /* synthetic */ Object val$processCreationLock;
                    
                    public void run(@NotNull final ProgressIndicator progressIndicator) {
                        try {
                            if (progressIndicator == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/execution/build/CidrBuild$1", "run"));
                            }
                        }
                        catch (InterruptedException ex) {
                            throw b(ex);
                        }
                        this.val$indicatorResult.set(progressIndicator);
                        int n = 0;
                        while (true) {
                            Label_0141: {
                                Label_0078: {
                                    try {
                                        if (this.val$context.myResult.isDone()) {
                                            break;
                                        }
                                        final int n2 = n;
                                        if (n2 == 0) {
                                            break Label_0078;
                                        }
                                        break Label_0141;
                                    }
                                    catch (InterruptedException ex2) {
                                        throw b(ex2);
                                    }
                                    try {
                                        final int n2 = n;
                                        if (n2 != 0) {
                                            break Label_0141;
                                        }
                                        if (!progressIndicator.isCanceled()) {
                                            break Label_0141;
                                        }
                                    }
                                    catch (InterruptedException ex3) {
                                        throw b(ex3);
                                    }
                                }
                                n = 1;
                                synchronized (this.val$processCreationLock) {
                                    final ProcessHandler processHandler = this.val$context.processHandler;
                                    try {
                                        if (processHandler != null) {
                                            processHandler.destroyProcess();
                                        }
                                    }
                                    catch (InterruptedException ex4) {
                                        throw b(ex4);
                                    }
                                }
                                try {
                                    Thread.sleep(100L);
                                    continue;
                                }
                                catch (InterruptedException ex5) {
                                    throw new ProcessCanceledException((Throwable)ex5);
                                }
                            }
                            break;
                        }
                    }
                    
                    private static InterruptedException b(final InterruptedException ex) {
                        return ex;
                    }
                }.queue();
                return;
            });
            try {
                (buildContext.indicator = executionResult.get()).setText(text);
                buildContext.indicator.setText2("");
            }
            catch (ExecutionException exception) {
                buildContext.myResult.setException((Throwable)exception);
                return buildContext.myResult;
            }
        }
        final IllegalArgumentException ex13;
        final IllegalArgumentException ex15;
        final IllegalArgumentException ex17;
        final IllegalArgumentException ex20;
        final IllegalArgumentException ex22;
        final IllegalArgumentException ex24;
        final Object o3;
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                if (buildContext == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$execute$2"));
                    throw ex13;
                }
            }
            catch (IllegalArgumentException ex14) {
                throw b(ex14);
            }
            try {
                if (project == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$execute$2"));
                    throw ex15;
                }
            }
            catch (IllegalArgumentException ex16) {
                throw b(ex16);
            }
            try {
                if (runnable == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "doExecute", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$execute$2"));
                    throw ex17;
                }
            }
            catch (IllegalArgumentException ex18) {
                throw b(ex18);
            }
            try {
                if (!buildContext.waitAndStart()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex19) {
                throw b(ex19);
            }
            TransactionGuard.submitTransaction((Disposable)project, () -> {
                try {
                    if (buildContext == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$null$1"));
                        throw ex20;
                    }
                }
                catch (IllegalArgumentException ex21) {
                    throw b(ex21);
                }
                try {
                    if (project == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$null$1"));
                        throw ex22;
                    }
                }
                catch (IllegalArgumentException ex23) {
                    throw b(ex23);
                }
                try {
                    if (runnable == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "doExecute", "com/jetbrains/cidr/execution/build/CidrBuild", "lambda$null$1"));
                        throw ex24;
                    }
                }
                catch (IllegalArgumentException ex25) {
                    throw b(ex25);
                }
                synchronized (o3) {
                    if (buildContext.indicator.isCanceled()) {
                        buildContext.a(false);
                    }
                    else {
                        MessageView.SERVICE.getInstance(project);
                        FileDocumentManager.getInstance().saveAllDocuments();
                        runnable.run();
                    }
                }
            });
            return;
        });
        return buildContext.myResult;
    }
    
    public static void startProcess(final Project project, @NotNull final NotificationGroup notificationGroup, final String s, final BuildContext buildContext, @Nullable final BuildListener buildListener, final List<File> list) {
        try {
            if (notificationGroup == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "logNotificationGroup", "com/jetbrains/cidr/execution/build/CidrBuild", "startProcess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        buildContext.processHandler.addProcessListener((ProcessListener)new ProcessAdapter() {
            public void processTerminated(final ProcessEvent p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$context:Lcom/jetbrains/cidr/execution/build/CidrBuild$BuildContext;
                //     4: getfield        com/jetbrains/cidr/execution/build/CidrBuild$BuildContext.indicator:Lcom/intellij/openapi/progress/ProgressIndicator;
                //     7: ldc             "build.refreshing"
                //     9: iconst_0       
                //    10: anewarray       Ljava/lang/Object;
                //    13: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    16: invokeinterface com/intellij/openapi/progress/ProgressIndicator.setText:(Ljava/lang/String;)V
                //    21: aload_0        
                //    22: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$context:Lcom/jetbrains/cidr/execution/build/CidrBuild$BuildContext;
                //    25: getfield        com/jetbrains/cidr/execution/build/CidrBuild$BuildContext.indicator:Lcom/intellij/openapi/progress/ProgressIndicator;
                //    28: ldc             ""
                //    30: invokeinterface com/intellij/openapi/progress/ProgressIndicator.setText2:(Ljava/lang/String;)V
                //    35: aload_0        
                //    36: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$toRefresh:Ljava/util/List;
                //    39: aload_0        
                //    40: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$project:Lcom/intellij/openapi/project/Project;
                //    43: aload_1        
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$context:Lcom/jetbrains/cidr/execution/build/CidrBuild$BuildContext;
                //    48: aload_0        
                //    49: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$taskName:Ljava/lang/String;
                //    52: aload_0        
                //    53: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$logNotificationGroup:Lcom/intellij/notification/NotificationGroup;
                //    56: aload_0        
                //    57: getfield        com/jetbrains/cidr/execution/build/CidrBuild$2.val$buildListener:Lcom/jetbrains/cidr/execution/build/BuildListener;
                //    60: invokedynamic   run:(Lcom/intellij/openapi/project/Project;Lcom/intellij/execution/process/ProcessEvent;Lcom/jetbrains/cidr/execution/build/CidrBuild$BuildContext;Ljava/lang/String;Lcom/intellij/notification/NotificationGroup;Lcom/jetbrains/cidr/execution/build/BuildListener;)Ljava/lang/Runnable;
                //    65: invokestatic    com/jetbrains/cidr/execution/build/CidrBuild.refreshFiles:(Ljava/lang/Iterable;Ljava/lang/Runnable;)V
                //    68: return         
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
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
        buildContext.processHandler.startNotify();
    }
    
    public static void refreshFiles(@NotNull final Iterable<File> iterable, @Nullable final Runnable runnable) {
        try {
            if (iterable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/execution/build/CidrBuild", "refreshFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ArrayList list = new ArrayList();
        final Iterator<File> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            final VirtualFile refreshAndFindFileByIoFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile((File)iterator.next());
            try {
                if (refreshAndFindFileByIoFile == null) {
                    continue;
                }
                list.addAll(VfsUtil.markDirty(true, false, new VirtualFile[] { refreshAndFindFileByIoFile }));
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        LocalFileSystem.getInstance().refreshFiles((Iterable)list, true, true, runnable);
    }
    
    static String showFinishMessage(final Project project, @NotNull final NotificationGroup notificationGroup, @NotNull final MessageType messageType, @NotNull final String s, @Nullable final String s2, final long n) {
        try {
            if (notificationGroup == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "logNotificationGroup", "com/jetbrains/cidr/execution/build/CidrBuild", "showFinishMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (messageType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messageType", "com/jetbrains/cidr/execution/build/CidrBuild", "showFinishMessage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "finishMessage", "com/jetbrains/cidr/execution/build/CidrBuild", "showFinishMessage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        StringBuilder append = null;
        String string = null;
        Label_0182: {
            try {
                MessageView.SERVICE.getInstance(project);
                append = new StringBuilder().append(s);
                if (s2 == null) {
                    string = "";
                    break Label_0182;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            string = " with " + s2;
        }
        String s3 = append.append(string).toString();
        if (n > 0L) {
            s3 = s3 + " in " + StringUtil.formatDuration(n);
        }
        final Notification notification = notificationGroup.createNotification(s3, messageType);
        try {
            notification.notify(project);
            if (messageType != MessageType.INFO) {
                ToolWindowManager.getInstance(project).notifyByBalloon(ToolWindowId.MESSAGES_WINDOW, messageType, s3);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        SystemNotifications.getInstance().notify(notification.getGroupId(), StringUtil.capitalizeWords(s, true), StringUtil.notNullize(s2));
        return s3;
    }
    
    static Pair<AnAction, Disposable> initializeBuildLog(@NotNull final BuildContext buildContext, @NotNull final String s) {
        try {
            if (buildContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/build/CidrBuild", "initializeBuildLog"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "logName", "com/jetbrains/cidr/execution/build/CidrBuild", "initializeBuildLog"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        File tempFile = null;
        FileWriter fileWriter = null;
        try {
            tempFile = FileUtil.createTempFile(s, ".log", true);
            fileWriter = new FileWriter(tempFile);
        }
        catch (IOException ex3) {
            OCLog.LOG.error("Cannot create build log file", (Throwable)ex3);
        }
        final File file = tempFile;
        final FileWriter fileWriter2 = fileWriter;
        final AnAction anAction = new AnAction(CidrBundle.message("build.action.showLog", new Object[0]), null, AllIcons.Debugger.Console) {
            public void actionPerformed(final AnActionEvent anActionEvent) {
                try {
                    if (file == null) {
                        return;
                    }
                }
                catch (IOException ex) {
                    throw a(ex);
                }
                try {
                    Runtime.getRuntime().exec("open " + file.getPath());
                }
                catch (IOException ex2) {
                    OCLog.LOG.error("Cannot open build log file: " + file, (Throwable)ex2);
                }
            }
            
            public boolean isDumbAware() {
                return true;
            }
            
            public void update(final AnActionEvent anActionEvent) {
                super.update(anActionEvent);
                anActionEvent.getPresentation().setEnabled(file != null);
            }
            
            private static IOException a(final IOException ex) {
                return ex;
            }
        };
        try {
            if (fileWriter2 != null) {
                buildContext.processHandler.addProcessListener((ProcessListener)new ProcessAdapter() {
                    public void processTerminated(final ProcessEvent processEvent) {
                        try {
                            fileWriter2.close();
                        }
                        catch (IOException ex) {
                            OCLog.LOG.warn((Throwable)ex);
                        }
                    }
                    
                    public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
                        try {
                            fileWriter2.write(processEvent.getText());
                        }
                        catch (IOException ex) {
                            OCLog.LOG.warn((Throwable)ex);
                        }
                    }
                });
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        return (Pair<AnAction, Disposable>)Pair.create((Object)anAction, (Object)new Disposable() {
            public void dispose() {
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.close();
                    }
                    catch (IOException ex) {
                        OCLog.LOG.warn((Throwable)ex);
                    }
                }
                try {
                    if (file != null) {
                        FileUtil.delete(file);
                    }
                }
                catch (IOException ex2) {
                    throw a(ex2);
                }
            }
            
            private static IOException a(final IOException ex) {
                return ex;
            }
        });
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public static class BuildContext
    {
        @NotNull
        protected final ExecutionResult<Boolean> myResult;
        private static final Key<Semaphore> BUILD_SEMAPHORE_KEY;
        private final Project myProject;
        private final Semaphore myBuildSemaphore;
        @NotNull
        public ProgressIndicator indicator;
        @NotNull
        public ProcessHandler processHandler;
        @Nullable
        public String finishMessage;
        @Nullable
        public String finishDetails;
        public final long started;
        public long duration;
        public int errors;
        public int warnings;
        
        public BuildContext(@NotNull final Project myProject) {
            if (myProject == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/CidrBuild$BuildContext", "<init>"));
            }
            this.myResult = new ExecutionResult<Boolean>();
            this.started = System.currentTimeMillis();
            this.myProject = myProject;
            Semaphore myBuildSemaphore = (Semaphore)this.myProject.getUserData((Key)BuildContext.BUILD_SEMAPHORE_KEY);
            if (myBuildSemaphore == null) {
                myBuildSemaphore = (Semaphore)((UserDataHolderEx)this.myProject).putUserDataIfAbsent((Key)BuildContext.BUILD_SEMAPHORE_KEY, (Object)new Semaphore(1));
            }
            this.myBuildSemaphore = myBuildSemaphore;
        }
        
        public boolean waitAndStart() {
            this.indicator.pushState();
            try {
                this.indicator.setText(CidrBundle.message("build.waiting", new Object[0]));
                this.indicator.setText2("");
                while (true) {
                    Label_0070: {
                        try {
                            this.indicator.checkCanceled();
                            final BuildContext buildContext = this;
                            final Semaphore semaphore = buildContext.myBuildSemaphore;
                            final long n = 100L;
                            final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                            final boolean b = semaphore.tryAcquire(n, timeUnit);
                            if (b) {
                                return true;
                            }
                            break Label_0070;
                        }
                        catch (ProcessCanceledException ex) {
                            throw a(ex);
                        }
                        try {
                            final BuildContext buildContext = this;
                            final Semaphore semaphore = buildContext.myBuildSemaphore;
                            final long n = 100L;
                            final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                            final boolean b = semaphore.tryAcquire(n, timeUnit);
                            if (!b) {
                                continue;
                            }
                        }
                        catch (InterruptedException ex2) {
                            throw new ProcessCanceledException();
                        }
                    }
                    break;
                }
            }
            catch (ProcessCanceledException ex3) {
                this.myResult.set(false);
                return false;
            }
            finally {
                this.indicator.popState();
            }
            return true;
        }
        
        private void a(final boolean b) {
            try {
                this.myBuildSemaphore.release();
                this.myResult.set(b);
                if (!this.myProject.isDisposed()) {
                    OCWorkspaceModificationTrackers.getInstance(this.myProject).getBuildsTracker().incModificationCount();
                }
            }
            catch (ProcessCanceledException ex) {
                throw a(ex);
            }
        }
        
        public void error(final Throwable exception) {
            this.myBuildSemaphore.release();
            this.myResult.setException(exception);
        }
        
        static {
            BUILD_SEMAPHORE_KEY = Key.create("BUILD_SEMAPHORE_KEY");
        }
        
        private static ProcessCanceledException a(final ProcessCanceledException ex) {
            return ex;
        }
    }
}
