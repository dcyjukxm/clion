// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.util.DocumentUtil;
import com.intellij.openapi.application.ApplicationManager;
import java.util.List;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.xdebugger.impl.XSourcePositionImpl;
import com.intellij.pom.Navigatable;
import com.intellij.xdebugger.XSourcePosition;
import java.util.Iterator;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import com.jetbrains.cidr.execution.debugger.memory.AddressRange;
import java.util.function.BiFunction;
import com.intellij.openapi.project.Project;
import com.intellij.ui.GuiUtils;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.editor.Document;
import com.intellij.testFramework.LightVirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.jetbrains.cidr.execution.debugger.memory.AddressSpace;

public class CidrDisasmView implements AddressSpace.Listener<CidrDisasmRegion>
{
    @NotNull
    private final CidrDebugProcess myProcess;
    @NotNull
    private final LightVirtualFile myVirtualFile;
    @NotNull
    private final Document myDocument;
    @NotNull
    private final AddressSpace<CidrDisasmRegion> myAddressSpace;
    
    public CidrDisasmView(@NotNull final CidrDebugProcess myProcess) {
        if (myProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "<init>"));
        }
        this.myProcess = myProcess;
        (this.myVirtualFile = new LightVirtualFile(CidrDebuggerBundle.message("debug.disasm.view.name", this.myProcess.getSession().getSessionName()), (FileType)DisasmFileType.INSTANCE, (CharSequence)"")).setWritable(false);
        this.myVirtualFile.putUserData(Iconable.ICON_FLAG_IGNORE_MASK, (Object)2);
        this.myDocument = (Document)ReadAction.compute(() -> {
            final Document document = FileDocumentManager.getInstance().getDocument((VirtualFile)this.myVirtualFile);
            Logger log = null;
            boolean b = false;
            Label_0027: {
                try {
                    log = CidrDebuggerLog.LOG;
                    if (document != null) {
                        b = true;
                        break Label_0027;
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                b = false;
            }
            log.assertTrue(b, (Object)"null Document returned for newly created LightVirtualFile");
            return document;
        });
        this.myProcess.getSession().addSessionListener((XDebugSessionListener)new XDebugSessionListener() {
            public void sessionStopped() {
                final Project project;
                GuiUtils.invokeLaterIfNeeded(() -> {
                    CidrDisasmView.this.myProcess.getProject();
                    if (!project.isDisposed()) {
                        FileEditorManager.getInstance(project).closeFile((VirtualFile)CidrDisasmView.this.myVirtualFile);
                    }
                }, ModalityState.defaultModalityState());
            }
        });
        (this.myAddressSpace = new AddressSpace<CidrDisasmRegion>(CidrDisasmRegion::new)).addListener(this);
        this.d(() -> this.myAddressSpace.get(Address.NULL).addToDocument(this.myDocument, 0));
    }
    
    private void a(@NotNull final Address address) {
        try {
            if (address == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "requestDisassemblingForMissingRanges"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        final Iterator<AddressRange> iterator;
        this.d(() -> {
            try {
                if (address == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "lambda$requestDisassemblingForMissingRanges$2"));
                    throw ex2;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            this.myAddressSpace.preallocate(address).iterator();
            while (iterator.hasNext()) {
                this.myProcess.postCommand(new CidrDebugProcess.DebuggerCommand() {
                    final /* synthetic */ AddressRange val$range;
                    
                    @Override
                    public void run(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
                        try {
                            if (debuggerDriver == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$2", "run"));
                            }
                        }
                        catch (DebuggerCommandException ex) {
                            throw b(ex);
                        }
                        try {
                            CidrDisasmView.this.d(() -> CidrDisasmView.this.myAddressSpace.save((AddressSpace.Region)new CidrDisasmRegion(this.val$range, debuggerDriver.disassemble(this.val$range))));
                        }
                        catch (DebuggerCommandException ex2) {
                            CidrDebuggerLog.LOG.warn((Throwable)ex2);
                            CidrDisasmView.this.d(() -> CidrDisasmView.this.myAddressSpace.unallocate(this.val$range));
                        }
                    }
                    
                    @Override
                    public void rejected(@NotNull final String s) {
                        try {
                            if (s == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$2", "rejected"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        CidrDebuggerLog.LOG.warn(s);
                        CidrDisasmView.this.d(() -> CidrDisasmView.this.myAddressSpace.unallocate(this.val$range));
                    }
                    
                    private static Exception b(final Exception ex) {
                        return ex;
                    }
                });
            }
        });
    }
    
    public XSourcePosition disassemble(@NotNull final Address address) {
        try {
            if (address == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "disassemble"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return (XSourcePosition)new XSourcePosition() {
            private boolean myRequested;
            
            private void a() {
                try {
                    if (!this.myRequested) {
                        CidrDisasmView.this.a(address);
                        this.myRequested = true;
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
            }
            
            public int getLine() {
                this.a();
                return (int)ReadAction.compute(() -> {
                    try {
                        if (address == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "lambda$getLine$0"));
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    return CidrDisasmView.this.myAddressSpace.get(address).getLineNumberInDocument(address);
                });
            }
            
            public int getOffset() {
                this.a();
                return (int)ReadAction.compute(() -> {
                    try {
                        if (address == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "lambda$getOffset$1"));
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    return CidrDisasmView.this.myAddressSpace.get(address).getOffsetInDocument(address);
                });
            }
            
            @NotNull
            public VirtualFile getFile() {
                LightVirtualFile access$100;
                try {
                    access$100 = CidrDisasmView.this.myVirtualFile;
                    if (access$100 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "getFile"));
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                return (VirtualFile)access$100;
            }
            
            @NotNull
            public Navigatable createNavigatable(@NotNull final Project project) {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "createNavigatable"));
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                OpenFileDescriptor doCreateOpenFileDescriptor;
                try {
                    doCreateOpenFileDescriptor = XSourcePositionImpl.doCreateOpenFileDescriptor(project, (XSourcePosition)this);
                    if (doCreateOpenFileDescriptor == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "createNavigatable"));
                    }
                }
                catch (RuntimeException ex2) {
                    throw b(ex2);
                }
                return (Navigatable)doCreateOpenFileDescriptor;
            }
            
            private static RuntimeException b(final RuntimeException ex) {
                return ex;
            }
        };
    }
    
    @Override
    public void regionSplit(@NotNull final CidrDisasmRegion cidrDisasmRegion, @NotNull final List<? extends CidrDisasmRegion> list) {
        try {
            if (cidrDisasmRegion == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldRegion", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "regionSplit"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newRegions", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "regionSplit"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        ApplicationManager.getApplication().assertWriteAccessAllowed();
        cidrDisasmRegion.replaceWith(list);
    }
    
    @NotNull
    private Runnable c(@NotNull final Runnable p0) {
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
        //    18: ldc             "action"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "withDocumentWritable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokedynamic   run:(Lcom/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView;Ljava/lang/Runnable;)Ljava/lang/Runnable;
        //    51: dup            
        //    52: ifnonnull       89
        //    55: new             Ljava/lang/IllegalStateException;
        //    58: dup            
        //    59: ldc             "@NotNull method %s.%s must not return null"
        //    61: ldc             2
        //    63: anewarray       Ljava/lang/Object;
        //    66: dup            
        //    67: ldc             0
        //    69: ldc             "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             1
        //    75: ldc             "withDocumentWritable"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    88: athrow         
        //    89: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     85     85     89     Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    private void d(@NotNull final Runnable runnable) {
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "postWriteAction"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        GuiUtils.invokeLaterIfNeeded(() -> {
            try {
                if (runnable == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView", "lambda$postWriteAction$4"));
                    throw ex2;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            DocumentUtil.writeInRunUndoTransparentAction(this.c(runnable));
        }, ModalityState.defaultModalityState());
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
