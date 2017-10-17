// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.OutputStream;

public class ProcessInputWriter extends OutputStream
{
    private final Object myCloseOpLock;
    private File myInputFileDir;
    private volatile boolean closed;
    private volatile boolean pipeInput;
    private volatile OutputStream myOutputStream;
    
    public ProcessInputWriter() {
        this.myCloseOpLock = new Object();
    }
    
    public void initPipeInput(@NotNull final File p0, @NotNull final String p1) {
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
        //    18: ldc             "inputFileDir"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "initPipeInput"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "inputFileName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "initPipeInput"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: iconst_1       
        //    90: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.pipeInput:Z
        //    93: aload_0        
        //    94: iconst_0       
        //    95: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.closed:Z
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myCloseOpLock:Ljava/lang/Object;
        //   102: dup            
        //   103: astore_3       
        //   104: monitorenter   
        //   105: aload_0        
        //   106: aload_1        
        //   107: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myInputFileDir:Ljava/io/File;
        //   110: aload_3        
        //   111: monitorexit    
        //   112: goto            122
        //   115: astore          4
        //   117: aload_3        
        //   118: monitorexit    
        //   119: aload           4
        //   121: athrow         
        //   122: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   125: aload_0        
        //   126: aload_1        
        //   127: aload_2        
        //   128: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter;Ljava/io/File;Ljava/lang/String;)Ljava/lang/Runnable;
        //   133: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        //   138: pop            
        //   139: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  105    112    115    122    Any
        //  115    119    115    122    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119_1:
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
    
    public void initDispatchInput() {
        this.pipeInput = false;
    }
    
    @Override
    public void write(final int n) throws IOException {
        LLDBDriver.LOG.error("Shouldn't be here");
    }
    
    @Override
    public void write(@NotNull final byte[] p0, final int p1, final int p2) throws IOException {
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
        //    18: ldc             "bytes"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "write"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.pipeInput:Z
        //    48: ifeq            96
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.closed:Z
        //    55: ifne            145
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myOutputStream:Ljava/io/OutputStream;
        //    69: ifnull          145
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    78: athrow         
        //    79: aload_0        
        //    80: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.myOutputStream:Ljava/io/OutputStream;
        //    83: aload_1        
        //    84: iload_2        
        //    85: iload_3        
        //    86: invokevirtual   java/io/OutputStream.write:([BII)V
        //    89: goto            145
        //    92: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    95: athrow         
        //    96: new             Ljava/lang/String;
        //    99: dup            
        //   100: aload_1        
        //   101: iload_2        
        //   102: iload_3        
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.this$0:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;
        //   107: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.access$400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;)Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   110: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getCharset:()Ljava/nio/charset/Charset;
        //   113: invokespecial   java/lang/String.<init>:([BIILjava/nio/charset/Charset;)V
        //   116: astore          4
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$ProcessInputWriter.this$0:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;
        //   122: aload           4
        //   124: getstatic       com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$DispatchTarget.DispatchTargetProcess:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$DispatchTarget;
        //   127: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver.access$800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$DispatchTarget;)V
        //   130: goto            145
        //   133: astore          4
        //   135: new             Ljava/io/IOException;
        //   138: dup            
        //   139: aload           4
        //   141: invokespecial   java/io/IOException.<init>:(Ljava/lang/Throwable;)V
        //   144: athrow         
        //   145: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  65     92     92     96     Lcom/intellij/execution/ExecutionException;
        //  51     72     75     79     Lcom/intellij/execution/ExecutionException;
        //  44     58     61     65     Lcom/intellij/execution/ExecutionException;
        //  0      40     40     44     Lcom/intellij/execution/ExecutionException;
        //  96     130    133    145    Lcom/intellij/execution/ExecutionException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    File getInputFileDir() {
        synchronized (this.myCloseOpLock) {
            return this.myInputFileDir;
        }
    }
    
    @Override
    public void close() {
        try {
            if (!this.pipeInput) {
                return;
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        this.closed = true;
        synchronized (this.myCloseOpLock) {
            if (this.myOutputStream != null) {
                try {
                    this.myOutputStream.close();
                }
                catch (IOException ex2) {
                    LLDBDriver.LOG.warn((Throwable)ex2);
                }
                try {
                    if (this.myInputFileDir.exists()) {
                        FileUtil.delete(this.myInputFileDir);
                    }
                }
                catch (IOException ex3) {
                    throw b(ex3);
                }
            }
        }
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
