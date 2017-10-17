// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.Iterator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.inspections.OCNotReleasedIvarInspection;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.openapi.util.Pair;
import java.util.Set;

public class OCNotReleasedVariablesChecker extends OCSingleSymbolAlgorithm
{
    private Set<Pair<OCInstanceVariableSymbol, PsiElement>> myAssignedIvars;
    private boolean myWasRetained;
    private boolean myStartFromReturns;
    
    public OCNotReleasedVariablesChecker(@NotNull final OCControlFlowGraph ocControlFlowGraph, @NotNull final OCSymbol ocSymbol) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker", "<init>"));
        }
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker", "<init>"));
        }
        super(ocControlFlowGraph, false, ocSymbol);
        this.myAssignedIvars = new HashSet<Pair<OCInstanceVariableSymbol, PsiElement>>();
    }
    
    public void setStartFromReturns(final boolean myStartFromReturns) {
        this.myStartFromReturns = myStartFromReturns;
    }
    
    private boolean a(@NotNull final OCInstruction p0) {
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
        //    18: ldc             "instruction"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAssignmentToOuterScopeVariable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getRValue:()Lcom/intellij/psi/PsiElement;
        //    48: astore_2       
        //    49: aload_2        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    53: ifeq            185
        //    56: aload_2        
        //    57: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    62: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    65: ifeq            185
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_2        
        //    76: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    81: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    84: astore_3       
        //    85: aload_3        
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    91: astore          4
        //    93: aload_3        
        //    94: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    99: aload_2        
        //   100: if_acmpne       182
        //   103: aload           4
        //   105: iconst_1       
        //   106: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.getReceiverSymbol:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   109: astore          5
        //   111: aload           4
        //   113: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.getReceiverIvar:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   116: astore          6
        //   118: aload           6
        //   120: ifnull          150
        //   123: aload_0        
        //   124: getfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myAssignedIvars:Ljava/util/Set;
        //   127: new             Lcom/intellij/openapi/util/Pair;
        //   130: dup            
        //   131: aload           6
        //   133: aload           4
        //   135: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   138: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   143: pop            
        //   144: iconst_1       
        //   145: ireturn        
        //   146: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload           5
        //   152: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   155: ifeq            182
        //   158: aload           5
        //   160: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   163: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   166: ifeq            182
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: iconst_1       
        //   177: ireturn        
        //   178: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: goto            278
        //   185: aload_2        
        //   186: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   189: ifeq            278
        //   192: aload_2        
        //   193: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   198: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   201: ifeq            278
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload_2        
        //   212: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   217: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   220: astore_3       
        //   221: aload_3        
        //   222: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   227: astore          4
        //   229: aload_3        
        //   230: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   235: aload_2        
        //   236: if_acmpne       278
        //   239: aload           4
        //   241: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   244: ifeq            278
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload           4
        //   256: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   259: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   262: ifeq            278
        //   265: goto            272
        //   268: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   271: athrow         
        //   272: iconst_1       
        //   273: ireturn        
        //   274: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: iconst_0       
        //   279: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     68     71     75     Ljava/lang/IllegalArgumentException;
        //  118    146    146    150    Ljava/lang/IllegalArgumentException;
        //  150    169    172    176    Ljava/lang/IllegalArgumentException;
        //  158    178    178    182    Ljava/lang/IllegalArgumentException;
        //  185    204    207    211    Ljava/lang/IllegalArgumentException;
        //  229    247    250    254    Ljava/lang/IllegalArgumentException;
        //  239    265    268    272    Ljava/lang/IllegalArgumentException;
        //  254    274    274    278    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0254:
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
    
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction p0) {
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
        //    18: ldc             "instruction"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isStartInstruction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    48: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    51: if_acmpeq       77
        //    54: aload_1        
        //    55: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    58: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    61: if_acmpeq       77
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    73: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_1        
        //    78: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getRValue:()Lcom/intellij/psi/PsiElement;
        //    81: astore_2       
        //    82: aload_2        
        //    83: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    86: ifeq            147
        //    89: aload_2        
        //    90: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    95: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isReleaseCall:(Lcom/intellij/psi/PsiElement;)Z
        //    98: ifne            141
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myStartFromReturns:Z
        //   112: ifeq            147
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_2        
        //   123: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   128: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   131: ifeq            147
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: iconst_1       
        //   142: ireturn        
        //   143: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_0        
        //   148: aload_1        
        //   149: invokespecial   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.a:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction;)Z
        //   152: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     64     67     71     Ljava/lang/IllegalArgumentException;
        //  54     73     73     77     Ljava/lang/IllegalArgumentException;
        //  82     101    104    108    Ljava/lang/IllegalArgumentException;
        //  89     115    118    122    Ljava/lang/IllegalArgumentException;
        //  108    134    137    141    Ljava/lang/IllegalArgumentException;
        //  122    143    143    147    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction p0) {
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
        //    18: ldc             "instruction"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isEndInstruction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getRValue:()Lcom/intellij/psi/PsiElement;
        //    48: astore_2       
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    53: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    56: if_acmpne       87
        //    59: aload_2        
        //    60: iconst_1       
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainCall:(Lcom/intellij/psi/PsiElement;Z)Z
        //    64: ifeq            85
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload_0        
        //    75: iconst_1       
        //    76: dup_x1         
        //    77: putfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myWasRetained:Z
        //    80: ireturn        
        //    81: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    87: aload_1        
        //    88: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    91: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    94: if_acmpne       182
        //    97: aload_2        
        //    98: ifnull          121
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_2        
        //   109: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   114: goto            122
        //   117: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aconst_null    
        //   122: astore_3       
        //   123: aload_3        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   127: ifeq            182
        //   130: aload_3        
        //   131: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   134: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   139: ldc             "retain"
        //   141: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   144: ifeq            182
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_3        
        //   155: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   158: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;)Lcom/intellij/psi/PsiElement;
        //   161: ifnull          182
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aload_0        
        //   172: iconst_1       
        //   173: dup_x1         
        //   174: putfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myWasRetained:Z
        //   177: ireturn        
        //   178: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: iconst_0       
        //   183: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     67     70     74     Ljava/lang/IllegalArgumentException;
        //  59     81     81     85     Ljava/lang/IllegalArgumentException;
        //  87     101    104    108    Ljava/lang/IllegalArgumentException;
        //  97     117    117    121    Ljava/lang/IllegalArgumentException;
        //  123    147    150    154    Ljava/lang/IllegalArgumentException;
        //  130    164    167    171    Ljava/lang/IllegalArgumentException;
        //  154    178    178    182    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0154:
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
    
    @Nullable
    @Override
    protected PsiElement getElementFromInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker", "getElementFromInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        if (ocInstruction.getKind() == OCInstruction.InstructionKind.READ) {
            final PsiElement rValue = ocInstruction.getRValue();
            try {
                if (rValue != null) {
                    return rValue.getParent();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            return null;
        }
        return super.getElementFromInstruction(ocInstruction);
    }
    
    @NotNull
    public List<PsiElement> getNotReleasedElements() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myCfg:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     8: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getInstructions:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/util/containers/MultiMap;
        //    11: astore_1       
        //    12: aload_1        
        //    13: ifnull          38
        //    16: aload_1        
        //    17: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    20: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //    23: invokeinterface java/util/Collection.isEmpty:()Z
        //    28: ifne            87
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    41: dup            
        //    42: ifnonnull       86
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: new             Ljava/lang/IllegalStateException;
        //    55: dup            
        //    56: ldc             "@NotNull method %s.%s must not return null"
        //    58: ldc             2
        //    60: anewarray       Ljava/lang/Object;
        //    63: dup            
        //    64: ldc             0
        //    66: ldc             "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker"
        //    68: aastore        
        //    69: dup            
        //    70: ldc             1
        //    72: ldc             "getNotReleasedElements"
        //    74: aastore        
        //    75: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    78: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    81: athrow         
        //    82: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: areturn        
        //    87: aload_0        
        //    88: iconst_0       
        //    89: putfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myWasRetained:Z
        //    92: aload_0        
        //    93: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.getNonReachableElements:()Ljava/util/List;
        //    96: astore_2       
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myWasRetained:Z
        //   101: ifeq            143
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.myAssignedIvars:Ljava/util/Set;
        //   108: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   113: astore_3       
        //   114: aload_3        
        //   115: invokeinterface java/util/Iterator.hasNext:()Z
        //   120: ifeq            143
        //   123: aload_3        
        //   124: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   129: checkcast       Lcom/intellij/openapi/util/Pair;
        //   132: astore          4
        //   134: aload_0        
        //   135: aload           4
        //   137: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.handleAssignedIvar:(Lcom/intellij/openapi/util/Pair;)V
        //   140: goto            114
        //   143: aload_2        
        //   144: dup            
        //   145: ifnonnull       182
        //   148: new             Ljava/lang/IllegalStateException;
        //   151: dup            
        //   152: ldc             "@NotNull method %s.%s must not return null"
        //   154: ldc             2
        //   156: anewarray       Ljava/lang/Object;
        //   159: dup            
        //   160: ldc             0
        //   162: ldc             "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker"
        //   164: aastore        
        //   165: dup            
        //   166: ldc             1
        //   168: ldc             "getNotReleasedElements"
        //   170: aastore        
        //   171: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   174: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   177: athrow         
        //   178: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/intellij/psi/PsiElement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     31     34     38     Ljava/lang/IllegalArgumentException;
        //  16     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     82     82     86     Ljava/lang/IllegalArgumentException;
        //  143    178    178    182    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    protected void handleAssignedIvar(@NotNull final Pair<OCInstanceVariableSymbol, PsiElement> pair) {
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker", "handleAssignedIvar"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @NotNull
    public static List<Pair<OCSendMessageExpression, PsiElement>> getUnreleasedObjects(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragment", "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker", "getUnreleasedObjects"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final ArrayList list = new ArrayList<Pair<OCSendMessageExpression, PsiElement>>();
        ArrayList list2;
        try {
            psiElement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                    super.visitSendMessageExpression(ocSendMessageExpression);
                    if (!OCElementUtil.isRetainCall((PsiElement)ocSendMessageExpression, false)) {
                        return;
                    }
                    final List<OCMethodSymbol> allResponders = ocSendMessageExpression.getProbableResponders().getAllResponders();
                    if (allResponders.isEmpty()) {
                        return;
                    }
                    final Iterator<OCMethodSymbol> iterator = allResponders.iterator();
                    while (iterator.hasNext()) {
                        if (!iterator.next().getReturnType().resolve((PsiFile)ocSendMessageExpression.getContainingOCFile()).isPointerToObjectCompatible()) {
                            return;
                        }
                    }
                    final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
                    if ("retain".equals(ocSendMessageExpression.getMessageSelector())) {
                        if (receiverExpression instanceof OCReferenceExpression) {
                            final OCSymbol resolveToSymbol = ((OCReferenceExpression)receiverExpression).resolveToSymbol();
                            if (resolveToSymbol != null && resolveToSymbol.getKind().isVariable()) {
                                return;
                            }
                        }
                        else if (OCNotReleasedIvarInspection.getReceiverIvar(receiverExpression) != null) {
                            return;
                        }
                    }
                    final PsiElement access$000 = a(ocSendMessageExpression);
                    if (access$000 != null) {
                        if (access$000.getParent() instanceof OCReturnStatement && OCElementUtil.isRetainMethod((OCCallable)PsiTreeUtil.getParentOfType(access$000, (Class)OCCallable.class))) {
                            return;
                        }
                        list.add(Pair.create((Object)ocSendMessageExpression, (Object)access$000));
                    }
                }
            });
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker", "getUnreleasedObjects"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return (List<Pair<OCSendMessageExpression, PsiElement>>)list2;
    }
    
    @Nullable
    private static PsiElement a(@NotNull final OCSendMessageExpression p0) {
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
        //    18: ldc             "expression"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLastUnreleasedCall"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    50: astore_1       
        //    51: aload_0        
        //    52: astore_2       
        //    53: aload_0        
        //    54: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    59: astore_3       
        //    60: aload_1        
        //    61: ifnull          306
        //    64: aload_1        
        //    65: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //    68: ifne            106
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_1        
        //    79: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //    82: ifne            106
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_1        
        //    93: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //    96: ifeq            118
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload_1        
        //   107: astore_2       
        //   108: aload_1        
        //   109: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   114: astore_1       
        //   115: goto            60
        //   118: aload_1        
        //   119: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   122: ifeq            193
        //   125: aload_1        
        //   126: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   129: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   134: astore          4
        //   136: aload           4
        //   138: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   141: ifeq            191
        //   144: aload           4
        //   146: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   149: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   154: astore          5
        //   156: aload           5
        //   158: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   161: ifeq            191
        //   164: aload           5
        //   166: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   169: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isRetained:()Z
        //   174: ifeq            191
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: goto            306
        //   187: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aconst_null    
        //   192: areturn        
        //   193: aload_1        
        //   194: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   197: ifeq            206
        //   200: aconst_null    
        //   201: areturn        
        //   202: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: aload_1        
        //   207: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   210: ifeq            220
        //   213: goto            306
        //   216: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload_1        
        //   221: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   224: ifeq            306
        //   227: aload_1        
        //   228: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   231: astore          4
        //   233: aload           4
        //   235: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   240: astore          5
        //   242: aload           5
        //   244: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isReleaseSelector:(Ljava/lang/String;)Z
        //   247: ifeq            256
        //   250: aconst_null    
        //   251: areturn        
        //   252: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: aload           4
        //   258: iconst_0       
        //   259: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainCall:(Lcom/intellij/psi/PsiElement;Z)Z
        //   262: ifne            306
        //   265: aload           4
        //   267: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   272: aload_3        
        //   273: aload_0        
        //   274: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   277: ifne            294
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: goto            306
        //   290: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: aload_1        
        //   295: astore_2       
        //   296: aload_1        
        //   297: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   302: astore_1       
        //   303: goto            60
        //   306: aload_2        
        //   307: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  60     71     74     78     Ljava/lang/IllegalArgumentException;
        //  64     85     88     92     Ljava/lang/IllegalArgumentException;
        //  78     99     102    106    Ljava/lang/IllegalArgumentException;
        //  156    177    180    184    Ljava/lang/IllegalArgumentException;
        //  164    187    187    191    Ljava/lang/IllegalArgumentException;
        //  193    202    202    206    Ljava/lang/IllegalArgumentException;
        //  206    216    216    220    Ljava/lang/IllegalArgumentException;
        //  242    252    252    256    Ljava/lang/IllegalArgumentException;
        //  256    280    283    287    Ljava/lang/IllegalArgumentException;
        //  265    290    290    294    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
