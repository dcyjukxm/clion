// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.psi.impl.OCAsmStatementPartImpl;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import java.util.Iterator;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

private class MyParentVisitor extends OCVisitor
{
    private PsiElement myElement;
    private OCSymbol mySymbol;
    private boolean myWasQualifierOrIndex;
    private boolean myGenerated;
    private boolean myDereference;
    final /* synthetic */ OCControlFlowBuilder this$0;
    
    private MyParentVisitor(@NotNull final PsiElement myElement, final OCSymbol mySymbol, final boolean myWasQualifierOrIndex) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "<init>"));
        }
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "<init>"));
        }
        this.myElement = myElement;
        this.mySymbol = mySymbol;
        this.myWasQualifierOrIndex = myWasQualifierOrIndex;
    }
    
    public boolean isGenerated() {
        return this.myGenerated;
    }
    
    private boolean a() {
        return this.myDereference;
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        try {
            if (this.a(ocAssignmentExpression, OCOperatorReference.OperatorPlacement.INFIX)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        boolean b = false;
        Label_0037: {
            try {
                if (ocAssignmentExpression.getOperationSign() != OCTokenTypes.EQ) {
                    b = true;
                    break Label_0037;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        final boolean b2 = b;
        final OCExpression receiverExpression = ocAssignmentExpression.getReceiverExpression();
        final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
        try {
            if (PsiTreeUtil.isAncestor((PsiElement)receiverExpression, this.myElement, false)) {
                this.a(receiverExpression, sourceExpression, b2, ocAssignmentExpression.getContainingFile());
                this.myGenerated = true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    private void a(@Nullable final OCExpression ocExpression, @Nullable final OCExpression ocExpression2, final boolean b, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "generateAssignmentInstructions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                OCControlFlowBuilder.this.myGraph.addInstruction(OCInstruction.InstructionKind.READ, this.myElement, this.mySymbol);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0128: {
            OCControlFlowBuilder ocControlFlowBuilder = null;
            OCSymbol ocSymbol = null;
            OCExpression ocExpression3 = null;
            OCExpression ocExpression4 = null;
            boolean b2 = false;
            boolean b3 = false;
            Label_0122: {
                Label_0113: {
                    try {
                        if (this.myWasQualifierOrIndex) {
                            break Label_0128;
                        }
                        final MyParentVisitor myParentVisitor = this;
                        ocControlFlowBuilder = myParentVisitor.this$0;
                        final MyParentVisitor myParentVisitor2 = this;
                        ocSymbol = myParentVisitor2.mySymbol;
                        ocExpression3 = ocExpression;
                        ocExpression4 = ocExpression2;
                        b2 = b;
                        final MyParentVisitor myParentVisitor3 = this;
                        final OCControlFlowBuilder ocControlFlowBuilder2 = myParentVisitor3.this$0;
                        final int n = OCControlFlowBuilder.access$300(ocControlFlowBuilder2);
                        if (n != 0) {
                            break Label_0113;
                        }
                        break Label_0113;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final MyParentVisitor myParentVisitor = this;
                        ocControlFlowBuilder = myParentVisitor.this$0;
                        final MyParentVisitor myParentVisitor2 = this;
                        ocSymbol = myParentVisitor2.mySymbol;
                        ocExpression3 = ocExpression;
                        ocExpression4 = ocExpression2;
                        b2 = b;
                        final MyParentVisitor myParentVisitor3 = this;
                        final OCControlFlowBuilder ocControlFlowBuilder2 = myParentVisitor3.this$0;
                        final int n = OCControlFlowBuilder.access$300(ocControlFlowBuilder2);
                        if (n != 0) {
                            b3 = true;
                            break Label_0122;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                b3 = false;
            }
            ocControlFlowBuilder.processAssignment(ocSymbol, (PsiElement)ocExpression3, ocExpression4, b2, b3);
            return;
            try {
                if (this.mySymbol.getType().resolve(psiFile) instanceof OCStructType) {
                    OCControlFlowBuilder.this.myGraph.addInstruction(OCInstruction.InstructionKind.WRITE, (PsiElement)ocExpression, (PsiElement)ocExpression2, this.mySymbol);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
    }
    
    private void a(@Nullable final OCFunctionSymbol p0, @Nullable final OCOperatorReference.OperatorPlacement p1, @Nullable final OCFunctionType p2, final List<? extends OCTypeOwner> p3, @NotNull final OCResolveContext p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           5
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "processFunctionCall"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //    48: aload           4
        //    50: ifnonnull       58
        //    53: return         
        //    54: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_1        
        //    59: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //    62: ifeq            117
        //    65: aload_1        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //    69: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
        //    72: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    77: astore          6
        //    79: aload           6
        //    81: invokeinterface java/util/Iterator.hasNext:()Z
        //    86: ifeq            116
        //    89: aload           6
        //    91: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    96: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    99: astore          7
        //   101: aload_0        
        //   102: aload           7
        //   104: aload_2        
        //   105: aload_3        
        //   106: aload           4
        //   108: aload           5
        //   110: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   113: goto            79
        //   116: return         
        //   117: aload_1        
        //   118: ifnull          134
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload           5
        //   125: aconst_null    
        //   126: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.getParameterTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Ljava/util/List;
        //   129: astore          6
        //   131: goto            152
        //   134: aload_3        
        //   135: ifnull          147
        //   138: aload_3        
        //   139: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   142: astore          6
        //   144: goto            152
        //   147: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   150: astore          6
        //   152: iconst_m1      
        //   153: istore          7
        //   155: iconst_0       
        //   156: istore          8
        //   158: iload           8
        //   160: aload           4
        //   162: invokeinterface java/util/List.size:()I
        //   167: if_icmpge       228
        //   170: aload           4
        //   172: iload           8
        //   174: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   179: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   182: astore          9
        //   184: aload           9
        //   186: instanceof      Lcom/intellij/psi/PsiElement;
        //   189: ifeq            222
        //   192: aload           9
        //   194: checkcast       Lcom/intellij/psi/PsiElement;
        //   197: aload_0        
        //   198: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
        //   201: iconst_0       
        //   202: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   205: ifeq            222
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: iload           8
        //   217: istore          7
        //   219: goto            228
        //   222: iinc            8, 1
        //   225: goto            158
        //   228: iload           7
        //   230: iconst_m1      
        //   231: if_icmpeq       253
        //   234: iload           7
        //   236: aload           6
        //   238: invokeinterface java/util/List.size:()I
        //   243: if_icmplt       258
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: return         
        //   254: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: aload           6
        //   260: iload           7
        //   262: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   267: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   270: astore          8
        //   272: aload           8
        //   274: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   277: ifeq            298
        //   280: aload           8
        //   282: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   285: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //   288: ifeq            346
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: aload_1        
        //   299: ifnull          373
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: iload           7
        //   311: ifne            373
        //   314: goto            321
        //   317: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   320: athrow         
        //   321: aload_1        
        //   322: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   325: dup            
        //   326: aload_0        
        //   327: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
        //   330: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   333: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppMemberOperator:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   336: ifeq            373
        //   339: goto            346
        //   342: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: aload_0        
        //   347: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
        //   350: aload_0        
        //   351: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
        //   354: aload_0        
        //   355: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   358: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processReference:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   361: aload_0        
        //   362: iconst_1       
        //   363: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List<+Lcom/jetbrains/cidr/lang/types/OCTypeOwner;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     54     54     58     Ljava/lang/IllegalArgumentException;
        //  184    208    211    215    Ljava/lang/IllegalArgumentException;
        //  228    246    249    253    Ljava/lang/IllegalArgumentException;
        //  234    254    254    258    Ljava/lang/IllegalArgumentException;
        //  272    291    294    298    Ljava/lang/IllegalArgumentException;
        //  280    302    305    309    Ljava/lang/IllegalArgumentException;
        //  298    314    317    321    Ljava/lang/IllegalArgumentException;
        //  309    339    342    346    Ljava/lang/IllegalArgumentException;
        //  321    366    369    373    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0298:
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
    
    private boolean a(final OCExpression ocExpression, @Nullable final OCOperatorReference.OperatorPlacement operatorPlacement) {
        boolean b = false;
        final PsiReference reference = ocExpression.getReference();
        if (reference instanceof OCOperatorReference) {
            for (final OCSymbol ocSymbol : ((OCOperatorReference)reference).resolveToSymbols()) {
                if (ocSymbol instanceof OCFunctionSymbol) {
                    b = true;
                    this.a((OCFunctionSymbol)ocSymbol, operatorPlacement, null, ((OCOperatorReference)reference).getArgumentExpressions(), new OCResolveContext((PsiElement)ocExpression));
                }
            }
        }
        return b;
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        try {
            if (this.a(ocCallExpression, OCOperatorReference.OperatorPlacement.POSTFIX)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCType terminalType = ocCallExpression.getFunctionReferenceExpression().getResolvedType().getTerminalType();
        if (terminalType instanceof OCFunctionType) {
            final OCFunctionType ocFunctionType = (OCFunctionType)terminalType;
            final OCArgumentList argumentList = ocCallExpression.getArgumentList();
            this.a(null, null, ocFunctionType, argumentList.getArguments(), new OCResolveContext((PsiElement)argumentList));
        }
    }
    
    @Override
    public void visitArgumentList(final OCArgumentList list) {
        this.a((PsiElement)list);
    }
    
    @Override
    public void visitMessageArgument(final OCMessageArgument p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //     9: astore_2       
        //    10: aload_2        
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //    16: aload_1        
        //    17: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //    22: istore_3       
        //    23: aload_2        
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //    29: astore          4
        //    31: aload           4
        //    33: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getAllResponders:()Ljava/util/List;
        //    36: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    41: astore          5
        //    43: aload           5
        //    45: invokeinterface java/util/Iterator.hasNext:()Z
        //    50: ifeq            212
        //    53: aload           5
        //    55: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    60: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    63: astore          6
        //    65: aload           6
        //    67: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //    72: invokeinterface java/util/List.size:()I
        //    77: iload_3        
        //    78: if_icmpgt       88
        //    81: goto            43
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           6
        //    90: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //    95: iload_3        
        //    96: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   101: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   104: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   109: astore          7
        //   111: aload           7
        //   113: ifnull          209
        //   116: aload           7
        //   118: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isPassByReference:()Z
        //   121: ifne            170
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload           7
        //   133: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   136: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   139: ifeq            209
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload           7
        //   151: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   154: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   157: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //   160: ifne            209
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_1        
        //   171: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   176: astore          8
        //   178: aload           8
        //   180: ifnull          203
        //   183: aload_0        
        //   184: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
        //   187: aload           8
        //   189: aload_0        
        //   190: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   193: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processReference:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aload_0        
        //   204: iconst_1       
        //   205: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
        //   208: return         
        //   209: goto            43
        //   212: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  65     84     84     88     Ljava/lang/IllegalArgumentException;
        //  111    124    127    131    Ljava/lang/IllegalArgumentException;
        //  116    142    145    149    Ljava/lang/IllegalArgumentException;
        //  131    163    166    170    Ljava/lang/IllegalArgumentException;
        //  178    196    199    203    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        OCElement ocElement = ocForeachStatement.getVariableExpression();
        if (ocElement instanceof OCExpressionStatement) {
            ocElement = ((OCExpressionStatement)ocElement).getExpression();
        }
        try {
            if (this.myElement == ocElement) {
                OCControlFlowBuilder.this.processAssignment(this.mySymbol, this.myElement, null, true);
                this.myGenerated = true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitDeclarationStatement(final OCDeclarationStatement ocDeclarationStatement) {
        try {
            if (ocDeclarationStatement.getParent() instanceof OCForeachStatement) {
                this.visitForeachStatement((OCForeachStatement)ocDeclarationStatement.getParent());
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myGenerated = true;
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        final OCType resolvedType = ocDeclarator.getResolvedType();
        if (resolvedType instanceof OCCppReferenceType) {
            final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)resolvedType;
            try {
                if (!ocCppReferenceType.isReferenceToConst()) {
                    OCControlFlowBuilder.this.processReference(this.myElement, this.mySymbol);
                    this.myGenerated = true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        else if (resolvedType instanceof OCStructType) {
            final OCFunctionSymbol resolveCtorCall = OCResolveUtil.resolveCtorCall(ocDeclarator);
            if (resolveCtorCall != null) {
                this.a(resolveCtorCall, null, null, ocDeclarator.getInitializers(), new OCResolveContext((PsiElement)ocDeclarator));
            }
        }
    }
    
    @Override
    public void visitExpressionStatement(final OCExpressionStatement ocExpressionStatement) {
        try {
            if (ocExpressionStatement.getParent() instanceof OCForeachStatement) {
                this.visitForeachStatement((OCForeachStatement)ocExpressionStatement.getParent());
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myGenerated = true;
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.POSTFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
        //     8: ifne            107
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    17: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    20: if_acmpeq       49
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_1        
        //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    39: if_acmpne       107
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
        //    53: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    56: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    67: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addInstruction:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
        //    70: pop            
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
        //    75: aload_0        
        //    76: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    79: aload_1        
        //    80: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    85: aload_1        
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    91: iconst_1       
        //    92: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processAssignment:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)V
        //    95: aload_0        
        //    96: iconst_1       
        //    97: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      23     26     30     Ljava/lang/IllegalArgumentException;
        //  11     42     45     49     Ljava/lang/IllegalArgumentException;
        //  30     100    103    107    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        Label_0030: {
            try {
                if (this.a(ocPrefixExpression, OCOperatorReference.OperatorPlacement.PREFIX)) {
                    return;
                }
                final OCPrefixExpression ocPrefixExpression2 = ocPrefixExpression;
                final OCElementType ocElementType = ocPrefixExpression2.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.PLUSPLUS;
                if (ocElementType != ocPunctuatorElementType) {
                    break Label_0030;
                }
                break Label_0030;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCPrefixExpression ocPrefixExpression2 = ocPrefixExpression;
                final OCElementType ocElementType = ocPrefixExpression2.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.PLUSPLUS;
                if (ocElementType != ocPunctuatorElementType) {
                    if (ocPrefixExpression.getOperationSign() != OCTokenTypes.MINUSMINUS) {
                        return;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        OCControlFlowBuilder.this.myGraph.addInstruction(OCInstruction.InstructionKind.READ, this.myElement, this.mySymbol);
        OCControlFlowBuilder.this.processAssignment(this.mySymbol, (PsiElement)ocPrefixExpression.getOperand(), ocPrefixExpression.getOperand(), true);
        final PsiElement parent = OCParenthesesUtils.topmostParenthesized(ocPrefixExpression).getParent();
        try {
            if (parent != null) {
                parent.accept((PsiElementVisitor)this);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        this.a(ocBinaryExpression, OCOperatorReference.OperatorPlacement.INFIX);
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.PREFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
        //     8: ifeq            16
        //    11: return         
        //    12: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: aload_1        
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    22: astore_2       
        //    23: aload_2        
        //    24: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    27: if_acmpeq       58
        //    30: aload_2        
        //    31: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__IMAG__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    34: if_acmpeq       58
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__REAL__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    48: if_acmpne       82
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
        //    62: aload_1        
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    67: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processReference:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //    70: aload_0        
        //    71: iconst_1       
        //    72: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
        //    75: goto            128
        //    78: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_2        
        //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    86: if_acmpne       128
        //    89: aload_1        
        //    90: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    98: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //   101: ifeq            123
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_0        
        //   112: iconst_1       
        //   113: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
        //   116: goto            128
        //   119: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_0        
        //   124: iconst_1       
        //   125: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myDereference:Z
        //   128: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      12     12     16     Ljava/lang/IllegalArgumentException;
        //  23     37     40     44     Ljava/lang/IllegalArgumentException;
        //  30     51     54     58     Ljava/lang/IllegalArgumentException;
        //  44     78     78     82     Ljava/lang/IllegalArgumentException;
        //  82     104    107    111    Ljava/lang/IllegalArgumentException;
        //  89     119    119    123    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
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
    public void visitQualifiedExpression(final OCQualifiedExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.INFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
        //     8: ifne            86
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
        //    21: if_acmpne       86
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    37: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    40: if_acmpne       74
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_1        
        //    51: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    56: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    61: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    64: ifne            86
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload_0        
        //    75: iconst_1       
        //    76: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myDereference:Z
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      24     27     31     Ljava/lang/IllegalArgumentException;
        //  11     43     46     50     Ljava/lang/IllegalArgumentException;
        //  31     67     70     74     Ljava/lang/IllegalArgumentException;
        //  50     79     82     86     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    public void visitArraySelectionExpression(final OCArraySelectionExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.POSTFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
        //     8: ifne            67
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCArraySelectionExpression.getArrayExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
        //    21: if_acmpne       67
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: invokeinterface com/jetbrains/cidr/lang/psi/OCArraySelectionExpression.getArrayExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    37: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    45: ifne            67
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: iconst_1       
        //    57: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myDereference:Z
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      24     27     31     Ljava/lang/IllegalArgumentException;
        //  11     48     51     55     Ljava/lang/IllegalArgumentException;
        //  31     60     63     67     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        try {
            if (!PsiTreeUtil.isAncestor((PsiElement)ocConditionalExpression.getCondition(), this.myElement, false)) {
                this.a((PsiElement)ocConditionalExpression);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
        this.a((PsiElement)ocParenthesizedExpression);
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        final OCType castType = ocCastExpression.getCastType();
        Label_0031: {
            try {
                if (!(castType instanceof OCCppReferenceType)) {
                    return;
                }
                final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)castType;
                final OCCppReferenceType ocCppReferenceType2 = ocCppReferenceType;
                final boolean b = ocCppReferenceType2.isReferenceToConst();
                if (!b) {
                    break Label_0031;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)castType;
                final OCCppReferenceType ocCppReferenceType2 = ocCppReferenceType;
                final boolean b = ocCppReferenceType2.isReferenceToConst();
                if (!b) {
                    this.a((PsiElement)ocCastExpression);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
        this.a((PsiElement)ocCompoundInitializer);
    }
    
    @Override
    public void visitAsmStatementPart(final OCAsmStatementPartImpl ocAsmStatementPartImpl) {
        final OCExpression expression = ocAsmStatementPartImpl.getExpression();
        Label_0023: {
            try {
                if (!ocAsmStatementPartImpl.isOutputParametersPart()) {
                    return;
                }
                final OCExpression ocExpression = expression;
                if (ocExpression != null) {
                    break Label_0023;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression = expression;
                if (ocExpression != null) {
                    this.a(expression, null, false, ocAsmStatementPartImpl.getContainingFile());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    private void a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "visitParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement parent = psiElement.getParent();
        try {
            if (parent != null) {
                parent.accept((PsiElementVisitor)this);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
