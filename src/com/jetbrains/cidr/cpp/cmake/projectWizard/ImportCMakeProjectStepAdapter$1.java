// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.CheckedTreeNode;
import com.intellij.openapi.vfs.VirtualFileVisitor;

class ImportCMakeProjectStepAdapter$1 extends VirtualFileVisitor<CheckedTreeNode> {
    @NotNull
    public VirtualFileVisitor.Result visitFileEx(@NotNull final VirtualFile p0) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visitFileEx"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;
        //    48: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$000:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;)Lcom/intellij/openapi/progress/util/ProgressIndicatorBase;
        //    51: invokevirtual   com/intellij/openapi/progress/util/ProgressIndicatorBase.isCanceled:()Z
        //    54: ifeq            113
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;
        //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$100:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;)Lcom/intellij/openapi/vfs/VirtualFile;
        //    64: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //    67: dup            
        //    68: ifnonnull       112
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: new             Ljava/lang/IllegalStateException;
        //    81: dup            
        //    82: ldc             "@NotNull method %s.%s must not return null"
        //    84: ldc             2
        //    86: anewarray       Ljava/lang/Object;
        //    89: dup            
        //    90: ldc             0
        //    92: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //    94: aastore        
        //    95: dup            
        //    96: ldc             1
        //    98: ldc             "visitFileEx"
        //   100: aastore        
        //   101: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   104: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   107: athrow         
        //   108: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: areturn        
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;
        //   117: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$100:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   120: aload_1        
        //   121: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   124: ifeq            176
        //   127: getstatic       com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.CONTINUE:Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   130: dup            
        //   131: ifnonnull       175
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: new             Ljava/lang/IllegalStateException;
        //   144: dup            
        //   145: ldc             "@NotNull method %s.%s must not return null"
        //   147: ldc             2
        //   149: anewarray       Ljava/lang/Object;
        //   152: dup            
        //   153: ldc             0
        //   155: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //   157: aastore        
        //   158: dup            
        //   159: ldc             1
        //   161: ldc             "visitFileEx"
        //   163: aastore        
        //   164: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   167: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   170: athrow         
        //   171: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: areturn        
        //   176: invokestatic    com/intellij/openapi/fileTypes/FileTypeManager.getInstance:()Lcom/intellij/openapi/fileTypes/FileTypeManager;
        //   179: aload_1        
        //   180: invokevirtual   com/intellij/openapi/fileTypes/FileTypeManager.isFileIgnored:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   183: ifne            200
        //   186: aload_1        
        //   187: invokestatic    com/intellij/openapi/fileChooser/FileElement.isFileHidden:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   190: ifeq            250
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_1        
        //   201: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   204: dup            
        //   205: ifnonnull       249
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: new             Ljava/lang/IllegalStateException;
        //   218: dup            
        //   219: ldc             "@NotNull method %s.%s must not return null"
        //   221: ldc             2
        //   223: anewarray       Ljava/lang/Object;
        //   226: dup            
        //   227: ldc             0
        //   229: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //   231: aastore        
        //   232: dup            
        //   233: ldc             1
        //   235: ldc             "visitFileEx"
        //   237: aastore        
        //   238: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   241: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   244: athrow         
        //   245: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: areturn        
        //   250: aload_1        
        //   251: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //   254: ifeq            326
        //   257: aload_1        
        //   258: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   261: ldc             "CMakeFiles"
        //   263: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   266: ifeq            326
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: aload_1        
        //   277: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   280: dup            
        //   281: ifnonnull       325
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: new             Ljava/lang/IllegalStateException;
        //   294: dup            
        //   295: ldc             "@NotNull method %s.%s must not return null"
        //   297: ldc             2
        //   299: anewarray       Ljava/lang/Object;
        //   302: dup            
        //   303: ldc             0
        //   305: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //   307: aastore        
        //   308: dup            
        //   309: ldc             1
        //   311: ldc             "visitFileEx"
        //   313: aastore        
        //   314: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   317: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   320: athrow         
        //   321: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: areturn        
        //   326: new             Lcom/intellij/ui/CheckedTreeNode;
        //   329: dup            
        //   330: aload_1        
        //   331: invokespecial   com/intellij/ui/CheckedTreeNode.<init>:(Ljava/lang/Object;)V
        //   334: astore_2       
        //   335: aload_1        
        //   336: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //   339: ifne            403
        //   342: iconst_0       
        //   343: istore_3       
        //   344: aload_1        
        //   345: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getExtension:()Ljava/lang/String;
        //   348: astore          4
        //   350: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter.access$200:()[Ljava/lang/String;
        //   353: astore          5
        //   355: aload           5
        //   357: arraylength    
        //   358: istore          6
        //   360: iconst_0       
        //   361: istore          7
        //   363: iload           7
        //   365: iload           6
        //   367: if_icmpge       398
        //   370: aload           5
        //   372: iload           7
        //   374: aaload         
        //   375: astore          8
        //   377: aload           8
        //   379: aload           4
        //   381: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   384: ifeq            392
        //   387: iconst_1       
        //   388: istore_3       
        //   389: goto            398
        //   392: iinc            7, 1
        //   395: goto            363
        //   398: aload_2        
        //   399: iload_3        
        //   400: invokevirtual   com/intellij/ui/CheckedTreeNode.setChecked:(Z)V
        //   403: aload_0        
        //   404: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.getCurrentValue:()Ljava/lang/Object;
        //   407: checkcast       Lcom/intellij/ui/CheckedTreeNode;
        //   410: aload_2        
        //   411: invokevirtual   com/intellij/ui/CheckedTreeNode.add:(Ljavax/swing/tree/MutableTreeNode;)V
        //   414: aload_0        
        //   415: aload_2        
        //   416: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.setValueForChildren:(Ljava/lang/Object;)V
        //   419: getstatic       com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.CONTINUE:Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   422: dup            
        //   423: ifnonnull       460
        //   426: new             Ljava/lang/IllegalStateException;
        //   429: dup            
        //   430: ldc             "@NotNull method %s.%s must not return null"
        //   432: ldc             2
        //   434: anewarray       Ljava/lang/Object;
        //   437: dup            
        //   438: ldc             0
        //   440: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //   442: aastore        
        //   443: dup            
        //   444: ldc             1
        //   446: ldc             "visitFileEx"
        //   448: aastore        
        //   449: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   452: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   455: athrow         
        //   456: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   459: athrow         
        //   460: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     71     74     78     Ljava/lang/IllegalArgumentException;
        //  57     108    108    112    Ljava/lang/IllegalArgumentException;
        //  113    134    137    141    Ljava/lang/IllegalArgumentException;
        //  127    171    171    175    Ljava/lang/IllegalArgumentException;
        //  176    193    196    200    Ljava/lang/IllegalArgumentException;
        //  186    208    211    215    Ljava/lang/IllegalArgumentException;
        //  200    245    245    249    Ljava/lang/IllegalArgumentException;
        //  250    269    272    276    Ljava/lang/IllegalArgumentException;
        //  257    284    287    291    Ljava/lang/IllegalArgumentException;
        //  276    321    321    325    Ljava/lang/IllegalArgumentException;
        //  403    456    456    460    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0200:
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
    
    @Nullable
    public Iterable<VirtualFile> getChildrenIterable(@NotNull final VirtualFile p0) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getChildrenIterable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getChildren:()[Lcom/intellij/openapi/vfs/VirtualFile;
        //    48: astore_2       
        //    49: aload_2        
        //    50: invokedynamic   compare:()Ljava/util/Comparator;
        //    55: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;Ljava/util/Comparator;)V
        //    58: aload_2        
        //    59: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:([Ljava/lang/Object;)Ljava/util/ArrayList;
        //    62: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/lang/Iterable<Lcom/intellij/openapi/vfs/VirtualFile;>;
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
    
    public void afterChildrenVisited(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$1", "afterChildrenVisited"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!virtualFile.isDirectory()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final CheckedTreeNode checkedTreeNode = (CheckedTreeNode)this.getCurrentValue();
        final int childCount = checkedTreeNode.getChildCount();
        boolean checked = false;
        for (int i = 0; i < childCount; ++i) {
            if (((CheckedTreeNode)checkedTreeNode.getChildAt(i)).isChecked()) {
                checked = true;
                break;
            }
        }
        checkedTreeNode.setChecked(checked);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}