// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Iterator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.PsiFileSystemItemProcessor;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

class IncludedFilesProcessor
{
    public static boolean processFile(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @Nullable final Condition<VirtualFile> condition, @NotNull final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor", "processFile"));
            }
        }
        catch (IncludedFilesProcessor.1CancelException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor", "processFile"));
            }
        }
        catch (IncludedFilesProcessor.1CancelException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElementProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor", "processFile"));
            }
        }
        catch (IncludedFilesProcessor.1CancelException ex3) {
            throw b(ex3);
        }
        try {
            ProgressManager.checkCanceled();
            if (shouldSkip(virtualFile)) {
                return true;
            }
        }
        catch (IncludedFilesProcessor.1CancelException ex4) {
            throw b(ex4);
        }
        Label_0175: {
            try {
                if (condition == null) {
                    break Label_0175;
                }
                final Condition<VirtualFile> condition2 = condition;
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = condition2.value((Object)virtualFile2);
                if (!b) {
                    return true;
                }
                break Label_0175;
            }
            catch (IncludedFilesProcessor.1CancelException ex5) {
                throw b(ex5);
            }
            try {
                final Condition<VirtualFile> condition2 = condition;
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = condition2.value((Object)virtualFile2);
                if (!b) {
                    return true;
                }
            }
            catch (IncludedFilesProcessor.1CancelException ex6) {
                throw b(ex6);
            }
            try {
                if (psiElementProcessor instanceof HeadersSearchRootProcessor) {
                    return ((HeadersSearchRootProcessor)psiElementProcessor).process(virtualFile);
                }
            }
            catch (IncludedFilesProcessor.1CancelException ex7) {
                throw b(ex7);
            }
        }
        Label_0235: {
            try {
                if (!(psiElementProcessor instanceof PsiFileSystemItemProcessor)) {
                    break Label_0235;
                }
                final PsiFileSystemItemProcessor psiFileSystemItemProcessor = (PsiFileSystemItemProcessor)psiElementProcessor;
                final PsiFileSystemItemProcessor psiFileSystemItemProcessor2 = psiFileSystemItemProcessor;
                final VirtualFile virtualFile3 = virtualFile;
                final String s = virtualFile3.getName();
                final VirtualFile virtualFile4 = virtualFile;
                final boolean b2 = virtualFile4.isDirectory();
                final boolean b3 = psiFileSystemItemProcessor2.acceptItem(s, b2);
                if (!b3) {
                    return true;
                }
                break Label_0235;
            }
            catch (IncludedFilesProcessor.1CancelException ex8) {
                throw b(ex8);
            }
            try {
                final PsiFileSystemItemProcessor psiFileSystemItemProcessor = (PsiFileSystemItemProcessor)psiElementProcessor;
                final PsiFileSystemItemProcessor psiFileSystemItemProcessor2 = psiFileSystemItemProcessor;
                final VirtualFile virtualFile3 = virtualFile;
                final String s = virtualFile3.getName();
                final VirtualFile virtualFile4 = virtualFile;
                final boolean b2 = virtualFile4.isDirectory();
                final boolean b3 = psiFileSystemItemProcessor2.acceptItem(s, b2);
                if (!b3) {
                    return true;
                }
            }
            catch (IncludedFilesProcessor.1CancelException ex9) {
                throw b(ex9);
            }
        }
        final PsiManager instance = PsiManager.getInstance(project);
        Object o = null;
        Label_0307: {
            Label_0284: {
                Label_0276: {
                    try {
                        if (!virtualFile.isValid()) {
                            break Label_0284;
                        }
                        if (!virtualFile.isDirectory()) {
                            break Label_0276;
                        }
                    }
                    catch (IncludedFilesProcessor.1CancelException ex10) {
                        throw b(ex10);
                    }
                    o = instance.findDirectory(virtualFile);
                    break Label_0284;
                }
                o = instance.findFile(virtualFile);
                try {
                    if (o == null) {
                        break Label_0307;
                    }
                    final Object o2 = psiElementProcessor;
                    final Object o3 = o;
                    final boolean b4 = ((PsiElementProcessor)o2).execute((PsiElement)o3);
                    if (b4) {
                        break Label_0307;
                    }
                    return false;
                }
                catch (IncludedFilesProcessor.1CancelException ex11) {
                    throw b(ex11);
                }
            }
            try {
                final Object o2 = psiElementProcessor;
                final Object o3 = o;
                final boolean b4 = ((PsiElementProcessor)o2).execute((PsiElement)o3);
                if (b4) {
                    return true;
                }
            }
            catch (IncludedFilesProcessor.1CancelException ex12) {
                throw b(ex12);
            }
        }
        return false;
    }
    
    public static boolean shouldSkip(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor", "shouldSkip"));
            }
        }
        catch (IncludedFilesProcessor.1CancelException ex) {
            throw b(ex);
        }
        return AppleFramework.FRAMEWORK_CONDITION.value((Object)virtualFile);
    }
    
    public static boolean processFiles(@NotNull final Project p0, @NotNull final Iterable<VirtualFile> p1, @Nullable final Condition<VirtualFile> p2, final boolean p3, @NotNull final PsiElementProcessor<PsiFileSystemItem> p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processFiles"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "files"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processFiles"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "processor"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "processFiles"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   132: athrow         
        //   133: aload_1        
        //   134: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.a:(Ljava/lang/Iterable;)Ljava/lang/Iterable;
        //   137: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   142: astore          5
        //   144: aload           5
        //   146: invokeinterface java/util/Iterator.hasNext:()Z
        //   151: ifeq            238
        //   154: aload           5
        //   156: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   161: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   164: astore          6
        //   166: iload_3        
        //   167: ifeq            217
        //   170: aload           6
        //   172: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //   175: ifeq            217
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   184: athrow         
        //   185: aload           6
        //   187: new             Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1;
        //   190: dup            
        //   191: iconst_0       
        //   192: anewarray       Lcom/intellij/openapi/vfs/VirtualFileVisitor$Option;
        //   195: aload_0        
        //   196: aload_2        
        //   197: aload           4
        //   199: invokespecial   com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1.<init>:([Lcom/intellij/openapi/vfs/VirtualFileVisitor$Option;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/util/Condition;Lcom/intellij/psi/search/PsiElementProcessor;)V
        //   202: invokestatic    com/intellij/openapi/vfs/VfsUtilCore.visitChildrenRecursively:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFileVisitor;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   205: pop            
        //   206: goto            235
        //   209: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   212: athrow         
        //   213: astore          7
        //   215: iconst_0       
        //   216: ireturn        
        //   217: aload_0        
        //   218: aload           6
        //   220: aload_2        
        //   221: aload           4
        //   223: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.processFile:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/util/Condition;Lcom/intellij/psi/search/PsiElementProcessor;)Z
        //   226: ifne            235
        //   229: iconst_0       
        //   230: ireturn        
        //   231: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   234: athrow         
        //   235: goto            144
        //   238: iconst_1       
        //   239: ireturn        
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Ljava/lang/Iterable<Lcom/intellij/openapi/vfs/VirtualFile;>;Lcom/intellij/openapi/util/Condition<Lcom/intellij/openapi/vfs/VirtualFile;>;ZLcom/intellij/psi/search/PsiElementProcessor<Lcom/intellij/psi/PsiFileSystemItem;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                                   
        //  -----  -----  -----  -----  ---------------------------------------------------------------------------------------
        //  166    178    181    185    Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1CancelException;
        //  88     129    129    133    Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1CancelException;
        //  44     84     84     88     Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1CancelException;
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1CancelException;
        //  185    206    213    217    Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1CancelException;
        //  170    209    209    213    Ljava/lang/IllegalArgumentException;
        //  217    231    231    235    Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0185:
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
    
    private static Iterable<VirtualFile> a(@NotNull final Iterable<VirtualFile> iterable) {
        try {
            if (iterable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor", "iterateFilesFirst"));
            }
        }
        catch (IncludedFilesProcessor.1CancelException ex) {
            throw b(ex);
        }
        return new Iterable<VirtualFile>() {
            @Override
            public Iterator<VirtualFile> iterator() {
                return new Iterator<VirtualFile>() {
                    Iterator<VirtualFile> iterator = null;
                    List<VirtualFile> dirs = null;
                    boolean processingDirs = false;
                    VirtualFile prefetched = null;
                    
                    @Override
                    public boolean hasNext() {
                        try {
                            this.a();
                            if (this.prefetched != null) {
                                return true;
                            }
                        }
                        catch (NoSuchElementException ex) {
                            throw b(ex);
                        }
                        return false;
                    }
                    
                    @Override
                    public VirtualFile next() {
                        try {
                            if (this.prefetched == null) {
                                this.a();
                            }
                        }
                        catch (NoSuchElementException ex) {
                            throw b(ex);
                        }
                        try {
                            if (this.prefetched == null) {
                                throw new NoSuchElementException("No more items");
                            }
                        }
                        catch (NoSuchElementException ex2) {
                            throw b(ex2);
                        }
                        final VirtualFile prefetched = this.prefetched;
                        this.prefetched = null;
                        return prefetched;
                    }
                    
                    private void a() {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     0: aload_0        
                        //     1: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.prefetched:Lcom/intellij/openapi/vfs/VirtualFile;
                        //     4: ifnull          12
                        //     7: return         
                        //     8: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //    11: athrow         
                        //    12: aload_0        
                        //    13: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
                        //    16: ifnonnull       42
                        //    19: aload_0        
                        //    20: aload_0        
                        //    21: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.this$0:Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2;
                        //    24: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2.val$files:Ljava/lang/Iterable;
                        //    27: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
                        //    32: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
                        //    35: goto            42
                        //    38: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //    41: athrow         
                        //    42: aload_0        
                        //    43: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
                        //    46: invokeinterface java/util/Iterator.hasNext:()Z
                        //    51: ifeq            165
                        //    54: aload_0        
                        //    55: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
                        //    58: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
                        //    63: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
                        //    66: astore_1       
                        //    67: aload_1        
                        //    68: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
                        //    71: istore_2       
                        //    72: iload_2        
                        //    73: aload_0        
                        //    74: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
                        //    77: if_icmpne       90
                        //    80: aload_0        
                        //    81: aload_1        
                        //    82: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.prefetched:Lcom/intellij/openapi/vfs/VirtualFile;
                        //    85: return         
                        //    86: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //    89: athrow         
                        //    90: aload_0        
                        //    91: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
                        //    94: ifne            162
                        //    97: iload_2        
                        //    98: ifeq            140
                        //   101: goto            108
                        //   104: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   107: athrow         
                        //   108: aload_0        
                        //   109: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
                        //   112: ifnonnull       140
                        //   115: goto            122
                        //   118: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   121: athrow         
                        //   122: aload_0        
                        //   123: new             Ljava/util/ArrayList;
                        //   126: dup            
                        //   127: invokespecial   java/util/ArrayList.<init>:()V
                        //   130: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
                        //   133: goto            140
                        //   136: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   139: athrow         
                        //   140: iload_2        
                        //   141: ifeq            162
                        //   144: aload_0        
                        //   145: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
                        //   148: aload_1        
                        //   149: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
                        //   154: pop            
                        //   155: goto            162
                        //   158: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   161: athrow         
                        //   162: goto            42
                        //   165: aload_0        
                        //   166: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.prefetched:Lcom/intellij/openapi/vfs/VirtualFile;
                        //   169: ifnonnull       229
                        //   172: aload_0        
                        //   173: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
                        //   176: ifne            229
                        //   179: goto            186
                        //   182: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   185: athrow         
                        //   186: aload_0        
                        //   187: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
                        //   190: ifnull          229
                        //   193: goto            200
                        //   196: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   199: athrow         
                        //   200: aload_0        
                        //   201: iconst_1       
                        //   202: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.processingDirs:Z
                        //   205: aload_0        
                        //   206: aload_0        
                        //   207: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.dirs:Ljava/util/List;
                        //   210: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
                        //   215: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.iterator:Ljava/util/Iterator;
                        //   218: aload_0        
                        //   219: invokespecial   com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.a:()V
                        //   222: goto            229
                        //   225: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$2$1.b:(Ljava/util/NoSuchElementException;)Ljava/util/NoSuchElementException;
                        //   228: athrow         
                        //   229: return         
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                              
                        //  -----  -----  -----  -----  ----------------------------------
                        //  0      8      8      12     Ljava/util/NoSuchElementException;
                        //  12     35     38     42     Ljava/util/NoSuchElementException;
                        //  72     86     86     90     Ljava/util/NoSuchElementException;
                        //  90     101    104    108    Ljava/util/NoSuchElementException;
                        //  97     115    118    122    Ljava/util/NoSuchElementException;
                        //  108    133    136    140    Ljava/util/NoSuchElementException;
                        //  140    155    158    162    Ljava/util/NoSuchElementException;
                        //  165    179    182    186    Ljava/util/NoSuchElementException;
                        //  172    193    196    200    Ljava/util/NoSuchElementException;
                        //  186    222    225    229    Ljava/util/NoSuchElementException;
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                    
                    private static NoSuchElementException b(final NoSuchElementException ex) {
                        return ex;
                    }
                };
            }
        };
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
