// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerUtil;
import java.util.Collections;
import com.intellij.openapi.util.Expirable;
import com.intellij.xdebugger.Obsolescent;
import com.intellij.xdebugger.frame.XCompositeNode;
import javax.swing.Icon;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.execution.debugger.ThrowInTest;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.ValueRenderer;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.CachedDebuggerResult;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Key;

public abstract class CidrPhysicalValue extends CidrValue
{
    public static final Key THROW_ON_TYPE;
    public static final Key THROW_ON_ICON;
    public static final Key THROW_ON_VALUE;
    public static final Key THROW_ON_HAS_CHILDREN;
    public static final Key THROW_ON_CHILDREN;
    @NotNull
    private final LLValue myVar;
    @Nullable
    private volatile LLValue myPresentationVar;
    @NotNull
    private final CachedDebuggerResult<LLValueData> myVarDataResult;
    @NotNull
    private final CachedDebuggerResult<LLValueData> myPresentationVarDataResult;
    @NotNull
    private final CachedDebuggerResult<ValueRenderer> myRendererResult;
    
    public CidrPhysicalValue(@NotNull final LLValue llValue, @NotNull final CidrDebugProcess cidrDebugProcess, @Nullable final XSourcePosition xSourcePosition, @NotNull final CidrStackFrame cidrStackFrame) {
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        if (cidrDebugProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        if (cidrStackFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        this(llValue, llValue.getName(), cidrDebugProcess, xSourcePosition, cidrStackFrame);
    }
    
    public CidrPhysicalValue(@NotNull final LLValue myVar, @NotNull final String s, @NotNull final CidrDebugProcess cidrDebugProcess, @Nullable final XSourcePosition xSourcePosition, @NotNull final CidrStackFrame cidrStackFrame) {
        if (myVar == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        if (cidrDebugProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        if (cidrStackFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "<init>"));
        }
        super(s, cidrDebugProcess, xSourcePosition, cidrStackFrame);
        this.myVarDataResult = new CachedDebuggerResult<LLValueData>();
        this.myPresentationVarDataResult = new CachedDebuggerResult<LLValueData>();
        this.myRendererResult = new CachedDebuggerResult<ValueRenderer>();
        this.myVar = myVar;
    }
    
    @NotNull
    public ValueRenderer getPreparedRenderer() {
        final ValueRenderer valueRenderer = this.myRendererResult.getResultIfAvailable();
        ValueRenderer valueRenderer3 = null;
        Label_0077: {
            ValueRenderer valueRenderer2 = null;
            Label_0042: {
                try {
                    if (valueRenderer != null) {
                        break Label_0077;
                    }
                    final Logger logger = CidrDebuggerLog.LOG;
                    final String s = "getPreparedRenderer() called before getRenderer()";
                    logger.error(s);
                    final CidrPhysicalValue cidrPhysicalValue = this;
                    valueRenderer2 = new ValueRenderer(cidrPhysicalValue);
                    if (valueRenderer2 == null) {
                        break Label_0042;
                    }
                    return valueRenderer2;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final Logger logger = CidrDebuggerLog.LOG;
                    final String s = "getPreparedRenderer() called before getRenderer()";
                    logger.error(s);
                    final CidrPhysicalValue cidrPhysicalValue = this;
                    valueRenderer2 = new ValueRenderer(cidrPhysicalValue);
                    if (valueRenderer2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPreparedRenderer"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return valueRenderer2;
            try {
                valueRenderer3 = valueRenderer;
                if (valueRenderer3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPreparedRenderer"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return valueRenderer3;
    }
    
    @NotNull
    public ValueRenderer getRenderer(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getRenderer"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        ValueRenderer valueRenderer;
        try {
            valueRenderer = this.myRendererResult.getResult(new CachedDebuggerResult.NotNullCalculator<ValueRenderer>() {
                @NotNull
                @Override
                public ValueRenderer calculate() throws ExecutionException, DebuggerCommandException {
                    ValueRenderer doCreateRenderer;
                    try {
                        doCreateRenderer = CidrPhysicalValue.this.doCreateRenderer(evaluationContext);
                        if (doCreateRenderer == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue$1", "calculate"));
                        }
                    }
                    catch (ExecutionException ex) {
                        throw a(ex);
                    }
                    return doCreateRenderer;
                }
                
                private static ExecutionException a(final ExecutionException ex) {
                    return ex;
                }
            });
            if (valueRenderer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getRenderer"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return valueRenderer;
    }
    
    @NotNull
    protected ValueRenderer doCreateRenderer(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doCreateRenderer"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        ValueRenderer renderer;
        try {
            renderer = ValueRendererFactory.createRenderer(evaluationContext, this);
            if (renderer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doCreateRenderer"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return renderer;
    }
    
    @NotNull
    @Override
    public String getEvaluationExpression(final boolean b) {
        String referenceExpression;
        try {
            referenceExpression = this.myVar.getReferenceExpression();
            if (referenceExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return referenceExpression;
    }
    
    @NotNull
    public LLValue getVar() {
        LLValue myVar;
        try {
            myVar = this.myVar;
            if (myVar == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getVar"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return myVar;
    }
    
    @NotNull
    public LLValue getPresentationVar() {
        final LLValue myPresentationVar = this.myPresentationVar;
        LLValue llValue = null;
        Label_0021: {
            try {
                if (myPresentationVar != null) {
                    final LLValue myVar;
                    llValue = (myVar = myPresentationVar);
                    break Label_0021;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            LLValue myVar;
            llValue = (myVar = this.myVar);
            try {
                if (myVar == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPresentationVar"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return llValue;
    }
    
    public void setPresentationVar(@Nullable final LLValue myPresentationVar) {
        this.myPresentationVar = myPresentationVar;
    }
    
    public String getType() {
        return this.myVar.getType();
    }
    
    public boolean isValueDataAvailable() {
        try {
            if (this.myVarDataResult.getResultIfAvailable() != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @NotNull
    public LLValueData getPreparedVarData() {
        final LLValueData llValueData = this.myVarDataResult.getResultIfAvailable();
        LLValueData llValueData3 = null;
        Label_0082: {
            LLValueData llValueData2 = null;
            Label_0047: {
                try {
                    if (llValueData != null) {
                        break Label_0082;
                    }
                    final Logger logger = CidrDebuggerLog.LOG;
                    final String s = "getPreparedVarData() called before getVarData()";
                    logger.error(s);
                    final String s2 = "";
                    final String s3 = null;
                    final boolean b = false;
                    final boolean b2 = false;
                    final boolean b3 = false;
                    llValueData2 = new LLValueData(s2, s3, b, b2, b3);
                    if (llValueData2 == null) {
                        break Label_0047;
                    }
                    return llValueData2;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final Logger logger = CidrDebuggerLog.LOG;
                    final String s = "getPreparedVarData() called before getVarData()";
                    logger.error(s);
                    final String s2 = "";
                    final String s3 = null;
                    final boolean b = false;
                    final boolean b2 = false;
                    final boolean b3 = false;
                    llValueData2 = new LLValueData(s2, s3, b, b2, b3);
                    if (llValueData2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPreparedVarData"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return llValueData2;
            try {
                llValueData3 = llValueData;
                if (llValueData3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPreparedVarData"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return llValueData3;
    }
    
    @NotNull
    public LLValueData getVarData(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getVarData"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        LLValueData llValueData;
        try {
            llValueData = this.myVarDataResult.getResult(new CachedDebuggerResult.NotNullCalculator<LLValueData>() {
                @NotNull
                @Override
                public LLValueData calculate() throws ExecutionException, DebuggerCommandException {
                    LLValueData data;
                    try {
                        data = evaluationContext.getData(CidrPhysicalValue.this.myVar);
                        if (data == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue$2", "calculate"));
                        }
                    }
                    catch (ExecutionException ex) {
                        throw a(ex);
                    }
                    return data;
                }
                
                private static ExecutionException a(final ExecutionException ex) {
                    return ex;
                }
            });
            if (llValueData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getVarData"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return llValueData;
    }
    
    @NotNull
    public LLValueData getPresentationVarData(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPresentationVarData"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final LLValue myPresentationVar = this.myPresentationVar;
        LLValueData llValueData2 = null;
        Label_0104: {
            LLValueData llValueData = null;
            Label_0069: {
                try {
                    if (myPresentationVar != null) {
                        break Label_0104;
                    }
                    final CidrPhysicalValue cidrPhysicalValue = this;
                    final EvaluationContext evaluationContext2 = evaluationContext;
                    llValueData = cidrPhysicalValue.getVarData(evaluationContext2);
                    if (llValueData == null) {
                        break Label_0069;
                    }
                    return llValueData;
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
                try {
                    final CidrPhysicalValue cidrPhysicalValue = this;
                    final EvaluationContext evaluationContext2 = evaluationContext;
                    llValueData = cidrPhysicalValue.getVarData(evaluationContext2);
                    if (llValueData == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPresentationVarData"));
                    }
                }
                catch (ExecutionException ex3) {
                    throw c((Exception)ex3);
                }
            }
            return llValueData;
            try {
                llValueData2 = this.myPresentationVarDataResult.getResult(new CachedDebuggerResult.NotNullCalculator<LLValueData>() {
                    @NotNull
                    @Override
                    public LLValueData calculate() throws ExecutionException, DebuggerCommandException {
                        LLValueData data;
                        try {
                            data = evaluationContext.getData(myPresentationVar);
                            if (data == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue$3", "calculate"));
                            }
                        }
                        catch (ExecutionException ex) {
                            throw a(ex);
                        }
                        return data;
                    }
                    
                    private static ExecutionException a(final ExecutionException ex) {
                        return ex;
                    }
                });
                if (llValueData2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getPresentationVarData"));
                }
            }
            catch (ExecutionException ex4) {
                throw c((Exception)ex4);
            }
        }
        return llValueData2;
    }
    
    @Nullable
    @Override
    protected String doComputeType(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doComputeType"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        ThrowInTest.doThrow((UserDataHolder)this, CidrPhysicalValue.THROW_ON_TYPE);
        String s = this.getRenderer(evaluationContext).getDisplayType();
        if (this.getProcess().getProject().getUserData((Key)CidrPhysicalValue.DO_NOT_SHOW_ADDRESSES) != Boolean.TRUE) {
            final String presentablePointer = this.getVarData(evaluationContext).getPresentablePointer();
            if (presentablePointer != null) {
                s = s + " | " + presentablePointer;
            }
        }
        return s;
    }
    
    @Nullable
    public String getShownAddress() {
        try {
            if (!this.isValueDataAvailable()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this.getPreparedVarData().getPresentablePointer();
    }
    
    @NotNull
    @Override
    protected Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        Pair<String, XFullValueEvaluator> computeValueAndEvaluator;
        try {
            ThrowInTest.doThrow((UserDataHolder)this, CidrPhysicalValue.THROW_ON_VALUE);
            computeValueAndEvaluator = this.getRenderer(evaluationContext).computeValueAndEvaluator(evaluationContext);
            if (computeValueAndEvaluator == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return computeValueAndEvaluator;
    }
    
    @Override
    protected boolean doComputeMayHaveChildren(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doComputeMayHaveChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        ThrowInTest.doThrow((UserDataHolder)this, CidrPhysicalValue.THROW_ON_HAS_CHILDREN);
        return this.getRenderer(evaluationContext).computeMayHaveChildren(evaluationContext);
    }
    
    @Nullable
    @Override
    protected Icon doComputeIcon(@NotNull final EvaluationContext evaluationContext, final boolean b) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "doComputeIcon"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        ThrowInTest.doThrow((UserDataHolder)this, CidrPhysicalValue.THROW_ON_ICON);
        return this.getRenderer(evaluationContext).getIcon(b);
    }
    
    public final void computeValueChildren(@NotNull final XCompositeNode xCompositeNode) {
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "computeValueChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final IllegalArgumentException ex2;
        final EvaluationContext evaluationContext;
        this.getProcess().postCommand(debuggerDriver -> {
            try {
                if (xCompositeNode == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "lambda$computeValueChildren$0"));
                    throw ex2;
                }
            }
            catch (DebuggerCommandException ex3) {
                throw c(ex3);
            }
            try {
                if (xCompositeNode.isObsolete()) {
                    return;
                }
            }
            catch (DebuggerCommandException ex4) {
                throw c(ex4);
            }
            try {
                ThrowInTest.doThrow((UserDataHolder)this, CidrPhysicalValue.THROW_ON_CHILDREN);
                this.createEvaluationContext(debuggerDriver, (Expirable)new XValueNodeExpirable((Obsolescent)xCompositeNode));
                this.getRenderer(evaluationContext).computeChildren(evaluationContext, xCompositeNode);
            }
            catch (DebuggerCommandException ex5) {
                xCompositeNode.addChildren(CidrValue.createErrorChildren(Collections.singletonList(ex5.getMessage())), true);
            }
            catch (ExecutionException ex6) {
                xCompositeNode.addChildren(CidrValue.createErrorChildren(Collections.singletonList(CidrDebuggerUtil.getExceptionMessage((Exception)ex6))), true);
                throw ex6;
            }
        });
    }
    
    @NotNull
    @Override
    public String getConsoleDescription(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getConsoleDescription"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String string;
        try {
            string = this.getRenderer(evaluationContext).getConsoleDescription(evaluationContext).toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getConsoleDescription"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return string;
    }
    
    @NotNull
    public CidrDebuggerTypesHelper getTypesHelper() {
        CidrDebuggerTypesHelper typesHelper;
        try {
            typesHelper = this.getProcess().getTypesHelper();
            if (typesHelper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue", "getTypesHelper"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return typesHelper;
    }
    
    public String toString() {
        return this.getVar().toString();
    }
    
    @Nullable
    public DebuggerDriver.DebuggerLanguage getLanguage() {
        return this.getFrame().getFrame().getLanguage();
    }
    
    public boolean isObjectiveCContext() {
        final DebuggerDriver.DebuggerLanguage language = this.getLanguage();
        Label_0026: {
            try {
                if (language == DebuggerDriver.StandardDebuggerLanguage.OBJC) {
                    break Label_0026;
                }
                final DebuggerDriver.StandardDebuggerLanguage standardDebuggerLanguage = (DebuggerDriver.StandardDebuggerLanguage)language;
                final DebuggerDriver.StandardDebuggerLanguage standardDebuggerLanguage2 = DebuggerDriver.StandardDebuggerLanguage.OBJC_PLUS_PLUS;
                if (standardDebuggerLanguage == standardDebuggerLanguage2) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final DebuggerDriver.StandardDebuggerLanguage standardDebuggerLanguage = (DebuggerDriver.StandardDebuggerLanguage)language;
                final DebuggerDriver.StandardDebuggerLanguage standardDebuggerLanguage2 = DebuggerDriver.StandardDebuggerLanguage.OBJC_PLUS_PLUS;
                if (standardDebuggerLanguage == standardDebuggerLanguage2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    public boolean isSwiftContext() {
        try {
            if (this.getLanguage() == DebuggerDriver.StandardDebuggerLanguage.SWIFT) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    public boolean isSwiftOptional() {
        Label_0024: {
            try {
                if (!this.isSwiftContext()) {
                    return false;
                }
                final CidrPhysicalValue cidrPhysicalValue = this;
                final String s = cidrPhysicalValue.getType();
                final String s2 = CidrDebuggerTypesHelper.unwrapSwiftOptionalType(s);
                if (s2 != null) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final CidrPhysicalValue cidrPhysicalValue = this;
                final String s = cidrPhysicalValue.getType();
                final String s2 = CidrDebuggerTypesHelper.unwrapSwiftOptionalType(s);
                if (s2 != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    public boolean isSwiftVoid() {
        Label_0026: {
            try {
                if (!this.isSwiftContext()) {
                    return false;
                }
                final String s = "()";
                final CidrPhysicalValue cidrPhysicalValue = this;
                final String s2 = cidrPhysicalValue.getType();
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final String s = "()";
                final CidrPhysicalValue cidrPhysicalValue = this;
                final String s2 = cidrPhysicalValue.getType();
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    static {
        THROW_ON_TYPE = Key.create("THROW_ON_TYPE");
        THROW_ON_ICON = Key.create("THROW_ON_ICON");
        THROW_ON_VALUE = Key.create("THROW_ON_VALUE");
        THROW_ON_HAS_CHILDREN = Key.create("THROW_ON_CHILDREN_COUNT");
        THROW_ON_CHILDREN = Key.create("THROW_ON_CHILDREN");
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
