// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import java.util.List;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;

class FilesModificationsListener$1 implements BulkFileListener {
    public void after(@NotNull final List<? extends VFileEvent> p0) {
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
        //    18: ldc             "events"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/FilesModificationsListener$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "after"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/FilesModificationsListener$1.this$0:Lcom/jetbrains/cidr/FilesModificationsListener;
        //    48: invokevirtual   com/jetbrains/cidr/FilesModificationsListener.getWatchedFiles:()Ljava/util/Set;
        //    51: astore_2       
        //    52: new             Lgnu/trove/THashSet;
        //    55: dup            
        //    56: getstatic       com/intellij/openapi/util/io/FileUtil.FILE_HASHING_STRATEGY:Lgnu/trove/TObjectHashingStrategy;
        //    59: invokespecial   gnu/trove/THashSet.<init>:(Lgnu/trove/TObjectHashingStrategy;)V
        //    62: astore_3       
        //    63: new             Lgnu/trove/THashSet;
        //    66: dup            
        //    67: invokespecial   gnu/trove/THashSet.<init>:()V
        //    70: astore          4
        //    72: iconst_0       
        //    73: istore          5
        //    75: iconst_0       
        //    76: istore          6
        //    78: aload_1        
        //    79: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    84: astore          9
        //    86: aload           9
        //    88: invokeinterface java/util/Iterator.hasNext:()Z
        //    93: ifeq            540
        //    96: aload           9
        //    98: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   103: checkcast       Lcom/intellij/openapi/vfs/newvfs/events/VFileEvent;
        //   106: astore          10
        //   108: aconst_null    
        //   109: dup            
        //   110: astore          8
        //   112: astore          7
        //   114: iload           5
        //   116: aload           10
        //   118: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.isFromRefresh:()Z
        //   121: ior            
        //   122: istore          5
        //   124: iload           6
        //   126: aload           10
        //   128: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getRequestor:()Ljava/lang/Object;
        //   131: instanceof      Lcom/intellij/openapi/vfs/SavingRequestor;
        //   134: ior            
        //   135: istore          6
        //   137: aload           10
        //   139: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFileContentChangeEvent;
        //   142: ifeq            162
        //   145: new             Ljava/io/File;
        //   148: dup            
        //   149: aload           10
        //   151: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getPath:()Ljava/lang/String;
        //   154: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   157: astore          7
        //   159: goto            369
        //   162: aload           10
        //   164: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFileDeleteEvent;
        //   167: ifne            185
        //   170: aload           10
        //   172: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFileCreateEvent;
        //   175: ifeq            202
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: new             Ljava/io/File;
        //   188: dup            
        //   189: aload           10
        //   191: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getPath:()Ljava/lang/String;
        //   194: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   197: astore          7
        //   199: goto            369
        //   202: aload           10
        //   204: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFileCopyEvent;
        //   207: ifeq            242
        //   210: aload           10
        //   212: checkcast       Lcom/intellij/openapi/vfs/newvfs/events/VFileCopyEvent;
        //   215: astore          11
        //   217: new             Ljava/io/File;
        //   220: dup            
        //   221: aload           11
        //   223: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileCopyEvent.getNewParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   226: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   229: aload           11
        //   231: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileCopyEvent.getNewChildName:()Ljava/lang/String;
        //   234: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   237: astore          8
        //   239: goto            369
        //   242: aload           10
        //   244: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFileMoveEvent;
        //   247: ifeq            295
        //   250: new             Ljava/io/File;
        //   253: dup            
        //   254: aload           10
        //   256: checkcast       Lcom/intellij/openapi/vfs/newvfs/events/VFileMoveEvent;
        //   259: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileMoveEvent.getOldParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   262: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   265: aload           10
        //   267: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   270: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   273: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   276: astore          7
        //   278: new             Ljava/io/File;
        //   281: dup            
        //   282: aload           10
        //   284: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getPath:()Ljava/lang/String;
        //   287: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   290: astore          8
        //   292: goto            369
        //   295: aload           10
        //   297: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFilePropertyChangeEvent;
        //   300: ifeq            369
        //   303: aload           10
        //   305: checkcast       Lcom/intellij/openapi/vfs/newvfs/events/VFilePropertyChangeEvent;
        //   308: astore          11
        //   310: aload           11
        //   312: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFilePropertyChangeEvent.getPropertyName:()Ljava/lang/String;
        //   315: ldc             "name"
        //   317: if_acmpne       369
        //   320: new             Ljava/io/File;
        //   323: dup            
        //   324: new             Ljava/io/File;
        //   327: dup            
        //   328: aload           10
        //   330: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getPath:()Ljava/lang/String;
        //   333: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   336: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   339: aload           10
        //   341: checkcast       Lcom/intellij/openapi/vfs/newvfs/events/VFilePropertyChangeEvent;
        //   344: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFilePropertyChangeEvent.getOldValue:()Ljava/lang/Object;
        //   347: checkcast       Ljava/lang/String;
        //   350: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   353: astore          7
        //   355: new             Ljava/io/File;
        //   358: dup            
        //   359: aload           10
        //   361: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getPath:()Ljava/lang/String;
        //   364: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   367: astore          8
        //   369: aload           7
        //   371: ifnonnull       386
        //   374: aload           8
        //   376: ifnull          537
        //   379: goto            386
        //   382: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   385: athrow         
        //   386: aload_2        
        //   387: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   392: astore          11
        //   394: aload           11
        //   396: invokeinterface java/util/Iterator.hasNext:()Z
        //   401: ifeq            537
        //   404: aload           11
        //   406: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   411: checkcast       Ljava/io/File;
        //   414: astore          12
        //   416: aload           7
        //   418: ifnull          480
        //   421: aload           7
        //   423: aload           12
        //   425: iconst_0       
        //   426: invokestatic    com/intellij/openapi/util/io/FileUtil.isAncestor:(Ljava/io/File;Ljava/io/File;Z)Z
        //   429: ifeq            480
        //   432: goto            439
        //   435: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   438: athrow         
        //   439: aload           10
        //   441: instanceof      Lcom/intellij/openapi/vfs/newvfs/events/VFileContentChangeEvent;
        //   444: ifeq            471
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: aload           4
        //   456: aload           10
        //   458: invokevirtual   com/intellij/openapi/vfs/newvfs/events/VFileEvent.getFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   461: invokestatic    com/intellij/util/containers/ContainerUtil.addIfNotNull:(Ljava/util/Collection;Ljava/lang/Object;)V
        //   464: goto            480
        //   467: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_3        
        //   472: aload           12
        //   474: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   479: pop            
        //   480: aload           8
        //   482: ifnull          534
        //   485: aload           8
        //   487: aload           12
        //   489: iconst_0       
        //   490: invokestatic    com/intellij/openapi/util/io/FileUtil.isAncestor:(Ljava/io/File;Ljava/io/File;Z)Z
        //   493: ifeq            534
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload           12
        //   505: invokevirtual   java/io/File.exists:()Z
        //   508: ifeq            534
        //   511: goto            518
        //   514: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   517: athrow         
        //   518: aload_3        
        //   519: aload           12
        //   521: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   526: pop            
        //   527: goto            534
        //   530: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   533: athrow         
        //   534: goto            394
        //   537: goto            86
        //   540: aload_3        
        //   541: invokeinterface java/util/Set.isEmpty:()Z
        //   546: ifeq            566
        //   549: aload           4
        //   551: invokeinterface java/util/Set.isEmpty:()Z
        //   556: ifne            587
        //   559: goto            566
        //   562: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   565: athrow         
        //   566: aload_0        
        //   567: getfield        com/jetbrains/cidr/FilesModificationsListener$1.this$0:Lcom/jetbrains/cidr/FilesModificationsListener;
        //   570: aload_3        
        //   571: aload           4
        //   573: iload           5
        //   575: iload           6
        //   577: invokevirtual   com/jetbrains/cidr/FilesModificationsListener.watchedFilesChanged:(Ljava/util/Set;Ljava/util/Set;ZZ)V
        //   580: goto            587
        //   583: invokestatic    com/jetbrains/cidr/FilesModificationsListener$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   586: athrow         
        //   587: return         
        //    Signature:
        //  (Ljava/util/List<+Lcom/intellij/openapi/vfs/newvfs/events/VFileEvent;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  162    178    181    185    Ljava/lang/IllegalArgumentException;
        //  369    379    382    386    Ljava/lang/IllegalArgumentException;
        //  416    432    435    439    Ljava/lang/IllegalArgumentException;
        //  421    447    450    454    Ljava/lang/IllegalArgumentException;
        //  439    467    467    471    Ljava/lang/IllegalArgumentException;
        //  480    496    499    503    Ljava/lang/IllegalArgumentException;
        //  485    511    514    518    Ljava/lang/IllegalArgumentException;
        //  503    527    530    534    Ljava/lang/IllegalArgumentException;
        //  540    559    562    566    Ljava/lang/IllegalArgumentException;
        //  549    580    583    587    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0439:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}