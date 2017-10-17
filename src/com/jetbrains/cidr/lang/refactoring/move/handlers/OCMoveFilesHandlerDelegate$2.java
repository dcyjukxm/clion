// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.intellij.refactoring.move.moveFilesOrDirectories.MoveFilesOrDirectoriesProcessor;
import com.intellij.openapi.command.undo.DocumentReference;
import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.command.undo.DocumentReferenceManager;
import com.intellij.refactoring.RefactoringSettings;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.application.WriteAction;
import com.intellij.ide.impl.ProjectUtil;
import java.util.Iterator;
import com.intellij.openapi.vfs.VfsUtil;
import gnu.trove.THashSet;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.move.MoveCallback;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.move.OCCopyMoveProcessor;

class OCMoveFilesHandlerDelegate$2 extends OCCopyMoveProcessor<M, R, G, P> {
    @Override
    protected void doActionForExternalFiles(final Project project, @NotNull final P p5, @NotNull final List<VirtualFile> list, @Nullable final G g, @NotNull final PsiDirectory psiDirectory) {
        try {
            if (p5 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectFile", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2", "doActionForExternalFiles"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "notInProjectFiles", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2", "doActionForExternalFiles"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        try {
            if (psiDirectory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetDirectory", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2", "doActionForExternalFiles"));
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        new WriteCommandAction(project, new PsiFile[0]) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                final THashSet set = new THashSet(FileUtil.PATH_HASHING_STRATEGY);
                final Iterator<VirtualFile> iterator = list.iterator();
                while (iterator.hasNext()) {
                    VfsUtil.processFilesRecursively((VirtualFile)iterator.next(), virtualFile -> set.add((Object)virtualFile.getPath()));
                }
                OCMoveFilesHandlerDelegate.this.removePaths(set, p5);
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
        ProjectUtil.focusProjectWindow(project, true);
        OCMoveFilesHandlerDelegate.this.addFiles(project, p5, list, g, psiDirectory);
    }
    
    @Override
    protected void doAction(@NotNull final Helper<M, R, G, P> p0, @NotNull final P p1, @NotNull final PsiDirectory p2, @NotNull final G p3, @NotNull final List<R> p4) {
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
        //    18: ldc             "helper"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //    62: ldc             "projectFile"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doAction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "targetDir"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "doAction"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "targetGroup"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "doAction"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   176: athrow         
        //   177: aload           5
        //   179: ifnonnull       222
        //   182: new             Ljava/lang/IllegalArgumentException;
        //   185: dup            
        //   186: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   188: ldc             3
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: ldc             0
        //   196: ldc             "sourceRefs"
        //   198: aastore        
        //   199: dup            
        //   200: ldc             1
        //   202: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             2
        //   208: ldc             "doAction"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   221: athrow         
        //   222: aload_0        
        //   223: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   226: aload           5
        //   228: aload           4
        //   230: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate.getTargetProvider:(Ljava/util/List;Ljava/lang/Object;)Ljava/lang/Object;
        //   233: astore          6
        //   235: aload           6
        //   237: ifnonnull       245
        //   240: return         
        //   241: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   244: athrow         
        //   245: new             Lcom/intellij/util/containers/MultiMap;
        //   248: dup            
        //   249: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
        //   252: astore          7
        //   254: aload_1        
        //   255: aload_2        
        //   256: invokeinterface com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper.getManipulator:(Ljava/lang/Object;)Ljava/lang/Object;
        //   261: astore          8
        //   263: new             Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer;
        //   266: dup            
        //   267: aload_0        
        //   268: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   271: aload_1        
        //   272: aload           8
        //   274: invokespecial   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer.<init>:(Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;Ljava/lang/Object;)V
        //   277: astore          9
        //   279: aload           5
        //   281: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   286: astore          10
        //   288: aload           10
        //   290: invokeinterface java/util/Iterator.hasNext:()Z
        //   295: ifeq            563
        //   298: aload           10
        //   300: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   305: astore          11
        //   307: aload_0        
        //   308: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   311: aload           11
        //   313: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate.findPsiElement:(Ljava/lang/Object;)Lcom/intellij/psi/PsiFileSystemItem;
        //   316: astore          12
        //   318: aload_0        
        //   319: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   322: aload           11
        //   324: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate.isValid:(Ljava/lang/Object;)Z
        //   327: ifeq            539
        //   330: aload_0        
        //   331: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   334: aload           4
        //   336: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate.isValid:(Ljava/lang/Object;)Z
        //   339: ifeq            539
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   348: athrow         
        //   349: aload_0        
        //   350: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   353: aload           11
        //   355: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate.getPath:(Ljava/lang/Object;)Ljava/lang/String;
        //   358: astore          13
        //   360: aload           12
        //   362: ifnull          536
        //   365: aload           13
        //   367: ifnull          536
        //   370: goto            377
        //   373: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   376: athrow         
        //   377: aload           12
        //   379: aload_3        
        //   380: aload           12
        //   382: invokeinterface com/intellij/psi/PsiFileSystemItem.getName:()Ljava/lang/String;
        //   387: iconst_0       
        //   388: iconst_1       
        //   389: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.checkOperationIsValid:(Lcom/intellij/psi/PsiFileSystemItem;Lcom/intellij/psi/PsiDirectory;Ljava/lang/String;ZZ)Ljava/lang/String;
        //   392: astore          14
        //   394: aload           14
        //   396: ifnonnull       440
        //   399: aload           9
        //   401: aload_0        
        //   402: aload_0        
        //   403: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.val$project:Lcom/intellij/openapi/project/Project;
        //   406: aload           12
        //   408: aload_3        
        //   409: aload           13
        //   411: aload           8
        //   413: aload           11
        //   415: aload           4
        //   417: aload           6
        //   419: aload           9
        //   421: aload_0        
        //   422: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.val$callback:Lcom/intellij/refactoring/move/MoveCallback;
        //   425: invokedynamic   run:(Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiFileSystemItem;Lcom/intellij/psi/PsiDirectory;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer;Lcom/intellij/refactoring/move/MoveCallback;)Ljava/lang/Runnable;
        //   430: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer.addTask:(Ljava/lang/Runnable;)V
        //   433: goto            536
        //   436: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   439: athrow         
        //   440: aload_0        
        //   441: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate;
        //   444: aload_0        
        //   445: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.val$project:Lcom/intellij/openapi/project/Project;
        //   448: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate.isFilesView:(Lcom/intellij/openapi/project/Project;)Z
        //   451: ifeq            488
        //   454: aload           7
        //   456: aload           12
        //   458: new             Ljava/lang/StringBuilder;
        //   461: dup            
        //   462: invokespecial   java/lang/StringBuilder.<init>:()V
        //   465: aload           14
        //   467: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   470: ldc             ". File won't be moved."
        //   472: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   475: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   478: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   481: goto            536
        //   484: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   487: athrow         
        //   488: aload           7
        //   490: aload           12
        //   492: new             Ljava/lang/StringBuilder;
        //   495: dup            
        //   496: invokespecial   java/lang/StringBuilder.<init>:()V
        //   499: aload           14
        //   501: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   504: ldc             ". File won't be moved, only reference will be changed."
        //   506: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   509: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   512: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   515: aload           9
        //   517: aload_0        
        //   518: aload           8
        //   520: aload           11
        //   522: aload           4
        //   524: aload           6
        //   526: aload           9
        //   528: invokedynamic   run:(Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer;)Ljava/lang/Runnable;
        //   533: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer.addTask:(Ljava/lang/Runnable;)V
        //   536: goto            560
        //   539: aload           9
        //   541: aload_0        
        //   542: aload           8
        //   544: aload           11
        //   546: aload           4
        //   548: aload           6
        //   550: aload           9
        //   552: invokedynamic   run:(Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer;)Ljava/lang/Runnable;
        //   557: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer.addTask:(Ljava/lang/Runnable;)V
        //   560: goto            288
        //   563: aload           9
        //   565: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer.isEmpty:()Z
        //   568: ifne            579
        //   571: iconst_1       
        //   572: goto            580
        //   575: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   578: athrow         
        //   579: iconst_0       
        //   580: istore          10
        //   582: aload           7
        //   584: invokevirtual   com/intellij/util/containers/MultiMap.isEmpty:()Z
        //   587: ifne            615
        //   590: iload           10
        //   592: new             Lcom/intellij/refactoring/ui/ConflictsDialog;
        //   595: dup            
        //   596: aload_0        
        //   597: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.val$project:Lcom/intellij/openapi/project/Project;
        //   600: aload           7
        //   602: aconst_null    
        //   603: iload           10
        //   605: iconst_0       
        //   606: invokespecial   com/intellij/refactoring/ui/ConflictsDialog.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/util/containers/MultiMap;Ljava/lang/Runnable;ZZ)V
        //   609: invokevirtual   com/intellij/refactoring/ui/ConflictsDialog.showAndGet:()Z
        //   612: iand           
        //   613: istore          10
        //   615: iload           10
        //   617: ifeq            632
        //   620: aload           9
        //   622: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$Completer.executeTasks:()V
        //   625: goto            632
        //   628: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   631: athrow         
        //   632: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper<TM;TR;TG;TP;>;TP;Lcom/intellij/psi/PsiDirectory;TG;Ljava/util/List<TR;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     84     84     88     Ljava/lang/RuntimeException;
        //  88     128    128    132    Ljava/lang/RuntimeException;
        //  132    173    173    177    Ljava/lang/RuntimeException;
        //  177    218    218    222    Ljava/lang/RuntimeException;
        //  235    241    241    245    Ljava/lang/RuntimeException;
        //  318    342    345    349    Ljava/lang/RuntimeException;
        //  360    370    373    377    Ljava/lang/RuntimeException;
        //  394    436    436    440    Ljava/lang/RuntimeException;
        //  440    484    484    488    Ljava/lang/RuntimeException;
        //  563    575    575    579    Ljava/lang/RuntimeException;
        //  615    625    628    632    Ljava/lang/RuntimeException;
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
    
    @Override
    protected Helper<M, R, G, P> getHelper() {
        return OCMoveFilesHandlerDelegate.this.getHelper();
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}