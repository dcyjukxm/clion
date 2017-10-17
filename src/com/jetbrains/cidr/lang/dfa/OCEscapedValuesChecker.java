// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import com.intellij.psi.PsiElement;
import java.util.List;

class OCEscapedValuesChecker extends OCMultiSymbolAlgorithm
{
    OCEscapedValuesChecker(final OCControlFlowGraph ocControlFlowGraph) {
        super(ocControlFlowGraph);
    }
    
    @Override
    protected boolean processClosureSymbols() {
        return false;
    }
    
    @NotNull
    public List<PsiElement> getEscapedVariables() {
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        final Iterator<Pair<OCSymbol, PsiElement>> iterator = this.getReachableElements().iterator();
        while (iterator.hasNext()) {
            list.add((PsiElement)iterator.next().getSecond());
        }
        ArrayList<PsiElement> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker", "getEscapedVariables"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return list2;
    }
    
    @Override
    protected boolean isGoodWrite(@Nullable PsiElement diveIntoParenthesesAndCasts) {
        diveIntoParenthesesAndCasts = OCParenthesesUtils.diveIntoParenthesesAndCasts(diveIntoParenthesesAndCasts);
        if (!(diveIntoParenthesesAndCasts instanceof OCUnaryExpression)) {
            Label_0069: {
                try {
                    if (!(diveIntoParenthesesAndCasts instanceof OCBlockExpression)) {
                        return false;
                    }
                    final PsiElement psiElement = diveIntoParenthesesAndCasts;
                    final PsiFile psiFile = psiElement.getContainingFile();
                    final boolean b = OCCompilerFeatures.isArcEnabled(psiFile);
                    if (!b) {
                        break Label_0069;
                    }
                    return false;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = diveIntoParenthesesAndCasts;
                    final PsiFile psiFile = psiElement.getContainingFile();
                    final boolean b = OCCompilerFeatures.isArcEnabled(psiFile);
                    if (!b) {
                        return true;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)diveIntoParenthesesAndCasts;
        try {
            if (ocUnaryExpression.isGetAddress()) {
                return a(ocUnaryExpression.getOperand());
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    private static boolean a(final OCExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          194
        //     4: aload_0        
        //     5: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //     8: ifeq            53
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    17: athrow         
        //    18: aload_0        
        //    19: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    22: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    27: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    30: if_acmpne       53
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    39: athrow         
        //    40: aload_0        
        //    41: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    44: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    49: astore_0       
        //    50: goto            4
        //    53: new             Lcom/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor;
        //    56: dup            
        //    57: invokespecial   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.<init>:()V
        //    60: astore_1       
        //    61: aload_0        
        //    62: aload_1        
        //    63: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //    68: aload_1        
        //    69: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    72: astore_2       
        //    73: aload_2        
        //    74: ifnull          194
        //    77: aload_1        
        //    78: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getNumOfDereferences:()I
        //    81: ifne            194
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    90: athrow         
        //    91: aload_2        
        //    92: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    97: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //   100: ifeq            194
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   109: athrow         
        //   110: aload_2        
        //   111: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   114: ifeq            141
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   123: athrow         
        //   124: aload_2        
        //   125: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   128: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   131: ifne            194
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   140: athrow         
        //   141: aload_2        
        //   142: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   147: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   150: ifeq            192
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   159: athrow         
        //   160: aload_2        
        //   161: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   166: astore_3       
        //   167: aload_3        
        //   168: ifnull          187
        //   171: aload_3        
        //   172: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   175: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   180: goto            188
        //   183: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   186: athrow         
        //   187: aconst_null    
        //   188: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   191: ireturn        
        //   192: iconst_1       
        //   193: ireturn        
        //   194: iconst_0       
        //   195: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      11     14     18     Ljava/lang/IllegalStateException;
        //  4      33     36     40     Ljava/lang/IllegalStateException;
        //  73     84     87     91     Ljava/lang/IllegalStateException;
        //  77     103    106    110    Ljava/lang/IllegalStateException;
        //  91     117    120    124    Ljava/lang/IllegalStateException;
        //  110    134    137    141    Ljava/lang/IllegalStateException;
        //  124    153    156    160    Ljava/lang/IllegalStateException;
        //  167    183    183    187    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0004:
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
    protected boolean treatReferencesAsStartInstructions() {
        return false;
    }
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker", "isEndInstruction"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0070: {
            try {
                if (!super.isEndInstruction(ocInstruction)) {
                    return false;
                }
                final OCInstruction ocInstruction2 = ocInstruction;
                final PsiElement psiElement = ocInstruction2.getRValue();
                final TextRange textRange = null;
                final boolean b = a(psiElement, textRange);
                if (b) {
                    break Label_0070;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCInstruction ocInstruction2 = ocInstruction;
                final PsiElement psiElement = ocInstruction2.getRValue();
                final TextRange textRange = null;
                final boolean b = a(psiElement, textRange);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    public List<PsiElement> getEscapedObjects() {
        final ArrayList list = new ArrayList<PsiElement>();
        final PsiElement codeFragment = this.myCfg.getCodeFragment();
        ArrayList list2;
        try {
            codeFragment.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
                    if (ocBlockExpression != codeFragment && OCEscapedValuesChecker.this.isGoodWrite((PsiElement)ocBlockExpression)) {
                        final PsiElement psiElement = (PsiElement)ContainerUtil.getFirstItem((List)OCCodeInsightUtil.getScopeAndKind((PsiElement)ocBlockExpression).getFirst());
                        if (psiElement != null && a((PsiElement)ocBlockExpression, psiElement.getTextRange())) {
                            list.add(ocBlockExpression);
                        }
                    }
                }
                
                @Override
                public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
                    final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
                    if (resolveToSymbol instanceof OCDeclaratorSymbol && resolveToSymbol.getKind() == OCSymbolKind.LOCAL_VARIABLE && resolveToSymbol.getResolvedType() instanceof OCArrayType && !((OCDeclaratorSymbol)resolveToSymbol).isFriendOrStatic() && a((PsiElement)ocReferenceExpression, null)) {
                        list.add(ocReferenceExpression);
                    }
                }
                
                @Override
                public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
                    super.visitUnaryExpression(ocUnaryExpression);
                    if (OCEscapedValuesChecker.this.isGoodWrite((PsiElement)ocUnaryExpression) && a((PsiElement)ocUnaryExpression, null)) {
                        list.add(ocUnaryExpression);
                    }
                }
            });
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker", "getEscapedObjects"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (List<PsiElement>)list2;
    }
    
    private static boolean a(@Nullable final PsiElement p0, @Nullable final TextRange p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: bipush          10
        //     3: anewarray       Ljava/lang/Class;
        //     6: dup            
        //     7: iconst_0       
        //     8: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallExpression;.class
        //    10: aastore        
        //    11: dup            
        //    12: iconst_1       
        //    13: ldc             Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;.class
        //    15: aastore        
        //    16: dup            
        //    17: iconst_2       
        //    18: ldc             Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;.class
        //    20: aastore        
        //    21: dup            
        //    22: iconst_3       
        //    23: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //    25: aastore        
        //    26: dup            
        //    27: iconst_4       
        //    28: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCallArgument;.class
        //    30: aastore        
        //    31: dup            
        //    32: iconst_5       
        //    33: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //    35: aastore        
        //    36: dup            
        //    37: bipush          6
        //    39: ldc             Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;.class
        //    41: aastore        
        //    42: dup            
        //    43: bipush          7
        //    45: ldc             Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;.class
        //    47: aastore        
        //    48: dup            
        //    49: bipush          8
        //    51: ldc             Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;.class
        //    53: aastore        
        //    54: dup            
        //    55: bipush          9
        //    57: ldc             Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;.class
        //    59: aastore        
        //    60: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    63: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    66: astore_2       
        //    67: aload_2        
        //    68: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    71: ifeq            216
        //    74: new             Lcom/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor;
        //    77: dup            
        //    78: invokespecial   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.<init>:()V
        //    81: astore_3       
        //    82: aload_2        
        //    83: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    91: aload_3        
        //    92: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //    97: aload_3        
        //    98: invokevirtual   com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   101: astore          4
        //   103: aload           4
        //   105: ifnull          213
        //   108: aload           4
        //   110: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   113: ifeq            152
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   122: athrow         
        //   123: aload           4
        //   125: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   128: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   131: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   136: ifeq            152
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   145: athrow         
        //   146: iconst_0       
        //   147: ireturn        
        //   148: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   151: athrow         
        //   152: aload_1        
        //   153: ifnull          183
        //   156: aload_1        
        //   157: aload           4
        //   159: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   164: invokevirtual   com/intellij/openapi/util/TextRange.equals:(Ljava/lang/Object;)Z
        //   167: ifne            183
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   176: athrow         
        //   177: iconst_1       
        //   178: ireturn        
        //   179: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   182: athrow         
        //   183: aload_1        
        //   184: ifnonnull       213
        //   187: aload           4
        //   189: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   194: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //   197: ifne            213
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   206: athrow         
        //   207: iconst_1       
        //   208: ireturn        
        //   209: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   212: athrow         
        //   213: goto            229
        //   216: aload_2        
        //   217: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   220: ifeq            229
        //   223: iconst_1       
        //   224: ireturn        
        //   225: invokestatic    com/jetbrains/cidr/lang/dfa/OCEscapedValuesChecker.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   228: athrow         
        //   229: iconst_0       
        //   230: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  103    116    119    123    Ljava/lang/IllegalStateException;
        //  108    139    142    146    Ljava/lang/IllegalStateException;
        //  123    148    148    152    Ljava/lang/IllegalStateException;
        //  152    170    173    177    Ljava/lang/IllegalStateException;
        //  156    179    179    183    Ljava/lang/IllegalStateException;
        //  183    200    203    207    Ljava/lang/IllegalStateException;
        //  187    209    209    213    Ljava/lang/IllegalStateException;
        //  216    225    225    229    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0123:
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
