// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbolsCache;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;

public class OCTypeNameVisitor implements OCTypeVisitor<String>
{
    private static final int MAX_STRUCT_PARAMETERS_IN_PRESENTATION = 5;
    public static final int MAX_TEMPLATE_DEPTH_IN_PRESENTATION = 10;
    private final OCType.Presentation myPresentation;
    private final boolean myAppendContextName;
    private final boolean myIncludeGlobalQualifier;
    private final boolean myAliased;
    private final boolean myIsInOldC;
    private final int myTemplateDepth;
    @Nullable
    private final OCResolveContext myContext;
    
    public static String getFunctionSignature(final PsiElement psiElement, final OCFunctionType ocFunctionType, final String s) {
        return new OCTypeNameVisitor(psiElement).a(ocFunctionType, s);
    }
    
    public static String getFunctionSignature(final PsiElement psiElement, final OCFunctionType ocFunctionType, final String s, final boolean b, final List<OCExpression> list) {
        return new OCTypeNameVisitor(psiElement).a(ocFunctionType, s, b, list);
    }
    
    public OCTypeNameVisitor(@Nullable final PsiElement psiElement) {
        this(OCType.Presentation.BEST, new OCResolveContext(psiElement));
    }
    
    public OCTypeNameVisitor(final OCType.Presentation presentation, @Nullable final PsiElement psiElement) {
        this(presentation, true, true, true, new OCResolveContext(psiElement), 0);
    }
    
    public OCTypeNameVisitor(final OCType.Presentation presentation, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor", "<init>"));
        }
        this(presentation, true, true, true, ocResolveContext, 0);
    }
    
    public OCTypeNameVisitor(final OCType.Presentation presentation, final boolean b, final boolean b2, final boolean b3, @Nullable final PsiElement psiElement) {
        this(presentation, b, b2, b3, new OCResolveContext(psiElement), 0);
    }
    
    public OCTypeNameVisitor(final OCType.Presentation myPresentation, final boolean myAliased, final boolean myAppendContextName, final boolean myIncludeGlobalQualifier, @NotNull final OCResolveContext myContext, final int myTemplateDepth) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor", "<init>"));
        }
        this.myPresentation = myPresentation;
        this.myAliased = myAliased;
        this.myContext = myContext;
        this.myIsInOldC = OCCodeInsightUtil.isInPlainOldC(this.myContext.getElement());
        this.myAppendContextName = myAppendContextName;
        this.myIncludeGlobalQualifier = myIncludeGlobalQualifier;
        this.myTemplateDepth = myTemplateDepth;
    }
    
    @NotNull
    public String getName(final OCType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
        //     4: astore_2       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myPresentation:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //     9: getstatic       com/jetbrains/cidr/lang/types/OCType$Presentation.BEST:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //    12: if_acmpne       142
        //    15: aload_0        
        //    16: aload_1        
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myAliased:Z
        //    21: ifeq            39
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_2        
        //    32: goto            40
        //    35: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aconst_null    
        //    40: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //    43: astore_3       
        //    44: aload_3        
        //    45: ifnull          95
        //    48: aload_3        
        //    49: dup            
        //    50: ifnonnull       94
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: new             Ljava/lang/IllegalStateException;
        //    63: dup            
        //    64: ldc             "@NotNull method %s.%s must not return null"
        //    66: ldc             2
        //    68: anewarray       Ljava/lang/Object;
        //    71: dup            
        //    72: ldc             0
        //    74: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //    76: aastore        
        //    77: dup            
        //    78: ldc             1
        //    80: ldc             "getName"
        //    82: aastore        
        //    83: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    86: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    89: athrow         
        //    90: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: areturn        
        //    95: aload_1        
        //    96: aload_0        
        //    97: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   100: checkcast       Ljava/lang/String;
        //   103: dup            
        //   104: ifnonnull       141
        //   107: new             Ljava/lang/IllegalStateException;
        //   110: dup            
        //   111: ldc             "@NotNull method %s.%s must not return null"
        //   113: ldc             2
        //   115: anewarray       Ljava/lang/Object;
        //   118: dup            
        //   119: ldc             0
        //   121: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   123: aastore        
        //   124: dup            
        //   125: ldc             1
        //   127: ldc             "getName"
        //   129: aastore        
        //   130: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   133: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   136: athrow         
        //   137: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: areturn        
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myAliased:Z
        //   146: ifeq            207
        //   149: aload_2        
        //   150: ifnull          207
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload_2        
        //   161: dup            
        //   162: ifnonnull       206
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: new             Ljava/lang/IllegalStateException;
        //   175: dup            
        //   176: ldc             "@NotNull method %s.%s must not return null"
        //   178: ldc             2
        //   180: anewarray       Ljava/lang/Object;
        //   183: dup            
        //   184: ldc             0
        //   186: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   188: aastore        
        //   189: dup            
        //   190: ldc             1
        //   192: ldc             "getName"
        //   194: aastore        
        //   195: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   198: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   201: athrow         
        //   202: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: areturn        
        //   207: aload_1        
        //   208: aload_0        
        //   209: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   212: checkcast       Ljava/lang/String;
        //   215: dup            
        //   216: ifnonnull       253
        //   219: new             Ljava/lang/IllegalStateException;
        //   222: dup            
        //   223: ldc             "@NotNull method %s.%s must not return null"
        //   225: ldc             2
        //   227: anewarray       Ljava/lang/Object;
        //   230: dup            
        //   231: ldc             0
        //   233: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   235: aastore        
        //   236: dup            
        //   237: ldc             1
        //   239: ldc             "getName"
        //   241: aastore        
        //   242: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   245: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   248: athrow         
        //   249: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      24     27     31     Ljava/lang/IllegalArgumentException;
        //  15     35     35     39     Ljava/lang/IllegalArgumentException;
        //  44     53     56     60     Ljava/lang/IllegalArgumentException;
        //  48     90     90     94     Ljava/lang/IllegalArgumentException;
        //  95     137    137    141    Ljava/lang/IllegalArgumentException;
        //  142    153    156    160    Ljava/lang/IllegalArgumentException;
        //  149    165    168    172    Ljava/lang/IllegalArgumentException;
        //  160    202    202    206    Ljava/lang/IllegalArgumentException;
        //  207    249    249    253    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0160:
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
    private String a(final OCType p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          18
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     8: ifnonnull       24
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aconst_null    
        //    19: areturn        
        //    20: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: ldc             "instancetype"
        //    26: aload_2        
        //    27: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    30: ifeq            129
        //    33: aload_0        
        //    34: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    37: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //    40: iconst_0       
        //    41: iconst_1       
        //    42: anewarray       Ljava/lang/Class;
        //    45: dup            
        //    46: iconst_0       
        //    47: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //    49: aastore        
        //    50: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    53: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    56: astore_3       
        //    57: aload_1        
        //    58: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    61: astore          4
        //    63: aload_3        
        //    64: ifnull          80
        //    67: aload_3        
        //    68: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    73: goto            81
        //    76: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aconst_null    
        //    81: astore          5
        //    83: aload           5
        //    85: ifnull          129
        //    88: aload           4
        //    90: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    93: ifeq            129
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload           4
        //   105: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   108: aload           5
        //   110: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isAncestorOf:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Z
        //   113: ifeq            129
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_2        
        //   124: areturn        
        //   125: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: ldc             "const"
        //   131: astore_3       
        //   132: aload_2        
        //   133: new             Ljava/lang/StringBuilder;
        //   136: dup            
        //   137: invokespecial   java/lang/StringBuilder.<init>:()V
        //   140: ldc             " "
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: aload_3        
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   152: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   155: ifeq            178
        //   158: aload_2        
        //   159: iconst_0       
        //   160: aload_2        
        //   161: invokevirtual   java/lang/String.length:()I
        //   164: aload_3        
        //   165: invokevirtual   java/lang/String.length:()I
        //   168: isub           
        //   169: iconst_1       
        //   170: isub           
        //   171: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   174: astore_2       
        //   175: goto            181
        //   178: ldc             ""
        //   180: astore_3       
        //   181: new             Ljava/util/ArrayList;
        //   184: dup            
        //   185: invokespecial   java/util/ArrayList.<init>:()V
        //   188: astore          4
        //   190: aload_2        
        //   191: aload           4
        //   193: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/String;Ljava/util/List;)V
        //   196: new             Lcom/intellij/openapi/util/Ref;
        //   199: dup            
        //   200: ldc             ""
        //   202: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   205: astore          5
        //   207: aload           4
        //   209: iconst_0       
        //   210: aload           4
        //   212: iconst_0       
        //   213: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   218: checkcast       Ljava/lang/String;
        //   221: aload           5
        //   223: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/String;Lcom/intellij/openapi/util/Ref;)Ljava/lang/String;
        //   226: invokeinterface java/util/List.set:(ILjava/lang/Object;)Ljava/lang/Object;
        //   231: pop            
        //   232: aload           5
        //   234: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   237: checkcast       Ljava/lang/String;
        //   240: ldc             "const"
        //   242: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   245: ifeq            251
        //   248: ldc             "const"
        //   250: astore_3       
        //   251: aload_0        
        //   252: aload_1        
        //   253: aload           4
        //   255: iconst_0       
        //   256: aconst_null    
        //   257: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;ILcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)Ljava/lang/String;
        //   260: astore          6
        //   262: aload           6
        //   264: ifnull          288
        //   267: aload           6
        //   269: aload_1        
        //   270: aload_0        
        //   271: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   274: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   277: aload_3        
        //   278: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.addTypeQualifier:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;Ljava/lang/String;)Ljava/lang/String;
        //   281: goto            289
        //   284: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: aconst_null    
        //   289: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      20     20     24     Ljava/lang/IllegalArgumentException;
        //  63     76     76     80     Ljava/lang/IllegalArgumentException;
        //  83     96     99     103    Ljava/lang/IllegalArgumentException;
        //  88     116    119    123    Ljava/lang/IllegalArgumentException;
        //  103    125    125    129    Ljava/lang/IllegalArgumentException;
        //  262    284    284    288    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    private String a(final OCType ocType, final List<String> list, final int n, @Nullable OCQualifiedName a) {
        try {
            if (this.myContext == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (list.size() - 1 == n) {
            a = a(a, list.get(n));
            final OCReferenceType build = new OCReferenceTypeBuilder(a(a), this.myContext.getElement()).build();
            try {
                if (ocType.equalsAfterResolving(build, this.myContext, true, true)) {
                    return a.getCanonicalName(true);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return null;
        }
        try {
            if (list.get(n).isEmpty()) {
                return this.a(ocType, list, n + 1, a);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String a2 = this.a(ocType, list, n + 1, a);
        try {
            if (a2 != null) {
                return a2;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return this.a(ocType, list, n + 1, a(a, list.get(n)));
    }
    
    private static String a(final boolean b, final String s) {
        try {
            if (!b) {
                return s;
            }
            final String s2 = s;
            final String s3 = "__kindof ";
            final boolean b2 = s2.startsWith(s3);
            if (b2) {
                return s;
            }
            return "__kindof " + s;
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            final String s2 = s;
            final String s3 = "__kindof ";
            final boolean b2 = s2.startsWith(s3);
            if (b2) {
                return s;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return "__kindof " + s;
    }
    
    private String b(final OCType ocType, final String s) {
        try {
            if (this.myContext != null) {
                final Project project = this.myContext.getProject();
                return CVQualifiers.appendCVQualifiers(s, ocType, project);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = null;
        return CVQualifiers.appendCVQualifiers(s, ocType, project);
    }
    
    @NotNull
    public static String addTypeQualifier(@NotNull final String p0, @NotNull final OCType p1, @Nullable final Project p2, @NotNull final String p3) {
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
        //    18: ldc             "typeString"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addTypeQualifier"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "type"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "addTypeQualifier"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "qualifier"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "addTypeQualifier"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokevirtual   java/lang/String.isEmpty:()Z
        //   136: ifeq            186
        //   139: aload_0        
        //   140: dup            
        //   141: ifnonnull       185
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: new             Ljava/lang/IllegalStateException;
        //   154: dup            
        //   155: ldc             "@NotNull method %s.%s must not return null"
        //   157: ldc             2
        //   159: anewarray       Ljava/lang/Object;
        //   162: dup            
        //   163: ldc             0
        //   165: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   167: aastore        
        //   168: dup            
        //   169: ldc             1
        //   171: ldc             "addTypeQualifier"
        //   173: aastore        
        //   174: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   177: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   180: athrow         
        //   181: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: areturn        
        //   186: aload_2        
        //   187: ifnull          209
        //   190: aload_2        
        //   191: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   194: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   196: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   199: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   202: goto            210
        //   205: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aconst_null    
        //   210: astore          4
        //   212: aload_1        
        //   213: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   216: ifne            263
        //   219: aload_1        
        //   220: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   223: ifne            263
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload           4
        //   235: ifnull          366
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload           4
        //   247: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TYPE_QUALIFIERS_PLACEMENT:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$Placement;
        //   250: getstatic       com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$Placement.AFTER:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$Placement;
        //   253: if_acmpne       366
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload_0        
        //   264: new             Ljava/lang/StringBuilder;
        //   267: dup            
        //   268: invokespecial   java/lang/StringBuilder.<init>:()V
        //   271: ldc             " "
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: aload_3        
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   283: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   286: ifeq            304
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload_0        
        //   297: goto            327
        //   300: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: new             Ljava/lang/StringBuilder;
        //   307: dup            
        //   308: invokespecial   java/lang/StringBuilder.<init>:()V
        //   311: aload_0        
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: ldc             " "
        //   317: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   320: aload_3        
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   327: dup            
        //   328: ifnonnull       365
        //   331: new             Ljava/lang/IllegalStateException;
        //   334: dup            
        //   335: ldc             "@NotNull method %s.%s must not return null"
        //   337: ldc             2
        //   339: anewarray       Ljava/lang/Object;
        //   342: dup            
        //   343: ldc             0
        //   345: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   347: aastore        
        //   348: dup            
        //   349: ldc             1
        //   351: ldc             "addTypeQualifier"
        //   353: aastore        
        //   354: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   357: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   360: athrow         
        //   361: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   364: athrow         
        //   365: areturn        
        //   366: aload_0        
        //   367: new             Ljava/lang/StringBuilder;
        //   370: dup            
        //   371: invokespecial   java/lang/StringBuilder.<init>:()V
        //   374: aload_3        
        //   375: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: ldc             " "
        //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   383: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   386: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   389: ifeq            400
        //   392: aload_0        
        //   393: goto            423
        //   396: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: new             Ljava/lang/StringBuilder;
        //   403: dup            
        //   404: invokespecial   java/lang/StringBuilder.<init>:()V
        //   407: aload_3        
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: ldc             " "
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: aload_0        
        //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   423: dup            
        //   424: ifnonnull       461
        //   427: new             Ljava/lang/IllegalStateException;
        //   430: dup            
        //   431: ldc             "@NotNull method %s.%s must not return null"
        //   433: ldc             2
        //   435: anewarray       Ljava/lang/Object;
        //   438: dup            
        //   439: ldc             0
        //   441: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   443: aastore        
        //   444: dup            
        //   445: ldc             1
        //   447: ldc             "addTypeQualifier"
        //   449: aastore        
        //   450: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   453: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   456: athrow         
        //   457: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    144    147    151    Ljava/lang/IllegalArgumentException;
        //  139    181    181    185    Ljava/lang/IllegalArgumentException;
        //  186    205    205    209    Ljava/lang/IllegalArgumentException;
        //  212    226    229    233    Ljava/lang/IllegalArgumentException;
        //  219    238    241    245    Ljava/lang/IllegalArgumentException;
        //  233    256    259    263    Ljava/lang/IllegalArgumentException;
        //  245    289    292    296    Ljava/lang/IllegalArgumentException;
        //  263    300    300    304    Ljava/lang/IllegalArgumentException;
        //  327    361    361    365    Ljava/lang/IllegalArgumentException;
        //  366    396    396    400    Ljava/lang/IllegalArgumentException;
        //  423    457    457    461    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0233:
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
    public static String removeTypeQualifier(@NotNull final String p0, @NotNull final OCType p1, @Nullable final Project p2, @NotNull final String p3) {
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
        //    18: ldc             "typeString"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "removeTypeQualifier"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "type"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "removeTypeQualifier"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "qualifier"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "removeTypeQualifier"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: ifnull          155
        //   136: aload_2        
        //   137: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   140: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   142: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   145: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   148: goto            156
        //   151: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: aconst_null    
        //   156: astore          4
        //   158: aload_1        
        //   159: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   162: ifne            195
        //   165: aload           4
        //   167: ifnull          291
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload           4
        //   179: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TYPE_QUALIFIERS_PLACEMENT:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$Placement;
        //   182: getstatic       com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$Placement.AFTER:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$Placement;
        //   185: if_acmpne       291
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_0        
        //   196: new             Ljava/lang/StringBuilder;
        //   199: dup            
        //   200: invokespecial   java/lang/StringBuilder.<init>:()V
        //   203: ldc             " "
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: aload_3        
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   215: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   218: ifeq            251
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: aload_0        
        //   229: iconst_0       
        //   230: aload_0        
        //   231: invokevirtual   java/lang/String.length:()I
        //   234: aload_3        
        //   235: invokevirtual   java/lang/String.length:()I
        //   238: isub           
        //   239: iconst_1       
        //   240: isub           
        //   241: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   244: goto            252
        //   247: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_0        
        //   252: dup            
        //   253: ifnonnull       290
        //   256: new             Ljava/lang/IllegalStateException;
        //   259: dup            
        //   260: ldc             "@NotNull method %s.%s must not return null"
        //   262: ldc             2
        //   264: anewarray       Ljava/lang/Object;
        //   267: dup            
        //   268: ldc             0
        //   270: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   272: aastore        
        //   273: dup            
        //   274: ldc             1
        //   276: ldc             "removeTypeQualifier"
        //   278: aastore        
        //   279: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   282: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   285: athrow         
        //   286: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: areturn        
        //   291: aload_0        
        //   292: new             Ljava/lang/StringBuilder;
        //   295: dup            
        //   296: invokespecial   java/lang/StringBuilder.<init>:()V
        //   299: aload_3        
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: ldc             " "
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   311: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   314: ifeq            334
        //   317: aload_0        
        //   318: aload_3        
        //   319: invokevirtual   java/lang/String.length:()I
        //   322: iconst_1       
        //   323: iadd           
        //   324: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   327: goto            335
        //   330: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload_0        
        //   335: dup            
        //   336: ifnonnull       373
        //   339: new             Ljava/lang/IllegalStateException;
        //   342: dup            
        //   343: ldc             "@NotNull method %s.%s must not return null"
        //   345: ldc             2
        //   347: anewarray       Ljava/lang/Object;
        //   350: dup            
        //   351: ldc             0
        //   353: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor"
        //   355: aastore        
        //   356: dup            
        //   357: ldc             1
        //   359: ldc             "removeTypeQualifier"
        //   361: aastore        
        //   362: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   365: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   368: athrow         
        //   369: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    151    151    155    Ljava/lang/IllegalArgumentException;
        //  158    170    173    177    Ljava/lang/IllegalArgumentException;
        //  165    188    191    195    Ljava/lang/IllegalArgumentException;
        //  177    221    224    228    Ljava/lang/IllegalArgumentException;
        //  195    247    247    251    Ljava/lang/IllegalArgumentException;
        //  252    286    286    290    Ljava/lang/IllegalArgumentException;
        //  291    330    330    334    Ljava/lang/IllegalArgumentException;
        //  335    369    369    373    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0177:
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
    
    private static OCQualifiedName a(final OCQualifiedName ocQualifiedName, final String s) {
        try {
            if (ocQualifiedName == null) {
                return OCQualifiedName.interned(s);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCQualifiedName.interned(ocQualifiedName, s);
    }
    
    private static OCQualifiedName a(final OCQualifiedName ocQualifiedName) {
        OCQualifiedName a = null;
        Label_0022: {
            try {
                if (ocQualifiedName.getQualifier() != null) {
                    a = a(ocQualifiedName.getQualifier());
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            a = null;
        }
        final OCQualifiedName ocQualifiedName2 = a;
        final String name = ocQualifiedName.getName();
        int index = 0;
        Label_0046: {
            try {
                if (name != null) {
                    index = name.indexOf(60);
                    break Label_0046;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            index = -1;
        }
        final int n = index;
        String substring = null;
        Label_0066: {
            try {
                if (n != -1) {
                    substring = name.substring(0, n);
                    break Label_0066;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            substring = name;
        }
        final String s = substring;
        try {
            if (ocQualifiedName2 != null) {
                return OCQualifiedName.interned(ocQualifiedName2, s);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return OCQualifiedName.interned(s);
    }
    
    private static String a(final String s, final Ref<String> ref) {
        int n = 0;
        Label_0038: {
            while (true) {
                Label_0028: {
                    try {
                        if (n >= s.length()) {
                            break Label_0038;
                        }
                        final String s2 = s;
                        final int n2 = n;
                        final char c = s2.charAt(n2);
                        final boolean b = Character.isLetter(c);
                        if (b) {
                            break Label_0028;
                        }
                        break Label_0038;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s2 = s;
                        final int n2 = n;
                        final char c = s2.charAt(n2);
                        final boolean b = Character.isLetter(c);
                        if (b) {
                            ++n;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                break;
            }
            try {
                if (n >= s.length() || s.charAt(n) != ' ') {
                    return s;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final String[] split = s.split(" ");
        try {
            ref.set((Object)split[0]);
            if (split.length > 1) {
                return split[1];
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return "";
    }
    
    private static void a(final String p0, final List<String> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: iconst_0       
        //     3: istore_3       
        //     4: iconst_0       
        //     5: istore          4
        //     7: iload           4
        //     9: aload_0        
        //    10: invokevirtual   java/lang/String.length:()I
        //    13: if_icmpge       139
        //    16: aload_0        
        //    17: iload           4
        //    19: invokevirtual   java/lang/String.charAt:(I)C
        //    22: istore          5
        //    24: iload           5
        //    26: bipush          60
        //    28: if_icmpne       41
        //    31: iinc            2, 1
        //    34: goto            133
        //    37: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: iload           5
        //    43: bipush          62
        //    45: if_icmpne       58
        //    48: iinc            2, -1
        //    51: goto            133
        //    54: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iload_2        
        //    59: ifne            133
        //    62: iload           5
        //    64: bipush          58
        //    66: if_icmpne       133
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: iload           4
        //    78: iconst_1       
        //    79: iadd           
        //    80: aload_0        
        //    81: invokevirtual   java/lang/String.length:()I
        //    84: if_icmpge       133
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_0        
        //    95: iload           4
        //    97: iconst_1       
        //    98: iadd           
        //    99: invokevirtual   java/lang/String.charAt:(I)C
        //   102: bipush          58
        //   104: if_icmpne       133
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload_1        
        //   115: aload_0        
        //   116: iload_3        
        //   117: iload           4
        //   119: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   122: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   127: pop            
        //   128: iload           4
        //   130: iconst_2       
        //   131: iadd           
        //   132: istore_3       
        //   133: iinc            4, 1
        //   136: goto            7
        //   139: iload_3        
        //   140: aload_0        
        //   141: invokevirtual   java/lang/String.length:()I
        //   144: if_icmpge       170
        //   147: aload_1        
        //   148: aload_0        
        //   149: iload_3        
        //   150: aload_0        
        //   151: invokevirtual   java/lang/String.length:()I
        //   154: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   157: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   162: pop            
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: return         
        //    Signature:
        //  (Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  24     37     37     41     Ljava/lang/IllegalArgumentException;
        //  41     54     54     58     Ljava/lang/IllegalArgumentException;
        //  58     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     87     90     94     Ljava/lang/IllegalArgumentException;
        //  76     107    110    114    Ljava/lang/IllegalArgumentException;
        //  139    163    166    170    Ljava/lang/IllegalArgumentException;
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
    public String visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return "...";
    }
    
    @Override
    public String visitFunctionType(final OCFunctionType ocFunctionType) {
        final String name = this.getName(ocFunctionType.getReturnType());
        try {
            if (this.myPresentation == OCType.Presentation.BEST) {
                return this.a(ocFunctionType, name, false, null);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.a(ocFunctionType, name);
    }
    
    private String a(final OCFunctionType ocFunctionType, final String s) {
        final StringBuilder sb = new StringBuilder(s);
        sb.append('(');
        sb.append(StringUtil.join((Collection)ocFunctionType.getParameterTypes(true), ocType -> this.getName(ocType), ","));
        sb.append(')');
        return this.b(ocFunctionType, sb.toString());
    }
    
    private String a(final OCFunctionType p0, final String p1, final boolean p2, @Nullable final List<OCExpression> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     4: ifnonnull       13
        //     7: aconst_null    
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: new             Ljava/util/HashSet;
        //    16: dup            
        //    17: invokespecial   java/util/HashSet.<init>:()V
        //    20: astore          5
        //    22: new             Ljava/lang/StringBuilder;
        //    25: dup            
        //    26: aload_2        
        //    27: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    30: astore          6
        //    32: aload           6
        //    34: bipush          40
        //    36: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    39: pop            
        //    40: aload_1        
        //    41: iconst_1       
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:(Z)Ljava/util/List;
        //    45: astore          7
        //    47: aload_1        
        //    48: iconst_1       
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterNames:(Z)Ljava/util/List;
        //    52: astore          8
        //    54: iconst_0       
        //    55: istore          9
        //    57: aload           7
        //    59: invokeinterface java/util/List.size:()I
        //    64: istore          10
        //    66: iload           9
        //    68: iload           10
        //    70: if_icmpge       280
        //    73: aload           7
        //    75: iload           9
        //    77: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    82: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //    85: astore          11
        //    87: iload           9
        //    89: ifle            107
        //    92: aload           6
        //    94: bipush          44
        //    96: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    99: pop            
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iload_3        
        //   108: ifeq            262
        //   111: aload           8
        //   113: ifnull          142
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload           8
        //   125: iload           9
        //   127: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   132: checkcast       Ljava/lang/String;
        //   135: goto            144
        //   138: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: ldc             ""
        //   144: astore          12
        //   146: aload           12
        //   148: invokevirtual   java/lang/String.isEmpty:()Z
        //   151: ifeq            229
        //   154: aload           11
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   159: ifne            229
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload           5
        //   171: aload           11
        //   173: aload           4
        //   175: ifnull          223
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: iload           9
        //   187: aload           4
        //   189: invokeinterface java/util/List.size:()I
        //   194: if_icmpge       223
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: aload           4
        //   206: iload           9
        //   208: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   213: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   216: goto            224
        //   219: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aconst_null    
        //   224: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForParameter:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/String;
        //   227: astore          12
        //   229: aload           5
        //   231: aload           12
        //   233: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   238: pop            
        //   239: aload           6
        //   241: aload           12
        //   243: aload           11
        //   245: aload_0        
        //   246: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   249: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   252: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declarationText:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   255: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   258: pop            
        //   259: goto            274
        //   262: aload           6
        //   264: aload_0        
        //   265: aload           11
        //   267: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.getName:(Lcom/jetbrains/cidr/lang/types/OCType;)Ljava/lang/String;
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: pop            
        //   274: iinc            9, 1
        //   277: goto            66
        //   280: aload           6
        //   282: bipush          41
        //   284: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   287: pop            
        //   288: aload_0        
        //   289: aload_1        
        //   290: aload           6
        //   292: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   295: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //   298: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/lang/String;ZLjava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  87     100    103    107    Ljava/lang/IllegalArgumentException;
        //  107    116    119    123    Ljava/lang/IllegalArgumentException;
        //  111    138    138    142    Ljava/lang/IllegalArgumentException;
        //  146    162    165    169    Ljava/lang/IllegalArgumentException;
        //  154    178    181    185    Ljava/lang/IllegalArgumentException;
        //  169    197    200    204    Ljava/lang/IllegalArgumentException;
        //  185    219    219    223    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
    public String visitMagicType(final OCMagicType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCMagicType.getMagicName:()Ljava/lang/String;
        //     4: astore_2       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     9: ifnull          26
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    16: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    19: goto            27
        //    22: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aconst_null    
        //    27: astore_3       
        //    28: aload_3        
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    32: ifeq            84
        //    35: ldc             "<unknown>"
        //    37: aload_2        
        //    38: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    41: ifeq            84
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_3        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //    60: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //    65: ifeq            84
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: ldc             "id"
        //    77: goto            85
        //    80: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_2        
        //    85: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      22     22     26     Ljava/lang/IllegalArgumentException;
        //  28     44     47     51     Ljava/lang/IllegalArgumentException;
        //  35     68     71     75     Ljava/lang/IllegalArgumentException;
        //  51     80     80     84     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0051:
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
    public String visitObjectType(final OCObjectType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAugmentedProtocols:()Ljava/util/List;
        //     4: astore_2       
        //     5: aload_1        
        //     6: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //     9: astore_3       
        //    10: aload_3        
        //    11: ifnull          62
        //    14: aload_3        
        //    15: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getGenericParameters:()Ljava/util/List;
        //    20: invokeinterface java/util/List.isEmpty:()Z
        //    25: ifne            62
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_3        
        //    36: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    41: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //    44: if_acmpeq       62
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: iconst_1       
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: iconst_0       
        //    63: istore          4
        //    65: aload_2        
        //    66: invokeinterface java/util/List.isEmpty:()Z
        //    71: ifeq            107
        //    74: iload           4
        //    76: ifne            107
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_1        
        //    87: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isKindof:()Z
        //    90: aload_0        
        //    91: aload_1        
        //    92: aload_1        
        //    93: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassName:()Ljava/lang/String;
        //    96: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //    99: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(ZLjava/lang/String;)Ljava/lang/String;
        //   102: areturn        
        //   103: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: new             Ljava/lang/StringBuilder;
        //   110: dup            
        //   111: invokespecial   java/lang/StringBuilder.<init>:()V
        //   114: astore          5
        //   116: aload           5
        //   118: iload           4
        //   120: ifeq            139
        //   123: aload_0        
        //   124: aload_1        
        //   125: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassName:()Ljava/lang/String;
        //   128: aload_3        
        //   129: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;)Ljava/lang/String;
        //   132: goto            143
        //   135: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_1        
        //   140: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassName:()Ljava/lang/String;
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: pop            
        //   147: aload_2        
        //   148: invokeinterface java/util/List.isEmpty:()Z
        //   153: ifne            196
        //   156: aload           5
        //   158: bipush          60
        //   160: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   163: pop            
        //   164: aload           5
        //   166: aload_2        
        //   167: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   172: ldc             ", "
        //   174: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Lcom/intellij/util/Function;Ljava/lang/String;)Ljava/lang/String;
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: pop            
        //   181: aload           5
        //   183: bipush          62
        //   185: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   188: pop            
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload_1        
        //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isKindof:()Z
        //   200: aload_0        
        //   201: aload_1        
        //   202: aload           5
        //   204: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   207: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //   210: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(ZLjava/lang/String;)Ljava/lang/String;
        //   213: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     28     31     35     Ljava/lang/IllegalArgumentException;
        //  14     47     50     54     Ljava/lang/IllegalArgumentException;
        //  35     58     58     62     Ljava/lang/IllegalArgumentException;
        //  65     79     82     86     Ljava/lang/IllegalArgumentException;
        //  74     103    103    107    Ljava/lang/IllegalArgumentException;
        //  116    135    135    139    Ljava/lang/IllegalArgumentException;
        //  143    189    192    196    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    public String visitArrayType(final OCArrayType ocArrayType) {
        return this.a(ocArrayType, null);
    }
    
    @NotNull
    private String a(final OCArrayType ocArrayType, @Nullable final String s) {
        final StringBuilder sb = new StringBuilder();
        OCType refType;
        OCArrayType ocArrayType2;
        for (refType = ocArrayType; refType instanceof OCArrayType; refType = ocArrayType2.getRefType()) {
            ocArrayType2 = (OCArrayType)refType;
            final int length = ocArrayType2.getLength();
            try {
                sb.append("[");
                if (ocArrayType2.hasLength()) {
                    sb.append(length);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            sb.append("]");
        }
        try {
            if (s != null) {
                sb.insert(0, s);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        String string;
        try {
            sb.insert(0, this.getName(refType));
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor", "getArrayTypeName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return string;
    }
    
    @Override
    public String visitPointerType(final OCPointerType ocPointerType) {
        if (ocPointerType.getTerminalType() instanceof OCFunctionType) {
            final OCFunctionType ocFunctionType = (OCFunctionType)ocPointerType.getTerminalType();
            final StringBuilder sb = new StringBuilder();
            sb.append(ocFunctionType.getReturnType().getName()).append(" (");
            final StringBuilder sb2 = new StringBuilder();
            try {
                if (ocPointerType.getClassQualifier() != null) {
                    sb.append(this.getName(ocPointerType.getClassQualifier())).append("::");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCType refType = ocPointerType;
            while (true) {
                StringBuilder sb3 = null;
                char c = '\0';
                Label_0123: {
                    Label_0112: {
                        try {
                            if (!(refType instanceof OCPointerType)) {
                                break;
                            }
                            sb3 = sb2;
                            final OCType ocType = refType;
                            final boolean b = ocType instanceof OCBlockPointerType;
                            if (b) {
                                break Label_0112;
                            }
                            break Label_0112;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            sb3 = sb2;
                            final OCType ocType = refType;
                            final boolean b = ocType instanceof OCBlockPointerType;
                            if (b) {
                                c = '^';
                                break Label_0123;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    c = '*';
                }
                sb3.append(c);
                refType = ((OCPointerType)refType).getRefType();
            }
            int i = sb2.length() - 1;
            try {
                while (i >= 0) {
                    sb.append(sb2.charAt(i));
                    --i;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            sb.append(')');
            return this.b(ocPointerType, this.a(ocFunctionType, sb.toString()));
        }
        StringBuilder sb4 = null;
        String string = null;
        Label_0250: {
            try {
                sb4 = new StringBuilder();
                if (ocPointerType.getARCAttribute() != null) {
                    string = ocPointerType.getARCAttribute().getTokenName() + " ";
                    break Label_0250;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            string = "";
        }
        final String string2 = sb4.append(string).append(this.getName(ocPointerType.getRefType())).toString();
        try {
            if (ocPointerType instanceof OCBlockPointerType) {
                return "invalid_block_pointer: ^(" + string2 + ")";
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (ocPointerType.getRefType() instanceof OCIdType) {
                return string2;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        String string3 = "*";
        if (ocPointerType.getClassQualifier() != null) {
            string3 = ocPointerType.getClassQualifier().getName() + "::" + string3;
        }
        return this.b(ocPointerType, string2 + " " + string3);
    }
    
    @Override
    public String visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return this.visitPointerType((OCPointerType)ocBlockPointerType);
    }
    
    @Override
    public String visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        String s = null;
        Label_0018: {
            try {
                if (ocCppReferenceType.isRvalueRef()) {
                    s = "&&";
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            s = "&";
        }
        final String s2 = s;
        if (ocCppReferenceType.getTerminalType() instanceof OCFunctionType) {
            final OCFunctionType ocFunctionType = (OCFunctionType)ocCppReferenceType.getTerminalType();
            return this.a(ocFunctionType, this.getName(ocFunctionType.getReturnType()) + " (" + s2 + ")");
        }
        try {
            if (ocCppReferenceType.getRefType() instanceof OCArrayType) {
                return this.a((OCArrayType)ocCppReferenceType.getRefType(), " (" + s2 + ")");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String name = this.getName(ocCppReferenceType.getRefType());
        try {
            if (ocCppReferenceType.getRefType() instanceof OCIdType) {
                return name;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.b(ocCppReferenceType, name + " " + s2);
    }
    
    @Override
    public String visitIdType(final OCIdType ocIdType) {
        return this.visitObjectType((OCObjectType)ocIdType);
    }
    
    @Override
    public String visitIntType(final OCIntType ocIntType) {
        return this.b(ocIntType, ocIntType.getText());
    }
    
    @Override
    public String visitRealType(final OCRealType ocRealType) {
        PsiFile file = null;
        Label_0022: {
            try {
                if (this.myContext != null) {
                    file = this.myContext.getFile();
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            file = null;
        }
        final PsiFile psiFile = file;
        Label_0128: {
            try {
                if (!(psiFile instanceof OCFile) || !ocRealType.isComplex()) {
                    break Label_0128;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            String s = ocRealType.getText() + " _Complex";
            if (!OCFileGlobalSymbolsCache.getInstance(psiFile.getProject()).lightTableForFile((OCFile)psiFile).processMembers("complex", (com.intellij.util.Processor<OCSymbol>)(ocSymbol -> {
                try {
                    if (!(ocSymbol instanceof OCMacroSymbol)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return false;
            }))) {
                s = "complex " + ocRealType.getText();
            }
            return this.b(ocRealType, s);
        }
        String s = ocRealType.getText();
        return this.b(ocRealType, s);
    }
    
    @Override
    public String visitReferenceType(final OCReferenceType ocReferenceType) {
        final OCQualifiedName qualifiedName = ocReferenceType.getReference().getQualifiedName();
        String canonicalName = null;
        Label_0156: {
            Label_0132: {
                Label_0108: {
                    if (this.myContext != null) {
                        final String name = qualifiedName.getName();
                        boolean b = false;
                        Label_0046: {
                            try {
                                if (OCSymbolReference.removeTypeToken(name).typeToken != null) {
                                    b = true;
                                    break Label_0046;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            b = false;
                        }
                        final boolean b2 = b;
                        Label_0089: {
                            Label_0070: {
                                try {
                                    if (this.myPresentation != OCType.Presentation.BEST) {
                                        break Label_0089;
                                    }
                                    final String s = name;
                                    if (s != null) {
                                        break Label_0070;
                                    }
                                    break Label_0089;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    final String s = name;
                                    if (s == null) {
                                        break Label_0089;
                                    }
                                    if (!b2) {
                                        break Label_0089;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            canonicalName = name;
                            break Label_0108;
                        }
                        canonicalName = qualifiedName.getCanonicalName(this.myPresentation, true, this.myContext, this.myTemplateDepth);
                    }
                    try {
                        if (!ocReferenceType.getProtocolNames().isEmpty()) {
                            break Label_0156;
                        }
                        final String s2 = canonicalName;
                        if (s2 != null) {
                            break Label_0132;
                        }
                        return "<unnamed>";
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final String s2 = canonicalName;
                    if (s2 != null) {
                        return a(ocReferenceType.isKindof(), this.b(ocReferenceType, canonicalName));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return "<unnamed>";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(canonicalName).append('<');
        StringUtil.join((Collection)ocReferenceType.getProtocolNames(), ", ", sb);
        sb.append('>');
        return a(ocReferenceType.isKindof(), this.b(ocReferenceType, sb.toString()));
    }
    
    @Override
    public String visitStructType(final OCStructType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //     4: iconst_0       
        //     5: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    10: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    13: astore_3       
        //    14: aload_0        
        //    15: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myIsInOldC:Z
        //    18: ifeq            43
        //    21: aload_3        
        //    22: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isUnnamed:()Z
        //    25: ifne            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: istore          4
        //    46: iconst_0       
        //    47: istore          5
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    53: ifnull          298
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myPresentation:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //    60: getstatic       com/jetbrains/cidr/lang/types/OCType$Presentation.BEST:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //    63: if_acmpne       298
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: aload_1        
        //    75: aload_1        
        //    76: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getAliasName:()Ljava/lang/String;
        //    79: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //    82: astore          6
        //    84: aload           6
        //    86: ifnull          96
        //    89: aload           6
        //    91: areturn        
        //    92: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_3        
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   101: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getResolvedQualifiedName:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   104: astore          7
        //   106: aload_1        
        //   107: aload_0        
        //   108: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getResolvedArguments:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   114: astore          9
        //   116: aload           9
        //   118: ifnull          191
        //   121: aload           7
        //   123: ifnull          191
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload           7
        //   135: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //   138: ifeq            178
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload           7
        //   150: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //   153: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments.getArguments:()Ljava/util/List;
        //   156: invokeinterface java/util/List.size:()I
        //   161: aload           9
        //   163: invokeinterface java/util/List.size:()I
        //   168: if_icmple       191
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: new             Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //   181: dup            
        //   182: aload           7
        //   184: aload           9
        //   186: invokespecial   com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;)V
        //   189: astore          7
        //   191: aload_3        
        //   192: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isUnnamed:()Z
        //   195: ifne            233
        //   198: aload           7
        //   200: ifnull          233
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aload           7
        //   212: aload_0        
        //   213: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myPresentation:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //   216: iconst_1       
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   221: aload_0        
        //   222: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myTemplateDepth:I
        //   225: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getCanonicalName:(Lcom/jetbrains/cidr/lang/types/OCType$Presentation;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;I)Ljava/lang/String;
        //   228: astore          8
        //   230: goto            249
        //   233: aload_1        
        //   234: aload_0        
        //   235: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   238: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   241: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   244: astore          8
        //   246: iconst_0       
        //   247: istore          4
        //   249: iconst_1       
        //   250: istore          5
        //   252: aload_0        
        //   253: aload_1        
        //   254: aload           8
        //   256: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //   259: astore          6
        //   261: aload           6
        //   263: ifnull          272
        //   266: aload           6
        //   268: astore_2       
        //   269: goto            295
        //   272: aload           8
        //   274: ldc             "::"
        //   276: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   279: ifeq            292
        //   282: aload           8
        //   284: iconst_2       
        //   285: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   288: astore_2       
        //   289: goto            295
        //   292: aload           8
        //   294: astore_2       
        //   295: goto            477
        //   298: aload_1        
        //   299: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //   302: ifnull          316
        //   305: aload_1        
        //   306: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //   309: goto            359
        //   312: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: aload_3        
        //   317: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isUnnamed:()Z
        //   320: ifeq            355
        //   323: new             Ljava/lang/StringBuilder;
        //   326: dup            
        //   327: invokespecial   java/lang/StringBuilder.<init>:()V
        //   330: ldc             "anonymous "
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: aload_3        
        //   336: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   339: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   348: goto            359
        //   351: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: aload_3        
        //   356: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
        //   359: astore          6
        //   361: aload_0        
        //   362: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myPresentation:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //   365: getstatic       com/jetbrains/cidr/lang/types/OCType$Presentation.FULL:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //   368: if_acmpne       474
        //   371: aload_0        
        //   372: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   375: ifnull          474
        //   378: goto            385
        //   381: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   384: athrow         
        //   385: aload_3        
        //   386: aload_0        
        //   387: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   390: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getResolvedQualifiedName:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   393: astore          7
        //   395: aload_0        
        //   396: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myIsInOldC:Z
        //   399: ifeq            421
        //   402: aload           7
        //   404: ifnull          421
        //   407: goto            414
        //   410: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: aload           7
        //   416: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.dropSuperQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   419: astore          7
        //   421: aload           7
        //   423: ifnull          440
        //   426: aload           7
        //   428: aload           6
        //   430: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.changeName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   433: goto            444
        //   436: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: aload_3        
        //   441: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   444: astore          8
        //   446: aload_3        
        //   447: aload           8
        //   449: aload_0        
        //   450: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myPresentation:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //   453: aload_0        
        //   454: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myIncludeGlobalQualifier:Z
        //   457: aload_0        
        //   458: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   461: aload_0        
        //   462: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.myTemplateDepth:I
        //   465: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getCanonicalName:(Lcom/jetbrains/cidr/lang/types/OCType$Presentation;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;I)Ljava/lang/String;
        //   468: iload           4
        //   470: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;Z)Ljava/lang/String;
        //   473: areturn        
        //   474: aload           6
        //   476: astore_2       
        //   477: aload_3        
        //   478: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   481: astore          6
        //   483: iload           5
        //   485: ifne            538
        //   488: aload           6
        //   490: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   493: if_acmpne       517
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload_3        
        //   504: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
        //   507: ifeq            538
        //   510: goto            517
        //   513: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   516: athrow         
        //   517: aload_0        
        //   518: aload_1        
        //   519: aload_3        
        //   520: aload_0        
        //   521: aload_2        
        //   522: aload_3        
        //   523: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;)Ljava/lang/String;
        //   526: iconst_0       
        //   527: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;Z)Ljava/lang/String;
        //   530: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //   533: areturn        
        //   534: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   537: athrow         
        //   538: aload_0        
        //   539: aload_1        
        //   540: aload_3        
        //   541: aload_2        
        //   542: iload           4
        //   544: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;Z)Ljava/lang/String;
        //   547: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Ljava/lang/String;
        //   550: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        //  49     66     69     73     Ljava/lang/IllegalArgumentException;
        //  84     92     92     96     Ljava/lang/IllegalArgumentException;
        //  116    126    129    133    Ljava/lang/IllegalArgumentException;
        //  121    141    144    148    Ljava/lang/IllegalArgumentException;
        //  133    171    174    178    Ljava/lang/IllegalArgumentException;
        //  191    203    206    210    Ljava/lang/IllegalArgumentException;
        //  298    312    312    316    Ljava/lang/IllegalArgumentException;
        //  316    351    351    355    Ljava/lang/IllegalArgumentException;
        //  361    378    381    385    Ljava/lang/IllegalArgumentException;
        //  395    407    410    414    Ljava/lang/IllegalArgumentException;
        //  421    436    436    440    Ljava/lang/IllegalArgumentException;
        //  483    496    499    503    Ljava/lang/IllegalArgumentException;
        //  488    510    513    517    Ljava/lang/IllegalArgumentException;
        //  503    534    534    538    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0133:
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
    
    private static String a(final OCStructSymbol ocStructSymbol, final String s, final boolean b) {
        try {
            if (b) {
                return ocStructSymbol.getKind().getNameLowercase() + " " + s;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private String a(final String s, final OCTemplateSymbol<? extends PsiElement> ocTemplateSymbol) {
        try {
            if (this.myContext == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType.Presentation myPresentation = null;
        boolean b = false;
        Label_0037: {
            try {
                myPresentation = this.myPresentation;
                if (this.myPresentation != OCType.Presentation.SHORT) {
                    b = true;
                    break Label_0037;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        final List<OCTypeArgument> typeArguments = getTypeArguments(myPresentation, ocTemplateSymbol, b, true, true, this.myContext);
        final StringBuilder sb = new StringBuilder(s);
        Label_0341: {
            Label_0333: {
                try {
                    if (typeArguments.size() <= 0) {
                        return sb.toString();
                    }
                    sb.append('<');
                    if (this.myTemplateDepth >= 10) {
                        break Label_0333;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                int n = 0;
                while (true) {
                    Label_0132: {
                        Label_0117: {
                            try {
                                if (n >= typeArguments.size()) {
                                    break;
                                }
                                final int n2 = n;
                                final int n3 = 5;
                                if (n2 > n3) {
                                    break Label_0117;
                                }
                                break Label_0132;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final int n2 = n;
                                final int n3 = 5;
                                if (n2 > n3) {
                                    sb.append("...");
                                    break;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            if (n != 0) {
                                sb.append(", ");
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    final OCTypeArgument ocTypeArgument = typeArguments.get(n);
                    Label_0324: {
                        Label_0224: {
                            Label_0186: {
                                try {
                                    if (!ocTemplateSymbol.isSpecialization()) {
                                        break Label_0224;
                                    }
                                    final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                                    if (ocTypeArgument2 != null) {
                                        break Label_0186;
                                    }
                                    break Label_0224;
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                                try {
                                    final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                                    if (ocTypeArgument2 != null) {
                                        sb.append(ocTypeArgument.getNameForPresentation(this.myPresentation, this.myContext, this.myIncludeGlobalQualifier, this.myTemplateDepth + 1));
                                        break Label_0324;
                                    }
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                            }
                            try {
                                if (ocTypeArgument instanceof OCType) {
                                    sb.append(this.getName((OCType)ocTypeArgument));
                                    break Label_0324;
                                }
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                        }
                        try {
                            if (ocTypeArgument != null) {
                                sb.append(ocTypeArgument.getNameForPresentation(this.myPresentation, this.myContext, this.myIncludeGlobalQualifier, this.myTemplateDepth + 1));
                                break Label_0324;
                            }
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                        sb.append(ocTemplateSymbol.getTemplateParameters().get(n).getName());
                    }
                    ++n;
                }
                break Label_0341;
            }
            sb.append("...");
        }
        sb.append(">");
        return sb.toString();
    }
    
    @NotNull
    public static List<OCTypeArgument> getTypeArguments(final OCType.Presentation presentation, final OCTemplateSymbol<?> ocTemplateSymbol, final boolean b, final boolean b2, final boolean b3, @NotNull OCResolveContext substituteFirst) {
        try {
            if (substituteFirst == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor", "getTypeArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeSubstitution substitution = ocTemplateSymbol.getSubstitution();
        if (b) {
            substituteFirst = substituteFirst.substituteFirst(substitution);
        }
        final ArrayList<OCType> list = new ArrayList<OCType>();
        final List templateSpecialization = ocTemplateSymbol.getTemplateSpecialization();
        if (templateSpecialization != null) {
            for (final OCTypeArgument ocTypeArgument : templateSpecialization) {
                Label_0138: {
                    try {
                        if (!b) {
                            break Label_0138;
                        }
                        final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                        final boolean b4 = ocTypeArgument2 instanceof OCType;
                        if (b4) {
                            break Label_0138;
                        }
                        break Label_0138;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                        final boolean b4 = ocTypeArgument2 instanceof OCType;
                        if (b4) {
                            list.add(((OCType)ocTypeArgument).resolve(substituteFirst));
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                list.add((OCType)ocTypeArgument);
            }
        }
        else {
            final Iterator iterator2 = ocTemplateSymbol.getTemplateParameters().iterator();
            while (iterator2.hasNext()) {
                final OCTypeArgument substitution2 = substitution.getSubstitutionFor(iterator2.next());
                Label_0239: {
                    try {
                        if (substitution2 != null) {
                            break Label_0239;
                        }
                        final boolean b5 = b2;
                        if (b5) {
                            break Label_0239;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final boolean b5 = b2;
                        if (!b5) {
                            continue;
                        }
                        list.add((OCType)substitution2);
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
            }
            boolean b7 = false;
            Label_0291: {
                Label_0282: {
                    try {
                        if (presentation != OCType.Presentation.SHORT) {
                            break Label_0282;
                        }
                        final OCTemplateSymbol ocTemplateSymbol2 = ocTemplateSymbol;
                        final boolean b6 = ocTemplateSymbol2 instanceof OCInterfaceSymbol;
                        if (b6) {
                            break Label_0282;
                        }
                        break Label_0282;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    try {
                        final OCTemplateSymbol ocTemplateSymbol2 = ocTemplateSymbol;
                        final boolean b6 = ocTemplateSymbol2 instanceof OCInterfaceSymbol;
                        if (b6) {
                            b7 = true;
                            break Label_0291;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                b7 = false;
            }
            if (b3 & b7) {
                for (int i = list.size() - 1; i >= 0; --i) {
                    final OCTypeParameterSymbol ocTypeParameterSymbol = (OCTypeParameterSymbol)ocTemplateSymbol.getTemplateParameters().get(i);
                    final OCType ocType = list.get(i);
                    try {
                        if (!(ocType instanceof OCType) || !(ocTypeParameterSymbol.getDefaultValue() instanceof OCType)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    final OCTypeEqualityAfterResolvingVisitor ocTypeEqualityAfterResolvingVisitor = new OCTypeEqualityAfterResolvingVisitor(substitution.substitute((OCType)ocTypeParameterSymbol.getDefaultValue(), substituteFirst).resolve(substituteFirst), true, substituteFirst);
                    try {
                        if (!ocType.accept((OCTypeVisitor<Boolean>)ocTypeEqualityAfterResolvingVisitor)) {
                            break;
                        }
                        list.remove(i);
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                }
            }
        }
        ArrayList<OCType> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor", "getTypeArguments"));
            }
        }
        catch (IllegalArgumentException ex10) {
            throw a(ex10);
        }
        return (List<OCTypeArgument>)list2;
    }
    
    @Override
    public String visitUnknownType(final OCUnknownType ocUnknownType) {
        return this.visitMagicType((OCMagicType)ocUnknownType);
    }
    
    @Override
    public String visitVoidType(final OCVoidType ocVoidType) {
        return this.b(ocVoidType, "void");
    }
    
    @Override
    public String visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return this.b(ocTypeParameterType, ocTypeParameterType.getSymbol().getName());
    }
    
    @Override
    public String visitAutoType(final OCAutoType ocAutoType) {
        return "auto";
    }
    
    @Override
    public String visitVariadicType(final OCVariadicType ocVariadicType) {
        return this.getName(ocVariadicType.getUnderlyingType()) + "...";
    }
    
    @Override
    public String visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        return StringUtil.join((Collection)ContainerUtil.map((Collection)ocExpansionPackType.getExpansions(), ocTypeArgument -> ocTypeArgument.getNameForPresentation(this.myPresentation, this.myContext, this.myIncludeGlobalQualifier, this.myTemplateDepth)), ", ");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
