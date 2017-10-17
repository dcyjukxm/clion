// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.copy.CopyFilesOrDirectoriesHandler;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiNamedElement;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.SearchScope;
import java.util.ArrayList;
import com.intellij.psi.search.LocalSearchScope;
import com.jetbrains.cidr.lang.refactoring.rename.OCRenameProcessor;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiManager;
import java.io.IOException;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.move.OCCopyMoveProcessor;

private class MyProcessor extends OCCopyMoveProcessor<M, R, G, P>
{
    private String myNewName;
    
    private MyProcessor(final PsiElement[] array, final String myNewName) {
        super(array);
        this.myNewName = myNewName;
    }
    
    @Override
    protected void doActionForExternalFiles(final Project project, @NotNull final P p5, @NotNull final List<VirtualFile> list, final G g, @NotNull final PsiDirectory psiDirectory) {
        try {
            if (p5 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectFile", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor", "doActionForExternalFiles"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "notInProjectFiles", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor", "doActionForExternalFiles"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        try {
            if (psiDirectory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetDirectory", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor", "doActionForExternalFiles"));
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        new WriteCommandAction(project, new PsiFile[0]) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor$1", "run"));
                    }
                }
                catch (IOException ex) {
                    throw a(ex);
                }
                try {
                    final Iterator<VirtualFile> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        final PsiFile file = PsiManager.getInstance(project).findFile((VirtualFile)iterator.next());
                        try {
                            if (file == null) {
                                continue;
                            }
                            MyProcessor.this.a(psiDirectory, (PsiFileSystemItem)file);
                        }
                        catch (IOException ex2) {
                            throw a(ex2);
                        }
                    }
                }
                catch (IOException ex3) {
                    Messages.showErrorDialog("Failed to copy files to \"" + psiDirectory.getName() + "\"", "Error");
                }
            }
            
            private static IOException a(final IOException ex) {
                return ex;
            }
        }.execute();
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doAction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "doAction"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //   151: ldc             "targetRef"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "doAction"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //   202: ldc             "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             2
        //   208: ldc             "doAction"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   221: athrow         
        //   222: new             Ljava/util/ArrayList;
        //   225: dup            
        //   226: invokespecial   java/util/ArrayList.<init>:()V
        //   229: astore          6
        //   231: new             Ljava/util/ArrayList;
        //   234: dup            
        //   235: invokespecial   java/util/ArrayList.<init>:()V
        //   238: astore          7
        //   240: aload_0        
        //   241: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.myElements:Ljava/util/Set;
        //   244: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   249: astore          8
        //   251: aload           8
        //   253: invokeinterface java/util/Iterator.hasNext:()Z
        //   258: ifeq            499
        //   261: aload           8
        //   263: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   268: checkcast       Lcom/intellij/psi/PsiElement;
        //   271: astore          9
        //   273: aload_0        
        //   274: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate;
        //   277: aload           9
        //   279: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate.skipElement:(Lcom/intellij/psi/PsiElement;)Z
        //   282: ifne            496
        //   285: aload           9
        //   287: instanceof      Lcom/intellij/psi/PsiFile;
        //   290: ifne            315
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   299: athrow         
        //   300: aload           9
        //   302: instanceof      Lcom/intellij/psi/PsiDirectory;
        //   305: ifeq            496
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   314: athrow         
        //   315: aload_0        
        //   316: aload_3        
        //   317: aload           9
        //   319: checkcast       Lcom/intellij/psi/PsiFileSystemItem;
        //   322: invokespecial   com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.a:(Lcom/intellij/psi/PsiDirectory;Lcom/intellij/psi/PsiFileSystemItem;)Lcom/intellij/psi/PsiFile;
        //   325: astore          10
        //   327: aload           9
        //   329: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   332: ifeq            416
        //   335: aload_0        
        //   336: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.myNewName:Ljava/lang/String;
        //   339: ifnull          416
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   348: athrow         
        //   349: aload_0        
        //   350: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.myNewName:Ljava/lang/String;
        //   353: aload           9
        //   355: checkcast       Lcom/intellij/psi/PsiFileSystemItem;
        //   358: invokeinterface com/intellij/psi/PsiFileSystemItem.getName:()Ljava/lang/String;
        //   363: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   366: ifne            416
        //   369: goto            376
        //   372: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   375: athrow         
        //   376: aload_0        
        //   377: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.myNewName:Ljava/lang/String;
        //   380: invokestatic    com/intellij/openapi/util/io/FileUtil.getNameWithoutExtension:(Ljava/lang/String;)Ljava/lang/String;
        //   383: invokestatic    com/intellij/openapi/util/text/StringUtil.isJavaIdentifier:(Ljava/lang/String;)Z
        //   386: ifeq            416
        //   389: goto            396
        //   392: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   395: athrow         
        //   396: aload_0        
        //   397: aload           9
        //   399: aload           10
        //   401: invokedynamic   run:(Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiFile;)Lcom/intellij/util/ThrowableRunnable;
        //   406: invokestatic    com/intellij/openapi/application/WriteAction.run:(Lcom/intellij/util/ThrowableRunnable;)V
        //   409: goto            416
        //   412: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   415: athrow         
        //   416: aload           10
        //   418: ifnull          496
        //   421: aload           7
        //   423: aload           10
        //   425: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   430: pop            
        //   431: aload           9
        //   433: instanceof      Lcom/intellij/psi/PsiFile;
        //   436: ifeq            460
        //   439: goto            446
        //   442: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   445: athrow         
        //   446: aload           10
        //   448: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   453: goto            472
        //   456: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   459: athrow         
        //   460: aload           10
        //   462: invokeinterface com/intellij/psi/PsiFile.getContainingDirectory:()Lcom/intellij/psi/PsiDirectory;
        //   467: invokeinterface com/intellij/psi/PsiDirectory.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   472: astore          11
        //   474: aload           11
        //   476: ifnull          496
        //   479: aload           6
        //   481: aload           11
        //   483: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   488: pop            
        //   489: goto            496
        //   492: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   495: athrow         
        //   496: goto            251
        //   499: new             Ljava/util/HashSet;
        //   502: dup            
        //   503: invokespecial   java/util/HashSet.<init>:()V
        //   506: astore          8
        //   508: aload           5
        //   510: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   515: astore          9
        //   517: aload           9
        //   519: invokeinterface java/util/Iterator.hasNext:()Z
        //   524: ifeq            550
        //   527: aload           9
        //   529: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   534: astore          10
        //   536: aload_0        
        //   537: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate;
        //   540: aload           8
        //   542: aload           10
        //   544: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate.addTargets:(Ljava/util/Set;Ljava/lang/Object;)V
        //   547: goto            517
        //   550: aload           6
        //   552: invokeinterface java/util/List.isEmpty:()Z
        //   557: ifne            581
        //   560: aload_0        
        //   561: getfield        com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate;
        //   564: aload_3        
        //   565: aload           4
        //   567: aload           6
        //   569: aload           8
        //   571: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate.addFiles:(Lcom/intellij/psi/PsiDirectory;Ljava/lang/Object;Ljava/util/List;Ljava/util/Set;)V
        //   574: goto            581
        //   577: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   580: athrow         
        //   581: aload           7
        //   583: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   588: astore          9
        //   590: aload           9
        //   592: invokeinterface java/util/Iterator.hasNext:()Z
        //   597: ifeq            621
        //   600: aload           9
        //   602: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   607: checkcast       Lcom/intellij/psi/PsiFile;
        //   610: astore          10
        //   612: aload           10
        //   614: invokestatic    com/intellij/ide/util/EditorHelper.openInEditor:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/Editor;
        //   617: pop            
        //   618: goto            590
        //   621: goto            660
        //   624: astore          8
        //   626: new             Ljava/lang/StringBuilder;
        //   629: dup            
        //   630: invokespecial   java/lang/StringBuilder.<init>:()V
        //   633: ldc             "Failed to copy files to \""
        //   635: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   638: aload_3        
        //   639: invokeinterface com/intellij/psi/PsiDirectory.getName:()Ljava/lang/String;
        //   644: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   647: ldc             "\""
        //   649: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   652: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   655: ldc             "Error"
        //   657: invokestatic    com/intellij/openapi/ui/Messages.showErrorDialog:(Ljava/lang/String;Ljava/lang/String;)V
        //   660: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper<TM;TR;TG;TP;>;TP;Lcom/intellij/psi/PsiDirectory;TG;Ljava/util/List<TR;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  550    574    577    581    Ljava/io/IOException;
        //  474    489    492    496    Ljava/io/IOException;
        //  421    456    456    460    Ljava/io/IOException;
        //  416    439    442    446    Ljava/io/IOException;
        //  376    409    412    416    Ljava/io/IOException;
        //  349    389    392    396    Ljava/io/IOException;
        //  335    369    372    376    Ljava/io/IOException;
        //  327    342    345    349    Ljava/io/IOException;
        //  285    308    311    315    Ljava/io/IOException;
        //  273    293    296    300    Ljava/io/IOException;
        //  177    218    218    222    Ljava/io/IOException;
        //  132    173    173    177    Ljava/io/IOException;
        //  88     128    128    132    Ljava/io/IOException;
        //  44     84     84     88     Ljava/io/IOException;
        //  0      40     40     44     Ljava/io/IOException;
        //  240    621    624    660    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0349:
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
    
    protected void cloneClass(final OCFile ocFile, final OCFile ocFile2) {
        Object class1 = ocFile.findClass(ocFile.getMainClassName());
        final Project project = ocFile.getProject();
        if (class1 == null) {
            final OCFile associatedFile = ocFile.getAssociatedFile();
            Object class2 = null;
            Label_0059: {
                try {
                    if (associatedFile != null) {
                        class2 = associatedFile.findClass(ocFile.getMainClassName());
                        break Label_0059;
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                class2 = null;
            }
            class1 = class2;
        }
        try {
            if (class1 == null) {
                return;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final OCRenameProcessor ocRenameProcessor = new OCRenameProcessor();
        final LocalSearchScope localSearchScope = new LocalSearchScope((PsiElement)ocFile);
        final ArrayList<Object> list = new ArrayList<Object>();
        final VirtualFile virtualFile = ocFile.getVirtualFile();
        final OCStructSymbol symbol = ((OCSymbolDeclarator<OCStructSymbol>)class1).getSymbol();
        for (final PsiReference psiReference : ocRenameProcessor.findReferences((PsiElement)class1, (SearchScope)localSearchScope)) {
            list.add(psiReference.getElement().getTextOffset() + psiReference.getRangeInElement().getStartOffset());
        }
        for (final PsiReference psiReference2 : ocRenameProcessor.findReferences((PsiElement)ocFile.getAssociatedFile(), (SearchScope)localSearchScope)) {
            list.add(psiReference2.getElement().getTextOffset() + psiReference2.getRangeInElement().getStartOffset());
        }
        Label_0328: {
            Label_0311: {
                Label_0286: {
                    try {
                        if (!(symbol instanceof OCStructSymbol)) {
                            break Label_0311;
                        }
                        final VirtualFile virtualFile2 = virtualFile;
                        if (virtualFile2 != null) {
                            break Label_0286;
                        }
                        break Label_0311;
                    }
                    catch (RuntimeException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final VirtualFile virtualFile2 = virtualFile;
                        if (virtualFile2 != null) {
                            symbol.processConstructors((Processor<? super OCFunctionSymbol>)(ocFunctionSymbol -> {
                                try {
                                    if (virtualFile.equals(ocFunctionSymbol.getContainingFile())) {
                                        list.add(ocFunctionSymbol.getOffset());
                                    }
                                }
                                catch (RuntimeException ex) {
                                    throw b(ex);
                                }
                                return true;
                            }));
                        }
                    }
                    catch (RuntimeException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    if (symbol == null) {
                        break Label_0328;
                    }
                    final VirtualFile virtualFile3 = virtualFile;
                    if (virtualFile3 != null) {
                        break Label_0328;
                    }
                    break Label_0328;
                }
                catch (RuntimeException ex5) {
                    throw b(ex5);
                }
            }
            try {
                final VirtualFile virtualFile3 = virtualFile;
                if (virtualFile3 != null) {
                    symbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> {
                        try {
                            if (virtualFile.equals(ocSymbol.getContainingFile())) {
                                list.add(ocSymbol.getOffset());
                            }
                        }
                        catch (RuntimeException ex) {
                            throw b(ex);
                        }
                        return true;
                    }));
                }
            }
            catch (RuntimeException ex6) {
                throw b(ex6);
            }
        }
        Collections.sort(list, (n, n2) -> n2 - n);
        final String name = ((PsiNamedElement)class1).getName();
        final String nameWithoutExtension = FileUtil.getNameWithoutExtension(this.myNewName);
        final Document document = PsiDocumentManager.getInstance(project).getDocument((PsiFile)ocFile2);
        if (document != null) {
            PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(document);
            for (final Integer n3 : list) {
                document.replaceString((int)n3, n3 + name.length(), (CharSequence)nameWithoutExtension);
            }
            PsiDocumentManager.getInstance(project).commitDocument(document);
        }
    }
    
    private PsiFile a(final PsiDirectory psiDirectory, final PsiFileSystemItem psiFileSystemItem) throws IOException {
        String s = null;
        Label_0098: {
            if (this.myNewName == null) {
                s = psiFileSystemItem.getName();
            }
            else {
                Label_0093: {
                    try {
                        if (!(psiFileSystemItem instanceof OCFile) || !this.myElements.contains(((OCFile)psiFileSystemItem).getAssociatedFile())) {
                            break Label_0093;
                        }
                    }
                    catch (IOException ex) {
                        throw b(ex);
                    }
                    s = FileUtil.getNameWithoutExtension(this.myNewName) + "." + FileUtilRt.getExtension(psiFileSystemItem.getName());
                    break Label_0098;
                }
                s = this.myNewName;
            }
        }
        String string = s;
        final String checkOperationIsValid = OCCopyMoveProcessor.checkOperationIsValid(psiFileSystemItem, psiDirectory, string, true, false);
        try {
            if (checkOperationIsValid != null) {
                ApplicationManager.getApplication().invokeLater(() -> Messages.showErrorDialog(checkOperationIsValid, "Error"));
                return null;
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        String s2;
        for (int n = 1; OCCopyMoveProcessor.checkOperationIsValid(psiFileSystemItem, psiDirectory, string, false, false) != null; string = s2 + "." + FileUtilRt.getExtension(s), ++n) {
            s2 = FileUtil.getNameWithoutExtension(s) + " copy";
            if (n > 1) {
                s2 = s2 + " " + n;
            }
        }
        return CopyFilesOrDirectoriesHandler.copyToDirectory(psiFileSystemItem, string, psiDirectory);
    }
    
    @Override
    protected Helper<M, R, G, P> getHelper() {
        return OCCopyFilesHandlerDelegate.this.getHelper();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
