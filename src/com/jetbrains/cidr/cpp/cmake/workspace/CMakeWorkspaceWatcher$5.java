// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.openapi.project.Project;
import java.util.Map;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import com.jetbrains.cidr.FilesModificationsListener;

class CMakeWorkspaceWatcher$5 extends FilesModificationsListener {
    @NotNull
    @Override
    protected Set<File> getWatchedFiles() {
        Set<File> cMakeFiles;
        try {
            cMakeFiles = CMakeWorkspaceWatcher.access$500(CMakeWorkspaceWatcher.this).getCMakeFiles();
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
}