// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import java.util.List;
import com.intellij.xdebugger.XDebuggerUtil;
import java.util.Iterator;
import com.intellij.xdebugger.breakpoints.XBreakpointManager;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLCodepoint;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.breakpoints.XBreakpoint;

public class CidrWatchpointHandler extends CidrCodePointHandlerBase<XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>>
{
    public CidrWatchpointHandler(final CidrDebugProcess cidrDebugProcess) {
        super(cidrDebugProcess, CidrWatchpointType.class);
    }
    
    @Override
    protected Collection<LLCodepoint> doAddCodepoints(final DebuggerDriver p0, final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> p1, final long p2, final int p3) throws ExecutionException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/intellij/xdebugger/breakpoints/XBreakpoint.getProperties:()Lcom/intellij/xdebugger/breakpoints/XBreakpointProperties;
        //     6: checkcast       Lcom/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties;
        //     9: astore          6
        //    11: aload           6
        //    13: ifnull          52
        //    16: aload           6
        //    18: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.getDebugProcess:()Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointHandler.myProcess:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //    25: if_acmpne       52
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointHandler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    34: athrow         
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointHandler.myProcess:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //    39: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.supportsWatchpoints:()Z
        //    42: ifne            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointHandler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    51: athrow         
        //    52: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //    55: areturn        
        //    56: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointHandler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    59: athrow         
        //    60: aload_1        
        //    61: lload_3        
        //    62: iload           5
        //    64: aload           6
        //    66: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.getLLValue:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
        //    69: aload           6
        //    71: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.getExpr:()Ljava/lang/String;
        //    74: aload           6
        //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.getLifetime:()Lcom/jetbrains/cidr/execution/debugger/backend/LLWatchpoint$Lifetime;
        //    79: aload           6
        //    81: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.getAccessType:()Lcom/jetbrains/cidr/execution/debugger/backend/LLWatchpoint$AccessType;
        //    84: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver.addWatchpoint:(JILcom/jetbrains/cidr/execution/debugger/backend/LLValue;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/backend/LLWatchpoint$Lifetime;Lcom/jetbrains/cidr/execution/debugger/backend/LLWatchpoint$AccessType;)Lcom/jetbrains/cidr/execution/debugger/backend/LLWatchpoint;
        //    87: astore          7
        //    89: aload           6
        //    91: aconst_null    
        //    92: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.setError:(Ljava/lang/String;)V
        //    95: goto            114
        //    98: astore          8
        //   100: aload           6
        //   102: aload           8
        //   104: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException.getMessage:()Ljava/lang/String;
        //   107: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties.setError:(Ljava/lang/String;)V
        //   110: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   113: areturn        
        //   114: aload           7
        //   116: invokestatic    java/util/Collections.singleton:(Ljava/lang/Object;)Ljava/util/Set;
        //   119: areturn        
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //    Signature:
        //  (Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver;Lcom/intellij/xdebugger/breakpoints/XBreakpoint<Lcom/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType$CidrWatchpointProperties;>;JI)Ljava/util/Collection<Lcom/jetbrains/cidr/execution/debugger/backend/LLCodepoint;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                    
        //  -----  -----  -----  -----  ------------------------------------------------------------------------
        //  35     56     56     60     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  16     45     48     52     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  11     28     31     35     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  60     95     98     114    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    
    public void cleanup() {
        final XBreakpointManager xBreakpointManager;
        final Iterator<XBreakpoint> iterator;
        XBreakpoint xBreakpoint;
        CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties;
        ApplicationManager.getApplication().runWriteAction(() -> {
            XDebuggerManager.getInstance(this.myProcess.getProject()).getBreakpointManager();
            xBreakpointManager.getBreakpoints(this.getBreakpointTypeClass()).iterator();
            while (iterator.hasNext()) {
                xBreakpoint = iterator.next();
                cidrWatchpointProperties = (CidrWatchpointType.CidrWatchpointProperties)xBreakpoint.getProperties();
                if (cidrWatchpointProperties != null && cidrWatchpointProperties.getDebugProcess() == this.myProcess) {
                    xBreakpointManager.removeBreakpoint(xBreakpoint);
                }
            }
        });
    }
    
    public void handleWatchpointScope(final int n) {
        final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> codepoint = this.getCodepoint(n);
        if (codepoint == null) {
            return;
        }
        this.doUnregisterBreakpoint(codepoint);
        XDebuggerUtil.getInstance().removeBreakpoint(this.myProcess.getProject(), (XBreakpoint)codepoint);
    }
    
    @Override
    protected void removeBreakpointInBackend(final DebuggerDriver debuggerDriver, final List<Integer> list, final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> xBreakpoint) throws ExecutionException, DebuggerCommandException {
        final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties = (CidrWatchpointType.CidrWatchpointProperties)xBreakpoint.getProperties();
        Label_0051: {
            Logger logger = null;
            boolean b = false;
            Label_0047: {
                Label_0038: {
                    try {
                        if (cidrWatchpointProperties == null) {
                            break Label_0051;
                        }
                        logger = CidrDebuggerLog.LOG;
                        final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties2 = cidrWatchpointProperties;
                        final CidrDebugProcess cidrDebugProcess = cidrWatchpointProperties2.getDebugProcess();
                        final CidrWatchpointHandler cidrWatchpointHandler = this;
                        final CidrDebugProcess cidrDebugProcess2 = cidrWatchpointHandler.myProcess;
                        if (cidrDebugProcess == cidrDebugProcess2) {
                            break Label_0038;
                        }
                        break Label_0038;
                    }
                    catch (ExecutionException ex) {
                        throw c((Exception)ex);
                    }
                    try {
                        logger = CidrDebuggerLog.LOG;
                        final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties2 = cidrWatchpointProperties;
                        final CidrDebugProcess cidrDebugProcess = cidrWatchpointProperties2.getDebugProcess();
                        final CidrWatchpointHandler cidrWatchpointHandler = this;
                        final CidrDebugProcess cidrDebugProcess2 = cidrWatchpointHandler.myProcess;
                        if (cidrDebugProcess == cidrDebugProcess2) {
                            b = true;
                            break Label_0047;
                        }
                    }
                    catch (ExecutionException ex2) {
                        throw c((Exception)ex2);
                    }
                }
                b = false;
            }
            logger.assertTrue(b);
        }
        debuggerDriver.removeWatchpoint(list);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
