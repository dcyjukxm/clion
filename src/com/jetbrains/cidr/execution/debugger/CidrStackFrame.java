// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.roots.ProjectRootUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.util.Comparing;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import java.util.List;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.execution.debugger.backend.lldb.LLDBDriver;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.xdebugger.frame.XValuePlace;
import com.intellij.xdebugger.frame.XValueNode;
import com.intellij.xdebugger.frame.XNamedValue;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.icons.AllIcons;
import com.intellij.ui.ColoredTextContainer;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.LLFrame;
import com.jetbrains.cidr.execution.debugger.backend.LLThread;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.xdebugger.frame.XStackFrame;

public class CidrStackFrame extends XStackFrame implements UserDataHolderEx
{
    @NotNull
    private final UserDataHolderBase myUserDataHolder;
    @NotNull
    private final CidrDebugProcess myProcess;
    @NotNull
    private final LLThread myThread;
    @NotNull
    private final LLFrame myFrame;
    @Nullable
    private final CidrSuspensionCause mySuspensionCause;
    public static final Key THROW_ON_VARIABLES_COLLECTION;
    @NotNull
    private final NotNullLazyValue<Pair<XSourcePosition, Boolean>> mySourceOrDisasmLazyValue;
    
    public CidrStackFrame(@NotNull final CidrDebugProcess myProcess, @NotNull final LLThread myThread, @NotNull final LLFrame myFrame, @Nullable final CidrSuspensionCause mySuspensionCause) {
        if (myProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "<init>"));
        }
        if (myThread == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thread", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "<init>"));
        }
        if (myFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "<init>"));
        }
        this.myUserDataHolder = new UserDataHolderBase();
        this.mySourceOrDisasmLazyValue = (NotNullLazyValue<Pair<XSourcePosition, Boolean>>)NotNullLazyValue.createValue(this::doGetSourceOrDisasm);
        this.myProcess = myProcess;
        this.myThread = myThread;
        this.myFrame = myFrame;
        this.mySuspensionCause = mySuspensionCause;
    }
    
    @Nullable
    public <T> T getUserData(@NotNull final Key<T> key) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "getUserData"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return (T)this.myUserDataHolder.getUserData((Key)key);
    }
    
    public <T> void putUserData(@NotNull final Key<T> key, @Nullable final T t) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "putUserData"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        this.myUserDataHolder.putUserData((Key)key, (Object)t);
    }
    
    @NotNull
    public <T> T putUserDataIfAbsent(@NotNull final Key<T> key, @NotNull final T t) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "putUserDataIfAbsent"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "putUserDataIfAbsent"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        Object putUserDataIfAbsent;
        try {
            putUserDataIfAbsent = this.myUserDataHolder.putUserDataIfAbsent((Key)key, (Object)t);
            if (putUserDataIfAbsent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "putUserDataIfAbsent"));
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        return (T)putUserDataIfAbsent;
    }
    
    public <T> boolean replace(@NotNull final Key<T> key, @Nullable final T t, @Nullable final T t2) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "replace"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return this.myUserDataHolder.replace((Key)key, (Object)t, (Object)t2);
    }
    
    @NotNull
    public CidrDebugProcess getProcess() {
        CidrDebugProcess myProcess;
        try {
            myProcess = this.myProcess;
            if (myProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "getProcess"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return myProcess;
    }
    
    public long getThreadId() {
        return this.myThread.getId();
    }
    
    @NotNull
    public LLFrame getFrame() {
        LLFrame myFrame;
        try {
            myFrame = this.myFrame;
            if (myFrame == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "getFrame"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return myFrame;
    }
    
    public int getFrameIndex() {
        return this.myFrame.getIndex();
    }
    
    public Object getEqualityObject() {
        return Pair.create((Object)this.myThread.getId(), (Object)this.myFrame.getIndex());
    }
    
    @Nullable
    public XSourcePosition getSourcePosition() {
        return this.getSourcePosition(true);
    }
    
    @Nullable
    public XSourcePosition getSourcePosition(final boolean b) {
        final Pair pair = (Pair)this.mySourceOrDisasmLazyValue.getValue();
        Label_0035: {
            try {
                if (b) {
                    break Label_0035;
                }
                final Pair pair2 = pair;
                final Object o = pair2.second;
                final Boolean b2 = (Boolean)o;
                final boolean b3 = b2;
                if (b3) {
                    break Label_0035;
                }
                return null;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final Pair pair2 = pair;
                final Object o = pair2.second;
                final Boolean b2 = (Boolean)o;
                final boolean b3 = b2;
                if (b3) {
                    return (XSourcePosition)pair.first;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    public boolean hasSourceFile() {
        try {
            if (this.getSourcePosition(false) != null) {
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @NotNull
    protected Pair<XSourcePosition, Boolean> doGetSourceOrDisasm() {
        final VirtualFile b = this.b();
        Pair create = null;
        Label_0082: {
            if (b != null) {
                final XSourcePosition position = XDebuggerUtil.getInstance().createPosition(b, this.myFrame.getLine());
                Pair pair = null;
                Label_0047: {
                    try {
                        if (position == null) {
                            break Label_0082;
                        }
                        final XSourcePosition xSourcePosition = position;
                        final boolean b2 = true;
                        final Boolean b3 = b2;
                        pair = Pair.create((Object)xSourcePosition, (Object)b3);
                        if (pair == null) {
                            break Label_0047;
                        }
                        return (Pair<XSourcePosition, Boolean>)pair;
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    try {
                        final XSourcePosition xSourcePosition = position;
                        final boolean b2 = true;
                        final Boolean b3 = b2;
                        pair = Pair.create((Object)xSourcePosition, (Object)b3);
                        if (pair == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "doGetSourceOrDisasm"));
                        }
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                }
                return (Pair<XSourcePosition, Boolean>)pair;
            }
            try {
                create = Pair.create((Object)this.myProcess.createDisasmPosition(this.myFrame.getProgramCounter()), (Object)false);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "doGetSourceOrDisasm"));
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
        }
        return (Pair<XSourcePosition, Boolean>)create;
    }
    
    @Nullable
    private VirtualFile b() {
        final String file = this.myFrame.getFile();
        try {
            if (file == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return (VirtualFile)ReadAction.compute(() -> {
            final VirtualFile fileByPath = LocalFileSystem.getInstance().findFileByPath(file);
            try {
                if (fileByPath == null) {
                    return null;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            final VirtualFile symlinkedFileInContent = ProjectRootUtil.findSymlinkedFileInContent(this.myProcess.getProject(), fileByPath);
            Label_0054: {
                try {
                    if (!CidrDebuggerLog.LOG.isTraceEnabled()) {
                        return symlinkedFileInContent;
                    }
                    final VirtualFile virtualFile = fileByPath;
                    final VirtualFile virtualFile2 = symlinkedFileInContent;
                    final boolean b = virtualFile.equals(virtualFile2);
                    if (!b) {
                        break Label_0054;
                    }
                    return symlinkedFileInContent;
                }
                catch (RuntimeException ex2) {
                    throw b(ex2);
                }
                try {
                    final VirtualFile virtualFile = fileByPath;
                    final VirtualFile virtualFile2 = symlinkedFileInContent;
                    final boolean b = virtualFile.equals(virtualFile2);
                    if (!b) {
                        CidrDebuggerLog.LOG.trace("Debugger path resolved: " + fileByPath + " -> " + symlinkedFileInContent);
                    }
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
            }
            return symlinkedFileInContent;
        });
    }
    
    @Nullable
    public PsiElement getContext() {
        return (PsiElement)ApplicationManager.getApplication().runReadAction((Computable)(() -> this.myProcess.getTypesHelper().getContextElement(this.getSourcePosition(false))));
    }
    
    public void customizePresentation(@NotNull final ColoredTextContainer coloredTextContainer) {
        try {
            if (coloredTextContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "component", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "customizePresentation"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        coloredTextContainer.setIcon(AllIcons.Debugger.StackFrame);
        final XSourcePosition sourcePosition = this.getSourcePosition(false);
        try {
            if (sourcePosition != null) {
                coloredTextContainer.append(this.myFrame.getFunction(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
                coloredTextContainer.append(" " + sourcePosition.getFile().getName() + ":" + (sourcePosition.getLine() + 1), SimpleTextAttributes.GRAY_ITALIC_ATTRIBUTES);
                return;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        coloredTextContainer.append(this.myFrame.getFunction(), SimpleTextAttributes.GRAYED_ATTRIBUTES);
        coloredTextContainer.append(" " + this.myFrame.getProgramCounter(), SimpleTextAttributes.GRAYED_SMALL_ATTRIBUTES);
    }
    
    public final void computeChildren(@NotNull final XCompositeNode xCompositeNode) {
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "computeChildren"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (CidrDebugProcess.viewsUpdatesDisabledInTests(xCompositeNode)) {
                return;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final IllegalArgumentException ex3;
        this.myProcess.postCommand(debuggerDriver -> {
            try {
                if (xCompositeNode == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "lambda$computeChildren$2"));
                    throw ex3;
                }
            }
            catch (DebuggerCommandException ex4) {
                throw b(ex4);
            }
            try {
                this.a(debuggerDriver, xCompositeNode);
            }
            catch (DebuggerCommandException ex5) {
                xCompositeNode.setErrorMessage(ex5.getMessage());
            }
            catch (ExecutionException ex6) {
                xCompositeNode.setErrorMessage(CidrDebuggerUtil.getExceptionMessage((Exception)ex6));
                throw ex6;
            }
        });
    }
    
    private void a(@NotNull final DebuggerDriver debuggerDriver, @NotNull final XCompositeNode xCompositeNode) throws DebuggerCommandException, ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "doComputeChildren"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/CidrStackFrame", "doComputeChildren"));
            }
        }
        catch (DebuggerCommandException ex2) {
            throw b(ex2);
        }
        try {
            if (xCompositeNode.isObsolete()) {
                return;
            }
        }
        catch (DebuggerCommandException ex3) {
            throw b(ex3);
        }
        if (this.mySuspensionCause != null) {
            final XValueChildrenList list = new XValueChildrenList(1);
            list.add((XNamedValue)new XNamedValue(this.mySuspensionCause.type) {
                public void computePresentation(@NotNull final XValueNode xValueNode, @NotNull final XValuePlace xValuePlace) {
                    try {
                        if (xValueNode == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/CidrStackFrame$1", "computePresentation"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (xValuePlace == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/execution/debugger/CidrStackFrame$1", "computePresentation"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    xValueNode.setPresentation(CidrStackFrame.this.mySuspensionCause.icon, (String)null, CidrStackFrame.this.mySuspensionCause.reason, false);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            });
            xCompositeNode.addChildren(list, false);
        }
        ThrowInTest.doThrow((UserDataHolder)this, CidrStackFrame.THROW_ON_VARIABLES_COLLECTION);
        final XSourcePosition sourcePosition = this.getSourcePosition(false);
        debuggerDriver.setValuesFilteringEnabled(CidrDebuggerSettings.getInstance().VALUES_FILTER_ENABLED);
        List<LLValue> list2;
        if (this.myFrame.getLanguage() == DebuggerDriver.StandardDebuggerLanguage.SWIFT) {
            list2 = ((LLDBDriver)debuggerDriver).getVariables(this.myThread.getId(), this.myFrame.getIndex(), true, false);
        }
        else {
            list2 = debuggerDriver.getVariables(this.myThread.getId(), this.myFrame.getIndex());
        }
        CidrValue.addLocalValues(ContainerUtil.filter((Collection)list2, p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       15
            //     4: aload_2        
            //     5: iconst_0       
            //     6: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.setValid:(Z)V
            //     9: iconst_1       
            //    10: ireturn        
            //    11: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    14: athrow         
            //    15: iconst_1       
            //    16: newarray        Z
            //    18: astore_3       
            //    19: iconst_1       
            //    20: newarray        Z
            //    22: dup            
            //    23: iconst_0       
            //    24: iconst_0       
            //    25: bastore        
            //    26: astore          4
            //    28: aload_0        
            //    29: aload_1        
            //    30: aload_2        
            //    31: aload_3        
            //    32: aload           4
            //    34: invokedynamic   compute:(Lcom/jetbrains/cidr/execution/debugger/CidrStackFrame;Lcom/intellij/xdebugger/XSourcePosition;Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;[Z[Z)Lcom/intellij/openapi/util/ThrowableComputable;
            //    39: invokestatic    com/intellij/openapi/application/ReadAction.compute:(Lcom/intellij/openapi/util/ThrowableComputable;)Ljava/lang/Object;
            //    42: checkcast       Ljava/lang/Integer;
            //    45: astore          5
            //    47: aload_2        
            //    48: aload           4
            //    50: iconst_0       
            //    51: baload         
            //    52: ifne            101
            //    55: aload_3        
            //    56: iconst_0       
            //    57: baload         
            //    58: ifeq            109
            //    61: goto            68
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload           5
            //    70: ifnull          109
            //    73: goto            80
            //    76: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    79: athrow         
            //    80: aload           5
            //    82: invokevirtual   java/lang/Integer.intValue:()I
            //    85: aload_1        
            //    86: invokeinterface com/intellij/xdebugger/XSourcePosition.getLine:()I
            //    91: if_icmpge       109
            //    94: goto            101
            //    97: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   100: athrow         
            //   101: iconst_1       
            //   102: goto            110
            //   105: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   108: athrow         
            //   109: iconst_0       
            //   110: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.setValid:(Z)V
            //   113: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.getInstance:()Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;
            //   116: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.VALUES_FILTER_ENABLED:Z
            //   119: ifeq            149
            //   122: aload_3        
            //   123: iconst_0       
            //   124: baload         
            //   125: ifne            149
            //   128: goto            135
            //   131: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   134: athrow         
            //   135: aload           4
            //   137: iconst_0       
            //   138: baload         
            //   139: ifeq            157
            //   142: goto            149
            //   145: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   148: athrow         
            //   149: iconst_1       
            //   150: goto            158
            //   153: invokestatic    com/jetbrains/cidr/execution/debugger/CidrStackFrame.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   156: athrow         
            //   157: iconst_0       
            //   158: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  0      11     11     15     Ljava/lang/RuntimeException;
            //  47     61     64     68     Ljava/lang/RuntimeException;
            //  55     73     76     80     Ljava/lang/RuntimeException;
            //  68     94     97     101    Ljava/lang/RuntimeException;
            //  80     105    105    109    Ljava/lang/RuntimeException;
            //  110    128    131    135    Ljava/lang/RuntimeException;
            //  122    142    145    149    Ljava/lang/RuntimeException;
            //  135    153    153    157    Ljava/lang/RuntimeException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
        }), this.myProcess, sourcePosition, this, xCompositeNode);
    }
    
    public CidrEvaluator getEvaluator() {
        final CidrDebuggerLanguageSupportFactory[] array = (CidrDebuggerLanguageSupportFactory[])CidrDebuggerLanguageSupportFactory.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final CidrEvaluator evaluator = array[i].createEvaluator(this);
            try {
                if (evaluator != null) {
                    return evaluator;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
        }
        return null;
    }
    
    public String toString() {
        return this.myFrame.toString();
    }
    
    static {
        THROW_ON_VARIABLES_COLLECTION = Key.create("THROW_ON_VARIABLES_COLLECTION");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
