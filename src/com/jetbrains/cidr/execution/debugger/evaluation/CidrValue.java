// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.util.SmartList;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import java.util.Collection;
import java.util.Iterator;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.openapi.util.Pair;
import javax.swing.Icon;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerUtil;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.xdebugger.impl.ui.XDebuggerUIConstants;
import com.intellij.openapi.util.Expirable;
import com.intellij.xdebugger.Obsolescent;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.xdebugger.frame.XValuePlace;
import com.intellij.xdebugger.frame.XValueNode;
import com.intellij.xdebugger.frame.XNavigatable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.xdebugger.frame.XNamedValue;

public abstract class CidrValue extends XNamedValue implements UserDataHolderEx
{
    public static Key<Boolean> DO_NOT_SHOW_ADDRESSES;
    @NotNull
    private final UserDataHolderBase myUserDataHolder;
    @NotNull
    private final CidrDebugProcess myProcess;
    @Nullable
    private final XSourcePosition myPosition;
    @NotNull
    private final CidrStackFrame myFrame;
    @NotNull
    private List<String> myPresentationErrors;
    
    public CidrValue(@NotNull final String s, @NotNull final CidrDebugProcess myProcess, @Nullable final XSourcePosition myPosition, @NotNull final CidrStackFrame myFrame) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "<init>"));
        }
        if (myProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "<init>"));
        }
        if (myFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "<init>"));
        }
        super(s);
        this.myUserDataHolder = new UserDataHolderBase();
        this.myPresentationErrors = Collections.synchronizedList(new ArrayList<String>());
        this.myProcess = myProcess;
        this.myPosition = myPosition;
        this.myFrame = myFrame;
    }
    
    @Nullable
    public <T> T getUserData(@NotNull final Key<T> key) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "getUserData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (T)this.myUserDataHolder.getUserData((Key)key);
    }
    
    public <T> void putUserData(@NotNull final Key<T> key, @Nullable final T t) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "putUserData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myUserDataHolder.putUserData((Key)key, (Object)t);
    }
    
    @NotNull
    public <T> T putUserDataIfAbsent(@NotNull final Key<T> key, @NotNull final T t) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "putUserDataIfAbsent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "putUserDataIfAbsent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Object putUserDataIfAbsent;
        try {
            putUserDataIfAbsent = this.myUserDataHolder.putUserDataIfAbsent((Key)key, (Object)t);
            if (putUserDataIfAbsent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "putUserDataIfAbsent"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return (T)putUserDataIfAbsent;
    }
    
    public <T> boolean replace(@NotNull final Key<T> key, @Nullable final T t, @Nullable final T t2) {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "replace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myUserDataHolder.replace((Key)key, (Object)t, (Object)t2);
    }
    
    @NotNull
    public CidrStackFrame getFrame() {
        CidrStackFrame myFrame;
        try {
            myFrame = this.myFrame;
            if (myFrame == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "getFrame"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myFrame;
    }
    
    @Nullable
    public XSourcePosition getSourcePosition() {
        return this.myPosition;
    }
    
    @NotNull
    public String getEvaluationExpression() {
        String evaluationExpression;
        try {
            evaluationExpression = this.getEvaluationExpression(false);
            if (evaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "getEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return evaluationExpression;
    }
    
    @NotNull
    public String getEvaluationExpression(final boolean b) {
        String name;
        try {
            name = this.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "getEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return name;
    }
    
    @NotNull
    public CidrDebugProcess getProcess() {
        CidrDebugProcess myProcess;
        try {
            myProcess = this.myProcess;
            if (myProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "getProcess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myProcess;
    }
    
    public void computeSourcePosition(@NotNull final XNavigatable xNavigatable) {
        try {
            if (xNavigatable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "navigatable", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "computeSourcePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final XSourcePosition sourcePosition = this.getSourcePosition();
        XSourcePosition doComputePosition = null;
        Label_0067: {
            try {
                if (sourcePosition == null) {
                    doComputePosition = null;
                    break Label_0067;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            doComputePosition = this.doComputePosition(sourcePosition);
        }
        xNavigatable.setSourcePosition(doComputePosition);
    }
    
    @Nullable
    protected abstract XSourcePosition doComputePosition(@NotNull final XSourcePosition p0);
    
    public final void computePresentation(@NotNull final XValueNode xValueNode, @NotNull final XValuePlace xValuePlace) {
        try {
            if (xValueNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "computePresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (xValuePlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "computePresentation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (CidrDebugProcess.viewsUpdatesDisabledInTests(xValueNode)) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final IllegalArgumentException ex4;
        final EvaluationContext evaluationContext;
        boolean b;
        Icon icon;
        final Pair pair;
        String s;
        XFullValueEvaluator fullValueEvaluator;
        final String s2;
        this.myProcess.postCommand(debuggerDriver -> {
            try {
                if (xValueNode == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "lambda$computePresentation$0"));
                    throw ex4;
                }
            }
            catch (DebuggerCommandException ex5) {
                throw b(ex5);
            }
            try {
                if (xValueNode.isObsolete()) {
                    return;
                }
            }
            catch (DebuggerCommandException ex6) {
                throw b(ex6);
            }
            try {
                this.createEvaluationContext(debuggerDriver, (Expirable)new XValueNodeExpirable((Obsolescent)xValueNode));
                try {
                    this.doComputeMayHaveChildren(evaluationContext);
                    icon = this.doComputeIcon(evaluationContext, b);
                }
                catch (DebuggerCommandException ex7) {
                    icon = XDebuggerUIConstants.ERROR_MESSAGE_ICON;
                    this.myPresentationErrors.add(ex7.getMessage());
                    b = true;
                }
                try {
                    this.doComputeType(evaluationContext);
                    this.doComputeValueAndEvaluator(evaluationContext);
                    s = (String)pair.first;
                    fullValueEvaluator = (XFullValueEvaluator)pair.second;
                }
                catch (DebuggerCommandException ex8) {
                    CidrErrorValue.doComputePresentation(xValueNode, ex8.getMessage(), b);
                    return;
                }
                try {
                    if (fullValueEvaluator != null) {
                        xValueNode.setFullValueEvaluator(fullValueEvaluator);
                    }
                }
                catch (DebuggerCommandException ex9) {
                    throw b(ex9);
                }
                xValueNode.setPresentation(icon, s2, s, b);
            }
            catch (ExecutionException ex10) {
                CidrErrorValue.doComputePresentation(xValueNode, CidrDebuggerUtil.getExceptionMessage((Exception)ex10), b);
                throw ex10;
            }
        });
    }
    
    protected abstract boolean doComputeMayHaveChildren(@NotNull final EvaluationContext p0) throws ExecutionException, DebuggerCommandException;
    
    @Nullable
    protected abstract Icon doComputeIcon(@NotNull final EvaluationContext p0, final boolean p1) throws ExecutionException, DebuggerCommandException;
    
    @Nullable
    protected abstract String doComputeType(@NotNull final EvaluationContext p0) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    protected abstract Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext p0) throws ExecutionException, DebuggerCommandException;
    
    public final void computeChildren(@NotNull final XCompositeNode xCompositeNode) {
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "computeChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (CidrDebugProcess.viewsUpdatesDisabledInTests(xCompositeNode)) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.myPresentationErrors.isEmpty()) {
                xCompositeNode.addChildren(createErrorChildren(this.myPresentationErrors), false);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        this.computeValueChildren(xCompositeNode);
    }
    
    @NotNull
    protected static XValueChildrenList createErrorChildren(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errors", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "createErrorChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final XValueChildrenList list2 = new XValueChildrenList();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add((XNamedValue)new CidrErrorValue(iterator.next()));
        }
        XValueChildrenList list3;
        try {
            list3 = list2;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "createErrorChildren"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return list3;
    }
    
    protected abstract void computeValueChildren(@NotNull final XCompositeNode p0);
    
    public static void addLocalValues(@NotNull final Collection<LLValue> collection, @NotNull final CidrDebugProcess cidrDebugProcess, @Nullable final XSourcePosition xSourcePosition, @NotNull final CidrStackFrame cidrStackFrame, @NotNull final XCompositeNode xCompositeNode) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "vars", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "addLocalValues"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (cidrDebugProcess == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "addLocalValues"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (cidrStackFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "addLocalValues"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "addLocalValues"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (xCompositeNode.isObsolete()) {
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final SmartList list = new SmartList();
        final Iterator<LLValue> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ((List<CidrLocalValue>)list).add(new CidrLocalValue(iterator.next(), cidrDebugProcess, xSourcePosition, cidrStackFrame));
        }
        addAllTo((Collection<CidrValue>)list, xCompositeNode);
    }
    
    public static void addAllTo(@NotNull final Collection<CidrValue> collection, @NotNull final XCompositeNode xCompositeNode) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "values", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "addAllTo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "addAllTo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (xCompositeNode.isObsolete()) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        XValueChildrenList empty = null;
        Label_0134: {
            try {
                if (collection.isEmpty()) {
                    empty = XValueChildrenList.EMPTY;
                    break Label_0134;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            empty = new XValueChildrenList(collection.size());
        }
        final XValueChildrenList list = empty;
        final Iterator<CidrValue> iterator = collection.iterator();
        while (iterator.hasNext()) {
            list.add((XNamedValue)iterator.next());
        }
        xCompositeNode.addChildren(list, true);
    }
    
    @NotNull
    public abstract String getConsoleDescription(@NotNull final EvaluationContext p0) throws ExecutionException, DebuggerCommandException;
    
    public EvaluationContext createEvaluationContext(@NotNull final DebuggerDriver debuggerDriver, @Nullable final Expirable expirable) {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValue", "createEvaluationContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.getProcess().createEvaluationContext(debuggerDriver, expirable, this.myFrame);
    }
    
    static {
        CidrValue.DO_NOT_SHOW_ADDRESSES = (Key<Boolean>)Key.create("DO_NOT_SHOW_ADDRESSES");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
