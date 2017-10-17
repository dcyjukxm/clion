// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCLambdaIntroducer;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.Processor;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.util.Comparing;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCDeclaratorImpl extends OCElementWithReferenceBase implements OCDeclarator
{
    private static final MyResolver resolver;
    
    public OCDeclaratorImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCType getType() {
        Label_0064: {
            OCIntType ocIntType = null;
            Label_0029: {
                try {
                    if (!(this.getParent().getParent() instanceof OCEnum)) {
                        break Label_0064;
                    }
                    ocIntType = OCIntType.INT;
                    if (ocIntType == null) {
                        break Label_0029;
                    }
                    return ocIntType;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    ocIntType = OCIntType.INT;
                    if (ocIntType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getType"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return ocIntType;
        }
        final OCSymbol localSymbol = this.getLocalSymbol();
        OCType ocType = null;
        Label_0089: {
            try {
                if (localSymbol != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = localSymbol.getType());
                    break Label_0089;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            OCType ocType2;
            ocType = (ocType2 = OCUnknownType.INSTANCE);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getType"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return ocType;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType() {
        OCType resolve;
        try {
            resolve = this.getType().resolve(this.getContainingFile());
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getResolvedType"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolve;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getResolvedType"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCType resolve;
        try {
            resolve = this.getType().resolve(ocResolveContext);
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getResolvedType"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return resolve;
    }
    
    public OCSymbol getSymbol() {
        try {
            if (!this.isValid()) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = this.getContainingOCFile().findSymbol((OCElement)this, OCSymbol.class);
        try {
            if (symbol != null) {
                return symbol;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return this.getLocalSymbol();
    }
    
    @Nullable
    @Override
    public OCSymbol getLocalSymbol() {
        OCFile containingOCFile = this.getContainingOCFile();
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            final /* synthetic */ String val$name = OCDeclaratorImpl.this.getSymbolName();
            
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol.getOffset() == OCDeclaratorImpl.this.getTextOffset() && Comparing.equal(this.val$name, ocSymbol.getName());
            }
        };
        final ASTNode treeParent = this.getNode().getTreeParent();
        final OCLanguageKind kind = containingOCFile.getKind();
        final PsiElement context = containingOCFile.getContext();
        if (context != null) {
            containingOCFile = (OCFile)context.getContainingFile();
        }
        new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(kind, (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(treeParent), (BuilderDriverBase.NamedNodeStructure<Object>)BuilderDriverBase.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)findFirstProcessor).processDeclaration(treeParent, (Processor<OCSymbol>)findFirstProcessor, this.a());
        final OCSymbol ocSymbol = (OCSymbol)findFirstProcessor.getFoundValue();
        if (ocSymbol instanceof OCDeclaratorSymbol) {
            final OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCLocalScopeable.class, OCEnum.class });
            if (ocElement instanceof OCEnum) {
                final OCStructSymbol ocStructSymbol = ((OCEnum)ocElement).getSymbol();
                Label_0190: {
                    try {
                        if (ocStructSymbol == null) {
                            break Label_0190;
                        }
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final OCSymbolKind ocSymbolKind = ocStructSymbol2.getKind();
                        final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                        if (ocSymbolKind == ocSymbolKind2) {
                            break Label_0190;
                        }
                        break Label_0190;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final OCSymbolKind ocSymbolKind = ocStructSymbol2.getKind();
                        final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                        if (ocSymbolKind == ocSymbolKind2) {
                            ((OCDeclaratorSymbol)ocSymbol).setType(ocStructSymbol.getType());
                            return ocSymbol;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                ((OCDeclaratorSymbol)ocSymbol).setType(OCIntType.INT);
            }
        }
        return ocSymbol;
    }
    
    @Nullable
    @Override
    protected PsiReference createReference() {
        return (PsiReference)new MyReference();
    }
    
    @NotNull
    private BuilderDriverBase.DeclarationContext a() {
        Object parent = null;
        Label_0022: {
            try {
                if (this.getParent() instanceof OCFunctionDefinition) {
                    parent = this.getParent();
                    break Label_0022;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            parent = this;
        }
        final Pair<List<PsiElement>, OCSymbolKind> scopeAndKind = OCCodeInsightUtil.getScopeAndKind((PsiElement)parent);
        final PsiElement psiElement = (PsiElement)ContainerUtil.getFirstItem((List)scopeAndKind.getFirst());
        final OCSymbolKind ocSymbolKind = (OCSymbolKind)scopeAndKind.getSecond();
        final OCSymbolWithQualifiedName globalContextFromLocal = OCSymbolReferenceResolver.getGlobalContextFromLocal(this.getParent());
        OCSymbolKind ocSymbolKind2 = null;
        PsiElement psiElement2 = null;
        OCSymbolWithQualifiedName ocSymbolWithQualifiedName = null;
        OCVisibility ocVisibility = null;
        Object o = null;
        Label_0083: {
            try {
                ocSymbolKind2 = ocSymbolKind;
                psiElement2 = psiElement;
                ocSymbolWithQualifiedName = globalContextFromLocal;
                ocVisibility = null;
                if (psiElement != null) {
                    o = this;
                    break Label_0083;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            o = null;
        }
        final BuilderDriverBase.DeclarationContext declarationContext = new BuilderDriverBase.DeclarationContext(ocSymbolKind2, psiElement2, ocSymbolWithQualifiedName, ocVisibility, (PsiElement)o, false);
        declarationContext.setTemplateValueParameter(this.getParent().getParent() instanceof OCTemplateParameterList);
        final PsiElement parent2 = this.getParent().getParent().getParent();
        if (parent2 instanceof OCForeachStatement) {
            final OCExpression collectionExpression = ((OCForeachStatement)parent2).getCollectionExpression();
            BuilderDriverBase.DeclarationContext declarationContext2 = null;
            ASTNode node = null;
            Label_0164: {
                try {
                    declarationContext2 = declarationContext;
                    if (collectionExpression != null) {
                        node = collectionExpression.getNode();
                        break Label_0164;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                node = null;
            }
            declarationContext2.setForCollection(node);
        }
        Label_0230: {
            Label_0192: {
                try {
                    if (!(this.getParent() instanceof OCParameterDeclaration)) {
                        break Label_0230;
                    }
                    final PsiElement psiElement3 = parent2;
                    final boolean b = psiElement3 instanceof OCLambdaExpression;
                    if (b) {
                        break Label_0192;
                    }
                    break Label_0230;
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                try {
                    final PsiElement psiElement3 = parent2;
                    final boolean b = psiElement3 instanceof OCLambdaExpression;
                    if (b) {
                        declarationContext.setLambdaParameterIndex(((OCParameterList)this.getParent().getParent()).getParameterDeclarations().indexOf(this.getParent()));
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (this.getParent().getParent() instanceof OCLambdaIntroducer) {
                    declarationContext.setLambdaInitCapture(true);
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        BuilderDriverBase.DeclarationContext declarationContext3;
        try {
            declarationContext3 = declarationContext;
            if (declarationContext3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getDeclarationContext"));
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        return declarationContext3;
    }
    
    @NotNull
    @Override
    public OCType getRawType() {
        OCType type;
        try {
            type = this.getType();
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getRawType"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return type;
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArrayLengths() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //    12: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    17: astore_2       
        //    18: aconst_null    
        //    19: astore_3       
        //    20: iconst_0       
        //    21: istore          4
        //    23: aload_2        
        //    24: ifnull          158
        //    27: aload_2        
        //    28: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    33: astore          5
        //    35: aload           5
        //    37: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //    40: if_acmpne       46
        //    43: iconst_1       
        //    44: istore          4
        //    46: aload           5
        //    48: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    51: if_acmpne       59
        //    54: aconst_null    
        //    55: astore_3       
        //    56: goto            148
        //    59: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //    62: aload           5
        //    64: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    67: ifeq            83
        //    70: aload_2        
        //    71: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    76: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    79: astore_3       
        //    80: goto            148
        //    83: aload           5
        //    85: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    88: if_acmpne       133
        //    91: iload           4
        //    93: ifeq            114
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   102: athrow         
        //   103: aload_3        
        //   104: ifnull          127
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   113: athrow         
        //   114: aload_1        
        //   115: aload_3        
        //   116: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   119: pop            
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   126: athrow         
        //   127: iconst_0       
        //   128: istore          4
        //   130: goto            148
        //   133: aload           5
        //   135: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   138: if_acmpne       148
        //   141: goto            158
        //   144: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   147: athrow         
        //   148: aload_2        
        //   149: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   154: astore_2       
        //   155: goto            23
        //   158: aload_1        
        //   159: dup            
        //   160: ifnonnull       197
        //   163: new             Ljava/lang/IllegalStateException;
        //   166: dup            
        //   167: ldc             "@NotNull method %s.%s must not return null"
        //   169: ldc             2
        //   171: anewarray       Ljava/lang/Object;
        //   174: dup            
        //   175: ldc             0
        //   177: ldc             "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl"
        //   179: aastore        
        //   180: dup            
        //   181: ldc             1
        //   183: ldc             "getArrayLengths"
        //   185: aastore        
        //   186: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   189: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   192: athrow         
        //   193: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   196: athrow         
        //   197: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  83     96     99     103    Lcom/intellij/util/IncorrectOperationException;
        //  91     107    110    114    Lcom/intellij/util/IncorrectOperationException;
        //  103    120    123    127    Lcom/intellij/util/IncorrectOperationException;
        //  133    144    144    148    Lcom/intellij/util/IncorrectOperationException;
        //  158    193    193    197    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0103:
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
    public OCExpression getInitializer() {
        boolean b = false;
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            if (elementType == OCTokenTypes.EQ) {
                b = true;
            }
            else {
                Label_0056: {
                    try {
                        if (!b) {
                            continue;
                        }
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                        final boolean b2 = set.contains((IElementType)ocPunctuatorElementType);
                        if (b2) {
                            break Label_0056;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                        final boolean b2 = set.contains((IElementType)ocPunctuatorElementType);
                        if (b2) {
                            return (OCExpression)astNode.getPsi();
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public OCArgumentList getArgumentList() {
        return this.findChildByType(OCElementTypes.ARGUMENT_LIST);
    }
    
    @Override
    public OCCompoundInitializer getInitializerList() {
        return this.findChildByType(OCElementTypes.COMPOUND_INITIALIZER);
    }
    
    @Override
    public boolean isExplicitConstructorCall() {
        Label_0021: {
            try {
                if (this.getArgumentList() != null) {
                    break Label_0021;
                }
                final OCDeclaratorImpl ocDeclaratorImpl = this;
                final OCCompoundInitializer ocCompoundInitializer = ocDeclaratorImpl.getInitializerList();
                if (ocCompoundInitializer != null) {
                    break Label_0021;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCDeclaratorImpl ocDeclaratorImpl = this;
                final OCCompoundInitializer ocCompoundInitializer = ocDeclaratorImpl.getInitializerList();
                if (ocCompoundInitializer != null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public List<OCExpression> getInitializers() {
        final OCArgumentList argumentList = this.getArgumentList();
        Label_0061: {
            List<OCExpression> list2 = null;
            Label_0026: {
                try {
                    if (argumentList == null) {
                        break Label_0061;
                    }
                    final OCArgumentList list = argumentList;
                    list2 = list.getArguments();
                    if (list2 == null) {
                        break Label_0026;
                    }
                    return list2;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCArgumentList list = argumentList;
                    list2 = list.getArguments();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getInitializers"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return list2;
        }
        final OCCompoundInitializer initializerList = this.getInitializerList();
        Label_0120: {
            List<OCCompoundInitializer> list3 = null;
            Label_0085: {
                try {
                    if (initializerList == null) {
                        break Label_0120;
                    }
                    final OCCompoundInitializer ocCompoundInitializer = initializerList;
                    list3 = Collections.singletonList(ocCompoundInitializer);
                    if (list3 == null) {
                        break Label_0085;
                    }
                    return (List<OCExpression>)list3;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCCompoundInitializer ocCompoundInitializer = initializerList;
                    list3 = Collections.singletonList(ocCompoundInitializer);
                    if (list3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getInitializers"));
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            return (List<OCExpression>)list3;
        }
        final OCExpression initializer = this.getInitializer();
        List<OCExpression> list4 = null;
        Label_0143: {
            try {
                if (initializer != null) {
                    final List<OCExpression> list5;
                    list4 = (list5 = Collections.singletonList(initializer));
                    break Label_0143;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            List<OCExpression> list5;
            list4 = (list5 = Collections.emptyList());
            try {
                if (list5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getInitializers"));
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        return list4;
    }
    
    @Override
    public OCParameterList getParameterList() {
        return this.findChildByType(OCElementTypes.PARAMETER_LIST);
    }
    
    @Override
    public boolean isPointerToFunction() {
        final PsiElement childByType = this.findChildByType(OCTokenTypes.LPAR);
        final PsiElement childByType2 = this.findChildByType(OCTokenTypes.FUNCTION_PTR_MODIFIERS);
        final PsiElement nameIdentifier = this.getNameIdentifier();
        Label_0036: {
            try {
                if (childByType == null) {
                    return false;
                }
                final PsiElement psiElement = childByType2;
                if (psiElement != null) {
                    break Label_0036;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = childByType2;
                if (psiElement == null) {
                    return false;
                }
                if (nameIdentifier == null) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        final int startOffset = OCElementUtil.getRangeWithMacros(childByType).getStartOffset();
        final int startOffset2 = OCElementUtil.getRangeWithMacros(childByType2).getStartOffset();
        final int startOffset3 = OCElementUtil.getRangeWithMacros(nameIdentifier).getStartOffset();
        Label_0095: {
            try {
                if (startOffset > startOffset2) {
                    return false;
                }
                final int n = startOffset2;
                final int n2 = startOffset3;
                if (n <= n2) {
                    break Label_0095;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final int n = startOffset2;
                final int n2 = startOffset3;
                if (n <= n2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Override
    public boolean isPossibleStructMember() {
        try {
            if (this.getNamespaceQualifier() != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return PsiTreeUtil.getContextOfType(this.getExtendedContext(), true, new Class[] { OCStructLike.class, OCFunctionDefinition.class }) instanceof OCStructLike;
    }
    
    @NotNull
    @Override
    public PsiElement getExtendedContext() {
        final PsiElement parent = this.getParent();
        Object o = null;
        Label_0041: {
            Label_0032: {
                try {
                    if (!(parent instanceof OCFunctionDeclaration)) {
                        break Label_0032;
                    }
                    final PsiElement psiElement = parent;
                    final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)psiElement;
                    final OCDeclarator ocDeclarator = ocFunctionDeclaration.getDeclarator();
                    final OCDeclaratorImpl ocDeclaratorImpl = this;
                    if (ocDeclarator == ocDeclaratorImpl) {
                        break Label_0032;
                    }
                    break Label_0032;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = parent;
                    final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)psiElement;
                    final OCDeclarator ocDeclarator = ocFunctionDeclaration.getDeclarator();
                    final OCDeclaratorImpl ocDeclaratorImpl = this;
                    if (ocDeclarator == ocDeclaratorImpl) {
                        final Object o2;
                        o = (o2 = parent);
                        break Label_0041;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            o = this;
            Object o2 = this;
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getExtendedContext"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return (PsiElement)o;
    }
    
    @NotNull
    @Override
    public String getName() {
        String identifierName;
        try {
            identifierName = OCElementUtil.getIdentifierName(this.getNameIdentifier());
            if (identifierName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return identifierName;
    }
    
    @NotNull
    public String getSymbolName() {
        String identifierName;
        try {
            identifierName = OCElementUtil.getIdentifierName(this.findReferenceTokenInCall());
            if (identifierName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getSymbolName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return identifierName;
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        return this.findNameStartTokenInCall();
    }
    
    @Override
    public OCCppNamespaceQualifier getNamespaceQualifier() {
        return this.findChildByType(OCElementTypes.CPP_NAMESPACE_QUALIFIER);
    }
    
    @NotNull
    @Override
    public String getModifiersText() {
        ASTNode astNode = this.getNode().getFirstChildNode();
        final StringBuilder sb = new StringBuilder();
        while (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (!OCTokenTypes.DECLARATOR_MODIFIERS.contains(elementType)) {
                    if (!OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(elementType)) {
                        break;
                    }
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            sb.append(astNode.getText());
            astNode = astNode.getTreeNext();
        }
        String trim;
        try {
            trim = sb.toString().trim();
            if (trim == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "getModifiersText"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return trim;
    }
    
    @Override
    public int getTextOffset() {
        return OCSymbolOffsetUtil.getTextOffset(this.getComplexOffset());
    }
    
    @Override
    public long getComplexOffset() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier == null) {
                return super.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSymbolOffsetUtil.getComplexOffset(nameIdentifier);
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Nullable
    public <T extends PsiElement> T findChildByType(final IElementType type) {
        return super.findChildByType(type);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitDeclarator(this);
    }
    
    @Nullable
    @Override
    public OCTemplateArgumentList getTemplateArgumentList() {
        return this.findChildByType(OCElementTypes.TEMPLATE_ARGUMENT_LIST);
    }
    
    static {
        resolver = new MyResolver();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    private class MyReference implements OCReference
    {
        public PsiElement getElement() {
            return (PsiElement)OCDeclaratorImpl.this;
        }
        
        public TextRange getRangeInElement() {
            final PsiElement nameIdentifier = OCDeclaratorImpl.this.getNameIdentifier();
            try {
                if (nameIdentifier != null) {
                    return TextRange.from(nameIdentifier.getStartOffsetInParent(), nameIdentifier.getTextLength());
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return TextRange.EMPTY_RANGE;
        }
        
        public PsiElement resolve() {
            final OCSymbol resolveToSymbol = this.resolveToSymbol();
            try {
                if (resolveToSymbol != null) {
                    return resolveToSymbol.locateDefinition();
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return null;
        }
        
        @NotNull
        public String getCanonicalText() {
            String name;
            try {
                name = OCDeclaratorImpl.this.getName();
                if (name == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference", "getCanonicalText"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return name;
        }
        
        public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
            return (PsiElement)OCDeclaratorImpl.this;
        }
        
        @Override
        public PsiElement bindToSymbol(@NotNull final OCSymbol p0) {
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
            //    18: ldc             "symbol"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "bindToSymbol"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    43: athrow         
            //    44: getstatic       com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.$assertionsDisabled:Z
            //    47: ifne            100
            //    50: aload_1        
            //    51: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    56: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    59: if_acmpeq       100
            //    62: goto            69
            //    65: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    68: athrow         
            //    69: aload_1        
            //    70: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    75: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
            //    78: ifne            100
            //    81: goto            88
            //    84: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    87: athrow         
            //    88: new             Ljava/lang/AssertionError;
            //    91: dup            
            //    92: invokespecial   java/lang/AssertionError.<init>:()V
            //    95: athrow         
            //    96: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    99: athrow         
            //   100: aload_0        
            //   101: aload_1        
            //   102: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   107: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.handleElementRename:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
            //   110: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                           
            //  -----  -----  -----  -----  -----------------------------------------------
            //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
            //  44     62     65     69     Lcom/intellij/util/IncorrectOperationException;
            //  50     81     84     88     Lcom/intellij/util/IncorrectOperationException;
            //  69     96     96     100    Lcom/intellij/util/IncorrectOperationException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference", "bindToElement"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            try {
                if (symbol != null) {
                    return this.bindToSymbol(symbol);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            return psiElement;
        }
        
        public boolean isReferenceTo(final PsiElement p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
            //     4: ifne            13
            //     7: iconst_0       
            //     8: ireturn        
            //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    12: athrow         
            //    13: aload_1        
            //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
            //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    22: astore_2       
            //    23: aload_0        
            //    24: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    27: astore_3       
            //    28: aload_3        
            //    29: aload_2        
            //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
            //    33: ifeq            42
            //    36: iconst_1       
            //    37: ireturn        
            //    38: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    41: athrow         
            //    42: aload_3        
            //    43: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //    46: ifeq            157
            //    49: aload_2        
            //    50: ifnull          157
            //    53: goto            60
            //    56: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    59: athrow         
            //    60: aload_3        
            //    61: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
            //    66: ifeq            157
            //    69: goto            76
            //    72: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    75: athrow         
            //    76: aload_2        
            //    77: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
            //    82: ifeq            157
            //    85: goto            92
            //    88: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    91: athrow         
            //    92: aload_3        
            //    93: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //    99: astore          4
            //   101: aload_2        
            //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   105: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   108: astore          5
            //   110: aload           4
            //   112: aload           5
            //   114: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
            //   117: ifeq            155
            //   120: aload_3        
            //   121: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   126: astore          6
            //   128: aload_2        
            //   129: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   134: astore          7
            //   136: aload           6
            //   138: aload           7
            //   140: aload_1        
            //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
            //   144: ifne            153
            //   147: iconst_0       
            //   148: ireturn        
            //   149: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //   152: athrow         
            //   153: iconst_1       
            //   154: ireturn        
            //   155: iconst_0       
            //   156: ireturn        
            //   157: iconst_0       
            //   158: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                           
            //  -----  -----  -----  -----  -----------------------------------------------
            //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
            //  28     38     38     42     Lcom/intellij/util/IncorrectOperationException;
            //  42     53     56     60     Lcom/intellij/util/IncorrectOperationException;
            //  49     69     72     76     Lcom/intellij/util/IncorrectOperationException;
            //  60     85     88     92     Lcom/intellij/util/IncorrectOperationException;
            //  136    149    149    153    Lcom/intellij/util/IncorrectOperationException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0060:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public Object[] getVariants() {
            Object[] empty_OBJECT_ARRAY;
            try {
                empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
                if (empty_OBJECT_ARRAY == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference", "getVariants"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return empty_OBJECT_ARRAY;
        }
        
        public boolean isSoft() {
            return false;
        }
        
        public OCSymbol resolveToSymbol() {
            return ResolveCache.getInstance(OCDeclaratorImpl.this.getProject()).resolveWithCaching(this, (ResolveCache.AbstractResolver<MyReference, OCSymbol>)OCDeclaratorImpl.resolver, false, false);
        }
        
        public OCSymbol doResolveToSymbol() {
            OCType ocType = OCDeclaratorImpl.this.getResolvedType();
            if (ocType instanceof OCCppReferenceType) {
                ocType = ((OCCppReferenceType)ocType).getRefType();
            }
            try {
                if (!(ocType instanceof OCStructType)) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final PsiElement parent = OCDeclaratorImpl.this.getParent();
            Label_0076: {
                Label_0070: {
                    try {
                        if (!(parent instanceof OCDeclaration)) {
                            break Label_0070;
                        }
                        final PsiElement psiElement = parent;
                        final OCDeclaration ocDeclaration = (OCDeclaration)psiElement;
                        final boolean b = ocDeclaration.isTypedef();
                        if (b) {
                            break Label_0070;
                        }
                        break Label_0076;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final PsiElement psiElement = parent;
                        final OCDeclaration ocDeclaration = (OCDeclaration)psiElement;
                        final boolean b = ocDeclaration.isTypedef();
                        if (b) {
                            return null;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    if (parent.getParent() instanceof OCStruct) {
                        return null;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            return ((OCStructType)ocType).findConstructor(OCArgumentsList.getArgumentList(OCDeclaratorImpl.this.getInitializers()), new OCResolveContext((PsiElement)OCDeclaratorImpl.this), OCDeclaratorImpl.this.isExplicitConstructorCall(), false, null);
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!OCDeclaratorImpl.class.desiredAssertionStatus()) {
                        $assertionsDisabled2 = true;
                        break Label_0017;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                $assertionsDisabled2 = false;
            }
            $assertionsDisabled = $assertionsDisabled2;
        }
        
        private static IncorrectOperationException a(final IncorrectOperationException ex) {
            return ex;
        }
    }
    
    private static class MyResolver implements ResolveCache.AbstractResolver<MyReference, OCSymbol>
    {
        @Override
        public OCSymbol resolve(@NotNull final MyReference myReference, final boolean b) {
            try {
                if (myReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyResolver", "resolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myReference.doResolveToSymbol();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
