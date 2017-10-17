// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.util.TimeoutUtil;
import java.util.concurrent.locks.Lock;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsKt;
import com.intellij.openapi.application.ReadAction;
import com.intellij.ui.EditorNotifications;
import java.util.function.Consumer;
import java.util.concurrent.TimeoutException;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.util.concurrency.FutureResult;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.project.DumbAwareRunnable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.util.Set;
import com.jetbrains.cidr.FilesModificationsListener;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.NotNullFunction;
import java.util.Collection;
import com.intellij.util.ui.update.Update;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.editor.event.DocumentEvent;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.editor.Document;
import java.io.File;
import java.util.Map;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.util.Function;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.util.messages.Topic;
import java.util.concurrent.atomic.AtomicLong;
import com.intellij.openapi.application.ApplicationManager;
import javax.swing.JComponent;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import com.intellij.util.concurrency.QueueProcessor;
import com.intellij.util.ui.update.MergingUpdateQueue;
import com.intellij.openapi.progress.BackgroundTaskQueue;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.Disposable;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.atomic.AtomicBoolean;
import com.intellij.openapi.util.Key;

public class CMakeWorkspaceWatcher
{
    public static final Key<Boolean> NEEDS_RELOAD;
    public static final Key<Boolean> SHOULD_CHECK_DOCUMENT_TEXT;
    private static final String READING_CMAKE_PROJECT;
    private static final int DOCUMENT_CHECK_DELAY = 1000;
    @NotNull
    private AtomicBoolean isShutdown;
    @NotNull
    private final Disposable myShutdownDisposable;
    @NotNull
    private final Project myProject;
    @NotNull
    private final CMakeWorkspace myWorkspace;
    @NotNull
    private final BackgroundTaskQueue myReloadsQueue;
    @NotNull
    private final MergingUpdateQueue myChangedDocumentsQueue;
    @NotNull
    private final QueueProcessor<Runnable> mySchedulingQueueProcessor;
    @NotNull
    private final ReadWriteLock myReloadsLock;
    @NotNull
    private final AtomicInteger myWaitingReloadsCount;
    @Nullable
    private volatile ProgressIndicator myRunningReloadTaskIndicator;
    private volatile boolean myReloadInBackgroundInTestsMode;
    
    public CMakeWorkspaceWatcher(@NotNull final Project myProject, @NotNull final CMakeWorkspace myWorkspace) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "<init>"));
        }
        if (myWorkspace == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workspace", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "<init>"));
        }
        this.isShutdown = new AtomicBoolean(false);
        this.myShutdownDisposable = (Disposable)new Disposable() {
            public void dispose() {
            }
        };
        this.mySchedulingQueueProcessor = (QueueProcessor<Runnable>)QueueProcessor.createRunnableQueueProcessor();
        this.myReloadsLock = new ReentrantReadWriteLock(true);
        this.myWaitingReloadsCount = new AtomicInteger();
        this.myProject = myProject;
        this.myWorkspace = myWorkspace;
        this.myReloadsQueue = new BackgroundTaskQueue(myProject, CMakeWorkspaceWatcher.READING_CMAKE_PROJECT);
        this.myChangedDocumentsQueue = new MergingUpdateQueue(this.getClass() + ": Document changes queue", 1000, true, MergingUpdateQueue.ANY_COMPONENT, (Disposable)this.myProject, (JComponent)null, false);
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            this.myChangedDocumentsQueue.setPassThrough(true);
        }
    }
    
    public void listenForChanges() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final AtomicLong atomicLong = new AtomicLong(0L);
        this.myProject.getMessageBus().connect(this.myShutdownDisposable).subscribe((Topic)CMakeWorkspaceListener.TOPIC, (Object)new CMakeWorkspaceListener() {
            @Override
            public void generationStarted() {
                atomicBoolean.set(true);
                atomicInteger.incrementAndGet();
                atomicLong.set(System.currentTimeMillis());
            }
            
            @Override
            public void generationFinished() {
                atomicBoolean.set(false);
                atomicLong.set(System.currentTimeMillis());
            }
            
            @Override
            public void filesRefreshedAfterGeneration() {
                atomicInteger.decrementAndGet();
            }
        });
        final Function function = document -> {
            final VirtualFile file = FileDocumentManager.getInstance().getFile(document);
            try {
                if (file == null) {
                    return null;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            final File virtualToIoFile = VfsUtilCore.virtualToIoFile(file);
            try {
                if (!this.myWorkspace.getCMakeFiles().contains(virtualToIoFile)) {
                    return null;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
            return Pair.create((Object)virtualToIoFile, (Object)file);
        };
        final EditorEventMulticaster eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
        eventMulticaster.addCaretListener((CaretListener)new CaretListener() {
            public void caretPositionChanged(final CaretEvent caretEvent) {
                if (function.fun((Object)caretEvent.getEditor().getDocument()) != null) {
                    CMakeWorkspaceWatcher.this.myChangedDocumentsQueue.restartTimer();
                }
            }
        }, this.myShutdownDisposable);
        eventMulticaster.addDocumentListener((DocumentListener)new DocumentListener() {
            @NotNull
            final Map<File, Document> myChangedDocuments = new THashMap(FileUtil.FILE_HASHING_STRATEGY);
            
            public void documentChanged(final DocumentEvent documentEvent) {
                final Document document = documentEvent.getDocument();
                final Pair pair = (Pair)function.fun((Object)document);
                if (pair == null) {
                    return;
                }
                if (((VirtualFile)pair.second).getModificationStamp() >= document.getModificationStamp()) {
                    return;
                }
                document.putUserData((Key)CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT, (Object)true);
                synchronized (this.myChangedDocuments) {
                    this.myChangedDocuments.put((File)pair.first, document);
                }
                CMakeWorkspaceWatcher.this.b(this::a);
            }
            
            private void a() {
                CMakeWorkspaceWatcher.this.myChangedDocumentsQueue.restartTimer();
                CMakeWorkspaceWatcher.this.myChangedDocumentsQueue.queue((Update)new Update(CMakeWorkspaceWatcher.this) {
                    public void run() {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
                        //     3: aload_0        
                        //     4: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$4$1;)Ljava/lang/Runnable;
                        //     9: invokeinterface com/intellij/openapi/application/Application.runReadAction:(Ljava/lang/Runnable;)V
                        //    14: return         
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063_1:
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
                    
                    private void b() {
                        ((CMakeWorkspaceListener)CMakeWorkspaceWatcher.this.myProject.getMessageBus().syncPublisher((Topic)CMakeWorkspaceListener.TOPIC)).reloadingRescheduled();
                        CMakeWorkspaceWatcher.this.myChangedDocumentsQueue.restartTimer();
                        CMakeWorkspaceWatcher.this.myChangedDocumentsQueue.queue((Update)this);
                    }
                    
                    private boolean a(@NotNull final Collection<File> collection, @NotNull final NotNullFunction<File, FileStampFunctionAdapter> notNullFunction) {
                        try {
                            if (collection == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changedDocuments", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$4$1", "haveValidSyntax"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            if (notNullFunction == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "getFileDocumentStampFunction", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$4$1", "haveValidSyntax"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            if (!ContainerUtil.exists((Iterable)collection, file -> {
                                try {
                                    if (notNullFunction == null) {
                                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "getFileDocumentStampFunction", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$4$1", "lambda$haveValidSyntax$3"));
                                    }
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                final CMakeFile cMakeFile = ((FileStampFunctionAdapter)notNullFunction.fun((Object)file)).getCMakeFile(CMakeWorkspaceWatcher.this.myProject);
                                Label_0086: {
                                    try {
                                        if (cMakeFile == null) {
                                            return false;
                                        }
                                        final PsiElement psiElement = (PsiElement)cMakeFile;
                                        final boolean b = PsiTreeUtil.hasErrorElements(psiElement);
                                        if (b) {
                                            break Label_0086;
                                        }
                                        return false;
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                    try {
                                        final PsiElement psiElement = (PsiElement)cMakeFile;
                                        final boolean b = PsiTreeUtil.hasErrorElements(psiElement);
                                        if (b) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                return false;
                            })) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        return false;
                    }
                    
                    public boolean canEat(final Update update) {
                        return true;
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                });
            }
        }, this.myShutdownDisposable);
        new FilesModificationsListener() {
            @NotNull
            @Override
            protected Set<File> getWatchedFiles() {
                Set<File> cMakeFiles;
                try {
                    cMakeFiles = CMakeWorkspaceWatcher.this.myWorkspace.getCMakeFiles();
                    if (cMakeFiles == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5", "getWatchedFiles"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return cMakeFiles;
            }
            
            @Override
            protected void watchedFilesChanged(@NotNull final Set<File> p0, @NotNull final Set<VirtualFile> p1, final boolean p2, final boolean p3) {
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
                //    18: ldc             "structurallyChangedFiles"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "watchedFilesChanged"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
                //    62: ldc             "filesWithContentChanges"
                //    64: aastore        
                //    65: dup            
                //    66: ldc             1
                //    68: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5"
                //    70: aastore        
                //    71: dup            
                //    72: ldc             2
                //    74: ldc             "watchedFilesChanged"
                //    76: aastore        
                //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    83: athrow         
                //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    87: athrow         
                //    88: iload_3        
                //    89: ifeq            152
                //    92: aload_0        
                //    93: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.val$isGenerationRunning:Ljava/util/concurrent/atomic/AtomicBoolean;
                //    96: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
                //    99: ifeq            114
                //   102: goto            109
                //   105: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   108: athrow         
                //   109: return         
                //   110: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   113: athrow         
                //   114: aload_0        
                //   115: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.val$isGenerationRefreshRunning:Ljava/util/concurrent/atomic/AtomicInteger;
                //   118: invokevirtual   java/util/concurrent/atomic/AtomicInteger.get:()I
                //   121: ifle            152
                //   124: aload_1        
                //   125: aload_0        
                //   126: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.val$lastGenerationTimestamp:Ljava/util/concurrent/atomic/AtomicLong;
                //   129: invokedynamic   value:(Ljava/util/concurrent/atomic/AtomicLong;)Lcom/intellij/openapi/util/Condition;
                //   134: invokestatic    com/intellij/util/containers/ContainerUtil.exists:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Z
                //   137: ifne            152
                //   140: goto            147
                //   143: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   146: athrow         
                //   147: return         
                //   148: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   151: athrow         
                //   152: aload_1        
                //   153: invokeinterface java/util/Set.isEmpty:()Z
                //   158: ifne            240
                //   161: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                //   164: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
                //   167: ifeq            364
                //   170: goto            177
                //   173: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   176: athrow         
                //   177: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                //   180: new             Ljava/lang/StringBuilder;
                //   183: dup            
                //   184: invokespecial   java/lang/StringBuilder.<init>:()V
                //   187: ldc             "CMake needs to be reloaded because files changed structurally"
                //   189: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   192: iload_3        
                //   193: ifeq            212
                //   196: goto            203
                //   199: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   202: athrow         
                //   203: ldc             "(external)"
                //   205: goto            214
                //   208: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   211: athrow         
                //   212: ldc             ""
                //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   217: ldc             ":\n"
                //   219: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   222: aload_1        
                //   223: ldc             "\n"
                //   225: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/lang/Iterable;Ljava/lang/String;)Ljava/lang/String;
                //   228: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   231: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   234: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
                //   237: goto            364
                //   240: iload           4
                //   242: ifeq            250
                //   245: return         
                //   246: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   249: athrow         
                //   250: aload_2        
                //   251: invokedynamic   fun:()Lcom/intellij/util/Function;
                //   256: invokestatic    com/intellij/util/containers/ContainerUtil.map2MapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/Map;
                //   259: astore          5
                //   261: aload_0        
                //   262: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.this$0:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;
                //   265: aload           5
                //   267: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
                //   272: aload_0        
                //   273: aload           5
                //   275: invokedynamic   fun:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5;Ljava/util/Map;)Lcom/intellij/util/NotNullFunction;
                //   280: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.access$300:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;Ljava/util/Collection;Lcom/intellij/util/NotNullFunction;)Z
                //   283: istore          6
                //   285: iload           6
                //   287: ifne            295
                //   290: return         
                //   291: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   294: athrow         
                //   295: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                //   298: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
                //   301: ifeq            364
                //   304: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                //   307: new             Ljava/lang/StringBuilder;
                //   310: dup            
                //   311: invokespecial   java/lang/StringBuilder.<init>:()V
                //   314: ldc             "CMake needs to be reloaded because file contents changed"
                //   316: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   319: iload_3        
                //   320: ifeq            339
                //   323: goto            330
                //   326: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   329: athrow         
                //   330: ldc             "(external)"
                //   332: goto            341
                //   335: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   338: athrow         
                //   339: ldc             ""
                //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   344: ldc             ":\n"
                //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   349: aload_2        
                //   350: ldc             "\n"
                //   352: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/lang/Iterable;Ljava/lang/String;)Ljava/lang/String;
                //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   358: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   361: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
                //   364: aload_0        
                //   365: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$5.this$0:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;
                //   368: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.access$500:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;
                //   371: iconst_0       
                //   372: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.scheduleReload:(Z)V
                //   375: return         
                //    Signature:
                //  (Ljava/util/Set<Ljava/io/File;>;Ljava/util/Set<Lcom/intellij/openapi/vfs/VirtualFile;>;ZZ)V
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  0      40     40     44     Ljava/lang/IllegalStateException;
                //  44     84     84     88     Ljava/lang/IllegalStateException;
                //  88     102    105    109    Ljava/lang/IllegalStateException;
                //  92     110    110    114    Ljava/lang/IllegalStateException;
                //  114    140    143    147    Ljava/lang/IllegalStateException;
                //  124    148    148    152    Ljava/lang/IllegalStateException;
                //  152    170    173    177    Ljava/lang/IllegalStateException;
                //  161    196    199    203    Ljava/lang/IllegalStateException;
                //  177    208    208    212    Ljava/lang/IllegalStateException;
                //  240    246    246    250    Ljava/lang/IllegalStateException;
                //  285    291    291    295    Ljava/lang/IllegalStateException;
                //  295    323    326    330    Ljava/lang/IllegalStateException;
                //  304    335    335    339    Ljava/lang/IllegalStateException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0177:
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
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }.install(this.myProject, this.myShutdownDisposable);
    }
    
    public void shutdown() {
        try {
            if (this.isShutdown.getAndSet(true)) {
                return;
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        Disposer.dispose(this.myShutdownDisposable);
        this.myChangedDocumentsQueue.cancelAllUpdates();
        this.myReloadsQueue.clear();
        this.mySchedulingQueueProcessor.clear();
        final ProgressIndicator myRunningReloadTaskIndicator = this.myRunningReloadTaskIndicator;
        try {
            if (myRunningReloadTaskIndicator != null) {
                myRunningReloadTaskIndicator.cancel();
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
    }
    
    private void b(@NotNull final DumbAwareRunnable dumbAwareRunnable) {
        try {
            if (dumbAwareRunnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "runWhenReady"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        StartupManager.getInstance(this.myProject).runWhenProjectIsInitialized((Runnable)(() -> {
            try {
                if (dumbAwareRunnable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "lambda$runWhenReady$1"));
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            try {
                if (this.isShutdown.get()) {
                    return;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
            dumbAwareRunnable.run();
        }));
    }
    
    public <T> Future<T> lockModelDuring(final Callable<T> callable) {
        final FutureResult futureResult = new FutureResult();
        this.b(() -> {
            final Lock lock;
            ApplicationManager.getApplication().executeOnPooledThread(() -> {
                this.myReloadsLock.readLock();
                lock.lock();
                try {
                    futureResult.set(callable.call());
                }
                catch (Throwable exception) {
                    futureResult.setException(exception);
                }
                finally {
                    lock.unlock();
                }
            });
        });
        return (Future<T>)futureResult;
    }
    
    public void setReloadInBackgroundInTests(final boolean b, final Disposable disposable) {
        new WriteAction<Object>() {
            protected void run(@NotNull final Result<Object> result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$6", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                MergingUpdateQueue access$000 = null;
                boolean passThrough = false;
                Label_0079: {
                    try {
                        CMakeWorkspaceWatcher.this.myReloadInBackgroundInTestsMode = b;
                        access$000 = CMakeWorkspaceWatcher.this.myChangedDocumentsQueue;
                        if (!b) {
                            passThrough = true;
                            break Label_0079;
                        }
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                    passThrough = false;
                }
                access$000.setPassThrough(passThrough);
                CMakeWorkspaceWatcher.this.myReloadsQueue.setForceAsyncInTests(b, disposable);
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    public void stopReloadsOnDocumentChange() {
        this.myChangedDocumentsQueue.suspend();
    }
    
    public void resumeReloadsOnDocumentChange() {
        this.myChangedDocumentsQueue.resume();
    }
    
    public void waitForReloadsToFinish() throws TimeoutException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //     3: invokeinterface com/intellij/openapi/application/Application.isDispatchThread:()Z
        //     8: ifeq            25
        //    11: new             Ljava/lang/AssertionError;
        //    14: dup            
        //    15: ldc             "Must not wait for CMake reload on AWT, a deadlock will happen.\nRewrite the test with runInDispatchThread=false"
        //    17: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //    20: athrow         
        //    21: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    24: athrow         
        //    25: new             Ljava/util/concurrent/atomic/AtomicBoolean;
        //    28: dup            
        //    29: invokespecial   java/util/concurrent/atomic/AtomicBoolean.<init>:()V
        //    32: astore_1       
        //    33: new             Ljava/lang/Thread;
        //    36: dup            
        //    37: aload_0        
        //    38: aload_1        
        //    39: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;Ljava/util/concurrent/atomic/AtomicBoolean;)Ljava/lang/Runnable;
        //    44: ldc             "wait for reload"
        //    46: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;Ljava/lang/String;)V
        //    49: astore_2       
        //    50: aload_2        
        //    51: invokevirtual   java/lang/Thread.start:()V
        //    54: aload_2        
        //    55: ldc2_w          60000
        //    58: invokevirtual   java/lang/Thread.join:(J)V
        //    61: goto            65
        //    64: astore_3       
        //    65: aload_1        
        //    66: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
        //    69: ifne            84
        //    72: new             Ljava/util/concurrent/TimeoutException;
        //    75: dup            
        //    76: invokespecial   java/util/concurrent/TimeoutException.<init>:()V
        //    79: athrow         
        //    80: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    83: athrow         
        //    84: return         
        //    Exceptions:
        //  throws java.util.concurrent.TimeoutException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      21     21     25     Ljava/lang/InterruptedException;
        //  54     61     64     65     Ljava/lang/InterruptedException;
        //  65     80     80     84     Ljava/lang/InterruptedException;
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
    
    void scheduleReload(@NotNull final Consumer<ProgressIndicator> consumer, final boolean b) {
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reloadRunnable", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "scheduleReload"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.a(false);
        this.myChangedDocumentsQueue.cancelAllUpdates();
        this.myWaitingReloadsCount.incrementAndGet();
        this.b(() -> {
            try {
                if (consumer == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reloadRunnable", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "lambda$scheduleReload$5"));
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            this.b(consumer, b);
        });
    }
    
    private void b(@NotNull final Consumer<ProgressIndicator> p0, final boolean p1) {
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
        //    18: ldc             "reloadRunnable"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doScheduleReload"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.myRunningReloadTaskIndicator:Lcom/intellij/openapi/progress/ProgressIndicator;
        //    48: astore_3       
        //    49: aload_3        
        //    50: ifnull          66
        //    53: aload_3        
        //    54: invokeinterface com/intellij/openapi/progress/ProgressIndicator.cancel:()V
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    65: athrow         
        //    66: aload_0        
        //    67: iload_2        
        //    68: aload_1        
        //    69: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;ZLjava/util/function/Consumer;)Ljava/lang/Runnable;
        //    74: astore          4
        //    76: aload_0        
        //    77: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.a:()Z
        //    80: ifeq            99
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.mySchedulingQueueProcessor:Lcom/intellij/util/concurrency/QueueProcessor;
        //    87: aload           4
        //    89: invokevirtual   com/intellij/util/concurrency/QueueProcessor.add:(Ljava/lang/Object;)V
        //    92: goto            106
        //    95: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    98: athrow         
        //    99: aload           4
        //   101: invokeinterface java/lang/Runnable.run:()V
        //   106: return         
        //    Signature:
        //  (Ljava/util/function/Consumer<Lcom/intellij/openapi/progress/ProgressIndicator;>;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  49     59     62     66     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  76     95     95     99     Lcom/intellij/openapi/progress/ProcessCanceledException;
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
    
    @Nullable
    ProgressIndicator getRunningReloadTaskIndicator() {
        return this.myRunningReloadTaskIndicator;
    }
    
    private boolean a() {
        Label_0025: {
            try {
                if (!ApplicationManager.getApplication().isUnitTestMode()) {
                    break Label_0025;
                }
                final CMakeWorkspaceWatcher cMakeWorkspaceWatcher = this;
                final boolean b = cMakeWorkspaceWatcher.myReloadInBackgroundInTestsMode;
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            try {
                final CMakeWorkspaceWatcher cMakeWorkspaceWatcher = this;
                final boolean b = cMakeWorkspaceWatcher.myReloadInBackgroundInTestsMode;
                if (b) {
                    return true;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
        }
        return false;
    }
    
    private boolean b() {
        return this.myWorkspace.getSettings().isAutoReloadEnabled();
    }
    
    public void schedulePendingAutoReloads() {
        Label_0029: {
            try {
                if (this.myProject.getUserData((Key)CMakeWorkspaceWatcher.NEEDS_RELOAD) == null) {
                    return;
                }
                final CMakeWorkspaceWatcher cMakeWorkspaceWatcher = this;
                final boolean b = cMakeWorkspaceWatcher.b();
                if (b) {
                    break Label_0029;
                }
                return;
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            try {
                final CMakeWorkspaceWatcher cMakeWorkspaceWatcher = this;
                final boolean b = cMakeWorkspaceWatcher.b();
                if (b) {
                    this.myWorkspace.scheduleReload(false);
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
        }
    }
    
    private void a(final boolean b) {
        Project myProject = null;
        Key<Boolean> needs_RELOAD = null;
        Object value = null;
        Label_0023: {
            try {
                myProject = this.myProject;
                needs_RELOAD = CMakeWorkspaceWatcher.NEEDS_RELOAD;
                if (b) {
                    value = true;
                    break Label_0023;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            value = null;
        }
        myProject.putUserData((Key)needs_RELOAD, value);
        EditorNotifications.getInstance(this.myProject).updateAllNotifications();
    }
    
    private boolean b(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "cmakeFilesOrDocumentsHaveChangesOrSettingsChanged"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return (boolean)ReadAction.compute(() -> {
            try {
                if (progressIndicator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "lambda$cmakeFilesOrDocumentsHaveChangesOrSettingsChanged$7"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                progressIndicator.checkCanceled();
                if (this.myProject.isDisposed()) {
                    throw new ProcessCanceledException();
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            final long calcConfigurationsHash = CMakeSettingsKt.calcConfigurationsHash(this.myWorkspace.getSettings().getConfigurations());
            try {
                if (this.myWorkspace.getModelSettingsHash() != calcConfigurationsHash) {
                    return true;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            return this.b(null, (NotNullFunction<File, FileStampFunctionAdapter>)new NotNullFunction<File, FileStampFunctionAdapter>() {
                FileDocumentManager myDocumentManager;
                final /* synthetic */ ProgressIndicator val$indicator;
                
                @NotNull
                public FileStampFunctionAdapter fun(final File file) {
                    this.val$indicator.checkCanceled();
                    final VirtualFile fileByIoFile = VfsUtil.findFileByIoFile(file, false);
                    Label_0074: {
                        FileStampFunctionAdapter fileStampFunctionAdapter = null;
                        Label_0039: {
                            try {
                                if (fileByIoFile != null) {
                                    break Label_0074;
                                }
                                final NotNullFunction<File, FileStampFunctionAdapter> notNullFunction = (NotNullFunction<File, FileStampFunctionAdapter>)this;
                                final boolean b = true;
                                fileStampFunctionAdapter = new FileStampFunctionAdapter() {
                                    @Nullable
                                    @Override
                                    public CMakeFile getCMakeFile(@NotNull final Project project) {
                                        try {
                                            if (project == null) {
                                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$1", "getCMakeFile"));
                                            }
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw a(ex);
                                        }
                                        return null;
                                    }
                                    
                                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                                        return ex;
                                    }
                                };
                                if (fileStampFunctionAdapter == null) {
                                    break Label_0039;
                                }
                                return fileStampFunctionAdapter;
                            }
                            catch (IllegalStateException ex) {
                                throw a(ex);
                            }
                            try {
                                final NotNullFunction<File, FileStampFunctionAdapter> notNullFunction = (NotNullFunction<File, FileStampFunctionAdapter>)this;
                                final boolean b = true;
                                fileStampFunctionAdapter = new FileStampFunctionAdapter() {
                                    @Nullable
                                    @Override
                                    public CMakeFile getCMakeFile(@NotNull final Project project) {
                                        try {
                                            if (project == null) {
                                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$1", "getCMakeFile"));
                                            }
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw a(ex);
                                        }
                                        return null;
                                    }
                                    
                                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                                        return ex;
                                    }
                                };
                                if (fileStampFunctionAdapter == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8", "fun"));
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                        }
                        return fileStampFunctionAdapter;
                        try {
                            if (this.myDocumentManager == null) {
                                this.myDocumentManager = FileDocumentManager.getInstance();
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                    }
                    final Document cachedDocument = this.myDocumentManager.getCachedDocument(fileByIoFile);
                    boolean b3 = false;
                    Label_0139: {
                        Label_0130: {
                            try {
                                if (cachedDocument == null) {
                                    break Label_0130;
                                }
                                final Document document = cachedDocument;
                                final Key<Boolean> key = CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT;
                                final Object o = document.getUserData((Key)key);
                                final Boolean b2 = Boolean.TRUE;
                                if (o == b2) {
                                    break Label_0130;
                                }
                                break Label_0130;
                            }
                            catch (IllegalStateException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final Document document = cachedDocument;
                                final Key<Boolean> key = CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT;
                                final Object o = document.getUserData((Key)key);
                                final Boolean b2 = Boolean.TRUE;
                                if (o == b2) {
                                    b3 = true;
                                    break Label_0139;
                                }
                            }
                            catch (IllegalStateException ex5) {
                                throw a(ex5);
                            }
                        }
                        b3 = false;
                    }
                    final boolean b4 = b3;
                    try {
                        if (cachedDocument != null) {
                            cachedDocument.putUserData((Key)CMakeWorkspaceWatcher.SHOULD_CHECK_DOCUMENT_TEXT, (Object)null);
                        }
                    }
                    catch (IllegalStateException ex6) {
                        throw a(ex6);
                    }
                    boolean b5 = false;
                    Label_0181: {
                        try {
                            if (!b4) {
                                b5 = true;
                                break Label_0181;
                            }
                        }
                        catch (IllegalStateException ex7) {
                            throw a(ex7);
                        }
                        b5 = false;
                    }
                    final FileStampFunctionAdapter fileStampFunctionAdapter2 = new FileStampFunctionAdapter(b5) {
                        final /* synthetic */ boolean val$checkDocumentText;
                        final /* synthetic */ Document val$document;
                        final /* synthetic */ VirtualFile val$vFile;
                        
                        @Nullable
                        @Override
                        public CMakeFile getCMakeFile(@NotNull final Project project) {
                            try {
                                if (project == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$2", "getCMakeFile"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                if (this.val$checkDocumentText) {
                                    return FileStamp.createOrGetCMakeFile(project, this.val$document);
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            return FileStamp.createOrGetCMakeFile(project, this.val$vFile);
                        }
                        
                        private static IllegalArgumentException a(final IllegalArgumentException ex) {
                            return ex;
                        }
                    };
                    if (fileStampFunctionAdapter2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8", "fun"));
                    }
                    return fileStampFunctionAdapter2;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            });
        });
    }
    
    private boolean b(@Nullable final Collection<File> collection, @NotNull final NotNullFunction<File, FileStampFunctionAdapter> notNullFunction) {
        try {
            if (notNullFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "getFileDocumentStampFunction", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher", "cmakeFilesHaveChanges"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return (boolean)ReadAction.compute(() -> {
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
            //    18: ldc             "getFileDocumentStampFunction"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "lambda$cmakeFilesHaveChanges$8"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    43: athrow         
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.myProject:Lcom/intellij/openapi/project/Project;
            //    48: invokeinterface com/intellij/openapi/project/Project.isDisposed:()Z
            //    53: ifeq            65
            //    56: iconst_0       
            //    57: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    60: areturn        
            //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    64: athrow         
            //    65: aload_0        
            //    66: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.myWorkspace:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;
            //    69: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.getCMakeFilesWithStamps:()Ljava/util/Map;
            //    72: astore_3       
            //    73: aload_1        
            //    74: ifnull          85
            //    77: aload_1        
            //    78: goto            91
            //    81: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    84: athrow         
            //    85: aload_3        
            //    86: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
            //    91: astore          4
            //    93: aload           4
            //    95: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
            //   100: astore          5
            //   102: aload           5
            //   104: invokeinterface java/util/Iterator.hasNext:()Z
            //   109: ifeq            256
            //   112: aload           5
            //   114: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   119: checkcast       Ljava/io/File;
            //   122: astore          6
            //   124: aload_3        
            //   125: aload           6
            //   127: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //   132: checkcast       Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp;
            //   135: astore          7
            //   137: aload_0        
            //   138: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.myProject:Lcom/intellij/openapi/project/Project;
            //   141: aload           6
            //   143: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$9;
            //   146: dup            
            //   147: aload_0        
            //   148: aload_2        
            //   149: aload           6
            //   151: aload           7
            //   153: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$9.<init>:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher;Lcom/intellij/util/NotNullFunction;Ljava/io/File;Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp;)V
            //   156: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.calcFileStamp:(Lcom/intellij/openapi/project/Project;Ljava/io/File;Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp$FileStampFunction;)Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp;
            //   159: astore          8
            //   161: aload           7
            //   163: ifnull          221
            //   166: aload           7
            //   168: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.timestamp:J
            //   171: aload           8
            //   173: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.timestamp:J
            //   176: lcmp           
            //   177: ifeq            230
            //   180: goto            187
            //   183: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   186: athrow         
            //   187: aload           7
            //   189: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.timestamp:J
            //   192: lconst_0       
            //   193: lcmp           
            //   194: ifeq            221
            //   197: goto            204
            //   200: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   203: athrow         
            //   204: aload           8
            //   206: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.timestamp:J
            //   209: lconst_0       
            //   210: lcmp           
            //   211: ifne            230
            //   214: goto            221
            //   217: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   220: athrow         
            //   221: iconst_1       
            //   222: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   225: areturn        
            //   226: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   229: athrow         
            //   230: aload           7
            //   232: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.contentCrc:J
            //   235: aload           8
            //   237: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.contentCrc:J
            //   240: lcmp           
            //   241: ifeq            253
            //   244: iconst_1       
            //   245: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   248: areturn        
            //   249: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   252: athrow         
            //   253: goto            102
            //   256: iconst_0       
            //   257: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   260: areturn        
            //    Exceptions:
            //  throws java.lang.RuntimeException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  0      40     40     44     Ljava/lang/RuntimeException;
            //  44     61     61     65     Ljava/lang/RuntimeException;
            //  73     81     81     85     Ljava/lang/RuntimeException;
            //  161    180    183    187    Ljava/lang/RuntimeException;
            //  166    197    200    204    Ljava/lang/RuntimeException;
            //  187    214    217    221    Ljava/lang/RuntimeException;
            //  204    226    226    230    Ljava/lang/RuntimeException;
            //  230    249    249    253    Ljava/lang/RuntimeException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    }
    
    static {
        NEEDS_RELOAD = Key.create("NEEDS_RELOAD");
        SHOULD_CHECK_DOCUMENT_TEXT = Key.create("SHOULD_CHECK_DOCUMENT_TEXT");
        READING_CMAKE_PROJECT = CPPBundle.message("cmake.loadingProgress", new Object[0]);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private abstract static class FileStampFunctionAdapter
    {
        final boolean useAlreadyCalculatedStamp;
        
        public FileStampFunctionAdapter(final boolean useAlreadyCalculatedStamp) {
            this.useAlreadyCalculatedStamp = useAlreadyCalculatedStamp;
        }
        
        @Nullable
        public abstract CMakeFile getCMakeFile(@NotNull final Project p0);
    }
}
