// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLCodepoint;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.breakpoints.XBreakpointType;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.breakpoints.XBreakpoint;

public class CidrSymbolicBreakpointHandler extends CidrCodePointHandlerBase<XBreakpoint<CidrSymbolicBreakpointType.Properties>>
{
    public CidrSymbolicBreakpointHandler(final CidrDebugProcess cidrDebugProcess, @NotNull final Class<? extends XBreakpointType<XBreakpoint<CidrSymbolicBreakpointType.Properties>, ?>> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler", "<init>"));
        }
        super(cidrDebugProcess, clazz);
    }
    
    @NotNull
    @Override
    protected Collection<LLCodepoint> doAddCodepoints(@NotNull final DebuggerDriver p0, @NotNull final XBreakpoint<CidrSymbolicBreakpointType.Properties> p1, final long p2, final int p3) throws ExecutionException {
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
        //    18: ldc             "driver"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doAddCodepoints"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "breakpoint"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doAddCodepoints"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_2        
        //    90: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.convertCondition:(Lcom/intellij/xdebugger/breakpoints/XBreakpoint;)Ljava/lang/String;
        //    93: astore          6
        //    95: aload_2        
        //    96: invokeinterface com/intellij/xdebugger/breakpoints/XBreakpoint.getProperties:()Lcom/intellij/xdebugger/breakpoints/XBreakpointProperties;
        //   101: checkcast       Lcom/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties;
        //   104: astore          7
        //   106: aload           7
        //   108: ifnull          126
        //   111: aload           7
        //   113: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties.getSymbolPattern:()Ljava/lang/String;
        //   116: ifnonnull       175
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //   125: athrow         
        //   126: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   129: dup            
        //   130: ifnonnull       174
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //   139: athrow         
        //   140: new             Ljava/lang/IllegalStateException;
        //   143: dup            
        //   144: ldc             "@NotNull method %s.%s must not return null"
        //   146: ldc             2
        //   148: anewarray       Ljava/lang/Object;
        //   151: dup            
        //   152: ldc             0
        //   154: ldc             "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler"
        //   156: aastore        
        //   157: dup            
        //   158: ldc             1
        //   160: ldc             "doAddCodepoints"
        //   162: aastore        
        //   163: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   166: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   169: athrow         
        //   170: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //   173: athrow         
        //   174: areturn        
        //   175: aload_1        
        //   176: aload           7
        //   178: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties.getSymbolPattern:()Ljava/lang/String;
        //   181: aload           7
        //   183: invokevirtual   com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties.getModuleName:()Ljava/lang/String;
        //   186: aload           6
        //   188: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver.addSymbolicBreakpoint:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jetbrains/cidr/execution/debugger/backend/LLSymbolicBreakpoint;
        //   191: astore          8
        //   193: aload           8
        //   195: ifnonnull       208
        //   198: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   201: goto            213
        //   204: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //   207: athrow         
        //   208: aload           8
        //   210: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   213: dup            
        //   214: ifnonnull       251
        //   217: new             Ljava/lang/IllegalStateException;
        //   220: dup            
        //   221: ldc             "@NotNull method %s.%s must not return null"
        //   223: ldc             2
        //   225: anewarray       Ljava/lang/Object;
        //   228: dup            
        //   229: ldc             0
        //   231: ldc             "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler"
        //   233: aastore        
        //   234: dup            
        //   235: ldc             1
        //   237: ldc             "doAddCodepoints"
        //   239: aastore        
        //   240: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   243: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   246: athrow         
        //   247: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //   250: athrow         
        //   251: areturn        
        //   252: astore          8
        //   254: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   257: dup            
        //   258: ifnonnull       295
        //   261: new             Ljava/lang/IllegalStateException;
        //   264: dup            
        //   265: ldc             "@NotNull method %s.%s must not return null"
        //   267: ldc             2
        //   269: anewarray       Ljava/lang/Object;
        //   272: dup            
        //   273: ldc             0
        //   275: ldc             "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler"
        //   277: aastore        
        //   278: dup            
        //   279: ldc             1
        //   281: ldc             "doAddCodepoints"
        //   283: aastore        
        //   284: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   287: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   290: athrow         
        //   291: invokestatic    com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointHandler.b:(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;)Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //   294: athrow         
        //   295: areturn        
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //    Signature:
        //  (Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver;Lcom/intellij/xdebugger/breakpoints/XBreakpoint<Lcom/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties;>;JI)Ljava/util/Collection<Lcom/jetbrains/cidr/execution/debugger/backend/LLCodepoint;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                    
        //  -----  -----  -----  -----  ------------------------------------------------------------------------
        //  193    204    204    208    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  126    170    170    174    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  111    133    136    140    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  106    119    122    126    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  44     84     84     88     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  175    213    252    296    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  213    247    247    251    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  254    291    291    295    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    private static DebuggerCommandException b(final DebuggerCommandException ex) {
        return ex;
    }
}
