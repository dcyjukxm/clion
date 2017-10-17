// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.util.Key;
import java.io.OutputStream;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.atomic.AtomicReference;
import com.intellij.execution.process.AnsiEscapeDecoder;
import com.intellij.execution.process.ProcessHandler;

private class MyProcessHandler extends ProcessHandler implements AnsiEscapeDecoder.ColoredTextAcceptor
{
    private final AtomicReference<Integer> myExitCode;
    private final AnsiEscapeDecoder myAnsiEscapeDecoder;
    
    private MyProcessHandler() {
        this.myExitCode = new AtomicReference<Integer>();
        this.myAnsiEscapeDecoder = new AnsiEscapeDecoder();
    }
    
    public void setExitCode(final int n) {
        this.myExitCode.set(n);
    }
    
    @NotNull
    public Integer getExitCode() {
        final Integer n = this.myExitCode.get();
        Integer value = null;
        Label_0027: {
            try {
                if (n == null) {
                    final int intValue = 0;
                    break Label_0027;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final int intValue = n;
            try {
                value = intValue;
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler", "getExitCode"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return value;
    }
    
    protected void destroyProcessImpl() {
        this.a(false);
    }
    
    protected void detachProcessImpl() {
        this.a(true);
    }
    
    private void a(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //     3: aload_0        
        //     4: iload_1        
        //     5: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler;Z)Ljava/lang/Runnable;
        //    10: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        //    15: pop            
        //    16: return         
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
    
    public boolean detachIsDefault() {
        return CidrDebugProcess.this.isDetachDefault();
    }
    
    public OutputStream getProcessInput() {
        return CidrDebugProcess.access$500(CidrDebugProcess.this).getProcessInput();
    }
    
    public final void notifyTextAvailable(final String s, final Key key) {
        this.myAnsiEscapeDecoder.escapeText(s, key, (AnsiEscapeDecoder.ColoredTextAcceptor)this);
    }
    
    public void coloredTextAvailable(@NotNull final String s, @NotNull final Key key) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler", "coloredTextAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$MyProcessHandler", "coloredTextAvailable"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        super.notifyTextAvailable(s, key);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
