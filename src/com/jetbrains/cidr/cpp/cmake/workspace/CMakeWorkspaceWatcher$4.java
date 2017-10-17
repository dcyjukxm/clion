// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.NotNullFunction;
import java.util.Collection;
import com.intellij.util.messages.Topic;
import com.intellij.util.ui.update.Update;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.editor.event.DocumentEvent;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Document;
import java.io.File;
import java.util.Map;
import com.intellij.openapi.editor.event.DocumentListener;

class CMakeWorkspaceWatcher$4 implements DocumentListener {
    @NotNull
    final Map<File, Document> myChangedDocuments = new THashMap(FileUtil.FILE_HASHING_STRATEGY);
    final /* synthetic */ Function val$getWatchedFileForDocument;
    
    public void documentChanged(final DocumentEvent documentEvent) {
        final Document document = documentEvent.getDocument();
        final Pair pair = (Pair)this.val$getWatchedFileForDocument.fun((Object)document);
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
        CMakeWorkspaceWatcher.access$100(CMakeWorkspaceWatcher.this, this::a);
    }
    
    private void a() {
        CMakeWorkspaceWatcher.access$000(CMakeWorkspaceWatcher.this).restartTimer();
        CMakeWorkspaceWatcher.access$000(CMakeWorkspaceWatcher.this).queue((Update)new Update(CMakeWorkspaceWatcher.this) {
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
            
            private void b() {
                ((CMakeWorkspaceListener)CMakeWorkspaceWatcher.access$200(CMakeWorkspaceWatcher.this).getMessageBus().syncPublisher((Topic)CMakeWorkspaceListener.TOPIC)).reloadingRescheduled();
                CMakeWorkspaceWatcher.access$000(CMakeWorkspaceWatcher.this).restartTimer();
                CMakeWorkspaceWatcher.access$000(CMakeWorkspaceWatcher.this).queue((Update)this);
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
                        final CMakeFile cMakeFile = ((FileStampFunctionAdapter)notNullFunction.fun((Object)file)).getCMakeFile(CMakeWorkspaceWatcher.access$200(CMakeWorkspaceWatcher.this));
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
}