// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.editor.Document;
import com.intellij.xdebugger.evaluation.EvaluationMode;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;

public class OCDebuggerEditorsProvider extends XDebuggerEditorsProvider
{
    @NotNull
    public FileType getFileType() {
        OCFileType instance;
        try {
            instance = OCFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider", "getFileType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (FileType)instance;
    }
    
    @NotNull
    public Document createDocument(@NotNull final Project p0, @NotNull final String p1, @Nullable final XSourcePosition p2, @NotNull final EvaluationMode p3) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createDocument"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //    62: ldc             "text"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createDocument"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "mode"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "createDocument"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   132: athrow         
        //   133: aload_3        
        //   134: aload_1        
        //   135: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper.getContextElement:(Lcom/intellij/xdebugger/XSourcePosition;Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiElement;
        //   138: astore          5
        //   140: aload           5
        //   142: ifnull          234
        //   145: aload           5
        //   147: invokeinterface com/intellij/psi/PsiElement.getLanguage:()Lcom/intellij/lang/Language;
        //   152: invokestatic    com/jetbrains/cidr/lang/OCLanguage.getInstance:()Lcom/jetbrains/cidr/lang/OCLanguage;
        //   155: if_acmpne       234
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   164: athrow         
        //   165: new             Lcom/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider$1;
        //   168: dup            
        //   169: aload_0        
        //   170: aload           4
        //   172: aload_2        
        //   173: aload_1        
        //   174: aload           5
        //   176: invokespecial   com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider$1.<init>:(Lcom/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider;Lcom/intellij/xdebugger/evaluation/EvaluationMode;Ljava/lang/String;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiElement;)V
        //   179: invokevirtual   com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider$1.execute:()Lcom/intellij/openapi/application/RunResult;
        //   182: invokevirtual   com/intellij/openapi/application/RunResult.getResultObject:()Ljava/lang/Object;
        //   185: checkcast       Lcom/intellij/openapi/editor/Document;
        //   188: dup            
        //   189: ifnonnull       233
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   198: athrow         
        //   199: new             Ljava/lang/IllegalStateException;
        //   202: dup            
        //   203: ldc             "@NotNull method %s.%s must not return null"
        //   205: ldc             2
        //   207: anewarray       Ljava/lang/Object;
        //   210: dup            
        //   211: ldc             0
        //   213: ldc             "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider"
        //   215: aastore        
        //   216: dup            
        //   217: ldc             1
        //   219: ldc             "createDocument"
        //   221: aastore        
        //   222: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   225: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   228: athrow         
        //   229: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   232: athrow         
        //   233: areturn        
        //   234: new             Lcom/intellij/testFramework/LightVirtualFile;
        //   237: dup            
        //   238: ldc             "oc-debug-editor-when-no-source-position-available.txt"
        //   240: aload_2        
        //   241: invokespecial   com/intellij/testFramework/LightVirtualFile.<init>:(Ljava/lang/String;Ljava/lang/CharSequence;)V
        //   244: astore          6
        //   246: invokestatic    com/intellij/openapi/fileEditor/FileDocumentManager.getInstance:()Lcom/intellij/openapi/fileEditor/FileDocumentManager;
        //   249: aload           6
        //   251: invokevirtual   com/intellij/openapi/fileEditor/FileDocumentManager.getDocument:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/editor/Document;
        //   254: dup            
        //   255: ifnonnull       292
        //   258: new             Ljava/lang/IllegalStateException;
        //   261: dup            
        //   262: ldc             "@NotNull method %s.%s must not return null"
        //   264: ldc             2
        //   266: anewarray       Ljava/lang/Object;
        //   269: dup            
        //   270: ldc             0
        //   272: ldc             "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider"
        //   274: aastore        
        //   275: dup            
        //   276: ldc             1
        //   278: ldc             "createDocument"
        //   280: aastore        
        //   281: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   284: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   287: athrow         
        //   288: invokestatic    com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   291: athrow         
        //   292: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     129    129    133    Ljava/lang/IllegalStateException;
        //  140    158    161    165    Ljava/lang/IllegalStateException;
        //  145    192    195    199    Ljava/lang/IllegalStateException;
        //  165    229    229    233    Ljava/lang/IllegalStateException;
        //  246    288    288    292    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0165:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
