// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.Future;
import java.nio.charset.Charset;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.io.BaseOutputReader;
import org.jetbrains.annotations.NotNull;
import java.io.File;

private class MyLogReader
{
    @NotNull
    private final File myFile;
    @NotNull
    private final LogType myType;
    @Nullable
    private volatile BaseOutputReader myReader;
    private volatile boolean closed;
    final /* synthetic */ GLogOutputReaders this$0;
    
    private MyLogReader(@NotNull final GLogOutputReaders p0, @NotNull final File p1, @NotNull final String p2, final LogType p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       40
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "dir"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "<init>"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: aload_3        
        //    41: ifnonnull       80
        //    44: new             Ljava/lang/IllegalArgumentException;
        //    47: dup            
        //    48: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    50: ldc             3
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "name"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader"
        //    66: aastore        
        //    67: dup            
        //    68: ldc             2
        //    70: ldc             "<init>"
        //    72: aastore        
        //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    76: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    79: athrow         
        //    80: aload           4
        //    82: ifnonnull       121
        //    85: new             Ljava/lang/IllegalArgumentException;
        //    88: dup            
        //    89: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    91: ldc             3
        //    93: anewarray       Ljava/lang/Object;
        //    96: dup            
        //    97: ldc             0
        //    99: ldc             "type"
        //   101: aastore        
        //   102: dup            
        //   103: ldc             1
        //   105: ldc             "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader"
        //   107: aastore        
        //   108: dup            
        //   109: ldc             2
        //   111: ldc             "<init>"
        //   113: aastore        
        //   114: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   117: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   120: athrow         
        //   121: aload_0        
        //   122: aload_1        
        //   123: putfield        com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader.this$0:Lcom/jetbrains/cidr/execution/GLogOutputReaders;
        //   126: aload_0        
        //   127: invokespecial   java/lang/Object.<init>:()V
        //   130: aload_0        
        //   131: new             Ljava/io/File;
        //   134: dup            
        //   135: aload_2        
        //   136: new             Ljava/lang/StringBuilder;
        //   139: dup            
        //   140: invokespecial   java/lang/StringBuilder.<init>:()V
        //   143: aload_3        
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: ldc             "."
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: aload           4
        //   154: invokevirtual   com/jetbrains/cidr/execution/GLogOutputReaders$LogType.toString:()Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   163: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   166: putfield        com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader.myFile:Ljava/io/File;
        //   169: aload_0        
        //   170: aload           4
        //   172: putfield        com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader.myType:Lcom/jetbrains/cidr/execution/GLogOutputReaders$LogType;
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader.myFile:Ljava/io/File;
        //   179: invokevirtual   java/io/File.exists:()Z
        //   182: ifeq            193
        //   185: aload_0        
        //   186: getfield        com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader.myFile:Ljava/io/File;
        //   189: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   192: pop            
        //   193: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   196: aload_0        
        //   197: invokedynamic   run:(Lcom/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader;)Ljava/lang/Runnable;
        //   202: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        //   207: pop            
        //   208: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024_1:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    private void a(@NotNull final String s) throws IOException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "presentableName", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader", "start"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        this.myReader = new BaseOutputReader(new FileInputStream(this.myFile), null) {
            {
                this.start(s);
            }
            
            protected void onTextAvailable(@NotNull final String s) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader$1", "onTextAvailable"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                MyLogReader.this.this$0.onTextAvailable(s, MyLogReader.this.myType);
            }
            
            @NotNull
            protected Future<?> executeOnPooledThread(@NotNull final Runnable runnable) {
                try {
                    if (runnable == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader$1", "executeOnPooledThread"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                Future executeOnPooledThread;
                try {
                    executeOnPooledThread = ApplicationManager.getApplication().executeOnPooledThread(runnable);
                    if (executeOnPooledThread == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader$1", "executeOnPooledThread"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return (Future<?>)executeOnPooledThread;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    private void c() {
        final BaseOutputReader myReader = this.myReader;
        try {
            if (myReader != null) {
                myReader.stop();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.closed = true;
    }
    
    private void d() throws InterruptedException {
        final BaseOutputReader myReader = this.myReader;
        try {
            if (myReader != null) {
                myReader.waitFor();
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
    }
    
    @NotNull
    private File a() {
        File myFile;
        try {
            myFile = this.myFile;
            if (myFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader", "getFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myFile;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
