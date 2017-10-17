// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.util.containers.FactoryMap;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Expirable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.openapi.util.UserDataHolder;
import java.util.Map;
import com.intellij.openapi.util.Key;

public abstract class EvaluationContext
{
    private static final Key<Map<String, UserDataHolder>> TYPES_CACHE_KEY;
    @NotNull
    private final CidrStackFrame myFrame;
    protected final long myThreadId;
    protected final int myFrameIndex;
    @NotNull
    private final DebuggerDriver myDriver;
    @Nullable
    private final Expirable myExpirable;
    
    public EvaluationContext(@NotNull final DebuggerDriver myDriver, @Nullable final Expirable myExpirable, @NotNull final CidrStackFrame myFrame) {
        if (myDriver == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "<init>"));
        }
        if (myFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "<init>"));
        }
        this.myThreadId = myFrame.getThreadId();
        this.myFrameIndex = myFrame.getFrameIndex();
        this.myFrame = myFrame;
        this.myDriver = myDriver;
        this.myExpirable = myExpirable;
    }
    
    public void checkExpiration() {
        Label_0026: {
            try {
                if (this.myExpirable == null) {
                    return;
                }
                final EvaluationContext evaluationContext = this;
                final Expirable expirable = evaluationContext.myExpirable;
                final boolean b = expirable.isExpired();
                if (b) {
                    break Label_0026;
                }
                return;
            }
            catch (ExpiredException ex) {
                throw b(ex);
            }
            try {
                final EvaluationContext evaluationContext = this;
                final Expirable expirable = evaluationContext.myExpirable;
                final boolean b = expirable.isExpired();
                if (b) {
                    throw new ExpiredException(this, this.myExpirable);
                }
            }
            catch (ExpiredException ex2) {
                throw b(ex2);
            }
        }
    }
    
    @NotNull
    public LLValue evaluate(@NotNull final String s) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluate"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        LLValue evaluate;
        try {
            evaluate = this.evaluate(s, null);
            if (evaluate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluate"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return evaluate;
    }
    
    @NotNull
    public LLValueData evaluateData(@NotNull final String s) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluateData"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        LLValueData data;
        try {
            data = this.getData(this.evaluate(s, null));
            if (data == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluateData"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return data;
    }
    
    @NotNull
    public LLValue evaluate(@NotNull final String s, @Nullable final DebuggerDriver.DebuggerLanguage debuggerLanguage) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluate"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        LLValue evaluate;
        try {
            this.checkExpiration();
            evaluate = this.myDriver.evaluate(this.myThreadId, this.myFrameIndex, s, debuggerLanguage);
            if (evaluate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluate"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return evaluate;
    }
    
    @NotNull
    public LLValueData evaluateData(@NotNull final String s, @Nullable final DebuggerDriver.DebuggerLanguage debuggerLanguage) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluateData"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        LLValueData data;
        try {
            data = this.getData(this.evaluate(s, debuggerLanguage));
            if (data == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "evaluateData"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return data;
    }
    
    @NotNull
    public String castIDToNumber(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "castIDToNumber"));
            }
        }
        catch (ExpiredException ex) {
            throw b(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "castIDToNumber"));
            }
        }
        catch (ExpiredException ex2) {
            throw b(ex2);
        }
        String cast;
        try {
            cast = cast(s, s2);
            if (cast == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "castIDToNumber"));
            }
        }
        catch (ExpiredException ex3) {
            throw b(ex3);
        }
        return cast;
    }
    
    @NotNull
    public abstract String convertToRValue(@NotNull final LLValueData p0, @NotNull final Pair<LLValue, String> p1) throws DebuggerCommandException, ExecutionException;
    
    public static String cast(final String s, final String s2) {
        return "((" + s2 + ")(" + s + "))";
    }
    
    @NotNull
    public LLValue messageSend(final LLValueData llValueData, final String s, final String s2) throws ExecutionException, DebuggerCommandException {
        LLValue evaluate;
        try {
            evaluate = this.evaluate(messageSendExpr(llValueData.getPointer(), s, s2), DebuggerDriver.StandardDebuggerLanguage.OBJC_PLUS_PLUS);
            if (evaluate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "messageSend"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return evaluate;
    }
    
    @NotNull
    public LLValue messageSend(final LLValue llValue, final String s, final String s2) throws ExecutionException, DebuggerCommandException {
        LLValue messageSend;
        try {
            messageSend = this.messageSend(this.getData(llValue), s, s2);
            if (messageSend == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "messageSend"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return messageSend;
    }
    
    protected static String messageSendExpr(final String s, final String s2, final String s3) {
        return cast("[" + cast(s, "id") + " " + s2 + "]", s3);
    }
    
    @NotNull
    public LLValue messageSend(final LLValue llValue, final String s) throws ExecutionException, DebuggerCommandException {
        LLValue messageSend;
        try {
            messageSend = this.messageSend(llValue, s, "id");
            if (messageSend == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "messageSend"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return messageSend;
    }
    
    @NotNull
    public LLValueData messageSendData(final LLValue llValue, final String s) throws ExecutionException, DebuggerCommandException {
        LLValueData data;
        try {
            data = this.getData(this.messageSend(llValue, s));
            if (data == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "messageSendData"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return data;
    }
    
    public boolean isKindOfClass(final String s, final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        return this.evaluateData("(unsigned char)((Class)objc_getClass(\"" + s + "\")?" + cast("[" + cast(this.getData(llValue).getPointer(), "id") + " isKindOfClass:(Class)objc_lookUpClass(\"" + s + "\")]", "unsigned char") + ":0)", DebuggerDriver.StandardDebuggerLanguage.OBJC_PLUS_PLUS).isTrue();
    }
    
    @NotNull
    public String stringFromNSString(final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        String stringFromNSString;
        try {
            stringFromNSString = this.stringFromNSString(this.getData(llValue).getPointer());
            if (stringFromNSString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "stringFromNSString"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return stringFromNSString;
    }
    
    @NotNull
    public String stringFromNSString(final String s) throws ExecutionException, DebuggerCommandException {
        String unquoteString;
        try {
            unquoteString = StringUtil.unquoteString(this.evaluateData(a(s), DebuggerDriver.StandardDebuggerLanguage.OBJC_PLUS_PLUS).getPresentableValue());
            if (unquoteString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "stringFromNSString"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return unquoteString;
    }
    
    private static String a(final String s) {
        final String messageSendExpr = messageSendExpr(s, "length", "int");
        return messageSendExpr(messageSendExpr(s, "substringToIndex:" + String.format("(((%s) > %d) ? %d : %s)", messageSendExpr, 1000, 1000, messageSendExpr), "id"), "UTF8String", "char *");
    }
    
    @NotNull
    public LLValueData getData(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getData"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        LLValueData data;
        try {
            this.checkExpiration();
            data = this.myDriver.getData(llValue);
            if (data == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getData"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return data;
    }
    
    @Nullable
    public Integer getChildrenCount(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getChildrenCount"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        this.checkExpiration();
        return this.myDriver.getChildrenCount(llValue);
    }
    
    @NotNull
    public DebuggerDriver.ResultList<LLValue> getVariableChildren(final LLValue llValue, final int n, final int n2) throws ExecutionException, DebuggerCommandException {
        DebuggerDriver.ResultList<LLValue> variableChildren;
        try {
            this.checkExpiration();
            variableChildren = this.myDriver.getVariableChildren(llValue, n, n2);
            if (variableChildren == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getVariableChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return variableChildren;
    }
    
    @Nullable
    public <T> T getCachedTypeInfo(@NotNull final String s, @NotNull final Key<T> key) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getCachedTypeInfo"));
            }
        }
        catch (ExpiredException ex) {
            throw b(ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getCachedTypeInfo"));
            }
        }
        catch (ExpiredException ex2) {
            throw b(ex2);
        }
        final UserDataHolder b = this.b(s);
        try {
            if (b == null) {
                final Object userData = null;
                return (T)userData;
            }
        }
        catch (ExpiredException ex3) {
            throw b(ex3);
        }
        final Object userData = b.getUserData((Key)key);
        return (T)userData;
    }
    
    public <T> void putCachedTypeInfo(@NotNull final String s, @NotNull final Key<T> key, @Nullable final T t) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "putCachedTypeInfo"));
            }
        }
        catch (ExpiredException ex) {
            throw b(ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "putCachedTypeInfo"));
            }
        }
        catch (ExpiredException ex2) {
            throw b(ex2);
        }
        final UserDataHolder b = this.b(s);
        try {
            if (b != null) {
                b.putUserData((Key)key, (Object)t);
            }
        }
        catch (ExpiredException ex3) {
            throw b(ex3);
        }
    }
    
    @Nullable
    private UserDataHolder b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext", "getTypeInfoHolder"));
            }
        }
        catch (ExpiredException ex) {
            throw b(ex);
        }
        try {
            if (s.equals("id")) {
                return null;
            }
        }
        catch (ExpiredException ex2) {
            throw b(ex2);
        }
        Map<String, UserDataHolder> map = this.myFrame.getUserData(EvaluationContext.TYPES_CACHE_KEY);
        if (map == null) {
            map = (Map<String, UserDataHolder>)this.myFrame.putUserDataIfAbsent((com.intellij.openapi.util.Key<Map<K, UserDataHolder>>)EvaluationContext.TYPES_CACHE_KEY, (Map<K, UserDataHolder>)new FactoryMap<String, UserDataHolder>() {
                @Nullable
                protected UserDataHolder create(final String s) {
                    return (UserDataHolder)new UserDataHolderBase();
                }
            });
        }
        return map.get(s);
    }
    
    static {
        TYPES_CACHE_KEY = Key.create("TYPES_CACHE_KEY");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
