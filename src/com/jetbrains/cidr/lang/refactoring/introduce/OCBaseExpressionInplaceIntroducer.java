// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.codeInsight.template.impl.TemplateState;
import com.intellij.codeInsight.template.impl.TemplateManagerImpl;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.openapi.util.TextRange;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public abstract class OCBaseExpressionInplaceIntroducer<V extends PsiNameIdentifierOwner, E extends PsiElement> extends OCBaseInplaceIntroducer<V, E>
{
    protected boolean myIsUnresolvedReference;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCBaseExpressionInplaceIntroducer(final Project p0, final Editor p1, final E p2, final List<E> p3, final Class<V> p4, final Class<E> p5, final String p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: aload_3        
        //     4: aload           4
        //     6: aload           5
        //     8: aload           6
        //    10: aload           7
        //    12: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;Ljava/util/List;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)V
        //    15: aload_0        
        //    16: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    19: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    22: ifeq            193
        //    25: aload_0        
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    30: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    33: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    38: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    41: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    48: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    51: ifeq            117
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    58: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    61: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.isStringLiteral:()Z
        //    66: ifeq            117
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    75: athrow         
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    80: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    83: ifeq            117
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: aload_0        
        //    94: aload_0        
        //    95: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    98: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   101: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   104: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   107: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   116: athrow         
        //   117: aload_0        
        //   118: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   121: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   124: ifne            144
        //   127: aload_0        
        //   128: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   131: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   134: ifeq            231
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   143: athrow         
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   148: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   151: iconst_1       
        //   152: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   155: astore          8
        //   157: aload           8
        //   159: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   162: if_acmpeq       190
        //   165: aload_0        
        //   166: aload           8
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   172: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   177: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   180: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   189: athrow         
        //   190: goto            231
        //   193: aload_0        
        //   194: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   197: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   200: ifeq            231
        //   203: aload_0        
        //   204: aload_0        
        //   205: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   208: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   213: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   216: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   221: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   230: athrow         
        //   231: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;TE;Ljava/util/List<TE;>;Ljava/lang/Class<TV;>;Ljava/lang/Class<TE;>;Ljava/lang/String;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  25     69     72     76     Ljava/lang/IllegalStateException;
        //  54     86     89     93     Ljava/lang/IllegalStateException;
        //  76     110    113    117    Ljava/lang/IllegalStateException;
        //  117    137    140    144    Ljava/lang/IllegalStateException;
        //  157    183    186    190    Ljava/lang/IllegalStateException;
        //  193    224    227    231    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    
    protected abstract OCSymbolKind getDeclaratorKind();
    
    @Nullable
    protected String checkType() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     7: ifeq            36
        //    10: aload_0        
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    15: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    22: iconst_1       
        //    23: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpressionType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //    26: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    35: athrow         
        //    36: aload_0        
        //    37: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    40: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    43: ifeq            100
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    50: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    53: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isUnnamed:()Z
        //    56: ifeq            100
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    65: athrow         
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    70: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    73: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //    76: ifnonnull       100
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    85: athrow         
        //    86: aload_0        
        //    87: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    90: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    93: goto            148
        //    96: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    99: athrow         
        //   100: aload_0        
        //   101: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   104: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   107: if_acmpne       117
        //   110: ldc             "Cannot determine type of the selected expression"
        //   112: areturn        
        //   113: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   116: athrow         
        //   117: aload_0        
        //   118: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   121: ifnull          148
        //   124: aload_0        
        //   125: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   128: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   131: ifeq            148
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   140: athrow         
        //   141: ldc             "Cannot perform refactoring. Selected expression has void type."
        //   143: areturn        
        //   144: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   147: athrow         
        //   148: aconst_null    
        //   149: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      29     32     36     Ljava/lang/IllegalStateException;
        //  36     59     62     66     Ljava/lang/IllegalStateException;
        //  46     79     82     86     Ljava/lang/IllegalStateException;
        //  66     96     96     100    Ljava/lang/IllegalStateException;
        //  100    113    113    117    Ljava/lang/IllegalStateException;
        //  117    134    137    141    Ljava/lang/IllegalStateException;
        //  124    144    144    148    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    protected String checkExpression(final E e) {
        Label_0121: {
            Label_0085: {
                Label_0044: {
                    Label_0032: {
                        try {
                            if (!(this.myExpr instanceof OCReferenceExpression)) {
                                break Label_0044;
                            }
                            final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer = this;
                            final PsiElement psiElement = ocBaseExpressionInplaceIntroducer.myExpr;
                            final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)psiElement;
                            final OCSymbol ocSymbol = ocReferenceExpression.resolveToSymbol();
                            if (ocSymbol == null) {
                                break Label_0032;
                            }
                            break Label_0044;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer = this;
                            final PsiElement psiElement = ocBaseExpressionInplaceIntroducer.myExpr;
                            final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)psiElement;
                            final OCSymbol ocSymbol = ocReferenceExpression.resolveToSymbol();
                            if (ocSymbol == null) {
                                this.myIsUnresolvedReference = true;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    try {
                        if (this.myParentSymbol != null) {
                            break Label_0085;
                        }
                        final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer2 = this;
                        final PsiElement psiElement2 = ocBaseExpressionInplaceIntroducer2.myExpr;
                        final boolean b = false;
                        final int n = 1;
                        final Class[] array = new Class[n];
                        final int n2 = 0;
                        final Class<OCBlockStatement> clazz = OCBlockStatement.class;
                        array[n2] = clazz;
                        final PsiElement psiElement3 = PsiTreeUtil.getContextOfType(psiElement2, b, array);
                        if (psiElement3 == null) {
                            return "Selected expression should be inside a block statement";
                        }
                        break Label_0085;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer2 = this;
                    final PsiElement psiElement2 = ocBaseExpressionInplaceIntroducer2.myExpr;
                    final boolean b = false;
                    final int n = 1;
                    final Class[] array = new Class[n];
                    final int n2 = 0;
                    final Class<OCBlockStatement> clazz = OCBlockStatement.class;
                    array[n2] = clazz;
                    final PsiElement psiElement3 = PsiTreeUtil.getContextOfType(psiElement2, b, array);
                    if (psiElement3 == null) {
                        return "Selected expression should be inside a block statement";
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    if (!(e instanceof OCCompoundInitializer)) {
                        break Label_0121;
                    }
                    final PsiElement psiElement4 = e;
                    final OCCompoundInitializer ocCompoundInitializer = (OCCompoundInitializer)psiElement4;
                    final OCFile ocFile = ocCompoundInitializer.getContainingOCFile();
                    final boolean b2 = OCCompilerFeatures.supportsInitializerLists((PsiFile)ocFile);
                    if (!b2) {
                        return "Can't refactor the compound initializer";
                    }
                    break Label_0121;
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            try {
                final PsiElement psiElement4 = e;
                final OCCompoundInitializer ocCompoundInitializer = (OCCompoundInitializer)psiElement4;
                final OCFile ocFile = ocCompoundInitializer.getContainingOCFile();
                final boolean b2 = OCCompilerFeatures.supportsInitializerLists((PsiFile)ocFile);
                if (!b2) {
                    return "Can't refactor the compound initializer";
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        if (OCBaseLocalConvertibleHandler.isDeclaratorIdentifier(this.getMainExpression())) {
            final OCExpression initializer = ((OCDeclarator)this.getMainExpression().getParent()).getInitializer();
            try {
                if (!(initializer instanceof OCCompoundInitializer)) {
                    return this.checkType();
                }
                final OCExpression ocExpression = initializer;
                final OCFile ocFile2 = ocExpression.getContainingOCFile();
                final boolean b3 = OCCompilerFeatures.supportsInitializerLists((PsiFile)ocFile2);
                if (!b3) {
                    return "Can't refactor the compound initializer";
                }
                return this.checkType();
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
            try {
                final OCExpression ocExpression = initializer;
                final OCFile ocFile2 = ocExpression.getContainingOCFile();
                final boolean b3 = OCCompilerFeatures.supportsInitializerLists((PsiFile)ocFile2);
                if (!b3) {
                    return "Can't refactor the compound initializer";
                }
            }
            catch (IllegalStateException ex8) {
                throw a(ex8);
            }
        }
        return this.checkType();
    }
    
    @Override
    protected boolean isCreateFromUsageMode() {
        Label_0021: {
            try {
                if (super.isCreateFromUsageMode()) {
                    break Label_0021;
                }
                final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer = this;
                final boolean b = ocBaseExpressionInplaceIntroducer.myIsUnresolvedReference;
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer = this;
                final boolean b = ocBaseExpressionInplaceIntroducer.myIsUnresolvedReference;
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public PsiElement evaluateAnchor() {
        PsiElement psiElement = PsiTreeUtil.getContextOfType(this.getCommonContext(), false, new Class[] { OCStatement.class });
        if (!OCChangeUtil.canBeReplacedToBlockStatement(psiElement)) {
            psiElement = PsiTreeUtil.getContextOfType(psiElement.getParent(), false, new Class[] { OCStatement.class });
        }
        if (!(psiElement instanceof OCBlockStatement)) {
            psiElement = psiElement.getParent();
        }
        try {
            if (psiElement != null) {
                return this.findAnchor(psiElement);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    protected PsiElement ensureParentIsBlockStatement(final PsiElement psiElement) {
        Label_0032: {
            try {
                if (!(psiElement instanceof OCStatement)) {
                    return psiElement;
                }
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final boolean b = psiElement3 instanceof OCMacroCallArgument;
                if (b) {
                    return psiElement;
                }
                break Label_0032;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final boolean b = psiElement3 instanceof OCMacroCallArgument;
                if (b) {
                    return psiElement;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final TextRange textRange = psiElement.getTextRange();
        final ArrayList<MyPsiPointer> list = new ArrayList<MyPsiPointer>();
        final MyPsiPointer myPsiPointer = new MyPsiPointer(this.myExpr);
        final PsiElement[] myOccurrences = this.myOccurrences;
        for (int length = myOccurrences.length, i = 0; i < length; ++i) {
            list.add(new MyPsiPointer(myOccurrences[i]));
        }
        final OCStatement ensureParentIsBlockStatement = OCChangeUtil.ensureParentIsBlockStatement((OCStatement)psiElement);
        final TextRange textRange2 = ((PsiElement)ensureParentIsBlockStatement).getTextRange();
        if (!textRange.equals((Object)textRange2)) {
            int j = 0;
            try {
                while (j < this.myOccurrences.length) {
                    this.myOccurrences[j] = this.a(this.myOccurrences[j], (MyPsiPointer)list.get(j), textRange, textRange2);
                    ++j;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            this.myOccurrenceMarkers = null;
            this.initOccurrencesMarkers();
            this.myExpr = this.a(this.myExpr, myPsiPointer, textRange, textRange2);
            this.myExprMarker = this.createMarker(this.myExpr);
        }
        return (PsiElement)ensureParentIsBlockStatement;
    }
    
    private E a(final E e, final MyPsiPointer myPsiPointer, final TextRange textRange, final TextRange textRange2) {
        if (textRange.contains(myPsiPointer.range)) {
            final int n = textRange2.getStartOffset() - textRange.getStartOffset();
            myPsiPointer.range = new TextRange(myPsiPointer.range.getStartOffset() + n, myPsiPointer.range.getEndOffset() + n);
            final PsiElement element = myPsiPointer.getElement();
            Label_0078: {
                try {
                    if (OCBaseExpressionInplaceIntroducer.$assertionsDisabled) {
                        return (E)element;
                    }
                    final PsiElement psiElement = element;
                    if (psiElement == null) {
                        break Label_0078;
                    }
                    return (E)element;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = element;
                    if (psiElement == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return (E)element;
        }
        return e;
    }
    
    @Override
    protected String[] suggestNames(final boolean b, @Nullable final V v) {
        try {
            if (this.myUsageName != null) {
                return new String[] { this.myUsageName };
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (this.myExpr instanceof OCExpression) {
            final Collection<String> suggestForExpression = OCNameSuggester.suggestForExpression(this.getDeclaratorKind(), (OCExpression)this.myExpr);
            String[] array = null;
            try {
                if (suggestForExpression.isEmpty()) {
                    array = new String[] { "x" };
                    return array;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final String[] array2 = suggestForExpression.toArray(new String[suggestForExpression.size()]);
            return array;
        }
        try {
            if (this.myExpr instanceof OCReferenceElement) {
                return new String[] { ((OCReferenceElement)this.myExpr).getName() };
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (OCBaseLocalConvertibleHandler.isDeclaratorIdentifier(this.myExpr)) {
                return new String[] { ((OCDeclarator)this.myExpr.getParent()).getName() };
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            assert false : this.myExpr.getClass();
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    protected boolean isAutoAvailable() {
        return OCCompilerFeatures.supportsCxxAutoType(this.myExpr.getContainingFile());
    }
    
    protected boolean isConstAvailable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.isCreateFromUsageMode:()Z
        //     4: ifeq            93
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    11: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    16: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //    19: ifeq            93
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    28: athrow         
        //    29: aload_0        
        //    30: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    33: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    38: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //    41: astore_1       
        //    42: aload_1        
        //    43: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    46: astore_2       
        //    47: aload_1        
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.isGetAddress:()Z
        //    53: ifeq            93
        //    56: aload_2        
        //    57: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    60: ifeq            93
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    69: athrow         
        //    70: aload_2        
        //    71: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //    77: ifne            93
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    86: athrow         
        //    87: iconst_0       
        //    88: ireturn        
        //    89: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    97: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //   100: ifne            166
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   107: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   110: ifeq            166
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   119: athrow         
        //   120: aload_0        
        //   121: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   124: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   127: ifeq            166
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   136: athrow         
        //   137: aload_0        
        //   138: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   141: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   144: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   147: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //   150: ifne            166
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   159: athrow         
        //   160: iconst_0       
        //   161: ireturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   165: athrow         
        //   166: aload_0        
        //   167: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.hasNonConstUsages:()Z
        //   170: ifne            181
        //   173: iconst_1       
        //   174: goto            182
        //   177: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   180: athrow         
        //   181: iconst_0       
        //   182: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      22     25     29     Ljava/lang/IllegalStateException;
        //  47     63     66     70     Ljava/lang/IllegalStateException;
        //  56     80     83     87     Ljava/lang/IllegalStateException;
        //  70     89     89     93     Ljava/lang/IllegalStateException;
        //  93     113    116    120    Ljava/lang/IllegalStateException;
        //  103    130    133    137    Ljava/lang/IllegalStateException;
        //  120    153    156    160    Ljava/lang/IllegalStateException;
        //  137    162    162    166    Ljava/lang/IllegalStateException;
        //  166    177    177    181    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    protected OCDeclarator rebuildDeclarator(OCDeclarator rebuild, final Rebuilder rebuilder) {
        final TemplateState templateState = TemplateManagerImpl.getTemplateState(this.myEditor);
        try {
            this.myCaretRangeMarker.setGreedyToLeft(false);
            this.myCaretRangeMarker.setGreedyToRight(false);
            if (templateState != null) {
                templateState.setSegmentsGreedy(false);
                templateState.setTabStopHighlightersGreedy(false);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        rebuild = rebuilder.rebuild(rebuild);
        try {
            if (templateState != null) {
                templateState.setSegmentsGreedy(true);
                templateState.setTabStopHighlightersGreedy(true);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        this.myCaretRangeMarker.setGreedyToLeft(true);
        this.myCaretRangeMarker.setGreedyToRight(true);
        return rebuild;
    }
    
    @NotNull
    protected OCType getType(final PsiElement psiElement, final boolean b, final boolean b2) {
        Label_0067: {
            OCType ocType = null;
            Label_0032: {
                try {
                    if (!b) {
                        break Label_0067;
                    }
                    final String s = "auto";
                    final OCReferenceType ocReferenceType = OCReferenceType.fromText(s);
                    final boolean b3 = b2;
                    final boolean b4 = false;
                    final CVQualifiers cvQualifiers = CVQualifiers.get(b3, b4);
                    final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer = this;
                    final Project project = ocBaseExpressionInplaceIntroducer.myProject;
                    ocType = ocReferenceType.cloneWithCVQualifiers(cvQualifiers, project);
                    if (ocType == null) {
                        break Label_0032;
                    }
                    return ocType;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final String s = "auto";
                    final OCReferenceType ocReferenceType = OCReferenceType.fromText(s);
                    final boolean b3 = b2;
                    final boolean b4 = false;
                    final CVQualifiers cvQualifiers = CVQualifiers.get(b3, b4);
                    final OCBaseExpressionInplaceIntroducer ocBaseExpressionInplaceIntroducer = this;
                    final Project project = ocBaseExpressionInplaceIntroducer.myProject;
                    ocType = ocReferenceType.cloneWithCVQualifiers(cvQualifiers, project);
                    if (ocType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer", "getType"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return ocType;
        }
        OCType ocType2 = this.myExprType;
        if (psiElement != null) {
            ocType2 = OCTypeUtils.getExtractExpressionType(ocType2, psiElement, false);
        }
        if (ocType2 instanceof OCCppReferenceType) {
            final OCType refType = ((OCCppReferenceType)ocType2).getRefType();
            boolean b6 = false;
            Label_0141: {
                Label_0132: {
                    try {
                        if (!(psiElement instanceof OCExpression)) {
                            break Label_0132;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final OCExpression ocExpression = (OCExpression)psiElement2;
                        final OCExprValueCategory ocExprValueCategory = OCExprValueCategory.classify(ocExpression);
                        final boolean b5 = ocExprValueCategory.isLValue();
                        if (!b5) {
                            break Label_0132;
                        }
                        break Label_0132;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final OCExpression ocExpression = (OCExpression)psiElement2;
                        final OCExprValueCategory ocExprValueCategory = OCExprValueCategory.classify(ocExpression);
                        final boolean b5 = ocExprValueCategory.isLValue();
                        if (!b5) {
                            b6 = true;
                            break Label_0141;
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
                b6 = false;
            }
            final boolean b7 = b2 | b6;
            OCCppReferenceType to;
            try {
                to = OCCppReferenceType.to(refType.cloneWithCVQualifiers(CVQualifiers.get(b7, refType.isVolatile()), this.myProject));
                if (to == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer", "getType"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            return to;
        }
        OCType cloneWithCVQualifiers;
        try {
            cloneWithCVQualifiers = this.myExprType.cloneWithCVQualifiers(CVQualifiers.get(b2, this.myExprType.isVolatile()), this.myProject);
            if (cloneWithCVQualifiers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer", "getType"));
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        return cloneWithCVQualifiers;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCBaseExpressionInplaceIntroducer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    protected interface Rebuilder
    {
        OCDeclarator rebuild(final OCDeclarator p0);
    }
}
