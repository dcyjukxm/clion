// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.psi.OCGenericSelectionAssociation;
import com.jetbrains.cidr.lang.psi.OCGenericSelectionExpression;
import com.jetbrains.cidr.lang.psi.OCBoxedExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentsOwner;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.OCAnnotatingArgumentsChecker;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbols;
import java.util.Collection;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.refactoring.OCImportsOptimizer;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbolsCache;
import com.jetbrains.cidr.lang.psi.OCProtocolExpression;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCQualifiedDesignator;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCAttributesList;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.quickfixes.OCCreateNewDefinitionIntentionAction;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCNSDictionaryLiteral;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.psi.OCNSArrayLiteral;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.impl.OCDefineDirectiveImpl;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCTryStatement;
import com.jetbrains.cidr.lang.daemon.clang.OCClangMessageFinder;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.jetbrains.cidr.lang.psi.OCThrowExpression;
import com.jetbrains.cidr.lang.psi.OCSynchronizedStatement;
import com.jetbrains.cidr.lang.psi.OCContinueStatement;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCBreakStatement;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class OCErrorAnnotator extends OCAnnotator
{
    @NotNull
    private final OCOperatorsChecker myOperatorsChecker;
    @NotNull
    private final OCCppChecker myCppChecker;
    @NotNull
    private final OCDeclaratorChecker myDeclaratorChecker;
    @NotNull
    private final OCImplementationChecker myImplementationChecker;
    
    public OCErrorAnnotator() {
        this.myCppChecker = new OCCppChecker(this);
        this.myOperatorsChecker = new OCOperatorsChecker(this, this.myCppChecker);
        this.myDeclaratorChecker = new OCDeclaratorChecker(this, this.myCppChecker);
        this.myImplementationChecker = new OCImplementationChecker(this, this.myDeclaratorChecker, this.myOperatorsChecker);
    }
    
    public void visitElement(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitElement:(Lcom/intellij/psi/PsiElement;)V
        //     5: aload_1        
        //     6: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    11: astore_2       
        //    12: aload_2        
        //    13: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //    16: ifeq            81
        //    19: aload_1        
        //    20: aload_2        
        //    21: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    29: if_acmpne       81
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_1        
        //    40: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    45: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    53: ifeq            81
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //    67: aload_2        
        //    68: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //    71: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkClass:(Lcom/jetbrains/cidr/lang/psi/OCStruct;)V
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_2        
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    85: ifeq            108
        //    88: aload_1        
        //    89: aload_2        
        //    90: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    98: if_acmpeq       113
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: return         
        //   109: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_2        
        //   114: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   117: ifeq            159
        //   120: aload_0        
        //   121: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //   124: aload_2        
        //   125: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   128: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Lcom/intellij/psi/PsiNameIdentifierOwner;)Z
        //   131: ifeq            422
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: aload_0        
        //   142: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myImplementationChecker:Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker;
        //   145: aload_2        
        //   146: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   149: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.checkInterfaceDeclaration:(Lcom/jetbrains/cidr/lang/psi/OCInterface;)V
        //   152: goto            422
        //   155: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_2        
        //   160: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   163: ifeq            205
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //   170: aload_2        
        //   171: checkcast       Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   174: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Lcom/intellij/psi/PsiNameIdentifierOwner;)Z
        //   177: ifeq            422
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: aload_0        
        //   188: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myImplementationChecker:Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker;
        //   191: aload_2        
        //   192: checkcast       Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   195: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.checkInterfaceImplementation:(Lcom/jetbrains/cidr/lang/psi/OCImplementation;)V
        //   198: goto            422
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aload_2        
        //   206: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //   209: ifeq            251
        //   212: aload_0        
        //   213: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //   216: aload_2        
        //   217: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //   220: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Lcom/intellij/psi/PsiNameIdentifierOwner;)Z
        //   223: ifeq            422
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myImplementationChecker:Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker;
        //   237: aload_2        
        //   238: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //   241: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.checkProtocolDeclaration:(Lcom/jetbrains/cidr/lang/psi/OCProtocol;)V
        //   244: goto            422
        //   247: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_2        
        //   252: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclaration;
        //   255: ifeq            422
        //   258: aload_2        
        //   259: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassPredeclaration;
        //   262: invokeinterface com/jetbrains/cidr/lang/psi/OCClassPredeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   267: astore_3       
        //   268: aload_3        
        //   269: ifnull          422
        //   272: aload_3        
        //   273: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   278: astore          4
        //   280: aload           4
        //   282: ifnull          298
        //   285: aload           4
        //   287: aconst_null    
        //   288: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   291: goto            422
        //   294: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: aload_1        
        //   299: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   304: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //   307: ifeq            422
        //   310: aload_0        
        //   311: aload_2        
        //   312: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassPredeclaration;
        //   315: invokeinterface com/jetbrains/cidr/lang/psi/OCClassPredeclaration.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   320: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NoClassDefinition;.class
        //   322: ldc             "warn_undef_protocolref"
        //   324: new             Ljava/lang/StringBuilder;
        //   327: dup            
        //   328: invokespecial   java/lang/StringBuilder.<init>:()V
        //   331: ldc             "Can't find "
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: aload_3        
        //   337: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: ldc             " in the project and external files"
        //   347: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   353: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   356: astore          5
        //   358: aload_0        
        //   359: aload           5
        //   361: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   364: dup            
        //   365: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   368: aload_1        
        //   369: aconst_null    
        //   370: aload_3        
        //   371: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   376: aconst_null    
        //   377: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   380: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   383: aload_0        
        //   384: aload           5
        //   386: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //   389: dup            
        //   390: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   393: aload_1        
        //   394: aconst_null    
        //   395: aload_3        
        //   396: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   401: aconst_null    
        //   402: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   405: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   408: aload_0        
        //   409: aload           5
        //   411: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //   414: dup            
        //   415: aload_3        
        //   416: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   419: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   422: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     32     35     39     Ljava/lang/IllegalArgumentException;
        //  19     56     59     63     Ljava/lang/IllegalArgumentException;
        //  39     74     77     81     Ljava/lang/IllegalArgumentException;
        //  81     101    104    108    Ljava/lang/IllegalArgumentException;
        //  88     109    109    113    Ljava/lang/IllegalArgumentException;
        //  113    134    137    141    Ljava/lang/IllegalArgumentException;
        //  120    155    155    159    Ljava/lang/IllegalArgumentException;
        //  159    180    183    187    Ljava/lang/IllegalArgumentException;
        //  166    201    201    205    Ljava/lang/IllegalArgumentException;
        //  205    226    229    233    Ljava/lang/IllegalArgumentException;
        //  212    247    247    251    Ljava/lang/IllegalArgumentException;
        //  280    294    294    298    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
    public void visitReferenceExpression(final OCReferenceExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitReferenceExpression:(Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    11: astore_2       
        //    12: aload_2        
        //    13: astore_3       
        //    14: aload_3        
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //    18: ifeq            31
        //    21: aload_3        
        //    22: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    27: astore_3       
        //    28: goto            14
        //    31: aload_2        
        //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    35: ifeq            58
        //    38: aload_2        
        //    39: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    42: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    47: aload_1        
        //    48: if_acmpeq       171
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_2        
        //    59: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    62: ifeq            92
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_2        
        //    73: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    76: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    81: aload_1        
        //    82: if_acmpeq       171
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_3        
        //    93: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //    96: ifne            171
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload_2        
        //   107: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   110: ifeq            141
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload_1        
        //   121: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   126: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   131: ifne            171
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: aload_2        
        //   142: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //   145: ifne            171
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: aload_1        
        //   156: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.isParameterOfBuiltInTrait:()Z
        //   161: ifeq            179
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: iconst_1       
        //   172: goto            180
        //   175: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: istore          4
        //   182: aload_1        
        //   183: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   188: astore          5
        //   190: aload           5
        //   192: ifnull          389
        //   195: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
        //   198: dup            
        //   199: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
        //   202: aload_1        
        //   203: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   206: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   209: if_acmpne       274
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload_1        
        //   220: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   222: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   225: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   228: astore          6
        //   230: aload           6
        //   232: ifnull          274
        //   235: aload           6
        //   237: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelector:()Ljava/lang/String;
        //   242: ldc             "init"
        //   244: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   247: ifne            274
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: aload_0        
        //   258: aload_1        
        //   259: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //   261: ldc             "err_typecheck_arc_assign_self"
        //   263: ldc             "Assignment to self is allowed only in init methods"
        //   265: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   268: pop            
        //   269: return         
        //   270: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: aload           5
        //   276: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SELF:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   279: if_acmpne       287
        //   282: return         
        //   283: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload_1        
        //   288: iconst_1       
        //   289: anewarray       Ljava/lang/Class;
        //   292: dup            
        //   293: iconst_0       
        //   294: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   296: aastore        
        //   297: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   300: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   303: astore          6
        //   305: aload           6
        //   307: ifnonnull       315
        //   310: return         
        //   311: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: aload           6
        //   317: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   322: astore          7
        //   324: aload           7
        //   326: ifnull          388
        //   329: aload           7
        //   331: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   334: ifnonnull       388
        //   337: goto            344
        //   340: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: aload_0        
        //   345: aload_1        
        //   346: ldc             "CIDR"
        //   348: new             Ljava/lang/StringBuilder;
        //   351: dup            
        //   352: invokespecial   java/lang/StringBuilder.<init>:()V
        //   355: ldc             "Class '"
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: aload           7
        //   362: aload_1        
        //   363: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   369: ldc             "' doesn't have a superclass"
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   377: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   380: pop            
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: return         
        //   389: aload_1        
        //   390: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   395: astore          6
        //   397: aload           6
        //   399: ifnull          526
        //   402: aload           6
        //   404: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   409: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   412: if_acmpne       464
        //   415: goto            422
        //   418: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: aload           6
        //   424: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   427: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   430: aload_1        
        //   431: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   436: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.resolveToSymbols:(Lcom/intellij/psi/PsiFile;)Ljava/util/List;
        //   439: astore          7
        //   441: aload           7
        //   443: invokeinterface java/util/List.size:()I
        //   448: ifeq            464
        //   451: aload           7
        //   453: iconst_0       
        //   454: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   459: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   462: astore          6
        //   464: iload           4
        //   466: ifne            526
        //   469: aload           6
        //   471: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   476: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isExpression:()Z
        //   479: ifne            526
        //   482: goto            489
        //   485: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload           6
        //   491: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   496: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   499: if_acmpeq       526
        //   502: goto            509
        //   505: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   508: athrow         
        //   509: aload_0        
        //   510: aload_1        
        //   511: ldc             "CIDR"
        //   513: ldc             "Expression expected"
        //   515: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   518: pop            
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  31     51     54     58     Ljava/lang/IllegalArgumentException;
        //  38     65     68     72     Ljava/lang/IllegalArgumentException;
        //  58     85     88     92     Ljava/lang/IllegalArgumentException;
        //  72     99     102    106    Ljava/lang/IllegalArgumentException;
        //  92     113    116    120    Ljava/lang/IllegalArgumentException;
        //  106    134    137    141    Ljava/lang/IllegalArgumentException;
        //  120    148    151    155    Ljava/lang/IllegalArgumentException;
        //  141    164    167    171    Ljava/lang/IllegalArgumentException;
        //  155    175    175    179    Ljava/lang/IllegalArgumentException;
        //  190    212    215    219    Ljava/lang/IllegalArgumentException;
        //  230    250    253    257    Ljava/lang/IllegalArgumentException;
        //  235    270    270    274    Ljava/lang/IllegalArgumentException;
        //  274    283    283    287    Ljava/lang/IllegalArgumentException;
        //  305    311    311    315    Ljava/lang/IllegalArgumentException;
        //  324    337    340    344    Ljava/lang/IllegalArgumentException;
        //  329    381    384    388    Ljava/lang/IllegalArgumentException;
        //  397    415    418    422    Ljava/lang/IllegalArgumentException;
        //  464    482    485    489    Ljava/lang/IllegalArgumentException;
        //  469    502    505    509    Ljava/lang/IllegalArgumentException;
        //  489    519    522    526    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        super.visitReferenceElement(ocReferenceElement);
        this.myDeclaratorChecker.checkReferenceElement(ocReferenceElement);
    }
    
    @Override
    public void visitLabeledStatement(final OCLabeledStatement ocLabeledStatement) {
        super.visitLabeledStatement(ocLabeledStatement);
        this.myDeclaratorChecker.checkDuplicates((PsiNameIdentifierOwner)ocLabeledStatement);
    }
    
    private void a(final OCExpression ocExpression, OCType refType) {
        try {
            if (ocExpression == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolvedType = ocExpression.getResolvedType();
        if (refType instanceof OCCppReferenceType) {
            refType = ((OCCppReferenceType)refType).getRefType();
        }
        try {
            if (resolvedType.isUnknown()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Label_0139: {
            Label_0065: {
                try {
                    if (resolvedType.isIntegerCompatible((PsiElement)ocExpression)) {
                        break Label_0139;
                    }
                    final OCType ocType = resolvedType;
                    final boolean b = ocType instanceof OCStructType;
                    if (b) {
                        break Label_0065;
                    }
                    break Label_0065;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCType ocType = resolvedType;
                    final boolean b = ocType instanceof OCStructType;
                    if (b) {
                        if (((OCStructType)resolvedType).isEnumClass()) {
                            break Label_0139;
                        }
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocExpression, OCInspections.IntegerTypeRequired.class, "err_expr_not_ice", "Integer expression is required in 'case' statement instead of '" + resolvedType.getName((PsiElement)ocExpression) + "'"), (IntentionAction)OCChangeTypeIntentionAction.getAction(ocExpression, OCIntType.CHAR));
            return;
            try {
                if (!new OCConstantExpressionVisitor().isConstant(ocExpression)) {
                    this.addErrorAnnotation((PsiElement)ocExpression, OCInspections.IntegerTypeRequired.class, "err_expr_not_ice", "Constant expression is required");
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        this.myCppChecker.checkAssignment(ocExpression, (PsiElement)ocExpression, refType, resolvedType, "Case expression type '" + resolvedType.getName((PsiElement)ocExpression) + "' is incompatible with switch expression type '" + refType.getName((PsiElement)ocExpression) + "': ");
    }
    
    @Override
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        super.visitCaseStatement(ocCaseStatement);
        final OCSwitchStatement ocSwitchStatement = (OCSwitchStatement)PsiTreeUtil.getContextOfType((PsiElement)ocCaseStatement, new Class[] { OCSwitchStatement.class });
        try {
            if (ocSwitchStatement == null) {
                this.addErrorAnnotation((PsiElement)ocCaseStatement, OCInspections.ConstructionIsNotAllowed.class, "err_case_not_in_switch", "Case label is outside of a switch");
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCDeclarationOrExpression expression = ocSwitchStatement.getExpression();
        try {
            if (expression == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCType resolvedType = expression.getResolvedType();
        try {
            if (ocCaseStatement.isRange()) {
                this.a(ocCaseStatement.getRangeFirst(), resolvedType);
                this.a(ocCaseStatement.getRangeSecond(), resolvedType);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        this.a(ocCaseStatement.getExpression(), resolvedType);
    }
    
    private void a(@Nullable final OCElement p0, @Nullable final OCType p1, final String p2, final OCStatement... p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: astore          5
        //     4: aload           5
        //     6: arraylength    
        //     7: istore          6
        //     9: iconst_0       
        //    10: istore          7
        //    12: iload           7
        //    14: iload           6
        //    16: if_icmpge       84
        //    19: aload           5
        //    21: iload           7
        //    23: aaload         
        //    24: astore          8
        //    26: aload           8
        //    28: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //    31: ifeq            78
        //    34: aload           8
        //    36: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //    39: ifeq            78
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: aload           8
        //    52: ldc             "CIDR"
        //    54: ldc             "Declaration is not allowed here"
        //    56: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    59: astore          9
        //    61: aload_0        
        //    62: aload           9
        //    64: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //    67: dup            
        //    68: aload           8
        //    70: ldc             "Remove statement"
        //    72: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //    75: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //    78: iinc            7, 1
        //    81: goto            12
        //    84: aload_1        
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    88: ifeq            296
        //    91: aload_1        
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    95: astore          5
        //    97: aload           5
        //    99: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   104: astore          6
        //   106: aload           6
        //   108: ifnull          296
        //   111: aload           5
        //   113: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   118: ifnonnull       296
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_1        
        //   129: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   134: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   139: ifne            168
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_0        
        //   150: aload_1        
        //   151: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //   153: ldc             "CIDR"
        //   155: ldc             "Declaration is not allowed here"
        //   157: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   160: pop            
        //   161: goto            296
        //   164: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload           6
        //   170: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   175: invokeinterface java/util/List.isEmpty:()Z
        //   180: ifeq            200
        //   183: aload_0        
        //   184: aload_1        
        //   185: ldc             "err_expected_unqualified_id"
        //   187: ldc             "Declaration or expression is expected"
        //   189: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   192: pop            
        //   193: goto            296
        //   196: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload           6
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   207: invokeinterface java/util/List.size:()I
        //   212: iconst_1       
        //   213: if_icmple       233
        //   216: aload_0        
        //   217: aload_1        
        //   218: ldc             "CIDR"
        //   220: ldc             "Only one declarator is allowed here"
        //   222: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   225: pop            
        //   226: goto            296
        //   229: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload           6
        //   235: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   240: iconst_0       
        //   241: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   246: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   249: astore          7
        //   251: aload           7
        //   253: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   258: ifnonnull       296
        //   261: aload_0        
        //   262: aload_1        
        //   263: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InitializerIssues;.class
        //   265: ldc             "err_expected_init_in_condition"
        //   267: ldc             "Initializer is expected"
        //   269: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   272: astore          8
        //   274: aload_0        
        //   275: aload           8
        //   277: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction;
        //   280: dup            
        //   281: aload           7
        //   283: aload           7
        //   285: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   290: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   293: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   296: aload_2        
        //   297: ifnull          314
        //   300: aload_2        
        //   301: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   304: ifeq            319
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: return         
        //   315: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aload_2        
        //   320: aload_1        
        //   321: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalarOrConvertibleToScalar:(Lcom/intellij/psi/PsiElement;)Z
        //   324: ifne            378
        //   327: new             Ljava/lang/StringBuilder;
        //   330: dup            
        //   331: invokespecial   java/lang/StringBuilder.<init>:()V
        //   334: ldc             "Type '"
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: aload_2        
        //   340: aload_1        
        //   341: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   344: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: ldc             "' used in '"
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: aload_3        
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: ldc             "' condition is not scalar"
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   364: astore          5
        //   366: aload_0        
        //   367: aload_1        
        //   368: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ScalarTypeRequired;.class
        //   370: ldc             "err_typecheck_statement_requires_scalar"
        //   372: aload           5
        //   374: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   377: pop            
        //   378: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  26     42     45     49     Ljava/lang/IllegalArgumentException;
        //  106    121    124    128    Ljava/lang/IllegalArgumentException;
        //  111    142    145    149    Ljava/lang/IllegalArgumentException;
        //  128    164    164    168    Ljava/lang/IllegalArgumentException;
        //  168    196    196    200    Ljava/lang/IllegalArgumentException;
        //  200    229    229    233    Ljava/lang/IllegalArgumentException;
        //  296    307    310    314    Ljava/lang/IllegalArgumentException;
        //  300    315    315    319    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0128:
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
    public void visitIfStatement(final OCIfStatement ocIfStatement) {
        super.visitIfStatement(ocIfStatement);
        final OCDeclarationOrExpression condition = ocIfStatement.getCondition();
        try {
            if (condition != null) {
                this.a(condition, condition.getResolvedType(), "if", ocIfStatement.getThenBranch(), ocIfStatement.getElseBranch());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
        super.visitWhileStatement(ocWhileStatement);
        final OCDeclarationOrExpression condition = ocWhileStatement.getCondition();
        try {
            if (condition != null) {
                this.a(condition, condition.getResolvedType(), "while", ocWhileStatement.getBody());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
        super.visitDoWhileStatement(ocDoWhileStatement);
        final OCExpression condition = ocDoWhileStatement.getCondition();
        if (condition != null) {
            this.a(condition, condition.getResolvedType(), "do-while", ocDoWhileStatement.getBody());
        }
    }
    
    @Override
    public void visitForStatement(final OCForStatement ocForStatement) {
        super.visitForStatement(ocForStatement);
        final OCDeclarationOrExpression condition = ocForStatement.getCondition();
        OCDeclarationOrExpression ocDeclarationOrExpression = null;
        OCType resolvedType = null;
        Label_0032: {
            try {
                ocDeclarationOrExpression = condition;
                if (condition != null) {
                    resolvedType = condition.getResolvedType();
                    break Label_0032;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            resolvedType = null;
        }
        this.a(ocDeclarationOrExpression, resolvedType, "for", ocForStatement.getBody());
    }
    
    @Override
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        super.visitForeachStatement(ocForeachStatement);
        this.myOperatorsChecker.checkForeach(ocForeachStatement);
        this.a(null, null, "foreach", ocForeachStatement.getBody());
    }
    
    @Override
    public void visitReturnStatement(final OCReturnStatement ocReturnStatement) {
        super.visitReturnStatement(ocReturnStatement);
        this.myOperatorsChecker.checkReturnStatement(ocReturnStatement);
    }
    
    @Override
    public void visitBreakStatement(final OCBreakStatement ocBreakStatement) {
        try {
            super.visitBreakStatement(ocBreakStatement);
            if (PsiTreeUtil.getContextOfType((PsiElement)ocBreakStatement, new Class[] { OCLoopStatement.class, OCSwitchStatement.class }) == null) {
                this.addErrorAnnotation((PsiElement)ocBreakStatement, OCInspections.ConstructionIsNotAllowed.class, "err_break_not_in_loop_or_switch", "'break' statement is outside of a loop or switch");
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitContinueStatement(final OCContinueStatement ocContinueStatement) {
        try {
            super.visitContinueStatement(ocContinueStatement);
            if (PsiTreeUtil.getContextOfType((PsiElement)ocContinueStatement, new Class[] { OCLoopStatement.class }) == null) {
                this.addErrorAnnotation((PsiElement)ocContinueStatement, OCInspections.ConstructionIsNotAllowed.class, "err_continue_not_in_loop", "'continue' statement is outside of a loop");
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
        super.visitSwitchStatement(ocSwitchStatement);
        this.myOperatorsChecker.checkSwitchStatement(ocSwitchStatement);
    }
    
    private void a(final OCExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       9
        //     4: return         
        //     5: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     8: athrow         
        //     9: aload_1        
        //    10: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: astore_2       
        //    16: aload_2        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    20: ifne            100
        //    23: aload_2        
        //    24: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    27: ifne            100
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_2        
        //    38: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //    41: ifne            100
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_0        
        //    52: aload_1        
        //    53: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PointerTypeRequired;.class
        //    55: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //    58: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getObjcThrowExpectsObject:()Ljava/lang/String;
        //    61: new             Ljava/lang/StringBuilder;
        //    64: dup            
        //    65: invokespecial   java/lang/StringBuilder.<init>:()V
        //    68: ldc             "Expression type '"
        //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    73: aload_2        
        //    74: aload_1        
        //    75: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    81: ldc             "' is not a pointer to object"
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    89: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    92: pop            
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      5      5      9      Ljava/lang/IllegalArgumentException;
        //  16     30     33     37     Ljava/lang/IllegalArgumentException;
        //  23     44     47     51     Ljava/lang/IllegalArgumentException;
        //  37     93     96     100    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    public void visitSynchronizedStatement(final OCSynchronizedStatement ocSynchronizedStatement) {
        super.visitSynchronizedStatement(ocSynchronizedStatement);
        this.a(ocSynchronizedStatement.getLockExpression());
    }
    
    @Override
    public void visitThrowExpression(final OCThrowExpression ocThrowExpression) {
        try {
            super.visitThrowExpression(ocThrowExpression);
            if (ocThrowExpression.isCppStatement()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0064: {
            try {
                this.a(ocThrowExpression.getExceptionExpression());
                if (ocThrowExpression.getExceptionExpression() != null) {
                    return;
                }
                final OCThrowExpression ocThrowExpression2 = ocThrowExpression;
                final Class<OCCatchSection> clazz = OCCatchSection.class;
                final boolean b = true;
                final int n = 1;
                final Class[] array = new Class[n];
                final int n2 = 0;
                final Class<OCCallable> clazz2 = OCCallable.class;
                array[n2] = clazz2;
                final PsiElement psiElement = PsiTreeUtil.getParentOfType((PsiElement)ocThrowExpression2, (Class)clazz, b, array);
                if (psiElement == null) {
                    break Label_0064;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCThrowExpression ocThrowExpression2 = ocThrowExpression;
                final Class<OCCatchSection> clazz = OCCatchSection.class;
                final boolean b = true;
                final int n = 1;
                final Class[] array = new Class[n];
                final int n2 = 0;
                final Class<OCCallable> clazz2 = OCCallable.class;
                array[n2] = clazz2;
                final PsiElement psiElement = PsiTreeUtil.getParentOfType((PsiElement)ocThrowExpression2, (Class)clazz, b, array);
                if (psiElement == null) {
                    this.addErrorAnnotation((PsiElement)ocThrowExpression, OCClangMessageFinder.getInstance().getRethrowUsedOutsideCatch(), "@throw (rethrow) is used outside of a @catch block");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
    }
    
    @Override
    public void visitTryStatement(final OCTryStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCTryStatement.getCatchSections:()Ljava/util/List;
        //     6: invokeinterface java/util/List.isEmpty:()Z
        //    11: ifeq            70
        //    14: aload_1        
        //    15: invokeinterface com/jetbrains/cidr/lang/psi/OCTryStatement.getFinallySection:()Lcom/jetbrains/cidr/lang/psi/OCFinallySection;
        //    20: ifnonnull       70
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_0        
        //    31: aload_1        
        //    32: ldc             "err_missing_catch_finally"
        //    34: aload_1        
        //    35: invokeinterface com/jetbrains/cidr/lang/psi/OCTryStatement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    45: ifeq            64
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: ldc             "Missing catch or finally"
        //    57: goto            66
        //    60: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: ldc             "Missing @catch or @finally"
        //    66: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    69: pop            
        //    70: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      23     26     30     Ljava/lang/IllegalArgumentException;
        //  14     48     51     55     Ljava/lang/IllegalArgumentException;
        //  30     60     60     64     Ljava/lang/IllegalArgumentException;
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
    public void visitCatchSection(final OCCatchSection p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitCatchSection:(Lcom/jetbrains/cidr/lang/psi/OCCatchSection;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCCatchSection.getParameters:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //    11: astore_2       
        //    12: aload_2        
        //    13: ifnonnull       21
        //    16: return         
        //    17: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_2        
        //    22: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //    27: invokeinterface java/util/List.size:()I
        //    32: iconst_1       
        //    33: if_icmpeq       108
        //    36: aload_0        
        //    37: aload_2        
        //    38: ldc             "CIDR"
        //    40: ldc             "Only one variable declaration is allowed"
        //    42: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    45: astore_3       
        //    46: aload_2        
        //    47: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //    52: invokeinterface java/util/List.size:()I
        //    57: iconst_1       
        //    58: isub           
        //    59: istore          4
        //    61: iload           4
        //    63: iconst_1       
        //    64: if_icmplt       107
        //    67: aload_0        
        //    68: aload_3        
        //    69: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //    72: dup            
        //    73: aload_2        
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //    79: iload           4
        //    81: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    86: checkcast       Lcom/intellij/psi/PsiElement;
        //    89: ldc             "Remove extra declarators"
        //    91: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //    94: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //    97: iinc            4, -1
        //   100: goto            61
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: return         
        //   108: aload_2        
        //   109: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //   114: iconst_0       
        //   115: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   120: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   123: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   128: astore_3       
        //   129: aload_3        
        //   130: ifnull          149
        //   133: aload_1        
        //   134: invokeinterface com/jetbrains/cidr/lang/psi/OCCatchSection.isCppStatement:()Z
        //   139: ifeq            154
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: return         
        //   150: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_3        
        //   155: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   160: astore          4
        //   162: aload           4
        //   164: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   167: ifne            251
        //   170: aload           4
        //   172: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   175: ifne            251
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload           4
        //   187: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   190: ifne            251
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_0        
        //   201: aload_3        
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   207: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$PointerTypeRequired;.class
        //   209: ldc             "err_catch_param_not_objc_type"
        //   211: new             Ljava/lang/StringBuilder;
        //   214: dup            
        //   215: invokespecial   java/lang/StringBuilder.<init>:()V
        //   218: ldc             "Variable type ('"
        //   220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: aload           4
        //   225: aload_1        
        //   226: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: ldc             "') is not a pointer to object"
        //   234: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   237: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   240: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   243: pop            
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     17     17     21     Ljava/lang/IllegalArgumentException;
        //  61     103    103    107    Ljava/lang/IllegalArgumentException;
        //  129    142    145    149    Ljava/lang/IllegalArgumentException;
        //  133    150    150    154    Ljava/lang/IllegalArgumentException;
        //  162    178    181    185    Ljava/lang/IllegalArgumentException;
        //  170    193    196    200    Ljava/lang/IllegalArgumentException;
        //  185    244    247    251    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0185:
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
    public void visitProperty(final OCProperty ocProperty) {
        super.visitProperty(ocProperty);
        if (PsiTreeUtil.getContextOfType((PsiElement)ocProperty, new Class[] { OCInterface.class, OCProtocol.class }) == null) {
            this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocProperty, OCInspections.ConstructionIsNotAllowed.class, "CIDR", "Property is declared outside of an interface/protocol"), (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocProperty, "Remove property"));
        }
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        super.visitDeclarator(ocDeclarator);
        this.myDeclaratorChecker.checkDeclarator(ocDeclarator.getType(), (PsiNameIdentifierOwner)ocDeclarator, ocDeclarator, ocDeclarator.getSymbol());
    }
    
    @Override
    public void visitDefineDirective(final OCDefineDirectiveImpl ocDefineDirectiveImpl) {
        this.myDeclaratorChecker.checkDuplicates(ocDefineDirectiveImpl.getName(), (PsiElement)ocDefineDirectiveImpl, (List<? extends PsiElement>)Collections.singletonList(ocDefineDirectiveImpl));
    }
    
    @Override
    public void visitStructLike(final OCStructLike p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitStructLike:(Lcom/jetbrains/cidr/lang/psi/OCStructLike;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    16: ifeq            35
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //    23: aload_1        
        //    24: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Lcom/intellij/psi/PsiNameIdentifierOwner;)Z
        //    27: pop            
        //    28: goto            233
        //    31: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_1        
        //    36: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    41: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    44: astore_2       
        //    45: new             Ljava/lang/StringBuilder;
        //    48: dup            
        //    49: invokespecial   java/lang/StringBuilder.<init>:()V
        //    52: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //    58: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    61: ldc             " "
        //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    66: aload_1        
        //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getName:()Ljava/lang/String;
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    78: astore_3       
        //    79: new             Ljava/lang/StringBuilder;
        //    82: dup            
        //    83: invokespecial   java/lang/StringBuilder.<init>:()V
        //    86: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: ldc             " "
        //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: aload_1        
        //   101: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getName:()Ljava/lang/String;
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   112: astore          4
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   124: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: ldc             " "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: aload_1        
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getName:()Ljava/lang/String;
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   147: astore          5
        //   149: aload_0        
        //   150: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //   153: aload_3        
        //   154: aload_1        
        //   155: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   160: aload_1        
        //   161: aload_2        
        //   162: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Ljava/util/List;)Z
        //   165: ifeq            230
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //   172: aload           4
        //   174: aload_1        
        //   175: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   180: aload_1        
        //   181: aload_2        
        //   182: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Ljava/util/List;)Z
        //   185: ifeq            230
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_0        
        //   196: getfield        com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //   199: aload           5
        //   201: aload_1        
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   207: aload_1        
        //   208: aload_2        
        //   209: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Ljava/util/List;)Z
        //   212: ifeq            230
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: iconst_1       
        //   223: goto            231
        //   226: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: iconst_0       
        //   231: istore          6
        //   233: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      31     31     35     Ljava/lang/IllegalArgumentException;
        //  149    188    191    195    Ljava/lang/IllegalArgumentException;
        //  168    215    218    222    Ljava/lang/IllegalArgumentException;
        //  195    226    226    230    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0195:
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
    public void visitDeclaration(final OCDeclaration p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitDeclaration:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getParent:()Lcom/intellij/psi/PsiElement;
        //    11: astore_2       
        //    12: aload_2        
        //    13: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //    16: ifne            33
        //    19: aload_2        
        //    20: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //    23: ifeq            164
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aload_1        
        //    34: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //    39: invokeinterface java/util/List.isEmpty:()Z
        //    44: ifne            164
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_1        
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.isTypedef:()Z
        //    60: ifne            164
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_1        
        //    71: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    74: ifne            164
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_1        
        //    85: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //    90: iconst_0       
        //    91: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    96: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    99: astore_3       
        //   100: aload_3        
        //   101: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   106: astore          4
        //   108: aload           4
        //   110: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   113: ifeq            134
        //   116: aload           4
        //   118: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   121: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isExtern:()Z
        //   124: ifne            164
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_0        
        //   135: aload_1        
        //   136: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //   138: ldc             "err_objc_var_decl_inclass"
        //   140: ldc             "Can't declare variables inside @interface or @protocol"
        //   142: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   145: astore          5
        //   147: aload_0        
        //   148: aload           5
        //   150: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   153: dup            
        //   154: aload_1        
        //   155: ldc             "Remove declaration"
        //   157: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   160: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   163: return         
        //   164: aload_1        
        //   165: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   170: astore_3       
        //   171: aload_1        
        //   172: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   177: invokeinterface java/util/List.isEmpty:()Z
        //   182: ifeq            288
        //   185: aload_3        
        //   186: ifnull          218
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload_3        
        //   197: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   202: aload_1        
        //   203: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   211: goto            219
        //   214: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: aconst_null    
        //   219: astore          4
        //   221: aload           4
        //   223: ifnull          256
        //   226: aload           4
        //   228: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   231: ifne            285
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: aload           4
        //   243: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   246: ifne            285
        //   249: goto            256
        //   252: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: aload_0        
        //   257: aload_1        
        //   258: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedExpressionResult;.class
        //   260: ldc             "ext_no_declarators"
        //   262: ldc             "Useless type name in empty declaration"
        //   264: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   267: astore          5
        //   269: aload_0        
        //   270: aload           5
        //   272: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   275: dup            
        //   276: aload_1        
        //   277: ldc             "Remove statement"
        //   279: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   282: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   285: goto            586
        //   288: aload_3        
        //   289: ifnull          308
        //   292: aload_3        
        //   293: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.isEmpty:()Z
        //   298: ifeq            586
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: aload_2        
        //   309: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   312: ifne            406
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   321: athrow         
        //   322: aload_2        
        //   323: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   326: ifne            406
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   335: athrow         
        //   336: aload_2        
        //   337: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   340: ifne            406
        //   343: goto            350
        //   346: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   349: athrow         
        //   350: aload_2        
        //   351: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnion;
        //   354: ifne            406
        //   357: goto            364
        //   360: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: aload_2        
        //   365: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //   368: ifne            586
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload_2        
        //   379: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   382: ifne            406
        //   385: goto            392
        //   388: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: aload_2        
        //   393: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   396: ifeq            586
        //   399: goto            406
        //   402: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   405: athrow         
        //   406: aload_1        
        //   407: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   410: ifeq            492
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: aload_1        
        //   421: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   424: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   429: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   432: astore          4
        //   434: aload           4
        //   436: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   439: ifeq            492
        //   442: aload           4
        //   444: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   447: astore          5
        //   449: aload           5
        //   451: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   454: ifne            487
        //   457: aload           5
        //   459: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
        //   462: ifne            487
        //   465: goto            472
        //   468: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   471: athrow         
        //   472: aload           5
        //   474: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConversionOperator:()Z
        //   477: ifeq            492
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: return         
        //   488: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   491: athrow         
        //   492: aconst_null    
        //   493: astore          4
        //   495: aload_1        
        //   496: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   501: invokeinterface java/util/List.isEmpty:()Z
        //   506: ifne            572
        //   509: aload_1        
        //   510: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   515: iconst_0       
        //   516: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   521: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   524: astore          5
        //   526: aload           5
        //   528: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   533: ifnonnull       569
        //   536: aload           5
        //   538: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   543: ifnull          565
        //   546: goto            553
        //   549: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   552: athrow         
        //   553: aload           5
        //   555: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   560: astore          4
        //   562: goto            569
        //   565: aload           5
        //   567: astore          4
        //   569: goto            575
        //   572: aload_1        
        //   573: astore          4
        //   575: aload_0        
        //   576: aload           4
        //   578: ldc             "CIDR"
        //   580: ldc             "Explicit type is required here"
        //   582: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   585: pop            
        //   586: aload_3        
        //   587: ifnull          678
        //   590: aload_1        
        //   591: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   594: ifeq            678
        //   597: goto            604
        //   600: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   603: athrow         
        //   604: aload_3        
        //   605: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   610: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   613: ifeq            678
        //   616: goto            623
        //   619: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   622: athrow         
        //   623: aload_1        
        //   624: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   627: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTrailingReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   632: ifnonnull       678
        //   635: goto            642
        //   638: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   641: athrow         
        //   642: aload_3        
        //   643: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   648: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsCxxReturnTypeDeduction:(Lcom/intellij/psi/PsiFile;)Z
        //   651: ifne            678
        //   654: goto            661
        //   657: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   660: athrow         
        //   661: aload_0        
        //   662: aload_3        
        //   663: ldc             "err_auto_missing_trailing_return"
        //   665: ldc             "Return type deduction is not supported by the compiler"
        //   667: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   670: pop            
        //   671: goto            678
        //   674: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   677: athrow         
        //   678: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     26     29     33     Ljava/lang/IllegalArgumentException;
        //  19     47     50     54     Ljava/lang/IllegalArgumentException;
        //  33     63     66     70     Ljava/lang/IllegalArgumentException;
        //  54     77     80     84     Ljava/lang/IllegalArgumentException;
        //  108    127    130    134    Ljava/lang/IllegalArgumentException;
        //  171    189    192    196    Ljava/lang/IllegalArgumentException;
        //  185    214    214    218    Ljava/lang/IllegalArgumentException;
        //  221    234    237    241    Ljava/lang/IllegalArgumentException;
        //  226    249    252    256    Ljava/lang/IllegalArgumentException;
        //  288    301    304    308    Ljava/lang/IllegalArgumentException;
        //  292    315    318    322    Ljava/lang/IllegalArgumentException;
        //  308    329    332    336    Ljava/lang/IllegalArgumentException;
        //  322    343    346    350    Ljava/lang/IllegalArgumentException;
        //  336    357    360    364    Ljava/lang/IllegalArgumentException;
        //  350    371    374    378    Ljava/lang/IllegalArgumentException;
        //  364    385    388    392    Ljava/lang/IllegalArgumentException;
        //  378    399    402    406    Ljava/lang/IllegalArgumentException;
        //  392    413    416    420    Ljava/lang/IllegalArgumentException;
        //  449    465    468    472    Ljava/lang/IllegalArgumentException;
        //  457    480    483    487    Ljava/lang/IllegalArgumentException;
        //  472    488    488    492    Ljava/lang/IllegalArgumentException;
        //  526    546    549    553    Ljava/lang/IllegalArgumentException;
        //  586    597    600    604    Ljava/lang/IllegalArgumentException;
        //  590    616    619    623    Ljava/lang/IllegalArgumentException;
        //  604    635    638    642    Ljava/lang/IllegalArgumentException;
        //  623    654    657    661    Ljava/lang/IllegalArgumentException;
        //  642    671    674    678    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
    public void visitMethod(final OCMethod ocMethod) {
        super.visitMethod(ocMethod);
        this.myImplementationChecker.checkMethod(ocMethod);
    }
    
    @Override
    public void visitExpressionStatement(final OCExpressionStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitExpressionStatement:(Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    11: astore_2       
        //    12: aload_2        
        //    13: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //    16: ifne            47
        //    19: aload_2        
        //    20: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //    23: ifne            47
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aload_2        
        //    34: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //    37: ifeq            150
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: aload_2        
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getReference:()Lcom/intellij/psi/PsiReference;
        //    53: astore_3       
        //    54: aload_3        
        //    55: instanceof      Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
        //    58: ifeq            107
        //    61: aload_3        
        //    62: checkcast       Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
        //    65: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.resolveToSymbols:()Ljava/util/List;
        //    68: invokeinterface java/util/List.size:()I
        //    73: ifne            150
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_3        
        //    84: checkcast       Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
        //    87: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.hasMagicOperands:()Z
        //    90: ifeq            107
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: goto            150
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_0        
        //   108: aload_2        
        //   109: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedExpressionResult;.class
        //   111: ldc             "warn_unused_expr"
        //   113: ldc             "Expression result is unused"
        //   115: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   118: astore          4
        //   120: aload_2        
        //   121: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   124: ifne            150
        //   127: aload_0        
        //   128: aload           4
        //   130: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   133: dup            
        //   134: aload_1        
        //   135: ldc             "Remove statement"
        //   137: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   140: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     26     29     33     Ljava/lang/IllegalArgumentException;
        //  19     40     43     47     Ljava/lang/IllegalArgumentException;
        //  54     76     79     83     Ljava/lang/IllegalArgumentException;
        //  61     93     96     100    Ljava/lang/IllegalArgumentException;
        //  83     103    103    107    Ljava/lang/IllegalArgumentException;
        //  120    143    146    150    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0083:
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
    public void visitMethodSelectorPart(final OCMethodSelectorPart ocMethodSelectorPart) {
        super.visitMethodSelectorPart(ocMethodSelectorPart);
        this.myDeclaratorChecker.checkDeclarator(ocMethodSelectorPart.getType(), (PsiNameIdentifierOwner)ocMethodSelectorPart, null, ocMethodSelectorPart.getLocalSymbol());
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        super.visitAssignmentExpression(ocAssignmentExpression);
        final PsiReference reference = ocAssignmentExpression.getReference();
        Label_0041: {
            try {
                if (!(reference instanceof OCOperatorReference)) {
                    break Label_0041;
                }
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    break Label_0041;
                }
                break Label_0041;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    this.myOperatorsChecker.checkBinaryOperatorApplicable(ocAssignmentExpression.getReceiverExpression(), ocAssignmentExpression.getSourceExpression(), ocAssignmentExpression.getOperationSign(), ocAssignmentExpression);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.a(ocAssignmentExpression, (OCOperatorReference)reference);
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        super.visitBinaryExpression(ocBinaryExpression);
        final PsiReference reference = ocBinaryExpression.getReference();
        Label_0041: {
            try {
                if (!(reference instanceof OCOperatorReference)) {
                    break Label_0041;
                }
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    break Label_0041;
                }
                break Label_0041;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    this.myOperatorsChecker.checkBinaryOperatorApplicable(ocBinaryExpression.getLeft(), ocBinaryExpression.getRight(), ocBinaryExpression.getOperationSign(), ocBinaryExpression);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.a(ocBinaryExpression, (OCOperatorReference)reference);
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        super.visitUnaryExpression(ocUnaryExpression);
        final PsiReference reference = ocUnaryExpression.getReference();
        Label_0041: {
            try {
                if (!(reference instanceof OCOperatorReference)) {
                    break Label_0041;
                }
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    break Label_0041;
                }
                break Label_0041;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    this.myOperatorsChecker.checkUnaryOperatorApplicable(ocUnaryExpression.getOperand(), ocUnaryExpression.getOperationSign(), (PsiElement)ocUnaryExpression);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.a(ocUnaryExpression, (OCOperatorReference)reference);
    }
    
    @Override
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        super.visitPrefixExpression(ocPrefixExpression);
        final PsiReference reference = ocPrefixExpression.getReference();
        Label_0041: {
            try {
                if (!(reference instanceof OCOperatorReference)) {
                    break Label_0041;
                }
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    break Label_0041;
                }
                break Label_0041;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    this.myOperatorsChecker.checkUnaryOperatorApplicable(ocPrefixExpression.getOperand(), ocPrefixExpression.getOperationSign(), (PsiElement)ocPrefixExpression);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.a(ocPrefixExpression, (OCOperatorReference)reference);
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        super.visitPostfixExpression(ocPostfixExpression);
        final PsiReference reference = ocPostfixExpression.getReference();
        Label_0041: {
            try {
                if (!(reference instanceof OCOperatorReference)) {
                    break Label_0041;
                }
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    break Label_0041;
                }
                break Label_0041;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiReference psiReference = reference;
                final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                final int n = list.size();
                if (n == 0) {
                    this.myOperatorsChecker.checkUnaryOperatorApplicable(ocPostfixExpression.getOperand(), ocPostfixExpression.getOperationSign(), (PsiElement)ocPostfixExpression);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.a(ocPostfixExpression, (OCOperatorReference)reference);
    }
    
    private void a(final OCExpression ocExpression, final OCOperatorReference ocOperatorReference) {
        this.myCppChecker.checkFieldVisibility(ocOperatorReference.resolveToSymbols().get(0), (PsiElement)ocExpression, null);
    }
    
    @Override
    public void visitLiteralExpression(final OCLiteralExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitLiteralExpression:(Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.getNode:()Lcom/intellij/lang/ASTNode;
        //    11: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    16: astore_2       
        //    17: aload_1        
        //    18: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    23: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsObjectLiterals:(Lcom/intellij/psi/PsiFile;)Z
        //    26: ifne            151
        //    29: aload_1        
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    35: astore_3       
        //    36: aload_3        
        //    37: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    40: ifeq            151
        //    43: aload_3        
        //    44: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    47: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    50: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    53: astore          4
        //    55: ldc             "NSNumber"
        //    57: aload           4
        //    59: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    62: ifne            99
        //    65: ldc             "NSArray"
        //    67: aload           4
        //    69: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    72: ifne            99
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: ldc             "NSDictionary"
        //    84: aload           4
        //    86: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    89: ifeq            151
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_0        
        //   100: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.getHolder:()Lcom/intellij/lang/annotation/AnnotationHolder;
        //   103: ifnull          151
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.getHolder:()Lcom/intellij/lang/annotation/AnnotationHolder;
        //   117: aload_1        
        //   118: new             Ljava/lang/StringBuilder;
        //   121: dup            
        //   122: invokespecial   java/lang/StringBuilder.<init>:()V
        //   125: aload           4
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: ldc             " literals are not supported by the compiler"
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   138: invokeinterface com/intellij/lang/annotation/AnnotationHolder.createErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   143: pop            
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_2        
        //   152: ifnull          385
        //   155: aload_2        
        //   156: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   161: astore_3       
        //   162: aload_3        
        //   163: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   166: if_acmpne       260
        //   169: aload_2        
        //   170: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   175: astore          4
        //   177: aload           4
        //   179: ldc             "\""
        //   181: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   184: ifeq            257
        //   187: aload           4
        //   189: invokevirtual   java/lang/String.length:()I
        //   192: iconst_1       
        //   193: if_icmpeq       220
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aload           4
        //   205: ldc             "\""
        //   207: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   210: ifne            257
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload_0        
        //   221: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.getHolder:()Lcom/intellij/lang/annotation/AnnotationHolder;
        //   224: ifnull          257
        //   227: goto            234
        //   230: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: aload_0        
        //   235: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.getHolder:()Lcom/intellij/lang/annotation/AnnotationHolder;
        //   238: aload_2        
        //   239: ldc             "Unterminated string literal"
        //   241: invokeinterface com/intellij/lang/annotation/AnnotationHolder.createErrorAnnotation:(Lcom/intellij/lang/ASTNode;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   246: iconst_1       
        //   247: invokevirtual   com/intellij/lang/annotation/Annotation.setAfterEndOfLine:(Z)V
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: goto            375
        //   260: aload_3        
        //   261: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WRONG_RAW_STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   264: if_acmpne       309
        //   267: aload_0        
        //   268: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.getHolder:()Lcom/intellij/lang/annotation/AnnotationHolder;
        //   271: aload_2        
        //   272: ldc             "Invalid suffix on raw string"
        //   274: invokeinterface com/intellij/lang/annotation/AnnotationHolder.createErrorAnnotation:(Lcom/intellij/lang/ASTNode;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   279: astore          4
        //   281: aload           4
        //   283: iconst_1       
        //   284: invokevirtual   com/intellij/lang/annotation/Annotation.setNeedsUpdateOnTyping:(Z)V
        //   287: aload_0        
        //   288: aload           4
        //   290: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddRawStringSuffix;
        //   293: dup            
        //   294: aload_2        
        //   295: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   300: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddRawStringSuffix.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   303: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   306: goto            375
        //   309: aload_3        
        //   310: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WRONG_INTEGER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   313: if_acmpne       342
        //   316: aload_0        
        //   317: aload_2        
        //   318: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   323: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   326: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInvalidIntSuffix:()Ljava/lang/String;
        //   329: ldc             "Invalid suffix on integer constant"
        //   331: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   334: pop            
        //   335: goto            375
        //   338: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   341: athrow         
        //   342: aload_3        
        //   343: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WRONG_FLOAT_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   346: if_acmpne       375
        //   349: aload_0        
        //   350: aload_2        
        //   351: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   356: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   359: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInvalidFloatSuffix:()Ljava/lang/String;
        //   362: ldc             "Invalid suffix on floating constant"
        //   364: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   367: pop            
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: aload_2        
        //   376: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   381: astore_2       
        //   382: goto            151
        //   385: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  55     75     78     82     Ljava/lang/IllegalArgumentException;
        //  65     92     95     99     Ljava/lang/IllegalArgumentException;
        //  82     106    109    113    Ljava/lang/IllegalArgumentException;
        //  99     144    147    151    Ljava/lang/IllegalArgumentException;
        //  177    196    199    203    Ljava/lang/IllegalArgumentException;
        //  187    213    216    220    Ljava/lang/IllegalArgumentException;
        //  203    227    230    234    Ljava/lang/IllegalArgumentException;
        //  220    250    253    257    Ljava/lang/IllegalArgumentException;
        //  309    338    338    342    Ljava/lang/IllegalArgumentException;
        //  342    368    371    375    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
    public void visitArrayLiteral(final OCNSArrayLiteral ocnsArrayLiteral) {
        super.visitArrayLiteral(ocnsArrayLiteral);
        for (final OCExpression ocExpression : ocnsArrayLiteral.getElements()) {
            this.myCppChecker.checkAssignment(ocExpression, (PsiElement)ocExpression, OCIdType.pointerToID(ocnsArrayLiteral.getProject()), ocExpression.getResolvedType(), "");
        }
    }
    
    @Override
    public void visitDictionaryLiteral(final OCNSDictionaryLiteral ocnsDictionaryLiteral) {
        super.visitDictionaryLiteral(ocnsDictionaryLiteral);
        for (final OCExpression ocExpression : ocnsDictionaryLiteral.getElements()) {
            this.myCppChecker.checkAssignment(ocExpression, (PsiElement)ocExpression, OCIdType.pointerToID(ocnsDictionaryLiteral.getProject()), ocExpression.getResolvedType(), "");
        }
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        super.visitCastExpression(ocCastExpression);
        this.myOperatorsChecker.checkCastExperssion(ocCastExpression);
    }
    
    @Override
    public void visitArraySelectionExpression(final OCArraySelectionExpression ocArraySelectionExpression) {
        super.visitArraySelectionExpression(ocArraySelectionExpression);
        final OCExpression arrayExpression = ocArraySelectionExpression.getArrayExpression();
        final OCExpression indexExpression = ocArraySelectionExpression.getIndexExpression();
        try {
            if (indexExpression == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCType ocType = arrayExpression.getResolvedType();
        final OCType resolvedType = indexExpression.getResolvedType();
        final PsiReference reference = ocArraySelectionExpression.getReference();
        Label_0697: {
            Label_0539: {
                Label_0108: {
                    Label_0083: {
                        try {
                            if (!(reference instanceof OCOperatorReference)) {
                                break Label_0083;
                            }
                            final PsiReference psiReference = reference;
                            final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                            final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                            final int n = list.size();
                            if (n == 0) {
                                break Label_0083;
                            }
                            break Label_0697;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final PsiReference psiReference = reference;
                            final OCOperatorReference ocOperatorReference = (OCOperatorReference)psiReference;
                            final List<OCSymbol> list = ocOperatorReference.resolveToSymbols();
                            final int n = list.size();
                            if (n != 0) {
                                break Label_0697;
                            }
                            if (!(ocType instanceof OCCppReferenceType)) {
                                break Label_0108;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    ocType = ((OCCppReferenceType)ocType).getRefType();
                    try {
                        if (!ocType.isPointerToObject() || !OCCompilerFeatures.supportsSubscripting((PsiFile)ocArraySelectionExpression.getContainingOCFile())) {
                            break Label_0539;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                final ReadWriteAccessDetector.Access expressionAccess = new OCReadWriteAccessDetector().getExpressionAccess((PsiElement)ocArraySelectionExpression);
                final String arraySubscriptAccessorName = ocArraySelectionExpression.getArraySubscriptAccessorName(resolvedType, expressionAccess);
                if (arraySubscriptAccessorName != null) {
                    final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)ocArraySelectionExpression.getArrayExpression(), OCInspections.UnresolvedMessage.class, "err_objc_subscript_method_not_found", "Type '" + ocType.getName((PsiElement)ocArraySelectionExpression) + "' doesn't respond to '-" + arraySubscriptAccessorName + "'");
                    final OCObjectType ocObjectType = (OCObjectType)ocType.getTerminalType();
                    final OCClassSymbol classSymbol = ocObjectType.getClassSymbol();
                    final String arraySubscriptMethodSignature = ocArraySelectionExpression.getArraySubscriptMethodSignature(resolvedType, expressionAccess);
                    OCType ocType2 = null;
                    Label_0280: {
                        try {
                            if (expressionAccess == ReadWriteAccessDetector.Access.Read) {
                                ocType2 = OCIdType.pointerToID(ocArraySelectionExpression.getProject());
                                break Label_0280;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                        ocType2 = OCVoidType.instance();
                    }
                    final OCType ocType3 = ocType2;
                    Label_0424: {
                        try {
                            this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCCreateNewDefinitionIntentionAction((PsiElement)ocArraySelectionExpression, classSymbol, "-" + arraySubscriptAccessorName, arraySubscriptMethodSignature, ocType3, ocObjectType));
                            if (!ocType.getName().equals("NSArray *") || !arraySubscriptAccessorName.equals("setObject:atIndexedSubscript:")) {
                                break Label_0424;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw b(ex6);
                        }
                        final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(arrayExpression);
                        try {
                            if (symbol == null || !symbol.getResolvedType().equals(ocType, (PsiElement)arrayExpression)) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw b(ex7);
                        }
                        this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCChangeTypeIntentionAction(symbol, OCPointerType.to(OCReferenceType.resolvedFromText("NSMutableArray", arrayExpression.getContainingFile()))));
                        return;
                        try {
                            if (!ocType.getName().equals("NSDictionary *") || !arraySubscriptAccessorName.equals("setObject:forKeyedSubscript:")) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw b(ex8);
                        }
                    }
                    final OCSymbol symbol2 = OCGetSymbolVisitor.getSymbol(arrayExpression);
                    try {
                        if (symbol2 == null || !symbol2.getResolvedType().equals(ocType, (PsiElement)arrayExpression)) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw b(ex9);
                    }
                    this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCChangeTypeIntentionAction(symbol2, OCPointerType.to(OCReferenceType.resolvedFromText("NSMutableDictionary", arrayExpression.getContainingFile()))));
                }
                else {
                    this.addErrorAnnotation((PsiElement)indexExpression, OCInspections.ArrayIssues.class, "err_objc_subscript_index_type", "Index expression must be an integer or an object pointer");
                }
                return;
                try {
                    if (ocType.isSubclassOfMagic((PsiElement)ocArraySelectionExpression)) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
            }
            Label_0635: {
                Label_0611: {
                    Label_0578: {
                        try {
                            if (ocType.isUnknown()) {
                                break Label_0611;
                            }
                            final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType;
                            final boolean b = ocCppReferenceType instanceof OCPointerType;
                            if (!b) {
                                break Label_0578;
                            }
                            break Label_0611;
                        }
                        catch (IllegalArgumentException ex11) {
                            throw b(ex11);
                        }
                        try {
                            final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType;
                            final boolean b = ocCppReferenceType instanceof OCPointerType;
                            if (!b) {
                                this.addErrorAnnotation((PsiElement)ocArraySelectionExpression.getArrayExpression(), OCInspections.ArrayIssues.class, "err_typecheck_subscript_value", "Subscripted value is not an array");
                                ((OCOperatorReference)reference).resolveToSymbols();
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex12) {
                            throw b(ex12);
                        }
                    }
                    try {
                        if (resolvedType.isUnknown()) {
                            return;
                        }
                        final OCType ocType4 = resolvedType;
                        final OCArraySelectionExpression ocArraySelectionExpression2 = ocArraySelectionExpression;
                        final boolean b2 = ocType4.isIntegerCompatible((PsiElement)ocArraySelectionExpression2);
                        if (!b2) {
                            break Label_0635;
                        }
                        return;
                    }
                    catch (IllegalArgumentException ex13) {
                        throw b(ex13);
                    }
                }
                try {
                    final OCType ocType4 = resolvedType;
                    final OCArraySelectionExpression ocArraySelectionExpression2 = ocArraySelectionExpression;
                    final boolean b2 = ocType4.isIntegerCompatible((PsiElement)ocArraySelectionExpression2);
                    if (b2) {
                        return;
                    }
                    if (ocType.isMagicInside(new OCResolveContext((PsiElement)ocArraySelectionExpression))) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex14) {
                    throw b(ex14);
                }
            }
            this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocArraySelectionExpression.getIndexExpression(), OCInspections.ArrayIssues.class, "err_objc_subscript_index_type", "Array index is not integer"), (IntentionAction)OCChangeTypeIntentionAction.getAction(ocArraySelectionExpression.getIndexExpression(), OCIntType.CHAR));
            return;
        }
        final OCSymbol ocSymbol = ((OCOperatorReference)reference).resolveToSymbols().get(0);
        this.myCppChecker.checkFieldVisibility(ocSymbol, (PsiElement)ocArraySelectionExpression, null);
        if (ocSymbol instanceof OCMethodSymbol) {
            final List<OCMethodSymbol.SelectorPartSymbol> selectors = ((OCMethodSymbol)ocSymbol).getSelectors();
            this.myCppChecker.checkAssignment(indexExpression, (PsiElement)indexExpression, selectors.get(selectors.size() - 1).getParameter().getType().resolve(ocArraySelectionExpression.getContainingFile()), resolvedType, "Index type mismatch: ");
            final OCExpression topmostParenthesized = OCParenthesesUtils.topmostParenthesized(ocArraySelectionExpression);
            try {
                if (!(topmostParenthesized.getParent() instanceof OCAssignmentExpression) || ((OCAssignmentExpression)topmostParenthesized.getParent()).getReceiverExpression() != topmostParenthesized) {
                    return;
                }
            }
            catch (IllegalArgumentException ex15) {
                throw b(ex15);
            }
            final OCExpression sourceExpression = ((OCAssignmentExpression)topmostParenthesized.getParent()).getSourceExpression();
            final OCType resolve = selectors.get(0).getParameter().getType().resolve(ocArraySelectionExpression.getContainingFile());
            try {
                if (sourceExpression != null) {
                    this.myCppChecker.checkAssignment(sourceExpression, (PsiElement)sourceExpression, resolve, sourceExpression.getResolvedType(), "");
                }
            }
            catch (IllegalArgumentException ex16) {
                throw b(ex16);
            }
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        super.visitCallExpression(ocCallExpression);
        this.myOperatorsChecker.checkCallExpression(ocCallExpression);
    }
    
    @Override
    public void visitMacroCall(final OCMacroCall ocMacroCall) {
        super.visitMacroCall(ocMacroCall);
        this.myOperatorsChecker.checkMacroCall(ocMacroCall);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        super.visitSendMessageExpression(ocSendMessageExpression);
        this.myOperatorsChecker.checkSendMessageExpression(ocSendMessageExpression);
    }
    
    @Override
    public void visitSelectorExpression(final OCSelectorExpression ocSelectorExpression) {
        super.visitSelectorExpression(ocSelectorExpression);
        final Iterator<OCMethodSymbol> iterator = ocSelectorExpression.getReference().resolveToSymbols().iterator();
        while (iterator.hasNext()) {
            OCFileSymbols.markSymbolAsUsed(ocSelectorExpression.getContainingOCFile(), iterator.next(), (PsiElement)ocSelectorExpression);
        }
    }
    
    @Override
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        super.visitConditionalExpression(ocConditionalExpression);
        final OCType resolvedType = ocConditionalExpression.getCondition().getResolvedType();
        Label_0039: {
            try {
                if (resolvedType.isUnknown()) {
                    break Label_0039;
                }
                final OCType ocType = resolvedType;
                final OCConditionalExpression ocConditionalExpression2 = ocConditionalExpression;
                final boolean b = ocType.isScalarOrConvertibleToScalar((PsiElement)ocConditionalExpression2);
                if (!b) {
                    break Label_0039;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType = resolvedType;
                final OCConditionalExpression ocConditionalExpression2 = ocConditionalExpression;
                final boolean b = ocType.isScalarOrConvertibleToScalar((PsiElement)ocConditionalExpression2);
                if (!b) {
                    this.addErrorAnnotation((PsiElement)ocConditionalExpression.getCondition(), OCInspections.ScalarTypeRequired.class, OCClangMessageFinder.getInstance().getConditionShouldBeScalar(), "Type '" + resolvedType.getName((PsiElement)ocConditionalExpression) + "' used in '?:' operator condition is not scalar");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCExpression positiveExpression = ocConditionalExpression.getPositiveExpression(true);
        final OCExpression negativeExpression = ocConditionalExpression.getNegativeExpression();
        Label_0130: {
            try {
                if (positiveExpression == null) {
                    return;
                }
                final OCExpression ocExpression = negativeExpression;
                if (ocExpression == null) {
                    return;
                }
                break Label_0130;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCExpression ocExpression = negativeExpression;
                if (ocExpression == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        OCType ocType2 = positiveExpression.getResolvedType();
        if (ocType2 instanceof OCCppReferenceType) {
            ocType2 = ((OCCppReferenceType)ocType2).getRefType();
        }
        OCType ocType3 = negativeExpression.getResolvedType();
        if (ocType3 instanceof OCCppReferenceType) {
            ocType3 = ((OCCppReferenceType)ocType3).getRefType();
        }
        OCType ocType4 = ocConditionalExpression.getResolvedType();
        if (ocType4 instanceof OCCppReferenceType) {
            ocType4 = ((OCCppReferenceType)ocType4).getRefType();
        }
        final String string = "Types '" + ocType2.getName((PsiElement)ocConditionalExpression) + "' and '" + ocType3.getName((PsiElement)ocConditionalExpression) + "' are not compatible";
        try {
            if (this.myCppChecker.checkAssignment(positiveExpression, (PsiElement)positiveExpression, ocType4, ocType2, string) == null) {
                this.myCppChecker.checkAssignment(negativeExpression, (PsiElement)negativeExpression, ocType4, ocType3, string);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        super.visitQualifiedExpression(ocQualifiedExpression);
        this.myOperatorsChecker.checkQualifiedExpression(ocQualifiedExpression);
    }
    
    @Override
    public void visitPropertyAttributesList(final OCPropertyAttributesList p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitPropertyAttributesList:(Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;)V
        //     5: new             Ljava/util/HashSet;
        //     8: dup            
        //     9: invokespecial   java/util/HashSet.<init>:()V
        //    12: astore_2       
        //    13: aload_1        
        //    14: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getAttributes:()Ljava/util/List;
        //    19: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    24: astore_3       
        //    25: aload_3        
        //    26: invokeinterface java/util/Iterator.hasNext:()Z
        //    31: ifeq            903
        //    34: aload_3        
        //    35: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    40: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //    43: astore          4
        //    45: aload           4
        //    47: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getName:()Ljava/lang/String;
        //    52: astore          5
        //    54: aload           5
        //    56: ifnonnull       66
        //    59: goto            25
        //    62: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aconst_null    
        //    67: astore          6
        //    69: aload           5
        //    71: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.parseAttribute:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    74: astore          7
        //    76: aload_1        
        //    77: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    82: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsNullability:(Lcom/intellij/psi/PsiFile;)Z
        //    85: ifne            119
        //    88: aload           7
        //    90: ifnull          119
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: aload           7
        //   102: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //   105: iconst_5       
        //   106: if_icmpne       119
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aconst_null    
        //   117: astore          7
        //   119: aload_1        
        //   120: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   125: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsClassProperty:(Lcom/intellij/psi/PsiFile;)Z
        //   128: ifne            163
        //   131: aload           7
        //   133: ifnull          163
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload           7
        //   145: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //   148: bipush          6
        //   150: if_icmpne       163
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aconst_null    
        //   161: astore          7
        //   163: aload_1        
        //   164: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getParent:()Lcom/intellij/psi/PsiElement;
        //   169: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //   172: ifeq            193
        //   175: aload           7
        //   177: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.CLASS:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   180: if_acmpeq       193
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   189: athrow         
        //   190: aconst_null    
        //   191: astore          7
        //   193: aload           7
        //   195: ifnull          748
        //   198: aload_2        
        //   199: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   204: astore          8
        //   206: aload           8
        //   208: invokeinterface java/util/Iterator.hasNext:()Z
        //   213: ifeq            352
        //   216: aload           8
        //   218: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   223: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   226: astore          9
        //   228: aload           9
        //   230: aload           7
        //   232: if_acmpne       280
        //   235: aload_0        
        //   236: aload           4
        //   238: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   243: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$DuplicateAttribute;.class
        //   245: ldc             "CIDR"
        //   247: new             Ljava/lang/StringBuilder;
        //   250: dup            
        //   251: invokespecial   java/lang/StringBuilder.<init>:()V
        //   254: ldc             "Attribute '"
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: aload           5
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: ldc             "' was already declared"
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   272: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   275: astore          6
        //   277: goto            349
        //   280: aload           9
        //   282: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //   285: aload           7
        //   287: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //   290: if_icmpne       349
        //   293: aload_0        
        //   294: aload           4
        //   296: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   301: ldc             "err_objc_property_attr_mutually_exclusive"
        //   303: new             Ljava/lang/StringBuilder;
        //   306: dup            
        //   307: invokespecial   java/lang/StringBuilder.<init>:()V
        //   310: ldc             "Attributes '"
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: aload           9
        //   317: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.name:()Ljava/lang/String;
        //   320: invokestatic    com/intellij/openapi/util/text/StringUtil.toLowerCase:(Ljava/lang/String;)Ljava/lang/String;
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: ldc             "' and '"
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: aload           5
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: ldc             "' are mutually exclusive"
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   344: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   347: astore          6
        //   349: goto            206
        //   352: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.PROPERTY_ATTRIBUTES_WITH_VALUE:Ljava/util/Set;
        //   355: aload           7
        //   357: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   362: ifeq            428
        //   365: aload           4
        //   367: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getValue:()Ljava/lang/String;
        //   372: ifnonnull       428
        //   375: goto            382
        //   378: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: aload_0        
        //   383: aload           4
        //   385: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   390: ldc             "CIDR"
        //   392: new             Ljava/lang/StringBuilder;
        //   395: dup            
        //   396: invokespecial   java/lang/StringBuilder.<init>:()V
        //   399: ldc             "Attribute '"
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: aload           5
        //   406: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   409: ldc             "' requires a value"
        //   411: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   414: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   417: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   420: pop            
        //   421: goto            736
        //   424: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   427: athrow         
        //   428: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.PROPERTY_ATTRIBUTES_WITH_VALUE:Ljava/util/Set;
        //   431: aload           7
        //   433: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   438: ifne            504
        //   441: aload           4
        //   443: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getValue:()Ljava/lang/String;
        //   448: ifnull          504
        //   451: goto            458
        //   454: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   457: athrow         
        //   458: aload_0        
        //   459: aload           4
        //   461: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   466: ldc             "CIDR"
        //   468: new             Ljava/lang/StringBuilder;
        //   471: dup            
        //   472: invokespecial   java/lang/StringBuilder.<init>:()V
        //   475: ldc             "Attribute '"
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: aload           5
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: ldc             "' mustn't have a value"
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   493: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   496: pop            
        //   497: goto            736
        //   500: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   503: athrow         
        //   504: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.PROPERTY_ATTRIBUTES_FOR_OBJECT:Ljava/util/Set;
        //   507: aload           7
        //   509: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   514: ifeq            736
        //   517: aload           4
        //   519: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getParent:()Lcom/intellij/psi/PsiElement;
        //   524: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   529: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   532: ifeq            736
        //   535: goto            542
        //   538: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   541: athrow         
        //   542: aload           4
        //   544: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getParent:()Lcom/intellij/psi/PsiElement;
        //   549: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   554: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   557: astore          8
        //   559: aload           8
        //   561: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   566: astore          9
        //   568: aload           9
        //   570: ifnull          736
        //   573: aload           9
        //   575: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   580: astore          10
        //   582: aload           10
        //   584: invokeinterface java/util/List.isEmpty:()Z
        //   589: ifne            736
        //   592: aload           10
        //   594: iconst_0       
        //   595: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   600: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   603: astore          11
        //   605: aload           11
        //   607: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   612: astore          12
        //   614: aload           11
        //   616: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   621: astore          13
        //   623: aload           10
        //   625: invokeinterface java/util/List.size:()I
        //   630: ifle            736
        //   633: aload           12
        //   635: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   638: ifne            736
        //   641: goto            648
        //   644: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   647: athrow         
        //   648: aload           13
        //   650: ifnull          679
        //   653: goto            660
        //   656: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   659: athrow         
        //   660: aload           13
        //   662: ldc             "NSObject"
        //   664: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
        //   669: ifne            736
        //   672: goto            679
        //   675: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   678: athrow         
        //   679: new             Ljava/lang/StringBuilder;
        //   682: dup            
        //   683: invokespecial   java/lang/StringBuilder.<init>:()V
        //   686: ldc             "Attribute '"
        //   688: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   691: aload           5
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: ldc             "' requires the property of object type instead of '"
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: aload           12
        //   703: aload_1        
        //   704: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   707: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   710: ldc             "'"
        //   712: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   715: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   718: astore          14
        //   720: aload_0        
        //   721: aload           4
        //   723: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   728: ldc             "err_objc_property_requires_object"
        //   730: aload           14
        //   732: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   735: pop            
        //   736: aload_2        
        //   737: aload           7
        //   739: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   744: pop            
        //   745: goto            788
        //   748: aload_0        
        //   749: aload           4
        //   751: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   756: ldc             "err_objc_expected_property_attr"
        //   758: new             Ljava/lang/StringBuilder;
        //   761: dup            
        //   762: invokespecial   java/lang/StringBuilder.<init>:()V
        //   765: ldc             "Unknown attribute '"
        //   767: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   770: aload           5
        //   772: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   775: ldc             "'"
        //   777: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   780: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   783: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   786: astore          6
        //   788: aload           4
        //   790: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getColon:()Lcom/intellij/psi/PsiElement;
        //   795: ifnull          838
        //   798: ldc             "getter"
        //   800: aload           5
        //   802: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   805: ifeq            871
        //   808: goto            815
        //   811: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   814: athrow         
        //   815: aload_0        
        //   816: aload           4
        //   818: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getColon:()Lcom/intellij/psi/PsiElement;
        //   823: ldc             "CIDR"
        //   825: ldc             "Colon is only allowed for 'setter' attribute"
        //   827: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   830: pop            
        //   831: goto            871
        //   834: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   837: athrow         
        //   838: ldc             "setter"
        //   840: aload           5
        //   842: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   845: ifeq            871
        //   848: aload_0        
        //   849: aload           4
        //   851: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getValueElement:()Lcom/intellij/psi/PsiElement;
        //   856: ldc             "CIDR"
        //   858: ldc             "Colon is required for 'setter' attribute"
        //   860: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   863: pop            
        //   864: goto            871
        //   867: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   870: athrow         
        //   871: aload           6
        //   873: ifnull          900
        //   876: aload_0        
        //   877: aload           6
        //   879: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   882: dup            
        //   883: aload           4
        //   885: ldc             "Remove attribute"
        //   887: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   890: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   893: goto            900
        //   896: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   899: athrow         
        //   900: goto            25
        //   903: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  54     62     62     66     Ljava/lang/IllegalArgumentException;
        //  76     93     96     100    Ljava/lang/IllegalArgumentException;
        //  88     109    112    116    Ljava/lang/IllegalArgumentException;
        //  119    136    139    143    Ljava/lang/IllegalArgumentException;
        //  131    153    156    160    Ljava/lang/IllegalArgumentException;
        //  163    183    186    190    Ljava/lang/IllegalArgumentException;
        //  352    375    378    382    Ljava/lang/IllegalArgumentException;
        //  365    424    424    428    Ljava/lang/IllegalArgumentException;
        //  428    451    454    458    Ljava/lang/IllegalArgumentException;
        //  441    500    500    504    Ljava/lang/IllegalArgumentException;
        //  504    535    538    542    Ljava/lang/IllegalArgumentException;
        //  623    641    644    648    Ljava/lang/IllegalArgumentException;
        //  633    653    656    660    Ljava/lang/IllegalArgumentException;
        //  648    672    675    679    Ljava/lang/IllegalArgumentException;
        //  788    808    811    815    Ljava/lang/IllegalArgumentException;
        //  798    834    834    838    Ljava/lang/IllegalArgumentException;
        //  838    864    867    871    Ljava/lang/IllegalArgumentException;
        //  871    893    896    900    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0648:
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
    public void visitAttributeList(final OCAttributesList list) {
        Label_0033: {
            try {
                super.visitAttributeList(list);
                if (!list.isMicrosoftAttributes()) {
                    return;
                }
                final OCAttributesList list2 = list;
                final OCFile ocFile = list2.getContainingOCFile();
                final boolean b = OCCompilerFeatures.supportsMicrosoftAttributes((PsiFile)ocFile);
                if (!b) {
                    break Label_0033;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCAttributesList list2 = list;
                final OCFile ocFile = list2.getContainingOCFile();
                final boolean b = OCCompilerFeatures.supportsMicrosoftAttributes((PsiFile)ocFile);
                if (!b) {
                    this.addErrorAnnotation((PsiElement)list, "CIDR", "Microsoft attributes are not supported by the compiler");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
    }
    
    @Override
    public void visitTypeElement(final OCTypeElement ocTypeElement) {
        super.visitTypeElement(ocTypeElement);
        for (final ASTNode astNode : ocTypeElement.getNode().getChildren((TokenSet)null)) {
            Label_0071: {
                try {
                    if (astNode.getElementType() != OCTokenTypes.UNDERLYING_TYPE_KEYWORD) {
                        break Label_0071;
                    }
                    final OCTypeElement ocTypeElement2 = ocTypeElement;
                    final PsiFile psiFile = ocTypeElement2.getContainingFile();
                    final OCCompilerFeatures.Feature feature = OCCompilerFeatures.Feature.UNDERLYING_TYPE;
                    final boolean b = OCCompilerFeatures.isFeatureEnabled(psiFile, feature);
                    if (!b) {
                        break Label_0071;
                    }
                    break Label_0071;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCTypeElement ocTypeElement2 = ocTypeElement;
                    final PsiFile psiFile = ocTypeElement2.getContainingFile();
                    final OCCompilerFeatures.Feature feature = OCCompilerFeatures.Feature.UNDERLYING_TYPE;
                    final boolean b = OCCompilerFeatures.isFeatureEnabled(psiFile, feature);
                    if (!b) {
                        this.addErrorAnnotation(astNode.getPsi(), "CIDR", "__underlying_type is not supported by the compiler");
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
    }
    
    @Override
    public void visitQualifiedDesignator(final OCQualifiedDesignator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedDesignator.getArrayStartIndexer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     6: ifnull          276
        //     9: aload_1        
        //    10: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedDesignator.getParentType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: astore_2       
        //    16: aload_2        
        //    17: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    20: ifeq            264
        //    23: aload_2        
        //    24: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    27: astore_3       
        //    28: aload_3        
        //    29: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //    32: istore          4
        //    34: aload_1        
        //    35: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedDesignator.getArrayStartIndexer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Number;
        //    43: astore          5
        //    45: aload           5
        //    47: ifnull          115
        //    50: aload           5
        //    52: invokevirtual   java/lang/Number.intValue:()I
        //    55: iflt            96
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_3        
        //    66: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //    69: ifeq            115
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload           5
        //    81: invokevirtual   java/lang/Number.intValue:()I
        //    84: iload           4
        //    86: if_icmplt       115
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: aload_1        
        //    98: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   100: ldc             "CIDR"
        //   102: ldc             "Array index in initializer exceeds array bounds"
        //   104: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   107: pop            
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_1        
        //   116: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedDesignator.getArrayStopIndexer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   121: astore          6
        //   123: aload           6
        //   125: ifnull          261
        //   128: aload           6
        //   130: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Number;
        //   133: astore          7
        //   135: aload           7
        //   137: ifnull          205
        //   140: aload           7
        //   142: invokevirtual   java/lang/Number.intValue:()I
        //   145: iflt            186
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: aload_3        
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //   159: ifeq            205
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload           7
        //   171: invokevirtual   java/lang/Number.intValue:()I
        //   174: iload           4
        //   176: if_icmplt       205
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_0        
        //   187: aload_1        
        //   188: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   190: ldc             "CIDR"
        //   192: ldc             "Array index in initializer exceeds array bounds"
        //   194: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   197: pop            
        //   198: goto            261
        //   201: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aload           5
        //   207: ifnull          261
        //   210: aload           7
        //   212: ifnull          261
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload           5
        //   224: invokevirtual   java/lang/Number.intValue:()I
        //   227: aload           7
        //   229: invokevirtual   java/lang/Number.intValue:()I
        //   232: if_icmple       261
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: aload_0        
        //   243: aload_1        
        //   244: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   246: ldc             "err_array_designator_empty_range"
        //   248: ldc             "Empty index range in initializer"
        //   250: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   253: pop            
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: goto            276
        //   264: aload_0        
        //   265: aload_1        
        //   266: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ArrayIssues;.class
        //   268: ldc             "err_designator_for_scalar_init"
        //   270: ldc             "Subscripted value is not an array"
        //   272: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   275: pop            
        //   276: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  45     58     61     65     Ljava/lang/IllegalArgumentException;
        //  50     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     89     92     96     Ljava/lang/IllegalArgumentException;
        //  79     108    111    115    Ljava/lang/IllegalArgumentException;
        //  135    148    151    155    Ljava/lang/IllegalArgumentException;
        //  140    162    165    169    Ljava/lang/IllegalArgumentException;
        //  155    179    182    186    Ljava/lang/IllegalArgumentException;
        //  169    201    201    205    Ljava/lang/IllegalArgumentException;
        //  205    215    218    222    Ljava/lang/IllegalArgumentException;
        //  210    235    238    242    Ljava/lang/IllegalArgumentException;
        //  222    254    257    261    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    public void visitSynthesizeProperty(final OCSynthesizeProperty ocSynthesizeProperty) {
        super.visitSynthesizeProperty(ocSynthesizeProperty);
        this.myImplementationChecker.checkSynthesize(ocSynthesizeProperty);
    }
    
    @Override
    public void visitProtocolExpression(final OCProtocolExpression ocProtocolExpression) {
        super.visitProtocolExpression(ocProtocolExpression);
        final OCTypeElement typeElement = ocProtocolExpression.getTypeElement();
        if (typeElement != null) {
            final OCType resolve = typeElement.getType().resolve(ocProtocolExpression.getContainingFile());
            Label_0053: {
                try {
                    if (resolve instanceof OCObjectType) {
                        return;
                    }
                    final OCType ocType = resolve;
                    final boolean b = ocType.isUnknown();
                    if (!b) {
                        break Label_0053;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCType ocType = resolve;
                    final boolean b = ocType.isUnknown();
                    if (!b) {
                        this.addErrorAnnotation((PsiElement)typeElement, "CIDR", "'" + resolve.getName((PsiElement)ocProtocolExpression) + "' isn't a protocol");
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
    }
    
    @Override
    public void visitOCFile(final OCFile ocFile) {
        try {
            super.visitOCFile(ocFile);
            if (ocFile.isHeader()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCFileGlobalSymbols forFile = OCFileGlobalSymbolsCache.getInstance(ocFile.getProject()).forFile(ocFile);
        this.a(ocFile, forFile.getUndefinedClasses(), OCSymbolKind.INTERFACE);
        this.a(ocFile, forFile.getUndefinedProtocols(), OCSymbolKind.PROTOCOL);
        final List<OCIncludeDirective> doGetImports = OCImportsOptimizer.doGetImports(ocFile, false, true);
        for (final OCIncludeDirective ocIncludeDirective : doGetImports) {
            final Annotation addWarningAnnotation = this.addWarningAnnotation((PsiElement)ocIncludeDirective, OCInspections.UnusedImportStatement.class, "CIDR", "Unused import statement", ProblemHighlightType.LIKE_UNUSED_SYMBOL);
            this.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocIncludeDirective, "Remove useless import"));
            this.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCRemoveElementsIntentionAction((Collection<? extends PsiElement>)doGetImports, "Optimize imports", "Optimize imports"));
        }
    }
    
    private void a(final OCFile ocFile, final Map<String, Pair<OCSymbol, VirtualFile>> map, final OCSymbolKind ocSymbolKind) {
        for (final String s : map.keySet()) {
            final Pair<OCSymbol, VirtualFile> pair = map.get(s);
            final OCIncludeDirective includeDirective = ocFile.findIncludeDirective((VirtualFile)pair.getSecond());
            final OCSymbol ocSymbol = (OCSymbol)pair.getFirst();
            if (includeDirective != null) {
                try {
                    if (OCWorkspaceManager.getWorkspace(ocFile.getProject()).isInSDK(ocSymbol.getContainingFile())) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                Annotation annotation = null;
                if (ocSymbolKind == OCSymbolKind.INTERFACE) {
                    annotation = this.addErrorAnnotation((PsiElement)includeDirective, OCInspections.CannotResolve.class, "CIDR", "Cannot find class '" + s + "', superclass of " + ocSymbol.getNameWithKindLowercase());
                }
                else if (ocSymbolKind == OCSymbolKind.PROTOCOL) {
                    annotation = this.addWarningAnnotation((PsiElement)includeDirective, OCInspections.NotVisibleClass.class, "CIDR", "Cannot find protocol '" + s + "', conformed by " + ocSymbol.getNameWithKindLowercase());
                }
                final OCClassSymbol ocClassSymbol = OCSymbolBase.findSymbolDefinition(s, ocSymbolKind, ocFile.getProject(), ocFile.getVirtualFile());
                Label_0272: {
                    try {
                        if (ocClassSymbol == null) {
                            continue;
                        }
                        final Annotation annotation2 = annotation;
                        if (annotation2 != null) {
                            break Label_0272;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final Annotation annotation2 = annotation;
                        if (annotation2 == null) {
                            continue;
                        }
                        this.registerQuickFix(annotation, (IntentionAction)new OCImportSymbolFix((PsiElement)includeDirective, ocClassSymbol));
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
    }
    
    @Override
    public void visitCallable(final OCCallable ocCallable) {
        Label_0026: {
            try {
                super.visitCallable(ocCallable);
                if (ocCallable instanceof OCBlockExpression) {
                    break Label_0026;
                }
                final PsiElement psiElement = (PsiElement)ocCallable;
                final boolean b = psiElement instanceof OCLambdaExpression;
                if (b) {
                    break Label_0026;
                }
                break Label_0026;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement = (PsiElement)ocCallable;
                final boolean b = psiElement instanceof OCLambdaExpression;
                if (b) {
                    if (PsiTreeUtil.getContextOfType((PsiElement)ocCallable, new Class[] { OCCallable.class }) != null) {
                        return;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer((PsiElement)ocCallable, this, null);
        ocDataFlowAnalyzer.buildControlFlowGraph();
        ocDataFlowAnalyzer.analyze();
    }
    
    @Override
    public void visitDirective(final OCDirective ocDirective) {
        final IElementType elementType = ocDirective.getHeaderToken().getNode().getElementType();
        try {
            if (elementType != OCTokenTypes.ERROR_DIRECTIVE) {
                if (elementType != OCTokenTypes.WARNING_DIRECTIVE) {
                    return;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Pair<String, TextRange> content = ocDirective.getContent(true);
        try {
            if (elementType == OCTokenTypes.ERROR_DIRECTIVE) {
                this.addErrorAnnotation((PsiElement)ocDirective, (TextRange)content.getSecond(), null, "CIDR", (String)content.getFirst(), ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.addWarningAnnotation((PsiElement)ocDirective, (TextRange)content.getSecond(), null, "CIDR", (String)content.getFirst(), ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
    }
    
    @Override
    public void visitCppNewExpression(final OCCppNewExpression ocCppNewExpression) {
        super.visitCppNewExpression(ocCppNewExpression);
        final OCFile containingOCFile = ocCppNewExpression.getContainingOCFile();
        OCType ocType = ocCppNewExpression.getConstructingType().resolve((PsiFile)containingOCFile);
        if (ocType instanceof OCArrayType) {
            ocType = ((OCArrayType)ocType).getRefType();
        }
        if (ocType instanceof OCStructType) {
            final Iterator<OCStructSymbol> iterator = ((OCStructType)ocType).getStructs().iterator();
            while (iterator.hasNext()) {
                OCFileSymbols.markSymbolAsUsed(containingOCFile, iterator.next(), (PsiElement)ocCppNewExpression);
            }
            if (((OCStructType)ocType).isAbstract()) {
                this.addErrorAnnotation((PsiElement)ocCppNewExpression, "err_allocation_of_abstract_type", "Instantiating the abstract " + ((OCStructType)ocType).getStructs().get(0).getNameWithKindLowercase());
            }
            final OCReferenceElement referenceElement = ocCppNewExpression.getReferenceElement();
            OCCppChecker myCppChecker = null;
            List<OCExpression> initializers = null;
            OCSymbol resolveToSymbol = null;
            Label_0189: {
                try {
                    myCppChecker = this.myCppChecker;
                    initializers = ocCppNewExpression.getInitializers();
                    if (referenceElement != null) {
                        resolveToSymbol = referenceElement.resolveToSymbol();
                        break Label_0189;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                resolveToSymbol = null;
            }
            myCppChecker.checkConstructorCall(ocCppNewExpression, initializers, resolveToSymbol);
        }
    }
    
    @Override
    public void visitConstructorFieldInitializer(final OCConstructorFieldInitializer ocConstructorFieldInitializer) {
        super.visitConstructorFieldInitializer(ocConstructorFieldInitializer);
        final OCReferenceElement referenceElement = ocConstructorFieldInitializer.getReferenceElement();
        OCSymbol resolveToSymbol = null;
        Label_0030: {
            try {
                if (referenceElement != null) {
                    resolveToSymbol = referenceElement.resolveToSymbol();
                    break Label_0030;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            resolveToSymbol = null;
        }
        final OCSymbol ocSymbol = resolveToSymbol;
        final List<OCExpression> initializers = ocConstructorFieldInitializer.getInitializers();
        if (ocSymbol instanceof OCDeclaratorSymbol) {
            this.myCppChecker.checkTypeInitialization(ocConstructorFieldInitializer, (PsiElement)referenceElement, OCArgumentsList.getArgumentList(initializers), ocSymbol, ocSymbol.getType().resolve(ocConstructorFieldInitializer.getContainingFile()), false, (PsiElement)ocConstructorFieldInitializer);
        }
        else {
            this.myCppChecker.checkConstructorCall(ocConstructorFieldInitializer, initializers, ocSymbol);
        }
    }
    
    @Override
    public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
        try {
            super.visitCompoundInitializer(ocCompoundInitializer);
            if (ocCompoundInitializer.getParent() instanceof OCCompoundInitializer) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCType ocType = ocCompoundInitializer.inferType();
        if (ocType != null) {
            ocType = ocType.resolve(ocCompoundInitializer.getContainingFile());
        }
        new OCAnnotatingArgumentsChecker(this.myCppChecker).checkCompoundInitializer(ocCompoundInitializer, ocType, true, true);
    }
    
    @Override
    public void visitNamespaceQualifier(final OCCppNamespaceQualifier ocCppNamespaceQualifier) {
        super.visitNamespaceQualifier(ocCppNamespaceQualifier);
        if (ocCppNamespaceQualifier.getParent() instanceof OCReferenceElement) {
            for (final OCSymbol ocSymbol : ocCppNamespaceQualifier.resolveToSymbols()) {
                this.myCppChecker.checkFieldVisibility(ocSymbol, (PsiElement)ocCppNamespaceQualifier, null);
                OCFileSymbols.markSymbolAsUsed(ocCppNamespaceQualifier.getContainingOCFile(), ocSymbol, (PsiElement)ocCppNamespaceQualifier);
            }
        }
    }
    
    @Override
    public void visitTemplateArgumentsOwner(final OCTemplateArgumentsOwner p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitTemplateArgumentsOwner:(Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner.getTemplateArgumentList:()Lcom/jetbrains/cidr/lang/psi/OCTypeArgumentList;
        //    11: astore_2       
        //    12: aload_1        
        //    13: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getTypeArguments:(Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;)Ljava/util/List;
        //    16: astore_3       
        //    17: aconst_null    
        //    18: astore          4
        //    20: aload_2        
        //    21: ifnull          35
        //    24: aload_3        
        //    25: ifnonnull       40
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: return         
        //    36: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_1        
        //    41: instanceof      Lcom/jetbrains/cidr/lang/psi/OCResolvesToSymbol;
        //    44: ifeq            108
        //    47: aload_1        
        //    48: checkcast       Lcom/jetbrains/cidr/lang/psi/OCResolvesToSymbol;
        //    51: invokeinterface com/jetbrains/cidr/lang/psi/OCResolvesToSymbol.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    56: astore          5
        //    58: aload           5
        //    60: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //    63: ifeq            78
        //    66: aload           5
        //    68: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //    71: goto            79
        //    74: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aconst_null    
        //    79: astore          4
        //    81: aload           4
        //    83: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //    86: ifeq            108
        //    89: aload           4
        //    91: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //    94: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
        //    97: iconst_0       
        //    98: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   103: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   106: astore          4
        //   108: aload           4
        //   110: ifnull          133
        //   113: aload_0        
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_3        
        //   117: aload           4
        //   119: iconst_0       
        //   120: invokespecial   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.a:(Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;Lcom/jetbrains/cidr/lang/psi/OCTypeArgumentList;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Z)Z
        //   123: ifne            314
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: invokeinterface com/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner.resolveTemplateDeclarations:()Ljava/util/Collection;
        //   139: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   144: astore          5
        //   146: aload           5
        //   148: invokeinterface java/util/Iterator.hasNext:()Z
        //   153: ifeq            314
        //   156: aload           5
        //   158: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   163: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   166: astore          6
        //   168: aload           6
        //   170: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   173: ifeq            311
        //   176: aload           6
        //   178: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   181: astore          7
        //   183: aload           7
        //   185: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   190: invokeinterface java/util/List.isEmpty:()Z
        //   195: ifne            311
        //   198: aload           7
        //   200: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateSpecialization:()Ljava/util/List;
        //   205: ifnonnull       311
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload           4
        //   217: ifnull          307
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aload           4
        //   229: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getRequiredTemplateArgumentsCnt:()I
        //   234: aload_3        
        //   235: invokeinterface java/util/List.size:()I
        //   240: if_icmpgt       307
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: aload           4
        //   252: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   257: invokeinterface java/util/List.size:()I
        //   262: aload           7
        //   264: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   269: invokeinterface java/util/List.size:()I
        //   274: if_icmpge       311
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: aload           7
        //   286: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getRequiredTemplateArgumentsCnt:()I
        //   291: aload_3        
        //   292: invokeinterface java/util/List.size:()I
        //   297: if_icmpgt       311
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: aload           7
        //   309: astore          4
        //   311: goto            146
        //   314: aload           4
        //   316: ifnull          339
        //   319: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbol.NON_FANTOM_SYMBOL_CONDITION:Lcom/intellij/openapi/util/Condition;
        //   322: aload           4
        //   324: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
        //   329: ifne            344
        //   332: goto            339
        //   335: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   338: athrow         
        //   339: return         
        //   340: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: aload_0        
        //   345: aload_1        
        //   346: aload_2        
        //   347: aload_3        
        //   348: aload           4
        //   350: iconst_1       
        //   351: invokespecial   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.a:(Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;Lcom/jetbrains/cidr/lang/psi/OCTypeArgumentList;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Z)Z
        //   354: pop            
        //   355: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     28     31     35     Ljava/lang/IllegalArgumentException;
        //  24     36     36     40     Ljava/lang/IllegalArgumentException;
        //  58     74     74     78     Ljava/lang/IllegalArgumentException;
        //  108    126    129    133    Ljava/lang/IllegalArgumentException;
        //  183    208    211    215    Ljava/lang/IllegalArgumentException;
        //  198    220    223    227    Ljava/lang/IllegalArgumentException;
        //  215    243    246    250    Ljava/lang/IllegalArgumentException;
        //  227    277    280    284    Ljava/lang/IllegalArgumentException;
        //  250    300    303    307    Ljava/lang/IllegalArgumentException;
        //  314    332    335    339    Ljava/lang/IllegalArgumentException;
        //  319    340    340    344    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0215:
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
    
    private boolean a(@NotNull final OCTemplateArgumentsOwner p0, @NotNull final OCTypeArgumentList<?> p1, @NotNull final List<OCTypeArgument> p2, @NotNull final OCTemplateSymbol<?> p3, final boolean p4) {
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
        //    18: ldc             "argumentsOwner"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkArguments"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "argumentList"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkArguments"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "typeArguments"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "checkArguments"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "mainTemplate"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "checkArguments"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   180: dup            
        //   181: aload_2        
        //   182: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   185: astore          6
        //   187: aload           4
        //   189: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   194: astore          7
        //   196: aload           7
        //   198: invokeinterface java/util/List.size:()I
        //   203: istore          8
        //   205: aload           4
        //   207: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getRequiredTemplateArgumentsCnt:()I
        //   212: istore          9
        //   214: aload_2        
        //   215: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeArgumentList.getArguments:()Ljava/util/List;
        //   220: astore          10
        //   222: aload           4
        //   224: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isVariadicTemplate:()Z
        //   229: istore          11
        //   231: aload           4
        //   233: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   236: ifeq            361
        //   239: aload_1        
        //   240: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   243: ifeq            361
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: new             Ljava/util/HashMap;
        //   256: dup            
        //   257: invokespecial   java/util/HashMap.<init>:()V
        //   260: astore          12
        //   262: aload_1        
        //   263: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   266: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   271: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   274: astore          13
        //   276: aload           13
        //   278: ifnull          361
        //   281: aload           4
        //   283: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   288: aload           6
        //   290: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   293: aload           13
        //   295: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   298: aload           6
        //   300: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   303: aload           12
        //   305: aload           6
        //   307: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Ljava/util/Map;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   310: pop            
        //   311: goto            318
        //   314: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   317: athrow         
        //   318: iload           9
        //   320: ifle            361
        //   323: aload           12
        //   325: aload           7
        //   327: iload           9
        //   329: iconst_1       
        //   330: isub           
        //   331: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   336: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   341: ifeq            361
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   350: athrow         
        //   351: iinc            9, -1
        //   354: goto            318
        //   357: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: aload_3        
        //   362: invokeinterface java/util/List.size:()I
        //   367: iload           9
        //   369: if_icmpge       503
        //   372: aload_3        
        //   373: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   378: invokestatic    com/intellij/util/containers/ContainerUtil.exists:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Z
        //   381: ifne            503
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: aload_1        
        //   392: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   395: ifne            419
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   404: athrow         
        //   405: aload_1        
        //   406: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   409: ifeq            434
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: aload           4
        //   421: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   424: ifne            596
        //   427: goto            434
        //   430: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   433: athrow         
        //   434: iload           5
        //   436: ifeq            501
        //   439: goto            446
        //   442: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   445: athrow         
        //   446: aload_0        
        //   447: aload_2        
        //   448: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$TemplateArgumentsIssues;.class
        //   450: ldc             "CIDR"
        //   452: iload           9
        //   454: aload           7
        //   456: invokeinterface java/util/List.size:()I
        //   461: if_icmpge       480
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: ldc             "inspections.templateArguments.tooFewAtLeast"
        //   473: goto            482
        //   476: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   479: athrow         
        //   480: ldc             "inspections.templateArguments.tooFew"
        //   482: iconst_1       
        //   483: anewarray       Ljava/lang/Object;
        //   486: dup            
        //   487: iconst_0       
        //   488: iload           9
        //   490: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   493: aastore        
        //   494: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   497: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   500: pop            
        //   501: iconst_0       
        //   502: ireturn        
        //   503: iload           11
        //   505: ifne            596
        //   508: aload           10
        //   510: invokeinterface java/util/List.size:()I
        //   515: iload           8
        //   517: if_icmple       596
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   526: athrow         
        //   527: iload           5
        //   529: ifeq            594
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: aload_0        
        //   540: aload_2        
        //   541: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$TemplateArgumentsIssues;.class
        //   543: ldc             "CIDR"
        //   545: iload           9
        //   547: aload           7
        //   549: invokeinterface java/util/List.size:()I
        //   554: if_icmpge       573
        //   557: goto            564
        //   560: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   563: athrow         
        //   564: ldc             "inspections.templateArguments.tooManyAtMost"
        //   566: goto            575
        //   569: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   572: athrow         
        //   573: ldc             "inspections.templateArguments.tooMany"
        //   575: iconst_1       
        //   576: anewarray       Ljava/lang/Object;
        //   579: dup            
        //   580: iconst_0       
        //   581: iload           8
        //   583: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   586: aastore        
        //   587: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   590: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   593: pop            
        //   594: iconst_0       
        //   595: ireturn        
        //   596: iconst_1       
        //   597: istore          12
        //   599: iconst_0       
        //   600: istore          13
        //   602: iload           13
        //   604: aload_3        
        //   605: invokeinterface java/util/List.size:()I
        //   610: if_icmpge       1157
        //   613: iload           13
        //   615: aload           7
        //   617: invokeinterface java/util/List.size:()I
        //   622: if_icmplt       658
        //   625: goto            632
        //   628: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   631: athrow         
        //   632: aload           7
        //   634: aload           7
        //   636: invokeinterface java/util/List.size:()I
        //   641: iconst_1       
        //   642: isub           
        //   643: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   648: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   651: goto            670
        //   654: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: aload           7
        //   660: iload           13
        //   662: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   667: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   670: astore          14
        //   672: aload_3        
        //   673: iload           13
        //   675: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   680: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   683: astore          15
        //   685: iload           13
        //   687: aload           10
        //   689: invokeinterface java/util/List.size:()I
        //   694: if_icmplt       723
        //   697: aload           10
        //   699: aload           10
        //   701: invokeinterface java/util/List.size:()I
        //   706: iconst_1       
        //   707: isub           
        //   708: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   713: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   716: goto            735
        //   719: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   722: athrow         
        //   723: aload           10
        //   725: iload           13
        //   727: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   732: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   735: astore          16
        //   737: aload           14
        //   739: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   744: ifeq            775
        //   747: iload           13
        //   749: aload           7
        //   751: invokeinterface java/util/List.size:()I
        //   756: iconst_1       
        //   757: isub           
        //   758: if_icmpge       775
        //   761: goto            768
        //   764: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   767: athrow         
        //   768: goto            1157
        //   771: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   774: athrow         
        //   775: aload           14
        //   777: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   780: ifeq            1086
        //   783: aload           15
        //   785: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   788: ifeq            944
        //   791: goto            798
        //   794: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   797: athrow         
        //   798: aload           15
        //   800: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   803: aload           6
        //   805: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   808: astore          17
        //   810: aload           17
        //   812: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   815: ifeq            876
        //   818: aload           17
        //   820: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   823: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   826: astore          18
        //   828: aload           18
        //   830: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   833: ifne            1151
        //   836: aload           18
        //   838: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   841: ifeq            876
        //   844: goto            851
        //   847: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   850: athrow         
        //   851: aload           18
        //   853: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   856: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.isSynthetic:()Z
        //   859: ifeq            876
        //   862: goto            869
        //   865: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   868: athrow         
        //   869: goto            1151
        //   872: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   875: athrow         
        //   876: aload           17
        //   878: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   881: ifne            941
        //   884: aload           17
        //   886: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   889: ifne            941
        //   892: goto            899
        //   895: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   898: athrow         
        //   899: iload           5
        //   901: ifeq            938
        //   904: goto            911
        //   907: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   910: athrow         
        //   911: aload_0        
        //   912: aload           16
        //   914: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$TemplateArgumentsIssues;.class
        //   916: ldc             "CIDR"
        //   918: ldc             "inspections.templateArguments.valueInsteadOfType"
        //   920: iconst_0       
        //   921: anewarray       Ljava/lang/Object;
        //   924: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   927: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   930: pop            
        //   931: goto            938
        //   934: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   937: athrow         
        //   938: iconst_0       
        //   939: istore          12
        //   941: goto            1151
        //   944: aload           15
        //   946: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   949: ifeq            1151
        //   952: aload           14
        //   954: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   957: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   960: aload           6
        //   962: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   965: astore          17
        //   967: aload           15
        //   969: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   972: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   975: astore          18
        //   977: aload           18
        //   979: aload           6
        //   981: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   984: astore          19
        //   986: aload           19
        //   988: ifnull          1083
        //   991: aload           17
        //   993: aload           19
        //   995: aload           18
        //   997: aload           16
        //   999: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //  1002: astore          20
        //  1004: aload           20
        //  1006: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1009: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isOK:()Z
        //  1012: ifne            1083
        //  1015: aload           17
        //  1017: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1020: ifeq            1045
        //  1023: goto            1030
        //  1026: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1029: athrow         
        //  1030: aload           19
        //  1032: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1035: ifne            1083
        //  1038: goto            1045
        //  1041: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1044: athrow         
        //  1045: iload           5
        //  1047: ifeq            1080
        //  1050: goto            1057
        //  1053: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1056: athrow         
        //  1057: aload_0        
        //  1058: aload           16
        //  1060: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$TemplateArgumentsIssues;.class
        //  1062: ldc             "CIDR"
        //  1064: aload           20
        //  1066: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getMessage:()Ljava/lang/String;
        //  1069: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1072: pop            
        //  1073: goto            1080
        //  1076: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1079: athrow         
        //  1080: iconst_0       
        //  1081: istore          12
        //  1083: goto            1151
        //  1086: aload           14
        //  1088: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //  1091: ifeq            1151
        //  1094: aload           15
        //  1096: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //  1099: ifeq            1151
        //  1102: goto            1109
        //  1105: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1108: athrow         
        //  1109: iload           5
        //  1111: ifeq            1148
        //  1114: goto            1121
        //  1117: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1120: athrow         
        //  1121: aload_0        
        //  1122: aload           16
        //  1124: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$TemplateArgumentsIssues;.class
        //  1126: ldc             "CIDR"
        //  1128: ldc             "inspections.templateArguments.typeInsteadOfValue"
        //  1130: iconst_0       
        //  1131: anewarray       Ljava/lang/Object;
        //  1134: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1137: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //  1140: pop            
        //  1141: goto            1148
        //  1144: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1147: athrow         
        //  1148: iconst_0       
        //  1149: istore          12
        //  1151: iinc            13, 1
        //  1154: goto            602
        //  1157: iload           12
        //  1159: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;Lcom/jetbrains/cidr/lang/psi/OCTypeArgumentList<*>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol<*>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    173    173    177    Ljava/lang/IllegalArgumentException;
        //  231    246    249    253    Ljava/lang/IllegalArgumentException;
        //  276    311    314    318    Ljava/lang/IllegalArgumentException;
        //  318    344    347    351    Ljava/lang/IllegalArgumentException;
        //  323    357    357    361    Ljava/lang/IllegalArgumentException;
        //  361    384    387    391    Ljava/lang/IllegalArgumentException;
        //  372    398    401    405    Ljava/lang/IllegalArgumentException;
        //  391    412    415    419    Ljava/lang/IllegalArgumentException;
        //  405    427    430    434    Ljava/lang/IllegalArgumentException;
        //  419    439    442    446    Ljava/lang/IllegalArgumentException;
        //  434    464    467    471    Ljava/lang/IllegalArgumentException;
        //  446    476    476    480    Ljava/lang/IllegalArgumentException;
        //  503    520    523    527    Ljava/lang/IllegalArgumentException;
        //  508    532    535    539    Ljava/lang/IllegalArgumentException;
        //  527    557    560    564    Ljava/lang/IllegalArgumentException;
        //  539    569    569    573    Ljava/lang/IllegalArgumentException;
        //  602    625    628    632    Ljava/lang/IllegalArgumentException;
        //  613    654    654    658    Ljava/lang/IllegalArgumentException;
        //  685    719    719    723    Ljava/lang/IllegalArgumentException;
        //  737    761    764    768    Ljava/lang/IllegalArgumentException;
        //  747    771    771    775    Ljava/lang/IllegalArgumentException;
        //  775    791    794    798    Ljava/lang/IllegalArgumentException;
        //  828    844    847    851    Ljava/lang/IllegalArgumentException;
        //  836    862    865    869    Ljava/lang/IllegalArgumentException;
        //  851    872    872    876    Ljava/lang/IllegalArgumentException;
        //  876    892    895    899    Ljava/lang/IllegalArgumentException;
        //  884    904    907    911    Ljava/lang/IllegalArgumentException;
        //  899    931    934    938    Ljava/lang/IllegalArgumentException;
        //  1004   1023   1026   1030   Ljava/lang/IllegalArgumentException;
        //  1015   1038   1041   1045   Ljava/lang/IllegalArgumentException;
        //  1030   1050   1053   1057   Ljava/lang/IllegalArgumentException;
        //  1045   1073   1076   1080   Ljava/lang/IllegalArgumentException;
        //  1086   1102   1105   1109   Ljava/lang/IllegalArgumentException;
        //  1094   1114   1117   1121   Ljava/lang/IllegalArgumentException;
        //  1109   1141   1144   1148   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0391:
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
    public void visitBoxedExpression(final OCBoxedExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCBoxedExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     6: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsObjectLiterals:(Lcom/intellij/psi/PsiFile;)Z
        //     9: ifne            29
        //    12: aload_0        
        //    13: aload_1        
        //    14: ldc             "CIDR"
        //    16: ldc             "Expression literals are not supported by the compiler"
        //    18: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    21: pop            
        //    22: goto            126
        //    25: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_1        
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCBoxedExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    35: astore_2       
        //    36: aload_2        
        //    37: ifnull          126
        //    40: aload_2        
        //    41: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    46: astore_3       
        //    47: aload_3        
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //    52: ifne            126
        //    55: aload_3        
        //    56: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToChar:()Z
        //    59: ifne            126
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_3        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    73: ifne            126
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_0        
        //    84: aload_2        
        //    85: ldc             "err_objc_illegal_boxed_expression_type"
        //    87: new             Ljava/lang/StringBuilder;
        //    90: dup            
        //    91: invokespecial   java/lang/StringBuilder.<init>:()V
        //    94: ldc             "Type '"
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: aload_3        
        //   100: aload_2        
        //   101: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: ldc             "' is illegal for a boxed expression"
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   115: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   118: pop            
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_0        
        //   127: aload_1        
        //   128: invokespecial   com/jetbrains/cidr/lang/daemon/OCAnnotator.visitBoxedExpression:(Lcom/jetbrains/cidr/lang/psi/OCBoxedExpression;)V
        //   131: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      25     25     29     Ljava/lang/IllegalArgumentException;
        //  47     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     76     79     83     Ljava/lang/IllegalArgumentException;
        //  69     119    122    126    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    public void visitGenericSelectionExpression(final OCGenericSelectionExpression ocGenericSelectionExpression) {
        if (!this.checkAssociationList(ocGenericSelectionExpression)) {
            final OCExpression controllingExpression = ocGenericSelectionExpression.getControllingExpression();
            if (controllingExpression != null) {
                final OCType resolvedType = controllingExpression.getResolvedType();
                boolean b = false;
                Label_0049: {
                    try {
                        if (ocGenericSelectionExpression.getAssociationByType(resolvedType) == null) {
                            b = true;
                            break Label_0049;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    b = false;
                }
                final boolean b2 = b;
                try {
                    if (b2) {
                        this.addErrorAnnotation((PsiElement)controllingExpression, "err_generic_sel_no_match", "Controlling expression type '" + a(resolvedType, (PsiElement)ocGenericSelectionExpression) + "' not compatible with any generic association type");
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        super.visitGenericSelectionExpression(ocGenericSelectionExpression);
    }
    
    public boolean checkAssociationList(final OCGenericSelectionExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCGenericSelectionExpression.getAssociations:()Ljava/util/List;
        //     6: astore_2       
        //     7: aload_2        
        //     8: invokeinterface java/util/List.size:()I
        //    13: istore_3       
        //    14: iconst_0       
        //    15: istore          4
        //    17: iload_3        
        //    18: iconst_1       
        //    19: isub           
        //    20: istore          5
        //    22: iload           5
        //    24: iflt            373
        //    27: aload_2        
        //    28: iload           5
        //    30: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    35: checkcast       Lcom/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation;
        //    38: astore          6
        //    40: aload           6
        //    42: invokeinterface com/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation.isDefault:()Z
        //    47: istore          7
        //    49: iload           7
        //    51: ifeq            62
        //    54: aconst_null    
        //    55: goto            69
        //    58: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload           6
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation.getAssociationResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    69: astore          8
        //    71: aload           6
        //    73: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //    75: invokestatic    com/intellij/psi/util/PsiTreeUtil.findChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    78: checkcast       Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    81: astore          9
        //    83: aload           8
        //    85: ifnull          147
        //    88: aload           8
        //    90: aload           6
        //    92: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/intellij/psi/PsiElement;)Z
        //    95: ifeq            147
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_0        
        //   106: aload           9
        //   108: ldc             "err_assoc_type_incomplete"
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: ldc             "Type '"
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: aload           8
        //   124: aload           6
        //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: ldc             "' in generic association incomplete"
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   140: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   143: pop            
        //   144: iconst_1       
        //   145: istore          4
        //   147: aload           8
        //   149: astore          10
        //   151: aload           10
        //   153: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   156: ifeq            235
        //   159: aload           10
        //   161: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   164: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //   167: ifne            222
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload_0        
        //   178: aload           9
        //   180: ldc             "err_assoc_type_incomplete"
        //   182: new             Ljava/lang/StringBuilder;
        //   185: dup            
        //   186: invokespecial   java/lang/StringBuilder.<init>:()V
        //   189: ldc             "Type '"
        //   191: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   194: aload           10
        //   196: aload           6
        //   198: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: ldc             "' in generic association incomplete"
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   212: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   215: pop            
        //   216: iconst_1       
        //   217: istore          4
        //   219: goto            235
        //   222: aload           10
        //   224: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   227: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   230: astore          10
        //   232: goto            151
        //   235: aload_2        
        //   236: iconst_0       
        //   237: iload           5
        //   239: invokeinterface java/util/List.subList:(II)Ljava/util/List;
        //   244: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   249: iload           7
        //   251: aload           8
        //   253: invokedynamic   test:(ZLcom/jetbrains/cidr/lang/types/OCType;)Ljava/util/function/Predicate;
        //   258: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //   263: invokeinterface java/util/stream/Stream.findFirst:()Ljava/util/Optional;
        //   268: astore          10
        //   270: aload           10
        //   272: invokevirtual   java/util/Optional.isPresent:()Z
        //   275: ifeq            367
        //   278: aload           10
        //   280: invokevirtual   java/util/Optional.get:()Ljava/lang/Object;
        //   283: checkcast       Lcom/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation;
        //   286: astore          11
        //   288: aload           11
        //   290: invokeinterface com/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation.isDefault:()Z
        //   295: ifeq            314
        //   298: aload_0        
        //   299: aload           6
        //   301: aconst_null    
        //   302: ldc             "Duplicate default generic association"
        //   304: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   307: pop            
        //   308: iconst_1       
        //   309: istore          4
        //   311: goto            367
        //   314: aload_0        
        //   315: aload           9
        //   317: ldc             "err_assoc_compatible_types"
        //   319: new             Ljava/lang/StringBuilder;
        //   322: dup            
        //   323: invokespecial   java/lang/StringBuilder.<init>:()V
        //   326: ldc             "Type '"
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: aload           6
        //   333: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.a:(Lcom/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation;)Ljava/lang/String;
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: ldc             "' in generic association compatible with previously specified type '"
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: aload           11
        //   346: invokestatic    com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.a:(Lcom/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation;)Ljava/lang/String;
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: ldc             "'"
        //   354: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   357: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   360: invokevirtual   com/jetbrains/cidr/lang/daemon/OCErrorAnnotator.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   363: pop            
        //   364: iconst_1       
        //   365: istore          4
        //   367: iinc            5, -1
        //   370: goto            22
        //   373: iload           4
        //   375: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  49     58     58     62     Ljava/lang/IllegalArgumentException;
        //  83     98     101    105    Ljava/lang/IllegalArgumentException;
        //  151    170    173    177    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    private static String a(@NotNull final OCGenericSelectionAssociation ocGenericSelectionAssociation) {
        try {
            if (ocGenericSelectionAssociation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "a", "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator", "getTypeName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocGenericSelectionAssociation.isDefault()) {
                return OCTokenTypes.DEFAULT_KEYWORD.getName();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCType associationResolvedType = ocGenericSelectionAssociation.getAssociationResolvedType();
        try {
            if (associationResolvedType != null) {
                return associationResolvedType.getBestNameInContext((PsiElement)ocGenericSelectionAssociation);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    private static String a(@NotNull OCType decayType, @NotNull final PsiElement psiElement) {
        try {
            if (decayType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator", "getTypeName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/daemon/OCErrorAnnotator", "getTypeName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        decayType = OCTypeUtils.decayType(decayType, psiElement.getProject());
        return decayType.getBestNameInContext(psiElement);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
