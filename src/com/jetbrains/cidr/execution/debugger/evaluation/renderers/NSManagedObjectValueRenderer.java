// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.util.Expirable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValueModifier;
import com.intellij.xdebugger.frame.XValueModifier;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrMemberValue;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import com.jetbrains.cidr.execution.debugger.evaluation.ValueRendererFactory;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.SmartList;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.openapi.util.Pair;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Key;

public class NSManagedObjectValueRenderer extends ValueRenderer
{
    private static final Key<Boolean> IS_NS_MANAGED_OBJECT;
    private volatile LLValue myEntityCache;
    protected final String myClassName;
    private static Pattern MANAGED_OBJECT_PATTERN;
    
    protected NSManagedObjectValueRenderer(@NotNull final CidrPhysicalValue p0, final EvaluationContext p1) throws ExecutionException, DebuggerCommandException, NotApplicableException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       40
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "value"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "<init>"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: aload_0        
        //    41: aload_1        
        //    42: invokespecial   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.<init>:(Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;)V
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    49: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVar:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
        //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.getType:()Ljava/lang/String;
        //    55: astore_3       
        //    56: aload_2        
        //    57: aload_3        
        //    58: getstatic       com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.IS_NS_MANAGED_OBJECT:Lcom/intellij/openapi/util/Key;
        //    61: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext.getCachedTypeInfo:(Ljava/lang/String;Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //    64: checkcast       Ljava/lang/Boolean;
        //    67: astore          4
        //    69: aload           4
        //    71: ifnonnull       174
        //    74: aload_3        
        //    75: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    78: ldc             "*"
        //    80: invokestatic    com/intellij/openapi/util/text/StringUtil.trimEnd:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    83: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    86: astore          5
        //    88: aload_0        
        //    89: aload           5
        //    91: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.isManagedObjectClass:(Ljava/lang/String;)Z
        //    94: ifne            150
        //    97: getstatic       com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.MANAGED_OBJECT_PATTERN:Ljava/util/regex/Pattern;
        //   100: aload_1        
        //   101: aload_2        
        //   102: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVarData:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
        //   105: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.getDescription:()Ljava/lang/String;
        //   108: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   111: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   114: invokevirtual   java/util/regex/Matcher.matches:()Z
        //   117: ifeq            158
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   126: athrow         
        //   127: aload_2        
        //   128: ldc             "NSManagedObject"
        //   130: aload_0        
        //   131: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.getValue:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   134: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVar:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
        //   137: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext.isKindOfClass:(Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;)Z
        //   140: ifeq            158
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   149: athrow         
        //   150: iconst_1       
        //   151: goto            159
        //   154: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   157: athrow         
        //   158: iconst_0       
        //   159: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   162: astore          4
        //   164: aload_2        
        //   165: aload_3        
        //   166: getstatic       com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.IS_NS_MANAGED_OBJECT:Lcom/intellij/openapi/util/Key;
        //   169: aload           4
        //   171: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext.putCachedTypeInfo:(Ljava/lang/String;Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   174: aload           4
        //   176: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   179: ifne            194
        //   182: new             Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$NotApplicableException;
        //   185: dup            
        //   186: invokespecial   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$NotApplicableException.<init>:()V
        //   189: athrow         
        //   190: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   193: athrow         
        //   194: aload_0        
        //   195: aload_0        
        //   196: aload_2        
        //   197: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.determineClassName:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Ljava/lang/String;
        //   200: putfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myClassName:Ljava/lang/String;
        //   203: return         
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //  throws com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException
        //  throws com.jetbrains.cidr.execution.debugger.evaluation.renderers.NSManagedObjectValueRenderer.NotApplicableException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  88     120    123    127    Lcom/intellij/execution/ExecutionException;
        //  97     143    146    150    Lcom/intellij/execution/ExecutionException;
        //  127    154    154    158    Lcom/intellij/execution/ExecutionException;
        //  174    190    190    194    Lcom/intellij/execution/ExecutionException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0127:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    protected String determineClassName(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "determineClassName"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String stringFromNSString;
        try {
            stringFromNSString = evaluationContext.stringFromNSString(evaluationContext.messageSend(this.a(evaluationContext), "managedObjectClassName"));
            if (stringFromNSString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "determineClassName"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return stringFromNSString;
    }
    
    @NotNull
    @Override
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
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getChildEvaluationExpression"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: iload_2        
        //    45: ifeq            227
        //    48: aload_1        
        //    49: instanceof      Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValue;
        //    52: ifeq            150
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    61: athrow         
        //    62: new             Ljava/lang/StringBuilder;
        //    65: dup            
        //    66: invokespecial   java/lang/StringBuilder.<init>:()V
        //    69: ldc             "(id)["
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    78: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getEvaluationExpression:()Ljava/lang/String;
        //    81: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    84: ldc             " valueForKey:@\""
        //    86: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    89: aload_1        
        //    90: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getName:()Ljava/lang/String;
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: ldc             "\"]"
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   104: dup            
        //   105: ifnonnull       149
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   114: athrow         
        //   115: new             Ljava/lang/IllegalStateException;
        //   118: dup            
        //   119: ldc             "@NotNull method %s.%s must not return null"
        //   121: ldc             2
        //   123: anewarray       Ljava/lang/Object;
        //   126: dup            
        //   127: ldc             0
        //   129: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer"
        //   131: aastore        
        //   132: dup            
        //   133: ldc             1
        //   135: ldc             "getChildEvaluationExpression"
        //   137: aastore        
        //   138: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   141: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   144: athrow         
        //   145: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   148: athrow         
        //   149: areturn        
        //   150: new             Ljava/lang/StringBuilder;
        //   153: dup            
        //   154: invokespecial   java/lang/StringBuilder.<init>:()V
        //   157: ldc             "("
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: aload_0        
        //   163: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   166: iconst_1       
        //   167: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getEvaluationExpression:(Z)Ljava/lang/String;
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: ldc             ")->"
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: aload_1        
        //   179: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getName:()Ljava/lang/String;
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: dup            
        //   189: ifnonnull       226
        //   192: new             Ljava/lang/IllegalStateException;
        //   195: dup            
        //   196: ldc             "@NotNull method %s.%s must not return null"
        //   198: ldc             2
        //   200: anewarray       Ljava/lang/Object;
        //   203: dup            
        //   204: ldc             0
        //   206: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer"
        //   208: aastore        
        //   209: dup            
        //   210: ldc             1
        //   212: ldc             "getChildEvaluationExpression"
        //   214: aastore        
        //   215: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   218: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   221: athrow         
        //   222: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   225: athrow         
        //   226: areturn        
        //   227: aload_1        
        //   228: instanceof      Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValue;
        //   231: ifeq            322
        //   234: new             Ljava/lang/StringBuilder;
        //   237: dup            
        //   238: invokespecial   java/lang/StringBuilder.<init>:()V
        //   241: ldc             "(id)["
        //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   246: aload_0        
        //   247: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   250: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getEvaluationExpression:()Ljava/lang/String;
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: ldc             " "
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: aload_1        
        //   262: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getName:()Ljava/lang/String;
        //   265: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   268: ldc             "]"
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   276: dup            
        //   277: ifnonnull       321
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   286: athrow         
        //   287: new             Ljava/lang/IllegalStateException;
        //   290: dup            
        //   291: ldc             "@NotNull method %s.%s must not return null"
        //   293: ldc             2
        //   295: anewarray       Ljava/lang/Object;
        //   298: dup            
        //   299: ldc             0
        //   301: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer"
        //   303: aastore        
        //   304: dup            
        //   305: ldc             1
        //   307: ldc             "getChildEvaluationExpression"
        //   309: aastore        
        //   310: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   313: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   316: athrow         
        //   317: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   320: athrow         
        //   321: areturn        
        //   322: new             Ljava/lang/StringBuilder;
        //   325: dup            
        //   326: invokespecial   java/lang/StringBuilder.<init>:()V
        //   329: ldc             "(("
        //   331: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: aload_0        
        //   335: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myClassName:Ljava/lang/String;
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: ldc             " *)("
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: aload_0        
        //   347: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   350: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getEvaluationExpression:()Ljava/lang/String;
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: ldc             "))->"
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: aload_1        
        //   362: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getName:()Ljava/lang/String;
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   371: dup            
        //   372: ifnonnull       409
        //   375: new             Ljava/lang/IllegalStateException;
        //   378: dup            
        //   379: ldc             "@NotNull method %s.%s must not return null"
        //   381: ldc             2
        //   383: anewarray       Ljava/lang/Object;
        //   386: dup            
        //   387: ldc             0
        //   389: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer"
        //   391: aastore        
        //   392: dup            
        //   393: ldc             1
        //   395: ldc             "getChildEvaluationExpression"
        //   397: aastore        
        //   398: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   401: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   404: athrow         
        //   405: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   408: athrow         
        //   409: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     58     62     Ljava/lang/IllegalArgumentException;
        //  48     108    111    115    Ljava/lang/IllegalArgumentException;
        //  62     145    145    149    Ljava/lang/IllegalArgumentException;
        //  150    222    222    226    Ljava/lang/IllegalArgumentException;
        //  227    280    283    287    Ljava/lang/IllegalArgumentException;
        //  234    317    317    321    Ljava/lang/IllegalArgumentException;
        //  322    405    405    409    Ljava/lang/IllegalArgumentException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @NotNull
    @Override
    public String getDisplayType() {
        String string;
        try {
            string = this.myClassName + " *";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "getDisplayType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return string;
    }
    
    @Override
    public Icon getIcon(final boolean b) {
        return AllIcons.Debugger.Db_db_object;
    }
    
    @NotNull
    @Override
    protected Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final String stringFromNSString = evaluationContext.stringFromNSString(evaluationContext.messageSend(this.a(evaluationContext), "name"));
        final String presentableValue = evaluationContext.messageSendData(this.messageSendToSelf("objectID", "id", evaluationContext), "URIRepresentation").getPresentableValue();
        StringBuilder append = null;
        String s = null;
        Label_0124: {
            try {
                append = new StringBuilder().append("entity: ").append(stringFromNSString);
                if (this.messageSendToSelfData("isFault", "unsigned char", evaluationContext).isTrue()) {
                    s = "(Fault) ";
                    break Label_0124;
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
            s = " ";
        }
        final String string = append.append(s).append(presentableValue).toString();
        Pair create;
        try {
            create = Pair.create((Object)string, (Object)null);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        return (Pair<String, XFullValueEvaluator>)create;
    }
    
    @NotNull
    private LLValue a(final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        LLValue messageSendToSelf = null;
        Label_0032: {
            try {
                if (this.myEntityCache != null) {
                    final LLValue myEntityCache;
                    messageSendToSelf = (myEntityCache = this.myEntityCache);
                    break Label_0032;
                }
            }
            catch (ExecutionException ex) {
                throw c((Exception)ex);
            }
            LLValue myEntityCache = this.myEntityCache = (messageSendToSelf = this.messageSendToSelf("entity", "id", evaluationContext));
            try {
                if (myEntityCache == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "getEntity"));
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
        }
        return messageSendToSelf;
    }
    
    @Override
    protected void doComputeChildren(@NotNull final EvaluationContext evaluationContext, @NotNull final XCompositeNode xCompositeNode) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "doComputeChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "doComputeChildren"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        final LLValue a = this.a(evaluationContext);
        final LLValue messageSend = evaluationContext.messageSend(evaluationContext.messageSend(a, "attributesByName"), "keyEnumerator");
        final String myClassName = this.myClassName;
        final SmartList list = new SmartList();
        while (true) {
            final LLValue messageSend2 = evaluationContext.messageSend(messageSend, "nextObject");
            final LLValueData data = evaluationContext.getData(messageSend2);
            try {
                if (data.isFalse()) {
                    break;
                }
            }
            catch (ExecutionException ex3) {
                throw c((Exception)ex3);
            }
            ((List<KeyedValue>)list).add(new KeyedValue(this.messageSendToSelf("valueForKey:" + data.getPointer(), "id", evaluationContext), evaluationContext.stringFromNSString(messageSend2), myClassName));
        }
        final LLValue messageSend3 = evaluationContext.messageSend(evaluationContext.messageSend(a, "relationshipsByName"), "keyEnumerator");
        while (true) {
            final LLValue messageSend4 = evaluationContext.messageSend(messageSend3, "nextObject");
            final LLValueData data2 = evaluationContext.getData(messageSend4);
            try {
                if (data2.isFalse()) {
                    break;
                }
            }
            catch (ExecutionException ex4) {
                throw c((Exception)ex4);
            }
            ((List<KeyedValue>)list).add(new KeyedValue(this.messageSendToSelf("valueForKey:" + data2.getPointer(), "id", evaluationContext), evaluationContext.stringFromNSString(messageSend4), myClassName));
        }
        try {
            if (!this.isManagedObjectClass(myClassName)) {
                final LLValue evaluate = evaluationContext.evaluate(this.valueCastedToManagedObject(this.myValue.getVarData(evaluationContext)));
                List<LLValue> list2 = evaluationContext.getVariableChildren(evaluate, 0, evaluationContext.getChildrenCount(evaluate)).list;
                Label_0492: {
                    try {
                        if (list2.isEmpty() || !evaluate.getType().equals(myClassName + "_" + myClassName + "_ *")) {
                            break Label_0492;
                        }
                    }
                    catch (ExecutionException ex5) {
                        throw c((Exception)ex5);
                    }
                    final LLValue llValue = (LLValue)ContainerUtil.find((Iterable)list2, llValue -> llValue.getName().equals(myClassName));
                    if (llValue != null) {
                        list2 = evaluationContext.getVariableChildren(llValue, 0, evaluationContext.getChildrenCount(llValue)).list;
                    }
                }
                for (final LLValue llValue2 : list2) {
                    try {
                        if (this.isManagedObjectClass(llValue2.getName())) {
                            continue;
                        }
                        ((List<KeyedValue>)list).add((KeyedValue)new IVarValue(llValue2, myClassName));
                    }
                    catch (ExecutionException ex6) {
                        throw c((Exception)ex6);
                    }
                }
            }
        }
        finally {
            CidrValue.addAllTo((Collection<CidrValue>)list, xCompositeNode);
        }
    }
    
    protected String valueCastedToManagedObject(final LLValueData llValueData) throws DebuggerCommandException {
        return EvaluationContext.cast(llValueData.getPointer(), this.myClassName + "*");
    }
    
    protected boolean isManagedObjectClass(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "className", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "isManagedObjectClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return s.equals("NSManagedObject");
    }
    
    protected void invokeSetValue(@NotNull final Pair<LLValue, String> pair, @NotNull final EvaluationContext evaluationContext, @NotNull final LLValueData llValueData, @NotNull final String s) throws ExecutionException, DebuggerCommandException {
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "valuePair", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "invokeSetValue"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluationContext", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "invokeSetValue"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        try {
            if (llValueData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentVarData", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "invokeSetValue"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "keyName", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer", "invokeSetValue"));
            }
        }
        catch (ExecutionException ex4) {
            throw c((Exception)ex4);
        }
        evaluationContext.messageSend(llValueData, " setValue:((id)" + evaluationContext.convertToRValue(llValueData, pair) + ") forKey:@\"" + s + "\"", "void");
    }
    
    static {
        IS_NS_MANAGED_OBJECT = Key.create("IS_NS_MANAGED_OBJECT");
        NSManagedObjectValueRenderer.MANAGED_OBJECT_PATTERN = Pattern.compile(".+<x-coredata://.+", 32);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    public static class Factory implements ValueRendererFactory
    {
        @Nullable
        @Override
        public ValueRenderer createRenderer(@NotNull final FactoryContext factoryContext) throws ExecutionException, DebuggerCommandException {
            try {
                if (factoryContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$Factory", "createRenderer"));
                }
            }
            catch (NotApplicableException ex) {
                throw b(ex);
            }
            Label_0071: {
                try {
                    if (!factoryContext.getSettings().COCOA_RENDERERS_ENABLED) {
                        break Label_0071;
                    }
                    final FactoryContext factoryContext2 = factoryContext;
                    final CidrDebuggerSettings cidrDebuggerSettings = factoryContext2.getSettings();
                    final boolean b = cidrDebuggerSettings.CORE_DATA_RENDERERS_ENABLED;
                    if (!b) {
                        break Label_0071;
                    }
                    break Label_0071;
                }
                catch (NotApplicableException ex2) {
                    throw b(ex2);
                }
                try {
                    final FactoryContext factoryContext2 = factoryContext;
                    final CidrDebuggerSettings cidrDebuggerSettings = factoryContext2.getSettings();
                    final boolean b = cidrDebuggerSettings.CORE_DATA_RENDERERS_ENABLED;
                    if (!b) {
                        return null;
                    }
                }
                catch (NotApplicableException ex3) {
                    throw b(ex3);
                }
            }
            final CidrPhysicalValue physicalValue = factoryContext.getPhysicalValue();
            try {
                if (physicalValue.getVar().getTypeClass() != LLValue.TypeClass.OBJC_POINTER) {
                    return null;
                }
            }
            catch (NotApplicableException ex4) {
                throw b(ex4);
            }
            try {
                if (factoryContext.getLLValueData().isSynthetic()) {
                    return null;
                }
            }
            catch (NotApplicableException ex5) {
                throw b(ex5);
            }
            try {
                return new NSManagedObjectValueRenderer(factoryContext.getPhysicalValue(), factoryContext.getEvaluationContext());
            }
            catch (NotApplicableException ex6) {
                return null;
            }
            catch (DebuggerCommandException ex7) {
                return null;
            }
        }
        
        private static NotApplicableException b(final NotApplicableException ex) {
            return ex;
        }
    }
    
    private abstract class NSManagedObjectMemberValue extends CidrMemberValue
    {
        private final String myClassName;
        
        public NSManagedObjectMemberValue(final LLValue llValue, final String myClassName) {
            super(llValue, NSManagedObjectValueRenderer.this.myValue, true);
            this.myClassName = myClassName;
        }
        
        public NSManagedObjectMemberValue(final LLValue llValue, final String s, final String myClassName) {
            super(llValue, s, NSManagedObjectValueRenderer.this.myValue, true);
            this.myClassName = myClassName;
        }
        
        @Override
        protected XSourcePosition doComputePosition(@NotNull final XSourcePosition xSourcePosition) {
            try {
                if (xSourcePosition == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$NSManagedObjectMemberValue", "doComputePosition"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (NSManagedObjectValueRenderer.this.isManagedObjectClass(this.myClassName)) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return this.getTypesHelper().resolveProperty(this, this.myClassName);
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private class IVarValue extends NSManagedObjectMemberValue
    {
        public IVarValue(final LLValue llValue, final String s) {
            super(llValue, s);
        }
        
        @NotNull
        @Override
        protected ValueRenderer doCreateRenderer(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
            try {
                if (evaluationContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue", "doCreateRenderer"));
                }
            }
            catch (ExecutionException ex) {
                throw a(ex);
            }
            IVarValueRenderer varValueRenderer;
            try {
                varValueRenderer = new IVarValueRenderer((NSManagedObjectMemberValue)this);
                if (varValueRenderer == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue", "doCreateRenderer"));
                }
            }
            catch (ExecutionException ex2) {
                throw a(ex2);
            }
            return varValueRenderer;
        }
        
        private static ExecutionException a(final ExecutionException ex) {
            return ex;
        }
        
        private class IVarValueRenderer extends ValueRenderer
        {
            private IVarValueRenderer(final NSManagedObjectMemberValue nsManagedObjectMemberValue) {
                if (nsManagedObjectMemberValue == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue$IVarValueRenderer", "<init>"));
                }
                super(nsManagedObjectMemberValue);
            }
            
            @Override
            protected boolean areChildrenModifiable(@NotNull final LLValue llValue, @NotNull final LLValueData llValueData) {
                try {
                    if (llValue == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue$IVarValueRenderer", "areChildrenModifiable"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (llValueData == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue$IVarValueRenderer", "areChildrenModifiable"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return true;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }
    }
    
    private class KeyedValue extends NSManagedObjectMemberValue
    {
        public KeyedValue(final LLValue llValue, final String s, final String s2) {
            super(llValue, s, s2);
        }
        
        @Override
        public XValueModifier getModifier() {
            return new KeyedValueModifier(this);
        }
    }
    
    private class KeyedValueModifier extends CidrValueModifier
    {
        public KeyedValueModifier(final KeyedValue keyedValue) {
            super(keyedValue);
        }
        
        @Override
        protected void setValue(@NotNull final String s, @NotNull final Pair<LLValue, String> pair, @NotNull final XValueModifier.XModificationCallback xModificationCallback, @NotNull final DebuggerDriver debuggerDriver) throws DebuggerCommandException, ExecutionException {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origExpr", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
                }
            }
            catch (ExecutionException ex) {
                throw a(ex);
            }
            try {
                if (pair == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "valuePair", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
                }
            }
            catch (ExecutionException ex2) {
                throw a(ex2);
            }
            try {
                if (xModificationCallback == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
                }
            }
            catch (ExecutionException ex3) {
                throw a(ex3);
            }
            try {
                if (debuggerDriver == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
                }
            }
            catch (ExecutionException ex4) {
                throw a(ex4);
            }
            final EvaluationContext evaluationContext = this.getValue().createEvaluationContext(debuggerDriver, null);
            try {
                NSManagedObjectValueRenderer.this.invokeSetValue(pair, evaluationContext, ((KeyedValue)this.getValue()).getParent().getVarData(evaluationContext), this.getValue().getName());
                xModificationCallback.valueModified();
            }
            catch (ExecutionException ex5) {
                xModificationCallback.errorOccurred(CidrDebuggerBundle.message("debug.assign.error.cannotAssignExpression", s, this.getValue().getName()));
                CidrDebuggerLog.LOG.debug(ex5.getMessage());
            }
        }
        
        private static ExecutionException a(final ExecutionException ex) {
            return ex;
        }
    }
    
    protected static class NotApplicableException extends Exception
    {
    }
}
