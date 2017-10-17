// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCTypeElement;

public class OCTypeElementImpl extends OCElementBase implements OCTypeElement
{
    public OCTypeElementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitTypeElement(this);
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType rawType;
        try {
            rawType = this.getRawType();
            if (rawType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return rawType;
    }
    
    @NotNull
    @Override
    public OCType getRawType() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     4: astore_1       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_2       
        //    10: aload_2        
        //    11: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    16: astore_3       
        //    17: aload_3        
        //    18: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    21: astore          4
        //    23: aload           4
        //    25: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEOF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    28: if_acmpeq       46
        //    31: aload           4
        //    33: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DECLTYPE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //    36: if_acmpne       255
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_0        
        //    47: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.getChildren:()[Lcom/intellij/psi/PsiElement;
        //    50: astore          5
        //    52: aload           5
        //    54: arraylength    
        //    55: iconst_1       
        //    56: if_icmpne       134
        //    59: aload           5
        //    61: iconst_0       
        //    62: aaload         
        //    63: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    66: ifeq            134
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload           5
        //    78: iconst_0       
        //    79: aaload         
        //    80: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    83: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    88: dup            
        //    89: ifnonnull       133
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: new             Ljava/lang/IllegalStateException;
        //   102: dup            
        //   103: ldc             "@NotNull method %s.%s must not return null"
        //   105: ldc             2
        //   107: anewarray       Ljava/lang/Object;
        //   110: dup            
        //   111: ldc             0
        //   113: ldc             "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             1
        //   119: ldc             "getRawType"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: areturn        
        //   134: aload_0        
        //   135: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //   138: aconst_null    
        //   139: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //   144: invokestatic    java/util/Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //   147: invokedynamic   test:()Ljava/util/function/Predicate;
        //   152: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
        //   157: ifeq            213
        //   160: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   163: dup            
        //   164: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:()V
        //   167: dup            
        //   168: ifnonnull       212
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: new             Ljava/lang/IllegalStateException;
        //   181: dup            
        //   182: ldc             "@NotNull method %s.%s must not return null"
        //   184: ldc             2
        //   186: anewarray       Ljava/lang/Object;
        //   189: dup            
        //   190: ldc             0
        //   192: ldc             "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl"
        //   194: aastore        
        //   195: dup            
        //   196: ldc             1
        //   198: ldc             "getRawType"
        //   200: aastore        
        //   201: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   204: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   207: athrow         
        //   208: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: areturn        
        //   213: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   216: dup            
        //   217: ifnonnull       254
        //   220: new             Ljava/lang/IllegalStateException;
        //   223: dup            
        //   224: ldc             "@NotNull method %s.%s must not return null"
        //   226: ldc             2
        //   228: anewarray       Ljava/lang/Object;
        //   231: dup            
        //   232: ldc             0
        //   234: ldc             "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl"
        //   236: aastore        
        //   237: dup            
        //   238: ldc             1
        //   240: ldc             "getRawType"
        //   242: aastore        
        //   243: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   246: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   249: athrow         
        //   250: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: areturn        
        //   255: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
        //   258: dup            
        //   259: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:()V
        //   262: astore          5
        //   264: aload_1        
        //   265: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   270: astore          6
        //   272: new             Lcom/jetbrains/cidr/lang/symbols/OCBuilderDriver;
        //   275: dup            
        //   276: aload_1        
        //   277: aload           6
        //   279: aload_1        
        //   280: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.empty:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   283: new             Lcom/intellij/psi/impl/source/tree/ASTStructure;
        //   286: dup            
        //   287: aload_2        
        //   288: invokespecial   com/intellij/psi/impl/source/tree/ASTStructure.<init>:(Lcom/intellij/lang/ASTNode;)V
        //   291: getstatic       com/jetbrains/cidr/lang/symbols/OCBuilderDriver.AST_NAMED_NODE_STRUCTURE:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$NamedNodeStructure;
        //   294: aload           5
        //   296: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.<init>:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$NamedNodeStructure;Lcom/intellij/util/Processor;)V
        //   299: astore          7
        //   301: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   304: dup            
        //   305: aconst_null    
        //   306: aconst_null    
        //   307: aconst_null    
        //   308: aconst_null    
        //   309: aload_0        
        //   310: iconst_0       
        //   311: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //   314: astore          8
        //   316: new             Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //   319: dup            
        //   320: aload           6
        //   322: aload_1        
        //   323: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   328: aload           8
        //   330: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.<init>:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   333: astore          9
        //   335: aload_0        
        //   336: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getGlobalContextFromLocal:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   339: astore          10
        //   341: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   344: dup            
        //   345: aconst_null    
        //   346: aconst_null    
        //   347: aload           10
        //   349: aconst_null    
        //   350: aload_0        
        //   351: iconst_0       
        //   352: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //   355: astore          8
        //   357: aload           9
        //   359: aload           8
        //   361: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.setLocalContext:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   364: aload           7
        //   366: aload_2        
        //   367: aload           9
        //   369: aload           5
        //   371: aload           8
        //   373: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   376: dup            
        //   377: ifnonnull       414
        //   380: new             Ljava/lang/IllegalStateException;
        //   383: dup            
        //   384: ldc             "@NotNull method %s.%s must not return null"
        //   386: ldc             2
        //   388: anewarray       Ljava/lang/Object;
        //   391: dup            
        //   392: ldc             0
        //   394: ldc             "com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl"
        //   396: aastore        
        //   397: dup            
        //   398: ldc             1
        //   400: ldc             "getRawType"
        //   402: aastore        
        //   403: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   406: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   409: athrow         
        //   410: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCTypeElementImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  23     39     42     46     Ljava/lang/IllegalArgumentException;
        //  52     69     72     76     Ljava/lang/IllegalArgumentException;
        //  59     92     95     99     Ljava/lang/IllegalArgumentException;
        //  76     129    129    133    Ljava/lang/IllegalArgumentException;
        //  134    171    174    178    Ljava/lang/IllegalArgumentException;
        //  160    208    208    212    Ljava/lang/IllegalArgumentException;
        //  213    250    250    254    Ljava/lang/IllegalArgumentException;
        //  357    410    410    414    Ljava/lang/IllegalArgumentException;
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
    public int getArrayLengths() {
        return this.getNode().getChildren(TokenSet.create(new IElementType[] { OCTokenTypes.LBRACKET })).length;
    }
    
    @Override
    public boolean isEmptyType() {
        final PsiElement firstChild = this.getFirstChild();
        Label_0026: {
            try {
                if (firstChild == null) {
                    break Label_0026;
                }
                final PsiElement psiElement = firstChild;
                final IElementType elementType = OCElementUtil.getElementType(psiElement);
                final OCElementType ocElementType = OCElementTypes.EMPTY_NAME;
                if (elementType == ocElementType) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = firstChild;
                final IElementType elementType = OCElementUtil.getElementType(psiElement);
                final OCElementType ocElementType = OCElementTypes.EMPTY_NAME;
                if (elementType == ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
