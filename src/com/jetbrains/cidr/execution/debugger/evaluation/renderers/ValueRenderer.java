// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.intellij.ui.SimpleTextAttributes;
import com.intellij.xdebugger.frame.XDebuggerTreeNodeHyperlink;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrMemberValue;
import com.intellij.util.SmartList;
import java.util.List;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerUtil;
import com.intellij.xdebugger.frame.ImmediateFullValueEvaluator;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.xdebugger.frame.XCompositeNode;
import org.jetbrains.annotations.Nullable;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import java.util.regex.Pattern;

public class ValueRenderer
{
    private static final Pattern SWIFT_DESCRIPTION_PATTERN;
    @NotNull
    protected final CidrPhysicalValue myValue;
    @NotNull
    private final CachedDebuggerResult<Pair<String, XFullValueEvaluator>> myValueCache;
    @NotNull
    private final CachedDebuggerResult<Boolean> myMayHaveChildrenCache;
    @NotNull
    private final CachedDebuggerResult<Integer> myChildrenCountCache;
    protected int myCurrentIndex;
    
    public ValueRenderer(@NotNull final CidrPhysicalValue myValue) {
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "<init>"));
        }
        this.myValueCache = new CachedDebuggerResult<Pair<String, XFullValueEvaluator>>();
        this.myMayHaveChildrenCache = new CachedDebuggerResult<Boolean>();
        this.myChildrenCountCache = new CachedDebuggerResult<Integer>();
        this.myCurrentIndex = 0;
        this.myValue = myValue;
    }
    
    @NotNull
    public CidrPhysicalValue getValue() {
        CidrPhysicalValue myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "getValue"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        return myValue;
    }
    
    @NotNull
    public String getChildEvaluationExpression(@NotNull final CidrPhysicalValue p0, final boolean p1) {
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
        //    18: ldc             "child"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getChildEvaluationExpression"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    48: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getPreparedVarData:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
        //    51: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.isPointer:()Z
        //    54: istore_3       
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    59: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.isSwiftContext:()Z
        //    62: istore          4
        //    64: aload_1        
        //    65: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getName:()Ljava/lang/String;
        //    68: ldc             "\\[\\w+\\]"
        //    70: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    73: ifeq            133
        //    76: aload_0        
        //    77: aload_1        
        //    78: ldc             ""
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    84: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getChildEvaluationExpression:(Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;)Ljava/lang/String;
        //    87: dup            
        //    88: ifnonnull       132
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    97: athrow         
        //    98: new             Ljava/lang/IllegalStateException;
        //   101: dup            
        //   102: ldc             "@NotNull method %s.%s must not return null"
        //   104: ldc             2
        //   106: anewarray       Ljava/lang/Object;
        //   109: dup            
        //   110: ldc             0
        //   112: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             1
        //   118: ldc             "getChildEvaluationExpression"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: areturn        
        //   133: iload           4
        //   135: ifne            206
        //   138: iload_3        
        //   139: ifeq            206
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   148: athrow         
        //   149: aload_0        
        //   150: aload_1        
        //   151: ldc             "->"
        //   153: aload_0        
        //   154: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   157: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getChildEvaluationExpression:(Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;)Ljava/lang/String;
        //   160: dup            
        //   161: ifnonnull       205
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   170: athrow         
        //   171: new             Ljava/lang/IllegalStateException;
        //   174: dup            
        //   175: ldc             "@NotNull method %s.%s must not return null"
        //   177: ldc             2
        //   179: anewarray       Ljava/lang/Object;
        //   182: dup            
        //   183: ldc             0
        //   185: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //   187: aastore        
        //   188: dup            
        //   189: ldc             1
        //   191: ldc             "getChildEvaluationExpression"
        //   193: aastore        
        //   194: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   197: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   200: athrow         
        //   201: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   204: athrow         
        //   205: areturn        
        //   206: iload           4
        //   208: ifne            306
        //   211: aload_0        
        //   212: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   215: instanceof      Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue;
        //   218: ifeq            306
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   227: athrow         
        //   228: aload_0        
        //   229: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   232: checkcast       Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue;
        //   235: astore          5
        //   237: aload           5
        //   239: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue.getIndex:()I
        //   242: ifne            303
        //   245: aload_0        
        //   246: aload_1        
        //   247: ldc             "->"
        //   249: aload           5
        //   251: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue.getContainer:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   254: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getChildEvaluationExpression:(Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;)Ljava/lang/String;
        //   257: dup            
        //   258: ifnonnull       302
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   267: athrow         
        //   268: new             Ljava/lang/IllegalStateException;
        //   271: dup            
        //   272: ldc             "@NotNull method %s.%s must not return null"
        //   274: ldc             2
        //   276: anewarray       Ljava/lang/Object;
        //   279: dup            
        //   280: ldc             0
        //   282: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //   284: aastore        
        //   285: dup            
        //   286: ldc             1
        //   288: ldc             "getChildEvaluationExpression"
        //   290: aastore        
        //   291: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   294: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   297: athrow         
        //   298: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   301: athrow         
        //   302: areturn        
        //   303: goto            391
        //   306: aload_0        
        //   307: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   310: instanceof      Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue;
        //   313: ifeq            391
        //   316: aload_0        
        //   317: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   320: checkcast       Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue;
        //   323: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue.getParent:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   326: astore          5
        //   328: aload_0        
        //   329: invokespecial   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.a:()Z
        //   332: ifeq            391
        //   335: aload           5
        //   337: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getPreparedRenderer:()Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
        //   340: aload_1        
        //   341: iload_2        
        //   342: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getChildEvaluationExpression:(Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;Z)Ljava/lang/String;
        //   345: dup            
        //   346: ifnonnull       390
        //   349: goto            356
        //   352: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   355: athrow         
        //   356: new             Ljava/lang/IllegalStateException;
        //   359: dup            
        //   360: ldc             "@NotNull method %s.%s must not return null"
        //   362: ldc             2
        //   364: anewarray       Ljava/lang/Object;
        //   367: dup            
        //   368: ldc             0
        //   370: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //   372: aastore        
        //   373: dup            
        //   374: ldc             1
        //   376: ldc             "getChildEvaluationExpression"
        //   378: aastore        
        //   379: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   382: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   385: athrow         
        //   386: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   389: athrow         
        //   390: areturn        
        //   391: aload_0        
        //   392: aload_1        
        //   393: ldc             "."
        //   395: aload_0        
        //   396: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   399: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getChildEvaluationExpression:(Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;)Ljava/lang/String;
        //   402: dup            
        //   403: ifnonnull       440
        //   406: new             Ljava/lang/IllegalStateException;
        //   409: dup            
        //   410: ldc             "@NotNull method %s.%s must not return null"
        //   412: ldc             2
        //   414: anewarray       Ljava/lang/Object;
        //   417: dup            
        //   418: ldc             0
        //   420: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //   422: aastore        
        //   423: dup            
        //   424: ldc             1
        //   426: ldc             "getChildEvaluationExpression"
        //   428: aastore        
        //   429: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   432: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   435: athrow         
        //   436: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   439: athrow         
        //   440: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                                                
        //  -----  -----  -----  -----  ----------------------------------------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  64     91     94     98     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  76     128    128    132    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  133    142    145    149    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  138    164    167    171    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  149    201    201    205    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  206    221    224    228    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  237    261    264    268    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  245    298    298    302    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  328    349    352    356    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  335    386    386    390    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  391    436    436    440    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0149:
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
        final LLValue var = this.myValue.getVar();
        return var.getType().equals(var.getName());
    }
    
    protected LLValueData messageSendToSelfData(final String s, final String s2, final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        return evaluationContext.getData(this.messageSendToSelf(s, s2, evaluationContext));
    }
    
    protected LLValue messageSendToSelf(final String s, final String s2, final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        return evaluationContext.messageSend(this.myValue.getVar(), s, s2);
    }
    
    @Nullable
    public Icon getIcon(final boolean b) {
        try {
            if (b) {
                return AllIcons.Debugger.Value;
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        return AllIcons.Debugger.Db_primitive;
    }
    
    @NotNull
    public StringBuilder getConsoleDescription(@NotNull final EvaluationContext evaluationContext) throws ExecutionException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "getConsoleDescription"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("(").append(this.myValue.getType()).append((") " + (String)this.computeValueAndEvaluator(evaluationContext).first).trim());
            if (this.shouldPrintChildrenConsoleDescription()) {
                final boolean computeMayHaveChildren = this.computeMayHaveChildren(evaluationContext);
                try {
                    if (computeMayHaveChildren) {
                        sb.append("\n");
                        this.a(evaluationContext, sb);
                    }
                }
                catch (DebuggerCommandException ex2) {
                    throw b(ex2);
                }
            }
        }
        catch (DebuggerCommandException ex4) {
            a(sb, this.toString());
        }
        StringBuilder sb2;
        try {
            sb2 = sb;
            if (sb2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "getConsoleDescription"));
            }
        }
        catch (DebuggerCommandException ex3) {
            throw b(ex3);
        }
        return sb2;
    }
    
    protected boolean shouldPrintChildrenConsoleDescription() {
        return false;
    }
    
    private void a(final EvaluationContext evaluationContext, final StringBuilder sb) throws ExecutionException, DebuggerCommandException {
        sb.append("{\n");
        final ConsoleDescriptionNode consoleDescriptionNode = new ConsoleDescriptionNode(sb, evaluationContext);
        try {
            this.computeChildren(evaluationContext, (XCompositeNode)consoleDescriptionNode);
        }
        catch (ExecutionRuntimeException ex) {
            throw ex.cause;
        }
        finally {
            sb.append("}");
        }
    }
    
    private static void a(final StringBuilder sb, @NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "appendConsoleError"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        sb.append("error: ").append(s);
    }
    
    @NotNull
    public String getDisplayType() {
        String shortenedType;
        try {
            shortenedType = getShortenedType(this.myValue.getVar().getType());
            if (shortenedType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "getDisplayType"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        return shortenedType;
    }
    
    @NotNull
    public static String getShortenedType(@NotNull final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getShortenedType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: iconst_1       
        //    45: istore_1       
        //    46: new             Ljava/lang/StringBuilder;
        //    49: dup            
        //    50: invokespecial   java/lang/StringBuilder.<init>:()V
        //    53: astore_2       
        //    54: iconst_0       
        //    55: istore_3       
        //    56: iconst_0       
        //    57: istore          4
        //    59: iload           4
        //    61: aload_0        
        //    62: invokevirtual   java/lang/String.length:()I
        //    65: if_icmpge       209
        //    68: aload_0        
        //    69: iload           4
        //    71: invokevirtual   java/lang/String.charAt:(I)C
        //    74: istore          5
        //    76: iload           5
        //    78: bipush          60
        //    80: if_icmpne       93
        //    83: iinc            3, 1
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    92: athrow         
        //    93: iload_3        
        //    94: iload_1        
        //    95: if_icmpgt       186
        //    98: aload_2        
        //    99: invokevirtual   java/lang/StringBuilder.length:()I
        //   102: istore          6
        //   104: iload           5
        //   106: bipush          62
        //   108: if_icmpne       179
        //   111: iload           6
        //   113: iconst_2       
        //   114: if_icmple       179
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   123: athrow         
        //   124: aload_2        
        //   125: iload           6
        //   127: iconst_1       
        //   128: isub           
        //   129: invokevirtual   java/lang/StringBuilder.charAt:(I)C
        //   132: bipush          32
        //   134: if_icmpne       179
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   143: athrow         
        //   144: aload_2        
        //   145: iload           6
        //   147: iconst_2       
        //   148: isub           
        //   149: invokevirtual   java/lang/StringBuilder.charAt:(I)C
        //   152: bipush          62
        //   154: if_icmpeq       179
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   163: athrow         
        //   164: aload_2        
        //   165: iload           6
        //   167: iconst_1       
        //   168: isub           
        //   169: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   178: athrow         
        //   179: aload_2        
        //   180: iload           5
        //   182: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   185: pop            
        //   186: iload           5
        //   188: bipush          62
        //   190: if_icmpne       203
        //   193: iinc            3, -1
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   202: athrow         
        //   203: iinc            4, 1
        //   206: goto            59
        //   209: iload_3        
        //   210: ifne            224
        //   213: aload_2        
        //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   217: goto            225
        //   220: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   223: athrow         
        //   224: aload_0        
        //   225: dup            
        //   226: ifnonnull       263
        //   229: new             Ljava/lang/IllegalStateException;
        //   232: dup            
        //   233: ldc             "@NotNull method %s.%s must not return null"
        //   235: ldc             2
        //   237: anewarray       Ljava/lang/Object;
        //   240: dup            
        //   241: ldc             0
        //   243: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //   245: aastore        
        //   246: dup            
        //   247: ldc             1
        //   249: ldc             "getShortenedType"
        //   251: aastore        
        //   252: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   255: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   258: athrow         
        //   259: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   262: athrow         
        //   263: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                                                
        //  -----  -----  -----  -----  ----------------------------------------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  76     86     89     93     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  104    117    120    124    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  111    137    140    144    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  124    157    160    164    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  144    172    175    179    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  186    196    199    203    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  209    220    220    224    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  225    259    259    263    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0124:
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
    
    protected String getChildEvaluationExpression(final CidrPhysicalValue cidrPhysicalValue, final String s, final CidrPhysicalValue cidrPhysicalValue2) {
        final ValueRendererExtension extension = ValueRendererExtension.getExtension(cidrPhysicalValue2.getFrame().getFrame().getLanguage());
        try {
            if (extension != null) {
                return extension.getChildEvaluationExpression(cidrPhysicalValue, s, cidrPhysicalValue2);
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        String s2 = cidrPhysicalValue2.getEvaluationExpression();
        boolean b = false;
        Label_0061: {
            try {
                if (cidrPhysicalValue2.getVar().getTypeClass() == LLValue.TypeClass.OBJC_POINTER) {
                    b = true;
                    break Label_0061;
                }
            }
            catch (ExecutionRuntimeException ex2) {
                throw b(ex2);
            }
            b = false;
        }
        if (b) {
            s2 = "((" + cidrPhysicalValue2.getType() + ")(" + s2 + "))";
        }
        return s2 + s + cidrPhysicalValue.getVar().getName();
    }
    
    @NotNull
    public Pair<String, XFullValueEvaluator> computeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "computeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        Pair<String, XFullValueEvaluator> pair;
        try {
            pair = this.myValueCache.getResult(new CachedDebuggerResult.NotNullCalculator<Pair<String, XFullValueEvaluator>>() {
                @NotNull
                @Override
                public Pair<String, XFullValueEvaluator> calculate() throws ExecutionException, DebuggerCommandException {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_0        
                    //     1: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
                    //     4: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
                    //     7: aload_0        
                    //     8: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.val$context:Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;
                    //    11: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVarData:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
                    //    14: astore_1       
                    //    15: aload_1        
                    //    16: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.isNullPointer:()Z
                    //    19: ifeq            150
                    //    22: aload_0        
                    //    23: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
                    //    26: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getValue:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
                    //    29: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVar:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
                    //    32: astore_2       
                    //    33: aload_2        
                    //    34: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.getTypeClass:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue$TypeClass;
                    //    37: getstatic       com/jetbrains/cidr/execution/debugger/backend/LLValue$TypeClass.OBJC_POINTER:Lcom/jetbrains/cidr/execution/debugger/backend/LLValue$TypeClass;
                    //    40: if_acmpeq       82
                    //    43: ldc             "id"
                    //    45: aload_2        
                    //    46: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.getType:()Ljava/lang/String;
                    //    49: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //    52: ifeq            90
                    //    55: goto            62
                    //    58: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //    61: athrow         
                    //    62: aload_0        
                    //    63: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
                    //    66: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getValue:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
                    //    69: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.isObjectiveCContext:()Z
                    //    72: ifeq            90
                    //    75: goto            82
                    //    78: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //    81: athrow         
                    //    82: iconst_1       
                    //    83: goto            91
                    //    86: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //    89: athrow         
                    //    90: iconst_0       
                    //    91: istore_3       
                    //    92: iload_3        
                    //    93: ifeq            105
                    //    96: ldc             "nil"
                    //    98: goto            107
                    //   101: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //   104: athrow         
                    //   105: ldc             "NULL"
                    //   107: aconst_null    
                    //   108: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
                    //   111: dup            
                    //   112: ifnonnull       149
                    //   115: new             Ljava/lang/IllegalStateException;
                    //   118: dup            
                    //   119: ldc             "@NotNull method %s.%s must not return null"
                    //   121: ldc             2
                    //   123: anewarray       Ljava/lang/Object;
                    //   126: dup            
                    //   127: ldc             0
                    //   129: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1"
                    //   131: aastore        
                    //   132: dup            
                    //   133: ldc             1
                    //   135: ldc             "calculate"
                    //   137: aastore        
                    //   138: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   141: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   144: athrow         
                    //   145: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //   148: athrow         
                    //   149: areturn        
                    //   150: aload_0        
                    //   151: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
                    //   154: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
                    //   157: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.isSwiftVoid:()Z
                    //   160: ifeq            215
                    //   163: ldc             ""
                    //   165: aconst_null    
                    //   166: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
                    //   169: dup            
                    //   170: ifnonnull       214
                    //   173: goto            180
                    //   176: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //   179: athrow         
                    //   180: new             Ljava/lang/IllegalStateException;
                    //   183: dup            
                    //   184: ldc             "@NotNull method %s.%s must not return null"
                    //   186: ldc             2
                    //   188: anewarray       Ljava/lang/Object;
                    //   191: dup            
                    //   192: ldc             0
                    //   194: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1"
                    //   196: aastore        
                    //   197: dup            
                    //   198: ldc             1
                    //   200: ldc             "calculate"
                    //   202: aastore        
                    //   203: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   206: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   209: athrow         
                    //   210: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //   213: athrow         
                    //   214: areturn        
                    //   215: aload_0        
                    //   216: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
                    //   219: aload_0        
                    //   220: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.val$context:Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;
                    //   223: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.doComputeValueAndEvaluator:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Lcom/intellij/openapi/util/Pair;
                    //   226: astore_2       
                    //   227: aload_2        
                    //   228: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
                    //   231: checkcast       Ljava/lang/String;
                    //   234: ldc             "{...}"
                    //   236: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   239: ifeq            255
                    //   242: ldc             ""
                    //   244: aconst_null    
                    //   245: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
                    //   248: goto            256
                    //   251: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //   254: athrow         
                    //   255: aload_2        
                    //   256: dup            
                    //   257: ifnonnull       294
                    //   260: new             Ljava/lang/IllegalStateException;
                    //   263: dup            
                    //   264: ldc             "@NotNull method %s.%s must not return null"
                    //   266: ldc             2
                    //   268: anewarray       Ljava/lang/Object;
                    //   271: dup            
                    //   272: ldc             0
                    //   274: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1"
                    //   276: aastore        
                    //   277: dup            
                    //   278: ldc             1
                    //   280: ldc             "calculate"
                    //   282: aastore        
                    //   283: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   286: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   289: athrow         
                    //   290: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
                    //   293: athrow         
                    //   294: areturn        
                    //    Exceptions:
                    //  throws com.intellij.execution.ExecutionException
                    //  throws com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException
                    //    Signature:
                    //  ()Lcom/intellij/openapi/util/Pair<Ljava/lang/String;Lcom/intellij/xdebugger/frame/XFullValueEvaluator;>;
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                       
                    //  -----  -----  -----  -----  -------------------------------------------
                    //  33     55     58     62     Lcom/intellij/execution/ExecutionException;
                    //  43     75     78     82     Lcom/intellij/execution/ExecutionException;
                    //  62     86     86     90     Lcom/intellij/execution/ExecutionException;
                    //  92     101    101    105    Lcom/intellij/execution/ExecutionException;
                    //  107    145    145    149    Lcom/intellij/execution/ExecutionException;
                    //  150    173    176    180    Lcom/intellij/execution/ExecutionException;
                    //  163    210    210    214    Lcom/intellij/execution/ExecutionException;
                    //  227    251    251    255    Lcom/intellij/execution/ExecutionException;
                    //  256    290    290    294    Lcom/intellij/execution/ExecutionException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                
                private static ExecutionException a(final ExecutionException ex) {
                    return ex;
                }
            });
            if (pair == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "computeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return pair;
    }
    
    @NotNull
    protected Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator;
        try {
            doComputeValueAndEvaluator = this.doComputeValueAndEvaluator(evaluationContext, this.myValue.getPresentationVar(), this.myValue.getPresentationVarData(evaluationContext));
            if (doComputeValueAndEvaluator == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return doComputeValueAndEvaluator;
    }
    
    @NotNull
    protected Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext, final LLValue llValue, final LLValueData llValueData) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        String s = llValueData.getDescription();
        Pair pair = null;
        Label_0142: {
            Label_0085: {
                try {
                    if (s == null) {
                        break Label_0142;
                    }
                    if (this.myValue.getLanguage() != DebuggerDriver.StandardDebuggerLanguage.SWIFT) {
                        break Label_0085;
                    }
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
                s = StringUtil.unescapeStringCharacters(s);
            }
            final String trim = s.trim();
            String s3 = null;
            XFullValueEvaluator a = null;
            Label_0137: {
                Label_0124: {
                    try {
                        if (this.ignoreDescription(trim)) {
                            break Label_0142;
                        }
                        final String s2 = trim;
                        final int n = 1000;
                        final boolean b = false;
                        s3 = StringUtil.first(s2, n, b);
                        final LLValueData llValueData2 = llValueData;
                        final boolean b2 = llValueData2.hasLongerDescription();
                        if (b2) {
                            break Label_0124;
                        }
                        break Label_0124;
                    }
                    catch (ExecutionException ex3) {
                        throw b((Exception)ex3);
                    }
                    try {
                        final String s2 = trim;
                        final int n = 1000;
                        final boolean b = false;
                        s3 = StringUtil.first(s2, n, b);
                        final LLValueData llValueData2 = llValueData;
                        final boolean b2 = llValueData2.hasLongerDescription();
                        if (b2) {
                            a = this.a(llValue);
                            break Label_0137;
                        }
                    }
                    catch (ExecutionException ex4) {
                        throw b((Exception)ex4);
                    }
                }
                a = null;
            }
            pair = Pair.create((Object)s3, (Object)a);
        }
        if (pair == null) {
            final String doComputeFallbackValue = this.doComputeFallbackValue(evaluationContext, llValue, llValueData);
            Object o = null;
            if (doComputeFallbackValue.length() > 1000) {
                o = new ImmediateFullValueEvaluator(doComputeFallbackValue);
            }
            pair = Pair.create((Object)StringUtil.first(doComputeFallbackValue, 1000, false), o);
        }
        Pair pair2;
        try {
            pair2 = pair;
            if (pair2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex5) {
            throw b((Exception)ex5);
        }
        return (Pair<String, XFullValueEvaluator>)pair2;
    }
    
    protected boolean ignoreDescription(@NotNull final String p0) {
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
        //    18: ldc             "description"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "ignoreDescription"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    48: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getLanguage:()Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$DebuggerLanguage;
        //    51: astore_2       
        //    52: aload_2        
        //    53: getstatic       com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage.OBJC:Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage;
        //    56: if_acmpne       73
        //    59: aload_1        
        //    60: invokestatic    com/intellij/openapi/util/text/StringUtil.containsLineBreak:(Ljava/lang/CharSequence;)Z
        //    63: ifne            107
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    72: athrow         
        //    73: aload_2        
        //    74: getstatic       com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage.SWIFT:Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage;
        //    77: if_acmpne       113
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    86: athrow         
        //    87: getstatic       com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.SWIFT_DESCRIPTION_PATTERN:Ljava/util/regex/Pattern;
        //    90: aload_1        
        //    91: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    94: invokevirtual   java/util/regex/Matcher.matches:()Z
        //    97: ifeq            113
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   106: athrow         
        //   107: iconst_1       
        //   108: ireturn        
        //   109: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                                                
        //  -----  -----  -----  -----  ----------------------------------------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  52     66     69     73     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  59     80     83     87     Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  73     100    103    107    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        //  87     109    109    113    Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ExecutionRuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    @NotNull
    protected String doComputeFallbackValue(@NotNull final EvaluationContext evaluationContext, @NotNull final LLValue llValue, @NotNull final LLValueData llValueData) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (llValueData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        String value;
        try {
            value = llValueData.getValue();
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        return value;
    }
    
    @Nullable
    private XFullValueEvaluator a(@NotNull final LLValue llValue) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "createFullDescriptionEvaluator"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        return new XFullValueEvaluator() {
            public void startEvaluation(@NotNull final XFullValueEvaluator.XFullValueEvaluationCallback xFullValueEvaluationCallback) {
                try {
                    if (xFullValueEvaluationCallback == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$2", "startEvaluation"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                final LLValue val$value;
                final IllegalArgumentException ex2;
                final IllegalArgumentException ex4;
                ValueRenderer.this.getValue().getProcess().postCommand(debuggerDriver -> {
                    val$value = llValue;
                    try {
                        if (xFullValueEvaluationCallback == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$2", "lambda$startEvaluation$0"));
                            throw ex2;
                        }
                    }
                    catch (DebuggerCommandException ex3) {
                        throw b(ex3);
                    }
                    try {
                        if (val$value == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$2", "lambda$startEvaluation$0"));
                            throw ex4;
                        }
                    }
                    catch (DebuggerCommandException ex5) {
                        throw b(ex5);
                    }
                    try {
                        if (xFullValueEvaluationCallback.isObsolete()) {
                            return;
                        }
                    }
                    catch (DebuggerCommandException ex6) {
                        throw b(ex6);
                    }
                    try {
                        xFullValueEvaluationCallback.evaluated(StringUtil.notNullize(debuggerDriver.getDescription(val$value, 10485760)));
                    }
                    catch (DebuggerCommandException ex7) {
                        xFullValueEvaluationCallback.errorOccurred(ex7.getMessage());
                    }
                    catch (ExecutionException ex8) {
                        xFullValueEvaluationCallback.errorOccurred(CidrDebuggerUtil.getExceptionMessage((Exception)ex8));
                        throw ex8;
                    }
                });
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        };
    }
    
    public boolean computeMayHaveChildren(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "computeMayHaveChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        if (this.mayHaveChildrenViaChildrenCount()) {
            final Integer computeChildrenCount = this.computeChildrenCount(evaluationContext);
            Label_0075: {
                try {
                    if (computeChildrenCount == null) {
                        return this.myMayHaveChildrenCache.getResult(new CachedDebuggerResult.NotNullCalculator<Boolean>() {
                            final /* synthetic */ EvaluationContext val$context;
                            final /* synthetic */ ValueRenderer this$0;
                            
                            @NotNull
                            @Override
                            public Boolean calculate() throws ExecutionException, DebuggerCommandException {
                                final LLValueData varData = ValueRenderer.this.myValue.getVarData(this.val$context);
                                Boolean value = null;
                                Label_0054: {
                                    Label_0042: {
                                        try {
                                            if (varData.isNullPointer()) {
                                                break Label_0042;
                                            }
                                            final CachedDebuggerResult.NotNullCalculator<Boolean> notNullCalculator = this;
                                            final ValueRenderer valueRenderer = notNullCalculator.this$0;
                                            final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                                            final boolean b = cidrPhysicalValue.isSwiftVoid();
                                            if (b) {
                                                break Label_0042;
                                            }
                                            break Label_0042;
                                        }
                                        catch (ExecutionException ex) {
                                            throw a(ex);
                                        }
                                        try {
                                            final CachedDebuggerResult.NotNullCalculator<Boolean> notNullCalculator = this;
                                            final ValueRenderer valueRenderer = notNullCalculator.this$0;
                                            final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                                            final boolean b = cidrPhysicalValue.isSwiftVoid();
                                            if (b) {
                                                final boolean mayHaveChildren = false;
                                                break Label_0054;
                                            }
                                        }
                                        catch (ExecutionException ex2) {
                                            throw a(ex2);
                                        }
                                    }
                                    final boolean mayHaveChildren = varData.mayHaveChildren();
                                    try {
                                        value = mayHaveChildren;
                                        if (value == null) {
                                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$3", "calculate"));
                                        }
                                    }
                                    catch (ExecutionException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                return value;
                            }
                            
                            private static ExecutionException a(final ExecutionException ex) {
                                return ex;
                            }
                        });
                    }
                    final Integer n = computeChildrenCount;
                    final int n2 = n;
                    if (n2 > 0) {
                        break Label_0075;
                    }
                    return false;
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
                try {
                    final Integer n = computeChildrenCount;
                    final int n2 = n;
                    if (n2 > 0) {
                        return true;
                    }
                }
                catch (ExecutionException ex3) {
                    throw b((Exception)ex3);
                }
            }
            return false;
        }
        return this.myMayHaveChildrenCache.getResult(new CachedDebuggerResult.NotNullCalculator<Boolean>() {
            final /* synthetic */ EvaluationContext val$context;
            final /* synthetic */ ValueRenderer this$0;
            
            @NotNull
            @Override
            public Boolean calculate() throws ExecutionException, DebuggerCommandException {
                final LLValueData varData = ValueRenderer.this.myValue.getVarData(evaluationContext);
                Boolean value = null;
                Label_0054: {
                    Label_0042: {
                        try {
                            if (varData.isNullPointer()) {
                                break Label_0042;
                            }
                            final CachedDebuggerResult.NotNullCalculator<Boolean> notNullCalculator = this;
                            final ValueRenderer valueRenderer = notNullCalculator.this$0;
                            final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                            final boolean b = cidrPhysicalValue.isSwiftVoid();
                            if (b) {
                                break Label_0042;
                            }
                            break Label_0042;
                        }
                        catch (ExecutionException ex) {
                            throw a(ex);
                        }
                        try {
                            final CachedDebuggerResult.NotNullCalculator<Boolean> notNullCalculator = this;
                            final ValueRenderer valueRenderer = notNullCalculator.this$0;
                            final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                            final boolean b = cidrPhysicalValue.isSwiftVoid();
                            if (b) {
                                final boolean mayHaveChildren = false;
                                break Label_0054;
                            }
                        }
                        catch (ExecutionException ex2) {
                            throw a(ex2);
                        }
                    }
                    final boolean mayHaveChildren = varData.mayHaveChildren();
                    try {
                        value = mayHaveChildren;
                        if (value == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$3", "calculate"));
                        }
                    }
                    catch (ExecutionException ex3) {
                        throw a(ex3);
                    }
                }
                return value;
            }
            
            private static ExecutionException a(final ExecutionException ex) {
                return ex;
            }
        });
    }
    
    protected boolean mayHaveChildrenViaChildrenCount() {
        return false;
    }
    
    @Nullable
    protected Integer computeChildrenCount(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "computeChildrenCount"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return this.myChildrenCountCache.getResult(new CachedDebuggerResult.Calculator<Integer>() {
            final /* synthetic */ ValueRenderer this$0;
            
            @Nullable
            @Override
            public Integer calculate() throws ExecutionException, DebuggerCommandException {
                final LLValueData varData = ValueRenderer.this.myValue.getVarData(evaluationContext);
                Label_0042: {
                    try {
                        if (varData.isNullPointer()) {
                            break Label_0042;
                        }
                        final CachedDebuggerResult.Calculator<Integer> calculator = this;
                        final ValueRenderer valueRenderer = calculator.this$0;
                        final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                        final boolean b = cidrPhysicalValue.isSwiftVoid();
                        if (b) {
                            break Label_0042;
                        }
                        return ValueRenderer.this.doComputeChildrenCount(evaluationContext);
                    }
                    catch (ExecutionException ex) {
                        throw a(ex);
                    }
                    try {
                        final CachedDebuggerResult.Calculator<Integer> calculator = this;
                        final ValueRenderer valueRenderer = calculator.this$0;
                        final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                        final boolean b = cidrPhysicalValue.isSwiftVoid();
                        if (b) {
                            return new Integer(0);
                        }
                    }
                    catch (ExecutionException ex2) {
                        throw a(ex2);
                    }
                }
                return ValueRenderer.this.doComputeChildrenCount(evaluationContext);
            }
            
            private static ExecutionException a(final ExecutionException ex) {
                return ex;
            }
        });
    }
    
    @Nullable
    protected Integer doComputeChildrenCount(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeChildrenCount"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return evaluationContext.getChildrenCount(this.myValue.getPresentationVar());
    }
    
    public final void computeChildren(@NotNull final EvaluationContext evaluationContext, @NotNull final XCompositeNode xCompositeNode) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "computeChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "computeChildren"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (!this.computeMayHaveChildren(evaluationContext)) {
                xCompositeNode.addChildren(XValueChildrenList.EMPTY, true);
                return;
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        this.doComputeChildren(evaluationContext, xCompositeNode);
    }
    
    protected int getBatchSize() {
        return a("cidr.debugger.value.maxChildren", 50);
    }
    
    private static int a(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "getPositiveNumberFromRegistry"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        int intValue = Registry.intValue(s, n);
        if (intValue <= 0) {
            CidrDebuggerLog.LOG.warn(s + " must be a positive number, falling back to default value: " + n);
            intValue = n;
        }
        return intValue;
    }
    
    protected void doComputeChildren(@NotNull final EvaluationContext evaluationContext, @NotNull final XCompositeNode xCompositeNode) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "doComputeChildren"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final LLValue presentationVar = this.myValue.getPresentationVar();
        final DebuggerDriver.ResultList<LLValue> variableChildren = evaluationContext.getVariableChildren(presentationVar, this.getCurrentIndex(), this.getBatchSize());
        this.addChildrenTo(variableChildren.list, evaluationContext, xCompositeNode, this.areChildrenModifiable(presentationVar, this.myValue.getPresentationVarData(evaluationContext)));
        if (variableChildren.hasMore) {
            final Integer computeChildrenCount = this.computeChildrenCount(evaluationContext);
            int n = 0;
            Label_0174: {
                try {
                    if (computeChildrenCount == null) {
                        n = -1;
                        break Label_0174;
                    }
                }
                catch (ExecutionException ex3) {
                    throw b((Exception)ex3);
                }
                n = computeChildrenCount - this.myCurrentIndex;
            }
            xCompositeNode.tooManyChildren(n);
        }
    }
    
    protected void addChildrenTo(@NotNull final List<LLValue> list, @NotNull final EvaluationContext evaluationContext, @NotNull final XCompositeNode xCompositeNode, final boolean b) throws ExecutionException, DebuggerCommandException {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "children", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        final SmartList list2 = new SmartList();
        for (final LLValue llValue : list) {
            try {
                if (xCompositeNode.isObsolete()) {
                    return;
                }
            }
            catch (ExecutionException ex4) {
                throw b((Exception)ex4);
            }
            ((List<CidrMemberValue>)list2).add(new CidrMemberValue(llValue, this.myValue, b));
        }
        CidrValue.addAllTo((Collection<CidrValue>)list2, xCompositeNode);
        this.myCurrentIndex += list.size();
    }
    
    protected int getCurrentIndex() {
        return this.myCurrentIndex;
    }
    
    protected boolean areChildrenModifiable(@NotNull final LLValue llValue, @NotNull final LLValueData llValueData) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "areChildrenModifiable"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        try {
            if (llValueData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer", "areChildrenModifiable"));
            }
        }
        catch (ExecutionRuntimeException ex2) {
            throw b(ex2);
        }
        try {
            if (!llValueData.isSynthetic()) {
                return true;
            }
        }
        catch (ExecutionRuntimeException ex3) {
            throw b(ex3);
        }
        return false;
    }
    
    static {
        SWIFT_DESCRIPTION_PATTERN = Pattern.compile("( *((\u25bf .*)|(- .*? : .*?))(\n|$))*");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    static class ExecutionRuntimeException extends RuntimeException
    {
        ExecutionException cause;
        
        ExecutionRuntimeException(final ExecutionException cause) {
            this.cause = cause;
        }
    }
    
    private static class ConsoleDescriptionNode implements XCompositeNode
    {
        private final StringBuilder myBuilder;
        private final EvaluationContext myContext;
        
        public ConsoleDescriptionNode(final StringBuilder myBuilder, final EvaluationContext myContext) {
            this.myBuilder = myBuilder;
            this.myContext = myContext;
        }
        
        public void addChildren(@NotNull final XValueChildrenList list, final boolean b) {
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "children", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "addChildren"));
                }
            }
            catch (DebuggerCommandException ex) {
                throw b(ex);
            }
            for (int i = 0; i < list.size(); ++i) {
                this.myBuilder.append("\t").append(list.getName(i)).append(" = ");
                try {
                    this.myBuilder.append(((CidrValue)list.getValue(i)).getConsoleDescription(this.myContext).replace("\n", "\n\t"));
                }
                catch (DebuggerCommandException ex2) {
                    this.setErrorMessage(ex2.getMessage());
                }
                catch (ExecutionException ex3) {
                    throw new ExecutionRuntimeException(ex3);
                }
                this.myBuilder.append("\n");
            }
        }
        
        public void tooManyChildren(final int n) {
            try {
                this.myBuilder.append("\t...");
                if (n > 0) {
                    this.myBuilder.append(n).append(" more\n");
                }
            }
            catch (ExecutionRuntimeException ex) {
                throw b(ex);
            }
        }
        
        public void setErrorMessage(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setErrorMessage"));
                }
            }
            catch (ExecutionRuntimeException ex) {
                throw b(ex);
            }
            a(this.myBuilder, s);
        }
        
        public void setErrorMessage(@NotNull final String errorMessage, @Nullable final XDebuggerTreeNodeHyperlink xDebuggerTreeNodeHyperlink) {
            try {
                if (errorMessage == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setErrorMessage"));
                }
            }
            catch (ExecutionRuntimeException ex) {
                throw b(ex);
            }
            this.setErrorMessage(errorMessage);
        }
        
        public void setMessage(@NotNull final String s, @Nullable final Icon icon, @NotNull final SimpleTextAttributes simpleTextAttributes, @Nullable final XDebuggerTreeNodeHyperlink xDebuggerTreeNodeHyperlink) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setMessage"));
                }
            }
            catch (ExecutionRuntimeException ex) {
                throw b(ex);
            }
            try {
                if (simpleTextAttributes == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setMessage"));
                }
            }
            catch (ExecutionRuntimeException ex2) {
                throw b(ex2);
            }
        }
        
        public boolean isObsolete() {
            return false;
        }
        
        public void setAlreadySorted(final boolean b) {
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
