// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.openapi.util.Comparing;
import javax.swing.Icon;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.xdebugger.frame.XStackFrame;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.execution.debugger.backend.LLFrame;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLThread;
import com.intellij.xdebugger.frame.XExecutionStack;

private class CidrExecutionStack extends XExecutionStack
{
    @NotNull
    private final LLThread myThread;
    @Nullable
    private volatile CidrStackFrame myTopFrame;
    @Nullable
    private final CidrSuspensionCause mySuspensionCause;
    
    public CidrExecutionStack(@Nullable final LLThread myThread, final LLFrame llFrame, @Nullable final boolean b, final CidrSuspensionCause mySuspensionCause) {
        if (myThread == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thread", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack", "<init>"));
        }
        super(myThread.getDisplayName(), b ? AllIcons.Debugger.ThreadCurrent : AllIcons.Debugger.ThreadSuspended);
        this.myThread = myThread;
        this.mySuspensionCause = mySuspensionCause;
        this.myTopFrame = ((llFrame == null) ? null : this.a(llFrame));
    }
    
    public XStackFrame getTopFrame() {
        return this.myTopFrame;
    }
    
    public void computeStackFrames(final int p0, final XExecutionStack.XStackFrameContainer p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess.viewsUpdatesDisabledInTests:(Ljava/lang/Object;)Z
        //     4: ifeq            12
        //     7: return         
        //     8: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    11: athrow         
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack.this$0:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //    16: aload_0        
        //    17: aload_2        
        //    18: iload_1        
        //    19: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack;Lcom/intellij/xdebugger/frame/XExecutionStack$XStackFrameContainer;I)Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerUIUpdateCommand;
        //    24: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.postCommand:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess$DebuggerCommand;)V
        //    27: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      8      12     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0275_1:
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
    
    private boolean a() {
        try {
            if (!CidrDebugProcess.this.driverSupportsDisasm()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.mySuspensionCause == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @NotNull
    private CidrStackFrame a(@NotNull final LLFrame llFrame) {
        try {
            if (llFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack", "newFrame"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CidrStackFrame cidrStackFrame;
        try {
            cidrStackFrame = new CidrStackFrame(CidrDebugProcess.this, this.myThread, llFrame, this.mySuspensionCause);
            if (cidrStackFrame == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack", "newFrame"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return cidrStackFrame;
    }
    
    @Nullable
    public GutterIconRenderer getExecutionLineIconRenderer() {
        try {
            if (this.mySuspensionCause == null) {
                return super.getExecutionLineIconRenderer();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new GutterIconRenderer() {
            @NotNull
            public Icon getIcon() {
                Icon icon;
                try {
                    icon = CidrExecutionStack.this.mySuspensionCause.icon;
                    if (icon == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack$1", "getIcon"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return icon;
            }
            
            @Nullable
            public String getTooltipText() {
                return CidrExecutionStack.this.mySuspensionCause.getDisplayString();
            }
            
            public boolean equals(final Object o) {
                try {
                    if (this == o) {
                        return true;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    if (o == null) {
                        return false;
                    }
                    final GutterIconRenderer gutterIconRenderer = this;
                    final Class<? extends CidrDebugProcess$CidrExecutionStack$1> clazz = gutterIconRenderer.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    return Comparing.equal((Object)CidrExecutionStack.this.mySuspensionCause, (Object)((CidrExecutionStack)o).mySuspensionCause);
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final GutterIconRenderer gutterIconRenderer = this;
                    final Class<? extends CidrDebugProcess$CidrExecutionStack$1> clazz = gutterIconRenderer.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                return Comparing.equal((Object)CidrExecutionStack.this.mySuspensionCause, (Object)((CidrExecutionStack)o).mySuspensionCause);
            }
            
            public int hashCode() {
                return CidrExecutionStack.this.mySuspensionCause.hashCode();
            }
            
            public boolean isDumbAware() {
                return true;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    public String toString() {
        return this.myThread.toString();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
