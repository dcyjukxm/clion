// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.intellij.util.Alarm;
import com.intellij.openapi.Disposable;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCGotoStatement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.openapi.util.Ref;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import com.jetbrains.cidr.lang.dfa.OCNotUsedValueChecker;
import java.util.HashSet;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.jetbrains.cidr.lang.dfa.OCControlFlowBuilder;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.openapi.util.Pair;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.util.Processor;
import com.intellij.util.Query;
import com.intellij.psi.search.searches.ReferencesSearch;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCDFAUtils
{
    @Contract("null->false")
    public static boolean isConstant(@Nullable final OCExpression ocExpression) {
        try {
            if (OCExpressionEvaluator.evaluate(ocExpression) != null) {
                return true;
            }
        }
        catch (DFAException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Contract("null, _ -> false")
    public static boolean isLocalVariable(@Nullable final OCSymbol p0, final OCControlFlowGraph p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          63
        //     4: aload_1        
        //     5: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLocalSymbols:()Ljava/util/Set;
        //     8: aload_0        
        //     9: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    14: ifeq            63
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    23: athrow         
        //    24: aload_0        
        //    25: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    28: ifeq            55
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    37: athrow         
        //    38: aload_0        
        //    39: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    42: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //    45: ifne            63
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    54: athrow         
        //    55: iconst_1       
        //    56: goto            64
        //    59: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    62: athrow         
        //    63: iconst_0       
        //    64: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      17     20     24     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  4      31     34     38     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  24     48     51     55     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  38     59     59     63     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    @Contract("null -> null")
    @Nullable
    public static OCSymbol getCallSymbol(@Nullable final PsiElement psiElement) {
        OCSymbol ocSymbol = null;
        if (psiElement instanceof OCCallExpression) {
            final OCExpression functionReferenceExpression = ((OCCallExpression)psiElement).getFunctionReferenceExpression();
            if (functionReferenceExpression instanceof OCReferenceExpression) {
                ocSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
            }
            else if (functionReferenceExpression instanceof OCQualifiedExpression) {
                ocSymbol = ((OCQualifiedExpression)functionReferenceExpression).resolveToSymbol();
            }
        }
        else if (psiElement instanceof OCSendMessageExpression) {
            ocSymbol = ((OCSendMessageExpression)psiElement).getProbableResponders().getKnownResponder();
        }
        Label_0103: {
            try {
                if (psiElement == null) {
                    return null;
                }
                final PsiElement psiElement2 = psiElement;
                final PsiFile psiFile = psiElement2.getContainingFile();
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = isGoodCallable(psiFile, ocSymbol2);
                if (b) {
                    break Label_0103;
                }
                return null;
            }
            catch (DFAException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiFile psiFile = psiElement2.getContainingFile();
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = isGoodCallable(psiFile, ocSymbol2);
                if (b) {
                    return ocSymbol;
                }
            }
            catch (DFAException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    @Contract("_, null -> false")
    public static boolean isGoodCallable(@NotNull final PsiFile p0, @Nullable final OCSymbol p1) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isGoodCallable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnull          113
        //    48: aload_1        
        //    49: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isCallable:()Z
        //    54: ifeq            113
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    63: athrow         
        //    64: aload_0        
        //    65: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    70: ifnull          113
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    79: athrow         
        //    80: aload_0        
        //    81: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    86: aload_1        
        //    87: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    92: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    95: ifeq            113
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   104: athrow         
        //   105: iconst_1       
        //   106: goto            114
        //   109: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  44     57     60     64     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  48     73     76     80     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  64     98     101    105    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  80     109    109    113    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
    
    public static boolean isPrivateCallable(final OCSymbol ocSymbol) {
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        PsiFile containingFile = null;
        Label_0025: {
            try {
                if (locateDefinition != null) {
                    containingFile = locateDefinition.getContainingFile();
                    break Label_0025;
                }
            }
            catch (DFAException ex) {
                throw b(ex);
            }
            containingFile = null;
        }
        final PsiFile psiFile = containingFile;
        Label_0055: {
            try {
                if (psiFile == null) {
                    return false;
                }
                final PsiElement psiElement = locateDefinition;
                final Query query = ReferencesSearch.search(psiElement);
                final PsiFile psiFile2 = psiFile;
                final Processor processor = psiReference -> psiReference.getElement().getContainingFile().equals(psiFile2);
                final boolean b = query.forEach(processor);
                if (b) {
                    break Label_0055;
                }
                return false;
            }
            catch (DFAException ex2) {
                throw b(ex2);
            }
            try {
                final PsiElement psiElement = locateDefinition;
                final Query query = ReferencesSearch.search(psiElement);
                final PsiFile psiFile2 = psiFile;
                final Processor processor = psiReference -> psiReference.getElement().getContainingFile().equals(psiFile2);
                final boolean b = query.forEach(processor);
                if (b) {
                    return true;
                }
            }
            catch (DFAException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    public static boolean processTypeHierarchy(@NotNull final OCType p0, @NotNull final PsiElement p1, @NotNull final Set<OCType> p2, @NotNull final Processor<Pair<OCType, OCType>> p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processTypeHierarchy"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processTypeHierarchy"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "processed"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processTypeHierarchy"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   131: athrow         
        //   132: aload_3        
        //   133: ifnonnull       176
        //   136: new             Ljava/lang/IllegalArgumentException;
        //   139: dup            
        //   140: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   142: ldc             3
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "processor"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "processTypeHierarchy"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   175: athrow         
        //   176: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   179: aload_0        
        //   180: aload_1        
        //   181: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   186: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   189: astore          4
        //   191: aload_2        
        //   192: aload           4
        //   194: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   199: ifeq            208
        //   202: iconst_1       
        //   203: ireturn        
        //   204: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   207: athrow         
        //   208: aload_2        
        //   209: aload           4
        //   211: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   216: pop            
        //   217: aload_0        
        //   218: aload_1        
        //   219: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   224: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.getCounterpart:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   227: astore          5
        //   229: aload           5
        //   231: ifnull          269
        //   234: aload_3        
        //   235: aload           4
        //   237: aload           5
        //   239: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   242: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   247: pop            
        //   248: aload_3        
        //   249: aload           5
        //   251: aload           4
        //   253: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   256: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   261: pop            
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   268: athrow         
        //   269: aload           4
        //   271: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   274: ifeq            402
        //   277: aload           4
        //   279: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   282: ifne            402
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   291: athrow         
        //   292: aload           4
        //   294: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   297: ldc             "NSData *"
        //   299: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   302: ifne            402
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   311: athrow         
        //   312: aload           4
        //   314: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   317: ldc             "NSNull *"
        //   319: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   322: ifne            402
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   331: athrow         
        //   332: aload           4
        //   334: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   337: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   340: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   343: astore          6
        //   345: aload           6
        //   347: ifnull          383
        //   350: aload           6
        //   352: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   355: astore          6
        //   357: aload_3        
        //   358: aload           4
        //   360: aload           6
        //   362: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   365: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   370: pop            
        //   371: aload           6
        //   373: aload_1        
        //   374: aload_2        
        //   375: aload_3        
        //   376: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.processTypeHierarchy:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Ljava/util/Set;Lcom/intellij/util/Processor;)Z
        //   379: pop            
        //   380: goto            400
        //   383: aload_3        
        //   384: new             Lcom/intellij/openapi/util/Pair;
        //   387: dup            
        //   388: aload           4
        //   390: aconst_null    
        //   391: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   394: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   399: pop            
        //   400: iconst_1       
        //   401: ireturn        
        //   402: aload           4
        //   404: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //   407: ifne            425
        //   410: aload           4
        //   412: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToCppStructType:()Z
        //   415: ifeq            470
        //   418: goto            425
        //   421: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   424: athrow         
        //   425: aload           4
        //   427: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   430: astore          6
        //   432: aload           6
        //   434: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   437: ifne            446
        //   440: iconst_0       
        //   441: ireturn        
        //   442: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   445: athrow         
        //   446: aload           6
        //   448: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   451: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   454: aload           4
        //   456: aload_3        
        //   457: aload_1        
        //   458: aload_2        
        //   459: invokedynamic   process:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiElement;Ljava/util/Set;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   464: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processAllBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   467: pop            
        //   468: iconst_1       
        //   469: ireturn        
        //   470: iconst_0       
        //   471: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Ljava/util/Set<Lcom/jetbrains/cidr/lang/types/OCType;>;Lcom/intellij/util/Processor<Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;>;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  44     84     84     88     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  88     128    128    132    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  132    172    172    176    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  191    204    204    208    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  229    262    265    269    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  269    285    288    292    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  277    305    308    312    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  292    325    328    332    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  402    418    421    425    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  432    442    442    446    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0292:
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
    public static InstanceOf getInstanceofPair(@Nullable final OCExpression ocExpression) {
        if (ocExpression instanceof OCSendMessageExpression) {
            final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)ocExpression;
            final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
            try {
                if (!(receiverExpression instanceof OCReferenceExpression) || !ocSendMessageExpression.getMessageSelector().equals("isKindOfClass:")) {
                    return null;
                }
            }
            catch (DFAException ex) {
                throw b(ex);
            }
            final OCReferenceElement referenceElement = ((OCReferenceExpression)receiverExpression).getReferenceElement();
            final OCExpression ocExpression2 = ocSendMessageExpression.getArgumentExpressions().get(0);
            try {
                if (!(ocExpression2 instanceof OCSendMessageExpression) || referenceElement == null) {
                    return null;
                }
            }
            catch (DFAException ex2) {
                throw b(ex2);
            }
            final OCSendMessageExpression ocSendMessageExpression2 = (OCSendMessageExpression)ocExpression2;
            final OCExpression receiverExpression2 = ocSendMessageExpression2.getReceiverExpression();
            try {
                if (!ocSendMessageExpression2.getMessageSelector().equals("class") || !(receiverExpression2 instanceof OCReferenceExpression)) {
                    return null;
                }
            }
            catch (DFAException ex3) {
                throw b(ex3);
            }
            final OCReferenceElement referenceElement2 = ((OCReferenceExpression)receiverExpression2).getReferenceElement();
            if (referenceElement2 != null) {
                final OCSymbol resolveToSymbol = referenceElement.resolveToSymbol();
                try {
                    if (resolveToSymbol != null) {
                        return new InstanceOf((OCReferenceExpression)receiverExpression, resolveToSymbol, OCPointerType.to(OCReferenceType.resolvedFromText(referenceElement2.getName(), ocExpression.getContainingFile())));
                    }
                }
                catch (DFAException ex4) {
                    throw b(ex4);
                }
            }
        }
        return null;
    }
    
    @NotNull
    public static Pair<Set<PsiElement>, OCControlFlowGraph> getNotUsedWrites(@NotNull final OCCallable ocCallable) {
        try {
            if (ocCallable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils", "getNotUsedWrites"));
            }
        }
        catch (DFAException ex) {
            throw b(ex);
        }
        final OCControlFlowGraph ocControlFlowGraph = new OCControlFlowGraph((PsiElement)ocCallable, null);
        new OCControlFlowBuilder(null, ocControlFlowGraph, null).processFirstCodeFragment((PsiElement)ocCallable);
        final HashSet set = new HashSet();
        final Iterator<OCSymbol> iterator = (Iterator<OCSymbol>)ocControlFlowGraph.getLocalSymbols().iterator();
        while (iterator.hasNext()) {
            final OCNotUsedValueChecker ocNotUsedValueChecker = new OCNotUsedValueChecker(ocControlFlowGraph, iterator.next()) {
                @Override
                protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
                    try {
                        if (ocInstruction == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1", "isStartInstruction"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    final PsiElement rValue = ocInstruction.getRValue();
                    Label_0071: {
                        try {
                            if (!(rValue instanceof OCReferenceExpression)) {
                                return false;
                            }
                            final OCNotUsedValueChecker ocNotUsedValueChecker = this;
                            final PsiElement psiElement = rValue;
                            final boolean b = ocNotUsedValueChecker.a(psiElement);
                            if (b) {
                                break Label_0071;
                            }
                            return false;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw d(ex2);
                        }
                        try {
                            final OCNotUsedValueChecker ocNotUsedValueChecker = this;
                            final PsiElement psiElement = rValue;
                            final boolean b = ocNotUsedValueChecker.a(psiElement);
                            if (b) {
                                return super.isStartInstruction(ocInstruction);
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw d(ex3);
                        }
                    }
                    return false;
                }
                
                @Override
                protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
                    try {
                        if (ocInstruction == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1", "isEndInstruction"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    return true;
                }
                
                @Override
                protected PsiElement getElementFromInstruction(@NotNull final OCInstruction ocInstruction) {
                    try {
                        if (ocInstruction == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1", "getElementFromInstruction"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    return ocInstruction.getRValue();
                }
                
                private boolean a(final PsiElement p0) {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_1        
                    //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
                    //     6: astore_2       
                    //     7: aload_2        
                    //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
                    //    11: ifeq            20
                    //    14: iconst_1       
                    //    15: ireturn        
                    //    16: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    19: athrow         
                    //    20: aload_2        
                    //    21: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
                    //    24: ifeq            53
                    //    27: aload_2        
                    //    28: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
                    //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
                    //    36: aload_1        
                    //    37: if_acmpne       53
                    //    40: goto            47
                    //    43: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    46: athrow         
                    //    47: iconst_1       
                    //    48: ireturn        
                    //    49: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    52: athrow         
                    //    53: aload_2        
                    //    54: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
                    //    57: ifeq            86
                    //    60: aload_2        
                    //    61: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
                    //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
                    //    69: aload_1        
                    //    70: if_acmpne       86
                    //    73: goto            80
                    //    76: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    79: athrow         
                    //    80: iconst_1       
                    //    81: ireturn        
                    //    82: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    85: athrow         
                    //    86: aload_2        
                    //    87: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
                    //    90: ifeq            119
                    //    93: aload_2        
                    //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
                    //    97: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
                    //   102: aload_1        
                    //   103: if_acmpne       119
                    //   106: goto            113
                    //   109: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   112: athrow         
                    //   113: iconst_1       
                    //   114: ireturn        
                    //   115: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   118: athrow         
                    //   119: aload_2        
                    //   120: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
                    //   123: ifne            157
                    //   126: aload_2        
                    //   127: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
                    //   130: ifeq            167
                    //   133: goto            140
                    //   136: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   139: athrow         
                    //   140: aload_2        
                    //   141: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
                    //   144: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.getInstanceofPair:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$InstanceOf;
                    //   147: ifnull          167
                    //   150: goto            157
                    //   153: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   156: athrow         
                    //   157: aload_0        
                    //   158: aload_2        
                    //   159: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.a:(Lcom/intellij/psi/PsiElement;)Z
                    //   162: ireturn        
                    //   163: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   166: athrow         
                    //   167: aload_2        
                    //   168: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
                    //   171: ifne            202
                    //   174: aload_2        
                    //   175: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
                    //   178: ifne            202
                    //   181: goto            188
                    //   184: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   187: athrow         
                    //   188: aload_2        
                    //   189: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
                    //   192: ifeq            208
                    //   195: goto            202
                    //   198: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   201: athrow         
                    //   202: iconst_1       
                    //   203: ireturn        
                    //   204: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   207: athrow         
                    //   208: aload_2        
                    //   209: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
                    //   212: ifeq            258
                    //   215: aload_2        
                    //   216: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
                    //   219: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
                    //   224: astore_3       
                    //   225: aload_3        
                    //   226: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
                    //   229: if_acmpne       238
                    //   232: iconst_1       
                    //   233: ireturn        
                    //   234: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   237: athrow         
                    //   238: aload_3        
                    //   239: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
                    //   242: if_acmpne       255
                    //   245: aload_0        
                    //   246: aload_2        
                    //   247: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.a:(Lcom/intellij/psi/PsiElement;)Z
                    //   250: ireturn        
                    //   251: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   254: athrow         
                    //   255: goto            334
                    //   258: aload_2        
                    //   259: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
                    //   262: ifeq            334
                    //   265: aload_2        
                    //   266: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
                    //   269: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
                    //   274: astore_3       
                    //   275: aload_3        
                    //   276: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
                    //   279: if_acmpeq       324
                    //   282: aload_3        
                    //   283: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
                    //   286: if_acmpeq       324
                    //   289: goto            296
                    //   292: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   295: athrow         
                    //   296: aload_3        
                    //   297: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
                    //   300: if_acmpeq       324
                    //   303: goto            310
                    //   306: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   309: athrow         
                    //   310: aload_3        
                    //   311: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
                    //   314: if_acmpne       334
                    //   317: goto            324
                    //   320: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   323: athrow         
                    //   324: aload_0        
                    //   325: aload_2        
                    //   326: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.a:(Lcom/intellij/psi/PsiElement;)Z
                    //   329: ireturn        
                    //   330: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   333: athrow         
                    //   334: iconst_0       
                    //   335: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                
                    //  -----  -----  -----  -----  ------------------------------------
                    //  7      16     16     20     Ljava/lang/IllegalArgumentException;
                    //  20     40     43     47     Ljava/lang/IllegalArgumentException;
                    //  27     49     49     53     Ljava/lang/IllegalArgumentException;
                    //  53     73     76     80     Ljava/lang/IllegalArgumentException;
                    //  60     82     82     86     Ljava/lang/IllegalArgumentException;
                    //  86     106    109    113    Ljava/lang/IllegalArgumentException;
                    //  93     115    115    119    Ljava/lang/IllegalArgumentException;
                    //  119    133    136    140    Ljava/lang/IllegalArgumentException;
                    //  126    150    153    157    Ljava/lang/IllegalArgumentException;
                    //  140    163    163    167    Ljava/lang/IllegalArgumentException;
                    //  167    181    184    188    Ljava/lang/IllegalArgumentException;
                    //  174    195    198    202    Ljava/lang/IllegalArgumentException;
                    //  188    204    204    208    Ljava/lang/IllegalArgumentException;
                    //  225    234    234    238    Ljava/lang/IllegalArgumentException;
                    //  238    251    251    255    Ljava/lang/IllegalArgumentException;
                    //  275    289    292    296    Ljava/lang/IllegalArgumentException;
                    //  282    303    306    310    Ljava/lang/IllegalArgumentException;
                    //  296    317    320    324    Ljava/lang/IllegalArgumentException;
                    //  310    330    330    334    Ljava/lang/IllegalArgumentException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:425)
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
                
                private static IllegalArgumentException d(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            ocNotUsedValueChecker.process();
            set.addAll(ocNotUsedValueChecker.getNotUsedWrites());
        }
        Pair create;
        try {
            create = Pair.create((Object)set, (Object)ocControlFlowGraph);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils", "getNotUsedWrites"));
            }
        }
        catch (DFAException ex2) {
            throw b(ex2);
        }
        return (Pair<Set<PsiElement>, OCControlFlowGraph>)create;
    }
    
    public static boolean areValuesUsed(@NotNull final List<PsiElement> list, @NotNull final Set<PsiElement> set) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "values", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils", "areValuesUsed"));
            }
        }
        catch (DFAException ex) {
            throw b(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "notUsedValues", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils", "areValuesUsed"));
            }
        }
        catch (DFAException ex2) {
            throw b(ex2);
        }
        for (final PsiElement psiElement : list) {
            try {
                if (!set.contains(psiElement)) {
                    return true;
                }
                continue;
            }
            catch (DFAException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    public static boolean hasGotos(@NotNull final OCCallable ocCallable) {
        try {
            if (ocCallable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils", "hasGotos"));
            }
        }
        catch (DFAException ex) {
            throw b(ex);
        }
        final Ref create = Ref.create((Object)false);
        ocCallable.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            @Override
            public void visitGotoStatement(final OCGotoStatement ocGotoStatement) {
                create.set((Object)true);
            }
        });
        return (boolean)create.get();
    }
    
    private static DFAException b(final DFAException ex) {
        return ex;
    }
    
    public static class DFAException extends RuntimeException
    {
        public DFAException(final String s) {
            super(s);
        }
    }
    
    public static class InstanceOf
    {
        OCReferenceExpression variable;
        OCSymbol symbol;
        OCType type;
        
        public InstanceOf(final OCReferenceExpression variable, final OCSymbol symbol, final OCType type) {
            this.variable = variable;
            this.symbol = symbol;
            this.type = type;
        }
    }
    
    public static class WorkingTimeMeasurer implements Disposable
    {
        private final Alarm myAlarm;
        private volatile boolean myTimedOut;
        
        public WorkingTimeMeasurer(final long n) {
            this.myTimedOut = false;
            (this.myAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, (Disposable)this)).addRequest(() -> this.myTimedOut = true, n);
        }
        
        public boolean isTimeOver() {
            return this.myTimedOut;
        }
        
        public void dispose() {
        }
    }
}
