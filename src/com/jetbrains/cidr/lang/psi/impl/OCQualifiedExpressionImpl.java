// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.resolve.references.OCReferenceWithContext;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCTypeGuesser;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.Collection;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentsOwner;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import org.jetbrains.annotations.NonNls;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;

public class OCQualifiedExpressionImpl extends OCExpressionWithReferenceBase implements OCQualifiedExpression, OCExpectedTypeUtil.Expectable
{
    private static final Logger LOG;
    private static final Condition<OCSymbol> CAN_BE_STRUCTURE_FIELD;
    
    public OCQualifiedExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCExpression getQualifier() {
        final OCExpression ocExpression = this.findChildByType(OCElementTypes.EXPRESSIONS);
        OCExpression ocExpression2 = null;
        Label_0027: {
            Logger log;
            try {
                log = OCQualifiedExpressionImpl.LOG;
                if (ocExpression != null) {
                    final boolean b = true;
                    break Label_0027;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final boolean b = false;
            try {
                log.assertTrue(b, (Object)("Qualifier is null in: " + this.getText()));
                ocExpression2 = ocExpression;
                if (ocExpression2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getQualifier"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return ocExpression2;
    }
    
    @NotNull
    @Override
    public OCPunctuatorElementType getQualifyingTokenKind() {
        OCPunctuatorElementType ocPunctuatorElementType;
        try {
            ocPunctuatorElementType = (OCPunctuatorElementType)this.getQualifyingToken().getElementType();
            if (ocPunctuatorElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getQualifyingTokenKind"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return ocPunctuatorElementType;
    }
    
    @NotNull
    @Override
    public ASTNode getQualifyingToken() {
        for (ASTNode astNode = ((PsiElement)this.getQualifyingElement()).getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            ASTNode astNode2 = null;
            Label_0047: {
                try {
                    if (!(elementType instanceof OCPunctuatorElementType)) {
                        continue;
                    }
                    astNode2 = astNode;
                    if (astNode2 == null) {
                        break Label_0047;
                    }
                    return astNode2;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    astNode2 = astNode;
                    if (astNode2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getQualifyingToken"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return astNode2;
        }
        try {
            assert false;
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        ASTNode astNode3;
        try {
            astNode3 = null;
            if (astNode3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getQualifyingToken"));
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return astNode3;
    }
    
    @Nullable
    @Override
    public OCType getQualifierContainerType(@Nullable final Ref<Boolean> ref) {
        return this.a(this.getQualifier().getResolvedType(), null, ref);
    }
    
    @Nullable
    private OCType a(OCType ocType, @Nullable final OCPunctuatorElementType ocPunctuatorElementType, final Ref<Boolean> ref) {
        try {
            if (ref != null) {
                ref.set((Object)false);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        if (ocType instanceof OCCppReferenceType) {
            ocType = ((OCCppReferenceType)ocType).getRefType();
        }
        Label_0056: {
            try {
                if (!(ocType instanceof OCStructType)) {
                    break Label_0056;
                }
                final OCType ocType2 = ocType;
                final OCQualifiedExpressionImpl ocQualifiedExpressionImpl = this;
                final boolean b = ocType2.isSubclassOfMagic((PsiElement)ocQualifiedExpressionImpl);
                if (b) {
                    break Label_0056;
                }
                break Label_0056;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType2 = ocType;
                final OCQualifiedExpressionImpl ocQualifiedExpressionImpl = this;
                final boolean b = ocType2.isSubclassOfMagic((PsiElement)ocQualifiedExpressionImpl);
                if (b) {
                    return new OCMagicType();
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        OCPunctuatorElementType qualifyingTokenKind = ocPunctuatorElementType;
        if (qualifyingTokenKind == null) {
            qualifyingTokenKind = this.getQualifyingTokenKind();
        }
        if (qualifyingTokenKind == OCTokenTypes.DEREF) {
            final Set<OCType> typeSet = OCTypeUtils.newTypeSet();
            boolean b2 = false;
            while (true) {
                try {
                    if (!(ocType instanceof OCStructType) || !typeSet.add(ocType)) {
                        break;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                final OCFunctionSymbol resolveDerefOperator = OCOperatorReference.resolveDerefOperator(this, (OCStructType)ocType);
                if (resolveDerefOperator != null) {
                    b2 = true;
                    ocType = resolveDerefOperator.getEffectiveResolvedType();
                }
            }
            try {
                if (ocType instanceof OCPointerType) {
                    return ((OCPointerType)ocType).getRefType().resolve(this.getContainingFile());
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            try {
                if (b2) {
                    return ocType;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
            try {
                if (ref != null) {
                    ref.set((Object)true);
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
            return OCUnknownType.INSTANCE;
        }
        return ocType;
    }
    
    @NotNull
    @Override
    public OCElement getQualifyingElement() {
        OCElement ocElement;
        try {
            ocElement = this.findChildByType(OCElementTypes.QUALIFIED_EXPRESSION_ACCESSOR);
            if (ocElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getQualifyingElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return ocElement;
    }
    
    @Override
    public boolean canChangeQualifyingToken() {
        try {
            if (OCElementUtil.getUserVisibleRangeInDocument((PsiElement)this.getQualifyingElement()) != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    @Override
    public List<OCPunctuatorElementType> qualifyingTokensForCompletion() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //     4: astore_1       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.canChangeQualifyingToken:()Z
        //     9: ifeq            141
        //    12: aload_0        
        //    13: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    16: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    19: dup            
        //    20: aload_0        
        //    21: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    24: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    27: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    32: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    35: astore_2       
        //    36: aload_2        
        //    37: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    40: ifeq            61
        //    43: aload_0        
        //    44: aload_2        
        //    45: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    48: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.resolveDerefOperator:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    51: ifnonnull       141
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    60: athrow         
        //    61: iconst_2       
        //    62: anewarray       Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    65: dup            
        //    66: iconst_0       
        //    67: aload_1        
        //    68: aastore        
        //    69: dup            
        //    70: iconst_1       
        //    71: aload_1        
        //    72: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    75: if_acmpne       95
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    84: athrow         
        //    85: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    88: goto            98
        //    91: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    94: athrow         
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    98: aastore        
        //    99: invokestatic    com/intellij/util/containers/ContainerUtil.list:([Ljava/lang/Object;)Ljava/util/List;
        //   102: dup            
        //   103: ifnonnull       140
        //   106: new             Ljava/lang/IllegalStateException;
        //   109: dup            
        //   110: ldc             "@NotNull method %s.%s must not return null"
        //   112: ldc             2
        //   114: anewarray       Ljava/lang/Object;
        //   117: dup            
        //   118: ldc             0
        //   120: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   122: aastore        
        //   123: dup            
        //   124: ldc             1
        //   126: ldc             "qualifyingTokensForCompletion"
        //   128: aastore        
        //   129: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   132: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   135: athrow         
        //   136: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   139: athrow         
        //   140: areturn        
        //   141: aload_1        
        //   142: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   145: dup            
        //   146: ifnonnull       183
        //   149: new             Ljava/lang/IllegalStateException;
        //   152: dup            
        //   153: ldc             "@NotNull method %s.%s must not return null"
        //   155: ldc             2
        //   157: anewarray       Ljava/lang/Object;
        //   160: dup            
        //   161: ldc             0
        //   163: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   165: aastore        
        //   166: dup            
        //   167: ldc             1
        //   169: ldc             "qualifyingTokensForCompletion"
        //   171: aastore        
        //   172: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   175: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   178: athrow         
        //   179: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   182: athrow         
        //   183: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  36     54     57     61     Lcom/intellij/util/IncorrectOperationException;
        //  43     78     81     85     Lcom/intellij/util/IncorrectOperationException;
        //  61     91     91     95     Lcom/intellij/util/IncorrectOperationException;
        //  98     136    136    140    Lcom/intellij/util/IncorrectOperationException;
        //  141    179    179    183    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0061:
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
    public String getName() {
        String identifierName;
        try {
            identifierName = OCElementUtil.getIdentifierName(this.getNameIdentifier());
            if (identifierName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return identifierName;
    }
    
    @NotNull
    @Override
    public String getSymbolName() {
        String identifierName;
        try {
            identifierName = OCElementUtil.getIdentifierName(this.findReferenceTokenInCall());
            if (identifierName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getSymbolName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return identifierName;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        return this.findNameStartTokenInCall();
    }
    
    @Override
    public OCType getExpectedType() {
        OCType ocType;
        if (this.getContext() instanceof OCCallExpression) {
            ocType = getCallExpectedType((OCCallExpression)this.getParent());
        }
        else {
            ocType = OCExpectedTypeUtil.getExpectedType(this, true);
        }
        if (ocType == OCUnknownType.INSTANCE) {
            ocType = OCIdType.pointerToID(this.getProject());
        }
        return ocType;
    }
    
    public static OCType getCallExpectedType(final OCCallExpression ocCallExpression) {
        OCType ocType = OCExpectedTypeUtil.getExpectedType(ocCallExpression, true);
        final ArrayList<OCType> list = new ArrayList<OCType>();
        if (ocType == OCUnknownType.INSTANCE) {
            ocType = OCVoidType.instance();
        }
        final Iterator<OCExpression> iterator = ocCallExpression.getArguments().iterator();
        while (iterator.hasNext()) {
            list.add(OCExpectedTypeUtil.getExpressionType(iterator.next(), true));
        }
        return new OCFunctionType(ocType, list);
    }
    
    @Override
    public OCSymbolGroupContext getSymbolContext() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     4: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTypeContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //     9: astore_1       
        //    10: aload_0        
        //    11: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //    14: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    17: ifeq            47
        //    20: aload_0        
        //    21: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    29: ifeq            47
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    38: athrow         
        //    39: iconst_1       
        //    40: goto            48
        //    43: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    46: athrow         
        //    47: iconst_0       
        //    48: istore_2       
        //    49: aload_0        
        //    50: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    53: astore_3       
        //    54: aload_1        
        //    55: ifnull          182
        //    58: aload_1        
        //    59: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    62: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    65: astore          5
        //    67: aload_1        
        //    68: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getStaticMode:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //    71: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //    74: if_acmpne       95
        //    77: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //    80: dup            
        //    81: aload_0        
        //    82: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    85: aload           5
        //    87: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //    90: astore          4
        //    92: goto            179
        //    95: aload_3        
        //    96: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    99: if_acmpne       120
        //   102: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   105: dup            
        //   106: aload_0        
        //   107: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   110: aload           5
        //   112: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   115: astore          4
        //   117: goto            179
        //   120: aload_3        
        //   121: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   124: if_acmpne       177
        //   127: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   130: dup            
        //   131: aload_0        
        //   132: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   135: aload           5
        //   137: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   140: astore          4
        //   142: new             Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$1;
        //   145: dup            
        //   146: aload_0        
        //   147: aload           4
        //   149: aload           4
        //   151: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$1.<init>:(Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl;Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   154: astore          6
        //   156: aload           6
        //   158: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   161: dup            
        //   162: aload_0        
        //   163: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   166: aload           5
        //   168: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   171: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   174: aload           6
        //   176: areturn        
        //   177: aconst_null    
        //   178: areturn        
        //   179: goto            388
        //   182: aload_0        
        //   183: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   186: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   191: astore          5
        //   193: aload_3        
        //   194: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   197: if_acmpne       260
        //   200: aload           5
        //   202: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   205: ifeq            260
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   214: athrow         
        //   215: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   218: dup            
        //   219: aload_0        
        //   220: iload_2        
        //   221: ifeq            241
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   230: athrow         
        //   231: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   234: goto            244
        //   237: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   240: athrow         
        //   241: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   244: aload           5
        //   246: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   249: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   252: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   255: astore          4
        //   257: goto            388
        //   260: aload_3        
        //   261: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   264: if_acmpne       354
        //   267: aload           5
        //   269: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   272: ifeq            354
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   281: athrow         
        //   282: aload           5
        //   284: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   287: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   290: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   293: ifeq            354
        //   296: goto            303
        //   299: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   302: athrow         
        //   303: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   306: dup            
        //   307: aload_0        
        //   308: iload_2        
        //   309: ifeq            329
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   318: athrow         
        //   319: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   322: goto            332
        //   325: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   328: athrow         
        //   329: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   332: aload           5
        //   334: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   337: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   340: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   343: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   346: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   349: astore          4
        //   351: goto            388
        //   354: aload           5
        //   356: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   359: ifeq            386
        //   362: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   365: dup            
        //   366: aload_0        
        //   367: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   370: aload           5
        //   372: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   375: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   378: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   381: astore          4
        //   383: goto            388
        //   386: aconst_null    
        //   387: areturn        
        //   388: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   391: dup            
        //   392: aload           4
        //   394: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   397: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  10     32     35     39     Lcom/intellij/util/IncorrectOperationException;
        //  20     43     43     47     Lcom/intellij/util/IncorrectOperationException;
        //  193    208    211    215    Lcom/intellij/util/IncorrectOperationException;
        //  200    224    227    231    Lcom/intellij/util/IncorrectOperationException;
        //  215    237    237    241    Lcom/intellij/util/IncorrectOperationException;
        //  260    275    278    282    Lcom/intellij/util/IncorrectOperationException;
        //  267    296    299    303    Lcom/intellij/util/IncorrectOperationException;
        //  282    312    315    319    Lcom/intellij/util/IncorrectOperationException;
        //  303    325    325    329    Lcom/intellij/util/IncorrectOperationException;
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
    
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitQualifiedExpression(this);
    }
    
    @Nullable
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
    
    @Override
    public boolean processTargets(final String s, final Processor<OCSymbol> processor, final boolean b, @Nullable final OCPunctuatorElementType ocPunctuatorElementType, final boolean b2, final boolean b3, @Nullable final Ref<OCType> ref) {
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)this);
        final Object a = this.a(ocPunctuatorElementType, b3, ocResolveContext);
        Label_0042: {
            try {
                if (ref == null) {
                    return this.a(a, s, processor, b, b2, false, false, ocResolveContext, true);
                }
                final Object o = a;
                final boolean b4 = o instanceof QualifierTypeContext;
                if (b4) {
                    break Label_0042;
                }
                return this.a(a, s, processor, b, b2, false, false, ocResolveContext, true);
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final Object o = a;
                final boolean b4 = o instanceof QualifierTypeContext;
                if (b4) {
                    ref.set((Object)((QualifierTypeContext)a).containerType);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return this.a(a, s, processor, b, b2, false, false, ocResolveContext, true);
    }
    
    private boolean a(@Nullable final Object o, final String s, final Processor<OCSymbol> processor, final boolean b, final boolean b2, final boolean b3, final boolean b4, @NotNull final OCResolveContext ocResolveContext, final boolean b5) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "doProcessTargets"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (o instanceof OCObjectTypeContext) {
                return this.a((OCObjectTypeContext)o, s, processor, b);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        if (o instanceof QualifierTypeContext) {
            final OCType containerType = ((QualifierTypeContext)o).containerType;
            Label_0162: {
                Label_0139: {
                    Label_0114: {
                        try {
                            if (!(containerType instanceof OCStructType)) {
                                break Label_0139;
                            }
                            final OCType ocType = containerType;
                            final OCStructType ocStructType = (OCStructType)ocType;
                            final OCSymbolKind ocSymbolKind = ocStructType.getKind();
                            final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                            if (ocSymbolKind != ocSymbolKind2) {
                                break Label_0114;
                            }
                            break Label_0139;
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCType ocType = containerType;
                            final OCStructType ocStructType = (OCStructType)ocType;
                            final OCSymbolKind ocSymbolKind = ocStructType.getKind();
                            final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                            if (ocSymbolKind != ocSymbolKind2) {
                                return this.a(s, processor, b2, ocResolveContext, (QualifierTypeContext)o, b4, b3, b5);
                            }
                        }
                        catch (IncorrectOperationException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        if (containerType.isUnknown()) {
                            break Label_0162;
                        }
                        final OCType ocType2 = containerType;
                        final boolean b6 = ocType2 instanceof OCMagicType;
                        if (b6) {
                            break Label_0162;
                        }
                        return true;
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    final OCType ocType2 = containerType;
                    final boolean b6 = ocType2 instanceof OCMagicType;
                    if (b6) {
                        processor.process((Object)new OCDeclaratorSymbol(null, null, 0, null, s, Collections.emptyList(), new OCMagicType(s), OCSymbolKind.TEMPLATE_VALUE_PARAMETER));
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
            }
        }
        return true;
    }
    
    private boolean a(final OCObjectTypeContext p0, final String p1, final Processor<OCSymbol> p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: astore          5
        //     3: aload           5
        //     5: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getStaticMode:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //     8: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //    11: if_acmpne       22
        //    14: iconst_1       
        //    15: goto            23
        //    18: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    21: athrow         
        //    22: iconst_0       
        //    23: istore          6
        //    25: aload_0        
        //    26: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    29: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    32: if_acmpne       43
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: istore          7
        //    46: aload_0        
        //    47: iload           7
        //    49: aload_3        
        //    50: iload           6
        //    52: invokedynamic   process:(Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl;ZLcom/intellij/util/Processor;Z)Lcom/intellij/util/Processor;
        //    57: astore          8
        //    59: new             Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor;
        //    62: dup            
        //    63: aload           8
        //    65: iconst_5       
        //    66: anewarray       Lcom/intellij/openapi/util/Condition;
        //    69: dup            
        //    70: iconst_0       
        //    71: aload           5
        //    73: invokedynamic   value:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;)Lcom/intellij/openapi/util/Condition;
        //    78: aastore        
        //    79: dup            
        //    80: iconst_1       
        //    81: aload           5
        //    83: invokedynamic   value:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;)Lcom/intellij/openapi/util/Condition;
        //    88: aastore        
        //    89: dup            
        //    90: iconst_2       
        //    91: aload           5
        //    93: invokedynamic   value:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;)Lcom/intellij/openapi/util/Condition;
        //    98: aastore        
        //    99: dup            
        //   100: iconst_3       
        //   101: aload           5
        //   103: invokedynamic   value:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;)Lcom/intellij/openapi/util/Condition;
        //   108: aastore        
        //   109: dup            
        //   110: iconst_4       
        //   111: iload           4
        //   113: ifeq            126
        //   116: invokestatic    com/intellij/openapi/util/Conditions.alwaysTrue:()Lcom/intellij/openapi/util/Condition;
        //   119: goto            129
        //   122: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   125: athrow         
        //   126: invokestatic    com/intellij/openapi/util/Conditions.alwaysFalse:()Lcom/intellij/openapi/util/Condition;
        //   129: aastore        
        //   130: invokespecial   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.<init>:(Lcom/intellij/util/Processor;[Lcom/intellij/openapi/util/Condition;)V
        //   133: astore          9
        //   135: aload           5
        //   137: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   140: aload_2        
        //   141: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;.class
        //   143: aload           9
        //   145: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   148: pop            
        //   149: aload_2        
        //   150: ifnull          251
        //   153: iload           7
        //   155: ifeq            251
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   164: athrow         
        //   165: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
        //   168: dup            
        //   169: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
        //   172: aload_0        
        //   173: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   176: astore          10
        //   178: aload           10
        //   180: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   183: if_acmpeq       227
        //   186: aload           10
        //   188: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.ReadWrite:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   191: if_acmpne       251
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   200: athrow         
        //   201: aload           5
        //   203: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   206: aload_2        
        //   207: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   209: invokedynamic   process:()Lcom/intellij/util/Processor;
        //   214: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   217: ifne            251
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   226: athrow         
        //   227: aload           5
        //   229: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   232: aload_2        
        //   233: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Ljava/lang/String;)Ljava/lang/String;
        //   236: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   238: aload           9
        //   240: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   243: pop            
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   250: athrow         
        //   251: aload           9
        //   253: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.finish:()Z
        //   256: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;Ljava/lang/String;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  3      18     18     22     Lcom/intellij/util/IncorrectOperationException;
        //  25     39     39     43     Lcom/intellij/util/IncorrectOperationException;
        //  59     122    122    126    Lcom/intellij/util/IncorrectOperationException;
        //  135    158    161    165    Lcom/intellij/util/IncorrectOperationException;
        //  178    194    197    201    Lcom/intellij/util/IncorrectOperationException;
        //  186    220    223    227    Lcom/intellij/util/IncorrectOperationException;
        //  201    244    247    251    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0201:
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
    public CVQualifiers getCVQualifiers() {
        CVQualifiers cvQualifiers;
        try {
            cvQualifiers = this.getCVQualifiers(this.getQualifier().getResolvedType());
            if (cvQualifiers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getCVQualifiers"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return cvQualifiers;
    }
    
    @NotNull
    public CVQualifiers getCVQualifiers(@NotNull final OCType p0) {
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
        //    18: ldc             "qualifierType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCVQualifiers"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    48: aload_1        
        //    49: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getCVQualifiers:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    52: astore_2       
        //    53: aload_0        
        //    54: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    57: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    60: if_acmpne       159
        //    63: aload_1        
        //    64: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    67: ifeq            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    76: athrow         
        //    77: aload_1        
        //    78: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    81: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    84: astore_1       
        //    85: aload_1        
        //    86: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    89: ifeq            159
        //    92: aload_0        
        //    93: aload_1        
        //    94: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    97: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.resolveDerefOperator:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   100: ifnull          159
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   109: athrow         
        //   110: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   113: dup            
        //   114: ifnonnull       158
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   123: athrow         
        //   124: new             Ljava/lang/IllegalStateException;
        //   127: dup            
        //   128: ldc             "@NotNull method %s.%s must not return null"
        //   130: ldc             2
        //   132: anewarray       Ljava/lang/Object;
        //   135: dup            
        //   136: ldc             0
        //   138: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   140: aastore        
        //   141: dup            
        //   142: ldc             1
        //   144: ldc             "getCVQualifiers"
        //   146: aastore        
        //   147: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   150: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   153: athrow         
        //   154: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   157: athrow         
        //   158: areturn        
        //   159: aload_2        
        //   160: dup            
        //   161: ifnonnull       198
        //   164: new             Ljava/lang/IllegalStateException;
        //   167: dup            
        //   168: ldc             "@NotNull method %s.%s must not return null"
        //   170: ldc             2
        //   172: anewarray       Ljava/lang/Object;
        //   175: dup            
        //   176: ldc             0
        //   178: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   180: aastore        
        //   181: dup            
        //   182: ldc             1
        //   184: ldc             "getCVQualifiers"
        //   186: aastore        
        //   187: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   190: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   193: athrow         
        //   194: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  53     70     73     77     Lcom/intellij/util/IncorrectOperationException;
        //  85     103    106    110    Lcom/intellij/util/IncorrectOperationException;
        //  92     117    120    124    Lcom/intellij/util/IncorrectOperationException;
        //  110    154    154    158    Lcom/intellij/util/IncorrectOperationException;
        //  159    194    194    198    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0110:
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
    private OCExprValueCategory a() {
        final PsiReference reference = this.getReference();
        try {
            if (reference != null) {
                return ResolveCache.getInstance(this.getProject()).resolveWithCaching(reference, (ResolveCache.AbstractResolver<PsiReference, OCExprValueCategory>)new ExprValueCategoryResolver(), false, false);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private boolean a(final String s, final Processor<OCSymbol> processor, final boolean b, final OCResolveContext ocResolveContext, final QualifierTypeContext qualifierTypeContext, final boolean b2, final boolean b3, final boolean b4) {
        final List<OCSymbol> resolvedMembers = getResolvedMembers((OCStructType)qualifierTypeContext.containerType, s, OCSymbolReferenceResolver.getTypeArguments(this), ocResolveContext, b4);
        try {
            if (s == null) {
                return ContainerUtil.process((List)resolvedMembers, (Processor)processor);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!b || !(OCParenthesesUtils.topmostParenthesized(this).getParent() instanceof OCCallExpression)) {
                return ContainerUtil.process((List)resolvedMembers, (Processor)processor);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCType cloneWithAddedCVQualifiers = qualifierTypeContext.containerType.cloneWithAddedCVQualifiers(this.getCVQualifiers(qualifierTypeContext.qualifierType), this.getProject());
        final OCArgumentsList<OCExpression> argumentList = OCArgumentsList.getArgumentList(((OCCallExpression)OCParenthesesUtils.topmostParenthesized(this).getParent()).getArguments(), ocResolveContext);
        boolean b5 = false;
        Label_0158: {
            try {
                if (((OCStructType)qualifierTypeContext.containerType).getStructs().size() > 1) {
                    b5 = true;
                    break Label_0158;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            b5 = false;
        }
        final OCSymbol resolveOverloads = OCResolveOverloadsUtil.resolveOverloads(resolvedMembers, argumentList, cloneWithAddedCVQualifiers, this.a(), null, b2, b2, b3, true, b5, ocResolveContext);
        try {
            if (resolveOverloads != null) {
                return processor.process((Object)resolveOverloads);
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    public static List<OCSymbol> getResolvedMembers(final OCStructType ocStructType, final String s, final List<OCTypeArgument> list, final OCResolveContext ocResolveContext, final boolean b) {
        final OCSymbolReference.UsingAndTypedefSymbolsResolver usingAndTypedefSymbolsResolver = new OCSymbolReference.UsingAndTypedefSymbolsResolver(false, true, false, null, ocResolveContext);
        Object o = null;
        Label_0089: {
            Label_0076: {
                try {
                    ocStructType.processMembers(s, (Processor<OCSymbol>)usingAndTypedefSymbolsResolver, ocResolveContext);
                    if (!StringUtil.isNotEmpty(s) || s.charAt(0) != '~') {
                        break Label_0076;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                o = usingAndTypedefSymbolsResolver.getAnswer();
                if (((List)o).isEmpty()) {
                    o = Collections.singletonList(ocStructType.getSymbol());
                }
                break Label_0089;
            }
            o = ContainerUtil.filter((Collection)usingAndTypedefSymbolsResolver.getAnswer(), (Condition)OCQualifiedExpressionImpl.CAN_BE_STRUCTURE_FIELD);
            try {
                if (!b || list == null) {
                    return (List<OCSymbol>)o;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        o = new ArrayList(OCSimpleTypeSubstitution.resolveTemplateSpecialization(ContainerUtil.findAll((Collection)o, (Class)OCTemplateSymbol.class), list, ocResolveContext));
        return (List<OCSymbol>)o;
    }
    
    @Nullable
    private Object a(@Nullable final OCPunctuatorElementType ocPunctuatorElementType, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "getContainerTypeContext"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCFile containingOCFile = this.getContainingOCFile();
        final OCExpression diveIntoParentheses = OCParenthesesUtils.diveIntoParentheses(this.getQualifier());
        try {
            if (diveIntoParentheses == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCType type = diveIntoParentheses.getType(ocResolveContext);
        OCType ocType = null;
        Label_0105: {
            try {
                if (b) {
                    ocType = type.resolve((PsiFile)containingOCFile, true);
                    break Label_0105;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            ocType = type.resolve(ocResolveContext);
        }
        OCType ocType2 = ocType;
        boolean b2 = false;
        Label_0199: {
            Label_0183: {
                Label_0145: {
                    try {
                        if (!(diveIntoParentheses instanceof OCSendMessageExpression) || !OCTypeGuesser.isOfficialNamingConvention(((OCSendMessageExpression)diveIntoParentheses).getMessageSelector())) {
                            break Label_0145;
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                    ocType2 = ocType2.getGuessedType();
                    try {
                        if (!(diveIntoParentheses instanceof OCQualifiedExpression) || !OCTypeGuesser.isOfficialNamingConvention(((OCQualifiedExpression)diveIntoParentheses).getName())) {
                            break Label_0183;
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                ocType2 = ocType2.getGuessedType();
                try {
                    if (ocPunctuatorElementType == OCTokenTypes.DEREF) {
                        b2 = true;
                        break Label_0199;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
            }
            b2 = false;
        }
        final OCObjectTypeContext typeContext = diveIntoParentheses.getTypeContext(diveIntoParentheses, ocType2, type, b2, b);
        try {
            if (typeContext != null) {
                return typeContext;
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        return new QualifierTypeContext(ocType2, this.a(ocType2, ocPunctuatorElementType, null));
    }
    
    private static String a(final String s) {
        return "set" + StringUtil.capitalize(s) + ":";
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol() {
        return this.resolveToSymbol(new OCResolveContext((PsiElement)this), true, false, false);
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol(@NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "resolveToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        this.a(this.a(null, false, ocResolveContext), this.getSymbolName(), (Processor<OCSymbol>)findFirstProcessor, true, b, b2, b3, ocResolveContext, true);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @NotNull
    @Override
    public Collection<OCSymbol> resolveToOverloadsSymbols() {
        Collection<OCSymbol> a;
        try {
            a = this.a(true);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "resolveToOverloadsSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    @Override
    public Collection<OCSymbol> resolveTemplateDeclarations() {
        Collection<OCSymbol> a;
        try {
            a = this.a(false);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "resolveTemplateDeclarations"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    private Collection<OCSymbol> a(final boolean b) {
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)this.getContainingOCFile());
        final Object a = this.a(null, false, ocResolveContext);
        Collection results;
        try {
            this.a(a, this.getSymbolName(), (Processor<OCSymbol>)collectProcessor, true, false, false, false, ocResolveContext, b);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl", "resolveToOverloadsSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (Collection<OCSymbol>)results;
    }
    
    @Nullable
    @Override
    protected PsiReference createReference() {
        return (PsiReference)new OCReferenceWithContext() {
            @Contract(pure = true)
            public PsiElement getElement() {
                return (PsiElement)OCQualifiedExpressionImpl.this;
            }
            
            public TextRange getRangeInElement() {
                return OCQualifiedExpressionImpl.this.getRangeInCallElement();
            }
            
            public PsiElement resolve() {
                return OCQualifiedExpressionImpl.this.resolve();
            }
            
            @NotNull
            public String getCanonicalText() {
                String symbolName;
                try {
                    symbolName = OCQualifiedExpressionImpl.this.getSymbolName();
                    if (symbolName == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "getCanonicalText"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return symbolName;
            }
            
            public PsiElement handleElementRename(final String name) throws IncorrectOperationException {
                return OCQualifiedExpressionImpl.this.setName(name);
            }
            
            @Override
            public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
                try {
                    if (ocSymbol == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "bindToSymbol"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                final String name = ocSymbol.getName();
                final String objCGetterFromSetter = OCNameSuggester.getObjCGetterFromSetter(name);
                try {
                    if (objCGetterFromSetter != null) {
                        this.handleElementRename(objCGetterFromSetter);
                        return this.getElement();
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                this.handleElementRename(name);
                return this.getElement();
            }
            
            public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "bindToElement"));
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
                //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
                //     4: ifne            13
                //     7: iconst_0       
                //     8: ireturn        
                //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //    12: athrow         
                //    13: aload_1        
                //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
                //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    22: astore_2       
                //    23: aload_0        
                //    24: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    27: astore_3       
                //    28: aload_3        
                //    29: aload_2        
                //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
                //    33: ifeq            42
                //    36: iconst_1       
                //    37: ireturn        
                //    38: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //    41: athrow         
                //    42: aload_2        
                //    43: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                //    46: ifeq            181
                //    49: aload_3        
                //    50: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //    53: ifeq            181
                //    56: goto            63
                //    59: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //    62: athrow         
                //    63: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
                //    66: dup            
                //    67: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
                //    70: aload_0        
                //    71: getfield        com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.this$0:Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl;
                //    74: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
                //    77: astore          4
                //    79: aload           4
                //    81: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
                //    84: if_acmpne       112
                //    87: aload_2        
                //    88: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
                //    93: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
                //    96: ifeq            112
                //    99: goto            106
                //   102: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   105: athrow         
                //   106: iconst_0       
                //   107: ireturn        
                //   108: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   111: athrow         
                //   112: aload           4
                //   114: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
                //   117: if_acmpne       145
                //   120: aload_2        
                //   121: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
                //   126: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCGetter:(Ljava/lang/String;)Z
                //   129: ifeq            145
                //   132: goto            139
                //   135: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   138: athrow         
                //   139: iconst_0       
                //   140: ireturn        
                //   141: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   144: athrow         
                //   145: aload_2        
                //   146: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //   151: astore_2       
                //   152: aload_2        
                //   153: ifnull          166
                //   156: aload_2        
                //   157: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                //   160: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //   165: astore_2       
                //   166: aload_3        
                //   167: aload_2        
                //   168: if_acmpne       179
                //   171: iconst_1       
                //   172: goto            180
                //   175: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   178: athrow         
                //   179: iconst_0       
                //   180: ireturn        
                //   181: aload_2        
                //   182: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //   185: ifeq            272
                //   188: aload_3        
                //   189: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                //   192: ifeq            272
                //   195: goto            202
                //   198: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   201: athrow         
                //   202: aload_2        
                //   203: aload_3        
                //   204: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                //   207: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //   212: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //   215: ifeq            231
                //   218: goto            225
                //   221: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   224: athrow         
                //   225: iconst_1       
                //   226: ireturn        
                //   227: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   230: athrow         
                //   231: aload_3        
                //   232: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //   237: astore_3       
                //   238: aload_3        
                //   239: ifnull          270
                //   242: aload_3        
                //   243: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                //   246: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //   251: aload_2        
                //   252: if_acmpne       270
                //   255: goto            262
                //   258: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   261: athrow         
                //   262: iconst_1       
                //   263: goto            271
                //   266: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   269: athrow         
                //   270: iconst_0       
                //   271: ireturn        
                //   272: aload_3        
                //   273: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
                //   276: ifeq            307
                //   279: aload_2        
                //   280: astore          4
                //   282: aload_3        
                //   283: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
                //   286: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
                //   289: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
                //   294: aload           4
                //   296: invokedynamic   test:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/util/function/Predicate;
                //   301: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
                //   306: ireturn        
                //   307: aload_3        
                //   308: ifnull          336
                //   311: aload_3        
                //   312: aload_2        
                //   313: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
                //   318: ifeq            336
                //   321: goto            328
                //   324: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   327: athrow         
                //   328: iconst_1       
                //   329: goto            337
                //   332: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                //   335: athrow         
                //   336: iconst_0       
                //   337: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                           
                //  -----  -----  -----  -----  -----------------------------------------------
                //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
                //  28     38     38     42     Lcom/intellij/util/IncorrectOperationException;
                //  42     56     59     63     Lcom/intellij/util/IncorrectOperationException;
                //  79     99     102    106    Lcom/intellij/util/IncorrectOperationException;
                //  87     108    108    112    Lcom/intellij/util/IncorrectOperationException;
                //  112    132    135    139    Lcom/intellij/util/IncorrectOperationException;
                //  120    141    141    145    Lcom/intellij/util/IncorrectOperationException;
                //  166    175    175    179    Lcom/intellij/util/IncorrectOperationException;
                //  181    195    198    202    Lcom/intellij/util/IncorrectOperationException;
                //  188    218    221    225    Lcom/intellij/util/IncorrectOperationException;
                //  202    227    227    231    Lcom/intellij/util/IncorrectOperationException;
                //  238    255    258    262    Lcom/intellij/util/IncorrectOperationException;
                //  242    266    266    270    Lcom/intellij/util/IncorrectOperationException;
                //  307    321    324    328    Lcom/intellij/util/IncorrectOperationException;
                //  311    332    332    336    Lcom/intellij/util/IncorrectOperationException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0202:
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
            
            @NotNull
            public Object[] getVariants() {
                Object[] empty_OBJECT_ARRAY;
                try {
                    empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
                    if (empty_OBJECT_ARRAY == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "getVariants"));
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
                return OCQualifiedExpressionImpl.this.resolveToSymbol();
            }
            
            @Nullable
            @Override
            public OCSymbol resolveToSymbol(@NotNull final OCResolveContext ocResolveContext) {
                try {
                    if (ocResolveContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "resolveToSymbol"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return OCQualifiedExpressionImpl.this.resolveToSymbol(ocResolveContext, true, false, false);
            }
            
            private static IncorrectOperationException a(final IncorrectOperationException ex) {
                return ex;
            }
        };
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext p0) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aconst_null    
        //    46: iconst_0       
        //    47: aload_1        
        //    48: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Object;
        //    51: astore_2       
        //    52: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //    55: dup            
        //    56: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //    59: astore_3       
        //    60: new             Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor;
        //    63: dup            
        //    64: aload_3        
        //    65: iconst_3       
        //    66: anewarray       Lcom/intellij/openapi/util/Condition;
        //    69: dup            
        //    70: iconst_0       
        //    71: aload_2        
        //    72: invokedynamic   value:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Condition;
        //    77: aastore        
        //    78: dup            
        //    79: iconst_1       
        //    80: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbol.NON_FANTOM_SYMBOL_CONDITION:Lcom/intellij/openapi/util/Condition;
        //    83: aastore        
        //    84: dup            
        //    85: iconst_2       
        //    86: invokestatic    com/intellij/openapi/util/Conditions.alwaysTrue:()Lcom/intellij/openapi/util/Condition;
        //    89: aastore        
        //    90: invokespecial   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.<init>:(Lcom/intellij/util/Processor;[Lcom/intellij/openapi/util/Condition;)V
        //    93: astore          4
        //    95: aload_0        
        //    96: aload_2        
        //    97: aload_0        
        //    98: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getSymbolName:()Ljava/lang/String;
        //   101: aload           4
        //   103: iconst_1       
        //   104: iconst_1       
        //   105: iconst_0       
        //   106: iconst_0       
        //   107: aload_1        
        //   108: iconst_1       
        //   109: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Ljava/lang/Object;Ljava/lang/String;Lcom/intellij/util/Processor;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   112: pop            
        //   113: aload           4
        //   115: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.finish:()Z
        //   118: pop            
        //   119: aload_3        
        //   120: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   126: astore          5
        //   128: aload           5
        //   130: ifnull          654
        //   133: aload           5
        //   135: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   138: ifeq            514
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   147: athrow         
        //   148: aload           5
        //   150: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   155: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCGetter:(Ljava/lang/String;)Z
        //   158: ifeq            339
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   167: athrow         
        //   168: aload_2        
        //   169: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   172: ifeq            282
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   181: athrow         
        //   182: aload           5
        //   184: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   187: aload_2        
        //   188: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   191: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   194: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   199: aload_1        
        //   200: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   203: astore          6
        //   205: aload           5
        //   207: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   210: aload_2        
        //   211: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   214: aconst_null    
        //   215: aload_0        
        //   216: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.getMethodGuessedReturnType:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   219: astore          7
        //   221: aload           7
        //   223: ifnull          279
        //   226: aload           6
        //   228: aload           7
        //   230: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithGuessedType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   233: dup            
        //   234: ifnonnull       278
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   243: athrow         
        //   244: new             Ljava/lang/IllegalStateException;
        //   247: dup            
        //   248: ldc             "@NotNull method %s.%s must not return null"
        //   250: ldc             2
        //   252: anewarray       Ljava/lang/Object;
        //   255: dup            
        //   256: ldc             0
        //   258: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   260: aastore        
        //   261: dup            
        //   262: ldc             1
        //   264: ldc             "getType"
        //   266: aastore        
        //   267: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   270: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   273: athrow         
        //   274: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   277: athrow         
        //   278: areturn        
        //   279: goto            298
        //   282: aload           5
        //   284: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   287: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   292: aload_1        
        //   293: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   296: astore          6
        //   298: aload           6
        //   300: dup            
        //   301: ifnonnull       338
        //   304: new             Ljava/lang/IllegalStateException;
        //   307: dup            
        //   308: ldc             "@NotNull method %s.%s must not return null"
        //   310: ldc             2
        //   312: anewarray       Ljava/lang/Object;
        //   315: dup            
        //   316: ldc             0
        //   318: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   320: aastore        
        //   321: dup            
        //   322: ldc             1
        //   324: ldc             "getType"
        //   326: aastore        
        //   327: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   330: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   333: athrow         
        //   334: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   337: athrow         
        //   338: areturn        
        //   339: aload           5
        //   341: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   346: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
        //   349: ifeq            472
        //   352: aload           5
        //   354: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   357: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   362: astore          6
        //   364: aload           6
        //   366: ifnull          410
        //   369: aload           6
        //   371: invokeinterface java/util/List.size:()I
        //   376: iconst_1       
        //   377: if_icmpne       410
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   386: athrow         
        //   387: aload           6
        //   389: iconst_0       
        //   390: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   395: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   398: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   403: goto            411
        //   406: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   409: athrow         
        //   410: aconst_null    
        //   411: astore          7
        //   413: aload           7
        //   415: ifnull          430
        //   418: aload           7
        //   420: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   423: goto            433
        //   426: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   429: athrow         
        //   430: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   433: dup            
        //   434: ifnonnull       471
        //   437: new             Ljava/lang/IllegalStateException;
        //   440: dup            
        //   441: ldc             "@NotNull method %s.%s must not return null"
        //   443: ldc             2
        //   445: anewarray       Ljava/lang/Object;
        //   448: dup            
        //   449: ldc             0
        //   451: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   453: aastore        
        //   454: dup            
        //   455: ldc             1
        //   457: ldc             "getType"
        //   459: aastore        
        //   460: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   463: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   466: athrow         
        //   467: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   470: athrow         
        //   471: areturn        
        //   472: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   475: dup            
        //   476: ifnonnull       513
        //   479: new             Ljava/lang/IllegalStateException;
        //   482: dup            
        //   483: ldc             "@NotNull method %s.%s must not return null"
        //   485: ldc             2
        //   487: anewarray       Ljava/lang/Object;
        //   490: dup            
        //   491: ldc             0
        //   493: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   495: aastore        
        //   496: dup            
        //   497: ldc             1
        //   499: ldc             "getType"
        //   501: aastore        
        //   502: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   505: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   508: athrow         
        //   509: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   512: athrow         
        //   513: areturn        
        //   514: aload           5
        //   516: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   519: ifeq            581
        //   522: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   525: dup            
        //   526: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   529: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   532: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   535: dup            
        //   536: ifnonnull       580
        //   539: goto            546
        //   542: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   545: athrow         
        //   546: new             Ljava/lang/IllegalStateException;
        //   549: dup            
        //   550: ldc             "@NotNull method %s.%s must not return null"
        //   552: ldc             2
        //   554: anewarray       Ljava/lang/Object;
        //   557: dup            
        //   558: ldc             0
        //   560: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   562: aastore        
        //   563: dup            
        //   564: ldc             1
        //   566: ldc             "getType"
        //   568: aastore        
        //   569: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   572: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   575: athrow         
        //   576: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   579: athrow         
        //   580: areturn        
        //   581: aload           5
        //   583: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   588: astore          6
        //   590: aload           6
        //   592: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   595: ifeq            613
        //   598: aload           6
        //   600: checkcast       Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   603: invokevirtual   com/jetbrains/cidr/lang/types/OCVariadicType.getUnderlyingType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   606: goto            615
        //   609: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   612: athrow         
        //   613: aload           6
        //   615: dup            
        //   616: ifnonnull       653
        //   619: new             Ljava/lang/IllegalStateException;
        //   622: dup            
        //   623: ldc             "@NotNull method %s.%s must not return null"
        //   625: ldc             2
        //   627: anewarray       Ljava/lang/Object;
        //   630: dup            
        //   631: ldc             0
        //   633: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   635: aastore        
        //   636: dup            
        //   637: ldc             1
        //   639: ldc             "getType"
        //   641: aastore        
        //   642: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   645: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   648: athrow         
        //   649: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   652: athrow         
        //   653: areturn        
        //   654: aload_2        
        //   655: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$QualifierTypeContext;
        //   658: ifeq            681
        //   661: aload_2        
        //   662: checkcast       Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$QualifierTypeContext;
        //   665: getfield        com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$QualifierTypeContext.qualifierType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   668: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   671: ifne            715
        //   674: goto            681
        //   677: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   680: athrow         
        //   681: aload_2        
        //   682: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   685: ifeq            772
        //   688: goto            695
        //   691: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   694: athrow         
        //   695: aload_2        
        //   696: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   699: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getOriginalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   702: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   705: ifeq            772
        //   708: goto            715
        //   711: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   714: athrow         
        //   715: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   718: dup            
        //   719: aload_0        
        //   720: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getSymbolName:()Ljava/lang/String;
        //   723: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Ljava/lang/String;)V
        //   726: dup            
        //   727: ifnonnull       771
        //   730: goto            737
        //   733: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   736: athrow         
        //   737: new             Ljava/lang/IllegalStateException;
        //   740: dup            
        //   741: ldc             "@NotNull method %s.%s must not return null"
        //   743: ldc             2
        //   745: anewarray       Ljava/lang/Object;
        //   748: dup            
        //   749: ldc             0
        //   751: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   753: aastore        
        //   754: dup            
        //   755: ldc             1
        //   757: ldc             "getType"
        //   759: aastore        
        //   760: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   763: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   766: athrow         
        //   767: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   770: athrow         
        //   771: areturn        
        //   772: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   775: dup            
        //   776: ifnonnull       813
        //   779: new             Ljava/lang/IllegalStateException;
        //   782: dup            
        //   783: ldc             "@NotNull method %s.%s must not return null"
        //   785: ldc             2
        //   787: anewarray       Ljava/lang/Object;
        //   790: dup            
        //   791: ldc             0
        //   793: ldc             "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl"
        //   795: aastore        
        //   796: dup            
        //   797: ldc             1
        //   799: ldc             "getType"
        //   801: aastore        
        //   802: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   805: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   808: athrow         
        //   809: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   812: athrow         
        //   813: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  128    141    144    148    Lcom/intellij/util/IncorrectOperationException;
        //  133    161    164    168    Lcom/intellij/util/IncorrectOperationException;
        //  148    175    178    182    Lcom/intellij/util/IncorrectOperationException;
        //  221    237    240    244    Lcom/intellij/util/IncorrectOperationException;
        //  226    274    274    278    Lcom/intellij/util/IncorrectOperationException;
        //  298    334    334    338    Lcom/intellij/util/IncorrectOperationException;
        //  364    380    383    387    Lcom/intellij/util/IncorrectOperationException;
        //  369    406    406    410    Lcom/intellij/util/IncorrectOperationException;
        //  413    426    426    430    Lcom/intellij/util/IncorrectOperationException;
        //  433    467    467    471    Lcom/intellij/util/IncorrectOperationException;
        //  472    509    509    513    Lcom/intellij/util/IncorrectOperationException;
        //  514    539    542    546    Lcom/intellij/util/IncorrectOperationException;
        //  522    576    576    580    Lcom/intellij/util/IncorrectOperationException;
        //  590    609    609    613    Lcom/intellij/util/IncorrectOperationException;
        //  615    649    649    653    Lcom/intellij/util/IncorrectOperationException;
        //  654    674    677    681    Lcom/intellij/util/IncorrectOperationException;
        //  661    688    691    695    Lcom/intellij/util/IncorrectOperationException;
        //  681    708    711    715    Lcom/intellij/util/IncorrectOperationException;
        //  695    730    733    737    Lcom/intellij/util/IncorrectOperationException;
        //  715    767    767    771    Lcom/intellij/util/IncorrectOperationException;
        //  772    809    809    813    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0148:
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
    public OCTypeArgumentList getTemplateArgumentList() {
        return this.findChildByType(OCElementTypes.TEMPLATE_ARGUMENT_LIST);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCQualifiedExpressionImpl.class.desiredAssertionStatus()) {
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
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.psi.impl.OCQualifiedExpressionImpl");
        CAN_BE_STRUCTURE_FIELD = (ocSymbol -> ocSymbol.getKind().isExpression());
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    private static class ExprValueCategoryResolver implements ResolveCache.AbstractResolver<PsiReference, OCExprValueCategory>
    {
        @Override
        public OCExprValueCategory resolve(@NotNull final PsiReference psiReference, final boolean b) {
            try {
                if (psiReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$ExprValueCategoryResolver", "resolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return OCExprValueCategory.classify(((OCQualifiedExpression)psiReference.getElement()).getQualifier());
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class QualifierTypeContext
    {
        OCType qualifierType;
        OCType containerType;
        
        public QualifierTypeContext(final OCType qualifierType, final OCType containerType) {
            this.qualifierType = qualifierType;
            this.containerType = containerType;
        }
    }
}
