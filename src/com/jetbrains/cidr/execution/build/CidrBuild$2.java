// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.openapi.progress.ProcessCanceledException;
import java.util.concurrent.TimeUnit;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.concurrent.Semaphore;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.execution.ExecutionResult;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.openapi.ui.MessageType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.notification.NotificationGroup;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.intellij.execution.process.ProcessAdapter;

static final class CidrBuild$2 extends ProcessAdapter {
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