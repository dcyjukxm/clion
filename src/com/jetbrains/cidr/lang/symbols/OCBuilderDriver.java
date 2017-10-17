// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCNullability;
import java.util.EnumMap;
import java.util.EnumSet;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.parser.OCPragmaOnceContentElementType;
import com.jetbrains.cidr.lang.symbols.cpp.OCUndefMacroSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafType;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.jetbrains.cidr.lang.preprocessor.OCModuleResolver;
import com.jetbrains.cidr.lang.symbols.expression.OCUnknownExpressionSymbol;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.util.OCStringLiteralUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import java.util.Comparator;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.io.Serializable;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCAliasUsingSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.CommonProcessors;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbolImpl;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbolImpl;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbolImpl;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCTypeBuilder;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.symtable.OCNamesInternary;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Iterator;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.util.Processor;
import com.intellij.lang.NodeStructure;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;

public class OCBuilderDriver<T> extends BuilderDriverBase<T, OCFile>
{
    protected boolean myImplicitBridgingOn;
    protected boolean myAssumeNonNullOn;
    private boolean myHasNullabilityFeature;
    private boolean myHasClassPropertyFeature;
    
    public OCBuilderDriver(@NotNull final OCFile ocFile, @NotNull final OCInclusionContext ocInclusionContext, @Nullable final String s, @NotNull final FlyweightCapableTreeStructure<T> flyweightCapableTreeStructure, @NotNull final NodeStructure<T> nodeStructure, @NotNull final Processor<OCSymbol> processor) {
        if (ocFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (ocInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (flyweightCapableTreeStructure == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structure", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (nodeStructure == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeStructure", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (processor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        super((PsiFile)ocFile, ocInclusionContext, s, flyweightCapableTreeStructure, nodeStructure, processor, OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile));
        ocInclusionContext.getConfiguration();
        this.myHasNullabilityFeature = OCCompilerFeatures.supportsNullability(ocInclusionContext);
        this.myHasClassPropertyFeature = OCCompilerFeatures.supportsClassProperty(ocInclusionContext);
    }
    
    public OCBuilderDriver(@NotNull final OCFile ocFile, @NotNull final OCInclusionContext ocInclusionContext, @NotNull final FlyweightCapableTreeStructure<T> flyweightCapableTreeStructure, @NotNull final NamedNodeStructure<T> namedNodeStructure, @NotNull final Processor<OCSymbol> processor) {
        if (ocFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (ocInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (flyweightCapableTreeStructure == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structure", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (namedNodeStructure == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeStructure", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        if (processor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "<init>"));
        }
        super((PsiFile)ocFile, ocInclusionContext, null, flyweightCapableTreeStructure, (com.intellij.lang.NodeStructure<T>)namedNodeStructure, processor, OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile));
    }
    
    public void processDeclarationsList(@NotNull final T p0) {
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
        //    18: ldc             "root"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processDeclarationsList"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore_2       
        //    46: aload_0        
        //    47: aload_1        
        //    48: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //    51: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //    56: astore_3       
        //    57: aload_3        
        //    58: invokeinterface java/util/Iterator.hasNext:()Z
        //    63: ifeq            518
        //    66: aload_3        
        //    67: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    72: astore          4
        //    74: aload_0        
        //    75: aload           4
        //    77: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //    80: astore          5
        //    82: aload           5
        //    84: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    87: if_acmpeq       135
        //    90: aload           5
        //    92: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    95: if_acmpeq       135
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload           5
        //   107: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   110: if_acmpeq       135
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload           5
        //   122: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_KR_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   125: if_acmpne       191
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   138: dup            
        //   139: aload           5
        //   141: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   144: if_acmpne       164
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   157: goto            165
        //   160: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: aconst_null    
        //   165: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   168: astore          6
        //   170: aload           6
        //   172: aload_0        
        //   173: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   176: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setAssumeNonNull:(Z)V
        //   179: aload_0        
        //   180: aload           4
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.builder:Lcom/intellij/util/Processor;
        //   186: aload           6
        //   188: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclaration:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   191: aload           5
        //   193: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.OBJC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   196: if_acmpne       271
        //   199: aload_0        
        //   200: aload           4
        //   202: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   205: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   210: astore          6
        //   212: aload           6
        //   214: invokeinterface java/util/Iterator.hasNext:()Z
        //   219: ifeq            268
        //   222: aload           6
        //   224: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   229: astore          7
        //   231: aload_0        
        //   232: aload           7
        //   234: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   237: astore          8
        //   239: aload           8
        //   241: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CLASS_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   244: if_acmpeq       262
        //   247: aload           8
        //   249: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PROTOCOL_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   252: if_acmpne       265
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: aload           8
        //   264: astore_2       
        //   265: goto            212
        //   268: goto            509
        //   271: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   274: aload           5
        //   276: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   279: ifne            297
        //   282: aload           5
        //   284: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASS_PREDEF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   287: if_acmpne       315
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: aload_0        
        //   298: aload           4
        //   300: aload_0        
        //   301: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.builder:Lcom/intellij/util/Processor;
        //   304: aload_2        
        //   305: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processClassDeclaration:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/intellij/psi/tree/IElementType;)V
        //   308: goto            509
        //   311: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: aload           5
        //   317: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPATIBILITY_ALIAS:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   320: if_acmpne       347
        //   323: aload_0        
        //   324: aload           4
        //   326: aload_0        
        //   327: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.builder:Lcom/intellij/util/Processor;
        //   330: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   333: dup            
        //   334: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:()V
        //   337: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   340: goto            509
        //   343: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   346: athrow         
        //   347: aload           5
        //   349: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPORT_MODULE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   352: if_acmpne       368
        //   355: aload_0        
        //   356: aload           4
        //   358: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processImportModuleStatement:(Ljava/lang/Object;)V
        //   361: goto            509
        //   364: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: aload           5
        //   370: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASS_PREDEF_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   373: if_acmpne       389
        //   376: aload_0        
        //   377: aload           4
        //   379: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclarationsList:(Ljava/lang/Object;)V
        //   382: goto            509
        //   385: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: aload           5
        //   391: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   394: if_acmpne       424
        //   397: aload_0        
        //   398: aload           4
        //   400: aload_0        
        //   401: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.builder:Lcom/intellij/util/Processor;
        //   404: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   407: dup            
        //   408: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:()V
        //   411: aconst_null    
        //   412: aconst_null    
        //   413: aconst_null    
        //   414: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processNamespace:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/lang/String;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //   417: goto            509
        //   420: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: aload           5
        //   426: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   429: if_acmpne       456
        //   432: aload_0        
        //   433: aload           4
        //   435: aload_0        
        //   436: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.builder:Lcom/intellij/util/Processor;
        //   439: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   442: dup            
        //   443: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:()V
        //   446: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processNamespaceAlias:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   449: goto            509
        //   452: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   455: athrow         
        //   456: aload           5
        //   458: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_USING_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   461: if_acmpne       488
        //   464: aload_0        
        //   465: aload           4
        //   467: aload_0        
        //   468: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.builder:Lcom/intellij/util/Processor;
        //   471: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   474: dup            
        //   475: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:()V
        //   478: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processUsingStatement:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   481: goto            509
        //   484: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload           5
        //   490: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_EXTERN_BLOCK:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   493: if_acmpne       509
        //   496: aload_0        
        //   497: aload           4
        //   499: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclarationsList:(Ljava/lang/Object;)V
        //   502: goto            509
        //   505: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   508: athrow         
        //   509: aload_0        
        //   510: aload           4
        //   512: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/Object;)V
        //   515: goto            57
        //   518: return         
        //    Signature:
        //  (TT;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  82     98     101    105    Ljava/lang/IllegalArgumentException;
        //  90     113    116    120    Ljava/lang/IllegalArgumentException;
        //  105    128    131    135    Ljava/lang/IllegalArgumentException;
        //  120    147    150    154    Ljava/lang/IllegalArgumentException;
        //  135    160    160    164    Ljava/lang/IllegalArgumentException;
        //  239    255    258    262    Ljava/lang/IllegalArgumentException;
        //  271    290    293    297    Ljava/lang/IllegalArgumentException;
        //  282    311    311    315    Ljava/lang/IllegalArgumentException;
        //  315    343    343    347    Ljava/lang/IllegalArgumentException;
        //  347    364    364    368    Ljava/lang/IllegalArgumentException;
        //  368    385    385    389    Ljava/lang/IllegalArgumentException;
        //  389    420    420    424    Ljava/lang/IllegalArgumentException;
        //  424    452    452    456    Ljava/lang/IllegalArgumentException;
        //  456    484    484    488    Ljava/lang/IllegalArgumentException;
        //  488    502    505    509    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0105:
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
    
    private void b(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processDefines"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IElementType type = this.type(t);
        try {
            if (type == OCElementTypes.MACRO_DEFINITION) {
                this.processDefineDirective(t, true);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (type == OCElementTypes.MACRO_UNDEFINITION) {
                this.processDefineDirective(t, false);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (type == OCElementTypes.IMPORT_DIRECTIVE) {
                this.processImportDirective(t);
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        if (type == OCElementTypes.CPP_PRAGMA) {
            for (final T next : this.getChildren(t)) {
                if (this.type(next) == OCTokenTypes.STRING_LITERAL) {
                    final String nodeText = this.nodeText(next);
                    try {
                        if ("\"clang arc_cf_code_audited begin\"".equals(nodeText)) {
                            this.myImplicitBridgingOn = true;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        if ("\"clang arc_cf_code_audited end\"".equals(nodeText)) {
                            this.myImplicitBridgingOn = false;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    try {
                        if ("\"clang assume_nonnull begin\"".equals(nodeText)) {
                            this.myAssumeNonNullOn = true;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                    try {
                        if (!"\"clang assume_nonnull end\"".equals(nodeText)) {
                            continue;
                        }
                        this.myAssumeNonNullOn = false;
                    }
                    catch (IllegalArgumentException ex8) {
                        throw b(ex8);
                    }
                }
            }
        }
        else {
            try {
                if (type == OCElementTypes.PRAGMA) {
                    this.i(t);
                    return;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
            final Iterator<T> iterator2 = this.getChildren(t).iterator();
            while (iterator2.hasNext()) {
                this.b(iterator2.next());
            }
        }
    }
    
    private void a(@NotNull final T t, @NotNull final Processor<OCSymbol> processor, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processCompatibilityAlias"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processCompatibilityAlias"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processCompatibilityAlias"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        String nodeText = null;
        long complexOffset = -1L;
        OCType processTypeElement = null;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCTokenTypes.IDENTIFIER) {
                nodeText = this.nodeText(next);
                complexOffset = OCSymbolOffsetUtil.getComplexOffset((com.intellij.lang.NodeStructure<Object>)this.nodeStructure, next);
            }
            else {
                if (type != OCElementTypes.REFERENCE_ELEMENT) {
                    continue;
                }
                processTypeElement = this.processTypeElement(next, this.a(declarationContext), processor, declarationContext);
            }
        }
        try {
            if (StringUtil.isEmptyOrSpaces(nodeText) || processTypeElement == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        processor.process((Object)new OCCompatibilityAliasSymbol(this.project, this.myVirtualFile, complexOffset, nodeText, processTypeElement));
    }
    
    public void processClassDeclaration(@NotNull final T p0, @NotNull final Processor<OCSymbol> p1, @Nullable final IElementType p2) {
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
        //    18: ldc             "classNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processClassDeclaration"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "builder"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processClassDeclaration"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //    93: astore          4
        //    95: aconst_null    
        //    96: astore          5
        //    98: aconst_null    
        //    99: astore          6
        //   101: aconst_null    
        //   102: astore          7
        //   104: ldc2_w          -1
        //   107: lstore          8
        //   109: invokestatic    com/intellij/util/containers/ContainerUtil.newHashSet:()Ljava/util/HashSet;
        //   112: astore          10
        //   114: new             Ljava/util/ArrayList;
        //   117: dup            
        //   118: invokespecial   java/util/ArrayList.<init>:()V
        //   121: astore          11
        //   123: new             Ljava/util/ArrayList;
        //   126: dup            
        //   127: invokespecial   java/util/ArrayList.<init>:()V
        //   130: astore          12
        //   132: new             Ljava/util/ArrayList;
        //   135: dup            
        //   136: invokespecial   java/util/ArrayList.<init>:()V
        //   139: astore          13
        //   141: new             Ljava/util/ArrayList;
        //   144: dup            
        //   145: invokespecial   java/util/ArrayList.<init>:()V
        //   148: astore          14
        //   150: new             Ljava/util/ArrayList;
        //   153: dup            
        //   154: invokespecial   java/util/ArrayList.<init>:()V
        //   157: astore          15
        //   159: new             Ljava/util/ArrayList;
        //   162: dup            
        //   163: invokespecial   java/util/ArrayList.<init>:()V
        //   166: astore          16
        //   168: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   171: astore          17
        //   173: aconst_null    
        //   174: astore          18
        //   176: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   179: astore          19
        //   181: iconst_0       
        //   182: istore          20
        //   184: aload_0        
        //   185: aload_1        
        //   186: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   189: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   194: astore          21
        //   196: aload           21
        //   198: invokeinterface java/util/Iterator.hasNext:()Z
        //   203: ifeq            841
        //   206: aload           21
        //   208: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   213: astore          22
        //   215: aload_0        
        //   216: aload           22
        //   218: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   221: astore          23
        //   223: aload           23
        //   225: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   228: if_acmpne       269
        //   231: aload_0        
        //   232: aload           22
        //   234: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   237: astore          24
        //   239: aload           5
        //   241: ifnull          251
        //   244: goto            841
        //   247: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload           24
        //   253: astore          5
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   259: aload           22
        //   261: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getComplexOffset:(Lcom/intellij/lang/NodeStructure;Ljava/lang/Object;)J
        //   264: lstore          8
        //   266: goto            838
        //   269: aload           23
        //   271: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATEGORY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   274: if_acmpne       339
        //   277: ldc             ""
        //   279: astore          6
        //   281: aload_0        
        //   282: aload           22
        //   284: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   287: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   292: astore          24
        //   294: aload           24
        //   296: invokeinterface java/util/Iterator.hasNext:()Z
        //   301: ifeq            336
        //   304: aload           24
        //   306: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   311: astore          25
        //   313: aload_0        
        //   314: aload           25
        //   316: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   319: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   322: if_acmpne       333
        //   325: aload_0        
        //   326: aload           25
        //   328: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   331: astore          6
        //   333: goto            294
        //   336: goto            838
        //   339: aload           23
        //   341: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.OBJC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   344: if_acmpne       418
        //   347: aload_0        
        //   348: aload           22
        //   350: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   353: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   358: astore          24
        //   360: aload           24
        //   362: invokeinterface java/util/Iterator.hasNext:()Z
        //   367: ifeq            415
        //   370: aload           24
        //   372: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   377: astore          25
        //   379: aload_0        
        //   380: aload           25
        //   382: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   385: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPTIONAL_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   388: if_acmpne       397
        //   391: iconst_1       
        //   392: istore          20
        //   394: goto            412
        //   397: aload_0        
        //   398: aload           25
        //   400: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   403: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.REQUIRED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   406: if_acmpne       412
        //   409: iconst_0       
        //   410: istore          20
        //   412: goto            360
        //   415: goto            838
        //   418: aload           23
        //   420: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   423: if_acmpne       443
        //   426: aload           15
        //   428: aload           22
        //   430: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   435: pop            
        //   436: goto            838
        //   439: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   442: athrow         
        //   443: aload           23
        //   445: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   448: if_acmpne       517
        //   451: aload_0        
        //   452: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   455: ifeq            482
        //   458: goto            465
        //   461: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   464: athrow         
        //   465: aload           10
        //   467: aload           22
        //   469: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   474: pop            
        //   475: goto            482
        //   478: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   481: athrow         
        //   482: iload           20
        //   484: ifeq            504
        //   487: aload           11
        //   489: aload           22
        //   491: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   496: pop            
        //   497: goto            838
        //   500: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   503: athrow         
        //   504: aload           12
        //   506: aload           22
        //   508: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   513: pop            
        //   514: goto            838
        //   517: aload           23
        //   519: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   522: if_acmpne       591
        //   525: aload_0        
        //   526: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   529: ifeq            556
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: aload           10
        //   541: aload           22
        //   543: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   548: pop            
        //   549: goto            556
        //   552: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   555: athrow         
        //   556: iload           20
        //   558: ifeq            578
        //   561: aload           13
        //   563: aload           22
        //   565: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   570: pop            
        //   571: goto            838
        //   574: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   577: athrow         
        //   578: aload           14
        //   580: aload           22
        //   582: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   587: pop            
        //   588: goto            838
        //   591: aload           23
        //   593: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   596: if_acmpne       606
        //   599: aload           22
        //   601: astore          18
        //   603: goto            838
        //   606: aload           23
        //   608: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SUPER_CLASS_REF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   611: if_acmpne       645
        //   614: aload_0        
        //   615: aload           22
        //   617: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.c:(Ljava/lang/Object;)Ljava/util/List;
        //   620: astore          24
        //   622: aload           24
        //   624: invokeinterface java/util/List.size:()I
        //   629: ifle            642
        //   632: aload           24
        //   634: iconst_0       
        //   635: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   640: astore          7
        //   642: goto            838
        //   645: aload           23
        //   647: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ATTRIBUTES:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   650: if_acmpne       698
        //   653: aload           17
        //   655: invokeinterface java/util/List.isEmpty:()Z
        //   660: ifeq            681
        //   663: goto            670
        //   666: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   669: athrow         
        //   670: aload_0        
        //   671: aload           22
        //   673: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //   676: astore          17
        //   678: goto            838
        //   681: aload           17
        //   683: aload_0        
        //   684: aload           22
        //   686: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //   689: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   694: pop            
        //   695: goto            838
        //   698: aload           23
        //   700: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROTOCOL_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   703: if_acmpne       717
        //   706: aload_0        
        //   707: aload           22
        //   709: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;)Ljava/util/List;
        //   712: astore          19
        //   714: goto            838
        //   717: aload           23
        //   719: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   722: if_acmpeq       755
        //   725: aload           23
        //   727: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   730: if_acmpeq       755
        //   733: goto            740
        //   736: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   739: athrow         
        //   740: aload           23
        //   742: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   745: if_acmpne       813
        //   748: goto            755
        //   751: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   754: athrow         
        //   755: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   758: dup            
        //   759: aload           23
        //   761: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   764: if_acmpne       784
        //   767: goto            774
        //   770: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   773: athrow         
        //   774: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   777: goto            787
        //   780: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   783: athrow         
        //   784: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   787: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   790: astore          24
        //   792: aload           24
        //   794: aload_0        
        //   795: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   798: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setAssumeNonNull:(Z)V
        //   801: aload_0        
        //   802: aload           22
        //   804: aload_2        
        //   805: aload           24
        //   807: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclaration:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   810: goto            838
        //   813: aload           23
        //   815: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.GENERIC_PARAMETERS_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   818: if_acmpne       832
        //   821: aload_0        
        //   822: aload           22
        //   824: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.g:(Ljava/lang/Object;)Ljava/util/List;
        //   827: astore          16
        //   829: goto            838
        //   832: aload_0        
        //   833: aload           22
        //   835: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/Object;)V
        //   838: goto            196
        //   841: aload           5
        //   843: ifnull          1454
        //   846: new             Lcom/intellij/util/containers/MostlySingularMultiMap;
        //   849: dup            
        //   850: invokespecial   com/intellij/util/containers/MostlySingularMultiMap.<init>:()V
        //   853: astore          21
        //   855: aload           16
        //   857: invokeinterface java/util/List.isEmpty:()Z
        //   862: ifne            875
        //   865: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:()Ljava/util/ArrayList;
        //   868: goto            878
        //   871: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   874: athrow         
        //   875: invokestatic    com/intellij/util/containers/ContainerUtil.emptyList:()Ljava/util/List;
        //   878: astore          22
        //   880: aload           4
        //   882: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INTERFACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   885: if_acmpne       923
        //   888: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl;
        //   891: dup            
        //   892: aload_0        
        //   893: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   896: aload_0        
        //   897: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   900: lload           8
        //   902: aload           5
        //   904: aload           17
        //   906: aload           6
        //   908: aload           21
        //   910: aload           19
        //   912: aconst_null    
        //   913: aload           22
        //   915: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCReferenceType;Ljava/util/List;)V
        //   918: astore          23
        //   920: goto            1101
        //   923: aload           4
        //   925: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPLEMENTATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   928: if_acmpne       962
        //   931: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   934: dup            
        //   935: aload_0        
        //   936: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   939: aload_0        
        //   940: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   943: lload           8
        //   945: aload           5
        //   947: aload           17
        //   949: aload           6
        //   951: aload           21
        //   953: aconst_null    
        //   954: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/types/OCReferenceType;)V
        //   957: astore          23
        //   959: goto            1101
        //   962: aload           4
        //   964: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROTOCOL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   967: if_acmpne       1003
        //   970: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl;
        //   973: dup            
        //   974: aload_0        
        //   975: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   978: aload_0        
        //   979: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   982: lload           8
        //   984: aload           5
        //   986: aload           17
        //   988: aload           6
        //   990: aload           21
        //   992: aload           19
        //   994: aconst_null    
        //   995: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCReferenceType;)V
        //   998: astore          23
        //  1000: goto            1101
        //  1003: aload           4
        //  1005: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASS_PREDEF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1008: if_acmpne       1091
        //  1011: aload_3        
        //  1012: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PROTOCOL_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1015: if_acmpne       1057
        //  1018: goto            1025
        //  1021: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1024: athrow         
        //  1025: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl;
        //  1028: dup            
        //  1029: aload_0        
        //  1030: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1033: aload_0        
        //  1034: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1037: lload           8
        //  1039: aload           5
        //  1041: aload           17
        //  1043: aconst_null    
        //  1044: aconst_null    
        //  1045: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1048: aconst_null    
        //  1049: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbolImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCReferenceType;)V
        //  1052: astore          23
        //  1054: goto            1101
        //  1057: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl;
        //  1060: dup            
        //  1061: aload_0        
        //  1062: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1065: aload_0        
        //  1066: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1069: lload           8
        //  1071: aload           5
        //  1073: aload           17
        //  1075: aconst_null    
        //  1076: aconst_null    
        //  1077: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1080: aconst_null    
        //  1081: aload           22
        //  1083: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCReferenceType;Ljava/util/List;)V
        //  1086: astore          23
        //  1088: goto            1101
        //  1091: new             Ljava/lang/AssertionError;
        //  1094: dup            
        //  1095: ldc             "Unknown class kind"
        //  1097: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //  1100: athrow         
        //  1101: aload_0        
        //  1102: aload           7
        //  1104: aload           23
        //  1106: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)V
        //  1109: aload           16
        //  1111: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1116: astore          24
        //  1118: aload           24
        //  1120: invokeinterface java/util/Iterator.hasNext:()Z
        //  1125: ifeq            1150
        //  1128: aload           24
        //  1130: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1135: astore          25
        //  1137: aload_0        
        //  1138: aload           25
        //  1140: aload           22
        //  1142: aload           23
        //  1144: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processGenericParameter:(Ljava/lang/Object;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)V
        //  1147: goto            1118
        //  1150: aload           22
        //  1152: instanceof      Ljava/util/ArrayList;
        //  1155: ifeq            1173
        //  1158: aload           22
        //  1160: checkcast       Ljava/util/ArrayList;
        //  1163: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  1166: goto            1173
        //  1169: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1172: athrow         
        //  1173: aload           12
        //  1175: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1180: astore          24
        //  1182: aload           24
        //  1184: invokeinterface java/util/Iterator.hasNext:()Z
        //  1189: ifeq            1224
        //  1192: aload           24
        //  1194: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1199: astore          25
        //  1201: aload_0        
        //  1202: aload           25
        //  1204: aload           21
        //  1206: aload           23
        //  1208: iconst_0       
        //  1209: aload           10
        //  1211: aload           25
        //  1213: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //  1218: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;ZZ)V
        //  1221: goto            1182
        //  1224: aload           11
        //  1226: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1231: astore          24
        //  1233: aload           24
        //  1235: invokeinterface java/util/Iterator.hasNext:()Z
        //  1240: ifeq            1275
        //  1243: aload           24
        //  1245: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1250: astore          25
        //  1252: aload_0        
        //  1253: aload           25
        //  1255: aload           21
        //  1257: aload           23
        //  1259: iconst_1       
        //  1260: aload           10
        //  1262: aload           25
        //  1264: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //  1269: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;ZZ)V
        //  1272: goto            1233
        //  1275: aload           14
        //  1277: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1282: astore          24
        //  1284: aload           24
        //  1286: invokeinterface java/util/Iterator.hasNext:()Z
        //  1291: ifeq            1328
        //  1294: aload           24
        //  1296: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1301: astore          25
        //  1303: aload_0        
        //  1304: aload           25
        //  1306: aload           21
        //  1308: aload           23
        //  1310: aload           4
        //  1312: iconst_0       
        //  1313: aload           10
        //  1315: aload           25
        //  1317: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //  1322: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processProperty:(Ljava/lang/Object;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/intellij/psi/tree/IElementType;ZZ)V
        //  1325: goto            1284
        //  1328: aload           13
        //  1330: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1335: astore          24
        //  1337: aload           24
        //  1339: invokeinterface java/util/Iterator.hasNext:()Z
        //  1344: ifeq            1381
        //  1347: aload           24
        //  1349: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1354: astore          25
        //  1356: aload_0        
        //  1357: aload           25
        //  1359: aload           21
        //  1361: aload           23
        //  1363: aload           4
        //  1365: iconst_1       
        //  1366: aload           10
        //  1368: aload           25
        //  1370: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //  1375: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processProperty:(Ljava/lang/Object;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/intellij/psi/tree/IElementType;ZZ)V
        //  1378: goto            1337
        //  1381: aload           15
        //  1383: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1388: astore          24
        //  1390: aload           24
        //  1392: invokeinterface java/util/Iterator.hasNext:()Z
        //  1397: ifeq            1422
        //  1400: aload           24
        //  1402: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1407: astore          25
        //  1409: aload_0        
        //  1410: aload           25
        //  1412: aload           21
        //  1414: aload           23
        //  1416: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)V
        //  1419: goto            1390
        //  1422: aload           18
        //  1424: ifnull          1445
        //  1427: aload_0        
        //  1428: aload           18
        //  1430: aload           21
        //  1432: aload           23
        //  1434: aload_2        
        //  1435: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/intellij/util/Processor;)V
        //  1438: goto            1445
        //  1441: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1444: athrow         
        //  1445: aload_2        
        //  1446: aload           23
        //  1448: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //  1453: pop            
        //  1454: return         
        //    Signature:
        //  (TT;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/intellij/psi/tree/IElementType;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  239    247    247    251    Ljava/lang/IllegalArgumentException;
        //  418    439    439    443    Ljava/lang/IllegalArgumentException;
        //  443    458    461    465    Ljava/lang/IllegalArgumentException;
        //  451    475    478    482    Ljava/lang/IllegalArgumentException;
        //  482    500    500    504    Ljava/lang/IllegalArgumentException;
        //  517    532    535    539    Ljava/lang/IllegalArgumentException;
        //  525    549    552    556    Ljava/lang/IllegalArgumentException;
        //  556    574    574    578    Ljava/lang/IllegalArgumentException;
        //  645    663    666    670    Ljava/lang/IllegalArgumentException;
        //  717    733    736    740    Ljava/lang/IllegalArgumentException;
        //  725    748    751    755    Ljava/lang/IllegalArgumentException;
        //  740    767    770    774    Ljava/lang/IllegalArgumentException;
        //  755    780    780    784    Ljava/lang/IllegalArgumentException;
        //  855    871    871    875    Ljava/lang/IllegalArgumentException;
        //  1003   1018   1021   1025   Ljava/lang/IllegalArgumentException;
        //  1150   1166   1169   1173   Ljava/lang/IllegalArgumentException;
        //  1422   1438   1441   1445   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0740:
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
    
    private void a(@Nullable final T t, @NotNull final OCClassSymbol ocClassSymbol) {
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "klass", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSuperClassRef"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (t != null) {
            final DeclarationContext<T> declarationContext = this.createDeclarationContext(a(ocClassSymbol));
            final OCTypeBuilder a = this.a(declarationContext);
            this.a(t, a, declarationContext);
            final OCType result = a.getResult(false);
            try {
                if (result instanceof OCReferenceType) {
                    ocClassSymbol.setSuperType((OCReferenceType)result);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            ocClassSymbol.setSuperType(OCReferenceType.fromText(OCNamesInternary.intern(this.nodeTextNoIntern(t, false).trim()), Collections.emptyList()));
        }
        else {
            ocClassSymbol.setSuperType(OCReferenceType.fromText("", Collections.emptyList()));
        }
    }
    
    public void processGenericParameter(@NotNull final T t, @NotNull final List<OCGenericParameterSymbol> list, @NotNull final OCClassSymbol ocClassSymbol) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "genericParameterNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processGenericParameter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processGenericParameter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processGenericParameter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCGenericParameterSymbol processGenericParameter = this.processGenericParameter(t, new DeclarationContext<T>(null, ocClassSymbol, null, null, null, this.myAssumeNonNullOn));
        try {
            if (processGenericParameter != null) {
                list.add(processGenericParameter);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
    }
    
    @Nullable
    public OCGenericParameterSymbol processGenericParameter(@NotNull final T t, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "genericParameterNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processGenericParameter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processGenericParameter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCGenericParameterSymbol.Covariance covariance = OCGenericParameterSymbol.Covariance.INVARIANT;
        OCType ocType = null;
        String nodeText = null;
        long complexOffsetFromNode = -1L;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCTokenTypes.COVARIANT_KEYWORD) {
                covariance = OCGenericParameterSymbol.Covariance.COVARIANT;
            }
            else if (type == OCTokenTypes.CONTRAVARIANT_KEYWORD) {
                covariance = OCGenericParameterSymbol.Covariance.CONTRAVARIANT;
            }
            else if (type == OCElementTypes.TYPE_ELEMENT) {
                ocType = this.processTypeElement(next, this.a(declarationContext), this.builder, declarationContext);
            }
            else {
                if (type != OCTokenTypes.IDENTIFIER) {
                    continue;
                }
                nodeText = this.nodeText(next);
                complexOffsetFromNode = this.getComplexOffsetFromNode(t);
            }
        }
        Label_0252: {
            try {
                if (nodeText == null) {
                    break Label_0252;
                }
                final long n = complexOffsetFromNode;
                final long n2 = -1L;
                final long n3 = lcmp(n, n2);
                if (n3 == 0) {
                    break Label_0252;
                }
                break Label_0252;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final long n = complexOffsetFromNode;
                final long n2 = -1L;
                final long n3 = lcmp(n, n2);
                if (n3 == 0) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        if (ocType == null) {
            ocType = OCIdType.pointerToID(this.project);
        }
        return new OCGenericParameterSymbolImpl(this.project, this.myVirtualFile, complexOffsetFromNode, nodeText, Collections.emptyList(), covariance, ocType);
    }
    
    private void a(@NotNull final T t, @NotNull final MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap, @NotNull final OCClassSymbol ocClassSymbol) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "propertyNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSynthesizedProperties"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (mostlySingularMultiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSynthesizedProperties"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSynthesizedProperties"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        boolean b = false;
        boolean b2 = false;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCElementTypes.PROPERTY_ATTRIBUTES_LIST) {
                for (final T next2 : this.getChildren(next)) {
                    if (this.type(next2) == OCElementTypes.PROPERTY_ATTRIBUTE) {
                        for (final T next3 : this.getChildren(next2)) {
                            if (this.type(next3) == OCTokenTypes.IDENTIFIER) {
                                final OCPropertySymbol.PropertyAttribute attribute = OCPropertySymbolImpl.parseAttribute(this.nodeText(next3));
                                try {
                                    if (this.myHasClassPropertyFeature || attribute != OCPropertySymbol.PropertyAttribute.CLASS) {
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw b(ex4);
                                }
                                b2 = true;
                            }
                        }
                    }
                }
            }
            else if (type == OCElementTypes.OBJC_KEYWORD) {
                final Iterator<T> iterator4 = this.getChildren(next).iterator();
                while (iterator4.hasNext()) {
                    if (this.type(iterator4.next()) == OCTokenTypes.SYNTHESIZE_KEYWORD) {
                        b = true;
                    }
                }
            }
            else {
                if (type != OCElementTypes.SYNTHESIZED_PROPERTY) {
                    continue;
                }
                T t2 = null;
                T t3 = null;
                for (final T next4 : this.getChildren(next)) {
                    Label_0456: {
                        try {
                            if (this.type(next4) != OCElementTypes.REFERENCE_ELEMENT) {
                                continue;
                            }
                            if (t2 != null) {
                                break Label_0456;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                        t2 = next4;
                        continue;
                    }
                    t3 = next4;
                }
                try {
                    if (t2 == null) {
                        throw new AssertionError((Object)"Property node is null");
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                if (t3 == null) {
                    t3 = t2;
                }
                final String nodeText = this.nodeText(t2);
                final String nodeText2 = this.nodeText(t3);
                String s = null;
                Project project = null;
                VirtualFile containingFile = null;
                long complexOffsetFromNode = 0L;
                String s2 = null;
                List<String> emptyList = null;
                boolean b3 = false;
                boolean b4 = false;
                String s3 = null;
                Label_0557: {
                    try {
                        s = nodeText;
                        project = this.project;
                        containingFile = ocClassSymbol.getContainingFile();
                        complexOffsetFromNode = this.getComplexOffsetFromNode(next);
                        s2 = nodeText;
                        emptyList = Collections.emptyList();
                        b3 = b;
                        b4 = b2;
                        if (t3 != t2) {
                            s3 = nodeText2;
                            break Label_0557;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                    s3 = null;
                }
                mostlySingularMultiMap.add((Object)s, (Object)new OCSynthesizeSymbol(project, containingFile, complexOffsetFromNode, s2, emptyList, ocClassSymbol, b3, b4, s3));
                if (!b) {
                    continue;
                }
                mostlySingularMultiMap.add((Object)nodeText2, (Object)new OCInstanceVariableSymbolImpl(this.project, ocClassSymbol.getContainingFile(), this.getComplexOffsetFromNode(t3), nodeText2, Collections.emptyList(), ocClassSymbol, OCUnknownType.INSTANCE, OCVisibility.PRIVATE, nodeText));
            }
        }
    }
    
    @NotNull
    private List<T> c(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReferences"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        List<T> list = null;
        for (final T next : this.getChildren(t)) {
            Label_0105: {
                try {
                    if (this.type(next) != OCElementTypes.REFERENCE_ELEMENT) {
                        continue;
                    }
                    if (list != null) {
                        break Label_0105;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                list = new ArrayList<T>();
            }
            list.add(next);
        }
        List<T> list2 = null;
        Label_0132: {
            try {
                if (list != null) {
                    final List<T> emptyList;
                    list2 = (emptyList = list);
                    break Label_0132;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            List<T> emptyList;
            list2 = (emptyList = Collections.emptyList());
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReferences"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return list2;
    }
    
    @NotNull
    private List<String> a(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolListNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReferenceNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        List map;
        try {
            map = ContainerUtil.map((Collection)this.c(t), o -> OCNamesInternary.intern(this.nodeTextNoIntern((T)o, false).trim()));
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReferenceNames"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (List<String>)map;
    }
    
    @NotNull
    private List<T> g(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "genericParametersListNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectGenericParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        List<T> list = null;
        for (final T next : this.getChildren(t)) {
            Label_0105: {
                try {
                    if (this.type(next) != OCElementTypes.GENERIC_PARAMETER) {
                        continue;
                    }
                    if (list != null) {
                        break Label_0105;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                list = new ArrayList<T>();
            }
            list.add(next);
        }
        List<T> list2 = null;
        Label_0132: {
            try {
                if (list != null) {
                    final List<T> emptyList;
                    list2 = (emptyList = list);
                    break Label_0132;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            List<T> emptyList;
            list2 = (emptyList = Collections.emptyList());
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectGenericParameters"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return list2;
    }
    
    public void processProperty(@NotNull final T p0, @NotNull final MostlySingularMultiMap<String, OCMemberSymbol> p1, @NotNull final OCClassSymbol p2, @NotNull final IElementType p3, final boolean p4, final boolean p5) {
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
        //    18: ldc             "propertyNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processProperty"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "members"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processProperty"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "parent"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processProperty"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   151: ldc             "classType"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "processProperty"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;.class
        //   179: invokestatic    java/util/EnumSet.noneOf:(Ljava/lang/Class;)Ljava/util/EnumSet;
        //   182: astore          7
        //   184: new             Ljava/util/EnumMap;
        //   187: dup            
        //   188: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;.class
        //   190: invokespecial   java/util/EnumMap.<init>:(Ljava/lang/Class;)V
        //   193: astore          8
        //   195: aload_0        
        //   196: aload_1        
        //   197: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   200: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   205: astore          9
        //   207: aload           9
        //   209: invokeinterface java/util/Iterator.hasNext:()Z
        //   214: ifeq            605
        //   217: aload           9
        //   219: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   224: astore          10
        //   226: aload_0        
        //   227: aload           10
        //   229: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   232: astore          11
        //   234: aload           11
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY_ATTRIBUTES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   239: if_acmpne       533
        //   242: aload_0        
        //   243: aload           10
        //   245: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   248: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   253: astore          12
        //   255: aload           12
        //   257: invokeinterface java/util/Iterator.hasNext:()Z
        //   262: ifeq            530
        //   265: aload           12
        //   267: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   272: astore          13
        //   274: aload_0        
        //   275: aload           13
        //   277: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   280: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY_ATTRIBUTE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   283: if_acmpne       527
        //   286: aconst_null    
        //   287: astore          14
        //   289: aload_0        
        //   290: aload           13
        //   292: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   295: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   300: astore          15
        //   302: aload           15
        //   304: invokeinterface java/util/Iterator.hasNext:()Z
        //   309: ifeq            527
        //   312: aload           15
        //   314: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   319: astore          16
        //   321: aload_0        
        //   322: aload           16
        //   324: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   327: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   330: if_acmpne       524
        //   333: aload           14
        //   335: ifnonnull       483
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload_0        
        //   346: aload           16
        //   348: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   351: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.parseAttribute:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   354: astore          14
        //   356: aload_0        
        //   357: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myHasNullabilityFeature:Z
        //   360: ifne            394
        //   363: aload           14
        //   365: ifnull          394
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: aload           14
        //   377: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //   380: iconst_5       
        //   381: if_icmpne       394
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: aconst_null    
        //   392: astore          14
        //   394: aload_0        
        //   395: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myHasClassPropertyFeature:Z
        //   398: ifne            433
        //   401: aload           14
        //   403: ifnull          433
        //   406: goto            413
        //   409: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   412: athrow         
        //   413: aload           14
        //   415: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //   418: bipush          6
        //   420: if_icmpne       433
        //   423: goto            430
        //   426: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   429: athrow         
        //   430: aconst_null    
        //   431: astore          14
        //   433: aload           14
        //   435: ifnull          524
        //   438: aload           14
        //   440: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.GETTER:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   443: if_acmpeq       524
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: aload           14
        //   455: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.SETTER:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   458: if_acmpeq       524
        //   461: goto            468
        //   464: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   467: athrow         
        //   468: aload           7
        //   470: aload           14
        //   472: invokevirtual   java/util/EnumSet.add:(Ljava/lang/Object;)Z
        //   475: pop            
        //   476: goto            524
        //   479: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   482: athrow         
        //   483: aload           14
        //   485: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.GETTER:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   488: if_acmpeq       506
        //   491: aload           14
        //   493: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.SETTER:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   496: if_acmpne       524
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload_0        
        //   507: aload           16
        //   509: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   512: astore          17
        //   514: aload           8
        //   516: aload           14
        //   518: aload           17
        //   520: invokevirtual   java/util/EnumMap.put:(Ljava/lang/Enum;Ljava/lang/Object;)Ljava/lang/Object;
        //   523: pop            
        //   524: goto            302
        //   527: goto            255
        //   530: goto            602
        //   533: aload           11
        //   535: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   538: if_acmpeq       556
        //   541: aload           11
        //   543: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   546: if_acmpne       602
        //   549: goto            556
        //   552: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   555: athrow         
        //   556: aload_0        
        //   557: aload           10
        //   559: aload_0        
        //   560: aload           7
        //   562: aload_3        
        //   563: iload           5
        //   565: aload           8
        //   567: aload_2        
        //   568: aload           4
        //   570: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCBuilderDriver;Ljava/util/EnumSet;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;ZLjava/util/EnumMap;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/util/Processor;
        //   575: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   578: dup            
        //   579: aconst_null    
        //   580: aconst_null    
        //   581: aload_3        
        //   582: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   585: aconst_null    
        //   586: aconst_null    
        //   587: iload           6
        //   589: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //   592: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclaration:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   595: goto            602
        //   598: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   601: athrow         
        //   602: goto            207
        //   605: return         
        //    Signature:
        //  (TT;Lcom/intellij/util/containers/MostlySingularMultiMap<Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;>;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/intellij/psi/tree/IElementType;ZZ)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    173    173    177    Ljava/lang/IllegalArgumentException;
        //  321    338    341    345    Ljava/lang/IllegalArgumentException;
        //  356    368    371    375    Ljava/lang/IllegalArgumentException;
        //  363    384    387    391    Ljava/lang/IllegalArgumentException;
        //  394    406    409    413    Ljava/lang/IllegalArgumentException;
        //  401    423    426    430    Ljava/lang/IllegalArgumentException;
        //  433    446    449    453    Ljava/lang/IllegalArgumentException;
        //  438    461    464    468    Ljava/lang/IllegalArgumentException;
        //  453    479    479    483    Ljava/lang/IllegalArgumentException;
        //  483    499    502    506    Ljava/lang/IllegalArgumentException;
        //  533    549    552    556    Ljava/lang/IllegalArgumentException;
        //  541    595    598    602    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0453:
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
    
    private void a(@NotNull final T t, @NotNull final MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap, @NotNull final OCClassSymbol ocClassSymbol, final boolean b, final boolean assumeNonNull) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "methodNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processMethod"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (mostlySingularMultiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processMethod"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processMethod"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final StringBuilder sb = new StringBuilder();
        boolean b2 = false;
        boolean b3 = false;
        OCType processTypeElement = null;
        long n = -1L;
        final ArrayList<OCMethodSymbolImpl.SelectorPartSymbolImpl> list = new ArrayList<OCMethodSymbolImpl.SelectorPartSymbolImpl>();
        List<String> list2 = null;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCElementTypes.METHOD_SELECTOR_PART) {
                final Ref ref = new Ref();
                final OCMethodSymbol.SelectorPartSymbol processSelectorPart = this.processSelectorPart(next, sb, null, (Ref<Long>)ref, ocClassSymbol, assumeNonNull);
                try {
                    if (processSelectorPart != null) {
                        list.add((OCMethodSymbolImpl.SelectorPartSymbolImpl)processSelectorPart);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    if (n != -1L || ref.isNull()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                n = (long)ref.get();
            }
            else if (type == OCElementTypes.PARAMETER_DECLARATION) {
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                final DeclarationContext<T> declarationContext = new DeclarationContext<T>();
                declarationContext.setAssumeNonNull(assumeNonNull);
                this.a(next, declarationContext, (Processor<OCDeclaratorSymbol>)findFirstProcessor);
                list.add(new OCMethodSymbolImpl.SelectorPartSymbolImpl((OCDeclaratorSymbol)findFirstProcessor.getFoundValue(), null));
            }
            else {
                Label_0427: {
                    Label_0395: {
                        try {
                            if (type != OCElementTypes.ATTRIBUTES) {
                                break Label_0427;
                            }
                            final List<String> list3 = list2;
                            if (list3 != null) {
                                break Label_0395;
                            }
                            break Label_0395;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw b(ex6);
                        }
                        try {
                            final List<String> list3 = list2;
                            if (list3 != null) {
                                list2.addAll(this.processAttributeList(next));
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw b(ex7);
                        }
                    }
                    list2 = this.processAttributeList(next);
                    continue;
                }
                if (type == OCElementTypes.TYPE_ELEMENT) {
                    final DeclarationContext<T> declarationContext2 = new DeclarationContext<T>(null, null, a(ocClassSymbol), null, null, assumeNonNull);
                    processTypeElement = this.processTypeElement(next, this.a(declarationContext2), this.builder, declarationContext2);
                    final List<String> attributes = declarationContext2.getAttributes();
                    Label_0498: {
                        try {
                            if (list2 == null) {
                                break Label_0498;
                            }
                            final List<String> list4 = attributes;
                            if (list4 != null) {
                                break Label_0498;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex8) {
                            throw b(ex8);
                        }
                        try {
                            final List<String> list4 = attributes;
                            if (list4 != null) {
                                list2.addAll(attributes);
                                continue;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex9) {
                            throw b(ex9);
                        }
                    }
                    list2 = attributes;
                }
                else if (type == OCTokenTypes.PLUS) {
                    b2 = true;
                }
                else {
                    if (type != OCTokenTypes.ELLIPSIS) {
                        continue;
                    }
                    b3 = true;
                }
            }
        }
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        if (n == -1L) {
            n = this.getComplexOffsetFromNode(t);
        }
        MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap2 = null;
        String s = null;
        OCMethodSymbolImpl ocMethodSymbolImpl = null;
        Project project = null;
        VirtualFile virtualFile = null;
        long n2 = 0L;
        String s2 = null;
        List<String> list5 = null;
        OCClassSymbol ocClassSymbol2 = null;
        boolean b4 = false;
        boolean b5 = false;
        boolean b6 = false;
        OCType pointerToID = null;
        Label_0646: {
            Label_0630: {
                try {
                    if (sb.length() <= 0) {
                        return;
                    }
                    mostlySingularMultiMap2 = mostlySingularMultiMap;
                    final StringBuilder sb2 = sb;
                    s = sb2.toString();
                    ocMethodSymbolImpl = new(com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbolImpl.class);
                    final OCBuilderDriver ocBuilderDriver = this;
                    project = ocBuilderDriver.project;
                    final OCBuilderDriver ocBuilderDriver2 = this;
                    virtualFile = ocBuilderDriver2.myVirtualFile;
                    n2 = n;
                    final StringBuilder sb3 = sb;
                    s2 = sb3.toString();
                    list5 = list2;
                    ocClassSymbol2 = ocClassSymbol;
                    b4 = b2;
                    b5 = b;
                    b6 = b3;
                    final OCType ocType = processTypeElement;
                    if (ocType != null) {
                        break Label_0630;
                    }
                    break Label_0630;
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
                try {
                    mostlySingularMultiMap2 = mostlySingularMultiMap;
                    final StringBuilder sb2 = sb;
                    s = sb2.toString();
                    ocMethodSymbolImpl = new(com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbolImpl.class);
                    final OCBuilderDriver ocBuilderDriver = this;
                    project = ocBuilderDriver.project;
                    final OCBuilderDriver ocBuilderDriver2 = this;
                    virtualFile = ocBuilderDriver2.myVirtualFile;
                    n2 = n;
                    final StringBuilder sb3 = sb;
                    s2 = sb3.toString();
                    list5 = list2;
                    ocClassSymbol2 = ocClassSymbol;
                    b4 = b2;
                    b5 = b;
                    b6 = b3;
                    final OCType ocType = processTypeElement;
                    if (ocType != null) {
                        pointerToID = processTypeElement;
                        break Label_0646;
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw b(ex11);
                }
            }
            pointerToID = OCIdType.pointerToID(this.project);
        }
        new OCMethodSymbolImpl(project, virtualFile, n2, s2, list5, ocClassSymbol2, b4, b5, b6, pointerToID, (List<OCMethodSymbol.SelectorPartSymbol>)list, null);
        mostlySingularMultiMap2.add((Object)s, (Object)ocMethodSymbolImpl);
    }
    
    @Nullable
    private static OCSymbolWithQualifiedName a(@Nullable final OCClassSymbol ocClassSymbol) {
        try {
            if (ocClassSymbol instanceof OCSymbolWithQualifiedName) {
                return (OCSymbolWithQualifiedName)ocClassSymbol;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    private void a(@NotNull final T t, @NotNull final MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap, @NotNull final OCClassSymbol ocClassSymbol, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "handleInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (mostlySingularMultiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "handleInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "handleInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBuilder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "handleInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        this.a(t, (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (mostlySingularMultiMap == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "lambda$handleInstanceVariables$2"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBuilder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "lambda$handleInstanceVariables$2"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (ocSymbol instanceof OCInstanceVariableSymbol) {
                    mostlySingularMultiMap.add((Object)ocSymbol.getName(), (Object)ocSymbol);
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            Label_0147: {
                try {
                    if (ocSymbol.getKind().isType()) {
                        break Label_0147;
                    }
                    final OCMemberSymbol ocMemberSymbol = (OCMemberSymbol)ocSymbol;
                    final OCSymbolKind ocSymbolKind = ocMemberSymbol.getKind();
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM_CONST;
                    if (ocSymbolKind == ocSymbolKind2) {
                        break Label_0147;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCMemberSymbol ocMemberSymbol = (OCMemberSymbol)ocSymbol;
                    final OCSymbolKind ocSymbolKind = ocMemberSymbol.getKind();
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM_CONST;
                    if (ocSymbolKind == ocSymbolKind2) {
                        processor.process((Object)ocSymbol);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            return true;
        }), ocClassSymbol);
    }
    
    @Nullable
    public OCMethodSymbol.SelectorPartSymbol processSelectorPart(@NotNull final T t, @NotNull final StringBuilder sb, @Nullable final TextRange textRange, @NotNull final Ref<Long> ref, @Nullable final OCClassSymbol ocClassSymbol, final boolean b) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "selectorNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSelectorPart"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSelectorPart"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "startOffset", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processSelectorPart"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        String s = null;
        String nodeText = null;
        OCType ocType = OCIdType.pointerToID(this.project);
        boolean passByReference = false;
        long n = this.getComplexOffsetFromNode(t);
        List<String> list = null;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCTokenTypes.IDENTIFIER) {
                n = OCSymbolOffsetUtil.getComplexOffset((com.intellij.lang.NodeStructure<Object>)this.nodeStructure, next);
                if (s == null) {
                    ref.set((Object)n);
                    s = this.nodeText(next);
                    sb.append(s);
                }
                else {
                    nodeText = this.nodeText(next);
                }
            }
            else {
                Label_0322: {
                    Label_0312: {
                        Label_0308: {
                            try {
                                if (type != OCTokenTypes.COLON) {
                                    break Label_0322;
                                }
                                if (s == null) {
                                    break Label_0308;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw b(ex4);
                            }
                            s += ":";
                            break Label_0312;
                        }
                        s = ":";
                    }
                    sb.append(':');
                    continue;
                }
                if (type == OCElementTypes.TYPE_ELEMENT) {
                    final DeclarationContext declarationContext = new DeclarationContext<T>(null, null, a(ocClassSymbol), null, null, b);
                    final OCTypeBuilder a = this.a(declarationContext);
                    ocType = this.processTypeElement(next, a, this.builder, declarationContext);
                    passByReference = a.isPassByReference();
                    final List<String> attributes = declarationContext.getAttributes();
                    Label_0405: {
                        try {
                            if (attributes == null) {
                                continue;
                            }
                            final List<String> list2 = list;
                            if (list2 != null) {
                                break Label_0405;
                            }
                            break Label_0405;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                        try {
                            final List<String> list2 = list;
                            if (list2 != null) {
                                list.addAll(attributes);
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw b(ex6);
                        }
                    }
                    list = attributes;
                }
                else {
                    Label_0449: {
                        try {
                            if (type != OCElementTypes.ATTRIBUTES) {
                                continue;
                            }
                            final List<String> list3 = list;
                            if (list3 != null) {
                                break Label_0449;
                            }
                            break Label_0449;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw b(ex7);
                        }
                        try {
                            final List<String> list3 = list;
                            if (list3 != null) {
                                list.addAll(this.processAttributeList(next));
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw b(ex8);
                        }
                    }
                    list = this.processAttributeList(next);
                }
            }
        }
        if (s != null) {
            OCDeclaratorSymbol ocDeclaratorSymbol = null;
            Label_0511: {
                try {
                    if (nodeText == null) {
                        return new OCMethodSymbolImpl.SelectorPartSymbolImpl(ocDeclaratorSymbol, s);
                    }
                    if (list != null) {
                        break Label_0511;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
                list = Collections.emptyList();
            }
            final OCQualifiedName interned = OCQualifiedName.interned(nodeText);
            int mask = 0;
            Label_0537: {
                try {
                    if (passByReference) {
                        mask = OCDeclaratorSymbol.Property.IS_PASS_BY_REF.getMask();
                        break Label_0537;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
                mask = 0;
            }
            ocDeclaratorSymbol = new OCDeclaratorSymbol(this.project, this.myVirtualFile, n, a(ocClassSymbol), interned, list, ocType, OCSymbolKind.PARAMETER, ArrayUtil.EMPTY_INT_ARRAY, null, (List<OCTypeParameterSymbol>)Collections.emptyList(), null, mask, 0, textRange, null);
            return new OCMethodSymbolImpl.SelectorPartSymbolImpl(ocDeclaratorSymbol, s);
        }
        return null;
    }
    
    private void a(@NotNull final T t, @NotNull final Processor<OCSymbol> processor, @NotNull final OCClassSymbol ocClassSymbol) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processInstanceVariables"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        OCVisibility ocVisibility = OCVisibility.getDefaultObjCVisibility(OCSymbolKind.INSTANCE_VARIABLE);
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCElementTypes.OBJC_KEYWORD) {
                final Iterator<T> iterator2 = this.getChildren(next).iterator();
                while (iterator2.hasNext()) {
                    final IElementType type2 = this.type(iterator2.next());
                    if (OCTokenTypes.IVAR_VISIBILITY_KEYWORDS.contains(type2)) {
                        ocVisibility = OCVisibility.getVisibilityFromElementType(type2);
                    }
                }
            }
            else {
                try {
                    if (type != OCElementTypes.DECLARATION) {
                        continue;
                    }
                    this.processDeclaration(next, processor, new DeclarationContext<T>(null, ocClassSymbol, null, ocVisibility, null, this.myAssumeNonNullOn));
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
        }
    }
    
    public void processNamespaceAlias(@NotNull final T t, @NotNull final Processor<OCSymbol> processor, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "alias", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processNamespaceAlias"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processNamespaceAlias"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processNamespaceAlias"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        String nodeText = null;
        OCQualifiedName a = null;
        final TextRange h = this.h(declarationContext.getParent());
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCTokenTypes.IDENTIFIER) {
                nodeText = this.nodeText(next);
            }
            else {
                if (type != OCElementTypes.REFERENCE_ELEMENT) {
                    continue;
                }
                a = this.a(next, a, declarationContext);
            }
        }
        if (a != null) {
            final long complexOffsetFromNode = this.getComplexOffsetFromNode(t);
            final OCSymbolReference.GlobalReference globalReference = OCSymbolReference.getGlobalReference(a, declarationContext.getParentSymbol(), OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE);
            declarationContext.addSymbolReference(globalReference);
            processor.process((Object)new OCNamespaceAliasSymbol(this.project, this.myVirtualFile, complexOffsetFromNode, declarationContext.getParentSymbol(), OCQualifiedName.interned(nodeText), globalReference, h));
        }
    }
    
    public void processUsingStatement(@NotNull final T t, @NotNull final Processor<OCSymbol> processor, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statement", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processUsingStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processUsingStatement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processUsingStatement"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        OCSymbolKind ocSymbolKind = OCSymbolKind.SYMBOL_USING_SYMBOL;
        OCQualifiedName ocQualifiedName = null;
        String nodeText = null;
        OCType processTypeElement = null;
        long n = 0L;
        final TextRange h = this.h(declarationContext.getParent());
        Serializable s = null;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            List<OCTypeParameterSymbol> emptyList = null;
            Label_0404: {
                Label_0387: {
                    Label_0360: {
                        Label_0318: {
                            Label_0290: {
                                try {
                                    if (type == OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST) {
                                        declarationContext.getTemplateParameters().add(next);
                                        break Label_0290;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw b(ex4);
                                }
                                try {
                                    if (type == OCTokenTypes.USING_CPP_KEYWORD) {
                                        break Label_0290;
                                    }
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw b(ex5);
                                }
                                if (type == OCTokenTypes.NAMESPACE_CPP_KEYWORD) {
                                    ocSymbolKind = OCSymbolKind.NAMESPACE_USING_SYMBOL;
                                }
                                else if (type == OCTokenTypes.IDENTIFIER) {
                                    nodeText = this.nodeText(next);
                                    n = OCSymbolOffsetUtil.getComplexOffset((com.intellij.lang.NodeStructure<Object>)this.nodeStructure, next);
                                }
                                try {
                                    if (type != OCTokenTypes.COLON2X || ocQualifiedName != null) {
                                        break Label_0318;
                                    }
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw b(ex6);
                                }
                            }
                            ocQualifiedName = OCQualifiedName.GLOBAL;
                            continue;
                            try {
                                if (type != OCElementTypes.REFERENCE_ELEMENT) {
                                    break Label_0360;
                                }
                                if (nodeText != null) {
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException ex7) {
                                throw b(ex7);
                            }
                        }
                        ocQualifiedName = this.a(next, ocQualifiedName, declarationContext);
                        n = this.getComplexOffsetFromNode(next);
                        continue;
                        try {
                            if (type != OCElementTypes.TYPE_ELEMENT) {
                                continue;
                            }
                            final DeclarationContext<T> declarationContext2 = declarationContext;
                            final List<T> list = declarationContext2.getTemplateParameters();
                            final boolean b = list.isEmpty();
                            if (b) {
                                break Label_0387;
                            }
                            break Label_0387;
                        }
                        catch (IllegalArgumentException ex8) {
                            throw b(ex8);
                        }
                    }
                    try {
                        final DeclarationContext<T> declarationContext2 = declarationContext;
                        final List<T> list = declarationContext2.getTemplateParameters();
                        final boolean b = list.isEmpty();
                        if (b) {
                            emptyList = Collections.emptyList();
                            break Label_0404;
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw b(ex9);
                    }
                }
                emptyList = new ArrayList<OCTypeParameterSymbol>();
            }
            final List<OCTypeParameterSymbol> list2 = emptyList;
            Project project = null;
            VirtualFile myVirtualFile = null;
            long n2 = 0L;
            String s2 = null;
            Label_0436: {
                try {
                    project = this.project;
                    myVirtualFile = this.myVirtualFile;
                    n2 = n;
                    if (nodeText != null) {
                        s2 = nodeText;
                        break Label_0436;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
                s2 = "";
            }
            final OCAliasUsingSymbol ocAliasUsingSymbol = (OCAliasUsingSymbol)(s = new OCAliasUsingSymbol(project, myVirtualFile, n2, s2, declarationContext.getParentSymbol(), (List<OCTypeParameterSymbol>)list2, h));
            final Iterator<T> iterator2 = declarationContext.getTemplateParameters().iterator();
            while (iterator2.hasNext()) {
                this.b(iterator2.next(), (List<OCTypeParameterSymbol>)list2, this.createDeclarationContext(ocAliasUsingSymbol));
                ((ArrayList<OCTypeParameterSymbol>)list2).trimToSize();
            }
            final DeclarationContext<T> declarationContext3 = this.createDeclarationContext((OCSymbolWithQualifiedName)s);
            processTypeElement = this.processTypeElement(next, this.a(declarationContext3), processor, declarationContext3);
            ((OCAliasUsingSymbol)s).setType(processTypeElement);
        }
        Label_0689: {
            Label_0672: {
                try {
                    if (ocQualifiedName == null || ocQualifiedName.getName() == null) {
                        break Label_0672;
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw b(ex11);
                }
                OCSymbolReference.SymbolFilter symbolFilter = OCSymbolReference.SymbolFilter.NONE;
                if (ocSymbolKind == OCSymbolKind.NAMESPACE_USING_SYMBOL) {
                    symbolFilter = OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE;
                }
                OCSymbolReference ocSymbolReference;
                if (declarationContext.getLocalContext() != null) {
                    ocSymbolReference = OCSymbolReference.getLocalReference(ocQualifiedName, declarationContext.getLocalContext(), symbolFilter);
                }
                else {
                    ocSymbolReference = OCSymbolReference.getGlobalReference(ocQualifiedName, declarationContext.getParentSymbol(), symbolFilter);
                }
                declarationContext.addSymbolReference(ocSymbolReference);
                s = new OCUsingSymbol(this.project, this.myVirtualFile, n, declarationContext.getParentSymbol(), ocSymbolReference, ocSymbolKind, declarationContext.getVisibility(), Collections.emptyList(), h);
                break Label_0689;
                try {
                    if (nodeText != null && processTypeElement != null) {}
                }
                catch (IllegalArgumentException ex12) {
                    throw b(ex12);
                }
            }
            try {
                if (s != null) {
                    processor.process((Object)s);
                }
            }
            catch (IllegalArgumentException ex13) {
                throw b(ex13);
            }
        }
    }
    
    public void processNamespace(@NotNull final T p0, @NotNull final Processor<OCSymbol> p1, @NotNull final DeclarationContext<T> p2, @Nullable final String p3, @Nullable final Processor<OCSymbol> p4, @Nullable final OCSymbolWithQualifiedName p5) {
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
        //    18: ldc             "namespace"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processNamespace"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "builder"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processNamespace"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processNamespace"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: iconst_0       
        //   133: istore          7
        //   135: aload           6
        //   137: ifnonnull       148
        //   140: iconst_1       
        //   141: goto            149
        //   144: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_0       
        //   149: istore          8
        //   151: aload_0        
        //   152: aload_1        
        //   153: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   156: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   161: astore          9
        //   163: aload           9
        //   165: invokeinterface java/util/Iterator.hasNext:()Z
        //   170: ifeq            678
        //   173: aload           9
        //   175: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   180: astore          10
        //   182: aload_0        
        //   183: aload           10
        //   185: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   188: astore          11
        //   190: aload           11
        //   192: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.NAMESPACE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   195: if_acmpeq       669
        //   198: aload           11
        //   200: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   203: if_acmpne       220
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: goto            669
        //   216: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload           11
        //   222: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INLINE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   225: if_acmpne       234
        //   228: iconst_1       
        //   229: istore          7
        //   231: goto            669
        //   234: aload           11
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   239: if_acmpne       253
        //   242: aload_0        
        //   243: aload           10
        //   245: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   248: astore          4
        //   250: goto            669
        //   253: aload           11
        //   255: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   258: if_acmpeq       366
        //   261: aload           11
        //   263: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   266: if_acmpeq       366
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: aload           11
        //   278: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   281: if_acmpeq       366
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload           11
        //   293: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   296: if_acmpeq       366
        //   299: goto            306
        //   302: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: aload           11
        //   308: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   311: if_acmpeq       366
        //   314: goto            321
        //   317: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   320: athrow         
        //   321: aload           11
        //   323: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_USING_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   326: if_acmpeq       366
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   335: athrow         
        //   336: aload           11
        //   338: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   341: if_acmpeq       366
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   350: athrow         
        //   351: aload           11
        //   353: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_EXTERN_BLOCK:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   356: if_acmpne       669
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload           5
        //   368: ifnonnull       455
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload           4
        //   380: ifnull          446
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: aload_0        
        //   391: aload_1        
        //   392: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   395: lstore          12
        //   397: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   400: dup            
        //   401: aload_0        
        //   402: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   405: aload_0        
        //   406: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   409: lload           12
        //   411: aload_3        
        //   412: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   415: aload           4
        //   417: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.interned:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   420: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   423: aconst_null    
        //   424: aconst_null    
        //   425: aconst_null    
        //   426: iload           7
        //   428: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Ljava/util/List;Lcom/intellij/util/containers/MostlySingularMultiMap;Ljava/util/List;Z)V
        //   431: astore          6
        //   433: aload           6
        //   435: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   438: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.getBuilder:()Lcom/intellij/util/Processor;
        //   441: astore          5
        //   443: goto            455
        //   446: aload_3        
        //   447: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   450: astore          6
        //   452: aload_2        
        //   453: astore          5
        //   455: aload           11
        //   457: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   460: if_acmpeq       493
        //   463: aload           11
        //   465: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   468: if_acmpeq       493
        //   471: goto            478
        //   474: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: aload           11
        //   480: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   483: if_acmpne       550
        //   486: goto            493
        //   489: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   492: athrow         
        //   493: aload           11
        //   495: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   498: if_acmpne       518
        //   501: goto            508
        //   504: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   507: athrow         
        //   508: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   511: goto            519
        //   514: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   517: athrow         
        //   518: aconst_null    
        //   519: astore          12
        //   521: aload_0        
        //   522: aload           10
        //   524: aload           5
        //   526: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   529: dup            
        //   530: aload           12
        //   532: aconst_null    
        //   533: aload           6
        //   535: aconst_null    
        //   536: aconst_null    
        //   537: aload_0        
        //   538: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   541: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //   544: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclaration:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   547: goto            669
        //   550: aload           11
        //   552: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   555: if_acmpne       582
        //   558: aload_0        
        //   559: aload           10
        //   561: aload           5
        //   563: aload_0        
        //   564: aload           6
        //   566: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.createDeclarationContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   569: aconst_null    
        //   570: aconst_null    
        //   571: aconst_null    
        //   572: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processNamespace:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/lang/String;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //   575: goto            669
        //   578: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: aload           11
        //   584: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   587: if_acmpne       611
        //   590: aload_0        
        //   591: aload           10
        //   593: aload           5
        //   595: aload_0        
        //   596: aload           6
        //   598: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.createDeclarationContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   601: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processNamespaceAlias:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   604: goto            669
        //   607: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   610: athrow         
        //   611: aload           11
        //   613: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_USING_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   616: if_acmpne       640
        //   619: aload_0        
        //   620: aload           10
        //   622: aload           5
        //   624: aload_0        
        //   625: aload           6
        //   627: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.createDeclarationContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   630: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processUsingStatement:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   633: goto            669
        //   636: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   639: athrow         
        //   640: aload           11
        //   642: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_EXTERN_BLOCK:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   645: if_acmpne       669
        //   648: aload_0        
        //   649: aload           10
        //   651: aload_2        
        //   652: aload_3        
        //   653: aload           4
        //   655: aload           5
        //   657: aload           6
        //   659: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processNamespace:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/lang/String;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //   662: goto            669
        //   665: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: aload_0        
        //   670: aload           10
        //   672: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/Object;)V
        //   675: goto            163
        //   678: iload           8
        //   680: ifeq            786
        //   683: aload           4
        //   685: ifnull          786
        //   688: goto            695
        //   691: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   694: athrow         
        //   695: aload           6
        //   697: ifnull          786
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: aload_2        
        //   708: aload           6
        //   710: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   715: pop            
        //   716: iload           7
        //   718: ifeq            786
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: aload           4
        //   730: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   733: aload_3        
        //   734: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   737: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getGlobalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //   740: astore          9
        //   742: aload_3        
        //   743: aload           9
        //   745: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addSymbolReference:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)V
        //   748: aload_2        
        //   749: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   752: dup            
        //   753: aload_0        
        //   754: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   757: aconst_null    
        //   758: aload           6
        //   760: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getComplexOffset:()J
        //   763: aload_3        
        //   764: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   767: aload           9
        //   769: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   772: aconst_null    
        //   773: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   776: aconst_null    
        //   777: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Ljava/util/List;Lcom/intellij/openapi/util/TextRange;)V
        //   780: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   785: pop            
        //   786: return         
        //    Signature:
        //  (TT;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext<TT;>;Ljava/lang/String;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  135    144    144    148    Ljava/lang/IllegalArgumentException;
        //  190    206    209    213    Ljava/lang/IllegalArgumentException;
        //  198    216    216    220    Ljava/lang/IllegalArgumentException;
        //  253    269    272    276    Ljava/lang/IllegalArgumentException;
        //  261    284    287    291    Ljava/lang/IllegalArgumentException;
        //  276    299    302    306    Ljava/lang/IllegalArgumentException;
        //  291    314    317    321    Ljava/lang/IllegalArgumentException;
        //  306    329    332    336    Ljava/lang/IllegalArgumentException;
        //  321    344    347    351    Ljava/lang/IllegalArgumentException;
        //  336    359    362    366    Ljava/lang/IllegalArgumentException;
        //  351    371    374    378    Ljava/lang/IllegalArgumentException;
        //  366    383    386    390    Ljava/lang/IllegalArgumentException;
        //  455    471    474    478    Ljava/lang/IllegalArgumentException;
        //  463    486    489    493    Ljava/lang/IllegalArgumentException;
        //  478    501    504    508    Ljava/lang/IllegalArgumentException;
        //  493    514    514    518    Ljava/lang/IllegalArgumentException;
        //  550    578    578    582    Ljava/lang/IllegalArgumentException;
        //  582    607    607    611    Ljava/lang/IllegalArgumentException;
        //  611    636    636    640    Ljava/lang/IllegalArgumentException;
        //  640    662    665    669    Ljava/lang/IllegalArgumentException;
        //  678    688    691    695    Ljava/lang/IllegalArgumentException;
        //  683    700    703    707    Ljava/lang/IllegalArgumentException;
        //  695    721    724    728    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0276:
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
    protected DeclarationContext<T> createDeclarationContext(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        DeclarationContext declarationContext;
        try {
            declarationContext = new DeclarationContext<T>(null, null, ocSymbolWithQualifiedName, null, null, this.myAssumeNonNullOn);
            if (declarationContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createDeclarationContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (DeclarationContext<T>)declarationContext;
    }
    
    public void processDeclaration(@NotNull final T t, @NotNull final Processor<OCSymbol> processor, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaration", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processDeclaration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processDeclaration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final List collect = ContainerUtil.collect((Iterator)this.getChildren(t).iterator());
        int n = 0;
        final OCTypeBuilder a = this.a(declarationContext);
        declarationContext.pushDeclaration();
        OCSymbol a2 = null;
        if (this.type(t) == OCElementTypes.FUNCTION_KR_DEFINITION) {
            for (int i = collect.size() - 1; i >= 0; --i) {
                final T value = collect.get(i);
                try {
                    if (this.type(value) == OCElementTypes.PARAMETER_LIST) {
                        declarationContext.myKRParamterList = value;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
        }
        for (int j = 0; j < collect.size(); ++j) {
            final T value2 = collect.get(j);
            final IElementType type = this.type(value2);
            try {
                if (type == OCElementTypes.ATTRIBUTES) {
                    declarationContext.addAttributes(this.processAttributeList(value2));
                    continue;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            try {
                if (type == OCTokenTypes.TEMPLATE_CPP_KEYWORD) {
                    declarationContext.setTemplateSymbol(true);
                    continue;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            try {
                if (type == OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST) {
                    declarationContext.getTemplateParameters().add(value2);
                    continue;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            try {
                if (type == OCTokenTypes.EXTERN_KEYWORD) {
                    declarationContext.setDeclaratorType(OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION);
                    declarationContext.addModifier(OCSymbolAttribute.EXTERN);
                    continue;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            try {
                if (type == OCTokenTypes.INLINE_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.INLINE);
                    continue;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
            try {
                if (type == OCTokenTypes.EXPLICIT_CPP_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.EXPLICIT);
                    continue;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw b(ex10);
            }
            try {
                if (type == OCTokenTypes.STATIC_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.STATIC);
                    continue;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw b(ex11);
            }
            try {
                if (type == OCTokenTypes.REGISTER_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.REGISTER);
                    continue;
                }
            }
            catch (IllegalArgumentException ex12) {
                throw b(ex12);
            }
            Label_0493: {
                Label_0479: {
                    try {
                        if (type == OCTokenTypes.THREAD_LOCAL_KEYWORD) {
                            break Label_0479;
                        }
                        final OCKeywordElementType ocKeywordElementType = (OCKeywordElementType)type;
                        final OCElementType ocElementType = OCTokenTypes.THREAD_LOCAL_C_KEYWORD;
                        if (ocKeywordElementType == ocElementType) {
                            break Label_0479;
                        }
                        break Label_0493;
                    }
                    catch (IllegalArgumentException ex13) {
                        throw b(ex13);
                    }
                    try {
                        final OCKeywordElementType ocKeywordElementType = (OCKeywordElementType)type;
                        final OCElementType ocElementType = OCTokenTypes.THREAD_LOCAL_C_KEYWORD;
                        if (ocKeywordElementType == ocElementType) {
                            declarationContext.addModifier(OCSymbolAttribute.THREAD_LOCAL);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex14) {
                        throw b(ex14);
                    }
                }
                try {
                    if (type == OCTokenTypes.MUTABLE_CPP_KEYWORD) {
                        declarationContext.addModifier(OCSymbolAttribute.MUTABLE);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex15) {
                    throw b(ex15);
                }
            }
            try {
                if (type == OCTokenTypes.VIRTUAL_CPP_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.VIRTUAL);
                    continue;
                }
            }
            catch (IllegalArgumentException ex16) {
                throw b(ex16);
            }
            try {
                if (type == OCTokenTypes.CONSTEXPR_CPP_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.CONSTEXPR);
                    continue;
                }
            }
            catch (IllegalArgumentException ex17) {
                throw b(ex17);
            }
            try {
                if (type == OCTokenTypes.FRIEND_CPP_KEYWORD) {
                    declarationContext.addModifier(OCSymbolAttribute.FRIEND);
                    continue;
                }
            }
            catch (IllegalArgumentException ex18) {
                throw b(ex18);
            }
            Label_0628: {
                Label_0601: {
                    try {
                        if (type != OCElementTypes.DECLARATOR) {
                            break Label_0628;
                        }
                        final int n2 = n;
                        if (n2 == 0) {
                            break Label_0601;
                        }
                        break Label_0601;
                    }
                    catch (IllegalArgumentException ex19) {
                        throw b(ex19);
                    }
                    try {
                        final int n2 = n;
                        if (n2 == 0) {
                            declarationContext.setDeclarationWithoutType(true);
                        }
                    }
                    catch (IllegalArgumentException ex20) {
                        throw b(ex20);
                    }
                }
                a2 = this.a(value2, a, processor, declarationContext);
                continue;
            }
            if (type == OCElementTypes.TYPE_ELEMENT) {
                n = 1;
                boolean b = false;
                int n3 = j + 1;
                while (true) {
                    Label_0686: {
                        Label_0692: {
                            try {
                                if (n3 >= collect.size()) {
                                    break;
                                }
                                if (this.type(collect.get(n3)) != OCElementTypes.DECLARATOR) {
                                    break Label_0692;
                                }
                            }
                            catch (IllegalArgumentException ex21) {
                                throw b(ex21);
                            }
                            break Label_0686;
                        }
                        ++n3;
                        continue;
                    }
                    b = true;
                    break;
                }
                boolean declarationWithoutDeclarators = false;
                Label_0713: {
                    try {
                        if (!b) {
                            declarationWithoutDeclarators = true;
                            break Label_0713;
                        }
                    }
                    catch (IllegalArgumentException ex22) {
                        throw b(ex22);
                    }
                    declarationWithoutDeclarators = false;
                }
                declarationContext.setDeclarationWithoutDeclarators(declarationWithoutDeclarators);
                this.processTypeElement(value2, a, processor, declarationContext);
            }
            else {
                try {
                    if (OCTokenTypes.AUTO_KEYWORDS.contains(type)) {
                        a.learn(type);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex23) {
                    throw b(ex23);
                }
                Label_0781: {
                    try {
                        if (!OCElementTypes.STATEMENTS.contains(type)) {
                            continue;
                        }
                        final OCSymbol ocSymbol = a2;
                        final boolean b2 = ocSymbol instanceof OCFunctionSymbol;
                        if (b2) {
                            break Label_0781;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex24) {
                        throw b(ex24);
                    }
                    try {
                        final OCSymbol ocSymbol = a2;
                        final boolean b2 = ocSymbol instanceof OCFunctionSymbol;
                        if (!b2) {
                            continue;
                        }
                        if (!a(((OCFunctionType)a2.getType()).getReturnType().getTerminalType())) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex25) {
                        throw b(ex25);
                    }
                }
                final ArrayList<OCExpressionSymbol> list = new ArrayList<OCExpressionSymbol>();
                final ArrayList<Object> list2 = new ArrayList<Object>();
                final List<OCDeclaratorSymbol> parameterSymbols = ((OCFunctionSymbol)a2).getParameterSymbols();
                list2.addAll(parameterSymbols);
                final OCLambdaExpressionSymbol ocLambdaExpressionSymbol = new OCLambdaExpressionSymbol(this.project, this.myVirtualFile, 0L, null, list, parameterSymbols, (List<OCDeclaratorSymbol>)list2, null, null);
                ocLambdaExpressionSymbol.setFunctionSymbol((OCFunctionSymbol)a2);
                final DeclarationContext declarationContext2 = new DeclarationContext<T>(null, value2, (OCSymbolWithQualifiedName)a2, null, null, false);
                declarationContext2.setLambda(ocLambdaExpressionSymbol);
                this.a(value2, (DeclarationContext<T>)declarationContext2, list, (List<OCDeclaratorSymbol>)list2);
                ContainerUtil.sort((List)list2, (Comparator)OCLambdaExpressionSymbol.variablesComparator);
                list.trimToSize();
                list2.trimToSize();
                ((OCAutoType)((OCFunctionSymbol)a2).getType().getReturnType().getTerminalType()).setExpressionSymbol(ocLambdaExpressionSymbol);
            }
        }
        final OCType result = a.getResult();
        Label_1075: {
            try {
                if (!(result instanceof OCStructType) || ((OCStructType)result).getKind() != OCSymbolKind.ENUM) {
                    break Label_1075;
                }
            }
            catch (IllegalArgumentException ex26) {
                throw b(ex26);
            }
            for (final OCDeclaratorSymbol ocDeclaratorSymbol : ((OCStructType)result).getFields()) {
                ocDeclaratorSymbol.setType(result);
                ocDeclaratorSymbol.addAttributes(((OCStructType)result).getSymbol().getAttributes());
            }
        }
        declarationContext.popDeclaration();
    }
    
    private static boolean a(final OCType ocType) {
        Label_0024: {
            try {
                if (!(ocType instanceof OCAutoType)) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final OCAutoType ocAutoType = (OCAutoType)ocType2;
                final OCExpressionSymbol ocExpressionSymbol = ocAutoType.getExpressionSymbol();
                if (ocExpressionSymbol == null) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType2 = ocType;
                final OCAutoType ocAutoType = (OCAutoType)ocType2;
                final OCExpressionSymbol ocExpressionSymbol = ocAutoType.getExpressionSymbol();
                if (ocExpressionSymbol == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public long getComplexOffsetFromNode(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "getComplexOffsetFromNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final List collect = ContainerUtil.collect((Iterator)this.getChildren(t).iterator());
        final long complexOffset = OCSymbolOffsetUtil.getComplexOffset((com.intellij.lang.NodeStructure<Object>)this.nodeStructure, t);
        try {
            if (collect.isEmpty()) {
                return complexOffset;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return OCSymbolOffsetUtil.adjust(complexOffset, this.getComplexOffsetFromNode(collect.get(0)));
    }
    
    public OCType processTypeElement(@NotNull final T p0, @NotNull final OCTypeBuilder p1, @Nullable final Processor<OCSymbol> p2, @NotNull final DeclarationContext<T> p3) {
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
        //    18: ldc             "typeElement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processTypeElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "typeBuilder"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processTypeElement"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "context"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "processTypeElement"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aconst_null    
        //   134: astore          5
        //   136: aconst_null    
        //   137: astore          6
        //   139: iconst_0       
        //   140: istore          7
        //   142: iconst_1       
        //   143: istore          8
        //   145: iconst_0       
        //   146: istore          9
        //   148: iconst_0       
        //   149: istore          10
        //   151: iconst_0       
        //   152: istore          11
        //   154: iconst_0       
        //   155: istore          12
        //   157: iconst_0       
        //   158: istore          13
        //   160: aload_0        
        //   161: aload_1        
        //   162: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   165: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   170: astore          14
        //   172: aload           14
        //   174: invokeinterface java/util/Iterator.hasNext:()Z
        //   179: ifeq            896
        //   182: aload           14
        //   184: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   189: astore          15
        //   191: aload_0        
        //   192: aload           15
        //   194: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   197: astore          16
        //   199: iload           8
        //   201: ifeq            225
        //   204: aload           16
        //   206: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   209: if_acmpne       225
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: iconst_0       
        //   220: istore          8
        //   222: goto            172
        //   225: aload           16
        //   227: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STRUCT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   230: if_acmpeq       263
        //   233: aload           16
        //   235: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ENUM:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   238: if_acmpeq       263
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload           16
        //   250: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.UNION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   253: if_acmpne       280
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload_0        
        //   264: aload           15
        //   266: aload_2        
        //   267: aload_3        
        //   268: aload           4
        //   270: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processStruct:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   273: goto            890
        //   276: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload           16
        //   282: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.REFERENCE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   285: if_acmpne       304
        //   288: aload_0        
        //   289: aload           15
        //   291: aload_2        
        //   292: aload           4
        //   294: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   297: goto            890
        //   300: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: aload           16
        //   306: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   309: if_acmpne       349
        //   312: iconst_1       
        //   313: istore          9
        //   315: new             Ljava/util/ArrayList;
        //   318: dup            
        //   319: invokespecial   java/util/ArrayList.<init>:()V
        //   322: astore          5
        //   324: new             Ljava/util/ArrayList;
        //   327: dup            
        //   328: invokespecial   java/util/ArrayList.<init>:()V
        //   331: astore          6
        //   333: aload_0        
        //   334: aload           15
        //   336: aconst_null    
        //   337: aload           5
        //   339: aload           4
        //   341: aload           6
        //   343: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/util/List;)V
        //   346: goto            890
        //   349: aload           16
        //   351: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   354: if_acmpne       372
        //   357: aload           4
        //   359: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.BLOCK_MODIFIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   362: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //   365: goto            890
        //   368: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload           16
        //   374: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ATTRIBUTES:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   377: if_acmpne       398
        //   380: aload           4
        //   382: aload_0        
        //   383: aload           15
        //   385: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //   388: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addAttributes:(Ljava/util/List;)V
        //   391: goto            890
        //   394: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: aload           16
        //   400: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STATIC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   403: if_acmpne       421
        //   406: aload           4
        //   408: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.STATIC:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   411: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //   414: goto            890
        //   417: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   420: athrow         
        //   421: aload           16
        //   423: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUTABLE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   426: if_acmpne       444
        //   429: aload           4
        //   431: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.MUTABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   434: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //   437: goto            890
        //   440: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: aload           16
        //   446: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONSTEXPR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   449: if_acmpne       467
        //   452: aload           4
        //   454: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.CONSTEXPR:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   457: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //   460: goto            890
        //   463: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   466: athrow         
        //   467: aload           16
        //   469: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   472: if_acmpne       502
        //   475: iload           9
        //   477: ifeq            493
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: iconst_1       
        //   488: istore          10
        //   490: goto            890
        //   493: aload_2        
        //   494: aload           16
        //   496: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //   499: goto            890
        //   502: aload           16
        //   504: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   507: if_acmpne       537
        //   510: iload           9
        //   512: ifeq            528
        //   515: goto            522
        //   518: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   521: athrow         
        //   522: iconst_1       
        //   523: istore          11
        //   525: goto            890
        //   528: aload_2        
        //   529: aload           16
        //   531: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //   534: goto            890
        //   537: aload           16
        //   539: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   542: if_acmpne       572
        //   545: iload           9
        //   547: ifeq            563
        //   550: goto            557
        //   553: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   556: athrow         
        //   557: iconst_1       
        //   558: istore          12
        //   560: goto            890
        //   563: aload_2        
        //   564: aload           16
        //   566: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //   569: goto            890
        //   572: aload           16
        //   574: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   577: if_acmpne       607
        //   580: iload           9
        //   582: ifeq            598
        //   585: goto            592
        //   588: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   591: athrow         
        //   592: iconst_1       
        //   593: istore          13
        //   595: goto            890
        //   598: aload_2        
        //   599: aload           16
        //   601: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //   604: goto            890
        //   607: aload           16
        //   609: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEOF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   612: if_acmpeq       630
        //   615: aload           16
        //   617: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DECLTYPE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   620: if_acmpne       636
        //   623: goto            630
        //   626: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: iconst_1       
        //   631: istore          7
        //   633: goto            890
        //   636: aload           16
        //   638: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   641: if_acmpne       663
        //   644: aload_2        
        //   645: aload           16
        //   647: aload_0        
        //   648: aload           15
        //   650: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   653: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //   656: goto            890
        //   659: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   662: athrow         
        //   663: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //   666: aload           16
        //   668: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   671: ifeq            706
        //   674: iload           7
        //   676: ifeq            706
        //   679: goto            686
        //   682: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   685: athrow         
        //   686: aload_2        
        //   687: aload_0        
        //   688: aload           15
        //   690: aconst_null    
        //   691: aload           4
        //   693: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   696: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learnBaseType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   699: goto            890
        //   702: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: aload           16
        //   708: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LITERAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   711: if_acmpne       757
        //   714: aload_0        
        //   715: aload           15
        //   717: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.e:(Ljava/lang/Object;)Ljava/lang/Object;
        //   720: astore          17
        //   722: aload           17
        //   724: instanceof      Ljava/lang/Number;
        //   727: ifeq            749
        //   730: aload_2        
        //   731: aload           17
        //   733: checkcast       Ljava/lang/Number;
        //   736: invokevirtual   java/lang/Number.intValue:()I
        //   739: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(I)V
        //   742: goto            754
        //   745: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   748: athrow         
        //   749: aload_2        
        //   750: iconst_m1      
        //   751: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(I)V
        //   754: goto            890
        //   757: aload           16
        //   759: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_QUALIFIED_POINTER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   762: if_acmpne       842
        //   765: aload_0        
        //   766: aload           15
        //   768: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   771: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   776: astore          17
        //   778: aload           17
        //   780: invokeinterface java/util/Iterator.hasNext:()Z
        //   785: ifeq            832
        //   788: aload           17
        //   790: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   795: astore          18
        //   797: aload_0        
        //   798: aload           18
        //   800: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   803: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_QUALIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   806: if_acmpne       829
        //   809: aload_2        
        //   810: aload_0        
        //   811: aload           18
        //   813: aconst_null    
        //   814: aload           4
        //   816: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   819: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.setPointerQualifier:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)V
        //   822: goto            829
        //   825: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   828: athrow         
        //   829: goto            778
        //   832: aload_2        
        //   833: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   836: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //   839: goto            890
        //   842: iload           7
        //   844: ifeq            877
        //   847: aload           16
        //   849: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   852: if_acmpeq       890
        //   855: goto            862
        //   858: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   861: athrow         
        //   862: aload           16
        //   864: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   867: if_acmpeq       890
        //   870: goto            877
        //   873: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   876: athrow         
        //   877: aload_2        
        //   878: aload           16
        //   880: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //   883: goto            890
        //   886: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   889: athrow         
        //   890: iconst_0       
        //   891: istore          8
        //   893: goto            172
        //   896: aload_2        
        //   897: aload           5
        //   899: ifnull          910
        //   902: iconst_1       
        //   903: goto            911
        //   906: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   909: athrow         
        //   910: iconst_0       
        //   911: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getResult:(Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   914: astore          14
        //   916: aload_2        
        //   917: aload           14
        //   919: aload           5
        //   921: aload           6
        //   923: iconst_1       
        //   924: iload           10
        //   926: iload           11
        //   928: iload           12
        //   930: iload           13
        //   932: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.createFunction:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;ZZZZZ)Lcom/jetbrains/cidr/lang/types/OCType;
        //   935: areturn        
        //    Signature:
        //  (TT;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext<TT;>;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  199    212    215    219    Ljava/lang/IllegalArgumentException;
        //  225    241    244    248    Ljava/lang/IllegalArgumentException;
        //  233    256    259    263    Ljava/lang/IllegalArgumentException;
        //  248    276    276    280    Ljava/lang/IllegalArgumentException;
        //  280    300    300    304    Ljava/lang/IllegalArgumentException;
        //  349    368    368    372    Ljava/lang/IllegalArgumentException;
        //  372    394    394    398    Ljava/lang/IllegalArgumentException;
        //  398    417    417    421    Ljava/lang/IllegalArgumentException;
        //  421    440    440    444    Ljava/lang/IllegalArgumentException;
        //  444    463    463    467    Ljava/lang/IllegalArgumentException;
        //  467    480    483    487    Ljava/lang/IllegalArgumentException;
        //  502    515    518    522    Ljava/lang/IllegalArgumentException;
        //  537    550    553    557    Ljava/lang/IllegalArgumentException;
        //  572    585    588    592    Ljava/lang/IllegalArgumentException;
        //  607    623    626    630    Ljava/lang/IllegalArgumentException;
        //  636    659    659    663    Ljava/lang/IllegalArgumentException;
        //  663    679    682    686    Ljava/lang/IllegalArgumentException;
        //  674    702    702    706    Ljava/lang/IllegalArgumentException;
        //  722    745    745    749    Ljava/lang/IllegalArgumentException;
        //  797    822    825    829    Ljava/lang/IllegalArgumentException;
        //  842    855    858    862    Ljava/lang/IllegalArgumentException;
        //  847    870    873    877    Ljava/lang/IllegalArgumentException;
        //  862    883    886    890    Ljava/lang/IllegalArgumentException;
        //  896    906    906    910    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0248:
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
    
    public void processStruct(@NotNull final T p0, @NotNull final OCTypeBuilder p1, @Nullable final Processor<OCSymbol> p2, @NotNull final DeclarationContext<T> p3) {
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
        //    18: ldc             "structNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processStruct"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "typeBuilder"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processStruct"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "context"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "processStruct"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aconst_null    
        //   134: astore          5
        //   136: aconst_null    
        //   137: astore          6
        //   139: aconst_null    
        //   140: astore          7
        //   142: aconst_null    
        //   143: astore          8
        //   145: aconst_null    
        //   146: astore          9
        //   148: aconst_null    
        //   149: astore          10
        //   151: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   154: astore          11
        //   156: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   159: astore          12
        //   161: aload           4
        //   163: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getAttributes:()Ljava/util/List;
        //   166: astore          13
        //   168: iconst_0       
        //   169: istore          14
        //   171: iconst_0       
        //   172: istore          15
        //   174: aload_0        
        //   175: aload_1        
        //   176: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   179: lstore          16
        //   181: aload           4
        //   183: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParent:()Ljava/lang/Object;
        //   186: astore          18
        //   188: aload_0        
        //   189: aload           18
        //   191: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.h:(Ljava/lang/Object;)Lcom/intellij/openapi/util/TextRange;
        //   194: astore          19
        //   196: aconst_null    
        //   197: astore          20
        //   199: aconst_null    
        //   200: astore          21
        //   202: aload_0        
        //   203: aload_1        
        //   204: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   207: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   212: astore          22
        //   214: aload           22
        //   216: invokeinterface java/util/Iterator.hasNext:()Z
        //   221: ifeq            1249
        //   224: aload           22
        //   226: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   231: astore          23
        //   233: aload_0        
        //   234: aload           23
        //   236: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   239: astore          24
        //   241: aload           24
        //   243: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   246: if_acmpne       269
        //   249: aload           9
        //   251: ifnonnull       269
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   264: astore          9
        //   266: goto            1246
        //   269: aload           24
        //   271: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_QUALIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   274: if_acmpne       292
        //   277: aload_0        
        //   278: aload           23
        //   280: aload           9
        //   282: aload           4
        //   284: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   287: astore          9
        //   289: goto            1246
        //   292: aload           24
        //   294: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FINAL_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   297: if_acmpne       306
        //   300: iconst_1       
        //   301: istore          15
        //   303: goto            1246
        //   306: aload           24
        //   308: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   311: if_acmpeq       374
        //   314: aload           24
        //   316: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_USING_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   319: if_acmpeq       374
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: aload           24
        //   331: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   334: if_acmpeq       374
        //   337: goto            344
        //   340: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: aload           24
        //   346: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   349: if_acmpeq       374
        //   352: goto            359
        //   355: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   358: athrow         
        //   359: aload           24
        //   361: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   364: if_acmpne       930
        //   367: goto            374
        //   370: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: aload           4
        //   376: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isDeclarationWithoutDeclarators:Z
        //   379: ifeq            424
        //   382: goto            389
        //   385: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: aload           8
        //   391: ifnonnull       424
        //   394: goto            401
        //   397: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   400: athrow         
        //   401: aload           11
        //   403: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   406: ifeq            424
        //   409: goto            416
        //   412: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: iconst_1       
        //   417: goto            425
        //   420: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: iconst_0       
        //   425: istore          25
        //   427: aload           7
        //   429: ifnonnull       746
        //   432: aload           5
        //   434: ifnull          458
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: new             Ljava/util/ArrayList;
        //   447: dup            
        //   448: invokespecial   java/util/ArrayList.<init>:()V
        //   451: goto            461
        //   454: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   457: athrow         
        //   458: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   461: astore          26
        //   463: aload           9
        //   465: aload           8
        //   467: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.interned:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   470: astore          27
        //   472: aload           4
        //   474: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getTemplateParameters:()Ljava/util/List;
        //   477: invokeinterface java/util/List.isEmpty:()Z
        //   482: ifeq            495
        //   485: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   488: goto            502
        //   491: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   494: athrow         
        //   495: new             Ljava/util/ArrayList;
        //   498: dup            
        //   499: invokespecial   java/util/ArrayList.<init>:()V
        //   502: astore          28
        //   504: aload           6
        //   506: ifnull          523
        //   509: new             Ljava/util/ArrayList;
        //   512: dup            
        //   513: invokespecial   java/util/ArrayList.<init>:()V
        //   516: goto            524
        //   519: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   522: athrow         
        //   523: aconst_null    
        //   524: astore          29
        //   526: iload           14
        //   528: ifeq            544
        //   531: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$Property.IS_ENUM_CLASS:Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$Property;
        //   534: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$Property.getMask:()I
        //   537: goto            545
        //   540: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   543: athrow         
        //   544: iconst_0       
        //   545: istore          30
        //   547: iload           15
        //   549: ifeq            565
        //   552: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.FINAL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   555: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.getMask:()I
        //   558: goto            566
        //   561: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   564: athrow         
        //   565: iconst_0       
        //   566: istore          31
        //   568: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   571: dup            
        //   572: aload_0        
        //   573: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   576: aload_0        
        //   577: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   580: lload           16
        //   582: aload           4
        //   584: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   587: aload           27
        //   589: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   592: aload           11
        //   594: aload           26
        //   596: aload           28
        //   598: aload           29
        //   600: new             Ljava/util/ArrayList;
        //   603: dup            
        //   604: invokespecial   java/util/ArrayList.<init>:()V
        //   607: new             Lcom/intellij/util/containers/MostlySingularMultiMap;
        //   610: dup            
        //   611: invokespecial   com/intellij/util/containers/MostlySingularMultiMap.<init>:()V
        //   614: aload           19
        //   616: aload           4
        //   618: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   621: iload           30
        //   623: iload           31
        //   625: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;II)V
        //   628: astore          7
        //   630: aload_0        
        //   631: aload           7
        //   633: aload           28
        //   635: aload           29
        //   637: aload           4
        //   639: aload           6
        //   641: aload           5
        //   643: aload           21
        //   645: aload           26
        //   647: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/lang/Object;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Ljava/util/List;)V
        //   650: iload           25
        //   652: ifeq            746
        //   655: aload           7
        //   657: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   660: ifnull          746
        //   663: goto            670
        //   666: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   669: athrow         
        //   670: aload_3        
        //   671: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   674: dup            
        //   675: aload_0        
        //   676: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   679: aload_0        
        //   680: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   683: aload           7
        //   685: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getComplexOffset:()J
        //   688: aload           7
        //   690: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   693: aconst_null    
        //   694: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.interned:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   697: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   700: aload           7
        //   702: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   705: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   708: getstatic       com/intellij/util/ArrayUtil.EMPTY_INT_ARRAY:[I
        //   711: aconst_null    
        //   712: aload           7
        //   714: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateParameters:()Ljava/util/List;
        //   717: aload           7
        //   719: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateSpecialization:()Ljava/util/List;
        //   722: iconst_0       
        //   723: iconst_0       
        //   724: aload           7
        //   726: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   729: aconst_null    
        //   730: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;[ILcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List;Ljava/util/List;IILcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   733: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   738: pop            
        //   739: goto            746
        //   742: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   745: athrow         
        //   746: aload           24
        //   748: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   751: if_acmpne       761
        //   754: goto            927
        //   757: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   760: athrow         
        //   761: aload           24
        //   763: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_USING_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   766: if_acmpne       805
        //   769: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   772: dup            
        //   773: aconst_null    
        //   774: aconst_null    
        //   775: aload           7
        //   777: aload           20
        //   779: aconst_null    
        //   780: aload_0        
        //   781: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   784: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //   787: astore          26
        //   789: aload_0        
        //   790: aload           23
        //   792: aload           7
        //   794: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getBuilder:()Lcom/intellij/util/Processor;
        //   797: aload           26
        //   799: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processUsingStatement:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   802: goto            927
        //   805: aload           7
        //   807: astore          26
        //   809: aload           7
        //   811: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getBuilder:()Lcom/intellij/util/Processor;
        //   814: astore          27
        //   816: aload           11
        //   818: astore          28
        //   820: iload           14
        //   822: istore          29
        //   824: aload           12
        //   826: astore          30
        //   828: aload           24
        //   830: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   833: if_acmpne       841
        //   836: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   839: astore          30
        //   841: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   844: dup            
        //   845: aload           30
        //   847: aload           4
        //   849: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParent:()Ljava/lang/Object;
        //   852: iload           25
        //   854: ifne            877
        //   857: aload           28
        //   859: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   862: if_acmpne       885
        //   865: goto            872
        //   868: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   871: athrow         
        //   872: iload           29
        //   874: ifne            885
        //   877: aload           7
        //   879: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   882: goto            887
        //   885: aload           7
        //   887: aload           20
        //   889: aload           4
        //   891: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLocalContext:()Lcom/intellij/psi/PsiElement;
        //   894: aload_0        
        //   895: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myAssumeNonNullOn:Z
        //   898: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //   901: astore          31
        //   903: aload_0        
        //   904: aload           23
        //   906: iload           29
        //   908: aload           28
        //   910: aload           27
        //   912: iload           25
        //   914: aload           26
        //   916: aload_3        
        //   917: invokedynamic   process:(ZLcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/util/Processor;ZLcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //   922: aload           31
        //   924: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processDeclaration:(Ljava/lang/Object;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //   927: goto            1246
        //   930: aload           24
        //   932: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRUCT_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   935: if_acmpeq       953
        //   938: aload           24
        //   940: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CLASS_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   943: if_acmpne       1020
        //   946: goto            953
        //   949: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   952: athrow         
        //   953: aload           11
        //   955: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   958: if_acmpeq       1014
        //   961: goto            968
        //   964: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   967: athrow         
        //   968: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   971: astore          11
        //   973: aload           20
        //   975: ifnonnull       1246
        //   978: aload           24
        //   980: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRUCT_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   983: if_acmpne       1003
        //   986: goto            993
        //   989: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   992: athrow         
        //   993: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   996: goto            1006
        //   999: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1002: athrow         
        //  1003: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1006: dup            
        //  1007: astore          21
        //  1009: astore          20
        //  1011: goto            1246
        //  1014: iconst_1       
        //  1015: istore          14
        //  1017: goto            1246
        //  1020: aload           24
        //  1022: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ENUM_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1025: if_acmpne       1041
        //  1028: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1031: astore          11
        //  1033: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1036: astore          12
        //  1038: goto            1246
        //  1041: aload           24
        //  1043: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNION_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1046: if_acmpne       1057
        //  1049: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1052: astore          11
        //  1054: goto            1246
        //  1057: aload           24
        //  1059: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1062: if_acmpne       1087
        //  1065: aload_0        
        //  1066: aload           23
        //  1068: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1071: astore          8
        //  1073: aload_0        
        //  1074: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //  1077: aload           23
        //  1079: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getComplexOffset:(Lcom/intellij/lang/NodeStructure;Ljava/lang/Object;)J
        //  1082: lstore          16
        //  1084: goto            1246
        //  1087: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CPP_VISIBILITY_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //  1090: aload           24
        //  1092: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1095: ifeq            1108
        //  1098: aload           24
        //  1100: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getVisibilityFromElementType:(Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1103: astore          20
        //  1105: goto            1246
        //  1108: aload           24
        //  1110: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.REFERENCE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1113: if_acmpne       1167
        //  1116: aload_0        
        //  1117: aload           23
        //  1119: aconst_null    
        //  1120: aload           4
        //  1122: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1125: astore          25
        //  1127: aload           25
        //  1129: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //  1132: astore          8
        //  1134: aload_0        
        //  1135: aload           23
        //  1137: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1140: lstore          16
        //  1142: aload           25
        //  1144: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1147: astore          9
        //  1149: aload           25
        //  1151: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //  1154: ifeq            1164
        //  1157: aload           25
        //  1159: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //  1162: astore          10
        //  1164: goto            1246
        //  1167: aload           24
        //  1169: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1172: if_acmpne       1182
        //  1175: aload           23
        //  1177: astore          5
        //  1179: goto            1246
        //  1182: aload           24
        //  1184: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1187: if_acmpne       1197
        //  1190: aload           23
        //  1192: astore          6
        //  1194: goto            1246
        //  1197: aload           24
        //  1199: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ATTRIBUTES:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1202: if_acmpne       1246
        //  1205: aload           13
        //  1207: ifnull          1238
        //  1210: goto            1217
        //  1213: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1216: athrow         
        //  1217: aload           13
        //  1219: aload_0        
        //  1220: aload           23
        //  1222: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //  1225: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1230: pop            
        //  1231: goto            1246
        //  1234: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1237: athrow         
        //  1238: aload_0        
        //  1239: aload           23
        //  1241: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //  1244: astore          13
        //  1246: goto            214
        //  1249: aload           13
        //  1251: ifnonnull       1259
        //  1254: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1257: astore          13
        //  1259: aload           7
        //  1261: ifnonnull       1472
        //  1264: aload           10
        //  1266: ifnull          1285
        //  1269: goto            1276
        //  1272: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1275: athrow         
        //  1276: aload           10
        //  1278: goto            1292
        //  1281: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1284: athrow         
        //  1285: aload           9
        //  1287: aload           8
        //  1289: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.interned:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1292: astore          22
        //  1294: aload           5
        //  1296: ifnull          1313
        //  1299: new             Ljava/util/ArrayList;
        //  1302: dup            
        //  1303: invokespecial   java/util/ArrayList.<init>:()V
        //  1306: goto            1316
        //  1309: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1312: athrow         
        //  1313: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1316: astore          23
        //  1318: aload           4
        //  1320: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getTemplateParameters:()Ljava/util/List;
        //  1323: invokeinterface java/util/List.isEmpty:()Z
        //  1328: ifeq            1341
        //  1331: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1334: goto            1348
        //  1337: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1340: athrow         
        //  1341: new             Ljava/util/ArrayList;
        //  1344: dup            
        //  1345: invokespecial   java/util/ArrayList.<init>:()V
        //  1348: astore          24
        //  1350: aload           6
        //  1352: ifnull          1369
        //  1355: new             Ljava/util/ArrayList;
        //  1358: dup            
        //  1359: invokespecial   java/util/ArrayList.<init>:()V
        //  1362: goto            1370
        //  1365: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1368: athrow         
        //  1369: aconst_null    
        //  1370: astore          25
        //  1372: iload           14
        //  1374: ifeq            1390
        //  1377: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$Property.IS_ENUM_CLASS:Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$Property;
        //  1380: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$Property.getMask:()I
        //  1383: goto            1391
        //  1386: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1389: athrow         
        //  1390: iconst_0       
        //  1391: istore          26
        //  1393: aload           4
        //  1395: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getModifiers:()I
        //  1398: istore          27
        //  1400: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1403: dup            
        //  1404: aload_0        
        //  1405: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1408: aload_0        
        //  1409: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1412: lload           16
        //  1414: aload           4
        //  1416: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1419: aload           22
        //  1421: aload           13
        //  1423: aload           11
        //  1425: aload           23
        //  1427: aload           24
        //  1429: aload           25
        //  1431: aconst_null    
        //  1432: aconst_null    
        //  1433: aload           19
        //  1435: aload           4
        //  1437: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1440: iload           26
        //  1442: iload           27
        //  1444: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;II)V
        //  1447: astore          7
        //  1449: aload_0        
        //  1450: aload           7
        //  1452: aload           24
        //  1454: aload           25
        //  1456: aload           4
        //  1458: aload           6
        //  1460: aload           5
        //  1462: aload           21
        //  1464: aload           23
        //  1466: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/lang/Object;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Ljava/util/List;)V
        //  1469: goto            1479
        //  1472: aload           7
        //  1474: aload           13
        //  1476: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.addAttributes:(Ljava/util/List;)V
        //  1479: aload           7
        //  1481: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredeclaration:()Z
        //  1484: ifeq            1562
        //  1487: aload_2        
        //  1488: aload           4
        //  1490: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.setLocalContext:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  1493: aload_2        
        //  1494: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1497: new             Ljava/lang/StringBuilder;
        //  1500: dup            
        //  1501: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1504: aload           11
        //  1506: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //  1509: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1512: ldc             " "
        //  1514: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1517: aload           8
        //  1519: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1522: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1525: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //  1528: aload_2        
        //  1529: aload           9
        //  1531: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learnNamespaceQualifier:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)V
        //  1534: aload           10
        //  1536: ifnull          1592
        //  1539: goto            1546
        //  1542: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1545: athrow         
        //  1546: aload_2        
        //  1547: aload           10
        //  1549: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments.getArguments:()Ljava/util/List;
        //  1552: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learnTypeArguments:(Ljava/util/List;)V
        //  1555: goto            1592
        //  1558: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1561: athrow         
        //  1562: aload_2        
        //  1563: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.eraseWasConst:()Z
        //  1566: istore          22
        //  1568: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1571: dup            
        //  1572: aload           7
        //  1574: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1577: aconst_null    
        //  1578: iload           22
        //  1580: iconst_0       
        //  1581: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Ljava/util/List;Ljava/lang/String;ZZ)V
        //  1584: astore          23
        //  1586: aload_2        
        //  1587: aload           23
        //  1589: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learnBaseType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  1592: aload           8
        //  1594: ifnull          1636
        //  1597: aload           7
        //  1599: ifnull          1636
        //  1602: goto            1609
        //  1605: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1608: athrow         
        //  1609: aload_3        
        //  1610: ifnull          1636
        //  1613: goto            1620
        //  1616: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1619: athrow         
        //  1620: aload_3        
        //  1621: aload           7
        //  1623: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //  1628: pop            
        //  1629: goto            1636
        //  1632: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1635: athrow         
        //  1636: return         
        //    Signature:
        //  (TT;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext<TT;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  241    254    257    261    Ljava/lang/IllegalArgumentException;
        //  306    322    325    329    Ljava/lang/IllegalArgumentException;
        //  314    337    340    344    Ljava/lang/IllegalArgumentException;
        //  329    352    355    359    Ljava/lang/IllegalArgumentException;
        //  344    367    370    374    Ljava/lang/IllegalArgumentException;
        //  359    382    385    389    Ljava/lang/IllegalArgumentException;
        //  374    394    397    401    Ljava/lang/IllegalArgumentException;
        //  389    409    412    416    Ljava/lang/IllegalArgumentException;
        //  401    420    420    424    Ljava/lang/IllegalArgumentException;
        //  427    437    440    444    Ljava/lang/IllegalArgumentException;
        //  432    454    454    458    Ljava/lang/IllegalArgumentException;
        //  472    491    491    495    Ljava/lang/IllegalArgumentException;
        //  504    519    519    523    Ljava/lang/IllegalArgumentException;
        //  526    540    540    544    Ljava/lang/IllegalArgumentException;
        //  547    561    561    565    Ljava/lang/IllegalArgumentException;
        //  630    663    666    670    Ljava/lang/IllegalArgumentException;
        //  655    739    742    746    Ljava/lang/IllegalArgumentException;
        //  746    757    757    761    Ljava/lang/IllegalArgumentException;
        //  841    865    868    872    Ljava/lang/IllegalArgumentException;
        //  930    946    949    953    Ljava/lang/IllegalArgumentException;
        //  938    961    964    968    Ljava/lang/IllegalArgumentException;
        //  973    986    989    993    Ljava/lang/IllegalArgumentException;
        //  978    999    999    1003   Ljava/lang/IllegalArgumentException;
        //  1197   1210   1213   1217   Ljava/lang/IllegalArgumentException;
        //  1205   1234   1234   1238   Ljava/lang/IllegalArgumentException;
        //  1259   1269   1272   1276   Ljava/lang/IllegalArgumentException;
        //  1264   1281   1281   1285   Ljava/lang/IllegalArgumentException;
        //  1294   1309   1309   1313   Ljava/lang/IllegalArgumentException;
        //  1318   1337   1337   1341   Ljava/lang/IllegalArgumentException;
        //  1350   1365   1365   1369   Ljava/lang/IllegalArgumentException;
        //  1372   1386   1386   1390   Ljava/lang/IllegalArgumentException;
        //  1479   1539   1542   1546   Ljava/lang/IllegalArgumentException;
        //  1487   1558   1558   1562   Ljava/lang/IllegalArgumentException;
        //  1592   1602   1605   1609   Ljava/lang/IllegalArgumentException;
        //  1597   1613   1616   1620   Ljava/lang/IllegalArgumentException;
        //  1609   1629   1632   1636   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0329:
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
    
    private void a(final OCStructSymbol ocStructSymbol, final List<OCTypeParameterSymbol> list, final List<OCTypeArgument> list2, final DeclarationContext<T> declarationContext, final T t, final T t2, final OCVisibility ocVisibility, final List<Pair<OCType, OCVisibility>> list3) {
        if (t != null) {
            final DeclarationContext<T> declarationContext2 = this.createDeclarationContext(ocStructSymbol);
            declarationContext2.setInsideTemplateParams(true);
            this.a(t, list2, declarationContext2);
        }
        this.a(t2, ocStructSymbol, ocVisibility, list3, declarationContext);
        final Iterator<T> iterator = declarationContext.getTemplateParameters().iterator();
        while (iterator.hasNext()) {
            this.b(iterator.next(), list, this.createDeclarationContext(ocStructSymbol));
            ((ArrayList)list).trimToSize();
        }
    }
    
    private void a(@Nullable final T t, @Nullable final OCStructSymbol ocStructSymbol, @Nullable final OCVisibility ocVisibility, @NotNull final List<Pair<OCType, OCVisibility>> list, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "bases", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processStructBaseClause"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processStructBaseClause"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (t != null) {
            OCVisibility visibilityFromElementType = ocVisibility;
            final Iterator<T> iterator = this.getChildren(t).iterator();
            while (iterator.hasNext()) {
                for (final T next : this.getChildren(iterator.next())) {
                    final IElementType type = this.type(next);
                    Label_0291: {
                        try {
                            if (type != OCElementTypes.REFERENCE_ELEMENT) {
                                if (type != OCElementTypes.TYPE_ELEMENT) {
                                    break Label_0291;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        final DeclarationContext<T> declarationContext2 = new DeclarationContext<T>(null, null, ocStructSymbol, null, declarationContext.getLocalContext(), this.myAssumeNonNullOn);
                        final OCTypeBuilder a = this.a(declarationContext2);
                        Label_0267: {
                            try {
                                declarationContext2.setBaseClause(true);
                                if (type == OCElementTypes.TYPE_ELEMENT) {
                                    this.processTypeElement(next, a, this.builder, declarationContext2);
                                    break Label_0267;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw b(ex4);
                            }
                            this.a(next, a, declarationContext2);
                        }
                        list.add((Pair<OCType, OCVisibility>)Pair.create((Object)a.getResult(), (Object)visibilityFromElementType));
                        visibilityFromElementType = ocVisibility;
                        continue;
                    }
                    if (OCTokenTypes.CPP_VISIBILITY_KEYWORDS.contains(type)) {
                        visibilityFromElementType = OCVisibility.getVisibilityFromElementType(type);
                    }
                }
            }
            ((ArrayList)list).trimToSize();
        }
    }
    
    private OCQualifiedName a(@NotNull final T t, @Nullable final OCQualifiedName ocQualifiedName, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCQualifiedName ocQualifiedName2 = ocQualifiedName;
        String s = null;
        List<OCTypeArgument> list = null;
        int n = 0;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            Label_0285: {
                Label_0257: {
                    Label_0191: {
                        try {
                            if (n == 0) {
                                break Label_0257;
                            }
                            if (!OCTokenTypes.OVERLOADED_CPP_OPERATORS_ID_PARTS.contains(type)) {
                                break Label_0191;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        s += this.nodeText(next);
                        continue;
                    }
                    if (type == OCElementTypes.TYPE_ELEMENT) {
                        s = s + " " + this.processTypeElement(next, this.a(OCLanguageKind.CPP, declarationContext), this.builder, declarationContext).getCanonicalName();
                        continue;
                    }
                    continue;
                    try {
                        if (type != OCTokenTypes.COLON2X || ocQualifiedName2 != null) {
                            break Label_0285;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                ocQualifiedName2 = OCQualifiedName.GLOBAL;
                continue;
            }
            if (type == OCElementTypes.CPP_NAMESPACE_QUALIFIER) {
                ocQualifiedName2 = this.a(next, ocQualifiedName2, declarationContext);
            }
            else {
                try {
                    if (type == OCElementTypes.REFERENCE_ELEMENT) {
                        return this.a(next, ocQualifiedName2, declarationContext);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                Label_0363: {
                    try {
                        if (type != OCTokenTypes.IDENTIFIER) {
                            if (type != OCTokenTypes.THIS_CPP_KEYWORD) {
                                break Label_0363;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    s = this.nodeText(next);
                    continue;
                }
                if (type == OCElementTypes.TEMPLATE_ARGUMENT_LIST) {
                    list = new ArrayList<OCTypeArgument>();
                    this.a(next, list, declarationContext);
                }
                else {
                    if (type != OCTokenTypes.OPERATOR_CPP_KEYWORD) {
                        continue;
                    }
                    n = 1;
                    s = this.nodeText(next);
                }
            }
        }
        OCQualifiedName interned;
        if (list != null) {
            interned = new OCQualifiedNameWithArguments(ocQualifiedName2, s, list);
        }
        else {
            interned = OCQualifiedName.interned(ocQualifiedName2, s);
        }
        return interned;
    }
    
    private void a(@NotNull final T t, @NotNull final List<OCTypeArgument> list, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentListNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTemplateArgumentList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTemplateArgumentList"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTemplateArgumentList"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        for (final T next : this.getChildren(t)) {
            if (this.type(next) == OCElementTypes.GENERIC_ARGUMENT) {
                for (final T next2 : this.getChildren(next)) {
                    if (this.type(next2) == OCElementTypes.TYPE_ELEMENT) {
                        final OCType processTypeElement = this.processTypeElement(next2, this.a(OCLanguageKind.OBJ_C, declarationContext), this.builder, declarationContext);
                        try {
                            if (processTypeElement == null) {
                                continue;
                            }
                            list.add(processTypeElement);
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                    }
                }
            }
            if (this.type(next) == OCElementTypes.TYPE_ELEMENT) {
                final OCType processTypeElement2 = this.processTypeElement(next, this.a(OCLanguageKind.CPP, declarationContext), this.builder, declarationContext);
                try {
                    if (processTypeElement2 == null) {
                        continue;
                    }
                    list.add(processTypeElement2);
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            else {
                try {
                    if (!OCElementTypes.EXPRESSIONS.contains(this.type(next))) {
                        continue;
                    }
                    list.add(new OCExpressionTypeArgument(this.getExpressionSymbol(next, declarationContext)));
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
        }
    }
    
    public OCTypeParameterTypeSymbol processTypeParameter(@NotNull final T t, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTypeParameter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTypeParameter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final long complexOffsetFromNode = this.getComplexOffsetFromNode(t);
        final List<String> emptyList = Collections.emptyList();
        String nodeText = null;
        OCType processTypeElement = null;
        boolean b = false;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCTokenTypes.IDENTIFIER) {
                nodeText = this.nodeText(next);
            }
            else if (type == OCElementTypes.TYPE_ELEMENT) {
                processTypeElement = this.processTypeElement(next, this.a(declarationContext), this.builder, declarationContext);
            }
            else {
                if (type != OCTokenTypes.ELLIPSIS) {
                    continue;
                }
                b = true;
            }
        }
        return new OCTypeParameterTypeSymbol(this.project, this.myVirtualFile, complexOffsetFromNode, nodeText, processTypeElement, emptyList, this.h(declarationContext.getParent()), b);
    }
    
    private void a(@NotNull final T t, @NotNull final OCTypeBuilder ocTypeBuilder, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocTypeBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeBuilder", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processReference"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processReference"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        OCQualifiedName ocQualifiedName = null;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            Label_0202: {
                try {
                    if (type != OCTokenTypes.COLON2X || ocQualifiedName != null) {
                        break Label_0202;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                ocQualifiedName = OCQualifiedName.GLOBAL;
                continue;
            }
            if (type == OCElementTypes.CPP_NAMESPACE_QUALIFIER) {
                ocQualifiedName = this.a(next, ocQualifiedName, declarationContext);
            }
            else {
                try {
                    if (type == OCTokenTypes.IDENTIFIER) {
                        ocTypeBuilder.learn(type, this.nodeText(next));
                        continue;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                Label_0301: {
                    try {
                        if (type != OCElementTypes.TEMPLATE_ARGUMENT_LIST) {
                            if (type != OCElementTypes.GENERIC_ARGUMENTS_LIST) {
                                break Label_0301;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    final ArrayList<OCTypeArgument> list = new ArrayList<OCTypeArgument>();
                    this.a(next, list, declarationContext);
                    ocTypeBuilder.learnTypeArguments(list);
                    continue;
                    try {
                        if (type == OCElementTypes.REFERENCE_ELEMENT) {
                            this.a(next, ocTypeBuilder, declarationContext);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                ocTypeBuilder.learn(type);
            }
        }
        ocTypeBuilder.learnNamespaceQualifier(ocQualifiedName);
    }
    
    private OCSymbol a(@NotNull final T p0, @NotNull final OCTypeBuilder p1, @NotNull final Processor<OCSymbol> p2, @NotNull final DeclarationContext<T> p3) {
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
        //    18: ldc             "declaratorNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processDeclarator"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "typeBuilder"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processDeclarator"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "builder"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processDeclarator"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   151: ldc             "context"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "processDeclarator"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload_2        
        //   178: astore          5
        //   180: aload_2        
        //   181: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.copy:()Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //   184: astore_2       
        //   185: aload_0        
        //   186: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   189: aload_1        
        //   190: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getComplexOffset:(Lcom/intellij/lang/NodeStructure;Ljava/lang/Object;)J
        //   193: lstore          6
        //   195: aconst_null    
        //   196: astore          8
        //   198: aconst_null    
        //   199: astore          9
        //   201: new             Ljava/util/ArrayList;
        //   204: dup            
        //   205: invokespecial   java/util/ArrayList.<init>:()V
        //   208: astore          10
        //   210: aload           4
        //   212: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getAttributes:()Ljava/util/List;
        //   215: astore          11
        //   217: aconst_null    
        //   218: astore          12
        //   220: iconst_0       
        //   221: istore          13
        //   223: iconst_0       
        //   224: istore          14
        //   226: aconst_null    
        //   227: astore          15
        //   229: iconst_0       
        //   230: istore          16
        //   232: iconst_0       
        //   233: istore          17
        //   235: iconst_0       
        //   236: istore          18
        //   238: iconst_0       
        //   239: istore          19
        //   241: iconst_0       
        //   242: istore          20
        //   244: iconst_0       
        //   245: istore          21
        //   247: iconst_0       
        //   248: istore          22
        //   250: iconst_0       
        //   251: istore          23
        //   253: iconst_0       
        //   254: istore          24
        //   256: iconst_0       
        //   257: istore          25
        //   259: iconst_0       
        //   260: istore          26
        //   262: iconst_0       
        //   263: istore          27
        //   265: ldc             ""
        //   267: astore          28
        //   269: aconst_null    
        //   270: astore          29
        //   272: aload           4
        //   274: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getForCollection:()Lcom/intellij/lang/ASTNode;
        //   277: astore          30
        //   279: aconst_null    
        //   280: astore          31
        //   282: aconst_null    
        //   283: astore          32
        //   285: aload_0        
        //   286: aload_1        
        //   287: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   290: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   295: astore          33
        //   297: aload           33
        //   299: invokeinterface java/util/Iterator.hasNext:()Z
        //   304: ifeq            1661
        //   307: aload           33
        //   309: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   314: astore          34
        //   316: aload_0        
        //   317: aload           34
        //   319: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   322: astore          35
        //   324: aload           35
        //   326: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   329: if_acmpne       349
        //   332: aload           9
        //   334: ifnonnull       349
        //   337: goto            344
        //   340: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   347: astore          9
        //   349: aload           35
        //   351: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_QUALIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   354: if_acmpne       372
        //   357: aload_0        
        //   358: aload           34
        //   360: aload           9
        //   362: aload           4
        //   364: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   367: astore          9
        //   369: goto            1658
        //   372: iload           14
        //   374: ifeq            705
        //   377: iload           17
        //   379: ifne            705
        //   382: goto            389
        //   385: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OVERLOADED_CPP_OPERATORS_ID_PARTS:Lcom/intellij/psi/tree/TokenSet;
        //   392: aload           35
        //   394: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   397: ifne            434
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: aload           35
        //   409: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   412: if_acmpne       705
        //   415: goto            422
        //   418: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: iload           18
        //   424: ifne            705
        //   427: goto            434
        //   430: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   433: athrow         
        //   434: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.POSSIBLE_ID_NAMES:Lcom/intellij/psi/tree/TokenSet;
        //   437: aload           35
        //   439: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   442: ifne            485
        //   445: goto            452
        //   448: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: aload           35
        //   454: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   457: if_acmpeq       485
        //   460: goto            467
        //   463: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   466: athrow         
        //   467: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   470: aload           35
        //   472: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   475: ifeq            550
        //   478: goto            485
        //   481: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   484: athrow         
        //   485: iload           25
        //   487: ifne            519
        //   490: goto            497
        //   493: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: new             Ljava/lang/StringBuilder;
        //   500: dup            
        //   501: invokespecial   java/lang/StringBuilder.<init>:()V
        //   504: aload           28
        //   506: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   509: ldc             " "
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   517: astore          28
        //   519: aload           35
        //   521: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   524: if_acmpne       616
        //   527: aload_0        
        //   528: aload           34
        //   530: aload_0        
        //   531: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.CPP:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   534: aload           4
        //   536: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //   539: aload_3        
        //   540: aload           4
        //   542: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   545: astore          29
        //   547: goto            616
        //   550: aload           35
        //   552: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   555: if_acmpne       578
        //   558: aload           15
        //   560: ifnonnull       578
        //   563: goto            570
        //   566: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   569: athrow         
        //   570: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   573: astore          15
        //   575: goto            616
        //   578: aload           35
        //   580: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   583: if_acmpne       616
        //   586: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   589: aload           15
        //   591: invokevirtual   java/lang/Boolean.equals:(Ljava/lang/Object;)Z
        //   594: ifne            611
        //   597: goto            604
        //   600: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   603: athrow         
        //   604: goto            297
        //   607: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   610: athrow         
        //   611: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   614: astore          15
        //   616: aload           35
        //   618: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   621: if_acmpne       652
        //   624: iconst_1       
        //   625: istore          25
        //   627: new             Ljava/lang/StringBuilder;
        //   630: dup            
        //   631: invokespecial   java/lang/StringBuilder.<init>:()V
        //   634: aload           28
        //   636: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   639: ldc             "\"\""
        //   641: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   644: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   647: astore          28
        //   649: goto            1658
        //   652: new             Ljava/lang/StringBuilder;
        //   655: dup            
        //   656: invokespecial   java/lang/StringBuilder.<init>:()V
        //   659: aload           28
        //   661: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   664: aload_0        
        //   665: aload           34
        //   667: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   670: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   673: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   676: astore          28
        //   678: iload           26
        //   680: ifne            1658
        //   683: lload           6
        //   685: aload_0        
        //   686: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   689: aload           34
        //   691: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getComplexOffset:(Lcom/intellij/lang/NodeStructure;Ljava/lang/Object;)J
        //   694: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.adjust:(JJ)J
        //   697: lstore          6
        //   699: iconst_1       
        //   700: istore          26
        //   702: goto            1658
        //   705: aload           35
        //   707: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   710: if_acmpeq       728
        //   713: aload           35
        //   715: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   718: if_acmpne       808
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: iload           17
        //   730: ifne            808
        //   733: goto            740
        //   736: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   739: athrow         
        //   740: aload           35
        //   742: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   745: if_acmpne       763
        //   748: goto            755
        //   751: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   754: athrow         
        //   755: iconst_1       
        //   756: goto            764
        //   759: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   762: athrow         
        //   763: iconst_0       
        //   764: istore          14
        //   766: iload           14
        //   768: ifne            795
        //   771: aload_0        
        //   772: aload           34
        //   774: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   777: astore          8
        //   779: lload           6
        //   781: aload_0        
        //   782: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   785: aload           34
        //   787: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getComplexOffset:(Lcom/intellij/lang/NodeStructure;Ljava/lang/Object;)J
        //   790: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.adjust:(JJ)J
        //   793: lstore          6
        //   795: aload           12
        //   797: ifnonnull       1658
        //   800: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   803: astore          12
        //   805: goto            1658
        //   808: aload           35
        //   810: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TILDE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   813: if_acmpne       834
        //   816: iload           17
        //   818: ifne            834
        //   821: goto            828
        //   824: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   827: athrow         
        //   828: iconst_1       
        //   829: istore          13
        //   831: goto            1658
        //   834: aload           35
        //   836: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   839: if_acmpne       848
        //   842: iconst_1       
        //   843: istore          18
        //   845: goto            1658
        //   848: aload           35
        //   850: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   853: if_acmpne       948
        //   856: iload           14
        //   858: ifeq            898
        //   861: goto            868
        //   864: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   867: athrow         
        //   868: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   871: invokevirtual   com/jetbrains/cidr/lang/parser/OCKeywordElementType.getName:()Ljava/lang/String;
        //   874: astore          8
        //   876: new             Ljava/lang/StringBuilder;
        //   879: dup            
        //   880: invokespecial   java/lang/StringBuilder.<init>:()V
        //   883: aload           8
        //   885: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   888: aload           28
        //   890: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   893: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   896: astore          8
        //   898: iconst_1       
        //   899: istore          17
        //   901: aload           4
        //   903: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.myKRParamterList:Ljava/lang/Object;
        //   906: ifnull          935
        //   909: aload           10
        //   911: aload           4
        //   913: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.myKRParamterList:Ljava/lang/Object;
        //   916: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   921: pop            
        //   922: aload           4
        //   924: aconst_null    
        //   925: putfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.myKRParamterList:Ljava/lang/Object;
        //   928: goto            1658
        //   931: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   934: athrow         
        //   935: aload           10
        //   937: aload           34
        //   939: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   944: pop            
        //   945: goto            1658
        //   948: aload           35
        //   950: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ATTRIBUTES:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   953: if_acmpne       1000
        //   956: aload           11
        //   958: ifnull          989
        //   961: goto            968
        //   964: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   967: athrow         
        //   968: aload           11
        //   970: aload_0        
        //   971: aload           34
        //   973: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //   976: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   981: pop            
        //   982: goto            1658
        //   985: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   988: athrow         
        //   989: aload_0        
        //   990: aload           34
        //   992: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processAttributeList:(Ljava/lang/Object;)Ljava/util/List;
        //   995: astore          11
        //   997: goto            1658
        //  1000: aload           35
        //  1002: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1005: if_acmpne       1023
        //  1008: aload           4
        //  1010: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.BLOCK_MODIFIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  1013: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //  1016: goto            1658
        //  1019: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1022: athrow         
        //  1023: aload           35
        //  1025: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1028: if_acmpne       1037
        //  1031: iconst_1       
        //  1032: istore          16
        //  1034: goto            1658
        //  1037: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1040: aload           35
        //  1042: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1045: ifeq            1085
        //  1048: iload           16
        //  1050: ifne            1075
        //  1053: goto            1060
        //  1056: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1059: athrow         
        //  1060: aload           35
        //  1062: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPOUND_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1065: if_acmpne       1085
        //  1068: goto            1075
        //  1071: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1074: athrow         
        //  1075: iconst_1       
        //  1076: istore          16
        //  1078: aload           34
        //  1080: astore          30
        //  1082: goto            1658
        //  1085: aload           35
        //  1087: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1090: if_acmpne       1175
        //  1093: aload_0        
        //  1094: aload           34
        //  1096: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1099: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1104: astore          36
        //  1106: aload           36
        //  1108: invokeinterface java/util/Iterator.hasNext:()Z
        //  1113: ifeq            1172
        //  1116: aload           36
        //  1118: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1123: astore          37
        //  1125: aload_0        
        //  1126: aload           37
        //  1128: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1131: astore          38
        //  1133: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1136: aload           38
        //  1138: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1141: ifeq            1169
        //  1144: iload           16
        //  1146: ifne            1166
        //  1149: goto            1156
        //  1152: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1155: athrow         
        //  1156: iconst_1       
        //  1157: istore          16
        //  1159: aload           37
        //  1161: astore          30
        //  1163: goto            1169
        //  1166: aconst_null    
        //  1167: astore          30
        //  1169: goto            1106
        //  1172: goto            1658
        //  1175: aload           35
        //  1177: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1180: if_acmpne       1210
        //  1183: iload           17
        //  1185: ifeq            1201
        //  1188: goto            1195
        //  1191: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1194: athrow         
        //  1195: iconst_1       
        //  1196: istore          19
        //  1198: goto            1658
        //  1201: aload_2        
        //  1202: aload           35
        //  1204: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //  1207: goto            1658
        //  1210: aload           35
        //  1212: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1215: if_acmpne       1245
        //  1218: iload           17
        //  1220: ifeq            1236
        //  1223: goto            1230
        //  1226: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1229: athrow         
        //  1230: iconst_1       
        //  1231: istore          20
        //  1233: goto            1658
        //  1236: aload_2        
        //  1237: aload           35
        //  1239: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //  1242: goto            1658
        //  1245: aload           35
        //  1247: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1250: if_acmpne       1280
        //  1253: iload           17
        //  1255: ifeq            1271
        //  1258: goto            1265
        //  1261: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1264: athrow         
        //  1265: iconst_1       
        //  1266: istore          21
        //  1268: goto            1658
        //  1271: aload_2        
        //  1272: aload           35
        //  1274: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //  1277: goto            1658
        //  1280: aload           35
        //  1282: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1285: if_acmpne       1315
        //  1288: iload           17
        //  1290: ifeq            1306
        //  1293: goto            1300
        //  1296: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1299: athrow         
        //  1300: iconst_1       
        //  1301: istore          22
        //  1303: goto            1658
        //  1306: aload_2        
        //  1307: aload           35
        //  1309: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //  1312: goto            1658
        //  1315: aload           35
        //  1317: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LITERAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1320: if_acmpne       1366
        //  1323: aload_0        
        //  1324: aload           34
        //  1326: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.e:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1329: astore          36
        //  1331: aload           36
        //  1333: instanceof      Ljava/lang/Number;
        //  1336: ifeq            1358
        //  1339: aload_2        
        //  1340: aload           36
        //  1342: checkcast       Ljava/lang/Number;
        //  1345: invokevirtual   java/lang/Number.intValue:()I
        //  1348: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(I)V
        //  1351: goto            1363
        //  1354: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1357: athrow         
        //  1358: aload_2        
        //  1359: iconst_m1      
        //  1360: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(I)V
        //  1363: goto            1658
        //  1366: aload           35
        //  1368: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_QUALIFIED_POINTER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1371: if_acmpne       1475
        //  1374: aload_0        
        //  1375: aload           34
        //  1377: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1380: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1385: astore          36
        //  1387: aload           36
        //  1389: invokeinterface java/util/Iterator.hasNext:()Z
        //  1394: ifeq            1441
        //  1397: aload           36
        //  1399: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1404: astore          37
        //  1406: aload_0        
        //  1407: aload           37
        //  1409: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1412: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE_QUALIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1415: if_acmpne       1438
        //  1418: aload_2        
        //  1419: aload_0        
        //  1420: aload           37
        //  1422: aconst_null    
        //  1423: aload           4
        //  1425: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1428: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.setPointerQualifier:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)V
        //  1431: goto            1438
        //  1434: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1437: athrow         
        //  1438: goto            1387
        //  1441: aload_2        
        //  1442: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isInsideParentheses:()Z
        //  1445: ifeq            1465
        //  1448: aload           12
        //  1450: ifnonnull       1465
        //  1453: goto            1460
        //  1456: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1459: athrow         
        //  1460: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //  1463: astore          12
        //  1465: aload_2        
        //  1466: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1469: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //  1472: goto            1658
        //  1475: aload           35
        //  1477: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1480: if_acmpne       1490
        //  1483: aload           34
        //  1485: astore          32
        //  1487: goto            1658
        //  1490: aload           35
        //  1492: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1495: if_acmpne       1505
        //  1498: aload           34
        //  1500: astore          31
        //  1502: goto            1658
        //  1505: aload           35
        //  1507: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEFAULT_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1510: if_acmpne       1528
        //  1513: aload           4
        //  1515: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.DEFAULT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  1518: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //  1521: goto            1658
        //  1524: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1527: athrow         
        //  1528: aload           35
        //  1530: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DELETE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1533: if_acmpne       1551
        //  1536: aload           4
        //  1538: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.DELETE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  1541: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)V
        //  1544: goto            1658
        //  1547: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1550: athrow         
        //  1551: aload           35
        //  1553: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FINAL_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1556: if_acmpne       1565
        //  1559: iconst_1       
        //  1560: istore          23
        //  1562: goto            1658
        //  1565: aload           35
        //  1567: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OVERRIDE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1570: if_acmpne       1579
        //  1573: iconst_1       
        //  1574: istore          24
        //  1576: goto            1658
        //  1579: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FUNCTION_PTR_MODIFIERS:Lcom/intellij/psi/tree/TokenSet;
        //  1582: aload           35
        //  1584: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1587: ifeq            1624
        //  1590: aload_2        
        //  1591: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isInsideParentheses:()Z
        //  1594: ifeq            1624
        //  1597: goto            1604
        //  1600: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1603: athrow         
        //  1604: aload           12
        //  1606: ifnonnull       1624
        //  1609: goto            1616
        //  1612: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1615: athrow         
        //  1616: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //  1619: astore          12
        //  1621: goto            1652
        //  1624: aload           35
        //  1626: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1629: if_acmpne       1652
        //  1632: aload           10
        //  1634: invokeinterface java/util/List.isEmpty:()Z
        //  1639: ifne            1652
        //  1642: goto            1649
        //  1645: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1648: athrow         
        //  1649: iconst_1       
        //  1650: istore          27
        //  1652: aload_2        
        //  1653: aload           35
        //  1655: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learn:(Lcom/intellij/psi/tree/IElementType;)V
        //  1658: goto            297
        //  1661: new             Ljava/util/ArrayList;
        //  1664: dup            
        //  1665: aload           10
        //  1667: invokeinterface java/util/List.size:()I
        //  1672: invokespecial   java/util/ArrayList.<init>:(I)V
        //  1675: astore          33
        //  1677: new             Ljava/util/ArrayList;
        //  1680: dup            
        //  1681: aload           10
        //  1683: invokeinterface java/util/List.size:()I
        //  1688: invokespecial   java/util/ArrayList.<init>:(I)V
        //  1691: astore          34
        //  1693: new             Ljava/util/ArrayList;
        //  1696: dup            
        //  1697: aload           10
        //  1699: invokeinterface java/util/List.size:()I
        //  1704: invokespecial   java/util/ArrayList.<init>:(I)V
        //  1707: astore          35
        //  1709: aload_2        
        //  1710: aload           10
        //  1712: invokeinterface java/util/List.isEmpty:()Z
        //  1717: ifne            1728
        //  1720: iconst_1       
        //  1721: goto            1729
        //  1724: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1727: athrow         
        //  1728: iconst_0       
        //  1729: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getResult:(Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1732: astore          36
        //  1734: aload           30
        //  1736: ifnull          1805
        //  1739: aload_0        
        //  1740: aload           30
        //  1742: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1745: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPOUND_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1748: if_acmpne       1805
        //  1751: goto            1758
        //  1754: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1757: athrow         
        //  1758: aload_2        
        //  1759: aload_0        
        //  1760: aload           30
        //  1762: aload           36
        //  1764: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //  1767: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Z)I
        //  1770: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.updateArrayLength:(I)V
        //  1773: aload_2        
        //  1774: aload           10
        //  1776: invokeinterface java/util/List.isEmpty:()Z
        //  1781: ifne            1799
        //  1784: goto            1791
        //  1787: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1790: athrow         
        //  1791: iconst_1       
        //  1792: goto            1800
        //  1795: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1798: athrow         
        //  1799: iconst_0       
        //  1800: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getResult:(Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1803: astore          36
        //  1805: iconst_0       
        //  1806: istore          37
        //  1808: iload           37
        //  1810: aload           10
        //  1812: invokeinterface java/util/List.size:()I
        //  1817: if_icmpge       1957
        //  1820: aload           35
        //  1822: new             Ljava/util/ArrayList;
        //  1825: dup            
        //  1826: invokespecial   java/util/ArrayList.<init>:()V
        //  1829: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1834: pop            
        //  1835: aload           33
        //  1837: new             Ljava/util/ArrayList;
        //  1840: dup            
        //  1841: invokespecial   java/util/ArrayList.<init>:()V
        //  1844: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1849: pop            
        //  1850: aload           34
        //  1852: new             Ljava/util/ArrayList;
        //  1855: dup            
        //  1856: invokespecial   java/util/ArrayList.<init>:()V
        //  1859: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1864: pop            
        //  1865: iload           37
        //  1867: aload           10
        //  1869: invokeinterface java/util/List.size:()I
        //  1874: iconst_1       
        //  1875: isub           
        //  1876: if_icmpne       1906
        //  1879: goto            1886
        //  1882: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1885: athrow         
        //  1886: iload           27
        //  1888: ifne            1906
        //  1891: goto            1898
        //  1894: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1897: athrow         
        //  1898: iconst_1       
        //  1899: goto            1907
        //  1902: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1905: athrow         
        //  1906: iconst_0       
        //  1907: istore          38
        //  1909: aload_2        
        //  1910: aload           36
        //  1912: aload           33
        //  1914: iload           37
        //  1916: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  1921: checkcast       Ljava/util/List;
        //  1924: aload           35
        //  1926: iload           37
        //  1928: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  1933: checkcast       Ljava/util/List;
        //  1936: iload           38
        //  1938: iload           19
        //  1940: iload           20
        //  1942: iload           21
        //  1944: iload           22
        //  1946: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.createFunction:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;ZZZZZ)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1949: astore          36
        //  1951: iinc            37, 1
        //  1954: goto            1808
        //  1957: iload           16
        //  1959: aload_2        
        //  1960: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.wasNonArrayExpression:()Z
        //  1963: ior            
        //  1964: istore          16
        //  1966: aload           30
        //  1968: ifnull          1997
        //  1971: aload_2        
        //  1972: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.wasAuto:()Z
        //  1975: ifeq            1997
        //  1978: goto            1985
        //  1981: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1984: athrow         
        //  1985: aload_0        
        //  1986: aload           30
        //  1988: aload           36
        //  1990: aload           4
        //  1992: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //  1995: astore          36
        //  1997: aload           4
        //  1999: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getDeclaratorType:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2002: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2005: if_acmpne       2043
        //  2008: aload           36
        //  2010: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //  2013: ifeq            2036
        //  2016: goto            2023
        //  2019: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2022: athrow         
        //  2023: aload           36
        //  2025: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //  2028: astore          37
        //  2030: aload           37
        //  2032: iconst_1       
        //  2033: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.setFunctionParameterType:(Z)V
        //  2036: aload           36
        //  2038: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.convertArrayParameterType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  2041: astore          36
        //  2043: aload_0        
        //  2044: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myImplicitBridgingOn:Z
        //  2047: ifeq            2082
        //  2050: aload           11
        //  2052: ifnonnull       2072
        //  2055: goto            2062
        //  2058: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2061: athrow         
        //  2062: ldc             "ImplicitBridging"
        //  2064: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  2067: astore          11
        //  2069: goto            2082
        //  2072: aload           11
        //  2074: ldc             "ImplicitBridging"
        //  2076: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  2081: pop            
        //  2082: aload           11
        //  2084: ifnonnull       2092
        //  2087: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  2090: astore          11
        //  2092: aload_2        
        //  2093: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isTypedef:()Z
        //  2096: ifeq            2183
        //  2099: aload           36
        //  2101: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  2104: ifeq            2183
        //  2107: goto            2114
        //  2110: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2113: athrow         
        //  2114: aload           36
        //  2116: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  2119: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //  2122: astore          37
        //  2124: aload           37
        //  2126: invokeinterface java/util/List.size:()I
        //  2131: iconst_1       
        //  2132: if_icmpne       2183
        //  2135: aload           37
        //  2137: iconst_0       
        //  2138: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2143: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  2146: astore          38
        //  2148: aload           38
        //  2150: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isUnnamed:()Z
        //  2153: ifeq            2176
        //  2156: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  2159: dup            
        //  2160: aload           38
        //  2162: aload           8
        //  2164: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;)V
        //  2167: astore          36
        //  2169: aload           5
        //  2171: aload           36
        //  2173: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.learnBaseType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  2176: aload           38
        //  2178: aload           11
        //  2180: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.addAttributes:(Ljava/util/List;)V
        //  2183: iload           13
        //  2185: ifeq            2228
        //  2188: aload           8
        //  2190: ifnonnull       2206
        //  2193: goto            2200
        //  2196: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2199: athrow         
        //  2200: iconst_0       
        //  2201: istore          13
        //  2203: goto            2228
        //  2206: new             Ljava/lang/StringBuilder;
        //  2209: dup            
        //  2210: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2213: ldc             "~"
        //  2215: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2218: aload           8
        //  2220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2223: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2226: astore          8
        //  2228: aload           4
        //  2230: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getDeclaratorType:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2233: astore          37
        //  2235: aload           4
        //  2237: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParent:()Ljava/lang/Object;
        //  2240: astore          38
        //  2242: aload           4
        //  2244: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  2247: astore          39
        //  2249: aload           4
        //  2251: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  2254: astore          40
        //  2256: aload           36
        //  2258: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  2261: ifeq            2289
        //  2264: aload           4
        //  2266: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getDeclaratorType:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2269: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2272: if_acmpne       2289
        //  2275: goto            2282
        //  2278: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2281: athrow         
        //  2282: aload           36
        //  2284: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  2287: astore          36
        //  2289: aload           4
        //  2291: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isConstexpr:()Z
        //  2294: ifeq            2308
        //  2297: aload           36
        //  2299: aload_0        
        //  2300: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2303: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  2306: astore          36
        //  2308: aload           38
        //  2310: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  2313: ifeq            2368
        //  2316: aload           39
        //  2318: ifnull          2368
        //  2321: goto            2328
        //  2324: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2327: athrow         
        //  2328: aload           38
        //  2330: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //  2333: astore          42
        //  2335: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl;
        //  2338: dup            
        //  2339: aload_0        
        //  2340: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2343: aload_0        
        //  2344: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2347: lload           6
        //  2349: aload           8
        //  2351: aload           11
        //  2353: aload           42
        //  2355: aload           36
        //  2357: aload           39
        //  2359: aconst_null    
        //  2360: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Ljava/lang/String;)V
        //  2363: astore          41
        //  2365: goto            4201
        //  2368: aload           9
        //  2370: aload           8
        //  2372: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.interned:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  2375: astore          42
        //  2377: aconst_null    
        //  2378: astore          44
        //  2380: aload           4
        //  2382: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getTemplateParameters:()Ljava/util/List;
        //  2385: invokeinterface java/util/List.isEmpty:()Z
        //  2390: ifne            2405
        //  2393: new             Ljava/util/ArrayList;
        //  2396: dup            
        //  2397: invokespecial   java/util/ArrayList.<init>:()V
        //  2400: astore          43
        //  2402: goto            2410
        //  2405: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  2408: astore          43
        //  2410: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //  2413: aload           12
        //  2415: invokevirtual   java/lang/Boolean.equals:(Ljava/lang/Object;)Z
        //  2418: ifne            3727
        //  2421: aload           10
        //  2423: invokeinterface java/util/List.isEmpty:()Z
        //  2428: ifne            3727
        //  2431: goto            2438
        //  2434: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2437: athrow         
        //  2438: aload_2        
        //  2439: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isTypedef:()Z
        //  2442: ifne            3727
        //  2445: goto            2452
        //  2448: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2451: athrow         
        //  2452: aload           36
        //  2454: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  2457: ifeq            3727
        //  2460: goto            2467
        //  2463: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2466: athrow         
        //  2467: iconst_0       
        //  2468: istore          46
        //  2470: aload           4
        //  2472: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isVirtual:()Z
        //  2475: ifeq            2537
        //  2478: iload           16
        //  2480: ifeq            2537
        //  2483: goto            2490
        //  2486: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2489: athrow         
        //  2490: aload           4
        //  2492: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.DEFAULT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  2495: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.hasModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)Z
        //  2498: ifne            2537
        //  2501: goto            2508
        //  2504: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2507: athrow         
        //  2508: aload           4
        //  2510: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.DELETE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  2513: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.hasModifier:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)Z
        //  2516: ifne            2537
        //  2519: goto            2526
        //  2522: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2525: athrow         
        //  2526: iload           46
        //  2528: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.IS_PURE_VIRTUAL:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property;
        //  2531: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.getMask:()I
        //  2534: ior            
        //  2535: istore          46
        //  2537: aload           4
        //  2539: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isTemplateSymbol:()Z
        //  2542: ifeq            2556
        //  2545: iload           46
        //  2547: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.IS_TEMPLATE:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property;
        //  2550: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.getMask:()I
        //  2553: ior            
        //  2554: istore          46
        //  2556: aload           29
        //  2558: ifnull          2572
        //  2561: iload           46
        //  2563: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.IS_CONVERSION_OPERATOR:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property;
        //  2566: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.getMask:()I
        //  2569: ior            
        //  2570: istore          46
        //  2572: iload           14
        //  2574: ifeq            2588
        //  2577: iload           46
        //  2579: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.IS_OPERATOR:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property;
        //  2582: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.getMask:()I
        //  2585: ior            
        //  2586: istore          46
        //  2588: iload           25
        //  2590: ifeq            2604
        //  2593: iload           46
        //  2595: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.IS_UDL:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property;
        //  2598: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol$Property.getMask:()I
        //  2601: ior            
        //  2602: istore          46
        //  2604: aload           4
        //  2606: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getModifiers:()I
        //  2609: istore          47
        //  2611: iload           23
        //  2613: ifeq            2627
        //  2616: iload           47
        //  2618: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.FINAL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  2621: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.getMask:()I
        //  2624: ior            
        //  2625: istore          47
        //  2627: iload           24
        //  2629: ifeq            2643
        //  2632: iload           47
        //  2634: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.OVERRIDE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  2637: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.getMask:()I
        //  2640: ior            
        //  2641: istore          47
        //  2643: iload           13
        //  2645: ifne            2793
        //  2648: aload           9
        //  2650: ifnull          2692
        //  2653: goto            2660
        //  2656: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2659: athrow         
        //  2660: aload           8
        //  2662: ifnull          2692
        //  2665: goto            2672
        //  2668: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2671: athrow         
        //  2672: aload           8
        //  2674: aload           9
        //  2676: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //  2679: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2682: ifne            2793
        //  2685: goto            2692
        //  2688: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2691: athrow         
        //  2692: aload           40
        //  2694: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  2697: ifeq            2739
        //  2700: goto            2707
        //  2703: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2706: athrow         
        //  2707: aload           8
        //  2709: ifnull          2739
        //  2712: goto            2719
        //  2715: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2718: athrow         
        //  2719: aload           8
        //  2721: aload           40
        //  2723: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //  2726: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2729: ifne            2793
        //  2732: goto            2739
        //  2735: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2738: athrow         
        //  2739: aload           40
        //  2741: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  2744: ifeq            2801
        //  2747: goto            2754
        //  2750: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2753: athrow         
        //  2754: aload           4
        //  2756: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isDeclarationWithoutType:Z
        //  2759: ifeq            2801
        //  2762: goto            2769
        //  2765: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2768: athrow         
        //  2769: iload           14
        //  2771: ifne            2801
        //  2774: goto            2781
        //  2777: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2780: athrow         
        //  2781: aload           29
        //  2783: ifnonnull       2801
        //  2786: goto            2793
        //  2789: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2792: athrow         
        //  2793: iconst_1       
        //  2794: goto            2802
        //  2797: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2800: athrow         
        //  2801: iconst_0       
        //  2802: istore          48
        //  2804: iload           48
        //  2806: ifeq            2842
        //  2809: aload           37
        //  2811: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2814: if_acmpne       2834
        //  2817: goto            2824
        //  2820: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2823: athrow         
        //  2824: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2827: goto            2837
        //  2830: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2833: athrow         
        //  2834: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2837: astore          45
        //  2839: goto            2864
        //  2842: aload           37
        //  2844: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2847: if_acmpne       2859
        //  2850: aload           37
        //  2852: goto            2862
        //  2855: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2858: athrow         
        //  2859: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2862: astore          45
        //  2864: aload_0        
        //  2865: aload           38
        //  2867: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.h:(Ljava/lang/Object;)Lcom/intellij/openapi/util/TextRange;
        //  2870: astore          49
        //  2872: aload           34
        //  2874: iconst_0       
        //  2875: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2880: checkcast       Ljava/util/List;
        //  2883: astore          50
        //  2885: aload           33
        //  2887: iconst_0       
        //  2888: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2893: checkcast       Ljava/util/List;
        //  2896: astore          51
        //  2898: aload           35
        //  2900: iconst_0       
        //  2901: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2906: checkcast       Ljava/util/List;
        //  2909: astore          52
        //  2911: aload           32
        //  2913: ifnull          2925
        //  2916: new             Ljava/util/ArrayList;
        //  2919: dup            
        //  2920: invokespecial   java/util/ArrayList.<init>:()V
        //  2923: astore          44
        //  2925: aload           38
        //  2927: instanceof      Lcom/jetbrains/cidr/lang/psi/OCElement;
        //  2930: ifeq            3007
        //  2933: aload           38
        //  2935: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //  2938: iconst_1       
        //  2939: anewarray       Ljava/lang/Class;
        //  2942: dup            
        //  2943: iconst_0       
        //  2944: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //  2946: aastore        
        //  2947: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  2950: ifnull          3007
        //  2953: goto            2960
        //  2956: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2959: athrow         
        //  2960: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol;
        //  2963: dup            
        //  2964: aload_0        
        //  2965: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2968: aload_0        
        //  2969: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2972: lload           6
        //  2974: aload           40
        //  2976: aload           42
        //  2978: aload           43
        //  2980: aload           44
        //  2982: iload           46
        //  2984: iload           47
        //  2986: aload           11
        //  2988: aload           36
        //  2990: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  2993: aload           50
        //  2995: aload           45
        //  2997: aload           49
        //  2999: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCLocalFunctionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Ljava/util/List;IILjava/util/List;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/openapi/util/TextRange;)V
        //  3002: astore          41
        //  3004: goto            3140
        //  3007: aload           4
        //  3009: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isTemplateValueParameter:()Z
        //  3012: ifeq            3096
        //  3015: aload           30
        //  3017: ifnull          3042
        //  3020: goto            3027
        //  3023: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3026: athrow         
        //  3027: aload_0        
        //  3028: aload           30
        //  3030: aload           4
        //  3032: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  3035: goto            3043
        //  3038: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3041: athrow         
        //  3042: aconst_null    
        //  3043: astore          53
        //  3045: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //  3048: dup            
        //  3049: aload_0        
        //  3050: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  3053: aload_0        
        //  3054: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  3057: lload           6
        //  3059: aload           40
        //  3061: aload           42
        //  3063: aload           53
        //  3065: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  3068: aload           36
        //  3070: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  3073: aload_2        
        //  3074: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getArrayLengths:()[I
        //  3077: aload           43
        //  3079: aload           44
        //  3081: iconst_0       
        //  3082: iload           47
        //  3084: aload           49
        //  3086: aload           39
        //  3088: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;[ILjava/util/List;Ljava/util/List;IILcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //  3091: astore          41
        //  3093: goto            3140
        //  3096: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  3099: dup            
        //  3100: aload_0        
        //  3101: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  3104: aload_0        
        //  3105: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  3108: lload           6
        //  3110: aload           40
        //  3112: aload           42
        //  3114: aload           43
        //  3116: aload           44
        //  3118: iload           46
        //  3120: iload           47
        //  3122: aload           11
        //  3124: aload           36
        //  3126: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  3129: aload           50
        //  3131: aload           45
        //  3133: aload           39
        //  3135: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Ljava/util/List;IILjava/util/List;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //  3138: astore          41
        //  3140: aload           32
        //  3142: ifnull          3172
        //  3145: aload_0        
        //  3146: aload           41
        //  3148: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  3151: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.createDeclarationContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  3154: astore          53
        //  3156: aload           53
        //  3158: iconst_1       
        //  3159: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setInsideTemplateParams:(Z)V
        //  3162: aload_0        
        //  3163: aload           32
        //  3165: aload           44
        //  3167: aload           53
        //  3169: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  3172: aload_0        
        //  3173: aload           41
        //  3175: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  3178: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.createDeclarationContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  3181: astore          53
        //  3183: aload           53
        //  3185: aload           4
        //  3187: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLocalContext:()Lcom/intellij/psi/PsiElement;
        //  3190: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setLocalContext:(Lcom/intellij/psi/PsiElement;)V
        //  3193: aload           4
        //  3195: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getTemplateParameters:()Ljava/util/List;
        //  3198: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  3203: astore          54
        //  3205: aload           54
        //  3207: invokeinterface java/util/Iterator.hasNext:()Z
        //  3212: ifeq            3245
        //  3215: aload           54
        //  3217: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3222: astore          55
        //  3224: aload_0        
        //  3225: aload           55
        //  3227: aload           43
        //  3229: aload           53
        //  3231: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/Object;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  3234: aload           43
        //  3236: checkcast       Ljava/util/ArrayList;
        //  3239: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  3242: goto            3205
        //  3245: aload_2        
        //  3246: aload           53
        //  3248: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.setLocalContext:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  3251: aload           4
        //  3253: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getReferencesInDeclaration:()Ljava/util/List;
        //  3256: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  3261: astore          54
        //  3263: aload           54
        //  3265: invokeinterface java/util/Iterator.hasNext:()Z
        //  3270: ifeq            3316
        //  3273: aload           54
        //  3275: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3280: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //  3283: astore          55
        //  3285: aload           55
        //  3287: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //  3290: ifeq            3313
        //  3293: aload           55
        //  3295: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //  3298: aload           41
        //  3300: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  3303: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference.setSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //  3306: goto            3313
        //  3309: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3312: athrow         
        //  3313: goto            3263
        //  3316: aload           31
        //  3318: ifnull          3339
        //  3321: aload_0        
        //  3322: aload           31
        //  3324: aload_2        
        //  3325: aload_3        
        //  3326: aload           53
        //  3328: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  3331: pop            
        //  3332: goto            3339
        //  3335: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3338: athrow         
        //  3339: aload           43
        //  3341: invokeinterface java/util/List.isEmpty:()Z
        //  3346: ifeq            3358
        //  3349: aload_2        
        //  3350: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getResult:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  3353: astore          54
        //  3355: goto            3418
        //  3358: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeParentSymbolSetter;
        //  3361: dup            
        //  3362: aload           41
        //  3364: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  3367: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeParentSymbolSetter.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //  3370: astore          55
        //  3372: aload_2        
        //  3373: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getResult:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  3376: aload           55
        //  3378: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //  3381: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //  3384: astore          54
        //  3386: aload           29
        //  3388: ifnull          3403
        //  3391: aload           29
        //  3393: aload           55
        //  3395: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //  3398: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //  3401: astore          29
        //  3403: aload           41
        //  3405: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  3408: aload           55
        //  3410: aload           9
        //  3412: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeParentSymbolSetter.replaceQualifiedName:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  3415: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.setQualifier:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)V
        //  3418: aload           41
        //  3420: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  3423: ifeq            3471
        //  3426: aload           4
        //  3428: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isConstexpr:()Z
        //  3431: ifeq            3471
        //  3434: goto            3441
        //  3437: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3440: athrow         
        //  3441: aload           54
        //  3443: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //  3446: ifne            3471
        //  3449: goto            3456
        //  3452: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3455: athrow         
        //  3456: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //  3459: dup            
        //  3460: aconst_null    
        //  3461: aconst_null    
        //  3462: aload           54
        //  3464: iconst_1       
        //  3465: iconst_0       
        //  3466: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;ZZ)V
        //  3469: astore          54
        //  3471: aload_2        
        //  3472: aload           54
        //  3474: aload           51
        //  3476: aload           52
        //  3478: iconst_0       
        //  3479: iload           19
        //  3481: iload           20
        //  3483: iload           21
        //  3485: iload           22
        //  3487: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.createFunction:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;ZZZZZ)Lcom/jetbrains/cidr/lang/types/OCType;
        //  3490: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  3493: astore          55
        //  3495: iload           48
        //  3497: ifeq            3646
        //  3500: iload           13
        //  3502: ifeq            3531
        //  3505: goto            3512
        //  3508: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3511: athrow         
        //  3512: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  3515: dup            
        //  3516: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //  3519: aload           51
        //  3521: aload           52
        //  3523: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;)V
        //  3526: astore          55
        //  3528: goto            3646
        //  3531: aload           40
        //  3533: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  3536: ifeq            3599
        //  3539: aload           8
        //  3541: ifnull          3599
        //  3544: goto            3551
        //  3547: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3550: athrow         
        //  3551: aload           8
        //  3553: aload           40
        //  3555: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //  3558: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  3561: ifeq            3599
        //  3564: goto            3571
        //  3567: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3570: athrow         
        //  3571: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  3574: dup            
        //  3575: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  3578: dup            
        //  3579: aload           40
        //  3581: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  3584: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)V
        //  3587: aload           51
        //  3589: aload           52
        //  3591: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;)V
        //  3594: astore          55
        //  3596: goto            3646
        //  3599: new             Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //  3602: dup            
        //  3603: aload           9
        //  3605: aload           40
        //  3607: iconst_0       
        //  3608: iconst_0       
        //  3609: invokespecial   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;ZZ)V
        //  3612: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.build:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //  3615: astore          56
        //  3617: aload           4
        //  3619: aload           56
        //  3621: aload_0        
        //  3622: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.file:Lcom/intellij/psi/PsiFile;
        //  3625: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //  3628: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addSymbolReference:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)V
        //  3631: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  3634: dup            
        //  3635: aload           56
        //  3637: aload           51
        //  3639: aload           52
        //  3641: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;)V
        //  3644: astore          55
        //  3646: aload           29
        //  3648: ifnull          3674
        //  3651: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  3654: dup            
        //  3655: aload           29
        //  3657: aload           51
        //  3659: aload           52
        //  3661: iload           19
        //  3663: iload           20
        //  3665: iload           21
        //  3667: iload           22
        //  3669: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;ZZZZ)V
        //  3672: astore          55
        //  3674: aload           41
        //  3676: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  3679: ifeq            3699
        //  3682: aload           41
        //  3684: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  3687: aload           55
        //  3689: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.setType:(Lcom/jetbrains/cidr/lang/types/OCFunctionType;)V
        //  3692: goto            3724
        //  3695: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3698: athrow         
        //  3699: aload           41
        //  3701: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  3704: ifeq            3724
        //  3707: aload           41
        //  3709: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  3712: aload           55
        //  3714: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.setType:(Lcom/jetbrains/cidr/lang/types/OCFunctionType;)V
        //  3717: goto            3724
        //  3720: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3723: athrow         
        //  3724: goto            4201
        //  3727: aconst_null    
        //  3728: astore          45
        //  3730: aload_2        
        //  3731: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isTypedef:()Z
        //  3734: ifeq            3745
        //  3737: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  3740: astore          45
        //  3742: goto            3807
        //  3745: aload           37
        //  3747: ifnull          3807
        //  3750: getstatic       com/jetbrains/cidr/lang/symbols/OCBuilderDriver$1.$SwitchMap$com$jetbrains$cidr$lang$symbols$OCSymbolKind:[I
        //  3753: aload           37
        //  3755: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.ordinal:()I
        //  3758: iaload         
        //  3759: tableswitch {
        //                2: 3800
        //                3: 3800
        //                4: 3800
        //                5: 3800
        //                6: 3800
        //                7: 3800
        //          default: 3807
        //        }
        //  3796: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3799: athrow         
        //  3800: aload           37
        //  3802: astore          45
        //  3804: goto            3807
        //  3807: aload           45
        //  3809: ifnonnull       3854
        //  3812: aload           38
        //  3814: ifnull          3834
        //  3817: goto            3824
        //  3820: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3823: athrow         
        //  3824: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  3827: goto            3852
        //  3830: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3833: athrow         
        //  3834: iload           16
        //  3836: ifeq            3849
        //  3839: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  3842: goto            3852
        //  3845: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3848: athrow         
        //  3849: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  3852: astore          45
        //  3854: aload_0        
        //  3855: aload           38
        //  3857: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.h:(Ljava/lang/Object;)Lcom/intellij/openapi/util/TextRange;
        //  3860: astore          46
        //  3862: aload           4
        //  3864: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getTemplateParameters:()Ljava/util/List;
        //  3867: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  3872: astore          47
        //  3874: aload           47
        //  3876: invokeinterface java/util/Iterator.hasNext:()Z
        //  3881: ifeq            3914
        //  3884: aload           47
        //  3886: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3891: astore          48
        //  3893: aload_0        
        //  3894: aload           48
        //  3896: aload           43
        //  3898: aload           4
        //  3900: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/Object;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  3903: aload           43
        //  3905: checkcast       Ljava/util/ArrayList;
        //  3908: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  3911: goto            3874
        //  3914: aload           32
        //  3916: ifnull          3928
        //  3919: new             Ljava/util/ArrayList;
        //  3922: dup            
        //  3923: invokespecial   java/util/ArrayList.<init>:()V
        //  3926: astore          44
        //  3928: iload           16
        //  3930: ifeq            3946
        //  3933: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol$Property.IS_HAS_INITIALIZER:Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol$Property;
        //  3936: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol$Property.getMask:()I
        //  3939: goto            3947
        //  3942: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3945: athrow         
        //  3946: iconst_0       
        //  3947: istore          47
        //  3949: aload           4
        //  3951: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getModifiers:()I
        //  3954: istore          48
        //  3956: aload_2        
        //  3957: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isConst:()Z
        //  3960: ifeq            3974
        //  3963: iload           48
        //  3965: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //  3968: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.getMask:()I
        //  3971: ior            
        //  3972: istore          48
        //  3974: aload           4
        //  3976: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isLambdaInitCapture:()Z
        //  3979: ifeq            3993
        //  3982: iload           47
        //  3984: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol$Property.IS_DECLARED_IN_LAMBDA_CAPTURE:Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol$Property;
        //  3987: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol$Property.getMask:()I
        //  3990: ior            
        //  3991: istore          47
        //  3993: aload           30
        //  3995: ifnull          4013
        //  3998: aload_0        
        //  3999: aload           30
        //  4001: aload           4
        //  4003: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  4006: goto            4014
        //  4009: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4012: athrow         
        //  4013: aconst_null    
        //  4014: astore          49
        //  4016: aload           4
        //  4018: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isTemplateValueParameter:()Z
        //  4021: ifeq            4076
        //  4024: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //  4027: dup            
        //  4028: aload_0        
        //  4029: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  4032: aload_0        
        //  4033: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  4036: lload           6
        //  4038: aload           40
        //  4040: aload           42
        //  4042: aload           49
        //  4044: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  4047: aload           36
        //  4049: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  4052: aload_2        
        //  4053: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getArrayLengths:()[I
        //  4056: aload           43
        //  4058: aload           44
        //  4060: iload           47
        //  4062: iload           48
        //  4064: aload           46
        //  4066: aload           39
        //  4068: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;[ILjava/util/List;Ljava/util/List;IILcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //  4071: astore          41
        //  4073: goto            4123
        //  4076: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  4079: dup            
        //  4080: aload_0        
        //  4081: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  4084: aload_0        
        //  4085: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  4088: lload           6
        //  4090: aload           40
        //  4092: aload           42
        //  4094: aload           11
        //  4096: aload           36
        //  4098: aload           45
        //  4100: aload_2        
        //  4101: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.getArrayLengths:()[I
        //  4104: aload           49
        //  4106: aload           43
        //  4108: aload           44
        //  4110: iload           47
        //  4112: iload           48
        //  4114: aload           46
        //  4116: aload           39
        //  4118: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;[ILcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List;Ljava/util/List;IILcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //  4121: astore          41
        //  4123: aload           32
        //  4125: ifnull          4155
        //  4128: aload_0        
        //  4129: aload           41
        //  4131: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  4134: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.createDeclarationContext:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  4137: astore          50
        //  4139: aload           50
        //  4141: iconst_1       
        //  4142: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setInsideTemplateParams:(Z)V
        //  4145: aload_0        
        //  4146: aload           32
        //  4148: aload           44
        //  4150: aload           50
        //  4152: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  4155: aload           43
        //  4157: invokeinterface java/util/List.isEmpty:()Z
        //  4162: ifne            4201
        //  4165: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeParentSymbolSetter;
        //  4168: dup            
        //  4169: aload           41
        //  4171: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  4174: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeParentSymbolSetter.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //  4177: astore          50
        //  4179: aload           36
        //  4181: aload           50
        //  4183: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //  4186: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //  4189: astore          36
        //  4191: aload           41
        //  4193: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  4196: aload           36
        //  4198: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.setType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  4201: iconst_0       
        //  4202: istore          42
        //  4204: iload           42
        //  4206: aload           10
        //  4208: invokeinterface java/util/List.size:()I
        //  4213: if_icmpge       4351
        //  4216: aload           34
        //  4218: iload           42
        //  4220: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  4225: checkcast       Ljava/util/ArrayList;
        //  4228: astore          43
        //  4230: aload           33
        //  4232: iload           42
        //  4234: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  4239: checkcast       Ljava/util/ArrayList;
        //  4242: astore          44
        //  4244: aload           35
        //  4246: iload           42
        //  4248: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  4253: checkcast       Ljava/util/List;
        //  4256: astore          45
        //  4258: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  4261: dup            
        //  4262: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:()V
        //  4265: astore          46
        //  4267: aload           46
        //  4269: aload           4
        //  4271: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isAssumeNonNull:()Z
        //  4274: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setAssumeNonNull:(Z)V
        //  4277: aload           46
        //  4279: aload           41
        //  4281: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  4284: ifeq            4299
        //  4287: aload           41
        //  4289: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  4292: goto            4301
        //  4295: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4298: athrow         
        //  4299: aload           40
        //  4301: putfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.myParentSymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  4304: aload_0        
        //  4305: aload           10
        //  4307: aload           10
        //  4309: invokeinterface java/util/List.size:()I
        //  4314: iload           42
        //  4316: isub           
        //  4317: iconst_1       
        //  4318: isub           
        //  4319: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  4324: aload           43
        //  4326: aload           44
        //  4328: aload           46
        //  4330: aload           45
        //  4332: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/util/List;)V
        //  4335: aload           43
        //  4337: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  4340: aload           44
        //  4342: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  4345: iinc            42, 1
        //  4348: goto            4204
        //  4351: aload_3        
        //  4352: aload           41
        //  4354: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //  4359: pop            
        //  4360: aload           41
        //  4362: areturn        
        //    Signature:
        //  (TT;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext<TT;>;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    173    173    177    Ljava/lang/IllegalArgumentException;
        //  324    337    340    344    Ljava/lang/IllegalArgumentException;
        //  372    382    385    389    Ljava/lang/IllegalArgumentException;
        //  377    400    403    407    Ljava/lang/IllegalArgumentException;
        //  389    415    418    422    Ljava/lang/IllegalArgumentException;
        //  407    427    430    434    Ljava/lang/IllegalArgumentException;
        //  422    445    448    452    Ljava/lang/IllegalArgumentException;
        //  434    460    463    467    Ljava/lang/IllegalArgumentException;
        //  452    478    481    485    Ljava/lang/IllegalArgumentException;
        //  467    490    493    497    Ljava/lang/IllegalArgumentException;
        //  550    563    566    570    Ljava/lang/IllegalArgumentException;
        //  578    597    600    604    Ljava/lang/IllegalArgumentException;
        //  586    607    607    611    Ljava/lang/IllegalArgumentException;
        //  705    721    724    728    Ljava/lang/IllegalArgumentException;
        //  713    733    736    740    Ljava/lang/IllegalArgumentException;
        //  728    748    751    755    Ljava/lang/IllegalArgumentException;
        //  740    759    759    763    Ljava/lang/IllegalArgumentException;
        //  808    821    824    828    Ljava/lang/IllegalArgumentException;
        //  848    861    864    868    Ljava/lang/IllegalArgumentException;
        //  901    931    931    935    Ljava/lang/IllegalArgumentException;
        //  948    961    964    968    Ljava/lang/IllegalArgumentException;
        //  956    985    985    989    Ljava/lang/IllegalArgumentException;
        //  1000   1019   1019   1023   Ljava/lang/IllegalArgumentException;
        //  1037   1053   1056   1060   Ljava/lang/IllegalArgumentException;
        //  1048   1068   1071   1075   Ljava/lang/IllegalArgumentException;
        //  1133   1149   1152   1156   Ljava/lang/IllegalArgumentException;
        //  1175   1188   1191   1195   Ljava/lang/IllegalArgumentException;
        //  1210   1223   1226   1230   Ljava/lang/IllegalArgumentException;
        //  1245   1258   1261   1265   Ljava/lang/IllegalArgumentException;
        //  1280   1293   1296   1300   Ljava/lang/IllegalArgumentException;
        //  1331   1354   1354   1358   Ljava/lang/IllegalArgumentException;
        //  1406   1431   1434   1438   Ljava/lang/IllegalArgumentException;
        //  1441   1453   1456   1460   Ljava/lang/IllegalArgumentException;
        //  1505   1524   1524   1528   Ljava/lang/IllegalArgumentException;
        //  1528   1547   1547   1551   Ljava/lang/IllegalArgumentException;
        //  1579   1597   1600   1604   Ljava/lang/IllegalArgumentException;
        //  1590   1609   1612   1616   Ljava/lang/IllegalArgumentException;
        //  1624   1642   1645   1649   Ljava/lang/IllegalArgumentException;
        //  1709   1724   1724   1728   Ljava/lang/IllegalArgumentException;
        //  1734   1751   1754   1758   Ljava/lang/IllegalArgumentException;
        //  1739   1784   1787   1791   Ljava/lang/IllegalArgumentException;
        //  1758   1795   1795   1799   Ljava/lang/IllegalArgumentException;
        //  1808   1879   1882   1886   Ljava/lang/IllegalArgumentException;
        //  1820   1891   1894   1898   Ljava/lang/IllegalArgumentException;
        //  1886   1902   1902   1906   Ljava/lang/IllegalArgumentException;
        //  1966   1978   1981   1985   Ljava/lang/IllegalArgumentException;
        //  1997   2016   2019   2023   Ljava/lang/IllegalArgumentException;
        //  2043   2055   2058   2062   Ljava/lang/IllegalArgumentException;
        //  2092   2107   2110   2114   Ljava/lang/IllegalArgumentException;
        //  2183   2193   2196   2200   Ljava/lang/IllegalArgumentException;
        //  2256   2275   2278   2282   Ljava/lang/IllegalArgumentException;
        //  2308   2321   2324   2328   Ljava/lang/IllegalArgumentException;
        //  2410   2431   2434   2438   Ljava/lang/IllegalArgumentException;
        //  2421   2445   2448   2452   Ljava/lang/IllegalArgumentException;
        //  2438   2460   2463   2467   Ljava/lang/IllegalArgumentException;
        //  2470   2483   2486   2490   Ljava/lang/IllegalArgumentException;
        //  2478   2501   2504   2508   Ljava/lang/IllegalArgumentException;
        //  2490   2519   2522   2526   Ljava/lang/IllegalArgumentException;
        //  2643   2653   2656   2660   Ljava/lang/IllegalArgumentException;
        //  2648   2665   2668   2672   Ljava/lang/IllegalArgumentException;
        //  2660   2685   2688   2692   Ljava/lang/IllegalArgumentException;
        //  2672   2700   2703   2707   Ljava/lang/IllegalArgumentException;
        //  2692   2712   2715   2719   Ljava/lang/IllegalArgumentException;
        //  2707   2732   2735   2739   Ljava/lang/IllegalArgumentException;
        //  2719   2747   2750   2754   Ljava/lang/IllegalArgumentException;
        //  2739   2762   2765   2769   Ljava/lang/IllegalArgumentException;
        //  2754   2774   2777   2781   Ljava/lang/IllegalArgumentException;
        //  2769   2786   2789   2793   Ljava/lang/IllegalArgumentException;
        //  2781   2797   2797   2801   Ljava/lang/IllegalArgumentException;
        //  2804   2817   2820   2824   Ljava/lang/IllegalArgumentException;
        //  2809   2830   2830   2834   Ljava/lang/IllegalArgumentException;
        //  2842   2855   2855   2859   Ljava/lang/IllegalArgumentException;
        //  2925   2953   2956   2960   Ljava/lang/IllegalArgumentException;
        //  3007   3020   3023   3027   Ljava/lang/IllegalArgumentException;
        //  3015   3038   3038   3042   Ljava/lang/IllegalArgumentException;
        //  3285   3306   3309   3313   Ljava/lang/IllegalArgumentException;
        //  3316   3332   3335   3339   Ljava/lang/IllegalArgumentException;
        //  3418   3434   3437   3441   Ljava/lang/IllegalArgumentException;
        //  3426   3449   3452   3456   Ljava/lang/IllegalArgumentException;
        //  3495   3505   3508   3512   Ljava/lang/IllegalArgumentException;
        //  3531   3544   3547   3551   Ljava/lang/IllegalArgumentException;
        //  3539   3564   3567   3571   Ljava/lang/IllegalArgumentException;
        //  3674   3695   3695   3699   Ljava/lang/IllegalArgumentException;
        //  3699   3717   3720   3724   Ljava/lang/IllegalArgumentException;
        //  3745   3796   3796   3800   Ljava/lang/IllegalArgumentException;
        //  3807   3817   3820   3824   Ljava/lang/IllegalArgumentException;
        //  3812   3830   3830   3834   Ljava/lang/IllegalArgumentException;
        //  3834   3845   3845   3849   Ljava/lang/IllegalArgumentException;
        //  3928   3942   3942   3946   Ljava/lang/IllegalArgumentException;
        //  3993   4009   4009   4013   Ljava/lang/IllegalArgumentException;
        //  4267   4295   4295   4299   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0389:
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
    
    private TextRange h(final Object o) {
        try {
            if (o instanceof OCElement) {
                return ((OCElement)o).getTextRange();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o instanceof CompositeElement) {
                return ((CompositeElement)o).getTextRange();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o instanceof PsiBuilder.Marker) {
                return new TextRange(this.nodeStructure.getStartOffset(o), this.nodeStructure.getEndOffset(o));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @NotNull
    private OCAutoType a(@NotNull final T t, @Nullable final OCType ocType, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createAutoType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createAutoType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCExpression ocExpression = null;
        if (t instanceof CompositeElement) {
            final PsiElement psi = ((CompositeElement)t).getPsi();
            if (psi instanceof OCExpression) {
                ocExpression = (OCExpression)psi;
            }
        }
        OCAutoType ocAutoType;
        try {
            ocAutoType = new OCAutoType(this.getExpressionSymbol(t, declarationContext), ocExpression, ocType, false, false);
            if (ocAutoType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createAutoType"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return ocAutoType;
    }
    
    private int a(@NotNull final T t, final boolean b) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "evaluateArrayLengths"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = 0;
        for (Object next : this.getChildren(t)) {
            while (true) {
                try {
                    if (!b || this.type((T)next) != OCElementTypes.PAREN_EXPRESSION) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                final Object f = this.f(next);
                try {
                    if (f == null) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                next = f;
            }
            final IElementType type = this.type((T)next);
            Label_0222: {
                try {
                    if (!b || type != OCElementTypes.LITERAL_EXPRESSION) {
                        break Label_0222;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                final Iterator<T> iterator2 = this.getChildren((T)next).iterator();
                if (iterator2.hasNext()) {
                    final T next2 = iterator2.next();
                    if (OCTokenTypes.ALL_STRINGS.contains(this.type(next2))) {
                        return OCStringLiteralUtil.parseStringLiteral(this.nodeText(next2)).getContentLengthInCharacters();
                    }
                }
                try {
                    if (!OCElementTypes.EXPRESSIONS.contains(type)) {
                        continue;
                    }
                    ++n;
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
        }
        return n;
    }
    
    @NotNull
    public List<String> processAttributeList(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processAttributeList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ArrayList<String> list = new ArrayList<String>();
        for (final T next : this.getChildren(t)) {
            if (this.type(next) == OCElementTypes.ATTRIBUTE) {
                String nodeText = "";
                for (final T next2 : this.getChildren(next)) {
                    if (this.type(next2) == OCTokenTypes.IDENTIFIER) {
                        nodeText = this.nodeText(next2);
                        list.add(nodeText);
                    }
                    try {
                        if (this.type(next2) != OCElementTypes.ATTRIBUTE_PARAMETERS) {
                            continue;
                        }
                        list.add(nodeText + "#" + this.nodeText(next2));
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
            }
        }
        ArrayList<String> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processAttributeList"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return list2;
    }
    
    @Nullable
    private Object e(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exprNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "getIntegerConstValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            Label_0107: {
                try {
                    if (OCTokenTypes.LITERALS.contains(type)) {
                        break Label_0107;
                    }
                    final TokenSet set = OCTokenTypes.CPP_LITERALS;
                    final IElementType elementType = type;
                    final boolean b = set.contains(elementType);
                    if (b) {
                        break Label_0107;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final TokenSet set = OCTokenTypes.CPP_LITERALS;
                    final IElementType elementType = type;
                    final boolean b = set.contains(elementType);
                    if (b) {
                        return OCElementUtil.getConstValue(type, this.nodeText(next), (PsiElement)this.file, this.context);
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
        }
        return null;
    }
    
    @Nullable
    private T f(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parenExpr", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "getParenSubexpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (this.type(t) == OCElementTypes.PAREN_EXPRESSION) {
            for (final T next : this.getChildren(t)) {
                try {
                    if (OCElementTypes.EXPRESSIONS.contains(this.type(next))) {
                        return next;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        return null;
    }
    
    @NotNull
    public OCExpressionSymbol getExpressionSymbol(@NotNull final T t, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "getExpressionSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "getExpressionSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCExpressionSymbol a = this.a(t, declarationContext);
        OCExpressionSymbol ocExpressionSymbol = null;
        Label_0132: {
            try {
                if (a != null) {
                    final OCExpressionSymbol ocExpressionSymbol2;
                    ocExpressionSymbol = (ocExpressionSymbol2 = a);
                    break Label_0132;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            OCExpressionSymbol ocExpressionSymbol2;
            ocExpressionSymbol = (ocExpressionSymbol2 = new OCUnknownExpressionSymbol(this.project, this.myVirtualFile, this.getComplexOffsetFromNode(t), this.nodeText(t)));
            try {
                if (ocExpressionSymbol2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "getExpressionSymbol"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return ocExpressionSymbol;
    }
    
    @Nullable
    private OCExpressionSymbol a(@NotNull final T p0, @NotNull final DeclarationContext<T> p1) {
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
        //    18: ldc             "expr"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processExpression"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCBuilderDriver"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processExpression"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //    93: astore_3       
        //    94: aload_3        
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BINARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    98: if_acmpne       319
        //   101: aconst_null    
        //   102: astore          4
        //   104: aconst_null    
        //   105: astore          5
        //   107: aconst_null    
        //   108: astore          6
        //   110: aload_0        
        //   111: aload_1        
        //   112: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   115: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   120: astore          7
        //   122: aload           7
        //   124: invokeinterface java/util/Iterator.hasNext:()Z
        //   129: ifeq            207
        //   132: aload           7
        //   134: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   139: astore          8
        //   141: aload_0        
        //   142: aload           8
        //   144: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   147: astore          9
        //   149: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //   152: aload           9
        //   154: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   157: ifeq            186
        //   160: aload           4
        //   162: ifnonnull       179
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload           8
        //   174: astore          4
        //   176: goto            204
        //   179: aload           8
        //   181: astore          5
        //   183: goto            204
        //   186: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BINARY_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   189: aload           9
        //   191: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   194: ifeq            204
        //   197: aload           9
        //   199: checkcast       Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   202: astore          6
        //   204: goto            122
        //   207: aload           4
        //   209: ifnull          226
        //   212: aload_0        
        //   213: aload           4
        //   215: aload_2        
        //   216: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   219: goto            227
        //   222: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aconst_null    
        //   227: astore          7
        //   229: aload           5
        //   231: ifnull          248
        //   234: aload_0        
        //   235: aload           5
        //   237: aload_2        
        //   238: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   241: goto            249
        //   244: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aconst_null    
        //   249: astore          8
        //   251: aload           6
        //   253: ifnull          316
        //   256: aload           7
        //   258: ifnull          316
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload           8
        //   270: ifnull          316
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol;
        //   283: dup            
        //   284: aload_0        
        //   285: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   288: aload_0        
        //   289: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   292: aload_0        
        //   293: aload_1        
        //   294: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   297: aload_0        
        //   298: aload_1        
        //   299: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   302: aload           6
        //   304: aload           7
        //   306: aload           8
        //   308: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   311: areturn        
        //   312: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: goto            3357
        //   319: aload_3        
        //   320: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.UNARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   323: if_acmpeq       340
        //   326: aload_3        
        //   327: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_UDL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   330: if_acmpne       512
        //   333: goto            340
        //   336: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: aconst_null    
        //   341: astore          4
        //   343: aconst_null    
        //   344: astore          5
        //   346: aload_0        
        //   347: aload_1        
        //   348: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   351: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   356: astore          6
        //   358: aload           6
        //   360: invokeinterface java/util/Iterator.hasNext:()Z
        //   365: ifeq            436
        //   368: aload           6
        //   370: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   375: astore          7
        //   377: aload_0        
        //   378: aload           7
        //   380: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   383: astore          8
        //   385: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //   388: aload           8
        //   390: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   393: ifeq            415
        //   396: aload           4
        //   398: ifnonnull       433
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aload           7
        //   410: astore          4
        //   412: goto            433
        //   415: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNARY_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   418: aload           8
        //   420: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   423: ifeq            433
        //   426: aload           8
        //   428: checkcast       Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   431: astore          5
        //   433: goto            358
        //   436: aload           4
        //   438: ifnull          455
        //   441: aload_0        
        //   442: aload           4
        //   444: aload_2        
        //   445: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   448: goto            456
        //   451: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   454: athrow         
        //   455: aconst_null    
        //   456: astore          6
        //   458: aload           5
        //   460: ifnull          509
        //   463: aload           6
        //   465: ifnull          509
        //   468: goto            475
        //   471: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   474: athrow         
        //   475: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbol;
        //   478: dup            
        //   479: aload_0        
        //   480: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   483: aload_0        
        //   484: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   487: aload_0        
        //   488: aload_1        
        //   489: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   492: aload_0        
        //   493: aload_1        
        //   494: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   497: aload           5
        //   499: aload           6
        //   501: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   504: areturn        
        //   505: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   508: athrow         
        //   509: goto            3357
        //   512: aload_3        
        //   513: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PREFIX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   516: if_acmpeq       533
        //   519: aload_3        
        //   520: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.POSTFIX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   523: if_acmpne       751
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aconst_null    
        //   534: astore          4
        //   536: aconst_null    
        //   537: astore          5
        //   539: aload_0        
        //   540: aload_1        
        //   541: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   544: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   549: astore          6
        //   551: aload           6
        //   553: invokeinterface java/util/Iterator.hasNext:()Z
        //   558: ifeq            629
        //   561: aload           6
        //   563: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   568: astore          7
        //   570: aload_0        
        //   571: aload           7
        //   573: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   576: astore          8
        //   578: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //   581: aload           8
        //   583: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   586: ifeq            608
        //   589: aload           4
        //   591: ifnonnull       626
        //   594: goto            601
        //   597: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   600: athrow         
        //   601: aload           7
        //   603: astore          4
        //   605: goto            626
        //   608: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ARITHMETIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   611: aload           8
        //   613: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   616: ifeq            626
        //   619: aload           8
        //   621: checkcast       Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   624: astore          5
        //   626: goto            551
        //   629: aload           4
        //   631: ifnull          648
        //   634: aload_0        
        //   635: aload           4
        //   637: aload_2        
        //   638: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   641: goto            649
        //   644: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   647: athrow         
        //   648: aconst_null    
        //   649: astore          6
        //   651: aload           5
        //   653: ifnull          748
        //   656: aload           6
        //   658: ifnull          748
        //   661: goto            668
        //   664: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   667: athrow         
        //   668: aload_3        
        //   669: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PREFIX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   672: if_acmpne       718
        //   675: goto            682
        //   678: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   681: athrow         
        //   682: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCPrefixExpressionSymbol;
        //   685: dup            
        //   686: aload_0        
        //   687: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   690: aload_0        
        //   691: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   694: aload_0        
        //   695: aload_1        
        //   696: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   699: aload_0        
        //   700: aload_1        
        //   701: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   704: aload           5
        //   706: aload           6
        //   708: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCPrefixExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   711: goto            747
        //   714: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   717: athrow         
        //   718: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCPostfixExpressionSymbol;
        //   721: dup            
        //   722: aload_0        
        //   723: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   726: aload_0        
        //   727: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   730: aload_0        
        //   731: aload_1        
        //   732: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   735: aload_0        
        //   736: aload_1        
        //   737: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   740: aload           5
        //   742: aload           6
        //   744: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCPostfixExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   747: areturn        
        //   748: goto            3357
        //   751: aload_3        
        //   752: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CONDITIONAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   755: if_acmpne       989
        //   758: aconst_null    
        //   759: astore          4
        //   761: aconst_null    
        //   762: astore          5
        //   764: aconst_null    
        //   765: astore          6
        //   767: aload_0        
        //   768: aload_1        
        //   769: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   772: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   777: astore          7
        //   779: aload           7
        //   781: invokeinterface java/util/Iterator.hasNext:()Z
        //   786: ifeq            855
        //   789: aload           7
        //   791: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   796: astore          8
        //   798: aload_0        
        //   799: aload           8
        //   801: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //   804: astore          9
        //   806: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //   809: aload           9
        //   811: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   814: ifeq            852
        //   817: aload           4
        //   819: ifnonnull       836
        //   822: goto            829
        //   825: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   828: athrow         
        //   829: aload           8
        //   831: astore          4
        //   833: goto            852
        //   836: aload           5
        //   838: ifnonnull       848
        //   841: aload           8
        //   843: astore          5
        //   845: goto            852
        //   848: aload           8
        //   850: astore          6
        //   852: goto            779
        //   855: aload           4
        //   857: ifnull          874
        //   860: aload_0        
        //   861: aload           4
        //   863: aload_2        
        //   864: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   867: goto            875
        //   870: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   873: athrow         
        //   874: aconst_null    
        //   875: astore          7
        //   877: aload           5
        //   879: ifnull          896
        //   882: aload_0        
        //   883: aload           5
        //   885: aload_2        
        //   886: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   889: goto            897
        //   892: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   895: athrow         
        //   896: aconst_null    
        //   897: astore          8
        //   899: aload           6
        //   901: ifnull          918
        //   904: aload_0        
        //   905: aload           6
        //   907: aload_2        
        //   908: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   911: goto            919
        //   914: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   917: athrow         
        //   918: aconst_null    
        //   919: astore          9
        //   921: aload           7
        //   923: ifnull          986
        //   926: aload           8
        //   928: ifnull          986
        //   931: goto            938
        //   934: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   937: athrow         
        //   938: aload           9
        //   940: ifnull          986
        //   943: goto            950
        //   946: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   949: athrow         
        //   950: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol;
        //   953: dup            
        //   954: aload_0        
        //   955: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //   958: aload_0        
        //   959: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   962: aload_0        
        //   963: aload_1        
        //   964: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //   967: aload_0        
        //   968: aload_1        
        //   969: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   972: aload           7
        //   974: aload           8
        //   976: aload           9
        //   978: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   981: areturn        
        //   982: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   985: athrow         
        //   986: goto            3357
        //   989: aload_3        
        //   990: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LITERAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   993: if_acmpne       1092
        //   996: aload_0        
        //   997: aload_1        
        //   998: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.e:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1001: astore          4
        //  1003: aload           4
        //  1005: ifnull          1089
        //  1008: aload_0        
        //  1009: aload_1        
        //  1010: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1013: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1018: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1023: astore          5
        //  1025: aload_0        
        //  1026: aload           5
        //  1028: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1031: astore          6
        //  1033: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //  1036: aload           6
        //  1038: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1041: ifne            1062
        //  1044: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CPP_LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //  1047: aload           6
        //  1049: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1052: ifeq            1089
        //  1055: goto            1062
        //  1058: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1061: athrow         
        //  1062: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol;
        //  1065: dup            
        //  1066: aload_0        
        //  1067: aload_1        
        //  1068: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1071: aload           4
        //  1073: aload           6
        //  1075: aload_0        
        //  1076: aload           5
        //  1078: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1081: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol.<init>:(Ljava/lang/String;Ljava/lang/Object;Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //  1084: areturn        
        //  1085: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1088: athrow         
        //  1089: goto            3357
        //  1092: aload_3        
        //  1093: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.REFERENCE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1096: if_acmpne       1201
        //  1099: aload_0        
        //  1100: aload_1        
        //  1101: aconst_null    
        //  1102: aload_2        
        //  1103: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1106: astore          4
        //  1108: aload_2        
        //  1109: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLocalContext:()Lcom/intellij/psi/PsiElement;
        //  1112: ifnull          1131
        //  1115: aload           4
        //  1117: aload_2        
        //  1118: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLocalContext:()Lcom/intellij/psi/PsiElement;
        //  1121: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
        //  1124: goto            1140
        //  1127: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1130: athrow         
        //  1131: aload           4
        //  1133: aload_2        
        //  1134: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1137: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getGlobalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //  1140: astore          5
        //  1142: aload_2        
        //  1143: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLambda:()Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //  1146: ifnull          1167
        //  1149: aload           4
        //  1151: aload_0        
        //  1152: aload_1        
        //  1153: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1156: aload_2        
        //  1157: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLambda:()Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //  1160: aload           5
        //  1162: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLambdaLocalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;JLcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference;
        //  1165: astore          5
        //  1167: aload_2        
        //  1168: aload           5
        //  1170: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addSymbolReference:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)V
        //  1173: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //  1176: dup            
        //  1177: aload_0        
        //  1178: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1181: aload_0        
        //  1182: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1185: aload_0        
        //  1186: aload_1        
        //  1187: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1190: aload_0        
        //  1191: aload_1        
        //  1192: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1195: aload           5
        //  1197: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)V
        //  1200: areturn        
        //  1201: aload_3        
        //  1202: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.QUALIFIED_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1205: if_acmpne       1502
        //  1208: aconst_null    
        //  1209: astore          4
        //  1211: aconst_null    
        //  1212: astore          5
        //  1214: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1217: astore          6
        //  1219: iconst_0       
        //  1220: istore          7
        //  1222: aload_0        
        //  1223: aload_1        
        //  1224: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1227: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1232: astore          8
        //  1234: aload           8
        //  1236: invokeinterface java/util/Iterator.hasNext:()Z
        //  1241: ifeq            1444
        //  1244: aload           8
        //  1246: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1251: astore          9
        //  1253: aload_0        
        //  1254: aload           9
        //  1256: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1259: astore          10
        //  1261: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1264: aload           10
        //  1266: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1269: ifeq            1281
        //  1272: aload_0        
        //  1273: aload           9
        //  1275: aload_2        
        //  1276: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  1279: astore          4
        //  1281: aload           10
        //  1283: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TILDE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1286: if_acmpne       1296
        //  1289: ldc             "~"
        //  1291: astore          5
        //  1293: goto            1441
        //  1296: aload           10
        //  1298: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1301: if_acmpne       1354
        //  1304: new             Ljava/lang/StringBuilder;
        //  1307: dup            
        //  1308: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1311: aload           5
        //  1313: ifnull          1332
        //  1316: goto            1323
        //  1319: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1322: athrow         
        //  1323: aload           5
        //  1325: goto            1334
        //  1328: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1331: athrow         
        //  1332: ldc             ""
        //  1334: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1337: aload_0        
        //  1338: aload           9
        //  1340: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1346: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1349: astore          5
        //  1351: goto            1441
        //  1354: aload           10
        //  1356: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.QUALIFIED_EXPRESSION_ACCESSOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1359: if_acmpne       1415
        //  1362: aload_0        
        //  1363: aload           9
        //  1365: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1368: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1373: astore          11
        //  1375: aload           11
        //  1377: invokeinterface java/util/Iterator.hasNext:()Z
        //  1382: ifeq            1412
        //  1385: aload           11
        //  1387: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1392: astore          12
        //  1394: aload_0        
        //  1395: aload           12
        //  1397: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1400: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1403: if_acmpne       1409
        //  1406: iconst_1       
        //  1407: istore          7
        //  1409: goto            1375
        //  1412: goto            1441
        //  1415: aload           10
        //  1417: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1420: if_acmpne       1441
        //  1423: new             Ljava/util/ArrayList;
        //  1426: dup            
        //  1427: invokespecial   java/util/ArrayList.<init>:()V
        //  1430: astore          6
        //  1432: aload_0        
        //  1433: aload           9
        //  1435: aload           6
        //  1437: aload_2        
        //  1438: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)V
        //  1441: goto            1234
        //  1444: aload           4
        //  1446: ifnull          1499
        //  1449: aload           5
        //  1451: ifnull          1499
        //  1454: goto            1461
        //  1457: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1460: athrow         
        //  1461: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol;
        //  1464: dup            
        //  1465: aload_0        
        //  1466: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1469: aload_0        
        //  1470: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1473: aload_0        
        //  1474: aload_1        
        //  1475: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1478: aload_0        
        //  1479: aload_1        
        //  1480: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1483: aload           4
        //  1485: aload           5
        //  1487: iload           7
        //  1489: aload           6
        //  1491: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/lang/String;ZLjava/util/List;)V
        //  1494: areturn        
        //  1495: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1498: athrow         
        //  1499: goto            3357
        //  1502: aload_3        
        //  1503: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SIZEOF_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1506: if_acmpne       1684
        //  1509: iconst_0       
        //  1510: istore          4
        //  1512: aload_0        
        //  1513: aload_1        
        //  1514: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1517: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1522: astore          5
        //  1524: aload           5
        //  1526: invokeinterface java/util/Iterator.hasNext:()Z
        //  1531: ifeq            1681
        //  1534: aload           5
        //  1536: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1541: astore          6
        //  1543: aload_0        
        //  1544: aload           6
        //  1546: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1549: astore          7
        //  1551: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1554: aload           7
        //  1556: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1559: ifeq            1614
        //  1562: aload_0        
        //  1563: aload           6
        //  1565: aload_2        
        //  1566: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  1569: astore          8
        //  1571: aload           8
        //  1573: ifnull          1612
        //  1576: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol;
        //  1579: dup            
        //  1580: aload_0        
        //  1581: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1584: aload_0        
        //  1585: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1588: aload_0        
        //  1589: aload_1        
        //  1590: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1593: aload_0        
        //  1594: aload_1        
        //  1595: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1598: aload           8
        //  1600: iload           4
        //  1602: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Z)V
        //  1605: goto            1613
        //  1608: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1611: athrow         
        //  1612: aconst_null    
        //  1613: areturn        
        //  1614: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1617: aload           7
        //  1619: if_acmpne       1667
        //  1622: aload_0        
        //  1623: aload           6
        //  1625: aload_0        
        //  1626: aload_2        
        //  1627: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //  1630: aconst_null    
        //  1631: aload_2        
        //  1632: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1635: astore          8
        //  1637: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol;
        //  1640: dup            
        //  1641: aload_0        
        //  1642: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1645: aload_0        
        //  1646: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1649: aload_0        
        //  1650: aload_1        
        //  1651: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1654: aload_0        
        //  1655: aload_1        
        //  1656: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1659: aload           8
        //  1661: iload           4
        //  1663: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //  1666: areturn        
        //  1667: aload           7
        //  1669: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELLIPSIS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1672: if_acmpne       1678
        //  1675: iconst_1       
        //  1676: istore          4
        //  1678: goto            1524
        //  1681: goto            3357
        //  1684: aload_3        
        //  1685: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CALL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1688: if_acmpne       1951
        //  1691: aconst_null    
        //  1692: astore          4
        //  1694: new             Ljava/util/ArrayList;
        //  1697: dup            
        //  1698: invokespecial   java/util/ArrayList.<init>:()V
        //  1701: astore          5
        //  1703: aload_0        
        //  1704: aload_1        
        //  1705: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1708: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1713: astore          6
        //  1715: aload           6
        //  1717: invokeinterface java/util/Iterator.hasNext:()Z
        //  1722: ifeq            1859
        //  1725: aload           6
        //  1727: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1732: astore          7
        //  1734: aload_0        
        //  1735: aload           7
        //  1737: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1740: astore          8
        //  1742: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1745: aload           8
        //  1747: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1750: ifeq            1765
        //  1753: aload_0        
        //  1754: aload           7
        //  1756: aload_2        
        //  1757: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  1760: astore          4
        //  1762: goto            1856
        //  1765: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1768: aload           8
        //  1770: if_acmpne       1856
        //  1773: aload_0        
        //  1774: aload           7
        //  1776: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1779: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1784: astore          9
        //  1786: aload           9
        //  1788: invokeinterface java/util/Iterator.hasNext:()Z
        //  1793: ifeq            1856
        //  1796: aload           9
        //  1798: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1803: astore          10
        //  1805: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1808: aload_0        
        //  1809: aload           10
        //  1811: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  1814: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1817: ifeq            1853
        //  1820: aload_0        
        //  1821: aload           10
        //  1823: aload_2        
        //  1824: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  1827: astore          11
        //  1829: aload           11
        //  1831: ifnull          1851
        //  1834: aload           5
        //  1836: aload           11
        //  1838: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1843: pop            
        //  1844: goto            1853
        //  1847: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1850: athrow         
        //  1851: aconst_null    
        //  1852: areturn        
        //  1853: goto            1786
        //  1856: goto            1715
        //  1859: aload           4
        //  1861: ifnull          1948
        //  1864: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //  1867: dup            
        //  1868: aload_0        
        //  1869: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  1872: aload_0        
        //  1873: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  1876: aload_0        
        //  1877: aload_1        
        //  1878: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  1881: aload_0        
        //  1882: aload_1        
        //  1883: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  1886: aload           4
        //  1888: aload           5
        //  1890: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List;)V
        //  1893: astore          6
        //  1895: aload           4
        //  1897: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //  1900: ifeq            1920
        //  1903: aload           4
        //  1905: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //  1908: aload           6
        //  1910: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.setParent:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //  1913: goto            1945
        //  1916: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1919: athrow         
        //  1920: aload           4
        //  1922: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol;
        //  1925: ifeq            1945
        //  1928: aload           4
        //  1930: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol;
        //  1933: aload           6
        //  1935: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.setParent:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //  1938: goto            1945
        //  1941: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1944: athrow         
        //  1945: aload           6
        //  1947: areturn        
        //  1948: goto            3357
        //  1951: aload_3        
        //  1952: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARRAY_INDEX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1955: if_acmpne       2104
        //  1958: aconst_null    
        //  1959: astore          4
        //  1961: aconst_null    
        //  1962: astore          5
        //  1964: aload_0        
        //  1965: aload_1        
        //  1966: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  1969: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1974: astore          6
        //  1976: aload           6
        //  1978: invokeinterface java/util/Iterator.hasNext:()Z
        //  1983: ifeq            2050
        //  1986: aload           6
        //  1988: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1993: astore          7
        //  1995: aload_0        
        //  1996: aload           7
        //  1998: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  2001: astore          8
        //  2003: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  2006: aload           8
        //  2008: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2011: ifeq            2047
        //  2014: aload           4
        //  2016: ifnonnull       2038
        //  2019: goto            2026
        //  2022: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2025: athrow         
        //  2026: aload_0        
        //  2027: aload           7
        //  2029: aload_2        
        //  2030: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2033: astore          4
        //  2035: goto            2047
        //  2038: aload_0        
        //  2039: aload           7
        //  2041: aload_2        
        //  2042: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2045: astore          5
        //  2047: goto            1976
        //  2050: aload           4
        //  2052: ifnull          2101
        //  2055: aload           5
        //  2057: ifnull          2101
        //  2060: goto            2067
        //  2063: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2066: athrow         
        //  2067: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol;
        //  2070: dup            
        //  2071: aload_0        
        //  2072: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2075: aload_0        
        //  2076: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2079: aload_0        
        //  2080: aload_1        
        //  2081: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  2084: aload_0        
        //  2085: aload_1        
        //  2086: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  2089: aload           4
        //  2091: aload           5
        //  2093: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //  2096: areturn        
        //  2097: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2100: athrow         
        //  2101: goto            3357
        //  2104: aload_3        
        //  2105: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PAREN_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2108: if_acmpne       2138
        //  2111: aload_0        
        //  2112: aload_1        
        //  2113: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.f:(Ljava/lang/Object;)Ljava/lang/Object;
        //  2116: astore          4
        //  2118: aload           4
        //  2120: ifnull          2135
        //  2123: aload_0        
        //  2124: aload           4
        //  2126: aload_2        
        //  2127: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2130: areturn        
        //  2131: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2134: athrow         
        //  2135: goto            3357
        //  2138: aload_3        
        //  2139: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CAST_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2142: if_acmpne       2355
        //  2145: aconst_null    
        //  2146: astore          4
        //  2148: aconst_null    
        //  2149: astore          5
        //  2151: aload_0        
        //  2152: aload_1        
        //  2153: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  2156: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  2161: astore          6
        //  2163: aload           6
        //  2165: invokeinterface java/util/Iterator.hasNext:()Z
        //  2170: ifeq            2313
        //  2173: aload           6
        //  2175: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2180: astore          7
        //  2182: aload_0        
        //  2183: aload           7
        //  2185: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  2188: astore          8
        //  2190: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  2193: aload           8
        //  2195: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2198: ifeq            2213
        //  2201: aload_0        
        //  2202: aload           7
        //  2204: aload_2        
        //  2205: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2208: astore          4
        //  2210: goto            2310
        //  2213: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2216: aload           8
        //  2218: if_acmpne       2239
        //  2221: aload_0        
        //  2222: aload           7
        //  2224: aload_0        
        //  2225: aload_2        
        //  2226: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //  2229: aconst_null    
        //  2230: aload_2        
        //  2231: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  2234: astore          5
        //  2236: goto            2310
        //  2239: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2242: aload           8
        //  2244: if_acmpne       2310
        //  2247: aload_0        
        //  2248: aload           7
        //  2250: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  2253: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  2258: astore          9
        //  2260: aload           9
        //  2262: invokeinterface java/util/Iterator.hasNext:()Z
        //  2267: ifeq            2310
        //  2270: aload           9
        //  2272: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2277: astore          10
        //  2279: aload_0        
        //  2280: aload           10
        //  2282: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  2285: astore          11
        //  2287: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  2290: aload           11
        //  2292: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2295: ifeq            2307
        //  2298: aload_0        
        //  2299: aload           10
        //  2301: aload_2        
        //  2302: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2305: astore          4
        //  2307: goto            2260
        //  2310: goto            2163
        //  2313: aload           5
        //  2315: ifnull          2352
        //  2318: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCCastExpressionSymbol;
        //  2321: dup            
        //  2322: aload_0        
        //  2323: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2326: aload_0        
        //  2327: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2330: aload_0        
        //  2331: aload_1        
        //  2332: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  2335: aload_0        
        //  2336: aload_1        
        //  2337: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  2340: aload           4
        //  2342: aload           5
        //  2344: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCCastExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  2347: areturn        
        //  2348: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2351: athrow         
        //  2352: goto            3357
        //  2355: aload_3        
        //  2356: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPOUND_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2359: if_acmpne       2474
        //  2362: new             Ljava/util/ArrayList;
        //  2365: dup            
        //  2366: invokespecial   java/util/ArrayList.<init>:()V
        //  2369: astore          4
        //  2371: aload_0        
        //  2372: aload_1        
        //  2373: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  2376: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  2381: astore          5
        //  2383: aload           5
        //  2385: invokeinterface java/util/Iterator.hasNext:()Z
        //  2390: ifeq            2446
        //  2393: aload           5
        //  2395: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2400: astore          6
        //  2402: aload_0        
        //  2403: aload           6
        //  2405: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  2408: astore          7
        //  2410: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  2413: aload           7
        //  2415: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2418: ifeq            2443
        //  2421: aload           4
        //  2423: aload_0        
        //  2424: aload           6
        //  2426: aload_2        
        //  2427: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getExpressionSymbol:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2430: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  2435: pop            
        //  2436: goto            2443
        //  2439: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2442: athrow         
        //  2443: goto            2383
        //  2446: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol;
        //  2449: dup            
        //  2450: aload_0        
        //  2451: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2454: aload_0        
        //  2455: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2458: aload_0        
        //  2459: aload_1        
        //  2460: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  2463: aload_0        
        //  2464: aload_1        
        //  2465: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  2468: aload           4
        //  2470: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;)V
        //  2473: areturn        
        //  2474: aload_3        
        //  2475: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMMA_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2478: if_acmpne       2631
        //  2481: new             Ljava/util/ArrayList;
        //  2484: dup            
        //  2485: invokespecial   java/util/ArrayList.<init>:()V
        //  2488: astore          4
        //  2490: aload_0        
        //  2491: aload_1        
        //  2492: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  2495: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  2500: astore          5
        //  2502: aload           5
        //  2504: invokeinterface java/util/Iterator.hasNext:()Z
        //  2509: ifeq            2565
        //  2512: aload           5
        //  2514: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2519: astore          6
        //  2521: aload_0        
        //  2522: aload           6
        //  2524: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  2527: astore          7
        //  2529: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  2532: aload           7
        //  2534: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2537: ifeq            2562
        //  2540: aload           4
        //  2542: aload_0        
        //  2543: aload           6
        //  2545: aload_2        
        //  2546: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getExpressionSymbol:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2549: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  2554: pop            
        //  2555: goto            2562
        //  2558: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2561: athrow         
        //  2562: goto            2502
        //  2565: aload           4
        //  2567: invokeinterface java/util/List.size:()I
        //  2572: iconst_2       
        //  2573: if_icmpne       2628
        //  2576: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol;
        //  2579: dup            
        //  2580: aload_0        
        //  2581: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2584: aload_0        
        //  2585: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2588: aload_0        
        //  2589: aload_1        
        //  2590: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  2593: aload_0        
        //  2594: aload_1        
        //  2595: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  2598: aload           4
        //  2600: iconst_0       
        //  2601: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2606: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2609: aload           4
        //  2611: iconst_1       
        //  2612: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2617: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  2620: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //  2623: areturn        
        //  2624: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2627: athrow         
        //  2628: goto            3357
        //  2631: aload_3        
        //  2632: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2635: if_acmpne       2973
        //  2638: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  2641: astore          4
        //  2643: aconst_null    
        //  2644: astore          5
        //  2646: aconst_null    
        //  2647: astore          6
        //  2649: new             Ljava/util/ArrayList;
        //  2652: dup            
        //  2653: invokespecial   java/util/ArrayList.<init>:()V
        //  2656: astore          7
        //  2658: new             Ljava/util/ArrayList;
        //  2661: dup            
        //  2662: invokespecial   java/util/ArrayList.<init>:()V
        //  2665: astore          8
        //  2667: aload_0        
        //  2668: aload_1        
        //  2669: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  2672: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  2677: astore          9
        //  2679: aload           9
        //  2681: invokeinterface java/util/Iterator.hasNext:()Z
        //  2686: ifeq            2925
        //  2689: aload           9
        //  2691: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2696: astore          10
        //  2698: aload_0        
        //  2699: aload           10
        //  2701: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  2704: astore          11
        //  2706: aload           11
        //  2708: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2711: if_acmpne       2748
        //  2714: new             Ljava/util/ArrayList;
        //  2717: dup            
        //  2718: invokespecial   java/util/ArrayList.<init>:()V
        //  2721: astore          4
        //  2723: aload_0        
        //  2724: aload           10
        //  2726: aload           8
        //  2728: aload           4
        //  2730: aload_2        
        //  2731: aconst_null    
        //  2732: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/util/List;)V
        //  2735: aload           7
        //  2737: aload           8
        //  2739: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  2744: pop            
        //  2745: goto            2922
        //  2748: aload           11
        //  2750: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2753: if_acmpne       2774
        //  2756: aload_0        
        //  2757: aload           10
        //  2759: aload_0        
        //  2760: aload_2        
        //  2761: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //  2764: aconst_null    
        //  2765: aload_2        
        //  2766: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  2769: astore          5
        //  2771: goto            2922
        //  2774: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //  2777: aload           11
        //  2779: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2782: ifeq            2922
        //  2785: aload           5
        //  2787: ifnull          2812
        //  2790: goto            2797
        //  2793: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2796: athrow         
        //  2797: aload           5
        //  2799: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //  2802: ifeq            2922
        //  2805: goto            2812
        //  2808: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2811: athrow         
        //  2812: new             Ljava/util/ArrayList;
        //  2815: dup            
        //  2816: invokespecial   java/util/ArrayList.<init>:()V
        //  2819: astore          12
        //  2821: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //  2824: dup            
        //  2825: aload_0        
        //  2826: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2829: aload_0        
        //  2830: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2833: aload_0        
        //  2834: aload_1        
        //  2835: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  2838: aload_0        
        //  2839: aload_1        
        //  2840: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  2843: aload           12
        //  2845: aload           8
        //  2847: aload           7
        //  2849: aload           4
        //  2851: aconst_null    
        //  2852: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  2855: astore          6
        //  2857: new             Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  2860: dup            
        //  2861: aconst_null    
        //  2862: aload           10
        //  2864: aload_2        
        //  2865: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  2868: aconst_null    
        //  2869: aload_2        
        //  2870: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLocalContext:()Lcom/intellij/psi/PsiElement;
        //  2873: iconst_0       
        //  2874: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;Z)V
        //  2877: astore          13
        //  2879: aload           13
        //  2881: aload           6
        //  2883: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.setLambda:(Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;)V
        //  2886: aload_0        
        //  2887: aload           10
        //  2889: aload           13
        //  2891: aload           12
        //  2893: aload           7
        //  2895: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;Ljava/util/List;Ljava/util/List;)V
        //  2898: aload           7
        //  2900: getstatic       com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.variablesComparator:Ljava/util/Comparator;
        //  2903: invokestatic    com/intellij/util/containers/ContainerUtil.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //  2906: aload           12
        //  2908: checkcast       Ljava/util/ArrayList;
        //  2911: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  2914: aload           7
        //  2916: checkcast       Ljava/util/ArrayList;
        //  2919: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  2922: goto            2679
        //  2925: aload           6
        //  2927: ifnonnull       2970
        //  2930: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //  2933: dup            
        //  2934: aload_0        
        //  2935: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  2938: aload_0        
        //  2939: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  2942: aload_0        
        //  2943: aload_1        
        //  2944: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  2947: aload_0        
        //  2948: aload_1        
        //  2949: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  2952: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  2955: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  2958: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  2961: aload           4
        //  2963: aload           5
        //  2965: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  2968: astore          6
        //  2970: aload           6
        //  2972: areturn        
        //  2973: aload_3        
        //  2974: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NEW_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2977: if_acmpne       3255
        //  2980: new             Ljava/util/ArrayList;
        //  2983: dup            
        //  2984: invokespecial   java/util/ArrayList.<init>:()V
        //  2987: astore          4
        //  2989: aconst_null    
        //  2990: astore          5
        //  2992: iconst_0       
        //  2993: istore          6
        //  2995: aload_0        
        //  2996: aload_1        
        //  2997: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  3000: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  3005: astore          7
        //  3007: aload           7
        //  3009: invokeinterface java/util/Iterator.hasNext:()Z
        //  3014: ifeq            3211
        //  3017: aload           7
        //  3019: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3024: astore          8
        //  3026: aload_0        
        //  3027: aload           8
        //  3029: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  3032: astore          9
        //  3034: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3037: aload           9
        //  3039: if_acmpne       3117
        //  3042: aload_0        
        //  3043: aload           8
        //  3045: aload_0        
        //  3046: aload_2        
        //  3047: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;
        //  3050: aconst_null    
        //  3051: aload_2        
        //  3052: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.processTypeElement:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/types/OCTypeBuilder;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  3055: astore          5
        //  3057: aload_0        
        //  3058: aload           8
        //  3060: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  3063: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  3068: astore          10
        //  3070: aload           10
        //  3072: invokeinterface java/util/Iterator.hasNext:()Z
        //  3077: ifeq            3114
        //  3080: aload           10
        //  3082: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3087: astore          11
        //  3089: aload_0        
        //  3090: aload           11
        //  3092: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  3095: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3098: if_acmpne       3111
        //  3101: iinc            6, 1
        //  3104: goto            3111
        //  3107: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3110: athrow         
        //  3111: goto            3070
        //  3114: goto            3208
        //  3117: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3120: aload           9
        //  3122: if_acmpne       3208
        //  3125: aload_0        
        //  3126: aload           8
        //  3128: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  3131: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  3136: astore          10
        //  3138: aload           10
        //  3140: invokeinterface java/util/Iterator.hasNext:()Z
        //  3145: ifeq            3208
        //  3148: aload           10
        //  3150: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3155: astore          11
        //  3157: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  3160: aload_0        
        //  3161: aload           11
        //  3163: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  3166: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  3169: ifeq            3205
        //  3172: aload_0        
        //  3173: aload           11
        //  3175: aload_2        
        //  3176: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  3179: astore          12
        //  3181: aload           12
        //  3183: ifnull          3203
        //  3186: aload           4
        //  3188: aload           12
        //  3190: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  3195: pop            
        //  3196: goto            3205
        //  3199: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3202: athrow         
        //  3203: aconst_null    
        //  3204: areturn        
        //  3205: goto            3138
        //  3208: goto            3007
        //  3211: aload           5
        //  3213: ifnull          3252
        //  3216: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol;
        //  3219: dup            
        //  3220: aload_0        
        //  3221: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  3224: aload_0        
        //  3225: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  3228: aload_0        
        //  3229: aload_1        
        //  3230: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  3233: aload_0        
        //  3234: aload_1        
        //  3235: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  3238: aload           5
        //  3240: iload           6
        //  3242: aload           4
        //  3244: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;ILjava/util/List;)V
        //  3247: areturn        
        //  3248: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3251: athrow         
        //  3252: goto            3357
        //  3255: aload_3        
        //  3256: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_VARIADIC_PACK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3259: if_acmpne       3357
        //  3262: aload_0        
        //  3263: aload_1        
        //  3264: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //  3267: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  3272: astore          4
        //  3274: aload           4
        //  3276: invokeinterface java/util/Iterator.hasNext:()Z
        //  3281: ifeq            3357
        //  3284: aload           4
        //  3286: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  3291: astore          5
        //  3293: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  3296: aload_0        
        //  3297: aload           5
        //  3299: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //  3302: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  3305: ifeq            3354
        //  3308: aload_0        
        //  3309: aload           5
        //  3311: aload_2        
        //  3312: invokespecial   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //  3315: astore          6
        //  3317: aload           6
        //  3319: ifnull          3354
        //  3322: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCVariadicPackExpressionSymbol;
        //  3325: dup            
        //  3326: aload_0        
        //  3327: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.project:Lcom/intellij/openapi/project/Project;
        //  3330: aload_0        
        //  3331: getfield        com/jetbrains/cidr/lang/symbols/OCBuilderDriver.myVirtualFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //  3334: aload_0        
        //  3335: aload_1        
        //  3336: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.getComplexOffsetFromNode:(Ljava/lang/Object;)J
        //  3339: aload_0        
        //  3340: aload_1        
        //  3341: invokevirtual   com/jetbrains/cidr/lang/symbols/OCBuilderDriver.nodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //  3344: aload           6
        //  3346: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCVariadicPackExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //  3349: areturn        
        //  3350: invokestatic    com/jetbrains/cidr/lang/symbols/OCBuilderDriver.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3353: athrow         
        //  3354: goto            3274
        //  3357: aconst_null    
        //  3358: areturn        
        //    Signature:
        //  (TT;Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext<TT;>;)Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  149    165    168    172    Ljava/lang/IllegalArgumentException;
        //  207    222    222    226    Ljava/lang/IllegalArgumentException;
        //  229    244    244    248    Ljava/lang/IllegalArgumentException;
        //  251    261    264    268    Ljava/lang/IllegalArgumentException;
        //  256    273    276    280    Ljava/lang/IllegalArgumentException;
        //  268    312    312    316    Ljava/lang/IllegalArgumentException;
        //  319    333    336    340    Ljava/lang/IllegalArgumentException;
        //  385    401    404    408    Ljava/lang/IllegalArgumentException;
        //  436    451    451    455    Ljava/lang/IllegalArgumentException;
        //  458    468    471    475    Ljava/lang/IllegalArgumentException;
        //  463    505    505    509    Ljava/lang/IllegalArgumentException;
        //  512    526    529    533    Ljava/lang/IllegalArgumentException;
        //  578    594    597    601    Ljava/lang/IllegalArgumentException;
        //  629    644    644    648    Ljava/lang/IllegalArgumentException;
        //  651    661    664    668    Ljava/lang/IllegalArgumentException;
        //  656    675    678    682    Ljava/lang/IllegalArgumentException;
        //  668    714    714    718    Ljava/lang/IllegalArgumentException;
        //  806    822    825    829    Ljava/lang/IllegalArgumentException;
        //  855    870    870    874    Ljava/lang/IllegalArgumentException;
        //  877    892    892    896    Ljava/lang/IllegalArgumentException;
        //  899    914    914    918    Ljava/lang/IllegalArgumentException;
        //  921    931    934    938    Ljava/lang/IllegalArgumentException;
        //  926    943    946    950    Ljava/lang/IllegalArgumentException;
        //  938    982    982    986    Ljava/lang/IllegalArgumentException;
        //  1033   1055   1058   1062   Ljava/lang/IllegalArgumentException;
        //  1044   1085   1085   1089   Ljava/lang/IllegalArgumentException;
        //  1108   1127   1127   1131   Ljava/lang/IllegalArgumentException;
        //  1296   1316   1319   1323   Ljava/lang/IllegalArgumentException;
        //  1304   1328   1328   1332   Ljava/lang/IllegalArgumentException;
        //  1444   1454   1457   1461   Ljava/lang/IllegalArgumentException;
        //  1449   1495   1495   1499   Ljava/lang/IllegalArgumentException;
        //  1571   1608   1608   1612   Ljava/lang/IllegalArgumentException;
        //  1829   1847   1847   1851   Ljava/lang/IllegalArgumentException;
        //  1895   1916   1916   1920   Ljava/lang/IllegalArgumentException;
        //  1920   1938   1941   1945   Ljava/lang/IllegalArgumentException;
        //  2003   2019   2022   2026   Ljava/lang/IllegalArgumentException;
        //  2050   2060   2063   2067   Ljava/lang/IllegalArgumentException;
        //  2055   2097   2097   2101   Ljava/lang/IllegalArgumentException;
        //  2118   2131   2131   2135   Ljava/lang/IllegalArgumentException;
        //  2313   2348   2348   2352   Ljava/lang/IllegalArgumentException;
        //  2410   2436   2439   2443   Ljava/lang/IllegalArgumentException;
        //  2529   2555   2558   2562   Ljava/lang/IllegalArgumentException;
        //  2565   2624   2624   2628   Ljava/lang/IllegalArgumentException;
        //  2774   2790   2793   2797   Ljava/lang/IllegalArgumentException;
        //  2785   2805   2808   2812   Ljava/lang/IllegalArgumentException;
        //  3089   3104   3107   3111   Ljava/lang/IllegalArgumentException;
        //  3181   3199   3199   3203   Ljava/lang/IllegalArgumentException;
        //  3211   3248   3248   3252   Ljava/lang/IllegalArgumentException;
        //  3317   3350   3350   3354   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0268:
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
    
    private void a(@NotNull final T t, @NotNull final DeclarationContext<T> declarationContext, @NotNull final List<OCExpressionSymbol> list, @NotNull final List<OCDeclaratorSymbol> list2) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statement", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReturnsAndLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReturnsAndLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnExpressions", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReturnsAndLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "localVariables", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "collectReturnsAndLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCElementTypes.RETURN_STATEMENT) {
                for (final T next2 : this.getChildren(next)) {
                    if (OCElementTypes.EXPRESSIONS.contains(this.type(next2))) {
                        final OCExpressionSymbol a = this.a(next2, declarationContext);
                        try {
                            if (a == null) {
                                continue;
                            }
                            list.add(a);
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                    }
                }
            }
            else {
                try {
                    if (type == OCElementTypes.DECLARATION) {
                        this.processDeclaration(next, (Processor<OCSymbol>)(ocSymbol -> {
                            try {
                                if (list2 == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "localVariables", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "lambda$collectReturnsAndLocalSymbols$4"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw b(ex);
                            }
                            try {
                                if (ocSymbol instanceof OCDeclaratorSymbol) {
                                    list2.add(ocSymbol);
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw b(ex2);
                            }
                            return true;
                        }), declarationContext);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                if (OCElementTypes.BLOCK_STATEMENTS.contains(type)) {
                    final DeclarationContext declarationContext2 = new DeclarationContext<T>(null, next, declarationContext.getParentSymbol(), null, null, false);
                    declarationContext2.setLambda(declarationContext.getLambda());
                    this.a(next, (DeclarationContext<T>)declarationContext2, list, list2);
                }
                else {
                    try {
                        if (!OCElementTypes.STATEMENTS.contains(type)) {
                            continue;
                        }
                        this.a(next, declarationContext, list, list2);
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
            }
        }
    }
    
    private void b(@NotNull final T t, @NotNull final List<OCTypeParameterSymbol> list, @NotNull final DeclarationContext<T> declarationContext) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterListNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTemplateParameterList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "collector", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTemplateParameterList"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processTemplateParameterList"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final DeclarationContext<T> declarationContext2 = this.createDeclarationContext(declarationContext.getParentSymbol());
        declarationContext2.setTemplateValueParameter(true);
        for (final T next : this.getChildren(t)) {
            try {
                if (this.type(next) == OCElementTypes.CPP_TYPE_PARAMETER_DECLARATION) {
                    list.add(this.processTypeParameter(next, declarationContext2));
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                if (this.type(next) != OCElementTypes.PARAMETER_DECLARATION) {
                    continue;
                }
                this.processDeclaration(next, (Processor<OCSymbol>)(ocSymbol -> {
                    try {
                        if (list == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "collector", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "lambda$processTemplateParameterList$5"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        if (ocSymbol instanceof OCTypeParameterValueSymbol) {
                            list.add((OCTypeParameterSymbol<?>)ocSymbol);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    return true;
                }), declarationContext2);
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
    }
    
    private void a(@NotNull final T t, @Nullable final List<OCDeclaratorSymbol> list, @NotNull final List<OCType> list2, @NotNull final DeclarationContext<T> declarationContext, @Nullable final List<String> list3) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterListNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processParameterList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterTypes", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processParameterList"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processParameterList"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        int n = 0;
        for (final T next : this.getChildren(t)) {
            try {
                if (this.type(next) != OCElementTypes.PARAMETER_DECLARATION) {
                    continue;
                }
                declarationContext.setLambdaParameterIndex(n++);
                this.a(next, declarationContext, (Processor<OCDeclaratorSymbol>)(ocDeclaratorSymbol -> {
                    try {
                        if (list2 == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterTypes", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "lambda$processParameterList$6"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        if (list != null) {
                            list.add(ocDeclaratorSymbol);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        if (list3 != null) {
                            list3.add(ocDeclaratorSymbol.getName());
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    list2.add(ocDeclaratorSymbol.getType());
                    return true;
                }));
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        declarationContext.setLambdaParameterIndex(-1);
    }
    
    private void a(@NotNull final T t, @NotNull final DeclarationContext<T> declarationContext, @NotNull final Processor<OCDeclaratorSymbol> processor) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarationNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processParameterDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processParameterDeclaration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processParameterDeclaration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final DeclarationContext<T> declarationContext2 = new DeclarationContext<T>(OCSymbolKind.PARAMETER);
        declarationContext2.setAssumeNonNull(declarationContext.isAssumeNonNull());
        declarationContext2.myParentSymbol = declarationContext.myParentSymbol;
        declarationContext2.setLambdaParameterIndex(declarationContext.getLambdaParameterIndex());
        declarationContext2.setLocalContext(declarationContext.getLocalContext());
        this.processDeclaration(t, (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "lambda$processParameterDeclaration$7"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (ocSymbol instanceof OCDeclaratorSymbol) {
                    processor.process((Object)ocSymbol);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return true;
        }), declarationContext2);
    }
    
    public void processImportModuleStatement(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processImportModuleStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ArrayList arrayList = ContainerUtil.newArrayList();
        for (final T next : this.getChildren(t)) {
            try {
                if (this.type(next) != OCTokenTypes.IDENTIFIER) {
                    continue;
                }
                arrayList.add(this.nodeText(next));
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final int startOffset = this.nodeStructure.getStartOffset((Object)t);
        final int endOffset = this.nodeStructure.getEndOffset((Object)t);
        boolean b = false;
        Label_0163: {
            try {
                if (!OCModuleResolver.processModuleImports(this.context, arrayList, (Processor<VirtualFile>)(virtualFile -> {
                    ProgressManager.checkCanceled();
                    this.builder.process((Object)new OCIncludeSymbol(this.project, this.myVirtualFile, startOffset, virtualFile, new OCIncludeSymbol.IncludePath(virtualFile.getPath(), true), true, false, endOffset));
                    return true;
                }))) {
                    b = true;
                    break Label_0163;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            b = false;
        }
        if (!b) {
            final String guessedPath = OCModuleResolver.getGuessedPath(arrayList);
            if (guessedPath != null) {
                final OCIncludeSymbol.IncludePath includePath = new OCIncludeSymbol.IncludePath(guessedPath, true);
                final VirtualFile resolvePath = this.context.resolvePath(includePath, this.file);
                Label_0262: {
                    try {
                        this.builder.process((Object)new OCIncludeSymbol(this.project, this.myVirtualFile, startOffset, resolvePath, includePath, true, false, endOffset));
                        if (resolvePath != null) {
                            return;
                        }
                        final boolean b2 = OCBuilderDriver.LOG_UNRESOLVED_SYMBOLS;
                        if (b2) {
                            break Label_0262;
                        }
                        return;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    try {
                        final boolean b2 = OCBuilderDriver.LOG_UNRESOLVED_SYMBOLS;
                        if (b2) {
                            System.out.println("Can't resolve: " + includePath.getPath());
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                }
            }
        }
    }
    
    public void processImportDirective(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directiveNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processImportDirective"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        boolean b = false;
        boolean b2 = false;
        String nodeText = null;
        IElementType tokenType = null;
        for (final T next : this.getChildren(t)) {
            final IElementType type = this.type(next);
            if (type == OCTokenTypes.IMPORT_DIRECTIVE) {
                b2 = true;
            }
            else if (type == OCTokenTypes.INCLUDE_NEXT_DIRECTIVE) {
                b = true;
            }
            else {
                try {
                    if (type == OCTokenTypes.INCLUDE_DIRECTIVE_CONTENT) {
                        System.out.println("Raw import in processing: " + this.nodeText(next));
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                if (type == OCTokenTypes.HEADER_PATH_LITERAL) {
                    nodeText = this.nodeText(next);
                    tokenType = this.nodeStructure.getTokenType((Object)next);
                }
                else {
                    if (type != OCTokenTypes.HEADER_INLINED_PATH_LITERAL) {
                        continue;
                    }
                    nodeText = null;
                }
            }
        }
        if (nodeText != null) {
            PsiFile psiFile = this.file;
            Label_0308: {
                if (tokenType instanceof OCMacroForeignLeafType) {
                    final String macroName = ((OCMacroForeignLeafType)tokenType).getMacroName();
                    if (macroName != null) {
                        final VirtualFile resolvePath = this.context.resolvePath(OCInclusionContext.extractPath(macroName, true), this.file);
                        try {
                            if (resolvePath == null || !resolvePath.isValid()) {
                                break Label_0308;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        psiFile = ((OCFile)this.file).getManager().findFile(resolvePath);
                    }
                }
            }
            final OCIncludeSymbol.IncludePath path = OCInclusionContext.extractPath(nodeText, true);
            VirtualFile virtualFile = null;
            Label_0353: {
                try {
                    if (b) {
                        virtualFile = this.context.resolveNextPath(path, psiFile);
                        break Label_0353;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                virtualFile = this.context.resolvePath(path, psiFile);
            }
            final VirtualFile virtualFile2 = virtualFile;
            final OCIncludeSymbol ocIncludeSymbol = new OCIncludeSymbol(this.project, this.myVirtualFile, this.nodeStructure.getStartOffset((Object)t), virtualFile2, path, b2, b, this.nodeStructure.getEndOffset((Object)t));
            Label_0436: {
                try {
                    this.builder.process((Object)ocIncludeSymbol);
                    if (virtualFile2 != null) {
                        return;
                    }
                    final boolean b3 = OCBuilderDriver.LOG_UNRESOLVED_SYMBOLS;
                    if (b3) {
                        break Label_0436;
                    }
                    return;
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                try {
                    final boolean b3 = OCBuilderDriver.LOG_UNRESOLVED_SYMBOLS;
                    if (b3) {
                        System.out.println("Can't resolve: " + nodeText);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
        }
    }
    
    public void processDefineDirective(@NotNull final T t, final boolean b) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directiveNode", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processDefineDirective"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final List collect = ContainerUtil.collect((Iterator)this.getChildren(t).iterator());
        int i = 0;
        while (i < collect.size()) {
            final T value = collect.get(i);
            final IElementType type = this.type(value);
            Label_0131: {
                Label_0403: {
                    Label_0116: {
                        try {
                            if (type == OCTokenTypes.IDENTIFIER) {
                                break Label_0131;
                            }
                            final TokenSet set = OCTokenTypes.KEYWORDS;
                            final OCElementType ocElementType = (OCElementType)type;
                            final boolean b2 = set.contains((IElementType)ocElementType);
                            if (!b2) {
                                break Label_0116;
                            }
                            break Label_0131;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final TokenSet set = OCTokenTypes.KEYWORDS;
                            final OCElementType ocElementType = (OCElementType)type;
                            final boolean b2 = set.contains((IElementType)ocElementType);
                            if (!b2) {
                                if (type != OCElementTypes.MACRO_REF) {
                                    break Label_0403;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    break Label_0131;
                }
                ++i;
                continue;
            }
            final int startOffset = this.nodeStructure.getStartOffset((Object)value);
            String s;
            if (this.nodeStructure.getTokenType((Object)value) instanceof OCMacroForeignLeafType) {
                final StringBuilder sb = new StringBuilder();
                for (int j = i; j < collect.size(); ++j) {
                    final T value2 = collect.get(j);
                    try {
                        if (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(this.type(value2))) {
                            sb.append(" ");
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    sb.append(this.nodeTextNoIntern(value2, true));
                }
                s = sb.toString();
            }
            else {
                s = this.text.substring(startOffset, this.nodeStructure.getEndOffset((Object)t));
            }
            if (b) {
                final OCMacroSymbol fromDirectiveContent = OCMacroSymbol.parseFromDirectiveContent(s, (OCFile)this.file, startOffset);
                try {
                    if (fromDirectiveContent != null) {
                        this.context.define(fromDirectiveContent);
                        this.builder.process((Object)fromDirectiveContent);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                break;
            }
            final OCUndefMacroSymbol fromDirectiveContent2 = OCUndefMacroSymbol.parseFromDirectiveContent(s, (OCFile)this.file, this.nodeStructure.getStartOffset((Object)value));
            try {
                if (fromDirectiveContent2 != null) {
                    this.context.undef(fromDirectiveContent2.getName());
                    this.builder.process((Object)fromDirectiveContent2);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            break;
        }
    }
    
    private void i(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "processPragma"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Iterator<T> iterator = this.getChildren(t).iterator();
        while (iterator.hasNext()) {
            final IElementType type = this.type(iterator.next());
            if (type instanceof OCPragmaOnceContentElementType) {
                final VirtualFile file = ((OCPragmaOnceContentElementType)type).getFile();
                if (file != null) {
                    this.builder.process((Object)OCMacroSymbol.inclusionGuard(OCInclusionContextUtil.pragmaOnceId(file)));
                    break;
                }
                break;
            }
        }
    }
    
    @NotNull
    private OCTypeBuilder a(@NotNull final DeclarationContext declarationContext) {
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createTypeBuilder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCTypeBuilder a;
        try {
            a = this.a(this.myLanguageKind, declarationContext);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createTypeBuilder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    private OCTypeBuilder a(@NotNull final OCLanguageKind ocLanguageKind, @NotNull final DeclarationContext declarationContext) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createTypeBuilder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (declarationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createTypeBuilder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCTypeBuilder ocTypeBuilder;
        try {
            ocTypeBuilder = new OCTypeBuilder(ocLanguageKind, this.project, declarationContext);
            if (ocTypeBuilder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCBuilderDriver", "createTypeBuilder"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return ocTypeBuilder;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
