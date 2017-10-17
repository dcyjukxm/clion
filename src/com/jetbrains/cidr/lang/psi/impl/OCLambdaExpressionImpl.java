// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCLambdaIntroducer;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.intellij.util.Processor;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCNoexceptSpecifier;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;

public class OCLambdaExpressionImpl extends OCExpressionBase implements OCLambdaExpression
{
    public OCLambdaExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCCallableKind getKind() {
        return OCCallableKind.LAMBDA;
    }
    
    @NotNull
    @Override
    public OCType getReturnType() {
        final OCTypeElement returnTypeElement = this.getReturnTypeElement();
        OCType ocType = null;
        Label_0027: {
            try {
                if (returnTypeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = returnTypeElement.getType());
                    break Label_0027;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = this.a(0 != 0));
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl", "getReturnType"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return ocType;
    }
    
    @Override
    public List<OCDeclarator> getParameters() {
        final OCParameterList parameterList = this.getParameterList();
        try {
            if (parameterList != null) {
                return parameterList.getParameters();
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCNoexceptSpecifier getNoexceptSpecifier() {
        return this.findChildByType(OCElementTypes.CPP_NOEXCEPT_SPECIFIER);
    }
    
    public OCSymbol getSymbol() {
        final OCFile containingOCFile = this.getContainingOCFile();
        return new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(this.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)BuilderDriverBase.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)CommonProcessors.alwaysTrue()).getExpressionSymbol(this.getNode(), new BuilderDriverBase.DeclarationContext<ASTNode>(null, PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCLocalScopeable.class }), OCSymbolReferenceResolver.getGlobalContextFromLocal((PsiElement)this), null, (PsiElement)this, false));
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        throw new UnsupportedOperationException();
    }
    
    @NotNull
    @Override
    public OCLambdaIntroducer getLambdaIntroducer() {
        OCLambdaIntroducer ocLambdaIntroducer;
        try {
            ocLambdaIntroducer = this.findChildByType(OCElementTypes.CPP_LAMBDA_INTRODUCER);
            if (ocLambdaIntroducer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl", "getLambdaIntroducer"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return ocLambdaIntroducer;
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //    48: astore_2       
        //    49: new             Ljava/util/ArrayList;
        //    52: dup            
        //    53: invokespecial   java/util/ArrayList.<init>:()V
        //    56: astore_3       
        //    57: new             Ljava/util/ArrayList;
        //    60: dup            
        //    61: invokespecial   java/util/ArrayList.<init>:()V
        //    64: astore          4
        //    66: aload_2        
        //    67: ifnull          158
        //    70: aload_2        
        //    71: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //    76: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    81: astore          5
        //    83: aload           5
        //    85: invokeinterface java/util/Iterator.hasNext:()Z
        //    90: ifeq            158
        //    93: aload           5
        //    95: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   100: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   103: astore          6
        //   105: aload           6
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   112: astore          7
        //   114: aload           7
        //   116: ifnull          155
        //   119: aload_3        
        //   120: aload           7
        //   122: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   127: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   132: pop            
        //   133: aload           4
        //   135: aload           7
        //   137: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   142: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   147: pop            
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   154: athrow         
        //   155: goto            83
        //   158: aload_0        
        //   159: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   162: astore          5
        //   164: aload           5
        //   166: ifnull          183
        //   169: aload           5
        //   171: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   176: goto            188
        //   179: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   182: athrow         
        //   183: aload_0        
        //   184: iconst_1       
        //   185: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.a:(Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   188: astore          6
        //   190: aload           6
        //   192: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   195: ifeq            264
        //   198: aload           6
        //   200: checkcast       Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   203: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.needsAutoParamsResolving:()Z
        //   206: ifeq            264
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   215: athrow         
        //   216: aload           6
        //   218: dup            
        //   219: ifnonnull       263
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   228: athrow         
        //   229: new             Ljava/lang/IllegalStateException;
        //   232: dup            
        //   233: ldc             "@NotNull method %s.%s must not return null"
        //   235: ldc             2
        //   237: anewarray       Ljava/lang/Object;
        //   240: dup            
        //   241: ldc             0
        //   243: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl"
        //   245: aastore        
        //   246: dup            
        //   247: ldc             1
        //   249: ldc             "getType"
        //   251: aastore        
        //   252: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   255: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   258: athrow         
        //   259: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   262: athrow         
        //   263: areturn        
        //   264: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   267: dup            
        //   268: aload           6
        //   270: aload_3        
        //   271: aload           4
        //   273: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;)V
        //   276: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   279: dup            
        //   280: ifnonnull       317
        //   283: new             Ljava/lang/IllegalStateException;
        //   286: dup            
        //   287: ldc             "@NotNull method %s.%s must not return null"
        //   289: ldc             2
        //   291: anewarray       Ljava/lang/Object;
        //   294: dup            
        //   295: ldc             0
        //   297: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl"
        //   299: aastore        
        //   300: dup            
        //   301: ldc             1
        //   303: ldc             "getType"
        //   305: aastore        
        //   306: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   309: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   312: athrow         
        //   313: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   316: athrow         
        //   317: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      40     40     44     Ljava/lang/UnsupportedOperationException;
        //  114    148    151    155    Ljava/lang/UnsupportedOperationException;
        //  164    179    179    183    Ljava/lang/UnsupportedOperationException;
        //  190    209    212    216    Ljava/lang/UnsupportedOperationException;
        //  198    222    225    229    Ljava/lang/UnsupportedOperationException;
        //  216    259    259    263    Ljava/lang/UnsupportedOperationException;
        //  264    313    313    317    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0216:
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
    public OCBlockStatement getBody() {
        return this.findChildByType(OCElementTypes.BLOCK_STATEMENTS);
    }
    
    @Override
    public OCTypeElement getReturnTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Override
    public OCParameterList getParameterList() {
        return this.findChildByType(OCElementTypes.PARAMETER_LIST);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl", "accept"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        ocVisitor.visitLambdaExpression(this);
    }
    
    @NotNull
    private OCType a(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: astore_2       
        //     5: aload_2        
        //     6: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //     9: ifeq            34
        //    12: aload_2        
        //    13: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //    16: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    19: dup            
        //    20: aload_0        
        //    21: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    24: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    27: goto            35
        //    30: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    33: athrow         
        //    34: aconst_null    
        //    35: astore_3       
        //    36: iload_1        
        //    37: ifeq            118
        //    40: aload_3        
        //    41: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    44: ifeq            118
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    53: athrow         
        //    54: aload_3        
        //    55: checkcast       Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    58: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.needsAutoParamsResolving:()Z
        //    61: ifeq            118
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    70: athrow         
        //    71: aload_3        
        //    72: dup            
        //    73: ifnonnull       117
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    82: athrow         
        //    83: new             Ljava/lang/IllegalStateException;
        //    86: dup            
        //    87: ldc             "@NotNull method %s.%s must not return null"
        //    89: ldc             2
        //    91: anewarray       Ljava/lang/Object;
        //    94: dup            
        //    95: ldc             0
        //    97: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl"
        //    99: aastore        
        //   100: dup            
        //   101: ldc             1
        //   103: ldc             "getReturnType"
        //   105: aastore        
        //   106: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   109: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   112: athrow         
        //   113: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   116: athrow         
        //   117: areturn        
        //   118: aload_3        
        //   119: ifnull          195
        //   122: aload_3        
        //   123: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   126: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   129: ifeq            195
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   138: athrow         
        //   139: aload_3        
        //   140: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   143: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   146: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   149: dup            
        //   150: ifnonnull       194
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   159: athrow         
        //   160: new             Ljava/lang/IllegalStateException;
        //   163: dup            
        //   164: ldc             "@NotNull method %s.%s must not return null"
        //   166: ldc             2
        //   168: anewarray       Ljava/lang/Object;
        //   171: dup            
        //   172: ldc             0
        //   174: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl"
        //   176: aastore        
        //   177: dup            
        //   178: ldc             1
        //   180: ldc             "getReturnType"
        //   182: aastore        
        //   183: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   186: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   189: athrow         
        //   190: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   193: athrow         
        //   194: areturn        
        //   195: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   198: dup            
        //   199: ifnonnull       236
        //   202: new             Ljava/lang/IllegalStateException;
        //   205: dup            
        //   206: ldc             "@NotNull method %s.%s must not return null"
        //   208: ldc             2
        //   210: anewarray       Ljava/lang/Object;
        //   213: dup            
        //   214: ldc             0
        //   216: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl"
        //   218: aastore        
        //   219: dup            
        //   220: ldc             1
        //   222: ldc             "getReturnType"
        //   224: aastore        
        //   225: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   228: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   231: athrow         
        //   232: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLambdaExpressionImpl.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   235: athrow         
        //   236: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  5      30     30     34     Ljava/lang/UnsupportedOperationException;
        //  36     47     50     54     Ljava/lang/UnsupportedOperationException;
        //  40     64     67     71     Ljava/lang/UnsupportedOperationException;
        //  54     76     79     83     Ljava/lang/UnsupportedOperationException;
        //  71     113    113    117    Ljava/lang/UnsupportedOperationException;
        //  118    132    135    139    Ljava/lang/UnsupportedOperationException;
        //  122    153    156    160    Ljava/lang/UnsupportedOperationException;
        //  139    190    190    194    Ljava/lang/UnsupportedOperationException;
        //  195    232    232    236    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
